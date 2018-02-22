define([ $, 'base/common' ], function() {
	function init() {

		$('#loginBtn').click(function() {
			var username = $('#username').val();
			var password = $('#password').val();
			var remember = 0;
			if($('#remember').is(':checked')){
				remember = $('#remember').val();
			}
			
			if($.trim(username) == ''){
				$('#hints').text('用户名不能为空');
				$('#username').focus();
				$('#hints').show();
				return
			}else if($.trim(password) == ''){
				$('#hints').text('密码不能为空');
				$('#username').focus();
				$('#hints').show();
				return
			}
			
			$('#hints').hide();

			var data = {
				username : username,
				password : password,
				remember : remember
			}

			var url = Util.path + "/user/login";
			$.ajax({
				url : url,
				data : data,
				dataType : "json",
				cache : false,
				async : false,
				success : function(json) {
					if (json.status === 0) {
						window.location.href = Util.path;
					}else{
						$('#hints').text(json.msg);
						$('#hints').show();
					}
				},error: function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		});
	}

	return {
		init : init
	}
});