<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2020/04/19
  Time: 1:32 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="member.register"/></title>
</head>
<body>
    <h2><spring:message code="member.info"/></h2>
    <form:form action="step3" modelAttribute="registerRequest">
        <p>
            <label>
                <spring:message code="email"/> :<br>
                <form:input path="email"/>
                <form:errors path="email" element="div"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="name"/> :<br>
                <form:input path="name"/>
                <form:errors path="name" element="div"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="password"/> :<br>
                <form:password path="password"/>
                <form:errors path="password" element="div"/>
            </label>
        </p>
        <p>
            <label>
                <spring:message code="password.confirm"/>:<br>
                <form:password path="confirmPassword"/>
                <form:errors path="confirmPassword" element="div"/>
            </label>
        </p>
        <input type="submit" value="<spring:message code="register.btn"/>">
    </form:form>
</body>
</html>
