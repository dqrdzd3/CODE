//
//  HomeViewController.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "HomeViewController.h"
#import "AppDelegate.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
extern BOOL bWeitouIsGj;
@implementation HomeViewController
@synthesize viewTop;
@synthesize labelTitle;
@synthesize labelState;
@synthesize btnLeft;
@synthesize labelName;
@synthesize btnRight;

- (void)viewDidLoad {
    [super viewDidLoad];
    maryHexCache = [[NSMutableArray alloc]init];
    self.view.backgroundColor = [UIColor colorWithRed:245.0/255.0 green:252.0/255.0 blue:234.0/255.0 alpha:1.0];

    // Do any additional setup after loading the view from its nib.
    viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor colorWithRed:245.0/255.0 green:252.0/255.0 blue:234.0/255.0 alpha:1.0];
    [self.view addSubview:viewTop];

    labelName = [[UILabel alloc]initWithFrame:CGRectMake(50, STATUSBARHEIGHT, SCREENWIDTH/2-90, 44)];
    labelName.font = [UIFont boldSystemFontOfSize:23.0];
    labelName.textColor = Color_MiddleHei;
    labelName.textAlignment = NSTextAlignmentRight;
    labelName.backgroundColor = [UIColor clearColor];
    labelName.text = @"";
    [viewTop addSubview:labelName];
    
    labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-30, STATUSBARHEIGHT, 60, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"传感器";
    [viewTop addSubview:labelTitle];
    
    labelState = [[UILabel alloc]initWithFrame:CGRectMake(labelTitle.frame.origin.x+labelTitle.frame.size.width, STATUSBARHEIGHT, 50, 44)];
    labelState.font = [UIFont systemFontOfSize:14.0];
    labelState.textColor = Color_Hong;
    labelState.textAlignment = NSTextAlignmentCenter;
    labelState.backgroundColor = [UIColor clearColor];
    labelState.text = @"未连接";
    [viewTop addSubview:labelState];
    
    btnLeft = [[UIButton alloc]initWithFrame:CGRectMake(5, STATUSBARHEIGHT+10.5, 28, 23)];
    [btnLeft setImage:[UIImage imageNamed:@"title_bar_menu.png"] forState:UIControlStateNormal];
    [btnLeft addTarget:self action:@selector(showMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeft];
    
    btnRight = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-35, STATUSBARHEIGHT+7, 30, 25)];
    [btnRight setImage:[UIImage imageNamed:@"ui_home_push_on.png"] forState:UIControlStateNormal];
    [btnRight addTarget:self action:@selector(PushAdvice) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRight];
    

    // Do any additional setup after loading the view from its nib.
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight-BottomViewHeight)];
    scrollView.pagingEnabled = YES;//
    //scrollView.scrollEnabled = NO;//暂时
    [scrollView setBounces:NO];
    scrollView.showsHorizontalScrollIndicator = NO;
    scrollView.contentSize = CGSizeMake(4*SCREENWIDTH, scrollView.frame.size.height);
    scrollView.delegate = self;
    scrollView.backgroundColor = [UIColor colorWithRed:245.0/255.0 green:252.0/255.0 blue:234.0/255.0 alpha:1.0];
    [self.view addSubview:scrollView];
    
    [self LoadScrollSubView];
    bottomView = [[BottomView alloc]initWithFrame:CGRectMake(0, FULLSCREENHEIGHT-BottomViewHeight, SCREENWIDTH, BottomViewHeight)];
    bottomView.delegate = self;
    [self.view addSubview:bottomView];
    
    record = [[Recorder alloc]init];
    record.delegate = self;
    
    bstart = NO;
    [record stop];
    [record start];
}

