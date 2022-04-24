package pl.morstern.PizzaCQRS.service.queries.converter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.morstern.PizzaCQRS.common.enums.PizzaSizeEnum;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaDTO;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaTypeDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaBE;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaTypeBE;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PizzaConverterTest extends BaseUnitTest {

    @InjectMocks
    private PizzaConverter pizzaConverter;
    @Mock
    private PizzaTypeConverter pizzaTypeConverter;

    @Test
    void shouldConvertBEsToDTOs() {
        // given
        PizzaTypeBE pizzaType = PizzaTypeBE.builder().build();

        List<PizzaBE> pizzas = List.of(PizzaBE.builder()
                .pizzaSize(PizzaSizeEnum.LARGE)
                .pizzaType(pizzaType)
                .build());

        // when
        when(pizzaTypeConverter.convertBEtoDTO(pizzaType, polishLanguage))
                .thenReturn(PizzaTypeDTO.builder().build());

        // then
        List<PizzaDTO> pizzaDTOS = pizzaConverter.convertBEsToDTOs(pizzas, polishLanguage);

        verify(pizzaTypeConverter).convertBEtoDTO(pizzaType, polishLanguage);
        assertThat(pizzaDTOS).extracting(PizzaDTO::getPizzaSize).containsExactly("LARGE");
    }
}