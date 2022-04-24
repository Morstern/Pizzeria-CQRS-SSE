package pl.morstern.PizzaCQRS.service.queries.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_pizza_order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaOrderBE {

    public static final String NQ_FIND_BY_ID_WITH_PREFETCHED_PIZZAS = "PizzaOrderBE.findByIdWithPrefetchedPizzas";
    @Id
    @Column(name = "id")
    private long id;

    @Getter
    @Column(name = "user_id")
    private long userId;

    @Getter
    @OneToMany(mappedBy = "pizzaOrder")
    private List<PizzaBE> pizzas;

    @Getter
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public PizzaOrderBE(long userId, List<PizzaBE> pizzas) {
        this.userId = userId;
        this.pizzas = pizzas;
        this.orderStatus = OrderStatus.ORDERED;
    }

    public void startCooking() {
        this.orderStatus = OrderStatus.STARTED_COOKING;
    }

    public void startDelivering() {
        this.orderStatus = OrderStatus.STARTED_DELIVERING;
    }

    public void deliver() {
        this.orderStatus = OrderStatus.DELIVERED;
    }

    public enum OrderStatus {
        ORDERED,
        STARTED_COOKING,
        STARTED_DELIVERING,
        DELIVERED
    }
}
