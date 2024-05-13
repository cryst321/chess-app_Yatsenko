
<%@include file="WEB-INF/views/header.jsp"%>


<body>
<div class="container">
<h1>Users</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nickname</th>
        <th>Email</th>
    </tr>
    <c:forEach var="u" items="${users}">
        <tr>
            <td>${u.id}</td>
            <td>${u.nickname}</td>
            <td>${u.email}</td>
        </tr>
    </c:forEach>

</table>
</div>


<%@include file="WEB-INF/views/footer.jsp"%>
