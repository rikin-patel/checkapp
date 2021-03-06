// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
  .state('login', {
    url: '/login',
    templateUrl: 'templates/login.html'
	})
  
  .state('app', {
    url: '/app',
    templateUrl: 'templates/first.html',
	controller: 'FirstPageController'
  })
  
	.state('newuser', {
    url: '/newuser',
    templateUrl: 'templates/newuser.html',
	controller: 'NewUserController'
	})
  
   .state('groups', {
      url: '/groups',
      templateUrl: 'templates/groups.html'//,
	  //controller: 'GroupsController'
    })
	
	.state('search', {
      url: '/search',
      templateUrl: 'templates/search.html'
    })
	
	.state('recent', {
      url: '/recent',
      templateUrl: 'templates/recent_groups.html'
    })
	
	.state('groups.newgroup', {
      url: '/newgroup',
	  views: {
		'menuContent': {
		templateUrl: 'templates/newgroup.html'
		}
	  },
	  controller: 'NewGroupController'
    })
	
	.state('menu', {
		url: '/menu',
		abstract: true,
		templateUrl: 'templates/menu.html'
	})
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/login');
});
