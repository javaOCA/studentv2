<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Login page</title>
    <link rel="stylesheet" media="screen" href="./static/css/style.css"/>
</head>
<body>
    <sf:form modelAttribute="person" action="${formHandler}" cssClass="reg-form">
        <h1><s:message code="login.title"></s:message></h1>
        <hr>
        <div class="form-row">
            <label for="login"><s:message code="auth.login"></s:message></label>
            <sf:input path="login" required="required"/>
        </div>
        <div class="form-row">
            <label for="password"><s:message code="auth.password"></s:message></label>
            <sf:password path="password" required="required"/>
        </div>
        <hr>
        <div class="form-row">
            <button type="submit" class="btn_submit"><s:message code="login.enter"/></button>
            <a href="./registration" class="a-reg"><s:message code="auth.title"></s:message></a>
        </div>
    </sf:form>
    <div class="form-error">${error}</div>
</body>
</html>

