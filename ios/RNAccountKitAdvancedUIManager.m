#import "RNAccountKitAdvancedUIManager.h"

#import <React/RCTBridge.h>
#import <React/RCTRootView.h>

@implementation RNAccountKitAdvancedUIManager
{
    id<AKFAdvancedUIActionController> _actionController;
}

- (void)setActionController:(id<AKFAdvancedUIActionController>)actionController
{
    _actionController = actionController;
}

- (instancetype) initWithBridge:(RCTBridge *__nonnull)bridge options:(NSObject *__nonnull)options
{
    self = [super init];
    self.bridge = bridge;
    _footerId = [options valueForKey:@"footer"];
    _headerId = [options valueForKey:@"header"];
    _bodyId = [options valueForKey:@"body"];
    _actionBarId = [options valueForKey:@"actionBar"];
    return self;
}

- (nullable UIView *)footerViewForState:(AKFLoginFlowState)state
{
    return _footerId != nil ? [self _reactViewForState:_footerId] : nil;
}

- (nullable UIView *)bodyViewForState:(AKFLoginFlowState)state
{
    return _bodyId != nil ? [self _reactViewForState:_bodyId] : nil;
}

- (nullable UIView *)headerViewForState:(AKFLoginFlowState)state
{
    return _headerId != nil ? [self _reactViewForState:_headerId] : nil;
}

- (nullable UIView *)actionBarViewForState:(AKFLoginFlowState)state
{
    return _actionBarId != nil ? [self _reactViewForState:_actionBarId] : nil;
    
}

- (nonnull UIView *)_reactViewForState:(NSString *__nonnull)moduleName
{
    RCTRootView *view = [[RCTRootView alloc] initWithBridge:self.bridge
                                             moduleName:moduleName
                                             initialProperties:nil];
    return view;
}

@end
