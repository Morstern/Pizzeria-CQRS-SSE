package pl.morstern.PizzaCQRS.service.queries.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.morstern.PizzaCQRS.common.enums.Language;
import pl.morstern.PizzaCQRS.service.queries.converter.PizzaOrderConverter;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaOrderBE;
import pl.morstern.PizzaCQRS.service.queries.exception.OrderNotFoundException;
import pl.morstern.PizzaCQRS.service.queries.repository.PizzaOrderQueryRepository;

import java.util.Optional;

@Service
public class PizzaOrderQueryService {
    private final PizzaOrderQueryRepository pizzaOrderQueryRepository;
    private final PizzaOrderConverter pizzaOrderConverter;

    @Autowired
    public PizzaOrderQueryService(PizzaOrderQueryRepository pizzaOrderQueryRepository, PizzaOrderConverter pizzaOrderConverter) {
        this.pizzaOrderQueryRepository = pizzaOrderQueryRepository;
        this.pizzaOrderConverter = pizzaOrderConverter;
    }

    public ResponseEntity<?> findOrderById(long orderId, Language language) {
        Optional<PizzaOrderBE> order = pizzaOrderQueryRepository.findOrderByIdWithPrefetchedPizzas(orderId);

        if (order.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pizzaOrderConverter.convertBEtoDTO(order.get(), language));
        } else {
            throw new OrderNotFoundException("Order with ID: ".concat(String.valueOf(orderId)).concat(" was not found"), HttpStatus.NOT_FOUND);
        }
    }
}
