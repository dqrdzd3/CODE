<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript">
var setting = {
		data: {
			simpleData: {
				enable: true
			},
			keep: {
				parent: true,
				leaf: true
			}
		},
		callback: {
 			onClick: treeNodeClick
		}

};
function treeNodeClick(event, treeId, treeNode) {
	$("#treeBtn").val(treeNode.name);
	$("#orglist").val(treeNode.id);
	$$.closeDiv("xzDiv");
}

$(function(){
	var url = '',param='',callback = function(data){
		alert(data);
    	data = $.parseJSON(data);
    	alert(data);
		$.fn.zTree.init($("#tree"), setting, data);
	};
	var selectType = $("#selectType").val();
	if(selectType =='ENT'){
		url = 'register/c001!queryAllValidInstanceToJson';
	}else{
		url = 'gov/c002!queryAllValidInstanceToJson';
	}
	$.post(url,param,callback);
});

</script>
<div class="">
	<ul id="tree" class="ztree"></ul>
</div>
<input type="hidden" value="${selectType}" id="selectType" />
