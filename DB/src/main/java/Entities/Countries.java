package Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Countries")
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int idCountry;
    @Column(name = "Name")
    private  String Name;

    @OneToMany(mappedBy = "country")
    private Set<Dish> setDishes;

    public Countries(String name) {
        Name = name;
    }
    public Countries(){}
    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
       this.idCountry = idCountry;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Dish> getSetDishes() {
        return setDishes;
    }

    public void setSetDishes(Set<Dish> setDishes) {
        this.setDishes = setDishes;
    }
}
