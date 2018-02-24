define([ $, 'base/common', 'lib/jquery.getParmByUrl' ], function(jq, common) {

	function init() {
		Util.inputStyle();
		Util.addTblStyle();

		var param = $().getParmByUrl('type');

		if (param === 'View') {
			$('input[type="text"]').attr('readonly', 'readonly');
			$('select').attr('disabled', 'disabled');
			$('textarea').attr('disabled', 'disabled');
			$(':checkbox').attr('disabled', 'disabled');
			$('#btnSave').hide();
		}
		if (param != 'Update') {
			$('.uh').hide();
		}else{
			$('#account').attr('readOnly', 'readOnly');
		}

		$('#btnSave').click(function() {
			var url = Util.path + "/user/addOrUpdate";
			if (beforeSave()) {
				$('#btnSave').attr({
					"disabled" : "disabled"
				});
				$.post(url, $('#userForm').serialize(), function(data) {
					var status = data.status;
					if (status !== 0) {
						$("#btnSave").removeAttr("disabled");
						Util.info(data.msg);
					} else {
						if (window.top.frames['rframe']) {
							window.top.frames['rframe'].contentWindow.oTable.fnDraw(false);
						} else {
							console.log(window.top);
							window.top.oTable.fnDraw(false);
						}
						art.dialog.close();
					}
				});
			}
		});

	}

	function beforeSave() {
		Util.AddTrim();
		if (!Util.ValidEmpty()) {
			Util.info('必填字段不能为空');
			return false;
		}
		return true;
	}

	return {
		init : init
	};

});