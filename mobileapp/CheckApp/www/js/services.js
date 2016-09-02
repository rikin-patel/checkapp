var newUserServices = angular.module('newUserServices', ['ngResource']);

newUserServices.factory('NewUser', ['$resource',
  function($resource) {
    return $resource('/checkapp/webapi/user', {}, {
		save: {method:'POST', headers: {'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS',
										'Access-Control-Allow-Credentials': 'false',
										'Access-Control-Max-Age': '86400',
										'Access-Control-Allow-Headers':	 'X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept',
										'Content-Type': 'application/json'}}
	});
  }
])


var loginServices = angular.module('loginServices', ['ngResource']);


loginServices.factory('Login', ['$resource',
  function($resource) {
  return{
	passAuth: function(authToken,emailAddress) {
    return $resource('/checkapp/webapi/user/'+emailAddress, {}, {
		query: {method:'GET', isArray:true,
				headers: {'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS',
						'Access-Control-Allow-Credentials': 'false',
						'Access-Control-Max-Age': '86400',
						'Access-Control-Allow-Headers':	 'X-Requested-With, X-HTTP-Method-Override, Content-Type, Accept',
						'Content-Type': 'application/json',
						'Authorization': 'Basic ' + authToken}}
	});
	}
  }
  }
])