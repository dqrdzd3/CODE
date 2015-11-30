//
//  RegisterViewController.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-21.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "RegisterViewController.h"
#import "LoginViewController.h"
#import "UIButton+Bootstrap.h"
#import "ServerResult.h"
#import "ProtocolViewController.h"
#import "StringUtils.h"


#define PATH_OF_DOCUMENT [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0]
@implementation RegisterViewController
@synthesize delegate;
@synthesize scrollView;
@synthesize userHeadImage;
@synthesize userNameInput;
@synthesize phoneInput;
@synthesize psdInput;
@synthesize psdConfirmInput;
@synthesize emailInput;
@synthesize registerBtn;
@synthesize protocolBtn;
@synthesize protocolSwitch;

//NSData* imageData = nil;
- (void)viewDidLoad {
    [super viewDidLoad];
    bLocationed = NO;
    
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, STATUSBARHEIGHT, SCREENWIDTH-100, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"注册新用户";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnBack = [[UIButton alloc]initWithFrame:CGRectMake(0, STATUSBARHEIGHT, 50, 44)];
    [btnBack setTitle:@"返回" forState:UIControlStateNormal];
    [btnBack setTitleColor:[UIColor colorWithRed:45.0/255 green:119.0/255 blue:248.0/255 alpha:1.0] forState:UIControlStateNormal];
    [btnBack addTarget:self action:@selector(backBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnBack];
    
    
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight)];
    scrollView.contentSize = CGSizeMake([UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height);
    [scrollView setBounces:YES];
    [self.view addSubview:scrollView];

    userHeadImage = [[UIImageView alloc]initWithFrame:CGRectMake(10, 10, 70, 70)];
    userHeadImage.image = [UIImage imageNamed:@"userHeadImage.png"];
    userHeadImage.layer.masksToBounds = YES;
    userHeadImage.layer.cornerRadius = 2.0;
    userHeadImage.userInteractionEnabled = YES;
    [scrollView addSubview:userHeadImage];
    
    userNameInput = [[UITextField alloc]initWithFrame:CGRectMake(userHeadImage.frame.origin.x+userHeadImage.frame.size.width+10, userHeadImage.frame.origin.y+userHeadImage.frame.size.height/2-17.5, SCREENWIDTH-userHeadImage.frame.origin.x-userHeadImage.frame.size.width-20, 30)];
    userNameInput.font = [UIFont systemFontOfSize:15.0];
    userNameInput.returnKeyType = UIReturnKeyDone;
    userNameInput.placeholder = @"输入用户昵称";
    userNameInput.borderStyle = UITextBorderStyleRoundedRect;
    [scrollView addSubview:userNameInput];

    UILabel *labelStarPhone = [[UILabel alloc]initWithFrame:CGRectMake(userHeadImage.frame.origin.x, userHeadImage.frame.origin.y+userHeadImage.frame.size.height+10, 5, 30)];
    labelStarPhone.backgroundColor = [UIColor clearColor];
    labelStarPhone.font = [UIFont systemFontOfSize:15.0];
    labelStarPhone.textColor = [UIColor redColor];
    labelStarPhone.text = @"*";
    [scrollView addSubview:labelStarPhone];
    UILabel *labelPhone = [[UILabel alloc]initWithFrame:CGRectMake(labelStarPhone.frame.origin.x+labelStarPhone.frame.size.width, labelStarPhone.frame.origin.y, 70, 30)];
    labelPhone.backgroundColor = [UIColor clearColor];
    labelPhone.font = [UIFont systemFontOfSize:15.0];
    labelPhone.textColor = [UIColor blackColor];
    labelPhone.text = @"账       号:";
    [scrollView addSubview:labelPhone];
    phoneInput = [[UITextField alloc]initWithFrame:CGRectMake(labelPhone.frame.origin.x+labelPhone.frame.size.width+5, labelPhone.frame.origin.y, SCREENWIDTH-labelPhone.frame.origin.x-labelPhone.frame.size.width-15, 30)];
    phoneInput.font = [UIFont systemFontOfSize:15.0];
    phoneInput.returnKeyType = UIReturnKeyDone;
    phoneInput.placeholder = @"输入手机号";
    phoneInput.borderStyle = UITextBorderStyleRoundedRect;
    [scrollView addSubview:phoneInput];
    
    
    UILabel *labelStarPsd = [[UILabel alloc]initWithFrame:CGRectMake(labelStarPhone.frame.origin.x, labelStarPhone.frame.origin.y+labelStarPhone.frame.size.height+10, 5, 30)];
    labelStarPsd.backgroundColor = [UIColor clearColor];
    labelStarPsd.font = [UIFont systemFontOfSize:15.0];
    labelStarPsd.textColor = [UIColor redColor];
    labelStarPsd.text = @"*";
    [scrollView addSubview:labelStarPsd];
    UILabel *labelPsd = [[UILabel alloc]initWithFrame:CGRectMake(labelStarPsd.frame.origin.x+labelStarPsd.frame.size.width, labelStarPsd.frame.origin.y, 70, 30)];
    labelPsd.backgroundColor = [UIColor clearColor];
    labelPsd.font = [UIFont systemFontOfSize:15.0];
    labelPsd.textColor = [UIColor blackColor];
    labelPsd.text = @"密       码:";
    [scrollView addSubview:labelPsd];
    psdInput = [[UITextField alloc]initWithFrame:CGRectMake(labelPsd.frame.origin.x+labelPsd.frame.size.width+5, labelPsd.frame.origin.y, SCREENWIDTH-labelPsd.frame.origin.x-labelPsd.frame.size.width-15, 30)];
    psdInput.font = [UIFont systemFontOfSize:15.0];
    psdInput.returnKeyType = UIReturnKeyDone;
    psdInput.placeholder = @"输入密码";
    psdInput.borderStyle = UITextBorderStyleRoundedRect;
    [scrollView addSubview:psdInput];
    
    
    UILabel *labelStarPsd2 = [[UILabel alloc]initWithFrame:CGRectMake(labelStarPsd.frame.origin.x, labelStarPsd.frame.origin.y+labelStarPsd.frame.size.height+10, 5, 30)];
    labelStarPsd2.backgroundColor = [UIColor clearColor];
    labelStarPsd2.font = [UIFont systemFontOfSize:15.0];
    labelStarPsd2.textColor = [UIColor redColor];
    labelStarPsd2.text = @"*";
    [scrollView addSubview:labelStarPsd2];
    UILabel *labelPsd2 = [[UILabel alloc]initWithFrame:CGRectMake(labelStarPsd2.frame.origin.x+labelStarPsd2.frame.size.width, labelStarPsd2.frame.origin.y, 70, 30)];
    labelPsd2.backgroundColor = [UIColor clearColor];
    labelPsd2.font = [UIFont systemFontOfSize:15.0];
    labelPsd2.textColor = [UIColor blackColor];
    labelPsd2.text = @"确认密码:";
    [scrollView addSubview:labelPsd2];
    psdConfirmInput = [[UITextField alloc]initWithFrame:CGRectMake(labelPsd2.frame.origin.x+labelPsd2.frame.size.width+5, labelPsd2.frame.origin.y, SCREENWIDTH-labelPsd2.frame.origin.x-labelPsd2.frame.size.width-15, 30)];
    psdConfirmInput.font = [UIFont systemFontOfSize:15.0];
    psdConfirmInput.returnKeyType = UIReturnKeyDone;
    psdConfirmInput.placeholder = @"再次输入密码";
    psdConfirmInput.borderStyle = UITextBorderStyleRoundedRect;
    [scrollView addSubview:psdConfirmInput];
    
    
    UILabel *labelEmail = [[UILabel alloc]initWithFrame:CGRectMake(labelPsd2.frame.origin.x, labelPsd2.frame.origin.y+labelPsd2.frame.size.height+10, 70, 30)];
    labelEmail.backgroundColor = [UIColor clearColor];
    labelEmail.font = [UIFont systemFontOfSize:15.0];
    labelEmail.textColor = [UIColor blackColor];
    labelEmail.text = @"电子邮箱:";
    [scrollView addSubview:labelEmail];
    emailInput = [[UITextField alloc]initWithFrame:CGRectMake(labelEmail.frame.origin.x+labelEmail.frame.size.width+5, labelEmail.frame.origin.y, SCREENWIDTH-labelEmail.frame.origin.x-labelEmail.frame.size.width-15, 30)];
    emailInput.font = [UIFont systemFontOfSize:15.0];
    emailInput.returnKeyType = UIReturnKeyDone;
    emailInput.placeholder = @"输入电子邮箱";
    emailInput.borderStyle = UITextBorderStyleRoundedRect;
    [scrollView addSubview:emailInput];
    

    registerBtn = [[UIButton alloc]initWithFrame:CGRectMake(20, emailInput.frame.origin.y+emailInput.frame.size.height+10,SCREENWIDTH-40,40)];
    registerBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    registerBtn.layer.masksToBounds = YES;
    registerBtn.layer.cornerRadius = 5.0;
    [registerBtn setTitle:@"提交" forState:UIControlStateNormal];
    [registerBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [registerBtn addTarget:self action:@selector(registerBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [scrollView addSubview:registerBtn];
    
    
    protocolSwitch = [[UISwitch alloc]initWithFrame:CGRectMake(registerBtn.frame.origin.x, registerBtn.frame.origin.y+registerBtn.frame.size.height+10, 50, 40)];
    [protocolSwitch setOn:YES];
    [scrollView addSubview:protocolSwitch];
    
    protocolBtn = [[UIButton alloc]initWithFrame:CGRectMake(protocolSwitch.frame.origin.x+protocolSwitch.frame.size.width+10, protocolSwitch.frame.origin.y,SCREENWIDTH/2,40)];
    [protocolBtn setTitle:@"同意空气电台协议" forState:UIControlStateNormal];
    [protocolBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [protocolBtn addTarget:self action:@selector(protocolBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [scrollView addSubview:protocolBtn];

    
 
    [psdInput addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    [psdConfirmInput addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    [emailInput addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    
    [userNameInput addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [phoneInput addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [psdInput addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [psdConfirmInput addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [emailInput addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    

    // Do any additional setup after loading the view.
    [psdInput setSecureTextEntry:YES];
    [psdConfirmInput setSecureTextEntry:YES];
    
    _searcher =[[BMKGeoCodeSearch alloc]init];
    _searcher.delegate = self;
    
    _locService = [[BMKLocationService alloc]init];
    _locService.delegate = self;
    //启动LocationService
    [_locService startUserLocationService];
    
    
    UITapGestureRecognizer *tapHead = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(userHeadClick)];
    [userHeadImage addGestureRecognizer:tapHead];
    
    if ([[NSFileManager defaultManager]fileExistsAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"head.jpg"]]) {
        [[NSFileManager defaultManager] removeItemAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"head.jpg"] error:nil];
    }
    if ([[NSFileManager defaultManager]fileExistsAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"]]) {
        [[NSFileManager defaultManager] removeItemAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"] error:nil];
    }
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
    [self.view bringSubviewToFront:viewTop];
}



//接收反向地理编码结果
-(void) onGetReverseGeoCodeResult:(BMKGeoCodeSearch *)searcher result:
(BMKReverseGeoCodeResult *)result
errorCode:(BMKSearchErrorCode)error{
  if (error == BMK_SEARCH_NO_ERROR) {
      NSLog(@"结果:%@",result);
      strLoc = result.address;
      strProvice = result.addressDetail.province;
      strCity = result.addressDetail.city;
      strArea = result.addressDetail.district;
  }
  else {
      NSLog(@"抱歉，未找到结果");
  }
}

//实现相关delegate 处理位置信息更新
//处理位置坐标更新
- (void)didUpdateUserLocation:(BMKUserLocation *)userLocation
{
    if (userLocation != nil && !bLocationed) {
        bLocationed = YES;
        NSLog(@"didUpdateUserLocation lat %f,long %f",userLocation.location.coordinate.latitude,userLocation.location.coordinate.longitude);
        fLa = userLocation.location.coordinate.latitude;
        fLo = userLocation.location.coordinate.longitude;
        //发起反向地理编码检索
        CLLocationCoordinate2D pt = (CLLocationCoordinate2D){fLa, fLo};
        BMKReverseGeoCodeOption *reverseGeoCodeSearchOption = [[
        BMKReverseGeoCodeOption alloc]init];
        reverseGeoCodeSearchOption.reverseGeoPoint = pt;
        BOOL flag = [_searcher reverseGeoCode:reverseGeoCodeSearchOption];
        if(flag)
        {
          NSLog(@"反geo检索发送成功");
        }
        else
        {
          NSLog(@"反geo检索发送失败");
        }
    }
}
-(void)textViewEditBegin{
    [scrollView setContentOffset:CGPointMake(0, 100) animated:YES];
}

-(void)textViewEditDone{
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    [userNameInput resignFirstResponder];
    [phoneInput resignFirstResponder];
    [psdInput resignFirstResponder];
    [psdConfirmInput resignFirstResponder];
    [emailInput resignFirstResponder];
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

- (IBAction)registerBtnClick:(id)sender {
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    [self.phoneInput resignFirstResponder];
    [self.userNameInput resignFirstResponder];
    [self.emailInput resignFirstResponder];
    [self.psdInput resignFirstResponder];
    [self.psdConfirmInput resignFirstResponder];
    
    NSString *name = userNameInput.text;
    
    if ([name length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请填写完整用户信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }

    NSString *password = psdInput.text;
    if ([password length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请填写完整用户信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    NSString *phone = phoneInput.text;
    if ([phone length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请填写完整用户信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    if (![StringUtils isValidateUserName:phone]) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"用户名格式不对" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    NSString *psdConfirm = psdConfirmInput.text;
    if ([psdConfirm length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请填写完整用户信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    if (![password isEqualToString:psdConfirm]) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"两次密码输入不一致信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    NSString *email = emailInput.text;
    if (email.length) {
        if (![StringUtils isValidateEmail:email]) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"邮箱格式不正确" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
            return;
        }
    }
    if(![protocolSwitch isOn]){
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"注册账户须同意空气电台协议" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    

    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_02_01]];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    request.requestMethod = @"POST";
    request.tag = 1;
    [request setPostValue:phone forKey:@"PHONE"];
    [request setPostValue:password forKey:@"PASSWORD"];
    [request setPostValue:name forKey:@"USERNAME"];
    if (email.length != 0) {
        [request setPostValue:email forKey:@"EMAIL"];
    }
    NSString *deviceToken = [[NSUserDefaults standardUserDefaults] objectForKey:@"DEVICETOKEN"];
    if (deviceToken != nil) {
        [request setPostValue:deviceToken forKey:@"IOSTOKEN"];
    }
    if (fLo != 0.0 && fLa != 0.0) {
        [request setPostValue:[NSString stringWithFormat:@"%f",fLo] forKey:@"LNG"];
        [request setPostValue:[NSString stringWithFormat:@"%f",fLa] forKey:@"LAT"];
    }
    if (strLoc.length != 0) {
        [request setPostValue:strLoc forKey:@"LOC"];
    }
    if (strProvice.length != 0) {
        [request setPostValue:strProvice forKey:@"PROVICE"];
    }
    if (strCity.length != 0) {
        [request setPostValue:strCity forKey:@"CITY"];
    }
    if (strArea.length != 0) {
        [request setPostValue:strArea forKey:@"AREA"];
    }
    
    NSString *strimagePath = [PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"];
    if ([[NSFileManager defaultManager]fileExistsAtPath:strimagePath]) {
        [request setFile:strimagePath forKey:@"file"];
    }
    [request setDelegate:self];
    [request startAsynchronous];
    [HUD show:YES];
}

//网络部分
- (void)requestFinished:(ASIHTTPRequest *)request
{
    [HUD hide:YES];
    NSString *responseString = [request responseString];
    NSDictionary *resultdic = [responseString  objectFromJSONString];
    if (request.tag == 1) {
        ServerResult *result = [[ServerResult alloc] initWithDictionary:resultdic];
        if (result.isOperateSuccess) {
            //将上述数据全部存储到NSUserDefaults中
            [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"isRegisterSuccess"];
            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
            //存储时，除NSNumber类型使用对应的类型意外，其他的都是使用setObject:forKey:
            [userDefaults setObject:userNameInput.text forKey:@"USERNAME"];
            [userDefaults setObject:phoneInput.text forKey:@"PHONE"];
            [userDefaults setObject:emailInput.text forKey:@"EMAIL"];
            [userDefaults setObject:psdInput.text forKey:@"PASSWORD"];
            [userDefaults synchronize];
            [delegate RegisterViewControllerFinish];
            
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
            [self dismissViewControllerAnimated:YES completion:nil];
        }
        else{
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
        }
    }
}
- (void)requestFailed:(ASIHTTPRequest *)request
{
    [HUD hide:YES];
    NSError *error = [request error];
    if (error) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败 " delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
    }
}


- (void)userHeadClick{
    UIActionSheet *actionSheet = [[UIActionSheet alloc]initWithTitle:@"选择一张图片作为头像" delegate:self  cancelButtonTitle:@"取消" destructiveButtonTitle:@"立即拍照上传" otherButtonTitles:@"从手机相册选取",nil];
    actionSheet.actionSheetStyle = UIActionSheetStyleAutomatic;
    [actionSheet showInView:self.view];
}
//图片照相
- (void)toCameraPickingController
{
    if (![UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
        NSLog(@"Error:没有照相设备");
    }
    else {
        UIImagePickerController *cameraPicker = [[UIImagePickerController alloc] init];
        cameraPicker.delegate = self;
        cameraPicker.sourceType = UIImagePickerControllerSourceTypeCamera;
        cameraPicker.allowsEditing = YES;
        [self presentViewController:cameraPicker animated:YES completion:nil];
    }
}
//图片
- (void)toPhotoPickingController
{
    if (![UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypePhotoLibrary]) {
        NSLog(@"Error:无图片库");
    }
    else {
        UIImagePickerController *photoPicker = [[UIImagePickerController alloc] init];
        photoPicker.delegate = self;
        photoPicker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        photoPicker.allowsEditing = YES;
        [self presentViewController:photoPicker animated:YES completion:nil];
    }
}

#pragma mark UIActionSheet协议
-(void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (buttonIndex ==0)
    {
        [self toCameraPickingController];
        
    }
    if (buttonIndex ==1) {
        [self toPhotoPickingController];
        
    }
    else return;
}

-(void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info
{
    UIImage *imagePicked = [info objectForKey:UIImagePickerControllerEditedImage];
    NSData* imageData = UIImageJPEGRepresentation(imagePicked, 0);
    
    NSString *strimagepath = [PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"];
    if ([[NSFileManager defaultManager] fileExistsAtPath:strimagepath]) {
        [[NSFileManager defaultManager] removeItemAtPath:strimagepath error:nil];
    }
    BOOL bwritetofile = [imageData writeToFile:strimagepath atomically:NO];
    if (!bwritetofile) {
        NSLog(@"保存图片失败");
    }
    [userHeadImage setImage:imagePicked];
    [picker dismissViewControllerAnimated:YES completion:nil];
}
-(void)imagePickerControllerDidCancel:(UIImagePickerController *)picker
{
    [picker dismissViewControllerAnimated:YES completion:nil];
    return;
}
//-(NSString *)saveUserHead{
//    NSString *path=pathInDocumentDirectory([NSString stringWithFormat:@"IMAGE-%u",[[[NSDate date]description]hash]]);
//   
//    NSData *imageData=UIImageJPEGRepresentation(_userHeadImage, 0.5);
//    [imageData writeToFile:path atomically:YES];
//    
//    [[NSUserDefaults standardUserDefaults] setObject:path forKey:@"myUserHead"];
//    return path;
//}
- (IBAction)backBtnClick:(id)sender {
    [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"isRegisterSuccess"];
    [self dismissViewControllerAnimated:YES completion:nil];
}
-(void)viewDidDisappear:(BOOL)animated{
    [[NSUserDefaults standardUserDefaults] setBool:YES forKey:@"isRegisterSuccess"];
}
- (IBAction)protocolBtnClick:(id)sender {

    ProtocolViewController *ca = [[ProtocolViewController alloc]init];
    //http://iweiguo.hwsensor.com/smart/protocol.html
    ca.mUrl = [BASE_URI stringByAppendingString:@"protocol.html"];
    ca.modalTransitionStyle = UIModalPresentationFormSheet;//跳转效果
    [self presentViewController:ca animated:YES completion:NULL];

}



@end
