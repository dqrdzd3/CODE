
;(function($){
    // 弹出层的选项卡
    $.selectTag = function(showContent,selfObj) {
        
    // 20130328 mfb： 添加验证未通过标签样式时修改   
        $("li", $(selfObj).parent().parent()).removeClass("selectTag");
        $(selfObj).parent().addClass('selectTag');
        
        var con = $(selfObj).parent().parent().parent();
        $('.tagContent', con).hide();
        $("#" + showContent, con).show();
        
        if(window.resizeGrids){
            resizeGrids();
        }
        
    };
})(jQuery);