-(void)StartLink{
    if ([RealTimeModel SharedRealTimeModel].brealStart) {
        NSLog(@"StartLink");
        timer = [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(TimerUpdate) userInfo:nil repeats:YES];
        isec = 0;
        idataCount = 0;
    }
}
-(void)StopLink{
    if (![RealTimeModel SharedRealTimeModel].brealStart) {
        NSLog(@"StopLink");
        [timer invalidate];
        bstart = NO;
        bWeitouIsGj = NO;
        [RealTimeModel SharedRealTimeModel].strUseTime = @"0";
        [RealTimeModel SharedRealTimeModel].strBatteryPath = @"ui_home_power_0.png";
        [RealTimeModel SharedRealTimeModel].strPieValue = @"0";
        [RealTimeModel SharedRealTimeModel].strEffect = @"";
        [RealTimeModel SharedRealTimeModel].strAverage = @"0";
        [RealTimeModel SharedRealTimeModel].strMax = @"0";
        [RealTimeModel SharedRealTimeModel].strUnit = @"";
        [RealTimeModel SharedRealTimeModel].strStatus = @"";
        [RealTimeModel SharedRealTimeModel].strName = @"";
        if ([RealTimeModel SharedRealTimeModel].maryHistoryS.count != 0) {
            [[RealTimeModel SharedRealTimeModel].maryHistoryS removeAllObjects];
        }
        if ([RealTimeModel SharedRealTimeModel].maryHistoryM.count != 0) {
            [[RealTimeModel SharedRealTimeModel].maryHistoryM removeAllObjects];
        }
        [RealTimeModel SharedRealTimeModel].strFightShare = @"";
        labelName.text = @"";
        [realTimeView ReloadData];
        [historyView ReloadData];
        [historyView Stop];
        [historyView CleanLinePoint];
        [comeOnView RefreshRank];
        [friendShareView ReloadData];
    }
}

-(void)TimerUpdate{
    if ([RealTimeModel SharedRealTimeModel].brealStart) {
        isec++;
    }
    if (isec <= 5) {
        [realTimeView TimerHardStartUpdate:@"启动中" sec:6-isec];
    }
    //5秒内没有帧则调换耳机口监听类型（国际和国内标准的耳机）
    if (isec == 5) {
        if (idataCount == 0) {
            bWeitouIsGj = !bWeitouIsGj;
        }
    }
    if (isec >= 6 && isec < 11){
        [realTimeView TimerHardStartUpdate:@"预热中" sec:11-isec];
    }
    if (isec >= 11 ) {
        if (idataCount != 0) {
            [timer invalidate];
            bstart = YES;
            [self CheckACArryHex:maryHexCache];
            [self CheckAEArryHex:maryHexCache];
            if (maryHexCache.count != 0) {
                [maryHexCache removeAllObjects];
            }
            [historyView Start];
        }
        else{
            UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"启动失败,请检查设备重试" delegate:self cancelButtonTitle:@"重试" otherButtonTitles:nil, nil];
            alert.tag = 10;
            [alert show];
            bstart = NO;
            [timer invalidate];
            [historyView Stop];
            [self StopLink];
        }
    }
}

-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (alertView.tag == 10) {
        if (buttonIndex == 0) {
            [record stop];
            [record start];
            [timer invalidate];
            timer = [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(TimerUpdate) userInfo:nil repeats:YES];
            isec = 0;
            idataCount = 0;
            bWeitouIsGj = NO;
        }
    }
}
//加载左右滑动的子view
-(void)LoadScrollSubView{
    realTimeView = [[RealTimeView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, scrollView.frame.size.height)];
    [scrollView addSubview:realTimeView];
    [realTimeView ReloadData];
    
    historyView = [[HistoryView alloc]initWithFrame:CGRectMake(SCREENWIDTH, 0, SCREENWIDTH, scrollView.frame.size.height)];
    [scrollView addSubview:historyView];
    
    comeOnView = [[ComeOnView alloc]initWithFrame:CGRectMake(SCREENWIDTH*2, 0, SCREENWIDTH, scrollView.frame.size.height)];
    [scrollView addSubview:comeOnView];
    friendShareView = [[FriendShareView alloc]initWithFrame:CGRectMake(SCREENWIDTH*3, 0, SCREENWIDTH, scrollView.frame.size.height)];
    [scrollView addSubview:friendShareView];
}

