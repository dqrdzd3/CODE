<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="hwsoft" uri="http://www.hanwei.com/tag"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html lang="zh">
<head>
<title>帮助中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<s:action name="include(mainCSS,commonJS,jqueryJS,bootstrap)"
	executeResult="true" />
<!-- js脚本 -->
<script type="text/javascript">
	//单击tab页时切换内容
	function switchTab(ProTag, ProBox) {
		for (i = 1; i < 5; i++) {
			if ("tab" + i == ProTag) {
				document.getElementById(ProTag).getElementsByTagName("a")[0].className = "on";
			} else {
				document.getElementById("tab" + i).getElementsByTagName("a")[0].className = "";
			}
			if ("con" + i == ProBox) {
				document.getElementById(ProBox).style.display = "";
			} else {
				document.getElementById("con" + i).style.display = "none";
			}
		}
	}
</script>
<!-- css样式 -->
<style type="text/css">
/* a {
	text-decoration: underline;
} */

* {
	padding: 0;
	margin: 0;
	line-height: 25px;
	font-size: 12px;
	list-style: none
}

#tabContainer li {
	float: left;
	width: 80px;
	margin: 0 3px;
	background: #efefef;
	text-align: center;
}

#tabContainer a {
	display: block;
}

#tabContainer a.on {
	background: pink;
}
</style>
</head>
<body style="margin-left: 10px">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div id="tabs-60741" class="tabbable">
					<ul class="nav nav-tabs">
						<li ><a href="#panel-309407" data-toggle="tab">常见问题</a>
						</li>
						<li class="active"><a href="#panel-502549" data-toggle="tab">在线说明书</a></li>
					</ul>
					<div class="tab-content">
						<div id="panel-309407" class="tab-pane ">
							<p>
								<s:if test="#request.qaList!=null">
									<s:if test="#request.qaList.size!=0">
										<s:iterator value="#request.qaList" id="column">  
						                      Q: <s:property value="#column.MA002" />
											<br>     
						                      A: <s:property value="#column.MA003" />
											<br>
										</s:iterator>
									</s:if>
									<s:else>
										<div>还没有问题呢</div>
									</s:else>
								</s:if>
							</p>
						</div>
						<div id="panel-502549"  class="tab-pane active">
							<div id="tabContainer">
								<ul style="margin-left: 0px">
									<li id="tab1"><a href="#" class="on"
										onclick="switchTab('tab1','con1');this.blur();return false;">
											空气电台app</a></li>
									<li id="tab2"><a href="#"
										onclick="switchTab('tab2','con2');this.blur();return false;">
											可燃气体检测</a></li>
									<li id="tab3"><a href="#"
										onclick="switchTab('tab3','con3');this.blur();return false;">
											空气质量检测</a></li>
									<li id="tab4"><a href="#"
										onclick="switchTab('tab4','con4');this.blur();return false;">
											E1(酒精)</a></li>
									<br>
								</ul>
								<div style="clear: both"></div>
								<!-- tab1 -->
								<div id="con1">
									<div class='container-fluid'>
										<div class='row-fluid'>
											<div class='span12'>
												<!-- 图片-->
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/a1/help_app_1.jpg' /><br> <br>
												<!-- 内容 -->
												<font style="font-weight: bold;">注册传感器步骤如下：</font> <br>
												1.点击二维码图标，通过扫描传感器二维码，可以轻松扫出传感器的编号，当然也可以手工录入二维码编号。<br>
												2.选择WiFi账号，并录入正确的WiFi密码。<br> 3.点击“注册设备”，即可完成设备的注册。<br>
												<br>

												<!-- 图片 -->
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/a1/help_app_1.png' /><br> <br>
												<!-- 内容-->
												<font style="font-weight: bold;">主界面介绍：</font> <br>
												1.在主界面的左上角，往右斜下方向轻轻滑动，显示左菜单栏；在主界面的右上角，往左斜下方向轻轻滑动，显示右菜单栏。<br>
												   <label style="margin-left: 10px">a.左菜单栏显示功能有：主界面、讨论区、用户反馈、注销登录。</label> 
												   <label style="margin-left: 10px">b.右菜单栏显示功能有：配置传感器和配置中心。点击“配置中心”显示各个配置项。</label> 
												2.若注册两个或多个传感器设备时，可以通过左右滑动切换传感器的实时数据和历史数据。<br>
												3.在主界面的底端，往上滑动显示历史数据，历史数据是以曲线图形式展示各种气体在过去7天当中每日气体浓度的最大值。 <br>
												<br>  
												
											</div>
										</div>
									</div>
								</div>
								<!-- tab2 -->
								<div id="con2" style="display: none">
									<div class='container-fluid'>
										<div class='row-fluid'>
											<div class='span12'>
												<!-- 图片 -->
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/r1/r1_1.png' /><br> <br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/r1/r1_2.png' /><br> <br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/r1/r1_3.png' /><br> <br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/r1/r1_4.png' /><br> <br>			
												<!-- 内容 -->
												<!--<font style="font-weight: bold;">注册传感器步骤如下：</font> <br>
												1.点击二维码图标，通过扫描传感器二维码，可以轻松扫出传感器的编号，当然也可以手工录入二维码编号。<br>
												2.选择WiFi账号，并录入正确的WiFi密码。<br> 3.点击“注册设备”，即可完成设备的注册。<br>
												<br> <font style="font-weight: bold;">传感器历史数据：</font><br>
												以曲线图形式清晰直观的显示过去7天当中每日气体浓度的最大值。-->
											</div>
										</div>
									</div>
								</div>
								<!-- tab3 -->
								<div id="con3" style="display: none">
									<div class='container-fluid'>
										<div class='row-fluid'>
											<div class='span12'>
												<!-- 图片
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/daily.jpg' /><br> <br> -->
												<!-- 内容
												<font style="font-weight: bold;">注册传感器步骤如下：</font> <br>
												1.点击二维码图标，通过扫描传感器二维码，可以轻松扫出传感器的编号，当然也可以手工录入二维码编号。<br>
												2.选择WiFi账号，并录入正确的WiFi密码。<br> 3.点击“注册设备”，即可完成设备的注册。<br>
												<br> <font style="font-weight: bold;">传感器历史数据：</font><br>
												以曲线图形式清晰直观的显示过去7天当中每日气体浓度的最大值。 -->
												
												<!-- 图片 -->
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/a1/a1_1.png' /><br> <br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/a1/a1_2.png' /><br> <br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/a1/a1_3.png' /><br> <br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/a1/a1_4.png' /><br> <br>		
											</div>
										</div>
									</div>
								</div>
								<!-- tab4 -->
								<div id="con4" style="display: none">
									<div class='container-fluid'>
										<div class='row-fluid'>
											<div class='span12'>
											 <br>
												<!-- 内容 -->
												<font style="font-weight: bold;">一、扫描二维码下载E1 APP：</font> <br>
												根据您的手机系统选择版本下载。<br>
												<br><img class='img-rounded' alt='140x140'
													style="width: 183px; height: 181px"
													src='<%=basePath%>images/help/e1/e1_ewm.PNG' />
												<br>	
												<font style="font-family:arial;font-color:#252525;font-size:10px;">如果是Android设备，请确认您的手机是威豆支持的型号。</font>
												<br>
												 <font style="font-weight: bold;">二、安装E1 APP：</font><br>
												1、	进入APP，将E1插入您手机的耳机口。
												<br>	
												<img class='img-rounded' alt='140x140'
													style="width: 220px; height: 320px"
													src='<%=basePath%>images/help/e1/e1_style.PNG' />
												<br>											
												2、	E1在适配耳机口后自动进行启动45s和预热45s倒计时。
												<br>
													<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_yr.png' />
												<br>
												3、	如果您通过手机防火墙拒绝了APP对录音口的访问权限，则”E1 APP”不能正常工作。
												<br>
												 <font style="font-weight: bold;">三、使用E1 APP检测：</font><br>
											    1、	倒计时完毕后，进入<font style="font-weight: bold;">准备</font>测试界面。
												<br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_start.png' />
												<br>
												2、点击<font style="font-weight: bold;">”开始”</font>进入测试界面
												<br>		
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_cq.png' />
												<br>
												吹起界面
												<br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_fx.png' />
												<br>
												等待结果界面
												<br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_12.png' />
												<br>
												显示结果界面
												<br>						
												3、等待<font style="font-weight: bold;">脱附</font>结束后可以点击<font style="font-weight: bold;">”开始”</font>进行下次测试
												<br>
												<img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_next.png' />
												<br>
												4、显示结果的单位可以进行设置
												<br>
												 <font style="font-weight: bold;">四、规格参数:</font><br>
												 <img class='img-rounded' alt='140x140'
													style="width: 200px; height: 350px"
													src='<%=basePath%>images/help/e1/e1_dw.png' />
												<br>
												1、   产品尺寸
												<br>	
												 <img class='img-rounded' alt='140x140'
													style="width: 250px; height: 170px"
													src='<%=basePath%>images/help/e1/e1_size.png' />
												<br>							
												2、	产品特征
												<br>	
													•扩展智能手机功能的呼出气体酒精检测仪；	<br>	
													•采用智能、精准、可靠的电化学传感器；	<br>	
													•无需外部电源，插入耳机孔直接工作；	<br>	
													•小巧灵动，使用简单，即插即用。
									
												<br>
												3、	测量范围：0~2.0mg/L；	<br>	
													精度范围：0.001mg/L。	<br>	

												<br>								
												4、	工作寿命	<br>	
													3年	<br>	

												<br>
												5、	使用环境	<br>	
													工作温度：-10℃ ~ 50℃	<br>	
													工作湿度：15% ~ 90% RH无凝结	<br>	
												<br>
										 <img class='img-rounded' alt='140x140'
													style="width: 200px; height: 200px"
													src='<%=basePath%>images/help/e1/e1_corp_ewm.png' />
												<br>
												公司：北京威果智能科技有限公司	<br>	
												传真：010-63703302	<br>	
												消费者服务热线：010-84872739   13598098836	<br>	
												地址：北京朝阳区慧忠里103洛克时代中心B座15A08	<br>	
											
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>

</html>