package pl.morstern.PizzaCQRS.service.queries.converter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.queries.dto.IngredientDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.IngredientBE;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IngredientConverterTest extends BaseUnitTest {

    @InjectMocks
    IngredientConverter ingredientConverter;

    private static final String NAME = "testName";
    private static final String ENGLISH_NAME = "englishName";
    private static final String POLISH_NAME = "polishName";

    @Test
    void shouldConvertBEsToDTOsWithPolishNames() {
        // given
        List<IngredientBE> ingredientBEs = List.of(
                IngredientBE.builder()
                        .name(NAME)
                        .englishName(ENGLISH_NAME)
                        .polishName(POLISH_NAME)
                        .build()
        );

        // when

        // then
        List<IngredientDTO> ingredientDTOs = ingredientConverter.convertBEsToDTOs(ingredientBEs, polishLanguage);

        assertThat(ingredientDTOs).extracting(IngredientDTO::getName).containsExactly(POLISH_NAME);
    }

    @Test
    void shouldConvertBEsToDTOsWithEnglishNames() {
        // given
        List<IngredientBE> ingredientBEs = List.of(
                IngredientBE.builder()
                        .name(NAME)
                        .englishName(ENGLISH_NAME)
                        .polishName(POLISH_NAME)
                        .build()
        );

        // when

        // then
        List<IngredientDTO> ingredientDTOs = ingredientConverter.convertBEsToDTOs(ingredientBEs, englishLanguage);

        assertThat(ingredientDTOs).extracting(IngredientDTO::getName).containsExactly(ENGLISH_NAME);
    }
}