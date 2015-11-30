//
//  Recorder.m
//  Weidou
//
//  Created by wbiao on 14/12/2.
//  Copyright (c) 2014年 HanWei. All rights reserved.
//

#import "Recorder.h"
#import "StringBuilder.h"


@implementation Recorder
@synthesize aqc;
@synthesize delegate;

static void AQInputCallback (void                   * inUserData,
                             AudioQueueRef          inAudioQueue,
                             AudioQueueBufferRef    inBuffer,
                             const AudioTimeStamp   * inStartTime,
                             unsigned long          inNumPackets,
                             const AudioStreamPacketDescription * inPacketDesc)
{
    Recorder * engine = (__bridge Recorder *) inUserData;
    //NSLog(@"AQInputCallback");
    
    if (inNumPackets > 0)
    {
        [engine processAudioBuffer:inBuffer withQueue:inAudioQueue];
    }
    if (engine.aqc.run)
    {
        AudioQueueEnqueueBuffer(engine.aqc.queue, inBuffer, 0, NULL);
    }
    
}

- (id) init
{
    self = [super init];
    if (self)
    {
    }
    return self;
}

- (void) start
{
    aqc.mDataFormat.mSampleRate = kSamplingRate;
    aqc.mDataFormat.mFormatID = kAudioFormatLinearPCM;
    aqc.mDataFormat.mFormatFlags = kLinearPCMFormatFlagIsSignedInteger | kLinearPCMFormatFlagIsPacked;
    aqc.mDataFormat.mFramesPerPacket = 1;
    aqc.mDataFormat.mChannelsPerFrame = kNumberChannels;
    aqc.mDataFormat.mBitsPerChannel = kBitsPerChannels;
    aqc.mDataFormat.mBytesPerPacket = 2;
    aqc.mDataFormat.mBytesPerFrame = kBytesPerFrame;
    aqc.frameSize = kFrameSize;
    
    AudioQueueNewInput(&aqc.mDataFormat, AQInputCallback, (__bridge void *)(self), NULL, kCFRunLoopCommonModes, 0, &aqc.queue);
    
    UInt32 size = sizeof(aqc.mDataFormat);
    AudioQueueGetProperty(aqc.queue, kAudioQueueProperty_StreamDescription,
                                        &aqc.mDataFormat, &size);
    
    for (NSInteger i = 0;i < kNumberBuffers;i++)
    {
        AudioQueueAllocateBuffer(aqc.queue, aqc.frameSize, &aqc.mBuffers[i]);
        AudioQueueEnqueueBuffer(aqc.queue, aqc.mBuffers[i], 0, NULL);
    }
    aqc.recPtr = 0;
    aqc.run = 1;
    NSInteger status = AudioQueueStart(aqc.queue, NULL);
    NSLog(@"AudioQueueStart = %d", status);
}

- (void) stop
{
    AudioQueueStop(aqc.queue, true);
}

- (void) pause
{
    AudioQueuePause(aqc.queue);
}

- (void)processAudioBuffer:(AudioQueueBufferRef) buffer withQueue:(AudioQueueRef) queue
{
    //NSLog(@"processAudioBuffer：%d",buffer->mAudioDataByteSize);
    NSInteger size = buffer->mAudioDataByteSize/2;
    t_sample * data = (t_sample *)buffer->mAudioData;
    //memcpy(data, buffer->mAudioData, buffer->mAudioDataByteSize);
    [delegate RecorderAudioBuffer:data size:size];
}

@end
