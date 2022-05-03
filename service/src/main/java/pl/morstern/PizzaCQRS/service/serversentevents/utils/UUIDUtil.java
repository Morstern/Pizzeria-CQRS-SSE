package pl.morstern.PizzaCQRS.service.serversentevents.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtil {
    public String generate(){
        return UUID.randomUUID().toString();
    }
}
