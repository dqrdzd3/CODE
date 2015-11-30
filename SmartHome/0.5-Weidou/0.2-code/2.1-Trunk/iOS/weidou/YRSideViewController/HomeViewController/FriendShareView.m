//
//  FriendShareView.m
//  Weidou
//
//  Created by wbiao on 14/11/25.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "FriendShareView.h"

@implementation FriendShareView
-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        appdelegate = (AppDelegate *)[UIApplication sharedApplication].delegate;
        [self LoadSubView];
    }
    return self;
}
-(void)ReloadData{
    labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    NSDictionary *attributes = @{NSFontAttributeName:labelCurValue.font};
    CGSize size = [labelCurValue.text sizeWithAttributes:attributes];
    CGRect frame = labelCurValue.frame;
    frame.size.width = size.width+5;
    labelCurValue.frame = frame;
    labelUnit.frame = CGRectMake(labelCurValue.frame.origin.x+labelCurValue.frame.size.width, 20, 50, 20);
    labelUnit.text = [RealTimeModel SharedRealTimeModel].strUnit;
}
-(void)LoadSubView{
    UILabel *labelLeft = [[UILabel alloc]initWithFrame:CGRectMake(10, 20, 50, 20)];
    labelLeft.backgroundColor = [UIColor clearColor];
    labelLeft.font = [UIFont systemFontOfSize:14.0];
    labelLeft.textColor = Color_MiddleHei;
    labelLeft.text = @"当前值";
    [self addSubview:labelLeft];
    
    labelCurValue = [[UILabel alloc]initWithFrame:CGRectMake(labelLeft.frame.origin.x+labelLeft.frame.size.width, 0, rect.size.width-50, 40)];
    labelCurValue.backgroundColor = [UIColor clearColor];
    labelCurValue.font = [UIFont boldSystemFontOfSize:35.0];
    labelCurValue.textColor = Color_MiddleHei;
    labelCurValue.text = [RealTimeModel SharedRealTimeModel].strPieValue;
    [self addSubview:labelCurValue];
    
    NSDictionary *attributes = @{NSFontAttributeName:labelCurValue.font};
    CGSize size = [labelCurValue.text sizeWithAttributes:attributes];
    CGRect frame = labelCurValue.frame;
    frame.size.width = size.width+5;
    labelCurValue.frame = frame;
    
    
    labelUnit = [[UILabel alloc]initWithFrame:CGRectMake(labelCurValue.frame.origin.x+labelCurValue.frame.size.width, 20, 50, 20)];
    labelUnit.backgroundColor = [UIColor clearColor];
    labelUnit.font = [UIFont systemFontOfSize:14.0];
    labelUnit.textColor = Color_MiddleHei;
    labelUnit.text = [RealTimeModel SharedRealTimeModel].strUnit;
    [self addSubview:labelUnit];
    
    
    labelShareRealTime = [[UILabel alloc]initWithFrame:CGRectMake(labelLeft.frame.origin.x, labelLeft.frame.origin.y+labelLeft.frame.size.height+15, (SCREENWIDTH-20)/2, 27)];
    labelShareRealTime.font = [UIFont systemFontOfSize:16];
    labelShareRealTime.textColor = [UIColor blackColor];
    labelShareRealTime.text = @"分享实时数据";
    [self addSubview:labelShareRealTime];
    
    labelShareHistory = [[UILabel alloc]initWithFrame:CGRectMake(labelShareRealTime.frame.origin.x, labelShareRealTime.frame.origin.y+labelShareRealTime.frame.size.height+15, (SCREENWIDTH-20)/2, 27)];
    labelShareHistory.font = [UIFont systemFontOfSize:16];
    labelShareHistory.textColor = [UIColor blackColor];
    labelShareHistory.text = @"分享历史数据";
    [self addSubview:labelShareHistory];
    
    labelShareFight = [[UILabel alloc]initWithFrame:CGRectMake(labelShareRealTime.frame.origin.x, labelShareHistory.frame.origin.y+labelShareHistory.frame.size.height+15, (SCREENWIDTH-20)/2, 27)];
    labelShareFight.font = [UIFont systemFontOfSize:16];
    labelShareFight.textColor = [UIColor blackColor];
    labelShareFight.text = @"分享比赛";
    //[self addSubview:labelShareFight];
    
    switchShareRealTime = [[UISwitch alloc]initWithFrame:CGRectMake(SCREENWIDTH-70, labelShareRealTime.frame.origin.y, 60, 27)];
    [self addSubview:switchShareRealTime];
    switchShareHistory = [[UISwitch alloc]initWithFrame:CGRectMake(SCREENWIDTH-70, labelShareHistory.frame.origin.y, 60, 27)];
    [self addSubview:switchShareHistory];
    switchShareFight = [[UISwitch alloc]initWithFrame:CGRectMake(SCREENWIDTH-70, labelShareFight.frame.origin.y, 60, 27)];
    //[self addSubview:switchShareFight];
    [switchShareRealTime addTarget: self action:@selector(switchValueChanged:) forControlEvents:UIControlEventValueChanged];
    [switchShareHistory addTarget: self action:@selector(switchValueChanged:) forControlEvents:UIControlEventValueChanged];
    [switchShareFight addTarget: self action:@selector(switchValueChanged:) forControlEvents:UIControlEventValueChanged];
    switchShareRealTime.on = YES;
    switchShareHistory.on = YES;
    switchShareFight.on = YES;
    
    UILabel *labeltmp = [[UILabel alloc]initWithFrame:CGRectMake(labelShareRealTime.frame.origin.x, labelShareHistory.frame.origin.y+labelShareHistory.frame.size.height+10, (SCREENWIDTH-20), 20)];
    labeltmp.font = [UIFont systemFontOfSize:14];
    labeltmp.textColor = [UIColor grayColor];
    labeltmp.textAlignment = NSTextAlignmentCenter;
    labeltmp.text = @"将信息分享到";
    [self addSubview:labeltmp];
    
    UIView *viewline = [[UIView alloc]initWithFrame:CGRectMake(0, labeltmp.frame.origin.y+labeltmp.frame.size.height, SCREENWIDTH, 1)];
    viewline.backgroundColor = [UIColor blackColor];
    [self addSubview:viewline];
    
    
    btnqq = [[UIButton alloc]initWithFrame:CGRectMake(10, viewline.frame.origin.y+5, 58, 58)];
    [btnqq setImage:[UIImage imageNamed:@"logo_qq.png"] forState:UIControlStateNormal];
    [self addSubview:btnqq];
    //SCREENWIDTH/2-29
    btnqqzone = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-29, viewline.frame.origin.y+5, 58, 58)];
    [btnqqzone setImage:[UIImage imageNamed:@"logo_qzone.png"] forState:UIControlStateNormal];
    [self addSubview:btnqqzone];
    //微博分享会失败，暂时隐藏掉
    btnqqwb = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-68, viewline.frame.origin.y+5, 58, 58)];
    [btnqqwb setImage:[UIImage imageNamed:@"logo_tencentweibo.png"] forState:UIControlStateNormal];
    [self addSubview:btnqqwb];
    
    btnwx = [[UIButton alloc]initWithFrame:CGRectMake(10, btnqqwb.frame.origin.y+btnqqwb.frame.size.height+20, 58, 58)];
    [btnwx setImage:[UIImage imageNamed:@"logo_wechat.png"] forState:UIControlStateNormal];
    [self addSubview:btnwx];
    //微博分享会失败，暂时隐藏掉
    btnwxpyq = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH/2-29, btnqqwb.frame.origin.y+btnqqwb.frame.size.height+20, 58, 58)];
    [btnwxpyq setImage:[UIImage imageNamed:@"logo_wechatmoments.png"] forState:UIControlStateNormal];
    [self addSubview:btnwxpyq];
    btnsinawb = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-68, btnqqwb.frame.origin.y+btnqqwb.frame.size.height+20, 58, 58)];
    [btnsinawb setImage:[UIImage imageNamed:@"logo_sinaweibo.png"] forState:UIControlStateNormal];
    [self addSubview:btnsinawb];
    
    
    [btnqq addTarget:self action:@selector(shareToQQFriendClickHandler:) forControlEvents:UIControlEventTouchUpInside];
    [btnqqzone addTarget:self action:@selector(shareToQQSpaceClickHandler:) forControlEvents:UIControlEventTouchUpInside];
    [btnqqwb addTarget:self action:@selector(shareToTencentWeiboClickHandler:) forControlEvents:UIControlEventTouchUpInside];
    [btnwx addTarget:self action:@selector(shareToWeixinSessionClickHandler:) forControlEvents:UIControlEventTouchUpInside];
    [btnwxpyq addTarget:self action:@selector(shareToWeixinTimelineClickHandler:) forControlEvents:UIControlEventTouchUpInside];
    [btnsinawb addTarget:self action:@selector(shareToSinaWeiboClickHandler:) forControlEvents:UIControlEventTouchUpInside];
    
    
}
- (void)switchValueChanged:(id)sender{
    UISwitch* control = (UISwitch*)sender;
    if(control == switchShareRealTime){
        
    }
    else if (control == switchShareHistory){
        
    }
    else if (control == switchShareFight){
        
    }
}

