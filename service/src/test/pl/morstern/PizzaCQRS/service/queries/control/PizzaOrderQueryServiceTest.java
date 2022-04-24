package pl.morstern.PizzaCQRS.service.queries.control;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.queries.converter.PizzaOrderConverter;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaOrderBE;
import pl.morstern.PizzaCQRS.service.queries.exception.OrderNotFoundException;
import pl.morstern.PizzaCQRS.service.queries.repository.PizzaOrderQueryRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PizzaOrderQueryServiceTest extends BaseUnitTest {

    @InjectMocks
    private PizzaOrderQueryService pizzaOrderQueryService;
    @Mock
    private PizzaOrderQueryRepository pizzaOrderQueryRepository;
    @Mock
    private PizzaOrderConverter pizzaOrderConverter;

    private static final long ORDER_ID = 1000L;

    @Test
    void shouldFindByOrderId() {
        // given
        PizzaOrderBE pizzaOrder = PizzaOrderBE.builder()
                .id(ORDER_ID)
                .build();
        Optional<PizzaOrderBE> optionalPizzaOrder = Optional.of(pizzaOrder);

        // when
        when(pizzaOrderQueryRepository.findOrderByIdWithPrefetchedPizzas(ORDER_ID))
                .thenReturn(optionalPizzaOrder);

        // then
        pizzaOrderQueryService.findOrderById(ORDER_ID, polishLanguage);

        verify(pizzaOrderQueryRepository).findOrderByIdWithPrefetchedPizzas(ORDER_ID);
        verify(pizzaOrderConverter).convertBEtoDTO(pizzaOrder, polishLanguage);
    }

    @Test
    void shouldThrowOrderNotFoundExceptionWhenOrderIsMissing() {
        // given
        // when
        when(pizzaOrderQueryRepository.findOrderByIdWithPrefetchedPizzas(ORDER_ID))
                .thenReturn(Optional.empty());

        // then
        OrderNotFoundException thrown = assertThrows(OrderNotFoundException.class, () -> {
            pizzaOrderQueryService.findOrderById(ORDER_ID, polishLanguage);
        });

        verify(pizzaOrderQueryRepository).findOrderByIdWithPrefetchedPizzas(ORDER_ID);
        verify(pizzaOrderConverter, never()).convertBEtoDTO(any(), eq(polishLanguage));
        assertThat(thrown.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}