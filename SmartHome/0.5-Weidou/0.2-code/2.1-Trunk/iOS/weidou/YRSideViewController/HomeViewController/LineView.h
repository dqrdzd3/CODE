//
//  LineView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>


@protocol LineViewDelegate <NSObject>

-(void)LineViewDrawPoint:(CGPoint)point colWidth:(float)colwidth;

@end
@interface LineView : UIView{
    CGRect rect;
    NSInteger iXmaxValue;
    NSInteger iXcount;
    
    float fYmaxValue;
    NSInteger iYcount;
    
    UIColor *cLineColor;
    NSMutableArray *maryData;

    float fLineDrawViewHeight;
    float fRowHeight;
}

@property(nonatomic,strong)id<LineViewDelegate>delegate;
-(void)ReloadData:(NSInteger)XMax Ymax:(float)Ymax
                  Xcount:(NSInteger)Xcount Ycount:(NSInteger)Ycount
                  lineColor:(UIColor *)lineColor data:(NSArray *)data;
@end
