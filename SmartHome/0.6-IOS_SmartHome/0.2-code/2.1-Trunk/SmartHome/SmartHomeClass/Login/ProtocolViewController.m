//
//  ProtocolViewController.m
//  SmartHome
//
//  Created by 李静 on 14-11-7.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "ProtocolViewController.h"
#import "RegisterViewController.h"
@implementation ProtocolViewController
- (void)viewDidLoad {
    [super viewDidLoad];
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, STATUSBARHEIGHT, SCREENWIDTH-100, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"空气电台协议";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnBack = [[UIButton alloc]initWithFrame:CGRectMake(0, STATUSBARHEIGHT, 50, 44)];
    [btnBack setTitle:@"返回" forState:UIControlStateNormal];
    [btnBack setTitleColor:[UIColor colorWithRed:45.0/255 green:119.0/255 blue:248.0/255 alpha:1.0] forState:UIControlStateNormal];
    [btnBack addTarget:self action:@selector(backBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnBack];
    
    
    // Do any additional setup after loading the view.
    _mWebView = [[UIWebView alloc]initWithFrame:CGRectMake(0, 64, SCREENWIDTH, FULLSCREENHEIGHT-64)];
    
    [self.view addSubview:_mWebView];
    if (_mUrl != nil) {
      NSURL *url = [NSURL URLWithString:_mUrl];
      NSURLRequest *requestObj = [NSURLRequest requestWithURL:url];
        
     [_mWebView loadRequest:requestObj];
    }
    _mWebView.delegate = self;
    HUD = [[MBProgressHUD alloc]initWithView:_mWebView];
    [_mWebView addSubview:HUD];
}

-(void)webViewDidStartLoad:(UIWebView *)webView{
    [HUD show:YES];
}
-(void)webViewDidFinishLoad:(UIWebView *)webView{
    [HUD hide:YES];
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

- (IBAction)backBtnClick:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}
@end
