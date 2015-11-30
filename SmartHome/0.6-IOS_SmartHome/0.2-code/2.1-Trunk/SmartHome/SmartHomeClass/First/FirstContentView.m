//
//  FirstContentView.m
//  SmartHome
//
//  Created by apple on 14-11-12.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "FirstContentView.h"
#import "SensorDetail.h"
#import "DeviceDataConstant.h"
#import "Datetime.h"
#import <QuartzCore/QuartzCore.h>
#import "POP.h"

#define TopBarHeight    64
#define DOUWIDTH        125
#define DOUHEIGHT       165.5
#define Icon_alpha      0.85
#define Color_FirstBg               [UIColor colorWithRed:0.0/255 green:19.0/255 blue:36.0/255 alpha:0.0] //若背景需不透明把此处alpha设置一下就OK
#define Color_TypeButtonSelected    [UIColor colorWithRed:0.0/255 green:19.0/255 blue:36.0/255 alpha:0.6] //检测类型背景选中色
#define Color_FirstValueBg          [UIColor colorWithRed:0.0/255 green:19.0/255 blue:36.0/255 alpha:0.6] //空气值背景
extern NSString    *g_UserDirPath;
@implementation FirstContentView
@synthesize delegate;
@synthesize dicGasData;
@synthesize dicAirData;
@synthesize dicFLineData;
@synthesize aryFAllDeviceid;
@synthesize lineChartView;
@synthesize visiblePopTipViews;
@synthesize strlocalSoftVersion;
@synthesize strremoteSoftVersion;
@synthesize dicSensorDetail;

-(id)initWithFrame:(CGRect)frame alldata:(NSDictionary *)data AirDevice:(AirDevice *)airinfo GasDevice:(GasDevice *)gasinfo{
    self = [super initWithFrame:frame];
    if (self) {
        rect               = frame;
        iCartoonClickIndex = 0;
        fx_Info            = 30;
        bNight             = NO;
        fy_Device          = frame.size.height/2-TopBarHeight-107;
        dicAllData         = data;
        dicAirData         = airinfo;
        dicGasData         = gasinfo;
        bhardupdating      = NO;
        visiblePopTipViews = [[NSMutableArray alloc]init];
        
        dicFLineData = [[NSMutableDictionary alloc]init];
        // Do any additional setup after loading the view
    }
    return self;
}

-(void)initData:(NSDictionary *)data AirDevice:(AirDevice *)airinfo GasDevice:(GasDevice *)gasinfo{
    dicAllData = data;
    dicAirData = airinfo;
    dicGasData = gasinfo;
}
-(NSString *)Holiday{
    //NSLog(@"%@",[Datetime GetLunarDateTime]);
    //判断除夕
    NSDateFormatter *format = [[NSDateFormatter alloc] init];
    NSDate *newDate = [[NSDate alloc] initWithTimeIntervalSinceReferenceDate:([[NSDate date] timeIntervalSinceReferenceDate] + 24*3600)];
    [format setDateFormat:@"yyyy"];
    NSString *stenexty = [format stringFromDate:newDate];
    [format setDateFormat:@"MM"];
    NSString *stenextm = [format stringFromDate:newDate];
    [format setDateFormat:@"dd"];
    NSString *stenextd = [format stringFromDate:newDate];
    
    NSString *strlunarnext = [Datetime GetLunarDateByYear:[stenexty integerValue] andMonth:[stenextm integerValue] andDay:[stenextd integerValue]];
    NSArray *arytmpnext = [strlunarnext componentsSeparatedByString:@"年"];
    NSString *strlunarNextMD = [arytmpnext lastObject];
    /////////////////////////////////////////////////////
    
    NSString *strlunar = [Datetime GetLunarDateTime];
    NSArray *arytmp = [strlunar componentsSeparatedByString:@"年"];
    NSString *strlunarMD = [arytmp lastObject];
    
    if ([strlunarNextMD isEqualToString:@"正月初一"]||
        [strlunarMD isEqualToString:@"正月初一"]||
        [strlunarMD isEqualToString:@"正月初二"]||
        [strlunarMD isEqualToString:@"正月初三"]||
        [strlunarMD isEqualToString:@"正月初四"]||
        [strlunarMD isEqualToString:@"正月初五"]||
        [strlunarMD isEqualToString:@"正月初六"]||
        [strlunarMD isEqualToString:@"正月初七"]) {
        return @"xn";//新年
    }
    
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"MMdd"];
    NSString *strdate = [dateFormatter stringFromDate:[NSDate date]];
    
    if ([strdate isEqualToString:@"0101"] ||
        [strdate isEqualToString:@"0102"] ||
        [strdate isEqualToString:@"0103"]) {
        return @"yd";//元旦
    }
    //    else if ([strdate isEqualToString:@"0214"]) {
    //        return @"qr";//情人
    //    }
    //    else if ([strdate isEqualToString:@"1001"]) {
    //        return @"gq";//国庆
    //    }
    //    else if ([strdate isEqualToString:@"0501"]) {
    //        return @"ld";//劳动
    //    }
    else if ([strdate isEqualToString:@"1224"] ||
             [strdate isEqualToString:@"1225"]) {
        return @"sd";//圣诞
    }
    return @"pt";
}
#pragma mark------------LoadTopView

