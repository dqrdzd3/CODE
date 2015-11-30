//
//  DisscussDetailModel.h
//  SmartHome
//  讨论区主题详情
//  Created by 李静 on 14-11-12.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//


@interface DisscussDetailModel : NSObject
//用户留言标识
@property(nonatomic,copy) NSString *ma001;
//讨论区ID
@property(nonatomic,copy) NSString *ma002;
//回复内容
@property(nonatomic,copy) NSString *ma003;
//回复时间
@property(nonatomic,copy) NSString *ma004;
//用户id
@property(nonatomic,copy) NSString *ma005;
//备注1
@property(nonatomic,copy) NSString *ma006;
//备注2
@property(nonatomic,copy) NSString *ma007;
//用户昵称
@property(nonatomic,copy) NSString *ma008;
//用户头像
@property(nonatomic,copy) NSString *ma009;

-(id)initWithDictionary:(NSDictionary *)dic;
@end
