//
//  LeftMenuViewController.h
//  Weidou
//
//  Created by wbiao on 14/12/8.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"
#import "LeftMenuTableViewCell.h"
@class HomeViewController;
@class SetupViewController;
@class TeamMemViewController;
@class HelpViewController;
@class AboutViewController;
@interface LeftMenuViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>

@property (strong, readwrite, nonatomic) UITableView *tableView;
@property (strong,nonatomic)HomeViewController *homeViewController;
@property (strong,nonatomic)SetupViewController *setupViewController;
@property (strong,nonatomic)TeamMemViewController *teamViewController;
@property (strong,nonatomic)HelpViewController *helpViewController;
@property (strong,nonatomic)AboutViewController *aboutViewController;
@end