-(void)LoadTopView{
    NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
    [dateFormat setDateFormat:@"hh"];
    NSString *date = [dateFormat stringFromDate:[NSDate date]];
    if ([date integerValue] > 6 && [date integerValue] < 18) {
        bNight = NO;
    }
    else{
        bNight = YES;
    }

    if (imageHomeBg == nil) {
        imageHomeBg = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, rect.size.width, rect.size.height)];
        [self addSubview:imageHomeBg];
        imageHomeBg.userInteractionEnabled = YES;
        if (bNight) {
            NSString *strpath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"first_night_bg_%dx%d",(NSInteger)SCREENWIDTH*2,(NSInteger)FULLSCREENHEIGHT*2] ofType:@"jpg"];
            if (strpath == nil) {
                strpath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"first_night_bg_1240x2208"] ofType:@"jpg"];
            }
            imageHomeBg.image = [UIImage imageWithContentsOfFile:strpath];
        }
        else{
//            NSString *strpath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@_first_day_bg_%dx%d",[self Holiday],(NSInteger)SCREENWIDTH*2,(NSInteger)FULLSCREENHEIGHT*2] ofType:@"png"];
//            if (strpath == nil) {
//                strpath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"%@_first_day_bg_1240x2208_2",[self Holiday]] ofType:@"png"];
//            }
//            imageHomeBg.image = [UIImage imageWithContentsOfFile:strpath];
            NSString *strpath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"first_night_bg_%dx%d",(NSInteger)SCREENWIDTH*2,(NSInteger)FULLSCREENHEIGHT*2] ofType:@"jpg"];
            if (strpath == nil) {
                strpath = [[NSBundle mainBundle] pathForResource:[NSString stringWithFormat:@"first_night_bg_1240x2208"] ofType:@"jpg"];
            }
            imageHomeBg.image = [UIImage imageWithContentsOfFile:strpath];
        }
    }
    if (scrollview == nil) {
        scrollview = [[UIScrollView alloc] initWithFrame:CGRectMake(0, TopBarHeight, rect.size.width, rect.size.height/2-TopBarHeight)];
        scrollview.contentSize = CGSizeMake(SCREENWIDTH, fy_Device+400+FULLSCREENHEIGHT/4);
        scrollview.showsVerticalScrollIndicator = NO;
        scrollview.delegate = self;
        [imageHomeBg addSubview:scrollview];
    }
    
    if (labelUpdateTitle == nil) {
        labelUpdateTitle = [[UILabel alloc]initWithFrame:CGRectMake(fx_Info, 10, rect.size.width-2*fx_Info, 20)];
        [scrollview addSubview:labelUpdateTitle];
    }
    labelUpdateTitle.backgroundColor = [UIColor clearColor];
    labelUpdateTitle.font = [UIFont systemFontOfSize:14.0];
    if (bNight) {
        labelUpdateTitle.textColor = [UIColor whiteColor];
    }
    else{
        labelUpdateTitle.textColor = [UIColor whiteColor];
        //labelUpdateTitle.textColor = [UIColor blackColor];
    }
    labelUpdateTitle.text = @"更新时间";
    

    
    if (ActivityView == nil) {
        ActivityView = [[UIActivityIndicatorView alloc]initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhite];
        ActivityView.frame = CGRectMake(rect.size.width-50, labelUpdateTitle.frame.origin.y, 28, 28);
        [scrollview addSubview:ActivityView];
        ActivityView.hidden = YES;
    }
    if (labelUpdateDate == nil) {
        labelUpdateDate = [[UILabel alloc]initWithFrame:CGRectMake(fx_Info, labelUpdateTitle.frame.origin.y+labelUpdateTitle.frame.size.height, labelUpdateTitle.frame.size.width, 20)];
        [scrollview addSubview:labelUpdateDate];
    }
    labelUpdateDate.backgroundColor = [UIColor clearColor];
    labelUpdateDate.font = [UIFont systemFontOfSize:14.0];
    if (bNight) {
        labelUpdateDate.textColor = [UIColor whiteColor];
    }
    else{
        labelUpdateDate.textColor = [UIColor whiteColor];
        //labelUpdateDate.textColor = [UIColor blackColor];
    }

    if (viewValueBg == nil) {
        viewValueBg = [[UIView alloc]initWithFrame:CGRectMake(fx_Info-10, labelUpdateDate.frame.origin.y+labelUpdateDate.frame.size.height+10, 120, 42)];
        [scrollview addSubview:viewValueBg];
    }
    viewValueBg.layer.masksToBounds = YES;
    viewValueBg.layer.cornerRadius = 21;
    viewValueBg.backgroundColor = Color_FirstValueBg;
    
    
    if (labelValue == nil) {
        labelValue = [[UILabel alloc]initWithFrame:CGRectMake(10, 0, viewValueBg.frame.size.width-20, 2*viewValueBg.frame.size.height/3)];
        [viewValueBg addSubview:labelValue];
    }
    labelValue.textAlignment = NSTextAlignmentCenter;
    labelValue.backgroundColor = [UIColor clearColor];
    labelValue.font = [UIFont systemFontOfSize:30.0];
    labelValue.textColor = [UIColor whiteColor];


    if (labelStatus == nil) {
        labelStatus = [[UILabel alloc]initWithFrame:CGRectMake(viewValueBg.frame.size.width/4, labelValue.frame.origin.y+labelValue.frame.size.height, viewValueBg.frame.size.width/2, viewValueBg.frame.size.height/3-2)];
        [viewValueBg addSubview:labelStatus];
    }
    labelStatus.textAlignment = NSTextAlignmentCenter;
    labelStatus.backgroundColor = [UIColor clearColor];
    labelStatus.font = [UIFont boldSystemFontOfSize:14.0];
    labelStatus.textColor = [UIColor whiteColor];
    
    
    if (labelStatusTitle == nil) {
        labelStatusTitle = [[UILabel alloc]initWithFrame:CGRectMake(fx_Info, viewValueBg.frame.origin.y+viewValueBg.frame.size.height, rect.size.width-2*fx_Info, 20)];
        //[self addSubview:labelStatusTitle];
    }
    labelStatusTitle.backgroundColor = [UIColor clearColor];
    labelStatusTitle.font = [UIFont systemFontOfSize:14.0];
    labelStatusTitle.textColor = [UIColor whiteColor];
    labelStatusTitle.text = @"当前室内空气指数";
    
    
    if (btnValueRank == nil) {
        btnValueRank = [[UIButton alloc]initWithFrame:CGRectMake(fx_Info, labelStatusTitle.frame.origin.y+labelStatusTitle.frame.size.height, 150, 20)];
        [btnValueRank addTarget:self action:@selector(ClickValueRank) forControlEvents:UIControlEventTouchUpInside];
        [scrollview addSubview:btnValueRank];
    }
    if (imageValueRankIcon == nil) {
        imageValueRankIcon = [[UIImageView alloc]initWithFrame:CGRectMake(0, 5, 10, 10)];
        [btnValueRank addSubview:imageValueRankIcon];
    }
    if (labelValueRank == nil) {
        labelValueRank = [[UILabel alloc]initWithFrame:CGRectMake(imageValueRankIcon.frame.origin.x+imageValueRankIcon.frame.size.width, 0, btnValueRank.frame.size.width-imageValueRankIcon.frame.origin.x-imageValueRankIcon.frame.size.width+20, btnValueRank.frame.size.height)];
        [btnValueRank addSubview:labelValueRank];
    }
    imageValueRankIcon.image = [UIImage imageNamed:@"up.png"];
    labelValueRank.backgroundColor = [UIColor clearColor];
    labelValueRank.font = [UIFont systemFontOfSize:12.0];
    if (bNight) {
        labelValueRank.textColor = [UIColor whiteColor];
    }
    else{
        labelValueRank.textColor = [UIColor whiteColor];
        //labelValueRank.textColor = [UIColor blackColor];
    }
    labelValueRank.text = @"领先全国的用户,改善>>>";
    
    if (btnHistory == nil) {
        btnHistory = [[UIButton alloc]initWithFrame:CGRectMake(fx_Info-20, fy_Device-40, 30, 30)];
        [btnHistory addTarget:self action:@selector(ClickHistory) forControlEvents:UIControlEventTouchUpInside];
        [scrollview addSubview:btnHistory];
        btnHistory.alpha = Icon_alpha;
    }
    if (bNight) {
        [btnHistory setImage:[UIImage imageNamed:@"history_alarm.png"] forState:UIControlStateNormal];
    }
    else{
        [btnHistory setImage:[UIImage imageNamed:@"history_alarm.png"] forState:UIControlStateNormal];
        //[btnHistory setImage:[UIImage imageNamed:@"ui_home_alarm_history_2.png"] forState:UIControlStateNormal];
    }
    
    if (btnShare == nil) {
        btnShare = [[UIButton alloc]initWithFrame:CGRectMake(btnHistory.frame.origin.x+btnHistory.frame.size.width+10, btnHistory.frame.origin.y, 30, 30)];
        [btnShare addTarget:self action:@selector(ClickShare) forControlEvents:UIControlEventTouchUpInside];
        [scrollview addSubview:btnShare];
        btnShare.alpha = Icon_alpha;
    }
    
    if (btnUpdateHardVersion == nil) {
        btnUpdateHardVersion = [[UIButton alloc]initWithFrame:CGRectMake(btnShare.frame.origin.x+btnShare.frame.size.width+10, btnShare.frame.origin.y, 30, 30)];
        [btnUpdateHardVersion setImage:[UIImage imageNamed:@"upgrade"] forState:UIControlStateNormal];
        [btnUpdateHardVersion addTarget:self action:@selector(ClickBeginUpdateHardVersion) forControlEvents:UIControlEventTouchUpInside];
        [scrollview addSubview:btnUpdateHardVersion];
    }
    
    if (labelUpdateHard == nil) {
        labelUpdateHard = [[UILabel alloc]initWithFrame:CGRectMake(btnUpdateHardVersion.frame.origin.x+btnUpdateHardVersion.frame.size.width+5, btnUpdateHardVersion.frame.origin.y, 60, 30)];
        labelUpdateHard.backgroundColor = [UIColor clearColor];
        labelUpdateHard.textColor = [UIColor whiteColor];
        labelUpdateHard.textAlignment = NSTextAlignmentCenter;
        labelUpdateHard.font = [UIFont systemFontOfSize:14.0];
        labelUpdateHard.text = @"0%";
        labelUpdateHard.hidden = YES;
        [scrollview addSubview:labelUpdateHard];
    }
    
    if (bNight) {
        [btnShare setImage:[UIImage imageNamed:@"ui_home_share.png"] forState:UIControlStateNormal];
    }
    else{
        [btnShare setImage:[UIImage imageNamed:@"ui_home_share.png"] forState:UIControlStateNormal];
        //[btnShare setImage:[UIImage imageNamed:@"ui_home_share_2.png"] forState:UIControlStateNormal];
    }
    
    if (btnCortoon == nil) {
        btnCortoon = [[UIButton alloc]initWithFrame:CGRectMake(rect.size.width-5*DOUWIDTH/6, fy_Device-5*DOUHEIGHT/6, 5*DOUWIDTH/6, 5*DOUHEIGHT/6)];
        [btnCortoon addTarget:self action:@selector(ClickCortoon) forControlEvents:UIControlEventTouchUpInside];
        [scrollview addSubview:btnCortoon];
    }
    [btnCortoon setImage:[UIImage imageNamed:@"001.png"] forState:UIControlStateNormal];
}
#pragma mark------------LoadAirInfoView
-(void)LoadAirInfoView{
    if (viewDeviceDate == nil) {
        viewDeviceDate = [[UIView alloc]initWithFrame:CGRectMake(0, fy_Device, rect.size.width, rect.size.height)];
        [scrollview addSubview:viewDeviceDate];
    }
    viewDeviceDate.backgroundColor = [UIColor clearColor];
    
    if (viewDeviceNamebg == nil) {
        viewDeviceNamebg = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 30)];
        viewDeviceNamebg.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:viewDeviceNamebg];
    }
    
    if (btnArrowTop == nil) {
        btnArrowTop = [[UIButton alloc]initWithFrame:CGRectMake(rect.size.width/2-15, 0, 30, 10)];
        //[viewDeviceNamebg addSubview:btnArrowTop];
    }
    [btnArrowTop addTarget:self action:@selector(ClickArrowTop) forControlEvents:UIControlEventTouchUpInside];
    [btnArrowTop setImage:[UIImage imageNamed:@"ui_home_page_up.png"] forState:UIControlStateNormal];
    [btnArrowTop setImage:[UIImage imageNamed:@"ui_home_page_down.png"] forState:UIControlStateHighlighted];
    [btnArrowTop setImage:[UIImage imageNamed:@"ui_home_page_down.png"] forState:UIControlStateSelected];
    
    if (labelDeviceName == nil) {
        labelDeviceName = [[UILabel alloc]initWithFrame:CGRectMake(10, 0, viewDeviceDate.frame.size.width-20, 30)];
        [viewDeviceNamebg addSubview:labelDeviceName];
    }
    labelDeviceName.textAlignment = NSTextAlignmentCenter;
    labelDeviceName.backgroundColor = [UIColor clearColor];
    labelDeviceName.font = [UIFont boldSystemFontOfSize:14.0];
    labelDeviceName.textColor = [UIColor whiteColor];

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    NSInteger icount = 5;//设备检测项目个数
    float fviewDeviceDateWidth = viewDeviceDate.frame.size.width;//设备展示栏目的宽度
    float fgap = 10;//间隙
    float fDeviceImageWidth = (fviewDeviceDateWidth-(2*icount)*fgap)/icount;//一项检测项目图片宽度
    float fy = viewDeviceNamebg.frame.origin.y+viewDeviceNamebg.frame.size.height+5;//该栏目的Y坐标
    
    if (viewDevicebg == nil) {
        viewDevicebg = [[UIView alloc]initWithFrame:CGRectMake(0, fy, SCREENWIDTH, 75)];
        viewDevicebg.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:viewDevicebg];
    }
    
    if (imageTemp == nil) {
        imageTemp = [[UIImageView alloc]initWithFrame:CGRectMake(fgap, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imageTemp];
        imageTemp.alpha = Icon_alpha;
    }
    if (bNight) {
        imageTemp.image = [UIImage imageNamed:@"ui_home_unit_temperature.png"];
    }
    else{
        imageTemp.image = [UIImage imageNamed:@"ui_home_unit_temperature.png"];
    }
    
    if (labelTemp == nil) {
        labelTemp = [[UILabel alloc]initWithFrame:CGRectMake(imageTemp.frame.origin.x-fgap, imageTemp.frame.origin.y+imageTemp.frame.size.height, imageTemp.frame.size.width+2*fgap, 20)];
        [viewDeviceDate addSubview:labelTemp];
    }
    labelTemp.textAlignment = NSTextAlignmentCenter;
    labelTemp.backgroundColor = [UIColor clearColor];
    labelTemp.font = [UIFont systemFontOfSize:14.0];
    labelTemp.textColor = [UIColor whiteColor];

    
    if (imageWet == nil) {
        imageWet = [[UIImageView alloc]initWithFrame:CGRectMake(imageTemp.frame.origin.x+fDeviceImageWidth+2*fgap, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imageWet];
        imageWet.alpha = Icon_alpha;
    }
    if (bNight) {
        imageWet.image = [UIImage imageNamed:@"ui_home_unit_humidity.png"];
    }
    else{
        imageWet.image = [UIImage imageNamed:@"ui_home_unit_humidity.png"];
    }
    
    if (labelWet == nil) {
        labelWet = [[UILabel alloc]initWithFrame:CGRectMake(imageWet.frame.origin.x-fgap, imageWet.frame.origin.y+imageWet.frame.size.height, imageWet.frame.size.width+2*fgap, 20)];
        [viewDeviceDate addSubview:labelWet];
    }
    labelWet.textAlignment = NSTextAlignmentCenter;
    labelWet.backgroundColor = [UIColor clearColor];
    labelWet.font = [UIFont systemFontOfSize:14.0];
    labelWet.textColor = [UIColor whiteColor];

    
    if (imageCo2 == nil) {
        imageCo2 = [[UIImageView alloc]initWithFrame:CGRectMake(imageWet.frame.origin.x+fDeviceImageWidth+2*fgap, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imageCo2];
        imageCo2.alpha = Icon_alpha;
    }
    if (bNight) {
        imageCo2.image = [UIImage imageNamed:@"ui_home_unit_co2.png"];
    }
    else{
        imageCo2.image = [UIImage imageNamed:@"ui_home_unit_co2.png"];
    }
    if (labelCo2 == nil) {
        labelCo2 = [[UILabel alloc]initWithFrame:CGRectMake(imageCo2.frame.origin.x-fgap, imageCo2.frame.origin.y+imageCo2.frame.size.height, imageCo2.frame.size.width+2*fgap, 20)];
        [viewDeviceDate addSubview:labelCo2];
    }
    labelCo2.textAlignment = NSTextAlignmentCenter;
    labelCo2.backgroundColor = [UIColor clearColor];
    labelCo2.font = [UIFont systemFontOfSize:14.0];
    labelCo2.textColor = [UIColor whiteColor];

    
    if (imagePm == nil) {
        imagePm = [[UIImageView alloc]initWithFrame:CGRectMake(imageCo2.frame.origin.x+fDeviceImageWidth+2*fgap, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imagePm];
        imagePm.alpha = Icon_alpha;
    }
    if (bNight) {
        imagePm.image = [UIImage imageNamed:@"ui_home_unit_pm25.png"];
    }
    else{
        imagePm.image = [UIImage imageNamed:@"ui_home_unit_pm25.png"];
    }
    if (labelPm == nil) {
        labelPm = [[UILabel alloc]initWithFrame:CGRectMake(imagePm.frame.origin.x-fgap, imagePm.frame.origin.y+imagePm.frame.size.height, imagePm.frame.size.width+2*fgap, 20)];
        [viewDeviceDate addSubview:labelPm];
    }
    labelPm.textAlignment = NSTextAlignmentCenter;
    labelPm.backgroundColor = [UIColor clearColor];
    labelPm.font = [UIFont systemFontOfSize:14.0];
    labelPm.textColor = [UIColor whiteColor];

    
    if (imageVoc == nil) {
        imageVoc = [[UIImageView alloc]initWithFrame:CGRectMake(imagePm.frame.origin.x+fDeviceImageWidth+2*fgap, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imageVoc];
        imageVoc.alpha = Icon_alpha;
    }
    if (bNight) {
        imageVoc.image = [UIImage imageNamed:@"ui_home_unit_voc.png"];
    }
    else{
        imageVoc.image = [UIImage imageNamed:@"ui_home_unit_voc.png"];
    }
    if (labelVoc == nil) {
        labelVoc = [[UILabel alloc]initWithFrame:CGRectMake(imageVoc.frame.origin.x-fgap, imageVoc.frame.origin.y+imageVoc.frame.size.height, imageVoc.frame.size.width+2*fgap, 20)];
        [viewDeviceDate addSubview:labelVoc];
    }
    labelVoc.textAlignment = NSTextAlignmentCenter;
    labelVoc.backgroundColor = [UIColor clearColor];
    labelVoc.font = [UIFont systemFontOfSize:14.0];
    labelVoc.textColor = [UIColor whiteColor];

    
    //////////////////////////////////////////////////////////
    //添加左右箭头
    if (btnArrowLeft == nil) {
        btnArrowLeft = [[UIButton alloc]initWithFrame:CGRectMake(0,5, 10, 20)];
        [viewDeviceNamebg addSubview:btnArrowLeft];
    }
    [btnArrowLeft setImage:[UIImage imageNamed:@"ui_home_page_left.png"] forState:UIControlStateNormal];
    
    if (btnArrowRight == nil) {
        btnArrowRight = [[UIButton alloc]initWithFrame:CGRectMake(rect.size.width-10, 5, 10, 20)];
        [viewDeviceNamebg addSubview:btnArrowRight];
    }
    [btnArrowRight setImage:[UIImage imageNamed:@"ui_home_page_right.png"] forState:UIControlStateNormal];
    if (rect.origin.x == 0) {
        btnArrowLeft.hidden = YES;
    }
    
    labelStatus.hidden = NO;
    labelValue.hidden = NO;
    viewValueBg.hidden = NO;
    labelStatusTitle.hidden = NO;
    labelValueRank.hidden = NO;
    imageValueRankIcon.hidden = NO;
//    panGestureRecognizer = [[UIPanGestureRecognizer alloc]
//                            initWithTarget:self
//                            action:@selector(handlePan:)];
//    panGestureRecognizer.delegate = self;
//    panGestureRecognizer.maximumNumberOfTouches = 1;
//    [viewDeviceDate addGestureRecognizer:panGestureRecognizer];
}

#pragma mark------------LoadGasInfoView
-(void)LoadGasInfoView{
    if (viewDeviceDate == nil) {
        viewDeviceDate = [[UIView alloc]initWithFrame:CGRectMake(0, fy_Device, rect.size.width, rect.size.height)];
        [scrollview addSubview:viewDeviceDate];
    }
    viewDeviceDate.backgroundColor = [UIColor clearColor];
    
    if (viewDeviceNamebg == nil) {
        viewDeviceNamebg = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 30)];
        viewDeviceNamebg.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:viewDeviceNamebg];
    }
    
    if (btnArrowTop == nil) {
        btnArrowTop = [[UIButton alloc]initWithFrame:CGRectMake(rect.size.width/2-15, 5, 30, 10)];
        //[viewDeviceDate addSubview:btnArrowTop];
    }
    [btnArrowTop addTarget:self action:@selector(ClickArrowTop) forControlEvents:UIControlEventTouchUpInside];
    [btnArrowTop setImage:[UIImage imageNamed:@"ui_home_page_up.png"] forState:UIControlStateNormal];
    [btnArrowTop setImage:[UIImage imageNamed:@"ui_home_page_down.png"] forState:UIControlStateHighlighted];
    [btnArrowTop setImage:[UIImage imageNamed:@"ui_home_page_down.png"] forState:UIControlStateSelected];
    
    
    if (labelDeviceName == nil) {
        labelDeviceName = [[UILabel alloc]initWithFrame:CGRectMake(10, 0, viewDeviceDate.frame.size.width-20, 30)];
        [viewDeviceNamebg addSubview:labelDeviceName];
    }
    labelDeviceName.textAlignment = NSTextAlignmentCenter;
    labelDeviceName.backgroundColor = [UIColor clearColor];
    labelDeviceName.font = [UIFont boldSystemFontOfSize:14.0];
    labelDeviceName.textColor = [UIColor whiteColor];
    
    
    float fviewDeviceDateWidth = viewDeviceDate.frame.size.width;//设备展示栏目的宽度
    float fDeviceImageWidth = 44;//一项检测项目图片宽度
    float fy = viewDeviceNamebg.frame.origin.y+viewDeviceNamebg.frame.size.height+5;//该栏目的Y坐标
    float fgap2 = (fviewDeviceDateWidth-2*fDeviceImageWidth)/4;
    
    if (viewDevicebg == nil) {
        viewDevicebg = [[UIView alloc]initWithFrame:CGRectMake(0, fy, SCREENWIDTH, 75)];
        viewDevicebg.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:viewDevicebg];
    }
    if (imageGas == nil) {
        imageGas = [[UIImageView alloc]initWithFrame:CGRectMake(fgap2, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imageGas];
        imageGas.alpha = Icon_alpha;
    }
    if (bNight) {
        imageGas.image = [UIImage imageNamed:@"ui_home_unit_ch4.png"];
    }
    else{
        imageGas.image = [UIImage imageNamed:@"ui_home_unit_ch4.png"];
    }
    if (labelGas == nil) {
        labelGas = [[UILabel alloc]initWithFrame:CGRectMake(imageGas.frame.origin.x-fgap2, imageGas.frame.origin.y+imageGas.frame.size.height, imageGas.frame.size.width+2*fgap2, 20)];
        [viewDeviceDate addSubview:labelGas];
    }
    labelGas.textAlignment = NSTextAlignmentCenter;
    labelGas.backgroundColor = [UIColor clearColor];
    labelGas.font = [UIFont systemFontOfSize:14.0];
    labelGas.textColor = [UIColor whiteColor];
    
    
    if (imageCo == nil) {
        imageCo = [[UIImageView alloc]initWithFrame:CGRectMake(imageGas.frame.origin.x+imageGas.frame.size.width+2*fgap2, fy+5, fDeviceImageWidth, fDeviceImageWidth)];
        [viewDeviceDate addSubview:imageCo];
        imageCo.alpha = Icon_alpha;
    }
    if (bNight) {
        imageCo.image = [UIImage imageNamed:@"ui_home_unit_co.png"];
    }
    else{
        imageCo.image = [UIImage imageNamed:@"ui_home_unit_co.png"];
    }
    if (labelCo == nil) {
        labelCo = [[UILabel alloc]initWithFrame:CGRectMake(imageCo.frame.origin.x-fgap2, imageCo.frame.origin.y+imageCo.frame.size.height, imageCo.frame.size.width+2*fgap2, 20)];
        [viewDeviceDate addSubview:labelCo];
    }
    labelCo.textAlignment = NSTextAlignmentCenter;
    labelCo.backgroundColor = [UIColor clearColor];
    labelCo.font = [UIFont systemFontOfSize:14.0];
    labelCo.textColor = [UIColor whiteColor];
    
    //////////////////////////////////////////////////////////
    //添加左右箭头
    if (btnArrowLeft == nil) {
        btnArrowLeft = [[UIButton alloc]initWithFrame:CGRectMake(0,5, 10, 20)];
        [viewDeviceNamebg addSubview:btnArrowLeft];
    }
    [btnArrowLeft setImage:[UIImage imageNamed:@"ui_home_page_left.png"] forState:UIControlStateNormal];
    
    if (btnArrowRight == nil) {
        btnArrowRight = [[UIButton alloc]initWithFrame:CGRectMake(rect.size.width-10, 5, 10, 20)];
        [viewDeviceNamebg addSubview:btnArrowRight];
    }
    [btnArrowRight setImage:[UIImage imageNamed:@"ui_home_page_right.png"] forState:UIControlStateNormal];
    
    labelStatus.hidden = YES;
    labelValue.hidden = YES;
    viewValueBg.hidden = YES;
    labelStatusTitle.hidden = YES;
    labelValueRank.hidden = YES;
    imageValueRankIcon.hidden = YES;
//    panGestureRecognizer = [[UIPanGestureRecognizer alloc]
//                            initWithTarget:self
//                            action:@selector(handlePan:)];
//    panGestureRecognizer.delegate = self;
//    panGestureRecognizer.maximumNumberOfTouches = 1;
//    [viewDeviceDate addGestureRecognizer:panGestureRecognizer];
}
-(void)hideRightArrow{
   btnArrowRight.hidden = YES;
}
-(void)hideLeftArrow{
    btnArrowLeft.hidden = YES;
}
-(NSString*)DataTOjsonString:(id)object
{
    NSString *jsonString = nil;
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:object
                                                       options:NSJSONWritingPrettyPrinted // Pass 0 if you don't care about the readability of the generated string
                                                         error:&error];
    if (! jsonData) {
        NSLog(@"Got an error: %@", error);
    } else {
        jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    }
    return jsonString;
}
-(void)ClickBeginUpdateHardVersion{
    if ([self checkUpdate] == 1) {
        NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:CHECKUPGRADE]];
        ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
        __weak typeof(ASIFormDataRequest) *request = requestTmp;
        request.requestMethod = @"POST";
        NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
        NSString *userId = [userDefaultes stringForKey:@"USERID"];
        if (userId == nil) {
            return;
        };
        
        NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
        if (sessionId == nil) {
            return;
        };
        [request setPostValue:userId forKey:@"USERID"];
        [request setPostValue:sessionId forKey:@"SESSIONID"];
        [request setPostValue:strSensorId forKey:@"DRIVERID"];
        [request setPostValue:[self DataTOjsonString:dicSensorDetail] forKey:@"DATA"];
        
        NSLog(@"%@",[self DataTOjsonString:dicSensorDetail]);
        [request setCompletionBlock:^{
            NSString *responseString = [request responseString];
            NSDictionary *result = [responseString  objectFromJSONString];
            [self startUpdateHardWare];
        }];
        [request setFailedBlock :^{
            NSError *error = [request error];
            if (error) {
                NSLog(@"GetRankData:%@",error.description);
            }
            UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"网络连接失败" delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:nil, nil];
            [alert show];
        }];
        [request startAsynchronous];
    }
    else if ([self checkUpdate] == 0) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"当前固件版本是最新的" delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:nil, nil];
        [alert show];
    }
    else if ([self checkUpdate] == -1) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"当前设备不在线" delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:nil, nil];
        [alert show];
    }
    
}
/**
 *  硬件升级///////////////////////////////////////////////////////////
 */
