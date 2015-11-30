//
//  User.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-21.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface User : NSObject
  //用户信息标识
@property (nonatomic, copy) NSString *ma001;//USERID
//省
@property (nonatomic, copy) NSString *ma002;
/*
 市
*/
@property (nonatomic, copy) NSString *ma003;
@property (nonatomic, copy) NSString *ma004;//地址
@property (nonatomic, copy) NSString *ma005;//EMAIL
@property (nonatomic, copy) NSString *ma006;//电话PHONE
@property (nonatomic, copy) NSDictionary *ma007;//添加时间
@property (nonatomic, copy) NSString *ma008;//登录名
@property (nonatomic, copy) NSString *ma009;//密码
@property (nonatomic, copy) NSString *ma010;//SESSIONID
@property (nonatomic, copy) NSString *ma011;//备注1
@property (nonatomic, copy) NSString *ma012;//备注2 头像地址
@property (nonatomic, copy) NSString *ma013;
@property (nonatomic, copy) NSString *ma014;//经度
@property (nonatomic, copy) NSString *ma015;//纬度
@property (nonatomic, copy) NSString *ma016;
@property (nonatomic, copy) NSString *ma017;
@property (nonatomic, copy) NSString *ma018;
@property (nonatomic, copy) NSString *ma019;
@property (nonatomic, copy) NSString *ma020;

-(id)initWithDictionary:(NSDictionary *)dic;

@end
