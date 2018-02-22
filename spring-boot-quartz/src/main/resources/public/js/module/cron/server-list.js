define([ $, 'base/common' ], function() {

	function init() {
		initPage();
	}

	function initPage() {
		$('#startedBtn').click(function() {
			changeStatus('start');
		});
		$('#pausedBtn').click(function() {
			changeStatus('pause');
		});
		$('#stopedBtn').click(function() {
			changeStatus('stop');
		});
	}

	function changeStatus(cmd) {
	}

	return {
		init : init
	};

});