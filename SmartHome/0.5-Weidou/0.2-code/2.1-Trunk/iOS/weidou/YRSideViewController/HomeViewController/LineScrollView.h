//
//  LineScrollView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "LineView.h"
@class LineScrollView;

@protocol LineScrollViewDataSource <NSObject>

-(NSInteger)LineScrollViewOfXmaxValue:(LineScrollView *)lineScrollView;
-(NSInteger)LineScrollViewOfXCount:(LineScrollView *)lineScrollView;
-(CGFloat)LineScrollViewOfYmaxValue:(LineScrollView *)lineScrollView;
-(NSInteger)LineScrollViewOfYCount:(LineScrollView *)lineScrollView;
-(UIColor *)LineScrollViewOfLineColor:(LineScrollView *)lineScrollView;
-(NSArray *)LineScrollViewOfData:(LineScrollView *)lineScrollView;
-(NSString *)LineScrollViewOfLineName:(LineScrollView *)lineScrollView;

-(NSInteger)LineScrollViewOfType:(LineScrollView *)lineScrollView;
@end
@interface LineScrollView : UIView<LineViewDelegate>{
    UIScrollView *scrollView;
    LineView *lineview;
    
    CGRect rect;
    UIView *viewColor;
    UILabel *labelWhitch;
    
    NSInteger iXmaxValue;
    NSInteger iXcount;
    
    CGFloat fYmaxValue;
    NSInteger iYcount;
    
    UIColor *cLineColor;
    NSMutableArray *maryData;
    
    CGFloat fLeftGap;
    UIFont *leftFont;
    
    NSInteger itype;//1,甲醛 2，酒精 3一氧化碳
}

@property(nonatomic,strong)id<LineScrollViewDataSource>dataSource;

-(void)ReloadData;
@end
