package pl.morstern.PizzaCQRS.service.serversentevents.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.morstern.PizzaCQRS.service.serversentevents.repository.EmitterRepository;

// Inspired by https://github.com/mkapiczy/server-sent-events
@Service
public class EventService {
    private final long timeout = 5000L;
    private final EmitterRepository emitterRepository;

    @Autowired
    public EventService(EmitterRepository emitterRepository) {
        this.emitterRepository = emitterRepository;
    }

    public SseEmitter createEmitter(long orderId) {
        SseEmitter emitter = new SseEmitter(timeout);
        emitter.onCompletion(() -> emitterRepository.remove(orderId));
        emitter.onTimeout(() -> emitterRepository.remove(orderId));
        emitter.onError(e -> {
            emitterRepository.remove(orderId);
        });
        emitterRepository.addOrReplaceEmitter(orderId, emitter);
        return emitter;
    }

}
