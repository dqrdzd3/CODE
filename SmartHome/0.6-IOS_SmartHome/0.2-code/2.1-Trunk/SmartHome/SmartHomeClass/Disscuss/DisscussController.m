//
//  DisscussController.m
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "DisscussController.h"
#import "DisscussTheme.h"
#import "ServerResult.h"
#import "DisscussDetailViewController.h"

@implementation DisscussController
@synthesize disscussThemes;
- (void)viewDidLoad {
    [super viewDidLoad];
    disscussThemes             = [[NSMutableArray alloc]init];
    UIView *viewTop            = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 64)];
    viewTop.backgroundColor    = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle        = [[UILabel alloc]initWithFrame:CGRectMake(50, 20, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment   = NSTextAlignmentCenter;
    labelTitle.textColor       = [UIColor blackColor];
    labelTitle.font            = [UIFont systemFontOfSize:16];
    labelTitle.text            = @"讨论区";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5, 25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    
    UIButton *btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33, 25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
    
    
    _mTableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 64, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height-64)];
    _mTableView.tableFooterView = [[UIView alloc]init];
    _mTableView.dataSource      = self;
    _mTableView.delegate        = self;
    [self.view addSubview:_mTableView];
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
    [self.view bringSubviewToFront:viewTop];
}

-(void)ReloadData{
    [self initData];
}
-(void)ClickLeftMenu{
    [self presentLeftMenuViewController:nil];
}
-(void)ClickRightMenu{
    [self presentRightMenuViewController:nil];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/
#pragma mark - Table View
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSUInteger count = [self.disscussThemes count];
    return count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSString *tableCellIdentifier =  [NSString stringWithFormat:@"Cell_%d",indexPath.row];
    UITableViewCell *cell = (UITableViewCell *)[tableView dequeueReusableCellWithIdentifier:tableCellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:tableCellIdentifier];
    }
    if (self.disscussThemes.count != 0) {
        NSDictionary *s       = [self.disscussThemes objectAtIndex:indexPath.row];
        DisscussTheme *theme1 = [[DisscussTheme alloc] initWithDictionary:s];
        cell.textLabel.text   = theme1.ma002;
    }
    return cell;
    
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return 60;
}

- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"Detail selected");
}
//设置选中Cell的响应事件

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath*)indexPath{
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];//选中后的反显颜色即刻消失
     NSLog(@"didSelectRowAtIndexPath");

    DisscussTheme *theme = [[DisscussTheme alloc] initWithDictionary:[self.disscussThemes objectAtIndex:indexPath.row]];
    DisscussDetailViewController *disscussDetailView = [[DisscussDetailViewController alloc]init];
    disscussDetailView.disscussTheme = theme;
    [self.navigationController pushViewController:disscussDetailView animated:YES];
}


//获取讨论区主题数据
-(void)initData{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_05_01_01_01]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.requestMethod = @"POST";
    NSUserDefaults *userDefaultes = [NSUserDefaults standardUserDefaults];
    NSString *userId = [userDefaultes stringForKey:@"USERID"];
    if (userId == nil) {
        return;
    };
    NSString *sessionId = [userDefaultes stringForKey:@"SESSIONID"];
    if (sessionId == nil) {
        return;
    };
    [request setPostValue:userId forKey:@"USERID"];
    [request setPostValue:sessionId forKey:@"SESSIONID"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc] initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            if (disscussThemes.count != 0) {
                [disscussThemes removeAllObjects];
            }
            [disscussThemes addObjectsFromArray:(NSArray *)result.dataObject];
            [_mTableView reloadData];
        }
    }];
    [request setFailedBlock:^{
        [HUD hide:YES];
        NSError *error = [request error];
        if (error) {
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = @"网络链接失败";
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
        }
    }];
    
    [request startAsynchronous];
    [HUD show:YES];
}

@end
