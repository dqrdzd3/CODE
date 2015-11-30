//
//  LeftMenuTableViewCell.h
//  Weidou
//
//  Created by wbiao on 14/12/8.
//  Copyright (c) 2014年 YueRuo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LeftMenuTableViewCell : UITableViewCell{
    float fwidth;
    float fheight;
}

@property(nonatomic,strong)UILabel *labelTitle;
@property(nonatomic,strong)UIImageView *imageTitle;

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier cellwidth:(float)width cellheight:(float)height;
@end
