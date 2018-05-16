(function (ng) {
    var mod = ng.module("bodasModule");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('bodaNewCtrl', ['$scope', '$http', 'bodasContext', '$state',  '$rootScope',
        function ($scope, $http, bodasContext, $state,  $rootScope) {
            $rootScope.edit = false;
            $scope.createBoda = function () {
                if($rootScope.isAuthenticated() && $rootScope.currentUser.rol === 'admin'){
                    $http.post(bodasContext + "/" + $scope.bodaIdPareja, {
                    fecha: $scope.bodaFecha,
                    image: $scope.bodaImage,
                    name: $scope.bodaName,
                    religion: $scope.bodaReligion,
                    tema: $scope.bodaTema,
                    tipo: $scope.bodaTipo
                    
                }).then(function (response) {
                    //Boda created successfully
                    $state.go('bodasList', {bodaId: response.data.id}, {reload: true});
                });
                }else if($rootScope.isAuthenticated() && $rootScope.currentUser.rol === 'pareja'){
                    $http.post(bodasContext + "/" + $rootScope.currentUser.username, {
                    fecha: $scope.bodaFecha,
                    image: $scope.bodaImage,
                    name: $scope.bodaName,
                    religion: $scope.bodaReligion,
                    tema: $scope.bodaTema,
                    tipo: $scope.bodaTipo
                    
                }).then(function (response) {
                    //Boda created successfully
                    $state.go('parejasDetail', {parejaId: response.data.pareja.correoElec}, {reload: true});
                });
                }
                
            };
        }
    ]);
}
)(angular);