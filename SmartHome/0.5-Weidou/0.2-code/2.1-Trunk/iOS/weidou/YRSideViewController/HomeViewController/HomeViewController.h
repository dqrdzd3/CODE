//
//  HomeViewController.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "../Macro.h"
#import "BottomView.h"
#import <AudioToolbox/AudioSession.h>
#import <AVFoundation/AVFoundation.h>
#import "RealTimeView.h"
#import "HistoryView.h"
#import "ComeOnView.h"
#import "FriendShareView.h"
#import "RealTimeModel.h"

#import "StringBuilder.h"
#import "Recorder.h"
#import "FrameContent.h"
#import "RESideMenu.h"
#import "AdviceViewController.h"
@interface HomeViewController : UIViewController<AVAudioSessionDelegate,UIScrollViewDelegate,RecorderDelegate,FrameContentDelegate,BottomViewDelegate,UIAlertViewDelegate>{
    UIScrollView *scrollView;
    BottomView *bottomView;
    
    HistoryView *historyView;
    ComeOnView *comeOnView;
    FriendShareView *friendShareView;
    
    RealTimeView *realTimeView;
    Recorder *record;
    SInt16 *sbuffer;
    long lsize;
    NSString *strLastData;
    
    NSTimer *timer;//检测耳机类型和调换1，0
    NSInteger isec;//计时器计数器
    NSInteger idataCount;//数据计数器
    BOOL bkeepLink;
    
    FrameContent *frameC;
    BOOL bstart;
    
    NSMutableArray *maryHexCache;
}
@property(nonatomic,strong)UIView *viewTop;
@property(nonatomic,strong)UILabel *labelName;
@property(nonatomic,strong)UILabel *labelTitle;
@property(nonatomic,strong)UILabel *labelState;
@property(nonatomic,strong)UIButton *btnLeft;
@property(nonatomic,strong)UIButton *btnRight;
@end
