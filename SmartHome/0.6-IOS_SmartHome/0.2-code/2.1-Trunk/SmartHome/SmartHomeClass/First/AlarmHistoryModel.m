//
//  AlarmHistoryModel.m
//  SmartHome
//  历史报警
//  Created by 李静 on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "AlarmHistoryModel.h"

@implementation AlarmHistoryModel

@synthesize ma001;
@synthesize ma002;
@synthesize ma003;
@synthesize ma004;
@synthesize ma005;
@synthesize ma006;
@synthesize ma007;
@synthesize ma008;

-(id)initWithDictionary:(NSDictionary *)dic{
    self = [super init];
    if (self) {
        self.ma001 = [dic objectForKey:@"ma001"];
        self.ma002 = [dic objectForKey:@"ma002"];
        self.ma003 = [dic objectForKey:@"ma003"];
        self.ma004 = [dic objectForKey:@"ma004"];
        self.ma005 = [dic objectForKey:@"ma005"];
        self.ma006 = [dic objectForKey:@"ma006"];
        self.ma007 = [dic objectForKey:@"ma007"];
        self.ma008 = [dic objectForKey:@"ma008"];
    }
    return self;
}
@end
