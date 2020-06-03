package DAO;

import Entities.Dish;
import Entities.GroupOfDishes;
import Entities.HighRates;
import Entities.Process;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import SessionFactory.SessionFactoryUtil;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class GroupOfDishesDAO implements IGroupOfDishesDAO {
    private static SessionFactory sessionFactory;

    public GroupOfDishesDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public void addGroupOfDishes(GroupOfDishes groupOfDishes) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(groupOfDishes);
        transaction.commit();
        session.close();
    }
    public void deleteGroupOfDishes(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        GroupOfDishes groupOfDishes = session.get(GroupOfDishes.class,id);
        session.delete( groupOfDishes);
        transaction.commit();
        session.close();
    }
    public List<GroupOfDishes> getAllGroups() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <GroupOfDishes> groupOfDishesList = new ArrayList<GroupOfDishes>(
                session.createQuery("from GroupOfDishes").list());
        session.getTransaction().commit();
        return groupOfDishesList;
    }

    public List<GroupOfDishes> searchGroupOfDishesById(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(GroupOfDishes.class).add(Restrictions.idEq(id));
        List<GroupOfDishes> groupOfDishesList = criteria.list();
        return groupOfDishesList;
    }
    public List<GroupOfDishes> searchGroupOfDishesByName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(GroupOfDishes.class).add(Restrictions.like("Name", name));
        List<GroupOfDishes> groupOfDishesList = criteria.list();
        return groupOfDishesList;
    }
}
