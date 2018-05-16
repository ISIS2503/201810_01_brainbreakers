(function (ng) {
    var mod = ng.module("regalosModule");
    mod.constant("regalosContext", "regalos");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('regalosNewCtrl', ['$scope', '$http', 'bodasContext', '$state',  '$rootScope','regalosContext',
        function ($scope, $http, bodasContext, $state,  $rootScope, regalosContext) {
            $rootScope.edit = false;
            $scope.createRegalo = function () {
                $http.post(bodasContext + '/' + $state.params.bodaId + '/' + regalosContext , {
                        name: $scope.regaloName,
                        imagen: $scope.regaloImagen,
                        comprado: $scope.reagaloComprado
                    
                }).then(function (response) {
                    $state.go('regalosList', {regaloId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);


