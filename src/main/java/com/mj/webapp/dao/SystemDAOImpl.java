package com.mj.webapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mj.webapp.model.System;

public class SystemDAOImpl implements SystemDAO {
 
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<System> list() {
        Session session = this.sessionFactory.openSession();
        List<System> systemList = session.createQuery("from System").list();
        session.close();
        return systemList;
    }
    @SuppressWarnings("unchecked")
    @Override
    public System getSystemByName(String systemName) {
        Session session = this.sessionFactory.openSession();
        List<System> systemList = session.createQuery("FROM System S WHERE S.name = '"+systemName+"'").list();
        session.close();
        if(systemList.isEmpty())
        	return null;
        return systemList.get(0);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public System getSystemById(long id) {
        Session session = this.sessionFactory.openSession();
        List<System> systemList = session.createQuery("FROM System S WHERE S.id = '"+id+"'").list();
        session.close();
        if(systemList.isEmpty())
        	return null;
        return systemList.get(0);
    }
    
 
}