-(NSString *)shareContent{
    NSString *strContent = @"";
    if ([RealTimeModel SharedRealTimeModel].brealStart) {
        strContent = [NSString stringWithFormat:@"当前的%@值为%@%@(%@)，威豆已启动【该数据由空气电台提供】",[RealTimeModel SharedRealTimeModel].strName,[RealTimeModel SharedRealTimeModel].strPieValue,[RealTimeModel SharedRealTimeModel].strUnit,[RealTimeModel SharedRealTimeModel].strStatus];
    }
    else{
        strContent = [NSString stringWithFormat:@"当前的%@值为%@%@(%@)，未检测到威豆启动，请重新插入【该数据由空气电台提供】",[RealTimeModel SharedRealTimeModel].strName,[RealTimeModel SharedRealTimeModel].strPieValue,[RealTimeModel SharedRealTimeModel].strUnit,[RealTimeModel SharedRealTimeModel].strStatus];
    }
    return strContent;
}

-(NSString *)ShareUrl{
    NSString *strShareUrl = @"";
    if (switchShareRealTime.on) {
        strShareUrl = @"";
        NSString *strName = [RealTimeModel SharedRealTimeModel].strName;
        NSString *strUnit = [RealTimeModel SharedRealTimeModel].strUnit;
        strUnit = [strUnit stringByReplacingOccurrencesOfString:@"/" withString:@"_"];
        if ([strName isEqualToString:HOME_NAME_ALCOHOL]) {
            strName = @"3";
        }
        else if ([strName isEqualToString:HOME_NAME_CO]){
            strName = @"4";
        }
        else if ([strName isEqualToString:HOME_NAME_CH2O]){
            strName = @"2";
        }
        NSDate *date = [NSDate date];
        NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
        [dateFormatter setDateFormat:@"MM-dd/HH:mm"];
        NSArray *aryTmp = [[dateFormatter stringFromDate:date] componentsSeparatedByString:@"/"];
        strShareUrl = [NSString stringWithFormat:@"http://weiguo.hanwei.cn/smart/share/shareW.html?recordDate=%@&recordTime=%@&curValue=%@&maxValue=%@&avgValue=%@&equip=%@0&unit=%@&hour=[0]]",[aryTmp firstObject],[aryTmp lastObject],[RealTimeModel SharedRealTimeModel].strPieValue,[RealTimeModel SharedRealTimeModel].strMax,[RealTimeModel SharedRealTimeModel].strAverage,strName,strUnit];
        
    }
    if (switchShareHistory.on) {
        strShareUrl = @"";
        NSString *strMin = @"";
        NSString *strHour = @"";
        NSString *strName = [RealTimeModel SharedRealTimeModel].strName;
        NSString *strUnit = [RealTimeModel SharedRealTimeModel].strUnit;
        strUnit = [strUnit stringByReplacingOccurrencesOfString:@"/" withString:@"_"];
        if ([strName isEqualToString:HOME_NAME_ALCOHOL]) {
            strName = @"3";
        }
        else if ([strName isEqualToString:HOME_NAME_CO]){
            strName = @"4";
        }
        else if ([strName isEqualToString:HOME_NAME_CH2O]){
            strName = @"2";
        }
        
        for (NSString *str in [RealTimeModel SharedRealTimeModel].maryHistoryS) {
            if (strMin.length == 0) {
                strMin = [NSString stringWithFormat:@"%@",str];
            }
            else{
               strMin = [NSString stringWithFormat:@"%@,%@",strMin,str];
            }
        }
        for (NSString *str in [RealTimeModel SharedRealTimeModel].maryHistoryM) {
            if (strHour.length == 0) {
                strHour = [NSString stringWithFormat:@"%@",str];
            }
            else{
                strHour = [NSString stringWithFormat:@"%@,%@",strHour,str];
            }
        }
        
        NSDate *date = [NSDate date];
        NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
        [dateFormatter setDateFormat:@"MM-dd/HH:mm"];
        NSArray *aryTmp = [[dateFormatter stringFromDate:date] componentsSeparatedByString:@"/"];
        strShareUrl = [NSString stringWithFormat:@"http://weiguo.hanwei.cn/smart/share/shareW.html?recordDate=%@&recordTime=%@&curValue=%@&maxValue=%@&avgValue=%@&equip=%@&unit=%@&minute=[%@]&hour=[%@]",[aryTmp firstObject],[aryTmp lastObject],[RealTimeModel SharedRealTimeModel].strPieValue,[RealTimeModel SharedRealTimeModel].strMax,[RealTimeModel SharedRealTimeModel].strAverage,strName,strUnit,strMin,strHour];
    }
    return strShareUrl;
}
/**
 *	@brief	分享到新浪微博
 *
 *	@param 	sender 	事件对象
 */
