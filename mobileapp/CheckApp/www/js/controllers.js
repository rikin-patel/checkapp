angular.module('starter.controllers', ['newUserServices', 'loginServices','groupsServices'])

.controller('AppCtrl', function($scope, $ionicModal, $timeout) {

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal
  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/groups.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.modal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.modal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.modal.show();
  };

  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };
})

.controller('NewUserController', ['$scope', 'NewUser',
  function($scope, NewUser) {

    $scope.createUser = function(user) {
        alert(angular.toJson(user, true));
		var data = angular.toJson(user, true);
		NewUser.save({},data);
    };
  }
])

.controller('LoginController', ['$scope', 'Login',
  function($scope, Login) {
		
		$scope.loginUser = function(login) {
			alert(angular.toJson(login, true));
			var authToken = btoa(login.emailAddress + ':' + login.password);
			var userDetails = Login.passAuth(authToken,login.emailAddress).query({},authToken);

		};
  }
])

.controller('GroupsController',['$scope', '$sessionStorage', 'Groups',
  function($scope,$sessionStorage, Groups) {
		document.getElementById("username").innerHTML=$sessionStorage.UserName;
  }

])

.controller('MyCtrl',function MyCtrl($scope, $ionicHistory) {
  $scope.myGoBack = function() {
    $ionicHistory.goBack();
  };
})

