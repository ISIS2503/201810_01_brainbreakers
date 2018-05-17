(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('logoutCtrl', ['$rootScope', '$state', function ($rootScope, $state) {    
        $state.go('mapa', {}, {reload: true});
        }
    ]);
}
)(window.angular);

