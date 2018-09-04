#import "RNAccountKitAdvancedUIManager.h"
#import "RNAccountKitUIView.h"
#import <React/RCTBridge.h>

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
    return _footerId != nil ? [self _reactViewForState:_footerId state:state] : nil;
}

- (nullable UIView *)bodyViewForState:(AKFLoginFlowState)state
{
    return _bodyId != nil ? [self _reactViewForState:_bodyId state:state] : nil;
}

- (nullable UIView *)headerViewForState:(AKFLoginFlowState)state
{
    return _headerId != nil ? [self _reactViewForState:_headerId state:state] : nil;
}

- (nullable UIView *)actionBarViewForState:(AKFLoginFlowState)state
{
    return _actionBarId != nil ? [self _reactViewForState:_actionBarId state:state] : nil;
}

- (nonnull NSString *)_stringForLoginState:(AKFLoginFlowState)state
{
    switch (state) {
        case AKFLoginFlowStateNone: return @"NONE";
        case AKFLoginFlowStatePhoneNumberInput: return @"PHONE_NUMBER_INPUT";
        case AKFLoginFlowStateEmailInput: return @"EMAIL_INPUT";
        case AKFLoginFlowStateEmailVerify: return @"EMAIL_VERIFY";
        case AKFLoginFlowStateSendingCode: return @"SENDING_CODE";
        case AKFLoginFlowStateSentCode: return @"SENT_CODE";
        case AKFLoginFlowStateCodeInput: return @"CODE_INPUT";
        case AKFLoginFlowStateVerifyingCode: return @"VERIFYING_CODE";
        case AKFLoginFlowStateVerified: return @"VERIFIED";
        case AKFLoginFlowStateError: return @"ERROR";
    }
}

- (nonnull UIView *)_reactViewForState:(NSString *__nonnull)moduleName
                                 state:(AKFLoginFlowState)state
{
    NSDictionary *props = @{
        @"loginState": [self _stringForLoginState:state]
    };
    return [[RNAccountKitUIView alloc] initWithBridge:self.bridge
                                       moduleName:moduleName
                                       initialProperties:props];
}

@end
