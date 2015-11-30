//
//  SecondViewController.m
//  RESideMenuStoryboards
//
//  Created by Roman Efimov on 10/9/13.
//  Copyright (c) 2013 Roman Efimov. All rights reserved.
//

#import "SecondViewController.h"
#import "UIButton+Bootstrap.h"
#import "SensorType.h"
#import "DeviceTableViewCell.h"
#import "SensorDetail.h"
#import "DeviceUtil.h"
extern BOOL newModuleFound;
extern BOOL g_bBindDevice;
extern BOOL g_bgologin;
extern NSString *g_UserDirPath;
@implementation SecondViewController
@synthesize waitForAckThread;

//判断设备编号是否合法
-(BOOL)isValidateDeviceNo:(NSString *)deviceNo {
    NSString *regex = @"^(([a-fA-F0-9]{8})|([a-fA-F0-9]{12}))$";
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regex];
    return [emailTest evaluateWithObject:deviceNo];
}
-(void)viewDidAppear:(BOOL)animated{
    timer = [NSTimer scheduledTimerWithTimeInterval:3.0 target:self selector:@selector(UpdateDeviceList) userInfo:nil repeats:YES];
}
-(void)viewDidDisappear:(BOOL)animated{
    [timer invalidate];
    [self stopAction];
    [_deviceId resignFirstResponder];
    [_wifiPassword resignFirstResponder];
}

