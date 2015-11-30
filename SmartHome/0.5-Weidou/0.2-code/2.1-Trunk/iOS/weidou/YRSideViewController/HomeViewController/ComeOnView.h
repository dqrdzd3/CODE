//
//  ComeOnView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Macro.h"
#import "RealTimeModel.h"
#import "FightButton.h"
@interface ComeOnView : UIView{
    CGRect rect;
    UIButton *btnRefresh;
    NSMutableArray *maryRankValue;
    NSMutableArray *maryValue;
    NSMutableArray *maryButton;
}
@property(nonatomic,strong)UILabel *labelCurValue;
@property(nonatomic,strong)UILabel *labelUnit;

-(void)RefreshRank;
-(void)ReloadData;
@end
