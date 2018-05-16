(function (ng) {
    var mod = ng.module("regalosModule");
    mod.constant("regalosContext", "regalos");
    mod.constant("bodasContext", "api/bodas");
    mod.controller('regalosDeleteCtrl', ['$scope', '$http', 'bodasContext', '$state','regalosContext',
        function ($scope, $http, bodasContext, $state,regalosContext) {
            var idBoda = $state.params.bodaId;  
            var idReg= $state.params.regaloId;
            $scope.deleteRegalo = function () {
                $http.delete(bodasContext + "/" + idBoda + "/" + regalosContext + "/" + idReg ,{}).then(function (response) {
                    $state.go('regalosList',{regaloId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);