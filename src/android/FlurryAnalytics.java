package com.codemech.flurryanalytics;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
//import org.apache.cordova.test.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.flurry.android.FlurryAgent;

public class FlurryAnalytics extends CordovaPlugin 

	private static final String FLURRY_ID = "Your flurry identifier must be here"; 

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		cordova.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run() {
				FlurryAgent.init(this, FLURRY_ID);
				FlurryAgent.onStartSession(this, FLURRY_ID);
			}
		});
	}
}