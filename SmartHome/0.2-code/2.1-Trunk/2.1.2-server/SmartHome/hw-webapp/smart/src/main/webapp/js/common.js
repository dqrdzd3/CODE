;(function($,w,undefined){
	// $$, runOnLoadFunctions, selectTag, initActionButtons, showMsg
	w.$$ = w.$$ || {};
	$.extend(w.$$,{
		// getIdx, clearInput, getActionUrl
			
		idx: 0,
		
		getIdx: function(){
			return ++this.idx;
		},
		
		getTabs: function() {
			return $$.getFrame("rightFrame").Tabs;
		},
		
		// 清除form表单中的input框，查看页面使用
		clearInput: function(sltr) {
			
			// 清除文件上传按钮， danger.js中使用
			$("#b01001modifyupload,#b01002modifyupload,#b01003modifyupload,#b01007modifyupload," +
					"#b01009modifyupload,#b01008modifyupload,#b01006modifyupload,#b01004modifyupload,#b01005modifyupload")
				.each(function(i){
					$(this).siblings().filter("img")
					   .add($(this))
					   .hide();
				});
			// end
			
			// 文本框与textarea
			$('input[type="text"],textarea', sltr).each(function(){
				var val = $(this).val();
				$(this).parent().text(val);
			});
			
//			$('textarea', sltr).each(function(){
//				$(this).attr("disabled",true);
//			});
			
			var r = $('input[type="radio"]:not(:checked)', sltr);
			r.next('label').remove();
			r.remove();
			
			// 单选、多选
			$('radio,checkbox',sltr).attr('disabled','disabled');
			
			// 下拉
			$('select', sltr).each(function(){
//				var txt = this.selectedIndex ? $('option:selected', this).text() : ""; // 未选择时显示空串
				var txt = $('option:selected', this).text();
				if (txt === '请选择')
					txt = '';
				$(this).parent().text(txt);
			});

		},
		

		// 清除form表单中的上传按钮, 查看页面使用
		clearUpload: function(form){
			$(form).find("#upload").siblings().filter("img")
			   .add("#upload")
			   .hide();
		},
		
		getActionUrl: function() {
			return location.pathname.match(/[^!\.\?]*/)[0];
		},
		
		// 从url中截取web应用的contextPath
		getContextPath : function() {
//			return location.pathname.match("/[^/]*/?")[0];
			return this.ctx || location.pathname.match("/[^/]*/?")[0];
		},
		
		// 获取指定模块的权限按钮
		// 参数：actionUrl:模块的url , id:按钮的div容器id , callback:回调
		// showActionButtons : function(actionUrl, id, callback) {
			// $.get('getPermButtons.action?actionUrl=' + actionUrl, function(data){
				// $("#" + id).html(data);
				// if (callback) callback();
			// });
		// },
		
		// 添加新的权限按钮
		// 参数： name:名称 , fn:点击事件  , className:样式名称 , btnDivId:容器id
		// addButton : function(name, fn, className, btnDivId) {
			// var idSltr = '#' + (btnDivId || 'operating'),
				// className = className || '';
			// $('ul', idSltr).append($('<li/>')
						// .addClass(className)
						// .click(fn)
						// .append('<a href="javascript:;">' + name + '</a>')
			// );
		// },
		
		
		getBtn : function(idSltr, type) {
			var btn = {
					'add' : idSltr + " .liadd",
					'edit' : idSltr + " .liedit",
					'view' : idSltr + " .liview",
					'delete' : idSltr + " .lidelete",
					'print' : idSltr + " .liprint"
				};
			
			return $(btn[type]);
		},
		
		// 对增删改查权限按钮绑定新的事件
		bindButton : function(type, fn, btnDivId) {
			var idSltr = '#' + (btnDivId || 'operating');
			
			this.getBtn(idSltr,type).unbind('click').click(fn);
		},
		
		// 调用增删改查权限按钮的click事件
		clickButton : function(type, btnDivId) {
			var idSltr = '#' + (btnDivId || 'operating');
			
			this.getBtn(idSltr,type).click();
		},
		
		// 
		procCloseButton : function(divId) {
			$(".back", "#" + divId).click(function(){
				$$.closeDiv(divId);
			});
		},
		
		// 弹出层配置选项
		dialogOption : {
			autoOpen:false,
			width:760,
		//	height:300,
			modal:true,
//			show:{effect:'drop',direction:'up'},
//			hide:{effect:'drop',direction:'down'},
			dialogClass:'jumpdiv-dialog'
			//zIndex:99999
			
		},
		
		// 打开弹出层，
		// 参数： div层的ID , 窗口标题  , url , 传参, callback, 是否自动打开窗口
		// callback(data, option): data:服务器返回的数据， option:窗口的配置选项， 函数返回option。
		openDiv : function(id, title, url, param, callback){
			var idSltr = "#" + id;
			var opt = $.extend({
				title:title,
				close:function(){
					// 关闭后清空内容
					$(idSltr).empty();
				}},this.dialogOption);
			
			// $.post(url, param, function(data) {
				// $(idSltr).html(data);
				// if (callback) callback();
			// });

			
            //修改请求方式，用于在请求成功后再打开页面，并重新调整grid布局
			$.ajax({
			       url:url,
			       data:param,
			       type:'POST',
			       success:function(data){
			           
			           if (data && data.IDENTITY == 'message') {
                                showMsg(data.content,data.type,{title:data.title,buttons:{
                                    '确定':function(){
                                        $(this).dialog('close');
                                    }
                                }});
                        }else{
                    		$(idSltr).html(data);
                    		var newOpt;
            				callback && (newOpt = callback(data, opt));
            				// 请求回的内容太多时，窗口配置为自动打开时会出现一些问题，如位置错位等
                			$(idSltr).dialog(newOpt || opt).dialog("open");
                			if(window.resizeGrids){
                				resizeGrids();
                			}
                        }
        			    
        			},
			       error:function(data){}
              });

			
		},
		
		// 关闭弹出层， 参数： div层的ID
		closeDiv : function(id) {
			//$("#" + id).empty();
			$("#" + id).dialog('close');
		},
		
		
		SelRowMsg : {
			msg0 : '请至少选中一条记录！',
			msg1 : '请选中一条记录！'
		},
		
		// 判断是否一条未先
		noSel : function(idArr,msg0) {
			if (idArr.length == 0) {
				showMsg(msg0 || this.SelRowMsg.msg0, MSG.INFO);
				return true;
			}
			return false;
		},
		
		// 判断是否选择了一条
		oneSel : function(idArr,msg1) {
			if (idArr.length != 1) {
				showMsg(msg1 || this.SelRowMsg.msg1, MSG.INFO);
				return false;
			}
			return true;
		},
		
		
		// 判断grid选择行数
		// num为1时，判断是否选择了且只选了一条。
		// num为0时，判断是否一条了未选。
		// gridId: 指定grid
		// msg0: 未选时的提示信息
		// msg1: 选择多于一条时的提示信息
		/*  
		  
		  	// 删除等需要批量操作时，判断是否一条未选
			if (!$$.checkSelRow(0)) {
				
			}
			
			
			// 查看或修改时， 用来判断是否只选了一条。
			if ($$.checkSelRow(1)) {
				
			}
			
			// 自定义提示消息
			if($$.checkSelRow(1, 'gridTable', '选一条呗', '只能选一条啊')) {
				alert('ok')
			}
		
		*/
		checkSelRow : function(num, gridId, msg0, msg1) {
		//	this.SelRowMsg.msg0 = msg0 || this.SelRowMsg.msg0;
		//	this.SelRowMsg.msg1 = msg1 || this.SelRowMsg.msg1;
			var idArr = getSelectedRowID("#" + (gridId || "gridTable"));
			//alert(idArr);
			// 选一条
			if (num == 1) {
//				if (this.noSel(idArr) || !this.oneSel(idArr)) return false;
				if (!this.oneSel(idArr,msg1)) return false;
				return true;
			} 
			// 未选
			else if (num == 0) {
				if (this.noSel(idArr,msg0)) return true;
				return false;
			}
		},
		
		checkboxSel : function(gridId){
			var newIdArr = '';
			$.each($("#"+gridId+' input[type="checkbox"]'), function(key, value){
			    if(value.name != null && value.name != ''){
			    	if(value.checked){
			    		var str = value.id;
			    		str = str.replace('jqg_gridTable_','');
			    		newIdArr = newIdArr + str + ',';
			    		return false;
			    	}
			    }
			});
			if(newIdArr == '') {showMsg(this.SelRowMsg.msg0, 2);return true;}
			else return false;
		},
		
		// 获取jsonPO
		// 参数：
		// url: 请求的action url， action中需 return JSON_PO;
		// param: 请求参数
		// fn: 回调函数
		/*
		 * 例：
		  	var url = $$.getContextPath() + 'occupation/h003!queryPO?h003PO.ma001=' + id;
		 			
 			$$.getPO(url, function(h003PO) {
 				alert(h003PO.ma002);
 				alert(h003PO.ma003);
 			});
		  
		 */
		getPO : function(url, param, fn) {
			fn = fn || (typeof param === 'function' ? param : null);
			param = typeof param !== 'function' ? param : {};
			
			$.post(url, param, function(po) {
				if (fn) fn(po);
			});
		},
		
		wrapStyle : function(name) {
			return "<span class=\"parNameInCapt\">【" + name + "】</span>";
		}
		
		
	});

	// 弹出层的选项卡
	w.selectTag = function(showContent,selfObj) {
	/*	
		// 操作标签
		var tag = document.getElementById("tags").getElementsByTagName("li");
		var taglength = tag.length;
		for(var i=0; i<taglength; i++){
			tag[i].className = "";
		} 
		selfObj.parentNode.className = "selectTag";
		// 操作内容
		for(i=0; j=document.getElementById("tagContent"+i); i++){
			j.style.display = "none";
		}
		document.getElementById(showContent).style.display = "block";
	*/
		
		
	// 20130328 mfb： 添加验证未通过标签样式时修改   
	//	$("li", $(selfObj).parent().parent()).attr('class', '');
	//	$(selfObj).parent().attr('class','selectTag');
		$("li", $(selfObj).parent().parent()).removeClass("selectTag");
		$(selfObj).parent().addClass('selectTag');
		
		var con = $(selfObj).parent().parent().parent();
		$('.tagContent', con).hide();
		$("#" + showContent, con).show();
		
		if(window.resizeGrids){
			resizeGrids();
		}
		
	};
	
	
	// 页面加载之后需要调用的一些函数
	w.runOnLoadFunctions = function() {
		
		// handle button， 搜索区域的伸缩按钮
		(function(){
			var seltor = "#hiddlebutton",
				upClass = "upArrow",
				downClass = "downArrow";
			
			$(seltor)
				.css('zIndex',1)
				.empty()
				.append($("<a>"))
				.children("a")
				.attr("href","javascript:;")
				.addClass(upClass)
				.click(function(){
					var obj = this;
					$("div:first", $(seltor).parent()).slideToggle("fast", function(){
						$(obj).toggleClass(downClass);
					});
				});
		})();
		
		
	};
	

	// message box， 消息提示框
	// 参数：msg:消息 , type:图标类型 , option:自定义dialog选项
	// eg: 自定义按钮时:
	/*	showMsg('这是消息内容', 1, {
	 * 		title : '这是消息框的标题',
	 * 		buttons:{
	 * 			'确定' : function(){}
	 * 			'取消' : function(){}
	 * 		}
	 *  });
	 * 
	 */
	w.__MsgBoxState = 0;
	w.showMsg = function(msg, type, option) {
		// 20130515 update by mfb : IE6
		var msgBoxId,
			defaultMsgBoxId = "msg_box";
		if (!w.__MsgBoxState) {
			w.__MsgBoxState = 1;
			msgBoxId = defaultMsgBoxId;
		} else {
			msgBoxId = defaultMsgBoxId + $$.getIdx();
		}
		var	msgBoxSltr = "#" + msgBoxId,
			msgDivClass = 'msg-div',
			
			// style control
			dialogClass = 'msgbox-dialog',
			icoType = (typeof type) == "number" ? type : 0,
			option = (typeof type) == "object" ? type : (option || {});		
			

		if ($(msgBoxSltr).length == 0) {
			$("body").append(
				$("<div>").attr({id:msgBoxId, 'class':'hide'})
						  .append($('<div class="' + msgDivClass + '"/>'))
			);
		}
			
		// icon， 左侧类型图标
		var iconClass = {0:'ico-none', 1:'ico-ok', 2:'ico-info', 3:'ico-question', 4:'ico-warning', 5:'ico-error'}[icoType] || 'ico-none';
		$(msgBoxSltr + " ." + msgDivClass).attr('class', msgDivClass + " " + iconClass);

		// message， 消息内容
		$(msgBoxSltr + " ." + msgDivClass).html(msg||'');
		
		
		// 消息框的按钮
		var buttons = option.buttons || {'确定':'close'},
			close = function() {$(this).dialog('close');};
		
		for (var p in buttons) {
			if (buttons[p] == 'close') buttons[p] = close;
			// 处理所有消息框下方的按钮，点击后将自动关闭，兼容之前系统中未手动关闭的Bug   // 20130515 update by mfb
			else if (typeof buttons[p] === 'function') {
				(function(oldFun){
					buttons[p] = function() {
						oldFun.call(this);
						close.call(this); // 关闭
					};
				})(buttons[p]);
			}
		}
		
		
		delete option.buttons;
		option.title = option.title || '消息';
		
		// when closed
		var oldCloseFun = option.close;
		option.close = function() {
			oldCloseFun && oldCloseFun();
			if (msgBoxId == defaultMsgBoxId) {
				w.__MsgBoxState = 0;
			} else {
				$(msgBoxSltr).dialog('destroy').remove();
			}
		};
		
		var initOption = $.extend({
			modal:true,
			resizable:false,
			buttons:buttons,
			dialogClass: dialogClass
		//	zIndex:999999 // greater than resize handler	
				
		}, option);
		
		
		$(msgBoxSltr).dialog(initOption).dialog("moveToTop");
	};
	
   /**
     *提示消息类型常量 
     */
    w.MSG = {'NONE':0,'OK':1,'INFO':2,'QUESTION':3,'WARNING':4,'ERROR':5};
	
    
    // 20130428 mfb update: 修改主从表问题
    w.initActionButtons = function (option) {
		// 从表隐藏 
    	var opt = option || {};
    	if (opt.gridId && _AllGridOptions[opt.gridId].po_pId) {
    		$("#" + opt.id).hide();
    		return;
    	}
    	
    	_initActionButtons(opt);
    };
    
	// 初始化权限按钮
	w._initActionButtons = function(option) {

		/* ********** 配置选项  ************
		 
			var actionOption = {
					init: true, // 是否进行初始化，默认页面加载完后自动执行
					id:'',		// div容器id
					gridId:'',	// 对应的grid表格id
					actionUrl:'corpinfo/b002',	// 指定权限模块的url
					hide:true,	// 初始化后是否隐藏
					methodNames:{view:'view', add:'add', edit:'edit', del:'del'},	// 调用对应的action中的方法名称
					actionMethods:{saveAdd:'doSaveAdd', saveEdit:'doSaveEdit'}		// 添加，与修改 对应原action中的方法名称
					poName:'b002PO',	// action中的poName
					poId:'',			// po中的主键字段
					table:'b002',		// 删除时的指定的表名
					winTitle:'',		// 弹出容器的标题
					validate:true,		// 在提交form表单时是否自动验证
					
				// 以下是事件：	 如果函数 return false, 将中断执行下一步操作 
					
					// 点击“添加”时执行
					// 参数： form:表单对象
					beforeAdd : function(form) {
					
					},
					
					// 点击“修改”时执行
					beforeEdit : function(id, form) {
					
					},
						
					// 点击“查看”时执行
					beforeView : function(id, form) {
					
					},
					
					// 点击“删除”时执行
					beforeDelete : function(idArray) {
					
					},
					
					// “修改”和“查看”时，通过ajax去请求选中数据的po对象，在将对象的值填充到form表单之前，调用该函数。
					// 传入的参数： id:选中行的ID，    jsonPO:action中返回的po对象，  form:表单对象
					beforeFillForm : function(id, jsonPO, form) {
					
					},
					
					// 填充表单之后调用该函数
					afterFillForm : function(id, jsonPO, form) {
					
					},
					
					// 添加新记录， 点击“确定”时，在表单提交之前调用该函数。
					beforeSaveAdd : function(form) {
					
					},
					
					// 修改记录，点击“确定”时，在表单提交之前调用。
					beforeSaveEdit : function(form) {
					
					},
					
					// 权限按钮初始化之后，调用该函数，可进行重新绑定增删改查事件，以及添加新的权限按钮。
					buttonInited : function() {
						$$.addButton('','','');
						$$.bindButton('','','');
					},
					
					// “添加”、“修改”成功之后，调用该函数
					success : function(form) {
						
					}
					
					// 弹出层对话框选项
					dialog: {
						width:970,
						height:300,
						title:'这是标题'
					}
				};
				
		************************************* */
			
			// option
			var opt = option || {};
			
			// 默认自动初始化，若配置为false， 则不进行初始化
			if (opt.init === false) return;
			
			var	id = opt.id || "operating",
				idSltr = "#" + id,
				gridId = opt.gridId || "gridTable",
				gridSltr = "#" + gridId,
				actionUrl = opt.actionUrl ? opt.actionUrl : ($(gridSltr).data("actionUrl") || $$.getActionUrl().replace($$.getContextPath(),"")),
				winTitle = opt.winTitle ||  $(gridSltr).data("winTitle") || "",
				validate = typeof opt.validate === 'undefined' ?  true : opt.validate,
				dialogOpt = opt.dialog || {};
				dialogOpt.title = winTitle || '',
			
			// Event	
				successFun = opt.success;
			
			// 权限按钮的容器div	
			if (!$(idSltr).length) 
				$(gridSltr).parents('.tablestyle').before('<div class="operating" id="' + id + '"/>');
			if (opt.hide) $(idSltr).hide();
			// 请求权限按钮
			// $$.showActionButtons(actionUrl, id, addClickEvent);
			
			
			actionUrl = $$.getContextPath() + actionUrl;
			var	idArr = [],
				viewSltr = idSltr + " .liview",
				addSltr = idSltr + " .liadd",
				editSltr = idSltr + " .liedit",
				delSltr = idSltr + " .lidelete",
				
				jumpDivId = id + "_input_form",
				jumpDivSltr = "#" + jumpDivId,
				
				table = opt.table || actionUrl.substring(actionUrl.lastIndexOf("/") + 1, actionUrl.length),
				pk = opt.pk || $(gridSltr).data("pk"),
				poName = opt.poName || table + "PO",
				poId = opt.poId || pk || "ma001",
				methodNames = $.extend({view:'edit', add:'add', edit:'edit', del:'del'}, opt.methodNames),
				actionMethods = $.extend({saveAdd:'doSaveAdd', saveEdit:'doSaveEdit'}, opt.actionMethods),
				addUrl = actionUrl + "!" + actionMethods.saveAdd,
				editUrl = actionUrl + "!" + actionMethods.saveEdit;
				
			

			
			function getJumpDiv() {
				return $(jumpDivSltr);
			}
			function getForm() {
				return $("form", getJumpDiv());
			}
			// 弹出层对象
			var JumpDiv = {
				html : '',
				getJumpDiv : getJumpDiv,
				getForm : getForm,
				getSubmitBtn : function() {return $(".submit", this.getForm());},
				getBackBtn : function() {return $(".back", this.getForm());},
				
			/*	option : $.extend({
							title: winTitle || '',
							width:760,
						//	height:300,
							modal:true,
							show:{effect:'drop',direction:'up'},
							hide:{effect:'drop',direction:'down'},
							dialogClass:'jumpdiv-dialog',
							zIndex:99999,
							
							close : function(){
								JumpDiv.reSetHtml();
							}
							
				}, opt.dialog),
			*/	
				// dialog选项
				option : $.extend($.extend(dialogOpt, {
					close : function(){
					//	JumpDiv.reSetHtml();
						// 关闭后清空内容
						getJumpDiv().empty();
					}
					
				}), $$.dialogOption),
				
				// 打开层
				open : function() {
					// 打开之前重置html内容
				//	JumpDiv.reSetHtml();
					// 打开窗口
					this.getJumpDiv().dialog(this.option).dialog("open");
					return this;
				},
				
				// 关闭
				close : function() {
					this.getJumpDiv().dialog('close');
				},
				
				// 处理“添加”与“修改”返回的消息
				procMsg : function(msg, type) {
					var buttons;
					
					if (msg.type != 1) {
						if(!msg.content){ // MN于20121115修改,解决数据类型转换后服务器DOWN掉的问题
							msg = {content:'操作失败,请检查输入数据是否符合规定'};
						}
						buttons = {
								'确定':function(){
									$(this).dialog('close');
									reloadGrid(gridId);
								}
						};
					} else {
						// 调用配置的success函数
						if (successFun) successFun(this.getForm()[0]);
						if (type == 'add') {
						
						// 去掉'添加成功！是否继续添加？'提示
						/*	msg.content = '添加成功！是否继续添加？';
							buttons = {
									'否' : function() {
										$(this).dialog('close');
										JumpDiv.close();
										reloadGrid(gridId);
									},
									'是' : function() {
										JumpDiv.getForm()[0].reset();
										if(jQuery("#filepath").length)
										jQuery("#filepath").val(null);
										if(jQuery("#filename").length)
										jQuery("#filename").val(null);
										$(this).dialog('close');
										reloadGrid(gridId);
									}
							};
						*/
					//		if (msg.content) msg.content = '添加成功！';
							buttons = {
									'确定':function(){
										$(this).dialog('close');
										JumpDiv.close();
										reloadGrid(gridId);
									}
							};
							
						} else if (type == 'edit') {
				//			if (msg.content) msg.content = '修改成功！';
							buttons = {
									'确定':function(){
										$(this).dialog('close');
										JumpDiv.close();
										reloadGrid(gridId);
									}
							};
						}
					}
					
					
					showMsg(msg.content, msg.type, {title:msg.title,buttons:buttons});
				},
				
				// 提交表单（添加修改时）
				req : function (url, type) {
					$.post(url+"?ran="+Math.random(), this.getForm().serialize(), function(msg){
						
					/*	showMsg(msg.content, msg.type, {title:msg.title, buttons:{'返回':function(){
								$(this).dialog('close');
								JumpDiv.close();
								reloadGrid(gridId);
							},'确定':function(){
								$(this).dialog('close');
								reloadGrid(gridId);
							}}});
					*/	
						
						JumpDiv.procMsg(msg, type);
						
					});
				},
				
				// 处理添加层的按钮
				setButtonAdd : function() {
					this.getSubmitBtn().click(function(){
						var beforeSaveAdd = opt.beforeSaveAdd;
						if ( (!beforeSaveAdd || beforeSaveAdd && beforeSaveAdd(getForm()[0]) !== false) 
								&& (!validate || getForm().valid() || ((typeof (showErrorTab) !== 'undefined') && showErrorTab())) ){
							JumpDiv.req(addUrl, 'add');
						}
					});
					return this;
				},
				
				// 处理修改层的按钮
				setButtonEdit : function() {
					this.getSubmitBtn().click(function(){
						var beforeSaveEdit = opt.beforeSaveEdit;
						if ( (!beforeSaveEdit || beforeSaveEdit && beforeSaveEdit(getForm()[0]) !== false) 
								&& (!validate || getForm().valid() || ((typeof (showErrorTab) !== 'undefined') && showErrorTab())) ){
							
							JumpDiv.req(editUrl, 'edit');
						}
					});
					
					// 日期控件格式化
					// if($$.datepickerFormat){
    					// $$.datepickerFormat(this.getForm());
					// }
					if($.my97DateFormat){
    					$.my97DateFormat(this.getForm(),null,'yyyy-MM-dd HH:mm:ss');
					}					return this;
				},
				
				// 处理查看层按钮
				setButtonView : function() {
					this.getSubmitBtn().parent().hide();
					$$.clearInput(this.getForm());
					$$.clearUpload(this.getForm());
					return this;
				},
				
				// 重置层中的html
				reSetHtml : function() {
					this.getJumpDiv().html(this.html);
					
					// prevent submit
					this.getForm().submit(function(){return false;});
					// button
					var backBtn = this.getBackBtn(), backPar = backBtn.parent(),
						submitBtn = this.getSubmitBtn(), submitPar = submitBtn.parent();
					backBtn.remove(); 
					submitBtn.remove();
					backPar.append($('<input />').attr({type:'button','class':'back'}));
					submitPar.append($('<input />').attr({type:'button','class':'submit'}));
					
					var jumpDiv = this;
					this.getBackBtn().click(function(){
						jumpDiv.close();
					});
					
					// datepicker
					// if($$.datepicker){
    					// $$.datepicker(this.getForm());
					// }                    if($.my97DateFormat){
                        $.my97DateFormat(this.getForm(),null,'yyyy-MM-dd HH:mm:ss');
                    }
					
					if (opt.afterResetHtml) opt.afterResetHtml(this.getForm()[0]);
				}
					
			};
			
			// input_from
			$("body").append($("<div>").attr({id:jumpDivId,'class':'hide'}));
			$.get(actionUrl + "!" + methodNames.add, function(data){
				JumpDiv.html = data;
				
			//	JumpDiv.reSetHtml();
			});
			
			
			
			function exeClick(url,view) {
			
				// open div
			//	$(iFrameSltr).attr("src", url);
				openJumpDiv();
				
				$.post(url,function(data){
					$(jumpDivSltr).html(data);
					if (view) $$.clearInput(jumpDivSltr);
				});
				
				// open window
			//	window.open(url,'','status=no,menubar=no,location=no,resizable=no,toolbar=no,left=260,top=172,width=937,height=594')
			}
			
			
			// 批量删除
			function delBatch() {
				var newIdArr = getSelectedRowID(gridSltr);
				var url = actionUrl + "!delBatch.action";
				$.post(url,{table:table,pk:pk,ids:newIdArr.join(),ran:Math.random()},function(msg){
					showMsg(msg.content,msg.type);
					reloadGrid(gridId);
					
					var gridOptions = window.gridOption ? [window.gridOption] : (window.gridOptions || []);
					
					for (var i = 0, len = gridOptions.length; i < len; i ++) {
						var option = gridOptions[i];
						var id = option.id || "";
						var subGridId = option.subGridId || "";;
						if(id == gridId){
							if(subGridId){
								if($.isArray(subGridId)){
									$.each(subGridId, function(key, val){
										reloadGrid(val);
									});
								}
								reloadGrid(subGridId);
							}
						}
					}
					
				});
				$(this).dialog('close');
			}
			
			// 添加权限按钮的click事件
			function addClickEvent() {
					// add
					if(!($(addSltr).data("events") && $(delSltr).data("events")["click"])){
						$(addSltr).click(function(){
							
						/* 	var url = actionUrl + "!" + methodNames.add + "?ran=" + Math.random();
							exeClick(url);   */	
							
							jQuery.get("sessionStatus!checkSessionStatus.action");
							
							// 重置html内容
							JumpDiv.reSetHtml();
							// before add
							var beforeAdd = opt.beforeAdd;
							if (!beforeAdd || beforeAdd && beforeAdd(JumpDiv.getForm()[0]) !== false)
								JumpDiv.open().setButtonAdd();
						});
					}
					// edit
					if(!($(editSltr).data("events") && $(delSltr).data("events")["click"])){
						$(editSltr).click(function(){
							if (!$$.checkSelRow(1,gridId)) return;
							idArr = getSelectedRowID(gridSltr);
							
							// 重置html内容
							JumpDiv.reSetHtml();
							var beforeEdit = opt.beforeEdit;
							if (!beforeEdit || beforeEdit && beforeEdit(idArr[0], JumpDiv.getForm()[0]) !== false) {
								var url = actionUrl + "!" + methodNames.edit + "?" + poName + "." + poId + "=" + idArr[0] + "&ran=" + Math.random();
								$.getJSON(url, function(obj){
									// 判断返回对象，若为消息，则弹出消息框
									if (obj && obj.IDENTITY == 'message') {
										showMsg(obj.content,obj.type,{title:obj.title,buttons:{
											'确定':function(){
												$(this).dialog('close');
											}
										}});
									} else {
										// 填充form表单
										if (fillForm(obj)){
											JumpDiv.open().setButtonEdit();
										}
											
									}
								});
							}
							
						});
					}
					
					// view
					// 链接列点击时调用该函数 (upd:130412mfb)
					window._viewContent = function(id) {
						// 重置html内容
						JumpDiv.reSetHtml();
						// before view
						var beforeView = opt.beforeView;
						if (!beforeView || beforeView && beforeView(id, JumpDiv.getForm()[0]) !== false) {
							var url = actionUrl + "!" + methodNames.view + "?" + poName + "." + poId + "=" + id + "&ran=" + Math.random();
							$.getJSON(url, function(obj){
								if (obj && obj.IDENTITY == 'message') {
									showMsg(obj.content,obj.type,{title:obj.title,buttons:{
										'确定':function(){
											$(this).dialog('close');
										}
									}});
								} else {
									if (fillForm(obj))
										JumpDiv.setButtonView();
									JumpDiv.open();
								}
							});
						}
					};
					
					if(!($(viewSltr).data("events") && $(delSltr).data("events")["click"])){
						$(viewSltr).click(function(){
							if (!$$.checkSelRow(1,gridId)) return;
							var id = getSelectedRowID(gridSltr)[0];
							
							// view
							_viewContent(id);
						});
					}
					// 填充表单
					function fillForm(obj) {
						var ret = true, beforeFillForm = opt.beforeFillForm;
						if (!beforeFillForm || beforeFillForm && (ret = beforeFillForm(idArr[0], obj, JumpDiv.getForm()[0])) !== false) {
							JumpDiv.getForm().json2form(obj);
						}
						if (ret !== false && opt.afterFillForm)
							ret = opt.afterFillForm(idArr[0], obj, JumpDiv.getForm()[0]);
						return ret !== false;
					}
					
					// del
					if(!($(delSltr).data("events") && $(delSltr).data("events")["click"])){
						$(delSltr).click(function(){
							if ($$.checkSelRow(0, gridId)) return;
							idArr = getSelectedRowID(gridSltr);
							
							if (!opt.beforeDelete || opt.beforeDelete && opt.beforeDelete(idArr) !== false) {
								showMsg("确定要删除所选的记录吗？", MSG.WARNING, {buttons:{'取消':'close','确定':delBatch}});
							}
							
						});
					}
					
					// button initialized
					if (opt.buttonInited) opt.buttonInited();
					
					 
			}

            //新权限系统修改后绑定按钮事件
			addClickEvent();
		
			
	};
	
	w.Page={

	    'showLoading':function(){
	    	if($$.getFrame("rightFrame")){
	    		$$.getFrame("rightFrame").showLoading();
	    		return;
	    	}
	    	if(window.showLoading){
	    		showLoading();
	    		return;
	    	}
	    },
	    'hideLoading':function(){
	    	if($$.getFrame("rightFrame")){
	    		$$.getFrame("rightFrame").hideLoading();
	    		return;
	    	}
	    	if(window.hideLoading){
	    		hideLoading();
	    		return;
	    	}
	    }
	    
	};
})(jQuery,window);

