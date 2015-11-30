<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag" %>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>" />
<title>安全人员信息-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,utilJS,validatorJS,my97,grid,commonJS)" executeResult="true" />
<script type="text/javascript">
	var gridOptions = [ {
		id : 'gridTable',
		caption : '人员信息',
		subGridId : [ 'gridTable1', 'gridTable2' ],
		formId : 'searchForm',
		colModel : [ {
			label : 'id',
			name : 'MA001',
			index : 'MA001',
			hidden:true,hidedlg:true
		},{
			label : '姓名',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent
		}, {
			label : '职务',
			name : 'MA005',
			index : 'MA005'
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
		winTitle : "人员信息"
// 		loadComplete:function(){
// 			setTimeout(function(){
// 				var rows = $("#gridTable").getRowData();
// 				if(rows.length > 0){
// 					var id = rows[0].MA001||'';
// 					$("#gridTable").setSelection(id,true);
// 				}
// 			}, 50);
// 		}
	}, {
		id : 'gridTable1',
		po_pId : 'MA002',
		caption : '人员培训信息',
		url : 'corpinfo/b00301!queryData.action',
		colModel : [
		{
			label : '',
			name : 'MA001',
			index : 'MA001',
			hidden:true,
			hidedlg:true
		},
		{
			label : '培训名称',
			name : 'MA003',
			index : 'MA003',
			formatter:showcontent1
		}, {
			label : '培训机构名称',
			name : 'MA004',
			index : 'MA004'
		}, {
			label : '培训时间',
			name : 'MA007',
			index : 'MA007',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		}, {
			label : '培训内容',
			name : 'MA008',
			index : 'MA008'
		}

		],
		pk : "MA001",
		rowNum : 5,
		height : 115,
		winTitle : '人员培训信息'
	}, {
		id : 'gridTable2',
		url : 'corpinfo/b00302!queryData.action',
		po_pId : 'MA002',
		caption : '人员证书信息',
		colModel : [
		{
			label : '',
			name : 'MA001',
			index : 'MA001',
			hidden:true,
			hidedlg:true
		},
		     {
			label : '证书名称',
			name : 'MA004',
			index : 'MA004',
			formatter:showcontent2
		},  {
			label : '证书编号',
			name : 'MA003',
			index : 'MA003'
		},{
			label : '发证部门',
			name : 'MA005',
			index : 'MA005'
		}, {
			label : '发证时间',
			name : 'MA006',
			index : 'MA006',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		}, {
			label : '截至有效日期',
			name : 'MA007',
			index : 'MA007',
			formatter:'date',
			formatoptions:{newformat:'Y-m-d'}
		} ],
		pk : "MA001",
		rowNum : 5,
		height : 115,
		winTitle : '人员证书信息'
	} ];

	var actionOptions = [
			{
				id : 'operating',
				gridId : 'gridTable',
				beforeAdd : function(form) {
					jQuery("#b003MA015").removeAttr("disabled");
				},
				buttonInited : function() {
					
					$$.bindButton('view', function() {
						var i = getSelectedRowID('#gridTable');
						if($$.checkSelRow(1)){
							
							$$.openDiv('updia', '个人信息查看', $$.getContextPath()
									+ '/corpinfo/b003!doEdit.action', {
								'b003PO.MA001' : getSelectedRowID('#gridTable')
										+ ''
							}, function() {
								$$.clearInput('#updia');
								jQuery("#updia .save").hide();
							});
						}
						
					}, 'operating');

					//重新绑定修改事件
					$$.bindButton('edit', function() {
						
						if($$.checkSelRow(1)){
							$$.openDiv('updia', '个人信息修改', $$.getContextPath()
									+ '/corpinfo/b003!doEdit.action', {
								'b003PO.MA001' : ''+getSelectedRowID('#gridTable')
										
							}, function() {
								
							});
						}
					
					}, 'operating');
				}
			}, {
				id : 'operating1',
				gridId : 'gridTable1',
				beforeAdd : function(form) {
					jQuery("#MA008").removeAttr("disabled");
					var id = getSelectedRowID('#gridTable')[0];
					if ($$.checkSelRow(1)) {
						 id = getSelectedRowID('#gridTable')[0];
						var url = 'corpinfo/b003!doQueryByID';//需要修改
						//var url='occupation/h003!doQueryByID?h003PO.MA001='+id;
						$$.getPO(url, {
							'b003PO.MA001' : id
						}, function(b003PO) {
							form['b00301PO.MA002'].value = id;
						});
						return true;
					} else {
						return false;
					}

				},
				buttonInited : function() {
					$$.bindButton('delete', function() {
						var ids = getSelectedRowID('#gridTable1');
						if(ids.length==0){
							showMsg("请选择要删除的记录",4);
							return;
						}
						showMsg('确定要删除所选的记录吗？', 3, {title : '删除',buttons:{'取消' : function(){
							jQuery(this).dialog("close");
			    		},'确定' : function(){
			    			jQuery.post(
			    					$$.getContextPath()+ '/corpinfo/b00301!delb00301',
			    					{'ids':''+ids},
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
						var url = 'corpinfo/b003!doQueryByID';//需要修改
						//var url='occupation/h003!doQueryByID?h003PO.MA001='+id;
						$$.getPO(url, {
							'b003PO.MA001' : id
						}, function(h003PO) {
							form['b00302PO.MA002'].value = id;
						});
						return true;
					} else {
						return false;
					}

				},
			buttonInited : function() {
				$$.bindButton('delete', function() {
					var ids = getSelectedRowID('#gridTable2');
					if(ids.length==0){
						showMsg("请选择要删除的记录",4);
						return;
					}
					showMsg('确定要删除所选的记录吗？', 3, {title : '删除',buttons:{'取消' : function(){
						jQuery(this).dialog("close");
		    		},'确定' : function(){
		    			jQuery.post(
		    					$$.getContextPath()+ '/corpinfo/b00302!delb00302',
		    					{'ids':''+ids},
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
			
			} ];
	//关闭对话框
	function closedia() {
		jQuery('#updia').dialog('close');
	}
	function closediv(id){
		jQuery("#"+id).dialog('close');
	}
	//修改执行 
	function doupd() {
		if(!jQuery("#updform").valid()) {
			showMsg("请检查数据合法性",4);
			return;
		}
		jQuery.post($$.getContextPath() + '/corpinfo/b003!doSaveEdit.action',
				jQuery('#updform').serialize(), function(msg) {
					showMsg(msg.content, msg.type, '');
					if (msg.type == 1) {
						closedia();
						reloadGrid("gridTable");
					}
				});
	}
	
	function showcontent(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
	};
	function showContentById(id){
		if(id == null || id == ''){
			showMsg("至少应该选择一条记录!",5,'错误');
		}else{
			$$.openDiv('showcont','安全人员信息详情',$$.getContextPath()+'/corpinfo/b003!view',{'b003PO.MA001':id},function(){
				$$.clearInput('#showcont');
			});
		}
	};
	function showcontent1(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById1(\''+obj[0]+'\')">'+celv+'</a>';
	};
	function showContentById1(id){
		if(id == null || id == ''){
			showMsg("至少应该选择一条记录!",5,'错误');
		}else{
			$$.openDiv('showcont','人员培训信息详情',$$.getContextPath()+'/corpinfo/b00301!view',{'b00301PO.MA001':id},function(){
				$$.clearInput('#showcont');
				$('#b00301addform #MA008').attr('disabled',false);
			});
		}
	};
	function showcontent2(celv,opts,obj){
		return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById2(\''+obj[0]+'\')">'+celv+'</a>';
	};
	function showContentById2(id){
		if(id == null || id == ''){
			showMsg("至少应该选择一条记录!",5,'错误');
		}else{
			$$.openDiv('showcont','人员证书信息详情',$$.getContextPath()+'/corpinfo/b00302!view',{'b00302PO.MA001':id},function(){
				$$.clearInput('#showcont');
			});
		}
	};
</script>
</head>
<body>
	<div>
		<div id="searcharea">
			<div id="searcharealeft">
				<div id="searcharearight">
					<s:form action="b003!query" method="post" cssClass="formmargin"
						id="searchForm">
						<s:hidden name="b003PO.MA006"/>
						<s:hidden name="b003PO.MA003"/>
						<table border="0" cellspacing="8" cellpadding="0">
							<tr>
								<td align="right" style="width: 50px;">人员编号</td>
								<td colspan="1" style="width: 200px;"><s:textfield
										name="b003PO.MA002" cssClass="input2" /></td>
								<td align="right" style="width: 30px;">姓名</td>
								<td colspan="1" style="width: 200px;"><s:textfield
										name="b003PO.MA004" cssClass="input2" /></td>
								<td width="98" rowspan="2"><s:submit value=""
										cssClass="search" /></td>
						</table>
					</s:form>
				</div>
			</div>
		</div>
		<div id="hiddlebutton">
			<a href="#"><img src="images/searchhiddleicon.jpg" width="71"
				height="13" /></a>
		</div>
		
	<div class="operating" id="operating">
		<hwsoft:operation code="${code}" param="add,edit,delete"/>
	</div>
		<div style="display: none;" id="updia"></div>
		<div id="showcont"></div>
		<div class="tablestyle">
			<table id="gridTable"></table>
		</div>
		<DIV id=con>
			<UL id=tags>
				<LI class=selectTag><A onClick="selectTag('tagContent1',this)"
					href="javascript:void(0)" onfocus="this.blur()">人员培训信息</A></LI>
				<LI><A onClick="selectTag('tagContent2',this)"
					href="javascript:void(0)" onfocus="this.blur()">人员证书信息</A></LI>
			</UL>
			<DIV id=tagContent>
			<s:iterator var="menuCode" value="codes"  status="s">
				<s:if test="#s.index == 0">
					<DIV id="tagContent${s.index+1}" class="tagContent selectTag">
				</s:if>
				<s:else>
					<DIV id="tagContent${s.index+1}" class="tagContent">
				</s:else>
						<div class="operating" id="operating${s.index+1}">
							<hwsoft:operation code="${menuCode}" param="add,edit,delete"/>
						</div>
						<div class="tablestyle">
							<table id="gridTable${s.index+1}" >
							</table>
						</div>
					</DIV>
			</s:iterator>	
			</DIV>
		</DIV>
	</div>
</body>
</html>