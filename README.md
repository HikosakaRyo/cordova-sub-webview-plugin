cordova-sub-webview-plugin
================

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
