<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
									type="text" name="k008PO.ma002" value="${k008PO.ma002 }" id="ma004"
									class="required {maxlength:30,minlength:0}" /></td>
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
								
							</tr>
	<s:iterator var="chap"  value="k008PO.k00801List" status="s" >		
	<tr id="chapterTr${s.index}">
        <td colspan="9">
            <table id="zwTb${s.index}" style="width: 100%;">
                <tbody>
                <tr><th scope="col"><span>*</span>章节数</th>
                    <td><input id="chapter1" name="k008PO.k00801List[<s:property value="#s.index"/>].ma004" value="${chap.ma004}"></td>
                    <th scope="col"><span>*</span>章节名称</th>
                    <td><textarea id="chapter_val1" name="k008PO.k00801List[<s:property value="#s.index"/>].ma003">${chap.ma003}</textarea></td>
                    
                </tr>
                <s:iterator var ="cont" value="%{k008PO.k00801List[#s.index].k0080101List}" status="ss">
                <tr id="zwTb${s.index}_contentTr${ss.index}">
                    <td colspan="5">
                        <table style="width: 100%;">
                            <tbody>
                            <tr>
                                <th scope="col"><span>*</span>条数</th>
                                <td><input id="zwTb1_content0" value="${cont.ma005 }" name="k008PO.k00801List[<s:property value="#s.index"/>].k0080101List[<s:property value="#ss.index"/>].ma005"></td>
                                <th scope="col"><span>*</span>法律内容</th>
                                <td><textarea id="zwTb1_content_val0" name="k008PO.k00801List[<s:property value="#s.index"/>].k0080101List[<s:property value="#ss.index"/>].ma006">${cont.ma006}</textarea></td>
                                
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
				 			<li class="cancel" ><input type="button" class="back"  id="backBtn" onclick="closeshowd" /></li> 
						</ul>
					</div>
				</div>
			</div> 
		</s:form>
	</div>
