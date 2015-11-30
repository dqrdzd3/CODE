//
//  PhoneBindDeviceController.m
//  SmartHome
//
//  Created by 李静 on 14-11-24.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "PhoneBindDeviceController.h"
#import "UIButton+Bootstrap.h"
#import "LoginViewController.h"
#import "DeviceUtil.h"
#import "SensorDetail.h"
#import "HardwareViewController.h"
#import "AppDelegate.h"
extern BOOL      g_bBindDevice;
extern NSString *g_UserDirPath;
@implementation PhoneBindDeviceController
@synthesize scrollView;
@synthesize imageDis;
@synthesize hintLabel;
@synthesize scanBtn;
@synthesize qrCodeTextField;
@synthesize bindBtn;

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor colorWithRed:235/255.0 green:230/255.0 blue:226/255.0 alpha:1.0];
    // Do any additional setup after loading the view.
    [self initView];
}
//触摸其他区域键盘消失
-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    [self.view endEditing:YES];
}
-(void)initView{
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
    
    btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5, 24.5, 60, 35)];
    [btnLeftMenu setTitle:@"上一步" forState:UIControlStateNormal];
    [btnLeftMenu setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    btnLeftMenu.backgroundColor = [UIColor colorWithRed:217/255.0 green:83/255.0 blue:79/255.0 alpha:1];
    btnLeftMenu.layer.masksToBounds = YES;
    btnLeftMenu.layer.cornerRadius = 3.0;
    [btnLeftMenu addTarget:self action:@selector(lastBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-65,24.5, 60, 35)];
    [btnRightMenu setTitle:@"下一步" forState:UIControlStateNormal];
    [btnRightMenu setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    btnRightMenu.backgroundColor = [UIColor colorWithRed:92/255.0 green:184/255.0 blue:92/255.0 alpha:1];
    btnRightMenu.layer.masksToBounds = YES;
    btnRightMenu.layer.cornerRadius = 3.0;
    [btnRightMenu addTarget:self action:@selector(nextBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
    
    
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight)];
    scrollView.contentSize = CGSizeMake([UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height+100);
    [self.view addSubview:scrollView];
    
    UIView *viewDis = [[UIView alloc]initWithFrame:CGRectMake(10, 10, SCREENWIDTH-20, 230)];
    viewDis.backgroundColor = [UIColor whiteColor];
    [scrollView addSubview:viewDis];
    
    imageDis = [[UIImageView alloc]initWithFrame:CGRectMake(viewDis.frame.size.width/2-150, 10, 300, 110)];
    imageDis.image = [UIImage imageNamed:@"ui_sensor_scan_show"];
    [viewDis addSubview:imageDis];
    
    hintLabel = [[RTLabel alloc]initWithFrame:CGRectMake(0, imageDis.frame.origin.y+imageDis.frame.size.height+30, imageDis.frame.size.width, viewDis.frame.size.height-imageDis.frame.origin.y-imageDis.frame.size.height-30)];
    [hintLabel setText:SENSOR_WIFI_GUIDE];
    [viewDis addSubview:hintLabel];
    
    UILabel *labeltmp = [[UILabel alloc]initWithFrame:CGRectMake(viewDis.frame.origin.x, viewDis.frame.origin.y+viewDis.frame.size.height, 100, 20)];
    labeltmp.backgroundColor = [UIColor clearColor];
    labeltmp.font = [UIFont systemFontOfSize:15.0];
    labeltmp.textColor = [UIColor lightGrayColor];
    labeltmp.text = @"手机绑定设备";
    [scrollView addSubview:labeltmp];

    UIView *viewScan = [[UIView alloc]initWithFrame:CGRectMake(labeltmp.frame.origin.x, labeltmp.frame.origin.y+labeltmp.frame.size.height+5, viewDis.frame.size.width, 60)];
    viewScan.backgroundColor = [UIColor whiteColor];
    [scrollView addSubview:viewScan];
    
    UILabel *labelSensorId = [[UILabel alloc]initWithFrame:CGRectMake(5, viewScan.frame.size.height/2-20, 60, 40)];
    labelSensorId.backgroundColor = [UIColor clearColor];
    labelSensorId.font = [UIFont systemFontOfSize:15.0];
    labelSensorId.textColor = [UIColor blackColor];
    labelSensorId.text = @"设备ID";
    [viewScan addSubview:labelSensorId];
    
    scanBtn = [[UIButton alloc]initWithFrame:CGRectMake(labelSensorId.frame.origin.x+labelSensorId.frame.size.width, labelSensorId.frame.origin.y, 50, labelSensorId.frame.size.height)];
    [scanBtn setTitle:@"扫一扫" forState:UIControlStateNormal];
    scanBtn.titleLabel.font = [UIFont systemFontOfSize:15.0];
    [scanBtn setTitleColor:[UIColor colorWithRed:45.0/255 green:119.0/255 blue:248.0/255 alpha:1.0] forState:UIControlStateNormal];
    [scanBtn addTarget:self action:@selector(scanBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewScan addSubview:scanBtn];

    qrCodeTextField = [[UITextField alloc]initWithFrame:CGRectMake(scanBtn.frame.origin.x+scanBtn.frame.size.width+5, scanBtn.frame.origin.y, viewScan.frame.size.width-scanBtn.frame.origin.x-scanBtn.frame.size.width-10, scanBtn.frame.size.height)];
    qrCodeTextField.font = [UIFont systemFontOfSize:15.0];
    qrCodeTextField.returnKeyType = UIReturnKeyDone;
    qrCodeTextField.placeholder = @"输入设备ID";
    [viewScan addSubview:qrCodeTextField];
    
    bindBtn = [[UIButton alloc]initWithFrame:CGRectMake(viewScan.frame.origin.x, viewScan.frame.origin.y+viewScan.frame.size.height+10,SCREENWIDTH-2*viewScan.frame.origin.x,40)];
    bindBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    bindBtn.layer.masksToBounds = YES;
    bindBtn.layer.cornerRadius = 5.0;
    [bindBtn setTitle:@"绑定设备" forState:UIControlStateNormal];
    [bindBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [bindBtn addTarget:self action:@selector(bindBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [scrollView addSubview:bindBtn];
    
    [btnRightMenu setHidden:YES];

    [qrCodeTextField addTarget:self action:@selector(CodetextViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [qrCodeTextField addTarget:self action:@selector(CodetextViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
    [self.view bringSubviewToFront:viewTop];
}

-(void)CodetextViewEditBegin{
    [scrollView setContentOffset:CGPointMake(0, 200) animated:YES];
}
-(void)CodetextViewEditDone{
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    [qrCodeTextField resignFirstResponder];
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

- (IBAction)bindBtnClick:(id)sender {
    [qrCodeTextField resignFirstResponder];
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    
    NSString *sensorId = [qrCodeTextField.text length] ? qrCodeTextField.text : @"";
    if([DeviceUtil isValidateDeviceNo:sensorId]){
        [self saveSensor];
    }
    else{
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"二维码格式不正确" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
    }
}

- (IBAction)scanBtnClick:(id)sender {
    QRCodeViewController *qrcodeView = [[QRCodeViewController alloc]init];
    qrcodeView.delegate = self;
    [self presentViewController:qrcodeView animated:YES completion:nil];
}
-(void)QRCodeViewControllerFinished:(NSString *)code{
    qrCodeTextField.text = code;
}

- (IBAction)lastBtnClick:(id)sender {
    [HUD hide:YES];
    if(g_bBindDevice || [[NSFileManager defaultManager]fileExistsAtPath:[g_UserDirPath stringByAppendingPathComponent:@"CacheData.plist"]]){
        [[NSNotificationCenter defaultCenter]postNotificationName:@"NeedShowRoot" object:nil];
        [self dismissViewControllerAnimated:NO completion:nil];
    }
    else
    {
        [self dismissViewControllerAnimated:NO completion:nil];
        AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
        [appDelegate ShowLoginView];
    }
}

- (IBAction)nextBtnClick:(id)sender {
    DescriptionConfigViewController *descriptionConfigViewController = [[DescriptionConfigViewController alloc]init];
        NSString *sensorId = [qrCodeTextField.text length] ? qrCodeTextField.text : nil;
    descriptionConfigViewController.deviceId = sensorId;
    descriptionConfigViewController.modalTransitionStyle = UIModalPresentationFormSheet;//跳转效果
    [self presentViewController:descriptionConfigViewController animated:YES completion:NULL];
}

-(void)saveSensor{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_01_01_02]];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    request.tag = 1;
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
    NSString *sensorId = qrCodeTextField.text;
    int type = [DeviceUtil getDeviceType:sensorId];
    NSString *name = [DeviceUtil getDeviceName:type];
    
    NSString *typeStr = [NSString stringWithFormat:@"%d",type];
    NSString *sensorIdUper = [sensorId uppercaseString];

    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:sensorIdUper forKey:@"DRIVERID"];
    [request setPostValue:typeStr forKey:@"DRIVERTYPE"];
    [request setPostValue:name forKey:@"DRIVERNAME"];
    [request setDelegate:self];
    [request startAsynchronous];
    [HUD show:YES];
}

//网络部分
- (void)requestFinished:(ASIHTTPRequest *)request
{
    [HUD hide:YES];
    NSString *responseString = [request responseString];
    NSDictionary *result = [responseString  objectFromJSONString];
    if (request.tag == 1) {
        NSString *resultCode = [result objectForKey:SERVER_RESULT_CODE_KEY];
        if ([resultCode isEqualToString:SERVER_RESULT_CODE_SUCCESS]) {
            [btnRightMenu setHidden:NO];
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = [result objectForKey:@"message"];
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
            g_bBindDevice = YES;
        }
        else{
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:[result objectForKey:@"message"] delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }
}
- (void)requestFailed:(ASIHTTPRequest *)request
{
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
}
@end
