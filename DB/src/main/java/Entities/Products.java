package Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Products")
public class Products {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdProduct;
    @Column(name = "Name")
    private  String Name;

    @Column(name = "Weight")
    private double Weight;

    @Column(name = "Callories")
    private double Callories;

    @Column(name = "IsVegan")
    private int IsVegan;

    @Column(name = "Price")
    private double Price;

    @Column(name = "EXP")
    private int EXP;

    @ManyToMany
    @JoinTable(name = "ProductsForDishes",joinColumns = @JoinColumn(name = "IdProduct"),inverseJoinColumns = @JoinColumn(name= "IdDish"))

    private Set<Dish> dishSet;
    public int getId() {
        return IdProduct;
    }

    public void setId(int id) {
        IdProduct = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }

    public double getCallories() {
        return Callories;
    }

    public void setCallories(double callories) {
        Callories = callories;
    }

    public int getIsVegan() {
        return IsVegan;
    }

    public void setIsVegan(int isVegan) {
        IsVegan = isVegan;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public Products(String name, double weight, double callories, int isVegan, double price, int EXP) {
        Name = name;
        Weight = weight;
        Callories = callories;
        IsVegan = isVegan;
        Price = price;
        this.EXP = EXP;
    }
    public Products(){}
    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        IdProduct = idProduct;
    }

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }
}
