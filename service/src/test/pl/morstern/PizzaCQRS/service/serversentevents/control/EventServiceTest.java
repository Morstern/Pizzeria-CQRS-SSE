package pl.morstern.PizzaCQRS.service.serversentevents.control;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.serversentevents.repository.EmitterRepository;

import static org.mockito.Mockito.verify;

class EventServiceTest extends BaseUnitTest {

    @InjectMocks
    private EventService eventService;
    @Mock
    private EmitterRepository emitterRepository;

    private static final long ORDER_ID = 1000L;

    @Test
    void shouldCreateEmitterAndAddToRepository() {
        // given
        // when
        // then
        SseEmitter emitter = eventService.createEmitter(ORDER_ID);

        verify(emitterRepository).addOrReplaceEmitter(ORDER_ID, emitter);
    }
}