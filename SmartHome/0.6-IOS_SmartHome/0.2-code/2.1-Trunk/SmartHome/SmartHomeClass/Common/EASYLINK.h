//
//  EASYLINK.h
//  EasyLink
//
//  Created by William Xu on 13-7-24.
//  Copyright (c) 2013年 MXCHIP. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AsyncUdpSocket.h"
#import "AsyncSocket.h"
#import <UIKit/UIKit.h>
#include <sys/socket.h>
#include <netdb.h>
#include <AssertMacros.h>
#import <CFNetwork/CFNetwork.h>
#include <netinet/in.h>
#include <errno.h>
#include <ifaddrs.h>
#include <arpa/inet.h>
#if TARGET_IPHONE_SIMULATOR
#include <net/route.h>
#else
#include "route.h"
#endif

#import <SystemConfiguration/CaptiveNetwork.h>

#define EASYLINK_V1         0
#define EASYLINK_V2         1
#define EASYLINK_PLUS       2

/*wlanConfigArray content index*/
#define INDEX_SSID          0
#define INDEX_PASSWORD      1
#define INDEX_DHCP          2
#define INDEX_IP            3
#define INDEX_NETMASK       4
#define INDEX_GATEWAY       5
#define INDEX_DNS1          6
#define INDEX_DNS2          7


#define FTC_PORT 8000

@protocol EasyLinkFTCDelegate
@optional

/**
 *
 **/
- (void)onFoundByFTC:(NSNumber *)client currentConfig: (NSData *)config;

/**
 *
 **/
- (void)onDisconnectFromFTC:(NSNumber *)client;

@end



@interface EASYLINK : NSObject{
@private
    NSUInteger broadcastcount;
    NSUInteger multicastCount;
    NSTimer *closeFTCClientTimer;
    NSUInteger version;
    NSMutableArray *multicastArray, *broadcastArray;   //Used for EasyLink transmitting
    AsyncUdpSocket *multicastSocket, *broadcastSocket;
    
    //Used for EasyLink first time configuration
    AsyncSocket *ftcServerSocket;
    NSMutableArray *ftcClients;
    CFMutableArrayRef inCommingMessages;
    
    NSTimer *multicastSendInterval;
    NSTimer *broadcastSendInterval;
    NSThread *easyLinkThread;
    BOOL firstTimeConfig;
    id theDelegate;
}

@property (retain, nonatomic) NSMutableArray *multicastArray;
@property (retain, nonatomic) NSMutableArray *broadcastArray;
@property (retain, nonatomic) AsyncUdpSocket *multicastSocket;
@property (retain, nonatomic) AsyncUdpSocket *broadcastSocket;
@property (retain, nonatomic) AsyncSocket *ftcServerSocket;
@property (retain, nonatomic) NSMutableArray *ftcClients;



- (void)prepareEasyLink_withFTC:(NSArray *)wlanConfigArray info: (NSData *)userInfo version: (NSUInteger)ver;

- (void)transmitSettings;
- (void)stopTransmitting;

- (id)delegate;
- (void)setDelegate:(id)delegate;
- (void)startFTCServerWithDelegate:(id)delegate;
- (void)configFTCClient:(NSNumber *)client withConfigurationData: (NSData *)configData;
- (void)otaFTCClient:(NSNumber *)client withOTAData: (NSData *)otaData;
- (void)closeFTCClient:(NSNumber *)client;
- (void)closeFTCServer;



/**
 * Tools
 **/

+ (NSString *)ssidForConnectedNetwork;
+ (NSDictionary *)infoForConnectedNetwork;
+ (NSString *)getIPAddress;
+ (NSString *)getNetMask;
+ (NSString *)getBroadcastAddress;

+ (NSString *)getGatewayAddress;

@end
