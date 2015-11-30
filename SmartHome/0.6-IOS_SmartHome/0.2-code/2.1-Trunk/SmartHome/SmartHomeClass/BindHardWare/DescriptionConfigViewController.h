//
//  DescriptionConfigViewController.h
//  SmartHome
//
//  Created by 李静 on 14-11-25.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RTLabel.h"
#import "HardwareViewController.h"

@interface DescriptionConfigViewController : UIViewController

@property (strong, nonatomic) UIImageView *desImage;
@property (strong, nonatomic) RTLabel *configLabel;
@property (strong, nonatomic) UIButton *nextBtn;
@property (strong, nonatomic) UIButton *lastBtn;
@property (strong, nonatomic) UIImageView *powerImage;
@property (strong, nonatomic) RTLabel *powerHint;

- (void)lastBtnClick:(id)sender;
- (void)nextBtnClick:(id)sender;

@property(nonatomic,copy)NSString *deviceId;
@property(strong, nonatomic) UIScrollView *scrollView;
@end
