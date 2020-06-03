package DAO;

import Entities.Users;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.ParseException;
import java.util.List;

public interface IUsersDAO {
    void authorize() throws JsonProcessingException, ParseException;
    void registrate() throws JsonProcessingException, ParseException;
    List<Users> getNeedUsers(String login, int password);
    public void menuDish() throws JsonProcessingException, ParseException;
    public void menuCountries() throws JsonProcessingException, ParseException;
    public void menuGroupOfDishes() throws JsonProcessingException, ParseException;
    public void menuProducts() throws JsonProcessingException, ParseException;
    public void menuProcess() throws JsonProcessingException, ParseException;
    public void menuRate() throws JsonProcessingException, ParseException;
    public void menuHighRate() throws ParseException, JsonProcessingException;
    public void start() throws JsonProcessingException, ParseException;
    void menu() throws JsonProcessingException, ParseException;

}