-(void)ReloadData{
    [self initListData];
}
- (void)viewDidLoad {
    [super viewDidLoad];
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(keyboardWillHide:)
                                                 name:UIKeyboardWillHideNotification
                                               object:nil];
    
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 64)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, 20, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = @"配置设备";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5, 25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    
    UIButton *btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33, 25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
   
    
    viewSetup = [[UIView alloc]initWithFrame:CGRectMake(0, 64, SCREENWIDTH, FULLSCREENHEIGHT-64)];
    viewSetup.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewSetup];
    
    UILabel *labelzcsb = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 20)];
    labelzcsb.backgroundColor = [UIColor clearColor];
    labelzcsb.textColor = [UIColor lightGrayColor];
    labelzcsb.text = @"注册设备";
    labelzcsb.font = [UIFont systemFontOfSize:14.0];
    [viewSetup addSubview:labelzcsb];
    
    UILabel *labelsbbh = [[UILabel alloc]initWithFrame:CGRectMake(5, labelzcsb.frame.origin.y+labelzcsb.frame.size.height+5, 80, 30)];
    labelsbbh.backgroundColor = [UIColor clearColor];
    labelsbbh.textColor = [UIColor blackColor];
    labelsbbh.text = @"设备编号";
    labelsbbh.font = [UIFont systemFontOfSize:14.0];
    [viewSetup addSubview:labelsbbh];
    
    _scanBtn = [[UIButton alloc]initWithFrame:CGRectMake(labelsbbh.frame.origin.x+labelsbbh.frame.size.width, labelsbbh.frame.origin.y, 60, labelsbbh.frame.size.height)];
    [_scanBtn setTitle:@"扫一扫" forState:UIControlStateNormal];
    _scanBtn.titleLabel.font = [UIFont systemFontOfSize:14.0];
    [_scanBtn setTitleColor:[UIColor colorWithRed:20/255.0 green:90/255.0 blue:180/255.0 alpha:1.0] forState:UIControlStateNormal];
    [_scanBtn addTarget:self action:@selector(scanBtnClick) forControlEvents:UIControlEventTouchUpInside];
    [viewSetup addSubview:_scanBtn];
    
    _deviceId = [[UITextField alloc]initWithFrame:CGRectMake(_scanBtn.frame.origin.x+_scanBtn.frame.size.width, labelsbbh.frame.origin.y, SCREENWIDTH-_scanBtn.frame.origin.x-_scanBtn.frame.size.width-5, labelsbbh.frame.size.height)];
    _deviceId.placeholder = @"输入设备ID";
    _deviceId.returnKeyType = UIReturnKeyDone;
    _deviceId.font = [UIFont systemFontOfSize:14.0];
    _deviceId.borderStyle = UITextBorderStyleRoundedRect;
    [viewSetup addSubview:_deviceId];
    
    
    _deviceSetupNetBtn = [[UIButton alloc]initWithFrame:CGRectMake(2*labelsbbh.frame.origin.x, labelsbbh.frame.origin.y+labelsbbh.frame.size.height+5, SCREENWIDTH-4*labelsbbh.frame.origin.x, 35)];
    [_deviceSetupNetBtn setTitle:@"注册设备" forState:UIControlStateNormal];
    [_deviceSetupNetBtn addTarget:self action:@selector(saveSensor) forControlEvents:UIControlEventTouchUpInside];
    [viewSetup addSubview:_deviceSetupNetBtn];
    
    
    UILabel *labelwifiname = [[UILabel alloc]initWithFrame:CGRectMake(labelsbbh.frame.origin.x, _deviceSetupNetBtn.frame.origin.y+_deviceSetupNetBtn.frame.size.height+5, labelsbbh.frame.size.width, labelsbbh.frame.size.height)];
    labelwifiname.backgroundColor = [UIColor clearColor];
    labelwifiname.textColor = [UIColor blackColor];
    labelwifiname.text = @"Wi-Fi名称";
    labelwifiname.font = [UIFont systemFontOfSize:14.0];
    [viewSetup addSubview:labelwifiname];
    
    _wifiName = [[UILabel alloc]initWithFrame:CGRectMake(labelwifiname.frame.origin.x+labelwifiname.frame.size.width, labelwifiname.frame.origin.y, SCREENWIDTH-labelwifiname.frame.origin.x-labelwifiname.frame.size.width-5, labelwifiname.frame.size.height)];
    _wifiName.backgroundColor = [UIColor clearColor];
    _wifiName.font = [UIFont systemFontOfSize:14.0];
    [viewSetup addSubview:_wifiName];
    
    UILabel *labelwifipass = [[UILabel alloc]initWithFrame:CGRectMake(labelwifiname.frame.origin.x, labelwifiname.frame.origin.y+labelwifiname.frame.size.height+5, labelwifiname.frame.size.width, labelsbbh.frame.size.height)];
    labelwifipass.backgroundColor = [UIColor clearColor];
    labelwifipass.textColor = [UIColor blackColor];
    labelwifipass.text = @"Wi-Fi密码";
    labelwifipass.font = [UIFont systemFontOfSize:14.0];
    [viewSetup addSubview:labelwifipass];
    
    _wifiPassword = [[UITextField alloc]initWithFrame:CGRectMake(labelwifipass.frame.origin.x+labelwifipass.frame.size.width, labelwifipass.frame.origin.y, SCREENWIDTH-labelwifipass.frame.origin.x-labelwifipass.frame.size.width-5, labelwifipass.frame.size.height)];
    _wifiPassword.placeholder = @"输入Wifi密码";
    _wifiPassword.returnKeyType = UIReturnKeyDone;
    _wifiPassword.font = [UIFont systemFontOfSize:14.0];
    _wifiPassword.borderStyle = UITextBorderStyleRoundedRect;
    [viewSetup addSubview:_wifiPassword];
    
    _deviceRegisterBtn = [[UIButton alloc]initWithFrame:CGRectMake(2*labelwifipass.frame.origin.x, labelwifipass.frame.origin.y+labelwifipass.frame.size.height+5, SCREENWIDTH-4*labelwifipass.frame.origin.x, 35)];
    [_deviceRegisterBtn setTitle:@"配网" forState:UIControlStateNormal];
    [_deviceRegisterBtn addTarget:self action:@selector(deviceRegisterClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewSetup addSubview:_deviceRegisterBtn];
    
    labelsblb = [[UILabel alloc]initWithFrame:CGRectMake(0, _deviceRegisterBtn.frame.origin.y+_deviceRegisterBtn.frame.size.height+5, SCREENWIDTH, 20)];
    labelsblb.backgroundColor = [UIColor clearColor];
    labelsblb.textColor = [UIColor lightGrayColor];
    labelsblb.text = @"设备列表 提示:左滑删除设备";
    labelsblb.font = [UIFont systemFontOfSize:14.0];
    [viewSetup addSubview:labelsblb];

    
    _devicesTableView = [[UITableView alloc]initWithFrame:CGRectMake(0, labelsblb.frame.origin.y+labelsblb.frame.size.height, SCREENWIDTH, viewSetup.frame.size.height-labelsblb.frame.origin.y-labelsblb.frame.size.height)];
    _devicesTableView.delegate = self;
    _devicesTableView.dataSource = self;
    [viewSetup addSubview:_devicesTableView];
    
    displayServices = [[NSMutableArray alloc]init];
    aryViewDetail = [[NSMutableArray alloc]init];
    displayServicesID = [[NSMutableArray alloc]init];
    _wifiPassword.secureTextEntry = YES;
    [_deviceRegisterBtn infoStyle];
    [_deviceSetupNetBtn infoStyle];
    if( easylink_config == nil){
        easylink_config = [[EASYLINK alloc]init];
        [easylink_config startFTCServerWithDelegate:self];
    }
    
    deviceIPConfig = [[NSMutableDictionary alloc] initWithCapacity:5];
    // wifi notification when changed.
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(wifiStatusChanged:) name:kReachabilityChangedNotification object:nil];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(appEnterInforground:) name:UIApplicationDidBecomeActiveNotification object:nil];
    
    wifiReachability = [Reachability reachabilityForLocalWiFi];  //监测Wi-Fi连接状态
    [wifiReachability startNotifier];
    
    //waitForAckThread = nil;
    NetworkStatus netStatus = [wifiReachability currentReachabilityStatus];
    if ( netStatus == NotReachable ) {// No activity if no wifi
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"Alert" message:@"WiFi 不可用.请检查wifi是否连接" delegate:Nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
        alertView.tag = 101;
        [alertView show];
    }
    else{
        [deviceIPConfig setObject:@YES forKey:@"DHCP"];
        [deviceIPConfig setObject:[EASYLINK getIPAddress] forKey:@"IP"];
        [deviceIPConfig setObject:[EASYLINK getNetMask] forKey:@"NetMask"];
        [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"GateWay"];
        [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"DnsServer"];
        [_wifiName setText:[EASYLINK ssidForConnectedNetwork]];
    }
    
    [_deviceId addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [_wifiPassword addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    _devicesTableView.tableFooterView = [[UIView alloc]init];
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
    [self.view bringSubviewToFront:viewTop];
}
-(void)UpdateDeviceList{
    [self initListData];
}
-(void)ClickLeftMenu{
    [self presentLeftMenuViewController:nil];
}
-(void)ClickRightMenu{
    [self presentRightMenuViewController:nil];
}
-(void)textViewEditDone{
    [_deviceId resignFirstResponder];
    [_wifiPassword resignFirstResponder];
}
- (IBAction)deviceRegisterClick:(id)sender {
    [_deviceId resignFirstResponder];
    [_wifiPassword resignFirstResponder];
    
    if (_wifiPassword.text.length == 0) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请输入WiFi密码" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        alert.tag = 102;
        [alert show];
        return;
    }
    [self startTransmitting: EASYLINK_V2];
    MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
    HUD2.mode = MBProgressHUDModeCustomView;
    HUD2.labelText = @"开始配网...";
    UIImageView *imageview = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, 60, 70)];
    imageview.image = [UIImage imageNamed:@"aboutLogoImage.png"];
    HUD2.customView = imageview;
    HUD2.margin = 10.f;
    HUD2.yOffset = 0.f;
    HUD2.removeFromSuperViewOnHide = YES;
    [HUD2 hide:YES afterDelay:2.0];
}

