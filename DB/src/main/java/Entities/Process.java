package Entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "Process")
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProcess;

    @Column(name = "Name")
    private String Name;

   // @ManyToMany
    //@JoinTable(name = "ProcessesForDishes",joinColumns = @JoinColumn(name = "idProcess"),inverseJoinColumns = @JoinColumn(name= "idDish"))
    //private Set<Dish> dishSet;

    public int getId() {
        return idProcess;
    }

    public void setId(int id) {
        idProcess = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Process(){}
    public Process(String name) {
        Name = name;
    }
}
