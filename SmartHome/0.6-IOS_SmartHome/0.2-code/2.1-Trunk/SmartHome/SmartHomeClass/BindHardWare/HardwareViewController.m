//
//  HardwareViewController.m
//  SmartHome
//  设备配网
//  Created by 李静 on 14-11-24.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "HardwareViewController.h"
#import "PhoneBindDeviceController.h"
#import "DeviceUtil.h"
#import "SensorDetail.h"
#import "DescriptionConfigViewController.h"
extern BOOL newModuleFound;
extern BOOL g_bBindDevice;
BOOL configSuccess;
int configTimeMin = 2;


@interface HardwareViewController ()
/*
 Notification method handler wifi 状态改变 @param the fired notification object
 */
- (void)wifiStatusChanged:(NSNotification*)notification;


/* enableUIAccess
 * enable / disable the UI access like enable / disable the textfields
 * and other component while transmitting the packets.
 * @param: vbool is to validate the controls.
 */
-(void) enableUIAccess:(BOOL) isEnable;
@property (nonatomic, retain, readwrite) NSThread* waitForAckThread;
@end

@implementation HardwareViewController
@synthesize waitForAckThread;
@synthesize deviceId;
@synthesize lastBtn;
@synthesize finishBtn;
@synthesize scrollView;
@synthesize desImage;
@synthesize wifiName;
@synthesize wifiPassword;
@synthesize bindBtn;
@synthesize configLabel;

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor colorWithRed:235/255.0 green:230/255.0 blue:226/255.0 alpha:1.0];
    // Do any additional setup after loading the view.
    
    [self initView];
}
-(void)viewDidDisappear:(BOOL)animated{
    [self stopAction];
    [configTimer invalidate];
    [timer invalidate];
}
- (void)initView{
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(60, STATUSBARHEIGHT, SCREENWIDTH-120, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = @"绑定设备";
    [viewTop addSubview:labelTitle];
    
    lastBtn = [[UIButton alloc]initWithFrame:CGRectMake(5, 24.5, 60, 35)];
    [lastBtn setTitle:@"上一步" forState:UIControlStateNormal];
    [lastBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    lastBtn.backgroundColor = [UIColor colorWithRed:217/255.0 green:83/255.0 blue:79/255.0 alpha:1];
    lastBtn.layer.masksToBounds = YES;
    lastBtn.layer.cornerRadius = 3.0;
    [lastBtn addTarget:self action:@selector(lastBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:lastBtn];
    
    finishBtn = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-65,24.5, 60, 35)];
    [finishBtn setTitle:@"下一步" forState:UIControlStateNormal];
    [finishBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    finishBtn.backgroundColor = [UIColor colorWithRed:92/255.0 green:184/255.0 blue:92/255.0 alpha:1];
    finishBtn.layer.masksToBounds = YES;
    finishBtn.layer.cornerRadius = 3.0;
    [finishBtn addTarget:self action:@selector(finishBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:finishBtn];
    
    
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight)];
    scrollView.contentSize = CGSizeMake([UIScreen mainScreen].bounds.size.width, FULLSCREENHEIGHT);
    [self.view addSubview:scrollView];
    
    UIView *viewDis = [[UIView alloc]initWithFrame:CGRectMake(10, 10, SCREENWIDTH-20, 230)];
    viewDis.backgroundColor = [UIColor whiteColor];
    [scrollView addSubview:viewDis];
    
    desImage = [[UIImageView alloc]initWithFrame:CGRectMake(viewDis.frame.size.width/2-49, 10, 98, 87)];
    [viewDis addSubview:desImage];
    
    configLabel = [[RTLabel alloc]initWithFrame:CGRectMake(0, desImage.frame.origin.y+desImage.frame.size.height+30, viewDis.frame.size.width, viewDis.frame.size.height-desImage.frame.origin.y-desImage.frame.size.height-30)];
    [viewDis addSubview:configLabel];
    
    
    UILabel *labeltmp = [[UILabel alloc]initWithFrame:CGRectMake(viewDis.frame.origin.x, viewDis.frame.origin.y+viewDis.frame.size.height, 100, 20)];
    labeltmp.backgroundColor = [UIColor clearColor];
    labeltmp.font = [UIFont systemFontOfSize:15.0];
    labeltmp.textColor = [UIColor lightGrayColor];
    labeltmp.text = @"手机绑定设备";
    [scrollView addSubview:labeltmp];
    
    UIView *viewWifi = [[UIView alloc]initWithFrame:CGRectMake(labeltmp.frame.origin.x, labeltmp.frame.origin.y+labeltmp.frame.size.height+5, viewDis.frame.size.width, 100)];
    viewWifi.backgroundColor = [UIColor whiteColor];
    [scrollView addSubview:viewWifi];
    
    UILabel *labelWifiName = [[UILabel alloc]initWithFrame:CGRectMake(5, 5, 70, 40)];
    labelWifiName.backgroundColor = [UIColor clearColor];
    labelWifiName.font = [UIFont systemFontOfSize:15.0];
    labelWifiName.textColor = [UIColor blackColor];
    labelWifiName.text = @"WiFi-名称";
    [viewWifi addSubview:labelWifiName];
    
    
    wifiName = [[UILabel alloc]initWithFrame:CGRectMake(labelWifiName.frame.origin.x+labelWifiName.frame.size.width+5, 5, viewWifi.frame.size.width-labelWifiName.frame.origin.x-labelWifiName.frame.size.width-10, 40)];
    wifiName.backgroundColor = [UIColor clearColor];
    wifiName.font = [UIFont systemFontOfSize:14.0];
    wifiName.textColor = [UIColor blackColor];
    wifiName.text = @"";
    [viewWifi addSubview:wifiName];
    
    
    UILabel *labelWifiPsd = [[UILabel alloc]initWithFrame:CGRectMake(5, labelWifiName.frame.origin.y+labelWifiName.frame.size.height+10, 70, 40)];
    labelWifiPsd.backgroundColor = [UIColor clearColor];
    labelWifiPsd.font = [UIFont systemFontOfSize:15.0];
    labelWifiPsd.textColor = [UIColor blackColor];
    labelWifiPsd.text = @"WiFi-密码";
    [viewWifi addSubview:labelWifiPsd];
    
    wifiPassword = [[UITextField alloc]initWithFrame:CGRectMake(labelWifiPsd.frame.origin.x+labelWifiPsd.frame.size.width+5, labelWifiPsd.frame.origin.y, viewWifi.frame.size.width-labelWifiPsd.frame.origin.x-labelWifiPsd.frame.size.width-10, 40)];
    wifiPassword.font = [UIFont systemFontOfSize:15.0];
    wifiPassword.returnKeyType = UIReturnKeyDone;
    wifiPassword.placeholder = @"输入WiFi密码";
    [viewWifi addSubview:wifiPassword];
    
    
    bindBtn = [[UIButton alloc]initWithFrame:CGRectMake(viewWifi.frame.origin.x, viewWifi.frame.origin.y+viewWifi.frame.size.height+10,SCREENWIDTH-2*viewWifi.frame.origin.x,40)];
    bindBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    bindBtn.layer.masksToBounds = YES;
    bindBtn.layer.cornerRadius = 5.0;
    [bindBtn setTitle:@"设备配网" forState:UIControlStateNormal];
    [bindBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bindBtn addTarget:self action:@selector(bindBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [scrollView addSubview:bindBtn];
    
  
    [finishBtn setHidden:YES];
    [wifiPassword addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [wifiPassword addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    [self initEasylink];
    
    [wifiPassword setSecureTextEntry:YES];
    if (deviceId!=nil&&deviceId.length) {
        int deviceType = [DeviceUtil getDeviceType:self.deviceId];
        if (deviceType == SENSOR_TYPE_GAS) {
            [desImage setImage:[UIImage imageNamed:@"ui_sensor_wifi_show_gas.png"]];
            [configLabel setText:SENSOR_WIFI_GAS_HINT];
        }
        else if (deviceType == SENSOR_TYPE_AIR){
            [desImage setImage:[UIImage imageNamed:@"ui_sensor_wifi_show_air.png"]];
            [configLabel setText:SENSOR_WIFI_AIR_HINT];
        }
    }
    configSuccess = NO;
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
    [self.view bringSubviewToFront:viewTop];
}

-(void)textViewEditBegin{
    [scrollView setContentOffset:CGPointMake(0, 200) animated:YES];
}
-(void)textViewEditDone{
    [wifiPassword resignFirstResponder];
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
}
-(void)initEasylink{
    if( easylink_config == nil){
        easylink_config = [[EASYLINK alloc]init];
        [easylink_config startFTCServerWithDelegate:self];
    }
    deviceIPConfig = [[NSMutableDictionary alloc] initWithCapacity:5];
    // wifi notification when changed.
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(wifiStatusChanged:) name:kReachabilityChangedNotification object:nil];
    
    wifiReachability = [Reachability reachabilityForLocalWiFi];  //监测Wi-Fi连接状态
    [wifiReachability startNotifier];
    
    waitForAckThread = nil;
    
    
    NetworkStatus netStatus = [wifiReachability currentReachabilityStatus];
    if ( netStatus == NotReachable ) {// No activity if no wifi
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Alert" message:@"WiFi 不可用.请检查wifi是否连接" delegate:Nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alertView show];
    }
    else{
        [deviceIPConfig setObject:@YES forKey:@"DHCP"];
        [deviceIPConfig setObject:[EASYLINK getIPAddress] forKey:@"IP"];
        [deviceIPConfig setObject:[EASYLINK getNetMask] forKey:@"NetMask"];
        [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"GateWay"];
        [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"DnsServer"];
        [wifiName setText:[EASYLINK ssidForConnectedNetwork]];
    }
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/
- (IBAction)lastBtnClick:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)bindBtnClick:(id)sender {
    [wifiPassword resignFirstResponder];
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    //[SVProgressHUD showWithStatus:@"配网中" maskType:SVProgressHUDMaskTypeBlack];
    
    [self timerStart];
    [self configTimerStart];
}

#pragma mark - Private Methods -

/* enableUIAccess
 * enable / disable the UI access like enable / disable the textfields
 * and other component while transmitting the packets.
 * @param: vbool is to validate the controls.
 */
-(void)enableUIAccess:(BOOL) isEnable{
    //             ssidField.userInteractionEnabled = isEnable;
    //             passwordField.userInteractionEnabled = isEnable;
    
    //             ipAddress.userInteractionEnabled = isEnable;
    
    //[halo startAnimation: !isEnable];
//    _wifiPassword.userInteractionEnabled = isEnable;
//    _bindBtn.userInteractionEnabled = isEnable;
}

/*
 Notification method handler when status of wifi changes
 @param the fired notification object
 */
- (void)wifiStatusChanged:(NSNotification*)notification{
    NSLog(@"%s", __func__);
    Reachability *verifyConnection = [notification object];
    NSAssert(verifyConnection != NULL, @"currentNetworkStatus called with NULL verifyConnection Object");
    NetworkStatus netStatus = [verifyConnection currentReachabilityStatus];
    if ( netStatus == NotReachable ){

    }
    else {
        [wifiName setText:[EASYLINK ssidForConnectedNetwork]];
        [deviceIPConfig setObject:@YES forKey:@"DHCP"];
        [deviceIPConfig setObject:[EASYLINK getIPAddress] forKey:@"IP"];
        [deviceIPConfig setObject:[EASYLINK getNetMask] forKey:@"NetMask"];
        [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"GateWay"];
        [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"DnsServer"];
    }
}
#pragma mark - TRASMITTING DATA -

/*
 This method begins configuration transmit
 In case of a failure the method throws an OSFailureException.
 */
-(void) sendAction{
    newModuleFound = NO;
    [easylink_config transmitSettings];
}

/*
 This method stop the sending of the configuration to the remote device
 In case of a failure the method throws an OSFailureException.
 */
-(void) stopAction{
    [easylink_config stopTransmitting];
    if (waitForAckThread !=nil) {
         [waitForAckThread cancel];
    }
   
    waitForAckThread= nil;
}

/*
 This method waits for an acknowledge from the remote device than it stops the transmit to the remote device and returns with data it got from the remote device.
 This method blocks until it gets respond.
 The method will return true if it got the ack from the remote device or false if it got aborted by a call to stopTransmitting.
 In case of a failure the method throws an OSFailureException.
 */

- (void) waitForAck: (id)sender{
    while(![[NSThread currentThread] isCancelled])
    {
//        if ( newModuleFound==YES ){
//            [self stopAction];
//            [self enableUIAccess:YES];
//            [self.navigationController popToRootViewControllerAnimated:YES];
//            break;
//        }
        sleep(0.8);
    };

}

/*
 This method start the transmitting the data to connected
 AP. Nerwork validation is also done here. All exceptions from
 library is handled.
 */
- (void)startTransmitting: (int)version {
    NSArray *wlanConfigArray;
    
    NetworkStatus netStatus = [wifiReachability currentReachabilityStatus];
    if ( netStatus == NotReachable ){// No activity if no wifi
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"WiFi 不可用.请检查wifi是否连接" delegate:Nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    NSString *ssid = [wifiName.text length] ? wifiName.text : nil;
    NSString *passwordKey = [wifiPassword.text length] ? wifiPassword.text : @"";
    
    NSNumber *dhcp = [NSNumber numberWithBool:[[deviceIPConfig objectForKey:@"DHCP"] boolValue]];
    NSString *ipString = [[deviceIPConfig objectForKey:@"IP"] length] ? [deviceIPConfig objectForKey:@"IP"] : @"";
    NSString *netmaskString = [[deviceIPConfig objectForKey:@"NetMask"] length] ? [deviceIPConfig objectForKey:@"NetMask"] : @"";
    NSString *gatewayString = [[deviceIPConfig objectForKey:@"GateWay"] length] ? [deviceIPConfig objectForKey:@"GateWay"] : @"";
    NSString *dnsString = [[deviceIPConfig objectForKey:@"DnsServer"] length] ? [deviceIPConfig objectForKey:@"DnsServer"] : @"";
    if([[deviceIPConfig objectForKey:@"DHCP"] boolValue] == YES) ipString = @"";
    
    wlanConfigArray = [NSArray arrayWithObjects: ssid, passwordKey, dhcp, ipString, netmaskString, gatewayString, dnsString, nil];
    
    [easylink_config prepareEasyLink_withFTC:wlanConfigArray info:nil version:version];
    [self sendAction];
    [self enableUIAccess:NO];
}

//触摸其他区域键盘消失
-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    [self.view endEditing:YES];
}

-(void)EndConfigWifi {
    NSLog(@"EndConfigWifi");
    [self stopAction];
    [configTimer invalidate];
    [timer invalidate];
    [HUD hide:YES];
    if(configSuccess){
        MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
        HUD2.mode = MBProgressHUDModeText;
        HUD2.labelText = @"配网成功";
        HUD2.margin = 10.f;
        HUD2.yOffset = 0.f;
        HUD2.removeFromSuperViewOnHide = YES;
        [HUD2 hide:YES afterDelay:1.0];
        [desImage setImage:[UIImage imageNamed:@"icon_peiwangsuc.png"]];
        [configLabel setText:SENSOR_WIFI_GAS_SUCCESS_HINT];
        
        [finishBtn setHidden:NO];
    }
    else{
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"配网失败" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
    }

}

-(void)TimeOver{
    [self checkDeviceData];
}

-(void)checkDeviceData{
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
        NSDictionary *result = [responseString  objectFromJSONString];
        NSArray *dataObject = [result objectForKey:@"dataObject"];
        NSDictionary *arr = dataObject[0];
        NSArray *list = [arr objectForKey:@"sensorList"];
        if (list.count == 0) {
            configSuccess = false;
            [self configTimeOver];
        }
        
        for (int i = 0; i < list.count; i++) {
            NSDictionary *dicTemp = [list objectAtIndex:i];
            
            NSString *sensorId = [dicTemp objectForKey:@"sensorId"];
            if ([self.deviceId isEqualToString:sensorId]){
                if ([DeviceUtil getDeviceType:sensorId] == SENSOR_TYPE_AIR) {
                    NSDictionary *airDic = [dicTemp objectForKey:@"air"];
                    AirDevice *air = [[AirDevice alloc] initWithDictionary:airDic];
                    if (air.isOnline) {
                        configSuccess = true;
                        [self configTimeOver];
                    }
                    
                }
                else{
                    NSDictionary *gasDic = [dicTemp objectForKey:@"gas"];
                    GasDevice *gas = [[GasDevice alloc]initWithDictionary:gasDic];
                    if (gas.isOnline) {
                        configSuccess = true;
                        [self configTimeOver];
                    }
                }
                
            }
            
        }
    }];
    
    [request setFailedBlock:^{
        [HUD hide:YES];
        [configTimer invalidate];
        [timer invalidate];
        NSError *error = [request error];
        if (error) {
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = @"网络链接失败";
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
        }
    }];
    [request startAsynchronous];
}


-(void)timerStart{
    timer = [NSTimer scheduledTimerWithTimeInterval:5.0 target:self selector:@selector(TimeOver) userInfo:nil repeats:YES];
}
-(void) configTimeOver{
    NSLog(@"startConfig");
    [self EndConfigWifi];
    
}
-(void)configTimerStart{
    NSLog(@"configTimerStart");
    [HUD show:YES];
    [self startTransmitting: EASYLINK_V2];
    configTimer = [NSTimer scheduledTimerWithTimeInterval:120.0 target:self selector:@selector(configTimeOver)  userInfo:nil repeats:NO];
}

-(void)configTimeStop{
    configSuccess = NO;
    [self EndConfigWifi];
    [configTimer invalidate];
}

- (IBAction)finishBtnClick:(id)sender {
    if([[NSUserDefaults standardUserDefaults] boolForKey:@"isLoginSuccess"]){
        [[NSNotificationCenter defaultCenter]postNotificationName:@"NeedShowRoot" object:nil];
        
    }
    else{
        [[NSNotificationCenter defaultCenter]postNotificationName:@"NeedShowLogin" object:nil];
    }
    UIViewController * controller = self.presentingViewController;
    [self dismissViewControllerAnimated:NO completion:^{
        UIViewController * c = controller.presentingViewController;
        [c dismissViewControllerAnimated:NO completion:^{
                UIViewController * b = c.presentingViewController;
                [b dismissViewControllerAnimated:NO completion:nil];
        }];
    }];
    
}
@end
