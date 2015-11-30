;(function($){
    $.hwBindBtn = function(type, fn, btnDivId){
        var idSltr = '#' + (btnDivId || 'operating');
        
        $('.li'+type,idSltr).unbind('click').click(fn);
    } 
})(jQuery);
