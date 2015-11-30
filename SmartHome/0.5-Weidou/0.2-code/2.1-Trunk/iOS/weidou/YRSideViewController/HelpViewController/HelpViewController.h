//
//  HelpViewController.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "../Macro.h"
#import "AppDelegate.h"
@interface HelpViewController : UIViewController{
    IBOutlet UIWebView *webview;
}


@property(nonatomic,strong)UIView *viewTop;
@property(nonatomic,strong)UILabel *labelTitle;
@property(nonatomic,strong)UIButton *btnLeft;
@end
