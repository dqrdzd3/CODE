//
//  HistoryView.m
//  SmartHome
//
//  Created by apple on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "HistoryView.h"
@implementation HistoryView

-(id)initWithFrame:(CGRect)frame deviceid:(NSString *)deviceid{
    self = [super initWithFrame:frame];
    if (self) {
        // Do any additional setup after loading the view
        rect = frame;
        strDeviceid = deviceid;
        maryData = [[NSMutableArray alloc]init];
        iPageIndex = 1;
        iPageSize = 10;
        [self LoadView];
    }
    return self;
}

-(void)LoadView{
    UILabel *labelLeft = [[UILabel alloc]initWithFrame:CGRectMake(0, 0, rect.size.width/2-5, 44)];
    labelLeft.backgroundColor = [UIColor clearColor];
    labelLeft.font = [UIFont systemFontOfSize:14.0];
    labelLeft.textAlignment = NSTextAlignmentRight;
    labelLeft.textColor = [UIColor colorWithRed:153/255.0 green:204/255.0 blue:51/255.0 alpha:1.0];
    labelLeft.text = @"报警时间";
    [self addSubview:labelLeft];
    
    UIImageView *imageLine = [[UIImageView alloc]initWithFrame:CGRectMake(rect.size.width/2-3, 8, 6, 28)];
    imageLine.image = [UIImage imageNamed:@"list_ho_diver.png"];
    [self addSubview:imageLine];
    
    UILabel *labelRight = [[UILabel alloc]initWithFrame:CGRectMake(rect.size.width/2+5, 0, rect.size.width/2-5, 44)];
    labelRight.backgroundColor = [UIColor clearColor];
    labelRight.font = [UIFont systemFontOfSize:14.0];
    labelRight.textAlignment = NSTextAlignmentLeft;
    labelRight.textColor = [UIColor colorWithRed:153/255.0 green:204/255.0 blue:51/255.0 alpha:1.0];
    labelRight.text = @"状态   报警值";
    [self addSubview:labelRight];
  
    HistoryTableview = [[UITableView alloc]initWithFrame:CGRectMake(0, 44, rect.size.width, rect.size.height-44)];
    HistoryTableview.dataSource = self;
    HistoryTableview.delegate = self;
    HistoryTableview.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self addSubview:HistoryTableview];
    HistoryTableview.tableFooterView = [[UIView alloc]init];
    
    header = [MJRefreshHeaderView header];
    header.scrollView = HistoryTableview;
    header.delegate = self;
    
    footer = [MJRefreshFooterView footer];
    footer.scrollView = HistoryTableview;
    footer.delegate = self;
    [self refreshViewBeginRefreshing:header];
}

-(void)initData{
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_02_01_02]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    
    //读取NSString类型的数据
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId.length == 0) {
        return;
    };
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId.length == 0) {
        return;
    };
    NSString *pageIndex = [NSString stringWithFormat:@"%d",iPageIndex];
    NSString *pageSize = [NSString stringWithFormat:@"%d",iPageSize];
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setPostValue:strDeviceid forKey:@"DRIVERID"];
    [request setPostValue:pageIndex forKey:@"PAGENO"];
    [request setPostValue:pageSize forKey:@"PAGESIZE"];
    [request setCompletionBlock:^{
        NSString *responseString = [request responseString];
        NSDictionary *resultDic = [responseString  objectFromJSONString];
        if ([resultDic objectForKey:@"dataObject"] == [NSNull null] ||
            ((NSArray *)[resultDic objectForKey:@"dataObject"]).count == 0) {
            [self doneWithView:header];
            [self doneWithView:footer];
        }
        else{
            if (bLoading) {
                [maryData addObjectsFromArray:[resultDic objectForKey:@"dataObject"]];
                [self doneWithView:footer];
            }
            if (bUpdateLoading) {
                if (maryData.count != 0) {
                    [maryData removeAllObjects];
                }
                [maryData addObjectsFromArray:[resultDic objectForKey:@"dataObject"]];
                [self doneWithView:header];
            }
        }
    }];
    [request setFailedBlock:^{
        [self doneWithView:header];
        [self doneWithView:footer];
        NSError *error = [request error];
        if (error) {
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = @"网络链接失败";
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
        }
    }];
    [request startAsynchronous];
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return maryData.count;
}
-(float)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 60;
}
-(HistoryTableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    NSString *identifier = [NSString stringWithFormat:@"Cell_%d",indexPath.row];
    HistoryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[HistoryTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault
                               reuseIdentifier:identifier cellWidth:rect.size.width];
    }
    if (maryData.count != 0) {
        NSDictionary *dictemp = [maryData objectAtIndex:indexPath.row];
        
        NSString *strDate = [dictemp objectForKey:@"ma005"];
        NSArray *aryDate = [strDate componentsSeparatedByString:@" "];
        
        cell.labelInfo.text = [NSString stringWithFormat:@"%@\n%@\n%@",[dictemp objectForKey:@"ma004"],[aryDate objectAtIndex:0],[aryDate objectAtIndex:1]];
        cell.labelStateValue.text = [NSString stringWithFormat:@"%@         %@",[dictemp objectForKey:@"ma003"],[dictemp objectForKey:@"ma002"]];
    }
    return cell;
}

#pragma mark - 刷新控件的代理方法
#pragma mark 开始进入刷新状态
- (void)refreshViewBeginRefreshing:(MJRefreshBaseView *)refreshView
{
NSLog(@"%@----开始进入刷新状态", refreshView.class);
    
    if (refreshView.class == [MJRefreshFooterView class]) {
        iPageIndex++;
        bLoading = YES;
        bUpdateLoading = NO;
    }
    else{
        iPageIndex = 1;
        bUpdateLoading = YES;
        bLoading = NO;
    }
    [self initData];
    
}

#pragma mark 刷新完毕
- (void)refreshViewEndRefreshing:(MJRefreshBaseView *)refreshView
{
    NSLog(@"%@----刷新完毕", refreshView.class);
}

#pragma mark 监听刷新状态的改变
- (void)refreshView:(MJRefreshBaseView *)refreshView stateChange:(MJRefreshState)state
{
    switch (state) {
        case MJRefreshStateNormal:
            NSLog(@"%@----切换到：普通状态", refreshView.class);
            break;
            
        case MJRefreshStatePulling:
            NSLog(@"%@----切换到：松开即可刷新的状态", refreshView.class);
            break;
        case MJRefreshStateRefreshing:
            NSLog(@"%@----切换到：正在刷新状态", refreshView.class);
            break;
        default:
            break;
    }
}

#pragma mark 刷新表格并且结束正在刷新状态
- (void)doneWithView:(MJRefreshBaseView *)refreshView
{
    // (最好在刷新表格后调用)调用endRefreshing可以结束刷新状态
    bUpdateLoading = NO;
    bLoading = NO;
    [refreshView endRefreshing];
    [HistoryTableview reloadData];
}


@end
