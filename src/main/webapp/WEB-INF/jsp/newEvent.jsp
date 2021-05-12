<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet" />
<title>New Event</title>
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
            <td><form:label for="meeting" path="type">Type</form:label></td>
           <td> <label for="meeting">meeting</label><form:radiobutton path="type" value="meeting" name="meeting" checked="checked" />
               <form:label path="type" for="task">task</form:label>
             <form:radiobutton path="type" value="task" name="task" /></td>
        </tr>
        <tr>
            <td><form:label path="privacy">Privacy</form:label></td>
            <td><form:select path="privacy" items="${privacyOptions}" /></td>
        </tr>
        <tr>
            <td><form:label path="date">Date</form:label></td>
            <td><form:input type="date" path="date" required="required" placeholder="yyyy-MM-dd" /></td>
        </tr>
        <tr>
            <td><form:label path="time">Time</form:label></td>
            <td><form:input type="time" path="time" required="required" placeholder="hh:mm"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>