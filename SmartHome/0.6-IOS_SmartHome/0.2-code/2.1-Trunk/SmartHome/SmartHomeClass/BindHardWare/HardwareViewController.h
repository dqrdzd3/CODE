//
//  HardwareViewController.h
//  SmartHome
//
//  Created by 李静 on 14-11-24.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "EASYLINK.h"
#import "Reachability.h"
#import "RTLabel.h"

@interface HardwareViewController : UIViewController<UITextFieldDelegate, EasyLinkFTCDelegate>{
    Reachability *wifiReachability;
    EASYLINK *easylink_config;
    
    NSMutableDictionary *deviceIPConfig;
    NSTimer *timer;
    NSTimer *configTimer;
    MBProgressHUD *HUD;
}

@property (strong, nonatomic) UILabel *wifiName;
@property (strong, nonatomic) UITextField *wifiPassword;
@property (strong, nonatomic) UIButton *lastBtn;
@property (strong, nonatomic) UIButton *bindBtn;
@property (strong, nonatomic) UIImageView *desImage;
@property (strong, nonatomic) RTLabel *configLabel;
@property (strong, nonatomic) UIButton *finishBtn;
@property (nonatomic,copy)    NSString *deviceId;
@property (strong, nonatomic) UIScrollView *scrollView;

- (void)finishBtnClick:(id)sender;
- (void)bindBtnClick:(id)sender;
- (void)lastBtnClick:(id)sender;
@end
