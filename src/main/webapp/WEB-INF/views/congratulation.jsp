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
        <h1>
            <s:message code="auth.greeting"/>&nbsp;
            <c:out value="${sessionScope.firstName}"/>&nbsp;
            <c:out value="${sessionScope.lastName}"/>!
        </h1>
        <hr>
        <div class="form-row">
            <p><s:message code="auth.congratulation"/></p>
        </div>
        <hr>
        <h2><s:message code="auth.uploaded"/></h2>
        <table border="1">
            <tr>
                <th><s:message code="auth.name.original"/></th>
                <th><s:message code="auth.name.uploaded"/></th>
                <th><s:message code="auth.file.size"/></th>
            </tr>
            <c:forEach items="${sessionScope.files}" var="file">
                <tr>
                    <td><c:out value="${file.originalName}"/></td>
                    <td><c:out value="${file.uploadName}"/></td>
                    <td><c:out value="${file.size}"/></td>
                </tr>
            </c:forEach>
        </table>
        <hr>
        <div class="form-row">
            <button type="submit" class="btn_submit"><s:message code="auth.next"/></button>
        </div>
    </sf:form>
</body>
</html>

