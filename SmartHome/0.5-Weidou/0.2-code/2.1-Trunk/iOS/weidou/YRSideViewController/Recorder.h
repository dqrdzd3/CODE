//
//  Recorder.h
//  Weidou
//
//  Created by wbiao on 14/12/2.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <AudioToolbox/AudioToolbox.h>
#import <CoreAudio/CoreAudioTypes.h>

#define kNumberBuffers      3
#define t_sample             SInt16

#define kSamplingRate       8000
#define kNumberChannels     1
#define kBitsPerChannels    16
#define kBytesPerFrame      (kNumberChannels * sizeof(t_sample))
#define kFrameSize          8000


typedef struct AQCallbackStruct
{
    AudioStreamBasicDescription mDataFormat;
    AudioQueueRef               queue;
    AudioQueueBufferRef         mBuffers[kNumberBuffers];
    AudioFileID                 outputFile;
    
    unsigned long               frameSize;
    long long                   recPtr;
    int                         run;
} AQCallbackStruct;


@protocol RecorderDelegate <NSObject>

-(void)RecorderAudioBuffer:(t_sample *)buffer size:(long) size;

@end

@interface Recorder : NSObject
{
    AQCallbackStruct aqc;
    AudioFileTypeID fileFormat;
    
    NSString *strBuffer;
}

@property(nonatomic,strong)id<RecorderDelegate> delegate;

- (id) init;
- (void) start;
- (void) stop;
- (void) pause;

- (void) processAudioBuffer:(AudioQueueBufferRef) buffer withQueue:(AudioQueueRef) queue;

@property (nonatomic, assign) AQCallbackStruct aqc;

@end