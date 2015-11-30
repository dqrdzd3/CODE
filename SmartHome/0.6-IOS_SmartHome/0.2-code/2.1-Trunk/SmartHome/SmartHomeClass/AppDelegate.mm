//
//  AppDelegate.m
//  YRSideViewController
//
//  Created by wbiao on 14-5-10.
//  Copyright (c) 2014年 Apple. All rights reserved.
//

#import "AppDelegate.h"
#import "LeftMenuViewController.h"
#import "RightMenuViewController.h"
#import "TransitionViewController.h"
#import "FirstSkin.h"
#define IS_WIDESCREEN ( fabs( ( double )[ [ UIScreen mainScreen ] bounds ].size.height - ( double )568 ) < DBL_EPSILON )
BOOL       newModuleFound;
BOOL       g_bBindDevice;
BOOL       g_bgologin;
NSString  *g_UserDirPath;

@implementation AppDelegate
@synthesize navController;
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
    FirstSkin *firstskin = [[FirstSkin alloc]init];
    NSDictionary *dic = [NSDictionary dictionaryWithObject:@"image2" forKey:@"imageurl"];
    [[NSNotificationCenter defaultCenter]postNotificationName:@"ChangeSkin" object:dic];
    
    
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    // Override point for customization after application launch.
    self.window.backgroundColor = [UIColor whiteColor];
    [self confirmationWasHidden:nil];
    [self onCheckVersion];
    //分享sdk
    {

        [ShareSDK registerApp:@"30fb7c8144a0"];
        //添加新浪微博应用 注册网址 http://open.weibo.com
        //当使用新浪微博客户端分享的时候需要按照下面的方法来初始化新浪的平台
        [ShareSDK connectSinaWeiboWithAppKey:@"4063835458"
                                   appSecret:@"6918d72106fcea0c373bfecfd8f34180"
                                 redirectUri:@"http://weiguo.hanwei.cn/smart/"
                                 weiboSDKCls:[WeiboSDK class]];
        

        //添加腾讯微博应用 注册网址 http://dev.t.qq.com
        [ShareSDK connectTencentWeiboWithAppKey:@"801539135"
                                      appSecret:@"174beb630f39ce51bedf11a9998f8667"
                                    redirectUri:@"http://weiguo.hanwei.cn/smart/"
                                       wbApiCls:[WeiboApi class]];
        
        
        //添加QQ空间应用  注册网址  http://connect.qq.com/intro/login/
        [ShareSDK connectQZoneWithAppKey:@"801539135"
                                appSecret:@"174beb630f39ce51bedf11a9998f8667"
                        qqApiInterfaceCls:[QQApiInterface class]
                            tencentOAuthCls:[TencentOAuth class]];
        

        
        //添加微信应用 注册网址 http://open.weixin.qq.com
        [ShareSDK connectWeChatWithAppId:@"wx55ada1de78e391c9"
                               wechatCls:[WXApi class]];
        
        [ShareSDK ssoEnabled:NO];
    }
    _mapManager = [[BMKMapManager alloc]init];
    // 如果要关注网络及授权验证事件，请设定    generalDelegate参数
    BOOL ret = [_mapManager start:@"RI36Dvkx20tD0Ooa3XXucVj1"  generalDelegate:nil];
    if (!ret) {
        NSLog(@"manager start failed!");
    }
    TransitionViewController *transitionViewController = [[TransitionViewController alloc]init];
    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:transitionViewController];
    navigationController.navigationBarHidden = YES;
    leftmenuView = [[LeftMenuViewController alloc]init];
    rightMenuView = [[RightMenuViewController alloc]init];
    
    sideMenuViewController = [[RESideMenu alloc] initWithContentViewController:navigationController leftMenuViewController:leftmenuView rightMenuViewController:rightMenuView];
    sideMenuViewController.backgroundImage = [UIImage imageNamed:@""];
    sideMenuViewController.menuPreferredStatusBarStyle = UIStatusBarStyleDefault; // UIStatusBarStyleLightContent
    sideMenuViewController.delegate = self;
    sideMenuViewController.contentViewShadowColor = [UIColor grayColor];
    sideMenuViewController.contentViewShadowOffset = CGSizeMake(0, 0);
    sideMenuViewController.contentViewShadowOpacity = 0.6;
    sideMenuViewController.contentViewShadowRadius = 12;
    sideMenuViewController.contentViewShadowEnabled = YES;
    self.window.rootViewController = sideMenuViewController;
    [self.window makeKeyAndVisible];
    
    
    if(![[NSUserDefaults standardUserDefaults] boolForKey:@"firstLaunch"])
    {
        [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"firstLaunch"];
        [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"isSavePassword"];
        NSLog(@"第一次启动");
        GuideViewController *userGuideViewController = [[GuideViewController alloc]init];
        [sideMenuViewController presentViewController:userGuideViewController animated:NO completion:nil];
    }
    else
    {
        NSLog(@"不是第一次启动");
        if(![[NSUserDefaults standardUserDefaults] boolForKey:@"isLoginSuccess"]){
            NSLog(@"ShowLoginView");
            [self ShowLoginView];
        }
        else{
            NSLog(@"LoadFirstView");
            [leftmenuView LoadFirstView];
        }
    }
    return YES;
}

