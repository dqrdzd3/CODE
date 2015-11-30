//
//  RightMenuViewController.h
//  Weidou
//
//  Created by wbiao on 14/12/11.
//  Copyright (c) 2014年 YueRuo. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"

@class PhoneBindDeviceController;
@class SecondViewController;
@class AccountManagerViewController;
@class HelpViewController;
@class AboutViewController;
@interface RightMenuViewController : UIViewController <UITableViewDataSource, UITableViewDelegate>

@property (strong, readwrite, nonatomic) UITableView *tableView;
//@property (strong,nonatomic)PhoneBindDeviceController *phoneBindDeviceController;
@property (strong,nonatomic)SecondViewController *secondViewController;
@property (strong,nonatomic)AccountManagerViewController *accountManagerViewController;
@property (strong,nonatomic)HelpViewController *helpViewController;
@property (strong,nonatomic)AboutViewController *aboutViewController;

-(void)RemoveSubViewController;
@end