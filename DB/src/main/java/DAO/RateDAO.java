package DAO;

import Entities.Dish;
import Entities.HighRates;
import Entities.Process;
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

public class RateDAO implements IRateDAO {
    private static SessionFactory sessionFactory;

    public RateDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public void addRate(Rate rate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(rate);
        transaction.commit();
        session.close();
    }

    public void deleteRate(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Rate rate= session.get(Rate.class,id);
        session.delete(rate);
        transaction.commit();
        session.close();
    }

    public List<Rate> getAllRates() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Rate> rateList = new ArrayList<Rate>(
                session.createQuery("from Rate").list());
        session.getTransaction().commit();
        return rateList;
    }

    public List<Rate> searchRateById(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Rate.class).add(Restrictions.idEq(id));
        List<Rate> rateList = criteria.list();
        return rateList;
    }
    public List<Rate> searchRateByRate(int rate) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Rate.class).add(Restrictions.like("Rate", rate));
        List <Rate> rateList = criteria.list();
        return rateList;
    }
}
