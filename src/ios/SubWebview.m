#import "SubWebview.h"

/*
#import "MainViewController.h"

@implementation MainViewController (CDVViewController)
- (void)webViewDidFinishLoad:(UIWebView*)webView
{
    //[super webViewDidFinishLoad:webView];
    webView.backgroundColor = [UIColor clearColor];
    webView.opaque = NO;
}

@end
*/

@implementation Subwebview

@synthesize childView;
@synthesize subWebView;

- (void)showContent:(CDVInvokedUrlCommand*)command
{
    if (!self.subWebView)
	{
        return;
	}
    NSDictionary *options = command.arguments[0];
    NSString* urlString = [options objectForKey:@"url"];
    NSURL *url = [NSURL URLWithString:urlString];    

    NSURLRequest *req = [NSURLRequest requestWithURL:url];
    [self.subWebView loadRequest:req];

	self.childView.hidden = NO;
	self.webView.hidden = NO;
    [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:command.callbackId];
}

- (void)createSubWebView:(CDVInvokedUrlCommand*)command
{
    self.childView = [[UIView alloc] init];
    self.subWebView = [[UIWebView alloc] init];

    NSDictionary *options = command.arguments[0];
    [self resizeSubWebViewWithOptions:options];
    self.subWebView.delegate = self;

	self.subWebView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
	self.childView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;

    [self.childView addSubview:self.subWebView];

	[ [ [ self viewController ] view ] addSubview:self.childView];
    [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:command.callbackId];
}

- (void)resizeSubWebView:(CDVInvokedUrlCommand*)command 
{
    NSDictionary *options = command.arguments[0];
    [self resizeSubWebViewWithOptions:options];
    [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:command.callbackId];
}

- (void)resizeSubWebViewWithOptions:(NSDictionary *)options
{
    float height = ([options objectForKey:@"height"]) ? [[options objectForKey:@"height"] floatValue] : self.webView.bounds.size.height/2;
    float width = ([options objectForKey:@"width"]) ? [[options objectForKey:@"width"] floatValue] : self.webView.bounds.size.width;
    float x = self.webView.bounds.origin.x;
    float y = self.webView.bounds.origin.y;
    BOOL atBottom = ([options objectForKey:@"atBottom"]) ? [[options objectForKey:@"atBottom"] boolValue] : NO;
    if(atBottom) {
        y += self.webView.bounds.size.height - height;
    }
    self.childView.frame = CGRectMake(x,y,width,height);
    self.subWebView.frame = CGRectMake(
        self.childView.bounds.origin.x, self.childView.bounds.origin.x, 
        self.childView.bounds.size.width, self.childView.bounds.size.height);
}

- (void)setVisible:(CDVInvokedUrlCommand*)command
{
    NSDictionary *options = command.arguments[0];
    BOOL visible = ([options objectForKey:@"visible"]) ? [[options objectForKey:@"visible"] boolValue] : NO;
    if (self.childView) {
        self.childView.hidden = !visible;
    }
}

- (void)viewDidLoad
{
    //[super viewDidLoad];
    //self.webView.backgroundColor = [UIColor clearColor];
    //self.webView.opaque = NO;
}

- (void)webViewDidStartLoad:(UIWebView*)webView
{
}

- (void)webViewDidFinishLoad:(UIWebView*)webView
{
}

@end