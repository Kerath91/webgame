package com.mj.webapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mj.webapp.model.System;
import com.mj.webapp.model.SystemContract;

public class SystemContractDAOImpl implements SystemContractDAO {

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<SystemContract> list() {
        Session session = this.sessionFactory.openSession();
        List<SystemContract> systemList = session.createQuery("from SystemContract").list();
        session.close();
        return systemList;
    }
    
    @Override
    public void add(SystemContract entity){
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	session.saveOrUpdate(entity);
    	tx.commit();
    	session.close();  							
    }
    
    @Override
    public void delete(SystemContract entity){
    	Session session = this.sessionFactory.openSession();
    	Transaction tx = session.beginTransaction();
    	session.delete(entity);
    	tx.commit();
    	session.close();  							
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public int generate(){
        Session session = this.sessionFactory.openSession();
        for(int i=1;;i++) {
        	List<System> systemList = session.createQuery("FROM SystemContract S WHERE S.id = '"+i+"'").list();
        	if(systemList.isEmpty()){
        		session.close();
        		return i;
        	}
        		
        }
        
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public SystemContract generateId(SystemContract sc){
        Session session = this.sessionFactory.openSession();
        String request = sc.getRequest();
        long systemId = sc.getSystemId();
        List<SystemContract> systemList = session.createQuery("FROM SystemContract S WHERE S.request = '"+request+"' AND S.systemId = "+systemId).list();
        if(systemList.isEmpty())
        	sc.setContractId(generate());
        else
        	sc.setContractId(systemList.get(0).getContractId());
        session.close();
        return sc;

    }
}
