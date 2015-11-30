//
//  RightImageButton.h
//  SmartHome
//
//  Created by wbiao on 14/12/22.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface RightImageButton : UIButton{
    CGRect rect;
}

@property(nonatomic,strong)UIImageView  *imageRight;
@property(nonatomic,strong)UILabel      *labelTitle;
@end
