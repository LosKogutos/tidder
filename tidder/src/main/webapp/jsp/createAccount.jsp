<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tidder create Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/angular-block-ui/0.2.2/angular-block-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-block-ui/0.2.2/angular-block-ui.min.js"></script>
<link href="css/bootstrapTemplate.css" rel="stylesheet">
<script src="js/MainPage.js"></script>
<script src="scripts/ui-bootstrap-tpls-2.5.0.min.js"></script>
<script src="js/Login.js"></script>

<title>Tidder new account</title>
</head>

<body>

			<div class="container loginPage" ng-controller="LoginController">
	<div class="row">

		<div class="content">
			<div class="createAccountForm">
				<c:choose>
					<c:when test="${not empty error}">
						<div class="alert alert-danger">
							Your account creation was unsuccessful. <br /> 
							You already created account with this email.
						</div>
					</c:when>
				</c:choose>
				<div class="col-md-1">
			
				</div>
				<div class="col-md-2 haveFun">
					<p>You can register here and have fun with other users</p>
				<span class="glyphicon glyphicon-hand-right"></span>
				<span class="glyphicon glyphicon-record"></span>
				</div>
				<div class="col-md-4 col-md-offset-3 credentials">
				
				    </br>
				<form:form modelAttribute="userProfile">
				    <h2>Create Account</h2>
					<table>
						<tr>
							<td>Email:</td>
							<td><form:input path="email" class="form-control" id="email"
									placeholder="Enter email" /></td>
							<td><form:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><form:input type="password" path="password"
									class="form-control" id="password" placeholder="Enter password" /></td>
							<td><form:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><form:input path="name" class="form-control" id="name"
									placeholder="Enter name" /></td>
							<td><form:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Lastname:</td>
							<td><form:input path="lastname" class="form-control"
									id="lastname" placeholder="Enter lastname" /></td>
							<td><form:errors path="lastname" cssClass="error" /></td>
						</tr>
				
					</table>
							<button class="btn btn-submit" type="submit">Submit</button>
				</form:form>
				</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>