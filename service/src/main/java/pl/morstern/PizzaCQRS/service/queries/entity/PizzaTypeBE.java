package pl.morstern.PizzaCQRS.service.queries.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_pizza_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PizzaTypeBE {
    @Id
    @Column(name = "pizza_name")
    String pizzaName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_pizza_type_ingredient",
            joinColumns = @JoinColumn(name = "pizza_type_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<IngredientBE> ingredients;
    // From the data perspective SET would fit better here (PRIMARY KEY constraint),
    // although having LIST will assure - that the order will always be the same
}
