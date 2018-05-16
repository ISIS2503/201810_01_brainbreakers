(function (ng) {
    var mod = ng.module("parejasModule");
    mod.constant("parejasContext", "api/parejas");
    mod.controller('parejasNewCtrl', ['$scope', '$http', 'parejasContext', '$state', '$rootScope',
        function ($scope, $http, parejasContext, $state , $rootScope) {
            $rootScope.edit = false;
            $scope.data = {};
            $scope.data.pago=0;
            $scope.createPareja = function () {
                $http.post(parejasContext, $scope.data ).then(function (response) {
                    //Author created successfully
                    swal("Creada!", "Tu Pareja fue creada.", "success");
                    $state.go('parejasList', {parejaId: response.data.correoElec}, {reload: true});
                });
            };

        }
    ]);
}
)(angular);