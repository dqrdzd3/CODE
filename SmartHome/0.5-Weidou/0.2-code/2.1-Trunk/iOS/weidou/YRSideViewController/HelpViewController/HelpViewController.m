//
//  HelpViewController.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "HelpViewController.h"

@interface HelpViewController ()

@end

@implementation HelpViewController
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
    labelTitle.text = @"帮助中心";
    [viewTop addSubview:labelTitle];
    
    btnLeft = [[UIButton alloc]initWithFrame:CGRectMake(5, STATUSBARHEIGHT+10.5, 28, 23)];
    [btnLeft setImage:[UIImage imageNamed:@"title_bar_menu.png"] forState:UIControlStateNormal];
    [btnLeft addTarget:self action:@selector(showMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeft];
    
    webview = [[UIWebView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight)];
    webview.scalesPageToFit = YES;
    [webview loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:@"http://weiguo.hanwei.cn/smart/hwmobile/smart/help"]]];
    [self.view addSubview:webview];
}
- (void)showMenu{
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
