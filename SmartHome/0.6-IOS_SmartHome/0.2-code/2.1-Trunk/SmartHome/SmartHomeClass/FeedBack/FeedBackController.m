//
//  FeedBackController.m
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "FeedBackController.h"
#import "FeedbackTableViewCell.h"

#import "ServerResult.h"
#import "FeedbackModel.h"
#import "UIButton+Bootstrap.h"

@implementation FeedBackController
@synthesize feedbackList;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initView];
}
-(void)initView{
    feedbackList = [[NSMutableArray alloc]init];
    
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 64)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle        = [[UILabel alloc]initWithFrame:CGRectMake(50, 20, SCREENWIDTH-100, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment   = NSTextAlignmentCenter;
    labelTitle.textColor       = [UIColor blackColor];
    labelTitle.font            = [UIFont systemFontOfSize:16];
    labelTitle.text            = @"用户反馈";
    [viewTop addSubview:labelTitle];
    
    UIButton *btnLeftMenu = [[UIButton alloc]initWithFrame:CGRectMake(5,25.5, 28, 23)];
    [btnLeftMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnLeftMenu addTarget:self action:@selector(ClickLeftMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnLeftMenu];
    
    
    UIButton *btnRightMenu = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-33,25.5, 28, 23)];
    [btnRightMenu setImage:[UIImage imageNamed:@"title_bar_menu_on.png"] forState:UIControlStateNormal];
    [btnRightMenu addTarget:self action:@selector(ClickRightMenu) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:btnRightMenu];
    
    
    // Do any additional setup after loading the view.
    _scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, viewTop.frame.size.height, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height-viewTop.frame.size.height)];
    _scrollView.showsVerticalScrollIndicator = NO;
    _scrollView.contentSize = CGSizeMake([UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height);
    [self.view addSubview:_scrollView];
    

    _feedbackTableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 0, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height-250)];
    _feedbackTableView.showsVerticalScrollIndicator = NO;
    _feedbackTableView.delegate = self;
    _feedbackTableView.dataSource = self;
    [_scrollView addSubview:_feedbackTableView];
    _feedbackTableView.tableFooterView = [[UIView alloc]init];
    

    _feedbackContentText                   = [[UITextView alloc]initWithFrame:CGRectMake(5, _feedbackTableView.frame.origin.y+_feedbackTableView.frame.size.height+5, [UIScreen mainScreen].bounds.size.width-10, 80)];
    _feedbackContentText.delegate          = self;
    _feedbackContentText.layer.borderColor = [UIColor lightGrayColor].CGColor;
    _feedbackContentText.layer.borderWidth = 0.5;
    _feedbackContentText.font              = [UIFont systemFontOfSize:14.0];
    _feedbackContentText.textColor         = [UIColor blackColor];
    _feedbackContentText.returnKeyType     = UIReturnKeyDone;
    [_scrollView addSubview:_feedbackContentText];
    
    
    _numLabel                 = [[UILabel alloc]initWithFrame:CGRectMake(0,  _feedbackContentText.frame.origin.y+_feedbackContentText.frame.size.height+5, _feedbackContentText.frame.size.width, 20)];
    _numLabel.backgroundColor = [UIColor clearColor];
    _numLabel.font            = [UIFont systemFontOfSize:12.0];
    _numLabel.textColor       = [UIColor blackColor];
    _numLabel.textAlignment   = NSTextAlignmentRight;
    _numLabel.text            = [NSString stringWithFormat:@"还能输入128个字"];
    [_scrollView addSubview:_numLabel];
    
    _postBtn = [[UIButton alloc]initWithFrame:CGRectMake(5, _numLabel.frame.origin.y+_numLabel.frame.size.height, _numLabel.frame.size.width, 40)];
    _postBtn.layer.cornerRadius = 2.0;
    [_postBtn setTitle:@"提交" forState:UIControlStateNormal];
    _postBtn.titleLabel.font = [UIFont systemFontOfSize:16.0];
    [_postBtn setBackgroundColor:[UIColor colorWithRed:0.0 green:153/255.0 blue:204/255.0 alpha:1.0]];
    [_scrollView addSubview:_postBtn];
    [_postBtn addTarget:self action:@selector(postBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    
    [_postBtn infoStyle];
    
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
-(void)textViewDidBeginEditing:(UITextView *)textView{
    if (FULLSCREENHEIGHT == 480) {
        [_scrollView setContentOffset:CGPointMake(0, [UIScreen mainScreen].bounds.size.height-_feedbackContentText.frame.origin.y-20) animated:YES];
    }
    else{
        [_scrollView setContentOffset:CGPointMake(0, [UIScreen mainScreen].bounds.size.height-_feedbackContentText.frame.origin.y) animated:YES];
    }
}
- (BOOL)textView:(UITextView *)textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text{
    if ([text isEqualToString:@"\n"]) {
        [_feedbackContentText resignFirstResponder];
        [_scrollView setContentOffset:CGPointMake(0, 0) animated:YES];
        return NO;
    }
    
    if (![text isEqualToString:@""] && textView.text.length >= 128) {
        return NO;
    }
    return YES;
}
-(void)textViewDidChange:(UITextView *)textView{
    if (_feedbackContentText.text.length >= 128) {
        _feedbackContentText.text = [_feedbackContentText.text substringToIndex:128];
    }
    _numLabel.text = [NSString stringWithFormat:@"还能输入%d个字",128-textView.text.length];
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
    [_feedbackContentText resignFirstResponder];
    [self postQuestion];
}

#pragma mark - Table View

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    NSUInteger count = [self.feedbackList count];
    NSLog(@"COUNT,@%d",count);
    return count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *tableCellIdentifier = @"feedbackTableViewCell";
    FeedbackTableViewCell *cell = (FeedbackTableViewCell *)[tableView dequeueReusableCellWithIdentifier:tableCellIdentifier];
    if (cell == nil) {
        cell = [[FeedbackTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:tableCellIdentifier];
    }
    if (self.feedbackList.count != 0) {
        NSDictionary *s         = [self.feedbackList objectAtIndex:indexPath.row];
        FeedbackModel *feedback = [[FeedbackModel alloc] initWithDictionary:s];
        
        CGSize maximumSizeContent    = CGSizeMake([UIScreen mainScreen].bounds.size.width-10, CGFLOAT_MAX);
        NSDictionary * tdicContent   = [NSDictionary dictionaryWithObjectsAndKeys:cell.questionLable.font,NSFontAttributeName,nil];
        CGSize  actualsizeContent    = [[NSString stringWithFormat:@"反馈问题:%@",feedback.ma002] boundingRectWithSize:maximumSizeContent options:NSStringDrawingUsesLineFragmentOrigin |NSStringDrawingUsesFontLeading attributes:tdicContent context:nil].size;
        CGRect rectContent           = cell.questionLable.frame;
        rectContent.origin.x         = 5;
        rectContent.origin.y         = 5;
        rectContent.size.width       = [UIScreen mainScreen].bounds.size.width-10;
        rectContent.size.height      = actualsizeContent.height+10;
        cell.questionLable.frame     = rectContent;
        cell.questionDateLabel.frame = CGRectMake(5, cell.questionLable.frame.origin.y+cell.questionLable.frame.size.height,[UIScreen mainScreen].bounds.size.width-10, 20);
        
        
        if (feedback.ma005.length == 0) {
            CGRect rectContentReply      = cell.replyLable.frame;
            rectContentReply.size.height = 0;
            rectContentReply.origin.y    = cell.questionDateLabel.frame.origin.y+cell.questionDateLabel.frame.size.height;
            cell.replyLable.frame        = rectContentReply;
            
            cell.replyDateLable.frame    = CGRectMake(5, cell.replyLable.frame.origin.y+cell.replyLable.frame.size.height,[UIScreen mainScreen].bounds.size.width-10, 0);
            cell.replyLable.hidden       = YES;
            cell.replyDateLable.hidden   = YES;
        }
        else{
            cell.replyLable.hidden          = NO;
            cell.replyDateLable.hidden      = NO;
            CGSize maximumSizeContentReply  = CGSizeMake([UIScreen mainScreen].bounds.size.width-10, CGFLOAT_MAX);
            NSDictionary * tdicContentReply = [NSDictionary dictionaryWithObjectsAndKeys:cell.replyLable.font,NSFontAttributeName,nil];
            CGSize  actualsizeContentReply  = [[NSString stringWithFormat:@"回复问题:%@",feedback.ma005] boundingRectWithSize:maximumSizeContentReply options:NSStringDrawingUsesLineFragmentOrigin |NSStringDrawingUsesFontLeading attributes:tdicContentReply context:nil].size;
            CGRect rectContentReply         = cell.replyLable.frame;
            rectContentReply.origin.x       = 5;
            rectContentReply.size.width     = [UIScreen mainScreen].bounds.size.width-10;
            rectContentReply.origin.y       = cell.questionDateLabel.frame.origin.y+cell.questionDateLabel.frame.size.height;
            rectContentReply.size.height    = actualsizeContentReply.height+10;
            cell.replyLable.frame           = rectContentReply;
            cell.replyDateLable.frame       = CGRectMake(5, cell.replyLable.frame.origin.y+cell.replyLable.frame.size.height,[UIScreen mainScreen].bounds.size.width-10, 20);
        }
        cell.questionLable.text     = [NSString stringWithFormat:@"反馈问题:%@",feedback.ma002];
        cell.questionDateLabel.text = feedback.ma003;
        cell.replyDateLable.text    = feedback.ma007;
        cell.replyLable.text        = [NSString stringWithFormat:@"回复问题:%@",feedback.ma005];
    }
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    float fcellheight = 0.0;
    FeedbackTableViewCell *cell = (FeedbackTableViewCell *)[self tableView:_feedbackTableView cellForRowAtIndexPath:indexPath];
    fcellheight                 = cell.replyDateLable.frame.origin.y+cell.replyDateLable.frame.size.height+10;
    return fcellheight;
}

- (void)tableView:(UITableView *)tableView accessoryButtonTappedForRowWithIndexPath:(NSIndexPath *)indexPath
{
    NSLog(@"Detail selected");
}

//获取讨论区主题数据
-(void)initData{
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_05_01_02_02]];
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
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc]initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            if (feedbackList.count != 0) {
                [feedbackList removeAllObjects];
            }
            [feedbackList addObjectsFromArray:(NSArray *)result.dataObject];
            [_feedbackTableView reloadData];
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

