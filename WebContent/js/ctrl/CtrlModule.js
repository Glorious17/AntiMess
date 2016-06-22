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


/**
    $scope.persons = [
        {
            "id": 1,
            "firstName": "Mackenzie",
            "lastName": "Hodges",
            "gender": "female",
            "age": 30,
            "registered": 1288323623006,
            "email": "mackenzie@multitiqua.com",
            "image": "././img/fimg01.jpg",
            "keywords": "schwarzweiß, lippen"
        },
        {
            "id": 2,
            "firstName": "Aaliyah",
            "lastName": "Hailey",
            "gender": "male",
            "age": 21,
            "registered": 1288323623006,
            "email": "aaliyah@polytheon.com",
            "image": "././img/mimg01.jpg",
            "keywords": "schwarzweiß"
        },
        {
            "id": 3,
            "firstName": "Lillian",
            "lastName": "Goodman",
            "gender": "female",
            "age": 27,
            "registered": 1288323623006,
            "email": "lillian@ienland.com",
            "image": "././img/fimg02.jpg",
            "keywords": "blaue Augen, braunes Haar, bunter Schall"
        },
        {
            "id": 4,
            "firstName": "Gabrielle",
            "lastName": "Warren",
            "gender": "male",
            "age": 25,
            "registered": 1288323623006,
            "email": "gabrielle@infraique.com",
            "image": "././img/mimg02.jpg"
        },
        {
            "id": 5,
            "firstName": "Layla",
            "lastName": "Ogden",
            "gender": "male",
            "age": 27,
            "registered": 1288323623006,
            "email": "layla@rapigrafix.com",
            "image": "././img/mimg03.jpg",
            "keyword": "bart, schwarzweis"
        },
        {
            "id": 6,
            "firstName": "Maya",
            "lastName": "Turner",
            "gender": "male",
            "age": 28,
            "registered": 1288323623006,
            "email": "maya@polytheon.com",
            "image": "././img/mimg04.jpg"
        },
        {
            "id": 7,
            "firstName": "Brooklyn",
            "lastName": "Oldridge",
            "gender": "male",
            "age": 21,
            "registered": 1288323623006,
            "email": "brooklyn@superscope.com",
            "image": "././img/mimg05.jpg"
        },
        {
            "id": 8,
            "firstName": "Brooklyn",
            "lastName": "Fulton",
            "gender": "female",
            "age": 22,
            "registered": 1288323623006,
            "email": "brooklyn@infoairway.com",
            "image": "././img/fimg03.jpg"
        },
        {
            "id": 9,
            "firstName": "Isabella",
            "lastName": "Gate",
            "gender": "female",
            "age": 22,
            "registered": 1288323623006,
            "email": "isabella@esteganoergy.com",
            "image": "././img/fimg04.jpg"
        },
        {
            "id": 10,
            "firstName": "Peyton",
            "lastName": "Oliver",
            "gender": "female",
            "age": 33,
            "registered": 1288323623006,
            "email": "peyton@dynarama.com",
            "image": "././img/fimg05.jpg"
        },
        {
            "id": 11,
            "firstName": "Jocelyn",
            "lastName": "Oldman",
            "gender": "male",
            "age": 24,
            "registered": 1288323623006,
            "email": "jocelyn@netseco.com",
            "image": "././img/mimg06.jpg"
        },
        {
            "id": 12,
            "firstName": "Gianna",
            "lastName": "Conors",
            "gender": "male",
            "age": 33,
            "registered": 1288323623006,
            "email": "gianna@unologic.com",
            "image": "././img/mimg07.jpg"
        },
        {
            "id": 13,
            "firstName": "Destiny",
            "lastName": "Conors",
            "gender": "male",
            "age": 23,
            "registered": 1288323623006,
            "email": "destiny@systheon.com",
            "image": "././img/mimg08.jpg"
        },
        {
            "id": 14,
            "firstName": "Sophie",
            "lastName": "Ford",
            "gender": "female",
            "age": 25,
            "registered": 1288323623006,
            "email": "sophie@us omnigraphik.com",
            "image": "././img/fimg06.jpg"
        },
        {
            "id": 15,
            "firstName": "Serenity",
            "lastName": "Molligan",
            "gender": "male",
            "age": 35,
            "registered": 1288323623006,
            "email": "serenity@fibrotouch.com",
            "image": "././img/mimg08.jpg"
        },
        {
            "id": 16,
            "firstName": "Makayla",
            "lastName": "Ford",
            "gender": "male",
            "age": 40,
            "registered": 1288323623006,
            "email": "makayla@polytheon.com",
            "image": "././img/mimg09.jpg"
        },
        {
            "id": 17,
            "firstName": "Melanie",
            "lastName": "Sherlock",
            "gender": "female",
            "age": 37,
            "registered": 1288323623006,
            "email": "melanie@robocomm.com",
            "image": "././img/fimg07.jpg"
        },
        {
            "id": 18,
            "firstName": "Kylie",
            "lastName": "Gilson",
            "gender": "female",
            "age": 38,
            "registered": 1288323623006,
            "email": "kylie@entcast.com",
            "image": "././img/fimg08.jpg"
        }
        ];
**/

        $scope.getIteratingClass = function ( index )
        {
            var n = arguments.length - 1
            return arguments [ (index%n) + 1 ];
        }



}

var ctrl = angular.module('app.ctrl', [])
        .controller ( 'ContentCtrl', ContentCtrl )
    ;

var strgctrl = angular.module('app.strgctrl', [])
		.controller('strgController', function($scope, $log, $http){
			$http({
	        	mehtod: 'GET',
	        	url: 'rest/getStorage?&id=' + get_url_param("id")
	        }).
	        success(function(data, status, headers, config) {
	            $scope.storage = data;
	        }).
	        error(function(data, status, headers, config) {
	            // log error
	        });

		});
