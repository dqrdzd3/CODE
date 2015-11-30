;(function($){
    $.hwTb = $.hwTb || {};
    $.extend($.hwTb, {
            sortTable:function(obj,str,formId,fun){
                        var $column = $('#column','#'+formId);
                        //说明字段相同，则更改排序规则
                        var $sort = $('#sort','#'+formId);
                        var sortImg = '';
                        if($column.val() == str){
                            if($sort.val() == 'desc'){
                                $sort.val('asc');
                                
                               sortImg = '<img src="res/table/images/up.png" width="10" height="4" />';  
                            }
                            
                            else if($sort.val() == 'asc'){
                                $sort.val('desc');
                                sortImg = '<img src="res/table/images/down.png" width="10" height="4" />';  
                            }
                        }
                        
                        //如果是不同字段，则默认倒序
                        else{
                            $sort.val('desc');
                            sortImg = '<img src="res/table/images/down.png" width="10" height="4" />';  
                        }
                        $(obj).append(sortImg);
                        $column.val(str);
                        fun();
                       
                },
            showHidden:function(){
                    if($('#search_img').attr('src') == 'images/searchhiddleicon.jpg'){
                        $('#searcharea').hide();
                        $('#search_img').attr('src','images/searchhiddleicon_down.jpg');
                    }else{
                        $('#searcharea').show();
                        $('#search_img').attr('src','images/searchhiddleicon.jpg');
                    }
                    
                },
            /**
             * 多表用
             * 用异步刷新生替换表单提交 
             * @param {Object} formId
             * @param {Object} divId
             * @param {Object} url
             */
            load:function (formId,divId,url,callbackFun){
                $.post(url,$('#'+formId).serialize(),function(data){
                    $('#'+divId).html(data);
                    $.hwTb.bindTrEventFun(divId);
                    if(callbackFun){
                        callbackFun();
                    }
                });
                
            },
            loadData:function (curPage,pagesize,formId,divId,url,callbackFun){
                    if(!!curPage){
                        $('#curPage','#'+formId).val(curPage);
                    }
                    if(!!pagesize){
                        $('#pagesize','#'+formId).val(pagesize);
                    }
                    this.load(formId,divId,url,callbackFun);
            },
                /**
                 *隐藏表 
                 * @param {Object} id
                 */
             hidden_table: function (id){
                    var $o = $('#'+id);
                    if(!!$o.data('hide')){
                        $o.show();
                        $o.data('hide',false);
                    }else{
                        $o.hide();
                        $o.data('hide',true);
                    }
            },
                
                /**
                 *全选 
                 * @param {Object} tag
                 */
             selectAll: function (tag){
                    $.each($('tr',$(tag).closest('div').siblings('.p_r')), function(i, n){
                        $(n).find('input[type=checkbox]').attr('checked',tag.checked);
                        if(tag.checked){
                            $(n).addClass('td_actived');
                        }else{
                            $(n).removeClass('td_actived')
                        }
                    });
                    // $.each($('input[type="checkbox"]',$(tag).closest('.divb').has('.miantable')), function(key, value){
                        // value.checked = tag.checked;
                    // });
                },
                
                //获取选中行的ID数组
             getSelectedRowID: function (id) {
                    // var newIdArr = '';
                    // $.each($('input[type="checkbox"]','#'+id), function(key, value){
                        // if(value.name != null && value.name != ''){
                            // if(value.checked){
                                // var str = value.parentNode.parentNode.id;
                                // newIdArr = newIdArr + str + ',';
                            // }
                        // }
                    // });
                    // if(newIdArr != ''){
                        // newIdArr = newIdArr.substring(0, newIdArr.length - 1);
                        // var arr = newIdArr.split(',');
                        // return arr;
                    // }else{
                        // return new Array(0);
                    // }
//                     
                    var newIdArr = '';
                    $.each($('input[type="checkbox"]','#'+id), function(i, n){
                        if(n.value != null && n.value != ''){
                            if(n.checked){
                                var str = n.value;
                                newIdArr = newIdArr + str + ',';
                            }
                        }
                    });
                    if(newIdArr != ''){
                        newIdArr = newIdArr.substring(0, newIdArr.length - 1);
                        var arr = newIdArr.split(',');
                        return arr;
                    }else{
                        return new Array(0);
                    }
                },
                /**
                 *绑定行事件函数 
                 */
                bindTrEventFun:function(divId){
                    $('.miantable','#'+divId).find('tr').bind({
                        mousemove:$.hwTb.mouseMoveFun,
                        mouseout:$.hwTb.mouseOutFun,
                        click:$.hwTb.selectRow
                    });
                    
                    
                },             
                /**
                 *点击行选中，样式改为选中的样式 
                 */
                selectRow:function(){
                    var $tr = $(this);
                    $tr.addClass('td_actived')
                       .find('input[type=checkbox]').attr('checked',true)
                       .end()
                       .siblings('.td_actived').removeClass('td_actived')
                       .find('input[type=checkbox]').attr('checked',false);
                },
                /**
                 *鼠标悬停函数 
                 */
                mouseMoveFun:function(){
                    $(this).addClass('td_hover');
                },
                /**
                 *鼠标离开函数 
                 */
                mouseOutFun:function(){
                    $(this).removeClass('td_hover');
                },
                /**
                 *给表单绑定行点击事件 
                 */
                clickFun:function(sel,fun){
                    var $o; 
                    if(typeof(sel) == "string"){
                        $o = $("#"+sel);
                    }else{
                        $o = sel;
                    }
                    $('tr',$o).bind('click', function(event) {
                       var el = event.target;
                       if(el.type && el.type.toLowerCase() == 'checkbox'){
                           return;
                       }
                       if(el.tagName.toUpperCase() == 'A'){
                           return;
                       }
                       if(fun){
                           fun();
                       }
                    });
                },
                export_table:  function (){
                    
                },
                
                /**
                 *单表用 
                 * @param {Object} formId
                 */
               reLoadTable: function (formId){
                    if($('#column') != null){
                        $('#column').val('');
                    }
                    if($('#sort') != null){
                        $('#sort').val('');
                    }
                    
                    jQuery('#'+formId).submit();
                },
                /**
                 *提交表单用 
                 */
               refresh_table: function (){
                    this.reLoadTable('queryFrom');
                }

        });
    
})(jQuery);

