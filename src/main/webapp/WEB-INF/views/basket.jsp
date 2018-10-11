<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Basket</title>
</head>
<body>
<h2><b>Basket</b></h2>
<c:forEach var="eachFruit" items="${basket}">
	<br />
	<b>Name:</b> ${eachFruit.key.name}
	<br />
	<b>Total Quantity:</b> ${eachFruit.value}
	<br />
	<sf:form action="${pageContext.request.contextPath}/removeFruitFromBasket/${eachFruit.key.name}">
     <input type="submit" name="commit" value="Remove Fruit" />
   </sf:form>
</c:forEach>
<br />
<a href="${pageContext.request.contextPath}/placeOrder">Place Order</a>
<br /><br />
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>