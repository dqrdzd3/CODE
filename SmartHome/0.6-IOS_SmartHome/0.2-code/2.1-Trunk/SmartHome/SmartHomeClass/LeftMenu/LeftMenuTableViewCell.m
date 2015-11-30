//
//  LeftMenuTableViewCell.m
//  Weidou
//
//  Created by wbiao on 14/12/8.
//  Copyright (c) 2014年 YueRuo. All rights reserved.
//

#import "LeftMenuTableViewCell.h"

@implementation LeftMenuTableViewCell
@synthesize labelTitle;
@synthesize imageTitle;

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier cellwidth:(float)width cellheight:(float)height{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        fwidth = width;
        fheight = height;
        [self LoadCell];
    }
    return self;
}

-(void)LoadCell{
    imageTitle = [[UIImageView alloc]initWithFrame:CGRectMake(5, (fheight-35)/2.0, 35, 35)];
    [self.contentView addSubview:imageTitle];
    
    labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, 0, fwidth, fheight)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont fontWithName:@"HelveticaNeue" size:18];
    [self.contentView addSubview:labelTitle];
}
- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
