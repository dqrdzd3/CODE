//
//  AccountManagerViewController.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-23.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "AccountManagerViewController.h"
#import "UIButton+Bootstrap.h"
#import "ServerResult.h"
#import "UIImageView+WebCache.h"
#import "StringUtils.h"
#define PATH_OF_DOCUMENT    [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0]
@implementation AccountManagerViewController
@synthesize userInfo;
@synthesize scrollView;
@synthesize userHeadImage;
@synthesize userName;
@synthesize labelphone;
@synthesize fieldemail;
@synthesize changeAccountBtn;
@synthesize oldPsdText;
@synthesize nPsdText;
@synthesize repeatPsdText;
@synthesize changePsdBtn;

- (void)viewDidLoad {
    [super viewDidLoad];
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, STATUSBARHEIGHT, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = @"账号管理";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5, 25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    UIButton *btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33,25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
    
    
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-64)];
    scrollView.contentSize = CGSizeMake(SCREENWIDTH, FULLSCREENHEIGHT);
    [self.view addSubview:scrollView];
    
    //////////////////////////////////////////////////////////////////////
    UIView *viewAccount = [[UIView alloc]initWithFrame:CGRectMake(10, 10, SCREENWIDTH-20, 200)];
    viewAccount.backgroundColor = [UIColor colorWithRed:246/255.0 green:250/255.0 blue:246/255.0 alpha:1.0];
    [scrollView addSubview:viewAccount];
    
    userHeadImage = [[UIImageView alloc]initWithFrame:CGRectMake(5, 5, 60, 60)];
    userHeadImage.layer.masksToBounds = YES;
    userHeadImage.layer.cornerRadius = 2.0;
    userHeadImage.image = [UIImage imageNamed:@"userHeadImage.png"];
    [viewAccount addSubview:userHeadImage];
    
    userName = [[UITextField alloc]initWithFrame:CGRectMake(userHeadImage.frame.origin.x+userHeadImage.frame.size.width+15, userHeadImage.frame.origin.y+userHeadImage.frame.size.height/2-15, viewAccount.frame.size.width-userHeadImage.frame.origin.x-userHeadImage.frame.size.width-20, 30)];
    userName.font = [UIFont systemFontOfSize:15.0];
    userName.returnKeyType = UIReturnKeyDone;
    userName.placeholder = @"用户昵称";
    userName.borderStyle = UITextBorderStyleRoundedRect;
    [viewAccount addSubview:userName];

    
    UILabel *labelPhoneLeft = [[UILabel alloc]initWithFrame:CGRectMake(userHeadImage.frame.origin.x, userHeadImage.frame.origin.y+userHeadImage.frame.size.height+10, 70, 30)];
    labelPhoneLeft.backgroundColor = [UIColor clearColor];
    labelPhoneLeft.font = [UIFont systemFontOfSize:15.0];
    labelPhoneLeft.textColor = [UIColor blackColor];
    labelPhoneLeft.text = @"手机号:";
    [viewAccount addSubview:labelPhoneLeft];
    labelphone = [[UILabel alloc]initWithFrame:CGRectMake(labelPhoneLeft.frame.origin.x+labelPhoneLeft.frame.size.width+5, labelPhoneLeft.frame.origin.y, viewAccount.frame.size.width-labelPhoneLeft.frame.origin.x-labelPhoneLeft.frame.size.width-10, 30)];
    labelphone.backgroundColor = [UIColor clearColor];
    labelphone.font = [UIFont systemFontOfSize:15.0];
    labelphone.textColor = [UIColor lightGrayColor];
    labelphone.text = @"";
    [viewAccount addSubview:labelphone];
    
    UILabel *labelEmailLeft = [[UILabel alloc]initWithFrame:CGRectMake(labelPhoneLeft.frame.origin.x, labelPhoneLeft.frame.origin.y+labelPhoneLeft.frame.size.height, 70, 30)];
    labelEmailLeft.backgroundColor = [UIColor clearColor];
    labelEmailLeft.font = [UIFont systemFontOfSize:15.0];
    labelEmailLeft.textColor = [UIColor blackColor];
    labelEmailLeft.text = @"电子邮箱:";
    [viewAccount addSubview:labelEmailLeft];
    fieldemail = [[UITextField alloc]initWithFrame:CGRectMake(labelEmailLeft.frame.origin.x+labelEmailLeft.frame.size.width+5, labelEmailLeft.frame.origin.y, viewAccount.frame.size.width-labelEmailLeft.frame.origin.x-labelEmailLeft.frame.size.width-10, 30)];
    fieldemail.font = [UIFont systemFontOfSize:15.0];
    fieldemail.returnKeyType = UIReturnKeyDone;
    fieldemail.placeholder = @"电子邮箱";
    fieldemail.borderStyle = UITextBorderStyleRoundedRect;
    [viewAccount addSubview:fieldemail];
    
    changeAccountBtn = [[UIButton alloc]initWithFrame:CGRectMake(labelEmailLeft.frame.origin.x, labelEmailLeft.frame.origin.y+labelEmailLeft.frame.size.height+10,viewAccount.frame.size.width-2*labelEmailLeft.frame.origin.x,40)];
    changeAccountBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    changeAccountBtn.layer.masksToBounds = YES;
    changeAccountBtn.layer.cornerRadius = 5.0;
    [changeAccountBtn setTitle:@"修改账号信息" forState:UIControlStateNormal];
    [changeAccountBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [changeAccountBtn addTarget:self action:@selector(changeAccountClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewAccount addSubview:changeAccountBtn];
    
    ///////////////////////////////////////////////////////////////////////
    UIView *viewPassword = [[UIView alloc]initWithFrame:CGRectMake(viewAccount.frame.origin.x, viewAccount.frame.origin.x+viewAccount.frame.size.height+10, viewAccount.frame.size.width, 180)];
    viewPassword.backgroundColor = [UIColor colorWithRed:246/255.0 green:250/255.0 blue:246/255.0 alpha:1.0];
    [scrollView addSubview:viewPassword];

    UILabel *labelpsdLeft = [[UILabel alloc]initWithFrame:CGRectMake(5, 5, 70, 30)];
    labelpsdLeft.backgroundColor = [UIColor clearColor];
    labelpsdLeft.font = [UIFont systemFontOfSize:15.0];
    labelpsdLeft.textColor = [UIColor blackColor];
    labelpsdLeft.text = @"旧密码:";
    [viewPassword addSubview:labelpsdLeft];
    oldPsdText = [[UITextField alloc]initWithFrame:CGRectMake(labelpsdLeft.frame.origin.x+labelpsdLeft.frame.size.width+5, labelpsdLeft.frame.origin.y, viewPassword.frame.size.width-labelpsdLeft.frame.origin.x-labelpsdLeft.frame.size.width-10, 30)];
    oldPsdText.font = [UIFont systemFontOfSize:15.0];
    oldPsdText.returnKeyType = UIReturnKeyDone;
    oldPsdText.placeholder = @"输入旧密码";
    oldPsdText.borderStyle = UITextBorderStyleRoundedRect;
    [viewPassword addSubview:oldPsdText];
    
    
    UILabel *labelnPsdTextLeft = [[UILabel alloc]initWithFrame:CGRectMake(labelpsdLeft.frame.origin.x, labelpsdLeft.frame.origin.y+labelpsdLeft.frame.size.height+10, 70, 30)];
    labelnPsdTextLeft.backgroundColor = [UIColor clearColor];
    labelnPsdTextLeft.font = [UIFont systemFontOfSize:15.0];
    labelnPsdTextLeft.textColor = [UIColor blackColor];
    labelnPsdTextLeft.text = @"新密码:";
    [viewPassword addSubview:labelnPsdTextLeft];
    nPsdText = [[UITextField alloc]initWithFrame:CGRectMake(labelnPsdTextLeft.frame.origin.x+labelnPsdTextLeft.frame.size.width+5, labelnPsdTextLeft.frame.origin.y, viewPassword.frame.size.width-labelnPsdTextLeft.frame.origin.x-labelnPsdTextLeft.frame.size.width-10, 30)];
    nPsdText.font = [UIFont systemFontOfSize:15.0];
    nPsdText.returnKeyType = UIReturnKeyDone;
    nPsdText.placeholder = @"输入新密码";
    nPsdText.borderStyle = UITextBorderStyleRoundedRect;
    [viewPassword addSubview:nPsdText];
    
    UILabel *labelrepeatPsdTextLeft = [[UILabel alloc]initWithFrame:CGRectMake(labelnPsdTextLeft.frame.origin.x, labelnPsdTextLeft.frame.origin.y+labelnPsdTextLeft.frame.size.height+10, 70, 30)];
    labelrepeatPsdTextLeft.backgroundColor = [UIColor clearColor];
    labelrepeatPsdTextLeft.font = [UIFont systemFontOfSize:15.0];
    labelrepeatPsdTextLeft.textColor = [UIColor blackColor];
    labelrepeatPsdTextLeft.text = @"确认密码:";
    [viewPassword addSubview:labelrepeatPsdTextLeft];
    repeatPsdText = [[UITextField alloc]initWithFrame:CGRectMake(labelrepeatPsdTextLeft.frame.origin.x+labelrepeatPsdTextLeft.frame.size.width+5, labelrepeatPsdTextLeft.frame.origin.y, viewPassword.frame.size.width-labelrepeatPsdTextLeft.frame.origin.x-labelrepeatPsdTextLeft.frame.size.width-10, 30)];
    repeatPsdText.font = [UIFont systemFontOfSize:15.0];
    repeatPsdText.returnKeyType = UIReturnKeyDone;
    repeatPsdText.placeholder = @"再次输入新密码";
    repeatPsdText.borderStyle = UITextBorderStyleRoundedRect;
    [viewPassword addSubview:repeatPsdText];
    
    
    changePsdBtn = [[UIButton alloc]initWithFrame:CGRectMake(labelrepeatPsdTextLeft.frame.origin.x, labelrepeatPsdTextLeft.frame.origin.y+labelrepeatPsdTextLeft.frame.size.height+10,viewPassword.frame.size.width-2*labelrepeatPsdTextLeft.frame.origin.x,40)];
    changePsdBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    changePsdBtn.layer.masksToBounds = YES;
    changePsdBtn.layer.cornerRadius = 5.0;
    [changePsdBtn setTitle:@"修改密码" forState:UIControlStateNormal];
    [changePsdBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [changePsdBtn addTarget:self action:@selector(changePsdBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewPassword addSubview:changePsdBtn];
    
    
    
    [userName addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [fieldemail addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [oldPsdText addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [nPsdText addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    [repeatPsdText addTarget:self action:@selector(textViewEditDone) forControlEvents:UIControlEventEditingDidEndOnExit];
    
    [oldPsdText addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    [nPsdText addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];
    [repeatPsdText addTarget:self action:@selector(textViewEditBegin) forControlEvents:UIControlEventEditingDidBegin];

    
    HUD = [[MBProgressHUD alloc]initWithView:scrollView];
    [scrollView addSubview:HUD];
    // Do any additional setup after loading the view.
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString *nametmp = [userDefaults objectForKey:@"USERNAME"];
    if(nametmp != nil){
        [userName setText:nametmp];
    }
    NSString *emailtemp = [userDefaults objectForKey:@"EMAIL"];
    if(emailtemp != nil){
        [fieldemail setText:emailtemp];
    }
    
    NSString *phoneStr = [userDefaults objectForKey:@"PHONE"];
    [labelphone setText:phoneStr];

    [oldPsdText setSecureTextEntry:YES];
    [nPsdText setSecureTextEntry:YES];
    [repeatPsdText setSecureTextEntry:YES];
    
    [userHeadImage setUserInteractionEnabled:YES];
    UITapGestureRecognizer *singleTap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClickHeadImage)];
    [userHeadImage addGestureRecognizer:singleTap];
    
    if ([[NSFileManager defaultManager]fileExistsAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"head.jpg"]]) {
        [[NSFileManager defaultManager] removeItemAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"head.jpg"] error:nil];
    }
    if ([[NSFileManager defaultManager]fileExistsAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"]]) {
        [[NSFileManager defaultManager] removeItemAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"] error:nil];
    }
}
-(void)ReloadData{
    userInfo = nil;
    [self getUserData];
}
-(void)ClickLeftMenu{
    [self presentLeftMenuViewController:nil];
}
-(void)ClickRightMenu{
    [self presentRightMenuViewController:nil];
}
-(void)textViewEditBegin{
    [scrollView setContentOffset:CGPointMake(0, 200) animated:YES];
}
-(void)textViewEditDone{
    [scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    [userName resignFirstResponder];
    [fieldemail resignFirstResponder];
    [oldPsdText resignFirstResponder];
    [nPsdText resignFirstResponder];
    [repeatPsdText resignFirstResponder];
}
-(void)onClickHeadImage{
    [self textViewEditDone];
    NSLog(@"图片被点击!");
    UIActionSheet *actionSheet = [[UIActionSheet alloc]initWithTitle:@"选择一张图片作为头像" delegate:self  cancelButtonTitle:@"取消" destructiveButtonTitle:@"立即拍照上传" otherButtonTitles:@"从手机相册选取",nil];
    actionSheet.actionSheetStyle = UIActionSheetStyleAutomatic;
    [actionSheet showInView:self.view];
    
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

- (IBAction)changeAccountClick:(id)sender {
    [self textViewEditDone];
    
    name = userName.text;
    if ([name length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请填写完整用户信息" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    email = fieldemail.text;
    if (email.length) {
        if (![StringUtils isValidateEmail:email]) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"邮箱格式不正确" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
            return;
        }
    }
    NSString *phoneNum = labelphone.text;
    
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_01_02]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.timeOutSeconds = 20;
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
    [request setPostValue:phoneNum forKey:@"PHONE"];
    [request setPostValue:name forKey:@"USERNAME"];
    [request setPostValue:email forKey:@"EMAIL"];
    if ([[NSFileManager defaultManager]fileExistsAtPath:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"]]) {
        strimagePath = [PATH_OF_DOCUMENT stringByAppendingPathComponent:@"temp.jpg"];
        [request setFile:strimagePath forKey:@"file"];
    }
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc] initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            //将上述数据全部存储到NSUserDefaults中
            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
            //存储时，除NSNumber类型使用对应的类型意外，其他的都是使用setObject:forKey:
            self.userInfo.ma017 = strimagePath;
            [userDefaults setObject:name forKey:@"USERNAME"];
            [userDefaults setObject:email forKey:@"EMAIL"];
            [userDefaults synchronize];
            
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:result.message delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
            
        }
        else{
            NSString *errorMsg = result.message;
            NSLog(@"Error: %@", errorMsg);
            
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:result.message delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        if (error) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    [request startAsynchronous];
    [HUD show:YES];
}

- (IBAction)changePsdBtnClick:(id)sender {
    [self textViewEditDone];
    [self changePassword];
}

-(void)changePassword{
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
    NSString *oldPassword = [oldPsdText text];
    if ([oldPassword length] == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"请输入老密码" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    Gaipassword = [nPsdText text];
    if (Gaipassword.length == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"信息不能为空" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    if ([Gaipassword length] < 6) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"新密码输入不合法" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    NSString *psdConfirm = [repeatPsdText text];
    if (psdConfirm.length == 0) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"信息不能为空" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    if (![Gaipassword isEqualToString:psdConfirm]) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"密码不一致" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_02_03]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:oldPassword forKey:@"oldpass"];
    [request setPostValue:Gaipassword forKey:@"MA009"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc]initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            if (result.dataObject != [NSNull null]) {
                self.userInfo.ma010 = [result.dataObject objectForKey:@"ma010"];
                NSString *sessionId = [result.dataObject objectForKey:@"ma010"];
                //将上述数据全部存储到NSUserDefaults中
                NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
                //存储时，除NSNumber类型使用对应的类型意外，其他的都是使用setObject:forKey:
                [userDefaults removeObjectForKey:@"SESSIONID"];
                [userDefaults removeObjectForKey:@"PASSWORD"];
                [userDefaults synchronize];
                
                [userDefaults setObject:sessionId forKey:@"SESSIONID"];
                [userDefaults setObject:Gaipassword forKey:@"PASSWORD"];
                
                [repeatPsdText setText:@""];
                [oldPsdText setText:@""];
                [nPsdText setText:@""];
                UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:result.message delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
                [alertView show];
            }
        }
        else{
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:result.message delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        if (error) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    
    [request startAsynchronous];
    [HUD show:YES];
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
//图片照相
- (void)toCameraPickingController
{
    if (![UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
        NSLog(@"Error:没有照相设备");
    }
    else {
        UIImagePickerController *cameraPicker = [[UIImagePickerController alloc] init];
        cameraPicker.delegate = self;
        cameraPicker.allowsEditing = YES;
        cameraPicker.sourceType = UIImagePickerControllerSourceTypeCamera;
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
        photoPicker.allowsEditing = YES;
        photoPicker.delegate = self;
        photoPicker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        [self presentViewController:photoPicker animated:YES completion:nil];
    }
}

-(void)getUserData{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString *phoneStr = [userDefaults objectForKey:@"PHONE"];
    NSString *password = [userDefaults objectForKey:@"PASSWORD"];
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_03_02_04]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    [request setPostValue:phoneStr forKey:@"PHONE"];
    [request setPostValue:password forKey:@"PASSWORD"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc]initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            NSMutableDictionary *s = [[NSMutableDictionary alloc]initWithDictionary:result.dataObject];
            NSMutableArray *maryImage = [[NSMutableArray alloc]init];
            [maryImage addObjectsFromArray:[s objectForKey:@"ma017"]];
            if (maryImage.count != 0) {
                unsigned c = maryImage.count;
                uint8_t *bytes = malloc(sizeof(*bytes) * c);
                unsigned i;
                for (i = 0; i < c; i++)
                {
                    NSString *str = [maryImage objectAtIndex:i];
                    int byte = [str intValue];
                    bytes[i] = (uint8_t)byte;
                }
                NSData* dataImage = [NSData dataWithBytes:bytes length:sizeof(unsigned char)*c];
                
                [dataImage writeToFile:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"head.jpg"] atomically:NO];
                [s setObject:[PATH_OF_DOCUMENT stringByAppendingPathComponent:@"head.jpg"] forKey:@"ma017"];
            }
            else{
                [s setObject:@"" forKey:@"ma017"];
            }
            [self setUserInfoData:s];
        }
    }];
    
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        if (error) {
            UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"网络连接失败" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
            [alertView show];
        }
    }];
    [request startAsynchronous];
    [HUD show:YES];
}

-(void)setUserInfoData:(NSDictionary *)info{
    //[SVProgressHUD showWithStatus:@"加载中" maskType:SVProgressHUDMaskTypeBlack];
    self.userInfo = [[User alloc] initWithDictionary:info];
    NSString *userId = userInfo.ma001;
    NSString *sessionId = userInfo.ma010;
    NSString *emailStr = userInfo.ma005;
    NSString *userNameStr = userInfo.ma008;
    NSString *phoneStr = userInfo.ma006;

    //将上述数据全部存储到NSUserDefaults中
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    //存储时，除NSNumber类型使用对应的类型意外，其他的都是使用setObject:forKey:
    [userDefaults removeObjectForKey:@"USERID"];
    [userDefaults removeObjectForKey:@"SESSIONID"];
    [userDefaults removeObjectForKey:@"EMAIL"];
    [userDefaults removeObjectForKey:@"USERNAME"];
    [userDefaults removeObjectForKey:@"PHONE"];
    [userDefaults synchronize];
    
    [userDefaults setObject:userId forKey:@"USERID"];
    [userDefaults setObject:sessionId forKey:@"SESSIONID"];
    [userDefaults setObject:emailStr forKey:@"EMAIL"];
    [userDefaults setObject:userNameStr forKey:@"USERNAME"];
    [userDefaults setObject:phoneStr forKey:@"PHONE"];
    
    if(userNameStr.length != 0){
        [userName setText:userNameStr];
    }
    if(emailStr.length != 0){
        [fieldemail setText:emailStr];
    }
    if (phoneStr.length != 0){
        [labelphone setText:phoneStr];
    }
    if ([[NSFileManager defaultManager] fileExistsAtPath:self.userInfo.ma017]) {
        userHeadImage.image = [UIImage imageWithContentsOfFile:self.userInfo.ma017];
    }
    else{
        userHeadImage.image = [UIImage imageNamed:@"userHeadImage.png"];
    }
    //[SVProgressHUD dismiss];
}

-(void)viewDidDisappear:(BOOL)animated{
    [self textViewEditDone];
}
@end
