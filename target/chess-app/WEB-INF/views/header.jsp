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
            padding-bottom: 7rem;
            min-height: 100%;
            font-family: "Lora", serif;
        }

    </style>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Chess UA title -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Chess UA</a>
        </div>

        <!-- Navigation links -->
        <ul class="nav navbar-nav">
            <li><a href="#">Main</a></li>
            <li><a href="#">Lobby</a></li>
            <li><a href="#">Players</a></li>
        </ul>

        <!-- Profile icon -->
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#" class="profile-icon"><i class="fa fa-user-circle-o" aria-hidden="true"></i></a></li>
        </ul>
    </div>
</nav>

