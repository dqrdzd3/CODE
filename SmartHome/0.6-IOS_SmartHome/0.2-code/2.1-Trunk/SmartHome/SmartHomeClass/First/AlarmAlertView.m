//
//  AlarmAlertView.m
//  SmartHome
//
//  Created by wbiao on 14/12/17.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "AlarmAlertView.h"
@implementation AlarmAlertView
@synthesize strSensorid;

-(id)initWithFrame:(CGRect)frame title:(NSString *)title message:(NSString *)message sensorid:(NSString *)sensorid{
    self = [super initWithFrame:frame];
    if (self) {
        rect = frame;
        strSensorid = sensorid;
        self.backgroundColor = [UIColor colorWithWhite:0.8 alpha:0.01];
        [self LoadView:title message:message];
    }
    return self;
}

-(void)LoadView:(NSString *)title message:(NSString *)message{
    alertView = [[UIView alloc]initWithFrame:CGRectMake(rect.size.width/2-120, rect.size.height/2-100, 240, 200)];
    alertView.backgroundColor = [UIColor whiteColor];
    alertView.layer.masksToBounds = YES;
    alertView.layer.cornerRadius = 4.0;
    [self addSubview:alertView];
    
    UIImageView *imageView = [[UIImageView alloc]initWithFrame:CGRectMake(alertView.frame.size.width/2-30, 5, 60, 60)];
    imageView.image = [UIImage imageNamed:@"alertimage.png"];
    [alertView addSubview:imageView];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(5,imageView.frame.origin.y+imageView.frame.size.height+5, alertView.frame.size.width-10, 40)];
    labelTitle.numberOfLines = 2;
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = title;
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor colorWithWhite:0.1 alpha:1.0];
    [alertView addSubview:labelTitle];
    
    
    UILabel *labelMessage = [[UILabel alloc]initWithFrame:CGRectMake(10,labelTitle.frame.origin.y+labelTitle.frame.size.height+5, alertView.frame.size.width-20, 0)];
    labelMessage.numberOfLines = 0;
    labelMessage.backgroundColor = [UIColor clearColor];
    labelMessage.font = [UIFont systemFontOfSize:14];
    labelMessage.text = message;
    labelMessage.textAlignment = NSTextAlignmentLeft;
    labelMessage.textColor = [UIColor colorWithWhite:0.1 alpha:1.0];
    [alertView addSubview:labelMessage];
    

    CGRect frame = labelMessage.frame;
    CGSize maximumSizeContent = CGSizeMake(alertView.frame.size.width-20, rect.size.height);
    NSDictionary * tdicContent = [NSDictionary dictionaryWithObjectsAndKeys:[UIFont systemFontOfSize:14],NSFontAttributeName,nil];
    CGSize  actualsizeContent = [message boundingRectWithSize:maximumSizeContent options:NSStringDrawingUsesLineFragmentOrigin |NSStringDrawingUsesFontLeading attributes:tdicContent context:nil].size;
    frame.size.height = actualsizeContent.height;
    labelMessage.frame = frame;
    
    CGRect alertframe = alertView.frame;
    alertframe.size.height = labelMessage.frame.origin.y+labelMessage.frame.size.height+50;
    alertframe.origin.y = rect.size.height/2-(labelMessage.frame.origin.y+labelMessage.frame.size.height+50)/2;
    alertView.frame = alertframe;
    
    UIButton *btnClose = [[UIButton alloc]initWithFrame:CGRectMake(alertView.frame.size.width/2-35, alertView.frame.size.height-40, 70, 35)];
    btnClose.backgroundColor = [UIColor colorWithRed:190.0/255.0 green:22.0/255.0 blue:2.0/255.0 alpha:1.0];
    [btnClose setTitle:@"确定" forState:UIControlStateNormal];
    [btnClose setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    btnClose.layer.masksToBounds = YES;
    btnClose.layer.cornerRadius = 3.0;
    [alertView addSubview:btnClose];
    
    [btnClose addTarget:self action:@selector(ClickOk) forControlEvents:UIControlEventTouchUpInside];
}

-(void)ClickOk{
    [self removeFromSuperview];
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
