<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
    <body>
        <a href="${spring:mvcUrl('loginPerson').build()}">Login</a>
        <a href="${spring:mvcUrl('registrationPerson').build()}">Registration</a>
    </body>
</html>
