function get_url_param( name )
{
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");

	var regexS = "[\\?&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec( window.location.href );

	if ( results == null )
		return "";
	else
		return results[1];
}

function ContentCtrl ( $scope, $log, $http )
{
    $log.log ("ContentCtrl");


        $http({
        	mehtod: 'GET',
        	url: 'rest/search?&id=' + get_url_param("id")
        }).
        success(function(data, status, headers, config) {
            $scope.person = data;
        }).
        error(function(data, status, headers, config) {
            // log error
        });

        $scope.getIteratingClass = function ( index )
        {
            var n = arguments.length - 1
            return arguments [ (index%n) + 1 ];
        }
}

var ctrl = angular.module('app.ctrl', [])
        .controller ( 'ContentCtrl', ContentCtrl )
    ;

var strgctrl = angular.module('strgctrl', [])
		.controller('strgController', function($scope, $log, $http){
			$http({
	        	mehtod: 'GET',
	        	url: 'rest/getStorage?&id=' + get_url_param("id")
	        }).
	        success(function(data, status, headers, config) {
	        	console.log(data);
	            $scope.store = data;
	        }).
	        error(function(data, status, headers, config) {
	            // log error
	        });

		});
