<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>URL normalization</h1>

<form method="POST"  action="/demo//url/doNormalize">
    <form:errors path="*" element="div"/>
    <table class="table_standard">
        <tr>
            <td>URL :</td>
            <td><input type="text" name="url"/></td>
        </tr>
        <tr>
            <td colspan="2" class="align_right"><input type="submit" name="submit" value="submit"/></td>
        </tr>
    </table>
</form>
