//
//  DeviceDataConstant.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-11-3.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "DeviceDataConstant.h"

@implementation DeviceDataConstant

// 气体类型（0:温度，1:湿度，2:co2，3:pm2.5，4:voc，5:c6h6，6:ch2o,7:酒精，8:co,9:ch4）
//0  低报   1正常   2 高报
const int CO2_THRESHOLD = 800;
const int TEMPERATURE_THRESHOLD = 25;
const int HUMIDITY_THRESHOLD = 45;
const int PM25_THRESHOLD = 200;
const int PM25_VERY_GOOD = 35;
const int PM25_GOOD = 75;
const int PM25_WEAK = 115;
const int PM25_BAD = 150;
const int PM25_VERY_BAD = 250;
const int PM25_HIGH_THRESHOLD = 400;
const int CH4_THRESHOLD = 7;
const int CO_THRESHOLD = 100;
const int VOC_NORMAL = 300;
const int VOC_MIDDLE = 500;
const int VOC_HIGH = 1000;
NSString *const EXPRESS_CH4_DANGEROUS = @"天然气泄漏，危险";
NSString *const EXPRESS_CH4_NORMAL = @"天然气正常";
NSString *const EXPRESS_CO_DANGEROUS = @"一氧化碳泄漏，危险";
NSString *const EXPRESS_CO_NORMAL = @"一氧化碳正常";
NSString *const EXPRESS_NO_DATA = @"当前传感器没有上传数据，请检查传感器是否联网成功";
NSString *const EXPRESS_TEMPERATURE_NORMAL = @"主人，您的小窝待着实在太舒服了，我都不想出门了。";
NSString *const EXPRESS_TEMPERATURE_HOT = @"主淫！果果要发芽啦，热死了咩！";
NSString *const EXPRESS_TEMPERATURE_COLD = @"主人啊，我都快冻感冒了啦，快给我那件厚衣服";
NSString *const EXPRESS_HUMIDITY_NORMAL = @"小主人，多亏了这里很合适的湿度呦，我的皮肤现在超级棒！";
NSString *const EXPRESS_HUMIDITY_OVER = @"主人，您的小窝太干燥了，你看我的皮肤都干裂了，过来帮我开加湿器呀！";
NSString *const EXPRESS_HUMIDITY_LESS = @"主人，湿度太大了，你看看我都萎靡不振的，都没精神跟你说话了。";
NSString *const EXPRESS_CO2_NORMAL = @"浓度正常，可以自由呼吸";
NSString *const EXPRESS_CO2_OVER = @"浓度有点高了，注意室内空气流通，植物在晚上也会释放二氧化碳。";
NSString *const EXPRESS_CO2_OVER_MORE = @"主银啊，浓度让我有点呼吸不顺畅啦！赶快来给我打开窗户 。";
NSString *const EXPRESS_PM25_NORMAL = @"主人，您的小窝清新宜人，沁人心脾，好好享受生活吧。";
NSString *const EXPRESS_PM25_OVER = @"还没有报表，请在适当的时候开窗透透气。";
NSString *const EXPRESS_PM25_OVER_MORE = @"主人，您的小窝烟雾缭绕，尘飞土扬，是时候打开空气净化器了";
NSString *const EXPRESS_VOC_NORMAL = @"小窝很健康哦。";
NSString *const EXPRESS_VOC_MIDDLE = @"中度风险，容易出现感官刺激，头痛、乏力和昏昏欲睡。";
NSString *const EXPRESS_VOC_HIGH = @"高危环境，可导致基因毒性和致癌性，影响染色体致其畸变。";
NSString *const EXPRESS_OUTLINE = @"设备没有数据上传,您家的网线被猫咪踢掉了吗?";


NSString *const SENSOR_HARDWARE_GAS_HINT = @"<font size=14>1) 用针戳一下[Radio]的<b>i</b><br>2) 如果[Air]的<b>i会闪烁</b>，则说明设备进入配网状态<br>3) 确认手机已连接Wi-Fi，点击<b>[下一步]</b></font>";
NSString *const SENSOR_HARDWARE_AIR_HINT = @"<font size=14>1) 点击<b>圆形按钮</b>切换到<b>云形状</b>的界面<br>2) 在此页面双击<b>圆形按钮</b>进入Wi-Fi配网模式</font>";
NSString *const SENSOR_WIFI_GAS_HINT = @"<font size=14>1) 输入Wi-Fi密码，点击下方<b>[设备配网]</b>按钮<br>2) <b>[Air]的i常亮</b>说明报警器已经连接上了路由器<br><font color='red'>*配网失败请重启设备和APP，并重新绑定设备</font></font>";
NSString *const SENSOR_WIFI_AIR_HINT = @"<font size=14>1) 输入Wi-Fi密码，点击下方<b>[设备配网]</b>按钮<br>2) 如果配网成功，点阵屏的Wi-Fi图表会消失<br><font color='red'>*配网失败请重启设备和APP，并重新绑定设备</font></font>";
NSString *const SENSOR_WIFI_GUIDE = @"<font size=14>1) 点击<b>[扫一扫]</b>扫描设备或者包装箱上的<b>[设备ID]</b>\n2) 点击<b>[绑定设备]</b>，成功后点击<b>[下一步]</b></font>";

 NSString *const SENSOR_HARDWARE_POWER_GAS_HINT=@"<font size=14>1) 如上图所示，向右旋转插入转接头，并确认牢固<br>2) 将报警器插入电源，如果正常会发光</font>";
 NSString *const SENSOR_HARDWARE_POWER_AIR_HINT=@"<font size=14>1) usb下口朝下，插入[空气电台]背后的电源口<br>2) 点击[空气电台]正面的圆形按钮启动设备</font>";
 NSString *const SENSOR_WIFI_GAS_SUCCESS_HINT=@"<font size=14>1) 报警器插电后有三分钟时间预热，期间<b>不能</b>进行燃气检测<br>2) 如果报警器的[环形指示灯]变为呼吸灯(亮到暗的逐渐变化)状态，说明报警器进入可检测状态</font>";
@end
