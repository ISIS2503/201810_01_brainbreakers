(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
            
              $scope.verifico = false;  
              $scope.init = false;
              $scope.autenticar = function () {
                  $scope.init = true;
                 $http.get("http://localhost:8080/users/validarSeguridad?email="+data.username+"&contraseña="+data.password)

                    .then(function (response) {

                        $scope.verifico = response.data.validar;
           
                    });
                
                if($scope.verifico){
                  $state.go('mapa', {}, {reload: true});  
                }
                       
              };
        }
    ]);
}
)(window.angular);

