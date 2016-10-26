cordova-sub-webview-plugin
================
# Description
- Open a webview ( = sub-webview) in your Cordova application.
- You can resize the height of sub-webview
- You can place sub-webview at bottom or top in the window).

# Install
`cordova plugin add https://github.com/HikosakaRyo/cordova-sub-webview-plugin --save`

# Quick Sample
## create SubWebview
    subwebview.createSubWebView(
        function() {
            // success callback
            console.log("success!");
        },
        function(msg) {
            // error callback
            console.log(msg);
        },
        {
            height: 400,    // height of the SubWebview
            atBottom: true  // bottom or top of the webview
        });

## show contents in SubWebview
    subwebview.showContent(
        function() {
            // success callback
            console.log("success!");
        },
        function(msg) {
            // error callback
            console.log(msg);
        },
        {
            url: 'http://...'   // url of the contents
        });

## resize SubWebview
    subwebview.resizeSubWebView(
        function() {
            // success callback
            console.log("success!");
        },
        function(msg) {
            // error callback
            console.log(msg);
        },
        {
            height: 400,    // height of the SubWebview
            atBottom: true  // bottom or top of the webview
        });

## show or hide SubWebview
    subwebview.setVisible(
        function() {
            // success callback
            console.log("success!");
        },
        function(msg) {
            // error callback
            console.log(msg);
        },
        false // true => show, false => hide
        );
