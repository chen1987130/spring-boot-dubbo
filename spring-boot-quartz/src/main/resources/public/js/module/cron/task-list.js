var oTable = null;

define([ $, 'base/common', 'lib/jquery.dataTables.min' ], function(jq, common) {

	var aoColumns = [
			{
				"sTitle" : "任务编码",
				"mDataProp" : "code"
			},
			{
				"sTitle" : "crn.field.taskName",
				"mDataProp" : "taskName"
			},
			{
				"sTitle" : "crn.field.class",
				"mDataProp" : "clazz"
			},
			{
				"sTitle" : "crn.field.exception",
				"mDataProp" : "recoveryView",
				"fnRender" : function(o) {
					if (o.aData.recovery == 'Y') {
						return '<span class="label label-info label-font">' + o.aData.recoveryView + '</span>';
					} else {
						return '<span class="label label-default label-font"> ' + o.aData.recoveryView + '</span>';
					}
				}
			},
			{
				"sTitle" : "crn.field.preTask",
				"mDataProp" : "prevTask"
			},
			{
				"sTitle" : "操作",
				"mDataProp" : "id",
				"sWidth" : "100px",
				"sClass" : "center",
				"aTargets" : [ 0 ],
				"fnRender" : function(o) {
					return renderOption(o.aData.code);
				}
			} ];

	function init() {
		Util.init();
		Util.expandPanel();
		
		var url = Util.remote + "/cronTask/getTableList.do";
		createDataTable("#taskList", url, aoColumns);
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
		div_ += '<button id="delBtn" data-id="' + ID + '" type="button" class="btn btn-danger btn-linked">删除</button>&nbsp;&nbsp;';
		div_ += '<button id="execBtn" data-id="' + ID + '" type="button" class="btn btn-default btn-linked">执行</button>';
		return div_;
	}

	return {
		init : init
	};

});