//
//  FirstViewController.m
//  RESideMenuStoryboards
//
//  Created by Roman Efimov on 10/9/13.
//  Copyright (c) 2013 Roman Efimov. All rights reserved.
//

#import "FirstViewController.h"
#import "SensorDetail.h"
#import "DeviceDataConstant.h"
#import <QuartzCore/QuartzCore.h>
#import "AlarmAlertView.h"
extern BOOL        g_bBindDevice;
extern NSString    *g_UserDirPath;

#define PATH_OF_DOCUMENT  [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0]
@implementation FirstViewController
-(void)ReloadData{
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    //读取NSString类型的数据
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId != nil) {
        NSString * folerName = userId;
        if ([[NSFileManager defaultManager] fileExistsAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:folerName]]) {
            g_UserDirPath = [PATH_OF_DOCUMENT stringByAppendingPathComponent:folerName];
        }
        else{
            [[NSFileManager defaultManager] createDirectoryAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:folerName] withIntermediateDirectories:YES attributes:nil error:nil];
            g_UserDirPath = [PATH_OF_DOCUMENT stringByAppendingPathComponent:folerName];
        }
    }
    [self LoadDataCache];
}
-(void)StopTimer{
    [timer invalidate];
    if (mdicContentViews.count != 0) {
        NSArray *firstContents = [mdicContentViews allValues];
        for (FirstContentView *firstContent in firstContents) {
            [firstContent StopAlarmTimer];
        }
        [mdicContentViews removeAllObjects];
    }
}
- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.view.backgroundColor = [UIColor whiteColor];
    mdicContentViews = [[NSMutableDictionary alloc]init];
    self.automaticallyAdjustsScrollViewInsets = NO;
    _scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, FULLSCREENHEIGHT)];
    _scrollView.contentSize = CGSizeMake(SCREENWIDTH*3 , FULLSCREENHEIGHT-TopBarHeight);
    _scrollView.backgroundColor = [UIColor whiteColor];
    [_scrollView setBounces:NO];
    _scrollView.pagingEnabled = YES;
    _scrollView.showsHorizontalScrollIndicator = NO;
    _scrollView.showsVerticalScrollIndicator = NO;
    [self.view addSubview:_scrollView];
    
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    [self.view addSubview:viewTop];
    
    btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5,25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    
    btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33,25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];

//    CATransform3D transform = CATransform3DIdentity;
//    transform.m34 = 1.0 / 1200;
//    //在X轴上做一个20度的小旋转
//    transform = CATransform3DRotate(transform, M_PI / 4, 1, 0, 0);
//    self.view.layer.transform = transform;

}

