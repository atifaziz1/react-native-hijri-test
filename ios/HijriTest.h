
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNHijriTestSpec.h"

@interface HijriTest : NSObject <NativeHijriTestSpec>
#else
#import <React/RCTBridgeModule.h>

@interface HijriTest : NSObject <RCTBridgeModule>
#endif

@end
