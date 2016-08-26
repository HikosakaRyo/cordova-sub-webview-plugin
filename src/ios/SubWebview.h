//
//  UIControls.h
//  Cordova
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#import <Cordova/CDVPlugin.h>

@interface MapKitView : CDVPlugin
{
}

@property (nonatomic, retain) UIView* subWebView;

- (void)createViewWithOptions:(NSDictionary *)options;

@end