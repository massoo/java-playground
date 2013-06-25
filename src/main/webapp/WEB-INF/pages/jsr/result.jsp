<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="esapi" uri="/WEB-INF/tld/esapi.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>JSR 303 logged in</h1>

<table class="table_standard">
    <tr>
        <td>Email</td>
        <td><esapi:encodeForHTML>${email}</esapi:encodeForHTML></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><esapi:encodeForHTML>${password}</esapi:encodeForHTML></td>
    </tr>
</table>