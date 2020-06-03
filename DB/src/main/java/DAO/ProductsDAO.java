package DAO;

import Entities.*;
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

public class ProductsDAO implements IProductDAO {
    private static SessionFactory sessionFactory;

    public ProductsDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public void addProduct(Products products) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(products);
        transaction.commit();
        session.close();
    }

    public void deleteProduct(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Products products = session.get(Products.class,id);
        session.delete(products);
        transaction.commit();
        session.close();
    }

    public List<Products> getAllProducts() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Products> productsList = new ArrayList<Products>(
                session.createQuery("from Products").list());
        session.getTransaction().commit();
        return productsList;
    }


    public List<Products> searchProductsById(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(HighRates.class).add(Restrictions.idEq(id));
        List<Products> productList = criteria.list();
        return productList;
    }
    public List<Products>  searchProductsByName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(HighRates.class).add(Restrictions.like("Name", name));
        List <Products> productList = criteria.list();
        return productList;
    }
}
