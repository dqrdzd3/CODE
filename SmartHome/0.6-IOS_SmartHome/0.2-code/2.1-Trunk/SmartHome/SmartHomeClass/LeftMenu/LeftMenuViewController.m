//
//  LeftMenuViewController.m
//  Weidou
//
//  Created by wbiao on 14/12/8.
//  Copyright (c) 2014年 YueRuo. All rights reserved.
//

#import "LeftMenuViewController.h"
#import "FirstViewController.h"
#import "DisscussController.h"
#import "FeedBackController.h"

extern BOOL g_bBindDevice;
extern BOOL g_bgologin;
@implementation LeftMenuViewController

@synthesize firstViewController;
@synthesize disscussController;
@synthesize feedBackController;

-(void)RemoveSubViewController{
    [firstViewController StopTimer];
    [firstViewController removeFromParentViewController];
    firstViewController = nil;
    
    [disscussController removeFromParentViewController];
    disscussController = nil;
    
    [feedBackController removeFromParentViewController];
    feedBackController = nil;
}
- (void)viewDidLoad
{
    [super viewDidLoad];
    UIImageView *imageviewBg = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, FULLSCREENHEIGHT)];
    imageviewBg.userInteractionEnabled = YES;
    imageviewBg.image = [UIImage imageNamed:@"ui_background.png"];
    [self.view addSubview:imageviewBg];
    
    self.tableView = ({
        UITableView *tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, (imageviewBg.frame.size.height/2.0 - 55 * 5/2.0f) , self.view.frame.size.width/2, 55 * 5) style:UITableViewStylePlain];
        tableView.autoresizingMask = UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleBottomMargin | UIViewAutoresizingFlexibleWidth;
        tableView.delegate = self;
        tableView.dataSource = self;
        tableView.opaque = NO;
        tableView.backgroundColor = [UIColor clearColor];
        tableView.backgroundView = nil;
        tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        tableView.bounces = NO;
        tableView;
    });
    [imageviewBg addSubview:self.tableView];
    
    firstViewController = [[FirstViewController alloc]init];
    firstViewController.view.backgroundColor = [UIColor whiteColor];
    
    disscussController = [[DisscussController alloc]init];
    disscussController.view.backgroundColor = [UIColor whiteColor];
    
    feedBackController = [[FeedBackController alloc]init];
    feedBackController.view.backgroundColor = [UIColor whiteColor];
    
    [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(NeedShowRoot) name:@"NeedShowRoot" object:nil];
}
-(void)NeedShowRoot{
    if (firstViewController == nil) {
        firstViewController = [[FirstViewController alloc]init];
        firstViewController.view.backgroundColor = [UIColor whiteColor];
    }
    UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:firstViewController];
    nav.navigationBarHidden = YES;
    [self.sideMenuViewController setContentViewController:nav animated:NO];
    [self.sideMenuViewController hideMenuViewController];
    [firstViewController ReloadData];
}
-(void)LoadFirstView{
    if (firstViewController == nil) {
        firstViewController = [[FirstViewController alloc]init];
        firstViewController.view.backgroundColor = [UIColor whiteColor];
    }
    UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:firstViewController];
    nav.navigationBarHidden = YES;
    [self.sideMenuViewController setContentViewController:nav animated:NO];
    [self.sideMenuViewController hideMenuViewController];
    [firstViewController ReloadData];
}
#pragma mark -
#pragma mark UITableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    switch (indexPath.row) {
        case 0:
        {
            if (g_bgologin && !g_bBindDevice) {
                [firstViewController StopTimer];
                [firstViewController removeFromParentViewController];
                firstViewController = nil;
                AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
                [appDelegate ShowLoginView];
                [self.sideMenuViewController hideMenuViewController];
            }
            else{
                UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:firstViewController];
                nav.navigationBarHidden = YES;
                [self.sideMenuViewController setContentViewController:nav animated:NO];
                [self.sideMenuViewController hideMenuViewController];
                [firstViewController ReloadData];
            }
        }
            break;
        case 1:
        {
            if (disscussController == nil) {
                disscussController = [[DisscussController alloc]init];
                disscussController.view.backgroundColor = [UIColor whiteColor];
            }
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:disscussController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
            [disscussController ReloadData];
        }
            break;
        case 2:
        {
            if (feedBackController == nil) {
                feedBackController = [[FeedBackController alloc]init];
                feedBackController.view.backgroundColor = [UIColor whiteColor];
            }
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:feedBackController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
            [feedBackController ReloadData];
        }
            break;
        case 3:
        {
            UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"确定要注销?" message:@"" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"确定", nil];
            alert.tag = 10;
            [alert show];
            [self.sideMenuViewController hideMenuViewController];
        }
            break;
        default:
            break;
    }
}
-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (alertView.tag == 10) {
        if (buttonIndex == 1) {
            [firstViewController StopTimer];
            [firstViewController removeFromParentViewController];
            firstViewController = nil;
            
            
            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
            //存储时，除NSNumber类型使用对应的类型意外，其他的都是使用setObject:forKey:
            [userDefaults removeObjectForKey:@"isLoginSuccess"];
            [userDefaults synchronize];
            
            [userDefaults setBool:NO forKey:@"isLoginSuccess"];
            AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
            [appDelegate ShowLoginView];
        }
    }
}
#pragma mark -
#pragma mark UITableView Datasource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return 55;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)sectionIndex
{
    return 4;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"Cell";
    LeftMenuTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
        cell = [[LeftMenuTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier cellwidth:self.view.frame.size.width/2 cellheight:55];
        cell.backgroundColor        = [UIColor clearColor];
        cell.selectedBackgroundView = [[UIView alloc] init];
    }
    
    NSArray *titles       = @[@"首页", @"讨论区", @"用户反馈", @"注销登录"];
    NSArray *images       = @[@"icon_home.png", @"icon_topic.png", @"icon_feedback.png", @"icon_logout.png"];
    cell.labelTitle.text  = titles[indexPath.row];
    cell.imageTitle.image = [UIImage imageNamed:images[indexPath.row]];
    
    return cell;
}

@end

