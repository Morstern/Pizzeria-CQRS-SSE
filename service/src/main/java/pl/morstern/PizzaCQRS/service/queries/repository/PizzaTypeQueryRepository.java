package pl.morstern.PizzaCQRS.service.queries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.morstern.PizzaCQRS.service.queries.entity.PizzaTypeBE;

@Repository
public interface PizzaTypeQueryRepository extends JpaRepository<PizzaTypeBE, String> {
}
