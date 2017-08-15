//
//  GeTuiPush.h
//  GeTuiPush
//
//  Created by X on 14-4-3.
//  Copyright (c) 2014年 io.dcloud. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GexinSdk.h"
#import "GXSdkError.h"
#import "PGPush.h"

typedef enum {
    SdkStatusStoped,
    SdkStatusStarting,
    SdkStatusStarted
} SdkStatus;

@interface PGGetuiPush : PGPush
- (NSMutableDictionary*)getClientInfoJSObjcet;
@end

@interface GeTuiPush : NSObject<GexinSdkDelegate> {
    GexinSdk *_gexinPusher;
}
@property (retain, nonatomic) NSString *appKey;
@property (retain, nonatomic) NSString *appSecret;
@property (retain, nonatomic) NSString *appID;
@property (retain, nonatomic) NSString *clientId;
@property (assign, nonatomic) SdkStatus sdkStatus;
- (void)startEngine;
- (void)stopEngine;
- (void)registerDeviceToken:(NSData *)deviceToken;
@end
