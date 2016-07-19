<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Protected page</title>
</head>
<body>
<h1>Title : ${title}</h1>

<c:if test="${pageContext.request.userPrincipal.name != null}">
<h2>Welcome : ${pageContext.request.userPrincipal.name}
    </c:if>
</h2>
<ul>
    <li><a href="/login">LOGIN</a></li>
    <li><a href="/user">USER PAGE</a></li>
    <li><a href="/admin">ADMIN PAGE</a></li>
</ul>
</body>
</html>