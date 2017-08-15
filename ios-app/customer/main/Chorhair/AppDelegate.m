//
//  AppDelegate.m
//  Pandora
//
//  Created by Mac Pro_C on 12-12-26.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "AppDelegate.h"
#import "PDRCoreWindowManager.h"
#import "PDRCore.h"
#import "PDRCommonString.h"
#ifdef PDR_PLUS_UMENG
#import "MobClick.h"
#endif

BOOL bIsDeactivate = YES;

@implementation AppDelegate

@synthesize window = _window;
#ifdef PDR_PLUS_GETUI
@synthesize gexinPusher = _gexinPusher;
#endif

#pragma mark -
#pragma mark app lifecycle

#ifdef PDR_PLUS_MAP
/**
 *返回网络错误
 *@param iError 错误号
 */
- (void)onGetNetworkState:(int)iError{
    
}

/**
 *返回授权验证错误
 *@param iError 错误号 : BMKErrorPermissionCheckFailure 验证失败
 */
- (void)onGetPermissionState:(int)iError {
    if ( E_PERMISSION_OK != iError ) {
        NSLog(@"baidu maponGetPermissionState--[%d]", iError);
    }
}
#endif

/*
 * @Summary:程序启动时收到push消息
 */
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    [[PDRCore Instance] load];
    NSDictionary *infoPlist = nil;
#ifdef PDR_PLUS_MAP
    // 启动百度地图
    NSString *baiduAppKey = @"mRFUA0lOYyCGXOcFkZ4cP44K";
    if ( !infoPlist ) {
        infoPlist = [[NSBundle mainBundle] infoDictionary];
    }
    NSDictionary *baiduInfo = [infoPlist objectForKey:@"baidu"];
    if ( [baiduInfo isKindOfClass:[NSDictionary class]] ) {
        NSString *tempAK = [baiduInfo objectForKey:@"appkey"];
        if ( [baiduAppKey isKindOfClass:[NSString class]] ) {
            baiduAppKey = tempAK;
        }
    }
    _mapManager = [[BMKMapManager alloc]init];
    [_mapManager start:baiduAppKey generalDelegate:self];
#endif
    
    //友盟统计集成
#ifdef PDR_PLUS_UMENG
    if ( !infoPlist ) {
        infoPlist = [[NSBundle mainBundle] infoDictionary];
    }
    NSDictionary *dict = [infoPlist objectForKey:@"umeng"];
    if ( [dict isKindOfClass:[NSDictionary class]] ) {
        NSString *appKey = [dict objectForKey:@"appkey"];
        if ( appKey ) {
            [MobClick startWithAppkey:appKey];
        }
    }
#endif
    //处理openURL
    NSURL *url = [launchOptions objectForKey:UIApplicationLaunchOptionsURLKey];
    if ( url ) {
        [[PDRCore Instance] handleSysEvent:PDRCoreSysEventOpenURL withObject:url];
    }
    
    //处理aps
    if ( launchOptions ) {
        NSDictionary* userInfo  = [launchOptions objectForKey:UIApplicationLaunchOptionsRemoteNotificationKey];
        if ( userInfo ) {
            [[PDRCore Instance] handleSysEvent:PDRCoreSysEventRevRemoteNotification withObject:[self packageApns:userInfo receive:FALSE]];
        }
    }
#ifdef PDR_PLUS_DHPUSH
    _dhPusher = [[DHPushHelper alloc] init];
    [_dhPusher startEngineWithDelegate:self];
#endif
#ifdef PDR_PLUS_GETUI
    _gexinPusher = [[GeTuiPush alloc] init];
    [_gexinPusher startEngine];
#endif
    
    //处理本地消息
    if ( launchOptions ) {
        UILocalNotification *localNotif = [launchOptions objectForKey:UIApplicationLaunchOptionsLocalNotificationKey];
        if ( localNotif ) {
            NSMutableDictionary* pDictionary = [self packageApns:localNotif receive:FALSE];
            [[PDRCore Instance] handleSysEvent:PDRCoreSysEventRevLocalNotification withObject:pDictionary];
        }
    }
    
    return YES;
}

