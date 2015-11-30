function getDate(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate() + AddDayCount);// 获取AddDayCount天后的日期
	return dd;
}

function getWeekStr(date) {
	if (date.getDay() == 0) {
		return "周日";
	}
	if (date.getDay() == 1) {
		return "周一";
	}
	if (date.getDay() == 2) {
		return "周二";
	}
	if (date.getDay() == 3) {
		return "周三";
	}
	if (date.getDay() == 4) {
		return "周四";
	}
	if (date.getDay() == 5) {
		return "周五";
	}
	if (date.getDay() == 6) {
		return "周六";
	}
}
/**
 * 获取上一周的名称
 * @returns {Array}
 */
function getLastWeek() {
	var weeks = new Array(7);
	var passedDate = null;
	for (var i = 0; i < 7; i++) {
		passedDate = getDate(i - 7);
		weeks[i] = getWeekStr(passedDate);
	}
	return weeks;
}