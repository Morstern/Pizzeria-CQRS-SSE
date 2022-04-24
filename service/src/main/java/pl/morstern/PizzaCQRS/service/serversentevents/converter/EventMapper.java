package pl.morstern.PizzaCQRS.service.serversentevents.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.morstern.PizzaCQRS.service.serversentevents.dto.EventDTO;
import pl.morstern.PizzaCQRS.service.serversentevents.utils.UUIDUtil;

// Inspired by https://github.com/mkapiczy/server-sent-events
@Component
public class EventMapper {
    private final UUIDUtil uuidUtil;

    @Autowired
    public EventMapper(UUIDUtil uuidUtil) {
        this.uuidUtil = uuidUtil;
    }

    public SseEmitter.SseEventBuilder toSseEventBuilder(EventDTO event) {
        return SseEmitter.event()
                .id(uuidUtil.generate())
                .name(event.getType())
                .data(event.getBody());
    }
}