#pragma mark--------scrollViewDelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    int currentPage = floor((scrollView.contentOffset.x - SCREENWIDTH / 2) / SCREENWIDTH) + 1;
    [bottomView SetBottomViewItemSelect:currentPage];
    NSLog(@"currentPage:%d",currentPage);
}
-(void)BottomViewSelected:(NSInteger)index{
    [scrollView setContentOffset:CGPointMake(index*SCREENWIDTH, 0) animated:NO];
}
#pragma mark-----------------RecorderAudioBuffer
-(void)AdptFrame{
    @autoreleasepool{
        NSString *strSourceWaveBinary = [StringBuilder AnalyzeShortToBinary:sbuffer :lsize];
        NSString *strBinary = [StringBuilder SquareWaveAdapter:strSourceWaveBinary];
        NSArray *arySectionBinary = [StringBuilder SectionBinary:strBinary];
        
        
        NSMutableArray *maryAdptFourier = [[NSMutableArray alloc]init];
        for (NSString *str in arySectionBinary) {
            if ([StringBuilder adptFourier:str] == nil) {
                continue;
            }
            NSString *strtemp = [StringBuilder adptFourier:str];
            [maryAdptFourier addObject:strtemp];
        }
        NSMutableArray *maryHex = [[NSMutableArray alloc]init];
        for (NSString *str in maryAdptFourier) {
            if (str.length > 0) {
                NSString *strContent = [str substringWithRange:NSMakeRange(1,8)];
                strContent = [StringBuilder stringByReversed:strContent];
                //NSString *strHead = [str substringWithRange:NSMakeRange(0,1)];
                //NSString *strEnd = [str substringWithRange:NSMakeRange(10,1)];
                NSString *strHexContent = [StringBuilder hexStringFromString:strContent];
                [maryHex addObject:strHexContent];
                //            BOOL bcheck = [StringBuilder BinaryCheck:strHead end:strEnd contenthex:strHexContent];
                //            if (bcheck) {
                //                [maryHex addObject:strHexContent];
                //            }
            }
        }
        if (maryHex.count >= 5) {
            if (!bstart) {
                [maryHexCache addObjectsFromArray:maryHex];
            }
            [self CheckACArryHex:maryHex];
            [self CheckAEArryHex:maryHex];
        }
        else if (maryHex.count == 0){
            if ([RealTimeModel SharedRealTimeModel].brealStart) {
                labelState.text = @"未连接";
                [RealTimeModel SharedRealTimeModel].brealStart = NO;
                //断开连接关闭计时器
                [self performSelectorOnMainThread:@selector(StopLink) withObject:nil waitUntilDone:NO];
            }
        }
    }
}