- (void)shareToSinaWeiboClickHandler:(UIButton *)sender
{
    if (!switchShareHistory.on && !switchShareRealTime.on) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请打开分享开关" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        [alert show];
        return;
    }
    //创建分享内容
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"Icon0" ofType:@"png"];
    id<ISSContent> publishContent = [ShareSDK content:[self shareContent]
                                       defaultContent:@""
                                                image:[ShareSDK imageWithPath:imagePath]
                                                title:@"威豆分享"
                                                  url:[self ShareUrl]
                                          description:nil
                                            mediaType:SSPublishContentMediaTypeText];
    
    //创建弹出菜单容器
    id<ISSContainer> container = [ShareSDK container];
    [container setIPadContainerWithView:sender arrowDirect:UIPopoverArrowDirectionUp];
    
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:appdelegate.viewDelegate];
    
    //在授权页面中添加关注官方微博
    [authOptions setFollowAccounts:[NSDictionary dictionaryWithObjectsAndKeys:
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeSinaWeibo),
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeTencentWeibo),
                                    nil]];
    
    //显示分享菜单
    [ShareSDK showShareViewWithType:ShareTypeSinaWeibo
                          container:container
                            content:publishContent
                      statusBarTips:YES
                        authOptions:authOptions
                       shareOptions:[ShareSDK defaultShareOptionsWithTitle:nil
                                                           oneKeyShareList:[NSArray defaultOneKeyShareList]
                                                            qqButtonHidden:NO
                                                     wxSessionButtonHidden:NO
                                                    wxTimelineButtonHidden:NO
                                                      showKeyboardOnAppear:NO
                                                         shareViewDelegate:appdelegate.viewDelegate
                                                       friendsViewDelegate:appdelegate.viewDelegate
                                                     picViewerViewDelegate:nil]
                             result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                 
                                 if (state == SSPublishContentStateSuccess)
                                 {
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"发表成功"));
                                 }
                                 else if (state == SSPublishContentStateFail)
                                 {
                                     UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"未安装相关应用,分享失败" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                                     [alert show];
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"发布失败!error code == %d, error code == %@"), [error errorCode], [error errorDescription]);
                                 }
                             }];
}

