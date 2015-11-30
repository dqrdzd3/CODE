//
//  BottomView.h
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol BottomViewDelegate <NSObject>

-(void)BottomViewSelected:(NSInteger)index;

@end
@interface BottomView : UIView{
    CGRect rect;
}
@property(nonatomic,strong)id<BottomViewDelegate>delegate;
@property(nonatomic,strong)UIButton* btnRealTimeData;
@property(nonatomic,strong)UIButton* btnHistoryData;
@property(nonatomic,strong)UIButton* btnComeOn;
@property(nonatomic,strong)UIButton* btnFriendShare;

-(void)SetBottomViewItemSelect:(NSInteger)index;
@end
