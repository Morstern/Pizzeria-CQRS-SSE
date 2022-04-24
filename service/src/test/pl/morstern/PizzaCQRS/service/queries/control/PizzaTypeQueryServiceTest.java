package pl.morstern.PizzaCQRS.service.queries.control;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.queries.converter.PizzaTypeConverter;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaTypeBE;
import pl.morstern.PizzaCQRS.service.queries.repository.PizzaTypeQueryRepository;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PizzaTypeQueryServiceTest extends BaseUnitTest {

    @InjectMocks
    private PizzaTypeQueryService pizzaTypeQueryService;
    @Mock
    private PizzaTypeQueryRepository pizzaTypeQueryRepository;
    @Mock
    private PizzaTypeConverter pizzaTypeConverter;

    @Test
    void shouldFindAll() {
        // given
        PizzaTypeBE pizzaType1 = PizzaTypeBE.builder().build();
        PizzaTypeBE pizzaType2 = PizzaTypeBE.builder().build();

        List<PizzaTypeBE> pizzaTypes = List.of(pizzaType1, pizzaType2);

        // when
        when(pizzaTypeQueryRepository.findAll())
                .thenReturn(pizzaTypes);
        // then
        pizzaTypeQueryService.findAll(polishLanguage);

        verify(pizzaTypeQueryRepository).findAll();
        verify(pizzaTypeConverter).convertBEsToDTOs(pizzaTypes, polishLanguage);
    }
}