<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>User management</h1><br></br>
<p>list of users</p>
<a href="<%=request.getContextPath()%>/new">ADD NEW USER</a>
<table>
<tr>
<th><b>name</b></th>
<th><b>email</b></th>
<th><b>country</b></th>
<th><b>actions</b></th>
</tr>
<c:forEach var="user" items="${a}">
<tr>
<td><c:out value="${user.name}"></c:out></td>
<td><c:out value="${user.email}"></c:out></td>
<td><c:out value="${user.country}"></c:out></td>
<td><a href="edit?=<c:out value='${user.id }'></c:out>">edit</a>
<a href="delete?=<c:out value='${user.id}'></c:out>"></a>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>