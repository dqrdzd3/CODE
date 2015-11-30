//
//  AirDevice.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-11-1.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface AirDevice : NSObject
@property(nonatomic,copy) NSString *createTime;
@property(nonatomic,copy) NSString *c6h6;
@property(nonatomic,copy) NSString *ch2o;
@property(nonatomic,copy) NSString *co2;

@property(nonatomic,copy) NSString *humidity;
@property(nonatomic,copy) NSString *pm25;
@property(nonatomic,copy) NSString *temperature;
@property(nonatomic,copy) NSString *voc;

@property(nonatomic,copy) NSString *name;
@property(nonatomic,copy) NSString *sensorId;
- (BOOL)isOnline;
-(id)initWithDictionary:(NSDictionary *)dic;
@end
