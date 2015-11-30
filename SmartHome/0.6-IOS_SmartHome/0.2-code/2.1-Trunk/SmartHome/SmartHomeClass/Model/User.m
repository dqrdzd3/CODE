//
//  User.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-21.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "User.h"

@implementation User
@synthesize ma001;
@synthesize ma002;
@synthesize ma003;
@synthesize ma004;
@synthesize ma005;
@synthesize ma006;
@synthesize ma007;
@synthesize ma008;
@synthesize ma009;
@synthesize ma010;
@synthesize ma011;
@synthesize ma012;
@synthesize ma013;
@synthesize ma014;
@synthesize ma015;
@synthesize ma016;
@synthesize ma017;
@synthesize ma018;
@synthesize ma019;
@synthesize ma020;

-(id)initWithDictionary:(NSDictionary *)dic{
    self = [super init];
    if (self) {
        self.ma001 = [dic objectForKey:@"ma001"];
        self.ma002 = [dic objectForKey:@"ma002"];
        self.ma003 = [dic objectForKey:@"ma003"];
        self.ma004 = [dic objectForKey:@"ma004"];
        self.ma005 = [dic objectForKey:@"ma005"];
        self.ma006 = [dic objectForKey:@"ma006"];
        self.ma007 = [dic objectForKey:@"ma007"];
        self.ma008 = [dic objectForKey:@"ma008"];
        self.ma009 = [dic objectForKey:@"ma009"];
        self.ma010 = [dic objectForKey:@"ma010"];
        self.ma011 = [dic objectForKey:@"ma011"];
        self.ma012 = [dic objectForKey:@"ma012"];
        self.ma013 = [dic objectForKey:@"ma013"];
        self.ma014 = [dic objectForKey:@"ma014"];
        self.ma015 = [dic objectForKey:@"ma015"];
        self.ma016 = [dic objectForKey:@"ma016"];
        self.ma017 = [dic objectForKey:@"ma017"];
        self.ma018 = [dic objectForKey:@"ma018"];
        self.ma019 = [dic objectForKey:@"ma019"];
        self.ma020 = [dic objectForKey:@"ma020"];
    }
    return self;
}
@end