-(int)checkUpdate{
    NSLog(@"SoftVersion:%@-%@",strlocalSoftVersion,strremoteSoftVersion);
    if (strlocalSoftVersion.length == 0 || strremoteSoftVersion.length == 0) {
        return -1;
    }
    if (![strremoteSoftVersion isEqualToString:strlocalSoftVersion]) {
        return 1;
    }
    else{
        return 0;
    }
}
-(void)startUpdateHardWare{
    if (!bhardupdating) {
        timerUpdateHard = [NSTimer scheduledTimerWithTimeInterval:10.0 target:self selector:@selector(timerStartUpdateHard) userInfo:nil repeats:YES];
        labelUpdateHard.hidden = NO;
        bhardupdating = YES;
    }
}
-(void)timerStartUpdateHard{
    [self beginHardWareUpdate];
}
-(void)stopUpdateHardWare{
    [timerUpdateHard invalidate];
    bhardupdating = NO;
    labelUpdateHard.text = @"100%";
    [UIView animateWithDuration:2.0 animations:^{
        labelUpdateHard.alpha = 0.0;
    } completion:^(BOOL finished){
        labelUpdateHard.hidden = YES;
        labelUpdateHard.alpha = 1.0;
    }];
}
/**
 *  更新硬件
 *
 *  @param bupdate 是否
 */
-(void)beginHardWareUpdate{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:CHECKUPGRADEINFO]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    [request setPostValue:strSensorId forKey:@"DRIVERID"];
    [request setCompletionBlock:^{
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        if ([[result objectForKey:@"code"] isEqualToString:@"0"]) {
            UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:[result objectForKey:@"message"] delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:nil, nil];
            [alert show];
            [timerUpdateHard invalidate];
            bhardupdating = NO;
            labelUpdateHard.text = @"升级失败";
            [UIView animateWithDuration:2.0 animations:^{
                labelUpdateHard.alpha = 0.0;
            } completion:^(BOOL finished){
                labelUpdateHard.hidden = YES;
                labelUpdateHard.alpha = 1.0;
            }];
        }
        else{
            NSDictionary *dataObject = [result  objectForKey:@"dataObject"];
            NSString *strTotal = [dataObject objectForKey:@"totalLength"];
            NSString *strRecive = [dataObject objectForKey:@"reciveLength"];
            NSLog(@"update:%@-%@",strRecive,strTotal);
            if ([strTotal floatValue] != 0.0) {
                if ([strRecive floatValue] >= [strTotal floatValue]) {
                    [self stopUpdateHardWare];
                }
                else{
                    labelUpdateHard.text = [NSString stringWithFormat:@"%d%%",[strRecive floatValue]/[strTotal floatValue]*100];
                }
            }
        }
    }];
    [request setFailedBlock :^{
        NSError *error = [request error];
        if (error) {
            NSLog(@"GetRankData:%@",error.description);
        }
    }];
    [request startAsynchronous];
}
/**
 *  /////////////////////////////////////////////////////////////
 */

