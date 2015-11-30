//
//  FeedBackController.h
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

#import "RESideMenu.h"
@interface FeedBackController : UIViewController <UITableViewDataSource,UITableViewDelegate,UITextViewDelegate>{
    MBProgressHUD *HUD;
}
@property (strong, nonatomic)  UIScrollView *scrollView;
@property (strong, nonatomic)  UITableView *feedbackTableView;
@property (strong, nonatomic)  UITextView *feedbackContentText;
@property (strong, nonatomic)  UILabel *numLabel;
@property (strong, nonatomic)  UIButton *postBtn;
- (void)postBtnClick:(id)sender;
@property (strong, nonatomic) NSMutableArray *feedbackList;


-(void)ReloadData;
@end
