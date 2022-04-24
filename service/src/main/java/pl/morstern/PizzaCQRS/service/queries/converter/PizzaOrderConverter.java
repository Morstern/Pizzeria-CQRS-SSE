package pl.morstern.PizzaCQRS.service.queries.converter;

import org.springframework.stereotype.Component;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaOrderDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaOrderBE;

@Component
public class PizzaOrderConverter {
    PizzaConverter pizzaConverter;

    public PizzaOrderConverter(PizzaConverter pizzaConverter) {
        this.pizzaConverter = pizzaConverter;
    }

    public PizzaOrderDTO convertBEtoDTO(PizzaOrderBE pizzaOrderBE, Language language) {
        return PizzaOrderDTO.builder()
                .orderStatus(pizzaOrderBE.getOrderStatus().toString())
                .pizzas(pizzaConverter.convertBEsToDTOs(pizzaOrderBE.getPizzas(), language))
                .build();
    }
}