- (void)scanBtnClick{
    [_deviceId resignFirstResponder];
    [_wifiPassword resignFirstResponder];
    
    QRCodeViewController *qrcodeView = [[QRCodeViewController alloc]init];
    qrcodeView.delegate = self;
    [self presentViewController:qrcodeView animated:YES completion:nil];
}
-(void)QRCodeViewControllerFinished:(NSString *)code{
    _deviceId.text = code;
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
//        if ( _deviceRegisterBtn.selected )
            //            [self easyLinkV2ButtonAction:EasylinkV2Button]; /// Simply revert the state
            // The operation couldn’t be completed. No route to host
//        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"EMW ToolBox Alert" message:@"未连接wifi. 须连接wifi配网" delegate:Nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
//        [alertView show];
//        
//        _wifiName.text = @"";
//        _wifiPassword.text = @"";
    }
    else {
        //        NSString *password = [apInforRecord objectForKey:ssidField.text];
        //        if(password == nil) password = @"";
        //        [_wifiPassword setText:password];
        [_wifiName setText:[EASYLINK ssidForConnectedNetwork]];
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
    //waitForAckThread = [[NSThread alloc] initWithTarget:self selector:@selector(waitForAck:) object:nil];
    //[waitForAckThread start];
}

/*
 This method stop the sending of the configuration to the remote device
 In case of a failure the method throws an OSFailureException.
 */
