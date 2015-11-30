//
//  AboutViewController.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "../Macro.h"
#import "AppDelegate.h"
#import "MBProgressHUD.h"
#import "JSONKit.h"
@interface AboutViewController : UIViewController{
    NSString *trackViewURL;
    NSString *currentVersion;
    MBProgressHUD *HUD;
}


@property(nonatomic,strong)UIView *viewTop;
@property(nonatomic,strong)UILabel *labelTitle;
@property(nonatomic,strong)UIButton *btnLeft;
@property(nonatomic,strong)UIButton *btnCheckUpdate;
@end
