/**
 * 工具类
 */

if (!Util)
	var Util = {};

Util.path = "http://10.211.240.120:3000";
Util.remote = "http://10.211.240.120:8089";

// 重置，清空查询条件
Util.clearQuery = function() {
	Util.lblSelect('clear');
	Util.clear('.widget-body');
	Util.clear('.tool');
}

Util.initDate = function(type) {
	var type = type ? type : 'List';

	if (type != 'View') {
		$('.form_date_time').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : true,
			autoclose : true,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			minuteStep : 1
		});

		$('.form_date').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			minView : 2,
			forceParse : 0
		});

		$('.form_time').datetimepicker({
			language : 'zh-CN',
			weekStart : 0,
			todayBtn : 0,
			autoclose : 1,
			todayHighlight : 1,
			startView : 1,
			minView : 0,
			maxView : 1,
			forceParse : 0,
			minuteStep : 1
		});

		$('.add-on .icon-remove').click(function() {
			var linkField = $(this).parent().parent().attr("data-link-field");
			$(this).parent().parent().find('input').val("");
			$('#' + linkField).val("");
		});
	} else {
		$('.form_date_time > .add-on').remove();
		$('.form_date > .add-on').remove();
		$('.form_time > .add-on').remove();
	}

}

Util.textLimit = function(item, span, num) {
	span.text(item.val().length);
	item.keyup(function() {
		span.text(item.val().length);
		if (item.val().length > num) {
			span.css("color", "red");
		} else {
			span.css("color", "#686868");
		}
	})
}

// 初始化菜单圆角及其位置
Util.init = function(width) {
	$('.box-info span').click(function() {
		$.each($(this), function() {
			$('.box-info span').parent().hide();
		})
	});

	var w = 0;
	if (typeof (width) !== 'undefined') {
		w = width;
	}

	// 提示
	$('.alert-content').width($(window).width() - 32 - 20);
	// 查询
	$('.widget-box').width($(window).width() - 32);
	// 列表外层
	$('.tbl_div').width($(window).width() - 30 - w);
	// 列表
	$('.list-tbl').width($(window).width() - 30 - w);

	$(window).resize(function() {
		// 提示
		$('.alert-content').width($(window).width() - 32 - 20);
		// 查询
		$('.widget-box').width($(window).width() - 32);
		// 列表外层
		$('.tbl_div').width($(window).width() - 30 - w);
		// 列表
		$('.list-tbl').width($(window).width() - 30 - w);
	});

}

// 滚动到顶部
Util.scrollToTop = function(e) {
	$(e).hide().removeAttr("href");
	if ($(window).scrollTop() != "0") {
		$(e).fadeIn("fast")
	}
	var scrollDiv = $(e);
	$(window).scroll(function() {
		if ($(window).scrollTop() == "0") {
			$(scrollDiv).fadeOut("fast")
		} else {
			$(scrollDiv).fadeIn("fast")
		}
	});
	$(e).click(function() {
		$("html, body").animate({
			scrollTop : 0
		}, "fast")
	})
}

// 输入框样式
Util.inputStyle = function() {
	$('input[type="text"],input[type="password"],input[type="file"],textarea').focus(function() {
		$(this).addClass("focus");
	}).blur(function() {
		$(this).removeClass("focus");
	});
}

// 新增/修改表格样式
Util.addTblStyle = function() {
	$('.add-tbl tr:odd').addClass("odd");
	$('.add-tbl tr:even').addClass("even");
}

// 通用折叠面板
Util.expandPanel = function() {
	$('[data-action="collapse"]').click(function() {
		var p = $('.widget-box');
		var b = p.children('.widget-body');

		if ($(this).children().hasClass("icon-chevron-up")) {
			$(this).children().removeClass("icon-chevron-up");
			$(this).children().addClass("icon-chevron-down");

			b.slideUp("normal", function() {

				p.removeClass("collapsed");
			});

		} else if ($(this).children().hasClass("icon-chevron-down")) {
			$(this).children().removeClass("icon-chevron-down");
			$(this).children().addClass("icon-chevron-up");

			b.slideDown("normal", function() {
				p.addClass("collapsed");
			});
		}
	});
}

