<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Events Table</title>
</head>
<body>
<h1 align="center">My Events</h1>
<h2><a href="/newEvent">Add Event</a> </h2>
<br/>
<table border="1" cellpadding="10" align="center">
    <tr>
        <th>type</th><th>privacy</th><th>description</th><th>date</th><th>time</th>
    </tr>
    <c:forEach var="event" items="${events}">
        <tr>
            <td>${event.type}</td>
            <td>${event.privacy}</td>
            <td>${event.description}</td>
            <td>${event.date}</td>
            <td>${event.time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>