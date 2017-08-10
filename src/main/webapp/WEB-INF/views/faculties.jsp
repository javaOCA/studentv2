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
            <th><s:message code="faculty.name"/></th>
            <th><s:message code="faculty.date"/></th>
            <th><s:message code="faculty.score"/></th>
            <th><s:message code="faculty.students"/></th>
            <th><s:message code="faculty.action"/></th>
        </tr>
        <c:forEach items="${faculties}" var="fc">
            <tr>
                <sf:form modelAttribute="faculty" action="/admin/faculties/update">
                    <td><sf:input path="id" size="3" readonly="true" value="${fc.id}"/></td>
                    <td><sf:input path="name" size="50" value="${fc.name}"/></td>
                    <td><sf:input path="evaluationDate" size="10" value="${fc.evaluationDate}"/></td>
                    <td><sf:input path="passingScore" size="5" value="${fc.passingScore}"/></td>
                    <td><sf:input path="numberOfStudents" size="5" value="${fc.numberOfStudents}"/></td>
                    <td>
                        <button type="submit" class="btn_submit"><s:message code="faculty.update"/></button>
                    </td>
                </sf:form>
            </tr>
        </c:forEach>
        <tr>
            <form action="/admin/faculties/insert" method="post">
                <td></td>
                <td><input name="u_name" size="50" required="required"/></td>
                <td><input name="u_evaluationDate" size="10"/></td>
                <td><input name="u_passingScore" size="5"/></td>
                <td><input name="u_numberOfStudents" size="5"/></td>
                <td>
                    <button type="submit" class="btn_submit"><s:message code="faculty.insert"/></button>
                </td>
            </form>
        </tr>
    </table>
</body>
</html>
