<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 16.05.2024
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Players</title>
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
    .profile-pic {
      max-width: 50px;
      max-height: 50px;
    }
  </style>
</head>

<body>
<div class="container mt-5">
  <h1 class="mb-4">All Players</h1>
  <table class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
      <th>Profile Picture</th>
      <th>Nickname</th>
      <th>Rating (Bullet)</th>
      <th>Rating (Blitz)</th>
      <th>Rating (Rapid)</th>
      <th>Rating (Classic)</th>
      <th>Country</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="player" items="${players}">
      <tr>
        <td>
          <img class="profile-pic" src="${player.profilePicture != null ? player.profilePicture : 'https://i.ibb.co/R9hVKtm/blank.jpg'}" alt="Profile Picture">
        </td>
        <td>${player.nickname}</td>
        <td>${player.ratingBullet}</td>
        <td>${player.ratingBlitz}</td>
        <td>${player.ratingRapid}</td>
        <td>${player.ratingClassic}</td>
        <td>${player.country != null ? player.country : '-'}</td>
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
