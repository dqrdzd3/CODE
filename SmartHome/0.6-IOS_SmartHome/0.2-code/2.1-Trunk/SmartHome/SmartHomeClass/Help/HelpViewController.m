//
//  HelpViewController.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-22.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "HelpViewController.h"

@implementation HelpViewController
@synthesize mUrl = _mUrl;

- (void)viewDidLoad {
    [super viewDidLoad];
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 64)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, 20, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = @"帮助中心";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5, 25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    UIButton *btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33, 25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
    
    _helpWebview = [[UIWebView alloc]initWithFrame:CGRectMake(0, 64, SCREENWIDTH, FULLSCREENHEIGHT-64)];
    [self.view addSubview:_helpWebview];
    // Do any additional setup after loading the view.
    NSString *fullURL = [SERVER_BASE_URI stringByAppendingString:SH01_05_02_02];
    NSURL *url = [NSURL URLWithString:fullURL];
    if (_mUrl != nil) {
        url = [NSURL URLWithString:_mUrl];
    }
    
    NSLog(@"UTL%@",fullURL);
    NSURLRequest *requestObj = [NSURLRequest requestWithURL:url];
    [self.helpWebview loadRequest:requestObj];
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

@end
