(function (ng) {
    var mod = ng.module("ubicacionesModule");
    mod.constant("ubicacionesContext", "api/ubicaciones");
    mod.controller('ubicacionNewCtrl', ['$scope', '$http', 'ubicacionesContext', '$state',  '$rootScope',
        function ($scope, $http, ubicacionesContext, $state,  $rootScope) {
            $rootScope.edit = false;
            $scope.createUbicacion = function () {
                $http.post(ubicacionesContext, {
                    direccion: $scope.ubicacionDireccion,
                    name: $scope.ubicacionName,
                    telefono: $scope.ubicacionTelefono,
                    longitud:$scope.ubicacionLongitud,
                    latitud:$scope.ubicacionLatitud
                    
                }).then(function (response) {
                    $state.go('ubicacionesList', {ubicacionId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);