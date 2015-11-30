//
//  AlarmAlertView.h
//  SmartHome
//
//  Created by wbiao on 14/12/17.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AlarmAlertView : UIView{
    UIView *alertView;
    CGRect rect;
}

@property(nonatomic,strong)NSString *strSensorid;
-(id)initWithFrame:(CGRect)frame title:(NSString *)title message:(NSString *)message sensorid:(NSString *)sensorid;
@end
