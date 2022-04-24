package pl.morstern.PizzaCQRS.service.queries.converter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaDTO;
import pl.morstern.PizzaCQRS.service.queries.dto.PizzaOrderDTO;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaBE;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaOrderBE;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class PizzaOrderConverterTest extends BaseUnitTest {

    @InjectMocks
    private PizzaOrderConverter pizzaOrderConverter;
    @Mock
    private PizzaConverter pizzaConverter;

    @Test
    void shouldConvertBEtoDTO() {
        // given

        PizzaBE pizza = PizzaBE.builder().build();

        PizzaOrderBE pizzaOrder = PizzaOrderBE.builder()
                .orderStatus(PizzaOrderBE.OrderStatus.STARTED_COOKING)
                .pizzas(List.of(pizza))
                .build();

        // when
        when(pizzaConverter.convertBEsToDTOs(List.of(pizza), polishLanguage))
                .thenReturn(List.of(PizzaDTO.builder().build()));
        // then
        PizzaOrderDTO pizzaOrderDTO = pizzaOrderConverter.convertBEtoDTO(pizzaOrder, polishLanguage);

        verify(pizzaConverter).convertBEsToDTOs(List.of(pizza), polishLanguage);
        assertThat(pizzaOrderDTO).extracting(PizzaOrderDTO::getOrderStatus).isEqualTo("STARTED_COOKING");
    }
}