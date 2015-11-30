//
//  RealTimeModel.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "RealTimeModel.h"

@implementation RealTimeModel
@synthesize strAverage;
@synthesize strMax;
@synthesize strEffect;
@synthesize strStatus;
@synthesize strBatteryPath;
@synthesize strUseTime;
@synthesize strPieValue;
@synthesize strUnit;
@synthesize brealStart;
@synthesize strName;
@synthesize maryHistoryM;
@synthesize maryHistoryS;
@synthesize strFightShare;

+ (RealTimeModel *)SharedRealTimeModel
{
    static RealTimeModel * sharedConstant;
    @synchronized(self)
    {
        if (!sharedConstant) {
            sharedConstant = [[RealTimeModel alloc] init];
        }
        return sharedConstant;
    }
}
@end
