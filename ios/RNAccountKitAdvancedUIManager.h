#import <AccountKit/AccountKit.h>
#import <React/RCTBridge.h>

@interface RNAccountKitAdvancedUIManager : NSObject <AKFAdvancedUIManager>

@property(nonatomic, strong, nonnull) RCTBridge *bridge;
@property(nonatomic, strong, nullable) NSString *footerId;
@property(nonatomic, strong, nullable) NSString *headerId;
@property(nonatomic, strong, nullable) NSString *bodyId;
@property(nonatomic, strong, nullable) NSString *actionBarId;

- (instancetype) initWithBridge: (RCTBridge *__nonnull)bridge options:(NSObject *__nonnull)options;

@end

