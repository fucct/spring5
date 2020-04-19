<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020/04/19
  Time: 11:33 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="change.pwd.title"/></title>
</head>
<body>
<form:form>
    <p>
        <spring:message code="change.pwd.done"/>
    </p>
    <p>
        <a href="<c:url value='/main'/>">
            [<spring:message code="go.main"/>]
        </a>
    </p>
    <input type="submit" value="<spring:message code='change.btn' />">
</form:form>
</body>
</html>
