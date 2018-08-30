#import "RNAccountKitUIView.h"
#import <React/RCTRootView.h>

@implementation RNAccountKitUIView

- (instancetype)initWithBridge:(RCTBridge *__nonnull)bridge
                    moduleName:(NSString *__nonnull)moduleName
{
    self = [super init];
    if (self) {
        self.subView = [[RCTRootView alloc] initWithBridge:bridge
                                            moduleName:moduleName
                                            initialProperties:nil];
        self.subView.sizeFlexibility = RCTRootViewSizeFlexibilityWidthAndHeight;
        self.subView.delegate = self;
        [self addSubview:self.subView];
    }
    return self;
}

- (CGSize)intrinsicContentSize
{
    return _subView.intrinsicContentSize;
}

- (void)rootViewDidChangeIntrinsicSize:(RCTRootView *)rootView {
    [self invalidateIntrinsicContentSize];
}

@end
