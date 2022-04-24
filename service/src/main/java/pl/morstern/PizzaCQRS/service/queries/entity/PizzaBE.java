package pl.morstern.PizzaCQRS.service.queries.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.morstern.PizzaCQRS.common.enums.PizzaSizeEnum;

import javax.persistence.*;

@Entity
@Table(name = "t_pizza")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PizzaBE {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "pizza_type_id")
    private PizzaTypeBE pizzaType;

    @Getter
    @Column(name = "pizza_size")
    @Enumerated(EnumType.STRING)
    private PizzaSizeEnum pizzaSize;

    @ManyToOne
    @JoinColumn(name = "pizza_order_id")
    private PizzaOrderBE pizzaOrder;
}
