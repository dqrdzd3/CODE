//
//  AdviceViewController.h
//  Weidou
//
//  Created by wbiao on 14/12/10.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Macro.h"
#import "RealTimeModel.h"

@interface AdviceViewController : UIViewController<UIWebViewDelegate>{
    UIWebView *webview;
    UIActivityIndicatorView *activity;
}
@property(nonatomic,strong)UIView *viewTop;
@property(nonatomic,strong)UILabel *labelTitle;
@property(nonatomic,strong)UIButton *btnLeft;
@end
