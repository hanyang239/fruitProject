<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders</title>
</head>
<body>
<h2><b>List of Orders</b></h2>
<c:forEach var="eachOrders" items="${listOfOrders}">
	<br />
	<b>Order id:</b> ${eachOrders.orderId}
	<br />
	<b>Total Price:</b> ${eachOrders.totalPrice}
	<br />  <br />
	<sf:form action="${pageContext.request.contextPath}/removeOrder/${eachOrders.orderId}">
     <input type="submit" name="commit" value="Cancel Order" />
   </sf:form>
</c:forEach>
<br />
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>