/*************************/

$(function(){
    //设置全局ajax属性，用于请求是显示加载数据div
        $.ajaxSetup(
                {
                    beforeSend:function(){
                        Page.showLoading();
                        
                    },
                    complete:function(xhr, status){
                    	
                    	Page.hideLoading();
                    	
                    	// 处理session失效
                    	if(xhr.getResponseHeader("sessionStatus") == "timeout"){
                    		top.location=location.pathname.match("/[^/]*/?")[0] + "login!logout.action";
                    	} 
                    	
                    	// 处理重复登录
                        if(xhr.getResponseHeader("loginStatus") == "duplicationLogin"){
                        	top.location=location.pathname.match("/[^/]*/?")[0] + "login!publicDuplicationLogin.action";
                        }
                    }
                }
                
            );
        
	try {
		
		// 运行onload函数
		runOnLoadFunctions();
	//	initGrid(window.gridOption);
	//	initActionButtons(window.actionOption);
		
		// 初始化grid
		
		// 初始化权限按钮
		// 20130428 mfb update : 修改主从表问题
		
		var gridOptions = window.gridOption ? [window.gridOption] : (window.gridOptions || []);
		var actOpts = (window.actionOption ? [window.actionOption] : window.actionOptions) || (gridOptions.length > 0 ? [null] : []);
		window._AllGridOptions = {};
		window._AllActionOptions = {};
		for (var i = 0, len = gridOptions.length; i < len; i ++) {
			window._AllGridOptions[gridOptions[i].id || "gridTable"] = gridOptions[i];
		}
		for (var i = 0, len = actOpts.length; i < len; i++) {
			if (actOpts[i]) {
				window._AllActionOptions[actOpts[i].gridId || "gridTable"] = actOpts[i];
				window._AllGridOptions[actOpts[i].gridId || "gridTable"].operatingId = actOpts[i].id || "operating";
			}
		}
		
		for (var i = 0, len = gridOptions.length; i < len; i ++) {
			initGrid(gridOptions[i]);
		}
		for (var i = 0, len = actOpts.length; i < len; i++) {
			initActionButtons(actOpts[i]);
		}
	
	} catch (e) {alert(e)}
	
});



