<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Events Table</title>
</head>
<body>
<header><h1>View events by:</h1><a href="/events/byMonth">Month</a> </header>
<h1 align="center">All Events</h1> <c:if test="${month != null}">for ${month}</c:if>
<h2 align="center"><a href="/newEvent">Add Event</a></h2>
<br/>
<form:form method="POST"
           action="/events" >
    <input type="month" id="yearMonth" name="yearMonth"
           min="2021-01" required = "required">
    <input type="submit" value="Month">
</form:form>

<table border="1" cellpadding="10" align="center">
    <tr>
        <th>type</th><th>privacy</th><th>description</th><th>date</th><th>time</th><th>edit</th><th>delete</th>
    </tr>
    <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.type} </td>
            <td>${event.privacy}</td>
            <td>${event.description}</td>
            <td>${event.date}</td>
            <td>${event.time}</td>
            <td><form action="/events/${event.id}">
                <input type="submit" value="edit" />
            </form></td>
            <td><form:form method="DELETE" action="/delete/${event.id}"><input type="submit" value="delete" onclick="return confirm('Are you sure you want to delete this event?')" ></form:form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>