package pl.morstern.PizzaCQRS.service.queries.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.morstern.PizzaCQRS.common.constants.PizzaPath;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.control.PizzaTypeQueryService;

@RestController
@RequestMapping(path = PizzaPath.API_ENDPOINT)
public class PizzaResource {

    private final PizzaTypeQueryService pizzaTypeQueryService;

    @Autowired
    public PizzaResource(PizzaTypeQueryService pizzaTypeQueryService) {
        this.pizzaTypeQueryService = pizzaTypeQueryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPizzas(@RequestParam("lang") Language language) {
        return pizzaTypeQueryService.findAll(language);
    }
}
