(function (ng) {
    var mod = ng.module("opcionesModule");
    mod.constant("opcionesServicioContext", "opcionServicios");
    mod.constant("proveedoresContext", "api/proveedores");
    mod.controller('opcionesCtrl', ['$scope', '$http', 'proveedoresContext', '$state', 'opcionesContext', '$rootScope',

        function ($scope, $http, proveedoresContext, $state, opcionesContext, $rootScope) {

            $http.get(proveedoresContext + '/' + $state.params.proveedorId + '/' + opcionesContext).then(function (response) {
                $scope.opcionesRecords = response.data;
            });
            if ($state.params.opcionId !== undefined) {
                $http.get(proveedoresContext + '/' + $state.params.proveedorId + '/' + opcionesContext + '/' + $state.params.opcionId).then(function (response) {

                    $scope.currentOpcion = response.data;
                    $rootScope.currentOpcionPago = response.data;
                });
            }
            $scope.orderOpciones = function (condicionNombre, tipoCondicion) {
                $scope.tipoOrdenOpcion = condicionNombre;
                $scope.currentOrdenOp = tipoCondicion;
            };
        }
    ]);

})(window.angular);