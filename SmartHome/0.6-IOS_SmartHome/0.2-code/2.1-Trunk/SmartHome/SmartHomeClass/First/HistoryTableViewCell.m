//
//  HistoryTableViewCell.m
//  SmartHome
//
//  Created by apple on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "HistoryTableViewCell.h"

//list_ho_diver.png
@implementation HistoryTableViewCell
@synthesize labelInfo;
@synthesize labelStateValue;


-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier cellWidth:(float)cellWidth{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        fcellWidth = cellWidth;
        [self LoadCell];
    }
    return self;
}
-(void)LoadCell{
    labelInfo = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, fcellWidth/2-5, 60)];
    labelInfo.backgroundColor = [UIColor clearColor];
    labelInfo.font = [UIFont systemFontOfSize:14.0];
    labelInfo.textAlignment = NSTextAlignmentRight;
    labelInfo.numberOfLines = 3;
    labelInfo.textColor = [UIColor grayColor];
    labelInfo.text = @"";
    [self addSubview:labelInfo];
    
    UIImageView *imageLine = [[UIImageView alloc]initWithFrame:CGRectMake(fcellWidth/2-3, 16, 6, 28)];
    imageLine.image = [UIImage imageNamed:@"list_ho_diver.png"];
    [self.contentView addSubview:imageLine];
    
    labelStateValue = [[UILabel alloc]initWithFrame:CGRectMake(fcellWidth/2+5, 0, fcellWidth/2-5, 60)];
    labelStateValue.backgroundColor = [UIColor clearColor];
    labelStateValue.font = [UIFont systemFontOfSize:14.0];
    labelStateValue.textAlignment = NSTextAlignmentLeft;
    labelStateValue.textColor = [UIColor blackColor];
    labelStateValue.text = @"状态报警值";
    [self.contentView addSubview:labelStateValue];
}
- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
