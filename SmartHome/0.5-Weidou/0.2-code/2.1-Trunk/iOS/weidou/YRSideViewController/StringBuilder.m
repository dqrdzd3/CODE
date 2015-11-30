//
//  StringBuilder.m
//  Weidou
//
//  Created by wbiao on 14/12/1.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "StringBuilder.h"
short iBigMax = 8000;
short iSmallMin = -8000;
BOOL bWeitouIsGj;
@implementation StringBuilder

//解析捕获到的数据，并根据阀值进行1，0转换
//此处获取的数据1，0有时是反得(?)所以在这里对国内标准和国际标准的耳机进行适配
+(NSString *)AnalyzeShortToBinary:(short*)sourceData :(NSInteger)isize{
    @autoreleasepool {
        NSString *strTarget = @"";
        //NSLog(@"bWeitouIsGj:%d",bWeitouIsGj);
        for (int i = 0; i < isize; i++) {
            NSString *strTmpBinary = @"";
            if (sourceData[i] > iBigMax) {
                if (bWeitouIsGj) {
                    strTmpBinary = @"1";
                }
                else{
                    strTmpBinary = @"0";
                }
            }
            else if (sourceData[i] < iSmallMin){
                if (bWeitouIsGj) {
                    strTmpBinary = @"0";
                }
                else{
                    strTmpBinary = @"1";
                }
            }
            else
                continue;
            //NSLog(@"sourceData:%i",sourceData[i]);
            strTarget = [NSString stringWithFormat:@"%@%@",strTarget,strTmpBinary];
        }
        return strTarget;
    }
}

//+(NSString *)AnalyzeDataToBinary:(NSString *)sourceData{
//    NSArray *arySourceData = [sourceData componentsSeparatedByString:@","];
//    NSString *strTarget = @"";
//    for (int i = 0; i < arySourceData.count; i++) {
//        //NSLog(@"AnalyzeDataToBinary:%d",i);
//        NSString *strNum = [arySourceData objectAtIndex:i];
//        NSString *strTmpBinary = @"";
//        if ([strNum integerValue] > 8600) {
//            strTmpBinary = @"1";
//        }
//        else if ([strNum integerValue] < -8600){
//            strTmpBinary = @"0";
//        }
//        else{
//            continue;
//        }
//        strTarget = [NSString stringWithFormat:@"%@%@",strTarget,strTmpBinary];
//    }
//    return strTarget;
//}

//分段替换
+(NSString *)SquareWaveAdapter:(NSString *)wave{
    NSString *strSquareWave = wave;
//    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"10011110" withString:@"10001110"];
//    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"01100001" withString:@"01110001"];
//    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"011001" withString:@"01110001"];
//    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"100110" withString:@"10001110"];
    
    
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"01111111110" withString:@"011110"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"0111111110" withString:@"011110"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"011111110" withString:@"011110"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"01111110" withString:@"011110"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"0111110" withString:@"011110"];
    
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"10000000001" withString:@"100001"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"1000000001" withString:@"100001"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"100000001" withString:@"100001"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"10000001" withString:@"100001"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"1000001" withString:@"100001"];
    
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"10001" withString:@"1001"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"01110" withString:@"0110"];

    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"11" withString:@"1"];
    strSquareWave = [strSquareWave stringByReplacingOccurrencesOfString:@"00" withString:@"0"];
    return strSquareWave;
}

//分段
+(NSArray *)SectionBinary:(NSString *)binary{
    //@autoreleasepool{
        NSString *strbinary = binary;
        NSArray *arybinary = [strbinary componentsSeparatedByString:SECTION_STRING];
        NSMutableArray *maryTemp = [[NSMutableArray alloc]init];
        for (int i = arybinary.count-1;i >= 0;i--) {
            [maryTemp addObject:[arybinary objectAtIndex:i]];
        }
        
        NSMutableArray *maryBinary = [[NSMutableArray alloc]init];
        NSString *strSectionHead = [SECTION_STRING substringFromIndex:(SECTION_STRING.length-2)];
        NSString *strSectionEnd = [SECTION_STRING substringToIndex:(SECTION_STRING.length-2)];
        
        for (NSInteger i = 0; i < maryTemp.count; i++) {
            NSString *strTempCellBinary = [maryTemp objectAtIndex:i];
            if (strTempCellBinary.length != 0) {
//                if (i == 0) {
//                    continue;
//                    strTempCellBinary = [NSString stringWithFormat:@"%@%@%@",strSectionHead,[strTempCellBinary substringFromIndex:strTempCellBinary.length-22],strSectionEnd];
//                    [maryBinary addObject:strTempCellBinary];
//                }
//                else if (i == (maryTemp.count-1)){
//                    continue;
//                    strTempCellBinary = [NSString stringWithFormat:@"%@%@%@",strSectionHead,[strTempCellBinary substringToIndex:22],strSectionEnd];
//                    [maryBinary addObject:strTempCellBinary];
//                }
//                else{
                    strTempCellBinary = [NSString stringWithFormat:@"%@%@%@",strSectionHead,strTempCellBinary,strSectionEnd];
                    [maryBinary addObject:strTempCellBinary];
                //}
            }
        }
        return maryBinary;
    //}
}

