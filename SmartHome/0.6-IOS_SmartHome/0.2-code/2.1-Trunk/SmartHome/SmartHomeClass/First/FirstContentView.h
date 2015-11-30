//
//  FirstContentView.h
//  SmartHome
//
//  Created by apple on 14-11-12.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <ShareSDK/ShareSDK.h>
#import "RESideMenu.h"
#import "CMPopTipView.h"
#import "PCLineChartView.h"
#import "KGModal.h"
#import "HistoryView.h"
#import "AirDevice.h"
#import "GasDevice.h"
#import "CartoonInfo.h"

#import "DeviceUtil.h"
#import "RightImageButton.h"
#import "ServerConstants.h"
#import "../Utils/DateUtils.h"
@class FirstContentView;

@protocol FirstContentViewDelegate <NSObject>
-(void)FirstContentViewUpdataHardWare:(FirstContentView *)firstContentView sensorid:(NSString *)sensorid;
-(void)FirstContentViewRank:(FirstContentView *)firstContentView data:(NSDictionary *)dicdata;
-(void)FirstContentViewSendAlarm:(FirstContentView *)firstContentView data:(NSDictionary *)dicdata sensorName:(NSString *)sensorName;
@end

@interface FirstContentView : UIView<CMPopTipViewDelegate,UIGestureRecognizerDelegate,UIScrollViewDelegate,UIAlertViewDelegate>{
    CGRect                 rect;
    float                  fx_Info;         //主页面文本左gap
    float                  fy_Device;//设备信息View的Y
    float                  fPanOldY;
    UIScrollView           * scrollview;


    NSDictionary           * dicAllData;
    NSString               * strSensorId;
    CartoonInfo            * carToonInfo;

    HistoryView            * historyView;
    NSInteger              iCartoonClickIndex;
    UIPanGestureRecognizer * panGestureRecognizer;

    NSString               * todayDate;
    NSTimer                * timerAlarm;

    UIView                 * viewValueBg;
    UILabel                * labelUpdateTitle;
    UILabel                * labelStatusTitle;
    UIButton               * btnArrowTop;
    UIButton               * btnArrowLeft;
    UIButton               * btnArrowRight;

    UIImageView            * imageHomeBg;
    UILabel                * labelUpdateDate;
    UILabel                * labelValue;
    UILabel                * labelStatus;

    UIButton               * btnValueRank;
    UIButton               * btnHistory;
    UIButton               * btnShare;
    UIButton               * btnCortoon;
    UIImageView            * imageValueRankIcon;
    UILabel                * labelValueRank;
     //////////////////////////////////////////////////////////////////////
     //监测设备数据展示所需变量
    UIView                 * viewDeviceDate;
    UILabel                * labelDeviceName;

    UIImageView            * imageTemp;
    UILabel                * labelTemp;
    UIImageView            * imageWet;
    UILabel                * labelWet;
    UIImageView            * imageCo2;
    UILabel                * labelCo2;
    UIImageView            * imagePm;
    UILabel                * labelPm;
    UIImageView            * imageVoc;
    UILabel                * labelVoc;

    UIImageView            * imageGas;
    UILabel                * labelGas;
    UIImageView            * imageCo;
    UILabel                * labelCo;

    UIView                 * viewDeviceNamebg;
    UIView                 * viewDevicebg;
    UIView                 * viewLineTitlebg;
    UILabel                * labelLineTitleLeft;
    UIButton               * btnLineTitleRight;
    UIScrollView           * scrollLinebg;
    UIView                 * viewTipsInfo;
    UILabel                * labelTip;
    UILabel                * labelbtnTitle;

    NSString               * strGasType;
    RightImageButton       * btn11;
    RightImageButton       * btn12;
    RightImageButton       * btn21;
    RightImageButton       * btn22;
    RightImageButton       * btn31;
    RightImageButton       * btn32;

    BOOL                   bNight;
    BOOL                   bGotLineData;
    UIActivityIndicatorView *ActivityView;
    UIButton *btnUpdateHardVersion;
    
    NSMutableDictionary *mdicHistory;

    NSTimer *timerUpdateHard;
    UILabel *labelUpdateHard;
    BOOL bhardupdating;
    
    UIButton *btn7;
    UIButton *btn24;
    UIButton *btn30;
    
}
@property (nonatomic, strong) NSDictionary *dicSensorDetail;
@property (nonatomic, strong) NSString *strlocalSoftVersion;
@property (nonatomic, strong) NSString *strremoteSoftVersion;
@property (nonatomic, strong) NSMutableDictionary      *dicFLineData;
@property (nonatomic, strong) NSArray                  *aryFAllDeviceid;
@property (nonatomic, strong) AirDevice                *dicAirData;
@property (nonatomic, strong) GasDevice                *dicGasData;
@property (nonatomic, strong) id<FirstContentViewDelegate> delegate;
@property (nonatomic, strong) id                       currentPopTipViewTarget;
@property (nonatomic, strong) NSMutableArray           *visiblePopTipViews;
@property (strong, nonatomic) PCLineChartView          *lineChartView;

-(void)initData:(NSDictionary *)data AirDevice:(AirDevice *)airinfo GasDevice:(GasDevice *)gasinfo;
-(id)initWithFrame:(CGRect)frame alldata:(NSDictionary *)data AirDevice:(AirDevice *)airinfo GasDevice:(GasDevice *)gasinfo;
-(void)LoadTopViewData:(CartoonInfo *)cartooninfo;
-(void)LoadTopView;
-(void)LoadAirInfoView;
-(void)LoadGasInfoView;
-(void)LoadAirInfoData;
-(void)LoadGasInfoData;
-(void)GetLineData;
-(void)hideRightArrow;
-(void)hideLeftArrow;
-(void)beginHardWareUpdate;
-(void)StopAlarmTimer;

-(void)startUpdateHardWare;
-(void)stopUpdateHardWare;
@end