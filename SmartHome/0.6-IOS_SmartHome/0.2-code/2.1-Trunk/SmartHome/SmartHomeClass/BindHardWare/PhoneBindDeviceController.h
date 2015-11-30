//
//  PhoneBindDeviceController.h
//  SmartHome
//
//  Created by 李静 on 14-11-24.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//


#import <UIKit/UIKit.h>
#import "RTLabel.h"
#import "DescriptionConfigViewController.h"

#import "QRCodeViewController.h"
@interface PhoneBindDeviceController : UIViewController<UITextFieldDelegate,QRCodeViewControllerDelegate>{
    MBProgressHUD *HUD;
    UIButton *btnLeftMenu;
    UIButton *btnRightMenu;
}


- (void)bindBtnClick:(id)sender;
- (void)scanBtnClick:(id)sender;
- (void)lastBtnClick:(id)sender;
- (void)nextBtnClick:(id)sender;

@property (strong, nonatomic) UIButton *scanBtn;
@property (strong, nonatomic) UIImageView *imageDis;
@property (strong, nonatomic) UITextField *qrCodeTextField;
@property (strong, nonatomic) UIButton *bindBtn;
@property (strong, nonatomic) RTLabel *hintLabel;
@property (strong, nonatomic) UIScrollView *scrollView;

@end
