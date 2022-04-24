package pl.morstern.PizzaCQRS.service.queries.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_ingredient")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientBE {
    @Id
    @Column(name = "ingredient_name")
    String name;

    @Column(name = "value_en")
    String englishName;

    @Column(name = "value_pl")
    String polishName;
}
