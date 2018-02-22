$(function() {
	$("#setCookiesBtn").click(function() {
		if (typeof FNJSBridge != 'undefined') {
			FNJSBridge.callHandler("setCookie", "{\"th5_site_area\":\"GP0002_001_002\",\"th5_site\":\"GP0002\"}", "");
		}
	});

	$("#getCookiesBtn").click(function() {
		if (typeof FNJSBridge != 'undefined') {
			FNJSBridge.callHandler("getCookie", "", "getCookieCallback");
		}
	});
});

// 获取cookie回调
function getCookieCallback(cookies) {
	alert(cookies);
}