#pragma mark------------LoadTopViewData
-(void)LoadTopViewData:(CartoonInfo *)cartooninfo{
    carToonInfo = cartooninfo;
    NSString *strUpdateDate = @"";
    if (dicAirData != nil) {
        strUpdateDate = [NSString stringWithFormat:@"%@",dicAirData.createTime];
    }
    else{
        strUpdateDate = [NSString stringWithFormat:@"%@",dicGasData.createTime];
    }
    if ([strUpdateDate isEqualToString:@"1900-01-01 01:01:01"] || strUpdateDate.length == 0) {
        labelUpdateDate.text = @"传感器未联网";
    }
    else{
       labelUpdateDate.text = [DateUtils DateFormat:strUpdateDate];
    }
    NSString *strPm = dicAirData.pm25;
    if (strPm.length == 0) {
        strPm = @"0";
    }
    
    labelValue.text = [NSString stringWithFormat:@"%d",[strPm integerValue]];
//    CGSize maximumSizeContent = CGSizeMake([UIScreen mainScreen].bounds.size.width/2, 70);
//    NSDictionary * tdicContent = [NSDictionary dictionaryWithObjectsAndKeys:labelValue.font,NSFontAttributeName,nil];
//    CGSize  actualsizeContent = [strPm boundingRectWithSize:maximumSizeContent options:NSStringDrawingUsesLineFragmentOrigin |NSStringDrawingUsesFontLeading attributes:tdicContent context:nil].size;
//    CGRect frame = labelValue.frame;
//    frame.size.width = actualsizeContent.width;
//    labelValue.frame = frame;
    
    [self setAQI:[strPm floatValue]];
    [self GetRankData:strPm];
}
#pragma mark------------LoadAirInfoData
-(void)LoadAirInfoData{
    strSensorId = dicAirData.sensorId;
    if (strSensorId.length == 0) {
        labelDeviceName.text = [NSString stringWithFormat:@"气体质量检测设备:"];
    }
    NSString *strOnline = @"";
    if ([dicAirData isOnline]) {
        strOnline = @"在线";
    }
    else{
        strOnline = @"不在线";
    }
    labelDeviceName.text = [NSString stringWithFormat:@"%@(%@):%@",dicAirData.name,strOnline,strSensorId];

    NSString *strTemp = dicAirData.temperature;
    if (strTemp.length == 0) {
        labelTemp.text = @"0.0";
    }
    else{
        labelTemp.text = strTemp;
    }
    
    NSString *strWet = dicAirData.humidity;
    if (strWet.length == 0) {
        labelWet.text = @"0.0";
    }
    else{
        labelWet.text = strWet;
    }
    
    NSString *strCo2 = dicAirData.co2;
    if (strCo2.length == 0) {
        labelCo2.text = @"0.0";
    }
    else{
        labelCo2.text = strCo2;
    }
    
    NSString *strPm = dicAirData.pm25;
    if (strPm.length == 0) {
        labelPm.text = @"0.0";
    }
    else{
        labelPm.text = strPm;
    }
    NSString *strVoc = dicAirData.voc;
    if (strVoc.length == 0) {
        labelVoc.text = @"0.0";
    }
    else{
        labelVoc.text = strVoc;
    }
    
    if (!bGotLineData) {
        [self GetLineData];
    }
}

#pragma mark------------LoadGasInfoData
-(void)LoadGasInfoData{
    strSensorId = dicGasData.sensorId;
    if (strSensorId.length == 0) {
        labelDeviceName.text = [NSString stringWithFormat:@"气体质量检测设备:"];
    }
    NSString *strOnline = @"";
    if ([dicGasData isOnline]) {
        strOnline = @"在线";
    }
    else{
        strOnline = @"不在线";
    }
    labelDeviceName.text = [NSString stringWithFormat:@"%@(%@):%@",dicGasData.name,strOnline,strSensorId];
    
    NSString *strGas = dicGasData.ch4;
    if (strGas.length == 0) {
        labelGas.text = @"0.0 LEL";
    }
    else{
        labelGas.text = [NSString stringWithFormat:@"%@ LEL",strGas];
    }
    
    NSString *strCo = dicGasData.co;
    if (strCo.length == 0) {
        labelCo.text = @"0.0 PPM";
    }
    else{
        labelCo.text = [NSString stringWithFormat:@"%@ PPM",strCo];
    }
    
    if ([strGas floatValue] >= CH4_THRESHOLD) {
        imageGas.image = [UIImage imageNamed:@"ui_home_unit_ch4_high.png"];
    }
    else{
        if (bNight) {
            imageGas.image = [UIImage imageNamed:@"ui_home_unit_ch4.png"];
        }
        else{
            imageGas.image = [UIImage imageNamed:@"ui_home_unit_ch4.png"];
        }
    }
    if ([strCo floatValue] >= CO_THRESHOLD) {
        imageCo.image = [UIImage imageNamed:@"ui_home_unit_co_hight.png"];
    }
    else{
        if (bNight) {
            imageCo.image = [UIImage imageNamed:@"ui_home_unit_co.png"];
        }
        else{
            imageCo.image = [UIImage imageNamed:@"ui_home_unit_co.png"];
        }
    }
    if (!bGotLineData) {
        [self GetLineData];
    }
}

#pragma mark------------NetData
-(void)GetRankData:(NSString *)score
{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:RANKING]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    request.tag = 1;
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:score forKey:@"SCORE"];
    [request setPostValue:@"3" forKey:@"GASTYPE"];
    [request setCompletionBlock:^{
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        if ([result objectForKey:@"data"] != [NSNull null] && [result objectForKey:@"data"] != nil) {
            labelValueRank.text = [NSString stringWithFormat:@"领先全国%@的用户,改善>>>",[result objectForKey:@"data"]];
        }
        if ([result objectForKey:@"dataObject"] != [NSNull null]) {
            if ([[result objectForKey:@"dataObject"] isEqualToString:@"up"]) {
                imageValueRankIcon.image = [UIImage imageNamed:@"up.png"];
            }
            else{
                imageValueRankIcon.image = [UIImage imageNamed:@"down.png"];
            }
        }
    }];
    [request setFailedBlock :^{
        NSError *error = [request error];
        if (error) {
            NSLog(@"GetRankData:%@",error.description);
        }
    }];
    [request startAsynchronous];
}
-(void)GetLineData{
    [self LoadLineView];
    if (dicFLineData.count == 0) {
        NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
        [dateFormat setDateFormat:@"yyyyMMdd"];
        todayDate = [dateFormat stringFromDate:[NSDate date]];
    }
    else{
        NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
        [dateFormat setDateFormat:@"yyyyMMdd"];
        NSString* curDate = [dateFormat stringFromDate:[NSDate date]];
        if ([curDate isEqualToString:todayDate]) {
            return;
        }
    }
    
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_02_01]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setCompletionBlock:^{
        NSLog(@"GetLineDataFinished");
        bGotLineData = YES;
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        
        if (![[result objectForKey:@"code"] isEqualToString:@"0"]) {
            if ([result objectForKey:@"dataObject"] != [NSNull null]) {
                NSArray *aryDataObject = [result objectForKey:@"dataObject"];
                NSDictionary *dictemp = [aryDataObject objectAtIndex:0];
                NSArray *arySensorList = [dictemp objectForKey:@"sensorList"];
                
                NSMutableDictionary *mdicSensorId = [[NSMutableDictionary alloc]init];
                for (NSDictionary *dic in arySensorList) {
                    [mdicSensorId setObject:[dic objectForKey:@"sensorId"] forKey:[dic objectForKey:@"sensorId"]];
                }
                NSArray *aryAllDeviceid = [mdicSensorId allKeys];
                
                
                NSMutableArray *mary = [[NSMutableArray alloc]init];
                for (NSDictionary *dic in arySensorList) {
                    NSDictionary *dicAir = [dic objectForKey:@"air"];
                    NSDictionary *dicGas = [dic objectForKey:@"gas"];
                    if ([[dic objectForKey:@"sensorId"] isEqualToString:strSensorId]) {
                        if (dicAir.count != 0) {
                            [mary addObject:dicAir];
                        }
                        else if (dicGas.count != 0){
                            [mary addObject:dicGas];
                        }
                    }
                }
                if (dicFLineData.count != 0) {
                    [dicFLineData removeAllObjects];
                }
                [dicFLineData setObject:mary forKey:strSensorId];

                aryFAllDeviceid = aryAllDeviceid;
                [self LoadLineAndData];
                if (dicGasData != nil) {
                    if (timerAlarm == nil) {
                        timerAlarm = [NSTimer scheduledTimerWithTimeInterval:5 target:self selector:@selector(AlarmTimerUpdate) userInfo:nil repeats:YES];
                    }
                }
            }
            else{
                NSLog(@"dataObject空值!");
            }
        }
        else{
            NSLog(@"GetLineData:%@",[result objectForKey:@"message"]);
        }
    }];
    [request setFailedBlock :^{
        NSError *error = [request error];
        if (error) {
            NSLog(@"GetLineData:%@",error.description);
        }
    }];
    [request startAsynchronous];
}
-(void)StopAlarmTimer{
    [timerAlarm invalidate];
}
-(void)GetAlarmData{
    NSLog(@"%@:GetAlarmData",strSensorId);
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_02_01_02]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    request.tag = 3;
    
    //读取NSString类型的数据
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    
    NSString *pageIndex = [NSString stringWithFormat:@"%d",1];
    NSString *pageSize = [NSString stringWithFormat:@"%d",1];
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:strSensorId forKey:@"DRIVERID"];
    [request setPostValue:pageIndex forKey:@"PAGENO"];
    [request setPostValue:pageSize forKey:@"PAGESIZE"];
    
    [request setCompletionBlock:^{
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        if ([result objectForKey:@"dataObject"] != [NSNull null]) {
            NSArray *aryDataObject = [result objectForKey:@"dataObject"];
            if (aryDataObject.count != 0) {
                NSDictionary* dicAlarm = [aryDataObject firstObject];
                if ([self SendAlarm:dicAlarm]) {
                    if (dicAirData != nil) {
                        [delegate FirstContentViewSendAlarm:self data:dicAlarm sensorName:dicAirData.name];
                    }
                    else if(dicGasData != nil){
                        [delegate FirstContentViewSendAlarm:self data:dicAlarm sensorName:dicGasData.name];
                    }
                }
            }
        }
    }];
    [request setFailedBlock :^{
        NSError *error = [request error];
        if (error) {
            NSLog(@"GetAlarmData:%@",error.description);
        }
    }];
    [request startAsynchronous];
}

-(void)AlarmTimerUpdate{
    [self GetAlarmData];
}

-(BOOL)SendAlarm:(NSDictionary *)dicalarm{
    if ([[NSFileManager defaultManager]fileExistsAtPath:[g_UserDirPath stringByAppendingPathComponent:@"alarm.plist"]]) {
        NSDictionary *dicLoaclAllAlarm = [NSDictionary dictionaryWithContentsOfFile:[g_UserDirPath stringByAppendingPathComponent:@"alarm.plist"]];
        NSDictionary *diclocalAlarm = [dicLoaclAllAlarm objectForKey:strSensorId];
        NSString *strLocalma005 = [diclocalAlarm objectForKey:@"ma005"];
        NSString *strNewma005 = [dicalarm objectForKey:@"ma005"];
        if ([strLocalma005 isEqualToString:strNewma005]) {
            return NO;
        }
        else{
            NSMutableDictionary *dicSave = [[NSMutableDictionary alloc]initWithContentsOfFile:[g_UserDirPath stringByAppendingPathComponent:@"alarm.plist"]];
            [dicSave setObject:dicalarm forKey:strSensorId];
            [dicSave writeToFile:[g_UserDirPath stringByAppendingPathComponent:@"alarm.plist"] atomically:NO];
            return YES;
        }
    }
    else{
        NSMutableDictionary *dicSave = [[NSMutableDictionary alloc]init];
        [dicSave setObject:dicalarm forKey:strSensorId];
        [dicSave writeToFile:[g_UserDirPath stringByAppendingPathComponent:@"alarm.plist"] atomically:NO];
        return YES;
    }
}

