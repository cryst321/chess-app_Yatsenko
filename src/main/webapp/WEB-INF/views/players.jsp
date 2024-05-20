<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 16.05.2024
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<style>
  .container {
    margin-top: 20px;
  }
  .profile-pic {
    width: 50px;
    height: 50px;
    object-fit: cover;
    border-radius: 50%;
  }
  table {
    width: 100%;
    border-collapse: collapse;
  }
  th, td {
    padding: 10px;
    text-align: left;
  }
  thead {
    background-color: #f2f2f2;
  }
  tbody tr:hover {
    background-color: #ececec;
  }
</style>

<body>
<div class="container">
  <h1 class="text-center">All Players</h1>
  <table class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
      <th>Profile Picture</th>
      <th>Nickname</th>
      <th>Rating (Bullet / Blitz / Rapid / Classic)</th>
      <th>Country</th>
      <th>Joined at</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="player" items="${users}">
      <tr onclick="redirectToProfile(${player.userCredentials.id})">
        <td>
          <img class="profile-pic" src="${player.userDetails.profilePicture != null ? player.userDetails.profilePicture : 'https://i.ibb.co/R9hVKtm/blank.jpg'}" alt="Profile Picture">
        </td>
        <td>${player.userCredentials.nickname}</td>
        <td>
            ${player.userDetails.ratingBullet} /
            ${player.userDetails.ratingBlitz} /
            ${player.userDetails.ratingRapid} /
            ${player.userDetails.ratingClassic}
        </td>

        <td>${player.userDetails.country != null ? player.userDetails.country : '-'}</td>
        <td>
          <fmt:formatDate value="${player.userDetails.accountCreatedAt}" pattern="yyyy-MM-dd"/>
        </td>

      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script>
  function redirectToProfile(userId) {
    window.location.href = '${pageContext.request.contextPath}/chess/profile?userId=' + userId;
  }
</script>


<%@include file="footer.jsp"%>
