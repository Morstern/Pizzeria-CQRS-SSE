package pl.morstern.PizzaCQRS.service.serversentevents.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.morstern.PizzaCQRS.service.serversentevents.repository.EmitterRepository;
import pl.morstern.PizzaCQRS.service.serversentevents.converter.EventMapper;
import pl.morstern.PizzaCQRS.service.serversentevents.dto.EventDTO;

import java.io.IOException;

// Inspired by https://github.com/mkapiczy/server-sent-events
@Service
public class OrderEventService {
    private final EmitterRepository emitterRepository;
    private final EventMapper eventMapper;

    @Autowired
    public OrderEventService(EmitterRepository emitterRepository, EventMapper eventMapper) {
        this.emitterRepository = emitterRepository;
        this.eventMapper = eventMapper;
    }

    public void sendNotification(Long orderId, EventDTO event) {
        if (event == null) {
            return;
        }
        doSendNotification(orderId, event);
    }

    private void doSendNotification(Long orderId, EventDTO event) {
        emitterRepository.get(orderId).ifPresent(sseEmitter -> {
            try {
                sseEmitter.send(eventMapper.toSseEventBuilder(event));
            } catch (IOException | IllegalStateException e) {
                emitterRepository.remove(orderId);
            }});
    }
}
