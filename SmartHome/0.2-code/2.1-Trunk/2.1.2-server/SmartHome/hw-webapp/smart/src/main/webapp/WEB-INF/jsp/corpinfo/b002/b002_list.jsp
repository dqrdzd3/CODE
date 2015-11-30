<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<title>安全人员信息-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,utilJS,validatorJS,my97,grid,commonJS,tabTip)" executeResult="true" />
<script type="text/javascript">
$(function(){
	jQuery.validator.setDefaults({ 
		ignore:""
	});
	
});
	var gridOptions = [ {
		id : 'gridTable',
		caption : '安全生产组织机构',
		subGridId : [ 'gridTable1', 'gridTable2','gridTable3'],
		formId : 'searchForm',
		colModel:[
		            {label:'', name:'MA001', index:'MA001',hidden:true,hidedlg:true},
		            {label:'机构名称', name:'MA004', index:'MA004',formatter:showcontent},
					{label:'机构编号', name:'MA002', index:'MA002'},
					{label:'安全生产奖金投入', name:'MA005', index:'MA005'},
					{label:'从业人数', name:'MA006', index:'MA006'},
					{label:'参加培训人数', name:'MA007', index:'MA007'},
					{label:'缴保险人数', name:'MA008', index:'MA008'},
					{label:'是否上报', name:'MA021D', index:'MA021D'},
					{label:'是否上报', name:'MA021', index:'MA021',hidden:true,hidedlg:true}
				],
		pk : "MA001",
		winTitle : "安全生产组织机构"
	}, {
		id : 'gridTable1',
		po_pId : 'MA005',
		caption : '安全生产责任人',
		formId : 'searchForm1',
		url : 'corpinfo/b103!queryData?b103PO.MA006=1&ORGAN_UUID=${param.ORGAN_UUID}',
		colModel : [ {
			label : 'id',
			name : 'MA001',
			index : 'MA001',
			hidden:true,hidedlg:true
		},{
			label : '姓名',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent1
		}, {
			label : '性别',
			name : 'MA0161',
			index : 'MA0161'
		}, {
			label : '人员编号',
			name : 'MA002',
			index : 'MA002'
		}, {
			label : '人员类别',
			name : 'MA006',
			index : 'MA006'
		} ],
		pk : "MA001",
		rowNum : 5,
		height : 115,
		winTitle : '安全生产责任人'
	}, {
		id : 'gridTable2',
		url : 'corpinfo/b103!queryData?b103PO.MA006=2&ORGAN_UUID=${param.ORGAN_UUID}',
		po_pId : 'MA005',
		formId : 'searchForm2',
		caption : '安全生产管理人员',
		colModel : [ {
			label : 'id',
			name : 'MA001',
			index : 'MA001',
			hidden:true,hidedlg:true
		},{
			label : '姓名',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent1
		}, {
			label : '性别',
			name : 'MA0161',
			index : 'MA0161'
		}, {
			label : '人员编号',
			name : 'MA002',
			index : 'MA002'
		}, {
			label : '人员类别',
			name : 'MA006',
			index : 'MA006'
		} ],
		pk : "MA001",
		rowNum : 5,
		height : 115,
		winTitle : '安全生产管理人员信息'
	},{
		id : 'gridTable3',
		url : 'corpinfo/b103!queryData?b103PO.MA006=3&ORGAN_UUID=${param.ORGAN_UUID}',
		po_pId : 'MA005',
		formId : 'searchForm3',
		caption : '特种作业人员',
		colModel : [ {
			label : 'id',
			name : 'MA001',
			index : 'MA001',
			hidden:true,hidedlg:true
		},{
			label : '姓名',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent1
		}, {
			label : '性别',
			name : 'MA0161',
			index : 'MA0161'
		}, {
			label : '人员编号',
			name : 'MA002',
			index : 'MA002'
		}, {
			label : '人员类别',
			name : 'MA006',
			index : 'MA006'
		} ],
		pk : "MA001",
		rowNum : 5,
		height : 115,
		winTitle : '特种作业人员'
	} ];

	var actionOptions = [
			{
				id : 'operating',
				gridId : 'gridTable',
				beforeEdit : function(idArray) {
		        	var idArray=getSelectedRowID("#gridTable");
	                for(var i = 0 ; i < idArray.length ; i ++ ){
	                    if(jQuery('#gridTable').jqGrid('getCell',idArray[i]+'','MA021')!=0){
	                        showMsg("所选数据有已上报数据，不能修改，请重新选择!",4,'');
	                        return false;
	                    };
	                }
		        },
		        beforeDelete : function() {
		        	var idArray=getSelectedRowID("#gridTable");
	                for(var i = 0 ; i < idArray.length ; i ++ ){
	                    if(jQuery('#gridTable').jqGrid('getCell',idArray[i]+'','MA021')!=0){
	                        showMsg("所选数据有已上报数据，不能删除，请重新选择!",4,'');
	                        return false;
	                    };
	                }
		        },
		        afterFillForm : function(id, jsonPO, form) {
					 $('#aqglrynum').text(jsonPO.MA018);
				}
// 				beforeAdd : function(form) {
// 					jQuery("#b003MA015").removeAttr("disabled");
// 				},
// 				buttonInited : function() {
					
// 					$$.bindButton('view', function() {
// 						var i = getSelectedRowID('#gridTable');
// 						if($$.checkSelRow(1)){
							
// 							$$.openDiv('updia', '个人信息查看', $$.getContextPath()
// 									+ '/corpinfo/b003!doEdit.action', {
// 								'b003PO.MA001' : getSelectedRowID('#gridTable')
// 										+ ''
// 							}, function() {
// 								$$.clearInput('#updia');
// 								jQuery("#updia .save").hide();
// 							});
// 						}
						
// 					}, 'operating');

// 					//重新绑定修改事件
// 					$$.bindButton('edit', function() {
						
// 						if($$.checkSelRow(1)){
// 							$$.openDiv('updia', '个人信息修改', $$.getContextPath()
// 									+ '/corpinfo/b003!doEdit.action', {
// 								'b003PO.MA001' : ''+getSelectedRowID('#gridTable')
										
// 							}, function() {
								
// 							});
// 						}
					
// 					}, 'operating');
// 				}
			}, {
				id : 'operating1',
				gridId : 'gridTable1',
				beforeAdd : function(form) {
					var id = getSelectedRowID('#gridTable')[0];
				   
					if ($$.checkSelRow(1)) {
						 id = getSelectedRowID('#gridTable')[0];
						 form['b103PO.MA005'].value = id;
						 form['b103PO.MA006'].value = '1';
						 form['b103PO.MA024'].value = '1';
						return true;
					} else {
						return false;
					}

				},
				beforeEdit : function(idArray) {
		        	var id = getSelectedRowID('#gridTable')[0];
                    if(jQuery('#gridTable').jqGrid('getCell',id+'','MA021')!=0){
                        showMsg("所选数据有已上报数据，不能修改，请重新选择!",4,'');
                        return false;
                    }
		        },
				afterFillForm : function(id, jsonPO, form) {
					 form['b103PO.MA024'].value = '1';
				},
				buttonInited : function() {
					$$.bindButton('delete', function() {
						var ids = getSelectedRowID('#gridTable1');
						if(ids.length==0){
							showMsg("请选择要删除的记录",4);
							return;
						}
						var id = getSelectedRowID('#gridTable')[0];
	                    if(jQuery('#gridTable').jqGrid('getCell',id+'','MA021')!=0){
	                        showMsg("所选数据有已上报数据，不能删除，请重新选择!",4,'');
	                        return false;
	                    }
						showMsg('确定要删除所选的记录吗？', 4, {title : '消息',buttons:{'取消' : function(){
							jQuery(this).dialog("close");
			    		},'确定' : function(){
			    			jQuery.post(
			    					$$.getContextPath()+ '/corpinfo/b103!delb103',
			    					{'ids':''+ids,'ryType':'1'},
			    					function(msg){
			    						showMsg(msg.content,msg.type);
			    						reloadGrid('gridTable1');
			    					}
			    			 );
			    			}
			    		}
			    	});
					}, 'operating1');
				}
			}, {
				id : 'operating2',
				gridId : 'gridTable2',
				beforeAdd : function(form) {
					if ($$.checkSelRow(1)) {
						var id = getSelectedRowID('#gridTable')[0];
						 id = getSelectedRowID('#gridTable')[0];
						 form['b103PO.MA005'].value = id;
						 form['b103PO.MA006'].value = '2';
						 form['b103PO.MA024'].value = '2';
						return true;
					} else {
						return false;
					}

				},
				beforeEdit : function(idArray) {
		        	var id = getSelectedRowID('#gridTable')[0];
                    if(jQuery('#gridTable').jqGrid('getCell',id+'','MA021')!=0){
                        showMsg("所选数据有已上报数据，不能修改，请重新选择!",4,'');
                        return false;
                    }
		        },
				afterFillForm : function(id, jsonPO, form) {
					 form['b103PO.MA024'].value = '2';
				},
				
			buttonInited : function() {
				$$.bindButton('delete', function() {
					var ids = getSelectedRowID('#gridTable2');
					if(ids.length==0){
						showMsg("请选择要删除的记录",4);
						return;
					}
					var id = getSelectedRowID('#gridTable')[0];
                    if(jQuery('#gridTable').jqGrid('getCell',id+'','MA021')!=0){
                        showMsg("所选数据有已上报数据，不能删除，请重新选择!",4,'');
                        return false;
                    }
					showMsg('确定要删除所选的记录吗？', 4, {title : '消息',buttons:{'取消' : function(){
						jQuery(this).dialog("close");
		    		},'确定' : function(){
		    			jQuery.post(
		    					$$.getContextPath()+ '/corpinfo/b103!delb103',
		    					{'ids':''+ids,'ryType':'2'},
		    					function(msg){
		    						showMsg(msg.content,msg.type);
		    						reloadGrid('gridTable2');
		    					}
		    			 );
		    			}
		    		}
		    	});
				}, 'operating2');
			}
			
			},
			{
				id : 'operating3',
				gridId : 'gridTable3',
				beforeAdd : function(form) {
					if ($$.checkSelRow(1)) {
						var id = getSelectedRowID('#gridTable')[0];
						 id = getSelectedRowID('#gridTable')[0];
						 form['b103PO.MA005'].value = id;
						 form['b103PO.MA006'].value = '3';
						 form['b103PO.MA024'].value = '3';
						 jQuery('#ab103 #tsgz').show();
						 jQuery('#ab103 #tsgzbt').show();
						 jQuery('#ab103 #testid').show();
						 jQuery('#upload').show();
						 $("#colid").attr('colspan','2');
					     $("#img",form).attr("src","<%=path%>/upload/initfileuploadnew?MA002=");
						return true;
					} else {
						return false;
					}

				},
		       
				beforeEdit : function(idArray) {
		        	var id = getSelectedRowID('#gridTable')[0];
                    if(jQuery('#gridTable').jqGrid('getCell',id+'','MA021')!=0){
                        showMsg("所选数据有已上报数据，不能修改，请重新选择!",4,'');
                        return false;
                    }
		        },
				afterFillForm : function(id, jsonPO, form) {
					 form['b103PO.MA024'].value = '3';
					 jQuery('#ab103 #tsgz').show();
					 jQuery('#ab103 #tsgzbt').show();
					 jQuery('#ab103 #testid').show();
					 jQuery('#upload').show();
					 $("#colid").attr('colspan','2');
					 $("#img",form).attr("src","<%=path%>/upload/initfileuploadnew?MA002="+id);
				},
			buttonInited : function() {
				$$.bindButton('delete', function() {
					var ids = getSelectedRowID('#gridTable3');
					if(ids.length==0){
						showMsg("请选择要删除的记录",4);
						return;
					}
					var id = getSelectedRowID('#gridTable')[0];
                    if(jQuery('#gridTable').jqGrid('getCell',id+'','MA021')!=0){
                        showMsg("所选数据有已上报数据，不能删除，请重新选择!",4,'');
                        return false;
                    }
					showMsg('确定要删除所选的记录吗？', 4, {title : '消息',buttons:{'取消' : function(){
						jQuery(this).dialog("close");
		    		},'确定' : function(){
		    			jQuery.post(
		    					$$.getContextPath()+ '/corpinfo/b103!delb103',
		    					{'ids':''+ids,'ryType':'3'},
		    					function(msg){
		    						showMsg(msg.content,msg.type);
		    						reloadGrid('gridTable3');
		    					}
		    			 );
		    			}
		    		}
		    	});
				}, 'operating3');
			}
			} 
			];
	//关闭对话框
	function closedia() {
		jQuery('#updia').dialog('close');
	}
	function closediv(id){
		jQuery("#"+id).dialog('close');
	}
	
	function showcontent(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
	};
	function showContentById(id){
		if(id == null || id == ''){
			showMsg("至少应该选择一条记录!",5,'错误');
		}else{
			$$.openDiv('showcont','企业安全机构详情',$$.getContextPath()+'/corpinfo/b002!view',{'b002PO.MA001':id},function(){
				$$.clearInput('#showcont');
				$('#b002form #MA024').attr('disabled',false);
			});
		}
	};
	
	
