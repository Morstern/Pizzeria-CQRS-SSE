package pl.morstern.PizzaCQRS.service.serversentevents.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


// Inspired by https://github.com/mkapiczy/server-sent-events
@Repository
public class EmitterRepository {
    private final Map<Long, SseEmitter> userEmitterMap = new ConcurrentHashMap<>();

    public void addOrReplaceEmitter(Long orderId, SseEmitter emitter) {
        userEmitterMap.put(orderId, emitter);
    }

    public void remove(Long orderId) {
        userEmitterMap.remove(orderId);
    }

    public Optional<SseEmitter> get(Long orderId) {
        return Optional.ofNullable(userEmitterMap.get(orderId));
    }
}
