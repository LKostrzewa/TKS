package pl.lodz.p.it.tks.converter;

import org.springframework.core.convert.converter.Converter;
import pl.lodz.p.it.tks.dto.ClientTypeDTO;
import pl.lodz.p.it.tks.dto.NormalClientDTO;
import pl.lodz.p.it.tks.dto.PremiumClientDTO;
import pl.lodz.p.it.tks.dto.RegularClientDTO;

public class StringToTypeConverter implements Converter<String, ClientTypeDTO> {

    @Override
    public ClientTypeDTO convert(String s) {
        switch (s){
            case "Regular":
                return new RegularClientDTO();
            case "Premium":
                return new PremiumClientDTO();
        }
        return new NormalClientDTO();
    }
}
