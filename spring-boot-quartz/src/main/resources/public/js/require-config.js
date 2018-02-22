!(function() {

	requirejs.config({
		baseUrl : 'http://127.0.0.1:3000/js',
		paths : {
			$ : '$',
			jquery : '$'
		},
		shim : {
		},
		urlArgs : "v=" + new Date().getTime()
	});

})();