//
//  StringBuilder.h
//  Weidou
//
//  Created by wbiao on 14/12/1.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "Macro.h"
#import "RealTimeModel.h"
@interface StringBuilder : NSObject

+(NSString *)adptFourier:(NSString *)fourier;
+(NSArray *)SectionBinary:(NSString *)binary;
+(NSString *)SquareWaveAdapter:(NSString *)wave;
+(NSString *)AnalyzeDataToBinary:(NSString *)sourceData;
+(NSString *)hexStringFromString:(NSString *)string;
+(NSString *)AdpHexStringFromString:(NSString *)string;
+(NSString *)AnalyzeShortToBinary:(short[])sourceData :(NSInteger)isize;
+ (NSString *)stringByReversed:(NSString *)string;
+(BOOL)BinaryCheck:(NSString *)head end:(NSString *)end contenthex:(NSString *)contenthex;
+(NSInteger)HexContentEndCharCheck:(NSString *)HexContent;
@end
