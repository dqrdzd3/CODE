//
//  CartoonInfo.m
//  SmartHome
//
//  Created by 李静 on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "CartoonInfo.h"

@implementation CartoonInfo
@synthesize talkText;
@synthesize expressImages;
@synthesize sensorName;
@synthesize sensorValue;
@synthesize CartoonState;

-(id)initWithDictionary:(NSDictionary *)dic{
    self = [super init];
    if (self) {
        self.talkText = [[NSMutableArray alloc]initWithArray:[dic objectForKey:@"talkText"]];
        self.expressImages = [[NSMutableArray alloc]initWithArray:[dic objectForKey:@"expressImages"]];
        self.sensorName = [dic objectForKey:@"sensorName"];
        self.sensorValue = [dic objectForKey:@"sensorValue"];
        self.CartoonState = [[NSMutableArray alloc]initWithArray:[dic objectForKey:@"CartoonState"]];
    }
    return self;
}


-(CartoonInfo *)init{
    self = [super init];
    if (self) {
        talkText = [[NSMutableArray alloc]init];
        expressImages = [[NSMutableArray alloc]init];
        CartoonState = [[NSMutableArray alloc]init];
    }
    return self;
}
@end
