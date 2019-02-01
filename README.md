# cordova-plugin-flurryanalytics
First you need to add flurry libruary

FlurryAnalytics.logEvent(params);

FlurryAnalytics.stopTimedEvent(params);

params is object with keys and values

Example:
{
    "name":"myCustomEvent",
	"customParam1":"myValue1",
	"customParam2":"myValue2",
	"timed":"true"
	
}

"name" is reserved word. Value with this key is name of event

if there is "timed" key this event will be timed event