//获取讨论区主题数据
-(void)postQuestion{
    NSString *question = [_feedbackContentText text];
    if (question.length == 0 || question.length > 128) {
        UIAlertView *alertView = [[UIAlertView alloc] initWithTitle:@"" message:@"内容不能为空或者超过128个字" delegate:Nil cancelButtonTitle:@"确定" otherButtonTitles: nil];
        [alertView show];
        return;
    }
    NSURL *url = [NSURL URLWithString:[SERVER_BASE_URI stringByAppendingString:SH01_05_01_02_01]];
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
    [request setPostValue:question forKey:@"MSG"];
    [request setCompletionBlock:^{
        [HUD hide:YES];
        NSString *responseString = [request responseString];
        ServerResult *result = [[ServerResult alloc]initWithDictionary:[responseString  objectFromJSONString]];
        if (result.isOperateSuccess) {
            [self initData];
            [_feedbackContentText setText:@""];
            _numLabel.text = [NSString stringWithFormat:@"还能输入128个字"];
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
            
            [_feedbackTableView scrollsToTop];
        }
        else{
            MBProgressHUD *HUD2 = [MBProgressHUD showHUDAddedTo:self.view.window animated:YES];
            HUD2.mode = MBProgressHUDModeText;
            HUD2.labelText = result.message;
            HUD2.margin = 10.f;
            HUD2.yOffset = 0.f;
            HUD2.removeFromSuperViewOnHide = YES;
            [HUD2 hide:YES afterDelay:1.0];
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

-(void)viewDidDisappear:(BOOL)animated{
    [_feedbackContentText resignFirstResponder];
    [_scrollView setContentOffset:CGPointMake(0, 0)];
}
@end
