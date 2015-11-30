//
//  DisscussDetailViewController.h
//  SmartHome
//
//  Created by 李静 on 14-11-13.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DisscussTheme.h"

@interface DisscussDetailViewController : UIViewController<UITableViewDataSource,UITableViewDelegate,UITextViewDelegate>{
    MBProgressHUD *HUD;
}
@property (strong, nonatomic) UIView *viewTop;
@property (strong, nonatomic) UIScrollView *scrollView;
@property (strong, nonatomic) UITableView *mTableView;
@property (strong, nonatomic) UITextView *inputContentText;
@property (strong, nonatomic) UIButton *postBtn;
@property (strong, nonatomic) UILabel *leftWordsLable;
@property (strong, nonatomic) NSMutableArray *disscussList;
@property (strong, nonatomic) DisscussTheme *disscussTheme;

- (void)postBtnClick:(id)sender;
- (IBAction)backBtnClick:(id)sender;
@end
