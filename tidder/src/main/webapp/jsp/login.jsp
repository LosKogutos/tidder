<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tidder Login</title>
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

</head>
<body ng-app="Tidder">
<div class="container loginPage" ng-controller="LoginController">
	<div class="row">

		<div class="content">
			<div class="">
				<c:choose>
			
					<c:when test="${not empty param.accCreated}">
						<div class="alert alert-success">
							Your account was created successfully. You can now log in. <br />
						</div>
					</c:when>
					<c:when test="${not empty param.accNotCreated}">
						<div class="alert alert-danger">
							Something went wrong during account creation. Try again. <br />
						</div>
					</c:when>
				</c:choose>
				<div class="col-md-6 col-md-offset-3 credentials">
				    <h2>Login</h2>
				    </br>
				    <form action="login" name="f" method="POST" ng-submit="vm.login()" >
				        <div class="form-group" ng-class="{ 'has-error': form.username.$dirty && form.username.$error.required }">
				            <label for="login">Email:</label>
				            <input type="text" name="email" id="email" placeholder="Enter email"class="form-control" ng-model="vm.email" required />
				            <span ng-show="form.email.$dirty && form.email.$error.required" class="help-block">Email is required</span>
				        </div>
				        <div class="form-group" ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
				            <label for="pwd">Password</label>
				            <input type="password" name="password" id="password" placeholder="Enter password" class="form-control" ng-model="vm.password" required />
				            <span ng-show="form.password.$dirty && form.password.$error.required" class="help-block">Password is required</span>
				        </div>
				        <div class="form-actions">
				            <button type="submit" ng-disabled="form.$invalid || vm.dataLoading" class="btn btn-primary">Login</button>
				            <img ng-if="vm.dataLoading" src="data:image/gif;base64,R0lGODlhEAAQAPIAAP///wAAAMLCwkJCQgAAAGJiYoKCgpKSkiH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCgAAACwAAAAAEAAQAAADMwi63P4wyklrE2MIOggZnAdOmGYJRbExwroUmcG2LmDEwnHQLVsYOd2mBzkYDAdKa+dIAAAh+QQJCgAAACwAAAAAEAAQAAADNAi63P5OjCEgG4QMu7DmikRxQlFUYDEZIGBMRVsaqHwctXXf7WEYB4Ag1xjihkMZsiUkKhIAIfkECQoAAAAsAAAAABAAEAAAAzYIujIjK8pByJDMlFYvBoVjHA70GU7xSUJhmKtwHPAKzLO9HMaoKwJZ7Rf8AYPDDzKpZBqfvwQAIfkECQoAAAAsAAAAABAAEAAAAzMIumIlK8oyhpHsnFZfhYumCYUhDAQxRIdhHBGqRoKw0R8DYlJd8z0fMDgsGo/IpHI5TAAAIfkECQoAAAAsAAAAABAAEAAAAzIIunInK0rnZBTwGPNMgQwmdsNgXGJUlIWEuR5oWUIpz8pAEAMe6TwfwyYsGo/IpFKSAAAh+QQJCgAAACwAAAAAEAAQAAADMwi6IMKQORfjdOe82p4wGccc4CEuQradylesojEMBgsUc2G7sDX3lQGBMLAJibufbSlKAAAh+QQJCgAAACwAAAAAEAAQAAADMgi63P7wCRHZnFVdmgHu2nFwlWCI3WGc3TSWhUFGxTAUkGCbtgENBMJAEJsxgMLWzpEAACH5BAkKAAAALAAAAAAQABAAAAMyCLrc/jDKSatlQtScKdceCAjDII7HcQ4EMTCpyrCuUBjCYRgHVtqlAiB1YhiCnlsRkAAAOwAAAAAAAAAAAA==" />
				            <a href="/tidder/createAccount.html" class="btn btn-link">Register</a>
				        </div>
				    </form>
				</div>
				<div class="col-md-2 col-md-offset-1">
					<p class="haveFun">
					<span class="glyphicon glyphicon-eye-open">
					</span>
					Hello from tidder! Reddit younger brother.
					</p>	
						
				</div>
	<!-- 			<div class="row" >
					<div class="col-md-3 col-md-offset-3">
				
					</div>
					
				
				</div>
			 -->
			</div>
		</div>
	</div>

</div>
</body>
</html>