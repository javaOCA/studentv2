<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Upload page</title>
    <link rel="stylesheet" media="screen" href="./static/css/style.css"/>
</head>
<body>
    <sf:form method="post" action='upload' enctype="multipart/form-data" cssClass="reg-form">
        <h1><s:message code="upload.title"></s:message></h1>
        <hr>
        <div class="form-row">
            <label for="certificate"><s:message code="upload.certificate"></s:message></label>
            <input type="file" name="file" id="certificate" required="required"/>
        </div>
        <div class="form-row">
            <label for="passport"><s:message code="upload.passport"></s:message></label>
            <input type="file" name="file" id="passport" required="required"/>
        </div>
        <hr>
        <p><s:message code="message.file.size"/></p>
        <hr>
        <div class="form-row">
            <button type="submit" class="btn_submit"><s:message code="auth.next"/></button>
        </div>
    </sf:form>
    <div class="form-error">${error}</div>
</body>
</html>
