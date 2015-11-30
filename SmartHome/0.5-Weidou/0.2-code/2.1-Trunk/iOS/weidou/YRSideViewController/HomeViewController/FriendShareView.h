//
//  FriendShareView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "../Macro.h"
#import "RealTimeModel.h"
#import "AppDelegate.h"

@interface FriendShareView : UIView{
    CGRect rect;
    UILabel *labelCurValue;
    UILabel *labelUnit;
    
    UILabel *labelShareRealTime;
    UILabel *labelShareHistory;
    UILabel *labelShareFight;
    
    UISwitch *switchShareRealTime;
    UISwitch *switchShareHistory;
    UISwitch *switchShareFight;
    
    UIButton *btnqq;
    UIButton *btnqqzone;
    UIButton *btnqqwb;
    UIButton *btnwx;
    UIButton *btnwxpyq;
    UIButton *btnsinawb;
    
    AppDelegate *appdelegate;
}

-(void)ReloadData;
@end
