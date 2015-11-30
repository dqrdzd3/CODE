//
//  BottomView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "BottomView.h"

@implementation BottomView
@synthesize btnRealTimeData;
@synthesize btnHistoryData;
@synthesize btnFriendShare;
@synthesize btnComeOn;
@synthesize delegate;

-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        self.backgroundColor = [UIColor colorWithRed:245.0/255.0 green:252.0/255.0 blue:234.0/255.0 alpha:1.0];
        [self LoadView];
    }
    return self;
}

-(void)LoadView{
    float fButtonWidth = 56;
    float fgap = (rect.size.width-4*fButtonWidth)/8;
    
    btnRealTimeData = [[UIButton alloc]initWithFrame:CGRectMake(fgap, 0, fButtonWidth, fButtonWidth)];
    [btnRealTimeData setImage:[UIImage imageNamed:@"ui_home_current.png"] forState:UIControlStateNormal];
    [btnRealTimeData setImage:[UIImage imageNamed:@"ui_home_current_on.png"] forState:UIControlStateSelected];
    [btnRealTimeData addTarget:self action:@selector(ClickReal) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:btnRealTimeData];
    UILabel *labelrealTimeData = [[UILabel alloc]initWithFrame:CGRectMake(0, btnRealTimeData.frame.origin.y+btnRealTimeData.frame.size.height, rect.size.width/4, 20)];
    labelrealTimeData.backgroundColor = [UIColor clearColor];
    labelrealTimeData.font = [UIFont systemFontOfSize:14.0];
    labelrealTimeData.textColor = [UIColor blackColor];
    labelrealTimeData.textAlignment = NSTextAlignmentCenter;
    labelrealTimeData.text = @"实时数据";
    [self addSubview:labelrealTimeData];
    
    btnHistoryData = [[UIButton alloc]initWithFrame:CGRectMake(3*fgap+fButtonWidth, 0, fButtonWidth, fButtonWidth)];
    [btnHistoryData setImage:[UIImage imageNamed:@"ui_home_history.png"] forState:UIControlStateNormal];
    [btnHistoryData setImage:[UIImage imageNamed:@"ui_home_history_on.png"] forState:UIControlStateSelected];
    [btnHistoryData addTarget:self action:@selector(ClickHistory) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:btnHistoryData];
    UILabel *labelHistoryData = [[UILabel alloc]initWithFrame:CGRectMake(rect.size.width/4, btnRealTimeData.frame.origin.y+btnRealTimeData.frame.size.height, rect.size.width/4, 20)];
    labelHistoryData.backgroundColor = [UIColor clearColor];
    labelHistoryData.font = [UIFont systemFontOfSize:14.0];
    labelHistoryData.textColor = [UIColor blackColor];
    labelHistoryData.textAlignment = NSTextAlignmentCenter;
    labelHistoryData.text = @"历史数据";
    [self addSubview:labelHistoryData];
    
    btnComeOn = [[UIButton alloc]initWithFrame:CGRectMake(5*fgap+2*fButtonWidth, 0, fButtonWidth, fButtonWidth)];
    [btnComeOn setImage:[UIImage imageNamed:@"ui_home_match.png"] forState:UIControlStateNormal];
    [btnComeOn setImage:[UIImage imageNamed:@"ui_home_match_on.png"] forState:UIControlStateSelected];
    [btnComeOn addTarget:self action:@selector(ClickComeon) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:btnComeOn];//
    UILabel *labelComeOn = [[UILabel alloc]initWithFrame:CGRectMake(rect.size.width/2, btnRealTimeData.frame.origin.y+btnRealTimeData.frame.size.height, rect.size.width/4, 20)];
    labelComeOn.backgroundColor = [UIColor clearColor];
    labelComeOn.font = [UIFont systemFontOfSize:14.0];
    labelComeOn.textColor = [UIColor blackColor];
    labelComeOn.textAlignment = NSTextAlignmentCenter;
    labelComeOn.text = @"来局比赛";
    [self addSubview:labelComeOn];//
    
    btnFriendShare = [[UIButton alloc]initWithFrame:CGRectMake(7*fgap+3*fButtonWidth, 0, fButtonWidth, fButtonWidth)];
    [btnFriendShare setImage:[UIImage imageNamed:@"ui_home_share.png"] forState:UIControlStateNormal];
    [btnFriendShare setImage:[UIImage imageNamed:@"ui_home_share_on.png"] forState:UIControlStateSelected];
    [btnFriendShare addTarget:self action:@selector(ClickFriendShare) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:btnFriendShare];//
    UILabel *labelFriendShare = [[UILabel alloc]initWithFrame:CGRectMake(3*rect.size.width/4, btnRealTimeData.frame.origin.y+btnRealTimeData.frame.size.height, rect.size.width/4, 20)];
    labelFriendShare.backgroundColor = [UIColor clearColor];
    labelFriendShare.font = [UIFont systemFontOfSize:14.0];
    labelFriendShare.textColor = [UIColor blackColor];
    labelFriendShare.textAlignment = NSTextAlignmentCenter;
    labelFriendShare.text = @"朋友分享";
    [self addSubview:labelFriendShare];//
    
    btnRealTimeData.selected = YES;
}
-(void)ClickReal{
    btnRealTimeData.selected = NO;
    btnHistoryData.selected = NO;
    btnComeOn.selected = NO;
    btnFriendShare.selected = NO;
    btnRealTimeData.selected = YES;
    [delegate BottomViewSelected:0];
}
-(void)ClickHistory{
    btnRealTimeData.selected = NO;
    btnHistoryData.selected = NO;
    btnComeOn.selected = NO;
    btnFriendShare.selected = NO;
    btnHistoryData.selected = YES;
    [delegate BottomViewSelected:1];
}
-(void)ClickComeon{
    btnRealTimeData.selected = NO;
    btnHistoryData.selected = NO;
    btnComeOn.selected = NO;
    btnFriendShare.selected = NO;
    btnComeOn.selected = YES;
    [delegate BottomViewSelected:2];
}
-(void)ClickFriendShare{
    btnRealTimeData.selected = NO;
    btnHistoryData.selected = NO;
    btnComeOn.selected = NO;
    btnFriendShare.selected = NO;
    btnFriendShare.selected = YES;
    [delegate BottomViewSelected:3];
}
-(void)SetBottomViewItemSelect:(NSInteger)index{
    btnRealTimeData.selected = NO;
    btnHistoryData.selected = NO;
    btnComeOn.selected = NO;
    btnFriendShare.selected = NO;
    
    switch (index) {
        case 0:
            btnRealTimeData.selected = YES;
            break;
        case 1:
            btnHistoryData.selected = YES;
            break;
        case 2:
            btnComeOn.selected = YES;
            break;
        case 3:
            btnFriendShare.selected = YES;
            break;
        default:
            break;
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
