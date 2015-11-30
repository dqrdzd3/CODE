/**
 * 处理带有readonly属性的只读框不能获取焦点
 */
$(function(){
    $("form input[readonly]").bind("focus click",function(event){
        $(this).blur();
        event.preventDefault();        event.stopPropagation();
    });
    $("form textarea[readonly]").bind("focus click",function(event){
        $(this).blur();
        event.preventDefault();        event.stopPropagation();
    });
});