/**
 *	@brief	分享到腾讯微博
 *
 *	@param 	sender 	事件对象
 */
- (void)shareToTencentWeiboClickHandler:(UIButton *)sender
{
    if (!switchShareHistory.on && !switchShareRealTime.on) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请打开分享开关" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        [alert show];
        return;
    }
    //创建分享内容
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"Icon0" ofType:@"png"];
    id<ISSContent> publishContent = [ShareSDK content:[self shareContent]
                                       defaultContent:@""
                                                image:[ShareSDK imageWithPath:imagePath]
                                                title:@"威豆分享"
                                                  url:[self ShareUrl]
                                          description:nil
                                            mediaType:SSPublishContentMediaTypeText];
    
    //创建弹出菜单容器
    id<ISSContainer> container = [ShareSDK container];
    [container setIPadContainerWithView:sender arrowDirect:UIPopoverArrowDirectionUp];
    
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:appdelegate.viewDelegate];
    
    //在授权页面中添加关注官方微博
    [authOptions setFollowAccounts:[NSDictionary dictionaryWithObjectsAndKeys:
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeSinaWeibo),
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeTencentWeibo),
                                    nil]];
    
    //显示分享菜单
    [ShareSDK showShareViewWithType:ShareTypeTencentWeibo
                          container:container
                            content:publishContent
                      statusBarTips:YES
                        authOptions:authOptions
                       shareOptions:[ShareSDK defaultShareOptionsWithTitle:nil
                                                           oneKeyShareList:[NSArray defaultOneKeyShareList]
                                                            qqButtonHidden:NO
                                                     wxSessionButtonHidden:NO
                                                    wxTimelineButtonHidden:NO
                                                      showKeyboardOnAppear:NO
                                                         shareViewDelegate:appdelegate.viewDelegate
                                                       friendsViewDelegate:appdelegate.viewDelegate
                                                     picViewerViewDelegate:nil]
                             result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                 
                                 if (state == SSPublishContentStateSuccess)
                                 {
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"发表成功"));
                                 }
                                 else if (state == SSPublishContentStateFail)
                                 {
                                     UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"未安装相关应用,分享失败" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                                     [alert show];
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"发布失败!error code == %d, error code == %@") , [error errorCode], [error errorDescription]);
                                 }
                             }];
}

