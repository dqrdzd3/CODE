//
//  ServerConstants.m
//  RESideMenuStoryboardsExample
//
//  Created by 李静 on 14-10-22.
//  Copyright (c) 2014年 汉威电子股份有限公司. All rights reserved.
//

#import "ServerConstants.h"

@implementation ServerConstants
//NSString *const BASE_URI                 = @"http://121.40.94.44/smart/";
NSString *const BASE_URI                   = @"http://weiguo.hanwei.cn/smart/";
//NSString *const BASE_URI                 = @"http://iweiguo.hwsensor.com/smart/";

NSString *const BASE_URI_NAME              = @"hwmobile/smart/";

NSString *const SERVER_BASE_URI            = @"http://weiguo.hanwei.cn/smart/hwmobile/smart/";
NSString *const DOWNLOADSTREAM             = @"u001!doDownLoad";
//NSString *const SERVER_BASE_URI          = @"http://iweiguo.hwsensor.com/smart/hwmobile/smart/";
//NSString *const SERVER_BASE_URI          = @"http://121.40.94.44/smart/hwmobile/smart/";



//NSString *const BASE_URI                   = @"http://192.168.111.186:8080/smart/";
//NSString *const SERVER_BASE_URI            = @"http://192.168.111.186:8080/smart/hwmobile/smart/";


NSString *const SERVER_RESULT_CODE_KEY     = @"code";
NSString *const SERVER_RESULT_MESSAGE_KEY  = @"message";
NSString *const SERVER_RESULT_CODE_SUCCESS = @"1";
NSString *const SERVER_RESULT_CODE_FAIL    = @"0";
NSString *const SH01_01_01_02              = @"d002!doSaveAddEquips";
NSString *const SH01_01_01_03              = @"d002!doListEquip";
NSString *const SH01_01_01_04              = @"d002!doDeleteEquips";
NSString *const SH01_02_01                 = @"d002!doListDetailHistory";
NSString *const SH01_02_01_02              = @"a001!doHisAlarmList";
NSString *const SH01_03_01_02              = @"u001!doEditInfo";
NSString *const SH01_03_02_04              = @"u001!doLogin";
NSString *const SH01_03_02_01              = @"u001!doCreateAccount";
NSString *const SH01_03_02_02              = @"u001!doResetPw";
NSString *const SH01_03_02_03              = @"u001!doEditPw";
NSString *const SH01_03_02_05              = @"u001!sendCode";

NSString *const SH01_05_01_01_01           = @"s004!doView";
NSString *const SH01_05_01_01_02           = @"s005!doReply";
NSString *const SH01_05_01_01_03           = @"s005!doGetReplayList";
NSString *const SH01_05_01_02_01           = @"s006!doAddMessage";
NSString *const SH01_05_01_02_02           = @"s006!doListMessage";
NSString *const SH01_05_02_02              = @"help";
NSString *const SH01_02_02_01              = @"d002!doViewDetail";
NSString *const RANKING                    = @"rank!doRank";
NSString *const SHARE_URI                  = @"share/share.html";

NSString *const SH01_01_07                 = @"s007!getSolution";
NSString *const D005_DOSAVE                = @"d005!doSaveAdd";

NSString *const SAVEEDITEQUIPS             = @"d002!doSaveEditEquips";
NSString *const CHECKUPGRADE               = @"d002!checkUpgrade";
NSString *const DOLISTDETAILHISTORY        = @"d002!doListDetailHistory";
NSString *const CHECKUPGRADEINFO           = @"d002!checkUpgradeInfo";


@end
