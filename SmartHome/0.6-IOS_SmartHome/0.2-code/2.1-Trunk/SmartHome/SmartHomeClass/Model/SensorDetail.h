//
//  SensorDetail.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-28.
//  Copyright (c) 2014年 河南汉威电子股份有限公司. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AirDevice.h"
#import "GasDevice.h"
#import "CartoonInfo.h"
@interface SensorDetail : NSObject

@property(nonatomic,strong)NSString *sensorId;
@property(nonatomic,strong)NSString *name;
@property(nonatomic,strong)NSString *online;
@property(nonatomic,strong)NSString *localSoftVersion;// //旧
@property(nonatomic,strong)NSString *remoteSoftVersion;// //新
@property(nonatomic,strong)NSString *localHardVersion;
@property(nonatomic,strong)NSString *remoteHardVersion;
@property(nonatomic,strong) AirDevice *airDevice;
@property(nonatomic,strong) GasDevice *gasDevice;
- (CartoonInfo *)getCartoonInfo;
- (BOOL)isOnline;
- (id)initWithDictionary:(NSDictionary *)dic;
- (BOOL)updateSoftVersion;
@end
