<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Congratulation page</title>
    <link rel="stylesheet" media="screen" href="./static/css/style.css"/>
</head>
<body>
    <sf:form cssClass="reg-form">
        <h3>
            <s:message code="auth.greeting"/>&nbsp;
            <c:out value="${firstName}"/>&nbsp;
            <c:out value="${lastName}"/>!
        </h3>
        <hr>
        <div class="form-row">
            <p><s:message code="auth.congratulation"></s:message></p>
        </div>
        <hr>
        <div class="form-row">
            <button type="submit" class="btn_submit"><s:message code="auth.next"/></button>
        </div>
    </sf:form>
</body>
</html>

