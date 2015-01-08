/* SVN.committedRevision=327869 */
function getQueryStringRegExp(d) {
    var c = new RegExp("(^|\\?|&)" + d + "=([^&]*)(\\s|&|$)", "i");
    if (c.test(location.href)) {
        return unescape(RegExp.$2.replace(/\+/g, " "))
    }
    return""
}
var ref = getQueryStringRegExp("tracker_u");
var uid = getQueryStringRegExp("uid");
var websiteid = getQueryStringRegExp("website_id");
var utype = getQueryStringRegExp("tracker_type");
var adgroupKeywordID = getQueryStringRegExp("adgroupKeywordID");
var expire_time = new Date((new Date()).getTime() + 30 * 24 * 3600000).toGMTString();
var expire_time2 = new Date((new Date()).getTime() + 30 * 24 * 3600000).toGMTString();
var expire_time3 = new Date((new Date()).getTime()).toGMTString();
var expire_time_wangmeng = new Date((new Date()).getTime() + 1 * 24 * 3600000).toGMTString();
if (ref) {
    if (ref != "") {
        document.cookie = "unionKey=" + ref + ";expires=" + expire_time_wangmeng + ";domain=." + no3wUrl + ";path=/"
    }
}
if (adgroupKeywordID) {
    if (adgroupKeywordID != "") {
        document.cookie = "adgroupKeywordID=" + adgroupKeywordID + ";expires=" + expire_time_wangmeng + ";domain=." + no3wUrl + ";path=/"
    }
}
if (utype) {
    if (utype != "") {
        document.cookie = "unionType=" + utype + ";expires=" + expire_time2 + ";domain=." + no3wUrl + ";path=/"
    }
}
if (uid) {
    document.cookie = "uid=" + uid + ";expires=" + expire_time + ";domain=." + no3wUrl + ";path=/"
}
if (websiteid) {
    document.cookie = "websiteid=" + websiteid + ";expires=" + expire_time + ";domain=." + no3wUrl + ";path=/"
}
;
var refer = document.referrer ? document.referrer : "";
if (refer != "") {
    refer = encodeURIComponent(refer)
}
var pars = document.location.search;
var input = new Object();
if (pars && pars.indexOf("?") == 0 && pars.length > 1) {
    pars = pars.substr(1);
    var list = pars.split("&");
    for (var n = 0; n < list.length; n++) {
        var item = list[n].split("=");
        if (item.length == 2) {
            input[item[0]] = item[1]
        }
    }
}
var tracker_u = input.tracker_u ? input.tracker_u : "";
var tracker_type = input.tracker_type ? input.tracker_type : "";
var tracker_pid = input.tracker_pid ? input.tracker_pid : "";
var tracker_src = input.tracker_src ? input.tracker_src : "";
var adgroupKeywordID = input.adgroupKeywordID ? input.adgroupKeywordID : "";
trackerContainer.addParameter(new Parameter("adgroupKeywordID", adgroupKeywordID));
if (refer.indexOf("yihaodian.com") != -1) {
    refer = ""
}
if (refer != "" && "" == tracker_u) {
    tracker_type = "0"
}
if (jQuery.cookie("unionKey")) {
    trackerContainer.addParameter(new Parameter("tracker_u", jQuery.cookie("unionKey")))
}
trackerContainer.addParameter(new Parameter("tracker_src", tracker_src));
var info_refer = document.referrer ? document.referrer : "";
if (info_refer != "") {
    info_refer = encodeURIComponent(info_refer)
}
trackerContainer.addParameter(new Parameter("infoPreviousUrl", info_refer));
trackerContainer.addParameter(new Parameter("infoTrackerSrc", tracker_src));
if (jQuery.cookie("yihaodian_uid")) {
    trackerContainer.addParameter(new Parameter("endUserId", jQuery.cookie("yihaodian_uid")))
}
if (jQuery.cookie("abtest")) {
    trackerContainer.addParameter(new Parameter("extField6", jQuery.cookie("abtest")))
}
if (jQuery.cookie("extField7")) {
    trackerContainer.addParameter(new Parameter("extField7", jQuery.cookie("extField7")))
}
if (jQuery.cookie("extField8")) {
    trackerContainer.addParameter(new Parameter("extField8", jQuery.cookie("extField8")))
}
if (jQuery.cookie("extField9")) {
    trackerContainer.addParameter(new Parameter("extField9", jQuery.cookie("extField9")))
}
if (jQuery.cookie("extField10")) {
    trackerContainer.addParameter(new Parameter("extField10", jQuery.cookie("extField10")))
}
var sendTrackerCookie = "";
if (jQuery.cookie("msessionid")) {
    sendTrackerCookie = "msessionid:" + jQuery.cookie("msessionid")
}
if (jQuery.cookie("uname")) {
    sendTrackerCookie += ",uname:" + jQuery.cookie("uname")
}
if (jQuery.cookie("unionKey")) {
    sendTrackerCookie += ",unionKey:" + jQuery.cookie("unionKey")
}
if (jQuery.cookie("unionType")) {
    sendTrackerCookie += ",unionType:" + jQuery.cookie("unionType")
}
if (jQuery.cookie("tracker")) {
    sendTrackerCookie += ",tracker:" + jQuery.cookie("tracker")
}
if (jQuery.cookie("LTINFO")) {
    sendTrackerCookie += ",LTINFO:" + jQuery.cookie("LTINFO")
}
trackerContainer.addParameter(new Parameter("cookie", sendTrackerCookie));
if (getQueryStringRegExp("fee") != null) {
    trackerContainer.addParameter(new Parameter("fee", getQueryStringRegExp("fee")))
}
trackerContainer.addParameter(new Parameter("provinceId", jQuery.cookie("provinceId")));
function initHijack() {
    jQuery.ajax({async: true, url: trackerContainer.toUrl(), type: "GET", dataType: "jsonp", jsonp: "jsoncallback"})
}
jQuery(document).ready(function () {
    initHijack()
});
function callTracker(b) {
    trackerContainer.addParameter(new Parameter("provinceId", b));
    trackerContainer.addParameter(new Parameter("cityId", jQuery.cookie("cityId")));
    jQuery.ajax({async: true, url: trackerContainer.toUrl(), type: "GET", dataType: "jsonp", jsonp: "jsoncallback"})
};
$(document).ready(function () {
    $(window).scroll(function () {
        if ($(window).scrollTop() > 0) {
            $(".fixedRight .toTop").show()
        } else {
            $(".fixedRight .toTop").hide()
        }
    });
    $(".fixedRight .toTop a").click(function () {
        $("body,html").scrollTop(0);
        return false
    });
    if ($.browser.version <= 6) {
        a("fixedRight", 250);
        function a(b, c) {
            var d = "." + b;
            var e = c;
            $(window).scroll(function () {
                $(d).css("top", (e + $(window).scrollTop()) + "px")
            })
        }
    }
});