-(void)ClickLeftMenu{
    [self presentLeftMenuViewController:nil];
}
-(void)ClickRightMenu{
    [self presentRightMenuViewController:nil];
}
-(void)LoadDataCache{
    if ([[NSFileManager defaultManager]fileExistsAtPath:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"]]) {
        NSArray *list = [NSArray arrayWithContentsOfFile:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"]];
        _scrollView.contentSize = CGSizeMake(SCREENWIDTH*list.count , FULLSCREENHEIGHT-TopBarHeight);
        
//        if (mdicContentViews.count != 0) {
//            [mdicContentViews removeAllObjects];
//        }
        for (int i = 0; i < list.count; i++) {
            NSDictionary *dicTemp      = [list objectAtIndex:i];
            NSDictionary *dicAir       = [dicTemp objectForKey:@"air"];
            NSDictionary *dicGas       = [dicTemp objectForKey:@"gas"];
            SensorDetail *sensorDetail = [[SensorDetail alloc]init];
            AirDevice *airDevice       = [[AirDevice alloc]initWithDictionary:dicAir];
            sensorDetail.airDevice     = airDevice;
            GasDevice *gasDevice       = [[GasDevice alloc]initWithDictionary:dicGas];
            sensorDetail.gasDevice     = gasDevice;
            
            sensorDetail.sensorId      = [dicTemp objectForKey:@"sensorId"];
            sensorDetail.name          = [dicTemp objectForKey:@"name"];
            sensorDetail.online        = [dicTemp objectForKey:@"online"];
            
            CartoonInfo *cartoonInfo   = [sensorDetail getCartoonInfo];
            if ([mdicContentViews objectForKey:[dicTemp objectForKey:@"sensorId"]] == nil) {
                FirstContentView *firstContent = [[FirstContentView alloc]initWithFrame:CGRectMake(SCREENWIDTH*i, 0, SCREENWIDTH, 2*FULLSCREENHEIGHT) alldata:dicTemp AirDevice:airDevice GasDevice:gasDevice];
                firstContent.delegate = self;
                [firstContent LoadTopView];
                [firstContent LoadTopViewData:cartoonInfo];
                if (airDevice != nil) {
                    [firstContent LoadAirInfoView];
                    [firstContent LoadAirInfoData];
                }
                if (gasDevice != nil) {
                    [firstContent LoadGasInfoView];
                    [firstContent LoadGasInfoData];
                }
                [_scrollView addSubview:firstContent];
                [mdicContentViews setObject:firstContent forKey:[dicTemp objectForKey:@"sensorId"]];
                if (i == list.count-1) {
                    [firstContent hideRightArrow];
                }
                if (i == 0){
                    [firstContent hideLeftArrow];
                }
            }
            else{
                FirstContentView *firstContent = [mdicContentViews objectForKey:[dicTemp objectForKey:@"sensorId"]];
                [firstContent LoadTopViewData:cartoonInfo];
                if (airDevice != nil) {
                    [firstContent LoadAirInfoData];
                }
                else if (airDevice != nil){
                    [firstContent LoadGasInfoData];
                }
                if (i == list.count-1) {
                    [firstContent hideRightArrow];
                }
                if (i == 0){
                    [firstContent hideLeftArrow];
                }
            }
        }
    }
    else{
        for (UIView *view in _scrollView.subviews) {
            [view removeFromSuperview];
        }
        if (mdicContentViews.count != 0) {
            [mdicContentViews removeAllObjects];
        }
    }
    [timer invalidate];
    timer = [NSTimer scheduledTimerWithTimeInterval:2.0 target:self selector:@selector(TimeOver) userInfo:nil repeats:YES];
}

-(void)initData
{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_02_02_01]];
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
        NSString *responseString = [request responseString];
        NSDictionary *result     = [responseString  objectFromJSONString];
        NSArray *dataObject      = [result objectForKey:@"dataObject"];
        
        if ([result objectForKey:@"dataObject"] != [NSNull null] && dataObject.count != 0) {
            NSDictionary *dic        = [dataObject objectAtIndex:0];
            NSArray *list            = [dic objectForKey:@"sensorList"];
            if (list.count == 0) {
                g_bBindDevice = NO;
                [[NSNotificationCenter defaultCenter]postNotificationName:@"NeedAddSensor" object:nil];
                [timer invalidate];
                return ;
            }
            else{
                g_bBindDevice = YES;
                if (list.count != 0) {
                    [list writeToFile:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"] atomically:YES];
                }
                _scrollView.contentSize = CGSizeMake(SCREENWIDTH*list.count , FULLSCREENHEIGHT-TopBarHeight);
                
                //筛选出已经删除掉的设备从设备集合中删除
                NSArray *aryAllKeys = [mdicContentViews allKeys];
                for (NSString *key in aryAllKeys) {
                    NSMutableArray *arySensorid = [[NSMutableArray alloc]init];
                    for (int i = 0; i < list.count; i++) {
                        NSDictionary *dicTemp = [list objectAtIndex:i];
                        [arySensorid addObject:[dicTemp objectForKey:@"sensorId"]];
                    }
                    if ([arySensorid indexOfObject:key] == NSNotFound) {
                        FirstContentView *firstContent = [mdicContentViews objectForKey:key];
                        [firstContent StopAlarmTimer];
                        [firstContent removeFromSuperview];
                        [mdicContentViews removeObjectForKey:key];
                    }
                }
                
                for (int i = 0; i < list.count; i++) {
                    NSDictionary *dicTemp      = [list objectAtIndex:i];
                    NSDictionary *dicAir       = [dicTemp objectForKey:@"air"];
                    NSDictionary *dicGas       = [dicTemp objectForKey:@"gas"];
                    
                    SensorDetail *sensorDetail = [[SensorDetail alloc]initWithDictionary:dicTemp];
                    AirDevice *airDevice       = [[AirDevice alloc]initWithDictionary:dicAir];
                    sensorDetail.airDevice     = airDevice;
                    GasDevice *gasDevice       = [[GasDevice alloc]initWithDictionary:dicGas];
                    sensorDetail.gasDevice     = gasDevice;
                    
                    CartoonInfo *cartoonInfo   = [sensorDetail getCartoonInfo];
      
                    if ([mdicContentViews objectForKey:[dicTemp objectForKey:@"sensorId"]] == nil) {
                        FirstContentView *firstContent = [[FirstContentView alloc]initWithFrame:CGRectMake(SCREENWIDTH*i, 0, SCREENWIDTH, 2*FULLSCREENHEIGHT) alldata:dicTemp AirDevice:airDevice GasDevice:gasDevice];
                        firstContent.dicSensorDetail = dicTemp;
                        firstContent.delegate = self;
                        firstContent.strlocalSoftVersion = [dicTemp objectForKey:@"localSoftVersion"];
                        firstContent.strremoteSoftVersion = [dicTemp objectForKey:@"remoteSoftVersion"];
                        [firstContent LoadTopView];
                        [firstContent LoadTopViewData:cartoonInfo];
                        if (airDevice != nil) {
                            [firstContent LoadAirInfoView];
                            [firstContent LoadAirInfoData];
                        }
                        if (gasDevice != nil) {
                            [firstContent LoadGasInfoView];
                            [firstContent LoadGasInfoData];
                        }
                        [_scrollView addSubview:firstContent];
                        [mdicContentViews setObject:firstContent forKey:[dicTemp objectForKey:@"sensorId"]];
                        if (i == list.count-1) {
                            [firstContent hideRightArrow];
                        }
                        if (i == 0){
                            [firstContent hideLeftArrow];
                        }
                    }
                    else{
                        FirstContentView *firstContent = [mdicContentViews objectForKey:[dicTemp objectForKey:@"sensorId"]];
                        firstContent.dicSensorDetail = dicTemp;
                        firstContent.strlocalSoftVersion = [dicTemp objectForKey:@"localSoftVersion"];
                        firstContent.strremoteSoftVersion = [dicTemp objectForKey:@"remoteSoftVersion"];
                        CGRect frame = firstContent.frame;
                        frame.origin.x = SCREENWIDTH*i;
                        firstContent.frame = frame;
                        
                        [firstContent initData:dicTemp AirDevice:airDevice GasDevice:gasDevice];
                        [firstContent LoadTopViewData:cartoonInfo];
                        if (airDevice != nil) {
                            [firstContent LoadAirInfoData];
                        }
                        else if (airDevice != nil){
                            [firstContent LoadGasInfoData];
                        }
                        if (i == list.count-1) {
                            [firstContent hideRightArrow];
                        }
                        if (i == 0){
                            [firstContent hideLeftArrow];
                        }
                    }
                    
                    
                }
            }
        }
    }];
    [request setFailedBlock:^{
        NSError *error = [request error];
        NSLog(@"FirstViewError:%@",error.description);
    }];
    [request startAsynchronous];
}


