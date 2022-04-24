package pl.morstern.PizzaCQRS.service.queries.converter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaTypeDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaTypeBE;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PizzaTypeConverterTest extends BaseUnitTest {

    @InjectMocks
    private PizzaTypeConverter pizzaTypeConverter;
    @Mock
    private IngredientConverter ingredientConverter;

    private static final String PIZZA_NAME_1 = "pizza name 1";
    private static final String PIZZA_NAME_2 = "pizza name 2";

    @Test
    void shouldConvertBEsToDTOs() {
        // given
        List<PizzaTypeBE> pizzaTypes = List.of(
                PizzaTypeBE.builder().pizzaName(PIZZA_NAME_1).ingredients(List.of()).build(),
                PizzaTypeBE.builder().pizzaName(PIZZA_NAME_2).ingredients(List.of()).build()
        );

        // when
        when(ingredientConverter.convertBEsToDTOs(anyList(),eq(englishLanguage)))
                .thenReturn(List.of());

        // then
        List<PizzaTypeDTO> pizzaTypeDTOs = pizzaTypeConverter.convertBEsToDTOs(pizzaTypes, englishLanguage);

        assertThat(pizzaTypeDTOs).extracting(PizzaTypeDTO::getPizzaName).containsExactlyInAnyOrder(PIZZA_NAME_1, PIZZA_NAME_2);
        verify(ingredientConverter, times(2)).convertBEsToDTOs(anyList(), eq(englishLanguage));
    }

    @Test
    void shouldConvertBEtoDTO() {
        // given
        PizzaTypeBE pizzaType = PizzaTypeBE.builder().pizzaName(PIZZA_NAME_1).ingredients(List.of()).build();

        // when
        when(ingredientConverter.convertBEsToDTOs(anyList(), eq(polishLanguage)))
                .thenReturn(List.of());

        // then
        PizzaTypeDTO pizzaTypeDTOs = pizzaTypeConverter.convertBEtoDTO(pizzaType, polishLanguage);

        assertThat(pizzaTypeDTOs).extracting(PizzaTypeDTO::getPizzaName).isEqualTo(PIZZA_NAME_1);
        verify(ingredientConverter).convertBEsToDTOs(anyCollection(), eq(polishLanguage));
    }
}