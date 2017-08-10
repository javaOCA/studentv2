<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Profile Page</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Birthday</th>
            <th>Total mark</th>
            <td>Get into faculty</td>
        </tr>
        <tr>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.birthday}</td>
            <td>${total}</td>
            <td>Your data are processing, wait...</td>
        </tr>
    </table>
    <hr>
    <a href="/exit">Logout -></a>
</body>
</html>