// function sortTable(str,formId,fun){
	// var $column = $('#column','#'+formId);
	// //说明字段相同，则更改排序规则
	// var $sort = $('#sort','#'+formId);
	// if($column.val() == str){
		// if($sort.val() == 'desc'){
			// $sort.val('asc');
		// }
// 		
		// else if($sort.val() == 'asc'){
			// $sort.val('desc');
		// }
	// }
// 	
	// //如果是不同字段，则默认倒序
	// else{
		// $sort.val('desc');
	// }
	// $column.val(str);
	// fun();
// }
// 
// function showHidden(){
	// if($('#search_img').attr('src') == 'images/searchhiddleicon.jpg'){
		// $('#searcharea').hide();
		// $('#search_img').attr('src','images/searchhiddleicon_down.jpg');
	// }else{
		// $('#searcharea').show();
		// $('#search_img').attr('src','images/searchhiddleicon.jpg');
	// }
// 	
// }
// 
// /**
 // *提交表单用 
 // */
// function refresh_table(){
	// reLoadTable('queryFrom');
// }
// 
// /**
 // * 多表用
 // * 用异步刷新生替换表单提交 
 // * @param {Object} formId
 // * @param {Object} divId
 // * @param {Object} url
 // */
// function load(formId,divId,url){
    // $.post(url,$('#'+formId).serialize(),function(data){
        // $('#'+divId).html(data);
    // });
// }
// 
// function loadData(curPage,pagesize,formId,divId,url,callbackFun){
    // if(!!curPage){
        // $('#curPage','#'+formId).val(curPage);
    // }
    // if(!!pagesize){
        // $('#pagesize','#'+formId).val(pagesize);
    // }
    // load(formId,divId,url);
    // if(callbackFun){
        // callbackFun();
    // }
// }
// /**
 // *隐藏表 
 // * @param {Object} id
 // */
// function hidden_table(id){
    // var $o = $('#'+id);
    // if(!!$o.data('hide')){
        // $o.show();
        // $o.data('hide',false);
    // }else{
        // $o.hide();
        // $o.data('hide',true);
    // }
// }
// 
// /**
 // *全选 
 // * @param {Object} tag
 // */
// function selectAll(tag){
//     
    // // $.each($('#divc input[type="checkbox"]'), function(key, value){
        // // value.checked = tag;
    // // });
    // $.each($('input[type="checkbox"]',$(tag).closest('.divb').has('.miantable')), function(key, value){
        // value.checked = tag.checked;
    // });
// }
// 
// //获取选中行的ID数组
// function getSelectedRowID(id) {
	// var newIdArr = '';
	// $.each($('input[type="checkbox"]','#'+id), function(key, value){
	    // if(value.name != null && value.name != ''){
	    	// if(value.checked){
	    		// var str = value.parentNode.parentNode.id;
	    		// newIdArr = newIdArr + str + ',';
	    	// }
	    // }
	// });
	// if(newIdArr != ''){
		// newIdArr = newIdArr.substring(0, newIdArr.length - 1);
		// var arr = newIdArr.split(',');
		// return arr;
	// }else{
		// return new Array(0);
	// }
// }
// 
// function export_table(){
// 	
// }
// 
// /**
 // *单表用 
 // * @param {Object} formId
 // */
// function reLoadTable(formId){
	// if($('#column') != null){
		// $('#column').val('');
	// }
	// if($('#sort') != null){
		// $('#sort').val('');
	// }
// 	
	// jQuery('#'+formId).submit();
// }