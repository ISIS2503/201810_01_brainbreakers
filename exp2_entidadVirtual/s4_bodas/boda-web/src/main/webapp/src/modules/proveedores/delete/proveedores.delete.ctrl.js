(function (ng) {
    var mod = ng.module("proveedoresModule");
    mod.constant("proveedoresContext", "api/proveedores");
    mod.controller('proveedorDeleteCtrl', ['$scope', '$http', 'proveedoresContext', '$state',
        function ($scope, $http, proveedoresContext, $state) {
            var idProveedor = $state.params.proveedorId;
            $scope.deleteProveedor = function () {
                $http.delete(proveedoresContext + '/' + idProveedor, {}).then(function (response) {
                    $state.go('proveedoresList', {proveedorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);