//
//  RealTimeView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "RealTimeView.h"

@implementation RealTimeView
@synthesize labelUseTime;
@synthesize imageBattery;
@synthesize webviewState;
@synthesize labelEffect;

@synthesize labelCurValue;
@synthesize labelCurValueTitle;

@synthesize labelAverage;
@synthesize labelAverageTitle;

@synthesize labelMax;
@synthesize labelMaxTitle;
@synthesize bloaded;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        self.backgroundColor = [UIColor colorWithRed:245.0/255.0 green:252.0/255.0 blue:234.0/255.0 alpha:1.0];
        [self LoadSubView];
    }
    return self;
}

-(void)ReloadData{
    NSInteger ih = [[RealTimeModel SharedRealTimeModel].strUseTime integerValue]/60;
    NSInteger im = [[RealTimeModel SharedRealTimeModel].strUseTime integerValue]%60;
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        dispatch_sync(dispatch_get_main_queue(), ^{
            labelUseTime.text = [NSString stringWithFormat:@"累计使用时间%d小时%d分钟",ih,im];
            imageBattery.image = [UIImage imageNamed:[RealTimeModel SharedRealTimeModel].strBatteryPath];
            
            labelEffect.text = [RealTimeModel SharedRealTimeModel].strEffect;
            labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
            labelMax.text = [RealTimeModel SharedRealTimeModel].strMax;
            labelAverage.text = [RealTimeModel SharedRealTimeModel].strAverage;
            
            if ([RealTimeModel SharedRealTimeModel].strStatus.length == 0) {
                [RealTimeModel SharedRealTimeModel].strStatus = @"";
            }
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
            NSString* strjs = [NSString stringWithFormat:@"update('{\"maxValue\":%f,\"height\":%f,\"curValue\":%@,\"width\":%f,\"unit\":\"%@\",\"status\":\"%@\"}')",MaxValue,webviewState.frame.size.height,[RealTimeModel SharedRealTimeModel].strPieValue,webviewState.frame.size.height,[RealTimeModel SharedRealTimeModel].strUnit,[RealTimeModel SharedRealTimeModel].strStatus];
            [webviewState stringByEvaluatingJavaScriptFromString:strjs];
        });
    });
}
-(void)LoadSubView{
    labelUseTime = [[UILabel alloc]initWithFrame:CGRectMake(5, 0, rect.size.width-50, 30)];
    labelUseTime.backgroundColor = [UIColor clearColor];
    labelUseTime.font = [UIFont systemFontOfSize:14.0];
    labelUseTime.textColor = Color_MiddleHei;
    labelUseTime.text = @"累计使用时间0小时0分钟";
    //[self addSubview:labelUseTime];
    
    imageBattery = [[UIImageView alloc]initWithFrame:CGRectMake(rect.size.width-45, 9, 30, 12)];
    imageBattery.image = [UIImage imageNamed:@"ui_home_power_0.png"];
    [self addSubview:imageBattery];
    
    webviewState = [[UIWebView alloc]initWithFrame:CGRectMake(0, labelUseTime.frame.origin.y+labelUseTime.frame.size.height+10, rect.size.width, 17/48.0*FULLSCREENHEIGHT+10)];
    webviewState.backgroundColor = [UIColor clearColor];
    webviewState.opaque = NO;
    webviewState.scrollView.scrollEnabled = NO;
    webviewState.delegate = self;
    NSString *filePath = [[NSBundle mainBundle]pathForResource:@"PercentageLoader" ofType:@"htm"];
    NSString *htmlString = [NSString stringWithContentsOfFile:filePath encoding:NSUTF8StringEncoding error:nil];
    [webviewState loadHTMLString:htmlString baseURL:[NSURL URLWithString:filePath]];
    [self addSubview:webviewState];
    
    
    labelEffect = [[UILabel alloc]initWithFrame:CGRectMake(5, webviewState.frame.origin.y+webviewState.frame.size.height, rect.size.width-10, 20)];
    labelEffect.backgroundColor = [UIColor clearColor];
    labelEffect.font = [UIFont systemFontOfSize:14.0];
    labelEffect.textColor = Color_MiddleHei;
    labelEffect.text = @"";
    labelEffect.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelEffect];

    labelCurValue = [[UILabel alloc]initWithFrame:CGRectMake(0, labelEffect.frame.origin.y+labelEffect.frame.size.height+25, rect.size.width/3, 30)];
    labelCurValue.backgroundColor = [UIColor clearColor];
    labelCurValue.font = [UIFont systemFontOfSize:25.0];
    labelCurValue.textColor = Color_MiddleHei;
    labelCurValue.text = @"0";
    labelCurValue.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelCurValue];
    labelCurValueTitle = [[UILabel alloc]initWithFrame:CGRectMake(labelCurValue.frame.origin.x, labelCurValue.frame.origin.y+labelCurValue.frame.size.height,labelCurValue.frame.size.width, 30)];
    labelCurValueTitle.backgroundColor = [UIColor clearColor];
    labelCurValueTitle.font = [UIFont systemFontOfSize:18.0];
    labelCurValueTitle.textColor = Color_MiddleHei;
    labelCurValueTitle.text = @"当前值";
    labelCurValueTitle.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelCurValueTitle];
    
    labelMax = [[UILabel alloc]initWithFrame:CGRectMake(rect.size.width/3, labelCurValue.frame.origin.y, rect.size.width/3, 30)];
    labelMax.backgroundColor = [UIColor clearColor];
    labelMax.font = [UIFont systemFontOfSize:25.0];
    labelMax.textColor = Color_MiddleHei;
    labelMax.text = @"0";
    labelMax.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelMax];
    labelMaxTitle = [[UILabel alloc]initWithFrame:CGRectMake(labelMax.frame.origin.x, labelMax.frame.origin.y+labelMax.frame.size.height,labelMax.frame.size.width, 30)];
    labelMaxTitle.backgroundColor = [UIColor clearColor];
    labelMaxTitle.font = [UIFont systemFontOfSize:18.0];
    labelMaxTitle.textColor = Color_MiddleHei;
    labelMaxTitle.text = @"最大值";
    labelMaxTitle.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelMaxTitle];
    
    
    labelAverage = [[UILabel alloc]initWithFrame:CGRectMake(2*rect.size.width/3, labelCurValue.frame.origin.y, rect.size.width/3, 30)];
    labelAverage.backgroundColor = [UIColor clearColor];
    labelAverage.font = [UIFont systemFontOfSize:25.0];
    labelAverage.textColor = Color_MiddleHei;
    labelAverage.text = @"0";
    labelAverage.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelAverage];
    labelAverageTitle = [[UILabel alloc]initWithFrame:CGRectMake(labelAverage.frame.origin.x, labelAverage.frame.origin.y+labelAverage.frame.size.height,labelAverage.frame.size.width,30)];
    labelAverageTitle.backgroundColor = [UIColor clearColor];
    labelAverageTitle.font = [UIFont systemFontOfSize:18.0];
    labelAverageTitle.textColor = Color_MiddleHei;
    labelAverageTitle.text = @"平均值";
    labelAverageTitle.textAlignment = NSTextAlignmentCenter;
    [self addSubview:labelAverageTitle];
}



