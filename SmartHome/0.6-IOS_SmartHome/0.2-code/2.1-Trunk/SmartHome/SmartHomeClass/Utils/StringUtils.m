//
//  StringUtils.m
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "StringUtils.h"

@implementation StringUtils
//判断设备编号是否合法
+(BOOL)isValidateEmail:(NSString *)email {
    NSString *regex = @"^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$";
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regex];
    return [emailTest evaluateWithObject:email];
}
+(BOOL)isValidateUserName:(NSString *)username{
    NSString *regex = @"^[0-9]{11}$";
    NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regex];
    return [emailTest evaluateWithObject:username];
}
@end
