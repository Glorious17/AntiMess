/**
 * 
 */

/// <reference path="Script/angular.js" />

var myApp = angular
				.module("nameModule", [])
				.controller("nameController", function($scope, $http){
					$http.get('searchService.java/getNames()')
						.success(function(data){
							system.out.println(response.data);
							$scope.input = response.data;
						})
				});
				