//
//  GasDevice.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-11-1.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "GasDevice.h"
#import "DateUtils.h"
@implementation GasDevice
@synthesize createTime;
@synthesize ch4;
@synthesize co;
@synthesize name;
@synthesize sensorId;
@synthesize switchStatus;

-(id)initWithDictionary:(NSDictionary *)dic{
    if (dic == nil || dic.count == 0) {
        return nil;
    }
    self = [super init];
    if (self) {
        self.createTime = [dic objectForKey:@"createTime"];
        self.ch4 = [dic objectForKey:@"ch4"];
        self.co = [dic objectForKey:@"co"];
        self.name = [dic objectForKey:@"name"];
        self.sensorId = [dic objectForKey:@"sensorId"];
        self.switchStatus = [dic objectForKey:@"switchStatus"];
    }
    return self;
}

-(BOOL)isOnline{
    long min = [DateUtils getMinitusFromNow:createTime];
    if (min > 2) {
        return NO;
    }
    else{
        return YES;
    }
}
@end
