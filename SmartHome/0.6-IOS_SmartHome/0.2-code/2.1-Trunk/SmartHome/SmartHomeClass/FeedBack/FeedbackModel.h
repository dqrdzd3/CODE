//
//  FeedbackModel.h
//  SmartHome
//
//  Created by 李静 on 14-11-12.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//


@interface FeedbackModel : NSObject
//留言板标识
@property(nonatomic,copy) NSString *ma001;
//内容
@property(nonatomic,copy) NSString *ma002;
//创建时间
@property(nonatomic,copy) NSString *ma003;
//用户ID
@property(nonatomic,copy) NSString *ma004;
//回复内容
@property(nonatomic,copy) NSString *ma005;
//0未回复1已恢复
@property(nonatomic,copy) NSString *ma006;
//回复时间
@property(nonatomic,copy) NSString *ma007;

-(id)initWithDictionary:(NSDictionary *)dic;

@end
