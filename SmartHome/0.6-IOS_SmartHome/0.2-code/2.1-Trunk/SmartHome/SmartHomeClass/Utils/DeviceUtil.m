//
//  DeviceUtil.m
//  SmartHome
//
//  Created by 李静 on 14-11-24.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "DeviceUtil.h"

@implementation DeviceUtil
+(int)getDeviceType:(NSString *)deviceId{
    
    if ([deviceId length] < 1) {
        return SENSOR_TYPE_ERROR;
    }
    NSString *typeStr = [deviceId substringToIndex:1];
    int typeInt = [typeStr intValue];
    if (typeInt <= 4) {
        return SENSOR_TYPE_GAS;
    }
    else if (typeInt > 4 && typeInt <= 8){
        return SENSOR_TYPE_AIR;
    }
    else if (typeInt > 8 && typeInt <= 10){
        return SENSOR_TYPE_SMOKE;
    }
    return SENSOR_TYPE_ERROR;
}

+(NSString*)getDeviceName:(int)type{
    NSString *name = @"未定义设备";
    switch (type) {
        case 0:
            break;
        case 1:
            name = @"可燃气体";
            break;
        case 2:
            name = @"空气质量";
            break;
        case 3:
            name = @"烟雾传感器";
            break;
        default:
            name = @"未定义设备";
            break;
    }
    return name;
}

//判断设备编号是否合法
+(BOOL)isValidateDeviceNo:(NSString *)deviceNo {
    NSString *regex = @"^(([a-fA-F0-9]{8})|([a-fA-F0-9]{12}))$";
    // NSString *regex = @"^[a-fA-F0-9]{12}$";
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regex];
    return [emailTest evaluateWithObject:deviceNo];
}

@end
