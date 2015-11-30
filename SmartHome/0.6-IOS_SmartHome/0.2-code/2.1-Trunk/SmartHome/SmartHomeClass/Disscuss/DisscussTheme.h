//
//  DisscussTheme.h
//  SmartHome
//
//  Created by 李静 on 14-11-12.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

@interface DisscussTheme : NSObject
//讨论区标识
@property(nonatomic,copy) NSString *ma001;
//主题
@property(nonatomic,copy) NSString *ma002;
//内容
@property(nonatomic,copy) NSString *ma003;
//建立时间
@property(nonatomic,copy) NSString *ma004;
//用户id
@property(nonatomic,copy) NSString *ma005;
//备注1
@property(nonatomic,copy) NSString *ma006;
//备注2
@property(nonatomic,copy) NSString *ma007;

-(id)initWithDictionary:(NSDictionary *)dic;
@end
