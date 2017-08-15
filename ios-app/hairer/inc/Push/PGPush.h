//
//  PGPush.h
//  Pandora
//
//  Created by Pro_C Mac on 13-3-12.
//
//

#import "PGPlugin.h"
#import "PGMethod.h"

@interface PGPush : PGPlugin {
    NSMutableArray *m_pApsListenerList;
}
- (void)clear:(PGMethod*)pMethod;
- (void)addEventListener:(PGMethod*)pMethod;
- (void)createMessage:(PGMethod*)pMethod;
- ( NSData* )getClientInfo:(PGMethod*)pMethod;
- (NSMutableDictionary*)getClientInfoJSObjcet;
+ (NSDictionary*)packageApnsMessage:(id)userInfo isReceive:(BOOL)bRev;
@end
