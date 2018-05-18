(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router'
        ,'loginModule',
        'mapaModule',
        'casaModule'
    ]);
     // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

