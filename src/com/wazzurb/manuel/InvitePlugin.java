package com.wazzurb.manuel;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

// TODO: PLUGIN SHOULD HANDLE: pause(), resume() and msg passing ( maybe onReset() )


public class InvitePlugin extends CordovaPlugin {

    private String previewImagePath = "http://wzzrb.nl/appinvite/1.png"; // 1-2-3-4.png
    private String AppDownloadUrl = "https://fb.me/400072263520020";
    private Activity activity;

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        activity = cordova.getActivity();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        System.out.println("execute initiated");
        // wraps execute arguments in cordovaArgs
        CordovaArgs cordovaArgs = new CordovaArgs(args);

        if ("invite".equals(action.toLowerCase())) {
            if (AppInviteDialog.canShow()) {
                launchInvite();
            }
        }

        return execute(action, cordovaArgs, callbackContext);
    }

    private void launchInvite() {
        System.out.println("invite launched");
        AppInviteContent content = new AppInviteContent.Builder()
                .setApplinkUrl(AppDownloadUrl)
                .setPreviewImageUrl(previewImagePath)
                .build();
        AppInviteDialog.show(activity, content);
    }

}
