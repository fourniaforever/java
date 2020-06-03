package Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "GroupOfDishes")

public class GroupOfDishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroup;

    @Column(name = "Name")
    private String Name;
    public GroupOfDishes(){}
    public GroupOfDishes(String name) {
        Name = name;
    }

    @ManyToMany
    @JoinTable(name = "GroupOfDishesForDishes",joinColumns = @JoinColumn(name = "idGroup"),inverseJoinColumns = @JoinColumn(name= "idDish"))

    private Set<Dish> dishSet;

    public int getId() {
        return idGroup;
    }

    public void setId(int id) {
        idGroup = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }
}
