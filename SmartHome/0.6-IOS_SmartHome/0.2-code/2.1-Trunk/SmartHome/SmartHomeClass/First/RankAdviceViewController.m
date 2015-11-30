//
//  RankAdviceViewController.m
//  SmartHome
//
//  Created by apple on 14-11-17.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "RankAdviceViewController.h"

@implementation RankAdviceViewController
@synthesize strGasstate;
@synthesize strGastype;
@synthesize webView;

- (void)viewDidLoad {
    [super viewDidLoad];
    NSLog(@"%f",[UIScreen mainScreen].bounds.size.height);
    
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 64)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle         = [[UILabel alloc]initWithFrame:CGRectMake(50, 20, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor  = [UIColor clearColor];
    labelTitle.textAlignment    = NSTextAlignmentCenter;
    labelTitle.textColor        = [UIColor blackColor];
    labelTitle.font             = [UIFont systemFontOfSize:18];
    labelTitle.text             = @"改善建议";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu       = [[UIButton alloc]initWithFrame:CGRectMake(5, 20, 44, 44)];
    [btnLeftMenu setTitle:@"返回" forState:UIControlStateNormal];
    [btnLeftMenu setTitleColor:Color_Blue forState:UIControlStateNormal];
    btnLeftMenu.titleLabel.font = [UIFont systemFontOfSize:14.0];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    
    webView = [[UIWebView alloc]initWithFrame:CGRectMake(0, viewTop.frame.size.height, SCREENWIDTH, FULLSCREENHEIGHT-viewTop.frame.size.height)];
    webView.scalesPageToFit = YES;
    [self.view addSubview:webView];
    // Do any additional setup after loading the view from its nib.
    [self GetAdviceData];
}

-(void)GetAdviceData{
    NSString *url = [SERVER_BASE_URI stringByAppendingString:SH01_01_07];
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
    
    NSDateFormatter* dateFormat = [[NSDateFormatter alloc] init];
    [dateFormat setDateFormat:@"MM"];
    NSString* timeInuse = [dateFormat stringFromDate:[NSDate date]];
    
    NSString *requestUrl = [NSString stringWithFormat:@"%@?SESSIONID=%@&USERID=%@&GASSTATE=%@&GASTYPE=%@&TIMEINUSE=%@",url,sessionId,userId,strGasstate,strGastype,timeInuse];
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:requestUrl]];
    [webView loadRequest:request];
    webView.delegate = self;

    HUD = [[MBProgressHUD alloc]initWithView:webView];
    [webView addSubview:HUD];
}
-(void)webViewDidStartLoad:(UIWebView *)webView{
    [HUD show:YES];
}
-(void)webViewDidFinishLoad:(UIWebView *)webView{
    [HUD hide:YES];
}
-(void)ClickLeftMenu{
    [self.navigationController popViewControllerAnimated:YES];
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

@end