-(void) stopAction{
    [easylink_config stopTransmitting];
    //[waitForAckThread cancel];
    //waitForAckThread= nil;
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
        sleep(1);
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
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"WIFI不可用,请检查WIFI链接" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        alertView.tag = 103;
        [alertView show];
        return;
    }
    
    NSString *ssid = [_wifiName.text length] ? _wifiName.text : nil;
    NSString *passwordKey = [_wifiPassword.text length] ? _wifiPassword.text : @"";
    
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

#pragma mark - Private Methods -

/* enableUIAccess
 * enable / disable the UI access like enable / disable the textfields
 * and other component while transmitting the packets.
 * @param: vbool is to validate the controls.
 */
-(void) enableUIAccess:(BOOL) isEnable{
    //             ssidField.userInteractionEnabled = isEnable;
    //             passwordField.userInteractionEnabled = isEnable;
    //             userInfoField.userInteractionEnabled = isEnable;
    //             ipAddress.userInteractionEnabled = isEnable;
    
    //[halo startAnimation: !isEnable];
    
}
//获取wifi信息
- (id)fetchSSIDInfo {
    NSArray *ifs = (__bridge_transfer id)CNCopySupportedInterfaces();
    NSLog(@"Supported interfaces: %@", ifs);
    id info = nil;
    for (NSString *ifnam in ifs) {
        info = (__bridge_transfer id)CNCopyCurrentNetworkInfo((__bridge CFStringRef)ifnam);
        NSLog(@"%@ => %@", ifnam, info);
        if (info && [info count]) { break; }
    }
    return info;
}
-(void)saveSensor{
    [_deviceId resignFirstResponder];
    [_wifiPassword resignFirstResponder];
    
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_01_01_02]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    
    NSString *sensorId = [_deviceId.text length] ? _deviceId.text : nil;
    if ([sensorId length] == 0) {
        return;
    }
    NSString *wifiNa = [_wifiName.text length] ? _wifiName.text : nil;
    NSString *wifiPwd = _wifiPassword.text;
    
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    int type = [DeviceUtil getDeviceType:sensorId];
    NSString *name = [self getDeviceName:type];
    NSString *typeStr = [NSString stringWithFormat:@"%d",type];
    NSString *sensorIdUper = [sensorId uppercaseString];
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:sensorIdUper forKey:@"DRIVERID"];
    [request setPostValue:typeStr forKey:@"DRIVERTYPE"];
    [request setPostValue:name forKey:@"DRIVERNAME"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        NSString *resultCode = [result objectForKey:SERVER_RESULT_CODE_KEY];
        MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
        HUD2.mode = MBProgressHUDModeText;
        HUD2.labelText = [result objectForKey:@"message"];
        HUD2.margin = 10.f;
        HUD2.yOffset = 0.f;
        HUD2.removeFromSuperViewOnHide = YES;
        [HUD2 hide:YES afterDelay:1.0];
        
//        if ([SERVER_RESULT_CODE_SUCCESS isEqualToString:resultCode]) {
//            [self performSelector:@selector(initData) withObject:nil afterDelay:1.0f];
//        }
    }];
    [request setFailedBlock:^{
        [HUD hide:YES];
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
    HUD.labelFont = [UIFont systemFontOfSize:14.0];
    [HUD show:YES];
}

-(NSString*)getDeviceName:(int)type{
    NSString *name = @"未定义设备";
    switch (type) {
        case 0:
            break;
        case 1:
            name = @"可燃气体";
            break;
        case 2:
            name = @"空气质量";
            break;
        case 3:
            name = @"烟雾传感器";
            break;
        default:
            name = @"未定义设备";
            break;
    }
    return name;
}

#pragma mark - Table View
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSUInteger count = [displayServices count];
    return count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSString *tableCellIdentifier2 = [NSString stringWithFormat:@"DeviceTableCell_%d",indexPath.row];
    DeviceTableViewCell *cell = (DeviceTableViewCell *)[tableView dequeueReusableCellWithIdentifier:tableCellIdentifier2];
    if (cell == nil) {
        cell = [[DeviceTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:tableCellIdentifier2];
        cell.delegate = self;
        cell.tag = indexPath.row;
    }
    SensorDetail *sensorDetail = [aryViewDetail objectAtIndex:indexPath.row];
    if ([displayServicesID indexOfObject:sensorDetail.sensorId] != NSNotFound) {
        cell.deviceName.text = sensorDetail.name;
        cell.deviceId.text = sensorDetail.sensorId;
        
        if ([DeviceUtil getDeviceType:sensorDetail.sensorId] == SENSOR_TYPE_AIR) {
            AirDevice *air = sensorDetail.airDevice;
            if (![air isOnline]) {
                cell.isOnline.text = @"不在线";
            }
            else{
                cell.isOnline.text = @"在线";
            }
        }
        else{
            GasDevice *gas = sensorDetail.gasDevice;
            if (![gas isOnline]) {
                cell.isOnline.text = @"不在线";
            }
            else{
                cell.isOnline.text = @"在线";
            }
        }
        NSLog(@"id:%@-name:%@",sensorDetail.sensorId,sensorDetail.name);
    }
    else{
        cell.isOnline.text = @"不在线";
        NSLog(@"id:%@-name:%@-设备不在设备列表内",sensorDetail.sensorId,sensorDetail.name);
    }

    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 60;
}
- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"Detail selected");
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (alertView.tag == 10001){
        if (buttonIndex == 1) {
            UITextField *namefield = [alertView textFieldAtIndex:0];
            if (namefield.text.length != 0) {
                strChangeDeviceName = namefield.text;
                NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SAVEEDITEQUIPS]];
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
                [request setPostValue:strChangeDeviceName forKey:@"DRIVERNAME"];
                [request setPostValue:strChangeDeviceId forKey:@"DRIVERID"];
                [request setPostValue:[NSString stringWithFormat:@"%d",[DeviceUtil getDeviceType:strChangeDeviceId]] forKey:@"DRIVERTYPE"];
                [request setCompletionBlock:^{
                    [HUD hide:YES];
                    NSString *responseString = [request responseString];
                    NSDictionary *result = [responseString  objectFromJSONString];
                    MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
                    HUD2.mode = MBProgressHUDModeText;
                    HUD2.labelText = [result objectForKey:@"message"];
                    HUD2.margin = 10.f;
                    HUD2.yOffset = 0.f;
                    HUD2.removeFromSuperViewOnHide = YES;
                    [HUD2 hide:YES afterDelay:1.0];
                    
                    NSString *resultCode = [result objectForKey:SERVER_RESULT_CODE_KEY];
                    if ([SERVER_RESULT_CODE_SUCCESS isEqualToString:resultCode]) {
                        [self performSelector:@selector(initListData) withObject:nil afterDelay:1.0f];
                    }
                }];
                
                [request setFailedBlock:^{
                    [HUD hide:YES];
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
                [HUD show:YES];
            }
            else{
                UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"设备名称不能为空" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:nil, nil];
                alert.tag = 10002;
                [alert show];
            }
        }
    }
}
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle) editingStyle forRowAtIndexPath:(NSIndexPath *) indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        SensorDetail *sensorDetail = [displayServices objectAtIndex:indexPath.row];
        [displayServices removeObjectAtIndex:[indexPath row]];
        strDeleteSensorid = sensorDetail.sensorId;
        [self deleteDevice:sensorDetail.sensorId];
        [_devicesTableView deleteRowsAtIndexPaths:[NSArray arrayWithObjects:indexPath, nil] withRowAnimation:UITableViewRowAnimationTop];
    }  
}
//设置选中Cell的响应事件

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath*)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];//
    SensorDetail *sensorDetail = [displayServices objectAtIndex:indexPath.row];
    strChangeDeviceName = sensorDetail.name;
    strChangeDeviceId = sensorDetail.sensorId;
    UIAlertView *alertText = [[UIAlertView alloc]initWithTitle:@"请输入新的设备名称" message:@"" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"确定", nil];
    alertText.alertViewStyle = UIAlertViewStylePlainTextInput;
    UITextField *namefield = [alertText textFieldAtIndex:0];
    namefield.returnKeyType = UIReturnKeyDone;
    namefield.text = strChangeDeviceName;
    alertText.tag = 10001;
    [alertText show];
}
-(long)getMinitusFromNow:(NSString *)time{
    int result = 0;
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSDate *senddate = [NSDate date];
    //结束时间
    NSDate *endDate = [dateFormatter dateFromString:time];
    //当前时间
    NSDate *senderDate = [dateFormatter dateFromString:[dateFormatter stringFromDate:senddate]];
    //得到相差秒数
    NSTimeInterval second = [endDate timeIntervalSinceDate:senderDate];
    result = ((int)second)/60;
    return result;
}

