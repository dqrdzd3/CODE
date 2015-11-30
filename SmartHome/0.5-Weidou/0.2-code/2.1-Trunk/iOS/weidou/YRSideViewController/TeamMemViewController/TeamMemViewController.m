//
//  TeamMemViewController.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "TeamMemViewController.h"

@interface TeamMemViewController ()

@end

@implementation TeamMemViewController
@synthesize viewTop;
@synthesize labelTitle;
@synthesize btnLeft;


- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-50, STATUSBARHEIGHT, 100, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"团队成员";
    [viewTop addSubview:labelTitle];
    
    btnLeft = [[UIButton alloc]initWithFrame:CGRectMake(5, STATUSBARHEIGHT+10.5, 28, 23)];
    [btnLeft setImage:[UIImage imageNamed:@"title_bar_menu.png"] forState:UIControlStateNormal];
    [btnLeft addTarget:self action:@selector(showMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeft];
    
    tableview.delegate = self;
    tableview.dataSource = self;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 2;
}
-(float)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 45;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    NSString *tableCellIdentifier = [NSString stringWithFormat:@"Cell_%d",indexPath.row];
    UITableViewCell *cell = (UITableViewCell *)[tableView dequeueReusableCellWithIdentifier:tableCellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:tableCellIdentifier];
    }
    if (indexPath.row == 0) {
        cell.textLabel.text = @"开发人员";
        cell.detailTextLabel.text = @"彪哥";
    }
    else if (indexPath.row == 1){
        cell.textLabel.text = @"UI";
        cell.detailTextLabel.text = @"赵帅";
    }
    return cell;
}

- (void)showMenu{
    [self presentLeftMenuViewController:nil];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (BOOL)shouldAutorotate
{
    return YES;
}
- (NSUInteger)supportedInterfaceOrientations
{
    return UIInterfaceOrientationMaskPortrait | UIInterfaceOrientationMaskPortraitUpsideDown;
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
