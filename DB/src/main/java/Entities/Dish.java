package Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Dish")
public class Dish {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDish;
@Column(name = "Name")
    private  String name;

@Column(name = "Weight")
    private double weight;

@Column(name = "Callories")
    private double callories;

@ManyToOne
@JoinColumn(name = "idCountry")
    private Countries country;

@Column(name = "IsVegan")
    private int isVegan;

@Column(name = "Price")
    private double price;

@Column(name = "EXP")
    private int exp;

@ManyToMany
@JoinTable(name = "ProductsForDishes",joinColumns = @JoinColumn(name = "idDish"),inverseJoinColumns = @JoinColumn(name= "idProduct"))
private Set<Products> productSet;

@ManyToMany
@JoinTable(name = "RateForDishes",joinColumns = @JoinColumn(name = "idDish"),inverseJoinColumns = @JoinColumn(name= "idRate"))
@JsonBackReference
private Set<Rate> rateSet;

@ManyToMany
@JoinTable(name = "GroupOfDishesForDishes",joinColumns = @JoinColumn(name = "idDish"),inverseJoinColumns = @JoinColumn(name= "idGroup"))
private Set<GroupOfDishes> groupOfDishesSet;

    public Dish(){}

    public Dish(String name, double weight, double callories, int isVegan, double price, int exp) {
        this.name = name;
        this.weight = weight;
        this.callories = callories;
        this.isVegan = isVegan;
        this.price = price;
        this.exp = exp;
    }

    public Dish(String name, double weight, double callories) {
        this.name = name;
        this.weight = weight;
        this.callories = callories;
    }

    public int getId() {
        return idDish;
    }

    public void setId(int id) {
        idDish = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        weight = weight;
    }

    public double getCallories() {
        return callories;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public void setCallories(double callories) {
        this.callories = callories;
    }

    public int getIsVegan() {
        return isVegan;
    }

    public void setIsVegan(int isVegan) {
        this.isVegan = isVegan;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getEXP() {
        return exp;
    }

    public void setEXP(int EXP) {
        this.exp = EXP;
    }

    public Set<Products> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Products> productSet) {
        this.productSet = productSet;
    }

    public Set<Rate> getRateSet() {
        return rateSet;
    }

    public void setRateSet(Set<Rate> rateSet) {
        this.rateSet = rateSet;
    }

    public Set<GroupOfDishes> getGroupOfDishesSet() {
        return groupOfDishesSet;
    }

    public void setGroupOfDishesSet(Set<GroupOfDishes> groupOfDishesSet) {
        this.groupOfDishesSet = groupOfDishesSet;
    }

}
