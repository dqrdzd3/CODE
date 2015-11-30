//
//  RankCellButton.m
//  Weidou
//
//  Created by wbiao on 14/11/28.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "RankCellButton.h"

@implementation RankCellButton

@synthesize labelValue;
@synthesize labelState;
@synthesize labelRank;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        [self LoadButton];
    }
    return self;
}

-(void)LoadButton{
    labelValue = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, rect.size.width, rect.size.height/4)];
    labelValue.backgroundColor = [UIColor clearColor];
    labelValue.textAlignment = NSTextAlignmentCenter;
    labelValue.textColor = [UIColor blackColor];
    labelValue.font = [UIFont systemFontOfSize:16];
    [self addSubview:labelValue];
    
    labelState = [[UILabel alloc]initWithFrame:CGRectMake(0,labelValue.frame.origin.y+labelValue.frame.size.height, rect.size.width, rect.size.height/4)];
    labelState.backgroundColor = [UIColor clearColor];
    labelState.textAlignment = NSTextAlignmentCenter;
    labelState.textColor = [UIColor blackColor];
    labelState.font = [UIFont systemFontOfSize:14];
    [self addSubview:labelState];
    
    labelRank = [[UILabel alloc]initWithFrame:CGRectMake(0, labelState.frame.origin.y+labelState.frame.size.height, rect.size.width, rect.size.height/4)];
    labelRank.backgroundColor = [UIColor clearColor];
    labelRank.textAlignment = NSTextAlignmentCenter;
    labelRank.textColor = [UIColor blackColor];
    labelRank.font = [UIFont systemFontOfSize:14];
    [self addSubview:labelRank];
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