//折线图页面及数据
-(void)LoadLineView{
    NSLog(@"LoadLineView");
    float fLineViewWidth = viewDeviceDate.frame.size.width-10;
    float fLineHeight = FULLSCREENHEIGHT/4;
    float fy = 0.0;
    if (dicAirData != nil) {
        fy = viewDevicebg.frame.origin.y+viewDevicebg.frame.size.height+10;
    }
    else{
        fy = viewDevicebg.frame.origin.y+viewDevicebg.frame.size.height+10;
    }
    if (viewLineTitlebg == nil) {
        viewLineTitlebg = [[UIView alloc]initWithFrame:CGRectMake(0, fy, SCREENWIDTH, 30)];
        viewLineTitlebg.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:viewLineTitlebg];
    }
    
    if (scrollLinebg == nil) {
        scrollLinebg = [[UIScrollView alloc]initWithFrame:CGRectMake(0, viewLineTitlebg.frame.origin.y+viewLineTitlebg.frame.size.height+5, SCREENWIDTH, fLineHeight)];
        scrollLinebg.contentSize = CGSizeMake(SCREENWIDTH, fLineHeight);
        scrollLinebg.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:scrollLinebg];
    }
    
    if (labelLineTitleLeft == nil) {
        labelLineTitleLeft = [[UILabel alloc]initWithFrame:CGRectMake(10, 0,viewLineTitlebg.frame.size.width/2-10 , viewLineTitlebg.frame.size.height)];
        labelLineTitleLeft.backgroundColor = [UIColor clearColor];
        labelLineTitleLeft.textColor = [UIColor whiteColor];
        labelLineTitleLeft.font = [UIFont systemFontOfSize:16];
        [viewLineTitlebg addSubview:labelLineTitleLeft];
    }
    
    if (btnLineTitleRight == nil) {
        btnLineTitleRight = [[UIButton alloc]initWithFrame:CGRectMake(viewLineTitlebg.frame.size.width-125, 0,120 , viewLineTitlebg.frame.size.height)];
        [btnLineTitleRight setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [btnLineTitleRight setTitle:@"过去七天" forState:UIControlStateNormal];
        btnLineTitleRight.titleLabel.font = [UIFont systemFontOfSize:16];
        [viewLineTitlebg addSubview:btnLineTitleRight];
    }
    if (lineChartView == nil) {
        lineChartView = [[PCLineChartView alloc] initWithFrame:CGRectMake(5,(scrollLinebg.frame.size.height-fLineHeight)/2,fLineViewWidth,fLineHeight)];
        [lineChartView setAutoresizingMask:UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleHeight];
        lineChartView.minValue = 0;
        lineChartView.maxValue = 10;
        lineChartView.interval = 2;
        [scrollLinebg addSubview:lineChartView];
    }

    if (dicAirData != nil) {
        if (strGasType.length == 0) {
            strGasType = @"温度";
        }
        labelLineTitleLeft.text = strGasType;
        [self LoadLineTypeButton:1];
    }
    else{
        if (strGasType.length == 0) {
            strGasType = @"燃气";
        }
        labelLineTitleLeft.text = strGasType;
        [self LoadLineTypeButton:2];
    }
}

