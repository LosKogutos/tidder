(function() {
	'use strict';
	var data;
	var myApp = angular.module('Tidder', [ 'ui.bootstrap', 'ngAnimate','blockUI','ngAnimate']);

	myApp.controller(
					'PaginationMyController',
					function($scope, $http, $q, blockUI) {
						$http.defaults.headers.post["Content-Type"] = "application/json";
						var vm = this;
					
						var config = {
							params : data,
							headers : {
								'Accept' : 'application/json'
							}
						};
						$scope.init = function() {
							$scope.commentVisibleOnInit = null;
							$scope.commentVisible = false;
							$scope.commentAdded = false;
							$scope.commentByPostId = {};
							$scope.comment = {};
							$scope.comment.text = "";
							$scope.myData = "0,1";
							$scope.dataPerPage = [];
							$scope.ItemsOnPageAmount = 9;
							$scope.currentPage = 1;
							$scope.getPostsAmount();
							$scope.visibleComment = false;
							$scope.getPostsPerPage(2);
							$scope.post = {};
							$scope.post.hideWindow = true;
							window.onscroll = function() {scrollFunction()};
							
						};
						$scope.$watch("currentPage", function() {
							$scope.setCurrentPage($scope.currentPage);
						  });
						
						$scope.changePostsPerPageAmount = function(total) {
							$scope.ItemsOnPageAmount = total
							$scope.setCurrentPage($scope.currentPage)
						}
						
						$scope.addPost = function(_topic, _text) {
							blockUI.start();
							$http({
								method : "post",
								url : "http://localhost:8080/tidder/webapi/post/new",
								data : {topic : _topic, text : _text}
							}).then(
									function(response) {
										blockUI.stop();
										$scope.post.postAdded = true;
										$scope.post.hideWindow = true;
									}, function(response) {
										alert('failure' + response);
							});
						}
						
						$scope.checkIfPostAdded = function() {
							if( $scope.post.postAdded) {
								$scope.post.hideWindow = true;
								return true;
							} else {
								$scope.post.hideWindow = false;
								false;
							}
						}

						

						$scope.likePost = function(postId) {
							blockUI.start();
							$http({
								method : "post",
								url : "http://localhost:8080/tidder/webapi/post/like/post?id=" + postId ,
								data : data
							}).then(
									function(response) {				
										if (response.data.liked) {
											$scope.changeLikesByValue($scope.dataPerPage, postId, true);
											
										} else {$scope.changeLikesByValue($scope.dataPerPage, postId, false);
										}
										blockUI.stop();
									}, function(response) {
										alert(response);
							});
						};
						
						$scope.likeComment = function(commentId,postId) {
							blockUI.start();
							$http({
								method : "post",
								url : "http://localhost:8080/tidder/webapi/post/like/comment?id="+ commentId ,
								data : data
							}).then(
									function(response) {				
										if (response.data.liked) {
											$scope.changeLikesByValue($scope.commentByPostId[postId].comments, commentId, true);
										} else {
											$scope.changeLikesByValue($scope.commentByPostId[postId].comments, commentId, false);
										}
										blockUI.stop();
										
									}, function(response) {
										console.log(response);
										alert(response);
							});
						};
						
						$scope.changeLikesByValue = function(item, key, up) {
							for (var i in item) {
								var keys = Object.keys(item[i]);
								
								if(item[i].id == key) {
									if(up) {
										item[i].likes ++;	
									} else {
										item[i].likes --;
									}
									
								}
							}
						}
						
			

						$scope.getPostsAmount = function() {
							$http({		method : "GET",
										url : "http://localhost:8080/tidder/webapi/post/all/amount",
										data : data
									}).then(function(response) {
								$scope.postsTotal = response.data.ammount;
							}, function(response) {
									alert('failure ' + response);
							});
						};

						$scope.sendComment = function(postId, text) {
							var data = JSON.stringify({
								"text" : text
							})
							$http({	method : "post",
									url : "http://localhost:8080/tidder/webapi/post/" + postId + "/comment",
									data : data
									}).then(
											function(response) {
												console
														.log($scope.comment.text);
												$scope.commentByPostId[postId].comments
														.push(response.data.comments[response.data.comments.length - 1])
												$scope.comment.text = "";
												console
														.log($scope.comment.text);
											}, function(response) {

											});
						};
						
						$scope.getCommentById = function(postId) {
							var resultset = $http.get(
											'http://localhost:8080/tidder/webapi/post/'
													+ postId).then(
											function(result) {
												$scope.commentByPostId[postId] = result.data;
												return result.data;
											}, function(res) {
												alert('failure' + res);
											});
							return resultset;
						};
						
				

						$scope.getPostsPerPage = function(page) {					
							$http.get('http://localhost:8080/tidder/webapi/post/page/'+ page+ "?size=" + $scope.ItemsOnPageAmount, config)
									.then( function(data) {
												if( data != null || data !== "undefined") {
													
													$scope.dataPerPage = [];
												}
												$scope.dataPerPageTemp = data.data;
												angular.forEach($scope.dataPerPageTemp,
														function(obj) {
															if(obj.id.length !==0) {
																$scope.dataPerPage.push(obj);
															}
														}
												);
												angular.forEach($scope.dataPerPage, function(obj) {
													obj.commentData = $scope.getCommentById(obj.id)
												});
												 blockUI.stop();
											}, function(res) {
												alert('failure' + res);
											});
						}
						
						$scope.getAllPosts = function() {
							$http.get('http://localhost:8080/tidder/webapi/post/all',
											config).then(function(data) {
												$scope.mydata = data.data;
												angular.forEach(
																$scope.mydata,
																function(obj) {
																	obj.commentsData = $scope
																			.getCommentById(obj.id);
																});
											}, function(res) {
												alert('failure' + res);
											});
						}
						
						$scope.setCurrentPage = function(page) {
							  blockUI.start();  
							$scope.getPostsPerPage(page);
						}
						
						function scrollFunction () {
							if ( document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
								document.getElementById("navbar").style.top = "0"
							} else {
								document.getElementById("navbar").style.top = "-120px";
							}
						}
						
						$("span.addPostByUser").click(function() {
						    $('html, body').animate({
						        scrollTop:0
						    }, 2000);
						});
						
					});
	
			
	
	
}());