// label条件选择
Util.lblSelect = function(o) {
	if (!o) {
		$('.widget-box label').click(function() {
			$(this).addClass('selected');
			$(this).siblings().removeClass('selected');
		});
	} else {
		$('.widget-box label').removeClass('selected');
		$('.widget-box .qtype').each(function() {
			$(this).children('label').first().addClass('selected');
		});
	}
}

// JSON字符串格式化
Util.format = function(str) {
	if (str == null || typeof (str) == 'undefined')
		return '';
	return str;
}

// 清空文本框和下拉框
Util.clear = function(selector) {
	$(selector + ' input[type="hidden"]').val('');
	$(selector + ' input[type="text"]').val('');
	$(selector + ' select').each(function(i, item) {
		item.selectedIndex = 0;
	})
}

// 跳转到页面
Util.redirect = function(url) {
	location.href = url;
}

// 跳转到页面
Util.redirectFrame = function(url) {
	window.top.frames['rframe'].location.href = url;
}

// 跳转到页面
Util.redirectTop = function(url) {
	window.top.location.href = url;
}

// 可折叠行查看详情
Util.expandableRows = function() {
	var titles_total = $('td.title').length;
	if (titles_total) {
		$('td.title').each(function(i, e) {
			$(e).children('div').css('z-index', String(titles_total - i));
		});

		$('td.title').find('a.tit').click(function() {
			$('.opened').slideUp("fast");
			$('td.highlighted').removeClass('highlighted');

			var tr = $(this).parents("tr");
			var div = $(this).parent().find('.listingDetails');

			if (!$(div).hasClass('opened')) {
				$(div).addClass('opened').width($(tr).width() - 2).slideDown("fast");
				$(tr).find('td').addClass('highlighted');
			} else {
				$(div).removeClass('opened');
				$(tr).find('td').removeClass('highlighted');
			}
			return false;
		});
	}
}

// 删除确认
Util.delConfirm = function(msg, url) {
	window.top.art.dialog({
		title : '系统提示',
		content : msg,
		icon : 'question',
		width : 240,
		height : 100,
		ok : function() {
			$.ajax({
				url : url,
				dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					if (data.status == 0) {
						window.top.frames['rframe'].location.reload();
					} else {
						Util.info(data.msg);
					}
				}
			});
			return true;
		},
		cancelVal : '关闭',
		cancel : true,
		lock : true
	});
}

// 删除确认
Util.delConfirm2 = function(msg, url, fn) {
	window.top.art.dialog({
		title : '系统提示',
		content : msg,
		icon : 'question',
		width : 240,
		height : 100,
		ok : function() {
			$.ajax({
				url : url,
				dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					if (data.status == 0) {
						if (fn) {
							fn();
						}
					} else {
						Util.info(data.msg);
					}
				}
			});
			return true;
		},
		cancelVal : '关闭',
		cancel : true,
		lock : true
	});
}

// 删除确认
Util.delConfirm3 = function(msg, url, toUrl) {
	window.top.art.dialog({
		title : '系统提示',
		content : msg,
		icon : 'question',
		width : 240,
		height : 100,
		ok : function() {
			$.ajax({
				url : url,
				dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					if (data.status == 0) {
						window.location.href = toUrl;
					} else {
						Util.info(data.msg);
					}
				}
			});
			return true;
		},
		cancelVal : '关闭',
		cancel : true,
		lock : true
	});
}

Util.confirm = function(msg, url) {
	window.top.art.dialog({
		title : '系统提示',
		content : msg,
		icon : 'question',
		width : 240,
		height : 100,
		ok : function() {
			$.ajax({
				url : url,
				dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					var status = data.status;
					if (status == 0) {
						Util.info("操作成功!");
					} else {
						Util.info(data.msg);
					}
				}
			});
			return true;
		},
		cancelVal : '关闭',
		cancel : true,
		lock : true
	});
}

