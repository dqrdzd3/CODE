//
//  HistoryView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "HistoryView.h"

@implementation HistoryView
@synthesize labelCurValue;
@synthesize labelUnit;
@synthesize bstart;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        iXmax = 30;
        maryHistoryDataM = [[NSMutableArray alloc]init];
        maryHistoryDataS = [[NSMutableArray alloc]init];
        [self LoadSubView];
    }
    return self;
}

-(void)ReloadData{
    labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    NSDictionary *attributes = @{NSFontAttributeName:labelCurValue.font};
    CGSize size = [labelCurValue.text sizeWithAttributes:attributes];
    CGRect frame = labelCurValue.frame;
    frame.size.width = size.width+5;
    labelCurValue.frame = frame;
    labelUnit.frame = CGRectMake(labelCurValue.frame.origin.x+labelCurValue.frame.size.width, 20, 50, 20);
    
    labelUnit.text = [RealTimeModel SharedRealTimeModel].strUnit;
}
-(void)CleanLinePoint{
    isec = 0;
    imin = 0;
    [maryHistoryDataM removeAllObjects];
    [maryHistoryDataS removeAllObjects];
    [ScrollLineviewM ReloadData];
    [ScrollLineviewS ReloadData];
}
-(void)LoadSubView{
    UILabel *labelLeft = [[UILabel alloc]initWithFrame:CGRectMake(10, 20, 50, 20)];
    labelLeft.backgroundColor = [UIColor clearColor];
    labelLeft.font = [UIFont systemFontOfSize:14.0];
    labelLeft.textColor = Color_MiddleHei;
    labelLeft.text = @"当前值";
    [self addSubview:labelLeft];
    
    labelCurValue = [[UILabel alloc]initWithFrame:CGRectMake(labelLeft.frame.origin.x+labelLeft.frame.size.width, 0, rect.size.width-50, 40)];
    labelCurValue.backgroundColor = [UIColor clearColor];
    labelCurValue.font = [UIFont boldSystemFontOfSize:35.0];
    labelCurValue.textColor = Color_MiddleHei;
    labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    [self addSubview:labelCurValue];
    
    NSDictionary *attributes = @{NSFontAttributeName:labelCurValue.font};
    CGSize size = [labelCurValue.text sizeWithAttributes:attributes];
    CGRect frame = labelCurValue.frame;
    frame.size.width = size.width+5;
    labelCurValue.frame = frame;
    
    
    labelUnit = [[UILabel alloc]initWithFrame:CGRectMake(labelCurValue.frame.origin.x+labelCurValue.frame.size.width, 20, 50, 20)];
    labelUnit.backgroundColor = [UIColor clearColor];
    labelUnit.font = [UIFont systemFontOfSize:14.0];
    labelUnit.textColor = Color_MiddleHei;
    labelUnit.text = [RealTimeModel SharedRealTimeModel].strUnit;
    [self addSubview:labelUnit];
    
    
    ScrollLineviewS = [[LineScrollView alloc]initWithFrame:CGRectMake(5, 50, SCREENWIDTH-10, 150)];
    ScrollLineviewS.dataSource = self;
    ScrollLineviewS.tag = 2;
    [self addSubview:ScrollLineviewS];
    
    
    ScrollLineviewM = [[LineScrollView alloc]initWithFrame:CGRectMake(ScrollLineviewS.frame.origin.x,ScrollLineviewS.frame.origin.y+ScrollLineviewS.frame.size.height, ScrollLineviewS.frame.size.width, ScrollLineviewS.frame.size.height)];
    ScrollLineviewM.dataSource = self;
    ScrollLineviewM.tag = 1;
    [self addSubview:ScrollLineviewM];
}
-(void)Start{
    timerS = [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(TimerUpdateS) userInfo:nil repeats:YES];
}
-(void)Stop{
    [timerS invalidate];
}
-(void)TimerUpdateS{
    if ([RealTimeModel SharedRealTimeModel].brealStart) {
        isec++;
        if (isec > 60) {
            isec = 1;
            imin++;
            NSComparator cmptr = ^(id obj1, id obj2){
                if ([obj1 floatValue] > [obj2 floatValue]) {
                    return (NSComparisonResult)NSOrderedDescending;
                }
                if ([obj1 floatValue] < [obj2 floatValue]) {
                    return (NSComparisonResult)NSOrderedAscending;
                }
                return (NSComparisonResult)NSOrderedSame;
            };
            NSArray *array = [maryHistoryDataS sortedArrayUsingComparator:cmptr];
            if (array.count == 0) {
                if([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_ALCOHOL]){
                    [maryHistoryDataM addObject:@"0.000"];
                }
                else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CO]){
                    [maryHistoryDataM addObject:@"0.0"];
                }
                else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CH2O]){
                    [maryHistoryDataM addObject:@"0.00"];
                }
            }
            else{
                [maryHistoryDataM addObject:array.lastObject];
            }
            [ScrollLineviewM ReloadData];
            [maryHistoryDataS removeAllObjects];
        }
        [maryHistoryDataS addObject:[RealTimeModel SharedRealTimeModel].strPieValue];
        [ScrollLineviewS ReloadData];
    }
}

