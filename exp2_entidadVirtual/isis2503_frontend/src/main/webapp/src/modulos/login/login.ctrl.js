(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {
              $scope.autenticar = function () {
                 $state.go('mapa', {}, {reload: true});      
              }
        }
    ]);
}
)(window.angular);

