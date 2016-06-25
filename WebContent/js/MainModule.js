var app = angular.module( 'app', ['app.ctrl', 'strgctrl'])


        .run ( function ( $rootScope, $log ) {

            $log.log ("run app")

        })

    ;
