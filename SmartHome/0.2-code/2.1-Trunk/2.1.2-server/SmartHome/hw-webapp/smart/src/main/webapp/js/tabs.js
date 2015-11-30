/*
 * 多标签页（使用easyui的tab控件，版本1.2.6），其中标签页面使用iframe形式，避免js冲突等问题
 */ 
function EUTabs(id, frameCls, contentTpl) {
	
	var idSltr = "#" + id,
		tabs,
		frameCls = frameCls || 'tabPanelIFrame',
		frameSltr = "." + frameCls,
		contentTpl = contentTpl || '<iframe name="#name#" src="#url#" class="' + frameCls + '" frameborder="0"></iframe>',
		homePageId = "homePage",
		defHomePageIconCls = 'easyui-icon-homepage',
		homePageTableId = "welPicTable",
		miniRefreshIconCls = 'icon-mini-refresh';
	
	
	function tabs() {
		return $(idSltr);
	}	
		
	// 获取指定id的tab
	function getTabById(id){
		var tab = null;
		$.each(tabs().tabs("tabs"),function(){
			if (this.panel("options").id == id) tab = this;
		});
		return tab;
	};
	
	// 更新标签
	// 参数： tab:标签 , opt:标签选项
	function updateTab(tab,opt) {
		tabs().tabs("update", {
			tab:tab,
			options:opt
		});
	};
	
	// 刷新标签
	// 参数： idOrTab:标签的id或标签对象
	function refreshTab(idOrTab) {
		var tab = typeof idOrTab == "string" ? getTabById(idOrTab) : idOrTab;
		if (tabs().tabs("getTabIndex", tab) == 0) return;
	//	updateTab(tab, {content:contentTpl.replace("#url#",tab.panel("options").content.match(/src="(.*?)"/i)[1])});
		
		// 20150521 update by mfb: 修改刷新方法，兼容IE6
		var name = typeof idOrTab === "string" ? idOrTab : tab.panel("options").id;
		window.frames[name].location.reload();
	};
	
	// 刷新标签
	// 参数： idOrTab:标签的id或标签对象
	//2013年5月7日，参数：newUrl，如果传递的有这个参数，则重新请求，否则不变，用于列表标注后定位，重新发出请求
	function refreshTabMap(idOrTab, newUrl) {
		var tab = typeof idOrTab == "string" ? getTabById(idOrTab) : idOrTab;
		if (tabs().tabs("getTabIndex", tab) == 0) return;
		updateTab(tab, {content: contentTpl.replace("#url#", newUrl ? newUrl : tab.panel("options").content.match(/src="(.*?)"/i)[1])});
	};
	
	// 重置标签页iframe的大小
	function resizeTabs() {
		var width = $("body").width(),
			height = $("body").height();
		$(frameSltr).width(width < 500 ? 500 : width).height(height - 24 < 0 ? 0 : height - 24);
		$("#" + homePageTableId).height(height - 24 < 0 ? 0 : height - 24);
		
		tabs().tabs("resize");
	};
	
	
	
	
	this.id = id;
	this.idSltr = idSltr;
	this.frameCls = frameCls;
	this.contentTpl = contentTpl;
	
	this.tabs = tabs;
	this.getTabById = getTabById;
	this.updateTab = updateTab;
	this.refreshTab = refreshTab;
	this.resizeTabs = resizeTabs;
	
	this.tabIDIndex = 0;
	this.getID = function() {
		return "tabID__" + this.tabIDIndex++;
	};
	
	// 创建新标签
	this.createTab = function(id, name, url, position) {
			var  option = {
					id:id,
					title:name,
				//	href:url,
					content:this.contentTpl.replace("#url#", url).replace("#name#", id),
					cache:true,
					closable:true,
					selected:true,
					tools:[{iconCls:miniRefreshIconCls,
							handler:function(){
								refreshTab(id);
								resizeTabs();
							}
					   	}
					],
					position:position
			};
			
			this.tabs().tabs('add', option);
			this.resizeTabs();
	};
	
	// 获取指定索引的标签
	this.getTab = function(index) {
		return tabs().tabs('getTab',index);
	};
	
	// 选择标签
	this.selectTab = function(idxOrTab) {
		this.tabs().tabs("select", typeof idxOrTab == 'number' ? idxOrTab : this.tabs().tabs("getTabIndex", idxOrTab));
	};
	
	// 打开标签，若标签已存在则选中该标签，若未存在，则创建新的标签
	this.openTab = function(id, name, url, position, refresh) {
		if (id == null) id = this.getID();
		var tab = this.getTabById(id);
		if (tab) {
			this.selectTab(tab);
			if (refresh) {
				refreshTabMap(id, url);
				this.resizeTabs();
			}
		} else {
			this.createTab(id, name, url, position);
		}
	};
	
	// 打开标签，若标签已存在则选中该标签，若未存在，则创建新的标签
	//参数：refresh为true，则是重新发出请求，用于列表标注后定位，重新传递参数
	this.openTabMap = function(id, name, url, position, refresh) {
		if (id == null) id = this.getID();
		var tab = this.getTabById(id);
		if (tab) {
			this.selectTab(tab);
			if (refresh) {
				refreshTabMap(id, url);
				this.resizeTabs();
			}
		} else {
			this.createTab(id, name, url, position);
		}
	};


	// 设置首页标签样式
	this.setHomeIcon = function (homePageIconCls) {
		var hp = this.getTabById(homePageId);
		if (hp)
			this.updateTab(hp,{iconCls:homePageIconCls || defHomePageIconCls});
	};
	
	// 获取标签索引
	this.getTabIndex = function (tab) {
		return this.tabs().tabs("getTabIndex", tab);
	};
	
	// 获取当前选中的标签
	this.getSelTab = function() {
		 return this.tabs().tabs("getSelected");
	};
	
	// 关闭指定索引的标签
	this.closeTab = function (index) {
		if (index != 0)
			this.tabs().tabs("close", index);
	};

	// 获取当前打开标签的总数
	this.getTabsCount = function() {
		return tabs().tabs("tabs").length;
	};
	
	// 关闭当前标签
	this.closeSel = function() {
		this.closeTab(this.getTabIndex(this.getSelTab()));
	};
	
	// 关闭全部标签
	this.closeAll = function () {
		var len = this.getTabsCount();
		if (len > 1)
			for (var i = len - 1; i > 0; i--)
				this.closeTab(i);
	};
	
	// 刷新当前
	this.refreshSel = function() {
		this.refreshTab(this.getSelTab());
		this.resizeTabs();
	};
	
	// 下一个
	this.nextTab = function() {
		var selIdx = this.getTabIndex(this.getSelTab()),
			count = this.getTabsCount();
		this.selectTab(++selIdx >= count ? 0 : selIdx);
	};
	
	// 上一个
	this.preTab = function() {
		var selIdx = this.getTabIndex(this.getSelTab()),
			count = this.getTabsCount();
		this.selectTab(--selIdx < 0 ? count - 1 : selIdx);
	};
	
	// *********  标签的配置选项 **********
	var tabsOption = {
			border:false,
			// 工具条按钮
		/*	tools:[{
					iconCls:'icon-cancel',
					handler:function(){
							closeTab(getSelTabIndex());
					}
				},
				{
					iconCls:'icon-no',
					handler:function(){
						closeAll();
					}
				},
				{
					iconCls:'icon-reload',
					handler:function(){
						refreshTab(getSelTab());
					}
				}
				       
			],	*/
			onSelect:function(title,index) {
				// index == undefined ???
				var tab = tabs().tabs('getTab',title);
				
				// 改变“当前位置”
				if ($$.getFrame('righttopFrame')) {
					var chgPsn = $$.getFrame('righttopFrame').changePosition;
					if (chgPsn)
						chgPsn(tab.panel('options').position);
				}
				
				// 左侧菜单
				if (tabs().tabs('getTabIndex',tab) == 0) {
					if ($$.getFrame('leftFrame')) {
						var fn = $$.getFrame('leftFrame').backHome;
						if (fn) fn();
					}
				}
			}	
		};
	
		// initialise...
		$(idSltr).tabs(tabsOption);
		// 设置首页图标
		this.setHomeIcon();	
		// resize
		$(window).resize(resizeTabs);
		this.resizeTabs();
}



// -----------------------------------------------------------------

$(function(){
	// 标签容器的id
	var tabsDivId = 'bodyMainContainer';
	
	Tabs = new EUTabs(tabsDivId);
	
	
});






