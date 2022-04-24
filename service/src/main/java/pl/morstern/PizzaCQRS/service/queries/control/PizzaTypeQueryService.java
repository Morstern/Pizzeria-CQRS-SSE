package pl.morstern.PizzaCQRS.service.queries.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.converter.PizzaTypeConverter;
import pl.morstern.PizzaCQRS.service.queries.repository.PizzaTypeQueryRepository;

@Service
public class PizzaTypeQueryService {
    private final PizzaTypeQueryRepository pizzaTypeQueryRepository;
    private final PizzaTypeConverter pizzaTypeConverter;

    @Autowired
    public PizzaTypeQueryService(PizzaTypeQueryRepository pizzaTypeQueryRepository, PizzaTypeConverter pizzaTypeConverter){
        this.pizzaTypeQueryRepository = pizzaTypeQueryRepository;
        this.pizzaTypeConverter = pizzaTypeConverter;
    }

    public ResponseEntity<?> findAll(Language language){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pizzaTypeConverter.convertBEsToDTOs(pizzaTypeQueryRepository.findAll(), language));
    }
}
