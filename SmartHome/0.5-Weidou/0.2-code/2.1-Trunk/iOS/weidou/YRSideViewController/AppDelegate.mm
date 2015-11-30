//
//  AppDelegate.m
//  YRSideViewController
//
//  Created by wbiao on 14-5-10.
//  Copyright (c) 2014年 Apple. All rights reserved.
//

#import "AppDelegate.h"
#import "LeftMenuViewController.h"
#import "RealTimeModel.h"
#define IS_WIDESCREEN ( fabs( ( double )[ [ UIScreen mainScreen ] bounds ].size.height - ( double )568 ) < DBL_EPSILON )
@implementation AppDelegate
@synthesize navController;
//@synthesize setupViewController;
//@synthesize teamViewController;
//@synthesize helpViewController;
//@synthesize aboutViewController;

+ (NSInteger)OSVersion
{
    static NSUInteger _deviceSystemMajorVersion = -1;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _deviceSystemMajorVersion = [[[[[UIDevice currentDevice] systemVersion] componentsSeparatedByString:@"."] objectAtIndex:0] intValue];
    });
    return _deviceSystemMajorVersion;
}
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    // Override point for customization after application launch.
    self.window.backgroundColor = [UIColor whiteColor];
    
    [RealTimeModel SharedRealTimeModel].brealStart = NO;
    [RealTimeModel SharedRealTimeModel].strUseTime = @"0";
    [RealTimeModel SharedRealTimeModel].strBatteryPath = @"ui_home_power_0.png";
    [RealTimeModel SharedRealTimeModel].strPieValue = @"0";
    [RealTimeModel SharedRealTimeModel].strEffect = @"";
    [RealTimeModel SharedRealTimeModel].strAverage = @"0";
    [RealTimeModel SharedRealTimeModel].strMax = @"0";
    [RealTimeModel SharedRealTimeModel].strUnit = @"";
    [RealTimeModel SharedRealTimeModel].strStatus = @"";
    [RealTimeModel SharedRealTimeModel].strName = @"";
    [RealTimeModel SharedRealTimeModel].maryHistoryS = [[NSMutableArray alloc]init];
    [RealTimeModel SharedRealTimeModel].maryHistoryM = [[NSMutableArray alloc]init];
    [RealTimeModel SharedRealTimeModel].strFightShare = @"";
    /**
     注册SDK应用，此应用请到http://www.sharesdk.cn中进行注册申请。
     此方法必须在启动时调用，否则会限制SDK的使用。
     **/
    [ShareSDK registerApp:@"3fec647b0cc4"];
    _interfaceOrientationMask = SSInterfaceOrientationMaskAll;
    
    _viewDelegate = [[AGViewDelegate alloc] init];
    //添加新浪微博应用 注册网址 http://open.weibo.com
    //当使用新浪微博客户端分享的时候需要按照下面的方法来初始化新浪的平台
    [ShareSDK  connectSinaWeiboWithAppKey:@"3046321634"
                                appSecret:@"722509945b65f7a558ccae8a25f2f2ad"
                              redirectUri:@"http://ys.hanwei.cn"
                              weiboSDKCls:[WeiboSDK class]];
    
    //添加腾讯微博应用 注册网址 http://dev.t.qq.com
    [ShareSDK connectTencentWeiboWithAppKey:@"801549039"
                                  appSecret:@"27f77c1a1661e6a4a5ed5debf1128243"
                                redirectUri:@"https://itunes.apple.com/us/app/yi-nuo-dian-zi-bi/id949315830?l=zh&ls=1&mt=8"
                                   wbApiCls:[WeiboApi class]];
    
    //添加QQ空间应用  注册网址  http://connect.qq.com/intro/login/
    [ShareSDK connectQZoneWithAppKey:@"801549302"
                           appSecret:@"2b8b5920a004a6b485185d9cab9cfd22"
                   qqApiInterfaceCls:[QQApiInterface class]
                     tencentOAuthCls:[TencentOAuth class]];
    
    //添加QQ应用  注册网址  http://open.qq.com/
    [ShareSDK connectQQWithQZoneAppKey:@"801549302"
                     qqApiInterfaceCls:[QQApiInterface class]
                       tencentOAuthCls:[TencentOAuth class]];
    
    //添加微信应用 注册网址 http://open.weixin.qq.com
    [ShareSDK connectWeChatWithAppId:@"wx1ac9764b747830c1"
                           wechatCls:[WXApi class]];

    
    
    
    UINavigationController *navigationController = [[UINavigationController alloc] init];
    navigationController.navigationBarHidden = YES;
    LeftMenuViewController *leftmenuView = [[LeftMenuViewController alloc]init];
    RESideMenu *sideMenuViewController = [[RESideMenu alloc] initWithContentViewController:navigationController
                                                                    leftMenuViewController:leftmenuView
                                                                   rightMenuViewController:nil];
    sideMenuViewController.backgroundImage = [UIImage imageNamed:@"ui_background.png"];
    sideMenuViewController.menuPreferredStatusBarStyle = UIStatusBarStyleDefault; // UIStatusBarStyleLightContent
    sideMenuViewController.delegate = self;
    sideMenuViewController.contentViewShadowColor = [UIColor blackColor];
    sideMenuViewController.contentViewShadowOffset = CGSizeMake(0, 0);
    sideMenuViewController.contentViewShadowOpacity = 0.6;
    sideMenuViewController.contentViewShadowRadius = 12;
    sideMenuViewController.contentViewShadowEnabled = YES;
    self.window.rootViewController = sideMenuViewController;
    [self.window makeKeyAndVisible];
    return YES;
}


#pragma mark -
#pragma mark RESideMenu Delegate

- (void)sideMenu:(RESideMenu *)sideMenu willShowMenuViewController:(UIViewController *)menuViewController
{
    NSLog(@"willShowMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu didShowMenuViewController:(UIViewController *)menuViewController
{
    NSLog(@"didShowMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu willHideMenuViewController:(UIViewController *)menuViewController
{
    NSLog(@"willHideMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu didHideMenuViewController:(UIViewController *)menuViewController
{
    NSLog(@"didHideMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later. 
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

- (BOOL)application:(UIApplication *)application
      handleOpenURL:(NSURL *)url
{
    return [ShareSDK handleOpenURL:url
                        wxDelegate:self];
}

- (BOOL)application:(UIApplication *)application
            openURL:(NSURL *)url
  sourceApplication:(NSString *)sourceApplication
         annotation:(id)annotation
{
    return [ShareSDK handleOpenURL:url
                 sourceApplication:sourceApplication
                        annotation:annotation
                        wxDelegate:self];
}
@end