-(NSInteger)LineScrollViewOfXmaxValue:(LineScrollView *)lineScrollView{
    if (lineScrollView.tag == 1) {
        return imin;
    }
    else if (lineScrollView.tag == 2){
        //NSLog(@"isec:%d",isec);
        return isec;
    }
    return 0;
}
-(CGFloat)LineScrollViewOfYmaxValue:(LineScrollView *)lineScrollView{
    float MaxValue = 0.0;
    if([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_ALCOHOL]){
        MaxValue = ALCOHOL_MGL_HIGH;
    }
    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CO]){
        MaxValue = CO_HIGH;
    }
    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CH2O]){
        MaxValue = CH2O_HIGH;
    }
    
    NSComparator cmptr = ^(id obj1, id obj2){
        if ([obj1 floatValue] > [obj2 floatValue]) {
            return (NSComparisonResult)NSOrderedDescending;
        }
        
        if ([obj1 floatValue] < [obj2 floatValue]) {
            return (NSComparisonResult)NSOrderedAscending;
        }
        return (NSComparisonResult)NSOrderedSame;
    };
    if (lineScrollView.tag == 1) {
        NSArray *array = [maryHistoryDataM sortedArrayUsingComparator:cmptr];
        if ([array.lastObject floatValue] <= MaxValue) {
            return MaxValue;
        }
        else{
            return [array.lastObject floatValue];
        }
    }
    else if (lineScrollView.tag == 2){
        NSArray *array = [maryHistoryDataS sortedArrayUsingComparator:cmptr];
        if ([array.lastObject floatValue] <= MaxValue) {
            return MaxValue;
        }
        else{
            return [array.lastObject floatValue];
        }
    }
    return MaxValue;
}
-(NSInteger)LineScrollViewOfXCount:(LineScrollView *)lineScrollView{
    if (lineScrollView.tag == 1) {
        return imin;
    }
    else if (lineScrollView.tag == 2){
        return isec;
    }
    return 0;
}
-(NSInteger)LineScrollViewOfYCount:(LineScrollView *)lineScrollView{
    if (lineScrollView.tag == 1) {
        return 5;
    }
    else if (lineScrollView.tag == 2){
        return 5;
    }
    return 0;
}
-(NSInteger)LineScrollViewOfType:(LineScrollView *)lineScrollView{
    if([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_ALCOHOL]){
        return 2;
    }
    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CO]){
        return 3;
    }
    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CH2O]){
        return 1;
    }
    return 0;
}
-(UIColor *)LineScrollViewOfLineColor:(LineScrollView *)lineScrollView{
    return [UIColor redColor];
}
-(NSArray *)LineScrollViewOfData:(LineScrollView *)lineScrollView{
    //存储历史数据分享用
    if (lineScrollView.tag == 1) {
        if ([RealTimeModel SharedRealTimeModel].maryHistoryM.count != 0) {
            [[RealTimeModel SharedRealTimeModel].maryHistoryM removeAllObjects];
        }
        [[RealTimeModel SharedRealTimeModel].maryHistoryM addObjectsFromArray:maryHistoryDataM];
        return maryHistoryDataM;
    }
    else if (lineScrollView.tag == 2){
        if ([RealTimeModel SharedRealTimeModel].maryHistoryS.count != 0) {
            [[RealTimeModel SharedRealTimeModel].maryHistoryS removeAllObjects];
        }
        [[RealTimeModel SharedRealTimeModel].maryHistoryS addObjectsFromArray:maryHistoryDataS];
        return maryHistoryDataS;
    }
    return nil;
}
-(NSString *)LineScrollViewOfLineName:(LineScrollView *)lineScrollView{
    if (lineScrollView.tag == 1) {
        return @"分钟内最高值记录";
    }
    else if (lineScrollView.tag == 2){
        return @"实时记录";
    }
    return @"";
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
