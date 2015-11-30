//
//  Macro.h
//  PuyangProduct
//
//  Created by json on 14-5-4.
//  Copyright (c) 2014年 wang biao. All rights reserved.
//

#ifndef Macro_h
#define Macro_h

#define BottomViewHeight 76
#define TabBarHeight 50
#define  keyboardHeight 216
#define  TopBarHeight 64

//获取折线数据时如果返回的是空值，则不画折线，那么为了识别空值用此code代表
#define OffLineChartLineCode @"-100000.0"


#pragma mark ---- File  functions
#define PATH_OF_APP_HOME    NSHomeDirectory()
#define PATH_OF_TEMP        NSTemporaryDirectory()
#define PATH_OF_DOCUMENT    [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0]

#define FULLSCREENHEIGHT [[UIScreen mainScreen] bounds].size.height
#define SCREENHEIGHT ([[UIScreen mainScreen] bounds].size.height - [[UIApplication sharedApplication] statusBarFrame].size.height) //去除状态栏的高度的屏幕高度
#define SCREENWIDTH [[UIScreen mainScreen] bounds].size.width//屏幕宽度
#define STATUSBARHEIGHT [[UIApplication sharedApplication] statusBarFrame].size.height//状态栏高度

#define SYSTEM_VERSION_EQUAL_TO(v)                  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch]  NSOrderedSame)
#define SYSTEM_VERSION_GREATER_THAN(v)              ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch]  NSOrderedDescending)
#define SYSTEM_VERSION_GREATER_THAN_OR_EQUAL_TO(v)  ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] ! NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN(v)                 ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch]  NSOrderedAscending)
#define SYSTEM_VERSION_LESS_THAN_OR_EQUAL_TO(v)     ([[[UIDevice currentDevice] systemVersion] compare:v options:NSNumericSearch] ! NSOrderedDescending)



#define GAP_SMALL    5  //小间距
#define GAP_BIG      10 //大间距

#define Color_BaiAlpha           [UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:0.95]
#define Color_Bai                [UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:255.0/255.0 alpha:1.0]
#define Color_Hong               [UIColor colorWithRed:190.0/255.0 green:2.0/255.0 blue:1.0/255.0 alpha:1.0]

#define Color_LightGray          [UIColor colorWithRed:240.0/255.0 green:240.0/255.0 blue:240.0/255.0 alpha:1.0]
#define Color_MiddleLightGray    [UIColor colorWithRed:205.0/255.0 green:205.0/255.0 blue:205.0/255.0 alpha:1.0]
#define Color_MiddleGray         [UIColor colorWithRed:107.0/255.0 green:121.0/255.0 blue:136.0/255.0 alpha:1.0]
#define Color_Gray               [UIColor colorWithRed:51.0/255.0 green:51.0/255.0 blue:51.0/255.0 alpha:1.0]
#define Color_LineGray           [UIColor colorWithRed:176.0/255.0 green:175.0/255.0 blue:175.0/255.0 alpha:1.0]
#define Color_Yellow             [UIColor colorWithRed:255.0/255.0 green:255.0/255.0 blue:0.0/255.0 alpha:1.0]
#define Color_Blue               [UIColor colorWithRed:44/255.0 green:111/255.0 blue:190.0/255.0 alpha:1.0]

#define Color_Hei                [UIColor colorWithRed:0.0/255.0 green:0.0/255.0 blue:0.0/255.0 alpha:1.0]
#define Color_MiddleHei          [UIColor colorWithRed:40.0/255 green:40.0/255 blue:40.0/255 alpha:1.0]
#define Color_BgColor            [UIColor colorWithRed:247.0/255.0 green:247.0/255.0 blue:247.0/255.0 alpha:1.0]

#define Color_TopButtonNomal     [UIColor colorWithRed:102.0/255  green:103.0/255 blue:96.0/255 alpha:1.0]
#define Color_HeadImageBg        [UIColor colorWithRed:246.0/255 green:245.0/255 blue:243.0/255 alpha:1.0]


////////////////////////////////////////////////////////////////////////////////

#define APP_ID                                 @"937313624"
//#define SECTION_STRING @"01010101010100"
//#define SECTION_STRING @"11010101010101010"
#define SECTION_STRING                         @"10101010101011"


#define HOME            @"主页面"
#define THEME           @"更换主题"
#define LOGOUT          @"注销登录"
#define FEEDBACK        @"用户反馈"
#define PROFILE         @"用户信息"
#define TEST            @"测试"
#define SETTINGS        @"配置中心"

#define ACCOUNT_SETTING @"账号管理"
#define PUSH_SETTING    @"推送设置"
#define DEV_LIST        @"团队成员"
#define HELP_CENTER     @"帮助中心"
#define ABOUT           @"关于"

