//
//  DisscussDetailTableViewCell.m
//  SmartHome
//
//  Created by 李静 on 14-11-13.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "DisscussDetailTableViewCell.h"

@implementation DisscussDetailTableViewCell
@synthesize headImageView;
@synthesize nameLabel;
@synthesize contentLable;
@synthesize dateLabel;

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self LoadCell];
    }
    return self;
}

-(void)LoadCell{
    headImageView = [[UIImageView alloc]initWithFrame:CGRectMake(5, 10, 55, 55)];
    headImageView.layer.masksToBounds = YES;
    headImageView.layer.cornerRadius = 3.0;
    headImageView.image = [UIImage imageNamed:@"userHeadImage.png"];
    [self.contentView addSubview:headImageView];
    
    nameLabel = [[UILabel alloc]initWithFrame:CGRectMake(65, 10,([UIScreen mainScreen].bounds.size.width-65)/2-5, 20)];
    nameLabel.backgroundColor = [UIColor clearColor];
    nameLabel.font = [UIFont systemFontOfSize:14.0];
    nameLabel.textColor = [UIColor blackColor];
    nameLabel.text = [NSString stringWithFormat:@""];
    [self.contentView addSubview:nameLabel];
    
    dateLabel = [[UILabel alloc]initWithFrame:CGRectMake(nameLabel.frame.origin.x+nameLabel.frame.size.width, nameLabel.frame.origin.y,([UIScreen mainScreen].bounds.size.width-65)/2, 20)];
    dateLabel.backgroundColor = [UIColor clearColor];
    dateLabel.font = [UIFont systemFontOfSize:12.0];
    dateLabel.textColor = [UIColor blackColor];
    dateLabel.text = [NSString stringWithFormat:@""];
    [self.contentView addSubview:dateLabel];
    
    contentLable = [[UILabel alloc]initWithFrame:CGRectMake(0,0,0,0)];
    contentLable.backgroundColor = [UIColor clearColor];
    contentLable.font = [UIFont systemFontOfSize:14.0];
    contentLable.textColor = [UIColor blackColor];
    contentLable.text = [NSString stringWithFormat:@""];
    contentLable.numberOfLines = 0;
    [self.contentView addSubview:contentLable];
}
- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
