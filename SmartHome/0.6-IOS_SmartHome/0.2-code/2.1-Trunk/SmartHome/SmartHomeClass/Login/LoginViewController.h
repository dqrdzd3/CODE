//
//  LoginViewController.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-21.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RegisterViewController.h"
#import "ForgetPsdViewController.h"


@protocol LoginViewControllerDelegate <NSObject>

-(void)LoginViewControllerFinished:(BOOL)bsuc;

@end
@interface LoginViewController : UIViewController<UITextFieldDelegate,RegisterViewControllerDelegate>{
    MBProgressHUD *HUD;
    
    UIImageView *imageBackGround;
    UIImageView *imageLogo;
}
@property (strong, nonatomic) UIButton *btnRegister;
@property (strong, nonatomic) UIButton *btnForgetPsd;

@property (strong, nonatomic) UITextField *userNameInput;
@property (strong, nonatomic) UITextField *userPasswordInput;
@property (strong, nonatomic) UIButton *btnSavePsd;
@property (strong, nonatomic) UIButton *btnLogin;
@property (strong, nonatomic) UIView *viewInput;


@property(strong, nonatomic)id<LoginViewControllerDelegate> delegate;
- (void)loginBtnClick;
- (void)savePsdBtnClick;

@end