- (void)RecorderAudioBuffer:(SInt16 *)buffer size:(long)size
{
//    @autoreleasepool{
//        NSString *strSourceWaveBinary = [StringBuilder AnalyzeShortToBinary:buffer :size];
//        
//        if (strSourceWaveBinary.length > 0) {
//            if (![RealTimeModel SharedRealTimeModel].brealStart) {
//                labelState.text = @"已连接";
//                [RealTimeModel SharedRealTimeModel].brealStart = YES;
//                //连接打开计时器重新检测
//                [self performSelectorOnMainThread:@selector(StartLink) withObject:nil waitUntilDone:NO];
//            }
//        }
//        else{
//            if ([RealTimeModel SharedRealTimeModel].brealStart) {
//                labelState.text = @"未连接";
//                [RealTimeModel SharedRealTimeModel].brealStart = NO;
//                //断开连接关闭计时器
//                [self performSelectorOnMainThread:@selector(StopLink) withObject:nil waitUntilDone:NO];
//            }
//            return;
//        }
//   
//
//            NSString *strBinary = [StringBuilder SquareWaveAdapter:strSourceWaveBinary];
//            NSArray *arySectionBinary = [StringBuilder SectionBinary:strBinary];
//            
//            NSMutableArray *maryAdptFourier = [[NSMutableArray alloc]init];
//            for (NSString *str in arySectionBinary) {
//                if ([StringBuilder adptFourier:str] == nil) {
//                    continue;
//                }
//                NSString *strtemp = [StringBuilder adptFourier:str];
//                [maryAdptFourier addObject:strtemp];
//            }
//            
//            NSMutableArray *maryHex = [[NSMutableArray alloc]init];
//            for (NSString *str in maryAdptFourier) {
//                if (str.length > 0) {
//                    NSString *strContent = [str substringWithRange:NSMakeRange(1,8)];
//                    strContent = [StringBuilder stringByReversed:strContent];
//                    //NSString *strHead = [str substringWithRange:NSMakeRange(0,1)];
//                    //NSString *strEnd = [str substringWithRange:NSMakeRange(10,1)];
//                    NSString *strHexContent = [StringBuilder hexStringFromString:strContent];
//                    [maryHex addObject:strHexContent];
//                    //            BOOL bcheck = [StringBuilder BinaryCheck:strHead end:strEnd contenthex:strHexContent];
//                    //            if (bcheck) {
//                    //                [maryHex addObject:strHexContent];
//                    //            }
//                }
//            }
//            if (maryHex.count >= 5) {
//                [self CheckACArryHex:maryHex];
//                [self CheckAEArryHex:maryHex];
//            }
//    }
    
    memset(sbuffer, 0x0, lsize);
    sbuffer = buffer;
    lsize = size;
    [NSThread detachNewThreadSelector:@selector(AdptFrame) toTarget:self withObject:nil];
}

-(void)CheckAEArryHex:(NSArray *)aryhex{
    NSMutableArray *aryReversedHex = [[NSMutableArray alloc]initWithArray:aryhex.reverseObjectEnumerator.allObjects];
    if ([aryReversedHex indexOfObject:@"AE"] != NSNotFound &&
        aryReversedHex.count >= 5) {
        NSMutableArray *maryHexframeBytes = [[NSMutableArray alloc]init];
        
        for (NSInteger i = aryReversedHex.count-1;i >= 0;i--) {
            NSString *stringHex = [aryReversedHex objectAtIndex:i];
            if ([stringHex isEqualToString:@"AE"]) {
                [maryHexframeBytes addObject:stringHex];
            }
            else if (maryHexframeBytes.count > 0){
                [maryHexframeBytes addObject:stringHex];
                if (maryHexframeBytes.count == 5) {
                    NSArray *maryHexframeBytesR = maryHexframeBytes.reverseObjectEnumerator.allObjects;
                    if ([self CRCCheckAE:maryHexframeBytesR]) {
                        [self FrameHandler:maryHexframeBytesR];
                        [maryHexframeBytes removeAllObjects];
                    }
                }
                if (maryHexframeBytes.count == 6){
                    NSArray *maryHexframeBytesR = maryHexframeBytes.reverseObjectEnumerator.allObjects;
                    if ([self CRCCheckAE:maryHexframeBytesR]) {
                        [self FrameHandler:maryHexframeBytesR];
                        [maryHexframeBytes removeAllObjects];
                        return;
                    }
                }
                if (maryHexframeBytes.count == 6) {
                    [maryHexframeBytes removeAllObjects];
                }
            }
        }
    }
    //NSLog(@"endworkAE!");
}

