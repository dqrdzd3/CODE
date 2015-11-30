//
//  RealTimeModel.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface RealTimeModel : NSObject

@property(nonatomic,strong)NSString *strName;
@property(nonatomic,strong)NSString *strUseTime;
@property(nonatomic,strong)NSString *strBatteryPath;
@property(nonatomic,strong)NSString *strPieValue;
@property(nonatomic,strong)NSString *strEffect;
@property(nonatomic,strong)NSString *strStatus;
@property(nonatomic,strong)NSString *strAverage;
@property(nonatomic,strong)NSString *strMax;
@property(nonatomic,strong)NSString *strUnit;
@property(nonatomic,strong)NSMutableArray *maryHistoryM;
@property(nonatomic,strong)NSMutableArray *maryHistoryS;
@property(nonatomic,strong)NSString *strFightShare;

@property BOOL brealStart;
+ (RealTimeModel *)SharedRealTimeModel;
@end
