//
//  AlarmHistoryModel.h
//  SmartHome
//
//  Created by 李静 on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//


@interface AlarmHistoryModel : NSObject
//历史报警标识
@property(nonatomic,copy) NSString *ma001;
//值
@property(nonatomic,copy) NSString *ma002;
//状态
@property(nonatomic,copy) NSString *ma003;
//类型
@property(nonatomic,copy) NSString *ma004;
//时间
@property(nonatomic,copy) NSString *ma005;
//设备id
@property(nonatomic,copy) NSString *ma006;
//备注1
@property(nonatomic,copy) NSString *ma007;
//备注2
@property(nonatomic,copy) NSString *ma008;

-(id)initWithDictionary:(NSDictionary *)dic;
@end
