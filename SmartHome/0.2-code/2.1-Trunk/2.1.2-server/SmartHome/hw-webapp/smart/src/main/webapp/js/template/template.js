;(function($,undefined){
    $.template = {
        /**
         * 添加添加按钮函数 
         */
        add:function(){
                var options = {
                    id : 'templateDiv',
                    title : '添加模板'
                 },
                url='public/popup!add',
                params='method=add',
                callback = function(){
                    $('#saveBtn','#templateDiv').bind('click', function(event) {
                        $.template.saveAddData();
                    });
                    $('#backBtn','#templateDiv').bind('click', function(event) {
                        $.template.closeDiv('templateDiv',$.template.loadMasterTb);
                    });
                };
                $.hwPopup.openPopup(options,url,params,callback);
        },
        /**
         * 添加Frame按钮函数 
         */
        addFrame:function(){
            var options ={
                id : 'templateFrameDiv',
                title : '添加Frame模板',
                buttons : {
                    "返回":function(){
                        $.template.closeFrameDiv('templateFrameDiv','gridTable');
                    },
                    "确定":function(){
                         $.template.saveAddFrameData();
                    },
                    "提交":function(){
                         $.template.submitAddFrameData();
                    }
                },
                beforeClose:function(){
                    // alert("beforecloseframe");
                },
                close:function(){
                    // alert("closeframe");
                }
            },
                url='public/popup!doAddFrame',
                params={method:'add'},
                callback = function(){
                    alert("callback");
                };
                $.hwPopup.openFramePopup(options,url,params,callback);
        },
        /**
         *点击添加页码确定按钮函数 
         */
        saveAddData:function(){
            $.post('public/popup!doSaveAdd',$('#addTemplate').serialize(),function(data){
                    $.hwDialog.showMsg(data.content, data.type, {
                        title : data.title,
                        buttons : {
                            '确定' : function() {
                                $(this).dialog('close');
                                $.template.closeDiv('templateDiv',$.template.loadMasterTb);
                            }
                        }
                    }); 
            });

        },
        /**
         *  点击添加页码提交按钮函数 
         */
        submitAddData:function(){
            $.hwDialog.showMsg("提交成功！", MSG.OK, {title:'消息',buttons:{
                '确定':function(){
                    $(this).dialog('close');
                    $.template.closeDiv('templateDiv','gridTable');
                }
            }});
        },
        /**
         *点击添加页码确定按钮函数 
         */
        saveAddFrameData:function(){
            //兼容IE,google,firefox
            var $frame = $(window.frames["templateFrame"].document || window.frames["templateFrame"].contentDocument);
            $.post('public/popup!doSaveAdd',$frame.find('form[id=addTemplate]').serialize(),function(data){
                    $.hwDialog.showMsg(data.content, data.type, {
                        title : data.title,
                        buttons : {
                            '确定' : function() {
                                $(this).dialog('close');
                                $.template.closeFrameDiv('templateFrameDiv','gridTable');
                            }
                        }
                    }); 
            });
            // $.hwDialog.showMsg("保存成功！", MSG.OK, {title:'消息',buttons:{
                // '确定':function(){
                    // $(this).dialog('close');
                    // $.template.closeFrameDiv('templateFrameDiv','gridTable');
                // }
            // }});
        },
        /**
         *  点击添加页码提交按钮函数 
         */
        submitAddFrameData:function(){
            $.hwDialog.showMsg("提交成功！", MSG.OK, {title:'消息',buttons:{
                '确定':function(){
                    $(this).dialog('close');
                    $.template.closeFrameDiv('templateFrameDiv','gridTable');
                }
            }});
        },
        /**
         * 点击返回按钮函数，关闭层，清空层内容 ,刷新列表数据
         */
        closeDiv:function(divId,fun){
            fun();
            $("#"+divId).empty();//清空层内容
            $.hwPopup.closePopup(divId);//关闭层
        },
        /**
         * 点击返回按钮函数，关闭层，清空层内容 ,刷新列表数据
         */
        closeFrameDiv:function(divId,gridId){
            // alert("刷新"+gridId+"表");        
            $.hwPopup.closePopup(divId);//关闭层
        },
        /**
         * 从表tab切换
         * @param {Object} i tab索引
         * @param {Object} o tab对象
         */
        switchTab: function (i,o){
            $("div[id^=tabDiv]").hide();
            $("#tabDiv"+i).show();
            $.selectTag('tagContent'+i,o);
         },
          
         /**
          *加载所有的从表数据 
          */
         loadList :function(){
            
            var idArr = $.hwTb.getSelectedRowID('listTb');
            
            // alert(idArr);
            
            this.loadData1();
            this.loadData2()
            this.loadData3()
            $('div[id=con]').show();
            this.switchTab(0,$('ul[id=tags] li:first a:first'));
            // $("div[id^=tabDiv]").hide();
            // $("#tabDiv0").show();
        },
        /**
         *加载主表数据 
         */
        loadMasterTb: function(curPage,pagesize){
            //有从表时，写此方法让从表内容清空，恢复到加载时状态
            var callbackFun  = function(){
                $('div[id=con]').hide();
                $("div[id^=tabDiv]").empty();
                
            };
            
            $.hwTb.loadData(curPage,pagesize,'queryFrom','masterTb','public/popup!list',callbackFun);
        },
        /**
         *加载从表1数据 
         */
        loadData1:function (curPage,pagesize){
            $.hwTb.loadData(curPage,pagesize,'queryFrom1','tabDiv0','public/popup!list?method=list1');
        },
        /**
         *加载从表2数据 
         */
        loadData2:function (curPage,pagesize){
            $.hwTb.loadData(curPage,pagesize,'queryFrom2','tabDiv1','public/popup!list?method=list2');
        },
        /**
         *加载从表3数据 
         */
        loadData3:function (curPage,pagesize){
            $.hwTb.loadData(curPage,pagesize,'queryFrom3','tabDiv2','public/popup!list?method=list3');
        }
    }
})(jQuery);


