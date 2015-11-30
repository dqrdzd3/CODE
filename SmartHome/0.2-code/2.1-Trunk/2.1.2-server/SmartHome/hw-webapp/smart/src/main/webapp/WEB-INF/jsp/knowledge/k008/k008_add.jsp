<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<s:url value="/" forceAddSchemeHostAndPort="true"/>"/>
<title><s:text name="法律法规" />-<s:text name="application.title" /></title>
<s:action name="include(mainCSS,jquery,my97,validatorJS,utilJS,grid,commonJS)" executeResult="true"/>
<script type="text/javascript">
$(function(){
	$("#chapter").bind('click',$.k008.addChapter);
});
</script>
<script type="text/javascript">
$.k008 = $.k008 || {};
$.k008={
		delContent:function(num,count){
			var $oZWTb = $("#zwTb"+num);
			$oZWTb.find('tr[id=zwTb'+num+'_contentTr'+count+']').empty();
		},
		delChapter:function(num){
			var oFlfgTb = $("#flfgTb");
			oFlfgTb.find('tr[id=chapterTr'+num+']').empty();
		},
		addChapterContent:function (num){
			var $oZWTb = $("#zwTb"+num);
			var count = $oZWTb.find('tr:first').siblings().length;
			$oZWTb.append('<tr id="zwTb'+num+'_contentTr'+count+'"><td colspan="5"><table  style="width: 100%;"><tr><th scope="col"><span>*</span>条数</th><td><input type="text" id="zwTb'+num+'_content'+count+'"  name="k008PO.k00801List['+num+'].k0080101List['+count+'].ma005" class="required {maxlength:20,minlength:0}" /></td><th scope="col"><span>*</span>法律内容</th><td><textarea id="zwTb'+num+'_content_val'+count+'" name="k008PO.k00801List['+num+'].k0080101List['+count+'].ma006" class="required {maxlength:200,minlength:0}"></textarea><input type="hidden" name="k008PO.k00801List['+num+'].k0080101List['+count+'].ma004" value="'+count+'" /></td><td><input id="delContent" name="delContent" value="删除本内容" onclick="$.k008.delContent('+num+','+count+');" type="button"></input></td></tr></table></td></tr>');
		},
		addChapter:function (){
			var oFlfgTb = $("#flfgTb");
			var num = oFlfgTb.find('tr:first').siblings().length;
			oFlfgTb.append('<tr id="chapterTr'+num+'"><td colspan="9"><table id="zwTb'+num+'"style="width: 100%;"><tr><th scope="col"><span>*</span>章节数</th><td><input type="text" id="chapter'+num+'"  name="k008PO.k00801List['+num+'].ma004" class="required {maxlength:20,minlength:0}" /></td><th scope="col"><span>*</span>章节名称</th><td><textarea id="chapter_val'+num+'" name="k008PO.k00801List['+num+'].ma003" class="required {maxlength:200,minlength:0}" ></textarea><input type="hidden" name="k008PO.k00801List['+num+'].ma007" value="'+num+'" /></td><td><input id="delChapter" name="delChapter" value="删除本章" onclick="$.k008.delChapter('+num+');" type="button"></input><input id="addContent" name="addContent" value="增加内容" onclick="$.k008.addChapterContent('+num+');" type="button"></input></td></tr></table></td></tr>');
		},
		saveAdd:function(){
			if($('#k008').valid()){
				var param = $('#k008').serialize();
				var url = $$.getContextPath()+'knowledge/k008!doSaveAdd';
				$.post(url,param,function(data){
					showMsg(data.content, data.type, {
			  		buttons:{
			  			'确定' : function(){
			  				$(this).dialog('close');
			  				if(data.type == 1){
			  					$$.getFrame('rightFrame').Tabs.closeSel();		  					
			  				}
			  			}}
			   		});
				});
			};
		}
		
};

</script>
</head>
<body>
	<div id="windows">
		<div id="windowstop">
			<div id="windowstopleft">
				<ul>
					<li>法律法规</li>
				</ul>
			</div>
		</div>
		<s:form action="k008" method="post" id="k008">
			<div id="windowsmain">
				<div id="windowdiv">
					<div id="windowdivmain">
						<table id="flfgTb" class="windowdivmaintable">
							<tr style="width: 100%;">
								<th scope="col"><span>*</span>法规名称</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma002" id="ma002"
									class="required {maxlength:200,minlength:0}" /></td>
								<th scope="col"><span>*</span>法规编号</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma003" id="ma003"
									class="required {maxlength:20,minlength:0}" /></td>
								<th scope="col"><span>*</span>法规类别</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma004" id="ma004"
									class="required {maxlength:200,minlength:0}" /></td>
								<th scope="col"><span>*</span>颁布机构</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma007" id="ma007"
									class="required {maxlength:200,minlength:0}" /></td>
								<td><input type="button" value="新增章节" id="chapter" /></td>
							</tr>
						</table>
					</div>
				</div>
			</div>

			<div id="windowsbottom">
				<div id="windowsbottomleft">
					<div id="windowsbottomright">
						<ul>
							<li class="save"><input type="button" class="save"
								style="border: none;" id="saveBtn" onclick="$.k008.saveAdd();" /></li>
							<li>( 说明：<span>*</span>号位必填项)
							</li>
						</ul>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>
