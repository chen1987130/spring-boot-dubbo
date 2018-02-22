
/*
jquery.getParmByUrl.js 1.0
code by Deeka
date in 2008-08-06 1:31
*/
jQuery.fn.getParmByUrl = function(o){
	var url = window.location.toString();
	var tmp;
	if(url && url.indexOf("?")){
		var arr = url.split("?");
		var parms = arr[1];
		if(parms && parms.indexOf("&")){
			var parmList = parms.split("&");
			jQuery.each(parmList,function(key,val){
				if(val && val.indexOf("=")){
					var parmarr = val.split("=");
					if(o){
						if(typeof(o) == "string" && o == parmarr[0]){
							tmp = parmarr[1] == null?'':parmarr[1];
						}
					}
					else{
						tmp = parms;
					}
				}
			});
		}
	}
	return tmp;
}