// 	//修改执行 
// 	function doupd() {
// 		if(!jQuery("#updform").valid()) {
// 			showMsg("请检查数据合法性",4);
// 			return;
// 		}
// 		jQuery.post($$.getContextPath() + '/corpinfo/b003!doSaveEdit.action',
// 				jQuery('#updform').serialize(), function(msg) {
// 					showMsg(msg.content, msg.type, '');
// 					if (msg.type == 1) {
// 						closedia();
// 						reloadGrid("gridTable");
// 					}
// 				});
// 	}
	
	function closediv(id){
		jQuery("#"+id).dialog('close');
	}
// 	function showcontent(celv,opts,obj){
// 		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
// 	};
// 	function showContentById(id){
// 		if(id == null || id == ''){
// 			showMsg("至少应该选择一条记录!",5,'错误');
// 		}else{
// 			$$.openDiv('showcont','安全人员信息详情',$$.getContextPath()+'/corpinfo/b103!view',{'b103PO.MA001':id},function(){
// 				$$.clearInput('#showcont');
// 			});
// 		}
// 	};


    function showcontent1(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById1(\''+obj[0]+'\')">'+celv+'</a>';
	};
	function showContentById1(id){
		if(id == null || id == ''){
			showMsg("至少应该选择一条记录!",5,'错误');
		}else{
			$$.openDiv('showcont1','安全人员信息详情',$$.getContextPath()+'/corpinfo/b103!view',{'b103PO.MA001':id},function(){
				$$.clearInput('#showcont1');
			});
		}
	};
	

	function hwsafesbsj() {
		var ids = getSelectedRowID("#gridTable");
		if (ids.length < 1) {
			showMsg('请选中一条记录', 2);
			return;
		}
		for ( var i = 0; i < ids.length; i++) {
			var flag = jQuery('#gridTable').jqGrid('getCell', ids[i], 'MA021');
			if (flag == 0) {
				continue;
			} else {
				showMsg('提交的数据中有已经申报的数据，请重新选择!', 2);
				return false;
			}
		}
		jQuery.post($$.getContextPath() + 'corpinfo/b002!doGetUpdateSBSJ', {
			ids : '' + ids
		}, function(msg) {
			showMsg(msg.content, msg.type);
			reloadGrid('gridTable');
		});
	}
</script>
</head>
<body>
	<div>
		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight">
					<s:form  cssClass="formmargin"id="searchForm">
					<input type="hidden" name="ORGAN_UUID" value="${param.ORGAN_UUID}"/>
					<input type="hidden" name="govType" value="${param.govType}"/>
						<table border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td align="right" style="width: 100px;">安全管理机构编号</td>
								<td colspan="1" style="width: 200px;"><input
									name="b002PO.MA002" class="input2" /></td> 
								<td align="right" style="width: 100px;">安全管理机构名称</td>
								<td colspan="1" style="width: 200px;"><input 
										name="b002PO.MA004" class="input2" /></td> 
								<td width="98" rowspan="2"><s:submit value=""
										cssClass="search" /></td>
						</table>
					</s:form>
				</div>
			</div>
		</div>
		<div id="hiddlebutton">
			<a href="#"><img src="images/searchhiddleicon.jpg" width="71" height="13" /></a>
		</div>
		
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete,sbsj"/>
	</div>
		<div style="display: none;" id="updia"></div>
		<div id="showcont"></div>
		<div class="tablestyle">
			<table id="gridTable"></table>
		</div>
		<s:iterator var="menuCode" value="codes"  status="s">
			<s:set name="menuCode"  value="#menuCode" scope="page"></s:set>
		</s:iterator>
			<DIV id=con>
				<UL id=tags>
					<LI class=selectTag><A onClick="selectTag('tagContent1',this)"
						href="javascript:void(0)" onfocus="this.blur()">安全生产责任人</A></LI>
					<LI><A onClick="selectTag('tagContent2',this)"
						href="javascript:void(0)" onfocus="this.blur()">安全生产管理人员</A></LI>
					<LI><A onClick="selectTag('tagContent3',this)"
						href="javascript:void(0)" onfocus="this.blur()">特种作业人员</A></LI>
				</UL>
	<%-- 				<s:iterator var="menuCode" value="codes"  status="s"> --%>
	<%-- 					<s:if test="#s.index == 0"> --%>
	<%-- 						<DIV id="tagContent${s.index+1}" class="tagContent selectTag"> --%>
	<%-- 					</s:if> --%>
	<%-- 					<s:else> --%>
	<%-- 						<DIV id="tagContent${s.index+1}" class="tagContent"> --%>
	<%-- 					</s:else> --%>
	<%-- 				</s:iterator>	 --%>
				<DIV id=tagContent>
					<DIV id="tagContent1" class="tagContent selectTag">
						<div class="operating" id="operating1">
							<hwsoft:operation code="${menuCode }" param="add,edit,delete"/>
						</div>
						<div class="tablestyle">
							<table id="gridTable1" >
							</table>
						</div>
					</DIV>
					<DIV id="tagContent2" class="tagContent">
						<div class="operating" id="operating2">
							<hwsoft:operation code="${menuCode }" param="add,edit,delete"/>
						</div>
						<div class="tablestyle">
							<table id="gridTable2" >
							</table>
						</div>
					</DIV>
					<DIV id="tagContent3" class="tagContent">
						<div class="operating" id="operating3">
							<hwsoft:operation code="${menuCode }" param="add,edit,delete"/>
						</div>
						<div class="tablestyle">
							<table id="gridTable3" >
							</table>
						</div>
					</DIV>
				</DIV>
				</DIV>
	</div>
	<div id="showcont1"></div>
</body>
</html>