// 检查上传文件扩展名
Util.checkFileExt = function(ctrlId, allowFileExt) {
	allowFileExt = allowFileExt || [ "jpg", "jpeg", "gif", "png", "doc", "docx", "xls", "xlsx", "pdf", "zip", "rar", "txt" ];
	var filePath = $(ctrlId).val();
	var pos = filePath.lastIndexOf(".");
	var ext = filePath.substring(pos + 1, filePath.length).toLowerCase();
	if ($.inArray(ext, allowFileExt) == -1) {
		var p = ctrlId.parent();
		var copy = ctrlId.clone().val("");
		ctrlId.remove();
		p.append(copy);
		return false;
	}
	return true;
}

Util.checkFileNameExt = function(filePath, allowFileExt) {
	allowFileExt = allowFileExt || [ "jpg", "jpeg", "gif", "png", "doc", "docx", "xls", "xlsx", "pdf", "zip", "rar", "txt" ];
	var pos = filePath.lastIndexOf(".");
	var ext = filePath.substring(pos + 1, filePath.length).toLowerCase();
	if ($.inArray(ext, allowFileExt) == -1) {
		return true;
	}
	return false;
}

// 输入内容时放大提示
Util.easyInput = function(selector) {
	var p = $(selector).position();
	$(selector).easyinput({
		position : 'absolute',
		top : (p.top - 29) + 'px',
		left : (p.left + 18) + 'px',
		color : '#D08100',
		background : '#FFFFE4',
		border : '1px solid #FA9C00',
		fontsize : '20px',
		padding : '3px'
	});
}

Util.info = function(msg, i) {
	i = i || 3;
	window.top.art.dialog.tips(msg, i);
}

Util.error = function(msg) {
	window.top.art.dialog({
		title : "错误信息",
		content : msg,
		width : 200,
		height : 120
	});
}

/** ** artDialog 相关方法封装 *** */
// 弹出dialog对话框
Util.dialog = function(url, title, config) {
	config = config || {};
	var height = config.height || 580;
	var width = config.width || 800;
	var close = config.close || function() {
	};
	window.top.art.dialog.open(url, {
		title : title,
		width : width,
		height : height,
		cache : false,
		resize : false,
		drag : false,
		fixed : true,
		lock : true,
		close : close,
		id : '_artDialog_'
	});
}

Util.dialogFix = function(url, title, config) {
	config = config || {};
	var height = 'auto';
	var width = 800;
	var close = config.close || function() {
	};
	window.top.art.dialog.open(url, {
		title : title,
		width : width,
		height : height,
		cache : false,
		resize : false,
		drag : false,
		fixed : true,
		lock : true,
		close : close,
		id : '_artDialogFix_'
	});
}

// 弹出dialog对话框
Util.dialogWin = function(url, title, width, height) {
	var height = height || 580;
	var width = width || 800;
	;

	window.top.art.dialog.open(url, {
		title : title,
		width : width,
		height : height,
		cache : false,
		resize : false,
		drag : false,
		fixed : true,
		lock : true,
		id : '_artDialogWin_'
	});
}

// 弹出窗口
Util.popWin = function(title, html, width, height) {
	window.top.art.dialog({
		title : title,
		content : html,
		width : width,
		height : height,
		ok : function() {
			return false;
		},
		cancelVal : '取消',
		cancel : true,
		resize : false
	});
}

// 右下角弹出窗口
Util.popNotice = function(title, width, height, content) {
	window.top.art.dialog.notice({
		title : title,
		width : width,
		height : height,
		content : content,
		padding : '10px',
		time : 10,
		id : '_artNotice_'
	});
}

// toolbar(Full,Basic两种)
// toolbar默认Full
Util.Editor = function(render, toolbar, height) {
	var config = {};
	config.resize_enabled = false;
	config.removePlugins = 'elementspath';
	config.toolbar = toolbar ? toolbar : 'Full';
	config.toolbar_Full = [ {
		name : 'document',
		items : [ 'DocProps', 'Preview', 'Print', '-' ]
	}, {
		name : 'clipboard',
		items : [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ]
	}, {
		name : 'editing',
		items : [ 'Find', 'Replace', '-', 'SelectAll', '-' ]
	}, {
		name : 'basicstyles',
		items : [ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'RemoveFormat' ]
	}, {
		name : 'colors',
		items : [ 'TextColor', 'BGColor' ]
	}, "/", {
		name : 'links',
		items : [ 'Link', 'Unlink' ]
	}, {
		name : 'paragraph',
		items : [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ]
	}, {
		name : 'insert',
		items : [ 'Table', 'HorizontalRule', 'SpecialChar' ]
	}, {
		name : 'styles',
		items : [ 'Font', 'FontSize' ]
	}, {
		name : 'full',
		items : [ 'Maximize' ]
	} ];

	config.toolbar_Basic = [ {
		name : 'clipboard',
		items : [ 'Undo', 'Redo' ]
	}, {
		name : 'editing',
		items : [ 'Find', 'Replace', '-', 'SelectAll', '-' ]
	}, {
		name : 'basicstyles',
		items : [ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'RemoveFormat' ]
	}, {
		name : 'paragraph',
		items : [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ]
	}

	];
	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;';
	config.height = height || 250;
	CKEDITOR.replace(render, config);
}

