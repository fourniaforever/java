import DAO.DishDAO;
import DAO.IDishDAO;
import DAO.IUsersDAO;
import DAO.UsersDAO;
import Entities.Dish;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws JsonProcessingException, ParseException {
        IUsersDAO user = new UsersDAO();
        user.start();
    }
}
