//
//  UIControls.h
//  Cordova
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#import <Cordova/CDVPlugin.h>

@interface Subwebview : CDVPlugin <UIWebViewDelegate> 
{

}

@property (nonatomic, retain) UIView* childView;
@property (nonatomic, retain) UIWebView* subWebView;

- (void)showContent:(CDVInvokedUrlCommand*)command;

- (void)createSubWebView:(CDVInvokedUrlCommand*)command;

- (void)resizeSubWebView:(CDVInvokedUrlCommand*)command;

- (void)setVisible:(CDVInvokedUrlCommand*)command;

- (void)resizeSubWebViewWithOptions:(NSDictionary *)options;


@end