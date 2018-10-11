package com.fdmgroup.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.Fruit;

public class TestFruitDAO {

	FruitDAO fruitDAO;
	Fruit fruit1;
	Fruit fruit2;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fruitproject");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
			
		entityTransaction.begin();
		entityManager.createQuery("DELETE FROM Fruit f").executeUpdate();
		entityTransaction.commit();	
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fruitproject");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		entityManager.createQuery("DELETE FROM Fruit f").executeUpdate();
		entityTransaction.commit();
		
		fruitDAO = new FruitDAO();
		fruit1 = new Fruit("Apple", 1.99);
		fruit2 = new Fruit("Orange", 2.99);
	}

	//Test 1
	@Test
	public void test_listAllFruitReturnsEmptyListWhenNoFruitsAdded() {
		assertEquals(0,fruitDAO.listAllFruit().size());
	}

	//Test 2
	@Test
	public void test_listAllFruitReturnsListSizeOneWhenOneFruitAdded(){
		fruitDAO.addFruit(fruit1);
		assertEquals(1,fruitDAO.listAllFruit().size());
	}
	
	// Test 3
	@Test
	public void test_listAllFruitReturnsTwoWhenTwoDifferentFruitsAdded(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.addFruit(fruit2);
		assertEquals(2,fruitDAO.listAllFruit().size());
	}
	
	//Test 4
	@Test
	public void test_listAllFruitsReturnsZeroWhenFruit1HasBeenRemoved(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.removeFruit("Apple");
		assertEquals(0,fruitDAO.listAllFruit().size());
	}
	
	// Test 5
	@Test
	public void test_listAllFruitsReturnsOneWhenTwoFruitsAddedAndFruit1HasBeenRemoved(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.addFruit(fruit2);
		fruitDAO.removeFruit("Apple");
		assertEquals(1,fruitDAO.listAllFruit().size());
	}
	
	// Test 6
	@Test
	public void test_listAllFruitsReturnsZeroWhenTwoFruitsAddedAndTwoFruitsHasBeenRemoved(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.addFruit(fruit2);
		fruitDAO.removeFruit("Apple");
		fruitDAO.removeFruit("Orange");
		assertEquals(0,fruitDAO.listAllFruit().size());
	}
	
	// Test 7
	@Test
	public void test_IfFruit1StillExistWhenFruit2HasBeenRemoved(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.addFruit(fruit2);
		fruitDAO.removeFruit("Orange");
		assertTrue(fruitDAO.listAllFruit().contains(fruit1));
	}
	
	// Test 8
	@Test
	public void test_IfFruit2StillExistWhenFruit1HasBeenRemoved(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.addFruit(fruit2);
		fruitDAO.removeFruit("Apple");
		assertTrue(fruitDAO.listAllFruit().contains(fruit2));
	}
	
	// Test 9
	@Test
	public void test_getFruit1WhenAdded(){
		fruitDAO.addFruit(fruit1);
		assertEquals(fruit1,fruitDAO.getFruit("Apple"));		
	}
	
	// Test 10
	@Test
	public void test_getFruitWhenNoneAdded(){
		assertNull(fruitDAO.getFruit("Apple"));	
	}
	
	// Test 11
	@Test
	public void test_listAllFruitsListSizeEqualsOneWhenRemovingFruit2(){
		fruitDAO.addFruit(fruit1);
		fruitDAO.addFruit(fruit2);
		fruitDAO.removeFruit("Orange");
		assertEquals(1,fruitDAO.listAllFruit().size());	
	}
	
}