-(void)deleteDevice:(NSString*)sensorId{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_01_01_04]];
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
    
    int type = [DeviceUtil getDeviceType:sensorId];
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:sensorId forKey:@"DRIVERID"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        NSDictionary *result = [responseString  objectFromJSONString];
        NSString *resultCode = [result objectForKey:SERVER_RESULT_CODE_KEY];
        if ([resultCode isEqualToString:SERVER_RESULT_CODE_SUCCESS]) {
            NSMutableArray *list = [[NSMutableArray alloc]initWithContentsOfFile:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"]];
            for (NSDictionary *dic in list) {
                if ([strDeleteSensorid isEqualToString:[dic objectForKey:@"sensorId"]]) {
                    [list removeObject:dic];
                    break;
                }
            }
            if (list.count != 0) {
                [list writeToFile:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"] atomically:YES];
            }
            else{
                g_bBindDevice = NO;
                g_bgologin = YES;
                NSError *error;
                if ([[NSFileManager defaultManager]fileExistsAtPath:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"]]) {
                    BOOL bremove = [[NSFileManager defaultManager] removeItemAtPath:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"] error:&error];
                    if (!bremove) {
                        NSLog(@"error:%@",error.description);
                    }
                }
            }
            [_devicesTableView reloadData];
        }
    }];
    [request setFailedBlock:^{
        [HUD hide:YES];
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
    [HUD show:YES];
}

-(void)initViewDetailData{
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
        NSString *resultCode     = [result objectForKey:SERVER_RESULT_CODE_KEY];
        NSArray *dataObject      = [result objectForKey:@"dataObject"];
        NSDictionary *arr        = dataObject[0];
        NSArray *list            = [arr objectForKey:@"sensorList"];
        NSMutableArray *items    = [[NSMutableArray alloc]init];
        NSError* err             = nil;
        
        labelsblb.text = [NSString stringWithFormat:@"设备列表(%d台)提示:左滑删除设备",list.count];
        for(NSDictionary *member in list)
        {
            SensorDetail *item = [[SensorDetail alloc] init];
            item.sensorId = [member objectForKey:@"sensorId"];
            item.name = [member objectForKey:@"name"];
            
            if ([DeviceUtil getDeviceType:item.sensorId] == SENSOR_TYPE_AIR) {
                NSDictionary *airDic = [member objectForKey:@"air"];
                AirDevice *air = [[AirDevice alloc] initWithDictionary:airDic];
                item.airDevice = air;
            }
            else{
                NSDictionary *gasDic = [member objectForKey:@"gas"];
                GasDevice *gas = [[GasDevice alloc]initWithDictionary:gasDic];
                item.gasDevice = gas;
            }
            [items addObject:item];
        }
        
        if (aryViewDetail.count != 0) {
            [aryViewDetail removeAllObjects];
        }
        [aryViewDetail addObjectsFromArray:items];
        [_devicesTableView reloadData];
    }];
    
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        NSLog(@"error:%@",error.description);
    }];
    [request startAsynchronous];
}



