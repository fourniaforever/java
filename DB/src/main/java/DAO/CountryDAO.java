package DAO;

import Entities.Countries;
import Entities.Dish;
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

public class CountryDAO implements ICountryDAO{
    private static SessionFactory sessionFactory;

    public CountryDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public void addCountry(Countries country) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(country);
        transaction.commit();
        session.close();
    }

    public void deleteCountry(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Countries country = session.get(Countries.class,id);
        session.delete(country);
        transaction.commit();
        session.close();
    }

    public List<Countries> getAllCountries() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Countries> countriesList = new ArrayList<Countries>(
                session.createQuery("from Countries").list());
        session.getTransaction().commit();
        return countriesList;
    }

    public List<Countries> searchCountryId(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Countries.class).add(Restrictions.idEq(id));
        List<Countries> countriesList = criteria.list();
        return countriesList;
        /*if (countriesList.size() >0) {
            for (Countries item : countriesList) {
                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                System.out.println(json);
            }
        } else {
            System.out.println("No results.");
        }
        session.close();*/
    }
    public List<Countries> searchCountriesByName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Countries.class).add(Restrictions.like("Name", name));
        List <Countries> countriesList = criteria.list();
        return countriesList;
       /* if (countriesList.size()>0) {
            for (Countries item : countriesList) {
                String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(item);
                System.out.println(json);
            }
        } else {
            System.out.println("No results.");
        }
        session.close();*/
    }
}
