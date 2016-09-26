package org.apache.cordova.subwebview;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.ViewGroup;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.app.Dialog;
import android.content.DialogInterface;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SubWebview extends CordovaPlugin {
    protected ViewGroup root; // original Cordova layout
    protected RelativeLayout main; // new layout to support map
    protected WebView subWebview;
    private CallbackContext cCtx;
    private String TAG = "SubWebview";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        main = new RelativeLayout(cordova.getActivity());
    }

    public void createSubWebView(final JSONObject options) {
        try {
            cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RelativeLayout.LayoutParams params = createLayoutFromOptions(options);
                    if (params == null) {
                        LOG.e(TAG, "Error reading options");
                        cCtx.error("Error reading options.");
                        return;
                    }
                    
                    // https://github.com/mapsplugin/cordova-plugin-googlemaps/issues/576
                    // https://github.com/cordova-plugin-camera-preview/cordova-plugin-camera-preview/issues/40

                    //root = (ViewGroup) webView.getParent();
                    //root.removeView(webView);
                    //main.addView(webView);

                    View wb = webView.getView();
                    root = (ViewGroup) wb.getParent();
                    root.removeView(wb);
                    main.addView(wb);
                    cordova.getActivity().setContentView(main);

                    subWebview = new WebView(cordova.getActivity());
                    subWebview.setLayoutParams(params);

                    subWebview.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            return super.shouldOverrideUrlLoading(view, url);
                        }
                    });                    
                    main.addView(subWebview);
                    cCtx.success();

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            cCtx.error("SubWebviewPlugin::createSubWebView(): An exception occured");
        }
    }

    private RelativeLayout.LayoutParams createLayoutFromOptions(final JSONObject options) {
        int height = 460;
        boolean atBottom = false;
        try {
            height = options.getInt("height");
            atBottom = options.getBoolean("atBottom");
        } catch (JSONException e) {
            return null;
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, height);
        if (atBottom) {
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
                    RelativeLayout.TRUE);
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP,
                    RelativeLayout.TRUE);
        }
        params.addRule(RelativeLayout.CENTER_HORIZONTAL,
                RelativeLayout.TRUE);
        return params;        
    }

    public void resizeSubWebView(final JSONObject options) {
        try{
            cordova.getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if( subWebview != null ) {
                        RelativeLayout.LayoutParams params = createLayoutFromOptions(options);
                        if (params == null) {
                            LOG.e(TAG, "Error reading options");
                            cCtx.error("Error reading options.");
                            return;
                        }
                        subWebview.setLayoutParams(params);
                    }
                    cCtx.success();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            cCtx.error("SubWebviewPlugin::resizeSubWebView(): An exception occured ");
        }
        
    }

    public void showContent(final JSONObject options) {
        try{
            cordova.getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if( subWebview != null ) {
                        String url = null;
                        try {
                            url = options.getString("url");
                        } catch (JSONException e) {
                            LOG.e(TAG, "Error reading options");
                            cCtx.error("Error reading options.");
                            return;
                        }
                        subWebview.loadUrl(url);
                    }
                    cCtx.success();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            cCtx.error("SubWebviewPlugin::showContent(): An exception occured ");
        }
    }

    public void setVisible(final JSONObject options) {
        try{
            cordova.getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    boolean visible = false;
                    try {
                        visible = options.getBoolean("visible");
                    } catch (JSONException e) {
                        LOG.e(TAG, "Error reading options");
                        cCtx.error("Error reading options.");
                        return;
                    }
                    
                    if( subWebview != null ) {
                        if (visible) {
                            subWebview.setVisibility(View.VISIBLE);
                        } else {
                            subWebview.setVisibility(View.INVISIBLE);
                        }
                    }
                    cCtx.success();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            cCtx.error("SubWebviewPlugin::setVisible(): An exception occured ");
        }
    }
    

    public boolean execute(String action, JSONArray args,
            CallbackContext callbackContext) throws JSONException {
        cCtx = callbackContext;
        if (action.compareTo("createSubWebView") == 0) {
            createSubWebView(args.getJSONObject(0));
        } else if (action.compareTo("resizeSubWebView") == 0) {
            resizeSubWebView(args.getJSONObject(0));
        } else if (action.compareTo("showContent") == 0) {
            showContent(args.getJSONObject(0));
        } else if (action.compareTo("setVisible") == 0) {
            setVisible(args.getJSONObject(0));

        }
        LOG.d(TAG, action);

        return true;
    }

    @Override
    public void onPause(boolean multitasking) {
        LOG.d(TAG, "SubWebviewPlugin::onPause()");
        if (subWebview != null) {
            subWebview.onPause();
        }
        super.onPause(multitasking);
    }

    @Override
    public void onResume(boolean multitasking) {
        LOG.d(TAG, "SubWebviewPlugin::onResume()");
        if (subWebview != null) {
            subWebview.onResume();
        }
        super.onResume(multitasking);
    }

    @Override
    public void onDestroy() {
        LOG.d(TAG, "SubWebviewPlugin::onDestroy()");
        if (subWebview != null) {
            //subWebview.onDestroy();
        }
        super.onDestroy();
    }
    



}