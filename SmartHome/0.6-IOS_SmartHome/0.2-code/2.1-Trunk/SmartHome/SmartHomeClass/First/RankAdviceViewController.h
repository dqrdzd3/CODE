//
//  RankAdviceViewController.h
//  SmartHome
//
//  Created by apple on 14-11-17.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RankAdviceViewController : UIViewController<UIWebViewDelegate>{
    MBProgressHUD *HUD;
}

@property(nonatomic,strong)UIWebView *webView;
@property(nonatomic,strong)NSString *strGastype;
@property(nonatomic,strong)NSString *strGasstate;
@end
