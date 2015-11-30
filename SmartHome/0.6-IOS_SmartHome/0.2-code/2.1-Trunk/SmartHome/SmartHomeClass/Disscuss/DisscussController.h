//
//  DisscussController.h
//  SmartHome
//
//  Created by 李静 on 14-11-10.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import <UIKit/UIKit.h>


#import "RESideMenu.h"
@interface DisscussController : UIViewController <UITableViewDataSource,UITableViewDelegate>{
    MBProgressHUD *HUD;
}
@property (strong, nonatomic) UITableView *mTableView;
@property (strong, nonatomic) NSMutableArray *disscussThemes;

-(void)ReloadData;
@end