- (NSMutableDictionary*)packageApns:(NSObject*)userInfo receive:(BOOL)bRev
{
    if ( userInfo ) {
        NSMutableDictionary* dict = [NSMutableDictionary dictionary];
        [dict setObject:userInfo forKey:g_pdr_string_payload];
        if (bRev) {
            [dict setObject:g_pdr_string_receive forKey:g_pdr_string_type];
        } else {
            [dict setObject:g_pdr_string_click forKey:g_pdr_string_type];
        }
        return dict;
    }
    return nil;
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    bIsDeactivate = NO;
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
    bIsDeactivate = YES;
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
#ifdef PDR_PLUS_GETUI
    [_gexinPusher stopEngine];
#endif
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventEnterBackground withObject:nil];
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
#ifdef PDR_PLUS_GETUI
    [_gexinPusher startEngine];
#endif
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventEnterForeGround withObject:nil];
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}


- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
    [[PDRCore Instance] unLoad];
}

#pragma mark -
#pragma mark URL

- (BOOL)application:(UIApplication *)application
            openURL:(NSURL *)url
  sourceApplication:(NSString *)sourceApplication
         annotation:(id)annotation {
    [self application:application handleOpenURL:url];
    return YES;
}

/*
 * @Summary:程序被第三方调用，传入参数启动
 *
 */
- (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url
{
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventOpenURL withObject:url];
    return YES;
}


#pragma mark -
#pragma mark APNS
/*
 * @Summary:远程push注册成功收到DeviceToken回调
 *
 */
- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventRevDeviceToken withObject:deviceToken];
#ifdef PDR_PLUS_GETUI
    [_gexinPusher registerDeviceToken:deviceToken];
#endif
#ifdef PDR_PLUS_DHPUSH
    [_dhPusher registerMkeyPushUseDeviceToken:deviceToken];
#endif
}

/*
 * @Summary: 远程push注册失败
 */
- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
#ifdef PDR_PLUS_GETUI
    [_gexinPusher registerDeviceToken:nil];
#endif
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo {
#ifdef PDR_PLUS_DHPUSH
    [_dhPusher handleRemoteNotification:userInfo];
#endif
#ifdef PDR_PLUS_GETUI
    NSMutableDictionary* pDictionary = [self packageApns:userInfo receive:!bIsDeactivate];
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventRevRemoteNotification withObject:pDictionary];
#endif
}

#pragma mark -
#pragma mark DHPUSH
#ifdef PDR_PLUS_DHPUSH
//收到消息()
- (void)didReceiveMessage:(NSDictionary*)message {
    NSMutableDictionary* pDictionary = [self packageApns:message receive:!bIsDeactivate];
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventRevRemoteNotification withObject:pDictionary];
}

//收到炸弹短息消息
- (void)didReceiveBombMessage {
    
}
/*
//过程中出现错误
- (void)didReceiveFailWithError:(NSError*)error {
}
*/
#endif

#pragma mark -
#pragma mark other
/*
 * @Summary:程序收到本地消息
 */
- (void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification
{
    NSMutableDictionary* pDictionary = [self packageApns:notification receive:!bIsDeactivate];
    [[PDRCore Instance] handleSysEvent:PDRCoreSysEventRevLocalNotification withObject:pDictionary];
}

- (void)dealloc
{
#ifdef PDR_PLUS_GETUI
    [_gexinPusher stopEngine];
    [_gexinPusher release];
    _gexinPusher = nil;
#endif
#ifdef PDR_PLUS_DHPUSH
    [_dhPusher release];
    _dhPusher = nil;
#endif
#ifdef PDR_PLUS_MAP
    [_mapManager stop];
    [_mapManager release];
    _mapManager = nil;
#endif
    [super dealloc];
}

@end