-(void)ShowLoginView{
    [leftmenuView RemoveSubViewController];
    [rightMenuView RemoveSubViewController];
    
    [[NSUserDefaults standardUserDefaults] setBool:NO forKey:@"isLoginSuccess"];
    LoginViewController *ca = [[LoginViewController alloc]init];
    ca.delegate = self;
    [sideMenuViewController presentViewController:ca animated:NO completion:nil];
}
-(void)LoginViewControllerFinished:(BOOL)bsuc{
    if (bsuc) {
        [leftmenuView LoadFirstView];
    }
}

#pragma mark -
#pragma mark RESideMenu Delegate

- (void)sideMenu:(RESideMenu *)sideMenu willShowMenuViewController:(UIViewController *)menuViewController
{
     //NSLog(@"willShowMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu didShowMenuViewController:(UIViewController *)menuViewController
{
    //NSLog(@"didShowMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu willHideMenuViewController:(UIViewController *)menuViewController
{
    //NSLog(@"willHideMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

- (void)sideMenu:(RESideMenu *)sideMenu didHideMenuViewController:(UIViewController *)menuViewController
{
    //NSLog(@"didHideMenuViewController: %@", NSStringFromClass([menuViewController class]));
}

//调用psh，请求获取动态令牌
- (void) confirmationWasHidden:(NSNotification *) notification
{
    // IOS8 新系统需要使用新的代码咯
    if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0)
    {
        [[UIApplication sharedApplication] registerUserNotificationSettings:[UIUserNotificationSettings settingsForTypes:(UIUserNotificationTypeSound  | UIUserNotificationTypeBadge)categories:nil]];
        [[UIApplication sharedApplication] registerForRemoteNotifications];
    }
    else
    {
        //这里还是原来的代码
        [[UIApplication sharedApplication] registerForRemoteNotificationTypes:
         (UIUserNotificationTypeBadge | UIUserNotificationTypeSound)];
    }
}

//得到令牌
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
    //得到令牌
    NSString *strToken = [[[[deviceToken description]
                            stringByReplacingOccurrencesOfString:@"<" withString:@""]
                           stringByReplacingOccurrencesOfString:@">" withString:@""]
                          stringByReplacingOccurrencesOfString:@" " withString:@""];
    [[NSUserDefaults standardUserDefaults] setObject:strToken
                                              forKey:@"DEVICETOKEN"];
    NSLog(@"strToken:%@",strToken);
}
- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
    //push错误
    NSLog(@"Error in registration. Error:\n %@", error);
}


//网络推送通知栏打开时会调用
- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo
{
    //收到的push消息，userInfo：push里服务器传递的相关信息，这个信息是由公司服务器自己设定的。可以在这里处理一些逻辑
    NSLog(@"didReceiveRemoteNotification:%@",[userInfo description]);
}

//本地推送通知栏打开时会调用
-( void )application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification{
    NSLog(@"didReceiveRemoteNotification:%@",[notification.userInfo objectForKey:@"key"]);
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
- (BOOL)Version:(NSString*)versionA biggerThanVersion:(NSString*)versionB
{
    NSMutableString *mstringA = [NSMutableString stringWithString:versionA];
    NSMutableString *mstringB = [NSMutableString stringWithString:versionB];
    NSInteger fnow = [[mstringB stringByReplacingOccurrencesOfString:@"." withString:@""] integerValue];
    NSInteger fnew = [[mstringA stringByReplacingOccurrencesOfString:@"." withString:@""] integerValue];
    if (fnow < fnew) {
        return YES;
    }
    else{
        return NO;
    }
}
-(void)onCheckVersion
{
    NSURL *url = [NSURL URLWithString:@"http://itunes.apple.com/cn/lookup"];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    request.requestMethod = @"POST";
    [request setPostValue:APP_ID forKey:@"id"];
    [request setDelegate:self];
    [request startAsynchronous];
}
//网络部分
- (void)requestFinished:(ASIHTTPRequest *)request
{
    NSString *responseString = [request responseString];
    NSDictionary *result = [responseString  objectFromJSONString];
    
    NSArray *infoArray = [result objectForKey:@"results"];
    if ([infoArray count]) {
        NSDictionary *releaseInfo = [infoArray objectAtIndex:0];
        NSString *lastVersion = [releaseInfo objectForKey:@"version"];
        
        if ([self Version:lastVersion biggerThanVersion:[[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"]]) {
            trackViewURL = [releaseInfo objectForKey:@"trackViewUrl"];
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"有新的版本，是否前往更新？" delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:@"更新", nil];
            alert.tag = 10000;
            [alert show];
        }
    }
    else{
//        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"获取不到版本信息" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
//        alert.tag = 10002;
//        [alert show];
    }
}
- (void)requestFailed:(ASIHTTPRequest *)request
{
    NSError *error = [request error];
    if (error) {
        NSLog(@"onCheckVersion:%@",error.description);
    }
}


- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (alertView.tag == 10000) {
        if (buttonIndex == 1) {
            NSURL *url = [NSURL URLWithString:[trackViewURL stringByReplacingOccurrencesOfString:@"https" withString:@"itms-apps"]];
            [[UIApplication sharedApplication]openURL:url];
        }
    }
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
