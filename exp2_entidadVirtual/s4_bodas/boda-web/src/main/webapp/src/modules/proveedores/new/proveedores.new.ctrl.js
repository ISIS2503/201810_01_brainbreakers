(function (ng) {
    var mod = ng.module("proveedoresModule");
    mod.constant("proveedoresContext", "api/proveedores");
    mod.controller('proveedorNewCtrl', ['$scope', '$http', 'proveedoresContext', '$state',  '$rootScope',
        function ($scope, $http, proveedoresContext, $state,  $rootScope) {
            $rootScope.edit = false;
            $scope.createProveedor = function () {
                $http.post(proveedoresContext, {
                    name: $scope.proveedorName,
                    especialidad: $scope.proveedorEspecialidad
                }).then(function (response) {
                    //Proveedor created successfully
                    $state.go('proveedoresList', {proveedorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);