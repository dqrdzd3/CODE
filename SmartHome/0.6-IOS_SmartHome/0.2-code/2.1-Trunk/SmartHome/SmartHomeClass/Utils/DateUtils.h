//
//  DateUtils.h
//  SmartHome
//
//  Created by 李静 on 14-11-25.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DateUtils : NSObject
//距离当前时间分钟数
+(long)getMinitusFromNow:(NSString *)time;
+(NSString* )dayOfWeekFromNow:(NSDate *)date;
+(NSInteger)dayOfWeekType:(NSDate *)date;
+(NSString *)WeekToCh:(NSDate *)date;
+(NSString *)DateFormat:(NSString *)createTime;

+(NSArray *)getWeeksFromNow;
+(NSArray *)getHoursFromNow;
+(NSArray *)getDaysFromNow;
@end