-(void)ClickLineButton7{
    [btn7 setBackgroundColor:Color_FirstBg];
    [btn24 setBackgroundColor:Color_FirstBg];
    [btn30 setBackgroundColor:Color_FirstBg];
    [btn7 setBackgroundColor:Color_TypeButtonSelected];
    [btnLineTitleRight setTitle:@"过去七天" forState:UIControlStateNormal];
    [self dolistDetailHistory:@"2" lastpar:@"7"];
}
-(void)ClickLineButton24{
    [btn7 setBackgroundColor:Color_FirstBg];
    [btn24 setBackgroundColor:Color_FirstBg];
    [btn30 setBackgroundColor:Color_FirstBg];
    [btn24 setBackgroundColor:Color_TypeButtonSelected];
    [btnLineTitleRight setTitle:@"过去24小时" forState:UIControlStateNormal];
    [self dolistDetailHistory:@"1" lastpar:@"24"];
}
-(void)ClickLineButton30{
    [btn7 setBackgroundColor:Color_FirstBg];
    [btn24 setBackgroundColor:Color_FirstBg];
    [btn30 setBackgroundColor:Color_FirstBg];
    [btn30 setBackgroundColor:Color_TypeButtonSelected];
    [btnLineTitleRight setTitle:@"过去三十天" forState:UIControlStateNormal];
    [self dolistDetailHistory:@"2" lastpar:@"30"];
}
//type 1:空气 2:天然气
-(void)LoadLineTypeButton:(NSInteger)itype{
    if (btn7 == nil) {
        btn7 = [[UIButton alloc]initWithFrame:CGRectMake(0, scrollLinebg.frame.origin.y+scrollLinebg.frame.size.height+10,viewDeviceDate.frame.size.width/3,30)];
        [btn7 setBackgroundColor:Color_TypeButtonSelected];
        [btn7 setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [btn7 setTitle:@"过去七天" forState:UIControlStateNormal];
        btn7.titleLabel.font = [UIFont systemFontOfSize:16];
        [viewDeviceDate addSubview:btn7];
        [btn7 addTarget:self action:@selector(ClickLineButton7) forControlEvents:UIControlEventTouchUpInside];
    }
    if (btn24 == nil) {
        btn24 = [[UIButton alloc]initWithFrame:CGRectMake(viewDeviceDate.frame.size.width/3, scrollLinebg.frame.origin.y+scrollLinebg.frame.size.height+10,viewDeviceDate.frame.size.width/3,30)];
        [btn24 setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [btn24 setTitle:@"过去24小时" forState:UIControlStateNormal];
        btn24.titleLabel.font = [UIFont systemFontOfSize:16];
        [viewDeviceDate addSubview:btn24];
        [btn24 addTarget:self action:@selector(ClickLineButton24) forControlEvents:UIControlEventTouchUpInside];
    }
    if (btn30 == nil) {
        btn30 = [[UIButton alloc]initWithFrame:CGRectMake(2*viewDeviceDate.frame.size.width/3, scrollLinebg.frame.origin.y+scrollLinebg.frame.size.height+10,viewDeviceDate.frame.size.width/3,30)];
        [btn30 setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        [btn30 setTitle:@"过去三十天" forState:UIControlStateNormal];
        btn30.titleLabel.font = [UIFont systemFontOfSize:16];
        [viewDeviceDate addSubview:btn30];
        [btn30 addTarget:self action:@selector(ClickLineButton30) forControlEvents:UIControlEventTouchUpInside];
    }
    
    if (labelbtnTitle == nil) {
        labelbtnTitle = [[UILabel alloc]initWithFrame:CGRectMake(0, btn7.frame.origin.y+btn7.frame.size.height+10,SCREENWIDTH,30)];
        labelbtnTitle.backgroundColor = Color_FirstBg;
        labelbtnTitle.textColor = [UIColor whiteColor];
        labelbtnTitle.font = [UIFont systemFontOfSize:16];
        [viewDeviceDate addSubview:labelbtnTitle];
    }
    labelbtnTitle.text = @"  检测种类";
    
    if (btn11 == nil) {
        btn11 = [[RightImageButton alloc]initWithFrame:CGRectMake(0, labelbtnTitle.frame.origin.y+labelbtnTitle.frame.size.height+5, SCREENWIDTH/2-0.5, 50)];
        btn11.backgroundColor = Color_FirstBg;
        btn11.tag = 11;
        [btn11 addTarget:self action:@selector(ClickTypeButton:) forControlEvents:UIControlEventTouchUpInside];
        [viewDeviceDate addSubview:btn11];
        
        UIView *viewLine1 = [[UIView alloc]initWithFrame:CGRectMake(btn11.frame.origin.x+btn11.frame.size.width, btn11.frame.origin.y, 1, btn11.frame.size.height+1)];
        viewLine1.backgroundColor = [UIColor whiteColor];
        [viewDeviceDate addSubview:viewLine1];
    }
    btn11.selected = YES;
    btn11.backgroundColor = Color_TypeButtonSelected;
    if (btn12 == nil) {
        btn12 = [[RightImageButton alloc]initWithFrame:CGRectMake(SCREENWIDTH/2+0.5, btn11.frame.origin.y, SCREENWIDTH/2-0.5, 50)];
        btn12.backgroundColor = Color_FirstBg;
        btn12.tag = 12;
        [btn12 addTarget:self action:@selector(ClickTypeButton:) forControlEvents:UIControlEventTouchUpInside];
        [viewDeviceDate addSubview:btn12];
    }
    if (itype == 1) {
        btn11.labelTitle.text = @"温度";
        btn12.labelTitle.text = @"湿度";
        btn11.imageRight.image = [UIImage imageNamed:@"ui_home_unit_temperature.png"];
        btn12.imageRight.image = [UIImage imageNamed:@"ui_home_unit_humidity.png"];
    }
    else if(itype == 2){
        btn11.labelTitle.text = @"燃气";
        btn12.labelTitle.text = @"一氧化碳";
        btn11.imageRight.image = [UIImage imageNamed:@"ui_home_unit_ch4.png"];
        btn12.imageRight.image = [UIImage imageNamed:@"ui_home_unit_co.png"];
    }

    if (itype == 1) {
        if (btn21 == nil) {
            btn21 = [[RightImageButton alloc]initWithFrame:CGRectMake(btn11.frame.origin.x, btn11.frame.origin.y+btn11.frame.size.height+1, SCREENWIDTH/2-0.5, 50)];
            btn21.backgroundColor = Color_FirstBg;
            btn21.tag = 21;
            [btn21 addTarget:self action:@selector(ClickTypeButton:) forControlEvents:UIControlEventTouchUpInside];
            [viewDeviceDate addSubview:btn21];
            btn21.labelTitle.text = @"二氧化碳";
            btn21.imageRight.image = [UIImage imageNamed:@"ui_home_unit_co2.png"];
            
            UIView *viewLine1 = [[UIView alloc]initWithFrame:CGRectMake(btn21.frame.origin.x+btn21.frame.size.width, btn21.frame.origin.y, 1, btn21.frame.size.height+1)];
            viewLine1.backgroundColor = [UIColor whiteColor];
            [viewDeviceDate addSubview:viewLine1];
            
            UIView *viewLine2 = [[UIView alloc]initWithFrame:CGRectMake(0, btn21.frame.origin.y, viewDeviceDate.frame.size.width, 1)];
            viewLine2.backgroundColor = [UIColor whiteColor];
            [viewDeviceDate addSubview:viewLine2];
        }
        if (btn22 == nil) {
            btn22 = [[RightImageButton alloc]initWithFrame:CGRectMake(btn12.frame.origin.x, btn12.frame.origin.y+btn12.frame.size.height+1, SCREENWIDTH/2-0.5, 50)];
            btn22.backgroundColor = Color_FirstBg;
            btn22.tag = 22;
            [btn22 addTarget:self action:@selector(ClickTypeButton:) forControlEvents:UIControlEventTouchUpInside];
            [viewDeviceDate addSubview:btn22];
            btn22.labelTitle.text = @"PM2.5";
            btn22.imageRight.image = [UIImage imageNamed:@"ui_home_unit_pm25.png"];
        }
        if (btn31 == nil) {
            btn31 = [[RightImageButton alloc]initWithFrame:CGRectMake(btn11.frame.origin.x, btn22.frame.origin.y+btn22.frame.size.height+1, SCREENWIDTH/2-0.5, 50)];
            btn31.backgroundColor = Color_FirstBg;
            btn31.tag = 31;
            [btn31 addTarget:self action:@selector(ClickTypeButton:) forControlEvents:UIControlEventTouchUpInside];
            [viewDeviceDate addSubview:btn31];
            btn31.labelTitle.text = @"VOC";
            btn31.imageRight.image = [UIImage imageNamed:@"ui_home_unit_voc.png"];
            
            UIView *viewLine1 = [[UIView alloc]initWithFrame:CGRectMake(btn31.frame.origin.x+btn31.frame.size.width, btn31.frame.origin.y, 1, btn31.frame.size.height+1)];
            viewLine1.backgroundColor = [UIColor whiteColor];
            [viewDeviceDate addSubview:viewLine1];
            
            UIView *viewLine2 = [[UIView alloc]initWithFrame:CGRectMake(0, btn31.frame.origin.y, viewDeviceDate.frame.size.width, 1)];
            viewLine2.backgroundColor = [UIColor whiteColor];
            [viewDeviceDate addSubview:viewLine2];
        }

        if (btn32 == nil) {
            btn32 = [[RightImageButton alloc]initWithFrame:CGRectMake(btn12.frame.origin.x, btn22.frame.origin.y+btn22.frame.size.height+1, SCREENWIDTH/2-0.5, 50)];
            btn32.backgroundColor = Color_FirstBg;
            btn32.tag = 32;
            [viewDeviceDate addSubview:btn32];
        }
        [self LoadTipInfoView:btn31.frame.origin.y+btn31.frame.size.height+10];
    }
    else{
        [self LoadTipInfoView:btn11.frame.origin.y+btn11.frame.size.height+10];
    }
}

-(void)LoadTipInfoView:(float)y{
    if (viewTipsInfo == nil) {
        viewTipsInfo = [[UIView alloc]initWithFrame:CGRectMake(10, y, SCREENWIDTH-20, 50)];
        viewTipsInfo.backgroundColor = Color_FirstBg;
        [viewDeviceDate addSubview:viewTipsInfo];
    }
    
    if (labelTip == nil) {
        labelTip = [[UILabel alloc]initWithFrame:CGRectMake(0, 0,viewTipsInfo.frame.size.width,50)];
        labelTip.backgroundColor = Color_FirstBg;
        labelTip.numberOfLines = 3;
        labelTip.textColor = [UIColor whiteColor];
        labelTip.font = [UIFont systemFontOfSize:14];
        [viewTipsInfo addSubview:labelTip];
    }
    //labelTip.text = @"你相当于吸了100包烟!";
}

/**
 *  根据数据计算并生成曲线图/////////////////////////////////////////////////////////////
 */
-(void)GetAndShowLineData:(NSDictionary *)linedata isair:(BOOL)isair gasname:(NSString *)gasname linedate:(NSArray *)linedate{
    [lineChartView removeFromSuperview];
    lineChartView = nil;
    if (lineChartView == nil) {
        float fLineViewWidth = viewDeviceDate.frame.size.width-10;
        float fLineHeight = FULLSCREENHEIGHT/4;
        lineChartView = [[PCLineChartView alloc] initWithFrame:CGRectMake(5,(scrollLinebg.frame.size.height-fLineHeight)/2,fLineViewWidth,fLineHeight)];
        [lineChartView setAutoresizingMask:UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleHeight];
        lineChartView.minValue = 0;
        lineChartView.maxValue = 10;
        lineChartView.interval = 2;
        [scrollLinebg addSubview:lineChartView];
    }
    
    if (linedate.count == 7) {
        CGRect lineframe = lineChartView.frame;
        lineframe.size.width = viewDeviceDate.frame.size.width;
        lineChartView.frame =  lineframe;
        scrollLinebg.contentSize = CGSizeMake(viewDeviceDate.frame.size.width-10, FULLSCREENHEIGHT/4);
    }
    else if (linedate.count == 24) {
        CGRect lineframe = lineChartView.frame;
        lineframe.size.width = 3*viewDeviceDate.frame.size.width;
        lineChartView.frame =  lineframe;
        scrollLinebg.contentSize = CGSizeMake(3*viewDeviceDate.frame.size.width, FULLSCREENHEIGHT/4);
    }
    else if (linedate.count == 30) {
        CGRect lineframe = lineChartView.frame;
        lineframe.size.width = 3*viewDeviceDate.frame.size.width;
        lineChartView.frame =  lineframe;
        scrollLinebg.contentSize = CGSizeMake(3*viewDeviceDate.frame.size.width, FULLSCREENHEIGHT/4);
    }
    
    NSString *strDeviceid = strSensorId;
    if ([strDeviceid isEqualToString:strSensorId]) {
        NSArray *arytemp = [linedata objectForKey:strDeviceid];
        if (isair) {
            NSSortDescriptor *_sorter  = [[NSSortDescriptor alloc] initWithKey:@"createTime"ascending:YES];
            [arytemp sortedArrayUsingDescriptors:[NSArray arrayWithObjects:_sorter, nil]];
            NSMutableArray *maryLineDatas = [[NSMutableArray alloc]init];
            
            for (NSInteger i = (arytemp.count-1);i >= 0;i--) {
                NSDictionary *dic = [arytemp objectAtIndex:i];
                NSString *strgas = @"";
                
                if ([gasname isEqualToString:@"温度"]) {
                    strgas = [dic objectForKey:@"temperature"];
                }
                else if ([gasname isEqualToString:@"湿度"]) {
                    strgas = [dic objectForKey:@"humidity"];
                }
                else if ([gasname isEqualToString:@"PM2.5"]) {
                    strgas = [dic objectForKey:@"pm25"];
                }
                else if ([gasname isEqualToString:@"VOC"]) {
                    strgas = [dic objectForKey:@"voc"];
                }
                else if ([gasname isEqualToString:@"二氧化碳"]) {
                    strgas = [dic objectForKey:@"co2"];
                }
                if (strgas.length == 0) {
                    strgas = OffLineChartLineCode;//用这个数做标记，未联网数据
                }
                [maryLineDatas addObject:strgas];
            }
            
            NSInteger max = [[maryLineDatas valueForKeyPath:@"@max.intValue"] integerValue];
            lineChartView.minValue = 0;
            if (max == 0 || max == [OffLineChartLineCode integerValue]) {
                lineChartView.maxValue = 10;
            }
            else{
                if (max%5 != 0) {
                    max += (5-max%5);
                }
                lineChartView.interval = max/5;
                lineChartView.maxValue = max;
            }
            NSMutableArray *components = [NSMutableArray array];
            PCLineChartViewComponent *component = [[PCLineChartViewComponent alloc] init];
            [component setTitle:gasname];
            [component setPoints:maryLineDatas];
            [component setShouldLabelValues:NO];
            [component setColour:[UIColor grayColor]];
            [components addObject:component];
            [lineChartView setComponents:components];
            [lineChartView setXLabels:linedate];
        }
        else
        {
            NSSortDescriptor *_sorter  = [[NSSortDescriptor alloc] initWithKey:@"createTime"ascending:YES];
            [arytemp sortedArrayUsingDescriptors:[NSArray arrayWithObjects:_sorter, nil]];
            
            NSMutableArray *maryLineDatas = [[NSMutableArray alloc]init];
            for (NSInteger i = (arytemp.count-1);i >= 0;i--) {
                NSDictionary *dic = [arytemp objectAtIndex:i];
                NSString *strgas = @"";
                
                if ([gasname isEqualToString:@"一氧化碳"]) {
                    strgas = [dic objectForKey:@"co"];
                }
                else if ([gasname isEqualToString:@"燃气"]) {
                    strgas = [dic objectForKey:@"ch4"];
                }
                
                if (strgas.length == 0) {
                    strgas = @"0.0";
                }
                [maryLineDatas addObject:strgas];
            }
            
            NSInteger max = [[maryLineDatas valueForKeyPath:@"@max.intValue"] integerValue];
            lineChartView.minValue = 0;
            if (max == 0) {
                lineChartView.maxValue = 10;
            }
            else{
                if (max%5 != 0) {
                    max += (5-max%5);
                }
                lineChartView.interval = max/5;
                lineChartView.maxValue = max;
            }
            NSMutableArray *components = [NSMutableArray array];
            PCLineChartViewComponent *component = [[PCLineChartViewComponent alloc] init];
            [component setTitle:gasname];
            [component setPoints:maryLineDatas];
            [component setShouldLabelValues:NO];
            [component setColour:[UIColor grayColor]];
            [components addObject:component];
            [lineChartView setComponents:components];
            [lineChartView setXLabels:linedate];
        }
    }
    
}
-(void)GetShareDatas{
    for (NSInteger i = 0; i < aryFAllDeviceid.count; i++) {
        NSString *strDeviceid = [aryFAllDeviceid objectAtIndex:i];
        if ([strDeviceid isEqualToString:strSensorId]) {
            NSArray *arytemp = [dicFLineData objectForKey:strDeviceid];
            if (dicAirData != nil) {
                NSSortDescriptor *_sorter  = [[NSSortDescriptor alloc] initWithKey:@"createTime"ascending:YES];
                [arytemp sortedArrayUsingDescriptors:[NSArray arrayWithObjects:_sorter, nil]];
                
                NSMutableArray *maryLineWeek = [[NSMutableArray alloc]initWithArray:[DateUtils getWeeksFromNow]];
                NSMutableArray *maryLineDatastemp = [[NSMutableArray alloc]init];
                NSMutableArray *maryLineDatashum = [[NSMutableArray alloc]init];
                NSMutableArray *maryLineDataspm25 = [[NSMutableArray alloc]init];
                NSMutableArray *maryLineDatasvoc = [[NSMutableArray alloc]init];
                NSMutableArray *maryLineDatasco2 = [[NSMutableArray alloc]init];
                
                for (NSInteger i = (arytemp.count-1);i >= 0;i--) {
                    NSDictionary *dic = [arytemp objectAtIndex:i];
                    [maryLineDatastemp addObject:[dic objectForKey:@"temperature"]];
                    [maryLineDatashum addObject:[dic objectForKey:@"humidity"]];
                    [maryLineDataspm25 addObject:[dic objectForKey:@"pm25"]];
                    [maryLineDatasvoc addObject:[dic objectForKey:@"voc"]];
                    [maryLineDatasco2 addObject:[dic objectForKey:@"co2"]];
                }
            }
            else
            {
                NSSortDescriptor *_sorter  = [[NSSortDescriptor alloc] initWithKey:@"createTime"ascending:YES];
                [arytemp sortedArrayUsingDescriptors:[NSArray arrayWithObjects:_sorter, nil]];
                NSMutableArray *maryLineWeek = [[NSMutableArray alloc]initWithArray:[DateUtils getWeeksFromNow]];
                NSMutableArray *maryLineDataco = [[NSMutableArray alloc]init];
                NSMutableArray *maryLineDatach4 = [[NSMutableArray alloc]init];
                for (NSInteger i = (arytemp.count-1);i >= 0;i--) {
                    NSDictionary *dic = [arytemp objectAtIndex:i];
                    [maryLineDataco addObject:[dic objectForKey:@"co"]];
                    [maryLineDatach4 addObject:[dic objectForKey:@"ch4"]];
                }
            }
        }
    }
}

-(void)LoadLineAndData{
    [lineChartView removeFromSuperview];
    lineChartView = nil;
    if (lineChartView == nil) {
        float fLineViewWidth = viewDeviceDate.frame.size.width-10;
        float fLineHeight = FULLSCREENHEIGHT/4;
        lineChartView = [[PCLineChartView alloc] initWithFrame:CGRectMake(5,(scrollLinebg.frame.size.height-fLineHeight)/2,fLineViewWidth,fLineHeight)];
        [lineChartView setAutoresizingMask:UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleHeight];
        lineChartView.minValue = 0;
        lineChartView.maxValue = 10;
        lineChartView.interval = 2;
        [scrollLinebg addSubview:lineChartView];
    }
    scrollLinebg.contentSize = CGSizeMake(viewDeviceDate.frame.size.width-10, FULLSCREENHEIGHT/4);
    
    if ([btnLineTitleRight.titleLabel.text isEqualToString:@"过去七天"]){
        [self ClickLineButton7];
//        if (dicAirData != nil) {
//            [self GetAndShowLineData:dicFLineData isair:YES gasname:strGasType linedate:[DateUtils getWeeksFromNow]];
//        }
//        else{
//            [self GetAndShowLineData:dicFLineData isair:NO gasname:strGasType linedate:[DateUtils getWeeksFromNow]];
//        }
    }
    else if ([btnLineTitleRight.titleLabel.text isEqualToString:@"过去三十天"]){
        [self ClickLineButton30];
        
        
//        if (dicAirData != nil) {
//            [self GetAndShowLineData:dicFLineData isair:YES gasname:strGasType linedate:[DateUtils getDaysFromNow]];
//        }
//        else{
//            [self GetAndShowLineData:dicFLineData isair:NO gasname:strGasType linedate:[DateUtils getDaysFromNow]];
//        }
    }
    else if ([btnLineTitleRight.titleLabel.text isEqualToString:@"过去24小时"]){
        [self ClickLineButton24];
//        if (dicAirData != nil) {
//            [self GetAndShowLineData:dicFLineData isair:YES gasname:strGasType linedate:[DateUtils getHoursFromNow]];
//        }
//        else{
//            [self GetAndShowLineData:dicFLineData isair:NO gasname:strGasType linedate:[DateUtils getHoursFromNow]];
//        }
    }

}
#pragma mark------------tools

//空气质量指数
-(void) setAQI:(int)pm25{
    NSString *status = @"";
    UIColor *color = nil;
    if(pm25 == 0){
        status = @"空";
        color = [UIColor colorWithRed:154/255.0 green:189/255.0 blue:207/255.0 alpha:1];
    }else if(pm25 <= PM25_VERY_GOOD){
        status = @"优";
        color = [UIColor colorWithRed:72/255.0 green:201/255.0 blue:100/255.0 alpha:1];
    }else if(pm25 > PM25_VERY_GOOD && pm25 <= PM25_GOOD){
        status = @"良";
        color = [UIColor colorWithRed:207/255.0 green:177/255.0 blue:67/255.0 alpha:1];
    }else if(pm25 >PM25_GOOD && pm25 <= PM25_WEAK){
        status = @"轻度";
        color = [UIColor colorWithRed:207/255.0 green:177/255.0 blue:67/255.0 alpha:1];
    }else if(pm25 > PM25_WEAK && pm25 <= PM25_BAD){
        status = @"中度";
        color = [UIColor colorWithRed:223/255.0 green:95/255.0 blue:50/255.0 alpha:1];
    }else if (pm25 > PM25_BAD && pm25 <= PM25_VERY_BAD){
        status = @"重度";
        color = [UIColor colorWithRed:223/255.0 green:95/255.0 blue:50/255.0 alpha:1];
    }else if(pm25 > PM25_VERY_BAD){
        status = @"严重";
        color = [UIColor colorWithRed:223/255.0 green:95/255.0 blue:50/255.0 alpha:1];
    }
    
    [labelStatus setText:status];
    if (color != nil) {
        [labelStatus setBackgroundColor:color];
    }
}

-(void)ClickArrowTop{
    btnArrowTop.selected = !btnArrowTop.selected;
    if (!btnArrowTop.selected) {
        if (viewDeviceDate.frame.origin.y != fy_Device) {
            [UIView animateWithDuration:0.3 animations:^{
                CGRect frame = viewDeviceDate.frame;
                frame.origin.y = fy_Device;
                viewDeviceDate.frame = frame;
            } completion:^(BOOL finished){
            }];
            fPanOldY = viewDeviceDate.center.y;
        }
    }
    else{
        if (viewDeviceDate.frame.origin.y != 0) {
            [UIView animateWithDuration:0.3 animations:^{
                CGRect frame = viewDeviceDate.frame;
                frame.origin.y = 0;
                viewDeviceDate.frame = frame;
            } completion:^(BOOL finished){
            }];
            fPanOldY = viewDeviceDate.center.y;
        }
    }
}
-(void)ClickArrowLeft{
}
-(void)ClickArrowRight{
}
//点击全国排名
-(void)ClickValueRank{
    [delegate FirstContentViewRank:self data:dicAllData];
}
/**
 *  分析获取到的历史数据并分组以便曲线图展示
 *
 *  @param aryDataObject 数据
 */
-(void)getHistoryValues:(NSArray *)aryDataObject lastpar:(NSString *)lastpar{
    if (mdicHistory.count != 0) {
        [mdicHistory removeAllObjects];
    }
    NSDictionary *dicdataObject = [aryDataObject firstObject];
    NSArray *arysensorList = [dicdataObject objectForKey:@"sensorList"];
    if (dicFLineData.count != 0) {
        [dicFLineData removeAllObjects];
    }
    NSMutableArray *marytemp = [[NSMutableArray alloc]init];
    for (NSDictionary *dic in arysensorList) {
        if ([dic objectForKey:@"air"] != nil) {
            [marytemp addObject:[dic objectForKey:@"air"]];
        }
        else{
            [marytemp addObject:[dic objectForKey:@"gas"]];
        }
    }
    [dicFLineData setObject:marytemp forKey:strSensorId];
    
    if ([lastpar isEqualToString:@"7"]) {
        if (dicAirData != nil) {
            [self GetAndShowLineData:dicFLineData isair:YES gasname:strGasType linedate:[DateUtils getWeeksFromNow]];
        }
        else{
            [self GetAndShowLineData:dicFLineData isair:NO gasname:strGasType linedate:[DateUtils getWeeksFromNow]];
        }
    }
    else if ([lastpar isEqualToString:@"24"]){
        if (dicAirData != nil) {
            [self GetAndShowLineData:dicFLineData isair:YES gasname:strGasType linedate:[DateUtils getHoursFromNow]];
        }
        else{
            [self GetAndShowLineData:dicFLineData isair:NO gasname:strGasType linedate:[DateUtils getHoursFromNow]];
        }
    }
    else if ([lastpar isEqualToString:@"30"]){
        if (dicAirData != nil) {
            [self GetAndShowLineData:dicFLineData isair:YES gasname:strGasType linedate:[DateUtils getDaysFromNow]];
        }
        else{
            [self GetAndShowLineData:dicFLineData isair:NO gasname:strGasType linedate:[DateUtils getDaysFromNow]];
        }
    }
}
/**
 *  获取各种格式的历史数据
 *
 *  @param type    1，按小时 2,按天
 *  @param lastpar 时间长度
 */
-(void)dolistDetailHistory:(NSString *)type lastpar:(NSString *)lastpar{
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:DOLISTDETAILHISTORY]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    //读取NSString类型的数据
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:strSensorId forKey:@"SENSORID"];
    [request setPostValue:type forKey:@"TYPE"];
    [request setPostValue:lastpar forKey:@"LASTPAR"];
    [request setCompletionBlock:^{
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        if ([result objectForKey:@"dataObject"] != [NSNull null]) {
            NSArray *aryDataObject = [result objectForKey:@"dataObject"];
            if (aryDataObject.count != 0) {
                [self getHistoryValues:aryDataObject lastpar:lastpar];
            }
        }
    }];
    [request setFailedBlock :^{
        NSError *error = [request error];
        if (error) {
            NSLog(@"ClickLineTitleRight:%@",error.description);
        }
    }];
    [request startAsynchronous];
}


//点击历史记录
-(void)ClickHistory{
    if (historyView == nil) {
        historyView = [[HistoryView alloc]initWithFrame:CGRectMake(0, 0, rect.size.width-80, rect.size.height/2-100) deviceid:strSensorId];
    }
    [[KGModal sharedInstance] showWithContentView:historyView andAnimated:YES];
}

-(NSString *)AirShareDataUrl{
    NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
    [dateFormat setDateFormat:@"MM-dd"];
    NSString *strrecordDate = [dateFormat stringFromDate:[NSDate date]];
    
    NSDateFormatter* dateFormat2 = [[NSDateFormatter alloc] init];
    [dateFormat2 setDateFormat:@"hh-mm"];
    NSString *strrecordTime = [dateFormat2 stringFromDate:[NSDate date]];
    
    NSString *strtemp = @"";
    NSString *strhum  = @"";
    NSString *strpm25 = @"";
    NSString *strvoc  = @"";
    NSString *strco2  = @"";
    NSString *strweek = @"";
    for (NSInteger i = 0; i < aryFAllDeviceid.count; i++) {
        NSString *strDeviceid = [aryFAllDeviceid objectAtIndex:i];
        if ([strDeviceid isEqualToString:strSensorId]) {
            NSArray *arytemp = [dicFLineData objectForKey:strDeviceid];
            NSSortDescriptor *_sorter  = [[NSSortDescriptor alloc] initWithKey:@"createTime"ascending:YES];
            [arytemp sortedArrayUsingDescriptors:[NSArray arrayWithObjects:_sorter, nil]];
            
            for (NSInteger i = (arytemp.count-1);i >= 0;i--) {
                NSDictionary *dic = [arytemp objectAtIndex:i];
                NSString *strtmptemp = [dic objectForKey:@"temperature"];
                NSString *strtmphum = [dic objectForKey:@"humidity"];
                NSString *strtmppm25 = [dic objectForKey:@"pm25"];
                NSString *strtmpvoc = [dic objectForKey:@"voc"];
                NSString *strtmpco2 = [dic objectForKey:@"co2"];
                if (strtmptemp.length == 0) {
                    strtmptemp = @"0.0";
                }
                if (strtmphum.length == 0) {
                    strtmphum = @"0.0";
                }
                if (strtmppm25.length == 0) {
                    strtmppm25 = @"0.0";
                }
                if (strtmpvoc.length == 0) {
                    strtmpvoc = @"0.0";
                }
                if (strtmpco2.length == 0) {
                    strtmpco2 = @"0.0";
                }
                strtemp = [strtemp stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmptemp]];
                strhum = [strhum stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmphum]];
                strpm25 = [strpm25 stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmppm25]];
                strvoc = [strvoc stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmpvoc]];
                strco2 = [strco2 stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmpco2]];
                
                strweek = [strweek stringByAppendingString:[NSString stringWithFormat:@"%@,",[DateUtils WeekToCh:[dic objectForKey:@"createTime"]]]];
            }
        }
    }
    NSString* strAirshareurl = [NSString stringWithFormat:@"%@%@?temperature=%@&humidity=%@&pm25=%@&voc=%@&co2=%@&name=[temperature,humidity,pm25,voc,co2]&dates=[%@]&data1=[%@]&data2=[%@]&data3=[%@]&data4=[%@]&data5=[%@]&recordDate=%@&recordTime=%@",BASE_URI,SHARE_URI,dicAirData.temperature,dicAirData.humidity,dicAirData.pm25,dicAirData.voc,dicAirData.co2,strweek,strtemp,strhum,strpm25,strvoc,strco2,strrecordDate,strrecordTime];
    return strAirshareurl;
}
-(NSString *)GasShareDataUrl{
    NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
    [dateFormat setDateFormat:@"MM-dd"];
    NSString *strrecordDate = [dateFormat stringFromDate:[NSDate date]];
    
    NSDateFormatter* dateFormat2 = [[NSDateFormatter alloc] init];
    [dateFormat2 setDateFormat:@"hh-mm"];
    NSString *strrecordTime = [dateFormat2 stringFromDate:[NSDate date]];
    
    NSString *strch4 = @"";
    NSString *strco = @"";
    NSString *strweek = @"";
    for (NSInteger i = 0; i < aryFAllDeviceid.count; i++) {
        NSString *strDeviceid = [aryFAllDeviceid objectAtIndex:i];
        if ([strDeviceid isEqualToString:strSensorId]) {
            NSArray *arytemp = [dicFLineData objectForKey:strDeviceid];
            NSSortDescriptor *_sorter  = [[NSSortDescriptor alloc] initWithKey:@"createTime"ascending:YES];
            [arytemp sortedArrayUsingDescriptors:[NSArray arrayWithObjects:_sorter, nil]];
            
            for (NSInteger i = (arytemp.count-1);i >= 0;i--) {
                NSDictionary *dic = [arytemp objectAtIndex:i];
                
                NSString *strtmpch4 = [dic objectForKey:@"ch4"];
                NSString *strtmpco = [dic objectForKey:@"co"];
                if (strtmpch4.length == 0) {
                    strtmpch4 = @"0.0";
                }
                if (strtmpco.length == 0) {
                    strtmpco = @"0.0";
                }
                strch4 = [strch4 stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmpch4]];
                strco = [strco stringByAppendingString:[NSString stringWithFormat:@"%@,",strtmpco]];
                strweek = [strweek stringByAppendingString:[NSString stringWithFormat:@"%@,",[DateUtils WeekToCh:[dic objectForKey:@"createTime"]]]];
            }
        }
    }
    NSString* strAirshareurl = [NSString stringWithFormat:@"%@%@?co=%@&ch4=%@&name=[co,ch4]&dates=[%@]&data1=[%@]&data2=[%@]&recordDate=%@&recordTime=%@",BASE_URI,SHARE_URI,dicGasData.co,dicGasData.ch4,strweek,strco,strch4,strrecordDate,strrecordTime];
    return strAirshareurl;
}
-(NSString *)GetShareUrl{
    if (dicAirData != nil) {
        return [self AirShareDataUrl];
    }
    else{
        return [self GasShareDataUrl];
    }
}
//点击分享
-(void)ClickShare{
    //构造分享内容 share/share.html
    [self performSelector:@selector(delayShare) withObject:nil afterDelay:0.5f];
}
-(void)delayShare{
    NSString *strPath = [self renderScrollViewToImage];
    NSString *strContent = @"";
    NSString *strUrl = [self GetShareUrl];
    if (dicAirData != nil) {
        strContent = [NSString stringWithFormat:@"空气电台播报:温度[%@]湿度[%@]二氧化碳[%@]PM2.5[%@]Voc[%@]",labelTemp.text,labelWet.text,labelCo2.text,labelPm.text,labelVoc.text];
    }
    else{
        strContent = [NSString stringWithFormat:@"空气电台播报:燃气[%@]一氧化碳[%@]",labelGas.text,labelCo.text];
    }
    UIImage *image = [UIImage imageWithContentsOfFile:strPath];
    [self shareTitle:@"空气电台" content:strContent image:image url:strUrl];
}
-(void)ClickTypeButton:(id)sender{
    NSInteger itag = [sender tag];
    RightImageButton *buttontmp = nil;
    if (itag == 11) {
        buttontmp = btn11;
    }
    else if (itag == 12){
        buttontmp = btn12;
    }
    else if (itag == 21){
        buttontmp = btn21;
    }
    else if (itag == 22){
        buttontmp = btn22;
    }
    else if (itag == 31){
        buttontmp = btn31;
    }
    else if (itag == 32){
    }
    if (!buttontmp.selected) {
        labelLineTitleLeft.text = buttontmp.labelTitle.text;
        strGasType = labelLineTitleLeft.text;
        [self LoadLineAndData];
    }
    if (itag == 11) {
        btn11.backgroundColor = Color_TypeButtonSelected;
        btn12.backgroundColor = Color_FirstBg;
        btn21.backgroundColor = Color_FirstBg;
        btn22.backgroundColor = Color_FirstBg;
        btn31.backgroundColor = Color_FirstBg;
        
        btn11.selected = YES;
        btn12.selected = NO;
        btn21.selected = NO;
        btn22.selected = NO;
        btn31.selected = NO;
    }
    else if (itag == 12){
        btn11.backgroundColor = Color_FirstBg;
        btn12.backgroundColor = Color_TypeButtonSelected;
        btn21.backgroundColor = Color_FirstBg;
        btn22.backgroundColor = Color_FirstBg;
        btn31.backgroundColor = Color_FirstBg;
        btn11.selected = NO;
        btn12.selected = YES;
        btn21.selected = NO;
        btn22.selected = NO;
        btn31.selected = NO;
    }
    else if (itag == 21){
        btn11.backgroundColor = Color_FirstBg;
        btn12.backgroundColor = Color_FirstBg;
        btn21.backgroundColor = Color_TypeButtonSelected;
        btn22.backgroundColor = Color_FirstBg;
        btn31.backgroundColor = Color_FirstBg;
        btn11.selected = NO;
        btn12.selected = NO;
        btn21.selected = YES;
        btn22.selected = NO;
        btn31.selected = NO;
    }
    else if (itag == 22){
        btn11.backgroundColor = Color_FirstBg;
        btn12.backgroundColor = Color_FirstBg;
        btn21.backgroundColor = Color_FirstBg;
        btn22.backgroundColor = Color_TypeButtonSelected;
        btn31.backgroundColor = Color_FirstBg;
        btn11.selected = NO;
        btn12.selected = NO;
        btn21.selected = NO;
        btn22.selected = YES;
        btn31.selected = NO;
    }
    else if (itag == 31){
        btn11.backgroundColor = Color_FirstBg;
        btn12.backgroundColor = Color_FirstBg;
        btn21.backgroundColor = Color_FirstBg;
        btn22.backgroundColor = Color_FirstBg;
        btn31.backgroundColor = Color_TypeButtonSelected;
        btn11.selected = NO;
        btn12.selected = NO;
        btn21.selected = NO;
        btn22.selected = NO;
        btn31.selected = YES;
    }

}

