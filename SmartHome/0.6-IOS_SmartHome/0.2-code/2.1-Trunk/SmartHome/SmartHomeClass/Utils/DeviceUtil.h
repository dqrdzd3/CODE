//  设备工具类
//  DeviceUtil.h
//  SmartHome
//
//  Created by 李静 on 14-11-24.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DeviceUtil : NSObject
/**
 根据设备id 判断设备类型
 
 **/
+(int)getDeviceType:(NSString *)deviceId;
/**
 根据设备类型获取设备名称
 
 **/
+(NSString*)getDeviceName:(int)type;
//判断设备编号是否合法
+(BOOL)isValidateDeviceNo:(NSString *)deviceNo;

@end
