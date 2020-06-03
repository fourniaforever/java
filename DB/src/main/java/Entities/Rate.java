
package Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
@SuppressWarnings("PMD")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Rate")

public class Rate {
      @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private int idRate;

        @Column(name = "Date")
        private Date Date;

        @Column(name = "Rate")
        private int Rate;

    public Rate(int rate) {
        Rate = rate;
    }

    public Rate(java.util.Date date, int rate){
        Date = date;
        Rate = rate;
    }
    public Rate(){}

    @ManyToMany
    @JoinTable(name = "RateForDishes",joinColumns = @JoinColumn(name = "idRate"),inverseJoinColumns = @JoinColumn(name= "idDish"))
    @JsonManagedReference
    private Set<Dish> dishSet = new Set<Dish>() {
        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean contains(Object o) {
            return false;
        }

        public Iterator<Dish> iterator() {
            return null;
        }

        public Object[] toArray() {
            return new Object[0];
        }

        public <T> T[] toArray(T[] a) {
            return null;
        }

        public boolean add(Dish dish) {
            return false;
        }

        public boolean remove(Object o) {
            return false;
        }

        public boolean containsAll(Collection<?> c) {
            return false;
        }

        public boolean addAll(Collection<? extends Dish> c) {
            return false;
        }

        public boolean retainAll(Collection<?> c) {
            return false;
        }

        public boolean removeAll(Collection<?> c) {
            return false;
        }

        public void clear() {

        }
    };

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int id) {
        idRate = id;
    }


    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }
}
