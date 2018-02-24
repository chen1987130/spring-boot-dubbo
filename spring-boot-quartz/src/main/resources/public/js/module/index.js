define([ $, 'base/common' ], function() {

	function init() {
		$(window).resize(function() {
			autoResize();
		});

		autoResize();
		getMenu();

		// 展开收缩子菜单
		$('.nav-list .dropdown-toggle').click(function() {
			open($(this));
		});

		// 点击菜单
		$('.nav-list a[dataType="menu"]').click(function() {
			active($(this));
		});

		// 菜单向上
		$('#upBtn').click(function() {
			$('#sidebar').scrollTop($('#sidebar').scrollTop() - 30);
		});

		// 菜单向下
		$('#downBtn').click(function() {
			$('#sidebar').scrollTop($('#sidebar').scrollTop() + 30);
		});
	}

	function active(item) {
		var url = item.attr('dataUrl');
		$('.nav-list li').removeClass('active');
		menuActive(item.parent());
		document.getElementById("rframe").src = url;
	}

	function open(item) {
		if (item.parent().hasClass('open')) {
			item.parent().removeClass('open');
			item.next('.submenu').css('display', 'none');
		} else {
			// 关闭原先展开的
			var code = item.parent().attr('id').split("_")[1];
			if (code.length == 3) {
				$('.nav-list li').removeClass('open');
				$('.nav-list .submenu').css('display', 'none');
			}

			item.parent().addClass('open');
			item.next('.submenu').css('display', 'block');
		}
		autoResize();
	}

	// 菜单点击
	function menuActive(menu) {
		menu.addClass('active');
		var ID = menu.attr('id');
		if (ID.length > 8) {
			var code = ID.substring(0, ID.length - 3);
			var cMenu = $('#' + code);
			menuActive(cMenu);
		}
	}

	function autoResize() {
		var clientHeight = $(window).height();
		var clientWidth = $(window).width() - $('.lefttree').width() - 1;
		var right_height = clientHeight - $('#navbar').height();

		$("#rframe").width(clientWidth);
		$("#rframe").height(right_height);
		$("#menutree").height(right_height);
		$("#sidebar").css({
			'max-height' : right_height - $('#sidebar-shortcuts-large').height() - 1 - 26 * 2 + "px"
		});

		if ($('.nav-list').height() > $("#sidebar").height()) {
			$('.btn-chevron').show();
		} else {
			$('.btn-chevron').hide();
		}
	}

	// 获取菜单数据
	function getMenu() {
		var url = Util.path + "/module/getMenu";
		$.ajax({
			url : url,
			dataType : "json",
			cache : false,
			async : false,
			success : function(json) {
				if (json.status === 0) {
					$('.nav-list').html(renderMenu(json.data, 0));
				}
			}
		});
	}

	// 拼装菜单
	function renderMenu(data, index) {
		var div = '';
		$.each(data, function(i, item) {
			var name = item.name;
			var code = item.code;
			var url = item.url;
			var children = item.children;
			div += '<li id="menu_' + code + '">';
			div += '<a';
			if (typeof (children) != 'undefined' && children.length > 0) {
				div += ' class="dropdown-toggle"';
			}
			if (typeof (url) != 'undefined' && $.trim(url) != '') {
				div += ' dataType="menu" dataUrl="' + url + '" ';
			}
			div += ' href="javascript:void(0);">';

			if (index == 0) {
				if (code == '010') {
					div += '<i class="icon-cog"></i>';
				} else if (code == '020') {
					div += '<i class="icon-truck"></i>';
				} else if (code == '030') {
					div += '<i class="icon-user"></i>';
				} else if (code == '040') {
					div += '<i class="icon-laptop"></i>';
				} else if (code == '050') {
					div += '<i class="icon-credit-card"></i>';
				} else if (code == '060') {
					div += '<i class="icon-bar-chart"></i>';
				} else {
					div += '<i class="icon-*"></i>';
				}
			} else if (index == 1) {
				div += '<i class="icon-double-angle-right"></i>'
			} else {
				div += '<i class="icon-*"></i>';
			}

			if (index == 0) {
				div += '<span class="menu-text">';
			}
			div += name;
			if (index == 0) {
				div += '</span>';
			}

			if (typeof (children) != 'undefined' && children.length > 0) {
				div += '<b class="arrow icon-angle-down"></b>';
			}
			div += '</a>'

			if (typeof (children) != 'undefined' && children.length > 0) {
				div += '<ul class="submenu">';
				var temp = index + 1;
				div += renderMenu(children, temp);
				div += '</ul>';
			}
			div += '</li>';
		});
		return div;
	}

	return {
		init : init
	};

});
