//
//  LoginViewController.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-21.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "LoginViewController.h"
#import "AppDelegate.h"
#import "UIButton+Bootstrap.h"
#import "ServerResult.h"
#import <UIKit/UIKit.h>
#import <sys/utsname.h>
#import "RegisterViewController.h"
#import "../pop/POP.h"
@implementation LoginViewController
@synthesize delegate;
@synthesize viewInput;
@synthesize userNameInput;
@synthesize userPasswordInput;
@synthesize btnLogin;
@synthesize btnSavePsd;
@synthesize btnRegister;
@synthesize btnForgetPsd;

- (void)viewDidLoad {
    [super viewDidLoad];
    imageBackGround = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, FULLSCREENHEIGHT)];
    imageBackGround.image = [UIImage imageNamed:@"loginBg.png"];
    imageBackGround.userInteractionEnabled = YES;
    [self.view addSubview:imageBackGround];
    
    imageLogo = [[UIImageView alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-57, 60, 114, 48)];
    imageLogo.image = [UIImage imageNamed:@"aboutLogoText.png"];
    [imageBackGround addSubview:imageLogo];
    
    
    viewInput = [[UIView alloc]initWithFrame:CGRectMake(30, imageLogo.frame.origin.y+imageLogo.frame.size.height+20, SCREENWIDTH-60, 100)];
    viewInput.layer.masksToBounds = YES;
    viewInput.layer.cornerRadius = 8.0;
    viewInput.layer.borderColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0].CGColor;
    viewInput.layer.borderWidth = 1.0;
    viewInput.backgroundColor = [UIColor whiteColor];
    [imageBackGround addSubview:viewInput];
    
    
    UIImageView *imageUserNameIcon = [[UIImageView alloc]initWithFrame:CGRectMake(10, 15, 19, 19)];
    imageUserNameIcon.image = [UIImage imageNamed:@"loginUser.png"];
    [viewInput addSubview:imageUserNameIcon];
    
    UIView *viewInputline = [[UIView alloc]initWithFrame:CGRectMake(0, viewInput.frame.size.height/2-0.5 , viewInput.frame.size.width, 1)];
    viewInputline.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    [viewInput addSubview:viewInputline];
    
    UIImageView *imageUserPassIcon = [[UIImageView alloc]initWithFrame:CGRectMake(10, viewInputline.frame.origin.y+15, 19, 19)];
    imageUserPassIcon.image = [UIImage imageNamed:@"loginPwd.png"];
    [viewInput addSubview:imageUserPassIcon];
    
    userNameInput = [[UITextField alloc]initWithFrame:CGRectMake(imageUserNameIcon.frame.origin.x+imageUserNameIcon.frame.size.width+15, 2.5, viewInput.frame.size.width-imageUserNameIcon.frame.origin.x-imageUserNameIcon.frame.size.width-15, 45)];
    userNameInput.font = [UIFont systemFontOfSize:15.0];
    userNameInput.returnKeyType = UIReturnKeyDone;
    userNameInput.placeholder = @"输入用户名";
    [viewInput addSubview:userNameInput];

    userPasswordInput = [[UITextField alloc]initWithFrame:CGRectMake(imageUserPassIcon.frame.origin.x+imageUserPassIcon.frame.size.width+15, viewInputline.frame.origin.y+2.5, viewInput.frame.size.width-imageUserPassIcon.frame.origin.x-imageUserPassIcon.frame.size.width-15, 45)];
    userPasswordInput.font = [UIFont systemFontOfSize:15.0];
    userPasswordInput.returnKeyType = UIReturnKeyDone;
    userPasswordInput.placeholder = @"输入密码";
    userPasswordInput.secureTextEntry = YES;
    [viewInput addSubview:userPasswordInput];

    [userNameInput addTarget:self action:@selector(userNameInputEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [userPasswordInput addTarget:self action:@selector(userPasswordInputEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    
    btnSavePsd = [[UIButton alloc]initWithFrame:CGRectMake(viewInput.frame.origin.x, viewInput.frame.origin.y+viewInput.frame.size.height+5, 14, 14)];
    [btnSavePsd setImage:[UIImage imageNamed:@"cb_off.png"] forState:UIControlStateNormal];
    [btnSavePsd setImage:[UIImage imageNamed:@"cb_on.png"] forState:UIControlStateHighlighted];
    [btnSavePsd setImage:[UIImage imageNamed:@"cb_on.png"] forState:UIControlStateSelected];
    [btnSavePsd addTarget:self action:@selector(savePsdBtnClick) forControlEvents:UIControlEventTouchUpInside];
    [imageBackGround addSubview:btnSavePsd];
    
    UILabel *labelSavePsd = [[UILabel alloc]initWithFrame:CGRectMake(btnSavePsd.frame.origin.x+btnSavePsd.frame.size.width+5, btnSavePsd.frame.origin.y, SCREENWIDTH/2, 14)];
    labelSavePsd.backgroundColor = [UIColor clearColor];
    labelSavePsd.font = [UIFont systemFontOfSize:12.0];
    labelSavePsd.textColor = [UIColor blackColor];
    labelSavePsd.text = @"记住密码";
    [imageBackGround addSubview:labelSavePsd];
    
    btnLogin = [[UIButton alloc]initWithFrame:CGRectMake(30, btnSavePsd.frame.origin.y+btnSavePsd.frame.size.height+10,SCREENWIDTH-60,40)];
    btnLogin.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    btnLogin.layer.masksToBounds = YES;
    btnLogin.layer.cornerRadius = 5.0;
    [btnLogin setTitle:@"登录" forState:UIControlStateNormal];
    [btnLogin setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [btnLogin addTarget:self action:@selector(loginBtnClick) forControlEvents:UIControlEventTouchUpInside];
    [imageBackGround addSubview:btnLogin];
    
    btnForgetPsd = [[UIButton alloc]initWithFrame:CGRectMake(5, imageBackGround.frame.size.height-30, 90, 20)];
    [btnForgetPsd setTitle:@"忘记密码?" forState:UIControlStateNormal];
    [btnForgetPsd setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    btnForgetPsd.titleLabel.font = [UIFont systemFontOfSize:12.0];
    [btnForgetPsd addTarget:self action:@selector(ForgetPsd) forControlEvents:UIControlEventTouchUpInside];
    [imageBackGround addSubview:btnForgetPsd];
    
    btnRegister = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-100, imageBackGround.frame.size.height-30, 90, 20)];
    [btnRegister setTitle:@"注册新用户" forState:UIControlStateNormal];
    [btnRegister setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    btnRegister.titleLabel.font = [UIFont systemFontOfSize:12.0];
    [btnRegister addTarget:self action:@selector(gotoRegister) forControlEvents:UIControlEventTouchUpInside];
    [imageBackGround addSubview:btnRegister];

    [self initData];
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
}
-(void)viewDidAppear:(BOOL)animated{
    [self RegisterViewControllerFinish];
    if(![[NSUserDefaults standardUserDefaults] boolForKey:@"isRegisterSuccess"])
    {
        [self gotoRegister];
    }
}
-(void)userNameInputEditDone{
    [userNameInput resignFirstResponder];
}
-(void)userPasswordInputEditDone{
    [userPasswordInput resignFirstResponder];
}
-(void)initData{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    BOOL savePsd = [userDefaults boolForKey:@"isSavePassword"];
    
    [btnSavePsd setSelected:savePsd];
    if (savePsd) {
        NSString *phoneStr = [userDefaults objectForKey:@"PHONE"];
        NSString *psdStr = [userDefaults objectForKey:@"PASSWORD"];
        [userNameInput setText:phoneStr];
        [userPasswordInput setText:psdStr];
    }
}
-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    [self.view endEditing:YES];
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

/**
 *  获取手机型号
 *
 *  @return 手机型号
 */
-(NSString *)SystemInfo{
    struct utsname systemInfo;
    uname(&systemInfo);
    NSString *deviceString = [NSString stringWithCString:systemInfo.machine encoding:NSUTF8StringEncoding];
    
    NSArray *modelArray = @[
                            
                            @"i386", @"x86_64",
                            
                            @"iPhone1,1",
                            @"iPhone1,2",
                            @"iPhone2,1",
                            @"iPhone3,1",
                            @"iPhone3,2",
                            @"iPhone3,3",
                            @"iPhone4,1",
                            @"iPhone5,1",
                            @"iPhone5,2",
                            @"iPhone5,3",
                            @"iPhone5,4",
                            @"iPhone6,1",
                            @"iPhone6,2",
                            @"iPhone7,2",
                            @"iPhone7,3",
                            
                            @"iPod1,1",
                            @"iPod2,1",
                            @"iPod3,1",
                            @"iPod4,1",
                            @"iPod5,1",
                            
                            @"iPad1,1",
                            @"iPad2,1",
                            @"iPad2,2",
                            @"iPad2,3",
                            @"iPad2,4",
                            @"iPad3,1",
                            @"iPad3,2",
                            @"iPad3,3",
                            @"iPad3,4",
                            @"iPad3,5",
                            @"iPad3,6",
                            
                            @"iPad2,5",
                            @"iPad2,6",
                            @"iPad2,7",
                            ];
    NSArray *modelNameArray = @[
                                @"iPhone Simulator", @"iPhone Simulator",
                                
                                @"iPhone 2G",
                                @"iPhone 3G",
                                @"iPhone 3GS",
                                @"iPhone 4",
                                @"iPhone 4",
                                @"iPhone 4",
                                @"iPhone 4S",
                                @"iPhone 5",
                                @"iPhone 5",
                                @"iPhone 5c",
                                @"iPhone 5c",
                                @"iphone 5s",
                                @"iphone 5s",
                                @"iphone 6",
                                @"iphone 6 plus",
                                
                                @"iPod Touch 1G",
                                @"iPod Touch 2G",
                                @"iPod Touch 3G",
                                @"iPod Touch 4G",
                                @"iPod Touch 5G",
                                
                                @"iPad",
                                @"iPad 2",
                                @"iPad 2",
                                @"iPad 2",
                                @"iPad 2",
                                @"iPad 3",
                                @"iPad 3",
                                @"iPad 3",
                                @"iPad 4",
                                @"iPad 4",
                                @"iPad 4",
                                
                                @"iPad mini",
                                @"iPad mini",
                                @"ipad mini"
                                ];
    NSInteger modelIndex = -1;
    NSString *modelNameString = nil;
    modelIndex = [modelArray indexOfObject:deviceString];
    if (modelIndex >= 0 && modelIndex < [modelNameArray count]) {
        modelNameString = [modelNameArray objectAtIndex:modelIndex];
    }
    NSLog(@"----设备类型---%@",modelNameString);
    return modelNameString;
}

-(void)UpdateLoginInfo{
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    //读取NSString类型的数据
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:D005_DOSAVE]];

    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    request.requestMethod = @"POST";
    request.tag = 1;
    
    NSString *strModel = [self SystemInfo];
    struct utsname systemInfo;
    uname(&systemInfo);
    NSString  *deviceString   = [NSString stringWithCString:systemInfo.machine encoding:NSUTF8StringEncoding];
    NSString  *sysnameString  = [NSString stringWithCString:systemInfo.sysname encoding:NSUTF8StringEncoding];
    NSString  *nodenameString = [NSString stringWithCString:systemInfo.nodename encoding:NSUTF8StringEncoding];
    NSString  *releaseString  = [NSString stringWithCString:systemInfo.release encoding:NSUTF8StringEncoding];
    NSString  *versionString  = [NSString stringWithCString:systemInfo.version encoding:NSUTF8StringEncoding];
    NSString  *strAllInfo     = [NSString stringWithFormat:@"%@;%@;%@;%@;%@",deviceString,sysnameString,nodenameString,releaseString,versionString];
    
    NSDate *date = [NSDate date];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *strDate = [dateFormatter stringFromDate:date];
    
    [request setPostValue:@"1" forKey:@"ma001"];
    [request setPostValue:@"ios" forKey:@"ma002"];
    [request setPostValue:[NSString stringWithFormat:@"%f",[[[UIDevice currentDevice] systemVersion] floatValue]] forKey:@"ma003"];
    [request setPostValue:@"com.hwsensor.smartHome" forKey:@"ma004"];
    [request setPostValue:@"Apple" forKey:@"ma006"];
    [request setPostValue:strModel forKey:@"ma007"];
    [request setPostValue:userNameInput.text forKey:@"ma008"];
    [request setPostValue:strDate forKey:@"ma009"];
    [request setPostValue:strAllInfo forKey:@"ma010"];
    [request setPostValue:@"10" forKey:@"ma011"];
    [request setDelegate:self];
    [request startAsynchronous];
}


//网络部分
- (void)requestFinished:(ASIHTTPRequest *)request
{
    NSString *responseString = [request responseString];
    NSDictionary *resultdic = [responseString  objectFromJSONString];
    if (request.tag == 1) {
        [HUD hide:YES];
    }
    else if (request.tag == 2){
        NSString *resultCode = [resultdic objectForKey:SERVER_RESULT_CODE_KEY];
        if ([resultCode isEqualToString:SERVER_RESULT_CODE_SUCCESS]) {
            NSDictionary *userInfo = [resultdic objectForKey:@"dataObject"];
            NSString *userId = [userInfo objectForKey:@"ma001"];
            NSString *sessionId = [userInfo objectForKey:@"ma010"];
            NSString *email = [userInfo objectForKey:@"ma005"];
            NSString *userName = [userInfo objectForKey:@"ma008"];
            NSString *headUrl = [userInfo objectForKey:@"ma012"];
            //将上述数据全部存储到NSUserDefaults中
            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
            //存储时，除NSNumber类型使用对应的类型意外，其他的都是使用setObject:forKey:
            [userDefaults setObject:userId forKey:@"USERID"];
            [userDefaults setObject:sessionId forKey:@"SESSIONID"];
            [userDefaults setObject:email forKey:@"EMAIL"];
            [userDefaults setObject:userName forKey:@"USERNAME"];
            [userDefaults setObject:userNameInput.text forKey:@"PHONE"];
            [userDefaults setObject:userPasswordInput.text forKey:@"PASSWORD"];
            [userDefaults setObject:headUrl forKey:@"HEADIMAGEURL"];
            [userDefaults setBool:YES forKey:@"isLoginSuccess"];
            [userDefaults synchronize];
            NSLog(@"userId:%@",userId);
            [self UpdateLoginInfo];
            
            [delegate LoginViewControllerFinished:YES];
            [self dismissViewControllerAnimated:YES completion:nil];
        }
        else{
            [HUD hide:YES];
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:[resultdic objectForKey:@"message"] delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }
}
- (void)requestFailed:(ASIHTTPRequest *)request
{
    [HUD hide:YES];
    NSError *error = [request error];
    if (error) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
    }
}

- (void)loginBtnClick{
    //ZoneViewController *zone = [[ZoneViewController alloc]init];
    //[self presentViewController:zone animated:YES completion:nil];
    //return;
    [self.view endEditing:YES];
    NSString *name = userNameInput.text;
    if (name.length == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请输入用户名" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    NSString *password = userPasswordInput.text;
    if (password.length == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请输入密码" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        
        return;
    }
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_02_04]];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    request.requestMethod = @"POST";
    request.tag = 2;
    NSString *deviceToken = [[NSUserDefaults standardUserDefaults] objectForKey:@"DEVICETOKEN"];
    if (deviceToken != nil) {
        [request setPostValue:deviceToken forKey:@"IOSTOKEN"];
    }
    [request setPostValue:name forKey:@"PHONE"];
    [request setPostValue:password forKey:@"PASSWORD"];
    [request setDelegate:self];
    [request startAsynchronous];
    [HUD show:YES];
}
- (void)savePsdBtnClick{
    //将上述数据全部存储到NSUserDefaults中
    btnSavePsd.selected = !btnSavePsd.selected;
    if (!btnSavePsd.selected) {
        [btnSavePsd setSelected:NO];
        [[NSUserDefaults standardUserDefaults] setBool:NO
                       forKey:@"isSavePassword"];
      
    }
    else{
        [btnSavePsd setSelected:YES];
        [[NSUserDefaults standardUserDefaults] setBool:YES
                       forKey:@"isSavePassword"];
    }
}

-(void)ForgetPsd{
    ForgetPsdViewController *ForgetPsdView = [[ForgetPsdViewController alloc]init];
    ForgetPsdView.modalTransitionStyle = UIModalPresentationFormSheet;//跳转效果
    [self presentViewController:ForgetPsdView animated:YES completion:NULL];
}
-(void)gotoRegister{
    RegisterViewController *RegisterView = [[RegisterViewController alloc]init];
    RegisterView.modalTransitionStyle = UIModalPresentationFormSheet;//跳转效果
    RegisterView.delegate = self;
    [self presentViewController:RegisterView animated:YES completion:NULL];
}

-(void)RegisterViewControllerFinish{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    bool savePsd = [userDefaults boolForKey:@"isSavePassword"];
    if (savePsd) {
        NSString *phoneStr = [[NSUserDefaults standardUserDefaults] objectForKey:@"PHONE"];
        NSString *psdStr = [[NSUserDefaults standardUserDefaults] objectForKey:@"PASSWORD"];
        [userNameInput setText:phoneStr];
        [userPasswordInput setText:psdStr];
    }
}
@end
