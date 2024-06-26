<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 13.05.2024
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Credentials</title>
  <!-- Bootstrap CSS -->
  <style>
    table {
      border-collapse: separate;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    tbody tr:hover {
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      transition: box-shadow 0.3s ease-out;
    }
  </style>
</head>

<body>
<div class="container mt-5">
  <h1 class="mb-4">All User Credentials</h1>
  <table class="table table-bordered table-striped">
    <thead class="thead-dark">
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

<!-- Bootstrap JS and dependencies -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.7/js/bootstrap.min.js"></script>
