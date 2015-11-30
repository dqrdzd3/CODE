
//FCKConfig.AutoDetectLanguage = false ; 
FCKConfig.ToolbarSets["myself"] = [
	['Source','DocProps'],
	['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
	['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
	['Link','Unlink','Anchor'],
	['Image','Flash','Table','Rule','Smiley','SpecialChar','PageBreak'],
	'/',
	['Style','FontFormat','FontName','FontSize'],
	['TextColor','BGColor'],
	['FitWindow','ShowBlocks','-','About']		// No comma for the last row.
] ;

FCKConfig.FontNames		= '宋体;楷体_GB2312;黑体;Times New Roman;Verdana' ;

FCKConfig.EnterMode = 'br' ;			// p | div | br
FCKConfig.ShiftEnterMode = 'p' ;	// p | div | br

FCKConfig.SmileyPath	= FCKConfig.BasePath + 'images/smiley/msn/' ;
//配置要使用的图片
FCKConfig.SmileyImages	= ['gougou.gif','regular_smile.gif','sad_smile.gif','wink_smile.gif','teeth_smile.gif','confused_smile.gif','tounge_smile.gif','embaressed_smile.gif','omg_smile.gif','whatchutalkingabout_smile.gif','angry_smile.gif','angel_smile.gif','shades_smile.gif','devil_smile.gif','cry_smile.gif','lightbulb.gif','thumbs_down.gif','thumbs_up.gif','heart.gif','broken_heart.gif','kiss.gif','envelope.gif'] ;
//每行要显示几个
FCKConfig.SmileyColumns = 8 ;
//显示图片窗口的宽度和高度
FCKConfig.SmileyWindowWidth		= 200 ;
FCKConfig.SmileyWindowHeight	= 600 ;

FCKConfig.ImageUploadAllowedExtensions	= ".(jpg|gif|jpeg|png|bmp|doc|docx|xls|abc|text)$"
