//
//  GasDevice.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-11-1.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface GasDevice : NSObject
@property(nonatomic,copy) NSString *createTime;
@property(nonatomic,copy) NSString *ch4;
@property(nonatomic,copy) NSString *co;
@property(nonatomic,copy) NSString *name;
@property(nonatomic,copy) NSString *sensorId;
@property(nonatomic,copy) NSString *switchStatus;
- (BOOL)isOnline;

-(id)initWithDictionary:(NSDictionary *)dic;
@end
