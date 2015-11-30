<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">

	var nodes = <s:property value="operTree" escape="false"/>,
		haveOpers = "<s:property value="haveOpers" escape="false"/>";
	var ztreeObj;
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			}
	
	};
$(function(){
	ztreeObj = $.fn.zTree.init($("#ztree"), setting, nodes);
	checkNodes();
});

function checkNodes(){
	if(haveOpers == ""){
		return;
	}
	var ids=haveOpers.split('|');
	for(var x in ids){
		var node  = ztreeObj.getNodeByParam("id", ids[x]);
		if(node){
			ztreeObj.checkNode(node,true,false,false);
// 			ztreeObj.expandNode(node, true, false, true);
		}

	}
}
function doSubmitJson(){
	var moduleArr = [],operArr = [],
    	url = 'manager/sysrole!doSaveAssignPermi',
	    param = '',
	    callback = function(data){	
	        var buttons = {
	                '确定':function(){
	                	closeRefresh();
	                    $(this).dialog('close');
	                }
	            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
		},
		getNodeIds = function(){
			var treeObj = $.fn.zTree.getZTreeObj("ztree");
			var chkNodes = treeObj.getCheckedNodes(true);
			for(var x in chkNodes){
				
				var type = chkNodes[x].type;
				var id = chkNodes[x].id;
				if(type == 'module' || type== 'page'){
					moduleArr.push(id);
				}
				if(type == 'operation'){
					operArr.push(id);
				}
			}
		};
		
		getNodeIds();
		
		param = "modules="+moduleArr.join()+"&opers="+operArr.join();
		param += "&roleId="+$("#rolePermTb #roleId").val();
		
	 $.post(url,param,function(data){
	         callback(data);
	     });
	 
}



function closeRefresh(){
	closeDiv('roleDiv','gridTable');
}

</script>
<div id="windows">
	<div id="windowsmain">
			<table border="0" cellspacing="8" cellpadding="0" id="rolePermTb">
				<tr>
					<td style="width: 50px;" align="right">角色名称</td>
					<td style="width: 250px;">
						<s:hidden id="roleId" name="sysRolePO.UUID"/>
						<s:textfield name="sysRolePO.ROLE_NAME" readonly="true" cssClass="input2" />
					</td>
				</tr>
			</table>
		<div id="windowdiv">
			<div id="windowdivmain">
				<div id="opertree">
					<ul id="ztree" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
	<div id="windowsbottom">
		<div id="windowsbottomleft">
			<div id="windowsbottomright">
				<ul>
					<li class="back"><input type="button" class="back"
						onclick="closeRefresh()">
					</li>
					<li class="save" style="margin-right: 15px;"><input
						type="button" class="save" onclick="doSubmitJson()"></li>
				</ul>
			</div>
		</div>
	</div>
</div>