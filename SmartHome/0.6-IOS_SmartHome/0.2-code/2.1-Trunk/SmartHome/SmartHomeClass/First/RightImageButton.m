//
//  RightImageButton.m
//  SmartHome
//
//  Created by wbiao on 14/12/22.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "RightImageButton.h"

@implementation RightImageButton
@synthesize imageRight;
@synthesize labelTitle;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        [self LoadButton];
    }
    return self;
}

-(void)LoadButton{
    imageRight = [[UIImageView alloc]initWithFrame:CGRectMake(5, 5, rect.size.height-10, rect.size.height-10)];
    [self addSubview:imageRight];
    
    labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(imageRight.frame.origin.x+imageRight.frame.size.width+10, 5, rect.size.width-imageRight.frame.origin.x-imageRight.frame.size.width-10, imageRight.frame.size.height)];
    labelTitle.textColor = [UIColor whiteColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.backgroundColor = [UIColor clearColor];
    [self addSubview:labelTitle];
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