-(void)CheckACArryHex:(NSArray *)aryhex{
    NSMutableArray *aryReversedHex = [[NSMutableArray alloc]initWithArray:aryhex.reverseObjectEnumerator.allObjects];
    if ([aryReversedHex indexOfObject:@"AC"] != NSNotFound &&
        aryReversedHex.count >= 5) {
        NSMutableArray *maryHexframeBytes = [[NSMutableArray alloc]init];
        
        for (NSInteger i = 0;i < aryReversedHex.count;i++) {
            NSString *stringHex = [aryReversedHex objectAtIndex:i];
            if ([stringHex isEqualToString:@"AC"]) {
                [maryHexframeBytes addObject:stringHex];
            }
            else if (maryHexframeBytes.count > 0){
                [maryHexframeBytes addObject:stringHex];
                if (maryHexframeBytes.count == 5) {
                    if ([self CRCCheckAC:maryHexframeBytes]) {
                        [self FrameHandler:maryHexframeBytes];
                        [maryHexframeBytes removeAllObjects];
                    }
                }
                if (maryHexframeBytes.count == 6){
                    if ([self CRCCheckAC:maryHexframeBytes]) {
                        [self FrameHandler:maryHexframeBytes];
                        [maryHexframeBytes removeAllObjects];
                        return;
                    }
                }
                if (maryHexframeBytes.count == 6) {
                    [maryHexframeBytes removeAllObjects];
                }
            }
        }
    }
    //NSLog(@"endworkAC!");
}

//AE crc校验
-(BOOL)CRCCheckAE:(NSArray *)aryhex{
    //NSLog(@"CRCCheckAE!");
    //return YES;
    NSInteger iall = 0;
    NSInteger iCrc = 0;
    NSLog(@"%@",aryhex);
    iall = iall + strtoul([@"AC" UTF8String],0,16);
    for (NSInteger i = 0; i < aryhex.count; i++) {
        if (i == aryhex.count-2) {
            iCrc = strtoul([[aryhex objectAtIndex:i] UTF8String],0,16);
        }
        else{
            iall = iall + strtoul([[aryhex objectAtIndex:i] UTF8String],0,16);
        }
    }
    iall = iall &0xFF;
    
    NSLog(@"%d-%d",iall,iCrc);
    if (iall != iCrc) {
        return NO;
    }
    else{
        return YES;
    }
}
//AC crc校验
-(BOOL)CRCCheckAC:(NSArray *)aryhex{
    //NSLog(@"CRCCheckAC!");
    //return YES;
    NSInteger iall = 0;
    NSInteger iCrc = 0;
    NSLog(@"%@",aryhex);
    for (NSInteger i = 0; i < aryhex.count; i++) {
        if (i == aryhex.count-1) {
            iCrc = strtoul([[aryhex objectAtIndex:i] UTF8String],0,16);
        }
        else{
            iall = iall + strtoul([[aryhex objectAtIndex:i] UTF8String],0,16);
        }
    }
    iall = iall + strtoul([@"AE" UTF8String],0,16);
    iall = iall &0xFF;
    
    NSLog(@"%d-%d",iall,iCrc);
    if (iall != iCrc) {
        return NO;
    }
    else{
        return YES;
    }
}
//处理解析出来符合crc校验的帧
//2 AE OR 1 AC
-(void)FrameHandler:(NSArray *)aryframe{
    if (aryframe.count > 0) {
        if (![RealTimeModel SharedRealTimeModel].brealStart) {
            labelState.text = @"已连接";
            [RealTimeModel SharedRealTimeModel].brealStart = YES;
            //连接打开计时器重新检测
            [self performSelectorOnMainThread:@selector(StartLink) withObject:nil waitUntilDone:NO];
        }
    }

    NSMutableArray *aryHandle = [[NSMutableArray alloc]initWithArray:aryframe];
    
    if ([[aryHandle objectAtIndex:0] isEqualToString:@"AC"]) {
        [aryHandle removeObjectAtIndex:0];
    }
    if ([[aryHandle objectAtIndex:aryHandle.count-1] isEqualToString:@"AE"]){
        [aryHandle removeObjectAtIndex:aryHandle.count-1];
    }
    idataCount++;
    
    if (bstart) {
        if (frameC == nil) {
            frameC = [[FrameContent alloc]init];
            frameC.delegate = self;
        }
        [frameC ParserFrame:aryHandle];
        [realTimeView ReloadData];
    }
}
/////////////////////////////////////////////////////////////////////////////

