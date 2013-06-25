<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="esapi" uri="/WEB-INF/tld/esapi.tld" %>
<h1>URL normalized</h1>

<c:choose>
    <c:when test="${not empty error}">
        <div class="error"><esapi:encodeForHTML>${error}</esapi:encodeForHTML></div>
    </c:when>
    <c:otherwise>
        <div class="info"><esapi:encodeForHTML>${url}</esapi:encodeForHTML></div>
    </c:otherwise>
</c:choose>