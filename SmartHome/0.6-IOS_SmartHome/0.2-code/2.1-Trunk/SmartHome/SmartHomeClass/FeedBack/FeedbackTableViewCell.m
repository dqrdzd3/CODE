//
//  FeedbackTableViewCell.m
//  SmartHome
//
//  Created by 李静 on 14-11-13.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "FeedbackTableViewCell.h"

@implementation FeedbackTableViewCell
@synthesize questionLable;
@synthesize replyLable;
@synthesize questionDateLabel;
@synthesize replyDateLable;

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self LoadCell];
    }
    return self;
}

-(void)LoadCell{
    questionLable                 = [[UILabel alloc]initWithFrame:CGRectMake(0,0,0,0)];
    questionLable.backgroundColor = [UIColor clearColor];
    questionLable.font            = [UIFont systemFontOfSize:14.0];
    questionLable.textColor       = [UIColor blackColor];
    questionLable.text            = [NSString stringWithFormat:@"反馈问题:\n"];
    questionLable.numberOfLines   = 0;
    [self.contentView addSubview:questionLable];
    
    questionDateLabel                 = [[UILabel alloc]initWithFrame:CGRectMake(5, questionLable.frame.origin.y+questionLable.frame.size.height,[UIScreen mainScreen].bounds.size.width-10, 20)];
    questionDateLabel.backgroundColor = [UIColor clearColor];
    questionDateLabel.font            = [UIFont systemFontOfSize:12.0];
    questionDateLabel.textColor       = [UIColor grayColor];
    questionDateLabel.textAlignment   = NSTextAlignmentRight;
    questionDateLabel.text            = [NSString stringWithFormat:@"2014-11-11 11:11"];
    [self.contentView addSubview:questionDateLabel];
    
    replyLable                 = [[UILabel alloc]initWithFrame:CGRectMake(0,0,0,0)];
    replyLable.backgroundColor = [UIColor clearColor];
    replyLable.font            = [UIFont systemFontOfSize:14.0];
    replyLable.textColor       = [UIColor blackColor];
    replyLable.text            = [NSString stringWithFormat:@"回复问题:\n"];
    replyLable.numberOfLines   = 0;
    [self.contentView addSubview:replyLable];
    
    replyDateLable                 = [[UILabel alloc]initWithFrame:CGRectMake(5, replyLable.frame.origin.y+replyLable.frame.size.height,[UIScreen mainScreen].bounds.size.width-10, 20)];
    replyDateLable.backgroundColor = [UIColor clearColor];
    replyDateLable.font            = [UIFont systemFontOfSize:12.0];
    replyDateLable.textColor       = [UIColor grayColor];
    replyDateLable.textAlignment   = NSTextAlignmentRight;
    replyDateLable.text            = [NSString stringWithFormat:@""];
    [self.contentView addSubview:replyDateLable];
    
}
- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
