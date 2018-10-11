package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.fdmgroup.entity.Fruit;


public class FruitDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("fruitproject");
	// entity manager will be used in all interactions with database
	EntityManager entityManager = emf.createEntityManager();
	// entity transaction will be used any time a row is inserted, deleted or updated
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	public void addFruit(Fruit fruit){
		// Insert objects into tables
			entityTransaction.begin();
			entityManager.persist(fruit);
			entityTransaction.commit();
		}
	
	public void removeFruit(String name){
		// get object out of the database
			Fruit fruit = entityManager.find(Fruit.class, name);
			entityTransaction.begin();
			entityManager.remove(fruit);
			entityTransaction.commit();
		}
	
	public Fruit getFruit(String fruitName){
	
		Fruit fruit = entityManager.find(Fruit.class, fruitName);
		
		return fruit;
	}
	
	public List<Fruit> listAllFruit(){

        String jpql = "SELECT f FROM Fruit f";
        TypedQuery<Fruit> fruitQuery = entityManager.createQuery(jpql, Fruit.class);
        List<Fruit> fruit = fruitQuery.getResultList();
        return fruit;
 	}
}
