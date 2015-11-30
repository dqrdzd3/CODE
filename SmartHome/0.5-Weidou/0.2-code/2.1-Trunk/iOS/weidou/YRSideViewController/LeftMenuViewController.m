//
//  LeftMenuViewController.m
//  Weidou
//
//  Created by wbiao on 14/12/8.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "LeftMenuViewController.h"
#import "SetupViewController.h"
#import "TeamMemViewController.h"
#import "HelpViewController.h"
#import "AboutViewController.h"
#import "HomeViewController.h"

@implementation LeftMenuViewController

@synthesize homeViewController;
@synthesize setupViewController;
@synthesize aboutViewController;
@synthesize teamViewController;
@synthesize helpViewController;

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
    
    homeViewController = [[HomeViewController alloc]init];
    homeViewController.view.backgroundColor = [UIColor whiteColor];
    
    UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:homeViewController];
    nav.navigationBarHidden = YES;
    [self.sideMenuViewController setContentViewController:nav animated:NO];
    
    setupViewController = [[SetupViewController alloc]init];
    setupViewController.view.backgroundColor = [UIColor whiteColor];
    
    teamViewController = [[TeamMemViewController alloc]init];
    teamViewController.view.backgroundColor = [UIColor whiteColor];
    
    helpViewController = [[HelpViewController alloc]init];
    helpViewController.view.backgroundColor = [UIColor whiteColor];
    
    aboutViewController = [[AboutViewController alloc]init];
    aboutViewController.view.backgroundColor = [UIColor whiteColor];
}

#pragma mark -
#pragma mark UITableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    switch (indexPath.row) {
        case 0:
        {
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:homeViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
        }
            break;
        case 100:
        {
//            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:setupViewController];
//            nav.navigationBarHidden = YES;
//            [self.sideMenuViewController setContentViewController:nav animated:NO];
//            [self.sideMenuViewController hideMenuViewController];
        }
            break;
        case 1:
        {
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:teamViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
        }
            break;
        case 2:
        {
            UINavigationController* nav = [[UINavigationController alloc] initWithRootViewController:helpViewController];
            nav.navigationBarHidden = YES;
            [self.sideMenuViewController setContentViewController:nav animated:NO];
            [self.sideMenuViewController hideMenuViewController];
        }
            break;
        case 3:
        {
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
    return 4;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"Cell";
    LeftMenuTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    
    if (cell == nil) {
        cell = [[LeftMenuTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier cellwidth:self.view.frame.size.width/2 cellheight:55];
        cell.backgroundColor = [UIColor clearColor];
        cell.selectedBackgroundView = [[UIView alloc] init];
    }
    
    NSArray *titles = @[@"主页面", @"团队成员", @"帮助中心", @"关于"];
    NSArray *images = @[@"icon_home.png", @"", @"", @""];
    cell.labelTitle.text = titles[indexPath.row];
    cell.imageTitle.image = [UIImage imageNamed:images[indexPath.row]];
    
    return cell;
}

@end

