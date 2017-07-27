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
    <sf:form modelAttribute="address" action="${formHandler}" cssClass="reg-form">
        <h1><s:message code="address.title"></s:message></h1>
        <hr>
        <div class="form-row">
            <label for="city"><s:message code="address.city"></s:message></label>
           <sf:input path="city" required="required"/>
        </div>
       <div class="form-row">
           <sf:errors path="city" cssClass="error"/>
       </div>
        <div class="form-row">
           <label for="street"><s:message code="address.street"></s:message></label>
          <sf:input path="street" required="required"/>
        </div>
        <div class="form-row">
           <sf:errors path="street" cssClass="error"/>
       </div>
       <div class="form-row">
          <label for="home"><s:message code="address.home"></s:message></label>
           <sf:input path="home" required="required"/>
        </div>
       <div class="form-row">
           <sf:errors path="home" cssClass="error"/>
       </div>
       <div class="form-row">
           <label for="apartment"><s:message code="address.appartment"></s:message></label>
           <sf:input path="apartment"/>
        </div>
       <div class="form-row">
          <label for="zipcode"><s:message code="address.zipcode"></s:message></label>
          <sf:input path="zipcode" required="required"/>
       </div>
       <div class="form-row">
           <sf:errors path="zipcode" cssClass="error"/>
       </div>
       <hr>
        <div class="form-row">
          <button type="submit" class="btn_submit"><s:message code="auth.next"/></button>
       </div>
    </sf:form>
    <div class="form-error">${error}</div>
</body>
</html>
