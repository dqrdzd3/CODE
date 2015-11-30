//
//  RegisterViewController.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-21.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BMapKit.h"

@protocol RegisterViewControllerDelegate <NSObject>

-(void)RegisterViewControllerFinish;

@end
@interface RegisterViewController : UIViewController<UIActionSheetDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate,BMKLocationServiceDelegate,BMKGeoCodeSearchDelegate>{
    MBProgressHUD *HUD;
    BOOL bLocationed;
    float fLo; //LNG
    float fLa; //LAT
    
    NSString *strLoc;//LOC
    NSString *strProvice;//PROVICE
    NSString *strCity;//CITY
    NSString *strArea;//AREA
    
}
@property (strong, nonatomic) id<RegisterViewControllerDelegate>delegate;
@property (strong, nonatomic) BMKGeoCodeSearch *searcher;
@property (strong, nonatomic) BMKLocationService *locService;
@property (strong, nonatomic) UIScrollView *scrollView;
@property (strong, nonatomic) UITextField *userNameInput;
@property (strong, nonatomic) UIButton *registerBtn;
@property (strong, nonatomic) UITextField *phoneInput;
@property (strong, nonatomic) UITextField *psdInput;
@property (strong, nonatomic) UITextField *psdConfirmInput;
@property (strong, nonatomic) UITextField *emailInput;
@property (strong, nonatomic) UIImageView *userHeadImage;
@property (strong, nonatomic) UISwitch *protocolSwitch;
@property (strong, nonatomic) UIButton *protocolBtn;

- (IBAction)registerBtnClick:(id)sender;
- (void)userHeadClick;
- (IBAction)backBtnClick:(id)sender;
- (IBAction)protocolBtnClick:(id)sender;
@end
