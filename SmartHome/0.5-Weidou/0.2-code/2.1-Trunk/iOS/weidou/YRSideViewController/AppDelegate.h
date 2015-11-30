//
//  AppDelegate.h
//  YRSideViewController
//
//  Created by wbiao on 14-5-10.
//  Copyright (c) 2014年 Apple. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Macro.h"
#import "RESideMenu.h"
#import "ShareSDK/Extend/WeChatSDK/WXApi.h"
#import "ShareSDK/Extend/TencentWeiboSDK/WeiboApi.h"
#import "ShareSDK/Extend/TencentWeiboSDK/WeiboApiObject.h"
#import <TencentOpenAPI/QQApi.h>
#import <TencentOpenAPI/QQApiInterface.h>
#import <TencentOpenAPI/TencentOAuth.h>
#import "WeiboSDK.h"
#import "ShareSDK/ShareSDK.framework/Headers/ShareSDK.h"
#import "AGViewDelegate.h"

@class HomeViewController;
@interface AppDelegate : UIResponder <UIApplicationDelegate,RESideMenuDelegate,WXApiDelegate>{
    
}

@property (nonatomic,readonly) AGViewDelegate *viewDelegate;
@property (nonatomic) SSInterfaceOrientationMask interfaceOrientationMask;
@property (strong,nonatomic) UIWindow *window;
@property (strong,nonatomic) UINavigationController *navController;
@property(strong, nonatomic) RESideMenu *sideMenu;
+ (NSInteger)OSVersion;
@end