/**
 *	@brief	分享给QQ好友
 *
 *	@param 	sender 	事件对象
 */
- (void)shareToQQFriendClickHandler:(UIButton *)sender
{
    if (!switchShareHistory.on && !switchShareRealTime.on) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请打开分享开关" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        [alert show];
        return;
    }
    //创建分享内容
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"Icon0" ofType:@"png"];
    id<ISSContent> publishContent = [ShareSDK content:@""
                                       defaultContent:@""
                                                image:[ShareSDK imageWithPath:imagePath]
                                                title:@"威豆分享"
                                                  url:[self ShareUrl]
                                          description:[self shareContent]
                                            mediaType:SSPublishContentMediaTypeNews];
    
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:appdelegate.viewDelegate];
    
    //在授权页面中添加关注官方微博
    [authOptions setFollowAccounts:[NSDictionary dictionaryWithObjectsAndKeys:
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeSinaWeibo),
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeTencentWeibo),
                                    nil]];
    
    //显示分享菜单
    [ShareSDK showShareViewWithType:ShareTypeQQ
                          container:nil
                            content:publishContent
                      statusBarTips:YES
                        authOptions:authOptions
                       shareOptions:[ShareSDK defaultShareOptionsWithTitle:nil
                                                           oneKeyShareList:[NSArray defaultOneKeyShareList]
                                                            qqButtonHidden:NO
                                                     wxSessionButtonHidden:NO
                                                    wxTimelineButtonHidden:NO
                                                      showKeyboardOnAppear:NO
                                                         shareViewDelegate:appdelegate.viewDelegate
                                                       friendsViewDelegate:appdelegate.viewDelegate
                                                     picViewerViewDelegate:nil]
                             result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                 
                                 if (state == SSPublishContentStateSuccess)
                                 {
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"发表成功"));
                                 }
                                 else if (state == SSPublishContentStateFail)
                                 {
                                     UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请安装QQ应用进行分享" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                                     [alert show];
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"发布失败!error code == %d, error code == %@"), [error errorCode], [error errorDescription]);
                                 }
                             }];
}

