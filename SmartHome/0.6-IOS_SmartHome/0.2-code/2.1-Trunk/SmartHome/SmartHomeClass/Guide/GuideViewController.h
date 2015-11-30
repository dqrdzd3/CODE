//
//  GuideViewController.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-20.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "AppDelegate.h"
@interface GuideViewController : UIViewController<UIScrollViewDelegate>

@property (strong, nonatomic) UIScrollView *pageScroll;
@property (strong, nonatomic) NSArray *photoList;
@property (strong, nonatomic) UIButton *gotoNextBtn;
- (IBAction)clickBtn:(id)sender;
@end
