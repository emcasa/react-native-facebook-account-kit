#import "RNAccountKitUIView.h"
#import <React/RCTRootView.h>

@implementation RNAccountKitUIView

- (instancetype)initWithBridge:(RCTBridge *__nonnull)bridge
                    moduleName:(NSString *__nonnull)moduleName
             initialProperties:(NSDictionary *)initialProperties
{
    self = [super init];
    if (self) {
        self.subView = [[RCTRootView alloc] initWithBridge:bridge
                                            moduleName:moduleName
                                            initialProperties:initialProperties];
        self.subView.sizeFlexibility = RCTRootViewSizeFlexibilityWidthAndHeight;
        self.subView.delegate = self;
        [self addSubview:self.subView];
    }
    return self;
}

- (CGSize)intrinsicContentSize
{
    return CGSizeMake(self.bounds.size.width, _subView.intrinsicContentSize.height);
}

- (void)rootViewDidChangeIntrinsicSize:(RCTRootView *)rootView {
    [self invalidateIntrinsicContentSize];
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    _subView.frame = self.bounds;
}

- (CGSize)sizeThatFits:(CGSize)size
{
    return CGSizeMake(self.bounds.size.width, MAX(size.height, _subView.intrinsicContentSize.height));
}

- (BOOL)pointInside:(CGPoint)point withEvent:(UIEvent *)event
{
    UIView *view = _subView.contentView;
    if (!view.hidden && [view pointInside:[self convertPoint:point toView:view] withEvent:event]) {
        return YES;
    }
    return NO;
}

- (void)dealloc
{
    NSLog(@"DEALLOC");
    self.subView = nil;
}

@end
