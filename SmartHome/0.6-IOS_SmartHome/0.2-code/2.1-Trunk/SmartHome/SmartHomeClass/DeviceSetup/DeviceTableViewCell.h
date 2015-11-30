//
//  DeviceTableViewCell.h
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-28.
//  Copyright (c) 2014年 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol DeviceTableViewCellDelegate <NSObject>
-(void)DeviceTableViewCellDeviceNameEditBegin:(NSInteger)index;
-(void)DeviceTableViewCellDeviceNameEditDone:(NSInteger)index devicename:(NSString *)deviceName deviceid:(NSString *)deviceId;

@end
@interface DeviceTableViewCell : UITableViewCell
@property (strong, nonatomic) UILabel *deviceId;
@property (strong, nonatomic) UILabel *isOnline;
@property (strong, nonatomic) UILabel *deviceName;

@property (strong, nonatomic) id<DeviceTableViewCellDelegate>delegate;
@end