-(void)initListData{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_01_01_03]];
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
        NSString *resultCode     = [result objectForKey:SERVER_RESULT_CODE_KEY];
        NSArray *dataObject      = [result objectForKey:@"dataObject"];
        NSDictionary *arr        = dataObject[0];
        NSArray *list            = [arr objectForKey:@"sensorList"];
        NSMutableArray *items    = [[NSMutableArray alloc]init];
        NSError* err             = nil;
        
        labelsblb.text = [NSString stringWithFormat:@"设备列表(%d台)提示:左滑删除设备",list.count];
        if (displayServicesID.count != 0) {
            [displayServicesID removeAllObjects];
        }
        for(NSDictionary *member in list)
        {
            NSLog(@"%@",[member objectForKey:@"sensorId"]);
            SensorDetail *item = [[SensorDetail alloc] init];
            item.sensorId = [member objectForKey:@"sensorId"];
            item.name = [member objectForKey:@"name"];
            if ([DeviceUtil getDeviceType:item.sensorId] == SENSOR_TYPE_AIR) {
                NSDictionary *airDic = [member objectForKey:@"air"];
                AirDevice *air = [[AirDevice alloc] initWithDictionary:airDic];
                item.airDevice = air;
                NSLog(@"air%@",air.name);
            }
            else{
                NSDictionary *gasDic = [member objectForKey:@"gas"];
                GasDevice *gas = [[GasDevice alloc]initWithDictionary:gasDic];
                item.gasDevice = gas;
            }
            
            [displayServicesID addObject:item.sensorId];
            [items addObject:item];
        }
        
        if (displayServices.count != 0) {
            [displayServices removeAllObjects];
        }
        [displayServices addObjectsFromArray:items];
        if (displayServices.count != 0) {
            g_bBindDevice = YES;
            g_bgologin = NO;
        }
        [self initViewDetailData];
    }];
    
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        NSLog(@"error:%@",error.description);
    }];
    [request startAsynchronous];
}

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    [self.view endEditing:YES];
}

-(void) keyboardWillHide:(NSNotification *)note{
    if (viewSetup.frame.origin.y != 64) {
        CGRect frame = viewSetup.frame;
        frame.origin.y = 64;
        viewSetup.frame = frame;
    }
}

- (void)appEnterInforground:(NSNotification*)notification{
    NetworkStatus netStatus = [wifiReachability currentReachabilityStatus];
    
    if ( netStatus != NotReachable && ![[EASYLINK ssidForConnectedNetwork] hasPrefix:@"EasyLink_"]) {
        if (![_wifiName.text isEqualToString:[EASYLINK ssidForConnectedNetwork]]) {
            _wifiName.text = [EASYLINK ssidForConnectedNetwork];
            _wifiPassword.text = @"";
            
            [deviceIPConfig setObject:@YES forKey:@"DHCP"];
            [deviceIPConfig setObject:[EASYLINK getIPAddress] forKey:@"IP"];
            [deviceIPConfig setObject:[EASYLINK getNetMask] forKey:@"NetMask"];
            [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"GateWay"];
            [deviceIPConfig setObject:[EASYLINK getGatewayAddress] forKey:@"DnsServer"];
        }

    }
}
@end
