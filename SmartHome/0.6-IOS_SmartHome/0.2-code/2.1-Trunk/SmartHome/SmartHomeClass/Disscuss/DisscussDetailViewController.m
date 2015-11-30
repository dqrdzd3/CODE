//
//  DisscussDetailViewController.m
//  SmartHome
//
//  Created by 李静 on 14-11-13.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "DisscussDetailViewController.h"
#import "ServerResult.h"
#import "DisscussDetailModel.h"
#import "UIButton+Bootstrap.h"
#import "DisscussDetailTableViewCell.h"
#import "UIImageView+WebCache.h"

@implementation DisscussDetailViewController
@synthesize disscussList;
@synthesize disscussTheme;
- (void)viewDidLoad {
    disscussList = [[NSMutableArray alloc]init];
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    // Do any additional setup after loading the view.
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(50, STATUSBARHEIGHT, SCREENWIDTH-100, 44)];
    labelTitle.font = [UIFont boldSystemFontOfSize:16.0];
    labelTitle.textColor = Color_MiddleHei;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.text = @"讨论区详情";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnBack = [[UIButton alloc]initWithFrame:CGRectMake(0, STATUSBARHEIGHT, 50, 44)];
    [btnBack setTitle:@"返回" forState:UIControlStateNormal];
    [btnBack setTitleColor:[UIColor colorWithRed:45.0/255 green:119.0/255 blue:248.0/255 alpha:1.0] forState:UIControlStateNormal];
    [btnBack addTarget:self action:@selector(backBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnBack];
    
    
    _scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, 64, SCREENWIDTH, FULLSCREENHEIGHT-64)];
    _scrollView.contentSize = CGSizeMake([UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height-64);
    [self.view addSubview:_scrollView];
    
    _mTableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 0, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height-250)];
    _mTableView.showsVerticalScrollIndicator = NO;
    _mTableView.delegate = self;
    _mTableView.dataSource = self;
    [_scrollView addSubview:_mTableView];
    _mTableView.tableFooterView = [[UIView alloc]init];
    
    _inputContentText = [[UITextView alloc]initWithFrame:CGRectMake(5, _mTableView.frame.origin.y+_mTableView.frame.size.height+5, [UIScreen mainScreen].bounds.size.width-10, 80)];
    _inputContentText.delegate = self;
    _inputContentText.layer.borderColor = [UIColor lightGrayColor].CGColor;
    _inputContentText.layer.borderWidth = 0.5;
    _inputContentText.font = [UIFont systemFontOfSize:14.0];
    _inputContentText.textColor = [UIColor blackColor];
    _inputContentText.returnKeyType = UIReturnKeyDone;
    [_scrollView addSubview:_inputContentText];
    
    
    _leftWordsLable = [[UILabel alloc]initWithFrame:CGRectMake(0,  _inputContentText.frame.origin.y+_inputContentText.frame.size.height+5, _inputContentText.frame.size.width, 20)];
    _leftWordsLable.backgroundColor = [UIColor clearColor];
    _leftWordsLable.font = [UIFont systemFontOfSize:12.0];
    _leftWordsLable.textColor = [UIColor blackColor];
    _leftWordsLable.textAlignment = NSTextAlignmentRight;
    _leftWordsLable.text = [NSString stringWithFormat:@"还能输入128个字"];
    [_scrollView addSubview:_leftWordsLable];
    
    _postBtn = [[UIButton alloc]initWithFrame:CGRectMake(5, _leftWordsLable.frame.origin.y+_leftWordsLable.frame.size.height, _leftWordsLable.frame.size.width, 40)];
    _postBtn.layer.cornerRadius = 2.0;
    [_postBtn setTitle:@"提交" forState:UIControlStateNormal];
    _postBtn.titleLabel.font = [UIFont systemFontOfSize:16.0];
    [_postBtn setBackgroundColor:[UIColor colorWithRed:0.0 green:153/255.0 blue:204/255.0 alpha:1.0]];
    [_scrollView addSubview:_postBtn];
    [_postBtn addTarget:self action:@selector(postBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    
    HUD = [[MBProgressHUD alloc]initWithView:self.view];
    [self.view addSubview:HUD];
    [self.view bringSubviewToFront:_viewTop];
    //[UIScreen mainScreen].bounds.size.height-216-64-(_inputContentText.frame.size.height+_postBtn.frame.size.height)
    [self initData];
    [_postBtn infoStyle];
}

-(void)textViewDidBeginEditing:(UITextView *)textView{
    [_scrollView setContentOffset:CGPointMake(0, [UIScreen mainScreen].bounds.size.height-_inputContentText.frame.origin.y) animated:YES];
}
- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text{
    if ([text isEqualToString:@"\n"]) {
        [_inputContentText resignFirstResponder];
        [_scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
        return NO;
    }
    if (![text isEqualToString:@""] && _inputContentText.text.length >= 128) {
        return NO;
    }

    return YES;
}
-(void)textViewDidChange:(UITextView *)textView{
    if (_inputContentText.text.length >= 128) {
        _inputContentText.text = [_inputContentText.text substringToIndex:128];
    }
    _leftWordsLable.text = [NSString stringWithFormat:@"还能输入%d个字",128-_inputContentText.text.length];
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

- (IBAction)postBtnClick:(id)sender {
    [_inputContentText resignFirstResponder];
    [self postQuestion];
}
#pragma mark - Table View

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSUInteger count = [self.disscussList count];
    
    NSLog(@"COUNT,@%d",count);
    return count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *tableCellIdentifier = @"disscussDetailTableViewCell";
    DisscussDetailTableViewCell *cell = (DisscussDetailTableViewCell *)[tableView dequeueReusableCellWithIdentifier:tableCellIdentifier];
    if (cell == nil) {
        cell = [[DisscussDetailTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:tableCellIdentifier];
    }
    NSDictionary *s = [self.disscussList objectAtIndex:indexPath.row];
    DisscussDetailModel *item= [[DisscussDetailModel alloc] initWithDictionary:s];
    
    cell.nameLabel.text = item.ma008;
    cell.contentLable.text = item.ma003;
    cell.dateLabel.text = item.ma004;
    
    CGSize maximumSizeContent = CGSizeMake([UIScreen mainScreen].bounds.size.width-65, CGFLOAT_MAX);
    NSDictionary * tdicContent = [NSDictionary dictionaryWithObjectsAndKeys:cell.contentLable.font,NSFontAttributeName,nil];
    CGSize  actualsizeContent = [cell.contentLable.text boundingRectWithSize:maximumSizeContent options:NSStringDrawingUsesLineFragmentOrigin |NSStringDrawingUsesFontLeading attributes:tdicContent context:nil].size;
    CGRect rectContent = cell.contentLable.frame;
    rectContent.origin.x = cell.nameLabel.frame.origin.x;
    rectContent.origin.y = cell.nameLabel.frame.origin.y+cell.nameLabel.frame.size.height;
    rectContent.size.width = [UIScreen mainScreen].bounds.size.width-65;
    rectContent.size.height = actualsizeContent.height+10;
    cell.contentLable.frame = rectContent;
    
    NSString *imageUrl = item.ma009;
    if (imageUrl != nil && imageUrl.length) {
        NSString *streamUrl = [SERVER_BASE_URI stringByAppendingString:DOWNLOADSTREAM];
        NSString *indexUrl = [@"?ma001=" stringByAppendingString:imageUrl];
        NSString *wholeUrl = [streamUrl stringByAppendingString:indexUrl];
        NSLog(@"IMAGEURL-->%@",wholeUrl);
        [cell.headImageView setImageWithURL:[NSURL URLWithString:wholeUrl] placeholderImage:[UIImage imageNamed:@"userHeadImage.png"]];
    }
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
//    
//    DisscussDetailTableViewCell *cell = [self tableView:_mTableView cellForRowAtIndexPath:indexPath];
//    return cell.frame.size.height;
    float fcellheight = 0.0;
    DisscussDetailTableViewCell *cell = (DisscussDetailTableViewCell *)[self tableView:tableView cellForRowAtIndexPath:indexPath];
    fcellheight = cell.contentLable.frame.origin.y+cell.contentLable.frame.size.height+10;
    return fcellheight;
}

- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"Detail selected");
}

//设置选中Cell的响应事件
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath*)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];//选中后的反显颜色即刻消失
}
//获取讨论区主题详情数据
-(void)initData{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_05_01_01_03]];
    ASIFormDataRequest *requestTmp = [ASIFormDataRequest requestWithURL:url];
    __weak typeof(ASIFormDataRequest) *request = requestTmp;
    request.tag = 1;
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
    //[request setPostValue:[NSString stringWithFormat:@"%d",1] forKey:@"PAGENO"];
    //[request setPostValue:[NSString stringWithFormat:@"%d",10] forKey:@"PAGESIZE"];
    [request setPostValue:disscussTheme.ma001 forKey:@"TITLEID"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc] initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            if (disscussList.count != 0) {
                [disscussList removeAllObjects];
            }
            [disscussList addObjectsFromArray:result.dataObject];
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
            [HUD2 hide:YES afterDelay:2.0];
        }
    }];
    
    [request startAsynchronous];
    [HUD show:YES];
}

//提交讨论内容
-(void)postQuestion{
    NSString *content = [_inputContentText text];
    if (content.length == 0 || content.length > 128) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"内容不能为空或者超过128个字" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_05_01_01_02]];
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
    [request setPostValue:content forKey:@"REPLYMSG"];
    [request setPostValue:disscussTheme.ma001 forKey:@"TITLEID"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];

        ServerResult *result = [[ServerResult alloc] initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            [self initData];
            [_inputContentText setText:@""];
            _leftWordsLable.text = [NSString stringWithFormat:@"还能输入128个字"];
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:2.0];
            
            [_mTableView scrollsToTop];
        }
        else{
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:2.0];
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
            [HUD2 hide:YES afterDelay:2.0];
        }
    }];
    [request startAsynchronous];
    [HUD show:YES];
}


- (IBAction)backBtnClick:(id)sender {
    [self.navigationController popViewControllerAnimated:YES];
}

-(void)viewDidDisappear:(BOOL)animated{
    [_inputContentText resignFirstResponder];
    [_scrollView setContentOffset:CGPointMake(0, 0)];
}
@end
