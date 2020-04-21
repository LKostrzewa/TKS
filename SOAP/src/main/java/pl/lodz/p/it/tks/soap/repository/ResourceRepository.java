package pl.lodz.p.it.tks.soap.repository;

import org.springframework.stereotype.Repository;
import pl.lodz.p.it.tks.soap.model.Resource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ResourceRepository {
    private final Map<String, Resource> resources = new HashMap<>();

    @PostConstruct
    public void initData() {
        resources.put("1", new Resource("1", 24.15));
        resources.put("2", new Resource("2", 17.91));
    }

    public Resource findResource(String id){
        return resources.get(id);
    }
}
