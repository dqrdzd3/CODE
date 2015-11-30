//
//  HelpViewController.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-22.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"

@interface HelpViewController : UIViewController
@property (retain,nonatomic) NSString *mUrl;
@property (strong, nonatomic) UIWebView *helpWebview;
@end
