package pl.morstern.PizzaCQRS.service.queries.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PizzaOrderDTO {
    private List<PizzaDTO> pizzas;
    private String orderStatus;
}