-(void)shareTitle:(NSString *)title content:(NSString *)content image:(UIImage *)img url:(NSString *)url
{

    //分享的 底ViewControoler
    id<ISSContainer> container = [ShareSDK container];
    
    //可以 设置 sharesdk 弹出的底ViewController
    //[container setIPhoneContainerWithViewController:nil];
    //自动授权
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:nil];
    
    //要分享的列表
    NSArray *shareList = [ShareSDK getShareListWithType:ShareTypeSinaWeibo, ShareTypeTencentWeibo, ShareTypeQQSpace,ShareTypeWeixiSession,ShareTypeWeixiTimeline,ShareTypeQQ,nil];
    
    //加入分享的图片
    id<ISSCAttachment> shareImage = nil;
    SSPublishContentMediaType shareType = SSPublishContentMediaTypeText;
    if(img)
    {
        shareImage = [ShareSDK pngImageWithImage:img];
        shareType = SSPublishContentMediaTypeNews;
    }
    
    //分享的内容
    id<ISSContent>publishContent = [ShareSDK content:content
                                      defaultContent:@"空气电台播报"
                                               image:shareImage
                                               title:title
                                                 url:url
                                         description:@"空气电台播报"
                                           mediaType:shareType];
    
    //弹出分享菜单
    [ShareSDK showShareActionSheet:container
                         shareList:shareList
                           content:publishContent
                     statusBarTips:YES
                       authOptions:authOptions
                      shareOptions:nil
                            result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                if (state == SSResponseStateSuccess)
                                {
                                    NSLog(@"分享成功");
                                }
                                else if (state == SSResponseStateFail)
                                {
                                    MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.window animated:YES];
                                    HUD2.mode = MBProgressHUDModeText;
                                    HUD2.labelText = [NSString stringWithFormat:@"分享失败(尚未安装相关APP)"];
                                    HUD2.margin = 10.f;
                                    HUD2.yOffset = 0.f;
                                    HUD2.removeFromSuperViewOnHide = YES;
                                    [HUD2 hide:YES afterDelay:1.0];
                                    NSLog(@"发布失败!error code == %d, error code == %@", [error errorCode], [error errorDescription]);
                                }
                            }];
    
}