#define HOME_NAME_CO2         @"二氧化碳"
#define HOME_NAME_TEMPERATURE @"温度"
#define HOME_NAME_HUMIDITY    @"湿度"
#define HOME_NAME_C6H6        @"苯"
#define HOME_NAME_CH2O        @"甲醛"
/** 服务端解决方案要用 */
#define HOME_CH2O_TYPE        @"6"
#define HOME_NAME_PM25        @"Pm2.5"
#define HOME_NAME_CO          @"一氧化碳"
/** 服务端解决方案要用 */
#define HOME_CO_TYPE          @"8"
#define HOME_NAME_CH4         @"天然气"
#define HOME_NAME_ALCOHOL     @"酒精"
/** 服务端解决方案要用 */
#define HOME_ALCOHOL_TYPE     @"7"

#define HOME_UNIT_NULL           @""
#define HOME_UNIT_CO2            @"ppm"
#define HOME_UNIT_TEMPERATURE    @"℃"
#define HOME_UNIT_HUMIDITY       @"RH%"
#define HOME_UNIT_C6H6           @"μg/m3"
#define HOME_UNIT_CH2O           @"mg/m3"
#define HOME_UNIT_PM25           @"μg/m3"
#define HOME_UNIT_ALCOHOL        @"μg/m3"
#define HOME_UNIT_CO             @"ppm"
#define HOME_UNIT_CH4            @"LEL"

#define CO_50              50
#define CO_50_CONTENT      @"成年人置身其中所允许的最大含量"
#define CO_200             200
#define CO_200_CONTENT     @"2-3小时后会有有轻微的头痛、头晕、恶心"
#define CO_400             400
#define CO_400_CONTENT     @"2小时内前额痛,3h 后将有生命危"
#define CO_800             800
#define CO_800_CONTENT     @"45分钟内头痛、恶心,2-3小时内死亡"
#define CO_1600            1600
#define CO_1600_CONTENT    @"20分钟内头痛、恶心,1小时内死亡"

#define C0_CONTENT_STATUS_N          @"正常"
#define C0_CONTENT_STATUS_D          @"危险"
#define C0_CONTENT_STATUS_DD         @"非常危险"

/** 0.06～0.07mg/m3 (0.05～0.06ppm)时，儿童发生气喘病 */
#define CH2O_NORMAL_CONTENT          @"保持的不错，注意通风"
#define CH2O_NORMAL_VALUE            0.06f
#define CH2O_MIDDLE_CONTENT          @"儿童发生轻微气喘"
#define CH2O_MIDDLE_VALUE            0.07f
#define CH2O_MIDDLE_UP_CONTENT       @"可刺激眼睛，引起流泪"
/** 当甲醛浓度在 0.12～1.2mg/m3时即 (0.1～1ppm) */
#define CH2O_HIGH_VALUE              0.5f
#define CH2O_HIGH_CONTENT            @"可引起咽喉不适或疼痛"
#define CH2O_DEAD_VALUE              0.6f
#define CH2O_HIGH_UP_CONTENT         @"能致支气管哮喘，基因突变，胎儿畸形"

#define CH2O_CONTENT_STATUS_N        @"正常"
#define CH2O_CONTENT_STATUS_D        @"危险"
#define CH2O_CONTENT_STATUS_DD       @"非常危险"

#define CH2O_1600                    1600
#define CH2O_1600_CONTENT            @"20分钟内头痛、恶心,1小时内死亡"

#define ALCOHOL_20                   20
#define ALCOHOL_80                   80

#define ALCOHOL_CONTENT_STATUS_N     @"正常"
#define ALCOHOL_CONTENT_STATUS_D     @"饮酒"
#define ALCOHOL_CONTENT_STATUS_DD    @"醉酒"
/*
 * 只要大于0.090mg/L，即20mg/100ml，告诉他饮酒 大于0.363mg/L,即80mg/100ml，告诉他醉酒
 */
#define ALCOHOL_CONTENT              @"不算酒驾"
#define ALCOHOL_CONTENT_20           @"酒后驾驶"
#define ALCOHOL_CONTENT_80           @"醉酒驾驶"

#define SENSOR_LINK_TRUE             @"已连接"
#define SENSOR_LINK_FALSE            @"未连接"

/** 甲醛最大值  */
#define CH2O_HIGH                  2
/** 酒精最大值 mg/L  2 */
#define ALCOHOL_MGL_HIGH           2
/** mg/100mL  400或440（跟当地标准有关) */
#define ALCOHOL_MG100ML_ZH_HIGH    440
#define ALCOHOL_MG100ML_US_HIGH    400
/** BAC%  0.4 */
#define ALCOHOL_BACH_HIGH          0.4f
/** BAC‰  4 */
#define ALCOHOL_BACT_HIGH          0.4f
/** g/L  4 */
#define ALCOHOL_GL_HIGH            4
/** 一氧化碳1000PPM */
#define CO_HIGH                    1000

#define HOME_ADDR       @"file:///android_asset/web/view/progressbar/PercentageLoader.htm"
#define HINT_CONTENT    @"未检测到威豆启动，请重新插入"


//威豆帧解析
#define HOME_NAME_CH2O_OB        2
#define HOME_NAME_ALCOHOL_OB     3
#define HOME_NAME_CO_OB          5

#define WEIDOU_TYPE_VER          176
#define WEIDOU_TYPE_DATA         168
#define WEIDOU_TYPE_POWER        80
#define WEIDOU_TYPE_USEOFTIME    88

#endif
