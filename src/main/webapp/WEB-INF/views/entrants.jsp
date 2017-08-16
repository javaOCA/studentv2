<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Administration page</title>
    <link rel="stylesheet" media="screen" href="/static/css/admin.css"/>
</head>
<body>
    <%@ include file="../jspf/adminmenu.jspf" %>
    <table border="1">
        <tr>
            <th><s:message code="faculty.id"/></th>
            <th><s:message code="auth.firstname"/></th>
            <th><s:message code="auth.lastname"/></th>
            <th><s:message code="auth.email"/></th>
            <th><s:message code="auth.phone"/></th>
            <th><s:message code="auth.faculty"/></th>
            <th><s:message code="total.score"/></th>
            <th><s:message code="faculty.enlist"/></th>
            <th><s:message code="receipt.date"/></th>
            <th><s:message code="faculty.action"/></th>
        </tr>
        <c:forEach items="${entrants}" var="ent">
            <tr>
                <sf:form modelAttribute="person" action="/admin/entrants/update">
                    <td><sf:input path="id" size="3" readonly="true" value="${ent.id}"/></td>
                    <td><sf:input path="firstName" size="20" value="${ent.firstName}"/></td>
                    <td><sf:input path="lastName" size="20" value="${ent.lastName}"/></td>
                    <td><sf:input path="email" size="20" value="${ent.email}"/></td>
                    <td><sf:input path="phone" size="20" value="${ent.phone}"/></td>
                    <td><sf:input path="faculty" size="30" readonly="true" value="${ent.faculty}"/></td>
                    <td><c:out value="${ent.enlist.totalScore}"/></td>
                    <td><c:out value="${ent.enlist.receiptDate}"/></td>
                    <td><c:out value="${ent.enlist.action}"/></td>
                    <td>
                        <button type="submit" class="btn_submit"><s:message code="faculty.update"/></button>
                    </td>
                </sf:form>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
