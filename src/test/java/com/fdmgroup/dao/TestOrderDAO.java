package com.fdmgroup.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.entity.Order;

public class TestOrderDAO {

	OrderDAO orderDAO;
	Order order1;
	Order order2;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fruitproject");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
			
		entityTransaction.begin();
		entityManager.createQuery("DELETE FROM FruitOrders o").executeUpdate();
		entityTransaction.commit();
	}

	@Before
	public void setUp() throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("fruitproject");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		entityTransaction.begin();
		entityManager.createQuery("DELETE FROM FruitOrders o").executeUpdate();
		entityTransaction.commit();
		
		orderDAO = new OrderDAO();
		order1 = new Order(1, 5.99);
		order2 = new Order(2, 7.99);	
	}

	//Test 1
	@Test
	public void test_listAllOrderReturnsEmptyListWhenNoOrdersAdded() {
		assertEquals(0,orderDAO.listAllOrder().size());	
	}

	//Test 2
	@Test
	public void test_listAllOrderReturnsListSizeOneWhenOneOrderAdded(){
		orderDAO.addOrder(order1);
		assertEquals(1,orderDAO.listAllOrder().size());
	}
	
	// Test 3
	@Test
	public void test_listAllOrderReturnsTwoWhenTwoDifferentOrdersAdded(){
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		assertEquals(2,orderDAO.listAllOrder().size());
	
	}
	
	//Test 4
	@Test
	public void test_listAllOrdersReturnsZeroWhenOrder1HasBeenRemoved(){
		orderDAO.addOrder(order1);
		orderDAO.removeOrder(1);
		assertEquals(0,orderDAO.listAllOrder().size());
	}
	
	// Test 5
	@Test
	public void test_listAllOrdersReturnsOneWhenTwoOrdersAddedAndOrder1HasBeenRemoved(){
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.removeOrder(1);
		assertEquals(1,orderDAO.listAllOrder().size());
	}
	
	// Test 6
	@Test
	public void test_listAllOrdersReturnsZeroWhenTwoOrdersAddedAndTwoOrdersHasBeenRemoved(){
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.removeOrder(1);
		orderDAO.removeOrder(2);
		assertEquals(0,orderDAO.listAllOrder().size());
	}
	
	// Test 7
	@Test
	public void test_IfOrder1StillExistWhenOrder2HasBeenRemoved(){
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.removeOrder(2);
		assertTrue(orderDAO.listAllOrder().contains(order1));
	}
	
	// Test 8
	@Test
	public void test_IfOrder2StillExistWhenOrder1HasBeenRemoved(){
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.removeOrder(1);
		assertTrue(orderDAO.listAllOrder().contains(order2));
	}
	
	// Test 9
	@Test
	public void test_getOrder1WhenAdded(){		
		orderDAO.addOrder(order1);
		assertEquals(order1,orderDAO.getOrder(1));			
	}
	
	// Test 10 
	@Test
	public void test_getOrderWhenNoneAdded(){		
		assertNull(orderDAO.getOrder(1));				
	}
	
	// Test 11
	@Test
	public void test_listAllOrdersListSizeEqualsOneWhenRemovingOrder2(){
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		orderDAO.removeOrder(2);
		assertEquals(1,orderDAO.listAllOrder().size());			
	}

}
