<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 12.05.2024
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h1>
            Log In
        </h1>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <form action="./login" method="POST" role="form">

                <c:if test="${not empty requestScope.errors}">
                    <div class="alert alert-danger">
                        <c:forEach items="${requestScope.errors}" var="error">
                            <p>
                                ${error}
                            </p>
                        </c:forEach>
                    </div>
                </c:if>

                <div class="form-group">
                    <label for="email">Email</label> <input type="text" class="form-control"
                                                                                     id="email" name="email"
                                                                                     placeholder="Email"
                                                                                     value="<c:out value="${requestScope.loginUser.getEmail()}" />" />
                </div>
                <div class="form-group">
                    <label for="password">Password</label> <input type="password" class="form-control"
                                                                                        id="password" name="password"
                                                                                        placeholder="Password" />
                </div>
                <button type="submit" class="btn btn-default" id="submitButton">
                 Login
                </button>

                <p>
                    Or <a href="${pageContext.request.contextPath}/chess/signup">Sign Up</a> if you're not yet registered.
                </p>

            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>