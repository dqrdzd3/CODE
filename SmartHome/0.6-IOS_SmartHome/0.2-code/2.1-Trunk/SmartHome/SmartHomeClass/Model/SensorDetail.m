//
//  SensorDetail.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-28.
//  Copyright (c) 2014年 河南汉威电子股份有限公司. All rights reserved.
//

#import "SensorDetail.h"
#import "DeviceUtil.h"
@implementation SensorDetail
@synthesize sensorId;
@synthesize name;
@synthesize localSoftVersion;
@synthesize remoteSoftVersion;
@synthesize localHardVersion;
@synthesize remoteHardVersion;
@synthesize airDevice;
@synthesize gasDevice;
@synthesize online;
-(id)initWithDictionary:(NSDictionary *)dic{
    if (dic == nil || dic.count == 0) {
        return nil;
    }
    self = [super init];
    if (self) {
        self.sensorId = [dic objectForKey:@"sensorId"];
        self.name = [dic objectForKey:@"name"];
        self.localSoftVersion = [dic objectForKey:@"localSoftVersion"];
        self.remoteSoftVersion = [dic objectForKey:@"remoteSoftVersion"];
        self.localHardVersion = [dic objectForKey:@"localHardVersion"];
        self.remoteHardVersion = [dic objectForKey:@"remoteHardVersion"];
    }
    return self;
}
-(BOOL)updateSoftVersion{
    if ([self.localSoftVersion integerValue] > [self.remoteSoftVersion integerValue]) {
        return YES;
    }
    else{
        return NO;
    }
}
-(BOOL)isOnline{
    if ([DeviceUtil getDeviceType:self.sensorId] == SENSOR_TYPE_AIR) {
        return airDevice.isOnline;
    }
    else{
        return gasDevice.isOnline;
    }
}
-(CartoonInfo *)getCartoonInfo{
    CartoonInfo *info = [[CartoonInfo alloc] init];
    if (airDevice == nil && gasDevice == nil) {
        [info.talkText addObject:EXPRESS_NO_DATA];
        [info.expressImages addObject:[UIImage imageNamed:@"0011.png"]];
        return info;
    }
    if (airDevice != nil) {
        if (sensorId.length != 0) {
            if (airDevice.temperature.length == 0) {
                airDevice.temperature = @"0.0";
            }
            if (airDevice.temperature.length != 0) {
                NSString *nametmp = @"温度:";
                double value = [airDevice.temperature doubleValue];
                if (value <= (TEMPERATURE_THRESHOLD+3) && value >= (TEMPERATURE_THRESHOLD-3)) {
                    NSString *talkText = [nametmp stringByAppendingString:EXPRESS_TEMPERATURE_NORMAL];
                    [info.talkText addObject:talkText];
                    [info.expressImages addObject:[UIImage imageNamed:@"001.png"]];
                    [info.CartoonState addObject:@"1"];
                }else if(value >= TEMPERATURE_THRESHOLD){
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_TEMPERATURE_HOT]];
                    [info.expressImages addObject:[UIImage imageNamed:@"006.png"]];
                    [info.CartoonState addObject:@"2"];
                }else if(value < TEMPERATURE_THRESHOLD){
                    [info.CartoonState addObject:@"0"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_TEMPERATURE_COLD]];
                    [info.expressImages addObject:[UIImage imageNamed:@"004.png"]];
                }
            }
            
            //湿度
            if (airDevice.humidity.length == 0) {
                airDevice.humidity = @"0.0";
            }
            if(airDevice.humidity.length != 0){
                NSString *nametmp = @"湿度:";
                double value = [airDevice.humidity doubleValue];
                if (value <= (HUMIDITY_THRESHOLD+20) && value >= (HUMIDITY_THRESHOLD-20)) {
                    NSString *talkText = [nametmp stringByAppendingString:EXPRESS_HUMIDITY_NORMAL];
                    [info.talkText addObject:talkText];
                    [info.expressImages addObject:[UIImage imageNamed:@"001.png"]];
                    [info.CartoonState addObject:@"1"];
                }else if(value >= HUMIDITY_THRESHOLD){
                    [info.CartoonState addObject:@"2"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_HUMIDITY_OVER]];
                    [info.expressImages addObject:[UIImage imageNamed:@"0011.png"]];
                }else if(value < HUMIDITY_THRESHOLD){
                    [info.CartoonState addObject:@"0"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_HUMIDITY_LESS]];
                    [info.expressImages addObject:[UIImage imageNamed:@"0010.png"]];
                }
            }
            
            //co2
            if (airDevice.co2.length == 0) {
                airDevice.co2 = @"0.0";
            }
            if(airDevice.co2.length != 0){
                NSString *nametmp = @"二氧化碳:";
                double value = [airDevice.co2 doubleValue];
                if (value <= (CO2_THRESHOLD+10) && value >= (CO2_THRESHOLD-10)) {
                    NSString *talkText = [nametmp stringByAppendingString:EXPRESS_CO2_OVER];
                    [info.CartoonState addObject:@"1"];
                    [info.talkText addObject:talkText];
                    [info.expressImages addObject:[UIImage imageNamed:@"004.png"]];
                }else if(value >= CO2_THRESHOLD){
                    [info.CartoonState addObject:@"2"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_CO2_OVER_MORE]];
                    [info.expressImages addObject:[UIImage imageNamed:@"007.png"]];
                }else if(value < CO2_THRESHOLD){
                    [info.CartoonState addObject:@"0"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_CO2_NORMAL]];
                    [info.expressImages addObject:[UIImage imageNamed:@"004.png"]];
                }
            }
            
            //PM25
            if (airDevice.pm25.length == 0) {
                airDevice.pm25 = @"0.0";
            }
            if(airDevice.pm25.length != 0){
                NSString *nametmp = @"PM2.5:";
                double value = [airDevice.pm25 doubleValue];
                if(value < PM25_THRESHOLD){
                    [info.CartoonState addObject:@"0"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_PM25_NORMAL]];
                    [info.expressImages addObject:[UIImage imageNamed:@"001.png"]];
                }
                else if (value <= PM25_HIGH_THRESHOLD && value >= PM25_THRESHOLD) {
                    [info.CartoonState addObject:@"1"];
                    NSString *talkText = [nametmp stringByAppendingString:EXPRESS_PM25_OVER];
                    [info.talkText addObject:talkText];
                    [info.expressImages addObject:[UIImage imageNamed:@"004.png"]];
                }
                else if(value > PM25_HIGH_THRESHOLD){
                    [info.CartoonState addObject:@"2"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_PM25_OVER_MORE]];
                    [info.expressImages addObject:[UIImage imageNamed:@"0010.png"]];
                }
            }
            
            //VOC
            if (airDevice.voc.length == 0) {
                airDevice.voc = @"0.0";
            }
            if(airDevice.voc.length != 0){
                NSString *nametmp = @"VOC:";
                double value = [airDevice.voc doubleValue];
                if(value < VOC_NORMAL){
                    [info.CartoonState addObject:@"0"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_VOC_NORMAL]];
                    [info.expressImages addObject:[UIImage imageNamed:@"001.png"]];
                }else if (value <= VOC_HIGH && value >= VOC_MIDDLE) {
                    [info.CartoonState addObject:@"1"];
                    NSString *talkText = [nametmp stringByAppendingString:EXPRESS_VOC_MIDDLE];
                    [info.talkText addObject:talkText];
                    [info.expressImages addObject:[UIImage imageNamed:@"004.png"]];
                }else if(value > VOC_HIGH){
                    [info.CartoonState addObject:@"2"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_VOC_HIGH]];
                    [info.expressImages addObject:[UIImage imageNamed:@"0010.png"]];
                }
            }
            
        }
    }
    if (gasDevice!=nil) {
        if (sensorId.length != 0) {
            //ch4
            if (gasDevice.ch4.length == 0) {
                gasDevice.ch4 = @"0.0";
            }
            if (gasDevice.ch4.length != 0) {
                NSString *nametmp = @"";
                double value = [gasDevice.ch4 doubleValue];
                
                if(value < CH4_THRESHOLD){
                    [info.CartoonState addObject:@"1"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_CH4_NORMAL]];
                    [info.expressImages addObject:[UIImage imageNamed:@"003.png"]];
                }
                else if(value >= CH4_THRESHOLD){
                    [info.CartoonState addObject:@"2"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_CH4_DANGEROUS]];
                    [info.expressImages addObject:[UIImage imageNamed:@"006.png"]];
                }
            }
            
            //co
            if (gasDevice.co.length == 0) {
                gasDevice.co = @"0.0";
            }
            if (gasDevice.co.length != 0) {
                NSString *nametmp = @"";
                double value = [gasDevice.co doubleValue];
                
                if(value < CO_THRESHOLD){
                    [info.CartoonState addObject:@"1"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_CO_NORMAL]];
                    [info.expressImages addObject:[UIImage imageNamed:@"003.png"]];
                }
                else if(value >= CO_THRESHOLD){
                    [info.CartoonState addObject:@"2"];
                    [info.talkText addObject:[nametmp stringByAppendingString:EXPRESS_CO_DANGEROUS]];
                    [info.expressImages addObject:[UIImage imageNamed:@"0011.png"]];
                }
            }
        }
    }
     return info;
}


@end
