<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h1>Add new event</h1>
<form:form method="POST"
           action="/newEvent" modelAttribute="event">
    <table>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="type">Type</form:label></td>
            <td><form:input path="type"/></td>
        </tr>
        <tr>
            <td><form:label path="privacy">Privacy</form:label></td>
            <td><form:input path="privacy"/></td>
        </tr>
        <tr>
            <td><form:label path="date">Date(dd-MM-yyyy)</form:label></td>
            <td><form:input type="date" path="date"/></td>
        </tr>
        <tr>
            <td><form:label path="time">Time</form:label></td>
            <td><form:input type="time" path="time"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>