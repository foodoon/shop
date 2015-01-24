Core = {};
Core.username = function () {
    var username = $.cookie('username');
    if (username) {
        return BASE64.decode(username);
    }
    var rememberMe = $.cookie('remember_me_cookie');
    if (rememberMe) {
        var me = BASE64.decode(rememberMe);
        var i = me.indexOf(':');
        if (i != -1) {
            return me.substring(0, i);
        }
    }
}
Core.fullname = function () {
    var fullname = $.cookie('fullname');
    if (fullname) {
        var name = BASE64.decode(fullname);
        return name.split(':');
    }
}
Core.firstname = function () {
    var fullname = Core.fullname();
    if (fullname) {
        return fullname[0];
    }
}
Core.lastname = function () {
    var fullname = Core.fullname();
    if (fullname) {
        return fullname[1];
    }
}

Shop = {}
Shop.cartTotalItems = function () {
    var count = $.cookie('cart_total_items');
    return count || 0;
}

HistoryRecord = {}
HistoryRecord.record = function () {
    var count = $.cookie('shop_record');
    return count;
}

$.extend($.validator.messages, {
    required: "该项为必填项",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 ",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请再次输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: $.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: $.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: $.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: $.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: $.format("该项不能大于 {0}"),
    min: $.format("该项不能小于 {0}"),
    username: "只能输入字符、数字、中文、和 _ - @ 的组合",
    path: "只能输入字符和数字的组合"
});