package com.fdmgroup.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.dao.FruitDAO;
import com.fdmgroup.dao.OrderDAO;
import com.fdmgroup.entity.Fruit;
import com.fdmgroup.entity.Order;

@Controller
public class HomeController {

	// List fruits with basket
	private HashMap<Fruit, Integer> basket = new HashMap<Fruit, Integer>();

	@RequestMapping(value = "/")
	public String homePage() {
		return "homepage";
	}

	public double calculateBasketTotal() {

		double total = 0.0;

		for (Entry<Fruit, Integer> eachFruit : basket.entrySet()) {
			double price = eachFruit.getKey().getPrice();
			int quantity = eachFruit.getValue();
			total += price * quantity;
		}

		return total;
	}

	// Add fruits to basket
	@RequestMapping(value = "addFruitToBasket/{name}", method = POST)
	public String addProduct(@PathVariable String name, Model model) {

		FruitDAO fruitDAO = new FruitDAO();
		Fruit fruit = fruitDAO.getFruit(name);

		if (basket.containsKey(fruit)) {

			int quantity = basket.get(fruit);
			basket.put(fruit, quantity + 1);
			
		} else {
			
			basket.put(fruit, 1);
			
		}
		
		return "redirect:/basket";
	}

	// View Basket
	@RequestMapping(value = "basket")
	public String showBasket(Model model) {
		
		model.addAttribute("basket", basket);
		
		return "basket";
	}

	// Remove fruit from basket
	@RequestMapping(value = "removeFruitFromBasket/{name}", method = POST)
	public String removeProduct(@PathVariable String name, Model model) {
		FruitDAO fruitDAO = new FruitDAO();
		Fruit fruit = fruitDAO.getFruit(name);

		if (basket.containsKey(fruit)) {
			int quantity = basket.get(fruit);
			basket.put(fruit, quantity - 1);
		}

		if (basket.get(fruit) <= 0) {
			basket.remove(fruit);
		}

		model.addAttribute("basket", basket);
		return "basket";
	}

	// List fruits
	@RequestMapping("listFruits")
	public String listFruits(Model model) {

		FruitDAO fruitDAO = new FruitDAO();
		List<Fruit> listOfFruits = fruitDAO.listAllFruit();

		model.addAttribute("listOfFruits", listOfFruits);

		return "listFruits";
	}

	// List orders
	@RequestMapping("listOrders")
	public String listOrders(Model model) {

		OrderDAO orderDAO = new OrderDAO();
		List<Order> listOfOrders = orderDAO.listAllOrder();

		model.addAttribute("listOfOrders", listOfOrders);

		return "listOrders";
	}

	// Add Order
	@RequestMapping(value = "placeOrder")
	public String placeOrderHandler(HttpSession session) {

		OrderDAO orderDAO = new OrderDAO();
		Order order = new Order();

		double totalPrice = calculateBasketTotal();
		order.setTotalPrice(totalPrice);

		orderDAO.addOrder(order);
		basket.clear();
		return "placeOrder";
	}

	// Remove Order
	@RequestMapping(value = "removeOrder/{orderId}")
	public String removeOrdersHandler(@PathVariable int orderId) {

		OrderDAO orderDAO = new OrderDAO();
		orderDAO.removeOrder(orderId);

		return "listOrders";
	}

}
