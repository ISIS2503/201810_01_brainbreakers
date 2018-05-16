(function (ng) {
    var mod = ng.module("serviciosModule");
    mod.constant("serviciosContext", "api/servicios");
    mod.controller('serviciosCtrl', ['$scope', '$http', 'serviciosContext', '$state',
        function ($scope, $http,serviciosContext, $state) {
            $http.get(serviciosContext).then(function (response) {
                $scope.serviciosRecords = response.data;
                
            });

            if ($state.params.servicioId !== undefined) {
                $http.get(serviciosContext + '/' + $state.params.servicioId).then(function (response) {
                    //$scope.regalosRecords = response.data.regalos;
                    $scope.currentServicio = response.data;
                    
                });   
            }
            
        }]);
})(angular);