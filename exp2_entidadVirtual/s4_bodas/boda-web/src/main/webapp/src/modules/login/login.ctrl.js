(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope',
        function ($scope, $http, $state, $rootScope) {

            $scope.user = {};
            $scope.data = {};

            $http.get('data/users.json').then(function (response) {
                $scope.users = response.data;
            });

            $scope.autenticar = function () {
                if ($scope.data.rol === 'admin') {



                    var flag = false;
                    for (var item in $scope.users) {
                        if ($scope.users[item].user === $scope.data.username && $scope.users[item].password === $scope.data.password && $scope.users[item].rol === $scope.data.rol) {
                            flag = true;
                            $scope.user = $scope.users[item];
                            $state.go('parejasList', {}, {reload: true});
                            break;
                        }

                    }
                    if (!flag) {
                        $rootScope.alerts.push({type: "danger", msg: "Incorrect username or password."});
                    } else {
                        sessionStorage.token = $scope.user.token;
                        sessionStorage.setItem("username", $scope.user.user);
                        sessionStorage.setItem("name", $scope.user.name);
                        sessionStorage.setItem("rol", $scope.user.rol);
                        $rootScope.currentUser = $scope.user.name;
                    }
                } else if ($scope.data.rol === 'pareja') {

                    $http.get('api/parejas/' + $scope.data.username)
                            .then(function (response) {
                                $scope.usurPareja = response.data;
                                var flag = false;
                                if ($scope.usurPareja.correoElec === $scope.data.username && $scope.usurPareja.contrasenia === $scope.data.password) {
                                    
                                    flag = true;
                                    $state.go('#', {}, {reload: true});
                                }
                                if (!flag) {
                                        $rootScope.alerts.push({type: "danger", msg: "Incorrect username or password."});
                                    }
                                if (flag) {
                                    sessionStorage.token = $scope.user.token;
                                    sessionStorage.setItem("username", $scope.usurPareja.correoElec);
                                    sessionStorage.setItem("name", $scope.usurPareja.nombreAbreviado);
                                    sessionStorage.setItem("rol", "pareja");
                                    $rootScope.currentUser = $scope.usurPareja.correoElec;
                                }

                            });
                }
            };
        }
    ]);
}
)(window.angular);