Util.getEditorVal = function(render) {
	var editor = eval("CKEDITOR.instances." + render);
	return editor.getData();
}

Util.setEditorVal = function(render, value) {
	var editor = eval("CKEDITOR.instances." + render);
	editor.setData(value);
}

Util.editorReadOnly = function(flag) {
	if (flag == undefined) {
		flag = true;
	}
	CKEDITOR.on('instanceReady', function(ev) {
		var editor = ev.editor;
		editor.on('readOnly', function() {
			// document.getElementById( 'readOnlyOn' ).style.display =
			// this.readOnly ? 'none' : '';
			// document.getElementById( 'readOnlyOff' ).style.display =
			// this.readOnly ? '' : 'none';
		});
		editor.setReadOnly(flag);
	});
}

Util.serializeObject = function(form) {
	// 定义一个对象
	var obj = {};
	// 将表单序列化成数组，在循环遍历数组
	$.each(form.serializeArray(), function(index) {
		// 先判断当前值的名称是否存在，存在则追加
		if (obj[this['name']]) {
			obj[this['name']] = obj[this['name']] + "," + this['value'];
		} else {
			obj[this['name']] = this['value'];
		}
	});
	return obj;
}

// ********************输入验证相关*************************
Util.number = function(obj) { // 只能输入数字
	obj.value = obj.value.replace(/\D/g, "");
}

Util.decimal = function(obj) { // 只能输入数字及小数点
	obj.value = obj.value.replace(/[^\d.]/g, ""); // 先把非数字的都替换掉，除了数字和
	obj.value = obj.value.replace(/^\./g, ""); // 必须保证第一个为数字而不是
	obj.value = obj.value.replace(/\.{2,}/g, "."); // 保证只有出现一个.而没有多个
	obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", "."); // 保证.只出现一次，而不能出现两次以上
}

Util.Integer = function(obj) {
	var r = /^(0|[1-9])[0-9]*$/;
	if (!r.test(obj.value)) {
		obj.value = "0";
	}
}

Util.Float = function(obj) {
	var r = /^(0|[1-9])[0-9]*(.[0-9]+)?$/;
	if (!r.test(obj.value)) {
		obj.value = "0.0";
	}
}

Util.AddTrim = function() {
	$('.addBox input[type="text"],input[type="password"],textarea,select').each(function(i, item) {
		if ($(item).val() != null) {
			$(item).val($.trim($(item).val()));
		}
	});
}

Util.ValidEmpty = function() {
	var flag = true;
	$(".addBox span[class='red']").each(function(i, item) {
		if (!flag) {
			return false;
		}

		$(item).closest("td").next("td").find(":input[type='text'],input[type='password'],textarea,select").each(function(i, item) {
			if ($(item).val() == '') {
				// 判断是否是TAB
				var p = $(item).parents('.tab-content');
				if (p.size() > 0) {
					var tab = $(item).parents('.tab-pane:eq(0)');

					// 判断是否该字段是否在显示面板
					if (!tab.hasClass('active')) {
						// 切换表面板
						tab.siblings('.tab-pane').removeClass('active');
						tab.addClass('active');

						// 切换表头
						var tid = tab.attr('ID');
						$('.nav-tabs > li').removeClass('active');
						$(".nav-tabs a[href='#" + tid + "']").parent().addClass('active');
					}
				}
				$(item).focus();

				flag = false;
				return false;
			}
		});
	});
	return flag;
}
