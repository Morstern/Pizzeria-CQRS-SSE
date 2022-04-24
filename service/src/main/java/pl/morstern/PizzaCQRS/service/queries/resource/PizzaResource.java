package pl.morstern.PizzaCQRS.service.queries.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import pl.morstern.PizzaCQRS.common.constants.PizzaPath;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.control.PizzaOrderQueryService;
import pl.morstern.PizzaCQRS.service.queries.control.PizzaTypeQueryService;
import pl.morstern.PizzaCQRS.service.serversentevents.control.EventService;

@RestController
@RequestMapping(path = PizzaPath.API_ENDPOINT)
public class PizzaResource {

    private final PizzaTypeQueryService pizzaTypeQueryService;
    private final PizzaOrderQueryService pizzaOrderQueryService;
    private final EventService eventService;

    @Autowired
    public PizzaResource(PizzaTypeQueryService pizzaTypeQueryService, PizzaOrderQueryService pizzaOrderQueryService, EventService eventService) {
        this.pizzaTypeQueryService = pizzaTypeQueryService;
        this.pizzaOrderQueryService = pizzaOrderQueryService;
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPizzas(@RequestParam("lang") Language language) {
        return pizzaTypeQueryService.findAll(language);
    }

    @GetMapping(path = "/{ID}")
    public ResponseEntity<?> getPizzaOrder(@PathVariable("ID") long orderId, @RequestParam("lang") Language language) {
        return pizzaOrderQueryService.findOrderById(orderId, language);
    }

    @GetMapping(path = "/{ID}/stream-sse")
    public SseEmitter getPizzaOrderByServerSentEvents(@PathVariable("ID") long orderId, @RequestParam("lang") Language language) {
        return eventService.createEmitter(orderId);
    }
}
