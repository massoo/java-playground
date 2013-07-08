<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Demo's</h1>

<table id="table_overview">
    <tr>
        <td>JSR 303 login :</td>
        <td><a href="<c:url value='/jsr/index'/>">JSR Login</a></td>
    </tr>
    <tr>
        <td>URL whitelist redirect :</td>
        <td><a href="<c:url value='/url/index-redirect'/>">URL whitelist redirect</a></td>
    </tr>
    <tr>
        <td>URL normalization :</td>
        <td><a href="<c:url value='/url/index-normalization'/>">URL normalization</a></td>
    </tr>
</table>