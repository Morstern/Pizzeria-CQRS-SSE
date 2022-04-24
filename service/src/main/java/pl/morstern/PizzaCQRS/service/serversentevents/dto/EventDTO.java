package pl.morstern.PizzaCQRS.service.serversentevents.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class EventDTO {
    private String type;
    private Map<String, Object> body;
}