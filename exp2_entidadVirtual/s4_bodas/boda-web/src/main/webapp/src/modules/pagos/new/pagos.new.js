(function (ng) {
    var mod = ng.module("tarjetasCreditoModule");
    mod.constant("pagosContext", "pagos");
     mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");
    
    mod.controller('pagosNewCtrl', ['$scope', '$http','parejasContext','tarjetasCreditoContext', 'pagosContext','$state',  '$rootScope',
        function ($scope, $http, parejasContext,tarjetasCreditoContext, pagosContext, $state,  $rootScope) {
            $rootScope.edit = false;
            $scope.createPago = function () {
            if($scope.pagoCorreo === null || $scope.pagoCorreo === undefined){
                console.log();
                $http.post(parejasContext + '/' + $rootScope.currentUser.username + '/' + tarjetasCreditoContext + '/' + $scope.pagoTarjeta + '/' + pagosContext , {
                    correoPareja: $rootScope.currentUser.username,
                    montoTotal: $rootScope.currentOpcionPago.costo,
                    fecha: new Date(),
                    nombrePago: $rootScope.currentOpcionPago.descripcion,
                    tarjetaId: $scope.pagoTarjeta
                }).then(function (response) {
                    $state.go('pagosConfirm', {pagoId: response.data.id}, {reload: true});
                });
                 }
            else {
                $http.post(parejasContext + '/' + $scope.pagoCorreo + '/' + tarjetasCreditoContext + '/' + $scope.pagoTarjeta + '/' + pagosContext , {
                    correoPareja: $scope.pagoCorreo,
                    montoTotal: $rootScope.currentOpcionPago.costo,
                    fecha: new Date(),
                    nombrePago: $rootScope.currentOpcionPago.descripcion,
                    tarjetaId: $scope.pagoTarjeta
                }).then(function (response) {
                    $state.go('pagosConfirm', {pagoId: response.data.id}, {reload: true});
                });
                }
            };
        }
    ]);
}
)(angular);