<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 20.05.2024
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container mt-5">
  <h1 class="mb-4">Lobby</h1>
  <table class="table table-bordered table-striped">
    <thead class="thead-dark">
    <tr>
      <th>Nickname</th>
      <th>Game Type</th>
      <th>Rating</th>
      <th>Time Restriction (seconds)</th>
      <th>Preferred Color</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="gameRequest" items="${gameRequests}">
      <tr>
        <td>${gameRequest.user.userCredentials.nickname}</td>
        <td>${gameRequest.gameType}</td>
        <td>
          <c:choose>
            <c:when test="${gameRequest.gameType.getValue() == 'blitz'}">
              ${gameRequest.user.userDetails.ratingBlitz}
            </c:when>
            <c:when test="${gameRequest.gameType.getValue()  == 'bullet'}">
              ${gameRequest.user.userDetails.ratingBullet}
            </c:when>
            <c:when test="${gameRequest.gameType.getValue()  == 'rapid'}">
              ${gameRequest.user.userDetails.ratingRapid}
            </c:when>
            <c:when test="${gameRequest.gameType.getValue()  == 'classic'}">
              ${gameRequest.user.userDetails.ratingClassic}
            </c:when>
          </c:choose>
        </td>
        <td>${gameRequest.timeRestriction}</td>
        <td>
          <c:choose>
            <c:when test="${gameRequest.preferredColor == 'white'}">&#9899;</c:when>
            <c:when test="${gameRequest.preferredColor == 'black'}">&#9898;</c:when>
            <c:otherwise>&#9681;</c:otherwise>
          </c:choose>
        </td>
        <td>
          <c:choose>
            <c:when test="${gameRequest.user.userCredentials.id == sessionScope.userCredentials.id}">
              <form action="/chess/deleteGameRequest?gameRequestId=${gameRequest.requestId}" method="post">
                <input type="hidden" name="requestId" value="${gameRequest.requestId}">
                <button type="submit" class="btn btn-danger">Cancel</button>
              </form>
            </c:when>
            <c:otherwise>
              <form action="/chess/acceptGameRequest?gameRequestId=${gameRequest.requestId}" method="post">
                <input type="hidden" name="requestId" value="${gameRequest.requestId}">
                <button type="submit" class="btn btn-success">Accept</button>
              </form>
            </c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>


<script>
  function pollGameStatus() {
    setInterval(function() {
      fetch("${pageContext.request.contextPath}/chess/checkGameStatus")
              .then(response => {
                if (response.redirected) {
                  window.location.href = response.url;
                } else {
                  return response.text();
                }
              })
              .then(gameId => {
                if (gameId && !response.redirected) {
                  window.location.href = "${pageContext.request.contextPath}/chess/game?gameId=" + gameId;
                }
              });
    }, 5000);
  }

  pollGameStatus();
</script>



<%@include file="footer.jsp"%>