-(void)FirstContentViewUpdataHardWare:(FirstContentView *)firstContentView sensorid:(NSString *)sensorid{
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
    [request setPostValue:sensorid forKey:@"DRIVERID"];
    [request setCompletionBlock:^{
        NSString *responseString = [request responseString];
    }];
    [request setFailedBlock:^{
        NSError *error = [request error];
        NSLog(@"BeginUpdateSoftVersionError:%@",error.description);
    }];
    [request startAsynchronous];
}
#pragma mark======FirstContentViewDelegate
-(void)FirstContentViewRank:(FirstContentView *)firstContentView data:(NSDictionary *)dicdata{
    NSDictionary *dicAir = [dicdata objectForKey:@"air"];
    NSDictionary *dicGas = [dicdata objectForKey:@"gas"];
    SensorDetail *sensorDetail = [[SensorDetail alloc]init];
    NSString *strgastype = @"";
    NSString *strGasstate = @"";
    if (dicAir.count != 0) {
        AirDevice *airDevice = [[AirDevice alloc]initWithDictionary:dicAir];
        sensorDetail.airDevice = airDevice;
        sensorDetail.sensorId = [dicAir objectForKey:@"sensorId"];
        sensorDetail.name = [dicAir objectForKey:@"name"];
        sensorDetail.online = [dicAir objectForKey:@"online"];
        strgastype = @"[0,1,2,3,4]";
        CartoonInfo *cartoonInfo = [sensorDetail getCartoonInfo];
        strGasstate = [NSString stringWithFormat:@"[%@,%@,%@,%@,%@]",[cartoonInfo.CartoonState objectAtIndex:0],[cartoonInfo.CartoonState objectAtIndex:1],[cartoonInfo.CartoonState objectAtIndex:2],[cartoonInfo.CartoonState objectAtIndex:3],[cartoonInfo.CartoonState objectAtIndex:4]];
    }
    else if (dicGas.count != 0){
        GasDevice *gasDevice = [[GasDevice alloc]initWithDictionary:dicGas];
        sensorDetail.gasDevice = gasDevice;
        sensorDetail.sensorId = [dicGas objectForKey:@"sensorId"];
        sensorDetail.name = [dicGas objectForKey:@"name"];
        sensorDetail.online = [dicGas objectForKey:@"online"];
        strgastype = @"[9,8]";
        CartoonInfo *cartoonInfo = [sensorDetail getCartoonInfo];
        strGasstate = [NSString stringWithFormat:@"[%@,%@]",[cartoonInfo.CartoonState objectAtIndex:0],[cartoonInfo.CartoonState objectAtIndex:1]];
    }
    
    RankAdviceViewController *rankAdviceViewC = [[RankAdviceViewController alloc]init];
    rankAdviceViewC.strGastype = strgastype;
    rankAdviceViewC.strGasstate = strGasstate;
    [self.navigationController pushViewController:rankAdviceViewC animated:YES];
}

