package pl.lodz.p.it.tks.converter;

import org.springframework.core.convert.converter.Converter;
import pl.lodz.p.it.tks.model.ClientType;
import pl.lodz.p.it.tks.model.NormalClient;
import pl.lodz.p.it.tks.model.PremiumClient;
import pl.lodz.p.it.tks.model.RegularClient;

public class StringToTypeConverter implements Converter<String, ClientType> {

    @Override
    public ClientType convert(String s) {
        switch (s){
            case "Regular":
                return new RegularClient();
            case "Premium":
                return new PremiumClient();
        }
        return new NormalClient();
    }
}
