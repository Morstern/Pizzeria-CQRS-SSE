package pl.morstern.PizzaCQRS.service.serversentevents.control;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.serversentevents.converter.EventMapper;
import pl.morstern.PizzaCQRS.service.serversentevents.dto.EventDTO;
import pl.morstern.PizzaCQRS.service.serversentevents.repository.EmitterRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.*;

class OrderEventServiceTest extends BaseUnitTest {

    @InjectMocks
    private OrderEventService orderEventService;
    @Mock
    private EmitterRepository emitterRepository;
    @Mock
    private EventMapper eventMapper;

    private static final long ORDER_ID = 1000L;
    private static final String TYPE = "type";

    @Test
    void shouldSendNotificationWhenEmitterForOrderIsFound() {
        // given
        SseEmitter mockSseEmitter = Mockito.mock(SseEmitter.class);

        EventDTO eventDTO = EventDTO.builder()
                .body(new HashMap<>())
                .type(TYPE)
                .build();

        // when
        when(emitterRepository.get(ORDER_ID))
                .thenReturn(Optional.of(mockSseEmitter));
        when(eventMapper.toSseEventBuilder(eventDTO))
                .thenReturn(SseEmitter.event());

        // then
        orderEventService.sendNotification(ORDER_ID, eventDTO);
        verify(emitterRepository).get(ORDER_ID);
        verify(eventMapper).toSseEventBuilder(eventDTO);
        verify(emitterRepository, never()).remove(ORDER_ID);
    }

    @Test
    void shouldNotSendNotificationWhenEmitterForOrderIsNotFound() {
        // given
        EventDTO eventDTO = EventDTO.builder()
                .body(new HashMap<>())
                .type(TYPE)
                .build();

        // when
        when(emitterRepository.get(ORDER_ID))
                .thenReturn(Optional.empty());

        // then
        orderEventService.sendNotification(ORDER_ID, eventDTO);
        verify(emitterRepository).get(ORDER_ID);
        verify(eventMapper, never()).toSseEventBuilder(eventDTO);
    }

    @Test
    void shouldRemoveEmitterWhenIOExceptionIsThrown() {
        // given
        SseEmitter mockSseEmitter = Mockito.mock(SseEmitter.class);

        EventDTO eventDTO = EventDTO.builder()
                .body(new HashMap<>())
                .type(TYPE)
                .build();

        // when
        when(emitterRepository.get(ORDER_ID))
                .thenReturn(Optional.of(mockSseEmitter));
        when(eventMapper.toSseEventBuilder(eventDTO))
                .thenReturn(SseEmitter.event());
        try{
            doThrow(new IOException()).when(mockSseEmitter).send(any());
        } catch( IOException exception) {
            // do nothing
        }

        // then
        orderEventService.sendNotification(ORDER_ID, eventDTO);
        verify(emitterRepository).get(ORDER_ID);
        verify(eventMapper).toSseEventBuilder(eventDTO);
        verify(emitterRepository).remove(ORDER_ID);
    }
}