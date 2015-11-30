//
//  ServerResult.h
//  
//
//  Created by 李静 on 14-10-31.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ServerResult : NSObject
@property(nonatomic,copy) NSString *message;
@property(nonatomic,copy) NSString *tableName;
@property(nonatomic,copy) NSString *code;
@property(nonatomic,assign) int count;
@property(nonatomic,copy) id dataObject;
@property(nonatomic,copy) id data;
- (BOOL)isOperateSuccess;
-(id)initWithDictionary:(NSDictionary *)dic;
@end
