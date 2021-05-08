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
            <td><form:input path="description" required="required"/></td>
        </tr>
        <tr>
            <td><form:label path="type">Type</form:label></td>
           <td> Meeting: <form:radiobutton path="type" value="meeting"/>
            Task: <form:radiobutton path="type" value="task"/></td>
        </tr>
        <tr>
            <td><form:label path="privacy">Privacy</form:label></td>
            <td><form:select path="privacy" items="${privacyOptions}" /></td>
        </tr>
        <tr>
            <td><form:label path="date">Date(yyyy-MM-dd)</form:label></td>
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
</body>
</html>