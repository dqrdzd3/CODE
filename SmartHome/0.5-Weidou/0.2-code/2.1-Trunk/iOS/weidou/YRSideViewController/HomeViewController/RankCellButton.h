//
//  RankCellButton.h
//  Weidou
//
//  Created by wbiao on 14/11/28.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RankCellButton : UIButton{
    CGRect rect;
}


@property(nonatomic,strong)UILabel *labelValue;
@property(nonatomic,strong)UILabel *labelState;
@property(nonatomic,strong)UILabel *labelRank;
@end
