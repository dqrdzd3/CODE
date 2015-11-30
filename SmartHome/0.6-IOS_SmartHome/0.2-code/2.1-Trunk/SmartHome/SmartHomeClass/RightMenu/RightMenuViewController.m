//
//  RightMenuViewController.m
//  Weidou
//
//  Created by wbiao on 14/12/11.
//  Copyright (c) 2014年 YueRuo. All rights reserved.
//

#import "RightMenuViewController.h"
#import "PhoneBindDeviceController.h"
#import "secondViewController.h"
#import "accountManagerViewController.h"
#import "helpViewController.h"
#import "aboutViewController.h"
@implementation RightMenuViewController

//@synthesize phoneBindDeviceController;
@synthesize secondViewController;
@synthesize accountManagerViewController;
@synthesize helpViewController;
@synthesize aboutViewController;
-(void)RemoveSubViewController{
    [secondViewController removeFromParentViewController];
    secondViewController = nil;
    
    [accountManagerViewController removeFromParentViewController];
    accountManagerViewController = nil;
    
    [helpViewController removeFromParentViewController];
    helpViewController = nil;
    
    [aboutViewController removeFromParentViewController];
    aboutViewController = nil;
}
- (void)viewDidLoad
{
    [super viewDidLoad];
    UIImageView *imageviewBg = [[UIImageView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, FULLSCREENHEIGHT)];
    imageviewBg.userInteractionEnabled = YES;
    imageviewBg.image = [UIImage imageNamed:@"ui_background.png"];
    [self.view addSubview:imageviewBg];
    
    self.tableView = ({
        UITableView *tableView = [[UITableView alloc] initWithFrame:CGRectMake(self.view.frame.size.width/2, (imageviewBg.frame.size.height/2.0 - 55 * 5/2.0f) , self.view.frame.size.width/2, 55 * 5) style:UITableViewStylePlain];
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
    
    secondViewController = [[SecondViewController alloc]init];
    secondViewController.view.backgroundColor = [UIColor whiteColor];
    
    accountManagerViewController = [[AccountManagerViewController alloc]init];
    accountManagerViewController.view.backgroundColor = [UIColor whiteColor];
    
    helpViewController = [[HelpViewController alloc]init];
    helpViewController.view.backgroundColor = [UIColor whiteColor];
    
    aboutViewController = [[AboutViewController alloc]init];
    aboutViewController.view.backgroundColor = [UIColor whiteColor];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(NeedAddSensor) name:@"NeedAddSensor" object:nil];
}
-(void)NeedAddSensor{
    [self.sideMenuViewController presentViewController:[[PhoneBindDeviceController alloc]init] animated:YES completion:nil];
}

#pragma mark -
#pragma mark UITableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    switch (indexPath.row) {
        case 0:
        {
            [self.sideMenuViewController hideMenuViewController];
            [self.sideMenuViewController presentViewController:[[PhoneBindDeviceController alloc]init] animated:YES completion:nil];
        }
            break;
        case 1:
        {
            if (secondViewController == nil) {
                secondViewController = [[SecondViewController alloc]init];
                secondViewController.view.backgroundColor = [UIColor whiteColor];
            }
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:secondViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
            
            [secondViewController ReloadData];
        }
            break;
        case 2:
        {
            if (accountManagerViewController == nil) {
                accountManagerViewController = [[AccountManagerViewController alloc]init];
                accountManagerViewController.view.backgroundColor = [UIColor whiteColor];
            }
            
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:accountManagerViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
            [accountManagerViewController ReloadData];
        }
            break;
        case 3:
        {
            if (helpViewController == nil) {
                helpViewController = [[HelpViewController alloc]init];
                helpViewController.view.backgroundColor = [UIColor whiteColor];
            }
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:helpViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
        }
            break;
        case 4:
        {
            if (aboutViewController == nil) {
                aboutViewController = [[AboutViewController alloc]init];
                aboutViewController.view.backgroundColor = [UIColor whiteColor];
            }
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:aboutViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
        }
            break;
            
        default:
            break;
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
    return 5;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
        cell.backgroundColor = [UIColor clearColor];
        cell.selectedBackgroundView = [[UIView alloc] init];
    }
    NSArray *titles = @[@"配置绑定", @"配置设备", @"账号管理", @"帮助中心", @"关于"];
    cell.textLabel.textAlignment = NSTextAlignmentRight;
    cell.textLabel.font = [UIFont systemFontOfSize:18.0];
    cell.textLabel.text = titles[indexPath.row];
    return cell;
}

@end

