package pl.morstern.PizzaCQRS.service.queries.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Builder
public class PizzaTypeDTO {
    String pizzaName;
    List<IngredientDTO> ingredients;
}
