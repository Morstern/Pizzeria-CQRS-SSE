package pl.morstern.PizzaCQRS.service.queries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaOrderBE;

import java.util.Optional;

@Repository
public interface PizzaOrderQueryRepository extends JpaRepository<PizzaOrderBE, Long> {
    @Query(name = PizzaOrderBE.NQ_FIND_BY_ID_WITH_PREFETCHED_PIZZAS,
            value = "SELECT DISTINCT pizzaOrder FROM PizzaOrderBE pizzaOrder JOIN FETCH pizzaOrder.pizzas where pizzaOrder.id = ?1")
    Optional<PizzaOrderBE> findOrderByIdWithPrefetchedPizzas(Long orderId);
}
