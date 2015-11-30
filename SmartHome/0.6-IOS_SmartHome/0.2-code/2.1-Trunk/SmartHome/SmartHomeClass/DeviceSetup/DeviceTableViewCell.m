//
//  DeviceTableViewCell.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-28.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "DeviceTableViewCell.h"

@implementation DeviceTableViewCell
@synthesize deviceId;
@synthesize deviceName;
@synthesize isOnline;
@synthesize delegate;

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        [self LoadCell];
    }
    return self;
}
-(void)LoadCell{
    deviceId = [[UILabel alloc]initWithFrame:CGRectMake(5, 0, 2*SCREENWIDTH/5-5, 60)];
    deviceId.backgroundColor = [UIColor clearColor];
    deviceId.font = [UIFont systemFontOfSize:14.0];
    deviceId.textColor = [UIColor blackColor];
    [self.contentView addSubview:deviceId];
    
    isOnline = [[UILabel alloc]initWithFrame:CGRectMake(2*SCREENWIDTH/5, 0,SCREENWIDTH/5, 60)];
    isOnline.backgroundColor = [UIColor clearColor];
    isOnline.font = [UIFont systemFontOfSize:14.0];
    isOnline.textColor = [UIColor blackColor];
    [self.contentView addSubview:isOnline];
    
    deviceName = [[UILabel alloc]initWithFrame:CGRectMake(3*SCREENWIDTH/5, 0, 2*SCREENWIDTH/5-5, 60)];
    deviceName.numberOfLines = 3;
    deviceName.backgroundColor = [UIColor clearColor];
    deviceName.font = [UIFont systemFontOfSize:14.0];
    deviceName.textColor = [UIColor blackColor];
    [self.contentView addSubview:deviceName];
}

- (void)awakeFromNib {
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
