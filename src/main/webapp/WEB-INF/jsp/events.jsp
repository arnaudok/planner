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
    <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet" />
    <title>Events Table</title>
</head>
<body>
    <ol>
   <li> <label>View</label> <form:form method="GET"
                   action="/events" >
       <input type="submit" value="All">
   </form:form>

       <form:form method="GET"
                   action="/events" > </li>

        <li>
    <label>by</label>
    <input type="hidden" name="filter" value="month">
    <input type="month" id="yearMonth" name="month"
           min="2021-01" max="2021-12" required = "required" placeholder="2021-01">
    <input type="submit" value="Month">
</form:form>
        </li>

        <li>
    <form:form method="GET"
               action="/events" >
    <label>by</label>
    <input type="hidden" name="filter" value="day">
    <input type="date" name="date"
               min="2021-01-01" max="2021-12-31" required = "required">
        <input type="submit" value="Day">
    </form:form>
        </li>


        <li>
    <form:form method="GET"
               action="/events" >
    <label>by</label>
    <input type="hidden" name="filter" value="type">
    <td> <select name="type">
        <option value="meeting">Meeting</option>
        <option value="task">Task</option>
    </select>
        <input type="submit" value="Type">
    </td>
    </form:form>
        </li>


        <li>
    <form:form method="GET"
               action="/events" >
        <label>for a period</label>
        <input type="hidden" name="filter" value="period">
        <label>between</label>
        <input type="date" name="startDate"
               min="2021-01-01" max="2021-12-31" required = "required">
        <label>and</label>
        <input type="date" name="endDate"
               min="2021-01-01" max="2021-12-31" required = "required">
        <input type="submit" value="period">
    </form:form>
        </li>
    </ol>

<h1 align="center">All Events</h1> <h2 align="center"><c:if test="${month != null}">for the month of ${month}</c:if>
    <c:if test="${day != null}">for ${day}</c:if>
    <c:if test="${type != null}">of type ${type}</c:if>
    <c:if test="${period != null}">${period}</c:if>
</h2>
<h2 align="center"><a href="/newEvent">Add Event</a></h2>
<br/>


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