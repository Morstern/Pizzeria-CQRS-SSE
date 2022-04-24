package pl.morstern.PizzaCQRS.service.queries.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IngredientDTO {
    String name;
}
