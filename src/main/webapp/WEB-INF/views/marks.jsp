<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Address page</title>
    <link rel="stylesheet" media="screen" href="./static/css/style.css"/>
</head>
<body>
<sf:form modelAttribute="marks" action="${formHandler}" cssClass="reg-form">
    <h1><s:message code="marks.title"></s:message></h1>
    <hr>
    <div class="form-row">
        <label for="algebra"><s:message code="marks.algebra"></s:message></label>
        <sf:input path="algebra"/>
    </div>
    <div class="form-row">
        <sf:errors path="algebra" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="geometry"><s:message code="marks.geometry"></s:message></label>
        <sf:input path="geometry"/>
    </div>
    <div class="form-row">
        <sf:errors path="geometry" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="physics"><s:message code="marks.physics"></s:message></label>
        <sf:input path="physics"/>
    </div>
    <div class="form-row">
        <sf:errors path="physics" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="chemistry"><s:message code="marks.chemistry"></s:message></label>
        <sf:input path="chemistry"/>
    </div>
    <div class="form-row">
        <sf:errors path="chemistry" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="biology"><s:message code="marks.biology"></s:message></label>
        <sf:input path="biology"/>
    </div>
    <div class="form-row">
        <sf:errors path="biology" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="computer_science"><s:message code="marks.computer_science"></s:message></label>
        <sf:input path="computer_science"/>
    </div>
    <div class="form-row">
        <sf:errors path="computer_science" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="english"><s:message code="marks.english"></s:message></label>
        <sf:input path="english"/>
    </div>
    <div class="form-row">
        <sf:errors path="english" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="geography"><s:message code="marks.geography"></s:message></label>
        <sf:input path="geography"/>
    </div>
    <div class="form-row">
        <sf:errors path="geography" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="literature"><s:message code="marks.literature"></s:message></label>
        <sf:input path="literature"/>
    </div>
    <div class="form-row">
        <sf:errors path="literature" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="music"><s:message code="marks.music"></s:message></label>
        <sf:input path="music"/>
    </div>
    <div class="form-row">
        <sf:errors path="music" cssClass="error"/>
    </div>
    <div class="form-row">
        <label for="drawing"><s:message code="marks.drawing"></s:message></label>
        <sf:input path="drawing"/>
    </div>
    <div class="form-row">
        <sf:errors path="drawing" cssClass="error"/>
    </div>
    <hr>
    <div class="form-row">
        <button type="submit" class="btn_submit"><s:message code="auth.next"/></button>
    </div>
</sf:form>
<div class="form-error">${error}</div>
</body>
</html>