/**
 *  截取uiscrollview
 *
 *  @return 截图的path
 */
- (NSString *) renderScrollViewToImage
{
    UIImage* image = nil;
    UIGraphicsBeginImageContext(scrollview.contentSize);
    {
        CGPoint savedContentOffset = scrollview.contentOffset;
        CGRect savedFrame = scrollview.frame;
        
        scrollview.contentOffset = CGPointZero;
        scrollview.frame = CGRectMake(0, 0, scrollview.contentSize.width, scrollview.contentSize.height);
        
        [imageHomeBg.layer renderInContext: UIGraphicsGetCurrentContext()];
        image = UIGraphicsGetImageFromCurrentImageContext();
        
        scrollview.contentOffset = savedContentOffset;
        scrollview.frame = savedFrame;
    }
    UIGraphicsEndImageContext();
    
    if (image != nil) {
        NSLog(@"Succeeded! %@",[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"shareTmp.png"]);
        [UIImagePNGRepresentation(image) writeToFile:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"shareTmp.png"] atomically: YES];
        return [PATH_OF_DOCUMENT stringByAppendingPathComponent:@"shareTmp.png"];
    }
    else{
        NSLog(@"Failed!");
        return @"";
    }
}

-(NSString *)renderViewToImage{
    if(UIGraphicsBeginImageContextWithOptions != NULL)
    {
        UIGraphicsBeginImageContextWithOptions(CGSizeMake(scrollview.contentSize.width, scrollview.contentSize.height-TopBarHeight), NO, 0.0);
    } else {
        UIGraphicsBeginImageContext(scrollview.contentSize);
    }
    
    //获取图像
    [imageHomeBg.layer renderInContext:UIGraphicsGetCurrentContext()];
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    //保存图像
    NSString *path = [PATH_OF_DOCUMENT stringByAppendingPathComponent:@"shareTmp.png"];
    if ([UIImagePNGRepresentation(image) writeToFile:path atomically:YES]) {
        NSLog(@"Succeeded! %@",path);
        return path;
    }
    else {
        return @"";
        NSLog(@"Failed!");
    }
}

//点击豆
-(void)ClickCortoon{
//    给卡通添加POP效果
//    POPSpringAnimation *springAnimation = [POPSpringAnimation animationWithPropertyNamed:kPOPLayerSize];
//    if (btnCortoon.frame.size.width == 5*DOUWIDTH/6) {
//        springAnimation.toValue = [NSValue valueWithCGSize:CGSizeMake(5*DOUWIDTH/6+5, 5*DOUHEIGHT/6+5)];
//    }
//    else{
//        springAnimation.toValue = [NSValue valueWithCGSize:CGSizeMake(5*DOUWIDTH/6, 5*DOUHEIGHT/6)];
//    }
//    springAnimation.springBounciness = 50.0;
//    springAnimation.springSpeed = 70.0;
//    [btnCortoon.layer pop_addAnimation:springAnimation forKey:@"changesize"];
    
    
    if ([labelUpdateDate.text isEqualToString:@"传感器未联网"]) {
        NSString *strTalktext = EXPRESS_OUTLINE;
        [btnCortoon setImage:[carToonInfo.expressImages objectAtIndex:0] forState:UIControlStateNormal];
        [self showPopMessage:strTalktext];
    }
    else{
        NSInteger imaxCartoonClickIndex = carToonInfo.talkText.count;
        if (iCartoonClickIndex >= imaxCartoonClickIndex) {
            iCartoonClickIndex = 0;
        }
        NSString *strTalktext = [carToonInfo.talkText objectAtIndex:iCartoonClickIndex];
        [btnCortoon setImage:[carToonInfo.expressImages objectAtIndex:iCartoonClickIndex] forState:UIControlStateNormal];
        [self showPopMessage:strTalktext];
        iCartoonClickIndex++;
    }
}
#pragma mark--------------PopMessage

-(void)showPopMessage:(NSString *)message{
    [self dismissAllPopTipViews];
    UIColor *backgroundColor = [UIColor colorWithRed:255.0/255.0 green:255/255.0 blue:255/255.0 alpha:0.8];
    UIColor *textColor = [UIColor blackColor];

    CMPopTipView *popTipView = [[CMPopTipView alloc] initWithMessage:message];
    popTipView.delegate = self;
    
    /* Some options to try.
     */
    //popTipView.disableTapToDismiss = YES;
    popTipView.dismissTapAnywhere = NO;
    popTipView.preferredPointDirection = PointDirectionDown;
    popTipView.hasGradientBackground = NO;
    popTipView.cornerRadius = 3.0;
    popTipView.has3DStyle = NO;
    popTipView.borderWidth = 0.0;
    popTipView.sidePadding = 40.0f;
    //popTipView.topMargin = 20.0f;
    //popTipView.pointerSize = 50.0f;
    popTipView.hasShadow = NO;
    //popTipView.disableTapToDismiss = YES;
    if (backgroundColor && ![backgroundColor isEqual:[NSNull null]]) {
        popTipView.backgroundColor = backgroundColor;
    }
    if (textColor && ![textColor isEqual:[NSNull null]]) {
        popTipView.textColor = textColor;
        popTipView.textFont = [UIFont systemFontOfSize:14.0];
    }
    popTipView.animation = arc4random() % 2;
    //  popTipView.has3DStyle = (BOOL)(arc4random() % 2);
    [popTipView autoDismissAnimated:NO atTimeInterval:4.0];
    
    [popTipView presentPointingAtView:btnCortoon inView:self animated:YES];
    [self.visiblePopTipViews addObject:popTipView];
    self.currentPopTipViewTarget = btnCortoon;
    
    CGRect frame = popTipView.frame;
    frame.origin.y = frame.origin.y+60;
    popTipView.frame = frame;
}
- (void)dismissAllPopTipViews
{
    if  ([self.visiblePopTipViews count] > 0) {
        CMPopTipView *popTipView = [self.visiblePopTipViews objectAtIndex:0];
        [popTipView dismissAnimated:YES];
        [self.visiblePopTipViews removeObjectAtIndex:0];
    }
}

-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    if (self.visiblePopTipViews.count != 0) {
        [self dismissAllPopTipViews];
    }
    
    //页面往上拉卡通逐渐透明的效果
    if (scrollView.contentOffset.y <= 0.0) {
        btnCortoon.alpha = 1.0;
    }
    else{
        if (scrollView.contentOffset.y >= (FULLSCREENHEIGHT-btnCortoon.frame.origin.y)) {
            btnCortoon.alpha = 0.0;
        }
        else{
            btnCortoon.alpha = 1.0-scrollView.contentOffset.y/(FULLSCREENHEIGHT-btnCortoon.frame.origin.y);
        }
    }
    //NSLog(@"contentOffset:%f",scrollView.contentOffset.y);
}
#pragma mark - CMPopTipViewDelegate methods

- (void)popTipViewWasDismissedByUser:(CMPopTipView *)popTipView
{
    [self.visiblePopTipViews removeObject:popTipView];
    self.currentPopTipViewTarget = nil;
    
}
@end