-(void)TimerHardStartUpdate:(NSString *)startstate sec:(NSInteger)isec{
    NSString* strjs = [NSString stringWithFormat:@"update('{\"maxValue\":%f,\"height\":%f,\"curValue\":%d,\"width\":%f,\"unit\":\"%@\",\"status\":\"%@:%d\"}')",2.0,webviewState.frame.size.height,0,webviewState.frame.size.height,[RealTimeModel SharedRealTimeModel].strUnit,startstate,isec];
    [webviewState stringByEvaluatingJavaScriptFromString:strjs];
}


-(void)webViewDidFinishLoad:(UIWebView *)webView{
    if (!bloaded) {
        bloaded = YES;
        NSString* strjs = [NSString stringWithFormat:@"create('{\"maxValue\":1.0,\"height\":%f,\"curValue\":0.00,\"width\":%f}')",17/48.0*FULLSCREENHEIGHT,17/48.0*FULLSCREENHEIGHT];
        [webView stringByEvaluatingJavaScriptFromString:strjs];
        
        NSString* strjs2 = [NSString stringWithFormat:@"update('{\"maxValue\":1.0,\"height\":%f,\"curValue\":0.00,\"width\":%f,\"unit\":"",\"status\":""}')",17/48.0*FULLSCREENHEIGHT,17/48.0*FULLSCREENHEIGHT];
        [webView stringByEvaluatingJavaScriptFromString:strjs2];
    }
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
