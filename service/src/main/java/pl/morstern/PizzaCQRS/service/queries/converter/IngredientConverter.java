package pl.morstern.PizzaCQRS.service.queries.converter;

import org.springframework.stereotype.Component;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.dto.IngredientDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.IngredientBE;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientConverter {
    public List<IngredientDTO> convertBEsToDTOs(Collection<IngredientBE> ingredientBEs, Language language){
        return ingredientBEs.stream()
                .map(ingredientBE -> convertBEtoDTO(ingredientBE, language))
                .collect(Collectors.toList());
    }

    private IngredientDTO convertBEtoDTO(IngredientBE ingredientBE, Language language){
        return IngredientDTO.builder()
                .name(getIngredientNameForLanguage(ingredientBE, language))
                .build();
    }

    private String getIngredientNameForLanguage(IngredientBE ingredientBE, Language language){
        switch(language){
            case EN:
                return ingredientBE.getEnglishName();
            case PL:
            default:
                return ingredientBE.getPolishName();

        }
    }
}
