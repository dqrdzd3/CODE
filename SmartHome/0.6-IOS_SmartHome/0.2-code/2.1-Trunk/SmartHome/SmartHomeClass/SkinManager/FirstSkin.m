//
//  FirstSkin.m
//  SmartHome
//
//  Created by wbiao on 14/12/30.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "FirstSkin.h"

@implementation FirstSkin

-(id)init{
    self = [super init];
    if (self) {
        [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(ChangeSkin:) name:@"ChangeSkin" object:nil];
    }
    return self;
}
/**
 *  接收通知实现改变皮肤的功能
 */
-(void)ChangeSkin:(NSNotification*)value{
    NSDictionary *dictemp = [value object];
    NSLog(@"ChangeSkin_%@",[dictemp objectForKey:@"imageurl"]);
}
@end
