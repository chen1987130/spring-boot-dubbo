var oTable = null;

define([ $, 'base/common', 'lib/jquery.dataTables.min' ], function(jq, common) {
	var aoColumns = [ {
		"sTitle" : "ID",
		"mDataProp" : "id",
		"sWidth" : "50",
		"sClass" : "title"
	}, {
		"sTitle" : "账号",
		"mDataProp" : "account",
		"sWidth" : "200px"
	}, {
		"sTitle" : "用户名称",
		"mDataProp" : "username",
		"sWidth" : "200px"
	}, {
		"sTitle" : "密码",
		"mDataProp" : "password",
		"sWidth" : "200px"
	}, {
		"sTitle" : "用户状态",
		"mDataProp" : "statusView",
		"sWidth" : "200px"
	}, {
		"sTitle" : "操作",
		"sWidth" : "100px",
		"sClass" : "center",
		"aTargets" : [ 0 ],
		"fnRender" : function(o) {
			return renderOption(o.aData.id);
		}
	} ];

	function init() {
		Util.init();
		Util.expandPanel();

		var url = Util.path + '/user/getTableList';
		createDataTable("#userList", url, aoColumns);

		initEvent();

	}

	// 注册事件
	function initEvent() {
		$("#btnQuery").click(function() {
			queryList();
		});

		// 双击查看
		$(".list-tbl tbody").on("dblclick", "tr", function() {
			var i = $('.list-tbl tbody tr').index($(this));
			var ID = oTable.fnGetData(i).id;
			prepareView(ID);
		});

		$(".list-tbl tbody").on("click", "#editBtn", function() {
			var ID = $(this).data('id');
			prepareUpdate(ID);
		});

		$(".list-tbl tbody").on("click", "#delBtn", function() {
			var ID = $(this).data('id');
			prepareDel(ID);
		});

		$("#addBtn").click(function() {
			prepareAdd();
		});

		$("#baseQuery").keydown(function(event) {
			if (event.keyCode == 13) {
				var url = Util.path + '/user/getTableList';
				url += "?baseQuery=" + $('#baseQuery').val();
				createDataTable("#userList", url, aoColumns);
			}
		});
	}

	function queryList() {
		var url = Util.path + '/user/getTableList';
		url += "?statusQuery=" + $('.widget-box #statusQuery').val();
		url += "&userQuery=" + $('.widget-box #userQuery').val();
		createDataTable("#userList", url, aoColumns);
	}

	// 绑定数据到dataTable
	function createDataTable(datatableName, urlWithExtraParams, aoColumns) {
		if (oTable != null) {
			oTable.fnDestroy();
		}

		oTable = $(datatableName).dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sAjaxSource" : urlWithExtraParams,
			"aoColumns" : aoColumns,
			"iDisplayLength" : 10,
			"sPaginationType" : "full_numbers",
			"bLengthChange" : true,
			"bFilter" : false,
			"bSort" : false
		});
	}

	// 操作按钮
	function renderOption(ID) {
		var div_ = "";
		div_ += '<button id="editBtn" data-id="' + ID + '" type="button" class="btn btn-primary btn-linked">修改</button>&nbsp;&nbsp;';
		div_ += '<button id="delBtn" data-id="' + ID + '" type="button" class="btn btn-danger btn-linked">删除</button>';
		return div_;
	}

	// 新增
	function prepareAdd() {
		Util.dialog(Util.path + '/user/toAddOrUpdate?type=Add', '新增用户信息');
	}

	// 查看
	function prepareView(ID) {
		var url = Util.path + "/user/toAddOrUpdate?type=View&ID=" + ID;
		Util.dialog(url, '查看用户信息');
	}

	// 删除
	function prepareDel(ID) {
		var url = Util.path + "/user/delete?ID=" + ID;
		Util.delConfirm2('是否确认删除', url, function() {
			oTable.fnDraw(false);
		});
	}

	// 编辑
	function prepareUpdate(ID) {
		Util.dialog(Util.path + '/user/toAddOrUpdate?type=Update&ID=' + ID, '编辑用户信息');
	}

	return {
		init : init
	}

});