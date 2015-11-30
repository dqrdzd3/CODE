//
//  ServerResult.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-31.
//  Copyright (c) 2014年 河南汉威电子股份有限公司. All rights reserved.
//

#import "ServerResult.h"

@implementation ServerResult
@synthesize message;
@synthesize tableName;
@synthesize code;
@synthesize count;
@synthesize dataObject;
@synthesize data;

-(id)initWithDictionary:(NSDictionary *)dic{
    self = [super init];
    if (self) {
        self.message = [dic objectForKey:@"message"];
        self.tableName = [dic objectForKey:@"tableName"];
        self.code = [dic objectForKey:@"code"];
        self.count = [[dic objectForKey:@"count"] integerValue];
        self.dataObject = [dic objectForKey:@"dataObject"];
        self.data = [dic objectForKey:@"data"];
    }
    return self;
}


-(BOOL)isOperateSuccess{
    if ([self.code isEqualToString:SERVER_RESULT_CODE_SUCCESS]) {
        return YES
        ;
    }
    else{
        return NO;
    }
};
@end
