<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>JSR 303 login</h1>

<form:form method="POST" commandName="login" action="/demo/jsr/doLogin">
    <form:errors path="*" cssClass="error" element="div"/>
    <table class="table_standard">
        <tr>
            <td>Email :</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Password :</td>
            <td><form:password path="password"/></td>
        </tr>
        <tr>
            <td colspan="2" class="align_right" ><input type="submit" name="submit" value="submit"/></td>
        </tr>
    </table>
</form:form>
