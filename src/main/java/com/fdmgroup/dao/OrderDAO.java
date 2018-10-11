package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.entity.Order;


public class OrderDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("fruitproject");
	// entity manager will be used in all interactions with database
	EntityManager entityManager = emf.createEntityManager();
	// entity transaction will be used any time a row is inserted, deleted or updated
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void addOrder(Order order){
			entityTransaction.begin();
			entityManager.persist(order);
			entityTransaction.commit();
		}
	
	public void removeOrder(int id){
			Order order = entityManager.find(Order.class, id);
			entityTransaction.begin();
			entityManager.remove(order);
			entityTransaction.commit();
		}
	
	public Order getOrder(int id){
		Order order = entityManager.find(Order.class, id);
		
		return order;
			
	}
	
	public List<Order> listAllOrder(){
        String jpql = "SELECT o FROM FruitOrders o";
        TypedQuery<Order> orderQuery = entityManager.createQuery(jpql, Order.class);
        List<Order> order = orderQuery.getResultList();
        
        return order;
	}
	
}
