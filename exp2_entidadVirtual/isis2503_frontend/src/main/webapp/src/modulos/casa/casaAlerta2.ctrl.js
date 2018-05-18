/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("casaModule");
    mod.constant("brainBrakersContext", "api/casa");
    mod.controller('casaAlerta2Ctrl', ['$scope', '$http', 'mapaContext', '$state', '$filter',
        function ($scope, $http, mapaContext, $state, $filter) {
            $scope.alertas = [];
            $http.get("http://localhost:8080/residencia/alarmaByResidenciaAndTipo?nombreR=Apartamento-01_Torre-01_Lantana&tipo=alerta2")

                    .then(function (response) {

                        $scope.alertas = response.data;

                    });
        }

    ]);
})(angular);


