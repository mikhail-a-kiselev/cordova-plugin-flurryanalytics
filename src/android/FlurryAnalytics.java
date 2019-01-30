package com.codemech.flurryanalytics;

import java.util.HashMap;
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

	private static final String FLURRY_ID = "Your flurry identifier must be here"; 
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
	public void onDestroy() {
	    FlurryAgent.onEndSession(cordova.getActivity());
	    super.onDestroy();
    } 
	@Override
    public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
        PluginResult result = null;
        JSONObject options = inputs.optJSONObject(0);
        
        if(LOG_EVENT.equals(action)){
        	String eventname = "unknown";
        	Map<String, String> articleParams = new HashMap<String, String>();
			if(options.has("name")){
        		eventname = options.optString("name");
        	}
        	if(options.has("type")){
        		articleParams.put("type", options.optString("type"));
        	}
        	if(options.has("level")){
        		articleParams.put("level", options.optString("level"));
        	}
        	
        	FlurryAgent.logEvent(eventname, articleParams); 
        	
        } else if(END_TIMED_EVENT.equals(action)){
        	
        }
        
        return true;
	}
}