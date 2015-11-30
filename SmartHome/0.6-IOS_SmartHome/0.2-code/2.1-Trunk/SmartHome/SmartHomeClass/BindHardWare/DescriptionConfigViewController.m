//
//  DescriptionConfigViewController.m
//  SmartHome
//
//  Created by 李静 on 14-11-25.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "DescriptionConfigViewController.h"
#import "UIButton+Bootstrap.h"
#import "PhoneBindDeviceController.h"

#import "DeviceUtil.h"

@implementation DescriptionConfigViewController
@synthesize deviceId;
@synthesize lastBtn;
@synthesize nextBtn;
@synthesize desImage;
@synthesize configLabel;
@synthesize powerImage;
@synthesize powerHint;
@synthesize scrollView;


- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor colorWithRed:235/255.0 green:230/255.0 blue:226/255.0 alpha:1.0];
    // Do any additional setup after loading the view.
    [self initView];
}
-(void)initView{
    UIView *viewTop = [[UIView alloc]initWithFrame:CGRectMake(0, 0, SCREENWIDTH, TopBarHeight)];
    viewTop.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:viewTop];
    
    UILabel *labelTitle = [[UILabel alloc]initWithFrame:CGRectMake(60, STATUSBARHEIGHT, SCREENWIDTH-120, 44)];
    labelTitle.backgroundColor = [UIColor clearColor];
    labelTitle.textAlignment = NSTextAlignmentCenter;
    labelTitle.textColor = [UIColor blackColor];
    labelTitle.font = [UIFont systemFontOfSize:16];
    labelTitle.text = @"绑定设备";
    [viewTop addSubview:labelTitle];
    
    lastBtn = [[UIButton alloc]initWithFrame:CGRectMake(5, 24.5, 60, 35)];
    [lastBtn setTitle:@"上一步" forState:UIControlStateNormal];
    [lastBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    lastBtn.backgroundColor = [UIColor colorWithRed:217/255.0 green:83/255.0 blue:79/255.0 alpha:1];
    lastBtn.layer.masksToBounds = YES;
    lastBtn.layer.cornerRadius = 3.0;
    [lastBtn addTarget:self action:@selector(lastBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:lastBtn];
    
    nextBtn = [[UIButton alloc]initWithFrame:CGRectMake(SCREENWIDTH-65,24.5, 60, 35)];
    [nextBtn setTitle:@"下一步" forState:UIControlStateNormal];
    [nextBtn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
    nextBtn.backgroundColor = [UIColor colorWithRed:92/255.0 green:184/255.0 blue:92/255.0 alpha:1];
    nextBtn.layer.masksToBounds = YES;
    nextBtn.layer.cornerRadius = 3.0;
    [nextBtn addTarget:self action:@selector(nextBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    [viewTop addSubview:nextBtn];
    
    scrollView = [[UIScrollView alloc]initWithFrame:CGRectMake(0, TopBarHeight, SCREENWIDTH, FULLSCREENHEIGHT-TopBarHeight)];
    scrollView.contentSize = CGSizeMake([UIScreen mainScreen].bounds.size.width, FULLSCREENHEIGHT);
    [self.view addSubview:scrollView];
    
    UIView *viewDes = [[UIView alloc]initWithFrame:CGRectMake(10, 10, SCREENWIDTH-20, 430)];
    viewDes.backgroundColor = [UIColor whiteColor];
    [scrollView addSubview:viewDes];
    
    powerImage = [[UIImageView alloc]initWithFrame:CGRectMake(viewDes.frame.size.width/2-103, 10, 206, 113)];
    [viewDes addSubview:powerImage];
    powerHint = [[RTLabel alloc]initWithFrame:CGRectMake(0, powerImage.frame.origin.y+powerImage.frame.size.height+10, viewDes.frame.size.width, 60)];
    [viewDes addSubview:powerHint];
    
    desImage = [[UIImageView alloc]initWithFrame:CGRectMake(viewDes.frame.size.width/2-103, powerHint.frame.origin.y+powerHint.frame.size.height+10, 206, 113)];
    [viewDes addSubview:desImage];
    configLabel = [[RTLabel alloc]initWithFrame:CGRectMake(0, desImage.frame.origin.y+desImage.frame.size.height+10, viewDes.frame.size.width, 60)];
    [viewDes addSubview:configLabel];
    
    int deviceType = [DeviceUtil getDeviceType:self.deviceId];
    if (deviceType == SENSOR_TYPE_GAS) {
        [desImage setImage:[UIImage imageNamed:@"ui_sensor_hardware_show_gas.png"]];
        [configLabel setText:SENSOR_HARDWARE_GAS_HINT];
        [powerImage setImage:[UIImage imageNamed:@"ui_sensor_hardware_power_gas.png"]];
        [powerHint setText:SENSOR_HARDWARE_POWER_GAS_HINT];

    }
    else if (deviceType == SENSOR_TYPE_AIR){
        [desImage setImage:[UIImage imageNamed:@"ui_sensor_hardware_show_air.png"]];
        [configLabel setText:SENSOR_HARDWARE_AIR_HINT];
        [powerImage setImage:[UIImage imageNamed:@"ui_sensor_hardware_power_air.png"]];
        [powerHint setText:SENSOR_HARDWARE_POWER_AIR_HINT];
    }
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)lastBtnClick:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)nextBtnClick:(id)sender {
    HardwareViewController *hardwareViewController = [[HardwareViewController alloc]init];
    hardwareViewController.deviceId = deviceId;
    hardwareViewController.modalTransitionStyle = UIModalPresentationFormSheet;//跳转效果
    [self presentViewController:hardwareViewController animated:YES completion:nil];
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
