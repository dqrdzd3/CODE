//
//  HistoryView.h
//  SmartHome
//
//  Created by apple on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MJRefresh.h"
#import "HistoryTableViewCell.h"

////历史报警标识
//ma001;
////值
//ma002;
////状态
//ma003;
////类型
//ma004;
////时间
//ma005;
////设备id
//ma006;
////备注1
//ma007;
////备注2
//ma008;
@interface HistoryView : UIView<UITableViewDataSource,UITableViewDelegate,MJRefreshBaseViewDelegate>{
    CGRect rect;
    UITableView *HistoryTableview;
    NSMutableArray *maryData;
    
    BOOL bUpdateLoading;
    BOOL bLoading;
    NSString *strDeviceid;
    NSInteger iPageIndex;
    NSInteger iPageSize;
    
    MJRefreshHeaderView *header;
    MJRefreshFooterView *footer;
}

-(id)initWithFrame:(CGRect)frame deviceid:(NSString *)deviceid;
@end
