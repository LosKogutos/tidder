(function() {
    'use strict';  
    var data;
    var myApp = angular.module('Tidder',[]);

    myApp.controller('MainPageController', ['$scope','$http', function($scope,$http) {
      $scope.double = function(value) { return value * 2; };
      
        function GetAllPosts () {
          $http({method: 'GET', url: 'http://localhost:8080/tidder/GetAllPosts.json'})
          .then( function(response) {
        	  
        	  $scope.posts = response.data;
          
          }, function errorCallback(response) {
        	  
        	  	alert('ERROR !! ' + response.status);
        		})
          
        }
        
          GetAllPosts();
      
       

    
    
    }]);
    
    
}());