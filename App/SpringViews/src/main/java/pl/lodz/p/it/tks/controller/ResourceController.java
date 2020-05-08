package pl.lodz.p.it.tks.controller;

import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import pl.lodz.p.it.tks.dto.BallRoomDTO;
import pl.lodz.p.it.tks.dto.ResourceDTO;
import pl.lodz.p.it.tks.dto.TableDTO;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/resources/")
public class ResourceController {

    private String urlBase = "http://localhost:8080/api/resources";
    private RestTemplate rest;
    private HttpHeaders headers;

    public ResourceController() {
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");

        /*String keyStorePassword = "changeit";
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            keyStore.load(new FileInputStream(new File("C:\\Users\\Lukasz\\.keystore")),
                    keyStorePassword.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        SSLConnectionSocketFactory socketFactory = null;
        try {
            socketFactory = new SSLConnectionSocketFactory(
                    new SSLContextBuilder()
                            .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
                            .build(),
                    NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(
                socketFactory).build();*/

        HttpClient httpClient = HttpClients.createDefault();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        this.rest = new RestTemplate(requestFactory);
    }

    @GetMapping("/add-table")
    public ModelAndView showTableForm() {
        return new ModelAndView("tableForm", "table", new TableDTO());
    }

    @GetMapping("/add-room")
    public ModelAndView showBallRoomForm() {
        return new ModelAndView("ballRoomForm", "ballRoom", new BallRoomDTO());
    }

    @PostMapping("/add-table")
    public String addTable(@Valid @ModelAttribute TableDTO resource, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<TableDTO> entity = new HttpEntity<>(resource, headers);
            try{
                rest.exchange(urlBase + "/add-table", HttpMethod.POST, entity, String.class);
            }
            catch (HttpClientErrorException httpClientErrorException ){
                model.addAttribute("link", "/resources/add-table");
                model.addAttribute("msg", "resource of this id is already in database");
                return "exception";
            }
            return "redirect:/resources/";
        }
        return "tableForm";
    }

    @PostMapping("/add-room")
    public String addBallRoom(@Valid @ModelAttribute BallRoomDTO resource, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<BallRoomDTO> entity = new HttpEntity<>(resource, headers);
            rest.exchange(urlBase + "/add-room", HttpMethod.POST, entity, String.class);
            return "redirect:/resources/";
        }
        return "ballRoomForm";
    }

    @RequestMapping
    public ModelAndView showAllResources() {
        List<Object> resourceList = rest.exchange(urlBase + "", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Object>>() {
                }).getBody();
        List<ResourceDTO> resources = new ArrayList<>();
        for (Object obj : resourceList) {
            resources.add(getFromJson(obj));
        }
        return new ModelAndView("allResource", "resource", resources);
    }

    @GetMapping(path = "/all-tables")
    public ModelAndView showAllTables() {
        List<TableDTO> tables = rest.exchange(urlBase + "/all-tables", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<TableDTO>>() {
                }).getBody();
        return new ModelAndView("allResource", "resource", tables);
    }

    @GetMapping("/all-rooms")
    public ModelAndView getAllRooms() {
        List<BallRoomDTO> ballRooms = rest.exchange(urlBase + "/all-rooms", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<BallRoomDTO>>() {
                }).getBody();
        return new ModelAndView("allResource", "resource", ballRooms);
    }

    @GetMapping("/delete-resource/{id}")
    public String deleteResource(@PathVariable String id) {
        rest.delete(urlBase + "/delete-resource/" + id);
        return "redirect:/resources/";
    }

    private ResourceDTO getFromJson(Object obj) {
        Gson gson = new Gson();
        ResourceDTO resource;
        if(obj.toString().contains("numOfPeople")){
            resource = gson.fromJson(obj.toString(), TableDTO.class);
        }
        else {
            resource = gson.fromJson(obj.toString(), BallRoomDTO.class);
        }
        return resource;
    }

    @RequestMapping("/update-resource/{id}")
    public ModelAndView showUpdateForm(@PathVariable String id) {
        Object obj = rest.exchange(urlBase + "/get-resource/" + id, HttpMethod.GET,
                null, Object.class).getBody();
        ResourceDTO resource = getFromJson(obj);
        if (resource instanceof TableDTO) {
            return showUpdateTableForm((TableDTO) resource);
        } else {
            return showUpdateBallRoomForm((BallRoomDTO) resource);
        }
    }

    private ModelAndView showUpdateTableForm(TableDTO table) {
        return new ModelAndView("tableUpdateForm", "table", table);
    }

    @PostMapping("/update-table")
    public String updateTable(@Valid @ModelAttribute TableDTO table, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "tableUpdateForm";
        }
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TableDTO> entity = new HttpEntity<>(table, headers);
        rest.exchange(urlBase + "/update-table", HttpMethod.PUT, entity, String.class);
        return "redirect:/resources/";
    }

    private ModelAndView showUpdateBallRoomForm(BallRoomDTO ballRoom) {
        return new ModelAndView("ballRoomUpdateForm", "room", ballRoom);
    }

    @PostMapping("/update-room")
    public String updateBallRoom(@Valid @ModelAttribute BallRoomDTO ballRoom, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ballRoomUpdateForm";
        }
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BallRoomDTO> entity = new HttpEntity<>(ballRoom, headers);
        rest.exchange(urlBase + "/update-room", HttpMethod.PUT, entity, String.class);
        return "redirect:/resources/";
    }
}
