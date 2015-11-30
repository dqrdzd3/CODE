//
//  FrameContent.m
//  Weidou
//
//  Created by wbiao on 14/12/3.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "FrameContent.h"

#define CURGAS_CH2O
//#define CURGAS_CO
//#define CURGAS_ALCOHOL
@implementation FrameContent
@synthesize strName;
@synthesize strData;
@synthesize iPower;
@synthesize iUtilitytime;
@synthesize strVer;
@synthesize delegate;

-(void)ParserFrame:(NSArray *)aryframe{
    NSString *strFunction = [aryframe objectAtIndex:0];
    if (aryframe.count == 5) {
        NSLog(@"可用:%@,%@,%@,%@,%@",aryframe[0],aryframe[1],aryframe[2],aryframe[3],aryframe[4]);
    }
    else if (aryframe.count == 4){
        NSLog(@"可用:%@,%@,%@,%@",aryframe[0],aryframe[1],aryframe[2],aryframe[3]);
    }
    NSInteger intValue = strtoul([strFunction UTF8String],0,16);
    NSInteger igas = intValue >> 5;
    switch (igas) {
        case HOME_NAME_CH2O_OB:
#ifdef CURGAS_CH2O
            self.strName = HOME_NAME_CH2O;
            [delegate FrameContentUpdateName:self.strName];
#endif
            break;
        case HOME_NAME_CO_OB:
#ifdef CURGAS_CO
            self.strName = HOME_NAME_CO;
            [delegate FrameContentUpdateName:self.strName];
#endif
            return;
            break;
        case HOME_NAME_ALCOHOL_OB:
#ifdef CURGAS_ALCOHOL
            self.strName = HOME_NAME_ALCOHOL;
            [delegate FrameContentUpdateName:self.strName];
#endif
            return;
            break;
        default:
            return;
            break;
    }
    NSInteger ifunction = intValue << 3 & 0xFF;
    NSInteger iDataSize = strtoul([[aryframe objectAtIndex:1] UTF8String],0,16);
    
    switch (ifunction) {
        case WEIDOU_TYPE_VER:
        {
            if (iDataSize == 1) {
                NSString *strTmpVer = [aryframe objectAtIndex:2];
                NSInteger IntegerVer = strtoul([strTmpVer UTF8String],0,16);
                NSInteger data = IntegerVer & 0xFF;
                self.strVer = [NSString stringWithFormat:@"%.2f",data/100.0];
                [delegate FrameContentUpdateVer:self.strVer];
            }
            else{
                NSLog(@"Error:数据长度%d",iDataSize);
            }
        }
            break;
        case WEIDOU_TYPE_DATA:
        {
            if (iDataSize == 2) {
                NSLog(@"WEIDOU_TYPE_DATA");
                NSString *strHTmpData = [aryframe objectAtIndex:2];
                NSString *strLTmpData = [aryframe objectAtIndex:3];
                NSInteger IntegerHData = strtoul([strHTmpData UTF8String],0,16);
                NSInteger IntegerLData = strtoul([strLTmpData UTF8String],0,16);
                NSInteger data = ((IntegerHData << 8) & 0xFF00)+(IntegerLData & 0xFF);
                
                float MaxValue = 0.0;
                if ([self.strName isEqualToString:HOME_NAME_CH2O]) {
                    self.strData = [NSString stringWithFormat:@"%.2f",data/100.0];
                    MaxValue = CH2O_HIGH;
                }
                else if ([self.strName isEqualToString:HOME_NAME_ALCOHOL]){
                    self.strData = [NSString stringWithFormat:@"%.3f",data/1000.0];
                     MaxValue = ALCOHOL_MGL_HIGH;
                }
                else if ([self.strName isEqualToString:HOME_NAME_CO]){
                    self.strData = [NSString stringWithFormat:@"%.1f",(float)data];
                    MaxValue = CO_HIGH;
                }
                
                if ([self.strData floatValue] <= MaxValue) {
                    [delegate FrameContentUpdateData:self.strData];
                }
                else{
                    self.strData = @"2.0";
                }
            }
            else{
                NSLog(@"Error:数据长度%d",iDataSize);
            }
            
        }
            break;
        case WEIDOU_TYPE_POWER:
        {
            if (iDataSize == 1) {
                NSLog(@"WEIDOU_TYPE_POWER");
                NSString *strTmpPower = [aryframe objectAtIndex:2];
                NSInteger IntegerPower = strtoul([strTmpPower UTF8String],0,16);
                NSInteger data = IntegerPower & 0xFF;
                iPower = data;
                [delegate FrameContentUpdatePower:iPower];
            }
            else{
                NSLog(@"Error:数据长度%d",iDataSize);
            }
            
        }
            break;
        case WEIDOU_TYPE_USEOFTIME:
        {
            if (iDataSize == 2) {
                NSString *strHTmpTime = [aryframe objectAtIndex:2];
                NSString *strLTmpTime = [aryframe objectAtIndex:3];
                NSInteger IntegerHTime = strtoul([strHTmpTime UTF8String],0,16);
                NSInteger IntegerLTime = strtoul([strLTmpTime UTF8String],0,16);
                NSInteger data = ((IntegerHTime << 8) & 0xFF00)+(IntegerLTime & 0xFF);
                iUtilitytime = data;
                [delegate FrameContentUpdateUtime:iUtilitytime];
            }
            else{
                NSLog(@"Error:数据长度%d",iDataSize);
            }
        }
            break;
        default:
            break;
    }
}

@end
