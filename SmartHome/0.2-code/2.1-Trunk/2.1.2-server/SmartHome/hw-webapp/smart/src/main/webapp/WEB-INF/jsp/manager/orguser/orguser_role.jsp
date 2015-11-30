<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
#roleArea label {
	display: inline-block;
	width: 120px;
}
</style>
<script type="text/javascript">

	var nodes = <s:property value="operTree" escape="false"/>,
		haveOpers = "<s:property value="haveOpers" escape="false"/>",
		haveRoles = "<s:property value="haveRoles" escape="false"/>";
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
				chkboxType: { "Y": "p", "N": "s" }
			}
	
	};
$(function(){
	loadNodes(nodes);
	var allnodes = ztreeObj.getNodes();
	checkNodes(haveOpers,true);
	setChkDisabled(allnodes);
	//判断是部门角色不允许修改
	setDeptRoleDisabled();
	
});

function loadNodes(nodes){
	loadTree(nodes);
}
function loadTree(nodes){
	ztreeObj = $.fn.zTree.init($("#ztree"), setting, nodes);
}



function setChkDisabled(nodes){
	$.each(nodes, function(i, n){
		ztreeObj.setChkDisabled(nodes[i], true);
		});
}
function checkNodes(str,flag){ 
	if(!str || str == ""){
		return;
	}
	var ids=str.split('|');
// 	for(var x in ids){
// 		var node  = ztreeObj.getNodeByParam("id", ids[x]);
		
// 		if(node){
// 			ztreeObj.checkNode(node,flag,true);
// // 			ztreeObj.expandNode(node, true, false, true);
// 		}

// 	}

	$.each(ids, function(i, n){
		
			var node  = ztreeObj.getNodeByParam("id", n);
			
			if(node){
				ztreeObj.checkNode(node,flag,true);
	// 			ztreeObj.expandNode(node, true, false, true);
			}
		});
}
function doSubmitData(){
	
    var url = 'manager/orguser!doSaveAssignRole',
	    param = '',
	    callback = function(data){	
	        var buttons = {
	                '确定':function(){
	                	closeRefresh();
	                    $(this).dialog('close');
	                }
	            };
        showMsg(data.content, data.type, {title:data.title,buttons:buttons});
		};
		
		var ids = [];
		$.each($("#roleArea input[type=checkbox][name=role]:checked").not(":disabled"), function(i, n){
		  var id = $(n).val();
		  ids.push(id);
		});
		param += "roles="+ids;
		param += "&userId="+$("#roleArea #userId").val();
		Page.showLoading();
	 $.post(url,param,function(data){
	         callback(data);
	         Page.hideLoading();
	     });
	 
}



function closeRefresh(){
	closeDiv('userDiv');
}

function checkNodesByRoleId(obj){
	var ids = [];
	$.each($("#roleArea input[type=checkbox][name=role]:checked"), function(i, n){
	  var id = $(n).val();
	  ids.push(id);
	});
	
	loadNodes(nodes);
	var allnodes = ztreeObj.getNodes();

	var url = 'manager/orguser!doQueryHaveOpers',
		param = 'roleIds='+ids;
// 	Page.showLoading();
	$.post(url,param,function(data){
		var opers = data;
		checkNodes(opers,true);
		setChkDisabled(allnodes);
// 		Page.hideLoading();
	});
	
}

/**
 * 设置部门角色不可修改
 */
function setDeptRoleDisabled(){
	
	var deptRole = haveRoles.substring(2, haveRoles.length-2);
	
	deptRole = deptRole.replace(/}\s*,\s*{/g,"|");
	var deptRoleArr = deptRole.split("|");
	$.each($("#roleArea input[type=checkbox][name=role]:checked"), function(i, n){
		  var id = $(n).val();
		  for(var x = 0 ; x < deptRoleArr.length;x++){
			
			  var str =  deptRoleArr[x]; 
			  var roleArr = str.split(",");
			  
			  var isDeptRole = roleArr[0].split("=")[1];
			  var roleId = roleArr[1].split("=")[1];
		
			  if(roleId == id  && isDeptRole == 1){
				  $(n).attr("disabled","disabled");
				  break;
			  }
		  }
		  
		});
	
	
}

</script>

<div id="windows">
	<div id="windowsmain">
			<div id="windowdiv">
				<div id="windowdivmain">
					<table border="0" cellspacing="0" cellpadding="0"
						class="windowdivmaintable">
						<tr>
							<th scope="col" style="line-height: 26px; text-align: center; width:280px;" >用户权限</th>
							<th scope="col" style="line-height: 26px; text-align: center; width:420px;">角色分配</th>
						</tr>
						<tr>
							<td align="left" valign="top"  >
								<div style="height: 200px; overflow-y: auto;">
									<ul id="ztree" class="ztree"></ul>
								</div> 
							</td>

							<td align="left" valign="top">
								<div style="float: left; padding-top: 3px;">
									<div id="roleArea" style="padding: 10px;padding-top:0px;width:420px;height: 200px; overflow-y: auto;">
										<s:form name="roleForm" id="roleForm" theme="simple">
											<s:checkboxlist name="role" list="roleList" listKey="UUID" 
											listValue="ROLE_NAME" value="haveRoleList"  onclick="checkNodesByRoleId(this)"/>
											<s:hidden name="sysUserPO.UUID" id="userId"/>
										</s:form>
								</div>
							</td>
						</tr>
					</table>
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
						type="button" class="save" onclick="doSubmitData()"></li>
				</ul>
			</div>
		</div>
	</div>
</div>
