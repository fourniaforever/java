package DAO;

import Entities.Dish;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IDishDAO {
    void addDish(Dish dish);
    void deleteDish(int id);
    List<Dish> getAllDish();
   // void printAllDish() throws JsonProcessingException;
    List<Dish> searchDishId(int id) throws JsonProcessingException;
    List<Dish> searchDishByName(String name) throws JsonProcessingException;
}