+ (NSString *)stringByReversed:(NSString *)string
{
    NSMutableString *s = [NSMutableString string];
    for (NSUInteger i = string.length; i> 0; i--) {
        [s appendString:[string substringWithRange:NSMakeRange(i-1, 1)]];
    }
    return s;
}
//解析方波
+(NSString *)adptFourier:(NSString *)fourier{
    @try {
        NSString *temp = @"";
        NSString *currentNum = @"";
        NSString *strbinaryByte = @"";
        if (fourier.length < 22) {
            return nil;
        }
        /* 一个byte的长度是10位 */
        for (int i = 0; i <= 10; i++) {
            temp = [fourier substringWithRange:NSMakeRange(i * 2,2)];
            if ([temp isEqualToString:@"01"]) {
                currentNum = @"0";
            }
            else if ([temp isEqualToString:@"00"]) {
                currentNum = @"1";
            }
            else if ([temp isEqualToString:@"11"]) {
                currentNum = @"0";
            }
            else if ([temp isEqualToString:@"10"]) {
                currentNum = @"1";
            }
            else
                continue;
            strbinaryByte = [NSString stringWithFormat:@"%@%@",strbinaryByte,currentNum];
            
        }
        return strbinaryByte;
        //return [self stringByReversed:strbinaryByte];
    } @catch (NSException *e) {
        NSLog(@"adptFourier_Exception:%@",e.description);
    }
    return @"";
}

//二进制字符串转换为16进制
+ (NSString *)hexStringFromString:(NSString *)string{
    NSString *strH = [string substringToIndex:4];
    NSString *strL = [string substringFromIndex:4];
    return [NSString stringWithFormat:@"%@%@",[self AdpHexStringFromString:strH],[self AdpHexStringFromString:strL]];
}
//hexStringFromString 用到的转换过程
+ (NSString *)AdpHexStringFromString:(NSString *)string{
    if ([string isEqualToString:@"0000"]) {
        return @"0";
    }
    else if ([string isEqualToString:@"0001"]){
        return @"1";
    }
    else if ([string isEqualToString:@"0010"]){
        return @"2";
    }
    else if ([string isEqualToString:@"0011"]){
        return @"3";
    }
    else if ([string isEqualToString:@"0100"]){
        return @"4";
    }
    else if ([string isEqualToString:@"0101"]){
        return @"5";
    }
    else if ([string isEqualToString:@"0110"]){
        return @"6";
    }
    else if ([string isEqualToString:@"0111"]){
        return @"7";
    }
    else if ([string isEqualToString:@"1000"]){
        return @"8";
    }
    else if ([string isEqualToString:@"1001"]){
        return @"9";
    }
    else if ([string isEqualToString:@"1010"]){
        return @"A";
    }
    else if ([string isEqualToString:@"1011"]){
        return @"B";
    }
    else if ([string isEqualToString:@"1100"]){
        return @"C";
    }
    else if ([string isEqualToString:@"1101"]){
        return @"D";
    }
    else if ([string isEqualToString:@"1110"]){
        return @"E";
    }
    else if ([string isEqualToString:@"1111"]){
        return @"F";
    }
    return @"";
}

//奇偶校验和头尾校验
+(BOOL)BinaryCheck:(NSString *)head end:(NSString *)end contenthex:(NSString *)contenthex{
    if ([head isEqualToString:@"0"] && [end isEqualToString:@"1"] && [self HexContentEndCharCheck:contenthex] == 1) {
        return YES;
    }
    else{
        return NO;
    }
}
//奇偶校验
+(NSInteger)HexContentEndCharCheck:(NSString *)HexContent{
    NSString *strEndChar = [HexContent substringFromIndex:HexContent.length-1];
    if ([strEndChar isEqualToString:@"1"]) {
        return 1;
    }
    else if ([strEndChar isEqualToString:@"3"]){
        return 1;
    }
    else if ([strEndChar isEqualToString:@"5"]){
        return 1;
    }
    else if ([strEndChar isEqualToString:@"7"]){
        return 1;
    }
    else if ([strEndChar isEqualToString:@"9"]){
        return 1;
    }
    else if ([strEndChar isEqualToString:@"B"]){
        return 1;
    }
    else if ([strEndChar isEqualToString:@"D"]){
        return 1;
    }
    else if ([strEndChar isEqualToString:@"F"]){
        return 1;
    }
    else
        return 0;
}

@end
