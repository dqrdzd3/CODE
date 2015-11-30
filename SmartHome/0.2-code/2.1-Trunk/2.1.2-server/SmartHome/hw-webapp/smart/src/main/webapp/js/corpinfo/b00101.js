function showcontent(celv,opts,obj){
	return '<a href="javascript:;" style="text-decoration: none;" onclick="showContentById(\''+obj[0]+'\')">'+celv+'</a>';
}
/**
 * 根据ＩＤ，查询企业的详细信息，，弹出企业的详细信息列表
 * @param id
 */
function showContentById(id){
//	$$.openDiv('showcontent','企业信息详情',$$.getContextPath()+'corpinfo/b001!doGetB001Details',{'b001PO.MA001':id});
//	$$.getTabsBar().openTab(null, '企业信息详情', $$.getContextPath()+'corpinfo/b001!doViewCorp?b001PO.MA001='+id);
	$$.getTabsBar().openTab('tabid_qyxxck', '企业信息详情', $$.getContextPath()+'corpreport/govcheckreport?corpID='+id, '', true); // code=ZF_QYJG_WHPQY_QYSBXX
}

//关闭GIS标注的TAb，因为标注GIS 的TAb的id是mapgiseditid，所以是指定了
function closeGisEditTab(states)
{
	if(states==0){
		alert("标注成功，请刷新列表！");
	}
	else{
		alert("标注失败!");
	}
	$$.getFrame('rightFrame').Tabs.closeTab($$.getFrame('rightFrame').Tabs.getTabIndex($$.getFrame('rightFrame').Tabs.getTabById('mapgiseditid')));
}
/**
 * 根据ＩＤ，点击地图上企业图标，弹出企业的详细信息列表
 * @param id
 */
function showCorpInfoById(id){
	$$.getFrame('rightFrame').Tabs.openTabMap("qyInfo", "企业详细信息",
			"cpnysupervision/gov001!doView?b001PO.MA001="+ id , null, true);
	
}
/**
 * 根据类型和featureＤ，查询企业的详细信息，点击地图上图标，弹出要素的详细信息列表
 * @param id
 */
function showInfoById(type,id){
	if(type=='CORP'){
		//企业的相关信息比较多，所以企业信息是弹出一个tab标签页，而不是在div中展示
		$$.getFrame('rightFrame').Tabs.openTabMap("qyInfo", "企业详细信息",
				"cpnysupervision/gov001!doView?b001PO.MA001="+ id , null, true);
		return;
	}
	if(type=='WXY_B'){
		$$.openDiv('showcontent','储罐区信息详情',$$.getContextPath()+'danger/b01001!edit',{'b01001PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_C'){
		$$.openDiv('showcontent','库区信息详情',$$.getContextPath()+'danger/b01002!edit',{'b01002PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_D'){
		$$.openDiv('showcontent','生产场所信息详情',$$.getContextPath()+'danger/b01003!edit',{'b01003PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_H'){
		$$.openDiv('showcontent','压力管道信息详情',$$.getContextPath()+'danger/b01004!edit',{'b01004PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_G'){
		$$.openDiv('showcontent','锅炉信息详情',$$.getContextPath()+'danger/b01005!edit',{'b01005PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_F'){
		$$.openDiv('showcontent','压力容器信息详情',$$.getContextPath()+'danger/b01006!edit',{'b01006PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_K'){
		$$.openDiv('showcontent','煤矿信息详情',$$.getContextPath()+'danger/b0100701!edit',{'b0100701PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_P'){
		$$.openDiv('showcontent','金属非金属信息详情',$$.getContextPath()+'danger/b0100801!edit',{'b0100801PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WXY_N'){
		$$.openDiv('showcontent','尾矿库信息详情',$$.getContextPath()+'danger/b01009!edit',{'b01009PO.MA001':id},function(){
			$$.clearInput('#showcontent');
			$('#showcontent .cancel').hide();
			$('#showcontent .save').hide();
			
		});
		return;
	}
	if(type=='WHP'){
		$$.openDiv('showcontent','危化品信息详情',$$.getContextPath()+'corpinfo/b012!doView.action',{'b012PO.MA001':id},function(){
			$('#showcontent .cancel').hide();
		});
		return;
	}
	if(type=='ENV'){
		$$.openDiv('showcontent','周边环境信息详情',$$.getContextPath()+'corpinfo/b006!doView',{'b006PO.ma001':id},function(){
			$('#showcontent .cancel').hide();
		});
		return;
	}
	if(type=='WZZB'){
		$$.openDiv('showcontent','物资装备信息详情',$$.getContextPath()+'resource/r002!doGetShowById',{'r002PO.ma001':id},function(){
			$('#showcontent .back').hide();
		});
		return;
	}
	if(type=='YJZJ'){
		$$.openDiv('showcontent','应急专家信息详情',$$.getContextPath()+'resource/r003!doGetShowById',{'r003PO.ma001':id},function(){
			$('#showcontent .back').hide();
		});
		return;
	}
	if(type=='YJDW'){
		$$.openDiv('showcontent','应急队伍信息详情',$$.getContextPath()+'resource/r004!doGetShowById',{'r004PO.ma001':id},function(){
			$('#showcontent .back').hide();
		});
		return;
	}
	if(type=='BNCS'){
		$$.openDiv('showcontent','避难场所信息详情',$$.getContextPath()+'resource/r005!doGetShowById',{'r005PO.ma001':id},function(){
			$('#showcontent .back').hide();
		});
		return;
	}
	if(type=='JYCL'){
		$$.openDiv('showcontent','救援车辆信息详情',$$.getContextPath()+'resource/r006!doGetShowById',{'r006PO.ma001':id},function(){
			$('#showcontent .back').hide();
		});
		return;
	}
}

