#import <AccountKit/AccountKit.h>
#import <React/RCTBridge.h>

@interface RNAccountKitAdvancedUIManager : NSObject <AKFUIManager>

@property(nonatomic, weak) RCTBridge *bridge;
@property(nonatomic, strong, nullable) NSString *footerId;
@property(nonatomic, strong, nullable) NSString *headerId;
@property(nonatomic, strong, nullable) NSString *bodyId;
@property(nonatomic, strong, nullable) NSString *actionBarId;
@property(nonatomic, copy)  AKFTheme *theme;

- (instancetype) initWithBridge: (RCTBridge *__nonnull)bridge
                          theme:(AKFTheme *__nullable)theme
                        options:(NSObject *__nullable)options;

@end
