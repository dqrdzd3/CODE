//
//  StringUtils.h
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface StringUtils : NSObject
//判断邮箱是否合法
+(BOOL)isValidateEmail:(NSString *)email;
+(BOOL)isValidateUserName:(NSString *)username;
@end
