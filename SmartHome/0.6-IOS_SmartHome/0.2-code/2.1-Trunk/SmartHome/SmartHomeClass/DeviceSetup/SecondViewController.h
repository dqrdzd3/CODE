//
//  SecondViewController.h
//  RESideMenuStoryboards
//
//  Created by Roman Efimov on 10/9/13.
//  Copyright (c) 2013 Roman Efimov. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RESideMenu.h"
#import "Reachability.h"
#import "EASYLINK.h"
#import "DeviceTableViewCell.h"
#import "ServerConstants.h"

#import "QRCodeViewController.h"
@interface SecondViewController : UIViewController<UIAlertViewDelegate,UITextFieldDelegate, EasyLinkFTCDelegate,UITableViewDataSource,UITableViewDelegate,QRCodeViewControllerDelegate,DeviceTableViewCellDelegate>{
    Reachability *wifiReachability;
    
    NSMutableDictionary *deviceIPConfig;
    EASYLINK *easylink_config;
    
    MBProgressHUD *HUD;
    NSString *strDeleteSensorid;
    UILabel *labelsblb;

    NSString *strChangeDeviceName;
    NSString *strChangeDeviceId;
    UIView *viewSetup;

    NSTimer *timer;
    
    NSMutableArray *aryViewDetail;
    NSMutableArray *displayServices;
    NSMutableArray *displayServicesID;
}
@property (strong, nonatomic)  UIButton *deviceSetupNetBtn;
@property (strong, nonatomic)  UIButton *deviceRegisterBtn;
- (void)deviceRegisterClick:(id)sender;
@property (strong, nonatomic)  UIButton *scanBtn;
@property (strong, nonatomic)  UITextField *deviceId;
@property (strong, nonatomic)  UILabel *wifiName;
@property (strong, nonatomic)  UITextField *wifiPassword;

/*
 This method waits for an acknowledge from the remote device than it stops the transmit to the remote device and returns with data it got from the remote device.
 This method blocks until it gets respond.
 The method will return true if it got the ack from the remote device or false if it got aborted by a call to stopTransmitting.
 In case of a failure the method throws an OSFailureException.
 */
- (void) waitForAck: (id)sender;
/*
 This method start the transmitting the data to connected
 AP. Nerwork validation is also done here. All exceptions from
 library is handled.
 */
- (void)startTransmitting: (int)version;
@property (strong, nonatomic) UITableView *devicesTableView;
/*
 Notification method handler when status of wifi changes
 @param the fired notification object
 */
- (void)wifiStatusChanged:(NSNotification*)notification;


/* enableUIAccess
 * enable / disable the UI access like enable / disable the textfields
 * and other component while transmitting the packets.
 * @param: vbool is to validate the controls.
 */
-(void) enableUIAccess:(BOOL) isEnable;
@property (nonatomic, retain, readwrite) NSThread* waitForAckThread;



-(void)ReloadData;

@end
