package pl.morstern.PizzaCQRS.service.queries.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaTypeDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaTypeBE;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PizzaTypeConverter {
    IngredientConverter ingredientConverter;

    @Autowired
    public PizzaTypeConverter(IngredientConverter ingredientConverter) {
        this.ingredientConverter = ingredientConverter;
    }

    public List<PizzaTypeDTO> convertBEsToDTOs(Collection<PizzaTypeBE> pizzaTypeBES, Language language){
        return pizzaTypeBES.stream()
                .map(pizzaTypeBE -> this.convertBEtoDTO(pizzaTypeBE, language))
                .collect(Collectors.toList());
    }

    public PizzaTypeDTO convertBEtoDTO(PizzaTypeBE pizzaTypeBE, Language language){
        return PizzaTypeDTO.builder()
                .pizzaName(pizzaTypeBE.getPizzaName())
                .ingredients(ingredientConverter.convertBEsToDTOs(pizzaTypeBE.getIngredients(), language))
                .build();
    }
}
