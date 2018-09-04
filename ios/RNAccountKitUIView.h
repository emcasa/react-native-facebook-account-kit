#import <UIKit/UIKit.h>
#import <React/RCTBridge.h>
#import <React/RCTRootView.h>
#import <React/RCTRootViewDelegate.h>

@interface RNAccountKitUIView : UIView<RCTRootViewDelegate>

@property (nonatomic, strong) RCTRootView *subView;

- (instancetype)initWithBridge:(RCTBridge *__nonnull)bridge
                    moduleName:(NSString *__nonnull)moduleName
             initialProperties:(NSDictionary *__nullable)initialProperties;

@end
