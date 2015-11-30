/**
 * 输入框获取焦点时清空默认内容，失去焦点时判断，若为空，则还原为默认内容
 * */
function clearInput(event,obj){
    var e = event; 
    var defVal = obj.defaultValue;
    
    if(e.type=='focus'){
        if($(obj).val()==defVal){
            $(obj).val("");
        }
    }
    if(e.type=='blur'){
        if($(obj).val() == ''){
            $(obj).val(defVal);
        }
    }
    
}