//
//  FightButton.m
//  Weidou
//
//  Created by wbiao on 14/12/9.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "FightButton.h"

@implementation FightButton
@synthesize labelValue;
@synthesize labelRank;
@synthesize labelStatus;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        [self LoadButton];
    }
    return self;
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/


-(void)LoadButton{
    labelValue = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, rect.size.width, rect.size.height/4)];
    labelValue.backgroundColor = [UIColor clearColor];
    labelValue.font = [UIFont systemFontOfSize:16.0];
    labelValue.textAlignment = NSTextAlignmentCenter;
    labelValue.textColor = [UIColor blackColor];
    [self addSubview:labelValue];
    
    labelStatus = [[UILabel alloc]initWithFrame:CGRectMake(0, rect.size.height/4, rect.size.width, rect.size.height/4)];
    labelStatus.backgroundColor = [UIColor clearColor];
    labelStatus.font = [UIFont systemFontOfSize:14.0];
    labelStatus.textAlignment = NSTextAlignmentCenter;
    labelStatus.textColor = [UIColor blackColor];
    [self addSubview:labelStatus];
    
    labelRank = [[UILabel alloc]initWithFrame:CGRectMake(0, rect.size.height/2, rect.size.width, rect.size.height/4)];
    labelRank.backgroundColor = [UIColor clearColor];
    labelRank.font = [UIFont systemFontOfSize:14.0];
    labelRank.textAlignment = NSTextAlignmentCenter;
    labelRank.textColor = [UIColor blackColor];
    [self addSubview:labelRank];
}
@end