#pragma mark-----FrameContentDelegate
//FrameContent更新状态时回调代理
-(void)FrameContentUpdateName:(NSString *)name{
    labelName.text = name;
    [RealTimeModel SharedRealTimeModel].strName = name;
    if([name isEqualToString:HOME_NAME_ALCOHOL]){
        [RealTimeModel SharedRealTimeModel].strUnit = HOME_UNIT_ALCOHOL;
    }
    else if ([name isEqualToString:HOME_NAME_CO]){
        [RealTimeModel SharedRealTimeModel].strUnit = HOME_UNIT_CO;
    }
    else if ([name isEqualToString:HOME_NAME_CH2O]){
        [RealTimeModel SharedRealTimeModel].strUnit = HOME_UNIT_CH2O;
    }
    [realTimeView ReloadData];
}
-(void)FrameContentUpdateVer:(NSString *)ver{
    
}
-(void)FrameContentUpdateData:(NSString *)data{
//    if([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_ALCOHOL]){
//        [RealTimeModel SharedRealTimeModel].strEffect = [self GetALCOHOLFrameContentDataEffect:data];
//        [RealTimeModel SharedRealTimeModel].strStatus = [self GetALCOHOLFrameContentDataState:data];
//        
//        if ([data floatValue] > ALCOHOL_MGL_HIGH) {
//            [RealTimeModel SharedRealTimeModel].strPieValue = [NSString stringWithFormat:@"%f",ALCOHOL_MGL_HIGH];
//        }
//        else{
//            [RealTimeModel SharedRealTimeModel].strPieValue = [NSString stringWithFormat:@"%.3f",[data floatValue]];
//        }
//        
//        if ([RealTimeModel SharedRealTimeModel].strMax.length == 0) {
//            [RealTimeModel SharedRealTimeModel].strMax = [RealTimeModel SharedRealTimeModel].strPieValue;
//        }
//        else{
//            if ([[RealTimeModel SharedRealTimeModel].strPieValue floatValue] >= [[RealTimeModel SharedRealTimeModel].strMax floatValue]) {
//                [RealTimeModel SharedRealTimeModel].strMax = [RealTimeModel SharedRealTimeModel].strPieValue;
//            }
//        }
//        if (strLastData.length == 0) {
//            strLastData = [RealTimeModel SharedRealTimeModel].strPieValue;
//        }
//        else{
//            float fAverage = ([strLastData floatValue] + [[RealTimeModel SharedRealTimeModel].strPieValue floatValue])/2;
//            [RealTimeModel SharedRealTimeModel].strAverage = [NSString stringWithFormat:@"%.3f",fAverage];
//            strLastData = [RealTimeModel SharedRealTimeModel].strPieValue;
//        }
//    }
//    else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CO]){
//        [RealTimeModel SharedRealTimeModel].strEffect = [self GetCOFrameContentDataEffect:data];
//        [RealTimeModel SharedRealTimeModel].strStatus = [self GetCOFrameContentDataState:data];
//        if ([data floatValue] > CO_HIGH) {
//            [RealTimeModel SharedRealTimeModel].strPieValue = [NSString stringWithFormat:@"%f",CO_HIGH];
//        }
//        else{
//            [RealTimeModel SharedRealTimeModel].strPieValue = [NSString stringWithFormat:@"%.1f",[data floatValue]];
//        }
//        
//        if ([RealTimeModel SharedRealTimeModel].strMax.length == 0) {
//            [RealTimeModel SharedRealTimeModel].strMax = [RealTimeModel SharedRealTimeModel].strPieValue;
//        }
//        else{
//            if ([[RealTimeModel SharedRealTimeModel].strPieValue floatValue] >= [[RealTimeModel SharedRealTimeModel].strMax floatValue]) {
//                [RealTimeModel SharedRealTimeModel].strMax = [RealTimeModel SharedRealTimeModel].strPieValue;
//            }
//        }
//        if (strLastData.length == 0) {
//            strLastData = [RealTimeModel SharedRealTimeModel].strPieValue;
//        }
//        else{
//            float fAverage = ([strLastData floatValue] + [[RealTimeModel SharedRealTimeModel].strPieValue floatValue])/2;
//            [RealTimeModel SharedRealTimeModel].strAverage = [NSString stringWithFormat:@"%.1f",fAverage];
//            strLastData = [RealTimeModel SharedRealTimeModel].strPieValue;
//        }
//    }
    //else if ([[RealTimeModel SharedRealTimeModel].strName isEqualToString:HOME_NAME_CH2O]){
        [RealTimeModel SharedRealTimeModel].strEffect = [self GetCH2OFrameContentDataEffect:data];
        [RealTimeModel SharedRealTimeModel].strStatus = [self GetCH2OFrameContentDataState:data];
        if ([data floatValue] > CH2O_HIGH) {
            [RealTimeModel SharedRealTimeModel].strPieValue = [NSString stringWithFormat:@"%f",CH2O_HIGH];
        }
        else{
            [RealTimeModel SharedRealTimeModel].strPieValue = [NSString stringWithFormat:@"%.2f",[data floatValue]];
        }
        
        if ([RealTimeModel SharedRealTimeModel].strMax.length == 0) {
            [RealTimeModel SharedRealTimeModel].strMax = [RealTimeModel SharedRealTimeModel].strPieValue;
        }
        else{
            if ([[RealTimeModel SharedRealTimeModel].strPieValue floatValue] >= [[RealTimeModel SharedRealTimeModel].strMax floatValue]) {
                [RealTimeModel SharedRealTimeModel].strMax = [RealTimeModel SharedRealTimeModel].strPieValue;
            }
        }
        if (strLastData.length == 0) {
            strLastData = [RealTimeModel SharedRealTimeModel].strPieValue;
        }
        else{
            float fAverage = ([strLastData floatValue] + [[RealTimeModel SharedRealTimeModel].strPieValue floatValue])/2;
            [RealTimeModel SharedRealTimeModel].strAverage = [NSString stringWithFormat:@"%.2f",fAverage];
            strLastData = [RealTimeModel SharedRealTimeModel].strPieValue;
        }
    //}
    //此处刷新所有页面的数据
    [realTimeView ReloadData];
    [historyView ReloadData];
    [comeOnView ReloadData];
    [friendShareView ReloadData];
}
-(void)FrameContentUpdatePower:(NSInteger)power{
    if (power < 0 || power > 5) {
        return;
    }
    [RealTimeModel SharedRealTimeModel].strBatteryPath = [NSString stringWithFormat:@"ui_home_power_%d.png",power+1];
    [realTimeView ReloadData];
}
-(void)FrameContentUpdateUtime:(NSInteger)utime{
    [RealTimeModel SharedRealTimeModel].strUseTime = [NSString stringWithFormat:@"%d",utime];
}