/**
 *	@brief	分享到QQ空间
 *
 *	@param 	sender 	事件对象
 
 */
- (void)shareToQQSpaceClickHandler:(UIButton *)sender
{
    if (!switchShareHistory.on && !switchShareRealTime.on) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请打开分享开关" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        [alert show];
        return;
    }
    //创建分享内容
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"Icon0" ofType:@"png"];
    id<ISSContent> publishContent = [ShareSDK content:@""
                                       defaultContent:@""
                                                image:[ShareSDK imageWithPath:imagePath]
                                                title:@"威豆分享"
                                                  url:[self ShareUrl]
                                          description:[self shareContent]
                                            mediaType:SSPublishContentMediaTypeNews];
    
    
    //创建弹出菜单容器
    id<ISSContainer> container = [ShareSDK container];
    [container setIPadContainerWithView:sender arrowDirect:UIPopoverArrowDirectionUp];
    
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:appdelegate.viewDelegate];
    
    //在授权页面中添加关注官方微博
    [authOptions setFollowAccounts:[NSDictionary dictionaryWithObjectsAndKeys:
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeSinaWeibo),
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeTencentWeibo),
                                    nil]];
    
    //显示分享菜单
    [ShareSDK showShareViewWithType:ShareTypeQQSpace
                          container:container
                            content:publishContent
                      statusBarTips:YES
                        authOptions:authOptions
                       shareOptions:[ShareSDK defaultShareOptionsWithTitle:nil
                                                           oneKeyShareList:[NSArray defaultOneKeyShareList]
                                                            qqButtonHidden:NO
                                                     wxSessionButtonHidden:NO
                                                    wxTimelineButtonHidden:NO
                                                      showKeyboardOnAppear:NO
                                                         shareViewDelegate:appdelegate.viewDelegate
                                                       friendsViewDelegate:appdelegate.viewDelegate
                                                     picViewerViewDelegate:nil]
                             result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                 
                                 if (state == SSPublishContentStateSuccess)
                                 {
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"发表成功"));
                                 }
                                 else if (state == SSPublishContentStateFail)
                                 {
                                     UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请安装QQ应用进行分享" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                                     [alert show];
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"发布失败!error code == %d, error code == %@"), [error errorCode], [error errorDescription]);
                                 }
                             }];
}

/**
 *	@brief	分享给微信好友
 *
 *	@param 	sender 	事件对象
 */
