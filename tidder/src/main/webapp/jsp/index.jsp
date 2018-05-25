<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Tidder</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/angular-block-ui/0.2.2/angular-block-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-block-ui/0.2.2/angular-block-ui.min.js"></script>

<link href="css/bootstrapTemplate.css" rel="stylesheet">
<script src="js/MainPage.js"></script>
<script src="scripts/ui-bootstrap-tpls-2.5.0.min.js"></script>

</head>
<body ng-app="Tidder" ng-cloak>

	<div class="container-fluid" ng-controller="PaginationMyController" id="style-9">
		<div class="row content">
			<div class="navbar" id="navbar">
				<span>
					<p>Welcome ${user.name} ${user.lastname}</p>
				</span> 
				<span ng-click= "checkIfPostAdded()" class="addPostByUser">
					<p>Add post</p>
				</span> 
				<a class="navbarRight" href="<c:url value="/logout" />">Logout</a>
			</div>
		
			<div class="col-sm-2 ">
				<div class="sidenav">
					<h4 class="text-inside-block"> Welcome ${user.name}</h4>
					<!-- 				<ul class="nav nav-pills nav-stacked">
						<li class="active"><a href="#section1">Edit Account</a></li>
						<li><a href="#section2">Friends</a></li>
						<li><a href="#section3">Family</a></li>
						<li><a href="#section3">Photos</a></li>
						<li><p>{{dataPerPage}}</p></li>
					</ul> -->
					<form ng-submit="changePostsPerPageAmount(ItemsOnPageAmount)">
						<span>Change posts per page</span>
						<input ng-model="ItemsOnPageAmount" type="text" />
						<input type="submit" />
					</form>
					
					
					<br> <a href="<c:url value="/logout" />">Logout</a>
				</div>
				<div class="sidenav">
					<div class=" addPostByUser">
						<form role="form" ng-hide="post.hideWindow" ng-submit="addPost(post.topic, post.text)">
							<h4>Post editor</h4>
							<div class="form-group">
								<label >Topic:</label>
								<input ng-model="post.topic"type="text"/>
							</div>
							<div class="form-group">
								<label >Body:</label>
								<input ng-model="post.text" type="text"/>
							</div>
							<input class="btn" type="submit"/>
						</form>
						<div ng-if="post.postAdded">
							<h2 class="text-inside-block">Post added</h2>
						</div>
						
					</div>
				</div>
			</div>

			<!--    <div class="col-sm-10" ng-controller="MainPageController"> -->
			<div class="col-sm-10 mainPage"
				ng-init="init()">
				<h4>
					<small>RECENT POSTS</small>
				</h4>

				<div ng-model="mydata"></div>

				<div class="comment" ng-repeat="x in dataPerPage track by $index">
					<div class="container">
						<div class="row"></div>
					</div>
					<hr>
					<h2>{{x.topic}}</h2>
					<h5>
						<span class="glyphicon glyphicon-time"></span> Post by
						{{x.user.name}}, {{ x.date }}.
					</h5>
					<button class="btn"
						ng-click="likePost(x.id);">
						<span class="glyphicon glyphicon-chevron-up"></span>
					</button>
					<kbd>{{x.likes}}</kbd>

					<span></span>
					<h5>
						<span class="label label-danger">Food</span> <span
							class="label label-primary">Ipsum</span>
					</h5>
					<br>
					<div class="well animateOnHover">
						<p>{{ x.text }}</p>
					</div>
					<div class="col-xs-11">
						<div class="button">

							<span class="btn"
								ng-click="commentVisible = !commentVisible; commentVisibleOnInit = true;">View
								Comments</span>
							<div ng-if="commentVisibleOnInit" class="showComments"
								ng-class="{active: commentVisible}" ng-show="commentVisible"
								ng-repeat="comment in commentByPostId[x.id].comments">
								<ul>
									<li>
										<div class="row">
											<div class="col-sm-1">
												<img src="http://dl1.cbsistatic.com/i/r/2017/07/26/e5b5301d-c110-4fdc-9434-26e6414e3a6f/thumbnail/64x64/147e8b3da070d61c0cf7b7534bc2d676/imgingest-3529522317414257963.png">
												<button class="btn"
												ng-click="likeComment(comment.id, x.id);">
												<span class="glyphicon glyphicon-chevron-up"></span>
												</button>
												<kbd>{{comment.likes}}</kbd>
											</div>
											<div class="col-sm-11">
												<h4>@{{comment.user.name}}:</h4>
												<div class="well">
													<p>{{comment.text}}</p>
													<p>
														Date: <strong>{{comment.date}}</strong>
													</p>
												</div>

											</div>
										</div>
									</li>
								</ul>
							</div>

							<div ng-show="commentVisible">
								<br>
								<h4>Leave a Comment:</h4>
								<form role="form" ng-submit="sendComment(x.id, comment.text)">
									<div class="form-group">
										<label>Body:</label>
										<textarea name="text" ng-model="comment.text"
											class="form-control" rows="2"></textarea>

									</div>
									<button type="submit" class="btn btn-success">Add</button>
								</form>
								<br>
							</div>

						</div>
					</div>
					<div class="col-xs-1"></div>

					<br>
				</div>

				<div uib-pagination total-items="postsTotal" ng-model="currentPage"
					ng-change="setCurrentPage(currentPage)"
					items-per-page="ItemsOnPageAmount"></div>
				<br>
				<p>
					<span class="badge">2</span> Comments:
				</p>
				<br>

			</div>
		</div>

	</div>
	</div>

	<footer class="container-fluid">
		<p>@Kriof & @LosKogutos 2018</p>
	</footer>

</body>
</html>
