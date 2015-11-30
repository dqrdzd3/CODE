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
	$("#chapter").bind('click',$.k008edit.addChapter);
});
</script>
<script type="text/javascript">
$.k008edit = $.k008edit || {};
$.k008edit={
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
			$oZWTb.append('<tr id="zwTb'+num+'_contentTr'+count+'"><td colspan="5"><table  style="width: 100%;"><tr><th scope="col"><span>*</span>条数</th><td><input id="zwTb'+num+'_content'+count+'" value="" name="k008PO.k00801List['+num+'].k0080101List['+count+'].ma005"/></td><th scope="col"><span>*</span>法律内容</th><td><textarea id="zwTb'+num+'_content_val'+count+'" name="k008PO.k00801List['+num+'].k0080101List['+count+'].ma006"></textarea></td><td><input id="delContent" name="delContent" value="删除本内容" onclick="$.k008edit.delContent('+num+','+count+');" type="button"></input></td></tr></table></td></tr>');
		},
		addChapter:function (){
			var oFlfgTb = $("#flfgTb");
			var num = oFlfgTb.find('tr:first').siblings().length;
			oFlfgTb.append('<tr id="chapterTr'+num+'"><td colspan="3"><table id="zwTb'+num+'"style="width: 100%;"><tr><th scope="col"><span>*</span>章节数</th><td><input id="chapter'+num+'"  name="k008PO.k00801List['+num+'].ma004"/></td><th scope="col"><span>*</span>章节名称</th><td><textarea id="chapter_val'+num+'" name="k008PO.k00801List['+num+'].ma003"></textarea></td><td><input id="delChapter" name="delChapter" value="删除本章" onclick="$.k008edit.delChapter('+num+');" type="button"></input><input id="addContent" name="addContent" value="增加内容" onclick="$.k008edit.addChapterContent('+num+');" type="button"></input></td></tr></table></td></tr>');
		},
		saveEdit:function(){
			if($('#k008edit').valid()){
				var Id = $("#ma001").val();
				var param = $('#k008edit').serialize();
				var url = $$.getContextPath()+'knowledge/k008!doSaveEdit';
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
		},
		close:function(){
			$$.getFrame('rightFrame').Tabs.closeSel();
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
		<s:form action="k008" method="post" id="k008edit">
		<input type="hidden" id="ma001" name="k008PO.ma001" value="${k008PO.ma001}" />
			<div id="windowsmain">
				<div id="windowdiv">
					<div id="windowdivmain">
						<table id="flfgTb" class="windowdivmaintable">
							<tr style="width: 100%;">
								<th scope="col"><span>*</span>法规名称</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma002" value="${k008PO.ma002 }" id="ma002"
									class="required {maxlength:200,minlength:0}" /></td>
								<th scope="col"><span>*</span>法规编号</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma003" value="${k008PO.ma003 }" id="ma003"
									class="required {maxlength:20,minlength:0}" /></td>
								<th scope="col"><span>*</span>法规类别</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma004" value="${k008PO.ma004 }" id="ma004"
									class="required {maxlength:200,minlength:0}" /></td>
								<th scope="col"><span>*</span>颁布机构</th>
								<td class="tb_input" scope="col" ><input 
									type="text" name="k008PO.ma007" value="${k008PO.ma007 }" id="ma007"
									class="required {maxlength:200,minlength:0}" /></td>	
								<td><input type="button" value="新增章节" id="chapter" /></td>
							</tr>
	<s:iterator var="chap"  value="k008PO.k00801List" status="s" >		
	<tr id="chapterTr${s.index}">
        <td colspan="9">
            <table id="zwTb${s.index}" style="width: 100%;">
                <tbody>
                <tr><th scope="col"><span>*</span>章节数</th>
                    <td><input id="chapter1" type="text" name="k008PO.k00801List[<s:property value="#s.index"/>].ma004" value="${chap.ma004}" class="required {maxlength:20,minlength:0}"  ></td>
                    <th scope="col"><span>*</span>章节名称</th>
                    <td><textarea id="chapter_val1" name="k008PO.k00801List[<s:property value="#s.index"/>].ma003"  class="required {maxlength:200,minlength:0}" >${chap.ma003}</textarea></td>
                    <td><input id="delChapter" name="delChapter" value="删除本章" onclick="$.k008edit.delChapter(<s:property value="#s.index"/>);" type="button">
                        <input id="addContent" name="addContent" value="增加内容" onclick="$.k008edit.addChapterContent(<s:property value="#s.index"/>);" type="button"></td>
                </tr>
                <s:iterator var ="cont" value="%{k008PO.k00801List[#s.index].k0080101List}" status="ss">
                <tr id="zwTb${s.index}_contentTr${ss.index}">
                    <td colspan="5">
                        <table style="width: 100%;">
                            <tbody>
                            <tr>
                                <th scope="col"><span>*</span>条数</th>
                                <td><input id="zwTb1_content0" type="text" class="required {maxlength:20,minlength:0}"  value="${cont.ma005 }" name="k008PO.k00801List[<s:property value="#s.index"/>].k0080101List[<s:property value="#ss.index"/>].ma005"></td>
                                <th scope="col"><span>*</span>法律内容</th>
                                <td><textarea id="zwTb1_content_val0" class="required {maxlength:200,minlength:0}"  name="k008PO.k00801List[<s:property value="#s.index"/>].k0080101List[<s:property value="#ss.index"/>].ma006">${cont.ma006}</textarea></td>
                                <td><input id="delContent" name="delContent" value="删除本内容" onclick="$.k008edit.delContent(<s:property value="#s.index"/>,<s:property value="#ss.index"/>);" type="button"></td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr> 
                </s:iterator>
                </tbody>
            </table>
        </td>
    </tr>
    </s:iterator>		
    
						</table>
					</div>
				</div>
			</div>

			<div id="windowsbottom">
				<div id="windowsbottomleft">
					<div id="windowsbottomright">
						<ul>
				 			<li class="cancel" ><input type="button" class="back"  id="backBtn" onclick="$.k008edit.close();" /></li> 
							<li class="save"><input type="button" class="save"  id="saveBtn" onclick="$.k008edit.saveEdit();"/></li>
							<li>( 说明：<span>*</span>号位必填项)</li>
						</ul>
					</div>
				</div>
			</div> 
		</s:form>
	</div>
</body>
</html>