- (void)shareToWeixinSessionClickHandler:(UIButton *)sender
{
    if (!switchShareHistory.on && !switchShareRealTime.on) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请打开分享开关" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        [alert show];
        return;
    }
    //创建分享内容
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"Icon0" ofType:@"png"];
    id<ISSContent> publishContent = [ShareSDK content:[self shareContent]
                                       defaultContent:@""
                                                image:[ShareSDK imageWithPath:imagePath]
                                                title:@"威豆分享"
                                                  url:[self ShareUrl]
                                          description:[self shareContent]
                                            mediaType:SSPublishContentMediaTypeNews];
    
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:appdelegate.viewDelegate];
    
    //在授权页面中添加关注官方微博
    [authOptions setFollowAccounts:[NSDictionary dictionaryWithObjectsAndKeys:
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeSinaWeibo),
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeTencentWeibo),
                                    nil]];
    
    //显示分享菜单
    [ShareSDK showShareViewWithType:ShareTypeWeixiSession
                          container:nil
                            content:publishContent
                      statusBarTips:YES
                        authOptions:authOptions
                       shareOptions:[ShareSDK defaultShareOptionsWithTitle:nil
                                                           oneKeyShareList:[NSArray defaultOneKeyShareList]
                                                            qqButtonHidden:NO
                                                     wxSessionButtonHidden:NO
                                                    wxTimelineButtonHidden:NO
                                                      showKeyboardOnAppear:NO
                                                         shareViewDelegate:appdelegate.viewDelegate
                                                       friendsViewDelegate:appdelegate.viewDelegate
                                                     picViewerViewDelegate:nil]
                             result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                 
                                 if (state == SSPublishContentStateSuccess)
                                 {
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"发表成功"));
                                 }
                                 else if (state == SSPublishContentStateFail)
                                 {
                                     UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请安装微信应用进行分享" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                                     [alert show];
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"发布失败!error code == %d, error code == %@"), [error errorCode], [error errorDescription]);
                                 }
                             }];
}

/**
 *	@brief	分享给微信朋友圈
 *
 *	@param 	sender 	事件对象
 */
- (void)shareToWeixinTimelineClickHandler:(UIButton *)sender
{
    if (!switchShareHistory.on && !switchShareRealTime.on) {
        UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请打开分享开关" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
        [alert show];
        return;
    }
    //创建分享内容
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"Icon0" ofType:@"png"];
    id<ISSContent> publishContent = [ShareSDK content:[self shareContent]
                                       defaultContent:@""
                                                image:[ShareSDK imageWithPath:imagePath]
                                                title:@"威豆分享"
                                                  url:[self ShareUrl]
                                          description:[self shareContent]
                                            mediaType:SSPublishContentMediaTypeNews];
    
    id<ISSAuthOptions> authOptions = [ShareSDK authOptionsWithAutoAuth:YES
                                                         allowCallback:YES
                                                         authViewStyle:SSAuthViewStyleFullScreenPopup
                                                          viewDelegate:nil
                                               authManagerViewDelegate:appdelegate.viewDelegate];
    
    //在授权页面中添加关注官方微博
    [authOptions setFollowAccounts:[NSDictionary dictionaryWithObjectsAndKeys:
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeSinaWeibo),
                                    [ShareSDK userFieldWithType:SSUserFieldTypeName value:@"ShareSDK"],
                                    SHARE_TYPE_NUMBER(ShareTypeTencentWeibo),
                                    nil]];
    
    //显示分享菜单
    [ShareSDK showShareViewWithType:ShareTypeWeixiTimeline
                          container:nil
                            content:publishContent
                      statusBarTips:YES
                        authOptions:authOptions
                       shareOptions:[ShareSDK defaultShareOptionsWithTitle:nil
                                                           oneKeyShareList:[NSArray defaultOneKeyShareList]
                                                            qqButtonHidden:NO
                                                     wxSessionButtonHidden:NO
                                                    wxTimelineButtonHidden:NO
                                                      showKeyboardOnAppear:NO
                                                         shareViewDelegate:appdelegate.viewDelegate
                                                       friendsViewDelegate:appdelegate.viewDelegate
                                                     picViewerViewDelegate:nil]
                             result:^(ShareType type, SSResponseState state, id<ISSPlatformShareInfo> statusInfo, id<ICMErrorInfo> error, BOOL end) {
                                 
                                 if (state == SSPublishContentStateSuccess)
                                 {
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_SUC", @"发表成功"));
                                 }
                                 else if (state == SSPublishContentStateFail)
                                 {
                                     UIAlertView *alert = [[UIAlertView alloc]initWithTitle:@"" message:@"请安装微信应用进行分享" delegate:self cancelButtonTitle:@"确定" otherButtonTitles:nil, nil];
                                     [alert show];
                                     NSLog(NSLocalizedString(@"TEXT_SHARE_FAI", @"发布失败!error code == %d, error code == %@"), [error errorCode], [error errorDescription]);
                                 }
                             }];
}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
