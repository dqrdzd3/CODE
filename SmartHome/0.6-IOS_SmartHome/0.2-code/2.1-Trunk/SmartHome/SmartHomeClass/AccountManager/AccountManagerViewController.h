//
//  AccountManagerViewController.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-23.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "User.h"
#import "RESideMenu.h"
@interface AccountManagerViewController : UIViewController<UIActionSheetDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate,UITextFieldDelegate>{
    NSString* strimagePath;
    NSString* name;
    NSString* email;
    NSString *Gaipassword;
    MBProgressHUD *HUD;
}

@property (strong, nonatomic) UIScrollView *scrollView;
@property (strong, nonatomic) UITextField *fieldemail;
@property (strong, nonatomic) UILabel *labelphone;
@property (strong, nonatomic) UIButton *changeAccountBtn;
@property (strong, nonatomic) UITextField *userName;
@property (strong, nonatomic) UITextField *oldPsdText;
@property (strong, nonatomic) UITextField *nPsdText;
@property (strong, nonatomic) UITextField *repeatPsdText;
@property (strong, nonatomic) UIButton *changePsdBtn;
@property (strong, nonatomic) UIImageView *userHeadImage;
@property(nonatomic,strong) User *userInfo;

- (void)changeAccountClick:(id)sender;
- (void)changePsdBtnClick:(id)sender;
-(void)ReloadData;
@end
