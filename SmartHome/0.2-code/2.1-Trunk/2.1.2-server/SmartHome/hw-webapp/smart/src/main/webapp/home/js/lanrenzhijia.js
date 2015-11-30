
$(function(){
	$("img[original]").lazyload({ placeholder:"http://www.lanrenzhijia.com/images/color3.gif" });
});

function lazyloadForPart(container){
    container.find('img').each(function(){
        var original = $(this).attr("original");
        if (original) {
            $(this).attr('src', original).removeAttr('original');
        }
    });
}


$(function(){           
	
	var navBox = $('.tabTagBox'), navList = $('.tabTagList'), navs = navList.children('li'), upBtn = $('.uPrev'), downBtn = $('.dNext'), contentBoxs = $('.tabcon');
	var navBox1 = $('.tabTagBox1'), navList1 = $('.tabTagList1'), navs1 = navList1.children('li'),  contentBoxs1 = $('.tabcon1');
	var navBox2 = $('.tabTagBox2'), navList2 = $('.tabTagList2'), navs2 = navList2.children('li'),  contentBoxs2 = $('.tabcon2');
	var navBox3 = $('.tabTagBox3'), navList3 = $('.tabTagList3'), navs3 = navList3.children('li'),  contentBoxs3 = $('.tabcon3');
	var navBox4 = $('.tabTagBox4'), navList4 = $('.tabTagList4'), navs4 = navList4.children('li'),  contentBoxs4 = $('.tabcon4');
	var navBox5 = $('.tabTagBox5'), navList5 = $('.tabTagList5'), navs5 = navList5.children('li'),  contentBoxs5 = $('.tabcon5');
	var navBox6 = $('.tabTagBox6'), navList6 = $('.tabTagList6'), navs6 = navList6.children('li'),  contentBoxs6 = $('.tabcon6');

	var opts = {
		moveH: 58,
		moveSpeed: 200,
		curMoveH: 0,
		curSumH: 0,
		curNavIndex: 0
	}
	
	opts.curSumH = (opts.moveH * navs.size()) - navBox.height();
	
	var navToContentBox = function () {
		navs.removeClass('current');
		
		
		
			_currentHistoryType = opts.curNavIndex;
			setHistoryData();

		contentBoxs.hide().eq(opts.curNavIndex).show();
	}

	var navToContentBox1 = function () {
		navs1.removeClass('current1');
		
		_currentHistoryType = opts.curNavIndex;
		setHistoryData();
		contentBoxs1.hide().eq(opts.curNavIndex).show();
	}
	var navToContentBox2 = function () {
		navs2.removeClass('current2');
		_currentHistoryType = opts.curNavIndex;
		setHistoryData();
		contentBoxs2.hide().eq(opts.curNavIndex).show();
	}
	var navToContentBox3 = function () {
		navs3.removeClass('current3');
		_currentHistoryType = opts.curNavIndex;
		setHistoryData();
		contentBoxs3.hide().eq(opts.curNavIndex).show();
	}
	var navToContentBox4 = function () {
		navs4.removeClass('current4');
		
		_currentHistoryType = opts.curNavIndex;
		setHistoryData();
		contentBoxs4.hide().eq(opts.curNavIndex).show();
	}
	var navToContentBox5 = function () {
		navs5.removeClass('current5');
		
		_currentHistoryType = opts.curNavIndex;
		setHistoryData();
		contentBoxs5.hide().eq(opts.curNavIndex).show();
	}
	var navToContentBox6 = function () {
		navs6.removeClass('current6');
		
		_currentHistoryType = opts.curNavIndex;
		setHistoryData();
		contentBoxs6.hide().eq(opts.curNavIndex).show();
	}
	
	var navMove = function () {
		
		var _arg = arguments.length > 0 ? arguments[0] : '';
		
		if (_arg === 'up') {
			if (-opts.curSumH === opts.curMoveH) {
				alert('温馨提示：其他行业敬请期待！');
				return;
			}
			opts.curMoveH -= opts.moveH;
			opts.curNavIndex += 1;
		}
		
		if (_arg === 'down') {
			if (opts.curMoveH === 0) {
				alert('温馨提示：其他行业敬请期待！');
				return;
			}
			opts.curMoveH += opts.moveH;
			opts.curNavIndex -= 1;
		}
		
		navToContentBox();
		
		navs.eq(opts.curNavIndex).addClass('current');
		navList.animate({ top: opts.curMoveH + 'px' }, opts.moveSpeed);
		lazyloadForPart(contentBoxs);
	}
	
	upBtn.click(function () {
		navMove('down');
	});
	
	downBtn.click(function () {
		navMove('up');
	});
	
	navs.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox();
		$(this).addClass('current');
		lazyloadForPart(contentBoxs);
	});
	navs1.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox1();
		$(this).addClass('current1');
		lazyloadForPart(contentBoxs1);
	});

	
	navs2.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox2();
		$(this).addClass('current2');
		lazyloadForPart(contentBoxs2);
	});
	navs3.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox3();
		$(this).addClass('current3');
		lazyloadForPart(contentBoxs3);
	});
	navs4.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox4();
		$(this).addClass('current4');
		lazyloadForPart(contentBoxs4);
	});
	
	
	navs5.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox5();
		$(this).addClass('current5');
		lazyloadForPart(contentBoxs5);
	});
	
	navs6.click(function () {
		opts.curNavIndex = $(this).index();
		navToContentBox6();
		$(this).addClass('current6');
		lazyloadForPart(contentBoxs6);
	});
});