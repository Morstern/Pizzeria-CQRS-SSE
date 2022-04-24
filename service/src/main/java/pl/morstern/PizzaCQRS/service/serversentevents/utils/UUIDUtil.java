package pl.morstern.PizzaCQRS.service.serversentevents.utils;

import java.util.UUID;

public class UUIDUtil {
    public String generate(){
        return UUID.randomUUID().toString();
    }
}