-(void)FirstContentViewSendAlarm:(FirstContentView *)firstContentView data:(NSDictionary *)dicdata sensorName:(NSString *)sensorName{
    NSLog(@"SendAlarm:%@",dicdata);
    NSString *strunit = @"";
    NSString *strName = @"";
    NSString *strTime = @"";
    NSString *strSensorid = @"";
    NSString *strValue = @"";
    strName = [dicdata objectForKey:@"ma004"];
    if ([[dicdata objectForKey:@"ma004"] isEqualToString:@"甲烷"] || [[dicdata objectForKey:@"ma004"] isEqualToString:HOME_NAME_CH4]) {
        strunit = [NSString stringWithFormat:@"%%%@",HOME_UNIT_CH4];
    }
    else if ([[dicdata objectForKey:@"ma004"] isEqualToString:@"煤气"] || [[dicdata objectForKey:@"ma004"] isEqualToString:HOME_NAME_CO]){
        strunit = HOME_UNIT_CO;
    }
    else{
        strName = @"未知";
        strunit = @"未知";
    }
    strTime = [dicdata objectForKey:@"ma005"];
    strSensorid = [dicdata objectForKey:@"ma006"];
    strValue = [dicdata objectForKey:@"ma002"];
    
    NSString *strTitle = [NSString stringWithFormat:@"%@报警,浓度值为:%@%@",strName,strValue,strunit];
    NSString *strMessage = [NSString stringWithFormat:@"报警时间:%@\n设备名:%@\n设备ID:%@",strTime,sensorName,strSensorid];
    
    //本地推送通知
//    UILocalNotification *notification=[[UILocalNotification alloc] init];
//    if (notification!=nil) {
//        NSDate *now = [NSDate date];
//        //从现在开始，10秒以后通知
//        notification.fireDate=[now dateByAddingTimeInterval:1];
//        //使用本地时区
//        notification.timeZone=[NSTimeZone defaultTimeZone];
//        notification.alertBody = [NSString stringWithFormat:@"%@\n%@",strTitle,strMessage];
//        //通知提示音 使用默认的
//        notification.soundName= UILocalNotificationDefaultSoundName;
//        [[UIApplication sharedApplication]   scheduleLocalNotification:notification];
//    }
    
    if (dicAlert == nil) {
        dicAlert = [[NSMutableDictionary alloc]init];
    }
    AlarmAlertView *alarmAlertOld = [dicAlert objectForKey:[dicdata objectForKey:@"ma006"]];
    if (alarmAlertOld != nil) {
        [alarmAlertOld removeFromSuperview];
        [dicAlert removeObjectForKey:[dicdata objectForKey:@"ma006"]];
    }
    
    AlarmAlertView *alarmAlert = [[AlarmAlertView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, FULLSCREENHEIGHT) title:strTitle message:strMessage sensorid:[dicdata objectForKey:@"ma006"]];
    [self.view addSubview:alarmAlert];
    [dicAlert setObject:alarmAlert forKey:[dicdata objectForKey:@"ma006"]];
    
}
-(void)TimeOver{
    if (bWork) {
        [self initData];
    }
}
-(void)viewWillAppear:(BOOL)animated{
    bWork = YES;
}
-(void)viewWillDisappear:(BOOL)animated{
    bWork = NO;
}
-(void)viewDidDisappear:(BOOL)animated{

}
@end
