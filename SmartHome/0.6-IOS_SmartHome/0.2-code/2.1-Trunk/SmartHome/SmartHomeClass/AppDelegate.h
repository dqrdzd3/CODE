//
//  AppDelegate.h
//  YRSideViewController
//
//  Created by wbiao on 14-5-10.
//  Copyright (c) 2014年 Apple. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"
#import "./ShareSDK/Extend/WeChatSDK/WXApi.h"
#import "./ShareSDK/Extend/TencentWeiboSDK/WeiboApi.h"
#import <TencentOpenAPI/QQApi.h>
#import <TencentOpenAPI/QQApiInterface.h>
#import <TencentOpenAPI/TencentOAuth.h>
#import "WeiboSDK.h"
#import "ShareSDK/ShareSDK.framework/Headers/ShareSDK.h"
#import "BMapKit.h"
#import "GuideViewController.h"
#import "LoginViewController.h"

@class LeftMenuViewController;
@class RightMenuViewController;
@interface AppDelegate : UIResponder <UIApplicationDelegate,RESideMenuDelegate,WXApiDelegate,UIAlertViewDelegate,LoginViewControllerDelegate>{
    BMKMapManager* _mapManager;
    NSString *trackViewURL;
    
    enum WXScene _scene;
    SSInterfaceOrientationMask _interfaceOrientationMask;
    
    
    LeftMenuViewController *leftmenuView;
    RightMenuViewController *rightMenuView;
    RESideMenu *sideMenuViewController;
}

@property (nonatomic) SSInterfaceOrientationMask interfaceOrientationMask;
@property (strong,nonatomic) UIWindow *window;
@property (strong,nonatomic) UINavigationController *navController;
@property(strong, nonatomic) RESideMenu *sideMenu;
+ (NSInteger)OSVersion;
-(void)ShowLoginView;
@end
