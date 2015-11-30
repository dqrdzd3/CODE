//
//  GuideViewController.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-20.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "GuideViewController.h"
#import "LoginViewController.h"
#import "UIButton+Bootstrap.h"

@implementation GuideViewController

@synthesize photoList = _photoList;
@synthesize pageScroll = _pageScroll;

- (void)viewDidLoad {
    [super viewDidLoad];
    _pageScroll = [[UIScrollView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, FULLSCREENHEIGHT)];
    _pageScroll.contentSize = CGSizeMake(3*SCREENWIDTH, FULLSCREENHEIGHT);
    _pageScroll.pagingEnabled = YES;
    _pageScroll.delegate = self;
    _pageScroll.showsHorizontalScrollIndicator = NO;
    [self.view addSubview:_pageScroll];

    
    _gotoNextBtn = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-50, FULLSCREENHEIGHT-80,100,30)];
    _gotoNextBtn.backgroundColor = [UIColor colorWithRed:91/255.0 green:192/255.0 blue:222/255.0 alpha:1.0];
    _gotoNextBtn.layer.masksToBounds = YES;
    _gotoNextBtn.layer.cornerRadius = 2.0;
    [_gotoNextBtn setTitle:@"立即使用" forState:UIControlStateNormal];
    [_gotoNextBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    [_gotoNextBtn addTarget:self action:@selector(clickBtn:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:_gotoNextBtn];
    [_gotoNextBtn setHidden:YES];
    
    // Do any additional setup after loading the view.
    _photoList = [[NSArray alloc] initWithObjects:
                      [UIImage imageNamed:@"welcome1.png"],
                      [UIImage imageNamed:@"welcome2.png"],
                      [UIImage imageNamed:@"welcome3.png"],
                      [self getEmptyUIImage],nil];
    NSInteger pageCount = [_photoList count];

    for(NSInteger i = 0; i < pageCount;i++)
    {
        UIImageView *pageView = [[UIImageView alloc] initWithFrame:CGRectMake(i*SCREENWIDTH, 0, SCREENWIDTH, FULLSCREENHEIGHT)];
        pageView.image = [_photoList objectAtIndex:i];
        pageView.contentMode = UIViewContentModeScaleAspectFill;
        [_pageScroll addSubview:pageView];
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    CGFloat pageWidth = _pageScroll.frame.size.width;
    // 在滚动超过页面宽度的50%的时候，切换到新的页面
    int page = floor((_pageScroll.contentOffset.x + pageWidth/2)/pageWidth) ;
    [_gotoNextBtn setHidden:page<2];
    
   // 这里可以判断是否跳转到主页
    if (_pageScroll.contentOffset.x > (2*SCREENWIDTH+SCREENWIDTH/4))
    {
        [self dismissViewControllerAnimated:NO completion:^{
            AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
            [appDelegate ShowLoginView];
        }];
    }
}

-(UIImage *)getEmptyUIImage
{
    UIGraphicsBeginImageContextWithOptions(CGSizeMake(_pageScroll.frame.size.width,
                                                      _pageScroll.frame.size.height), NO, 0.0);
    UIImage *blank = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return blank;
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)clickBtn:(id)sender {
    [self dismissViewControllerAnimated:NO completion:^{
        AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
        [appDelegate ShowLoginView];
    }];
}
@end
