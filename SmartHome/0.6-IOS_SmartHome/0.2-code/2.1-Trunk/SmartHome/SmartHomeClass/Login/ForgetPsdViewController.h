//
//  ForgetPsdViewController.h
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ForgetPsdViewController : UIViewController{
    MBProgressHUD *HUD;
    NSTimer *countDownTimer;
    int secondsCountDown;
}
@property (strong, nonatomic) UITextField *phoneNumberText;
@property (strong, nonatomic) UITextField *verifyCodeText;
@property (strong, nonatomic) UITextField *nPasswordText;
@property (strong, nonatomic) UITextField *repeatPasswordText;
@property (strong, nonatomic) UIButton *getVerifyBtn;
@property (strong, nonatomic) UIButton *postBtn;


- (IBAction)backBtnClick:(id)sender;
- (IBAction)getVerifyBtnClick:(id)sender;
- (IBAction)postBtnClick:(id)sender;

@end
