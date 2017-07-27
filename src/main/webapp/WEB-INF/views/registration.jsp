<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Registration page</title>
    <link rel="stylesheet" media="screen" href="./static/css/style.css"/>
</head>
<body>
    <sf:form modelAttribute="person" action="${formHandler}" cssClass="reg-form">
        <h1><s:message code="auth.title"></s:message></h1>
        <hr>
        <div class="form-row">
            <label for="login"><s:message code="auth.login"></s:message></label>
            <sf:input path="login" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="login" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="password"><s:message code="auth.password"></s:message></label>
            <sf:password path="password" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="password" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="firstName"><s:message code="auth.firstname"></s:message></label>
            <sf:input path="firstName" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="firstName" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="lastName"><s:message code="auth.lastname"></s:message></label>
            <sf:input path="lastName" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="lastName" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="birthday"><s:message code="auth.birthday"></s:message></label>
            <sf:input path="birthday" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="birthday" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="education"><s:message code="auth.education"></s:message></label>
            <sf:select path="education" required="required">
                <sf:option value="none"><s:message code="auth.select"/></sf:option>
                <sf:option value="school"><s:message code="auth.school"/></sf:option>
                <sf:option value="technical"><s:message code="auth.technical"/></sf:option>
                <sf:option value="higher"><s:message code="auth.higher"/></sf:option>
            </sf:select>
        </div>
        <div class="form-row">
            <sf:errors path="birthday" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="email"><s:message code="auth.email"></s:message></label>
            <sf:input path="email" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="email" cssClass="error"/>
        </div>
        <div class="form-row">
            <label for="phone"><s:message code="auth.phone"></s:message></label>
            <sf:input path="phone" required="required"/>
        </div>
        <div class="form-row">
            <sf:errors path="phone" cssClass="error"/>
        </div>
        <hr>
        <div class="form-row">
            <button type="submit" class="btn_submit"><s:message code="auth.next"/></button>
        </div>
    </sf:form>
    <div class="form-error">${error}</div>
</body>
</html>
