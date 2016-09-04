var newUserServices = angular.module('newUserServices', ['ngResource']);

newUserServices.factory('NewUser', ['$resource',
  function($resource) {
    return $resource('/checkapp/webapi/user', {}, {
		save: {method:'POST', headers: {'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS',
										'Access-Control-Allow-Credentials': 'false',
										'Access-Control-Max-Age': '86400',
										'Access-Control-Allow-Headers':	 'X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept',
										'Content-Type': 'application/json'},
				transformResponse: function(data, headers, status){
					//response={};
					//response.data = data;
					//response.headers = headers;
					//return response;
					if (status == 200 ){
						//$session.data=data;
						//$session.auth=authToken;
						$window.location.href="#/login";
					}else{
						alert ("User Registration Failure");
					}
					return data;
					}}
	});
  }
])


var loginServices = angular.module('loginServices', ['ngResource','ngStorage']);

loginServices.factory('Login', ['$resource','$window','$sessionStorage',
  function($resource,$window,$sessionStorage) {
  return{
	passAuth: function(authToken,emailAddress) {
    return $resource('/checkapp/webapi/user/'+emailAddress, {}, {
		query: {method:'GET', isArray:true,
				headers: {'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS',
						'Access-Control-Allow-Credentials': 'false',
						'Access-Control-Max-Age': '86400',
						'Access-Control-Allow-Headers':	 'X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept',
						'Content-Type': 'application/json',
						'Accept': 'application/json',
						'Authorization': 'Basic ' + authToken},
				transformResponse: function(data, headers, status){
					if (status == 200 ){
						//$session.data=data;
						//$session.auth=authToken;
						var loggedInUser = JSON.parse(data);
						$sessionStorage.Authorization= 'Basic ' + authToken;
						$sessionStorage.UserId=loggedInUser[0].userId;
						$sessionStorage.EmailAddress=loggedInUser[0].emailAddress;
						$sessionStorage.UserName=loggedInUser[0].userName;
						$window.location.href="#/app";
					}else{
						alert ("Login Failure");
					}
					return JSON.parse(data);}
				}
	});
	}
  }
  }
])

var firstPageServices = angular.module('firstPageServices', ['ngResource','ngStorage']);

firstPageServices.factory('First', ['$resource','$window','$sessionStorage',
  function($resource,$window,$sessionStorage) {
    return $resource('/checkapp/webapi/user/', {}, {
		query: {method:'GET', isArray:true,
				headers: {'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS',
						'Access-Control-Allow-Credentials': 'false',
						'Access-Control-Max-Age': '86400',
						'Access-Control-Allow-Headers':	 'X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept',
						'Content-Type': 'application/json',
						'Accept': 'application/json',
						'Authorization': 'Basic ' },
				transformResponse: function(data, headers, status){
					if (status == 200 ){
						//$session.data=data;
						//$session.auth=authToken;
						var loggedInUser = JSON.parse(data);
						$window.location.href="#/groups";
					}else{
						alert ("Login Failure");
					}
					return JSON.parse(data);}
				}
	});
  }
])