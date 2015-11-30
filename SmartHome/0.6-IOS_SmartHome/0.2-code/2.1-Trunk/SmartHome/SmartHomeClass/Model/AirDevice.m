//
//  AirDevice.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-11-1.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import "AirDevice.h"
#import "DateUtils.h"
@implementation AirDevice
@synthesize createTime;
@synthesize c6h6;
@synthesize ch2o;
@synthesize co2;
@synthesize humidity;
@synthesize name;
@synthesize pm25;
@synthesize sensorId;
@synthesize temperature;
@synthesize voc;

-(id)initWithDictionary:(NSDictionary *)dic{
    if (dic == nil || dic.count == 0) {
        return nil;
    }
    self = [super init];
    if (self) {
        self.createTime = [dic objectForKey:@"createTime"];
        self.c6h6 = [dic objectForKey:@"c6h6"];
        self.ch2o = [dic objectForKey:@"ch2o"];
        self.co2 = [dic objectForKey:@"co2"];
        self.humidity = [dic objectForKey:@"humidity"];
        self.name = [dic objectForKey:@"name"];
        self.pm25 = [dic objectForKey:@"pm25"];
        self.sensorId = [dic objectForKey:@"sensorId"];
        self.temperature = [dic objectForKey:@"temperature"];
        self.voc = [dic objectForKey:@"voc"];
    }
    return self;
}
-(BOOL)isOnline{
    long min =[DateUtils getMinitusFromNow:createTime];
    if (min > 2) {
        return NO;
    }
    else{
        return YES;
    }
}
@end
