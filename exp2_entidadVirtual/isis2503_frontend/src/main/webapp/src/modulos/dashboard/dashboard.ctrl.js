(function (ng) {
    var mod = ng.module("dashboard");
    mod.controller('dashboardCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {

            $scope.user = {};
            $scope.data = {};

        }
    ]);
}
)(window.angular);

