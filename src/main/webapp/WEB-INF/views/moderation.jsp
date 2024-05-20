<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 16.05.2024
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container mt-5">
    <h1 class="mb-4">Moderation</h1>
    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Complainant</th>
            <th>Reported</th>
            <th>Date</th>
            <th>Type</th>
            <th>Reason</th>
            <th>Moderator</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="complaint" items="${complaints}">
            <c:if test="${complaint.status == 'pending'}">
            <tr>
                <td>${complaint.complainant.userCredentials.nickname}</td>
                <td>${complaint.reported.userCredentials.nickname}</td>
                <td><fmt:formatDate value="${complaint.createdAt}" pattern="yyyy-MM-dd"/></td>
                <td>${complaint.complaintType}</td>
                <td>${complaint.reason}</td>
                <td>
                    <c:choose>
                        <c:when test="${complaint.moderator == null}">
                            <button class="btn btn-warning" onclick="bookComplaint(${complaint.id})">Book</button>
                        </c:when>
                        <c:when test="${complaint.moderator.userCredentials.id == sessionScope.userCredentials.id}">
                            <button class="btn btn-danger" onclick="unbookComplaint(${complaint.id})">Unbook</button>
                        </c:when>
                        <c:otherwise>
                            ${complaint.moderator.userCredentials.nickname}
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <button class="btn btn-success" onclick="markResolved(${complaint.id})">Mark Resolved</button>
                </td>
            </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>


    <c:if test="${sessionScope.userCredentials.role == 'admin'}">
        <hr>
        <h1 class="mb-4">Recently Resolved</h1>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Moderator</th>
                <th>Reported</th>
                <th>Type</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="complaint" items="${complaints}">
                <c:if test="${complaint.status == 'resolved'}">
                    <tr>
                        <td>${complaint.moderator.userCredentials.nickname}</td>
                        <td>${complaint.reported.userCredentials.nickname}</td>
                        <td>${complaint.complaintType}</td>
                        <td><fmt:formatDate value="${complaint.createdAt}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:if>


</div>




<%@include file="footer.jsp"%>

<script>
    function bookComplaint(complaintId) {
        window.location.href = "${pageContext.request.contextPath}/chess/bookComplaint?complaintId=" + complaintId;
    }

    function unbookComplaint(complaintId) {
        window.location.href = "${pageContext.request.contextPath}/chess/unbookComplaint?complaintId=" + complaintId;
    }

    function markResolved(complaintId) {
        window.location.href = "${pageContext.request.contextPath}/chess/markResolved?complaintId=" + complaintId;
    }
</script>
