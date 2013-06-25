<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>URL whitelist redirect</h1>

<form method="get" action="<c:url value='/demo/url/doRedirect' />">
    <c:if test="${not empty error}">
        <div class="error">
                ${error}
        </div>
    </c:if>
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
