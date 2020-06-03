package DAO;

import Entities.Dish;
import Entities.GroupOfDishes;
import Entities.HighRates;
import Entities.Rate;
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

public class HighRateDAO implements IHighRateDAO {
    private static SessionFactory sessionFactory;

    public HighRateDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }


    public void addHighRate(HighRates highRates) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(highRates);
        transaction.commit();
        session.close();
    }

    public void deleteHighRate(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        HighRates highRates= session.get(HighRates.class,id);
        session.delete(highRates);
        transaction.commit();
        session.close();
    }

    public List<HighRates> getAllHighRate() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <HighRates> highRateList = new ArrayList<HighRates>(
                session.createQuery("from HighRate").list());
        session.getTransaction().commit();
        return highRateList;
    }

    public List<HighRates> searchHighRateId(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(HighRates.class).add(Restrictions.idEq(id));
        List<HighRates> highRatesList = criteria.list();
        return highRatesList;
    }
    public List<HighRates> searchHighRateByName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(HighRates.class).add(Restrictions.like("nameOfCritic", name));
        List <HighRates> highRatesList = criteria.list();
        return highRatesList;
    }
}
