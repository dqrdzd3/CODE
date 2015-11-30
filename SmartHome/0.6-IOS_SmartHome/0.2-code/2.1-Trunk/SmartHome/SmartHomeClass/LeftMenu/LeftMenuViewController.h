//
//  LeftMenuViewController.h
//  Weidou
//
//  Created by wbiao on 14/12/8.
//  Copyright (c) 2014年 YueRuo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"
#import "LeftMenuTableViewCell.h"

#import "LoginViewController.h"
#import "AppDelegate.h"
@class FirstViewController;
@class DisscussController;
@class FeedBackController;

@interface LeftMenuViewController : UIViewController <UITableViewDataSource, UITableViewDelegate,LoginViewControllerDelegate,UIAlertViewDelegate>

@property (strong, readwrite, nonatomic) UITableView *tableView;
@property (strong,nonatomic)FirstViewController *firstViewController;
@property (strong,nonatomic)DisscussController *disscussController;
@property (strong,nonatomic)FeedBackController *feedBackController;

-(void)RemoveSubViewController;
-(void)LoadFirstView;
@end
