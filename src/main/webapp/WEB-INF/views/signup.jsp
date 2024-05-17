<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 14.05.2024
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h1>
           Sign Up
        </h1>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3 ">
            <form action="./signup" method="POST" role="form">

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
                    <label for="nickname">Nickname</label> <input type="text" class="form-control"
                                                            id="nickname" name="nickname"
                                                            placeholder="Nickname"
                                                            value="<c:out value="${requestScope.loginUser.getNickname()}" />" />
                </div>
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
                    Or <a href="${pageContext.request.contextPath}/chess/login">Sign up</a> if you have an account.
                </p>

            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
