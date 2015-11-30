//  卡通的图片和语言
//  CartoonInfo.h
//  SmartHome
//
//  Created by 李静 on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//


@interface CartoonInfo : NSObject
@property(nonatomic,copy) NSString *sensorName;
@property(nonatomic,copy) NSString *sensorValue;

@property(nonatomic,copy) NSMutableArray *CartoonState;
@property(nonatomic,copy) NSMutableArray *talkText;
@property(nonatomic,copy) NSMutableArray *expressImages;

-(id)initWithDictionary:(NSDictionary *)dic;
@end
