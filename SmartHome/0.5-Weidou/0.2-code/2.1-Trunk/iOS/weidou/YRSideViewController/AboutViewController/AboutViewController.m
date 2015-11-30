//
//  AboutViewController.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "AboutViewController.h"
#import "ASIFormDataRequest.h"
@implementation AboutViewController
@synthesize viewTop;
@synthesize labelTitle;
@synthesize btnLeft;
@synthesize btnCheckUpdate;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-50, STATUSBARHEIGHT, 100, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"关于";
    [viewTop addSubview:labelTitle];
    
    btnLeft = [[UIButton alloc]initWithFrame:CGRectMake(5, STATUSBARHEIGHT+10.5, 28, 23)];
    [btnLeft setImage:[UIImage imageNamed:@"title_bar_menu.png"] forState:UIControlStateNormal];
    [btnLeft addTarget:self action:@selector(showMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeft];
    
    UIImageView *imageLogo = [[UIImageView alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-31, 84, 62, 72)];
    imageLogo.image = [UIImage imageNamed:@"about_logo_img.png"];
    [self.view addSubview:imageLogo];
    
    UIImageView *imageLogoText = [[UIImageView alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-67.5, 84+72+10, 135, 51)];
    imageLogoText.image = [UIImage imageNamed:@"about_logo_text.png"];
    [self.view addSubview:imageLogoText];
    
    UILabel *labelVersion = [[UILabel alloc]initWithFrame:CGRectMake(0, FULLSCREENHEIGHT/2, SCREENWIDTH, 20)];
    labelVersion.font = [UIFont boldSystemFontOfSize:12.0];
    labelVersion.textColor = [UIColor grayColor];
    labelVersion.textAlignment = NSTextAlignmentCenter;
    labelVersion.backgroundColor = [UIColor clearColor];
    labelVersion.text = [NSString stringWithFormat:@"版本号:%@",[[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"]];
    [self.view addSubview:labelVersion];
    
    currentVersion = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"];
    
    btnCheckUpdate = [[UIButton alloc]initWithFrame:CGRectMake(50, labelVersion.frame.origin.y+labelVersion.frame.size.height+5, SCREENWIDTH-100, 40)];
    [btnCheckUpdate setTitle:@"检查更新" forState:UIControlStateNormal];
    [btnCheckUpdate setTitleColor:[UIColor colorWithRed:20.0/255 green:170.0/255 blue:255.0/255 alpha:1.0] forState:UIControlStateNormal];
    [btnCheckUpdate addTarget:self action:@selector(onCheckVersion) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:btnCheckUpdate];
    
    
    UILabel *labelCright = [[UILabel alloc]initWithFrame:CGRectMake(5, FULLSCREENHEIGHT-25, SCREENWIDTH-10, 20)];
    labelCright.font = [UIFont boldSystemFontOfSize:12.0];
    labelCright.textColor = [UIColor lightGrayColor];
    labelCright.textAlignment = NSTextAlignmentCenter;
    labelCright.backgroundColor = [UIColor clearColor];
    labelCright.text = @"北京威果智能科技有限公司";
    [self.view addSubview:labelCright];
    
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
}

-(void)onCheckVersion
{
    HUD.labelText = @"检查更新";
    [HUD show:YES];
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
    [HUD hide:YES];
    NSString *responseString = [request responseString];
    NSDictionary *result = [responseString  objectFromJSONString];
    
    NSArray *infoArray = [result objectForKey:@"results"];
    if ([infoArray count]) {
        NSDictionary *releaseInfo = [infoArray objectAtIndex:0];
        NSString *lastVersion = [releaseInfo objectForKey:@"version"];
        
        if (![lastVersion isEqualToString:currentVersion]) {
            trackViewURL = [releaseInfo objectForKey:@"trackViewUrl"];
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"有新的版本，是否前往更新？" delegate:self cancelButtonTitle:@"关闭" otherButtonTitles:@"更新", nil];
            alert.tag = 10000;
            [alert show];
        }
        else
        {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"此版本为最新版本" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
            alert.tag = 10001;
            [alert show];
        }
    }else{
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"获取不到版本信息" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        alert.tag = 10002;
        [alert show];
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

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (alertView.tag == 10000) {
        if (buttonIndex == 1) {
            NSURL *url = [NSURL URLWithString:[trackViewURL stringByReplacingOccurrencesOfString:@"https" withString:@"itms-apps"]];
            [[UIApplication sharedApplication]openURL:url];
        }
    }
}

-(void)showMenu{
    [self presentLeftMenuViewController:nil];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (BOOL)shouldAutorotate
{
    return YES;
}
- (NSUInteger)supportedInterfaceOrientations
{
    return UIInterfaceOrientationMaskPortrait | UIInterfaceOrientationMaskPortraitUpsideDown;
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
