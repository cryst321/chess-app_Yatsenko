<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Error Page</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/styles.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/bootstrap-3.3.7/css/bootstrap.min.css" />" />
</head>
<body>
	<div class="container-fluid " align="center">
		<div class="row-fluid ">

			<div class=" error alert alert-danger ">
				<strong> ${pageContext.errorData.statusCode}</strong><br /> <strong>
					Page not found
				</strong>
			</div>

		</div>
		<div class="row-fluid ">
			<a href="${pageContext.request.contextPath}/chess/">Back to
				home page</a>
		</div>

	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/bootstrap-3.3.7/js/bootstrap.min.js"/>"></script>
</body>
</html>