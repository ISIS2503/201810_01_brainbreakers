(function(t,e){"object"==typeof exports&&"object"==typeof module?module.exports=e():"function"==typeof define&&define.amd?define([],e):"object"==typeof exports?exports.fp=e():t.fp=e()})(this,function(){return function(t){function e(n){if(r[n])return r[n].exports;var i=r[n]={exports:{},id:n,loaded:!1};return t[n].call(i.exports,i,i.exports,e),i.loaded=!0,i.exports}var r={};return e.m=t,e.c=r,e.p="",e(0)}([function(t,e,r){function n(t,e){return i(t,t,e)}var i=r(1);"function"==typeof _&&"function"==typeof _.runInContext&&(_=n(_.runInContext())),
t.exports=n},function(t,e,r){function n(t,e){return 2==e?function(e,r){return t.apply(void 0,arguments)}:function(e){return t.apply(void 0,arguments)}}function i(t,e){return 2==e?function(e,r){return t(e,r)}:function(e){return t(e)}}function a(t){for(var e=t?t.length:0,r=Array(e);e--;)r[e]=t[e];return r}function o(t){return function(e){return t({},e)}}function s(t,e){return function(){for(var r=arguments.length,n=r-1,i=Array(r);r--;)i[r]=arguments[r];var a=i[e],o=i.slice(0,e);return a&&d.apply(o,a),
e!=n&&d.apply(o,i.slice(e+1)),t.apply(this,o)}}function l(t,e){return function(){var r=arguments.length;if(r){for(var n=Array(r);r--;)n[r]=arguments[r];var i=n[0]=e.apply(void 0,n);return t.apply(void 0,n),i}}}function u(t,e,r,d){function c(t,e){if(B.cap){var r=p.iterateeRearg[t];if(r)return x(e,r);var n=!b&&p.iterateeAry[t];if(n)return W(e,n)}return e}function h(t,e,r){return E||B.curry&&r>1?z(e,r):e}function g(t,e,r){if(B.fixed&&(F||!p.skipFixed[t])){var n=p.methodSpread[t],i=n&&n.start;return void 0===i?w(e,r):s(e,i);
}return e}function y(t,e,r){return B.rearg&&r>1&&(j||!p.skipRearg[t])?_(e,p.methodRearg[t]||p.aryRearg[r]):e}function m(t,e){e=V(e);for(var r=-1,n=e.length,i=n-1,a=D(Object(t)),o=a;null!=o&&++r<n;){var s=e[r],l=o[s];null!=l&&(o[e[r]]=D(r==i?l:Object(l))),o=o[s]}return a}function v(t){return H.runInContext.convert(t)(void 0)}function A(t,e){var r=p.aliasToReal[t]||t,n=p.remap[r]||r,i=d;return function(t){return u(b?L:S,r,b?L[n]:e,M(M({},i),t))}}function W(t,e){return I(t,function(t){return"function"==typeof t?i(t,e):t;
})}function x(t,e){return I(t,function(t){var r=e.length;return n(_(i(t,r),e),r)})}function I(t,e){return function(){var r=arguments.length;if(!r)return t();for(var n=Array(r);r--;)n[r]=arguments[r];var i=B.rearg?0:r-1;return n[i]=e(n[i]),t.apply(void 0,n)}}function R(t,e){var r,n=p.aliasToReal[t]||t,i=e,s=G[n];return s?i=s(e):B.immutable&&(p.mutate.array[n]?i=l(e,a):p.mutate.object[n]?i=l(e,o(e)):p.mutate.set[n]&&(i=l(e,m))),q(U,function(t){return q(p.aryMethod[t],function(e){if(n==e){var a=p.methodSpread[n];
return r=a&&a.afterRearg?g(n,y(n,i,t),t):y(n,g(n,i,t),t),r=c(n,r),r=h(n,r,t),!1}}),!r}),r||(r=i),r==e&&(r=E?z(r,1):function(){return e.apply(this,arguments)}),r.convert=A(n,e),p.placeholder[n]&&(O=!0,r.placeholder=e.placeholder=C),r}var O,b="function"==typeof e,k=e===Object(e);if(k&&(d=r,r=e,e=void 0),null==r)throw new TypeError;d||(d={});var B={cap:!("cap"in d)||d.cap,curry:!("curry"in d)||d.curry,fixed:!("fixed"in d)||d.fixed,immutable:!("immutable"in d)||d.immutable,rearg:!("rearg"in d)||d.rearg
},E="curry"in d&&d.curry,F="fixed"in d&&d.fixed,j="rearg"in d&&d.rearg,C=b?r:f,L=b?r.runInContext():void 0,S=b?r:{ary:t.ary,assign:t.assign,clone:t.clone,curry:t.curry,forEach:t.forEach,isArray:t.isArray,isFunction:t.isFunction,iteratee:t.iteratee,keys:t.keys,rearg:t.rearg,toInteger:t.toInteger,toPath:t.toPath},w=S.ary,M=S.assign,D=S.clone,z=S.curry,q=S.forEach,P=S.isArray,T=S.isFunction,K=S.keys,_=S.rearg,N=S.toInteger,V=S.toPath,U=K(p.aryMethod),G={castArray:function(t){return function(){var e=arguments[0];
return P(e)?t(a(e)):t.apply(void 0,arguments)}},iteratee:function(t){return function(){var e=arguments[0],r=arguments[1],n=t(e,r),a=n.length;return B.cap&&"number"==typeof r?(r=r>2?r-2:1,a&&a<=r?n:i(n,r)):n}},mixin:function(t){return function(e){var r=this;if(!T(r))return t(r,Object(e));var n=[];return q(K(e),function(t){T(e[t])&&n.push([t,r.prototype[t]])}),t(r,Object(e)),q(n,function(t){var e=t[1];T(e)?r.prototype[t[0]]=e:delete r.prototype[t[0]]}),r}},nthArg:function(t){return function(e){var r=e<0?1:N(e)+1;
return z(t(e),r)}},rearg:function(t){return function(e,r){var n=r?r.length:0;return z(t(e,r),n)}},runInContext:function(e){return function(r){return u(t,e(r),d)}}};if(!k)return R(e,r);var H=r,J=[];return q(U,function(t){q(p.aryMethod[t],function(t){var e=H[p.remap[t]||t];e&&J.push([t,R(t,e)])})}),q(K(H),function(t){var e=H[t];if("function"==typeof e){for(var r=J.length;r--;)if(J[r][0]==t)return;e.convert=A(t,e),J.push([t,e])}}),q(J,function(t){H[t[0]]=t[1]}),H.convert=v,O&&(H.placeholder=C),q(K(H),function(t){
q(p.realToAlias[t]||[],function(e){H[e]=H[t]})}),H}var p=r(2),f=r(3),d=Array.prototype.push;t.exports=u},function(t,e){e.aliasToReal={each:"forEach",eachRight:"forEachRight",entries:"toPairs",entriesIn:"toPairsIn",extend:"assignIn",extendAll:"assignInAll",extendAllWith:"assignInAllWith",extendWith:"assignInWith",first:"head",conforms:"conformsTo",matches:"isMatch",property:"get",__:"placeholder",F:"stubFalse",T:"stubTrue",all:"every",allPass:"overEvery",always:"constant",any:"some",anyPass:"overSome",
apply:"spread",assoc:"set",assocPath:"set",complement:"negate",compose:"flowRight",contains:"includes",dissoc:"unset",dissocPath:"unset",dropLast:"dropRight",dropLastWhile:"dropRightWhile",equals:"isEqual",identical:"eq",indexBy:"keyBy",init:"initial",invertObj:"invert",juxt:"over",omitAll:"omit",nAry:"ary",path:"get",pathEq:"matchesProperty",pathOr:"getOr",paths:"at",pickAll:"pick",pipe:"flow",pluck:"map",prop:"get",propEq:"matchesProperty",propOr:"getOr",props:"at",symmetricDifference:"xor",symmetricDifferenceBy:"xorBy",
symmetricDifferenceWith:"xorWith",takeLast:"takeRight",takeLastWhile:"takeRightWhile",unapply:"rest",unnest:"flatten",useWith:"overArgs",where:"conformsTo",whereEq:"isMatch",zipObj:"zipObject"},e.aryMethod={1:["assignAll","assignInAll","attempt","castArray","ceil","create","curry","curryRight","defaultsAll","defaultsDeepAll","floor","flow","flowRight","fromPairs","invert","iteratee","memoize","method","mergeAll","methodOf","mixin","nthArg","over","overEvery","overSome","rest","reverse","round","runInContext","spread","template","trim","trimEnd","trimStart","uniqueId","words","zipAll"],
2:["add","after","ary","assign","assignAllWith","assignIn","assignInAllWith","at","before","bind","bindAll","bindKey","chunk","cloneDeepWith","cloneWith","concat","conformsTo","countBy","curryN","curryRightN","debounce","defaults","defaultsDeep","defaultTo","delay","difference","divide","drop","dropRight","dropRightWhile","dropWhile","endsWith","eq","every","filter","find","findIndex","findKey","findLast","findLastIndex","findLastKey","flatMap","flatMapDeep","flattenDepth","forEach","forEachRight","forIn","forInRight","forOwn","forOwnRight","get","groupBy","gt","gte","has","hasIn","includes","indexOf","intersection","invertBy","invoke","invokeMap","isEqual","isMatch","join","keyBy","lastIndexOf","lt","lte","map","mapKeys","mapValues","matchesProperty","maxBy","meanBy","merge","mergeAllWith","minBy","multiply","nth","omit","omitBy","overArgs","pad","padEnd","padStart","parseInt","partial","partialRight","partition","pick","pickBy","propertyOf","pull","pullAll","pullAt","random","range","rangeRight","rearg","reject","remove","repeat","restFrom","result","sampleSize","some","sortBy","sortedIndex","sortedIndexOf","sortedLastIndex","sortedLastIndexOf","sortedUniqBy","split","spreadFrom","startsWith","subtract","sumBy","take","takeRight","takeRightWhile","takeWhile","tap","throttle","thru","times","trimChars","trimCharsEnd","trimCharsStart","truncate","union","uniqBy","uniqWith","unset","unzipWith","without","wrap","xor","zip","zipObject","zipObjectDeep"],
3:["assignInWith","assignWith","clamp","differenceBy","differenceWith","findFrom","findIndexFrom","findLastFrom","findLastIndexFrom","getOr","includesFrom","indexOfFrom","inRange","intersectionBy","intersectionWith","invokeArgs","invokeArgsMap","isEqualWith","isMatchWith","flatMapDepth","lastIndexOfFrom","mergeWith","orderBy","padChars","padCharsEnd","padCharsStart","pullAllBy","pullAllWith","rangeStep","rangeStepRight","reduce","reduceRight","replace","set","slice","sortedIndexBy","sortedLastIndexBy","transform","unionBy","unionWith","update","xorBy","xorWith","zipWith"],
4:["fill","setWith","updateWith"]},e.aryRearg={2:[1,0],3:[2,0,1],4:[3,2,0,1]},e.iterateeAry={dropRightWhile:1,dropWhile:1,every:1,filter:1,find:1,findFrom:1,findIndex:1,findIndexFrom:1,findKey:1,findLast:1,findLastFrom:1,findLastIndex:1,findLastIndexFrom:1,findLastKey:1,flatMap:1,flatMapDeep:1,flatMapDepth:1,forEach:1,forEachRight:1,forIn:1,forInRight:1,forOwn:1,forOwnRight:1,map:1,mapKeys:1,mapValues:1,partition:1,reduce:2,reduceRight:2,reject:1,remove:1,some:1,takeRightWhile:1,takeWhile:1,times:1,
transform:2},e.iterateeRearg={mapKeys:[1],reduceRight:[1,0]},e.methodRearg={assignInAllWith:[1,0],assignInWith:[1,2,0],assignAllWith:[1,0],assignWith:[1,2,0],differenceBy:[1,2,0],differenceWith:[1,2,0],getOr:[2,1,0],intersectionBy:[1,2,0],intersectionWith:[1,2,0],isEqualWith:[1,2,0],isMatchWith:[2,1,0],mergeAllWith:[1,0],mergeWith:[1,2,0],padChars:[2,1,0],padCharsEnd:[2,1,0],padCharsStart:[2,1,0],pullAllBy:[2,1,0],pullAllWith:[2,1,0],rangeStep:[1,2,0],rangeStepRight:[1,2,0],setWith:[3,1,2,0],sortedIndexBy:[2,1,0],
sortedLastIndexBy:[2,1,0],unionBy:[1,2,0],unionWith:[1,2,0],updateWith:[3,1,2,0],xorBy:[1,2,0],xorWith:[1,2,0],zipWith:[1,2,0]},e.methodSpread={assignAll:{start:0},assignAllWith:{start:0},assignInAll:{start:0},assignInAllWith:{start:0},defaultsAll:{start:0},defaultsDeepAll:{start:0},invokeArgs:{start:2},invokeArgsMap:{start:2},mergeAll:{start:0},mergeAllWith:{start:0},partial:{start:1},partialRight:{start:1},without:{start:1},zipAll:{start:0}},e.mutate={array:{fill:!0,pull:!0,pullAll:!0,pullAllBy:!0,
pullAllWith:!0,pullAt:!0,remove:!0,reverse:!0},object:{assign:!0,assignAll:!0,assignAllWith:!0,assignIn:!0,assignInAll:!0,assignInAllWith:!0,assignInWith:!0,assignWith:!0,defaults:!0,defaultsAll:!0,defaultsDeep:!0,defaultsDeepAll:!0,merge:!0,mergeAll:!0,mergeAllWith:!0,mergeWith:!0},set:{set:!0,setWith:!0,unset:!0,update:!0,updateWith:!0}},e.placeholder={bind:!0,bindKey:!0,curry:!0,curryRight:!0,partial:!0,partialRight:!0},e.realToAlias=function(){var t=Object.prototype.hasOwnProperty,r=e.aliasToReal,n={};
for(var i in r){var a=r[i];t.call(n,a)?n[a].push(i):n[a]=[i]}return n}(),e.remap={assignAll:"assign",assignAllWith:"assignWith",assignInAll:"assignIn",assignInAllWith:"assignInWith",curryN:"curry",curryRightN:"curryRight",defaultsAll:"defaults",defaultsDeepAll:"defaultsDeep",findFrom:"find",findIndexFrom:"findIndex",findLastFrom:"findLast",findLastIndexFrom:"findLastIndex",getOr:"get",includesFrom:"includes",indexOfFrom:"indexOf",invokeArgs:"invoke",invokeArgsMap:"invokeMap",lastIndexOfFrom:"lastIndexOf",
mergeAll:"merge",mergeAllWith:"mergeWith",padChars:"pad",padCharsEnd:"padEnd",padCharsStart:"padStart",propertyOf:"get",rangeStep:"range",rangeStepRight:"rangeRight",restFrom:"rest",spreadFrom:"spread",trimChars:"trim",trimCharsEnd:"trimEnd",trimCharsStart:"trimStart",zipAll:"zip"},e.skipFixed={castArray:!0,flow:!0,flowRight:!0,iteratee:!0,mixin:!0,rearg:!0,runInContext:!0},e.skipRearg={add:!0,assign:!0,assignIn:!0,bind:!0,bindKey:!0,concat:!0,difference:!0,divide:!0,eq:!0,gt:!0,gte:!0,isEqual:!0,
lt:!0,lte:!0,matchesProperty:!0,merge:!0,multiply:!0,overArgs:!0,partial:!0,partialRight:!0,propertyOf:!0,random:!0,range:!0,rangeRight:!0,subtract:!0,zip:!0,zipObject:!0,zipObjectDeep:!0}},function(t,e){t.exports={}}])});