<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 13.05.2024
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<body>
<div class="container">
<h1>All User Credentials</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Nickname</th>
    <th>Email</th>
    <th>Password</th>
    <th>Role</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="credentials" items="${credentials}">
    <tr>
      <td>${credentials.id}</td>
      <td>${credentials.nickname}</td>
      <td>${credentials.email}</td>
      <td>${credentials.password}</td>
      <td>${credentials.role}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>

<%@include file="footer.jsp"%>
