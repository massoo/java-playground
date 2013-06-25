<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
    <title><tiles:getAsString name="title"/></title>
    <tiles:insertAttribute name="header"/>
</head>
<body>

<tiles:insertAttribute name="nav" />

<div class="container">
    <tiles:insertAttribute name="body"/>
</div>

<tiles:insertAttribute name="back" ignore="true" />

<tiles:insertAttribute name="footer" />

</body>
</html>