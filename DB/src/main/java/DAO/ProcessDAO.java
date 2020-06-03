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

public class ProcessDAO implements IProcessDAO {
    private static SessionFactory sessionFactory;

    public ProcessDAO() {
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    public void addProcess(Process process) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(process);
        transaction.commit();
        session.close();
    }

    public void deleteProcess(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Process process= session.get(Process.class,id);
        session.delete(process);
        transaction.commit();
        session.close();
    }

    public List<Process> getAllProcesses() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List <Process> processList = new ArrayList<Process>(
                session.createQuery("from Process").list());
        session.getTransaction().commit();
        return processList;
    }

    public List<Process> searchProcessById(int id) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(HighRates.class).add(Restrictions.idEq(id));
        List<Process> processList = criteria.list();
        return processList;
    }
    public List<Process> searchProcessByName(String name) throws JsonProcessingException {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Process.class).add(Restrictions.like("Name", name));
        List <Process> processList = criteria.list();
        return processList;
    }
}
