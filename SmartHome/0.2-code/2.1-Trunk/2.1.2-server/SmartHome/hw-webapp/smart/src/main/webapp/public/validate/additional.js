jQuery.validator.addMethod("integer", function(value, element) {
    return this.optional(element) || /^-?[1-9]\d*|0$/.test(value);
}, "请输入正确的整数");

jQuery.validator.addMethod("positive", function(value, element) {
    return this.optional(element) || /^[1-9]\d*$/.test(value);
}, "请输入正确的正整数");

jQuery.validator.addMethod("negative", function(value, element) {
    return this.optional(element) || /^-[1-9]\d*$/.test(value);
}, "请输入正确的负整数");

jQuery.validator.addMethod("notpositive", function(value, element) {
    return this.optional(element) || /^-[1-9]\d*|0$/.test(value);
}, "请输入正确的非正整数");

jQuery.validator.addMethod("notnegative", function(value, element) {
    return this.optional(element) || /^[1-9]\d*|0$/.test(value);
}, "请输入正确的非负整数");

jQuery.validator.addMethod("real", function(value, element) { // 实数
    return this.optional(element) || /^([+-]?)\d*\.?\d+$/.test(value);
}, "请输入正确的数字");

jQuery.validator.addMethod("decimal", function(value, element) {
    return this.optional(element) || /^([+-]?)\d*\.\d+$/.test(value);
}, "请输入正确的浮点数");

jQuery.validator.addMethod("color", function(value, element) {
    return this.optional(element) || /^[a-fA-F0-9]{6}$/.test(value);
}, "请输入正确的颜色代码，如：ff0000");

jQuery.validator.addMethod("chinese", function(value, element) {
    return this.optional(element) || /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/.test(value);
}, "请输入中文");

jQuery.validator.addMethod("ascii", function(value, element) {
    return this.optional(element) || /^[\x00-\xFF]+$/.test(value);
}, "请输入ACSII字符");

jQuery.validator.addMethod("zipcode", function(value, element) {
    return this.optional(element) || /^\d{6}$/.test(value);
}, "请输入正确的邮政编码，如450000");

jQuery.validator.addMethod("mobile", function(value, element) {
    return this.optional(element) || /^(13|15|18)[0-9]{9}$/.test(value);
}, "请输入正确的手机号");

jQuery.validator.addMethod("ip4", function(value, element) {
    return this.optional(element) || /^(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)$/.test(value);
}, "请输入正确的ip地址");

jQuery.validator.addMethod("notempty", function(value, element) {
    return this.optional(element) || /^\S+$/.test(value);
}, "不允许为空");

jQuery.validator.addMethod("picture", function(value, element) {
    return this.optional(element) || /(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$/.test(value);
}, "允许的图片格式：jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga");

jQuery.validator.addMethod("rar", function(value, element) {
    return this.optional(element) || /(.*)\.(rar|zip|7zip|tgz)$/.test(value);
}, "允许的压缩文件格式：rar|zip|7zip|tgz");

jQuery.validator.addMethod("qq", function(value, element) {
    return this.optional(element) || /^[1-9]*[1-9][0-9]*$/.test(value);
}, "请输入正确的QQ号码");

jQuery.validator.addMethod("phone", function(value, element) {
    return this.optional(element) || /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(value);
}, "请输入正确的电话号码，如：020-66661818");

jQuery.validator.addMethod("username", function(value, element) {
    return this.optional(element) || /^\w+$/.test(value);
}, "只允许由字母、数字或下划线组成的字符串");

jQuery.validator.addMethod("letter", function(value, element) {
    return this.optional(element) || /^[A-Za-z]+$/.test(value);
}, "请输入英文字母");

jQuery.validator.addMethod("uppercase", function(value, element) {
    return this.optional(element) || /^[A-Z]+$/.test(value);
}, "请输入大写字母");

jQuery.validator.addMethod("lowercase", function(value, element) {
    return this.optional(element) || /^[a-z]+$/.test(value);
}, "请输入小写字母");

jQuery.validator.addMethod("idcard", function(value, element) {
    return this.optional(element) || /^[1-9]([0-9]{14}|[0-9]{17})$/.test(value);
}, "请输入正确的身份证");

jQuery.validator.addMethod("date2", function(value, element) {
    var check = false;
    var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r != null) {
        var d = new Date(r[1], r[3] - 1, r[4]);
        check = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
    }
    return this.optional(element) || check;
}, "请输入正确的日期，如：2009-06-23");

jQuery.validator.addMethod("time", function(value, element) {
    var check = false;
    var a = value.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a != null) {
        if (a[1] <= 24 && a[3] <= 60 && a[4] <= 60) {
            check = true;
        }
    }
    return this.optional(element) || check;
}, "请输入正确的时间，如：13:04:06");

jQuery.validator.addMethod("datetime", function(value, element) {
    var check = false;
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = value.match(reg);
    if (r != null) {
        var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
        check = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
    }
    return this.optional(element) || check;
}, "请输入正确的日期时间，如：2003-12-05 13:04:06");

jQuery.validator.addMethod("byterangelength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("输入值的长度必须在{0}-{1}个字节之间(一个中文字算2个字节)"));

jQuery.validator.addMethod("bytemaxlength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length <= param);
}, $.validator.format("输入值的长度最多为{0}个字节(一个中文字算2个字节)"));

jQuery.validator.addMethod("byteminlength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param);
}, $.validator.format("输入值的长度至少为{0}个字节(一个中文字算2个字节)"));

//曾 2012-6-8 验证是否小数点后超过了3位
jQuery.validator.addMethod("savedecimalto2", function(value, element) {
    return this.optional(element) || !/^\d+\.\d{3,}/.test(value);
}, $.validator.format("输入值的小数点后只能保留两位"));

//曾 2012-6-8 验证正浮点小数或正整数
jQuery.validator.addMethod("positiveDouble", function(value, element) {
    return this.optional(element) || /^(\d+\.\d|[1-9]\d*)/.test(value);
}, $.validator.format("输入值的只能是正整数或浮点数"));

//曾 2012-6-8 验证不为零
jQuery.validator.addMethod("zero", function(value, element) {
    return this.optional(element) || !/^0*$/.test(value);
}, $.validator.format("输入值不能为零"));

//lgx 2012-6-19 验证输入日期不小于当前日期
jQuery.validator.addMethod("datecmp", function(value, element) {
    var check = false;
    var cDate = new Date();
    if(value!=""){
    	var dateArray = value.split(/[\s,:,-]/);
		var dYear = dateArray[0];
		var dMonth = dateArray[1];
		var dDate = dateArray[2];
		var date = new Date();
		date.setYear(dYear);
		date.setMonth(dMonth-1);
		date.setDate(dDate);
	    if(date>= cDate){
	    	check=true;
	    }
    }
    return this.optional(element) || check;
}, $.validator.format("输入日期不能小于当前日期"));

jQuery.validator.addMethod("isTelephone", function(value, element) {
    return (this.optional(element) || /^(13|15|18)[0-9]{9}$/.test(value) || this.optional(element) || /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(value));
}, "请输入正确的电话信息");