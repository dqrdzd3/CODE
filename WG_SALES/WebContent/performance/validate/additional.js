jQuery.validator.addMethod("integer", function(value, element) {
    return this.optional(element) || /^-?[1-9]\d*|0$/.test(value);
}, "��������ȷ������");

jQuery.validator.addMethod("positive", function(value, element) {
    return this.optional(element) || /^[1-9]\d*$/.test(value);
}, "��������ȷ��������");

jQuery.validator.addMethod("negative", function(value, element) {
    return this.optional(element) || /^-[1-9]\d*$/.test(value);
}, "��������ȷ�ĸ�����");

jQuery.validator.addMethod("notpositive", function(value, element) {
    return this.optional(element) || /^-[1-9]\d*|0$/.test(value);
}, "��������ȷ�ķ�������");

jQuery.validator.addMethod("notnegative", function(value, element) {
    return this.optional(element) || /^[1-9]\d*|0$/.test(value);
}, "��������ȷ�ķǸ�����");

jQuery.validator.addMethod("real", function(value, element) { // ʵ��
    return this.optional(element) || /^([+-]?)\d*\.?\d+$/.test(value);
}, "��������ȷ������");

jQuery.validator.addMethod("decimal", function(value, element) {
    return this.optional(element) || /^([+-]?)\d*\.\d+$/.test(value);
}, "��������ȷ�ĸ�����");

jQuery.validator.addMethod("color", function(value, element) {
    return this.optional(element) || /^[a-fA-F0-9]{6}$/.test(value);
}, "��������ȷ����ɫ���룬�磺ff0000");

jQuery.validator.addMethod("chinese", function(value, element) {
    return this.optional(element) || /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/.test(value);
}, "����������");

jQuery.validator.addMethod("ascii", function(value, element) {
    return this.optional(element) || /^[\x00-\xFF]+$/.test(value);
}, "������ACSII�ַ�");

jQuery.validator.addMethod("zipcode", function(value, element) {
    return this.optional(element) || /^\d{6}$/.test(value);
}, "��������ȷ���������룬��450000");

jQuery.validator.addMethod("mobile", function(value, element) {
    return this.optional(element) || /^(13|15|18)[0-9]{9}$/.test(value);
}, "��������ȷ���ֻ���");

jQuery.validator.addMethod("ip4", function(value, element) {
    return this.optional(element) || /^(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)\.(25[0-5]|2[0-4]\d|[0-1]\d{2}|[1-9]?\d)$/.test(value);
}, "��������ȷ��ip��ַ");

jQuery.validator.addMethod("notempty", function(value, element) {
    return this.optional(element) || /^\S+$/.test(value);
}, "������Ϊ��");

jQuery.validator.addMethod("picture", function(value, element) {
    return this.optional(element) || /(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$/.test(value);
}, "�����ͼƬ��ʽ��jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga");

jQuery.validator.addMethod("rar", function(value, element) {
    return this.optional(element) || /(.*)\.(rar|zip|7zip|tgz)$/.test(value);
}, "�����ѹ���ļ���ʽ��rar|zip|7zip|tgz");

jQuery.validator.addMethod("qq", function(value, element) {
    return this.optional(element) || /^[1-9]*[1-9][0-9]*$/.test(value);
}, "��������ȷ��QQ����");

jQuery.validator.addMethod("phone", function(value, element) {
    return this.optional(element) || /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(value);
}, "��������ȷ�ĵ绰���룬�磺020-66661818");

jQuery.validator.addMethod("username", function(value, element) {
    return this.optional(element) || /^\w+$/.test(value);
}, "ֻ��������ĸ�����ֻ��»�����ɵ��ַ���");

jQuery.validator.addMethod("letter", function(value, element) {
    return this.optional(element) || /^[A-Za-z]+$/.test(value);
}, "������Ӣ����ĸ");

jQuery.validator.addMethod("uppercase", function(value, element) {
    return this.optional(element) || /^[A-Z]+$/.test(value);
}, "�������д��ĸ");

jQuery.validator.addMethod("lowercase", function(value, element) {
    return this.optional(element) || /^[a-z]+$/.test(value);
}, "������Сд��ĸ");

jQuery.validator.addMethod("idcard", function(value, element) {
    return this.optional(element) || /^[1-9]([0-9]{14}|[0-9]{17})$/.test(value);
}, "��������ȷ�����֤");

jQuery.validator.addMethod("date2", function(value, element) {
    var check = false;
    var r = value.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r != null) {
        var d = new Date(r[1], r[3] - 1, r[4]);
        check = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
    }
    return this.optional(element) || check;
}, "��������ȷ�����ڣ��磺2009-06-23");

jQuery.validator.addMethod("time", function(value, element) {
    var check = false;
    var a = value.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
    if (a != null) {
        if (a[1] <= 24 && a[3] <= 60 && a[4] <= 60) {
            check = true;
        }
    }
    return this.optional(element) || check;
}, "��������ȷ��ʱ�䣬�磺13:04:06");

jQuery.validator.addMethod("datetime", function(value, element) {
    var check = false;
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
    var r = value.match(reg);
    if (r != null) {
        var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
        check = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
    }
    return this.optional(element) || check;
}, "��������ȷ������ʱ�䣬�磺2003-12-05 13:04:06");

jQuery.validator.addMethod("byterangelength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("����ֵ�ĳ��ȱ�����{0}-{1}���ֽ�֮��(һ����������2���ֽ�)"));

jQuery.validator.addMethod("bytemaxlength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length <= param);
}, $.validator.format("����ֵ�ĳ������Ϊ{0}���ֽ�(һ����������2���ֽ�)"));

jQuery.validator.addMethod("byteminlength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param);
}, $.validator.format("����ֵ�ĳ�������Ϊ{0}���ֽ�(һ����������2���ֽ�)"));

//�� 2012-6-8 ��֤�Ƿ�С����󳬹���3λ
jQuery.validator.addMethod("savedecimalto2", function(value, element) {
    return this.optional(element) || !/^\d+\.\d{3,}/.test(value);
}, $.validator.format("����ֵ��С�����ֻ�ܱ�����λ"));

//�� 2012-6-8 ��֤������С����������
jQuery.validator.addMethod("positiveDouble", function(value, element) {
    return this.optional(element) || /^(\d+\.\d|[1-9]\d*)/.test(value);
}, $.validator.format("����ֵ��ֻ�����������򸡵���"));

//�� 2012-6-8 ��֤��Ϊ��
jQuery.validator.addMethod("zero", function(value, element) {
    return this.optional(element) || !/^0*$/.test(value);
}, $.validator.format("����ֵ����Ϊ��"));

//lgx 2012-6-19 ��֤�������ڲ�С�ڵ�ǰ����
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
}, $.validator.format("�������ڲ���С�ڵ�ǰ����"));

jQuery.validator.addMethod("isTelephone", function(value, element) {
    return (this.optional(element) || /^(13|15|18)[0-9]{9}$/.test(value) || this.optional(element) || /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(value));
}, "��������ȷ�ĵ绰��Ϣ");