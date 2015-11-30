//
//  AboutViewController.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-22.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "AboutViewController.h"
@implementation AboutViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 64)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, 20, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = @"关于";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5, 25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    

    UIButton *btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33, 25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
    NSLog(@"%@",[[NSBundle mainBundle] infoDictionary] );
    
    
    imageLogo = [[UIImageView alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-21.5, 70, 53, 61)];
    imageLogo.image = [UIImage imageNamed:@"aboutLogoImage.png"];
    [self.view addSubview:imageLogo];
    
    imageTextLogo = [[UIImageView alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-57, imageLogo.frame.origin.y+imageLogo.frame.size.height+30, 114, 48)];
    imageTextLogo.image = [UIImage imageNamed:@"aboutLogoText.png"];
    [self.view addSubview:imageTextLogo];
    
    labelVer = [[UILabel alloc]initWithFrame:CGRectMake(0, imageTextLogo.frame.origin.y+imageTextLogo.frame.size.height+5, SCREENWIDTH, 20)];
    labelVer.backgroundColor = [UIColor clearColor];
    labelVer.font = [UIFont systemFontOfSize:14.0];
    labelVer.textAlignment = NSTextAlignmentCenter;
    labelVer.textColor = [UIColor blackColor];
    labelVer.text = [NSString stringWithFormat:@"版本 %@",[[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleShortVersionString"]];
    [self.view addSubview:labelVer];
    
    
    btnUpdate = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-50, labelVer.frame.origin.y+labelVer.frame.size.height+20, 100, 30)];
    [btnUpdate setTitleColor:[UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0] forState:UIControlStateNormal];
    [btnUpdate setTitle:@"检查更新" forState:UIControlStateNormal];
    [btnUpdate addTarget:self action:@selector(checkVersionBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    //[self.view addSubview:btnUpdate];
    
    labelComp = [[UILabel alloc]initWithFrame:CGRectMake(0, FULLSCREENHEIGHT-25, SCREENWIDTH, 20)];
    labelComp.backgroundColor = [UIColor clearColor];
    labelComp.font = [UIFont systemFontOfSize:12.0];
    labelComp.textAlignment = NSTextAlignmentCenter;
    labelComp.textColor = [UIColor blackColor];
    labelComp.text = [NSString stringWithFormat:@"北京威果智能科技股份有限公司"];
    [self.view addSubview:labelComp];
    
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
}
-(void)ClickLeftMenu{
    [self presentLeftMenuViewController:nil];
}
-(void)ClickRightMenu{
    [self presentRightMenuViewController:nil];
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

- (IBAction)checkVersionBtnClick:(id)sender {
    [self onCheckVersion];
}

-(void)onCheckVersion
{
    NSURL *url = [NSURL URLWithString:@"http://itunes.apple.com/cn/lookup"];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    [request setPostValue:APP_ID forKey:@"id"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
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
            else
            {
                UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"此版本为最新版本" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                alert.tag = 10001;
                [alert show];
            }
        }
        else{
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"" message:@"获取不到版本信息" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
            alert.tag = 10002;
            [alert show];
        }
    }];
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        if (error) {
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view animated:YES];
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

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (alertView.tag == 10000) {
        if (buttonIndex == 1) {
            NSURL *url = [NSURL URLWithString:[trackViewURL stringByReplacingOccurrencesOfString:@"https" withString:@"itms-apps"]];
            [[UIApplication sharedApplication]openURL:url];
        }
    }
}
@end
