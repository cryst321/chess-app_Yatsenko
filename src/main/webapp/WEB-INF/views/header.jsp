<%--
  Created by IntelliJ IDEA.
  User: Maryna
  Date: 23.04.2024
  Time: 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Chess App</title>

    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/bootstrap-3.3.7/css/bootstrap.css" />" />
    <link rel="stylesheet"
          href="<c:url value="/resources/font-awesome-4.7.0/css/font-awesome.min.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/css/styles.css" />" />
    <style>
        .profile-icon {
            font-size: 30px;
        }
        body {
            position: relative;
            margin: 0;
            padding-bottom: 3rem;
            min-height: 100%;
            font-family: "Lora", serif;
            font-size: 16px;
        }
         .navbar-nav > li > a {
             font-weight: bold;
             font-family: "Noto Sans", sans-serif;
             padding-bottom:5px;

         }
        .navbar-brand {
            font-family: "Noto Sans", sans-serif;
            font-weight: bold;
            text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);
            font-size: 1.5em;
            padding-right: 20px;
        }
    </style>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Chess UA title -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <div class="d-flex align-items-center">
                    <img src="https://i.ibb.co/Y39L5br/icons8-chess-100.png" alt="Chess Icon" width="25" height="25">
                    <span class="ml-2">Chess UA</span>
                </div>
            </a>

        </div>

        <!-- Navigation links -->
        <ul class="nav navbar-nav">
            <li><a href="/chess/creategame">GAME</a></li>
            <li><a href="#">LOBBY</a></li>
            <li><a href="/chess/players">COMMUNITY</a></li>
            <!-- Moderation link for admin or moderator -->
            <c:if test="${not empty userCredentials and (userCredentials.role == 'admin' or userCredentials.role == 'moderator')}">
                <li><a href="#">MODERATION</a></li>
            </c:if>
        </ul>

        <!-- Profile icon -->
        <ul class="nav navbar-nav navbar-right">

            <c:choose>
                <c:when test="${empty userCredentials}">
                    <li><a
                            href="${pageContext.request.contextPath}/chess/login"><span
                            class="glyphicon glyphicon-log-out"></span> Log In</a></li>
                </c:when>
                <c:otherwise>
                    <li><a
                            href="${pageContext.request.contextPath}/chess/logout"><span
                            class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
                </c:otherwise>
            </c:choose>

            <c:if test="${not empty userCredentials}">
            <li><a href="/chess/profile" class="profile-icon"><i class="fa fa-user-circle-o" aria-hidden="true"></i></a></li>
            </c:if>

        </ul>
    </div>
</nav>

