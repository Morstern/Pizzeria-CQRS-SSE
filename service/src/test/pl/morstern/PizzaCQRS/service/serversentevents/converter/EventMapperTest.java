package pl.morstern.PizzaCQRS.service.serversentevents.converter;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.morstern.PizzaCQRS.service.common.BaseUnitTest;
import pl.morstern.PizzaCQRS.service.serversentevents.dto.EventDTO;
import pl.morstern.PizzaCQRS.service.serversentevents.utils.UUIDUtil;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EventMapperTest extends BaseUnitTest {

    @InjectMocks
    private EventMapper eventMapper;
    @Mock
    private UUIDUtil uuidUtil;

    @Test
    void shouldReturnSseEventBuilder() {
        // given
        EventDTO event = EventDTO.builder()
                .type("type")
                .body(new HashMap<>())
                .build();
        // when
        when(uuidUtil.generate()).thenReturn("testUUID");

        // then
        assertThat(eventMapper.toSseEventBuilder(event))
                .isInstanceOf(SseEmitter.SseEventBuilder.class);

        verify(uuidUtil).generate();
    }
}