//
//  FightButton.h
//  Weidou
//
//  Created by wbiao on 14/12/9.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FightButton : UIButton{
    CGRect rect;
}

@property(nonatomic,strong)UILabel *labelValue;
@property(nonatomic,strong)UILabel *labelStatus;
@property(nonatomic,strong)UILabel *labelRank;
@end
