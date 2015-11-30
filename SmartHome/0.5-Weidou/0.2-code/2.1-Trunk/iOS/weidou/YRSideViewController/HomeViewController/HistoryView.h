//
//  HistoryView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "../Macro.h"
#import "LineScrollView.h"
#import "RealTimeModel.h"
@class HistoryView;

@interface HistoryView : UIView<LineScrollViewDataSource>{
    CGRect rect;
    LineScrollView *ScrollLineviewM;
    LineScrollView *ScrollLineviewS;
    
    
    NSInteger iXmax;
    NSMutableArray *maryHistoryDataM;
    NSMutableArray *maryHistoryDataS;
    NSInteger isec;
    NSInteger imin;
    NSTimer *timerS;
}
@property BOOL bstart;
@property(nonatomic,strong)UILabel *labelCurValue;
@property(nonatomic,strong)UILabel *labelUnit;
-(void)ReloadData;
-(void)CleanLinePoint;
-(void)Start;
-(void)Stop;
-(void)TimerUpdateS;
@end
