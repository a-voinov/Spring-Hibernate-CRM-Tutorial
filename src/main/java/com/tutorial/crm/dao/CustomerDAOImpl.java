package com.tutorial.crm.dao;

import com.tutorial.crm.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int customerId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Customer.class, customerId);
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }

    @Override
    public List<Customer> getCustomers(String searchName) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer where lower(lastName) like :searchName " +
                "or lower(firstName) like :searchName order by lastName", Customer.class);
        query.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
        return query.getResultList();
    }

}
