package DAO;

import Entities.Countries;
import Entities.Dish;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import SessionFactory.SessionFactoryUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DishDAO implements IDishDAO {
    private static SessionFactory sessionFactory;

    public DishDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public void addDish(Dish dish) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dish);
        transaction.commit();
        session.close();
    }

    public void deleteDish(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Dish dish = session.get(Dish.class,id);
        session.delete(dish);
        transaction.commit();
        session.close();
    }

    public List<Dish> getAllDish() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Dish> DishList = new ArrayList<Dish>(
                session.createQuery("from Dish").list());
        session.getTransaction().commit();
        return DishList;
    }

    public List<Dish> searchDishId(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Dish.class).add(Restrictions.idEq(id));
        List<Dish> dishesList = criteria.list();
        return dishesList;
    }
    public List<Dish> searchDishByName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Dish.class).add(Restrictions.like("name", name));
        List <Dish> dishesList = criteria.list();
        return dishesList;
    }
}