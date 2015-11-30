//
//  ForgetPsdViewController.m
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "ForgetPsdViewController.h"
#import "UIButton+Bootstrap.h"
#import "LoginViewController.h"
#import "ServerResult.h"

@implementation ForgetPsdViewController
@synthesize phoneNumberText;
@synthesize verifyCodeText;
@synthesize nPasswordText;
@synthesize repeatPasswordText;
@synthesize getVerifyBtn;
@synthesize postBtn;



-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event{
    [self.view endEditing:YES];
}
- (void)viewDidLoad {
    [super viewDidLoad];
    secondsCountDown = 60;
    // Do any additional setup after loading the view.'
    [self initView];
}
-(void)initView{
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];

    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, STATUSBARHEIGHT, SCREENWIDTH-100, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"忘记密码";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnBack = [[UIButton alloc]initWithFrame:CGRectMake(0, STATUSBARHEIGHT, 50, 44)];
    [btnBack setTitle:@"返回" forState:UIControlStateNormal];
    [btnBack setTitleColor:[UIColor colorWithRed:45.0/255 green:119.0/255 blue:248.0/255 alpha:1.0] forState:UIControlStateNormal];
    [btnBack addTarget:self action:@selector(backBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnBack];
    
    UILabel *labelPhone = [[UILabel alloc]initWithFrame:CGRectMake(5, viewTop.frame.size.height+10, 80, 30)];
    labelPhone.backgroundColor = [UIColor clearColor];
    labelPhone.font = [UIFont systemFontOfSize:15.0];
    labelPhone.textColor = [UIColor blackColor];
    labelPhone.text = @"手机号码:";
    [self.view addSubview:labelPhone];
    phoneNumberText = [[UITextField alloc]initWithFrame:CGRectMake(labelPhone.frame.origin.x+labelPhone.frame.size.width+5, labelPhone.frame.origin.y, SCREENWIDTH-labelPhone.frame.origin.x-labelPhone.frame.size.width-10, labelPhone.frame.size.height)];
    phoneNumberText.font = [UIFont systemFontOfSize:15.0];
    phoneNumberText.returnKeyType = UIReturnKeyDone;
    phoneNumberText.placeholder = @"手机号码";
    phoneNumberText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:phoneNumberText];
    

    UILabel *labelVerify = [[UILabel alloc]initWithFrame:CGRectMake(5, labelPhone.frame.origin.y+labelPhone.frame.size.height+10, 80, 30)];
    labelVerify.backgroundColor = [UIColor clearColor];
    labelVerify.font = [UIFont systemFontOfSize:15.0];
    labelVerify.textColor = [UIColor blackColor];
    labelVerify.text = @"验证码:";
    [self.view addSubview:labelVerify];
    verifyCodeText = [[UITextField alloc]initWithFrame:CGRectMake(labelVerify.frame.origin.x+labelVerify.frame.size.width+5, labelVerify.frame.origin.y, SCREENWIDTH-labelVerify.frame.origin.x-labelVerify.frame.size.width-10-85, labelVerify.frame.size.height)];
    verifyCodeText.font = [UIFont systemFontOfSize:15.0];
    verifyCodeText.returnKeyType = UIReturnKeyDone;
    verifyCodeText.placeholder = @"输入验证码";
    verifyCodeText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:verifyCodeText];
    
    getVerifyBtn = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-85, labelVerify.frame.origin.y, 80, 30)];
    getVerifyBtn.layer.masksToBounds = YES;
    getVerifyBtn.layer.cornerRadius = 3.0;
    [getVerifyBtn setTitle:@"获取验证码" forState:UIControlStateNormal];
    getVerifyBtn.backgroundColor = [UIColor colorWithRed:121/255.0 green:157/255.0 blue:63/255.0 alpha:1.0];
    getVerifyBtn.titleLabel.font = [UIFont systemFontOfSize:12.0];
    [getVerifyBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [getVerifyBtn addTarget:self action:@selector(getVerifyBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:getVerifyBtn];
    
    
    UILabel *labelNewPsd = [[UILabel alloc]initWithFrame:CGRectMake(5, labelVerify.frame.origin.y+labelVerify.frame.size.height+10, 80, 30)];
    labelNewPsd.backgroundColor = [UIColor clearColor];
    labelNewPsd.font = [UIFont systemFontOfSize:15.0];
    labelNewPsd.textColor = [UIColor blackColor];
    labelNewPsd.text = @"新密码:";
    [self.view addSubview:labelNewPsd];
    nPasswordText = [[UITextField alloc]initWithFrame:CGRectMake(labelNewPsd.frame.origin.x+labelNewPsd.frame.size.width+5, labelNewPsd.frame.origin.y, SCREENWIDTH-labelNewPsd.frame.origin.x-labelNewPsd.frame.size.width-10, labelNewPsd.frame.size.height)];
    nPasswordText.font = [UIFont systemFontOfSize:15.0];
    nPasswordText.returnKeyType = UIReturnKeyDone;
    nPasswordText.placeholder = @"输入新密码";
    nPasswordText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:nPasswordText];
    
    
    UILabel *labelNewPsd2 = [[UILabel alloc]initWithFrame:CGRectMake(5, labelNewPsd.frame.origin.y+labelNewPsd.frame.size.height+10, 80, 30)];
    labelNewPsd2.backgroundColor = [UIColor clearColor];
    labelNewPsd2.font = [UIFont systemFontOfSize:15.0];
    labelNewPsd2.textColor = [UIColor blackColor];
    labelNewPsd2.text = @"确认密码:";
    [self.view addSubview:labelNewPsd2];
    repeatPasswordText = [[UITextField alloc]initWithFrame:CGRectMake(labelNewPsd2.frame.origin.x+labelNewPsd2.frame.size.width+5, labelNewPsd2.frame.origin.y, SCREENWIDTH-labelNewPsd2.frame.origin.x-labelNewPsd2.frame.size.width-10, labelNewPsd2.frame.size.height)];
    repeatPasswordText.font = [UIFont systemFontOfSize:15.0];
    repeatPasswordText.returnKeyType = UIReturnKeyDone;
    repeatPasswordText.placeholder = @"确认密码";
    repeatPasswordText.borderStyle = UITextBorderStyleRoundedRect;
    [self.view addSubview:repeatPasswordText];
    
    postBtn = [[UIButton alloc]initWithFrame:CGRectMake(10, labelNewPsd2.frame.origin.y+labelNewPsd2.frame.size.height+10,SCREENWIDTH-20,40)];
    postBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    postBtn.layer.masksToBounds = YES;
    postBtn.layer.cornerRadius = 5.0;
    [postBtn setTitle:@"提交" forState:UIControlStateNormal];
    [postBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [postBtn addTarget:self action:@selector(postBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:postBtn];

    [phoneNumberText setText:[[NSUserDefaults standardUserDefaults] objectForKey:@"PHONE"]];
    [nPasswordText setSecureTextEntry:YES];
    [repeatPasswordText setSecureTextEntry:YES];
    
    [phoneNumberText addTarget:self action:@selector(FieldEditEnd) forControlEvents:UIControlEventEditingDidEndOnExit];
    [verifyCodeText addTarget:self action:@selector(FieldEditEnd) forControlEvents:UIControlEventEditingDidEndOnExit];
    [nPasswordText addTarget:self action:@selector(FieldEditEnd) forControlEvents:UIControlEventEditingDidEndOnExit];
    [repeatPasswordText addTarget:self action:@selector(FieldEditEnd) forControlEvents:UIControlEventEditingDidEndOnExit];
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
}

-(void)FieldEditEnd{
    [phoneNumberText resignFirstResponder];
    [verifyCodeText resignFirstResponder];
    [nPasswordText resignFirstResponder];
    [repeatPasswordText resignFirstResponder];
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

- (IBAction)getVerifyBtnClick:(id)sender {
    NSString *phone = [phoneNumberText text];
    if (phone.length!=11) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请检查手机号是否正确" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
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
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_02_05]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    request.tag = 1;
    [request setPostValue:phone forKey:@"PHONE"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        NSDictionary *resultDic = [responseString  objectFromJSONString];
        if (resultDic != nil) {
            ServerResult *result = [[ServerResult alloc] initWithDictionary:resultDic];
            if (result != nil && !result.isOperateSuccess) {
                UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:result.message delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
                [alertView show];
                
                [self stopCountDown];
                [getVerifyBtn setEnabled:YES];
                [getVerifyBtn setTitle:@"获取验证码" forState:UIControlStateNormal];
            }
        }
    }];
    
    [request setFailedBlock:^{
        [HUD hide:YES];
        [self stopCountDown];
        [getVerifyBtn setEnabled:YES];
        [getVerifyBtn setTitle:@"获取验证码" forState:UIControlStateNormal];
        NSError *error = [request error];
        if (error) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败 " delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    [request startAsynchronous];
    [self startCountDown];
}

- (IBAction)postBtnClick:(id)sender {
    [self postData];
}
- (void)postData{
    NSString *phone = [phoneNumberText text];
    if ([phone length] != 11) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"手机号不合法" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    NSString *verifyCode = [verifyCodeText text];
    if ([verifyCode length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请输入验证码" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    NSString *password = [nPasswordText text];
    if ([password length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请输入新密码" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }

    NSString *psdConfirm = [repeatPasswordText text];
    if ([psdConfirm length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请填写完整用户信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    if (![password isEqualToString:psdConfirm]) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"确认密码与设置的密码不一样" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }

    
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_02_02]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    [request setPostValue:phone forKey:@"PHONE"];
    [request setPostValue:password forKey:@"PASSWORD"];
    [request setPostValue:verifyCode forKey:@"YZM"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        NSDictionary *resultDic = [responseString  objectFromJSONString];
        ServerResult *result = [[ServerResult alloc] initWithDictionary:resultDic];
        if (result.isOperateSuccess) {
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
        }
        else{
            NSString *errorMsg = result.message;
            NSLog(@"Error: %@", errorMsg);
            
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:errorMsg delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        if (error) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败 " delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    [request startAsynchronous];
    [HUD show:YES];
    
}
- (IBAction)backBtnClick:(id)sender {
    [self.view endEditing:YES];
    [countDownTimer invalidate];
    [self dismissViewControllerAnimated:YES completion:nil];
}

-(void)countDownTime:(NSTimer *) timer{
    secondsCountDown--;
    if (secondsCountDown == 0) {
        [self stopCountDown];
        [getVerifyBtn setEnabled:YES];
        [getVerifyBtn setTitle:@"获取验证码" forState:UIControlStateNormal];
    }
    else{
        NSString *str = [NSString stringWithFormat:@"%ds后可重发",secondsCountDown];
        [getVerifyBtn setTitle:str forState:UIControlStateNormal];
    }
}

-(void)startCountDown{
    [getVerifyBtn setEnabled:NO];
    [countDownTimer invalidate];
    secondsCountDown = 60;
    // 定义一个NSTimer
    NSString *str= [NSString stringWithFormat:@"%ds后可重发",secondsCountDown];
    [getVerifyBtn setTitle:str forState:UIControlStateNormal];
    countDownTimer = [NSTimer scheduledTimerWithTimeInterval:1.0
                                                      target:self
                                                         selector:@selector(countDownTime:)  userInfo:nil
                                                     repeats:YES];
}

// 停止定时器
- (void)stopCountDown{
    [countDownTimer invalidate];
}
@end
