//
//  HistoryTableViewCell.h
//  SmartHome
//
//  Created by apple on 14-11-14.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HistoryTableViewCell : UITableViewCell{
    float fcellWidth;
}

@property(nonatomic,strong)UILabel *labelInfo;
@property(nonatomic,strong)UILabel *labelStateValue;

-(id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier cellWidth:(float)cellWidth;
@end
