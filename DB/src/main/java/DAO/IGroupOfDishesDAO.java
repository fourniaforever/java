package DAO;

import Entities.GroupOfDishes;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IGroupOfDishesDAO {
    void addGroupOfDishes(GroupOfDishes groupOfDishes);
    void deleteGroupOfDishes(int id);
    List<GroupOfDishes> getAllGroups();
    List<GroupOfDishes> searchGroupOfDishesById(int id) throws JsonProcessingException;
    List<GroupOfDishes> searchGroupOfDishesByName(String name) throws JsonProcessingException;
}
