function FlurryAnalytics() {
}

FlurryAnalytics.prototype.logEvent = function(params) {
    cordova.exec(null, null, "FlurryAnalytics", "logEvent", [params]);
}

FlurryAnalytics.prototype.endTimedEvent = function(params) {
    cordova.exec(null, null, "FlurryAnalytics", "endTimedEvent", [params]);
}

module.exports = new FlurryAnalytics();
