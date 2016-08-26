@implementation SubWebview

- (void)createViewWithOptions:(NSDictionary *)options
{
    CDVPluginResult* pluginResult = nil;
    NSString* echo = [options.arguments objectAtIndex:0];

    if (echo != nil && [echo length] > 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];    

}

@end