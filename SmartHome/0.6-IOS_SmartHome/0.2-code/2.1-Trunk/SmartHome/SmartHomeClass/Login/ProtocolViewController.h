//
//  ProtocolViewController.h
//  SmartHome
//
//  Created by 李静 on 14-11-7.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ProtocolViewController : UIViewController<UIWebViewDelegate>{
    MBProgressHUD *HUD;
}
- (IBAction)backBtnClick:(id)sender;
@property (strong, nonatomic) UIWebView *mWebView;
@property (strong, nonatomic) NSString *mUrl;
@end
