(function (ng) {
    var mod = ng.module("tarjetasCreditoModule");
    mod.constant("tarjetasCreditoContext", "tarjetasCredito");
    mod.constant("parejasContext", "api/parejas");

    mod.controller('tarjetasNewCtrl', ['$scope', '$http', 'parejasContext', 'tarjetasCreditoContext', '$state', '$rootScope',
        function ($scope, $http, parejasContext, tarjetasCreditoContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createTarjeta = function () {
               
            if ($rootScope.currentUser.rol === 'pareja') {
                
                    $http.post(parejasContext + '/' + $rootScope.currentUser.username + '/' + tarjetasCreditoContext, {
                        nombreBanco: $scope.tarjetaBanco,
                        fechaVen: $scope.tarjetaFechaVen,
                        numero: $scope.tarjetaNumero,
                        numDeSeg: $scope.tarjetaNumDeSeg,
                    }).then(function () {
                         $state.go('tarjetasList',{parejaId:$rootScope.currentUser.username},{reload: true});
                    });
                }
            };
        }
    ]);
}
)(angular);