//计算酒精数值产生的影响
-(NSString *)GetALCOHOLFrameContentDataEffect:(NSString *)data{
    float fdata = [data floatValue];
    NSString *strEffect = @"";
    if (fdata <= ALCOHOL_L) {
        strEffect = ALCOHOL_CONTENT;
    }
    else if (fdata > ALCOHOL_L && fdata <= ALCOHOL_H){
        strEffect = ALCOHOL_CONTENT_20;
    }
    else if (fdata > ALCOHOL_H){
        strEffect = ALCOHOL_CONTENT_80;
    }
    return strEffect;
}
-(NSString *)GetALCOHOLFrameContentDataState:(NSString *)data{
    float fdata = [data floatValue];
    NSString *strEffect = @"";
    if (fdata <= ALCOHOL_L) {
        strEffect = ALCOHOL_CONTENT_STATUS_N;
    }
    else if (fdata > ALCOHOL_L && fdata <= ALCOHOL_H){
        strEffect = ALCOHOL_CONTENT_STATUS_D;
    }
    else if (fdata > ALCOHOL_H){
        strEffect = ALCOHOL_CONTENT_STATUS_DD;
    }
    return strEffect;
}

//计算一氧化碳数值产生的影响
-(NSString *)GetCOFrameContentDataEffect:(NSString *)data{
    float fdata = [data floatValue];
    NSString *strEffect = @"";
    
    if (fdata <= CO_35) {
        strEffect = CO_35_CONTENT;
    }
    else if (fdata > CO_35 && fdata <= CO_100){
        strEffect = CO_100_CONTENT;
    }
    else if (fdata > CO_100){
        strEffect = CO_MORE_100_CONTENT;
    }
    return strEffect;
}
-(NSString *)GetCOFrameContentDataState:(NSString *)data{
    float fdata = [data floatValue];
    NSString *strEffect = @"";
    if (fdata <= CO_50) {
        strEffect = C0_CONTENT_STATUS_N;
    }
    else if (fdata > CO_50 && fdata <= CO_800){
        strEffect = C0_CONTENT_STATUS_D;
    }
    else if (fdata > CO_800){
        strEffect = C0_CONTENT_STATUS_DD;
    }
    return strEffect;
}
//计算甲醛数值产生的影响
-(NSString *)GetCH2OFrameContentDataEffect:(NSString *)data{
    float fdata = [data floatValue];
    NSString *strEffect = @"";
    if (fdata <= CH2O_NORMAL_VALUE) {
        strEffect = CH2O_NORMAL_CONTENT;
    }
    else if (fdata > CH2O_NORMAL_VALUE && fdata <= CH2O_MIDDLE_VALUE){
        strEffect = CH2O_MIDDLE_CONTENT;
    }
    else if (fdata > CH2O_MIDDLE_VALUE && fdata <= CH2O_HIGH_VALUE){
        strEffect = CH2O_MIDDLE_UP_CONTENT;
    }
    else if (fdata > CH2O_HIGH_VALUE && fdata <= CH2O_DEAD_VALUE){
        strEffect = CH2O_HIGH_CONTENT;
    }
    else if (fdata > CH2O_DEAD_VALUE){
        strEffect = CH2O_HIGH_UP_CONTENT;
    }
    return strEffect;
}
-(NSString *)GetCH2OFrameContentDataState:(NSString *)data{
    float fdata = [data floatValue];
    NSString *strEffect = @"";
    if (fdata <= CH2O_NORMAL_VALUE) {
        strEffect = CH2O_CONTENT_STATUS_N;
    }
    else if (fdata > CH2O_NORMAL_VALUE && fdata <= CH2O_DEAD_VALUE){
        strEffect = CH2O_CONTENT_STATUS_D;
    }
    else if (fdata > CH2O_DEAD_VALUE){
        strEffect = CH2O_CONTENT_STATUS_DD;
    }
    return strEffect;
}
-(void)showMenu{
    [self presentLeftMenuViewController:nil];
}
-(void)PushAdvice{
    AdviceViewController *advice = [[AdviceViewController alloc]init];
    [self.navigationController pushViewController:advice animated:YES];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (BOOL)shouldAutorotate
{
    return YES;
}
- (NSUInteger)supportedInterfaceOrientations
{
    return UIInterfaceOrientationMaskPortrait | UIInterfaceOrientationMaskPortraitUpsideDown;
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
