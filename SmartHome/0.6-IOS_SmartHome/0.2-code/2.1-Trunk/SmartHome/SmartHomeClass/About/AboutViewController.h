//
//  AboutViewController.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-22.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"
@interface AboutViewController : UIViewController{
    NSString *trackViewURL;
    MBProgressHUD *HUD;
    
    UIImageView *imageLogo;
    UIImageView *imageTextLogo;
    UIButton *btnUpdate;
    
    UILabel *labelComp;
    UILabel *labelVer;
}

- (IBAction)checkVersionBtnClick:(id)sender;
@end
