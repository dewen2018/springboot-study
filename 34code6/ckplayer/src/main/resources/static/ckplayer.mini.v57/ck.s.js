//CKplayer 5.7 简易封装js by Fufu（品味经典 fufuok.com），感谢作者捻灯（ckplayer.com）的辛勤劳动。
(function (window) {
    if (typeof window.C == "undefined") {
        window.C = (function () {
            var msie = /msie/.test(navigator.userAgent.toLowerCase()),
                merge = function (_o, o) {
                    if (o && typeof o == "object") {
                        for (var k in o) {
                            _o[k] = o[k];
                        }
                    }
                    return _o;
                },
                make = function (flashvars, id, width, height, params, attrs) {
                    var url = 'ckplayer.57.swf';
                    var varr = merge({
                        v: 85,
                        p: 1,
                        f: "",
                        i: "",
                        d: "",
                        u: "",
                        l: "",
                        r: "",
                        t: 0,
                        e: 3,
                        a: "",
                        s: 0,
                        x: "ckplayer.xml",
                        c: 0,
                        b: "#000000",
                        h: 0,
                        m: 0,
                        g: 0,
                        j: 0,
                        o: 0
                    }, varr);
                    if (!arguments[1]) id = "myvideo";
                    if (!arguments[2]) width = "600";
                    if (!arguments[3]) height = "485";
                    attrs = merge({
                        id: 'ck' + id,
                        width: width,
                        height: height
                    }, attrs);
                    params = merge({
                        allowfullscreen: "true",
                        allowscriptaccess: "always"
                    }, params);
                    var vars, htm, k, arr = [];
                    if (flashvars) {
                        if (typeof flashvars == "object") {
                            for (k in flashvars) {
                                varr[k] = flashvars[k];
                            }
                            for (k in varr) {
                                arr.push(k + "=" + encodeURIComponent(varr[k]));
                            }
                            if (varr['o'] > 0) {
                                vars = '';
                                url = varr['f'];
                            } else {
                                vars = arr.join("&");
                            }
                        } else {
                            vars = String(flashvars);
                        }
                        params.flashvars = vars;
                    }
                    htm = '<object ';
                    htm += msie ? 'classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=10,0,0,0" ' : 'type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer" data="' + url + '" ';
                    for (k in attrs) {
                        htm += k + '="' + attrs[k] + '" ';
                    }
                    htm += msie ? '><param name="movie" value="' + url + '" />' : '>';
                    for (k in params) {
                        htm += '<param name="' + k + '" value="' + params[k] + '" />';
                    }
                    htm += '</object>';
                    var z = document.getElementById(String(id));
                    if (z) {
                        z.innerHTML = htm;
                    } else {
                        document.write(htm);
                    }
                    return htm;
                };
            return {
                K: function () {
                    return make.apply(this, arguments);
                }
            };
        })();
    }
})(window);