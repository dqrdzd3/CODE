//
//  FeedbackTableViewCell.h
//  SmartHome
//
//  Created by 李静 on 14-11-13.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FeedbackTableViewCell : UITableViewCell
@property (strong, nonatomic) UILabel *questionLable;
@property (strong, nonatomic) UILabel *replyLable;
@property (strong, nonatomic) UILabel *questionDateLabel;
@property (strong, nonatomic) UILabel *replyDateLable;

@end
