//
//  DateUtils.m
//  SmartHome
//
//  Created by 李静 on 14-11-25.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "DateUtils.h"

@implementation DateUtils
//获取距离当前时间分钟
+(long)getMinitusFromNow:(NSString *)time{
    int result = 0;
    NSDate *date = [[NSDate alloc] init];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSDate *fromDate = [dateFormatter dateFromString:time];
    
    long second = [date timeIntervalSinceDate:fromDate];
    result = second/60;
    
    return result;
}
+(NSArray *)getHoursFromNow{
    NSDate *date = [[NSDate alloc] init];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"HH"];
    NSString *strcurhour = [dateFormatter stringFromDate:date];
    NSInteger icurhour = [strcurhour integerValue];
    NSMutableArray *maryHours = [[NSMutableArray alloc]init];
    for (NSInteger i = 0; i < 24; i++) {
        icurhour++;
        if (icurhour >= 24) {
            icurhour = 0;
        }
        [maryHours addObject:[NSString stringWithFormat:@"%d",icurhour]];
    }
    return maryHours;
}

+(NSArray *)getDaysFromNow{
    NSMutableArray *maryDays = [[NSMutableArray alloc]init];
    NSDate *yesterdayDate = [NSDate dateWithTimeInterval:-(24*60*60) sinceDate:[NSDate date]];
    for (NSInteger i = 0; i < 30; i++) {
        NSDate *yesterday = [NSDate dateWithTimeInterval:-(24*60*60) sinceDate:yesterdayDate];
        
        NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
        [dateFormatter setDateFormat:@"MM/dd"];
        NSString *strYesterday = [dateFormatter stringFromDate:yesterdayDate];
        [maryDays addObject:strYesterday];
        yesterdayDate = yesterday;
    }
    return maryDays;
}

+(NSString *)DateFormat:(NSString *)createTime{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:@"yyyy-MM-dd"];
    NSDate *  senddate = [NSDate date];
    //结束时间
    NSDate *endDate = [dateFormatter dateFromString:[createTime substringToIndex:10]];
    //当前时间
    NSDate *senderDate = [dateFormatter dateFromString:[dateFormatter stringFromDate:senddate]];
    //得到相差秒数
    NSTimeInterval time = [endDate timeIntervalSinceDate:senderDate];
    int days = ((int)time)/(3600*24);
    
    NSString *result;
    if (days == 0) {
        result = [NSString stringWithFormat:@"今天%@",[createTime substringFromIndex:10]];
    }
    else if (days == -1) {
        result = [NSString stringWithFormat:@"昨天%@",[createTime substringFromIndex:10]];
    }
    else{
        result = createTime;
    }
    return  result;
}
+(NSString *)WeekToCh:(NSDate *)date{
    NSInteger week = [self dayOfWeekType:date];
    if (week == 1) {
        return @"周一";
    }
    else if (week == 2){
        return @"周二";
    }
    else if (week == 3){
        return @"周三";
    }
    else if (week == 4){
        return @"周四";
    }
    else if (week == 5){
        return @"周五";
    }
    else if (week == 6){
        return @"周六";
    }
    else if (week == 7){
        return @"周日";
    }
    else{
        return @"未知";
    }
}
+(NSString* )dayOfWeekFromNow:(NSDate *)date{
    
    NSDateFormatter *fmtter =[[NSDateFormatter alloc] init];
    [fmtter setLocale:[[NSLocale alloc] initWithLocaleIdentifier:@"en_US"]];
    [fmtter setTimeZone:[NSTimeZone timeZoneForSecondsFromGMT:0]];
    [fmtter setDateFormat:@"EEE"];
    return [fmtter stringFromDate:date];
}
+(NSInteger)dayOfWeekType:(NSDate *)date{
    NSString* dayString = [self dayOfWeekFromNow:date];
    if (nil == dayString) {
        return 0;
    }
    if ([dayString hasPrefix:@"Mon"]) {
        return 2;
    }
    if ([dayString hasPrefix:@"Tue"]) {
        return 3;
    }
    if ([dayString hasPrefix:@"Wed"]) {
        return 4;
    }
    if ([dayString hasPrefix:@"Thu"]) {
        return 5;
    }
    if ([dayString hasPrefix:@"Fri"]) {
        return 6;
    }
    if ([dayString hasPrefix:@"Sat"]) {
        return 7;
    }
    if ([dayString hasPrefix:@"Sun"]) {
        return 1;
    }
    return 0;
}
+(NSArray *)getWeeksFromNow{
    NSMutableArray *aryweeks = [[NSMutableArray alloc]init];
    for (NSInteger i = 0; i < 7; i++) {
        NSDate *newDate = [[NSDate alloc] initWithTimeIntervalSinceReferenceDate:([[NSDate date] timeIntervalSinceReferenceDate] - i*24*3600)];
        [aryweeks addObject:[self WeekToCh:newDate]];
    }
    return aryweeks;
}
@end
