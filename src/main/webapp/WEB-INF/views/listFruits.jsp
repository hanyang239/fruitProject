<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Our Fruits</title>
</head>
<body>
<h2><b>Our Fruits</b></h2>
<c:forEach var="eachFruit" items="${listOfFruits}">
	<br />
	<b>Name:</b> ${eachFruit.name}
	<br />
	<b>Price:</b> ${eachFruit.price}
	<br />
	<sf:form action="addFruitToBasket/${eachFruit.name}">
     <input type="submit" name="commit" value="Add" />
   </sf:form>  
</c:forEach>
<br />
<a href="${pageContext.request.contextPath}/">Home</a>
</body>
</html>