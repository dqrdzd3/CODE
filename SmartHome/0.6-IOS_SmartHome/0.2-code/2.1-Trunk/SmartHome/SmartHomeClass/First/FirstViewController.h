//
//  FirstViewController.h
//  RESideMenuStoryboards
//
//  Created by Roman Efimov on 10/9/13.
//  Copyright (c) 2013 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"
#import "CMPopTipView.h"
#import "FirstContentView.h"
#import "RankAdviceViewController.h"

@interface FirstViewController : UIViewController<FirstContentViewDelegate>{
    NSMutableDictionary  *mdicContentViews;
    NSTimer              *timer;

    UIButton             *btnLeftMenu;
    UIButton             *btnRightMenu;

    NSMutableDictionary  *dicAlert;
    BOOL bWork;
}
@property (nonatomic, strong) UIScrollView  *scrollView;


-(void)StopTimer;
-(void)ReloadData;
@end
