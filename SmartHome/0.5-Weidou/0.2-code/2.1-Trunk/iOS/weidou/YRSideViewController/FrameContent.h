//
//  FrameContent.h
//  Weidou
//
//  Created by wbiao on 14/12/3.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Macro.h"

@protocol FrameContentDelegate <NSObject>

-(void)FrameContentUpdateName:(NSString *)name;
-(void)FrameContentUpdateVer:(NSString *)ver;
-(void)FrameContentUpdateData:(NSString *)data;
-(void)FrameContentUpdatePower:(NSInteger)power;
-(void)FrameContentUpdateUtime:(NSInteger)utime;

@end
@interface FrameContent : NSObject

@property(nonatomic,strong)id<FrameContentDelegate>delegate;
@property(nonatomic,strong)NSString *strName;//气体名字
@property(nonatomic,strong)NSString *strVer;//版本
@property(nonatomic,strong)NSString *strData;//数据
@property(nonatomic)NSInteger iPower;//电量0 1 2 3 4 五档
@property(nonatomic)NSInteger iUtilitytime;//分钟

-(void)ParserFrame:(NSArray *)aryframe;
@end
