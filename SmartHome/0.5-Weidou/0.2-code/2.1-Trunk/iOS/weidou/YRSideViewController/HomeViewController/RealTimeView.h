//
//  RealTimeView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "../Macro.h"
#import "RealTimeModel.h"
@class RealTimeView;

@interface RealTimeView : UIView<UIWebViewDelegate>{
    CGRect rect;
}
@property BOOL bloaded;
@property(nonatomic,strong)UILabel *labelUseTime;
@property(nonatomic,strong)UIImageView *imageBattery;
@property(nonatomic,strong)UIWebView *webviewState;
@property(nonatomic,strong)UILabel *labelEffect;

@property(nonatomic,strong)UILabel *labelCurValue;
@property(nonatomic,strong)UILabel *labelCurValueTitle;

@property(nonatomic,strong)UILabel *labelAverage;
@property(nonatomic,strong)UILabel *labelAverageTitle;

@property(nonatomic,strong)UILabel *labelMax;
@property(nonatomic,strong)UILabel *labelMaxTitle;

-(void)ReloadData;
-(void)TimerHardStart;
-(void)TimerHardStartUpdate:(NSString *)startstate sec:(NSInteger)isec;
@end
