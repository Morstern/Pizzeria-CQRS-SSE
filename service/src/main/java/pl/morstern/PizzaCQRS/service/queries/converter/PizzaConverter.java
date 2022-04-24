package pl.morstern.PizzaCQRS.service.queries.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaBE;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PizzaConverter {
    private final PizzaTypeConverter pizzaTypeConverter;

    @Autowired
    public PizzaConverter(PizzaTypeConverter pizzaTypeConverter) {
        this.pizzaTypeConverter = pizzaTypeConverter;
    }

    public List<PizzaDTO> convertBEsToDTOs(Collection<PizzaBE> pizzaBES, Language language) {
        return pizzaBES.stream()
                .map(pizzaBE -> convertBEtoDTO(pizzaBE, language))
                .collect(Collectors.toList());
    }

    private PizzaDTO convertBEtoDTO(PizzaBE pizzaBE, Language language){
        return PizzaDTO.builder()
                .pizzaSize(pizzaBE.getPizzaSize().toString())
                .pizzaTypeDTO(pizzaTypeConverter.convertBEtoDTO(pizzaBE.getPizzaType(), language))
                .build();
    }
}
