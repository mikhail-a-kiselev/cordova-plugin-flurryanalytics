package com.codemech.flurryanalytics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

import android.app.Activity;


public class FlurryAnalytics extends CordovaPlugin {

	private static final String FLURRY_ID = "JGDRBF9NP8DNZD3DQHZR"; 
	private static final String LOG_EVENT = "logEvent";
	private static final String END_TIMED_EVENT = "endTimedEvent";

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		final Activity mainactivity = cordova.getActivity();
		mainactivity.runOnUiThread(new Runnable(){
            @Override
            public void run() {
            	
				FlurryAgent.init(mainactivity, FLURRY_ID);
				FlurryAgent.onStartSession(mainactivity, FLURRY_ID);
			}
		});
	}
	
	//@Override
    public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
		//PluginResult result = null;
        JSONObject options = inputs.optJSONObject(0);
        String eventname = "unknown";
        Boolean timed = false;
    	Map<String, String> articleParams = new HashMap<String, String>();
    	Iterator<String> keys = options.keys();
    	
    	while(keys.hasNext()) {
    	    String key = keys.next();
    	    if (options.has(key)) {
                if(key.equals("name")){
                	eventname = options.optString("name");
                } else if(key.equals("timed")){
                	timed = true;
                } else {
                	articleParams.put(key, options.optString(key));
                }
    	    }
    	}
    	
        if(LOG_EVENT.equals(action)){
        	FlurryAgent.logEvent(eventname, articleParams, timed);
        } else if(END_TIMED_EVENT.equals(action)){
        	FlurryAgent.endTimedEvent(eventname, articleParams);
        } else {
			return false;
		}
        
        return true;
	}
    public void onDestroy() {
	    FlurryAgent.onEndSession(cordova.getActivity());
	    super.onDestroy();
    } 
}