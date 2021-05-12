<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet" />
    <title>Event update</title></head>
<body>
<h1>${event.description}</h1>
<form:form method="PUT"
           action="/events/${event.id}" modelAttribute="event">
    <table>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description" required="required"/></td>
        </tr>
        <tr>
            <c:set var="meeting" value="meeting"/>
            <td><form:label path="type">Type</form:label></td>
            <td> Meeting: <form:radiobutton path="type" value="meeting"/>
                Task: <form:radiobutton path="type" value="task"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="privacy">Privacy</form:label></td>
            <td><form:select path="privacy" items="${privacyOptions}" />
            </td>
        </tr>
        <tr>
            <td><form:label path="date">Date</form:label></td>
            <td><form:input type="date" path="date" required="required" /></td>
        </tr>
        <tr>
            <td><form:label path="time">Time</form:label></td>
            <td><form:input type="time" path="time" required="required" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
<h2><a href="/events">Back to list</a> </h2>
</body>
</html>
