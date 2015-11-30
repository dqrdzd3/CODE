//
//  AdviceViewController.m
//  Weidou
//
//  Created by wbiao on 14/12/10.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "AdviceViewController.h"

@implementation AdviceViewController
@synthesize viewTop;
@synthesize labelTitle;
@synthesize btnLeft;

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
    labelTitle.text = @"改善建议";
    [viewTop addSubview:labelTitle];
    
    btnLeft = [[UIButton alloc]initWithFrame:CGRectMake(5, STATUSBARHEIGHT, 44, 44)];
    [btnLeft setTitle:@"返回" forState:UIControlStateNormal];
    [btnLeft setTitleColor:[UIColor colorWithRed:20.0/255 green:170.0/255 blue:255.0/255 alpha:1.0] forState:UIControlStateNormal];
    btnLeft.titleLabel.font = [UIFont systemFontOfSize:14];
    [btnLeft addTarget:self action:@selector(back) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeft];
    
    //存储历史数据分享用
    NSDate *date = [NSDate date];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"MM"];
    
    NSString *strM = [dateFormatter stringFromDate:date];
    NSString *strState = @"";
    
    NSString *strType = @"";
    float fstateValue = 0.0;
    if([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_ALCOHOL]){
        strType = @"7";
        fstateValue = ALCOHOL_L;
    }
    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CO]){
        strType = @"8";
        fstateValue = CO_35;
    }
    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CH2O]){
        strType = @"6";
        fstateValue = CH2O_NORMAL_VALUE;
    }
    
    //GASSTATE 1正常 2危险 6
    if ([[RealTimeModel SharedRealTimeModel].strPieValue floatValue] <= fstateValue) {
        strState = @"1";
    }
    else{
        strState = @"2";
    }
    NSString *strUrl = [NSString stringWithFormat:@"http://weiguo.hanwei.cn/smart/hwmobile/smart/s007!getSolution?GASTYPE=[%@]&GASSTATE=[%@]&TIMEINUSE=%@",strType,strState,strM];
    webview = [[UIWebView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight)];
    webview.scalesPageToFit = YES;
    webview.delegate = self;
    [webview loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:strUrl]]];
    [self.view addSubview:webview];
    
    
    activity = [[UIActivityIndicatorView alloc] initWithFrame:CGRectMake(0, 0, 20, 20)];//指定进度轮的大小
    [activity setCenter:CGPointMake(SCREENWIDTH/2-10, (FULLSCREENHEIGHT-TopBarHeight)/2-10)];//指定进度轮中心点
    [activity setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleGray];//设置进度轮显示类型
    [self.view addSubview:activity];
}
-(BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType{
    [activity startAnimating];
    return YES;
}
-(void)webViewDidFinishLoad:(UIWebView *)webView{
    [activity stopAnimating];
}
-(void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error{
    [activity stopAnimating];
}
-(void)back{
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
