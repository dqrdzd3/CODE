;(function($,w){
    w.nameArr = [];
    w.pathArr = [];
    w.uploadOptions={
        'id' : 'upload',
        'auto' : false,//是否选择文件后自动上传
        'fileObjName':'upload',//上传时字段名称
        'removeCompleted':false,//上传完成后是否移除进度条
        'fileSizeLimit':'10MB',//上传文件大小限制
        'uploadLimit':1,//允许上传文件个数
        'fileTypeExts':'*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.pdf;*.zip;*.rar;*.jpg;*.gif;*.png;*.flv;*.3gp;',//上传文件类型限制
        'fileTypeDesc':'允许类型',
        'buttonClass' : 'scBtnClass',//按钮样式
        'progressData':'all',//进度条显示状态  
        'queueID':'queueDiv',//上传队列id
        'width' : 73, //按钮宽带
        'height': 28,//按钮高度
//      'buttonText' : '浏览',
        'transparent': false,
        'onUploadError':function(data){
            alert(jQuery.parseJSON(data).errMsg);
        }

    };
    w.onUploadSuccessFun = function (obj,file, data, response,baseURL){
        var jsonData =  jQuery.parseJSON(data);
        var idstr =  "#"+$(obj).attr('id');
        
        if(jsonData.errMsg){
            alert(jsonData.errMsg);
            
            $(idstr).uploadifySettings('uploader',baseURL+'/upload/fileupload!doUpLoad');
            return;
        }
        
        
        var srcValue = $(idstr).uploadifySettings('cancelImage');
        $("img[src*='"+srcValue+"']").first().remove();
        var name = jsonData.fileName;
        var path = jsonData.filePath;
        var imgPath ="";
        var fileName = "";
        var filePath = "";
        var fileid = file.id;
    
        if(name!=""){
            nameArr.push(name);
            pathArr.push(path);
        }
        fileName = nameArr.toString();
        filePath = pathArr.toString();
        
        if($("#filename")){
            $("#filename").val(fileName);
        }
        if($("#filepath")){
            $("#filepath").val(filePath);
        }
        
        var type = file.type;
        if(type && type != ""){
             // var imgType = $('#upload').uploadifySettings('imgType') || "";
            if(imgType && imgType.toString().indexOf(type.toLowerCase()) != -1){
                imgPath = jsonData.imagePath;
                if($("#imgpreDiv")){
                    $("#imgpreDiv").append($("<img>").attr({id:fileid,'alt':name,'src':baseURL+"/"+imgPath}));
                }
            }
        }
        
        //重新设置上传路径
         $(idstr).uploadifySettings('uploader',baseURL+'/upload/fileupload!doUpLoad');
        //注销上传实例
        // $(idstr).uploadifyDestroy();  

    };
    
    w.onUploadStartFun = function (obj,file,baseURL){
        var idstr =  "#"+$(obj).attr('id');
        
        var type = file.type;
        if(type && type != ""){
            var uploaderAction = $(idstr).uploadifySettings('uploader');//上传路径
             var vedioType = window.vedioType || "";
             var imgType = window.imgType || "";
            if(vedioType && vedioType.toString().indexOf(type.toLowerCase()) != -1){
                 $(idstr).uploadifySettings('uploader',baseURL+'/upload/fileupload!doUploadVedio');
            }else if(imgType && imgType.toString().indexOf(type.toLowerCase()) != -1){
                 $(idstr).uploadifySettings('uploader',baseURL+'/upload/fileupload!doUploadImage');
            }else if(uploaderAction == ''){
                 $(idstr).uploadifySettings('uploader',baseURL+'/upload/fileupload!doUpLoad');
            }
            
        }
    };
    
    w.onUploadErrorFun = function(obj){
        var idstr =  "#"+$(obj).attr('id');
                
        //注销上传实例
        // $(idstr).uploadifyDestroy();  
    }
    $.extend(
        
    );
    
})(jQuery,window);

/*
var uploadOptions = {
        'auto' : false,//是否选择文件后自动上传
		'fileObjName':'upload',//上传时字段名称
		'removeCompleted':false,//上传完成后是否移除进度条
		'fileSizeLimit':'10MB',//上传文件大小限制
		'uploadLimit':1,//允许上传文件个数
		'fileTypeExts':'*.doc;*.docx;*.ppt;*.pptx;*.xls;*.xlsx;*.pdf;*.zip;*.rar;*.jpg;*.gif;*.png;*.flv;*.3gp;',//上传文件类型限制
		'fileTypeDesc':'允许类型',
    	'buttonClass' : 'scBtnClass',//按钮样式
		'progressData':'all',//进度条显示状态	
		'queueID':'queueDiv',//上传队列id
    	'width' : 73, //按钮宽带
    	'height': 28,//按钮高度
//    	'buttonText' : '浏览',
 		'transparent': false

};

var nameArr = [];
var pathArr = [];

function onUploadSuccessFun(file, data, response,baseURL){
	var srcValue = $('#upload').uploadifySettings('cancelImage');
	$("img[src*='"+srcValue+"']").first().remove();
	var jsonData =  jQuery.parseJSON(data);
	var name = jsonData.fileName;
	var path = jsonData.filePath;
	var imgPath ="";
	var fileName = "";
	var filePath = "";
	var fileid = file.id;

	if(name!=""){
		nameArr.push(name);
		pathArr.push(path);
	}
	fileName = nameArr.toString();
	filePath = pathArr.toString();
	
	if($("#filename")){
		$("#filename").val(fileName);
	}
	if($("#filepath")){
		$("#filepath").val(filePath);
	}
	
	var type = file.type;
	if(type && type != ""){
		if(imgType && imgType.toString().indexOf(type.toLowerCase()) != -1){
			imgPath = jsonData.imagePath;
			if($("#imgpreDiv")){
				$("#imgpreDiv").append($("<img>").attr({id:fileid,'alt':name,'src':baseURL+"/"+imgPath}));
			}
		}
	}
}

function onUploadStartFun(file,baseURL){
	var type = file.type;
	if(type && type != ""){
		var uploaderAction = $('#upload').uploadifySettings('uploader');//上传路径
		var vedioType = window.vedioType || "";
		var imgType = window.imgType || "";
		if(vedioType && vedioType.toString().indexOf(type.toLowerCase()) != -1){
			 $('#upload').uploadifySettings('uploader',baseURL+'/upload/fileupload!doUploadVedio');
		}else if(imgType && imgType.toString().indexOf(type.toLowerCase()) != -1){
			 $('#upload').uploadifySettings('uploader',baseURL+'/upload/fileupload!doUploadImage');
		}else if(uploaderAction == ''){
			 $('#upload').uploadifySettings('uploader',baseURL+'/upload/fileupload!doUpLoad');
		}
		
	}
}*/