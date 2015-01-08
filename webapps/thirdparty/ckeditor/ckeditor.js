﻿/*
 Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.html or http://ckeditor.com/license
 */

(function () {
    if (window.CKEDITOR && window.CKEDITOR.dom)return;
    if (!window.CKEDITOR)window.CKEDITOR = (function () {
        var a = {timestamp: 'C9A85WF', version: '3.6.5', revision: '7647', rnd: Math.floor(Math.random() * 900) + 100, _: {}, status: 'unloaded', basePath: (function () {
            var d = window.CKEDITOR_BASEPATH || '';
            if (!d) {
                var e = document.getElementsByTagName('script');
                for (var f = 0; f < e.length; f++) {
                    var g = e[f].src.match(/(^|.*[\\\/])ckeditor(?:_basic)?(?:_source)?.js(?:\?.*)?$/i);
                    if (g) {
                        d = g[1];
                        break;
                    }
                }
            }
            if (d.indexOf(':/') == -1)if (d.indexOf('/') === 0)d = location.href.match(/^.*?:\/\/[^\/]*/)[0] + d; else d = location.href.match(/^[^\?]*\/(?:)/)[0] + d;
            if (!d)throw 'The CKEditor installation path could not be automatically detected. Please set the global variable "CKEDITOR_BASEPATH" before creating editor instances.';
            return d;
        })(), getUrl: function (d) {
            if (d.indexOf(':/') == -1 && d.indexOf('/') !== 0)d = this.basePath + d;
            if (this.timestamp && d.charAt(d.length - 1) != '/' && !/[&?]t=/.test(d))d += (d.indexOf('?') >= 0 ? '&' : '?') + 't=' + this.timestamp;
            return d;
        }}, b = window.CKEDITOR_GETURL;
        if (b) {
            var c = a.getUrl;
            a.getUrl = function (d) {
                return b.call(a, d) || c.call(a, d);
            };
        }
        return a;
    })();
    var a = CKEDITOR;
    if (!a.event) {
        a.event = function () {
        };
        a.event.implementOn = function (b) {
            var c = a.event.prototype;
            for (var d in c) {
                if (b[d] == undefined)b[d] = c[d];
            }
        };
        a.event.prototype = (function () {
            var b = function (d) {
                var e = d.getPrivate && d.getPrivate() || d._ || (d._ = {});
                return e.events || (e.events = {});
            }, c = function (d) {
                this.name = d;
                this.listeners = [];
            };
            c.prototype = {getListenerIndex: function (d) {
                for (var e = 0, f = this.listeners; e < f.length; e++) {
                    if (f[e].fn == d)return e;
                }
                return-1;
            }};
            return{on: function (d, e, f, g, h) {
                var i = b(this), j = i[d] || (i[d] = new c(d));
                if (j.getListenerIndex(e) < 0) {
                    var k = j.listeners;
                    if (!f)f = this;
                    if (isNaN(h))h = 10;
                    var l = this, m = function (o, p, q, r) {
                        var s = {name: d, sender: this, editor: o, data: p, listenerData: g, stop: q, cancel: r, removeListener: function () {
                            l.removeListener(d, e);
                        }};
                        e.call(f, s);
                        return s.data;
                    };
                    m.fn = e;
                    m.priority = h;
                    for (var n = k.length - 1; n >= 0; n--) {
                        if (k[n].priority <= h) {
                            k.splice(n + 1, 0, m);
                            return;
                        }
                    }
                    k.unshift(m);
                }
            }, fire: (function () {
                var d = false, e = function () {
                    d = true;
                }, f = false, g = function () {
                    f = true;
                };
                return function (h, i, j) {
                    var k = b(this)[h], l = d, m = f;
                    d = f = false;
                    if (k) {
                        var n = k.listeners;
                        if (n.length) {
                            n = n.slice(0);
                            for (var o = 0; o < n.length; o++) {
                                var p = n[o].call(this, j, i, e, g);
                                if (typeof p != 'undefined')i = p;
                                if (d || f)break;
                            }
                        }
                    }
                    var q = f || (typeof i == 'undefined' ? false : i);
                    d = l;
                    f = m;
                    return q;
                };
            })(), fireOnce: function (d, e, f) {
                var g = this.fire(d, e, f);
                delete b(this)[d];
                return g;
            }, removeListener: function (d, e) {
                var f = b(this)[d];
                if (f) {
                    var g = f.getListenerIndex(e);
                    if (g >= 0)f.listeners.splice(g, 1);
                }
            }, hasListeners: function (d) {
                var e = b(this)[d];
                return e && e.listeners.length > 0;
            }};
        })();
    }
    if (!a.editor) {
        a.ELEMENT_MODE_NONE = 0;
        a.ELEMENT_MODE_REPLACE = 1;
        a.ELEMENT_MODE_APPENDTO = 2;
        a.editor = function (b, c, d, e) {
            var f = this;
            f._ = {instanceConfig: b, element: c, data: e};
            f.elementMode = d || 0;
            a.event.call(f);
            f._init();
        };
        a.editor.replace = function (b, c) {
            var d = b;
            if (typeof d != 'object') {
                d = document.getElementById(b);
                if (d && d.tagName.toLowerCase() in {style: 1, script: 1, base: 1, link: 1, meta: 1, title: 1})d = null;
                if (!d) {
                    var e = 0, f = document.getElementsByName(b);
                    while ((d = f[e++]) && d.tagName.toLowerCase() != 'textarea') {
                    }
                }
                if (!d)throw '[CKEDITOR.editor.replace] The element with id or name "' + b + '" was not found.';
            }
            d.style.visibility = 'hidden';
            return new a.editor(c, d, 1);
        };
        a.editor.appendTo = function (b, c, d) {
            var e = b;
            if (typeof e != 'object') {
                e = document.getElementById(b);
                if (!e)throw '[CKEDITOR.editor.appendTo] The element with id "' + b + '" was not found.';
            }
            return new a.editor(c, e, 2, d);
        };
        a.editor.prototype = {_init: function () {
            var b = a.editor._pending || (a.editor._pending = []);
            b.push(this);
        }, fire: function (b, c) {
            return a.event.prototype.fire.call(this, b, c, this);
        }, fireOnce: function (b, c) {
            return a.event.prototype.fireOnce.call(this, b, c, this);
        }};
        a.event.implementOn(a.editor.prototype, true);
    }
    if (!a.env)a.env = (function () {
        var b = navigator.userAgent.toLowerCase(), c = window.opera, d = {ie: /*@cc_on!@*/false, opera: !!c && c.version, webkit: b.indexOf(' applewebkit/') > -1, air: b.indexOf(' adobeair/') > -1, mac: b.indexOf('macintosh') > -1, quirks: document.compatMode == 'BackCompat', mobile: b.indexOf('mobile') > -1, iOS: /(ipad|iphone|ipod)/.test(b), isCustomDomain: function () {
            if (!this.ie)return false;
            var g = document.domain, h = window.location.hostname;
            return g != h && g != '[' + h + ']';
        }, secure: location.protocol == 'https:'};
        d.gecko = navigator.product == 'Gecko' && !d.webkit && !d.opera;
        var e = 0;
        if (d.ie) {
            e = parseFloat(b.match(/msie (\d+)/)[1]);
            d.ie8 = !!document.documentMode;
            d.ie8Compat = document.documentMode == 8;
            d.ie9Compat = document.documentMode == 9;
            d.ie7Compat = e == 7 && !document.documentMode || document.documentMode == 7;
            d.ie6Compat = e < 7 || d.quirks;
        }
        if (d.gecko) {
            var f = b.match(/rv:([\d\.]+)/);
            if (f) {
                f = f[1].split('.');
                e = f[0] * 10000 + (f[1] || 0) * 100 + +(f[2] || 0);
            }
        }
        if (d.opera)e = parseFloat(c.version());
        if (d.air)e = parseFloat(b.match(/ adobeair\/(\d+)/)[1]);
        if (d.webkit)e = parseFloat(b.match(/ applewebkit\/(\d+)/)[1]);
        d.version = e;
        d.isCompatible = d.iOS && e >= 534 || !d.mobile && (d.ie && e >= 6 || d.gecko && e >= 10801 || d.opera && e >= 9.5 || d.air && e >= 1 || d.webkit && e >= 522 || false);
        d.cssClass = 'cke_browser_' + (d.ie ? 'ie' : d.gecko ? 'gecko' : d.opera ? 'opera' : d.webkit ? 'webkit' : 'unknown');
        if (d.quirks)d.cssClass += ' cke_browser_quirks';
        if (d.ie) {
            d.cssClass += ' cke_browser_ie' + (d.version < 7 ? '6' : d.version >= 8 ? document.documentMode : '7');
            if (d.quirks)d.cssClass += ' cke_browser_iequirks';
        }
        if (d.gecko && e < 10900)d.cssClass += ' cke_browser_gecko18';
        if (d.air)d.cssClass += ' cke_browser_air';
        return d;
    })();
    var b = a.env;
    var c = b.ie;
    if (a.status == 'unloaded')(function () {
        a.event.implementOn(a);
        a.loadFullCore = function () {
            if (a.status != 'basic_ready') {
                a.loadFullCore._load = 1;
                return;
            }
            delete a.loadFullCore;
            var e = document.createElement('script');
            e.type = 'text/javascript';
            e.src = a.basePath + 'ckeditor.js';
            document.getElementsByTagName('head')[0].appendChild(e);
        };
        a.loadFullCoreTimeout = 0;
        a.replaceClass = 'ckeditor';
        a.replaceByClassEnabled = 1;
        var d = function (e, f, g, h) {
            if (b.isCompatible) {
                if (a.loadFullCore)a.loadFullCore();
                var i = g(e, f, h);
                a.add(i);
                return i;
            }
            return null;
        };
        a.replace = function (e, f) {
            return d(e, f, a.editor.replace);
        };
        a.appendTo = function (e, f, g) {
            return d(e, f, a.editor.appendTo, g);
        };
        a.add = function (e) {
            var f = this._.pending || (this._.pending = []);
            f.push(e);
        };
        a.replaceAll = function () {
            var e = document.getElementsByTagName('textarea');
            for (var f = 0; f < e.length; f++) {
                var g = null, h = e[f];
                if (!h.name && !h.id)continue;
                if (typeof arguments[0] == 'string') {
                    var i = new RegExp('(?:^|\\s)' + arguments[0] + '(?:$|\\s)');
                    if (!i.test(h.className))continue;
                } else if (typeof arguments[0] == 'function') {
                    g = {};
                    if (arguments[0](h, g) === false)continue;
                }
                this.replace(h, g);
            }
        };
        (function () {
            var e = function () {
                var f = a.loadFullCore, g = a.loadFullCoreTimeout;
                if (a.replaceByClassEnabled)a.replaceAll(a.replaceClass);
                a.status = 'basic_ready';
                if (f && f._load)f(); else if (g)setTimeout(function () {
                    if (a.loadFullCore)a.loadFullCore();
                }, g * 1000);
            };
            if (window.addEventListener)window.addEventListener('load', e, false); else if (window.attachEvent)window.attachEvent('onload', e);
        })();
        a.status = 'basic_loaded';
    })();
    a.dom = {};
    var d = a.dom;
    (function () {
        var e = [];
        a.on('reset', function () {
            e = [];
        });
        a.tools = {arrayCompare: function (f, g) {
            if (!f && !g)return true;
            if (!f || !g || f.length != g.length)return false;
            for (var h = 0; h < f.length; h++) {
                if (f[h] != g[h])return false;
            }
            return true;
        }, clone: function (f) {
            var g;
            if (f && f instanceof Array) {
                g = [];
                for (var h = 0; h < f.length; h++)g[h] = this.clone(f[h]);
                return g;
            }
            if (f === null || typeof f != 'object' || f instanceof String || f instanceof Number || f instanceof Boolean || f instanceof Date || f instanceof RegExp)return f;
            g = new f.constructor();
            for (var i in f) {
                var j = f[i];
                g[i] = this.clone(j);
            }
            return g;
        }, capitalize: function (f) {
            return f.charAt(0).toUpperCase() + f.substring(1).toLowerCase();
        }, extend: function (f) {
            var g = arguments.length, h, i;
            if (typeof (h = arguments[g - 1]) == 'boolean')g--; else if (typeof (h = arguments[g - 2]) == 'boolean') {
                i = arguments[g - 1];
                g -= 2;
            }
            for (var j = 1; j < g; j++) {
                var k = arguments[j];
                for (var l in k) {
                    if (h === true || f[l] == undefined)if (!i || l in i)f[l] = k[l];
                }
            }
            return f;
        }, prototypedCopy: function (f) {
            var g = function () {
            };
            g.prototype = f;
            return new g();
        }, isArray: function (f) {
            return!!f && f instanceof Array;
        }, isEmpty: function (f) {
            for (var g in f) {
                if (f.hasOwnProperty(g))return false;
            }
            return true;
        }, cssStyleToDomStyle: (function () {
            var f = document.createElement('div').style, g = typeof f.cssFloat != 'undefined' ? 'cssFloat' : typeof f.styleFloat != 'undefined' ? 'styleFloat' : 'float';
            return function (h) {
                if (h == 'float')return g; else return h.replace(/-./g, function (i) {
                    return i.substr(1).toUpperCase();
                });
            };
        })(), buildStyleHtml: function (f) {
            f = [].concat(f);
            var g, h = [];
            for (var i = 0; i < f.length; i++) {
                g = f[i];
                if (/@import|[{}]/.test(g))h.push('<style>' + g + '</style>'); else h.push('<link type="text/css" rel=stylesheet href="' + g + '">');
            }
            return h.join('');
        }, htmlEncode: function (f) {
            var g = function (k) {
                var l = new d.element('span');
                l.setText(k);
                return l.getHtml();
            }, h = g('\n').toLowerCase() == '<br>' ? function (k) {
                return g(k).replace(/<br>/gi, '\n');
            } : g, i = g('>') == '>' ? function (k) {
                return h(k).replace(/>/g, '&gt;');
            } : h, j = g('  ') == '&nbsp; ' ? function (k) {
                return i(k).replace(/&nbsp;/g, ' ');
            } : i;
            this.htmlEncode = j;
            return this.htmlEncode(f);
        }, htmlEncodeAttr: function (f) {
            return f.replace(/"/g, '&quot;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
        }, getNextNumber: (function () {
            var f = 0;
            return function () {
                return++f;
            };
        })(), getNextId: function () {
            return 'cke_' + this.getNextNumber();
        }, override: function (f, g) {
            return g(f);
        }, setTimeout: function (f, g, h, i, j) {
            if (!j)j = window;
            if (!h)h = j;
            return j.setTimeout(function () {
                if (i)f.apply(h, [].concat(i)); else f.apply(h);
            }, g || 0);
        }, trim: (function () {
            var f = /(?:^[ \t\n\r]+)|(?:[ \t\n\r]+$)/g;
            return function (g) {
                return g.replace(f, '');
            };
        })(), ltrim: (function () {
            var f = /^[ \t\n\r]+/g;
            return function (g) {
                return g.replace(f, '');
            };
        })(), rtrim: (function () {
            var f = /[ \t\n\r]+$/g;
            return function (g) {
                return g.replace(f, '');
            };
        })(), indexOf: Array.prototype.indexOf ? function (f, g) {
            return f.indexOf(g);
        } : function (f, g) {
            for (var h = 0, i = f.length; h < i; h++) {
                if (f[h] === g)return h;
            }
            return-1;
        }, bind: function (f, g) {
            return function () {
                return f.apply(g, arguments);
            };
        }, createClass: function (f) {
            var g = f.$, h = f.base, i = f.privates || f._, j = f.proto, k = f.statics;
            if (i) {
                var l = g;
                g = function () {
                    var p = this;
                    var m = p._ || (p._ = {});
                    for (var n in i) {
                        var o = i[n];
                        m[n] = typeof o == 'function' ? a.tools.bind(o, p) : o;
                    }
                    l.apply(p, arguments);
                };
            }
            if (h) {
                g.prototype = this.prototypedCopy(h.prototype);
                g.prototype['constructor'] = g;
                g.prototype.base = function () {
                    this.base = h.prototype.base;
                    h.apply(this, arguments);
                    this.base = arguments.callee;
                };
            }
            if (j)this.extend(g.prototype, j, true);
            if (k)this.extend(g, k, true);
            return g;
        }, addFunction: function (f, g) {
            return e.push(function () {
                return f.apply(g || this, arguments);
            }) - 1;
        }, removeFunction: function (f) {
            e[f] = null;
        }, callFunction: function (f) {
            var g = e[f];
            return g && g.apply(window, Array.prototype.slice.call(arguments, 1));
        }, cssLength: (function () {
            return function (f) {
                return f + (!f || isNaN(Number(f)) ? '' : 'px');
            };
        })(), convertToPx: (function () {
            var f;
            return function (g) {
                if (!f) {
                    f = d.element.createFromHtml('<div style="position:absolute;left:-9999px;top:-9999px;margin:0px;padding:0px;border:0px;"></div>', a.document);
                    a.document.getBody().append(f);
                }
                if (!/%$/.test(g)) {
                    f.setStyle('width', g);
                    return f.$.clientWidth;
                }
                return g;
            };
        })(), repeat: function (f, g) {
            return new Array(g + 1).join(f);
        }, tryThese: function () {
            var f;
            for (var g = 0, h = arguments.length; g < h; g++) {
                var i = arguments[g];
                try {
                    f = i();
                    break;
                } catch (j) {
                }
            }
            return f;
        }, genKey: function () {
            return Array.prototype.slice.call(arguments).join('-');
        }, normalizeCssText: function (f, g) {
            var h = [], i, j = a.tools.parseCssText(f, true, g);
            for (i in j)h.push(i + ':' + j[i]);
            h.sort();
            return h.length ? h.join(';') + ';' : '';
        }, convertRgbToHex: function (f) {
            return f.replace(/(?:rgb\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\))/gi, function (g, h, i, j) {
                var k = [h, i, j];
                for (var l = 0; l < 3; l++)k[l] = ('0' + parseInt(k[l], 10).toString(16)).slice(-2);
                return '#' + k.join('');
            });
        }, parseCssText: function (f, g, h) {
            var i = {};
            if (h) {
                var j = new d.element('span');
                j.setAttribute('style', f);
                f = a.tools.convertRgbToHex(j.getAttribute('style') || '');
            }
            if (!f || f == ';')return i;
            f.replace(/&quot;/g, '"').replace(/\s*([^:;\s]+)\s*:\s*([^;]+)\s*(?=;|$)/g, function (k, l, m) {
                if (g) {
                    l = l.toLowerCase();
                    if (l == 'font-family')m = m.toLowerCase().replace(/["']/g, '').replace(/\s*,\s*/g, ',');
                    m = a.tools.trim(m);
                }
                i[l] = m;
            });
            return i;
        }};
    })();
    var e = a.tools;
    a.dtd = (function () {
        var f = e.extend, g = {isindex: 1, fieldset: 1}, h = {input: 1, button: 1, select: 1, textarea: 1, label: 1}, i = f({a: 1}, h), j = f({iframe: 1}, i), k = {hr: 1, ul: 1, menu: 1, div: 1, section: 1, header: 1, footer: 1, nav: 1, article: 1, aside: 1, figure: 1, dialog: 1, hgroup: 1, mark: 1, time: 1, meter: 1, command: 1, keygen: 1, output: 1, progress: 1, audio: 1, video: 1, details: 1, datagrid: 1, datalist: 1, blockquote: 1, noscript: 1, table: 1, center: 1, address: 1, dir: 1, pre: 1, h5: 1, dl: 1, h4: 1, noframes: 1, h6: 1, ol: 1, h1: 1, h3: 1, h2: 1}, l = {ins: 1, del: 1, script: 1, style: 1}, m = f({b: 1, acronym: 1, bdo: 1, 'var': 1, '#': 1, abbr: 1, code: 1, br: 1, i: 1, cite: 1, kbd: 1, u: 1, strike: 1, s: 1, tt: 1, strong: 1, q: 1, samp: 1, em: 1, dfn: 1, span: 1, wbr: 1}, l), n = f({sub: 1, img: 1, object: 1, sup: 1, basefont: 1, map: 1, applet: 1, font: 1, big: 1, small: 1, mark: 1}, m), o = f({p: 1}, n), p = f({iframe: 1}, n, h), q = {img: 1, noscript: 1, br: 1, kbd: 1, center: 1, button: 1, basefont: 1, h5: 1, h4: 1, samp: 1, h6: 1, ol: 1, h1: 1, h3: 1, h2: 1, form: 1, font: 1, '#': 1, select: 1, menu: 1, ins: 1, abbr: 1, label: 1, code: 1, table: 1, script: 1, cite: 1, input: 1, iframe: 1, strong: 1, textarea: 1, noframes: 1, big: 1, small: 1, span: 1, hr: 1, sub: 1, bdo: 1, 'var': 1, div: 1, section: 1, header: 1, footer: 1, nav: 1, article: 1, aside: 1, figure: 1, dialog: 1, hgroup: 1, mark: 1, time: 1, meter: 1, menu: 1, command: 1, keygen: 1, output: 1, progress: 1, audio: 1, video: 1, details: 1, datagrid: 1, datalist: 1, object: 1, sup: 1, strike: 1, dir: 1, map: 1, dl: 1, applet: 1, del: 1, isindex: 1, fieldset: 1, ul: 1, b: 1, acronym: 1, a: 1, blockquote: 1, i: 1, u: 1, s: 1, tt: 1, address: 1, q: 1, pre: 1, p: 1, em: 1, dfn: 1}, r = f({a: 1}, p), s = {tr: 1}, t = {'#': 1}, u = f({param: 1}, q), v = f({form: 1}, g, j, k, o), w = {li: 1}, x = {style: 1, script: 1}, y = {base: 1, link: 1, meta: 1, title: 1}, z = f(y, x), A = {head: 1, body: 1}, B = {html: 1}, C = {address: 1, blockquote: 1, center: 1, dir: 1, div: 1, section: 1, header: 1, footer: 1, nav: 1, article: 1, aside: 1, figure: 1, dialog: 1, hgroup: 1, time: 1, meter: 1, menu: 1, command: 1, keygen: 1, output: 1, progress: 1, audio: 1, video: 1, details: 1, datagrid: 1, datalist: 1, dl: 1, fieldset: 1, form: 1, h1: 1, h2: 1, h3: 1, h4: 1, h5: 1, h6: 1, hr: 1, isindex: 1, noframes: 1, ol: 1, p: 1, pre: 1, table: 1, ul: 1};
        return{$nonBodyContent: f(B, A, y), $block: C, $blockLimit: {body: 1, div: 1, section: 1, header: 1, footer: 1, nav: 1, article: 1, aside: 1, figure: 1, dialog: 1, hgroup: 1, time: 1, meter: 1, menu: 1, command: 1, keygen: 1, output: 1, progress: 1, audio: 1, video: 1, details: 1, datagrid: 1, datalist: 1, td: 1, th: 1, caption: 1, form: 1}, $inline: r, $body: f({script: 1, style: 1}, C), $cdata: {script: 1, style: 1}, $empty: {area: 1, base: 1, br: 1, col: 1, hr: 1, img: 1, input: 1, link: 1, meta: 1, param: 1, wbr: 1}, $listItem: {dd: 1, dt: 1, li: 1}, $list: {ul: 1, ol: 1, dl: 1}, $nonEditable: {applet: 1, button: 1, embed: 1, iframe: 1, map: 1, object: 1, option: 1, script: 1, textarea: 1, param: 1, audio: 1, video: 1}, $captionBlock: {caption: 1, legend: 1}, $removeEmpty: {abbr: 1, acronym: 1, address: 1, b: 1, bdo: 1, big: 1, cite: 1, code: 1, del: 1, dfn: 1, em: 1, font: 1, i: 1, ins: 1, label: 1, kbd: 1, q: 1, s: 1, samp: 1, small: 1, span: 1, strike: 1, strong: 1, sub: 1, sup: 1, tt: 1, u: 1, 'var': 1, mark: 1}, $tabIndex: {a: 1, area: 1, button: 1, input: 1, object: 1, select: 1, textarea: 1}, $tableContent: {caption: 1, col: 1, colgroup: 1, tbody: 1, td: 1, tfoot: 1, th: 1, thead: 1, tr: 1}, html: A, head: z, style: t, script: t, body: v, base: {}, link: {}, meta: {}, title: t, col: {}, tr: {td: 1, th: 1}, img: {}, colgroup: {col: 1}, noscript: v, td: v, br: {}, wbr: {}, th: v, center: v, kbd: r, button: f(o, k), basefont: {}, h5: r, h4: r, samp: r, h6: r, ol: w, h1: r, h3: r, option: t, h2: r, form: f(g, j, k, o), select: {optgroup: 1, option: 1}, font: r, ins: r, menu: w, abbr: r, label: r, table: {thead: 1, col: 1, tbody: 1, tr: 1, colgroup: 1, caption: 1, tfoot: 1}, code: r, tfoot: s, cite: r, li: v, input: {}, iframe: v, strong: r, textarea: t, noframes: v, big: r, small: r, span: r, hr: {}, dt: r, sub: r, optgroup: {option: 1}, param: {}, bdo: r, 'var': r, div: v, object: u, sup: r, dd: v, strike: r, area: {}, dir: w, map: f({area: 1, form: 1, p: 1}, g, l, k), applet: u, dl: {dt: 1, dd: 1}, del: r, isindex: {}, fieldset: f({legend: 1}, q), thead: s, ul: w, acronym: r, b: r, a: p, blockquote: v, caption: r, i: r, u: r, tbody: s, s: r, address: f(j, o), tt: r, legend: r, q: r, pre: f(m, i), p: r, em: r, dfn: r, section: v, header: v, footer: v, nav: v, article: v, aside: v, figure: v, dialog: v, hgroup: v, mark: r, time: r, meter: r, menu: r, command: r, keygen: r, output: r, progress: u, audio: u, video: u, details: u, datagrid: u, datalist: u};
    })();
    var f = a.dtd;
    d.event = function (g) {
        this.$ = g;
    };
    d.event.prototype = {getKey: function () {
        return this.$.keyCode || this.$.which;
    }, getKeystroke: function () {
        var h = this;
        var g = h.getKey();
        if (h.$.ctrlKey || h.$.metaKey)g += 1114112;
        if (h.$.shiftKey)g += 2228224;
        if (h.$.altKey)g += 4456448;
        return g;
    }, preventDefault: function (g) {
        var h = this.$;
        if (h.preventDefault)h.preventDefault(); else h.returnValue = false;
        if (g)this.stopPropagation();
    }, stopPropagation: function () {
        var g = this.$;
        if (g.stopPropagation)g.stopPropagation(); else g.cancelBubble = true;
    }, getTarget: function () {
        var g = this.$.target || this.$.srcElement;
        return g ? new d.node(g) : null;
    }, getPageOffset: function () {
        var j = this;
        var g = j.getTarget().getDocument().$, h = j.$.pageX || j.$.clientX + (g.documentElement.scrollLeft || g.body.scrollLeft), i = j.$.pageY || j.$.clientY + (g.documentElement.scrollTop || g.body.scrollTop);
        return{x: h, y: i};
    }};
    a.CTRL = 1114112;
    a.SHIFT = 2228224;
    a.ALT = 4456448;
    d.domObject = function (g) {
        if (g)this.$ = g;
    };
    d.domObject.prototype = (function () {
        var g = function (h, i) {
            return function (j) {
                if (typeof a != 'undefined')h.fire(i, new d.event(j));
            };
        };
        return{getPrivate: function () {
            var h;
            if (!(h = this.getCustomData('_')))this.setCustomData('_', h = {});
            return h;
        }, on: function (h) {
            var k = this;
            var i = k.getCustomData('_cke_nativeListeners');
            if (!i) {
                i = {};
                k.setCustomData('_cke_nativeListeners', i);
            }
            if (!i[h]) {
                var j = i[h] = g(k, h);
                if (k.$.addEventListener)k.$.addEventListener(h, j, !!a.event.useCapture); else if (k.$.attachEvent)k.$.attachEvent('on' + h, j);
            }
            return a.event.prototype.on.apply(k, arguments);
        }, removeListener: function (h) {
            var k = this;
            a.event.prototype.removeListener.apply(k, arguments);
            if (!k.hasListeners(h)) {
                var i = k.getCustomData('_cke_nativeListeners'), j = i && i[h];
                if (j) {
                    if (k.$.removeEventListener)k.$.removeEventListener(h, j, false); else if (k.$.detachEvent)k.$.detachEvent('on' + h, j);
                    delete i[h];
                }
            }
        }, removeAllListeners: function () {
            var k = this;
            var h = k.getCustomData('_cke_nativeListeners');
            for (var i in h) {
                var j = h[i];
                if (k.$.detachEvent)k.$.detachEvent('on' + i, j); else if (k.$.removeEventListener)k.$.removeEventListener(i, j, false);
                delete h[i];
            }
        }};
    })();
    (function (g) {
        var h = {};
        a.on('reset', function () {
            h = {};
        });
        g.equals = function (i) {
            return i && i.$ === this.$;
        };
        g.setCustomData = function (i, j) {
            var k = this.getUniqueId(), l = h[k] || (h[k] = {});
            l[i] = j;
            return this;
        };
        g.getCustomData = function (i) {
            var j = this.$['data-cke-expando'], k = j && h[j];
            return k && k[i];
        };
        g.removeCustomData = function (i) {
            var j = this.$['data-cke-expando'], k = j && h[j], l = k && k[i];
            if (typeof l != 'undefined')delete k[i];
            return l || null;
        };
        g.clearCustomData = function () {
            this.removeAllListeners();
            var i = this.$['data-cke-expando'];
            i && delete h[i];
        };
        g.getUniqueId = function () {
            return this.$['data-cke-expando'] || (this.$['data-cke-expando'] = e.getNextNumber());
        };
        a.event.implementOn(g);
    })(d.domObject.prototype);
    d.window = function (g) {
        d.domObject.call(this, g);
    };
    d.window.prototype = new d.domObject();
    e.extend(d.window.prototype, {focus: function () {
        if (b.webkit && this.$.parent)this.$.parent.focus();
        this.$.focus();
    }, getViewPaneSize: function () {
        var g = this.$.document, h = g.compatMode == 'CSS1Compat';
        return{width: (h ? g.documentElement.clientWidth : g.body.clientWidth) || 0, height: (h ? g.documentElement.clientHeight : g.body.clientHeight) || 0};
    }, getScrollPosition: function () {
        var g = this.$;
        if ('pageXOffset' in g)return{x: g.pageXOffset || 0, y: g.pageYOffset || 0}; else {
            var h = g.document;
            return{x: h.documentElement.scrollLeft || h.body.scrollLeft || 0, y: h.documentElement.scrollTop || h.body.scrollTop || 0};
        }
    }});
    d.document = function (g) {
        d.domObject.call(this, g);
    };
    var g = d.document;
    g.prototype = new d.domObject();
    e.extend(g.prototype, {appendStyleSheet: function (h) {
        if (this.$.createStyleSheet)this.$.createStyleSheet(h); else {
            var i = new d.element('link');
            i.setAttributes({rel: 'stylesheet', type: 'text/css', href: h});
            this.getHead().append(i);
        }
    }, appendStyleText: function (h) {
        var k = this;
        if (k.$.createStyleSheet) {
            var i = k.$.createStyleSheet('');
            i.cssText = h;
        } else {
            var j = new d.element('style', k);
            j.append(new d.text(h, k));
            k.getHead().append(j);
        }
    }, createElement: function (h, i) {
        var j = new d.element(h, this);
        if (i) {
            if (i.attributes)j.setAttributes(i.attributes);
            if (i.styles)j.setStyles(i.styles);
        }
        return j;
    }, createText: function (h) {
        return new d.text(h, this);
    }, focus: function () {
        this.getWindow().focus();
    }, getById: function (h) {
        var i = this.$.getElementById(h);
        return i ? new d.element(i) : null;
    }, getByAddress: function (h, i) {
        var j = this.$.documentElement;
        for (var k = 0; j && k < h.length; k++) {
            var l = h[k];
            if (!i) {
                j = j.childNodes[l];
                continue;
            }
            var m = -1;
            for (var n = 0; n < j.childNodes.length; n++) {
                var o = j.childNodes[n];
                if (i === true && o.nodeType == 3 && o.previousSibling && o.previousSibling.nodeType == 3)continue;
                m++;
                if (m == l) {
                    j = o;
                    break;
                }
            }
        }
        return j ? new d.node(j) : null;
    }, getElementsByTag: function (h, i) {
        if (!(c && !(document.documentMode > 8)) && i)h = i + ':' + h;
        return new d.nodeList(this.$.getElementsByTagName(h));
    }, getHead: function () {
        var h = this.$.getElementsByTagName('head')[0];
        if (!h)h = this.getDocumentElement().append(new d.element('head'), true); else h = new d.element(h);
        return(this.getHead = function () {
            return h;
        })();
    }, getBody: function () {
        var h = new d.element(this.$.body);
        return(this.getBody = function () {
            return h;
        })();
    }, getDocumentElement: function () {
        var h = new d.element(this.$.documentElement);
        return(this.getDocumentElement = function () {
            return h;
        })();
    }, getWindow: function () {
        var h = new d.window(this.$.parentWindow || this.$.defaultView);
        return(this.getWindow = function () {
            return h;
        })();
    }, write: function (h) {
        var i = this;
        i.$.open('text/html', 'replace');
        b.isCustomDomain() && (i.$.domain = document.domain);
        i.$.write(h);
        i.$.close();
    }});
    d.node = function (h) {
        if (h) {
            var i = h.nodeType == 9 ? 'document' : h.nodeType == 1 ? 'element' : h.nodeType == 3 ? 'text' : h.nodeType == 8 ? 'comment' : 'domObject';
            return new d[i](h);
        }
        return this;
    };
    d.node.prototype = new d.domObject();
    a.NODE_ELEMENT = 1;
    a.NODE_DOCUMENT = 9;
    a.NODE_TEXT = 3;
    a.NODE_COMMENT = 8;
    a.NODE_DOCUMENT_FRAGMENT = 11;
    a.POSITION_IDENTICAL = 0;
    a.POSITION_DISCONNECTED = 1;
    a.POSITION_FOLLOWING = 2;
    a.POSITION_PRECEDING = 4;
    a.POSITION_IS_CONTAINED = 8;
    a.POSITION_CONTAINS = 16;
    e.extend(d.node.prototype, {appendTo: function (h, i) {
        h.append(this, i);
        return h;
    }, clone: function (h, i) {
        var j = this.$.cloneNode(h), k = function (l) {
            if (l.nodeType != 1)return;
            if (!i)l.removeAttribute('id', false);
            l['data-cke-expando'] = undefined;
            if (h) {
                var m = l.childNodes;
                for (var n = 0; n < m.length; n++)k(m[n]);
            }
        };
        k(j);
        return new d.node(j);
    }, hasPrevious: function () {
        return!!this.$.previousSibling;
    }, hasNext: function () {
        return!!this.$.nextSibling;
    }, insertAfter: function (h) {
        h.$.parentNode.insertBefore(this.$, h.$.nextSibling);
        return h;
    }, insertBefore: function (h) {
        h.$.parentNode.insertBefore(this.$, h.$);
        return h;
    }, insertBeforeMe: function (h) {
        this.$.parentNode.insertBefore(h.$, this.$);
        return h;
    }, getAddress: function (h) {
        var i = [], j = this.getDocument().$.documentElement, k = this.$;
        while (k && k != j) {
            var l = k.parentNode;
            if (l)i.unshift(this.getIndex.call({$: k}, h));
            k = l;
        }
        return i;
    }, getDocument: function () {
        return new g(this.$.ownerDocument || this.$.parentNode.ownerDocument);
    }, getIndex: function (h) {
        var i = this.$, j = 0;
        while (i = i.previousSibling) {
            if (h && i.nodeType == 3 && (!i.nodeValue.length || i.previousSibling && i.previousSibling.nodeType == 3))continue;
            j++;
        }
        return j;
    }, getNextSourceNode: function (h, i, j) {
        if (j && !j.call) {
            var k = j;
            j = function (n) {
                return!n.equals(k);
            };
        }
        var l = !h && this.getFirst && this.getFirst(), m;
        if (!l) {
            if (this.type == 1 && j && j(this, true) === false)return null;
            l = this.getNext();
        }
        while (!l && (m = (m || this).getParent())) {
            if (j && j(m, true) === false)return null;
            l = m.getNext();
        }
        if (!l)return null;
        if (j && j(l) === false)return null;
        if (i && i != l.type)return l.getNextSourceNode(false, i, j);
        return l;
    }, getPreviousSourceNode: function (h, i, j) {
        if (j && !j.call) {
            var k = j;
            j = function (n) {
                return!n.equals(k);
            };
        }
        var l = !h && this.getLast && this.getLast(), m;
        if (!l) {
            if (this.type == 1 && j && j(this, true) === false)return null;
            l = this.getPrevious();
        }
        while (!l && (m = (m || this).getParent())) {
            if (j && j(m, true) === false)return null;
            l = m.getPrevious();
        }
        if (!l)return null;
        if (j && j(l) === false)return null;
        if (i && l.type != i)return l.getPreviousSourceNode(false, i, j);
        return l;
    }, getPrevious: function (h) {
        var i = this.$, j;
        do {
            i = i.previousSibling;
            j = i && i.nodeType != 10 && new d.node(i);
        } while (j && h && !h(j));
        return j;
    }, getNext: function (h) {
        var i = this.$, j;
        do {
            i = i.nextSibling;
            j = i && new d.node(i);
        } while (j && h && !h(j));
        return j;
    }, getParent: function () {
        var h = this.$.parentNode;
        return h && h.nodeType == 1 ? new d.node(h) : null;
    }, getParents: function (h) {
        var i = this, j = [];
        do j[h ? 'push' : 'unshift'](i); while (i = i.getParent());
        return j;
    }, getCommonAncestor: function (h) {
        var j = this;
        if (h.equals(j))return j;
        if (h.contains && h.contains(j))return h;
        var i = j.contains ? j : j.getParent();
        do {
            if (i.contains(h))return i;
        } while (i = i.getParent());
        return null;
    }, getPosition: function (h) {
        var i = this.$, j = h.$;
        if (i.compareDocumentPosition)return i.compareDocumentPosition(j);
        if (i == j)return 0;
        if (this.type == 1 && h.type == 1) {
            if (i.contains) {
                if (i.contains(j))return 16 + 4;
                if (j.contains(i))return 8 + 2;
            }
            if ('sourceIndex' in i)return i.sourceIndex < 0 || j.sourceIndex < 0 ? 1 : i.sourceIndex < j.sourceIndex ? 4 : 2;
        }
        var k = this.getAddress(), l = h.getAddress(), m = Math.min(k.length, l.length);
        for (var n = 0; n <= m - 1; n++) {
            if (k[n] != l[n]) {
                if (n < m)return k[n] < l[n] ? 4 : 2;
                break;
            }
        }
        return k.length < l.length ? 16 + 4 : 8 + 2;
    }, getAscendant: function (h, i) {
        var j = this.$, k;
        if (!i)j = j.parentNode;
        while (j) {
            if (j.nodeName && (k = j.nodeName.toLowerCase(), typeof h == 'string' ? k == h : k in h))return new d.node(j);
            j = j.parentNode;
        }
        return null;
    }, hasAscendant: function (h, i) {
        var j = this.$;
        if (!i)j = j.parentNode;
        while (j) {
            if (j.nodeName && j.nodeName.toLowerCase() == h)return true;
            j = j.parentNode;
        }
        return false;
    }, move: function (h, i) {
        h.append(this.remove(), i);
    }, remove: function (h) {
        var i = this.$, j = i.parentNode;
        if (j) {
            if (h)for (var k; k = i.firstChild;)j.insertBefore(i.removeChild(k), i);
            j.removeChild(i);
        }
        return this;
    }, replace: function (h) {
        this.insertBefore(h);
        h.remove();
    }, trim: function () {
        this.ltrim();
        this.rtrim();
    }, ltrim: function () {
        var k = this;
        var h;
        while (k.getFirst && (h = k.getFirst())) {
            if (h.type == 3) {
                var i = e.ltrim(h.getText()), j = h.getLength();
                if (!i) {
                    h.remove();
                    continue;
                } else if (i.length < j) {
                    h.split(j - i.length);
                    k.$.removeChild(k.$.firstChild);
                }
            }
            break;
        }
    }, rtrim: function () {
        var k = this;
        var h;
        while (k.getLast && (h = k.getLast())) {
            if (h.type == 3) {
                var i = e.rtrim(h.getText()), j = h.getLength();
                if (!i) {
                    h.remove();
                    continue;
                } else if (i.length < j) {
                    h.split(i.length);
                    k.$.lastChild.parentNode.removeChild(k.$.lastChild);
                }
            }
            break;
        }
        if (!c && !b.opera) {
            h = k.$.lastChild;
            if (h && h.type == 1 && h.nodeName.toLowerCase() == 'br')h.parentNode.removeChild(h);
        }
    }, isReadOnly: function () {
        var h = this;
        if (this.type != 1)h = this.getParent();
        if (h && typeof h.$.isContentEditable != 'undefined')return!(h.$.isContentEditable || h.data('cke-editable')); else {
            var i = h;
            while (i) {
                if (i.is('body') || !!i.data('cke-editable'))break;
                if (i.getAttribute('contentEditable') == 'false')return true; else if (i.getAttribute('contentEditable') == 'true')break;
                i = i.getParent();
            }
            return false;
        }
    }});
    d.nodeList = function (h) {
        this.$ = h;
    };
    d.nodeList.prototype = {count: function () {
        return this.$.length;
    }, getItem: function (h) {
        var i = this.$[h];
        return i ? new d.node(i) : null;
    }};
    d.element = function (h, i) {
        if (typeof h == 'string')h = (i ? i.$ : document).createElement(h);
        d.domObject.call(this, h);
    };
    var h = d.element;
    h.get = function (i) {
        return i && (i.$ ? i : new h(i));
    };
    h.prototype = new d.node();
    h.createFromHtml = function (i, j) {
        var k = new h('div', j);
        k.setHtml(i);
        return k.getFirst().remove();
    };
    h.setMarker = function (i, j, k, l) {
        var m = j.getCustomData('list_marker_id') || j.setCustomData('list_marker_id', e.getNextNumber()).getCustomData('list_marker_id'), n = j.getCustomData('list_marker_names') || j.setCustomData('list_marker_names', {}).getCustomData('list_marker_names');
        i[m] = j;
        n[k] = 1;
        return j.setCustomData(k, l);
    };
    h.clearAllMarkers = function (i) {
        for (var j in i)h.clearMarkers(i, i[j], 1);
    };
    h.clearMarkers = function (i, j, k) {
        var l = j.getCustomData('list_marker_names'), m = j.getCustomData('list_marker_id');
        for (var n in l)j.removeCustomData(n);
        j.removeCustomData('list_marker_names');
        if (k) {
            j.removeCustomData('list_marker_id');
            delete i[m];
        }
    };
    (function () {
        e.extend(h.prototype, {type: 1, addClass: function (l) {
            var m = this.$.className;
            if (m) {
                var n = new RegExp('(?:^|\\s)' + l + '(?:\\s|$)', '');
                if (!n.test(m))m += ' ' + l;
            }
            this.$.className = m || l;
        }, removeClass: function (l) {
            var m = this.getAttribute('class');
            if (m) {
                var n = new RegExp('(?:^|\\s+)' + l + '(?=\\s|$)', 'i');
                if (n.test(m)) {
                    m = m.replace(n, '').replace(/^\s+/, '');
                    if (m)this.setAttribute('class', m); else this.removeAttribute('class');
                }
            }
        }, hasClass: function (l) {
            var m = new RegExp('(?:^|\\s+)' + l + '(?=\\s|$)', '');
            return m.test(this.getAttribute('class'));
        }, append: function (l, m) {
            var n = this;
            if (typeof l == 'string')l = n.getDocument().createElement(l);
            if (m)n.$.insertBefore(l.$, n.$.firstChild); else n.$.appendChild(l.$);
            return l;
        }, appendHtml: function (l) {
            var n = this;
            if (!n.$.childNodes.length)n.setHtml(l); else {
                var m = new h('div', n.getDocument());
                m.setHtml(l);
                m.moveChildren(n);
            }
        }, appendText: function (l) {
            if (this.$.text != undefined)this.$.text += l; else this.append(new d.text(l));
        }, appendBogus: function () {
            var n = this;
            var l = n.getLast();
            while (l && l.type == 3 && !e.rtrim(l.getText()))l = l.getPrevious();
            if (!l || !l.is || !l.is('br')) {
                var m = b.opera ? n.getDocument().createText('') : n.getDocument().createElement('br');
                b.gecko && m.setAttribute('type', '_moz');
                n.append(m);
            }
        }, breakParent: function (l) {
            var o = this;
            var m = new d.range(o.getDocument());
            m.setStartAfter(o);
            m.setEndAfter(l);
            var n = m.extractContents();
            m.insertNode(o.remove());
            n.insertAfterNode(o);
        }, contains: c || b.webkit ? function (l) {
            var m = this.$;
            return l.type != 1 ? m.contains(l.getParent().$) : m != l.$ && m.contains(l.$);
        } : function (l) {
            return!!(this.$.compareDocumentPosition(l.$) & 16);
        }, focus: (function () {
            function l() {
                try {
                    this.$.focus();
                } catch (m) {
                }
            };
            return function (m) {
                if (m)e.setTimeout(l, 100, this); else l.call(this);
            };
        })(), getHtml: function () {
            var l = this.$.innerHTML;
            return c ? l.replace(/<\?[^>]*>/g, '') : l;
        }, getOuterHtml: function () {
            var m = this;
            if (m.$.outerHTML)return m.$.outerHTML.replace(/<\?[^>]*>/, '');
            var l = m.$.ownerDocument.createElement('div');
            l.appendChild(m.$.cloneNode(true));
            return l.innerHTML;
        }, setHtml: function (l) {
            return this.$.innerHTML = l;
        }, setText: function (l) {
            h.prototype.setText = this.$.innerText != undefined ? function (m) {
                return this.$.innerText = m;
            } : function (m) {
                return this.$.textContent = m;
            };
            return this.setText(l);
        }, getAttribute: (function () {
            var l = function (m) {
                return this.$.getAttribute(m, 2);
            };
            if (c && (b.ie7Compat || b.ie6Compat))return function (m) {
                var q = this;
                switch (m) {
                    case 'class':
                        m = 'className';
                        break;
                    case 'http-equiv':
                        m = 'httpEquiv';
                        break;
                    case 'name':
                        return q.$.name;
                    case 'tabindex':
                        var n = l.call(q, m);
                        if (n !== 0 && q.$.tabIndex === 0)n = null;
                        return n;
                        break;
                    case 'checked':
                        var o = q.$.attributes.getNamedItem(m), p = o.specified ? o.nodeValue : q.$.checked;
                        return p ? 'checked' : null;
                    case 'hspace':
                    case 'value':
                        return q.$[m];
                    case 'style':
                        return q.$.style.cssText;
                    case 'contenteditable':
                    case 'contentEditable':
                        return q.$.attributes.getNamedItem('contentEditable').specified ? q.$.getAttribute('contentEditable') : null;
                }
                return l.call(q, m);
            }; else return l;
        })(), getChildren: function () {
            return new d.nodeList(this.$.childNodes);
        }, getComputedStyle: c ? function (l) {
            return this.$.currentStyle[e.cssStyleToDomStyle(l)];
        } : function (l) {
            var m = this.getWindow().$.getComputedStyle(this.$, null);
            return m ? m.getPropertyValue(l) : '';
        }, getDtd: function () {
            var l = f[this.getName()];
            this.getDtd = function () {
                return l;
            };
            return l;
        }, getElementsByTag: g.prototype.getElementsByTag, getTabIndex: c ? function () {
            var l = this.$.tabIndex;
            if (l === 0 && !f.$tabIndex[this.getName()] && parseInt(this.getAttribute('tabindex'), 10) !== 0)l = -1;
            return l;
        } : b.webkit ? function () {
            var l = this.$.tabIndex;
            if (l == undefined) {
                l = parseInt(this.getAttribute('tabindex'), 10);
                if (isNaN(l))l = -1;
            }
            return l;
        } : function () {
            return this.$.tabIndex;
        }, getText: function () {
            return this.$.textContent || this.$.innerText || '';
        }, getWindow: function () {
            return this.getDocument().getWindow();
        }, getId: function () {
            return this.$.id || null;
        }, getNameAtt: function () {
            return this.$.name || null;
        }, getName: function () {
            var l = this.$.nodeName.toLowerCase();
            if (c && !(document.documentMode > 8)) {
                var m = this.$.scopeName;
                if (m != 'HTML')l = m.toLowerCase() + ':' + l;
            }
            return(this.getName = function () {
                return l;
            })();
        }, getValue: function () {
            return this.$.value;
        }, getFirst: function (l) {
            var m = this.$.firstChild, n = m && new d.node(m);
            if (n && l && !l(n))n = n.getNext(l);
            return n;
        }, getLast: function (l) {
            var m = this.$.lastChild, n = m && new d.node(m);
            if (n && l && !l(n))n = n.getPrevious(l);
            return n;
        }, getStyle: function (l) {
            return this.$.style[e.cssStyleToDomStyle(l)];
        }, is: function () {
            var l = this.getName();
            for (var m = 0; m < arguments.length; m++) {
                if (arguments[m] == l)return true;
            }
            return false;
        }, isEditable: function (l) {
            var o = this;
            var m = o.getName();
            if (o.isReadOnly() || o.getComputedStyle('display') == 'none' || o.getComputedStyle('visibility') == 'hidden' || o.is('a') && o.data('cke-saved-name') && !o.getChildCount() || f.$nonEditable[m] || f.$empty[m])return false;
            if (l !== false) {
                var n = f[m] || f.span;
                return n && n['#'];
            }
            return true;
        }, isIdentical: function (l) {
            if (this.getName() != l.getName())return false;
            var m = this.$.attributes, n = l.$.attributes, o = m.length, p = n.length;
            for (var q = 0; q < o; q++) {
                var r = m[q];
                if (r.nodeName == '_moz_dirty')continue;
                if ((!c || r.specified && r.nodeName != 'data-cke-expando') && r.nodeValue != l.getAttribute(r.nodeName))return false;
            }
            if (c)for (q = 0; q < p; q++) {
                r = n[q];
                if (r.specified && r.nodeName != 'data-cke-expando' && r.nodeValue != this.getAttribute(r.nodeName))return false;
            }
            return true;
        }, isVisible: function () {
            var o = this;
            var l = (o.$.offsetHeight || o.$.offsetWidth) && o.getComputedStyle('visibility') != 'hidden', m, n;
            if (l && (b.webkit || b.opera)) {
                m = o.getWindow();
                if (!m.equals(a.document.getWindow()) && (n = m.$.frameElement))l = new h(n).isVisible();
            }
            return!!l;
        }, isEmptyInlineRemoveable: function () {
            if (!f.$removeEmpty[this.getName()])return false;
            var l = this.getChildren();
            for (var m = 0, n = l.count(); m < n; m++) {
                var o = l.getItem(m);
                if (o.type == 1 && o.data('cke-bookmark'))continue;
                if (o.type == 1 && !o.isEmptyInlineRemoveable() || o.type == 3 && e.trim(o.getText()))return false;
            }
            return true;
        }, hasAttributes: c && (b.ie7Compat || b.ie6Compat) ? function () {
            var l = this.$.attributes;
            for (var m = 0; m < l.length; m++) {
                var n = l[m];
                switch (n.nodeName) {
                    case 'class':
                        if (this.getAttribute('class'))return true;
                    case 'data-cke-expando':
                        continue;
                    default:
                        if (n.specified)return true;
                }
            }
            return false;
        } : function () {
            var l = this.$.attributes, m = l.length, n = {'data-cke-expando': 1, _moz_dirty: 1};
            return m > 0 && (m > 2 || !n[l[0].nodeName] || m == 2 && !n[l[1].nodeName]);
        }, hasAttribute: (function () {
            function l(m) {
                var n = this.$.attributes.getNamedItem(m);
                return!!(n && n.specified);
            };
            return c && b.version < 8 ? function (m) {
                if (m == 'name')return!!this.$.name;
                return l.call(this, m);
            } : l;
        })(), hide: function () {
            this.setStyle('display', 'none');
        }, moveChildren: function (l, m) {
            var n = this.$;
            l = l.$;
            if (n == l)return;
            var o;
            if (m)while (o = n.lastChild)l.insertBefore(n.removeChild(o), l.firstChild); else while (o = n.firstChild)l.appendChild(n.removeChild(o));
        }, mergeSiblings: (function () {
            function l(m, n, o) {
                if (n && n.type == 1) {
                    var p = [];
                    while (n.data('cke-bookmark') || n.isEmptyInlineRemoveable()) {
                        p.push(n);
                        n = o ? n.getNext() : n.getPrevious();
                        if (!n || n.type != 1)return;
                    }
                    if (m.isIdentical(n)) {
                        var q = o ? m.getLast() : m.getFirst();
                        while (p.length)p.shift().move(m, !o);
                        n.moveChildren(m, !o);
                        n.remove();
                        if (q && q.type == 1)q.mergeSiblings();
                    }
                }
            };
            return function (m) {
                var n = this;
                if (!(m === false || f.$removeEmpty[n.getName()] || n.is('a')))return;
                l(n, n.getNext(), true);
                l(n, n.getPrevious());
            };
        })(), show: function () {
            this.setStyles({display: '', visibility: ''});
        }, setAttribute: (function () {
            var l = function (m, n) {
                this.$.setAttribute(m, n);
                return this;
            };
            if (c && (b.ie7Compat || b.ie6Compat))return function (m, n) {
                var o = this;
                if (m == 'class')o.$.className = n; else if (m == 'style')o.$.style.cssText = n; else if (m == 'tabindex')o.$.tabIndex = n; else if (m == 'checked')o.$.checked = n; else if (m == 'contenteditable')l.call(o, 'contentEditable', n); else l.apply(o, arguments);
                return o;
            }; else if (b.ie8Compat && b.secure)return function (m, n) {
                if (m == 'src' && n.match(/^http:\/\//))try {
                    l.apply(this, arguments);
                } catch (o) {
                } else l.apply(this, arguments);
                return this;
            }; else return l;
        })(), setAttributes: function (l) {
            for (var m in l)this.setAttribute(m, l[m]);
            return this;
        }, setValue: function (l) {
            this.$.value = l;
            return this;
        }, removeAttribute: (function () {
            var l = function (m) {
                this.$.removeAttribute(m);
            };
            if (c && (b.ie7Compat || b.ie6Compat))return function (m) {
                if (m == 'class')m = 'className'; else if (m == 'tabindex')m = 'tabIndex'; else if (m == 'contenteditable')m = 'contentEditable';
                l.call(this, m);
            }; else return l;
        })(), removeAttributes: function (l) {
            if (e.isArray(l))for (var m = 0; m < l.length; m++)this.removeAttribute(l[m]); else for (var n in l)l.hasOwnProperty(n) && this.removeAttribute(n);
        }, removeStyle: function (l) {
            var p = this;
            var m = p.$.style;
            if (!m.removeProperty && (l == 'border' || l == 'margin' || l == 'padding')) {
                var n = j(l);
                for (var o = 0; o < n.length; o++)p.removeStyle(n[o]);
                return;
            }
            m.removeProperty ? m.removeProperty(l) : m.removeAttribute(e.cssStyleToDomStyle(l));
            if (!p.$.style.cssText)p.removeAttribute('style');
        }, setStyle: function (l, m) {
            this.$.style[e.cssStyleToDomStyle(l)] = m;
            return this;
        }, setStyles: function (l) {
            for (var m in l)this.setStyle(m, l[m]);
            return this;
        }, setOpacity: function (l) {
            if (c && b.version < 9) {
                l = Math.round(l * 100);
                this.setStyle('filter', l >= 100 ? '' : 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + l + ')');
            } else this.setStyle('opacity', l);
        }, unselectable: b.gecko ? function () {
            this.$.style.MozUserSelect = 'none';
            this.on('dragstart', function (l) {
                l.data.preventDefault();
            });
        } : b.webkit ? function () {
            this.$.style.KhtmlUserSelect = 'none';
            this.on('dragstart', function (l) {
                l.data.preventDefault();
            });
        } : function () {
            if (c || b.opera) {
                var l = this.$, m = l.getElementsByTagName('*'), n, o = 0;
                l.unselectable = 'on';
                while (n = m[o++])switch (n.tagName.toLowerCase()) {
                    case 'iframe':
                    case 'textarea':
                    case 'input':
                    case 'select':
                        break;
                    default:
                        n.unselectable = 'on';
                }
            }
        }, getPositionedAncestor: function () {
            var l = this;
            while (l.getName() != 'html') {
                if (l.getComputedStyle('position') != 'static')return l;
                l = l.getParent();
            }
            return null;
        }, getDocumentPosition: function (l) {
            var G = this;
            var m = 0, n = 0, o = G.getDocument(), p = o.getBody(), q = o.$.compatMode == 'BackCompat';
            if (document.documentElement.getBoundingClientRect) {
                var r = G.$.getBoundingClientRect(), s = o.$, t = s.documentElement, u = t.clientTop || p.$.clientTop || 0, v = t.clientLeft || p.$.clientLeft || 0, w = true;
                if (c) {
                    var x = o.getDocumentElement().contains(G), y = o.getBody().contains(G);
                    w = q && y || !q && x;
                }
                if (w) {
                    m = r.left + (!q && t.scrollLeft || p.$.scrollLeft);
                    m -= v;
                    n = r.top + (!q && t.scrollTop || p.$.scrollTop);
                    n -= u;
                }
            } else {
                var z = G, A = null, B;
                while (z && !(z.getName() == 'body' || z.getName() == 'html')) {
                    m += z.$.offsetLeft - z.$.scrollLeft;
                    n += z.$.offsetTop - z.$.scrollTop;
                    if (!z.equals(G)) {
                        m += z.$.clientLeft || 0;
                        n += z.$.clientTop || 0;
                    }
                    var C = A;
                    while (C && !C.equals(z)) {
                        m -= C.$.scrollLeft;
                        n -= C.$.scrollTop;
                        C = C.getParent();
                    }
                    A = z;
                    z = (B = z.$.offsetParent) ? new h(B) : null;
                }
            }
            if (l) {
                var D = G.getWindow(), E = l.getWindow();
                if (!D.equals(E) && D.$.frameElement) {
                    var F = new h(D.$.frameElement).getDocumentPosition(l);
                    m += F.x;
                    n += F.y;
                }
            }
            if (!document.documentElement.getBoundingClientRect)if (b.gecko && !q) {
                m += G.$.clientLeft ? 1 : 0;
                n += G.$.clientTop ? 1 : 0;
            }
            return{x: m, y: n};
        }, scrollIntoView: function (l) {
            var m = this.getParent();
            if (!m)return;
            do {
                var n = m.$.clientWidth && m.$.clientWidth < m.$.scrollWidth || m.$.clientHeight && m.$.clientHeight < m.$.scrollHeight;
                if (n)this.scrollIntoParent(m, l, 1);
                if (m.is('html')) {
                    var o = m.getWindow();
                    try {
                        var p = o.$.frameElement;
                        p && (m = new h(p));
                    } catch (q) {
                    }
                }
            } while (m = m.getParent());
        }, scrollIntoParent: function (l, m, n) {
            !l && (l = this.getWindow());
            var o = l.getDocument(), p = o.$.compatMode == 'BackCompat';
            if (l instanceof d.window)l = p ? o.getBody() : o.getDocumentElement();
            function q(C, D) {
                if (/body|html/.test(l.getName()))l.getWindow().$.scrollBy(C, D); else {
                    l.$.scrollLeft += C;
                    l.$.scrollTop += D;
                }
            };
            function r(C, D) {
                var E = {x: 0, y: 0};
                if (!C.is(p ? 'body' : 'html')) {
                    var F = C.$.getBoundingClientRect();
                    E.x = F.left, E.y = F.top;
                }
                var G = C.getWindow();
                if (!G.equals(D)) {
                    var H = r(h.get(G.$.frameElement), D);
                    E.x += H.x, E.y += H.y;
                }
                return E;
            };
            function s(C, D) {
                return parseInt(C.getComputedStyle('margin-' + D) || 0, 10) || 0;
            };
            var t = l.getWindow(), u = r(this, t), v = r(l, t), w = this.$.offsetHeight, x = this.$.offsetWidth, y = l.$.clientHeight, z = l.$.clientWidth, A, B;
            A = {x: u.x - s(this, 'left') - v.x || 0, y: u.y - s(this, 'top') - v.y || 0};
            B = {x: u.x + x + s(this, 'right') - (v.x + z) || 0, y: u.y + w + s(this, 'bottom') - (v.y + y) || 0};
            if (A.y < 0 || B.y > 0)q(0, m === true ? A.y : m === false ? B.y : A.y < 0 ? A.y : B.y);
            if (n && (A.x < 0 || B.x > 0))q(A.x < 0 ? A.x : B.x, 0);
        }, setState: function (l) {
            var m = this;
            switch (l) {
                case 1:
                    m.addClass('cke_on');
                    m.removeClass('cke_off');
                    m.removeClass('cke_disabled');
                    break;
                case 0:
                    m.addClass('cke_disabled');
                    m.removeClass('cke_off');
                    m.removeClass('cke_on');
                    break;
                default:
                    m.addClass('cke_off');
                    m.removeClass('cke_on');
                    m.removeClass('cke_disabled');
                    break;
            }
        }, getFrameDocument: function () {
            var l = this.$;
            try {
                l.contentWindow.document;
            } catch (m) {
                l.src = l.src;
                if (c && b.version < 7)window.showModalDialog('javascript:document.write("<script>window.setTimeout(function(){window.close();},50);</script>")');
            }
            return l && new g(l.contentWindow.document);
        }, copyAttributes: function (l, m) {
            var s = this;
            var n = s.$.attributes;
            m = m || {};
            for (var o = 0; o < n.length; o++) {
                var p = n[o], q = p.nodeName.toLowerCase(), r;
                if (q in m)continue;
                if (q == 'checked' && (r = s.getAttribute(q)))l.setAttribute(q, r); else if (p.specified || c && p.nodeValue && q == 'value') {
                    r = s.getAttribute(q);
                    if (r === null)r = p.nodeValue;
                    l.setAttribute(q, r);
                }
            }
            if (s.$.style.cssText !== '')l.$.style.cssText = s.$.style.cssText;
        }, renameNode: function (l) {
            var o = this;
            if (o.getName() == l)return;
            var m = o.getDocument(), n = new h(l, m);
            o.copyAttributes(n);
            o.moveChildren(n);
            o.getParent() && o.$.parentNode.replaceChild(n.$, o.$);
            n.$['data-cke-expando'] = o.$['data-cke-expando'];
            o.$ = n.$;
        }, getChild: function (l) {
            var m = this.$;
            if (!l.slice)m = m.childNodes[l]; else while (l.length > 0 && m)m = m.childNodes[l.shift()];
            return m ? new d.node(m) : null;
        }, getChildCount: function () {
            return this.$.childNodes.length;
        }, disableContextMenu: function () {
            this.on('contextmenu', function (l) {
                if (!l.data.getTarget().hasClass('cke_enable_context_menu'))l.data.preventDefault();
            });
        }, getDirection: function (l) {
            var m = this;
            return l ? m.getComputedStyle('direction') || m.getDirection() || m.getDocument().$.dir || m.getDocument().getBody().getDirection(1) : m.getStyle('direction') || m.getAttribute('dir');
        }, data: function (l, m) {
            l = 'data-' + l;
            if (m === undefined)return this.getAttribute(l); else if (m === false)this.removeAttribute(l); else this.setAttribute(l, m);
            return null;
        }});
        var i = {width: ['border-left-width', 'border-right-width', 'padding-left', 'padding-right'], height: ['border-top-width', 'border-bottom-width', 'padding-top', 'padding-bottom']};

        function j(l) {
            var m = ['top', 'left', 'right', 'bottom'], n;
            if (l == 'border')n = ['color', 'style', 'width'];
            var o = [];
            for (var p = 0; p < m.length; p++) {
                if (n)for (var q = 0; q < n.length; q++)o.push([l, m[p], n[q]].join('-')); else o.push([l, m[p]].join('-'));
            }
            return o;
        };
        function k(l) {
            var m = 0;
            for (var n = 0, o = i[l].length; n < o; n++)m += parseInt(this.getComputedStyle(i[l][n]) || 0, 10) || 0;
            return m;
        };
        h.prototype.setSize = function (l, m, n) {
            if (typeof m == 'number') {
                if (n && !(c && b.quirks))m -= k.call(this, l);
                this.setStyle(l, m + 'px');
            }
        };
        h.prototype.getSize = function (l, m) {
            var n = Math.max(this.$['offset' + e.capitalize(l)], this.$['client' + e.capitalize(l)]) || 0;
            if (m)n -= k.call(this, l);
            return n;
        };
    })();
    a.command = function (i, j) {
        this.uiItems = [];
        this.exec = function (k) {
            var l = this;
            if (l.state == 0)return false;
            if (l.editorFocus)i.focus();
            if (l.fire('exec') === true)return true;
            return j.exec.call(l, i, k) !== false;
        };
        this.refresh = function () {
            if (this.fire('refresh') === true)return true;
            return j.refresh && j.refresh.apply(this, arguments) !== false;
        };
        e.extend(this, j, {modes: {wysiwyg: 1}, editorFocus: 1, state: 2});
        a.event.call(this);
    };
    a.command.prototype = {enable: function () {
        var i = this;
        if (i.state == 0)i.setState(!i.preserveState || typeof i.previousState == 'undefined' ? 2 : i.previousState);
    }, disable: function () {
        this.setState(0);
    }, setState: function (i) {
        var j = this;
        if (j.state == i)return false;
        j.previousState = j.state;
        j.state = i;
        j.fire('state');
        return true;
    }, toggleState: function () {
        var i = this;
        if (i.state == 2)i.setState(1); else if (i.state == 1)i.setState(2);
    }};
    a.event.implementOn(a.command.prototype, true);
    a.ENTER_P = 1;
    a.ENTER_BR = 2;
    a.ENTER_DIV = 3;
    a.config = {customConfig: 'config.js', autoUpdateElement: true, baseHref: '', contentsCss: a.basePath + 'contents.css', contentsLangDirection: 'ui', contentsLanguage: '', language: '', defaultLanguage: 'en', enterMode: 1, forceEnterMode: false, shiftEnterMode: 2, corePlugins: '', docType: '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">', bodyId: '', bodyClass: '', fullPage: false, height: 200, plugins: 'about,a11yhelp,basicstyles,bidi,blockquote,button,clipboard,colorbutton,colordialog,contextmenu,dialogadvtab,div,elementspath,enterkey,entities,filebrowser,find,flash,font,format,forms,horizontalrule,htmldataprocessor,iframe,image,indent,justify,keystrokes,link,list,liststyle,maximize,newpage,pagebreak,pastefromword,pastetext,popup,preview,print,removeformat,resize,save,scayt,showblocks,showborders,smiley,sourcearea,specialchar,stylescombo,tab,table,tabletools,templates,toolbar,undo,wsc,wysiwygarea', extraPlugins: '', removePlugins: '', protectedSource: [], tabIndex: 0, theme: 'default', skin: 'kama', width: '', baseFloatZIndex: 10000};
    var i = a.config;
    a.focusManager = function (j) {
        if (j.focusManager)return j.focusManager;
        this.hasFocus = false;
        this._ = {editor: j};
        return this;
    };
    a.focusManager.prototype = {focus: function () {
        var k = this;
        if (k._.timer)clearTimeout(k._.timer);
        if (!k.hasFocus) {
            if (a.currentInstance)a.currentInstance.focusManager.forceBlur();
            var j = k._.editor;
            j.container.getChild(1).addClass('cke_focus');
            k.hasFocus = true;
            j.fire('focus');
        }
    }, blur: function () {
        var j = this;
        if (j._.timer)clearTimeout(j._.timer);
        j._.timer = setTimeout(function () {
            delete j._.timer;
            j.forceBlur();
        }, 100);
    }, forceBlur: function () {
        if (this.hasFocus) {
            var j = this._.editor;
            j.container.getChild(1).removeClass('cke_focus');
            this.hasFocus = false;
            j.fire('blur');
        }
    }};
    (function () {
        var j = {};
        a.lang = {languages: {af: 1, ar: 1, bg: 1, bn: 1, bs: 1, ca: 1, cs: 1, cy: 1, da: 1, de: 1, el: 1, 'en-au': 1, 'en-ca': 1, 'en-gb': 1, en: 1, eo: 1, es: 1, et: 1, eu: 1, fa: 1, fi: 1, fo: 1, 'fr-ca': 1, fr: 1, gl: 1, gu: 1, he: 1, hi: 1, hr: 1, hu: 1, is: 1, it: 1, ja: 1, ka: 1, km: 1, ko: 1, ku: 1, lt: 1, lv: 1, mn: 1, ms: 1, nb: 1, nl: 1, no: 1, pl: 1, 'pt-br': 1, pt: 1, ro: 1, ru: 1, sk: 1, sl: 1, 'sr-latn': 1, sr: 1, sv: 1, th: 1, tr: 1, ug: 1, uk: 1, vi: 1, 'zh-cn': 1, zh: 1}, load: function (k, l, m) {
            if (!k || !a.lang.languages[k])k = this.detect(l, k);
            if (!this[k])a.scriptLoader.load(a.getUrl('lang/' + k + '.js'), function () {
                m(k, this[k]);
            }, this); else m(k, this[k]);
        }, detect: function (k, l) {
            var m = this.languages;
            l = l || navigator.userLanguage || navigator.language || k;
            var n = l.toLowerCase().match(/([a-z]+)(?:-([a-z]+))?/), o = n[1], p = n[2];
            if (m[o + '-' + p])o = o + '-' + p; else if (!m[o])o = null;
            a.lang.detect = o ? function () {
                return o;
            } : function (q) {
                return q;
            };
            return o || k;
        }};
    })();
    a.scriptLoader = (function () {
        var j = {}, k = {};
        return{load: function (l, m, n, o) {
            var p = typeof l == 'string';
            if (p)l = [l];
            if (!n)n = a;
            var q = l.length, r = [], s = [], t = function (y) {
                if (m)if (p)m.call(n, y); else m.call(n, r, s);
            };
            if (q === 0) {
                t(true);
                return;
            }
            var u = function (y, z) {
                (z ? r : s).push(y);
                if (--q <= 0) {
                    o && a.document.getDocumentElement().removeStyle('cursor');
                    t(z);
                }
            }, v = function (y, z) {
                j[y] = 1;
                var A = k[y];
                delete k[y];
                for (var B = 0; B < A.length; B++)A[B](y, z);
            }, w = function (y) {
                if (j[y]) {
                    u(y, true);
                    return;
                }
                var z = k[y] || (k[y] = []);
                z.push(u);
                if (z.length > 1)return;
                var A = new h('script');
                A.setAttributes({type: 'text/javascript', src: y});
                if (m)if (c)A.$.onreadystatechange = function () {
                    if (A.$.readyState == 'loaded' || A.$.readyState == 'complete') {
                        A.$.onreadystatechange = null;
                        v(y, true);
                    }
                }; else {
                    A.$.onload = function () {
                        setTimeout(function () {
                            v(y, true);
                        }, 0);
                    };
                    A.$.onerror = function () {
                        v(y, false);
                    };
                }
                A.appendTo(a.document.getHead());
            };
            o && a.document.getDocumentElement().setStyle('cursor', 'wait');
            for (var x = 0; x < q; x++)w(l[x]);
        }};
    })();
    a.resourceManager = function (j, k) {
        var l = this;
        l.basePath = j;
        l.fileName = k;
        l.registered = {};
        l.loaded = {};
        l.externals = {};
        l._ = {waitingList: {}};
    };
    a.resourceManager.prototype = {add: function (j, k) {
        if (this.registered[j])throw '[CKEDITOR.resourceManager.add] The resource name "' + j + '" is already registered.';
        a.fire(j + e.capitalize(this.fileName) + 'Ready', this.registered[j] = k || {});
    }, get: function (j) {
        return this.registered[j] || null;
    }, getPath: function (j) {
        var k = this.externals[j];
        return a.getUrl(k && k.dir || this.basePath + j + '/');
    }, getFilePath: function (j) {
        var k = this.externals[j];
        return a.getUrl(this.getPath(j) + (k && typeof k.file == 'string' ? k.file : this.fileName + '.js'));
    }, addExternal: function (j, k, l) {
        j = j.split(',');
        for (var m = 0; m < j.length; m++) {
            var n = j[m];
            this.externals[n] = {dir: k, file: l};
        }
    }, load: function (j, k, l) {
        if (!e.isArray(j))j = j ? [j] : [];
        var m = this.loaded, n = this.registered, o = [], p = {}, q = {};
        for (var r = 0; r < j.length; r++) {
            var s = j[r];
            if (!s)continue;
            if (!m[s] && !n[s]) {
                var t = this.getFilePath(s);
                o.push(t);
                if (!(t in p))p[t] = [];
                p[t].push(s);
            } else q[s] = this.get(s);
        }
        a.scriptLoader.load(o, function (u, v) {
            if (v.length)throw '[CKEDITOR.resourceManager.load] Resource name "' + p[v[0]].join(',') + '" was not found at "' + v[0] + '".';
            for (var w = 0; w < u.length; w++) {
                var x = p[u[w]];
                for (var y = 0; y < x.length; y++) {
                    var z = x[y];
                    q[z] = this.get(z);
                    m[z] = 1;
                }
            }
            k.call(l, q);
        }, this);
    }};
    a.plugins = new a.resourceManager('plugins/', 'plugin');
    var j = a.plugins;
    j.load = e.override(j.load, function (k) {
        return function (l, m, n) {
            var o = {}, p = function (q) {
                k.call(this, q, function (r) {
                    e.extend(o, r);
                    var s = [];
                    for (var t in r) {
                        var u = r[t], v = u && u.requires;
                        if (v)for (var w = 0; w < v.length; w++) {
                            if (!o[v[w]])s.push(v[w]);
                        }
                    }
                    if (s.length)p.call(this, s); else {
                        for (t in o) {
                            u = o[t];
                            if (u.onLoad && !u.onLoad._called) {
                                u.onLoad();
                                u.onLoad._called = 1;
                            }
                        }
                        if (m)m.call(n || window, o);
                    }
                }, this);
            };
            p.call(this, l);
        };
    });
    j.setLang = function (k, l, m) {
        var n = this.get(k), o = n.langEntries || (n.langEntries = {}), p = n.lang || (n.lang = []);
        if (e.indexOf(p, l) == -1)p.push(l);
        o[l] = m;
    };
    a.skins = (function () {
        var k = {}, l = {}, m = function (n, o, p, q) {
            var r = k[o];
            if (!n.skin) {
                n.skin = r;
                if (r.init)r.init(n);
            }
            var s = function (B) {
                for (var C = 0; C < B.length; C++)B[C] = a.getUrl(l[o] + B[C]);
            };

            function t(B, C) {
                return B.replace(/url\s*\(([\s'"]*)(.*?)([\s"']*)\)/g, function (D, E, F, G) {
                    if (/^\/|^\w?:/.test(F))return D; else return 'url(' + C + E + F + G + ')';
                });
            };
            p = r[p];
            var u = !p || !!p._isLoaded;
            if (u)q && q(); else {
                var v = p._pending || (p._pending = []);
                v.push(q);
                if (v.length > 1)return;
                var w = !p.css || !p.css.length, x = !p.js || !p.js.length, y = function () {
                    if (w && x) {
                        p._isLoaded = 1;
                        for (var B = 0; B < v.length; B++) {
                            if (v[B])v[B]();
                        }
                    }
                };
                if (!w) {
                    var z = p.css;
                    if (e.isArray(z)) {
                        s(z);
                        for (var A = 0; A < z.length; A++)a.document.appendStyleSheet(z[A]);
                    } else {
                        z = t(z, a.getUrl(l[o]));
                        a.document.appendStyleText(z);
                    }
                    p.css = z;
                    w = 1;
                }
                if (!x) {
                    s(p.js);
                    a.scriptLoader.load(p.js, function () {
                        x = 1;
                        y();
                    });
                }
                y();
            }
        };
        return{add: function (n, o) {
            k[n] = o;
            o.skinPath = l[n] || (l[n] = a.getUrl('skins/' + n + '/'));
        }, load: function (n, o, p) {
            var q = n.skinName, r = n.skinPath;
            if (k[q])m(n, q, o, p); else {
                l[q] = r;
                a.scriptLoader.load(a.getUrl(r + 'skin.js'), function () {
                    m(n, q, o, p);
                });
            }
        }};
    })();
    a.themes = new a.resourceManager('themes/', 'theme');
    a.ui = function (k) {
        if (k.ui)return k.ui;
        this._ = {handlers: {}, items: {}, editor: k};
        return this;
    };
    var k = a.ui;
    k.prototype = {add: function (l, m, n) {
        this._.items[l] = {type: m, command: n.command || null, args: Array.prototype.slice.call(arguments, 2)};
    }, create: function (l) {
        var q = this;
        var m = q._.items[l], n = m && q._.handlers[m.type], o = m && m.command && q._.editor.getCommand(m.command), p = n && n.create.apply(q, m.args);
        m && (p = e.extend(p, q._.editor.skin[m.type], true));
        if (o)o.uiItems.push(p);
        return p;
    }, addHandler: function (l, m) {
        this._.handlers[l] = m;
    }};
    a.event.implementOn(k);
    (function () {
        var l = 0, m = function () {
            var x = 'editor' + ++l;
            return a.instances && a.instances[x] ? m() : x;
        }, n = {}, o = function (x) {
            var y = x.config.customConfig;
            if (!y)return false;
            y = a.getUrl(y);
            var z = n[y] || (n[y] = {});
            if (z.fn) {
                z.fn.call(x, x.config);
                if (a.getUrl(x.config.customConfig) == y || !o(x))x.fireOnce('customConfigLoaded');
            } else a.scriptLoader.load(y, function () {
                if (a.editorConfig)z.fn = a.editorConfig; else z.fn = function () {
                };
                o(x);
            });
            return true;
        }, p = function (x, y) {
            x.on('customConfigLoaded', function () {
                if (y) {
                    if (y.on)for (var z in y.on)x.on(z, y.on[z]);
                    e.extend(x.config, y, true);
                    delete x.config.on;
                }
                q(x);
            });
            if (y && y.customConfig != undefined)x.config.customConfig = y.customConfig;
            if (!o(x))x.fireOnce('customConfigLoaded');
        }, q = function (x) {
            var y = x.config.skin.split(','), z = y[0], A = a.getUrl(y[1] || 'skins/' + z + '/');
            x.skinName = z;
            x.skinPath = A;
            x.skinClass = 'cke_skin_' + z;
            x.tabIndex = x.config.tabIndex || x.element.getAttribute('tabindex') || 0;
            x.readOnly = !!(x.config.readOnly || x.element.getAttribute('disabled'));
            x.fireOnce('configLoaded');
            t(x);
        }, r = function (x) {
            a.lang.load(x.config.language, x.config.defaultLanguage, function (y, z) {
                x.langCode = y;
                x.lang = e.prototypedCopy(z);
                if (b.gecko && b.version < 10900 && x.lang.dir == 'rtl')x.lang.dir = 'ltr';
                x.fire('langLoaded');
                var A = x.config;
                A.contentsLangDirection == 'ui' && (A.contentsLangDirection = x.lang.dir);
                s(x);
            });
        }, s = function (x) {
            var y = x.config, z = y.plugins, A = y.extraPlugins, B = y.removePlugins;
            if (A) {
                var C = new RegExp('(?:^|,)(?:' + A.replace(/\s*,\s*/g, '|') + ')(?=,|$)', 'g');
                z = z.replace(C, '');
                z += ',' + A;
            }
            if (B) {
                C = new RegExp('(?:^|,)(?:' + B.replace(/\s*,\s*/g, '|') + ')(?=,|$)', 'g');
                z = z.replace(C, '');
            }
            b.air && (z += ',adobeair');
            j.load(z.split(','), function (D) {
                var E = [], F = [], G = [];
                x.plugins = D;
                for (var H in D) {
                    var I = D[H], J = I.lang, K = j.getPath(H), L = null;
                    I.path = K;
                    if (J) {
                        L = e.indexOf(J, x.langCode) >= 0 ? x.langCode : J[0];
                        if (!I.langEntries || !I.langEntries[L])G.push(a.getUrl(K + 'lang/' + L + '.js')); else {
                            e.extend(x.lang, I.langEntries[L]);
                            L = null;
                        }
                    }
                    F.push(L);
                    E.push(I);
                }
                a.scriptLoader.load(G, function () {
                    var M = ['beforeInit', 'init', 'afterInit'];
                    for (var N = 0; N < M.length; N++)for (var O = 0; O < E.length; O++) {
                        var P = E[O];
                        if (N === 0 && F[O] && P.lang)e.extend(x.lang, P.langEntries[F[O]]);
                        if (P[M[N]])P[M[N]](x);
                    }
                    x.fire('pluginsLoaded');
                    u(x);
                });
            });
        }, t = function (x) {
            a.skins.load(x, 'editor', function () {
                r(x);
            });
        }, u = function (x) {
            var y = x.config.theme;
            a.themes.load(y, function () {
                var z = x.theme = a.themes.get(y);
                z.path = a.themes.getPath(y);
                z.build(x);
                if (x.config.autoUpdateElement)v(x);
            });
        }, v = function (x) {
            var y = x.element;
            if (x.elementMode == 1 && y.is('textarea')) {
                var z = y.$.form && new h(y.$.form);
                if (z) {
                    function A() {
                        x.updateElement();
                    };
                    z.on('submit', A);
                    if (!z.$.submit.nodeName && !z.$.submit.length)z.$.submit = e.override(z.$.submit, function (B) {
                        return function () {
                            x.updateElement();
                            if (B.apply)B.apply(this, arguments); else B();
                        };
                    });
                    x.on('destroy', function () {
                        z.removeListener('submit', A);
                    });
                }
            }
        };

        function w() {
            var x, y = this._.commands, z = this.mode;
            if (!z)return;
            for (var A in y) {
                x = y[A];
                x[x.startDisabled ? 'disable' : this.readOnly && !x.readOnly ? 'disable' : x.modes[z] ? 'enable' : 'disable']();
            }
        };
        a.editor.prototype._init = function () {
            var z = this;
            var x = h.get(z._.element), y = z._.instanceConfig;
            delete z._.element;
            delete z._.instanceConfig;
            z._.commands = {};
            z._.styles = [];
            z.element = x;
            z.name = x && z.elementMode == 1 && (x.getId() || x.getNameAtt()) || m();
            if (z.name in a.instances)throw '[CKEDITOR.editor] The instance "' + z.name + '" already exists.';
            z.id = e.getNextId();
            z.config = e.prototypedCopy(i);
            z.ui = new k(z);
            z.focusManager = new a.focusManager(z);
            a.fire('instanceCreated', null, z);
            z.on('mode', w, null, null, 1);
            z.on('readOnly', w, null, null, 1);
            p(z, y);
        };
    })();
    e.extend(a.editor.prototype, {addCommand: function (l, m) {
        return this._.commands[l] = new a.command(this, m);
    }, addCss: function (l) {
        this._.styles.push(l);
    }, destroy: function (l) {
        var m = this;
        if (!l)m.updateElement();
        m.fire('destroy');
        m.theme && m.theme.destroy(m);
        a.remove(m);
        a.fire('instanceDestroyed', null, m);
    }, execCommand: function (l, m) {
        var n = this.getCommand(l), o = {name: l, commandData: m, command: n};
        if (n && n.state != 0)if (this.fire('beforeCommandExec', o) !== true) {
            o.returnValue = n.exec(o.commandData);
            if (!n.async && this.fire('afterCommandExec', o) !== true)return o.returnValue;
        }
        return false;
    }, getCommand: function (l) {
        return this._.commands[l];
    }, getData: function () {
        var n = this;
        n.fire('beforeGetData');
        var l = n._.data;
        if (typeof l != 'string') {
            var m = n.element;
            if (m && n.elementMode == 1)l = m.is('textarea') ? m.getValue() : m.getHtml(); else l = '';
        }
        l = {dataValue: l};
        n.fire('getData', l);
        return l.dataValue;
    }, getSnapshot: function () {
        var l = this.fire('getSnapshot');
        if (typeof l != 'string') {
            var m = this.element;
            if (m && this.elementMode == 1)l = m.is('textarea') ? m.getValue() : m.getHtml();
        }
        return l;
    }, loadSnapshot: function (l) {
        this.fire('loadSnapshot', l);
    }, setData: function (l, m, n) {
        if (m)this.on('dataReady', function (p) {
            p.removeListener();
            m.call(p.editor);
        });
        var o = {dataValue: l};
        !n && this.fire('setData', o);
        this._.data = o.dataValue;
        !n && this.fire('afterSetData', o);
    }, setReadOnly: function (l) {
        l = l == undefined || l;
        if (this.readOnly != l) {
            this.readOnly = l;
            this.fire('readOnly');
        }
    }, insertHtml: function (l) {
        this.fire('insertHtml', l);
    }, insertText: function (l) {
        this.fire('insertText', l);
    }, insertElement: function (l) {
        this.fire('insertElement', l);
    }, checkDirty: function () {
        return this.mayBeDirty && this._.previousValue !== this.getSnapshot();
    }, resetDirty: function () {
        if (this.mayBeDirty)this._.previousValue = this.getSnapshot();
    }, updateElement: function () {
        var n = this;
        var l = n.element;
        if (l && n.elementMode == 1) {
            var m = n.getData();
            if (n.config.htmlEncodeOutput)m = e.htmlEncode(m);
            if (l.is('textarea'))l.setValue(m); else l.setHtml(m);
        }
    }});
    a.on('loaded', function () {
        var l = a.editor._pending;
        if (l) {
            delete a.editor._pending;
            for (var m = 0; m < l.length; m++)l[m]._init();
        }
    });
    a.htmlParser = function () {
        this._ = {htmlPartsRegex: new RegExp("<(?:(?:\\/([^>]+)>)|(?:!--([\\S|\\s]*?)-->)|(?:([^\\s>]+)\\s*((?:(?:\"[^\"]*\")|(?:'[^']*')|[^\"'>])*)\\/?>))", 'g')};
    };
    (function () {
        var l = /([\w\-:.]+)(?:(?:\s*=\s*(?:(?:"([^"]*)")|(?:'([^']*)')|([^\s>]+)))|(?=\s|$))/g, m = {checked: 1, compact: 1, declare: 1, defer: 1, disabled: 1, ismap: 1, multiple: 1, nohref: 1, noresize: 1, noshade: 1, nowrap: 1, readonly: 1, selected: 1};
        a.htmlParser.prototype = {onTagOpen: function () {
        }, onTagClose: function () {
        }, onText: function () {
        }, onCDATA: function () {
        }, onComment: function () {
        }, parse: function (n) {
            var A = this;
            var o, p, q = 0, r;
            while (o = A._.htmlPartsRegex.exec(n)) {
                var s = o.index;
                if (s > q) {
                    var t = n.substring(q, s);
                    if (r)r.push(t); else A.onText(t);
                }
                q = A._.htmlPartsRegex.lastIndex;
                if (p = o[1]) {
                    p = p.toLowerCase();
                    if (r && f.$cdata[p]) {
                        A.onCDATA(r.join(''));
                        r = null;
                    }
                    if (!r) {
                        A.onTagClose(p);
                        continue;
                    }
                }
                if (r) {
                    r.push(o[0]);
                    continue;
                }
                if (p = o[3]) {
                    p = p.toLowerCase();
                    if (/="/.test(p))continue;
                    var u = {}, v, w = o[4], x = !!(w && w.charAt(w.length - 1) == '/');
                    if (w)while (v = l.exec(w)) {
                        var y = v[1].toLowerCase(), z = v[2] || v[3] || v[4] || '';
                        if (!z && m[y])u[y] = y; else u[y] = z;
                    }
                    A.onTagOpen(p, u, x);
                    if (!r && f.$cdata[p])r = [];
                    continue;
                }
                if (p = o[2])A.onComment(p);
            }
            if (n.length > q)A.onText(n.substring(q, n.length));
        }};
    })();
    a.htmlParser.comment = function (l) {
        this.value = l;
        this._ = {isBlockLike: false};
    };
    a.htmlParser.comment.prototype = {type: 8, writeHtml: function (l, m) {
        var n = this.value;
        if (m) {
            if (!(n = m.onComment(n, this)))return;
            if (typeof n != 'string') {
                n.parent = this.parent;
                n.writeHtml(l, m);
                return;
            }
        }
        l.comment(n);
    }};
    (function () {
        a.htmlParser.text = function (l) {
            this.value = l;
            this._ = {isBlockLike: false};
        };
        a.htmlParser.text.prototype = {type: 3, writeHtml: function (l, m) {
            var n = this.value;
            if (m && !(n = m.onText(n, this)))return;
            l.text(n);
        }};
    })();
    (function () {
        a.htmlParser.cdata = function (l) {
            this.value = l;
        };
        a.htmlParser.cdata.prototype = {type: 3, writeHtml: function (l) {
            l.write(this.value);
        }};
    })();
    a.htmlParser.fragment = function () {
        this.children = [];
        this.parent = null;
        this._ = {isBlockLike: true, hasInlineStarted: false};
    };
    (function () {
        var l = e.extend({table: 1, ul: 1, ol: 1, dl: 1}, f.table, f.ul, f.ol, f.dl), m = c && b.version < 8 ? {dd: 1, dt: 1} : {}, n = {ol: 1, ul: 1}, o = e.extend({}, {html: 1}, f.html, f.body, f.head, {style: 1, script: 1});

        function p(q) {
            return q.name == 'a' && q.attributes.href || f.$removeEmpty[q.name];
        };
        a.htmlParser.fragment.fromHtml = function (q, r, s) {
            var t = new a.htmlParser(), u = s || new a.htmlParser.fragment(), v = [], w = [], x = u, y = false, z = false;

            function A(D) {
                var E;
                if (v.length > 0)for (var F = 0; F < v.length; F++) {
                    var G = v[F], H = G.name, I = f[H], J = x.name && f[x.name];
                    if ((!J || J[H]) && (!D || !I || I[D] || !f[D])) {
                        if (!E) {
                            B();
                            E = 1;
                        }
                        G = G.clone();
                        G.parent = x;
                        x = G;
                        v.splice(F, 1);
                        F--;
                    } else if (H == x.name)C(x, x.parent, 1), F--;
                }
            };
            function B() {
                while (w.length)C(w.shift(), x);
            };
            function C(D, E, F) {
                if (D.previous !== undefined)return;
                E = E || x || u;
                var G = x;
                if (r && (!E.type || E.name == 'body')) {
                    var H, I;
                    if (D.attributes && (I = D.attributes['data-cke-real-element-type']))H = I; else H = D.name;
                    if (H && !(H in f.$body || H == 'body' || D.isOrphan)) {
                        x = E;
                        t.onTagOpen(r, {});
                        D.returnPoint = E = x;
                    }
                }
                if (D._.isBlockLike && D.name != 'pre' && D.name != 'textarea') {
                    var J = D.children.length, K = D.children[J - 1], L;
                    if (K && K.type == 3)if (!(L = e.rtrim(K.value)))D.children.length = J - 1; else K.value = L;
                }
                E.add(D);
                if (D.name == 'pre')z = false;
                if (D.name == 'textarea')y = false;
                if (D.returnPoint) {
                    x = D.returnPoint;
                    delete D.returnPoint;
                } else x = F ? E : G;
            };
            t.onTagOpen = function (D, E, F, G) {
                var H = new a.htmlParser.element(D, E);
                if (H.isUnknown && F)H.isEmpty = true;
                H.isOptionalClose = D in m || G;
                if (p(H)) {
                    v.push(H);
                    return;
                } else if (D == 'pre')z = true;
                else if (D == 'br' && z) {
                    x.add(new a.htmlParser.text('\n'));
                    return;
                } else if (D == 'textarea')y = true;
                if (D == 'br') {
                    w.push(H);
                    return;
                }
                while (1) {
                    var I = x.name, J = I ? f[I] || (x._.isBlockLike ? f.div : f.span) : o;
                    if (!H.isUnknown && !x.isUnknown && !J[D]) {
                        if (x.isOptionalClose)t.onTagClose(I); else if (D in n && I in n) {
                            var K = x.children, L = K[K.length - 1];
                            if (!(L && L.name == 'li'))C(L = new a.htmlParser.element('li'), x);
                            !H.returnPoint && (H.returnPoint = x);
                            x = L;
                        } else if (D in f.$listItem && I != D)t.onTagOpen(D == 'li' ? 'ul' : 'dl', {}, 0, 1); else if (I in l && I != D) {
                            !H.returnPoint && (H.returnPoint = x);
                            x = x.parent;
                        } else {
                            if (I in f.$inline)v.unshift(x);
                            if (x.parent)C(x, x.parent, 1); else {
                                H.isOrphan = 1;
                                break;
                            }
                        }
                    } else break;
                }
                A(D);
                B();
                H.parent = x;
                if (H.isEmpty)C(H); else x = H;
            };
            t.onTagClose = function (D) {
                for (var E = v.length - 1; E >= 0; E--) {
                    if (D == v[E].name) {
                        v.splice(E, 1);
                        return;
                    }
                }
                var F = [], G = [], H = x;
                while (H != u && H.name != D) {
                    if (!H._.isBlockLike)G.unshift(H);
                    F.push(H);
                    H = H.returnPoint || H.parent;
                }
                if (H != u) {
                    for (E = 0; E < F.length; E++) {
                        var I = F[E];
                        C(I, I.parent);
                    }
                    x = H;
                    if (H._.isBlockLike)B();
                    C(H, H.parent);
                    if (H == x)x = x.parent;
                    v = v.concat(G);
                }
                if (D == 'body')r = false;
            };
            t.onText = function (D) {
                if ((!x._.hasInlineStarted || w.length) && !z && !y) {
                    D = e.ltrim(D);
                    if (D.length === 0)return;
                }
                var E = x.name, F = E ? f[E] || (x._.isBlockLike ? f.div : f.span) : o;
                if (!y && !F['#'] && E in l) {
                    t.onTagOpen(E in n ? 'li' : E == 'dl' ? 'dd' : E == 'table' ? 'tr' : E == 'tr' ? 'td' : '');
                    t.onText(D);
                    return;
                }
                B();
                A();
                if (r && (!x.type || x.name == 'body') && e.trim(D))this.onTagOpen(r, {}, 0, 1);
                if (!z && !y)D = D.replace(/[\t\r\n ]{2,}|[\t\r\n]/g, ' ');
                x.add(new a.htmlParser.text(D));
            };
            t.onCDATA = function (D) {
                x.add(new a.htmlParser.cdata(D));
            };
            t.onComment = function (D) {
                B();
                A();
                x.add(new a.htmlParser.comment(D));
            };
            t.parse(q);
            B(!c && 1);
            while (x != u)C(x, x.parent, 1);
            return u;
        };
        a.htmlParser.fragment.prototype = {add: function (q, r) {
            var t = this;
            isNaN(r) && (r = t.children.length);
            var s = r > 0 ? t.children[r - 1] : null;
            if (s) {
                if (q._.isBlockLike && s.type == 3) {
                    s.value = e.rtrim(s.value);
                    if (s.value.length === 0) {
                        t.children.pop();
                        t.add(q);
                        return;
                    }
                }
                s.next = q;
            }
            q.previous = s;
            q.parent = t;
            t.children.splice(r, 0, q);
            t._.hasInlineStarted = q.type == 3 || q.type == 1 && !q._.isBlockLike;
        }, writeHtml: function (q, r) {
            var s;
            this.filterChildren = function () {
                var t = new a.htmlParser.basicWriter();
                this.writeChildrenHtml.call(this, t, r, true);
                var u = t.getHtml();
                this.children = new a.htmlParser.fragment.fromHtml(u).children;
                s = 1;
            };
            !this.name && r && r.onFragment(this);
            this.writeChildrenHtml(q, s ? null : r);
        }, writeChildrenHtml: function (q, r) {
            for (var s = 0; s < this.children.length; s++)this.children[s].writeHtml(q, r);
        }};
    })();
    a.htmlParser.element = function (l, m) {
        var q = this;
        q.name = l;
        q.attributes = m || {};
        q.children = [];
        var n = l || '', o = n.match(/^cke:(.*)/);
        o && (n = o[1]);
        var p = !!(f.$nonBodyContent[n] || f.$block[n] || f.$listItem[n] || f.$tableContent[n] || f.$nonEditable[n] || n == 'br');
        q.isEmpty = !!f.$empty[l];
        q.isUnknown = !f[l];
        q._ = {isBlockLike: p, hasInlineStarted: q.isEmpty || !p};
    };
    a.htmlParser.cssStyle = function () {
        var l, m = arguments[0], n = {};
        l = m instanceof a.htmlParser.element ? m.attributes.style : m;
        (l || '').replace(/&quot;/g, '"').replace(/\s*([^ :;]+)\s*:\s*([^;]+)\s*(?=;|$)/g, function (o, p, q) {
            p == 'font-family' && (q = q.replace(/["']/g, ''));
            n[p.toLowerCase()] = q;
        });
        return{rules: n, populate: function (o) {
            var p = this.toString();
            if (p)o instanceof h ? o.setAttribute('style', p) : o instanceof a.htmlParser.element ? o.attributes.style = p : o.style = p;
        }, 'toString': function () {
            var o = [];
            for (var p in n)n[p] && o.push(p, ':', n[p], ';');
            return o.join('');
        }};
    };
    (function () {
        var l = function (m, n) {
            m = m[0];
            n = n[0];
            return m < n ? -1 : m > n ? 1 : 0;
        };
        a.htmlParser.element.prototype = {type: 1, add: a.htmlParser.fragment.prototype.add, clone: function () {
            return new a.htmlParser.element(this.name, this.attributes);
        }, writeHtml: function (m, n) {
            var o = this.attributes, p = this, q = p.name, r, s, t, u;
            p.filterChildren = function () {
                if (!u) {
                    var B = new a.htmlParser.basicWriter();
                    a.htmlParser.fragment.prototype.writeChildrenHtml.call(p, B, n);
                    p.children = new a.htmlParser.fragment.fromHtml(B.getHtml(), 0, p.clone()).children;
                    u = 1;
                }
            };
            if (n) {
                for (; ;) {
                    if (!(q = n.onElementName(q)))return;
                    p.name = q;
                    if (!(p = n.onElement(p)))return;
                    p.parent = this.parent;
                    if (p.name == q)break;
                    if (p.type != 1) {
                        p.writeHtml(m, n);
                        return;
                    }
                    q = p.name;
                    if (!q) {
                        for (var v = 0, w = this.children.length; v < w; v++)this.children[v].parent = p.parent;
                        this.writeChildrenHtml.call(p, m, u ? null : n);
                        return;
                    }
                }
                o = p.attributes;
            }
            m.openTag(q, o);
            var x = [];
            for (var y = 0; y < 2; y++)for (r in o) {
                s = r;
                t = o[r];
                if (y == 1)x.push([r, t]); else if (n) {
                    for (; ;) {
                        if (!(s = n.onAttributeName(r))) {
                            delete o[r];
                            break;
                        } else if (s != r) {
                            delete o[r];
                            r = s;
                            continue;
                        } else break;
                    }
                    if (s)if ((t = n.onAttribute(p, s, t)) === false)delete o[s]; else o[s] = t;
                }
            }
            if (m.sortAttributes)x.sort(l);
            var z = x.length;
            for (y = 0; y < z; y++) {
                var A = x[y];
                m.attribute(A[0], A[1]);
            }
            m.openTagClose(q, p.isEmpty);
            if (!p.isEmpty) {
                this.writeChildrenHtml.call(p, m, u ? null : n);
                m.closeTag(q);
            }
        }, writeChildrenHtml: function (m, n) {
            a.htmlParser.fragment.prototype.writeChildrenHtml.apply(this, arguments);
        }};
    })();
    (function () {
        a.htmlParser.filter = e.createClass({$: function (q) {
            this._ = {elementNames: [], attributeNames: [], elements: {$length: 0}, attributes: {$length: 0}};
            if (q)this.addRules(q, 10);
        }, proto: {addRules: function (q, r) {
            var s = this;
            if (typeof r != 'number')r = 10;
            m(s._.elementNames, q.elementNames, r);
            m(s._.attributeNames, q.attributeNames, r);
            n(s._.elements, q.elements, r);
            n(s._.attributes, q.attributes, r);
            s._.text = o(s._.text, q.text, r) || s._.text;
            s._.comment = o(s._.comment, q.comment, r) || s._.comment;
            s._.root = o(s._.root, q.root, r) || s._.root;
        }, onElementName: function (q) {
            return l(q, this._.elementNames);
        }, onAttributeName: function (q) {
            return l(q, this._.attributeNames);
        }, onText: function (q) {
            var r = this._.text;
            return r ? r.filter(q) : q;
        }, onComment: function (q, r) {
            var s = this._.comment;
            return s ? s.filter(q, r) : q;
        }, onFragment: function (q) {
            var r = this._.root;
            return r ? r.filter(q) : q;
        }, onElement: function (q) {
            var v = this;
            var r = [v._.elements['^'], v._.elements[q.name], v._.elements.$], s, t;
            for (var u = 0; u < 3; u++) {
                s = r[u];
                if (s) {
                    t = s.filter(q, v);
                    if (t === false)return null;
                    if (t && t != q)return v.onNode(t);
                    if (q.parent && !q.name)break;
                }
            }
            return q;
        }, onNode: function (q) {
            var r = q.type;
            return r == 1 ? this.onElement(q) : r == 3 ? new a.htmlParser.text(this.onText(q.value)) : r == 8 ? new a.htmlParser.comment(this.onComment(q.value)) : null;
        }, onAttribute: function (q, r, s) {
            var t = this._.attributes[r];
            if (t) {
                var u = t.filter(s, q, this);
                if (u === false)return false;
                if (typeof u != 'undefined')return u;
            }
            return s;
        }}});
        function l(q, r) {
            for (var s = 0; q && s < r.length; s++) {
                var t = r[s];
                q = q.replace(t[0], t[1]);
            }
            return q;
        };
        function m(q, r, s) {
            if (typeof r == 'function')r = [r];
            var t, u, v = q.length, w = r && r.length;
            if (w) {
                for (t = 0; t < v && q[t].pri < s; t++) {
                }
                for (u = w - 1; u >= 0; u--) {
                    var x = r[u];
                    if (x) {
                        x.pri = s;
                        q.splice(t, 0, x);
                    }
                }
            }
        };
        function n(q, r, s) {
            if (r)for (var t in r) {
                var u = q[t];
                q[t] = o(u, r[t], s);
                if (!u)q.$length++;
            }
        };
        function o(q, r, s) {
            if (r) {
                r.pri = s;
                if (q) {
                    if (!q.splice) {
                        if (q.pri > s)q = [r, q]; else q = [q, r];
                        q.filter = p;
                    } else m(q, r, s);
                    return q;
                } else {
                    r.filter = r;
                    return r;
                }
            }
        };
        function p(q) {
            var r = q.type || q instanceof a.htmlParser.fragment;
            for (var s = 0; s < this.length; s++) {
                if (r)var t = q.type, u = q.name;
                var v = this[s], w = v.apply(window, arguments);
                if (w === false)return w;
                if (r) {
                    if (w && (w.name != u || w.type != t))return w;
                } else if (typeof w != 'string')return w;
                w != undefined && (q = w);
            }
            return q;
        };
    })();
    a.htmlParser.basicWriter = e.createClass({$: function () {
        this._ = {output: []};
    }, proto: {openTag: function (l, m) {
        this._.output.push('<', l);
    }, openTagClose: function (l, m) {
        if (m)this._.output.push(' />'); else this._.output.push('>');
    }, attribute: function (l, m) {
        if (typeof m == 'string')m = e.htmlEncodeAttr(m);
        this._.output.push(' ', l, '="', m, '"');
    }, closeTag: function (l) {
        this._.output.push('</', l, '>');
    }, text: function (l) {
        this._.output.push(l);
    }, comment: function (l) {
        this._.output.push('<!--', l, '-->');
    }, write: function (l) {
        this._.output.push(l);
    }, reset: function () {
        this._.output = [];
        this._.indent = false;
    }, getHtml: function (l) {
        var m = this._.output.join('');
        if (l)this.reset();
        return m;
    }}});
    delete a.loadFullCore;
    a.instances = {};
    a.document = new g(document);
    a.add = function (l) {
        a.instances[l.name] = l;
        l.on('focus', function () {
            if (a.currentInstance != l) {
                a.currentInstance = l;
                a.fire('currentInstance');
            }
        });
        l.on('blur', function () {
            if (a.currentInstance == l) {
                a.currentInstance = null;
                a.fire('currentInstance');
            }
        });
    };
    a.remove = function (l) {
        delete a.instances[l.name];
    };
    a.on('instanceDestroyed', function () {
        if (e.isEmpty(this.instances))a.fire('reset');
    });
    a.TRISTATE_ON = 1;
    a.TRISTATE_OFF = 2;
    a.TRISTATE_DISABLED = 0;
    d.comment = function (l, m) {
        if (typeof l == 'string')l = (m ? m.$ : document).createComment(l);
        d.domObject.call(this, l);
    };
    d.comment.prototype = new d.node();
    e.extend(d.comment.prototype, {type: 8, getOuterHtml: function () {
        return '<!--' + this.$.nodeValue + '-->';
    }});
    (function () {
        var l = {address: 1, blockquote: 1, dl: 1, h1: 1, h2: 1, h3: 1, h4: 1, h5: 1, h6: 1, p: 1, pre: 1, li: 1, dt: 1, dd: 1, legend: 1, caption: 1}, m = {body: 1, div: 1, table: 1, tbody: 1, tr: 1, td: 1, th: 1, form: 1, fieldset: 1}, n = function (o) {
            var p = o.getChildren();
            for (var q = 0, r = p.count(); q < r; q++) {
                var s = p.getItem(q);
                if (s.type == 1 && f.$block[s.getName()])return true;
            }
            return false;
        };
        d.elementPath = function (o) {
            var u = this;
            var p = null, q = null, r = [], s = o;
            while (s) {
                if (s.type == 1) {
                    if (!u.lastElement)u.lastElement = s;
                    var t = s.getName();
                    if (!q) {
                        if (!p && l[t])p = s;
                        if (m[t])if (!p && t == 'div' && !n(s))p = s; else q = s;
                    }
                    r.push(s);
                    if (t == 'body')break;
                }
                s = s.getParent();
            }
            u.block = p;
            u.blockLimit = q;
            u.elements = r;
        };
    })();
    d.elementPath.prototype = {compare: function (l) {
        var m = this.elements, n = l && l.elements;
        if (!n || m.length != n.length)return false;
        for (var o = 0; o < m.length; o++) {
            if (!m[o].equals(n[o]))return false;
        }
        return true;
    }, contains: function (l) {
        var m = this.elements;
        for (var n = 0; n < m.length; n++) {
            if (m[n].getName() in l)return m[n];
        }
        return null;
    }};
    d.text = function (l, m) {
        if (typeof l == 'string')l = (m ? m.$ : document).createTextNode(l);
        this.$ = l;
    };
    d.text.prototype = new d.node();
    e.extend(d.text.prototype, {type: 3, getLength: function () {
        return this.$.nodeValue.length;
    }, getText: function () {
        return this.$.nodeValue;
    }, setText: function (l) {
        this.$.nodeValue = l;
    }, split: function (l) {
        var q = this;
        if (c && l == q.getLength()) {
            var m = q.getDocument().createText('');
            m.insertAfter(q);
            return m;
        }
        var n = q.getDocument(), o = new d.text(q.$.splitText(l), n);
        if (b.ie8) {
            var p = new d.text('', n);
            p.insertAfter(o);
            p.remove();
        }
        return o;
    }, substring: function (l, m) {
        if (typeof m != 'number')return this.$.nodeValue.substr(l); else return this.$.nodeValue.substring(l, m);
    }});
    d.documentFragment = function (l) {
        l = l || a.document;
        this.$ = l.$.createDocumentFragment();
    };
    e.extend(d.documentFragment.prototype, h.prototype, {type: 11, insertAfterNode: function (l) {
        l = l.$;
        l.parentNode.insertBefore(this.$, l.nextSibling);
    }}, true, {append: 1, appendBogus: 1, getFirst: 1, getLast: 1, appendTo: 1, moveChildren: 1, insertBefore: 1, insertAfterNode: 1, replace: 1, trim: 1, type: 1, ltrim: 1, rtrim: 1, getDocument: 1, getChildCount: 1, getChild: 1, getChildren: 1});
    (function () {
        function l(s, t) {
            var u = this.range;
            if (this._.end)return null;
            if (!this._.start) {
                this._.start = 1;
                if (u.collapsed) {
                    this.end();
                    return null;
                }
                u.optimize();
            }
            var v, w = u.startContainer, x = u.endContainer, y = u.startOffset, z = u.endOffset, A, B = this.guard, C = this.type, D = s ? 'getPreviousSourceNode' : 'getNextSourceNode';
            if (!s && !this._.guardLTR) {
                var E = x.type == 1 ? x : x.getParent(), F = x.type == 1 ? x.getChild(z) : x.getNext();
                this._.guardLTR = function (J, K) {
                    return(!K || !E.equals(J)) && (!F || !J.equals(F)) && (J.type != 1 || !K || J.getName() != 'body');
                };
            }
            if (s && !this._.guardRTL) {
                var G = w.type == 1 ? w : w.getParent(), H = w.type == 1 ? y ? w.getChild(y - 1) : null : w.getPrevious();
                this._.guardRTL = function (J, K) {
                    return(!K || !G.equals(J)) && (!H || !J.equals(H)) && (J.type != 1 || !K || J.getName() != 'body');
                };
            }
            var I = s ? this._.guardRTL : this._.guardLTR;
            if (B)A = function (J, K) {
                if (I(J, K) === false)return false;
                return B(J, K);
            }; else A = I;
            if (this.current)v = this.current[D](false, C, A); else {
                if (s) {
                    v = x;
                    if (v.type == 1)if (z > 0)v = v.getChild(z - 1); else v = A(v, true) === false ? null : v.getPreviousSourceNode(true, C, A);
                } else {
                    v = w;
                    if (v.type == 1)if (!(v = v.getChild(y)))v = A(w, true) === false ? null : w.getNextSourceNode(true, C, A);
                }
                if (v && A(v) === false)v = null;
            }
            while (v && !this._.end) {
                this.current = v;
                if (!this.evaluator || this.evaluator(v) !== false) {
                    if (!t)return v;
                } else if (t && this.evaluator)return false;
                v = v[D](false, C, A);
            }
            this.end();
            return this.current = null;
        };
        function m(s) {
            var t, u = null;
            while (t = l.call(this, s))u = t;
            return u;
        };
        d.walker = e.createClass({$: function (s) {
            this.range = s;
            this._ = {};
        }, proto: {end: function () {
            this._.end = 1;
        }, next: function () {
            return l.call(this);
        }, previous: function () {
            return l.call(this, 1);
        }, checkForward: function () {
            return l.call(this, 0, 1) !== false;
        }, checkBackward: function () {
            return l.call(this, 1, 1) !== false;
        }, lastForward: function () {
            return m.call(this);
        }, lastBackward: function () {
            return m.call(this, 1);
        }, reset: function () {
            delete this.current;
            this._ = {};
        }}});
        var n = {block: 1, 'list-item': 1, table: 1, 'table-row-group': 1, 'table-header-group': 1, 'table-footer-group': 1, 'table-row': 1, 'table-column-group': 1, 'table-column': 1, 'table-cell': 1, 'table-caption': 1};
        h.prototype.isBlockBoundary = function (s) {
            var t = s ? e.extend({}, f.$block, s || {}) : f.$block;
            return this.getComputedStyle('float') == 'none' && n[this.getComputedStyle('display')] || t[this.getName()];
        };
        d.walker.blockBoundary = function (s) {
            return function (t, u) {
                return!(t.type == 1 && t.isBlockBoundary(s));
            };
        };
        d.walker.listItemBoundary = function () {
            return this.blockBoundary({br: 1});
        };
        d.walker.bookmark = function (s, t) {
            function u(v) {
                return v && v.getName && v.getName() == 'span' && v.data('cke-bookmark');
            };
            return function (v) {
                var w, x;
                w = v && !v.getName && (x = v.getParent()) && u(x);
                w = s ? w : w || u(v);
                return!!(t ^ w);
            };
        };
        d.walker.whitespaces = function (s) {
            return function (t) {
                var u;
                if (t && t.type == 3)u = !e.trim(t.getText()) || b.webkit && t.getText() == '​';
                return!!(s ^ u);
            };
        };
        d.walker.invisible = function (s) {
            var t = d.walker.whitespaces();
            return function (u) {
                var v;
                if (t(u))v = 1; else {
                    if (u.type == 3)u = u.getParent();
                    v = !u.$.offsetHeight;
                }
                return!!(s ^ v);
            };
        };
        d.walker.nodeType = function (s, t) {
            return function (u) {
                return!!(t ^ u.type == s);
            };
        };
        d.walker.bogus = function (s) {
            function t(u) {
                return!p(u) && !q(u);
            };
            return function (u) {
                var v = !c ? u.is && u.is('br') : u.getText && o.test(u.getText());
                if (v) {
                    var w = u.getParent(), x = u.getNext(t);
                    v = w.isBlockBoundary() && (!x || x.type == 1 && x.isBlockBoundary());
                }
                return!!(s ^ v);
            };
        };
        var o = /^[\t\r\n ]*(?:&nbsp;|\xa0)$/, p = d.walker.whitespaces(), q = d.walker.bookmark(), r = function (s) {
            return q(s) || p(s) || s.type == 1 && s.getName() in f.$inline && !(s.getName() in f.$empty);
        };
        h.prototype.getBogus = function () {
            var s = this;
            do s = s.getPreviousSourceNode(); while (r(s));
            if (s && (!c ? s.is && s.is('br') : s.getText && o.test(s.getText())))return s;
            return false;
        };
    })();
    d.range = function (l) {
        var m = this;
        m.startContainer = null;
        m.startOffset = null;
        m.endContainer = null;
        m.endOffset = null;
        m.collapsed = true;
        m.document = l;
    };
    (function () {
        var l = function (v) {
            v.collapsed = v.startContainer && v.endContainer && v.startContainer.equals(v.endContainer) && v.startOffset == v.endOffset;
        }, m = function (v, w, x, y) {
            v.optimizeBookmark();
            var z = v.startContainer, A = v.endContainer, B = v.startOffset, C = v.endOffset, D, E;
            if (A.type == 3)A = A.split(C); else if (A.getChildCount() > 0)if (C >= A.getChildCount()) {
                A = A.append(v.document.createText(''));
                E = true;
            } else A = A.getChild(C);
            if (z.type == 3) {
                z.split(B);
                if (z.equals(A))A = z.getNext();
            } else if (!B) {
                z = z.getFirst().insertBeforeMe(v.document.createText(''));
                D = true;
            } else if (B >= z.getChildCount()) {
                z = z.append(v.document.createText(''));
                D = true;
            } else z = z.getChild(B).getPrevious();
            var F = z.getParents(), G = A.getParents(), H, I, J;
            for (H = 0; H < F.length; H++) {
                I = F[H];
                J = G[H];
                if (!I.equals(J))break;
            }
            var K = x, L, M, N, O;
            for (var P = H; P < F.length; P++) {
                L = F[P];
                if (K && !L.equals(z))M = K.append(L.clone());
                N = L.getNext();
                while (N) {
                    if (N.equals(G[P]) || N.equals(A))break;
                    O = N.getNext();
                    if (w == 2)K.append(N.clone(true)); else {
                        N.remove();
                        if (w == 1)K.append(N);
                    }
                    N = O;
                }
                if (K)K = M;
            }
            K = x;
            for (var Q = H; Q < G.length; Q++) {
                L = G[Q];
                if (w > 0 && !L.equals(A))M = K.append(L.clone());
                if (!F[Q] || L.$.parentNode != F[Q].$.parentNode) {
                    N = L.getPrevious();
                    while (N) {
                        if (N.equals(F[Q]) || N.equals(z))break;
                        O = N.getPrevious();
                        if (w == 2)K.$.insertBefore(N.$.cloneNode(true), K.$.firstChild); else {
                            N.remove();
                            if (w == 1)K.$.insertBefore(N.$, K.$.firstChild);
                        }
                        N = O;
                    }
                }
                if (K)K = M;
            }
            if (w == 2) {
                var R = v.startContainer;
                if (R.type == 3) {
                    R.$.data += R.$.nextSibling.data;
                    R.$.parentNode.removeChild(R.$.nextSibling);
                }
                var S = v.endContainer;
                if (S.type == 3 && S.$.nextSibling) {
                    S.$.data += S.$.nextSibling.data;
                    S.$.parentNode.removeChild(S.$.nextSibling);
                }
            } else {
                if (I && J && (z.$.parentNode != I.$.parentNode || A.$.parentNode != J.$.parentNode)) {
                    var T = J.getIndex();
                    if (D && J.$.parentNode == z.$.parentNode)T--;
                    if (y && I.type == 1) {
                        var U = h.createFromHtml('<span data-cke-bookmark="1" style="display:none">&nbsp;</span>', v.document);
                        U.insertAfter(I);
                        I.mergeSiblings(false);
                        v.moveToBookmark({startNode: U});
                    } else v.setStart(J.getParent(), T);
                }
                v.collapse(true);
            }
            if (D)z.remove();
            if (E && A.$.parentNode)A.remove();
        }, n = {abbr: 1, acronym: 1, b: 1, bdo: 1, big: 1, cite: 1, code: 1, del: 1, dfn: 1, em: 1, font: 1, i: 1, ins: 1, label: 1, kbd: 1, q: 1, samp: 1, small: 1, span: 1, strike: 1, strong: 1, sub: 1, sup: 1, tt: 1, u: 1, 'var': 1};

        function o() {
            var v = false, w = d.walker.whitespaces(), x = d.walker.bookmark(true), y = d.walker.bogus();
            return function (z) {
                if (x(z) || w(z))return true;
                if (y(z) && !v) {
                    v = true;
                    return true;
                }
                if (z.type == 3 && (z.hasAscendant('pre') || e.trim(z.getText()).length))return false;
                if (z.type == 1 && !n[z.getName()])return false;
                return true;
            };
        };
        var p = d.walker.bogus();

        function q(v) {
            var w = d.walker.whitespaces(), x = d.walker.bookmark(1);
            return function (y) {
                if (x(y) || w(y))return true;
                return!v && p(y) || y.type == 1 && y.getName() in f.$removeEmpty;
            };
        };
        var r = new d.walker.whitespaces(), s = new d.walker.bookmark(), t = /^[\t\r\n ]*(?:&nbsp;|\xa0)$/;

        function u(v) {
            return!r(v) && !s(v);
        };
        d.range.prototype = {clone: function () {
            var w = this;
            var v = new d.range(w.document);
            v.startContainer = w.startContainer;
            v.startOffset = w.startOffset;
            v.endContainer = w.endContainer;
            v.endOffset = w.endOffset;
            v.collapsed = w.collapsed;
            return v;
        }, collapse: function (v) {
            var w = this;
            if (v) {
                w.endContainer = w.startContainer;
                w.endOffset = w.startOffset;
            } else {
                w.startContainer = w.endContainer;
                w.startOffset = w.endOffset;
            }
            w.collapsed = true;
        }, cloneContents: function () {
            var v = new d.documentFragment(this.document);
            if (!this.collapsed)m(this, 2, v);
            return v;
        }, deleteContents: function (v) {
            if (this.collapsed)return;
            m(this, 0, null, v);
        }, extractContents: function (v) {
            var w = new d.documentFragment(this.document);
            if (!this.collapsed)m(this, 1, w, v);
            return w;
        }, createBookmark: function (v) {
            var B = this;
            var w, x, y, z, A = B.collapsed;
            w = B.document.createElement('span');
            w.data('cke-bookmark', 1);
            w.setStyle('display', 'none');
            w.setHtml('&nbsp;');
            if (v) {
                y = 'cke_bm_' + e.getNextNumber();
                w.setAttribute('id', y + (A ? 'C' : 'S'));
            }
            if (!A) {
                x = w.clone();
                x.setHtml('&nbsp;');
                if (v)x.setAttribute('id', y + 'E');
                z = B.clone();
                z.collapse();
                z.insertNode(x);
            }
            z = B.clone();
            z.collapse(true);
            z.insertNode(w);
            if (x) {
                B.setStartAfter(w);
                B.setEndBefore(x);
            } else B.moveToPosition(w, 4);
            return{startNode: v ? y + (A ? 'C' : 'S') : w, endNode: v ? y + 'E' : x, serializable: v, collapsed: A};
        }, createBookmark2: function (v) {
            var D = this;
            var w = D.startContainer, x = D.endContainer, y = D.startOffset, z = D.endOffset, A = D.collapsed, B, C;
            if (!w || !x)return{start: 0, end: 0};
            if (v) {
                if (w.type == 1) {
                    B = w.getChild(y);
                    if (B && B.type == 3 && y > 0 && B.getPrevious().type == 3) {
                        w = B;
                        y = 0;
                    }
                    if (B && B.type == 1)y = B.getIndex(1);
                }
                while (w.type == 3 && (C = w.getPrevious()) && C.type == 3) {
                    w = C;
                    y += C.getLength();
                }
                if (!A) {
                    if (x.type == 1) {
                        B = x.getChild(z);
                        if (B && B.type == 3 && z > 0 && B.getPrevious().type == 3) {
                            x = B;
                            z = 0;
                        }
                        if (B && B.type == 1)z = B.getIndex(1);
                    }
                    while (x.type == 3 && (C = x.getPrevious()) && C.type == 3) {
                        x = C;
                        z += C.getLength();
                    }
                }
            }
            return{start: w.getAddress(v), end: A ? null : x.getAddress(v), startOffset: y, endOffset: z, normalized: v, collapsed: A, is2: true};
        }, moveToBookmark: function (v) {
            var D = this;
            if (v.is2) {
                var w = D.document.getByAddress(v.start, v.normalized), x = v.startOffset, y = v.end && D.document.getByAddress(v.end, v.normalized), z = v.endOffset;
                D.setStart(w, x);
                if (y)D.setEnd(y, z); else D.collapse(true);
            } else {
                var A = v.serializable, B = A ? D.document.getById(v.startNode) : v.startNode, C = A ? D.document.getById(v.endNode) : v.endNode;
                D.setStartBefore(B);
                B.remove();
                if (C) {
                    D.setEndBefore(C);
                    C.remove();
                } else D.collapse(true);
            }
        }, getBoundaryNodes: function () {
            var A = this;
            var v = A.startContainer, w = A.endContainer, x = A.startOffset, y = A.endOffset, z;
            if (v.type == 1) {
                z = v.getChildCount();
                if (z > x)v = v.getChild(x); else if (z < 1)v = v.getPreviousSourceNode(); else {
                    v = v.$;
                    while (v.lastChild)v = v.lastChild;
                    v = new d.node(v);
                    v = v.getNextSourceNode() || v;
                }
            }
            if (w.type == 1) {
                z = w.getChildCount();
                if (z > y)w = w.getChild(y).getPreviousSourceNode(true); else if (z < 1)w = w.getPreviousSourceNode(); else {
                    w = w.$;
                    while (w.lastChild)w = w.lastChild;
                    w = new d.node(w);
                }
            }
            if (v.getPosition(w) & 2)v = w;
            return{startNode: v, endNode: w};
        }, getCommonAncestor: function (v, w) {
            var A = this;
            var x = A.startContainer, y = A.endContainer, z;
            if (x.equals(y)) {
                if (v && x.type == 1 && A.startOffset == A.endOffset - 1)z = x.getChild(A.startOffset);
                else z = x;
            } else z = x.getCommonAncestor(y);
            return w && !z.is ? z.getParent() : z;
        }, optimize: function () {
            var x = this;
            var v = x.startContainer, w = x.startOffset;
            if (v.type != 1)if (!w)x.setStartBefore(v); else if (w >= v.getLength())x.setStartAfter(v);
            v = x.endContainer;
            w = x.endOffset;
            if (v.type != 1)if (!w)x.setEndBefore(v); else if (w >= v.getLength())x.setEndAfter(v);
        }, optimizeBookmark: function () {
            var x = this;
            var v = x.startContainer, w = x.endContainer;
            if (v.is && v.is('span') && v.data('cke-bookmark'))x.setStartAt(v, 3);
            if (w && w.is && w.is('span') && w.data('cke-bookmark'))x.setEndAt(w, 4);
        }, trim: function (v, w) {
            var D = this;
            var x = D.startContainer, y = D.startOffset, z = D.collapsed;
            if ((!v || z) && x && x.type == 3) {
                if (!y) {
                    y = x.getIndex();
                    x = x.getParent();
                } else if (y >= x.getLength()) {
                    y = x.getIndex() + 1;
                    x = x.getParent();
                } else {
                    var A = x.split(y);
                    y = x.getIndex() + 1;
                    x = x.getParent();
                    if (D.startContainer.equals(D.endContainer))D.setEnd(A, D.endOffset - D.startOffset); else if (x.equals(D.endContainer))D.endOffset += 1;
                }
                D.setStart(x, y);
                if (z) {
                    D.collapse(true);
                    return;
                }
            }
            var B = D.endContainer, C = D.endOffset;
            if (!(w || z) && B && B.type == 3) {
                if (!C) {
                    C = B.getIndex();
                    B = B.getParent();
                } else if (C >= B.getLength()) {
                    C = B.getIndex() + 1;
                    B = B.getParent();
                } else {
                    B.split(C);
                    C = B.getIndex() + 1;
                    B = B.getParent();
                }
                D.setEnd(B, C);
            }
        }, enlarge: function (v, w) {
            switch (v) {
                case 1:
                    if (this.collapsed)return;
                    var x = this.getCommonAncestor(), y = this.document.getBody(), z, A, B, C, D, E = false, F, G, H = this.startContainer, I = this.startOffset;
                    if (H.type == 3) {
                        if (I) {
                            H = !e.trim(H.substring(0, I)).length && H;
                            E = !!H;
                        }
                        if (H)if (!(C = H.getPrevious()))B = H.getParent();
                    } else {
                        if (I)C = H.getChild(I - 1) || H.getLast();
                        if (!C)B = H;
                    }
                    while (B || C) {
                        if (B && !C) {
                            if (!D && B.equals(x))D = true;
                            if (!y.contains(B))break;
                            if (!E || B.getComputedStyle('display') != 'inline') {
                                E = false;
                                if (D)z = B; else this.setStartBefore(B);
                            }
                            C = B.getPrevious();
                        }
                        while (C) {
                            F = false;
                            if (C.type == 8) {
                                C = C.getPrevious();
                                continue;
                            } else if (C.type == 3) {
                                G = C.getText();
                                if (/[^\s\ufeff]/.test(G))C = null;
                                F = /[\s\ufeff]$/.test(G);
                            } else if ((C.$.offsetWidth > 0 || w && C.is('br')) && !C.data('cke-bookmark'))if (E && f.$removeEmpty[C.getName()]) {
                                G = C.getText();
                                if (/[^\s\ufeff]/.test(G))C = null; else {
                                    var J = C.$.getElementsByTagName('*');
                                    for (var K = 0, L; L = J[K++];) {
                                        if (!f.$removeEmpty[L.nodeName.toLowerCase()]) {
                                            C = null;
                                            break;
                                        }
                                    }
                                }
                                if (C)F = !!G.length;
                            } else C = null;
                            if (F)if (E) {
                                if (D)z = B; else if (B)this.setStartBefore(B);
                            } else E = true;
                            if (C) {
                                var M = C.getPrevious();
                                if (!B && !M) {
                                    B = C;
                                    C = null;
                                    break;
                                }
                                C = M;
                            } else B = null;
                        }
                        if (B)B = B.getParent();
                    }
                    H = this.endContainer;
                    I = this.endOffset;
                    B = C = null;
                    D = E = false;
                    if (H.type == 3) {
                        H = !e.trim(H.substring(I)).length && H;
                        E = !(H && H.getLength());
                        if (H)if (!(C = H.getNext()))B = H.getParent();
                    } else {
                        C = H.getChild(I);
                        if (!C)B = H;
                    }
                    while (B || C) {
                        if (B && !C) {
                            if (!D && B.equals(x))D = true;
                            if (!y.contains(B))break;
                            if (!E || B.getComputedStyle('display') != 'inline') {
                                E = false;
                                if (D)A = B; else if (B)this.setEndAfter(B);
                            }
                            C = B.getNext();
                        }
                        while (C) {
                            F = false;
                            if (C.type == 3) {
                                G = C.getText();
                                if (/[^\s\ufeff]/.test(G))C = null;
                                F = /^[\s\ufeff]/.test(G);
                            } else if (C.type == 1) {
                                if ((C.$.offsetWidth > 0 || w && C.is('br')) && !C.data('cke-bookmark'))if (E && f.$removeEmpty[C.getName()]) {
                                    G = C.getText();
                                    if (/[^\s\ufeff]/.test(G))C = null; else {
                                        J = C.$.getElementsByTagName('*');
                                        for (K = 0; L = J[K++];) {
                                            if (!f.$removeEmpty[L.nodeName.toLowerCase()]) {
                                                C = null;
                                                break;
                                            }
                                        }
                                    }
                                    if (C)F = !!G.length;
                                } else C = null;
                            } else F = 1;
                            if (F)if (E)if (D)A = B; else this.setEndAfter(B);
                            if (C) {
                                M = C.getNext();
                                if (!B && !M) {
                                    B = C;
                                    C = null;
                                    break;
                                }
                                C = M;
                            } else B = null;
                        }
                        if (B)B = B.getParent();
                    }
                    if (z && A) {
                        x = z.contains(A) ? A : z;
                        this.setStartBefore(x);
                        this.setEndAfter(x);
                    }
                    break;
                case 2:
                case 3:
                    var N = new d.range(this.document);
                    y = this.document.getBody();
                    N.setStartAt(y, 1);
                    N.setEnd(this.startContainer, this.startOffset);
                    var O = new d.walker(N), P, Q, R = d.walker.blockBoundary(v == 3 ? {br: 1} : null), S = function (Y) {
                        var Z = R(Y);
                        if (!Z)P = Y;
                        return Z;
                    }, T = function (Y) {
                        var Z = S(Y);
                        if (!Z && Y.is && Y.is('br'))Q = Y;
                        return Z;
                    };
                    O.guard = S;
                    B = O.lastBackward();
                    P = P || y;
                    this.setStartAt(P, !P.is('br') && (!B && this.checkStartOfBlock() || B && P.contains(B)) ? 1 : 4);
                    if (v == 3) {
                        var U = this.clone();
                        O = new d.walker(U);
                        var V = d.walker.whitespaces(), W = d.walker.bookmark();
                        O.evaluator = function (Y) {
                            return!V(Y) && !W(Y);
                        };
                        var X = O.previous();
                        if (X && X.type == 1 && X.is('br'))return;
                    }
                    N = this.clone();
                    N.collapse();
                    N.setEndAt(y, 2);
                    O = new d.walker(N);
                    O.guard = v == 3 ? T : S;
                    P = null;
                    B = O.lastForward();
                    P = P || y;
                    this.setEndAt(P, !B && this.checkEndOfBlock() || B && P.contains(B) ? 2 : 3);
                    if (Q)this.setEndAfter(Q);
            }
        }, shrink: function (v, w) {
            if (!this.collapsed) {
                v = v || 2;
                var x = this.clone(), y = this.startContainer, z = this.endContainer, A = this.startOffset, B = this.endOffset, C = this.collapsed, D = 1, E = 1;
                if (y && y.type == 3)if (!A)x.setStartBefore(y); else if (A >= y.getLength())x.setStartAfter(y); else {
                    x.setStartBefore(y);
                    D = 0;
                }
                if (z && z.type == 3)if (!B)x.setEndBefore(z); else if (B >= z.getLength())x.setEndAfter(z); else {
                    x.setEndAfter(z);
                    E = 0;
                }
                var F = new d.walker(x), G = d.walker.bookmark();
                F.evaluator = function (K) {
                    return K.type == (v == 1 ? 1 : 3);
                };
                var H;
                F.guard = function (K, L) {
                    if (G(K))return true;
                    if (v == 1 && K.type == 3)return false;
                    if (L && K.equals(H))return false;
                    if (!L && K.type == 1)H = K;
                    return true;
                };
                if (D) {
                    var I = F[v == 1 ? 'lastForward' : 'next']();
                    I && this.setStartAt(I, w ? 1 : 3);
                }
                if (E) {
                    F.reset();
                    var J = F[v == 1 ? 'lastBackward' : 'previous']();
                    J && this.setEndAt(J, w ? 2 : 4);
                }
                return!!(D || E);
            }
        }, insertNode: function (v) {
            var z = this;
            z.optimizeBookmark();
            z.trim(false, true);
            var w = z.startContainer, x = z.startOffset, y = w.getChild(x);
            if (y)v.insertBefore(y); else w.append(v);
            if (v.getParent().equals(z.endContainer))z.endOffset++;
            z.setStartBefore(v);
        }, moveToPosition: function (v, w) {
            this.setStartAt(v, w);
            this.collapse(true);
        }, selectNodeContents: function (v) {
            this.setStart(v, 0);
            this.setEnd(v, v.type == 3 ? v.getLength() : v.getChildCount());
        }, setStart: function (v, w) {
            var x = this;
            if (v.type == 1 && f.$empty[v.getName()])w = v.getIndex(), v = v.getParent();
            x.startContainer = v;
            x.startOffset = w;
            if (!x.endContainer) {
                x.endContainer = v;
                x.endOffset = w;
            }
            l(x);
        }, setEnd: function (v, w) {
            var x = this;
            if (v.type == 1 && f.$empty[v.getName()])w = v.getIndex() + 1, v = v.getParent();
            x.endContainer = v;
            x.endOffset = w;
            if (!x.startContainer) {
                x.startContainer = v;
                x.startOffset = w;
            }
            l(x);
        }, setStartAfter: function (v) {
            this.setStart(v.getParent(), v.getIndex() + 1);
        }, setStartBefore: function (v) {
            this.setStart(v.getParent(), v.getIndex());
        }, setEndAfter: function (v) {
            this.setEnd(v.getParent(), v.getIndex() + 1);
        }, setEndBefore: function (v) {
            this.setEnd(v.getParent(), v.getIndex());
        }, setStartAt: function (v, w) {
            var x = this;
            switch (w) {
                case 1:
                    x.setStart(v, 0);
                    break;
                case 2:
                    if (v.type == 3)x.setStart(v, v.getLength()); else x.setStart(v, v.getChildCount());
                    break;
                case 3:
                    x.setStartBefore(v);
                    break;
                case 4:
                    x.setStartAfter(v);
            }
            l(x);
        }, setEndAt: function (v, w) {
            var x = this;
            switch (w) {
                case 1:
                    x.setEnd(v, 0);
                    break;
                case 2:
                    if (v.type == 3)x.setEnd(v, v.getLength()); else x.setEnd(v, v.getChildCount());
                    break;
                case 3:
                    x.setEndBefore(v);
                    break;
                case 4:
                    x.setEndAfter(v);
            }
            l(x);
        }, fixBlock: function (v, w) {
            var z = this;
            var x = z.createBookmark(), y = z.document.createElement(w);
            z.collapse(v);
            z.enlarge(2);
            z.extractContents().appendTo(y);
            y.trim();
            if (!c)y.appendBogus();
            z.insertNode(y);
            z.moveToBookmark(x);
            return y;
        }, splitBlock: function (v) {
            var F = this;
            var w = new d.elementPath(F.startContainer), x = new d.elementPath(F.endContainer), y = w.blockLimit, z = x.blockLimit, A = w.block, B = x.block, C = null;
            if (!y.equals(z))return null;
            if (v != 'br') {
                if (!A) {
                    A = F.fixBlock(true, v);
                    B = new d.elementPath(F.endContainer).block;
                }
                if (!B)B = F.fixBlock(false, v);
            }
            var D = A && F.checkStartOfBlock(), E = B && F.checkEndOfBlock();
            F.deleteContents();
            if (A && A.equals(B))if (E) {
                C = new d.elementPath(F.startContainer);
                F.moveToPosition(B, 4);
                B = null;
            } else if (D) {
                C = new d.elementPath(F.startContainer);
                F.moveToPosition(A, 3);
                A = null;
            } else {
                B = F.splitElement(A);
                if (!c && !A.is('ul', 'ol'))A.appendBogus();
            }
            return{previousBlock: A, nextBlock: B, wasStartOfBlock: D, wasEndOfBlock: E, elementPath: C};
        }, splitElement: function (v) {
            var y = this;
            if (!y.collapsed)return null;
            y.setEndAt(v, 2);
            var w = y.extractContents(), x = v.clone(false);
            w.appendTo(x);
            x.insertAfter(v);
            y.moveToPosition(v, 4);
            return x;
        }, checkBoundaryOfElement: function (v, w) {
            var x = w == 1, y = this.clone();
            y.collapse(x);
            y[x ? 'setStartAt' : 'setEndAt'](v, x ? 1 : 2);
            var z = new d.walker(y);
            z.evaluator = q(x);
            return z[x ? 'checkBackward' : 'checkForward']();
        }, checkStartOfBlock: function () {
            var B = this;
            var v = B.startContainer, w = B.startOffset;
            if (c && w && v.type == 3) {
                var x = e.ltrim(v.substring(0, w));
                if (t.test(x))B.trim(0, 1);
            }
            var y = new d.elementPath(B.startContainer), z = B.clone();
            z.collapse(true);
            z.setStartAt(y.block || y.blockLimit, 1);
            var A = new d.walker(z);
            A.evaluator = o();
            return A.checkBackward();
        }, checkEndOfBlock: function () {
            var B = this;
            var v = B.endContainer, w = B.endOffset;
            if (c && v.type == 3) {
                var x = e.rtrim(v.substring(w));
                if (t.test(x))B.trim(1, 0);
            }
            var y = new d.elementPath(B.endContainer), z = B.clone();
            z.collapse(false);
            z.setEndAt(y.block || y.blockLimit, 2);
            var A = new d.walker(z);
            A.evaluator = o();
            return A.checkForward();
        }, getPreviousNode: function (v, w, x) {
            var y = this.clone();
            y.collapse(1);
            y.setStartAt(x || this.document.getBody(), 1);
            var z = new d.walker(y);
            z.evaluator = v;
            z.guard = w;
            return z.previous();
        }, getNextNode: function (v, w, x) {
            var y = this.clone();
            y.collapse();
            y.setEndAt(x || this.document.getBody(), 2);
            var z = new d.walker(y);
            z.evaluator = v;
            z.guard = w;
            return z.next();
        }, checkReadOnly: (function () {
            function v(w, x) {
                while (w) {
                    if (w.type == 1)if (w.getAttribute('contentEditable') == 'false' && !w.data('cke-editable'))return 0; else if (w.is('html') || w.getAttribute('contentEditable') == 'true' && (w.contains(x) || w.equals(x)))break;
                    w = w.getParent();
                }
                return 1;
            };
            return function () {
                var w = this.startContainer, x = this.endContainer;
                return!(v(w, x) && v(x, w));
            };
        })(), moveToElementEditablePosition: function (v, w) {
            function x(z, A) {
                var B;
                if (z.type == 1 && z.isEditable(false))B = z[w ? 'getLast' : 'getFirst'](u);
                if (!A && !B)B = z[w ? 'getPrevious' : 'getNext'](u);
                return B;
            };
            if (v.type == 1 && !v.isEditable(false)) {
                this.moveToPosition(v, w ? 4 : 3);
                return true;
            }
            var y = 0;
            while (v) {
                if (v.type == 3) {
                    if (w && this.checkEndOfBlock() && t.test(v.getText()))this.moveToPosition(v, 3); else this.moveToPosition(v, w ? 4 : 3);
                    y = 1;
                    break;
                }
                if (v.type == 1)if (v.isEditable()) {
                    this.moveToPosition(v, w ? 2 : 1);
                    y = 1;
                } else if (w && v.is('br') && this.checkEndOfBlock())this.moveToPosition(v, 3);
                v = x(v, y);
            }
            return!!y;
        }, moveToElementEditStart: function (v) {
            return this.moveToElementEditablePosition(v);
        }, moveToElementEditEnd: function (v) {
            return this.moveToElementEditablePosition(v, true);
        }, getEnclosedNode: function () {
            var v = this.clone();
            v.optimize();
            if (v.startContainer.type != 1 || v.endContainer.type != 1)return null;
            var w = new d.walker(v), x = d.walker.bookmark(true), y = d.walker.whitespaces(true), z = function (B) {
                return y(B) && x(B);
            };
            v.evaluator = z;
            var A = w.next();
            w.reset();
            return A && A.equals(w.previous()) ? A : null;
        }, getTouchedStartNode: function () {
            var v = this.startContainer;
            if (this.collapsed || v.type != 1)return v;
            return v.getChild(this.startOffset) || v;
        }, getTouchedEndNode: function () {
            var v = this.endContainer;
            if (this.collapsed || v.type != 1)return v;
            return v.getChild(this.endOffset - 1) || v;
        }};
    })();
    a.POSITION_AFTER_START = 1;
    a.POSITION_BEFORE_END = 2;
    a.POSITION_BEFORE_START = 3;
    a.POSITION_AFTER_END = 4;
    a.ENLARGE_ELEMENT = 1;
    a.ENLARGE_BLOCK_CONTENTS = 2;
    a.ENLARGE_LIST_ITEM_CONTENTS = 3;
    a.START = 1;
    a.END = 2;
    a.STARTEND = 3;
    a.SHRINK_ELEMENT = 1;
    a.SHRINK_TEXT = 2;
    (function () {
        d.rangeList = function (n) {
            if (n instanceof d.rangeList)return n;
            if (!n)n = []; else if (n instanceof d.range)n = [n];
            return e.extend(n, l);
        };
        var l = {createIterator: function () {
            var n = this, o = d.walker.bookmark(), p = function (s) {
                return!(s.is && s.is('tr'));
            }, q = [], r;
            return{getNextRange: function (s) {
                r = r == undefined ? 0 : r + 1;
                var t = n[r];
                if (t && n.length > 1) {
                    if (!r)for (var u = n.length - 1; u >= 0; u--)q.unshift(n[u].createBookmark(true));
                    if (s) {
                        var v = 0;
                        while (n[r + v + 1]) {
                            var w = t.document, x = 0, y = w.getById(q[v].endNode), z = w.getById(q[v + 1].startNode), A;
                            while (1) {
                                A = y.getNextSourceNode(false);
                                if (!z.equals(A)) {
                                    if (o(A) || A.type == 1 && A.isBlockBoundary()) {
                                        y = A;
                                        continue;
                                    }
                                } else x = 1;
                                break;
                            }
                            if (!x)break;
                            v++;
                        }
                    }
                    t.moveToBookmark(q.shift());
                    while (v--) {
                        A = n[++r];
                        A.moveToBookmark(q.shift());
                        t.setEnd(A.endContainer, A.endOffset);
                    }
                }
                return t;
            }};
        }, createBookmarks: function (n) {
            var s = this;
            var o = [], p;
            for (var q = 0; q < s.length; q++) {
                o.push(p = s[q].createBookmark(n, true));
                for (var r = q + 1; r < s.length; r++) {
                    s[r] = m(p, s[r]);
                    s[r] = m(p, s[r], true);
                }
            }
            return o;
        }, createBookmarks2: function (n) {
            var o = [];
            for (var p = 0; p < this.length; p++)o.push(this[p].createBookmark2(n));
            return o;
        }, moveToBookmarks: function (n) {
            for (var o = 0; o < this.length; o++)this[o].moveToBookmark(n[o]);
        }};

        function m(n, o, p) {
            var q = n.serializable, r = o[p ? 'endContainer' : 'startContainer'], s = p ? 'endOffset' : 'startOffset', t = q ? o.document.getById(n.startNode) : n.startNode, u = q ? o.document.getById(n.endNode) : n.endNode;
            if (r.equals(t.getPrevious())) {
                o.startOffset = o.startOffset - r.getLength() - u.getPrevious().getLength();
                r = u.getNext();
            } else if (r.equals(u.getPrevious())) {
                o.startOffset = o.startOffset - r.getLength();
                r = u.getNext();
            }
            r.equals(t.getParent()) && o[s]++;
            r.equals(u.getParent()) && o[s]++;
            o[p ? 'endContainer' : 'startContainer'] = r;
            return o;
        };
    })();
    (function () {
        if (b.webkit) {
            b.hc = false;
            return;
        }
        var l = h.createFromHtml('<div style="width:0px;height:0px;position:absolute;left:-10000px;border: 1px solid;border-color: red blue;"></div>', a.document);
        l.appendTo(a.document.getHead());
        try {
            b.hc = l.getComputedStyle('border-top-color') == l.getComputedStyle('border-right-color');
        } catch (m) {
            b.hc = false;
        }
        if (b.hc)b.cssClass += ' cke_hc';
        l.remove();
    })();
    j.load(i.corePlugins.split(','), function () {
        a.status = 'loaded';
        a.fire('loaded');
        var l = a._.pending;
        if (l) {
            delete a._.pending;
            for (var m = 0; m < l.length; m++)a.add(l[m]);
        }
    });
    if (c)try {
        document.execCommand('BackgroundImageCache', false, true);
    } catch (l) {
    }
    a.skins.add('kama', (function () {
        var m = 'cke_ui_color';
        return{editor: {css: ['editor.css']}, dialog: {css: ['dialog.css']}, richcombo: {canGroup: false}, templates: {css: ['templates.css']}, margins: [0, 0, 0, 0], init: function (n) {
            if (n.config.width && !isNaN(n.config.width))n.config.width -= 12;
            var o = [], p = /\$color/g, q = '/* UI Color Support */.cke_skin_kama .cke_menuitem .cke_icon_wrapper{\tbackground-color: $color !important;\tborder-color: $color !important;}.cke_skin_kama .cke_menuitem a:hover .cke_icon_wrapper,.cke_skin_kama .cke_menuitem a:focus .cke_icon_wrapper,.cke_skin_kama .cke_menuitem a:active .cke_icon_wrapper{\tbackground-color: $color !important;\tborder-color: $color !important;}.cke_skin_kama .cke_menuitem a:hover .cke_label,.cke_skin_kama .cke_menuitem a:focus .cke_label,.cke_skin_kama .cke_menuitem a:active .cke_label{\tbackground-color: $color !important;}.cke_skin_kama .cke_menuitem a.cke_disabled:hover .cke_label,.cke_skin_kama .cke_menuitem a.cke_disabled:focus .cke_label,.cke_skin_kama .cke_menuitem a.cke_disabled:active .cke_label{\tbackground-color: transparent !important;}.cke_skin_kama .cke_menuitem a.cke_disabled:hover .cke_icon_wrapper,.cke_skin_kama .cke_menuitem a.cke_disabled:focus .cke_icon_wrapper,.cke_skin_kama .cke_menuitem a.cke_disabled:active .cke_icon_wrapper{\tbackground-color: $color !important;\tborder-color: $color !important;}.cke_skin_kama .cke_menuitem a.cke_disabled .cke_icon_wrapper{\tbackground-color: $color !important;\tborder-color: $color !important;}.cke_skin_kama .cke_menuseparator{\tbackground-color: $color !important;}.cke_skin_kama .cke_menuitem a:hover,.cke_skin_kama .cke_menuitem a:focus,.cke_skin_kama .cke_menuitem a:active{\tbackground-color: $color !important;}';
            if (b.webkit) {
                q = q.split('}').slice(0, -1);
                for (var r = 0; r < q.length; r++)q[r] = q[r].split('{');
            }
            function s(v) {
                var w = v.getById(m);
                if (!w) {
                    w = v.getHead().append('style');
                    w.setAttribute('id', m);
                    w.setAttribute('type', 'text/css');
                }
                return w;
            };
            function t(v, w, x) {
                var y, z, A;
                for (var B = 0; B < v.length; B++) {
                    if (b.webkit)for (z = 0; z < w.length; z++) {
                        A = w[z][1];
                        for (y = 0; y < x.length; y++)A = A.replace(x[y][0], x[y][1]);
                        v[B].$.sheet.addRule(w[z][0], A);
                    } else {
                        A = w;
                        for (y = 0; y < x.length; y++)A = A.replace(x[y][0], x[y][1]);
                        if (c)v[B].$.styleSheet.cssText += A; else v[B].$.innerHTML += A;
                    }
                }
            };
            var u = /\$color/g;
            e.extend(n, {uiColor: null, getUiColor: function () {
                return this.uiColor;
            }, setUiColor: function (v) {
                var w, x = s(a.document), y = '.' + n.id, z = [y + ' .cke_wrapper', y + '_dialog .cke_dialog_contents', y + '_dialog a.cke_dialog_tab', y + '_dialog .cke_dialog_footer'].join(','), A = 'background-color: $color !important;';
                if (b.webkit)w = [
                    [z, A]
                ]; else w = z + '{' + A + '}';
                return(this.setUiColor = function (B) {
                    var C = [
                        [u, B]
                    ];
                    n.uiColor = B;
                    t([x], w, C);
                    t(o, q, C);
                })(v);
            }});
            n.on('menuShow', function (v) {
                var w = v.data[0], x = w.element.getElementsByTag('iframe').getItem(0).getFrameDocument();
                if (!x.getById('cke_ui_color')) {
                    var y = s(x);
                    o.push(y);
                    var z = n.getUiColor();
                    if (z)t([y], q, [
                        [u, z]
                    ]);
                }
            });
            if (n.config.uiColor)n.setUiColor(n.config.uiColor);
        }};
    })());
    (function () {
        a.dialog ? m() : a.on('dialogPluginReady', m);
        function m() {
            a.dialog.on('resize', function (n) {
                var o = n.data, p = o.width, q = o.height, r = o.dialog, s = r.parts.contents;
                if (o.skin != 'kama')return;
                s.setStyles({width: p + 'px', height: q + 'px'});
            });
        };
    })();
    j.add('about', {requires: ['dialog'], init: function (m) {
        var n = m.addCommand('about', new a.dialogCommand('about'));
        n.modes = {wysiwyg: 1, source: 1};
        n.canUndo = false;
        n.readOnly = 1;
        m.ui.addButton('About', {label: m.lang.about.title, command: 'about'});
        a.dialog.add('about', this.path + 'dialogs/about.js');
    }});
    (function () {
        var m = 'a11yhelp', n = 'a11yHelp';
        j.add(m, {requires: ['dialog'], availableLangs: {cs: 1, cy: 1, da: 1, de: 1, el: 1, en: 1, eo: 1, fa: 1, fi: 1, fr: 1, gu: 1, he: 1, it: 1, ku: 1, mk: 1, nb: 1, nl: 1, no: 1, 'pt-br': 1, ro: 1, tr: 1, ug: 1, vi: 1, 'zh-cn': 1}, init: function (o) {
            var p = this;
            o.addCommand(n, {exec: function () {
                var q = o.langCode;
                q = p.availableLangs[q] ? q : 'en';
                a.scriptLoader.load(a.getUrl(p.path + 'lang/' + q + '.js'), function () {
                    e.extend(o.lang, p.langEntries[q]);
                    o.openDialog(n);
                });
            }, modes: {wysiwyg: 1, source: 1}, readOnly: 1, canUndo: false});
            a.dialog.add(n, this.path + 'dialogs/a11yhelp.js');
        }});
    })();
    j.add('basicstyles', {requires: ['styles', 'button'], init: function (m) {
        var n = function (q, r, s, t) {
            var u = new a.style(t);
            m.attachStyleStateChange(u, function (v) {
                !m.readOnly && m.getCommand(s).setState(v);
            });
            m.addCommand(s, new a.styleCommand(u));
            m.ui.addButton(q, {label: r, command: s});
        }, o = m.config, p = m.lang;
        n('Bold', p.bold, 'bold', o.coreStyles_bold);
        n('Italic', p.italic, 'italic', o.coreStyles_italic);
        n('Underline', p.underline, 'underline', o.coreStyles_underline);
        n('Strike', p.strike, 'strike', o.coreStyles_strike);
        n('Subscript', p.subscript, 'subscript', o.coreStyles_subscript);
        n('Superscript', p.superscript, 'superscript', o.coreStyles_superscript);
    }});
    i.coreStyles_bold = {element: 'strong', overrides: 'b'};
    i.coreStyles_italic = {element: 'em', overrides: 'i'};
    i.coreStyles_underline = {element: 'u'};
    i.coreStyles_strike = {element: 'strike'};
    i.coreStyles_subscript = {element: 'sub'};
    i.coreStyles_superscript = {element: 'sup'};
    (function () {
        var m = {table: 1, ul: 1, ol: 1, blockquote: 1, div: 1}, n = {}, o = {};
        e.extend(n, m, {tr: 1, p: 1, div: 1, li: 1});
        e.extend(o, n, {td: 1});
        function p(B) {
            q(B);
            r(B);
        };
        function q(B) {
            var C = B.editor, D = B.data.path;
            if (C.readOnly)return;
            var E = C.config.useComputedState, F;
            E = E === undefined || E;
            if (!E)F = s(D.lastElement);
            F = F || D.block || D.blockLimit;
            if (F.is('body')) {
                var G = C.getSelection().getRanges()[0].getEnclosedNode();
                G && G.type == 1 && (F = G);
            }
            if (!F)return;
            var H = E ? F.getComputedStyle('direction') : F.getStyle('direction') || F.getAttribute('dir');
            C.getCommand('bidirtl').setState(H == 'rtl' ? 1 : 2);
            C.getCommand('bidiltr').setState(H == 'ltr' ? 1 : 2);
        };
        function r(B) {
            var C = B.editor, D = B.data.path.block || B.data.path.blockLimit;
            C.fire('contentDirChanged', D ? D.getComputedStyle('direction') : C.lang.dir);
        };
        function s(B) {
            while (B && !(B.getName() in o || B.is('body'))) {
                var C = B.getParent();
                if (!C)break;
                B = C;
            }
            return B;
        };
        function t(B, C, D, E) {
            if (B.isReadOnly())return;
            h.setMarker(E, B, 'bidi_processed', 1);
            var F = B;
            while ((F = F.getParent()) && !F.is('body')) {
                if (F.getCustomData('bidi_processed')) {
                    B.removeStyle('direction');
                    B.removeAttribute('dir');
                    return;
                }
            }
            var G = 'useComputedState' in D.config ? D.config.useComputedState : 1, H = G ? B.getComputedStyle('direction') : B.getStyle('direction') || B.hasAttribute('dir');
            if (H == C)return;
            B.removeStyle('direction');
            if (G) {
                B.removeAttribute('dir');
                if (C != B.getComputedStyle('direction'))B.setAttribute('dir', C);
            } else B.setAttribute('dir', C);
            D.forceNextSelectionCheck();
        };
        function u(B, C, D) {
            var E = B.getCommonAncestor(false, true);
            B = B.clone();
            B.enlarge(D == 2 ? 3 : 2);
            if (B.checkBoundaryOfElement(E, 1) && B.checkBoundaryOfElement(E, 2)) {
                var F;
                while (E && E.type == 1 && (F = E.getParent()) && F.getChildCount() == 1 && !(E.getName() in C))E = F;
                return E.type == 1 && E.getName() in C && E;
            }
        };
        function v(B) {
            return function (C) {
                var D = C.getSelection(), E = C.config.enterMode, F = D.getRanges();
                if (F && F.length) {
                    var G = {}, H = D.createBookmarks(), I = F.createIterator(), J, K = 0;
                    while (J = I.getNextRange(1)) {
                        var L = J.getEnclosedNode();
                        if (!L || L && !(L.type == 1 && L.getName() in n))L = u(J, m, E);
                        L && t(L, B, C, G);
                        var M, N, O = new d.walker(J), P = H[K].startNode, Q = H[K++].endNode;
                        O.evaluator = function (R) {
                            return!!(R.type == 1 && R.getName() in m && !(R.getName() == (E == 1 ? 'p' : 'div') && R.getParent().type == 1 && R.getParent().getName() == 'blockquote') && R.getPosition(P) & 2 && (R.getPosition(Q) & 4 + 16) == 4);
                        };
                        while (N = O.next())t(N, B, C, G);
                        M = J.createIterator();
                        M.enlargeBr = E != 2;
                        while (N = M.getNextParagraph(E == 1 ? 'p' : 'div'))t(N, B, C, G);
                    }
                    h.clearAllMarkers(G);
                    C.forceNextSelectionCheck();
                    D.selectBookmarks(H);
                    C.focus();
                }
            };
        };
        j.add('bidi', {requires: ['styles', 'button'], init: function (B) {
            var C = function (E, F, G, H) {
                B.addCommand(G, new a.command(B, {exec: H}));
                B.ui.addButton(E, {label: F, command: G});
            }, D = B.lang.bidi;
            C('BidiLtr', D.ltr, 'bidiltr', v('ltr'));
            C('BidiRtl', D.rtl, 'bidirtl', v('rtl'));
            B.on('selectionChange', p);
            B.on('contentDom', function () {
                B.document.on('dirChanged', function (E) {
                    B.fire('dirChanged', {node: E.data, dir: E.data.getDirection(1)});
                });
            });
        }});
        function w(B) {
            var C = B.getDocument().getBody().getParent();
            while (B) {
                if (B.equals(C))return false;
                B = B.getParent();
            }
            return true;
        };
        function x(B) {
            var C = B == y.setAttribute, D = B == y.removeAttribute, E = /\bdirection\s*:\s*(.*?)\s*(:?$|;)/;
            return function (F, G) {
                var J = this;
                if (!J.getDocument().equals(a.document)) {
                    var H;
                    if ((F == (C || D ? 'dir' : 'direction') || F == 'style' && (D || E.test(G))) && !w(J)) {
                        H = J.getDirection(1);
                        var I = B.apply(J, arguments);
                        if (H != J.getDirection(1)) {
                            J.getDocument().fire('dirChanged', J);
                            return I;
                        }
                    }
                }
                return B.apply(J, arguments);
            };
        };
        var y = h.prototype, z = ['setStyle', 'removeStyle', 'setAttribute', 'removeAttribute'];
        for (var A = 0; A < z.length; A++)y[z[A]] = e.override(y[z[A]], x);
    })();
    (function () {
        function m(q, r) {
            var s = r.block || r.blockLimit;
            if (!s || s.getName() == 'body')return 2;
            if (s.getAscendant('blockquote', true))return 1;
            return 2;
        };
        function n(q) {
            var r = q.editor;
            if (r.readOnly)return;
            var s = r.getCommand('blockquote');
            s.state = m(r, q.data.path);
            s.fire('state');
        };
        function o(q) {
            for (var r = 0, s = q.getChildCount(), t; r < s && (t = q.getChild(r)); r++) {
                if (t.type == 1 && t.isBlockBoundary())return false;
            }
            return true;
        };
        var p = {exec: function (q) {
            var r = q.getCommand('blockquote').state, s = q.getSelection(), t = s && s.getRanges(true)[0];
            if (!t)return;
            var u = s.createBookmarks();
            if (c) {
                var v = u[0].startNode, w = u[0].endNode, x;
                if (v && v.getParent().getName() == 'blockquote') {
                    x = v;
                    while (x = x.getNext()) {
                        if (x.type == 1 && x.isBlockBoundary()) {
                            v.move(x, true);
                            break;
                        }
                    }
                }
                if (w && w.getParent().getName() == 'blockquote') {
                    x = w;
                    while (x = x.getPrevious()) {
                        if (x.type == 1 && x.isBlockBoundary()) {
                            w.move(x);
                            break;
                        }
                    }
                }
            }
            var y = t.createIterator(), z;
            y.enlargeBr = q.config.enterMode != 2;
            if (r == 2) {
                var A = [];
                while (z = y.getNextParagraph())A.push(z);
                if (A.length < 1) {
                    var B = q.document.createElement(q.config.enterMode == 1 ? 'p' : 'div'), C = u.shift();
                    t.insertNode(B);
                    B.append(new d.text('\ufeff', q.document));
                    t.moveToBookmark(C);
                    t.selectNodeContents(B);
                    t.collapse(true);
                    C = t.createBookmark();
                    A.push(B);
                    u.unshift(C);
                }
                var D = A[0].getParent(), E = [];
                for (var F = 0; F < A.length; F++) {
                    z = A[F];
                    D = D.getCommonAncestor(z.getParent());
                }
                var G = {table: 1, tbody: 1, tr: 1, ol: 1, ul: 1};
                while (G[D.getName()])D = D.getParent();
                var H = null;
                while (A.length > 0) {
                    z = A.shift();
                    while (!z.getParent().equals(D))z = z.getParent();
                    if (!z.equals(H))E.push(z);
                    H = z;
                }
                while (E.length > 0) {
                    z = E.shift();
                    if (z.getName() == 'blockquote') {
                        var I = new d.documentFragment(q.document);
                        while (z.getFirst()) {
                            I.append(z.getFirst().remove());
                            A.push(I.getLast());
                        }
                        I.replace(z);
                    } else A.push(z);
                }
                var J = q.document.createElement('blockquote');
                J.insertBefore(A[0]);
                while (A.length > 0) {
                    z = A.shift();
                    J.append(z);
                }
            } else if (r == 1) {
                var K = [], L = {};
                while (z = y.getNextParagraph()) {
                    var M = null, N = null;
                    while (z.getParent()) {
                        if (z.getParent().getName() == 'blockquote') {
                            M = z.getParent();
                            N = z;
                            break;
                        }
                        z = z.getParent();
                    }
                    if (M && N && !N.getCustomData('blockquote_moveout')) {
                        K.push(N);
                        h.setMarker(L, N, 'blockquote_moveout', true);
                    }
                }
                h.clearAllMarkers(L);
                var O = [], P = [];
                L = {};
                while (K.length > 0) {
                    var Q = K.shift();
                    J = Q.getParent();
                    if (!Q.getPrevious())Q.remove().insertBefore(J); else if (!Q.getNext())Q.remove().insertAfter(J); else {
                        Q.breakParent(Q.getParent());
                        P.push(Q.getNext());
                    }
                    if (!J.getCustomData('blockquote_processed')) {
                        P.push(J);
                        h.setMarker(L, J, 'blockquote_processed', true);
                    }
                    O.push(Q);
                }
                h.clearAllMarkers(L);
                for (F = P.length - 1; F >= 0; F--) {
                    J = P[F];
                    if (o(J))J.remove();
                }
                if (q.config.enterMode == 2) {
                    var R = true;
                    while (O.length) {
                        Q = O.shift();
                        if (Q.getName() == 'div') {
                            I = new d.documentFragment(q.document);
                            var S = R && Q.getPrevious() && !(Q.getPrevious().type == 1 && Q.getPrevious().isBlockBoundary());
                            if (S)I.append(q.document.createElement('br'));
                            var T = Q.getNext() && !(Q.getNext().type == 1 && Q.getNext().isBlockBoundary());
                            while (Q.getFirst())Q.getFirst().remove().appendTo(I);
                            if (T)I.append(q.document.createElement('br'));
                            I.replace(Q);
                            R = false;
                        }
                    }
                }
            }
            s.selectBookmarks(u);
            q.focus();
        }};
        j.add('blockquote', {init: function (q) {
            q.addCommand('blockquote', p);
            q.ui.addButton('Blockquote', {label: q.lang.blockquote, command: 'blockquote'});
            q.on('selectionChange', n);
        }, requires: ['domiterator']});
    })();
    j.add('button', {beforeInit: function (m) {
        m.ui.addHandler('button', k.button.handler);
    }});
    a.UI_BUTTON = 'button';
    k.button = function (m) {
        e.extend(this, m, {title: m.label, className: m.className || m.command && 'cke_button_' + m.command || '', click: m.click || (function (n) {
            n.execCommand(m.command);
        })});
        this._ = {};
    };
    k.button.handler = {create: function (m) {
        return new k.button(m);
    }};
    (function () {
        k.button.prototype = {render: function (m, n) {
            var o = b, p = this._.id = e.getNextId(), q = '', r = this.command, s;
            this._.editor = m;
            var t = {id: p, button: this, editor: m, focus: function () {
                var z = a.document.getById(p);
                z.focus();
            }, execute: function () {
                if (c && b.version < 7)e.setTimeout(function () {
                    this.button.click(m);
                }, 0, this); else this.button.click(m);
            }}, u = e.addFunction(function (z) {
                if (t.onkey) {
                    z = new d.event(z);
                    return t.onkey(t, z.getKeystroke()) !== false;
                }
            }), v = e.addFunction(function (z) {
                var A;
                if (t.onfocus)A = t.onfocus(t, new d.event(z)) !== false;
                if (b.gecko && b.version < 10900)z.preventBubble();
                return A;
            });
            t.clickFn = s = e.addFunction(t.execute, t);
            if (this.modes) {
                var w = {};

                function x() {
                    var z = m.mode;
                    if (z) {
                        var A = this.modes[z] ? w[z] != undefined ? w[z] : 2 : 0;
                        this.setState(m.readOnly && !this.readOnly ? 0 : A);
                    }
                };
                m.on('beforeModeUnload', function () {
                    if (m.mode && this._.state != 0)w[m.mode] = this._.state;
                }, this);
                m.on('mode', x, this);
                !this.readOnly && m.on('readOnly', x, this);
            } else if (r) {
                r = m.getCommand(r);
                if (r) {
                    r.on('state', function () {
                        this.setState(r.state);
                    }, this);
                    q += 'cke_' + (r.state == 1 ? 'on' : r.state == 0 ? 'disabled' : 'off');
                }
            }
            if (!r)q += 'cke_off';
            if (this.className)q += ' ' + this.className;
            n.push('<span class="cke_button' + (this.icon && this.icon.indexOf('.png') == -1 ? ' cke_noalphafix' : '') + '">', '<a id="', p, '" class="', q, '"', o.gecko && o.version >= 10900 && !o.hc ? '' : '" href="javascript:void(\'' + (this.title || '').replace("'", '') + "')\"", ' title="', this.title, '" tabindex="-1" hidefocus="true" role="button" aria-labelledby="' + p + '_label"' + (this.hasArrow ? ' aria-haspopup="true"' : ''));
            if (o.opera || o.gecko && o.mac)n.push(' onkeypress="return false;"');
            if (o.gecko)n.push(' onblur="this.style.cssText = this.style.cssText;"');
            n.push(' onkeydown="return CKEDITOR.tools.callFunction(', u, ', event);" onfocus="return CKEDITOR.tools.callFunction(', v, ', event);" ' + (c ? 'onclick="return false;" onmouseup' : 'onclick') + '="CKEDITOR.tools.callFunction(', s, ', this); return false;"><span class="cke_icon"');
            if (this.icon) {
                var y = (this.iconOffset || 0) * -16;
                n.push(' style="background-image:url(', a.getUrl(this.icon), ');background-position:0 ' + y + 'px;"');
            }
            n.push('>&nbsp;</span><span id="', p, '_label" class="cke_label">', this.label, '</span>');
            if (this.hasArrow)n.push('<span class="cke_buttonarrow">' + (b.hc ? '&#9660;' : '&nbsp;') + '</span>');
            n.push('</a>', '</span>');
            if (this.onRender)this.onRender();
            return t;
        }, setState: function (m) {
            if (this._.state == m)return false;
            this._.state = m;
            var n = a.document.getById(this._.id);
            if (n) {
                n.setState(m);
                m == 0 ? n.setAttribute('aria-disabled', true) : n.removeAttribute('aria-disabled');
                m == 1 ? n.setAttribute('aria-pressed', true) : n.removeAttribute('aria-pressed');
                return true;
            } else return false;
        }};
    })();
    k.prototype.addButton = function (m, n) {
        this.add(m, 'button', n);
    };
    (function () {
        var m = function (y, z) {
            var A = y.document, B = A.getBody(), C = false, D = function () {
                C = true;
            };
            B.on(z, D);
            (b.version > 7 ? A.$ : A.$.selection.createRange()).execCommand(z);
            B.removeListener(z, D);
            return C;
        }, n = c ? function (y, z) {
            return m(y, z);
        } : function (y, z) {
            try {
                return y.document.$.execCommand(z, false, null);
            } catch (A) {
                return false;
            }
        }, o = function (y) {
            var z = this;
            z.type = y;
            z.canUndo = z.type == 'cut';
            z.startDisabled = true;
        };
        o.prototype = {exec: function (y, z) {
            this.type == 'cut' && t(y);
            var A = n(y, this.type);
            if (!A)alert(y.lang.clipboard[this.type + 'Error']);
            return A;
        }};
        var p = {canUndo: false, exec: c ? function (y) {
            y.focus();
            if (!y.document.getBody().fire('beforepaste') && !m(y, 'paste')) {
                y.fire('pasteDialog');
                return false;
            }
        } : function (y) {
            try {
                if (!y.document.getBody().fire('beforepaste') && !y.document.$.execCommand('Paste', false, null))throw 0;
            } catch (z) {
                setTimeout(function () {
                    y.fire('pasteDialog');
                }, 0);
                return false;
            }
        }}, q = function (y) {
            if (this.mode != 'wysiwyg')return;
            switch (y.data.keyCode) {
                case 1114112 + 86:
                case 2228224 + 45:
                    var z = this.document.getBody();
                    if (b.opera || b.gecko)z.fire('paste');
                    return;
                case 1114112 + 88:
                case 2228224 + 46:
                    var A = this;
                    this.fire('saveSnapshot');
                    setTimeout(function () {
                        A.fire('saveSnapshot');
                    }, 0);
            }
        };

        function r(y) {
            y.cancel();
        };
        function s(y, z, A) {
            var B = this.document;
            if (B.getById('cke_pastebin'))return;
            if (z == 'text' && y.data && y.data.$.clipboardData) {
                var C = y.data.$.clipboardData.getData('text/plain');
                if (C) {
                    y.data.preventDefault();
                    A(C);
                    return;
                }
            }
            var D = this.getSelection(), E = new d.range(B), F = new h(z == 'text' ? 'textarea' : b.webkit ? 'body' : 'div', B);
            F.setAttribute('id', 'cke_pastebin');
            b.webkit && F.append(B.createText('\xa0'));
            B.getBody().append(F);
            F.setStyles({position: 'absolute', top: D.getStartElement().getDocumentPosition().y + 'px', width: '1px', height: '1px', overflow: 'hidden'});
            F.setStyle(this.config.contentsLangDirection == 'ltr' ? 'left' : 'right', '-1000px');
            var G = D.createBookmarks();
            this.on('selectionChange', r, null, null, 0);
            if (z == 'text')F.$.focus(); else {
                E.setStartAt(F, 1);
                E.setEndAt(F, 2);
                E.select(true);
            }
            var H = this;
            window.setTimeout(function () {
                H.document.getBody().focus();
                H.removeListener('selectionChange', r);
                if (b.ie7Compat) {
                    D.selectBookmarks(G);
                    F.remove();
                } else {
                    F.remove();
                    D.selectBookmarks(G);
                }
                var I;
                F = b.webkit && (I = F.getFirst()) && I.is && I.hasClass('Apple-style-span') ? I : F;
                A(F['get' + (z == 'text' ? 'Value' : 'Html')]());
            }, 0);
        };
        function t(y) {
            if (!c || b.quirks)return;
            var z = y.getSelection(), A;
            if (z.getType() == 3 && (A = z.getSelectedElement())) {
                var B = z.getRanges()[0], C = y.document.createText('');
                C.insertBefore(A);
                B.setStartBefore(C);
                B.setEndAfter(A);
                z.selectRanges([B]);
                setTimeout(function () {
                    if (A.getParent()) {
                        C.remove();
                        z.selectElement(A);
                    }
                }, 0);
            }
        };
        var u, v;

        function w(y, z) {
            var A;
            if (v && y in {Paste: 1, Cut: 1})return 0;
            if (y == 'Paste') {
                c && (u = 1);
                try {
                    A = z.document.$.queryCommandEnabled(y) || b.webkit;
                } catch (D) {
                }
                u = 0;
            } else {
                var B = z.getSelection(), C = B && B.getRanges();
                A = B && !(C.length == 1 && C[0].collapsed);
            }
            return A ? 2 : 0;
        };
        function x() {
            var z = this;
            if (z.mode != 'wysiwyg')return;
            var y = w('Paste', z);
            z.getCommand('cut').setState(w('Cut', z));
            z.getCommand('copy').setState(w('Copy', z));
            z.getCommand('paste').setState(y);
            z.fire('pasteState', y);
        };
        j.add('clipboard', {requires: ['dialog', 'htmldataprocessor'], init: function (y) {
            y.on('paste', function (A) {
                var B = A.data;
                if (B.html)y.insertHtml(B.html); else if (B.text)y.insertText(B.text);
                setTimeout(function () {
                    y.fire('afterPaste');
                }, 0);
            }, null, null, 1000);
            y.on('pasteDialog', function (A) {
                setTimeout(function () {
                    y.openDialog('paste');
                }, 0);
            });
            y.on('pasteState', function (A) {
                y.getCommand('paste').setState(A.data);
            });
            function z(A, B, C, D) {
                var E = y.lang[B];
                y.addCommand(B, C);
                y.ui.addButton(A, {label: E, command: B});
                if (y.addMenuItems)y.addMenuItem(B, {label: E, command: B, group: 'clipboard', order: D});
            };
            z('Cut', 'cut', new o('cut'), 1);
            z('Copy', 'copy', new o('copy'), 4);
            z('Paste', 'paste', p, 8);
            a.dialog.add('paste', a.getUrl(this.path + 'dialogs/paste.js'));
            y.on('key', q, y);
            y.on('contentDom', function () {
                var A = y.document.getBody();
                A.on(!c ? 'paste' : 'beforepaste', function (B) {
                    if (u)return;
                    var C = B.data && B.data.$;
                    if (c && C && !C.ctrlKey)return;
                    var D = {mode: 'html'};
                    y.fire('beforePaste', D);
                    s.call(y, B, D.mode, function (E) {
                        if (!(E = e.trim(E.replace(/<span[^>]+data-cke-bookmark[^<]*?<\/span>/ig, ''))))return;
                        var F = {};
                        F[D.mode] = E;
                        y.fire('paste', F);
                    });
                });
                if (c) {
                    A.on('contextmenu', function () {
                        u = 1;
                        setTimeout(function () {
                            u = 0;
                        }, 0);
                    });
                    A.on('paste', function (B) {
                        if (!y.document.getById('cke_pastebin')) {
                            B.data.preventDefault();
                            u = 0;
                            p.exec(y);
                        }
                    });
                }
                A.on('beforecut', function () {
                    !u && t(y);
                });
                A.on('mouseup', function () {
                    setTimeout(function () {
                        x.call(y);
                    }, 0);
                }, y);
                A.on('keyup', x, y);
            });
            y.on('selectionChange', function (A) {
                v = A.data.selection.getRanges()[0].checkReadOnly();
                x.call(y);
            });
            if (y.contextMenu)y.contextMenu.addListener(function (A, B) {
                var C = B.getRanges()[0].checkReadOnly();
                return{cut: w('Cut', y), copy: w('Copy', y), paste: w('Paste', y)};
            });
        }});
    })();
    j.add('colorbutton', {requires: ['panelbutton', 'floatpanel', 'styles'], init: function (m) {
        var n = m.config, o = m.lang.colorButton, p;
        if (!b.hc) {
            q('TextColor', 'fore', o.textColorTitle);
            q('BGColor', 'back', o.bgColorTitle);
        }
        function q(t, u, v) {
            var w = e.getNextId() + '_colorBox';
            m.ui.add(t, 'panelbutton', {label: v, title: v, className: 'cke_button_' + t.toLowerCase(), modes: {wysiwyg: 1}, panel: {css: m.skin.editor.css, attributes: {role: 'listbox', 'aria-label': o.panelTitle}}, onBlock: function (x, y) {
                y.autoSize = true;
                y.element.addClass('cke_colorblock');
                y.element.setHtml(r(x, u, w));
                y.element.getDocument().getBody().setStyle('overflow', 'hidden');
                k.fire('ready', this);
                var z = y.keys, A = m.lang.dir == 'rtl';
                z[A ? 37 : 39] = 'next';
                z[40] = 'next';
                z[9] = 'next';
                z[A ? 39 : 37] = 'prev';
                z[38] = 'prev';
                z[2228224 + 9] = 'prev';
                z[32] = 'click';
            }, onOpen: function () {
                var x = m.getSelection(), y = x && x.getStartElement(), z = new d.elementPath(y), A;
                y = z.block || z.blockLimit || m.document.getBody();
                do A = y && y.getComputedStyle(u == 'back' ? 'background-color' : 'color') || 'transparent'; while (u == 'back' && A == 'transparent' && y && (y = y.getParent()));
                if (!A || A == 'transparent')A = '#ffffff';
                this._.panel._.iframe.getFrameDocument().getById(w).setStyle('background-color', A);
            }});
        };
        function r(t, u, v) {
            var w = [], x = n.colorButton_colors.split(','), y = e.addFunction(function (E, F) {
                if (E == '?') {
                    var G = arguments.callee;

                    function H(J) {
                        this.removeListener('ok', H);
                        this.removeListener('cancel', H);
                        J.name == 'ok' && G(this.getContentElement('picker', 'selectedColor').getValue(), F);
                    };
                    m.openDialog('colordialog', function () {
                        this.on('ok', H);
                        this.on('cancel', H);
                    });
                    return;
                }
                m.focus();
                t.hide(false);
                m.fire('saveSnapshot');
                new a.style(n['colorButton_' + F + 'Style'], {color: 'inherit'}).remove(m.document);
                if (E) {
                    var I = n['colorButton_' + F + 'Style'];
                    I.childRule = F == 'back' ? function (J) {
                        return s(J);
                    } : function (J) {
                        return!(J.is('a') || J.getElementsByTag('a').count()) || s(J);
                    };
                    new a.style(I, {color: E}).apply(m.document);
                }
                m.fire('saveSnapshot');
            });
            w.push('<a class="cke_colorauto" _cke_focus=1 hidefocus=true title="', o.auto, '" onclick="CKEDITOR.tools.callFunction(', y, ",null,'", u, "');return false;\" href=\"javascript:void('", o.auto, '\')" role="option"><table role="presentation" cellspacing=0 cellpadding=0 width="100%"><tr><td><span class="cke_colorbox" id="', v, '"></span></td><td colspan=7 align=center>', o.auto, '</td></tr></table></a><table role="presentation" cellspacing=0 cellpadding=0 width="100%">');
            for (var z = 0; z < x.length; z++) {
                if (z % 8 === 0)w.push('</tr><tr>');
                var A = x[z].split('/'), B = A[0], C = A[1] || B;
                if (!A[1])B = '#' + B.replace(/^(.)(.)(.)$/, '$1$1$2$2$3$3');
                var D = m.lang.colors[C] || C;
                w.push('<td><a class="cke_colorbox" _cke_focus=1 hidefocus=true title="', D, '" onclick="CKEDITOR.tools.callFunction(', y, ",'", B, "','", u, "'); return false;\" href=\"javascript:void('", D, '\')" role="option"><span class="cke_colorbox" style="background-color:#', C, '"></span></a></td>');
            }
            if (n.colorButton_enableMore === undefined || n.colorButton_enableMore)w.push('</tr><tr><td colspan=8 align=center><a class="cke_colormore" _cke_focus=1 hidefocus=true title="', o.more, '" onclick="CKEDITOR.tools.callFunction(', y, ",'?','", u, "');return false;\" href=\"javascript:void('", o.more, "')\"", ' role="option">', o.more, '</a></td>');
            w.push('</tr></table>');
            return w.join('');
        };
        function s(t) {
            return t.getAttribute('contentEditable') == 'false' || t.getAttribute('data-nostyle');
        };
    }});
    i.colorButton_colors = '000,800000,8B4513,2F4F4F,008080,000080,4B0082,696969,B22222,A52A2A,DAA520,006400,40E0D0,0000CD,800080,808080,F00,FF8C00,FFD700,008000,0FF,00F,EE82EE,A9A9A9,FFA07A,FFA500,FFFF00,00FF00,AFEEEE,ADD8E6,DDA0DD,D3D3D3,FFF0F5,FAEBD7,FFFFE0,F0FFF0,F0FFFF,F0F8FF,E6E6FA,FFF';
    i.colorButton_foreStyle = {element: 'span', styles: {color: '#(color)'}, overrides: [
        {element: 'font', attributes: {color: null}}
    ]};
    i.colorButton_backStyle = {element: 'span', styles: {'background-color': '#(color)'}};
    j.colordialog = {requires: ['dialog'], init: function (m) {
        m.addCommand('colordialog', new a.dialogCommand('colordialog'));
        a.dialog.add('colordialog', this.path + 'dialogs/colordialog.js');
    }};
    j.add('colordialog', j.colordialog);
    j.add('contextmenu', {requires: ['menu'], onLoad: function () {
        j.contextMenu = e.createClass({base: a.menu, $: function (m) {
            this.base.call(this, m, {panel: {className: m.skinClass + ' cke_contextmenu', attributes: {'aria-label': m.lang.contextmenu.options}}});
        }, proto: {addTarget: function (m, n) {
            if (b.opera && !('oncontextmenu' in document.body)) {
                var o;
                m.on('mousedown', function (s) {
                    s = s.data;
                    if (s.$.button != 2) {
                        if (s.getKeystroke() == 1114112 + 1)m.fire('contextmenu', s);
                        return;
                    }
                    if (n && (b.mac ? s.$.metaKey : s.$.ctrlKey))return;
                    var t = s.getTarget();
                    if (!o) {
                        var u = t.getDocument();
                        o = u.createElement('input');
                        o.$.type = 'button';
                        u.getBody().append(o);
                    }
                    o.setAttribute('style', 'position:absolute;top:' + (s.$.clientY - 2) + 'px;left:' + (s.$.clientX - 2) + 'px;width:5px;height:5px;opacity:0.01');
                });
                m.on('mouseup', function (s) {
                    if (o) {
                        o.remove();
                        o = undefined;
                        m.fire('contextmenu', s.data);
                    }
                });
            }
            m.on('contextmenu', function (s) {
                var t = s.data;
                if (n && (b.webkit ? p : b.mac ? t.$.metaKey : t.$.ctrlKey))return;
                t.preventDefault();
                var u = t.getTarget().getDocument().getDocumentElement(), v = t.$.clientX, w = t.$.clientY;
                e.setTimeout(function () {
                    this.open(u, null, v, w);
                }, c ? 200 : 0, this);
            }, this);
            if (b.opera)m.on('keypress', function (s) {
                var t = s.data;
                if (t.$.keyCode === 0)t.preventDefault();
            });
            if (b.webkit) {
                var p, q = function (s) {
                    p = b.mac ? s.data.$.metaKey : s.data.$.ctrlKey;
                }, r = function () {
                    p = 0;
                };
                m.on('keydown', q);
                m.on('keyup', r);
                m.on('contextmenu', r);
            }
        }, open: function (m, n, o, p) {
            this.editor.focus();
            m = m || a.document.getDocumentElement();
            this.show(m, n, o, p);
        }}});
    }, beforeInit: function (m) {
        m.contextMenu = new j.contextMenu(m);
        m.addCommand('contextMenu', {exec: function () {
            m.contextMenu.open(m.document.getBody());
        }});
    }});
    (function () {
        function m(o) {
            var p = this.att, q = o && o.hasAttribute(p) && o.getAttribute(p) || '';
            if (q !== undefined)this.setValue(q);
        };
        function n() {
            var o;
            for (var p = 0; p < arguments.length; p++) {
                if (arguments[p] instanceof h) {
                    o = arguments[p];
                    break;
                }
            }
            if (o) {
                var q = this.att, r = this.getValue();
                if (r)o.setAttribute(q, r); else o.removeAttribute(q, r);
            }
        };
        j.add('dialogadvtab', {createAdvancedTab: function (o, p) {
            if (!p)p = {id: 1, dir: 1, classes: 1, styles: 1};
            var q = o.lang.common, r = {id: 'advanced', label: q.advancedTab, title: q.advancedTab, elements: [
                {type: 'vbox', padding: 1, children: []}
            ]}, s = [];
            if (p.id || p.dir) {
                if (p.id)s.push({id: 'advId', att: 'id', type: 'text', label: q.id, setup: m, commit: n});
                if (p.dir)s.push({id: 'advLangDir', att: 'dir', type: 'select', label: q.langDir, 'default': '', style: 'width:100%', items: [
                    [q.notSet, ''],
                    [q.langDirLTR, 'ltr'],
                    [q.langDirRTL, 'rtl']
                ], setup: m, commit: n});
                r.elements[0].children.push({type: 'hbox', widths: ['50%', '50%'], children: [].concat(s)});
            }
            if (p.styles || p.classes) {
                s = [];
                if (p.styles)s.push({id: 'advStyles', att: 'style', type: 'text', label: q.styles, 'default': '', validate: a.dialog.validate.inlineStyle(q.invalidInlineStyle), onChange: function () {
                }, getStyle: function (t, u) {
                    var v = this.getValue().match(new RegExp(t + '\\s*:\\s*([^;]*)', 'i'));
                    return v ? v[1] : u;
                }, updateStyle: function (t, u) {
                    var v = this.getValue(), w = o.document.createElement('span');
                    w.setAttribute('style', v);
                    w.setStyle(t, u);
                    v = e.normalizeCssText(w.getAttribute('style'));
                    this.setValue(v, 1);
                }, setup: m, commit: n});
                if (p.classes)s.push({type: 'hbox', widths: ['45%', '55%'], children: [
                    {id: 'advCSSClasses', att: 'class', type: 'text', label: q.cssClasses, 'default': '', setup: m, commit: n}
                ]});
                r.elements[0].children.push({type: 'hbox', widths: ['50%', '50%'], children: [].concat(s)});
            }
            return r;
        }});
    })();
    (function () {
        j.add('div', {requires: ['editingblock', 'dialog', 'domiterator', 'styles'], init: function (m) {
            var n = m.lang.div;
            m.addCommand('creatediv', new a.dialogCommand('creatediv'));
            m.addCommand('editdiv', new a.dialogCommand('editdiv'));
            m.addCommand('removediv', {exec: function (o) {
                var p = o.getSelection(), q = p && p.getRanges(), r, s = p.createBookmarks(), t, u = [];

                function v(x) {
                    var y = new d.elementPath(x), z = y.blockLimit, A = z.is('div') && z;
                    if (A && !A.data('cke-div-added')) {
                        u.push(A);
                        A.data('cke-div-added');
                    }
                };
                for (var w = 0; w < q.length; w++) {
                    r = q[w];
                    if (r.collapsed)v(p.getStartElement()); else {
                        t = new d.walker(r);
                        t.evaluator = v;
                        t.lastForward();
                    }
                }
                for (w = 0; w < u.length; w++)u[w].remove(true);
                p.selectBookmarks(s);
            }});
            m.ui.addButton('CreateDiv', {label: n.toolbar, command: 'creatediv'});
            if (m.addMenuItems) {
                m.addMenuItems({editdiv: {label: n.edit, command: 'editdiv', group: 'div', order: 1}, removediv: {label: n.remove, command: 'removediv', group: 'div', order: 5}});
                if (m.contextMenu)m.contextMenu.addListener(function (o, p) {
                    if (!o || o.isReadOnly())return null;
                    var q = new d.elementPath(o), r = q.blockLimit;
                    if (r && r.getAscendant('div', true))return{editdiv: 2, removediv: 2};
                    return null;
                });
            }
            a.dialog.add('creatediv', this.path + 'dialogs/div.js');
            a.dialog.add('editdiv', this.path + 'dialogs/div.js');
        }});
    })();
    (function () {
        var m = {toolbarFocus: {editorFocus: false, readOnly: 1, exec: function (o) {
            var p = o._.elementsPath.idBase, q = a.document.getById(p + '0');
            q && q.focus(c || b.air);
        }}}, n = '<span class="cke_empty">&nbsp;</span>';
        j.add('elementspath', {requires: ['selection'], init: function (o) {
            var p = 'cke_path_' + o.name, q, r = function () {
                if (!q)q = a.document.getById(p);
                return q;
            }, s = 'cke_elementspath_' + e.getNextNumber() + '_';
            o._.elementsPath = {idBase: s, filters: []};
            o.on('themeSpace', function (x) {
                if (x.data.space == 'bottom')x.data.html += '<span id="' + p + '_label" class="cke_voice_label">' + o.lang.elementsPath.eleLabel + '</span>' + '<div id="' + p + '" class="cke_path" role="group" aria-labelledby="' + p + '_label">' + n + '</div>';
            });
            function t(x) {
                o.focus();
                var y = o._.elementsPath.list[x];
                if (y.is('body')) {
                    var z = new d.range(o.document);
                    z.selectNodeContents(y);
                    z.select();
                } else o.getSelection().selectElement(y);
            };
            var u = e.addFunction(t), v = e.addFunction(function (x, y) {
                var z = o._.elementsPath.idBase, A;
                y = new d.event(y);
                var B = o.lang.dir == 'rtl';
                switch (y.getKeystroke()) {
                    case B ? 39 : 37:
                    case 9:
                        A = a.document.getById(z + (x + 1));
                        if (!A)A = a.document.getById(z + '0');
                        A.focus();
                        return false;
                    case B ? 37 : 39:
                    case 2228224 + 9:
                        A = a.document.getById(z + (x - 1));
                        if (!A)A = a.document.getById(z + (o._.elementsPath.list.length - 1));
                        A.focus();
                        return false;
                    case 27:
                        o.focus();
                        return false;
                    case 13:
                    case 32:
                        t(x);
                        return false;
                }
                return true;
            });
            o.on('selectionChange', function (x) {
                var y = b, z = x.data.selection, A = z.getStartElement(), B = [], C = x.editor, D = C._.elementsPath.list = [], E = C._.elementsPath.filters;
                while (A) {
                    var F = 0, G;
                    if (A.data('cke-display-name'))G = A.data('cke-display-name'); else if (A.data('cke-real-element-type'))G = A.data('cke-real-element-type'); else G = A.getName();
                    for (var H = 0; H < E.length; H++) {
                        var I = E[H](A, G);
                        if (I === false) {
                            F = 1;
                            break;
                        }
                        G = I || G;
                    }
                    if (!F) {
                        var J = D.push(A) - 1, K = '';
                        if (y.opera || y.gecko && y.mac)K += ' onkeypress="return false;"';
                        if (y.gecko)K += ' onblur="this.style.cssText = this.style.cssText;"';
                        var L = C.lang.elementsPath.eleTitle.replace(/%1/, G);
                        B.unshift('<a id="', s, J, '" href="javascript:void(\'', G, '\')" tabindex="-1" title="', L, '"' + (b.gecko && b.version < 10900 ? ' onfocus="event.preventBubble();"' : '') + ' hidefocus="true" ' + ' onkeydown="return CKEDITOR.tools.callFunction(', v, ',', J, ', event );"' + K, ' onclick="CKEDITOR.tools.callFunction(' + u, ',', J, '); return false;"', ' role="button" aria-labelledby="' + s + J + '_label">', G, '<span id="', s, J, '_label" class="cke_label">' + L + '</span>', '</a>');
                    }
                    if (G == 'body')break;
                    A = A.getParent();
                }
                var M = r();
                M.setHtml(B.join('') + n);
                C.fire('elementsPathUpdate', {space: M});
            });
            function w() {
                q && q.setHtml(n);
                delete o._.elementsPath.list;
            };
            o.on('readOnly', w);
            o.on('contentDomUnload', w);
            o.addCommand('elementsPathFocus', m.toolbarFocus);
        }});
    })();
    (function () {
        j.add('enterkey', {requires: ['keystrokes', 'indent'], init: function (t) {
            t.addCommand('enter', {modes: {wysiwyg: 1}, editorFocus: false, exec: function (v) {
                r(v);
            }});
            t.addCommand('shiftEnter', {modes: {wysiwyg: 1}, editorFocus: false, exec: function (v) {
                q(v);
            }});
            var u = t.keystrokeHandler.keystrokes;
            u[13] = 'enter';
            u[2228224 + 13] = 'shiftEnter';
        }});
        j.enterkey = {enterBlock: function (t, u, v, w) {
            v = v || s(t);
            if (!v)return;
            var x = v.document, y = v.checkStartOfBlock(), z = v.checkEndOfBlock(), A = new d.elementPath(v.startContainer), B = A.block;
            if (y && z) {
                if (B && (B.is('li') || B.getParent().is('li'))) {
                    t.execCommand('outdent');
                    return;
                }
                if (B && B.getParent().is('blockquote')) {
                    B.breakParent(B.getParent());
                    if (!B.getPrevious().getFirst(d.walker.invisible(1)))B.getPrevious().remove();
                    if (!B.getNext().getFirst(d.walker.invisible(1)))B.getNext().remove();
                    v.moveToElementEditStart(B);
                    v.select();
                    return;
                }
            } else if (B && B.is('pre')) {
                if (!z) {
                    n(t, u, v, w);
                    return;
                }
            } else if (B && f.$captionBlock[B.getName()]) {
                n(t, u, v, w);
                return;
            }
            var C = u == 3 ? 'div' : 'p', D = v.splitBlock(C);
            if (!D)return;
            var E = D.previousBlock, F = D.nextBlock, G = D.wasStartOfBlock, H = D.wasEndOfBlock, I;
            if (F) {
                I = F.getParent();
                if (I.is('li')) {
                    F.breakParent(I);
                    F.move(F.getNext(), 1);
                }
            } else if (E && (I = E.getParent()) && I.is('li')) {
                E.breakParent(I);
                I = E.getNext();
                v.moveToElementEditStart(I);
                E.move(E.getPrevious());
            }
            if (!G && !H) {
                if (F.is('li') && (I = F.getFirst(d.walker.invisible(true))) && I.is && I.is('ul', 'ol'))(c ? x.createText('\xa0') : x.createElement('br')).insertBefore(I);
                if (F)v.moveToElementEditStart(F);
            } else {
                var J, K;
                if (E) {
                    if (E.is('li') || !(p.test(E.getName()) || E.is('pre')))J = E.clone();
                } else if (F)J = F.clone();
                if (!J) {
                    if (I && I.is('li'))J = I; else {
                        J = x.createElement(C);
                        if (E && (K = E.getDirection()))J.setAttribute('dir', K);
                    }
                } else if (w && !J.is('li'))J.renameNode(C);
                var L = D.elementPath;
                if (L)for (var M = 0, N = L.elements.length; M < N; M++) {
                    var O = L.elements[M];
                    if (O.equals(L.block) || O.equals(L.blockLimit))break;
                    if (f.$removeEmpty[O.getName()]) {
                        O = O.clone();
                        J.moveChildren(O);
                        J.append(O);
                    }
                }
                if (!c)J.appendBogus();
                if (!J.getParent())v.insertNode(J);
                J.is('li') && J.removeAttribute('value');
                if (c && G && (!H || !E.getChildCount())) {
                    v.moveToElementEditStart(H ? E : J);
                    v.select();
                }
                v.moveToElementEditStart(G && !H ? F : J);
            }
            if (!c)if (F) {
                var P = x.createElement('span');
                P.setHtml('&nbsp;');
                v.insertNode(P);
                P.scrollIntoView();
                v.deleteContents();
            } else J.scrollIntoView();
            v.select();
        }, enterBr: function (t, u, v, w) {
            v = v || s(t);
            if (!v)return;
            var x = v.document, y = u == 3 ? 'div' : 'p', z = v.checkEndOfBlock(), A = new d.elementPath(t.getSelection().getStartElement()), B = A.block, C = B && A.block.getName(), D = false;
            if (!w && C == 'li') {
                o(t, u, v, w);
                return;
            }
            if (!w && z && p.test(C)) {
                var E, F;
                if (F = B.getDirection()) {
                    E = x.createElement('div');
                    E.setAttribute('dir', F);
                    E.insertAfter(B);
                    v.setStart(E, 0);
                } else {
                    x.createElement('br').insertAfter(B);
                    if (b.gecko)x.createText('').insertAfter(B);
                    v.setStartAt(B.getNext(), c ? 3 : 1);
                }
            } else {
                var G;
                D = C == 'pre';
                if (D && !b.gecko)G = x.createText(c ? '\r' : '\n'); else G = x.createElement('br');
                v.deleteContents();
                v.insertNode(G);
                if (c)v.setStartAt(G, 4); else {
                    x.createText('\ufeff').insertAfter(G);
                    if (z)G.getParent().appendBogus();
                    G.getNext().$.nodeValue = '';
                    v.setStartAt(G.getNext(), 1);
                    var H = null;
                    if (!b.gecko) {
                        H = x.createElement('span');
                        H.setHtml('&nbsp;');
                    } else H = x.createElement('br');
                    H.insertBefore(G.getNext());
                    H.scrollIntoView();
                    H.remove();
                }
            }
            v.collapse(true);
            v.select(D);
        }};
        var m = j.enterkey, n = m.enterBr, o = m.enterBlock, p = /^h[1-6]$/;

        function q(t) {
            if (t.mode != 'wysiwyg')return false;
            return r(t, t.config.shiftEnterMode, 1);
        };
        function r(t, u, v) {
            v = t.config.forceEnterMode || v;
            if (t.mode != 'wysiwyg')return false;
            if (!u)u = t.config.enterMode;
            setTimeout(function () {
                t.fire('saveSnapshot');
                if (u == 2)n(t, u, null, v); else o(t, u, null, v);
                t.fire('saveSnapshot');
            }, 0);
            return true;
        };
        function s(t) {
            var u = t.getSelection().getRanges(true);
            for (var v = u.length - 1; v > 0; v--)u[v].deleteContents();
            return u[0];
        };
    })();
    (function () {
        var m = 'nbsp,gt,lt,amp', n = 'quot,iexcl,cent,pound,curren,yen,brvbar,sect,uml,copy,ordf,laquo,not,shy,reg,macr,deg,plusmn,sup2,sup3,acute,micro,para,middot,cedil,sup1,ordm,raquo,frac14,frac12,frac34,iquest,times,divide,fnof,bull,hellip,prime,Prime,oline,frasl,weierp,image,real,trade,alefsym,larr,uarr,rarr,darr,harr,crarr,lArr,uArr,rArr,dArr,hArr,forall,part,exist,empty,nabla,isin,notin,ni,prod,sum,minus,lowast,radic,prop,infin,ang,and,or,cap,cup,int,there4,sim,cong,asymp,ne,equiv,le,ge,sub,sup,nsub,sube,supe,oplus,otimes,perp,sdot,lceil,rceil,lfloor,rfloor,lang,rang,loz,spades,clubs,hearts,diams,circ,tilde,ensp,emsp,thinsp,zwnj,zwj,lrm,rlm,ndash,mdash,lsquo,rsquo,sbquo,ldquo,rdquo,bdquo,dagger,Dagger,permil,lsaquo,rsaquo,euro', o = 'Agrave,Aacute,Acirc,Atilde,Auml,Aring,AElig,Ccedil,Egrave,Eacute,Ecirc,Euml,Igrave,Iacute,Icirc,Iuml,ETH,Ntilde,Ograve,Oacute,Ocirc,Otilde,Ouml,Oslash,Ugrave,Uacute,Ucirc,Uuml,Yacute,THORN,szlig,agrave,aacute,acirc,atilde,auml,aring,aelig,ccedil,egrave,eacute,ecirc,euml,igrave,iacute,icirc,iuml,eth,ntilde,ograve,oacute,ocirc,otilde,ouml,oslash,ugrave,uacute,ucirc,uuml,yacute,thorn,yuml,OElig,oelig,Scaron,scaron,Yuml', p = 'Alpha,Beta,Gamma,Delta,Epsilon,Zeta,Eta,Theta,Iota,Kappa,Lambda,Mu,Nu,Xi,Omicron,Pi,Rho,Sigma,Tau,Upsilon,Phi,Chi,Psi,Omega,alpha,beta,gamma,delta,epsilon,zeta,eta,theta,iota,kappa,lambda,mu,nu,xi,omicron,pi,rho,sigmaf,sigma,tau,upsilon,phi,chi,psi,omega,thetasym,upsih,piv';

        function q(r, s) {
            var t = {}, u = [], v = {nbsp: '\xa0', shy: '­', gt: '>', lt: '<', amp: '&', apos: "'", quot: '"'};
            r = r.replace(/\b(nbsp|shy|gt|lt|amp|apos|quot)(?:,|$)/g, function (A, B) {
                var C = s ? '&' + B + ';' : v[B], D = s ? v[B] : '&' + B + ';';
                t[C] = D;
                u.push(C);
                return '';
            });
            if (!s && r) {
                r = r.split(',');
                var w = document.createElement('div'), x;
                w.innerHTML = '&' + r.join(';&') + ';';
                x = w.innerHTML;
                w = null;
                for (var y = 0; y < x.length; y++) {
                    var z = x.charAt(y);
                    t[z] = '&' + r[y] + ';';
                    u.push(z);
                }
            }
            t.regex = u.join(s ? '|' : '');
            return t;
        };
        j.add('entities', {afterInit: function (r) {
            var s = r.config, t = r.dataProcessor, u = t && t.htmlFilter;
            if (u) {
                var v = [];
                if (s.basicEntities !== false)v.push(m);
                if (s.entities) {
                    if (v.length)v.push(n);
                    if (s.entities_latin)v.push(o);
                    if (s.entities_greek)v.push(p);
                    if (s.entities_additional)v.push(s.entities_additional);
                }
                var w = q(v.join(',')), x = w.regex ? '[' + w.regex + ']' : 'a^';
                delete w.regex;
                if (s.entities && s.entities_processNumerical)x = '[^ -~]|' + x;
                x = new RegExp(x, 'g');
                function y(C) {
                    return s.entities_processNumerical == 'force' || !w[C] ? '&#' + C.charCodeAt(0) + ';' : w[C];
                };
                var z = q([m, 'shy'].join(','), true), A = new RegExp(z.regex, 'g');

                function B(C) {
                    return z[C];
                };
                u.addRules({text: function (C) {
                    return C.replace(A, B).replace(x, y);
                }});
            }
        }});
    })();
    i.basicEntities = true;
    i.entities = true;
    i.entities_latin = true;
    i.entities_greek = true;
    i.entities_additional = '#39';
    (function () {
        function m(v, w) {
            var x = [];
            if (!w)return v; else for (var y in w)x.push(y + '=' + encodeURIComponent(w[y]));
            return v + (v.indexOf('?') != -1 ? '&' : '?') + x.join('&');
        };
        function n(v) {
            v += '';
            var w = v.charAt(0).toUpperCase();
            return w + v.substr(1);
        };
        function o(v) {
            var C = this;
            var w = C.getDialog(), x = w.getParentEditor();
            x._.filebrowserSe = C;
            var y = x.config['filebrowser' + n(w.getName()) + 'WindowWidth'] || x.config.filebrowserWindowWidth || '80%', z = x.config['filebrowser' + n(w.getName()) + 'WindowHeight'] || x.config.filebrowserWindowHeight || '70%', A = C.filebrowser.params || {};
            A.CKEditor = x.name;
            A.CKEditorFuncNum = x._.filebrowserFn;
            if (!A.langCode)A.langCode = x.langCode;
            var B = m(C.filebrowser.url, A);
            x.popup(B, y, z, x.config.filebrowserWindowFeatures || x.config.fileBrowserWindowFeatures);
        };
        function p(v) {
            var y = this;
            var w = y.getDialog(), x = w.getParentEditor();
            x._.filebrowserSe = y;
            if (!w.getContentElement(y['for'][0], y['for'][1]).getInputElement().$.value)return false;
            if (!w.getContentElement(y['for'][0], y['for'][1]).getAction())return false;
            return true;
        };
        function q(v, w, x) {
            var y = x.params || {};
            y.CKEditor = v.name;
            y.CKEditorFuncNum = v._.filebrowserFn;
            if (!y.langCode)y.langCode = v.langCode;
            w.action = m(x.url, y);
            w.filebrowser = x;
        };
        function r(v, w, x, y) {
            var z, A;
            for (var B in y) {
                z = y[B];
                if (z.type == 'hbox' || z.type == 'vbox' || z.type == 'fieldset')r(v, w, x, z.children);
                if (!z.filebrowser)continue;
                if (typeof z.filebrowser == 'string') {
                    var C = {action: z.type == 'fileButton' ? 'QuickUpload' : 'Browse', target: z.filebrowser};
                    z.filebrowser = C;
                }
                if (z.filebrowser.action == 'Browse') {
                    var D = z.filebrowser.url;
                    if (D === undefined) {
                        D = v.config['filebrowser' + n(w) + 'BrowseUrl'];
                        if (D === undefined)D = v.config.filebrowserBrowseUrl;
                    }
                    if (D) {
                        z.onClick = o;
                        z.filebrowser.url = D;
                        z.hidden = false;
                    }
                } else if (z.filebrowser.action == 'QuickUpload' && z['for']) {
                    D = z.filebrowser.url;
                    if (D === undefined) {
                        D = v.config['filebrowser' + n(w) + 'UploadUrl'];
                        if (D === undefined)D = v.config.filebrowserUploadUrl;
                    }
                    if (D) {
                        var E = z.onClick;
                        z.onClick = function (F) {
                            var G = F.sender;
                            if (E && E.call(G, F) === false)return false;
                            return p.call(G, F);
                        };
                        z.filebrowser.url = D;
                        z.hidden = false;
                        q(v, x.getContents(z['for'][0]).get(z['for'][1]), z.filebrowser);
                    }
                }
            }
        };
        function s(v, w) {
            var x = w.getDialog(), y = w.filebrowser.target || null;
            if (y) {
                var z = y.split(':'), A = x.getContentElement(z[0], z[1]);
                if (A) {
                    A.setValue(v);
                    x.selectPage(z[0]);
                }
            }
        };
        function t(v, w, x) {
            if (x.indexOf(';') !== -1) {
                var y = x.split(';');
                for (var z = 0; z < y.length; z++) {
                    if (t(v, w, y[z]))return true;
                }
                return false;
            }
            var A = v.getContents(w).get(x).filebrowser;
            return A && A.url;
        };
        function u(v, w) {
            var A = this;
            var x = A._.filebrowserSe.getDialog(), y = A._.filebrowserSe['for'], z = A._.filebrowserSe.filebrowser.onSelect;
            if (y)x.getContentElement(y[0], y[1]).reset();
            if (typeof w == 'function' && w.call(A._.filebrowserSe) === false)return;
            if (z && z.call(A._.filebrowserSe, v, w) === false)return;
            if (typeof w == 'string' && w)alert(w);
            if (v)s(v, A._.filebrowserSe);
        };
        j.add('filebrowser', {init: function (v, w) {
            v._.filebrowserFn = e.addFunction(u, v);
            v.on('destroy', function () {
                e.removeFunction(this._.filebrowserFn);
            });
        }});
        a.on('dialogDefinition', function (v) {
            var w = v.data.definition, x;
            for (var y in w.contents) {
                if (x = w.contents[y]) {
                    r(v.editor, v.data.name, w, x.elements);
                    if (x.hidden && x.filebrowser)x.hidden = !t(w, x.id, x.filebrowser);
                }
            }
        });
    })();
    j.add('find', {requires: ['dialog'], init: function (m) {
        var n = j.find;
        m.ui.addButton('Find', {label: m.lang.findAndReplace.find, command: 'find'});
        var o = m.addCommand('find', new a.dialogCommand('find'));
        o.canUndo = false;
        o.readOnly = 1;
        m.ui.addButton('Replace', {label: m.lang.findAndReplace.replace, command: 'replace'});
        var p = m.addCommand('replace', new a.dialogCommand('replace'));
        p.canUndo = false;
        a.dialog.add('find', this.path + 'dialogs/find.js');
        a.dialog.add('replace', this.path + 'dialogs/find.js');
    }, requires: ['styles']});
    i.find_highlight = {element: 'span', styles: {'background-color': '#004', color: '#fff'}};
    (function () {
        var m = /\.swf(?:$|\?)/i;

        function n(p) {
            var q = p.attributes;
            return q.type == 'application/x-shockwave-flash' || m.test(q.src || '');
        };
        function o(p, q) {
            return p.createFakeParserElement(q, 'cke_flash', 'flash', true);
        };
        j.add('flash', {init: function (p) {
            p.addCommand('flash', new a.dialogCommand('flash'));
            p.ui.addButton('Flash', {label: p.lang.common.flash, command: 'flash'});
            a.dialog.add('flash', this.path + 'dialogs/flash.js');
            p.addCss('img.cke_flash{background-image: url(' + a.getUrl(this.path + 'images/placeholder.png') + ');' + 'background-position: center center;' + 'background-repeat: no-repeat;' + 'border: 1px solid #a9a9a9;' + 'width: 80px;' + 'height: 80px;' + '}');
            if (p.addMenuItems)p.addMenuItems({flash: {label: p.lang.flash.properties, command: 'flash', group: 'flash'}});
            p.on('doubleclick', function (q) {
                var r = q.data.element;
                if (r.is('img') && r.data('cke-real-element-type') == 'flash')q.data.dialog = 'flash';
            });
            if (p.contextMenu)p.contextMenu.addListener(function (q, r) {
                if (q && q.is('img') && !q.isReadOnly() && q.data('cke-real-element-type') == 'flash')return{flash: 2};
            });
        }, afterInit: function (p) {
            var q = p.dataProcessor, r = q && q.dataFilter;
            if (r)r.addRules({elements: {'cke:object': function (s) {
                var t = s.attributes, u = t.classid && String(t.classid).toLowerCase();
                if (!u && !n(s)) {
                    for (var v = 0; v < s.children.length; v++) {
                        if (s.children[v].name == 'cke:embed') {
                            if (!n(s.children[v]))return null;
                            return o(p, s);
                        }
                    }
                    return null;
                }
                return o(p, s);
            }, 'cke:embed': function (s) {
                if (!n(s))return null;
                return o(p, s);
            }}}, 5);
        }, requires: ['fakeobjects']});
    })();
    e.extend(i, {flashEmbedTagOnly: false, flashAddEmbedTag: true, flashConvertOnEdit: false});
    (function () {
        function m(n, o, p, q, r, s, t) {
            var u = n.config, v = r.split(';'), w = [], x = {};
            for (var y = 0; y < v.length; y++) {
                var z = v[y];
                if (z) {
                    z = z.split('/');
                    var A = {}, B = v[y] = z[0];
                    A[p] = w[y] = z[1] || B;
                    x[B] = new a.style(t, A);
                    x[B]._.definition.name = B;
                } else v.splice(y--, 1);
            }
            n.ui.addRichCombo(o, {label: q.label, title: q.panelTitle, className: 'cke_' + (p == 'size' ? 'fontSize' : 'font'), panel: {css: n.skin.editor.css.concat(u.contentsCss), multiSelect: false, attributes: {'aria-label': q.panelTitle}}, init: function () {
                this.startGroup(q.panelTitle);
                for (var C = 0; C < v.length; C++) {
                    var D = v[C];
                    this.add(D, x[D].buildPreview(), D);
                }
            }, onClick: function (C) {
                n.focus();
                n.fire('saveSnapshot');
                var D = x[C];
                if (this.getValue() == C)D.remove(n.document); else D.apply(n.document);
                n.fire('saveSnapshot');
            }, onRender: function () {
                n.on('selectionChange', function (C) {
                    var D = this.getValue(), E = C.data.path, F = E.elements;
                    for (var G = 0, H; G < F.length; G++) {
                        H = F[G];
                        for (var I in x) {
                            if (x[I].checkElementMatch(H, true)) {
                                if (I != D)this.setValue(I);
                                return;
                            }
                        }
                    }
                    this.setValue('', s);
                }, this);
            }});
        };
        j.add('font', {requires: ['richcombo', 'styles'], init: function (n) {
            var o = n.config;
            m(n, 'Font', 'family', n.lang.font, o.font_names, o.font_defaultLabel, o.font_style);
            m(n, 'FontSize', 'size', n.lang.fontSize, o.fontSize_sizes, o.fontSize_defaultLabel, o.fontSize_style);
        }});
    })();
    i.font_names = 'Arial/Arial, Helvetica, sans-serif;Comic Sans MS/Comic Sans MS, cursive;Courier New/Courier New, Courier, monospace;Georgia/Georgia, serif;Lucida Sans Unicode/Lucida Sans Unicode, Lucida Grande, sans-serif;Tahoma/Tahoma, Geneva, sans-serif;Times New Roman/Times New Roman, Times, serif;Trebuchet MS/Trebuchet MS, Helvetica, sans-serif;Verdana/Verdana, Geneva, sans-serif';
    i.font_defaultLabel = '';
    i.font_style = {element: 'span', styles: {'font-family': '#(family)'}, overrides: [
        {element: 'font', attributes: {face: null}}
    ]};
    i.fontSize_sizes = '8/8px;9/9px;10/10px;11/11px;12/12px;14/14px;16/16px;18/18px;20/20px;22/22px;24/24px;26/26px;28/28px;36/36px;48/48px;72/72px';
    i.fontSize_defaultLabel = '';
    i.fontSize_style = {element: 'span', styles: {'font-size': '#(size)'}, overrides: [
        {element: 'font', attributes: {size: null}}
    ]};
    j.add('format', {requires: ['richcombo', 'styles'], init: function (m) {
        var n = m.config, o = m.lang.format, p = n.format_tags.split(';'), q = {};
        for (var r = 0; r < p.length; r++) {
            var s = p[r];
            q[s] = new a.style(n['format_' + s]);
            q[s]._.enterMode = m.config.enterMode;
        }
        m.ui.addRichCombo('Format', {label: o.label, title: o.panelTitle, className: 'cke_format', panel: {css: m.skin.editor.css.concat(n.contentsCss), multiSelect: false, attributes: {'aria-label': o.panelTitle}}, init: function () {
            this.startGroup(o.panelTitle);
            for (var t in q) {
                var u = o['tag_' + t];
                this.add(t, q[t].buildPreview(u), u);
            }
        }, onClick: function (t) {
            m.focus();
            m.fire('saveSnapshot');
            var u = q[t], v = new d.elementPath(m.getSelection().getStartElement());
            u[u.checkActive(v) ? 'remove' : 'apply'](m.document);
            setTimeout(function () {
                m.fire('saveSnapshot');
            }, 0);
        }, onRender: function () {
            m.on('selectionChange', function (t) {
                var u = this.getValue(), v = t.data.path;
                for (var w in q) {
                    if (q[w].checkActive(v)) {
                        if (w != u)this.setValue(w, m.lang.format['tag_' + w]);
                        return;
                    }
                }
                this.setValue('');
            }, this);
        }});
    }});
    i.format_tags = 'p;h1;h2;h3;h4;h5;h6;pre;address;div';
    i.format_p = {element: 'p'};
    i.format_div = {element: 'div'};
    i.format_pre = {element: 'pre'};
    i.format_address = {element: 'address'};
    i.format_h1 = {element: 'h1'};
    i.format_h2 = {element: 'h2'};
    i.format_h3 = {element: 'h3'};
    i.format_h4 = {element: 'h4'};
    i.format_h5 = {element: 'h5'};
    i.format_h6 = {element: 'h6'};
    j.add('forms', {requires: ['dialog'], init: function (m) {
        var n = m.lang;
        m.addCss('form{border: 1px dotted #FF0000;padding: 2px;}\n');
        m.addCss('img.cke_hidden{background-image: url(' + a.getUrl(this.path + 'images/hiddenfield.gif') + ');' + 'background-position: center center;' + 'background-repeat: no-repeat;' + 'border: 1px solid #a9a9a9;' + 'width: 16px !important;' + 'height: 16px !important;' + '}');
        var o = function (q, r, s) {
            m.addCommand(r, new a.dialogCommand(r));
            m.ui.addButton(q, {label: n.common[q.charAt(0).toLowerCase() + q.slice(1)], command: r});
            a.dialog.add(r, s);
        }, p = this.path + 'dialogs/';
        o('Form', 'form', p + 'form.js');
        o('Checkbox', 'checkbox', p + 'checkbox.js');
        o('Radio', 'radio', p + 'radio.js');
        o('TextField', 'textfield', p + 'textfield.js');
        o('Textarea', 'textarea', p + 'textarea.js');
        o('Select', 'select', p + 'select.js');
        o('Button', 'button', p + 'button.js');
        o('ImageButton', 'imagebutton', j.getPath('image') + 'dialogs/image.js');
        o('HiddenField', 'hiddenfield', p + 'hiddenfield.js');
        if (m.addMenuItems)m.addMenuItems({form: {label: n.form.menu, command: 'form', group: 'form'}, checkbox: {label: n.checkboxAndRadio.checkboxTitle, command: 'checkbox', group: 'checkbox'}, radio: {label: n.checkboxAndRadio.radioTitle, command: 'radio', group: 'radio'}, textfield: {label: n.textfield.title, command: 'textfield', group: 'textfield'}, hiddenfield: {label: n.hidden.title, command: 'hiddenfield', group: 'hiddenfield'}, imagebutton: {label: n.image.titleButton, command: 'imagebutton', group: 'imagebutton'}, button: {label: n.button.title, command: 'button', group: 'button'}, select: {label: n.select.title, command: 'select', group: 'select'}, textarea: {label: n.textarea.title, command: 'textarea', group: 'textarea'}});
        if (m.contextMenu) {
            m.contextMenu.addListener(function (q) {
                if (q && q.hasAscendant('form', true) && !q.isReadOnly())return{form: 2};
            });
            m.contextMenu.addListener(function (q) {
                if (q && !q.isReadOnly()) {
                    var r = q.getName();
                    if (r == 'select')return{select: 2};
                    if (r == 'textarea')return{textarea: 2};
                    if (r == 'input')switch (q.getAttribute('type')) {
                        case 'button':
                        case 'submit':
                        case 'reset':
                            return{button: 2};
                        case 'checkbox':
                            return{checkbox: 2};
                        case 'radio':
                            return{radio: 2};
                        case 'image':
                            return{imagebutton: 2};
                        default:
                            return{textfield: 2};
                    }
                    if (r == 'img' && q.data('cke-real-element-type') == 'hiddenfield')return{hiddenfield: 2};
                }
            });
        }
        m.on('doubleclick', function (q) {
            var r = q.data.element;
            if (r.is('form'))q.data.dialog = 'form'; else if (r.is('select'))q.data.dialog = 'select'; else if (r.is('textarea'))q.data.dialog = 'textarea'; else if (r.is('img') && r.data('cke-real-element-type') == 'hiddenfield')q.data.dialog = 'hiddenfield'; else if (r.is('input'))switch (r.getAttribute('type')) {
                case 'button':
                case 'submit':
                case 'reset':
                    q.data.dialog = 'button';
                    break;
                case 'checkbox':
                    q.data.dialog = 'checkbox';
                    break;
                case 'radio':
                    q.data.dialog = 'radio';
                    break;
                case 'image':
                    q.data.dialog = 'imagebutton';
                    break;
                default:
                    q.data.dialog = 'textfield';
                    break;
            }
        });
    }, afterInit: function (m) {
        var n = m.dataProcessor, o = n && n.htmlFilter, p = n && n.dataFilter;
        if (c)o && o.addRules({elements: {input: function (q) {
            var r = q.attributes, s = r.type;
            if (!s)r.type = 'text';
            if (s == 'checkbox' || s == 'radio')r.value == 'on' && delete r.value;
        }}});
        if (p)p.addRules({elements: {input: function (q) {
            if (q.attributes.type == 'hidden')return m.createFakeParserElement(q, 'cke_hidden', 'hiddenfield');
        }}});
    }, requires: ['image', 'fakeobjects']});
    if (c)h.prototype.hasAttribute = e.override(h.prototype.hasAttribute, function (m) {
        return function (n) {
            var q = this;
            var o = q.$.attributes.getNamedItem(n);
            if (q.getName() == 'input')switch (n) {
                case 'class':
                    return q.$.className.length > 0;
                case 'checked':
                    return!!q.$.checked;
                case 'value':
                    var p = q.getAttribute('type');
                    return p == 'checkbox' || p == 'radio' ? q.$.value != 'on' : q.$.value;
            }
            return m.apply(q, arguments);
        };
    });
    (function () {
        var m = {canUndo: false, exec: function (o) {
            var p = o.document.createElement('hr');
            o.insertElement(p);
        }}, n = 'horizontalrule';
        j.add(n, {init: function (o) {
            o.addCommand(n, m);
            o.ui.addButton('HorizontalRule', {label: o.lang.horizontalrule, command: n});
        }});
    })();
    (function () {
        var m = /^[\t\r\n ]*(?:&nbsp;|\xa0)$/, n = '{cke_protected}';

        function o(U) {
            var V = U.children.length, W = U.children[V - 1];
            while (W && W.type == 3 && !e.trim(W.value))W = U.children[--V];
            return W;
        };
        function p(U) {
            var V = U.parent;
            return V ? e.indexOf(V.children, U) : -1;
        };
        function q(U, V) {
            var W = U.children, X = o(U);
            if (X) {
                if ((V || !c) && X.type == 1 && X.name == 'br')W.pop();
                if (X.type == 3 && m.test(X.value))W.pop();
            }
        };
        function r(U, V, W) {
            if (!V && (!W || typeof W == 'function' && W(U) === false))return false;
            if (V && c && (document.documentMode > 7 || U.name in f.tr || U.name in f.$listItem))return false;
            var X = o(U);
            return!X || X && (X.type == 1 && X.name == 'br' || U.name == 'form' && X.name == 'input');
        };
        function s(U, V) {
            return function (W) {
                q(W, !U);
                if (r(W, !U, V))if (U || c)W.add(new a.htmlParser.text('\xa0')); else W.add(new a.htmlParser.element('br', {}));
            };
        };
        var t = f, u = ['caption', 'colgroup', 'col', 'thead', 'tfoot', 'tbody'], v = e.extend({}, t.$block, t.$listItem, t.$tableContent);
        for (var w in v) {
            if (!('br' in t[w]))delete v[w];
        }
        delete v.pre;
        var x = {elements: {}, attributeNames: [
            [/^on/, 'data-cke-pa-on']
        ]}, y = {elements: {}};
        for (w in v)y.elements[w] = s();
        var z = {elementNames: [
            [/^cke:/, ''],
            [/^\?xml:namespace$/, '']
        ], attributeNames: [
            [/^data-cke-(saved|pa)-/, ''],
            [/^data-cke-.*/, ''],
            ['hidefocus', '']
        ], elements: {$: function (U) {
            var V = U.attributes;
            if (V) {
                if (V['data-cke-temp'])return false;
                var W = ['name', 'href', 'src'], X;
                for (var Y = 0; Y < W.length; Y++) {
                    X = 'data-cke-saved-' + W[Y];
                    X in V && delete V[W[Y]];
                }
            }
            return U;
        }, table: function (U) {
            var V = U.children.slice(0);
            V.sort(function (W, X) {
                var Y, Z;
                if (W.type == 1 && X.type == W.type) {
                    Y = e.indexOf(u, W.name);
                    Z = e.indexOf(u, X.name);
                }
                if (!(Y > -1 && Z > -1 && Y != Z)) {
                    Y = p(W);
                    Z = p(X);
                }
                return Y > Z ? 1 : -1;
            });
        }, embed: function (U) {
            var V = U.parent;
            if (V && V.name == 'object') {
                var W = V.attributes.width, X = V.attributes.height;
                W && (U.attributes.width = W);
                X && (U.attributes.height = X);
            }
        }, param: function (U) {
            U.children = [];
            U.isEmpty = true;
            return U;
        }, a: function (U) {
            if (!(U.children.length || U.attributes.name || U.attributes['data-cke-saved-name']))return false;
        }, span: function (U) {
            if (U.attributes['class'] == 'Apple-style-span')delete U.name;
        }, pre: function (U) {
            c && q(U);
        }, html: function (U) {
            delete U.attributes.contenteditable;
            delete U.attributes['class'];
        }, body: function (U) {
            delete U.attributes.spellcheck;
            delete U.attributes.contenteditable;
        }, style: function (U) {
            var V = U.children[0];
            V && V.value && (V.value = e.trim(V.value));
            if (!U.attributes.type)U.attributes.type = 'text/css';
        }, title: function (U) {
            var V = U.children[0];
            V && (V.value = U.attributes['data-cke-title'] || '');
        }}, attributes: {'class': function (U, V) {
            return e.ltrim(U.replace(/(?:^|\s+)cke_[^\s]*/g, '')) || false;
        }}};
        if (c)z.attributes.style = function (U, V) {
            return U.replace(/(^|;)([^\:]+)/g, function (W) {
                return W.toLowerCase();
            });
        };
        function A(U) {
            var V = U.attributes;
            if (V.contenteditable != 'false')V['data-cke-editable'] = V.contenteditable ? 'true' : 1;
            V.contenteditable = 'false';
        };
        function B(U) {
            var V = U.attributes;
            switch (V['data-cke-editable']) {
                case 'true':
                    V.contenteditable = 'true';
                    break;
                case '1':
                    delete V.contenteditable;
                    break;
            }
        };
        for (w in {input: 1, textarea: 1}) {
            x.elements[w] = A;
            z.elements[w] = B;
        }
        var C = /<(a|area|img|input|source)\b([^>]*)>/gi, D = /\b(on\w+|href|src|name)\s*=\s*(?:(?:"[^"]*")|(?:'[^']*')|(?:[^ "'>]+))/gi, E = /(?:<style(?=[ >])[^>]*>[\s\S]*<\/style>)|(?:<(:?link|meta|base)[^>]*>)/gi, F = /<cke:encoded>([^<]*)<\/cke:encoded>/gi, G = /(<\/?)((?:object|embed|param|html|body|head|title)[^>]*>)/gi, H = /(<\/?)cke:((?:html|body|head|title)[^>]*>)/gi, I = /<cke:(param|embed)([^>]*?)\/?>(?!\s*<\/cke:\1)/gi;

        function J(U) {
            return U.replace(C, function (V, W, X) {
                return '<' + W + X.replace(D, function (Y, Z) {
                    if (!/^on/.test(Z) && X.indexOf('data-cke-saved-' + Z) == -1)return ' data-cke-saved-' + Y + ' data-cke-' + a.rnd + '-' + Y;
                    return Y;
                }) + '>';
            });
        };
        function K(U) {
            return U.replace(E, function (V) {
                return '<cke:encoded>' + encodeURIComponent(V) + '</cke:encoded>';
            });
        };
        function L(U) {
            return U.replace(F, function (V, W) {
                return decodeURIComponent(W);
            });
        };
        function M(U) {
            return U.replace(G, '$1cke:$2');
        };
        function N(U) {
            return U.replace(H, '$1$2');
        };
        function O(U) {
            return U.replace(I, '<cke:$1$2></cke:$1>');
        };
        function P(U) {
            return U.replace(/(<pre\b[^>]*>)(\r\n|\n)/g, '$1$2$2');
        };
        function Q(U) {
            return U.replace(/<!--(?!{cke_protected})[\s\S]+?-->/g, function (V) {
                return '<!--' + n + '{C}' + encodeURIComponent(V).replace(/--/g, '%2D%2D') + '-->';
            });
        };
        function R(U) {
            return U.replace(/<!--\{cke_protected\}\{C\}([\s\S]+?)-->/g, function (V, W) {
                return decodeURIComponent(W);
            });
        };
        function S(U, V) {
            var W = V._.dataStore;
            return U.replace(/<!--\{cke_protected\}([\s\S]+?)-->/g, function (X, Y) {
                return decodeURIComponent(Y);
            }).replace(/\{cke_protected_(\d+)\}/g, function (X, Y) {
                return W && W[Y] || '';
            });
        };
        function T(U, V) {
            var W = [], X = V.config.protectedSource, Y = V._.dataStore || (V._.dataStore = {id: 1}), Z = /<\!--\{cke_temp(comment)?\}(\d*?)-->/g, aa = [/<script[\s\S]*?<\/script>/gi, /<noscript[\s\S]*?<\/noscript>/gi].concat(X);
            U = U.replace(/<!--[\s\S]*?-->/g, function (ac) {
                return '<!--{cke_tempcomment}' + (W.push(ac) - 1) + '-->';
            });
            for (var ab = 0; ab < aa.length; ab++)U = U.replace(aa[ab], function (ac) {
                ac = ac.replace(Z, function (ad, ae, af) {
                    return W[af];
                });
                return/cke_temp(comment)?/.test(ac) ? ac : '<!--{cke_temp}' + (W.push(ac) - 1) + '-->';
            });
            U = U.replace(Z, function (ac, ad, ae) {
                return '<!--' + n + (ad ? '{C}' : '') + encodeURIComponent(W[ae]).replace(/--/g, '%2D%2D') + '-->';
            });
            return U.replace(/(['"]).*?\1/g, function (ac) {
                return ac.replace(/<!--\{cke_protected\}([\s\S]+?)-->/g, function (ad, ae) {
                    Y[Y.id] = decodeURIComponent(ae);
                    return '{cke_protected_' + Y.id++ + '}';
                });
            });
        };
        j.add('htmldataprocessor', {requires: ['htmlwriter'], init: function (U) {
            var V = U.dataProcessor = new a.htmlDataProcessor(U);
            V.writer.forceSimpleAmpersand = U.config.forceSimpleAmpersand;
            V.dataFilter.addRules(x);
            V.dataFilter.addRules(y);
            V.htmlFilter.addRules(z);
            var W = {elements: {}};
            for (w in v)W.elements[w] = s(true, U.config.fillEmptyBlocks);
            V.htmlFilter.addRules(W);
        }, onLoad: function () {
            !('fillEmptyBlocks' in i) && (i.fillEmptyBlocks = 1);
        }});
        a.htmlDataProcessor = function (U) {
            var V = this;
            V.editor = U;
            V.writer = new a.htmlWriter();
            V.dataFilter = new a.htmlParser.filter();
            V.htmlFilter = new a.htmlParser.filter();
        };
        a.htmlDataProcessor.prototype = {toHtml: function (U, V) {
            U = T(U, this.editor);
            U = J(U);
            U = K(U);
            U = M(U);
            U = O(U);
            U = P(U);
            var W = new h('div');
            W.setHtml('a' + U);
            U = W.getHtml().substr(1);
            U = U.replace(new RegExp(' data-cke-' + a.rnd + '-', 'ig'), ' ');
            U = N(U);
            U = L(U);
            U = R(U);
            var X = a.htmlParser.fragment.fromHtml(U, V), Y = new a.htmlParser.basicWriter();
            X.writeHtml(Y, this.dataFilter);
            U = Y.getHtml(true);
            U = Q(U);
            return U;
        }, toDataFormat: function (U, V) {
            var W = this.writer, X = a.htmlParser.fragment.fromHtml(U, V);
            W.reset();
            X.writeHtml(W, this.htmlFilter);
            var Y = W.getHtml(true);
            Y = R(Y);
            Y = S(Y, this.editor);
            return Y;
        }};
    })();
    (function () {
        j.add('iframe', {requires: ['dialog', 'fakeobjects'], init: function (m) {
            var n = 'iframe', o = m.lang.iframe;
            a.dialog.add(n, this.path + 'dialogs/iframe.js');
            m.addCommand(n, new a.dialogCommand(n));
            m.addCss('img.cke_iframe{background-image: url(' + a.getUrl(this.path + 'images/placeholder.png') + ');' + 'background-position: center center;' + 'background-repeat: no-repeat;' + 'border: 1px solid #a9a9a9;' + 'width: 80px;' + 'height: 80px;' + '}');
            m.ui.addButton('Iframe', {label: o.toolbar, command: n});
            m.on('doubleclick', function (p) {
                var q = p.data.element;
                if (q.is('img') && q.data('cke-real-element-type') == 'iframe')p.data.dialog = 'iframe';
            });
            if (m.addMenuItems)m.addMenuItems({iframe: {label: o.title, command: 'iframe', group: 'image'}});
            if (m.contextMenu)m.contextMenu.addListener(function (p, q) {
                if (p && p.is('img') && p.data('cke-real-element-type') == 'iframe')return{iframe: 2};
            });
        }, afterInit: function (m) {
            var n = m.dataProcessor, o = n && n.dataFilter;
            if (o)o.addRules({elements: {iframe: function (p) {
                return m.createFakeParserElement(p, 'cke_iframe', 'iframe', true);
            }}});
        }});
    })();
    (function () {
        j.add('image', {requires: ['dialog'], init: function (o) {
            var p = 'image';
            a.dialog.add(p, this.path + 'dialogs/image.js');
            o.addCommand(p, new a.dialogCommand(p));
            o.ui.addButton('Image', {label: o.lang.common.image, command: p});
            o.on('doubleclick', function (q) {
                var r = q.data.element;
                if (r.is('img') && !r.data('cke-realelement') && !r.isReadOnly())q.data.dialog = 'image';
            });
            if (o.addMenuItems)o.addMenuItems({image: {label: o.lang.image.menu, command: 'image', group: 'image'}});
            if (o.contextMenu)o.contextMenu.addListener(function (q, r) {
                if (m(o, q))return{image: 2};
            });
        }, afterInit: function (o) {
            p('left');
            p('right');
            p('center');
            p('block');
            function p(q) {
                var r = o.getCommand('justify' + q);
                if (r) {
                    if (q == 'left' || q == 'right')r.on('exec', function (s) {
                        var t = m(o), u;
                        if (t) {
                            u = n(t);
                            if (u == q) {
                                t.removeStyle('float');
                                if (q == n(t))t.removeAttribute('align');
                            } else t.setStyle('float', q);
                            s.cancel();
                        }
                    });
                    r.on('refresh', function (s) {
                        var t = m(o), u;
                        if (t) {
                            u = n(t);
                            this.setState(u == q ? 1 : q == 'right' || q == 'left' ? 2 : 0);
                            s.cancel();
                        }
                    });
                }
            };
        }});
        function m(o, p) {
            if (!p) {
                var q = o.getSelection();
                p = q.getType() == 3 && q.getSelectedElement();
            }
            if (p && p.is('img') && !p.data('cke-realelement') && !p.isReadOnly())return p;
        };
        function n(o) {
            var p = o.getStyle('float');
            if (p == 'inherit' || p == 'none')p = 0;
            if (!p)p = o.getAttribute('align');
            return p;
        };
    })();
    i.image_removeLinkByEmptyURL = true;
    (function () {
        var m = {ol: 1, ul: 1}, n = d.walker.whitespaces(true), o = d.walker.bookmark(false, true);

        function p(t) {
            var B = this;
            if (t.editor.readOnly)return null;
            var u = t.editor, v = t.data.path, w = v && v.contains(m), x = v.block || v.blockLimit;
            if (w)return B.setState(2);
            if (!B.useIndentClasses && B.name == 'indent')return B.setState(2);
            if (!x)return B.setState(0);
            if (B.useIndentClasses) {
                var y = x.$.className.match(B.classNameRegex), z = 0;
                if (y) {
                    y = y[1];
                    z = B.indentClassMap[y];
                }
                if (B.name == 'outdent' && !z || B.name == 'indent' && z == u.config.indentClasses.length)return B.setState(0);
                return B.setState(2);
            } else {
                var A = parseInt(x.getStyle(r(x)), 10);
                if (isNaN(A))A = 0;
                if (A <= 0)return B.setState(0);
                return B.setState(2);
            }
        };
        function q(t, u) {
            var w = this;
            w.name = u;
            w.useIndentClasses = t.config.indentClasses && t.config.indentClasses.length > 0;
            if (w.useIndentClasses) {
                w.classNameRegex = new RegExp('(?:^|\\s+)(' + t.config.indentClasses.join('|') + ')(?=$|\\s)');
                w.indentClassMap = {};
                for (var v = 0; v < t.config.indentClasses.length; v++)w.indentClassMap[t.config.indentClasses[v]] = v + 1;
            }
            w.startDisabled = u == 'outdent';
        };
        function r(t, u) {
            return(u || t.getComputedStyle('direction')) == 'ltr' ? 'margin-left' : 'margin-right';
        };
        function s(t) {
            return t.type == 1 && t.is('li');
        };
        q.prototype = {exec: function (t) {
            var u = this, v = {};

            function w(M) {
                var N = C.startContainer, O = C.endContainer;
                while (N && !N.getParent().equals(M))N = N.getParent();
                while (O && !O.getParent().equals(M))O = O.getParent();
                if (!N || !O)return;
                var P = N, Q = [], R = false;
                while (!R) {
                    if (P.equals(O))R = true;
                    Q.push(P);
                    P = P.getNext();
                }
                if (Q.length < 1)return;
                var S = M.getParents(true);
                for (var T = 0; T < S.length; T++) {
                    if (S[T].getName && m[S[T].getName()]) {
                        M = S[T];
                        break;
                    }
                }
                var U = u.name == 'indent' ? 1 : -1, V = Q[0], W = Q[Q.length - 1], X = j.list.listToArray(M, v), Y = X[W.getCustomData('listarray_index')].indent;
                for (T = V.getCustomData('listarray_index'); T <= W.getCustomData('listarray_index'); T++) {
                    X[T].indent += U;
                    if (U > 0) {
                        var Z = X[T].parent;
                        X[T].parent = new h(Z.getName(), Z.getDocument());
                    }
                }
                for (T = W.getCustomData('listarray_index') + 1; T < X.length && X[T].indent > Y; T++)X[T].indent += U;
                var aa = j.list.arrayToList(X, v, null, t.config.enterMode, M.getDirection());
                if (u.name == 'outdent') {
                    var ab;
                    if ((ab = M.getParent()) && ab.is('li')) {
                        var ac = aa.listNode.getChildren(), ad = [], ae = ac.count(), af;
                        for (T = ae - 1; T >= 0; T--) {
                            if ((af = ac.getItem(T)) && af.is && af.is('li'))ad.push(af);
                        }
                    }
                }
                if (aa)aa.listNode.replace(M);
                if (ad && ad.length)for (T = 0; T < ad.length; T++) {
                    var ag = ad[T], ah = ag;
                    while ((ah = ah.getNext()) && ah.is && ah.getName() in m) {
                        if (c && !ag.getFirst(function (ai) {
                            return n(ai) && o(ai);
                        }))ag.append(C.document.createText('\xa0'));
                        ag.append(ah);
                    }
                    ag.insertAfter(ab);
                }
            };
            function x() {
                var M = C.createIterator(), N = t.config.enterMode;
                M.enforceRealBlocks = true;
                M.enlargeBr = N != 2;
                var O;
                while (O = M.getNextParagraph(N == 1 ? 'p' : 'div'))y(O);
            };
            function y(M, N) {
                if (M.getCustomData('indent_processed'))return false;
                if (u.useIndentClasses) {
                    var O = M.$.className.match(u.classNameRegex), P = 0;
                    if (O) {
                        O = O[1];
                        P = u.indentClassMap[O];
                    }
                    if (u.name == 'outdent')P--; else P++;
                    if (P < 0)return false;
                    P = Math.min(P, t.config.indentClasses.length);
                    P = Math.max(P, 0);
                    M.$.className = e.ltrim(M.$.className.replace(u.classNameRegex, ''));
                    if (P > 0)M.addClass(t.config.indentClasses[P - 1]);
                } else {
                    var Q = r(M, N), R = parseInt(M.getStyle(Q), 10);
                    if (isNaN(R))R = 0;
                    var S = t.config.indentOffset || 40;
                    R += (u.name == 'indent' ? 1 : -1) * S;
                    if (R < 0)return false;
                    R = Math.max(R, 0);
                    R = Math.ceil(R / S) * S;
                    M.setStyle(Q, R ? R + (t.config.indentUnit || 'px') : '');
                    if (M.getAttribute('style') === '')M.removeAttribute('style');
                }
                h.setMarker(v, M, 'indent_processed', 1);
                return true;
            };
            var z = t.getSelection(), A = z.createBookmarks(1), B = z && z.getRanges(1), C, D = B.createIterator();
            while (C = D.getNextRange()) {
                var E = C.getCommonAncestor(), F = E;
                while (F && !(F.type == 1 && m[F.getName()]))F = F.getParent();
                if (!F) {
                    var G = C.getEnclosedNode();
                    if (G && G.type == 1 && G.getName() in m) {
                        C.setStartAt(G, 1);
                        C.setEndAt(G, 2);
                        F = G;
                    }
                }
                if (F && C.startContainer.type == 1 && C.startContainer.getName() in m) {
                    var H = new d.walker(C);
                    H.evaluator = s;
                    C.startContainer = H.next();
                }
                if (F && C.endContainer.type == 1 && C.endContainer.getName() in m) {
                    H = new d.walker(C);
                    H.evaluator = s;
                    C.endContainer = H.previous();
                }
                if (F) {
                    var I = F.getFirst(s), J = !!I.getNext(s), K = C.startContainer, L = I.equals(K) || I.contains(K);
                    if (!(L && (u.name == 'indent' || u.useIndentClasses || parseInt(F.getStyle(r(F)), 10)) && y(F, !J && I.getDirection())))w(F);
                } else x();
            }
            h.clearAllMarkers(v);
            t.forceNextSelectionCheck();
            z.selectBookmarks(A);
        }};
        j.add('indent', {init: function (t) {
            var u = t.addCommand('indent', new q(t, 'indent')), v = t.addCommand('outdent', new q(t, 'outdent'));
            t.ui.addButton('Indent', {label: t.lang.indent, command: 'indent'});
            t.ui.addButton('Outdent', {label: t.lang.outdent, command: 'outdent'});
            t.on('selectionChange', e.bind(p, u));
            t.on('selectionChange', e.bind(p, v));
            if (b.ie6Compat || b.ie7Compat)t.addCss('ul,ol{\tmargin-left: 0px;\tpadding-left: 40px;}');
            t.on('dirChanged', function (w) {
                var x = new d.range(t.document);
                x.setStartBefore(w.data.node);
                x.setEndAfter(w.data.node);
                var y = new d.walker(x), z;
                while (z = y.next()) {
                    if (z.type == 1) {
                        if (!z.equals(w.data.node) && z.getDirection()) {
                            x.setStartAfter(z);
                            y = new d.walker(x);
                            continue;
                        }
                        var A = t.config.indentClasses;
                        if (A) {
                            var B = w.data.dir == 'ltr' ? ['_rtl', ''] : ['', '_rtl'];
                            for (var C = 0; C < A.length; C++) {
                                if (z.hasClass(A[C] + B[0])) {
                                    z.removeClass(A[C] + B[0]);
                                    z.addClass(A[C] + B[1]);
                                }
                            }
                        }
                        var D = z.getStyle('margin-right'), E = z.getStyle('margin-left');
                        D ? z.setStyle('margin-left', D) : z.removeStyle('margin-left');
                        E ? z.setStyle('margin-right', E) : z.removeStyle('margin-right');
                    }
                }
            });
        }, requires: ['domiterator', 'list']});
    })();
    (function () {
        function m(q, r) {
            r = r === undefined || r;
            var s;
            if (r)s = q.getComputedStyle('text-align'); else {
                while (!q.hasAttribute || !(q.hasAttribute('align') || q.getStyle('text-align'))) {
                    var t = q.getParent();
                    if (!t)break;
                    q = t;
                }
                s = q.getStyle('text-align') || q.getAttribute('align') || '';
            }
            s && (s = s.replace(/(?:-(?:moz|webkit)-)?(?:start|auto)/i, ''));
            !s && r && (s = q.getComputedStyle('direction') == 'rtl' ? 'right' : 'left');
            return s;
        };
        function n(q) {
            if (q.editor.readOnly)return;
            q.editor.getCommand(this.name).refresh(q.data.path);
        };
        function o(q, r, s) {
            var u = this;
            u.editor = q;
            u.name = r;
            u.value = s;
            var t = q.config.justifyClasses;
            if (t) {
                switch (s) {
                    case 'left':
                        u.cssClassName = t[0];
                        break;
                    case 'center':
                        u.cssClassName = t[1];
                        break;
                    case 'right':
                        u.cssClassName = t[2];
                        break;
                    case 'justify':
                        u.cssClassName = t[3];
                        break;
                }
                u.cssClassRegex = new RegExp('(?:^|\\s+)(?:' + t.join('|') + ')(?=$|\\s)');
            }
        };
        function p(q) {
            var r = q.editor, s = new d.range(r.document);
            s.setStartBefore(q.data.node);
            s.setEndAfter(q.data.node);
            var t = new d.walker(s), u;
            while (u = t.next()) {
                if (u.type == 1) {
                    if (!u.equals(q.data.node) && u.getDirection()) {
                        s.setStartAfter(u);
                        t = new d.walker(s);
                        continue;
                    }
                    var v = r.config.justifyClasses;
                    if (v)if (u.hasClass(v[0])) {
                        u.removeClass(v[0]);
                        u.addClass(v[2]);
                    } else if (u.hasClass(v[2])) {
                        u.removeClass(v[2]);
                        u.addClass(v[0]);
                    }
                    var w = 'text-align', x = u.getStyle(w);
                    if (x == 'left')u.setStyle(w, 'right'); else if (x == 'right')u.setStyle(w, 'left');
                }
            }
        };
        o.prototype = {exec: function (q) {
            var C = this;
            var r = q.getSelection(), s = q.config.enterMode;
            if (!r)return;
            var t = r.createBookmarks(), u = r.getRanges(true), v = C.cssClassName, w, x, y = q.config.useComputedState;
            y = y === undefined || y;
            for (var z = u.length - 1; z >= 0; z--) {
                w = u[z].createIterator();
                w.enlargeBr = s != 2;
                while (x = w.getNextParagraph(s == 1 ? 'p' : 'div')) {
                    x.removeAttribute('align');
                    x.removeStyle('text-align');
                    var A = v && (x.$.className = e.ltrim(x.$.className.replace(C.cssClassRegex, ''))), B = C.state == 2 && (!y || m(x, true) != C.value);
                    if (v) {
                        if (B)x.addClass(v); else if (!A)x.removeAttribute('class');
                    } else if (B)x.setStyle('text-align', C.value);
                }
            }
            q.focus();
            q.forceNextSelectionCheck();
            r.selectBookmarks(t);
        }, refresh: function (q) {
            var r = q.block || q.blockLimit;
            this.setState(r.getName() != 'body' && m(r, this.editor.config.useComputedState) == this.value ? 1 : 2);
        }};
        j.add('justify', {init: function (q) {
            var r = new o(q, 'justifyleft', 'left'), s = new o(q, 'justifycenter', 'center'), t = new o(q, 'justifyright', 'right'), u = new o(q, 'justifyblock', 'justify');
            q.addCommand('justifyleft', r);
            q.addCommand('justifycenter', s);
            q.addCommand('justifyright', t);
            q.addCommand('justifyblock', u);
            q.ui.addButton('JustifyLeft', {label: q.lang.justify.left, command: 'justifyleft'});
            q.ui.addButton('JustifyCenter', {label: q.lang.justify.center, command: 'justifycenter'});
            q.ui.addButton('JustifyRight', {label: q.lang.justify.right, command: 'justifyright'});
            q.ui.addButton('JustifyBlock', {label: q.lang.justify.block, command: 'justifyblock'});
            q.on('selectionChange', e.bind(n, r));
            q.on('selectionChange', e.bind(n, t));
            q.on('selectionChange', e.bind(n, s));
            q.on('selectionChange', e.bind(n, u));
            q.on('dirChanged', p);
        }, requires: ['domiterator']});
    })();
    j.add('keystrokes', {beforeInit: function (m) {
        m.keystrokeHandler = new a.keystrokeHandler(m);
        m.specialKeys = {};
    }, init: function (m) {
        var n = m.config.keystrokes, o = m.config.blockedKeystrokes, p = m.keystrokeHandler.keystrokes, q = m.keystrokeHandler.blockedKeystrokes;
        for (var r = 0; r < n.length; r++)p[n[r][0]] = n[r][1];
        for (r = 0; r < o.length; r++)q[o[r]] = 1;
    }});
    a.keystrokeHandler = function (m) {
        var n = this;
        if (m.keystrokeHandler)return m.keystrokeHandler;
        n.keystrokes = {};
        n.blockedKeystrokes = {};
        n._ = {editor: m};
        return n;
    };
    (function () {
        var m, n = function (p) {
            p = p.data;
            var q = p.getKeystroke(), r = this.keystrokes[q], s = this._.editor;
            m = s.fire('key', {keyCode: q}) === true;
            if (!m) {
                if (r) {
                    var t = {from: 'keystrokeHandler'};
                    m = s.execCommand(r, t) !== false;
                }
                if (!m) {
                    var u = s.specialKeys[q];
                    m = u && u(s) === true;
                    if (!m)m = !!this.blockedKeystrokes[q];
                }
            }
            if (m)p.preventDefault(true);
            return!m;
        }, o = function (p) {
            if (m) {
                m = false;
                p.data.preventDefault(true);
            }
        };
        a.keystrokeHandler.prototype = {attach: function (p) {
            p.on('keydown', n, this);
            if (b.opera || b.gecko && b.mac)p.on('keypress', o, this);
        }};
    })();
    i.blockedKeystrokes = [1114112 + 66, 1114112 + 73, 1114112 + 85];
    i.keystrokes = [
        [4456448 + 121, 'toolbarFocus'],
        [4456448 + 122, 'elementsPathFocus'],
        [2228224 + 121, 'contextMenu'],
        [1114112 + 2228224 + 121, 'contextMenu'],
        [1114112 + 90, 'undo'],
        [1114112 + 89, 'redo'],
        [1114112 + 2228224 + 90, 'redo'],
        [1114112 + 76, 'link'],
        [1114112 + 66, 'bold'],
        [1114112 + 73, 'italic'],
        [1114112 + 85, 'underline'],
        [4456448 + (c || b.webkit ? 189 : 109), 'toolbarCollapse'],
        [4456448 + 48, 'a11yHelp']
    ];
    j.add('link', {requires: ['fakeobjects', 'dialog'], init: function (m) {
        m.addCommand('link', new a.dialogCommand('link'));
        m.addCommand('anchor', new a.dialogCommand('anchor'));
        m.addCommand('unlink', new a.unlinkCommand());
        m.addCommand('removeAnchor', new a.removeAnchorCommand());
        m.ui.addButton('Link', {label: m.lang.link.toolbar, command: 'link'});
        m.ui.addButton('Unlink', {label: m.lang.unlink, command: 'unlink'});
        m.ui.addButton('Anchor', {label: m.lang.anchor.toolbar, command: 'anchor'});
        a.dialog.add('link', this.path + 'dialogs/link.js');
        a.dialog.add('anchor', this.path + 'dialogs/anchor.js');
        var n = m.lang.dir == 'rtl' ? 'right' : 'left', o = 'background:url(' + a.getUrl(this.path + 'images/anchor.gif') + ') no-repeat ' + n + ' center;' + 'border:1px dotted #00f;';
        m.addCss('a.cke_anchor,a.cke_anchor_empty' + (c && b.version < 7 ? '' : ',a[name],a[data-cke-saved-name]') + '{' + o + 'padding-' + n + ':18px;' + 'cursor:auto;' + '}' + (c ? 'a.cke_anchor_empty{display:inline-block;}' : '') + 'img.cke_anchor' + '{' + o + 'width:16px;' + 'min-height:15px;' + 'height:1.15em;' + 'vertical-align:' + (b.opera ? 'middle' : 'text-bottom') + ';' + '}');
        m.on('selectionChange', function (p) {
            if (m.readOnly)return;
            var q = m.getCommand('unlink'), r = p.data.path.lastElement && p.data.path.lastElement.getAscendant('a', true);
            if (r && r.getName() == 'a' && r.getAttribute('href') && r.getChildCount())q.setState(2); else q.setState(0);
        });
        m.on('doubleclick', function (p) {
            var q = j.link.getSelectedLink(m) || p.data.element;
            if (!q.isReadOnly())if (q.is('a')) {
                p.data.dialog = q.getAttribute('name') && (!q.getAttribute('href') || !q.getChildCount()) ? 'anchor' : 'link';
                m.getSelection().selectElement(q);
            } else if (j.link.tryRestoreFakeAnchor(m, q))p.data.dialog = 'anchor';
        });
        if (m.addMenuItems)m.addMenuItems({anchor: {label: m.lang.anchor.menu, command: 'anchor', group: 'anchor', order: 1}, removeAnchor: {label: m.lang.anchor.remove, command: 'removeAnchor', group: 'anchor', order: 5}, link: {label: m.lang.link.menu, command: 'link', group: 'link', order: 1}, unlink: {label: m.lang.unlink, command: 'unlink', group: 'link', order: 5}});
        if (m.contextMenu)m.contextMenu.addListener(function (p, q) {
            if (!p || p.isReadOnly())return null;
            var r = j.link.tryRestoreFakeAnchor(m, p);
            if (!r && !(r = j.link.getSelectedLink(m)))return null;
            var s = {};
            if (r.getAttribute('href') && r.getChildCount())s = {link: 2, unlink: 2};
            if (r && r.hasAttribute('name'))s.anchor = s.removeAnchor = 2;
            return s;
        });
    }, afterInit: function (m) {
        var n = m.dataProcessor, o = n && n.dataFilter, p = n && n.htmlFilter, q = m._.elementsPath && m._.elementsPath.filters;
        if (o)o.addRules({elements: {a: function (r) {
            var s = r.attributes;
            if (!s.name)return null;
            var t = !r.children.length;
            if (j.link.synAnchorSelector) {
                var u = t ? 'cke_anchor_empty' : 'cke_anchor', v = s['class'];
                if (s.name && (!v || v.indexOf(u) < 0))s['class'] = (v || '') + ' ' + u;
                if (t && j.link.emptyAnchorFix) {
                    s.contenteditable = 'false';
                    s['data-cke-editable'] = 1;
                }
            } else if (j.link.fakeAnchor && t)return m.createFakeParserElement(r, 'cke_anchor', 'anchor');
            return null;
        }}});
        if (j.link.emptyAnchorFix && p)p.addRules({elements: {a: function (r) {
            delete r.attributes.contenteditable;
        }}});
        if (q)q.push(function (r, s) {
            if (s == 'a')if (j.link.tryRestoreFakeAnchor(m, r) || r.getAttribute('name') && (!r.getAttribute('href') || !r.getChildCount()))return 'anchor';
        });
    }});
    j.link = {getSelectedLink: function (m) {
        try {
            var n = m.getSelection();
            if (n.getType() == 3) {
                var o = n.getSelectedElement();
                if (o.is('a'))return o;
            }
            var p = n.getRanges(true)[0];
            p.shrink(2);
            var q = p.getCommonAncestor();
            return q.getAscendant('a', true);
        } catch (r) {
            return null;
        }
    }, fakeAnchor: b.opera || b.webkit, synAnchorSelector: c, emptyAnchorFix: c && b.version < 8, tryRestoreFakeAnchor: function (m, n) {
        if (n && n.data('cke-real-element-type') && n.data('cke-real-element-type') == 'anchor') {
            var o = m.restoreRealElement(n);
            if (o.data('cke-saved-name'))return o;
        }
    }};
    a.unlinkCommand = function () {
    };
    a.unlinkCommand.prototype = {exec: function (m) {
        var n = m.getSelection(), o = n.createBookmarks(), p = n.getRanges(), q, r;
        for (var s = 0; s < p.length; s++) {
            q = p[s].getCommonAncestor(true);
            r = q.getAscendant('a', true);
            if (!r)continue;
            p[s].selectNodeContents(r);
        }
        n.selectRanges(p);
        m.document.$.execCommand('unlink', false, null);
        n.selectBookmarks(o);
    }, startDisabled: true};
    a.removeAnchorCommand = function () {
    };
    a.removeAnchorCommand.prototype = {exec: function (m) {
        var n = m.getSelection(), o = n.createBookmarks(), p;
        if (n && (p = n.getSelectedElement()) && (j.link.fakeAnchor && !p.getChildCount() ? j.link.tryRestoreFakeAnchor(m, p) : p.is('a')))p.remove(1); else if (p = j.link.getSelectedLink(m))if (p.hasAttribute('href')) {
            p.removeAttributes({name: 1, 'data-cke-saved-name': 1});
            p.removeClass('cke_anchor');
        } else p.remove(1);
        n.selectBookmarks(o);
    }};
    e.extend(i, {linkShowAdvancedTab: true, linkShowTargetTab: true});
    (function () {
        var m = {ol: 1, ul: 1}, n = /^[\n\r\t ]*$/, o = d.walker.whitespaces(), p = d.walker.bookmark(), q = function (N) {
            return!(o(N) || p(N));
        }, r = d.walker.bogus();

        function s(N) {
            var O, P, Q;
            if (O = N.getDirection()) {
                P = N.getParent();
                while (P && !(Q = P.getDirection()))P = P.getParent();
                if (O == Q)N.removeAttribute('dir');
            }
        };
        function t(N, O) {
            var P = N.getAttribute('style');
            P && O.setAttribute('style', P.replace(/([^;])$/, '$1;') + (O.getAttribute('style') || ''));
        };
        j.list = {listToArray: function (N, O, P, Q, R) {
            if (!m[N.getName()])return[];
            if (!Q)Q = 0;
            if (!P)P = [];
            for (var S = 0, T = N.getChildCount(); S < T; S++) {
                var U = N.getChild(S);
                if (U.type == 1 && U.getName() in f.$list)j.list.listToArray(U, O, P, Q + 1);
                if (U.$.nodeName.toLowerCase() != 'li')continue;
                var V = {parent: N, indent: Q, element: U, contents: []};
                if (!R) {
                    V.grandparent = N.getParent();
                    if (V.grandparent && V.grandparent.$.nodeName.toLowerCase() == 'li')V.grandparent = V.grandparent.getParent();
                } else V.grandparent = R;
                if (O)h.setMarker(O, U, 'listarray_index', P.length);
                P.push(V);
                for (var W = 0, X = U.getChildCount(), Y; W < X; W++) {
                    Y = U.getChild(W);
                    if (Y.type == 1 && m[Y.getName()])j.list.listToArray(Y, O, P, Q + 1, V.grandparent); else V.contents.push(Y);
                }
            }
            return P;
        }, arrayToList: function (N, O, P, Q, R) {
            if (!P)P = 0;
            if (!N || N.length < P + 1)return null;
            var S, T = N[P].parent.getDocument(), U = new d.documentFragment(T), V = null, W = P, X = Math.max(N[P].indent, 0), Y = null, Z, aa, ab = Q == 1 ? 'p' : 'div';
            while (1) {
                var ac = N[W], ad = ac.grandparent;
                Z = ac.element.getDirection(1);
                if (ac.indent == X) {
                    if (!V || N[W].parent.getName() != V.getName()) {
                        V = N[W].parent.clone(false, 1);
                        R && V.setAttribute('dir', R);
                        U.append(V);
                    }
                    Y = V.append(ac.element.clone(0, 1));
                    if (Z != V.getDirection(1))Y.setAttribute('dir', Z);
                    for (S = 0; S < ac.contents.length; S++)Y.append(ac.contents[S].clone(1, 1));
                    W++;
                } else if (ac.indent == Math.max(X, 0) + 1) {
                    var ae = N[W - 1].element.getDirection(1), af = j.list.arrayToList(N, null, W, Q, ae != Z ? Z : null);
                    if (!Y.getChildCount() && c && !(T.$.documentMode > 7))Y.append(T.createText('\xa0'));
                    Y.append(af.listNode);
                    W = af.nextIndex;
                } else if (ac.indent == -1 && !P && ad) {
                    if (m[ad.getName()]) {
                        Y = ac.element.clone(false, true);
                        if (Z != ad.getDirection(1))Y.setAttribute('dir', Z);
                    } else Y = new d.documentFragment(T);
                    var ag = ad.getDirection(1) != Z, ah = ac.element, ai = ah.getAttribute('class'), aj = ah.getAttribute('style'), ak = Y.type == 11 && (Q != 2 || ag || aj || ai), al, am = ac.contents.length;
                    for (S = 0; S < am; S++) {
                        al = ac.contents[S];
                        if (al.type == 1 && al.isBlockBoundary()) {
                            if (ag && !al.getDirection())al.setAttribute('dir', Z);
                            t(ah, al);
                            ai && al.addClass(ai);
                        } else if (ak) {
                            if (!aa) {
                                aa = T.createElement(ab);
                                ag && aa.setAttribute('dir', Z);
                            }
                            aj && aa.setAttribute('style', aj);
                            ai && aa.setAttribute('class', ai);
                            aa.append(al.clone(1, 1));
                        }
                        Y.append(aa || al.clone(1, 1));
                    }
                    if (Y.type == 11 && W != N.length - 1) {
                        var an = Y.getLast();
                        if (an && an.type == 1 && an.getAttribute('type') == '_moz')an.remove();
                        if (!(an = Y.getLast(q) && an.type == 1 && an.getName() in f.$block))Y.append(T.createElement('br'));
                    }
                    var ao = Y.$.nodeName.toLowerCase();
                    if (!c && (ao == 'div' || ao == 'p'))Y.appendBogus();
                    U.append(Y);
                    V = null;
                    W++;
                } else return null;
                aa = null;
                if (N.length <= W || Math.max(N[W].indent, 0) < X)break;
            }
            if (O) {
                var ap = U.getFirst(), aq = N[0].parent;
                while (ap) {
                    if (ap.type == 1) {
                        h.clearMarkers(O, ap);
                        if (ap.getName() in f.$listItem)s(ap);
                    }
                    ap = ap.getNextSourceNode();
                }
            }
            return{listNode: U, nextIndex: W};
        }};
        function u(N) {
            if (N.editor.readOnly)return null;
            var O = N.data.path, P = O.blockLimit, Q = O.elements, R, S;
            for (S = 0; S < Q.length && (R = Q[S]) && !R.equals(P); S++) {
                if (m[Q[S].getName()])return this.setState(this.type == Q[S].getName() ? 1 : 2);
            }
            return this.setState(2);
        };
        function v(N, O, P, Q) {
            var R = j.list.listToArray(O.root, P), S = [];
            for (var T = 0; T < O.contents.length; T++) {
                var U = O.contents[T];
                U = U.getAscendant('li', true);
                if (!U || U.getCustomData('list_item_processed'))continue;
                S.push(U);
                h.setMarker(P, U, 'list_item_processed', true);
            }
            var V = O.root, W = V.getDocument(), X, Y;
            for (T = 0; T < S.length; T++) {
                var Z = S[T].getCustomData('listarray_index');
                X = R[Z].parent;
                if (!X.is(this.type)) {
                    Y = W.createElement(this.type);
                    X.copyAttributes(Y, {start: 1, type: 1});
                    Y.removeStyle('list-style-type');
                    R[Z].parent = Y;
                }
            }
            var aa = j.list.arrayToList(R, P, null, N.config.enterMode), ab, ac = aa.listNode.getChildCount();
            for (T = 0; T < ac && (ab = aa.listNode.getChild(T)); T++) {
                if (ab.getName() == this.type)Q.push(ab);
            }
            aa.listNode.replace(O.root);
        };
        var w = /^h[1-6]$/;

        function x(N, O, P) {
            var Q = O.contents, R = O.root.getDocument(), S = [];
            if (Q.length == 1 && Q[0].equals(O.root)) {
                var T = R.createElement('div');
                Q[0].moveChildren && Q[0].moveChildren(T);
                Q[0].append(T);
                Q[0] = T;
            }
            var U = O.contents[0].getParent();
            for (var V = 0; V < Q.length; V++)U = U.getCommonAncestor(Q[V].getParent());
            var W = N.config.useComputedState, X, Y;
            W = W === undefined || W;
            for (V = 0; V < Q.length; V++) {
                var Z = Q[V], aa;
                while (aa = Z.getParent()) {
                    if (aa.equals(U)) {
                        S.push(Z);
                        if (!Y && Z.getDirection())Y = 1;
                        var ab = Z.getDirection(W);
                        if (X !== null)if (X && X != ab)X = null; else X = ab;
                        break;
                    }
                    Z = aa;
                }
            }
            if (S.length < 1)return;
            var ac = S[S.length - 1].getNext(), ad = R.createElement(this.type);
            P.push(ad);
            var ae, af;
            while (S.length) {
                ae = S.shift();
                af = R.createElement('li');
                if (ae.is('pre') || w.test(ae.getName()))ae.appendTo(af); else {
                    ae.copyAttributes(af);
                    if (X && ae.getDirection()) {
                        af.removeStyle('direction');
                        af.removeAttribute('dir');
                    }
                    ae.moveChildren(af);
                    ae.remove();
                }
                af.appendTo(ad);
            }
            if (X && Y)ad.setAttribute('dir', X);
            if (ac)ad.insertBefore(ac); else ad.appendTo(U);
        };
        function y(N, O, P) {
            var Q = j.list.listToArray(O.root, P), R = [];
            for (var S = 0; S < O.contents.length; S++) {
                var T = O.contents[S];
                T = T.getAscendant('li', true);
                if (!T || T.getCustomData('list_item_processed'))continue;
                R.push(T);
                h.setMarker(P, T, 'list_item_processed', true);
            }
            var U = null;
            for (S = 0; S < R.length; S++) {
                var V = R[S].getCustomData('listarray_index');
                Q[V].indent = -1;
                U = V;
            }
            for (S = U + 1; S < Q.length; S++) {
                if (Q[S].indent > Q[S - 1].indent + 1) {
                    var W = Q[S - 1].indent + 1 - Q[S].indent, X = Q[S].indent;
                    while (Q[S] && Q[S].indent >= X) {
                        Q[S].indent += W;
                        S++;
                    }
                    S--;
                }
            }
            var Y = j.list.arrayToList(Q, P, null, N.config.enterMode, O.root.getAttribute('dir')), Z = Y.listNode, aa, ab;

            function ac(ad) {
                if ((aa = Z[ad ? 'getFirst' : 'getLast']()) && !(aa.is && aa.isBlockBoundary()) && (ab = O.root[ad ? 'getPrevious' : 'getNext'](d.walker.whitespaces(true))) && !(ab.is && ab.isBlockBoundary({br: 1})))N.document.createElement('br')[ad ? 'insertBefore' : 'insertAfter'](aa);
            };
            ac(true);
            ac();
            Z.replace(O.root);
        };
        function z(N, O) {
            this.name = N;
            this.type = O;
        };
        var A = d.walker.nodeType(1);

        function B(N, O, P, Q) {
            var R, S;
            while (R = N[Q ? 'getLast' : 'getFirst'](A)) {
                if ((S = R.getDirection(1)) !== O.getDirection(1))R.setAttribute('dir', S);
                R.remove();
                P ? R[Q ? 'insertBefore' : 'insertAfter'](P) : O.append(R, Q);
            }
        };
        z.prototype = {exec: function (N) {
            var aq = this;
            var O = N.document, P = N.config, Q = N.getSelection(), R = Q && Q.getRanges(true);
            if (!R || R.length < 1)return;
            if (aq.state == 2) {
                var S = O.getBody();
                if (!S.getFirst(q)) {
                    P.enterMode == 2 ? S.appendBogus() : R[0].fixBlock(1, P.enterMode == 1 ? 'p' : 'div');
                    Q.selectRanges(R);
                } else {
                    var T = R.length == 1 && R[0], U = T && T.getEnclosedNode();
                    if (U && U.is && aq.type == U.getName())aq.setState(1);
                }
            }
            var V = Q.createBookmarks(true), W = [], X = {}, Y = R.createIterator(), Z = 0;
            while ((T = Y.getNextRange()) && ++Z) {
                var aa = T.getBoundaryNodes(), ab = aa.startNode, ac = aa.endNode;
                if (ab.type == 1 && ab.getName() == 'td')T.setStartAt(aa.startNode, 1);
                if (ac.type == 1 && ac.getName() == 'td')T.setEndAt(aa.endNode, 2);
                var ad = T.createIterator(), ae;
                ad.forceBrBreak = aq.state == 2;
                while (ae = ad.getNextParagraph()) {
                    if (ae.getCustomData('list_block'))continue; else h.setMarker(X, ae, 'list_block', 1);
                    var af = new d.elementPath(ae), ag = af.elements, ah = ag.length, ai = null, aj = 0, ak = af.blockLimit, al;
                    for (var am = ah - 1; am >= 0 && (al = ag[am]); am--) {
                        if (m[al.getName()] && ak.contains(al)) {
                            ak.removeCustomData('list_group_object_' + Z);
                            var an = al.getCustomData('list_group_object');
                            if (an)an.contents.push(ae); else {
                                an = {root: al, contents: [ae]};
                                W.push(an);
                                h.setMarker(X, al, 'list_group_object', an);
                            }
                            aj = 1;
                            break;
                        }
                    }
                    if (aj)continue;
                    var ao = ak;
                    if (ao.getCustomData('list_group_object_' + Z))ao.getCustomData('list_group_object_' + Z).contents.push(ae); else {
                        an = {root: ao, contents: [ae]};
                        h.setMarker(X, ao, 'list_group_object_' + Z, an);
                        W.push(an);
                    }
                }
            }
            var ap = [];
            while (W.length > 0) {
                an = W.shift();
                if (aq.state == 2) {
                    if (m[an.root.getName()])v.call(aq, N, an, X, ap); else x.call(aq, N, an, ap);
                } else if (aq.state == 1 && m[an.root.getName()])y.call(aq, N, an, X);
            }
            for (am = 0; am < ap.length; am++)C(ap[am]);
            h.clearAllMarkers(X);
            Q.selectBookmarks(V);
            N.focus();
        }};
        function C(N) {
            var O;
            (O = function (P) {
                var Q = N[P ? 'getPrevious' : 'getNext'](q);
                if (Q && Q.type == 1 && Q.is(N.getName())) {
                    B(N, Q, null, !P);
                    N.remove();
                    N = Q;
                }
            })();
            O(1);
        };
        var D = f, E = /[\t\r\n ]*(?:&nbsp;|\xa0)$/;

        function F(N, O) {
            var P, Q = N.children, R = Q.length;
            for (var S = 0; S < R; S++) {
                P = Q[S];
                if (P.name && P.name in O)return S;
            }
            return R;
        };
        function G(N) {
            return function (O) {
                var P = O.children, Q = F(O, D.$list), R = P[Q], S = R && R.previous, T;
                if (S && (S.name && S.name == 'br' || S.value && (T = S.value.match(E)))) {
                    var U = S;
                    if (!(T && T.index) && U == P[0])P[0] = N || c ? new a.htmlParser.text('\xa0') : new a.htmlParser.element('br', {});
                    else if (U.name == 'br')P.splice(Q - 1, 1); else U.value = U.value.replace(E, '');
                }
            };
        };
        var H = {elements: {}};
        for (var I in D.$listItem)H.elements[I] = G();
        var J = {elements: {}};
        for (I in D.$listItem)J.elements[I] = G(true);
        function K(N) {
            return N.type == 1 && (N.getName() in f.$block || N.getName() in f.$listItem) && f[N.getName()]['#'];
        };
        function L(N, O, P) {
            N.fire('saveSnapshot');
            P.enlarge(3);
            var Q = P.extractContents();
            O.trim(false, true);
            var R = O.createBookmark(), S = new d.elementPath(O.startContainer), T = S.block, U = S.lastElement.getAscendant('li', 1) || T, V = new d.elementPath(P.startContainer), W = V.contains(f.$listItem), X = V.contains(f.$list), Y;
            if (T) {
                var Z = T.getBogus();
                Z && Z.remove();
            } else if (X) {
                Y = X.getPrevious(q);
                if (Y && r(Y))Y.remove();
            }
            Y = Q.getLast();
            if (Y && Y.type == 1 && Y.is('br'))Y.remove();
            var aa = O.startContainer.getChild(O.startOffset);
            if (aa)Q.insertBefore(aa); else O.startContainer.append(Q);
            if (W) {
                var ab = M(W);
                if (ab)if (U.contains(W)) {
                    B(ab, W.getParent(), W);
                    ab.remove();
                } else U.append(ab);
            }
            while (P.checkStartOfBlock() && P.checkEndOfBlock()) {
                V = new d.elementPath(P.startContainer);
                var ac = V.block, ad;
                if (ac.is('li')) {
                    ad = ac.getParent();
                    if (ac.equals(ad.getLast(q)) && ac.equals(ad.getFirst(q)))ac = ad;
                }
                P.moveToPosition(ac, 3);
                ac.remove();
            }
            var ae = P.clone(), af = N.document.getBody();
            ae.setEndAt(af, 2);
            var ag = new d.walker(ae);
            ag.evaluator = function (ai) {
                return q(ai) && !r(ai);
            };
            var ah = ag.next();
            if (ah && ah.type == 1 && ah.getName() in f.$list)C(ah);
            O.moveToBookmark(R);
            O.select();
            N.selectionChange(1);
            N.fire('saveSnapshot');
        };
        function M(N) {
            var O = N.getLast(q);
            return O && O.type == 1 && O.getName() in m ? O : null;
        };
        j.add('list', {init: function (N) {
            var O = N.addCommand('numberedlist', new z('numberedlist', 'ol')), P = N.addCommand('bulletedlist', new z('bulletedlist', 'ul'));
            N.ui.addButton('NumberedList', {label: N.lang.numberedlist, command: 'numberedlist'});
            N.ui.addButton('BulletedList', {label: N.lang.bulletedlist, command: 'bulletedlist'});
            N.on('selectionChange', e.bind(u, O));
            N.on('selectionChange', e.bind(u, P));
            N.on('key', function (Q) {
                var R = Q.data.keyCode;
                if (N.mode == 'wysiwyg' && R in {8: 1, 46: 1}) {
                    var S = N.getSelection(), T = S.getRanges()[0];
                    if (!T.collapsed)return;
                    var U = new d.elementPath(T.startContainer), V = R == 8, W = N.document.getBody(), X = new d.walker(T.clone());
                    X.evaluator = function (ai) {
                        return q(ai) && !r(ai);
                    };
                    X.guard = function (ai, aj) {
                        return!(aj && ai.type == 1 && ai.is('table'));
                    };
                    var Y = T.clone();
                    if (V) {
                        var Z, aa;
                        if ((Z = U.contains(m)) && T.checkBoundaryOfElement(Z, 1) && (Z = Z.getParent()) && Z.is('li') && (Z = M(Z))) {
                            aa = Z;
                            Z = Z.getPrevious(q);
                            Y.moveToPosition(Z && r(Z) ? Z : aa, 3);
                        } else {
                            X.range.setStartAt(W, 1);
                            X.range.setEnd(T.startContainer, T.startOffset);
                            Z = X.previous();
                            if (Z && Z.type == 1 && (Z.getName() in m || Z.is('li'))) {
                                if (!Z.is('li')) {
                                    X.range.selectNodeContents(Z);
                                    X.reset();
                                    X.evaluator = K;
                                    Z = X.previous();
                                }
                                aa = Z;
                                Y.moveToElementEditEnd(aa);
                            }
                        }
                        if (aa) {
                            L(N, Y, T);
                            Q.cancel();
                        } else {
                            var ab = U.contains(m), ac;
                            if (ab && T.checkBoundaryOfElement(ab, 1)) {
                                ac = ab.getFirst(q);
                                if (T.checkBoundaryOfElement(ac, 1)) {
                                    Z = ab.getPrevious(q);
                                    if (M(ac)) {
                                        if (Z) {
                                            T.moveToElementEditEnd(Z);
                                            T.select();
                                        }
                                        Q.cancel();
                                    } else {
                                        N.execCommand('outdent');
                                        Q.cancel();
                                    }
                                }
                            }
                        }
                    } else {
                        var ad, ae;
                        ac = T.startContainer.getAscendant('li', 1);
                        if (ac) {
                            X.range.setEndAt(W, 2);
                            var af = ac.getLast(q), ag = af && K(af) ? af : ac, ah = 0;
                            ad = X.next();
                            if (ad && ad.type == 1 && ad.getName() in m && ad.equals(af)) {
                                ah = 1;
                                ad = X.next();
                            } else if (T.checkBoundaryOfElement(ag, 2))ah = 1;
                            if (ah && ad) {
                                ae = T.clone();
                                ae.moveToElementEditStart(ad);
                                L(N, Y, ae);
                                Q.cancel();
                            }
                        } else {
                            X.range.setEndAt(W, 2);
                            ad = X.next();
                            if (ad && ad.type == 1 && ad.getName() in m) {
                                ad = ad.getFirst(q);
                                if (U.block && T.checkStartOfBlock() && T.checkEndOfBlock()) {
                                    U.block.remove();
                                    T.moveToElementEditStart(ad);
                                    T.select();
                                    Q.cancel();
                                } else if (M(ad)) {
                                    T.moveToElementEditStart(ad);
                                    T.select();
                                    Q.cancel();
                                } else {
                                    ae = T.clone();
                                    ae.moveToElementEditStart(ad);
                                    L(N, Y, ae);
                                    Q.cancel();
                                }
                            }
                        }
                    }
                    setTimeout(function () {
                        N.selectionChange(1);
                    });
                }
            });
        }, afterInit: function (N) {
            var O = N.dataProcessor;
            if (O) {
                O.dataFilter.addRules(H);
                O.htmlFilter.addRules(J);
            }
        }, requires: ['domiterator']});
    })();
    (function () {
        j.liststyle = {requires: ['dialog'], init: function (m) {
            m.addCommand('numberedListStyle', new a.dialogCommand('numberedListStyle'));
            a.dialog.add('numberedListStyle', this.path + 'dialogs/liststyle.js');
            m.addCommand('bulletedListStyle', new a.dialogCommand('bulletedListStyle'));
            a.dialog.add('bulletedListStyle', this.path + 'dialogs/liststyle.js');
            if (m.addMenuItems) {
                m.addMenuGroup('list', 108);
                m.addMenuItems({numberedlist: {label: m.lang.list.numberedTitle, group: 'list', command: 'numberedListStyle'}, bulletedlist: {label: m.lang.list.bulletedTitle, group: 'list', command: 'bulletedListStyle'}});
            }
            if (m.contextMenu)m.contextMenu.addListener(function (n, o) {
                if (!n || n.isReadOnly())return null;
                while (n) {
                    var p = n.getName();
                    if (p == 'ol')return{numberedlist: 2}; else if (p == 'ul')return{bulletedlist: 2};
                    n = n.getParent();
                }
                return null;
            });
        }};
        j.add('liststyle', j.liststyle);
    })();
    (function () {
        function m(s) {
            if (!s || s.type != 1 || s.getName() != 'form')return[];
            var t = [], u = ['style', 'className'];
            for (var v = 0; v < u.length; v++) {
                var w = u[v], x = s.$.elements.namedItem(w);
                if (x) {
                    var y = new h(x);
                    t.push([y, y.nextSibling]);
                    y.remove();
                }
            }
            return t;
        };
        function n(s, t) {
            if (!s || s.type != 1 || s.getName() != 'form')return;
            if (t.length > 0)for (var u = t.length - 1; u >= 0; u--) {
                var v = t[u][0], w = t[u][1];
                if (w)v.insertBefore(w); else v.appendTo(s);
            }
        };
        function o(s, t) {
            var u = m(s), v = {}, w = s.$;
            if (!t) {
                v['class'] = w.className || '';
                w.className = '';
            }
            v.inline = w.style.cssText || '';
            if (!t)w.style.cssText = 'position: static; overflow: visible';
            n(u);
            return v;
        };
        function p(s, t) {
            var u = m(s), v = s.$;
            if ('class' in t)v.className = t['class'];
            if ('inline' in t)v.style.cssText = t.inline;
            n(u);
        };
        function q(s) {
            var t = a.instances;
            for (var u in t) {
                var v = t[u];
                if (v.mode == 'wysiwyg' && !v.readOnly) {
                    var w = v.document.getBody();
                    w.setAttribute('contentEditable', false);
                    w.setAttribute('contentEditable', true);
                }
            }
            if (s.focusManager.hasFocus) {
                s.toolbox.focus();
                s.focus();
            }
        };
        function r(s) {
            if (!c || b.version > 6)return null;
            var t = h.createFromHtml('<iframe frameborder="0" tabindex="-1" src="javascript:void((function(){document.open();' + (b.isCustomDomain() ? "document.domain='" + this.getDocument().$.domain + "';" : '') + 'document.close();' + '})())"' + ' style="display:block;position:absolute;z-index:-1;' + 'progid:DXImageTransform.Microsoft.Alpha(opacity=0);' + '"></iframe>');
            return s.append(t, true);
        };
        j.add('maximize', {init: function (s) {
            var t = s.lang, u = a.document, v = u.getWindow(), w, x, y, z;

            function A() {
                var C = v.getViewPaneSize();
                z && z.setStyles({width: C.width + 'px', height: C.height + 'px'});
                s.resize(C.width, C.height, null, true);
            };
            var B = 2;
            s.addCommand('maximize', {modes: {wysiwyg: !b.iOS, source: !b.iOS}, readOnly: 1, editorFocus: false, exec: function () {
                var C = s.container.getChild(1), D = s.getThemeSpace('contents');
                if (s.mode == 'wysiwyg') {
                    var E = s.getSelection();
                    w = E && E.getRanges();
                    x = v.getScrollPosition();
                } else {
                    var F = s.textarea.$;
                    w = !c && [F.selectionStart, F.selectionEnd];
                    x = [F.scrollLeft, F.scrollTop];
                }
                if (this.state == 2) {
                    v.on('resize', A);
                    y = v.getScrollPosition();
                    var G = s.container;
                    while (G = G.getParent()) {
                        G.setCustomData('maximize_saved_styles', o(G));
                        G.setStyle('z-index', s.config.baseFloatZIndex - 1);
                    }
                    D.setCustomData('maximize_saved_styles', o(D, true));
                    C.setCustomData('maximize_saved_styles', o(C, true));
                    var H = {overflow: b.webkit ? '' : 'hidden', width: 0, height: 0};
                    u.getDocumentElement().setStyles(H);
                    !b.gecko && u.getDocumentElement().setStyle('position', 'fixed');
                    !(b.gecko && b.quirks) && u.getBody().setStyles(H);
                    c ? setTimeout(function () {
                        v.$.scrollTo(0, 0);
                    }, 0) : v.$.scrollTo(0, 0);
                    C.setStyle('position', b.gecko && b.quirks ? 'fixed' : 'absolute');
                    C.$.offsetLeft;
                    C.setStyles({'z-index': s.config.baseFloatZIndex - 1, left: '0px', top: '0px'});
                    z = r(C);
                    C.addClass('cke_maximized');
                    A();
                    var I = C.getDocumentPosition();
                    C.setStyles({left: -1 * I.x + 'px', top: -1 * I.y + 'px'});
                    b.gecko && q(s);
                } else if (this.state == 1) {
                    v.removeListener('resize', A);
                    var J = [D, C];
                    for (var K = 0; K < J.length; K++) {
                        p(J[K], J[K].getCustomData('maximize_saved_styles'));
                        J[K].removeCustomData('maximize_saved_styles');
                    }
                    G = s.container;
                    while (G = G.getParent()) {
                        p(G, G.getCustomData('maximize_saved_styles'));
                        G.removeCustomData('maximize_saved_styles');
                    }
                    c ? setTimeout(function () {
                        v.$.scrollTo(y.x, y.y);
                    }, 0) : v.$.scrollTo(y.x, y.y);
                    C.removeClass('cke_maximized');
                    if (b.webkit) {
                        C.setStyle('display', 'inline');
                        setTimeout(function () {
                            C.setStyle('display', 'block');
                        }, 0);
                    }
                    if (z) {
                        z.remove();
                        z = null;
                    }
                    s.fire('resize');
                }
                this.toggleState();
                var L = this.uiItems[0];
                if (L) {
                    var M = this.state == 2 ? t.maximize : t.minimize, N = s.element.getDocument().getById(L._.id);
                    N.getChild(1).setHtml(M);
                    N.setAttribute('title', M);
                    N.setAttribute('href', 'javascript:void("' + M + '");');
                }
                if (s.mode == 'wysiwyg') {
                    if (w) {
                        b.gecko && q(s);
                        s.getSelection().selectRanges(w);
                        var O = s.getSelection().getStartElement();
                        O && O.scrollIntoView(true);
                    } else v.$.scrollTo(x.x, x.y);
                } else {
                    if (w) {
                        F.selectionStart = w[0];
                        F.selectionEnd = w[1];
                    }
                    F.scrollLeft = x[0];
                    F.scrollTop = x[1];
                }
                w = x = null;
                B = this.state;
            }, canUndo: false});
            s.ui.addButton('Maximize', {label: t.maximize, command: 'maximize'});
            s.on('mode', function () {
                var C = s.getCommand('maximize');
                C.setState(C.state == 0 ? 0 : B);
            }, null, null, 100);
        }});
    })();
    j.add('newpage', {init: function (m) {
        m.addCommand('newpage', {modes: {wysiwyg: 1, source: 1}, exec: function (n) {
            var o = this;
            n.setData(n.config.newpage_html || '', function () {
                setTimeout(function () {
                    n.fire('afterCommandExec', {name: 'newpage', command: o});
                    n.selectionChange();
                }, 200);
            });
            n.focus();
        }, async: true});
        m.ui.addButton('NewPage', {label: m.lang.newPage, command: 'newpage'});
    }});
    j.add('pagebreak', {init: function (m) {
        m.addCommand('pagebreak', j.pagebreakCmd);
        m.ui.addButton('PageBreak', {label: m.lang.pagebreak, command: 'pagebreak'});
        var n = ['{', 'background: url(' + a.getUrl(this.path + 'images/pagebreak.gif') + ') no-repeat center center;', 'clear: both;', 'width:100%; _width:99.9%;', 'border-top: #999999 1px dotted;', 'border-bottom: #999999 1px dotted;', 'padding:0;', 'height: 5px;', 'cursor: default;', '}'].join('').replace(/;/g, ' !important;');
        m.addCss('div.cke_pagebreak' + n);
        b.opera && m.on('contentDom', function () {
            m.document.on('click', function (o) {
                var p = o.data.getTarget();
                if (p.is('div') && p.hasClass('cke_pagebreak'))m.getSelection().selectElement(p);
            });
        });
    }, afterInit: function (m) {
        var n = m.lang.pagebreakAlt, o = m.dataProcessor, p = o && o.dataFilter, q = o && o.htmlFilter;
        if (q)q.addRules({attributes: {'class': function (r, s) {
            var t = r.replace('cke_pagebreak', '');
            if (t != r) {
                var u = a.htmlParser.fragment.fromHtml('<span style="display: none;">&nbsp;</span>');
                s.children.length = 0;
                s.add(u);
                var v = s.attributes;
                delete v['aria-label'];
                delete v.contenteditable;
                delete v.title;
            }
            return t;
        }}}, 5);
        if (p)p.addRules({elements: {div: function (r) {
            var s = r.attributes, t = s && s.style, u = t && r.children.length == 1 && r.children[0], v = u && u.name == 'span' && u.attributes.style;
            if (v && /page-break-after\s*:\s*always/i.test(t) && /display\s*:\s*none/i.test(v)) {
                s.contenteditable = 'false';
                s['class'] = 'cke_pagebreak';
                s['data-cke-display-name'] = 'pagebreak';
                s['aria-label'] = n;
                s.title = n;
                r.children.length = 0;
            }
        }}});
    }, requires: ['fakeobjects']});
    j.pagebreakCmd = {exec: function (m) {
        var n = m.lang.pagebreakAlt, o = h.createFromHtml('<div style="page-break-after: always;"contenteditable="false" title="' + n + '" ' + 'aria-label="' + n + '" ' + 'data-cke-display-name="pagebreak" ' + 'class="cke_pagebreak">' + '</div>', m.document), p = m.getSelection().getRanges(true);
        m.fire('saveSnapshot');
        for (var q, r = p.length - 1; r >= 0; r--) {
            q = p[r];
            if (r < p.length - 1)o = o.clone(true);
            q.splitBlock('p');
            q.insertNode(o);
            if (r == p.length - 1) {
                var s = o.getNext();
                q.moveToPosition(o, 4);
                if (!s || s.type == 1 && !s.isEditable())q.fixBlock(true, m.config.enterMode == 3 ? 'div' : 'p');
                q.select();
            }
        }
        m.fire('saveSnapshot');
    }};
    (function () {
        function m(n) {
            n.data.mode = 'html';
        };
        j.add('pastefromword', {init: function (n) {
            var o = 0, p = function (q) {
                q && q.removeListener();
                n.removeListener('beforePaste', m);
                o && setTimeout(function () {
                    o = 0;
                }, 0);
            };
            n.addCommand('pastefromword', {canUndo: false, exec: function () {
                o = 1;
                n.on('beforePaste', m);
                if (n.execCommand('paste', 'html') === false) {
                    n.on('dialogShow', function (q) {
                        q.removeListener();
                        q.data.on('cancel', p);
                    });
                    n.on('dialogHide', function (q) {
                        q.data.removeListener('cancel', p);
                    });
                }
                n.on('afterPaste', p);
            }});
            n.ui.addButton('PasteFromWord', {label: n.lang.pastefromword.toolbar, command: 'pastefromword'});
            n.on('pasteState', function (q) {
                n.getCommand('pastefromword').setState(q.data);
            });
            n.on('paste', function (q) {
                var r = q.data, s;
                if ((s = r.html) && (o || /(class=\"?Mso|style=\"[^\"]*\bmso\-|w:WordDocument)/.test(s))) {
                    var t = this.loadFilterRules(function () {
                        if (t)n.fire('paste', r); else if (!n.config.pasteFromWordPromptCleanup || o || confirm(n.lang.pastefromword.confirmCleanup))r.html = a.cleanWord(s, n);
                    });
                    t && q.cancel();
                }
            }, this);
        }, loadFilterRules: function (n) {
            var o = a.cleanWord;
            if (o)n(); else {
                var p = a.getUrl(i.pasteFromWordCleanupFile || this.path + 'filter/default.js');
                a.scriptLoader.load(p, n, null, true);
            }
            return!o;
        }, requires: ['clipboard']});
    })();
    (function () {
        var m = {exec: function (n) {
            var o = e.tryThese(function () {
                var p = window.clipboardData.getData('Text');
                if (!p)throw 0;
                return p;
            });
            if (!o) {
                n.openDialog('pastetext');
                return false;
            } else n.fire('paste', {text: o});
            return true;
        }};
        j.add('pastetext', {init: function (n) {
            var o = 'pastetext', p = n.addCommand(o, m);
            n.ui.addButton('PasteText', {label: n.lang.pasteText.button, command: o});
            a.dialog.add(o, a.getUrl(this.path + 'dialogs/pastetext.js'));
            if (n.config.forcePasteAsPlainText) {
                n.on('beforeCommandExec', function (q) {
                    var r = q.data.commandData;
                    if (q.data.name == 'paste' && r != 'html') {
                        n.execCommand('pastetext');
                        q.cancel();
                    }
                }, null, null, 0);
                n.on('beforePaste', function (q) {
                    q.data.mode = 'text';
                });
            }
            n.on('pasteState', function (q) {
                n.getCommand('pastetext').setState(q.data);
            });
        }, requires: ['clipboard']});
    })();
    j.add('popup');
    e.extend(a.editor.prototype, {popup: function (m, n, o, p) {
        n = n || '80%';
        o = o || '70%';
        if (typeof n == 'string' && n.length > 1 && n.substr(n.length - 1, 1) == '%')n = parseInt(window.screen.width * parseInt(n, 10) / 100, 10);
        if (typeof o == 'string' && o.length > 1 && o.substr(o.length - 1, 1) == '%')o = parseInt(window.screen.height * parseInt(o, 10) / 100, 10);
        if (n < 640)n = 640;
        if (o < 420)o = 420;
        var q = parseInt((window.screen.height - o) / 2, 10), r = parseInt((window.screen.width - n) / 2, 10);
        p = (p || 'location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=yes,scrollbars=yes') + ',width=' + n + ',height=' + o + ',top=' + q + ',left=' + r;
        var s = window.open('', null, p, true);
        if (!s)return false;
        try {
            var t = navigator.userAgent.toLowerCase();
            if (t.indexOf(' chrome/') == -1) {
                s.moveTo(r, q);
                s.resizeTo(n, o);
            }
            s.focus();
            s.location.href = m;
        } catch (u) {
            s = window.open(m, null, p, true);
        }
        return true;
    }});
    (function () {
        var m, n = {modes: {wysiwyg: 1, source: 1}, canUndo: false, readOnly: 1, exec: function (p) {
            var q, r = p.config, s = r.baseHref ? '<base href="' + r.baseHref + '"/>' : '', t = b.isCustomDomain();
            if (r.fullPage)q = p.getData().replace(/<head>/, '$&' + s).replace(/[^>]*(?=<\/title>)/, '$& &mdash; ' + p.lang.preview); else {
                var u = '<body ', v = p.document && p.document.getBody();
                if (v) {
                    if (v.getAttribute('id'))u += 'id="' + v.getAttribute('id') + '" ';
                    if (v.getAttribute('class'))u += 'class="' + v.getAttribute('class') + '" ';
                }
                u += '>';
                q = p.config.docType + '<html dir="' + p.config.contentsLangDirection + '">' + '<head>' + s + '<title>' + p.lang.preview + '</title>' + e.buildStyleHtml(p.config.contentsCss) + '</head>' + u + p.getData() + '</body></html>';
            }
            var w = 640, x = 420, y = 80;
            try {
                var z = window.screen;
                w = Math.round(z.width * 0.8);
                x = Math.round(z.height * 0.7);
                y = Math.round(z.width * 0.1);
            } catch (D) {
            }
            var A = '';
            if (t) {
                window._cke_htmlToLoad = q;
                A = 'javascript:void( (function(){document.open();document.domain="' + document.domain + '";' + 'document.write( window.opener._cke_htmlToLoad );' + 'document.close();' + 'window.opener._cke_htmlToLoad = null;' + '})() )';
            }
            if (b.gecko) {
                window._cke_htmlToLoad = q;
                A = m + 'preview.html';
            }
            var B = window.open(A, null, 'toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=yes,width=' + w + ',height=' + x + ',left=' + y);
            if (!t && !b.gecko) {
                var C = B.document;
                C.open();
                C.write(q);
                C.close();
                b.webkit && setTimeout(function () {
                    C.body.innerHTML += '';
                }, 0);
            }
        }}, o = 'preview';
        j.add(o, {init: function (p) {
            m = this.path;
            p.addCommand(o, n);
            p.ui.addButton('Preview', {label: p.lang.preview, command: o});
        }});
    })();
    j.add('print', {init: function (m) {
        var n = 'print', o = m.addCommand(n, j.print);
        m.ui.addButton('Print', {label: m.lang.print, command: n});
    }});
    j.print = {exec: function (m) {
        if (b.opera)return; else if (b.gecko)m.window.$.print(); else m.document.$.execCommand('Print');
    }, canUndo: false, readOnly: 1, modes: {wysiwyg: !b.opera}};
    j.add('removeformat', {requires: ['selection'], init: function (m) {
        m.addCommand('removeFormat', j.removeformat.commands.removeformat);
        m.ui.addButton('RemoveFormat', {label: m.lang.removeFormat, command: 'removeFormat'});
        m._.removeFormat = {filters: []};
    }});
    j.removeformat = {commands: {removeformat: {exec: function (m) {
        var n = m._.removeFormatRegex || (m._.removeFormatRegex = new RegExp('^(?:' + m.config.removeFormatTags.replace(/,/g, '|') + ')$', 'i')), o = m._.removeAttributes || (m._.removeAttributes = m.config.removeFormatAttributes.split(',')), p = j.removeformat.filter, q = m.getSelection().getRanges(1), r = q.createIterator(), s;
        while (s = r.getNextRange()) {
            if (!s.collapsed)s.enlarge(1);
            var t = s.createBookmark(), u = t.startNode, v = t.endNode, w, x = function (z) {
                var A = new d.elementPath(z), B = A.elements;
                for (var C = 1, D; D = B[C]; C++) {
                    if (D.equals(A.block) || D.equals(A.blockLimit))break;
                    if (n.test(D.getName()) && p(m, D))z.breakParent(D);
                }
            };
            x(u);
            if (v) {
                x(v);
                w = u.getNextSourceNode(true, 1);
                while (w) {
                    if (w.equals(v))break;
                    var y = w.getNextSourceNode(false, 1);
                    if (!(w.getName() == 'img' && w.data('cke-realelement')) && p(m, w))if (n.test(w.getName()))w.remove(1); else {
                        w.removeAttributes(o);
                        m.fire('removeFormatCleanup', w);
                    }
                    w = y;
                }
            }
            s.moveToBookmark(t);
        }
        m.getSelection().selectRanges(q);
    }}}, filter: function (m, n) {
        var o = m._.removeFormat.filters;
        for (var p = 0; p < o.length; p++) {
            if (o[p](n) === false)return false;
        }
        return true;
    }};
    a.editor.prototype.addRemoveFormatFilter = function (m) {
        this._.removeFormat.filters.push(m);
    };
    i.removeFormatTags = 'b,big,code,del,dfn,em,font,i,ins,kbd,q,samp,small,span,strike,strong,sub,sup,tt,u,var';
    i.removeFormatAttributes = 'class,style,lang,width,height,align,hspace,valign';
    j.add('resize', {init: function (m) {
        var n = m.config, o = m.element.getDirection(1);
        !n.resize_dir && (n.resize_dir = 'both');
        n.resize_maxWidth == undefined && (n.resize_maxWidth = 3000);
        n.resize_maxHeight == undefined && (n.resize_maxHeight = 3000);
        n.resize_minWidth == undefined && (n.resize_minWidth = 750);
        n.resize_minHeight == undefined && (n.resize_minHeight = 250);
        if (n.resize_enabled !== false) {
            var p = null, q, r, s = (n.resize_dir == 'both' || n.resize_dir == 'horizontal') && n.resize_minWidth != n.resize_maxWidth, t = (n.resize_dir == 'both' || n.resize_dir == 'vertical') && n.resize_minHeight != n.resize_maxHeight;

            function u(x) {
                var y = x.data.$.screenX - q.x, z = x.data.$.screenY - q.y, A = r.width, B = r.height, C = A + y * (o == 'rtl' ? -1 : 1), D = B + z;
                if (s)A = Math.max(n.resize_minWidth, Math.min(C, n.resize_maxWidth));
                if (t)B = Math.max(n.resize_minHeight, Math.min(D, n.resize_maxHeight));
                m.resize(s ? A : null, B);
            };
            function v(x) {
                a.document.removeListener('mousemove', u);
                a.document.removeListener('mouseup', v);
                if (m.document) {
                    m.document.removeListener('mousemove', u);
                    m.document.removeListener('mouseup', v);
                }
            };
            var w = e.addFunction(function (x) {
                if (!p)p = m.getResizable();
                r = {width: p.$.offsetWidth || 0, height: p.$.offsetHeight || 0};
                q = {x: x.screenX, y: x.screenY};
                n.resize_minWidth > r.width && (n.resize_minWidth = r.width);
                n.resize_minHeight > r.height && (n.resize_minHeight = r.height);
                a.document.on('mousemove', u);
                a.document.on('mouseup', v);
                if (m.document) {
                    m.document.on('mousemove', u);
                    m.document.on('mouseup', v);
                }
            });
            m.on('destroy', function () {
                e.removeFunction(w);
            });
            m.on('themeSpace', function (x) {
                if (x.data.space == 'bottom') {
                    var y = '';
                    if (s && !t)y = ' cke_resizer_horizontal';
                    if (!s && t)y = ' cke_resizer_vertical';
                    var z = '<div class="cke_resizer' + y + ' cke_resizer_' + o + '"' + ' title="' + e.htmlEncode(m.lang.resize) + '"' + ' onmousedown="CKEDITOR.tools.callFunction(' + w + ', event)"' + '></div>';
                    o == 'ltr' && y == 'ltr' ? x.data.html += z : x.data.html = z + x.data.html;
                }
            }, m, null, 100);
        }
    }});
    (function () {
        var m = {modes: {wysiwyg: 1, source: 1}, readOnly: 1, exec: function (o) {
            var p = o.element.$.form;
            if (p)try {
                p.submit();
            } catch (q) {
                if (p.submit.click)p.submit.click();
            }
        }}, n = 'save';
        j.add(n, {init: function (o) {
            var p = o.addCommand(n, m);
            p.modes = {wysiwyg: !!o.element.$.form};
            o.ui.addButton('Save', {label: o.lang.save, command: n});
        }});
    })();
    (function () {
        var m = 'scaytcheck', n = '';

        function o(t, u) {
            var v = 0, w;
            for (w in u) {
                if (u[w] == t) {
                    v = 1;
                    break;
                }
            }
            return v;
        };
        var p = function () {
            var t = this, u = function () {
                var y = t.config, z = {};
                z.srcNodeRef = t.document.getWindow().$.frameElement;
                z.assocApp = 'CKEDITOR.' + a.version + '@' + a.revision;
                z.customerid = y.scayt_customerid || '1:WvF0D4-UtPqN1-43nkD4-NKvUm2-daQqk3-LmNiI-z7Ysb4-mwry24-T8YrS3-Q2tpq2';
                z.customDictionaryIds = y.scayt_customDictionaryIds || '';
                z.userDictionaryName = y.scayt_userDictionaryName || '';
                z.sLang = y.scayt_sLang || 'en_US';
                z.onLoad = function () {
                    if (!(c && b.version < 8))this.addStyle(this.selectorCss(), 'padding-bottom: 2px !important;');
                    if (t.focusManager.hasFocus && !q.isControlRestored(t))this.focus();
                };
                z.onBeforeChange = function () {
                    if (q.getScayt(t) && !t.checkDirty())setTimeout(function () {
                        t.resetDirty();
                    }, 0);
                };
                var A = window.scayt_custom_params;
                if (typeof A == 'object')for (var B in A)z[B] = A[B];
                if (q.getControlId(t))z.id = q.getControlId(t);
                var C = new window.scayt(z);
                C.afterMarkupRemove.push(function (E) {
                    new h(E, C.document).mergeSiblings();
                });
                var D = q.instances[t.name];
                if (D) {
                    C.sLang = D.sLang;
                    C.option(D.option());
                    C.paused = D.paused;
                }
                q.instances[t.name] = C;
                try {
                    C.setDisabled(q.isPaused(t) === false);
                } catch (E) {
                }
                t.fire('showScaytState');
            };
            t.on('contentDom', u);
            t.on('contentDomUnload', function () {
                var y = a.document.getElementsByTag('script'), z = /^dojoIoScript(\d+)$/i, A = /^https?:\/\/svc\.webspellchecker\.net\/spellcheck\/script\/ssrv\.cgi/i;
                for (var B = 0; B < y.count(); B++) {
                    var C = y.getItem(B), D = C.getId(), E = C.getAttribute('src');
                    if (D && E && D.match(z) && E.match(A))C.remove();
                }
            });
            t.on('beforeCommandExec', function (y) {
                if ((y.data.name == 'source' || y.data.name == 'newpage') && t.mode == 'wysiwyg') {
                    var z = q.getScayt(t);
                    if (z) {
                        q.setPaused(t, !z.disabled);
                        q.setControlId(t, z.id);
                        z.destroy(true);
                        delete q.instances[t.name];
                    }
                } else if (y.data.name == 'source' && t.mode == 'source')q.markControlRestore(t);
            });
            t.on('afterCommandExec', function (y) {
                if (!q.isScaytEnabled(t))return;
                if (t.mode == 'wysiwyg' && (y.data.name == 'undo' || y.data.name == 'redo'))window.setTimeout(function () {
                    q.getScayt(t).refresh();
                }, 10);
            });
            t.on('destroy', function (y) {
                var z = y.editor, A = q.getScayt(z);
                if (!A)return;
                delete q.instances[z.name];
                q.setControlId(z, A.id);
                A.destroy(true);
            });
            t.on('afterSetData', function () {
                if (q.isScaytEnabled(t))window.setTimeout(function () {
                    var y = q.getScayt(t);
                    y && y.refresh();
                }, 10);
            });
            t.on('insertElement', function () {
                var y = q.getScayt(t);
                if (q.isScaytEnabled(t)) {
                    if (c)t.getSelection().unlock(true);
                    window.setTimeout(function () {
                        y.focus();
                        y.refresh();
                    }, 10);
                }
            }, this, null, 50);
            t.on('insertHtml', function () {
                var y = q.getScayt(t);
                if (q.isScaytEnabled(t)) {
                    if (c)t.getSelection().unlock(true);
                    window.setTimeout(function () {
                        y.focus();
                        y.refresh();
                    }, 10);
                }
            }, this, null, 50);
            t.on('scaytDialog', function (y) {
                y.data.djConfig = window.djConfig;
                y.data.scayt_control = q.getScayt(t);
                y.data.tab = n;
                y.data.scayt = window.scayt;
            });
            var v = t.dataProcessor, w = v && v.htmlFilter;
            if (w)w.addRules({elements: {span: function (y) {
                if (y.attributes['data-scayt_word'] && y.attributes['data-scaytid']) {
                    delete y.name;
                    return y;
                }
            }}});
            var x = j.undo.Image.prototype;
            x.equals = e.override(x.equals, function (y) {
                return function (z) {
                    var E = this;
                    var A = E.contents, B = z.contents, C = q.getScayt(E.editor);
                    if (C && q.isScaytReady(E.editor)) {
                        E.contents = C.reset(A) || '';
                        z.contents = C.reset(B) || '';
                    }
                    var D = y.apply(E, arguments);
                    E.contents = A;
                    z.contents = B;
                    return D;
                };
            });
            if (t.document)u();
        };
        j.scayt = {engineLoaded: false, instances: {}, controlInfo: {}, setControlInfo: function (t, u) {
            if (t && t.name && typeof this.controlInfo[t.name] != 'object')this.controlInfo[t.name] = {};
            for (var v in u)this.controlInfo[t.name][v] = u[v];
        }, isControlRestored: function (t) {
            if (t && t.name && this.controlInfo[t.name])return this.controlInfo[t.name].restored;
            return false;
        }, markControlRestore: function (t) {
            this.setControlInfo(t, {restored: true});
        }, setControlId: function (t, u) {
            this.setControlInfo(t, {id: u});
        }, getControlId: function (t) {
            if (t && t.name && this.controlInfo[t.name] && this.controlInfo[t.name].id)return this.controlInfo[t.name].id;
            return null;
        }, setPaused: function (t, u) {
            this.setControlInfo(t, {paused: u});
        }, isPaused: function (t) {
            if (t && t.name && this.controlInfo[t.name])return this.controlInfo[t.name].paused;
            return undefined;
        }, getScayt: function (t) {
            return this.instances[t.name];
        }, isScaytReady: function (t) {
            return this.engineLoaded === true && 'undefined' !== typeof window.scayt && this.getScayt(t);
        }, isScaytEnabled: function (t) {
            var u = this.getScayt(t);
            return u ? u.disabled === false : false;
        }, getUiTabs: function (t) {
            var u = [], v = t.config.scayt_uiTabs || '1,1,1';
            v = v.split(',');
            v[3] = '1';
            for (var w = 0; w < 4; w++)u[w] = typeof window.scayt != 'undefined' && typeof window.scayt.uiTags != 'undefined' ? parseInt(v[w], 10) && window.scayt.uiTags[w] : parseInt(v[w], 10);
            return u;
        }, loadEngine: function (t) {
            if (b.gecko && b.version < 10900 || b.opera || b.air)return t.fire('showScaytState');
            if (this.engineLoaded === true)return p.apply(t); else if (this.engineLoaded == -1)return a.on('scaytReady', function () {
                p.apply(t);
            });
            a.on('scaytReady', p, t);
            a.on('scaytReady', function () {
                this.engineLoaded = true;
            }, this, null, 0);
            this.engineLoaded = -1;
            var u = document.location.protocol;
            u = u.search(/https?:/) != -1 ? u : 'http:';
            var v = 'svc.webspellchecker.net/scayt26/loader__base.js', w = t.config.scayt_srcUrl || u + '//' + v, x = q.parseUrl(w).path + '/';
            if (window.scayt == undefined) {
                a._djScaytConfig = {baseUrl: x, addOnLoad: [function () {
                    a.fireOnce('scaytReady');
                }], isDebug: false};
                a.document.getHead().append(a.document.createElement('script', {attributes: {type: 'text/javascript', async: 'true', src: w}}));
            } else a.fireOnce('scaytReady');
            return null;
        }, parseUrl: function (t) {
            var u;
            if (t.match && (u = t.match(/(.*)[\/\\](.*?\.\w+)$/)))return{path: u[1], file: u[2]};
            else return t;
        }};
        var q = j.scayt, r = function (t, u, v, w, x, y, z) {
            t.addCommand(w, x);
            t.addMenuItem(w, {label: v, command: w, group: y, order: z});
        }, s = {preserveState: true, editorFocus: false, canUndo: false, exec: function (t) {
            if (q.isScaytReady(t)) {
                var u = q.isScaytEnabled(t);
                this.setState(u ? 2 : 1);
                var v = q.getScayt(t);
                v.focus();
                v.setDisabled(u);
            } else if (!t.config.scayt_autoStartup && q.engineLoaded >= 0) {
                this.setState(0);
                q.loadEngine(t);
            }
        }};
        j.add('scayt', {requires: ['menubutton'], beforeInit: function (t) {
            var u = t.config.scayt_contextMenuItemsOrder || 'suggest|moresuggest|control', v = '';
            u = u.split('|');
            if (u && u.length)for (var w = 0; w < u.length; w++)v += 'scayt_' + u[w] + (u.length != parseInt(w, 10) + 1 ? ',' : '');
            t.config.menu_groups = v + ',' + t.config.menu_groups;
        }, init: function (t) {
            var u = t.dataProcessor && t.dataProcessor.dataFilter, v = {elements: {span: function (E) {
                var F = E.attributes;
                if (F && F['data-scaytid'])delete E.name;
            }}};
            u && u.addRules(v);
            var w = {}, x = {}, y = t.addCommand(m, s);
            a.dialog.add(m, a.getUrl(this.path + 'dialogs/options.js'));
            var z = q.getUiTabs(t), A = 'scaytButton';
            t.addMenuGroup(A);
            var B = {}, C = t.lang.scayt;
            B.scaytToggle = {label: C.enable, command: m, group: A};
            if (z[0] == 1)B.scaytOptions = {label: C.options, group: A, onClick: function () {
                n = 'options';
                t.openDialog(m);
            }};
            if (z[1] == 1)B.scaytLangs = {label: C.langs, group: A, onClick: function () {
                n = 'langs';
                t.openDialog(m);
            }};
            if (z[2] == 1)B.scaytDict = {label: C.dictionariesTab, group: A, onClick: function () {
                n = 'dictionaries';
                t.openDialog(m);
            }};
            B.scaytAbout = {label: t.lang.scayt.about, group: A, onClick: function () {
                n = 'about';
                t.openDialog(m);
            }};
            t.addMenuItems(B);
            t.ui.add('Scayt', 'menubutton', {label: C.title, title: b.opera ? C.opera_title : C.title, className: 'cke_button_scayt', modes: {wysiwyg: 1}, onRender: function () {
                y.on('state', function () {
                    this.setState(y.state);
                }, this);
            }, onMenu: function () {
                var E = q.isScaytEnabled(t);
                t.getMenuItem('scaytToggle').label = C[E ? 'disable' : 'enable'];
                var F = q.getUiTabs(t);
                return{scaytToggle: 2, scaytOptions: E && F[0] ? 2 : 0, scaytLangs: E && F[1] ? 2 : 0, scaytDict: E && F[2] ? 2 : 0, scaytAbout: E && F[3] ? 2 : 0};
            }});
            if (t.contextMenu && t.addMenuItems)t.contextMenu.addListener(function (E, F) {
                if (!q.isScaytEnabled(t) || F.getRanges()[0].checkReadOnly())return null;
                var G = q.getScayt(t), H = G.getScaytNode();
                if (!H)return null;
                var I = G.getWord(H);
                if (!I)return null;
                var J = G.getLang(), K = {}, L = window.scayt.getSuggestion(I, J);
                if (!L || !L.length)return null;
                for (var M in w) {
                    delete t._.menuItems[M];
                    delete t._.commands[M];
                }
                for (M in x) {
                    delete t._.menuItems[M];
                    delete t._.commands[M];
                }
                w = {};
                x = {};
                var N = t.config.scayt_moreSuggestions || 'on', O = false, P = t.config.scayt_maxSuggestions;
                typeof P != 'number' && (P = 5);
                !P && (P = L.length);
                var Q = t.config.scayt_contextCommands || 'all';
                Q = Q.split('|');
                for (var R = 0, S = L.length; R < S; R += 1) {
                    var T = 'scayt_suggestion_' + L[R].replace(' ', '_'), U = (function (Y, Z) {
                        return{exec: function () {
                            G.replace(Y, Z);
                        }};
                    })(H, L[R]);
                    if (R < P) {
                        r(t, 'button_' + T, L[R], T, U, 'scayt_suggest', R + 1);
                        K[T] = 2;
                        x[T] = 2;
                    } else if (N == 'on') {
                        r(t, 'button_' + T, L[R], T, U, 'scayt_moresuggest', R + 1);
                        w[T] = 2;
                        O = true;
                    }
                }
                if (O) {
                    t.addMenuItem('scayt_moresuggest', {label: C.moreSuggestions, group: 'scayt_moresuggest', order: 10, getItems: function () {
                        return w;
                    }});
                    x.scayt_moresuggest = 2;
                }
                if (o('all', Q) || o('ignore', Q)) {
                    var V = {exec: function () {
                        G.ignore(H);
                    }};
                    r(t, 'ignore', C.ignore, 'scayt_ignore', V, 'scayt_control', 1);
                    x.scayt_ignore = 2;
                }
                if (o('all', Q) || o('ignoreall', Q)) {
                    var W = {exec: function () {
                        G.ignoreAll(H);
                    }};
                    r(t, 'ignore_all', C.ignoreAll, 'scayt_ignore_all', W, 'scayt_control', 2);
                    x.scayt_ignore_all = 2;
                }
                if (o('all', Q) || o('add', Q)) {
                    var X = {exec: function () {
                        window.scayt.addWordToUserDictionary(H);
                    }};
                    r(t, 'add_word', C.addWord, 'scayt_add_word', X, 'scayt_control', 3);
                    x.scayt_add_word = 2;
                }
                if (G.fireOnContextMenu)G.fireOnContextMenu(t);
                return x;
            });
            var D = function () {
                t.removeListener('showScaytState', D);
                if (!b.opera && !b.air)y.setState(q.isScaytEnabled(t) ? 1 : 2); else y.setState(0);
            };
            t.on('showScaytState', D);
            if (b.opera || b.air)t.on('instanceReady', function () {
                D();
            });
            if (t.config.scayt_autoStartup)t.on('instanceReady', function () {
                q.loadEngine(t);
            });
        }, afterInit: function (t) {
            var u, v = function (w) {
                if (w.hasAttribute('data-scaytid'))return false;
            };
            if (t._.elementsPath && (u = t._.elementsPath.filters))u.push(v);
            t.addRemoveFormatFilter && t.addRemoveFormatFilter(v);
        }});
    })();
    j.add('smiley', {requires: ['dialog'], init: function (m) {
        m.config.smiley_path = m.config.smiley_path || this.path + 'images/';
        m.addCommand('smiley', new a.dialogCommand('smiley'));
        m.ui.addButton('Smiley', {label: m.lang.smiley.toolbar, command: 'smiley'});
        a.dialog.add('smiley', this.path + 'dialogs/smiley.js');
    }});
    i.smiley_images = ['regular_smile.gif', 'sad_smile.gif', 'wink_smile.gif', 'teeth_smile.gif', 'confused_smile.gif', 'tounge_smile.gif', 'embaressed_smile.gif', 'omg_smile.gif', 'whatchutalkingabout_smile.gif', 'angry_smile.gif', 'angel_smile.gif', 'shades_smile.gif', 'devil_smile.gif', 'cry_smile.gif', 'lightbulb.gif', 'thumbs_down.gif', 'thumbs_up.gif', 'heart.gif', 'broken_heart.gif', 'kiss.gif', 'envelope.gif'];
    i.smiley_descriptions = ['smiley', 'sad', 'wink', 'laugh', 'frown', 'cheeky', 'blush', 'surprise', 'indecision', 'angry', 'angel', 'cool', 'devil', 'crying', 'enlightened', 'no', 'yes', 'heart', 'broken heart', 'kiss', 'mail'];
    (function () {
        var m = '.%2 p,.%2 div,.%2 pre,.%2 address,.%2 blockquote,.%2 h1,.%2 h2,.%2 h3,.%2 h4,.%2 h5,.%2 h6{background-repeat: no-repeat;background-position: top %3;border: 1px dotted gray;padding-top: 8px;padding-%3: 8px;}.%2 p{%1p.png);}.%2 div{%1div.png);}.%2 pre{%1pre.png);}.%2 address{%1address.png);}.%2 blockquote{%1blockquote.png);}.%2 h1{%1h1.png);}.%2 h2{%1h2.png);}.%2 h3{%1h3.png);}.%2 h4{%1h4.png);}.%2 h5{%1h5.png);}.%2 h6{%1h6.png);}', n = /%1/g, o = /%2/g, p = /%3/g, q = {readOnly: 1, preserveState: true, editorFocus: false, exec: function (r) {
            this.toggleState();
            this.refresh(r);
        }, refresh: function (r) {
            if (r.document) {
                var s = this.state == 1 ? 'addClass' : 'removeClass';
                r.document.getBody()[s]('cke_show_blocks');
            }
        }};
        j.add('showblocks', {requires: ['wysiwygarea'], init: function (r) {
            var s = r.addCommand('showblocks', q);
            s.canUndo = false;
            if (r.config.startupOutlineBlocks)s.setState(1);
            r.addCss(m.replace(n, 'background-image: url(' + a.getUrl(this.path) + 'images/block_').replace(o, 'cke_show_blocks ').replace(p, r.lang.dir == 'rtl' ? 'right' : 'left'));
            r.ui.addButton('ShowBlocks', {label: r.lang.showBlocks, command: 'showblocks'});
            r.on('mode', function () {
                if (s.state != 0)s.refresh(r);
            });
            r.on('contentDom', function () {
                if (s.state != 0)s.refresh(r);
            });
        }});
    })();
    (function () {
        var m = 'cke_show_border', n, o = (b.ie6Compat ? ['.%1 table.%2,', '.%1 table.%2 td, .%1 table.%2 th', '{', 'border : #d3d3d3 1px dotted', '}'] : ['.%1 table.%2,', '.%1 table.%2 > tr > td, .%1 table.%2 > tr > th,', '.%1 table.%2 > tbody > tr > td, .%1 table.%2 > tbody > tr > th,', '.%1 table.%2 > thead > tr > td, .%1 table.%2 > thead > tr > th,', '.%1 table.%2 > tfoot > tr > td, .%1 table.%2 > tfoot > tr > th', '{', 'border : #d3d3d3 1px dotted', '}']).join('');
        n = o.replace(/%2/g, m).replace(/%1/g, 'cke_show_borders ');
        var p = {preserveState: true, editorFocus: false, readOnly: 1, exec: function (q) {
            this.toggleState();
            this.refresh(q);
        }, refresh: function (q) {
            if (q.document) {
                var r = this.state == 1 ? 'addClass' : 'removeClass';
                q.document.getBody()[r]('cke_show_borders');
            }
        }};
        j.add('showborders', {requires: ['wysiwygarea'], modes: {wysiwyg: 1}, init: function (q) {
            var r = q.addCommand('showborders', p);
            r.canUndo = false;
            if (q.config.startupShowBorders !== false)r.setState(1);
            q.addCss(n);
            q.on('mode', function () {
                if (r.state != 0)r.refresh(q);
            }, null, null, 100);
            q.on('contentDom', function () {
                if (r.state != 0)r.refresh(q);
            });
            q.on('removeFormatCleanup', function (s) {
                var t = s.data;
                if (q.getCommand('showborders').state == 1 && t.is('table') && (!t.hasAttribute('border') || parseInt(t.getAttribute('border'), 10) <= 0))t.addClass(m);
            });
        }, afterInit: function (q) {
            var r = q.dataProcessor, s = r && r.dataFilter, t = r && r.htmlFilter;
            if (s)s.addRules({elements: {table: function (u) {
                var v = u.attributes, w = v['class'], x = parseInt(v.border, 10);
                if ((!x || x <= 0) && (!w || w.indexOf(m) == -1))v['class'] = (w || '') + ' ' + m;
            }}});
            if (t)t.addRules({elements: {table: function (u) {
                var v = u.attributes, w = v['class'];
                w && (v['class'] = w.replace(m, '').replace(/\s{2}/, ' ').replace(/^\s+|\s+$/, ''));
            }}});
        }});
        a.on('dialogDefinition', function (q) {
            var r = q.data.name;
            if (r == 'table' || r == 'tableProperties') {
                var s = q.data.definition, t = s.getContents('info'), u = t.get('txtBorder'), v = u.commit;
                u.commit = e.override(v, function (y) {
                    return function (z, A) {
                        y.apply(this, arguments);
                        var B = parseInt(this.getValue(), 10);
                        A[!B || B <= 0 ? 'addClass' : 'removeClass'](m);
                    };
                });
                var w = s.getContents('advanced'), x = w && w.get('advCSSClasses');
                if (x) {
                    x.setup = e.override(x.setup, function (y) {
                        return function () {
                            y.apply(this, arguments);
                            this.setValue(this.getValue().replace(/cke_show_border/, ''));
                        };
                    });
                    x.commit = e.override(x.commit, function (y) {
                        return function (z, A) {
                            y.apply(this, arguments);
                            if (!parseInt(A.getAttribute('border'), 10))A.addClass('cke_show_border');
                        };
                    });
                }
            }
        });
    })();
    j.add('sourcearea', {requires: ['editingblock'], init: function (m) {
        var n = j.sourcearea, o = a.document.getWindow();
        m.on('editingBlockReady', function () {
            var p, q;
            m.addMode('source', {load: function (r, s) {
                if (c && b.version < 8)r.setStyle('position', 'relative');
                m.textarea = p = new h('textarea');
                p.setAttributes({dir: 'ltr', tabIndex: b.webkit ? -1 : m.tabIndex, role: 'textbox', 'aria-label': m.lang.editorTitle.replace('%1', m.name)});
                p.addClass('cke_source');
                p.addClass('cke_enable_context_menu');
                m.readOnly && p.setAttribute('readOnly', 'readonly');
                var t = {width: b.ie7Compat ? '99%' : '100%', height: '100%', resize: 'none', outline: 'none', 'text-align': 'left'};
                if (c) {
                    q = function () {
                        p.hide();
                        p.setStyle('height', r.$.clientHeight + 'px');
                        p.setStyle('width', r.$.clientWidth + 'px');
                        p.show();
                    };
                    m.on('resize', q);
                    o.on('resize', q);
                    setTimeout(q, 0);
                }
                r.setHtml('');
                r.append(p);
                p.setStyles(t);
                m.fire('ariaWidget', p);
                p.on('blur', function () {
                    m.focusManager.blur();
                });
                p.on('focus', function () {
                    m.focusManager.focus();
                });
                m.mayBeDirty = true;
                this.loadData(s);
                var u = m.keystrokeHandler;
                if (u)u.attach(p);
                setTimeout(function () {
                    m.mode = 'source';
                    m.fire('mode', {previousMode: m._.previousMode});
                }, b.gecko || b.webkit ? 100 : 0);
            }, loadData: function (r) {
                p.setValue(r);
                m.fire('dataReady');
            }, getData: function () {
                return p.getValue();
            }, getSnapshotData: function () {
                return p.getValue();
            }, unload: function (r) {
                p.clearCustomData();
                m.textarea = p = null;
                if (q) {
                    m.removeListener('resize', q);
                    o.removeListener('resize', q);
                }
                if (c && b.version < 8)r.removeStyle('position');
            }, focus: function () {
                p.focus();
            }});
        });
        m.on('readOnly', function () {
            if (m.mode == 'source')if (m.readOnly)m.textarea.setAttribute('readOnly', 'readonly'); else m.textarea.removeAttribute('readOnly');
        });
        m.addCommand('source', n.commands.source);
        if (m.ui.addButton)m.ui.addButton('Source', {label: m.lang.source, command: 'source'});
        m.on('mode', function () {
            m.getCommand('source').setState(m.mode == 'source' ? 1 : 2);
        });
    }});
    j.sourcearea = {commands: {source: {modes: {wysiwyg: 1, source: 1}, editorFocus: false, readOnly: 1, exec: function (m) {
        if (m.mode == 'wysiwyg')m.fire('saveSnapshot');
        m.getCommand('source').setState(0);
        m.setMode(m.mode == 'source' ? 'wysiwyg' : 'source');
    }, canUndo: false}}};
    (function () {
        j.add('stylescombo', {requires: ['richcombo', 'styles'], init: function (n) {
            var o = n.config, p = n.lang.stylesCombo, q = {}, r = [], s;

            function t(u) {
                n.getStylesSet(function (v) {
                    if (!r.length) {
                        var w, x;
                        for (var y = 0, z = v.length; y < z; y++) {
                            var A = v[y];
                            x = A.name;
                            w = q[x] = new a.style(A);
                            w._name = x;
                            w._.enterMode = o.enterMode;
                            r.push(w);
                        }
                        r.sort(m);
                    }
                    u && u();
                });
            };
            n.ui.addRichCombo('Styles', {label: p.label, title: p.panelTitle, className: 'cke_styles', panel: {css: n.skin.editor.css.concat(o.contentsCss), multiSelect: true, attributes: {'aria-label': p.panelTitle}}, init: function () {
                s = this;
                t(function () {
                    var u, v, w, x, y, z;
                    for (y = 0, z = r.length; y < z; y++) {
                        u = r[y];
                        v = u._name;
                        x = u.type;
                        if (x != w) {
                            s.startGroup(p['panelTitle' + String(x)]);
                            w = x;
                        }
                        s.add(v, u.type == 3 ? v : u.buildPreview(), v);
                    }
                    s.commit();
                });
            }, onClick: function (u) {
                n.focus();
                n.fire('saveSnapshot');
                var v = q[u], w = n.getSelection(), x = new d.elementPath(w.getStartElement());
                v[v.checkActive(x) ? 'remove' : 'apply'](n.document);
                n.fire('saveSnapshot');
            }, onRender: function () {
                n.on('selectionChange', function (u) {
                    var v = this.getValue(), w = u.data.path, x = w.elements;
                    for (var y = 0, z = x.length, A; y < z; y++) {
                        A = x[y];
                        for (var B in q) {
                            if (q[B].checkElementRemovable(A, true)) {
                                if (B != v)this.setValue(B);
                                return;
                            }
                        }
                    }
                    this.setValue('');
                }, this);
            }, onOpen: function () {
                var B = this;
                if (c || b.webkit)n.focus();
                var u = n.getSelection(), v = u.getSelectedElement(), w = new d.elementPath(v || u.getStartElement()), x = [0, 0, 0, 0];
                B.showAll();
                B.unmarkAll();
                for (var y in q) {
                    var z = q[y], A = z.type;
                    if (z.checkActive(w))B.mark(y); else if (A == 3 && !z.checkApplicable(w)) {
                        B.hideItem(y);
                        x[A]--;
                    }
                    x[A]++;
                }
                if (!x[1])B.hideGroup(p['panelTitle' + String(1)]);
                if (!x[2])B.hideGroup(p['panelTitle' + String(2)]);
                if (!x[3])B.hideGroup(p['panelTitle' + String(3)]);
            }, reset: function () {
                if (s) {
                    delete s._.panel;
                    delete s._.list;
                    s._.committed = 0;
                    s._.items = {};
                    s._.state = 2;
                }
                q = {};
                r = [];
                t();
            }});
            n.on('instanceReady', function () {
                t();
            });
        }});
        function m(n, o) {
            var p = n.type, q = o.type;
            return p == q ? 0 : p == 3 ? -1 : q == 3 ? 1 : q == 1 ? 1 : -1;
        };
    })();
    j.add('table', {requires: ['dialog'], init: function (m) {
        var n = j.table, o = m.lang.table;
        m.addCommand('table', new a.dialogCommand('table'));
        m.addCommand('tableProperties', new a.dialogCommand('tableProperties'));
        m.ui.addButton('Table', {label: o.toolbar, command: 'table'});
        a.dialog.add('table', this.path + 'dialogs/table.js');
        a.dialog.add('tableProperties', this.path + 'dialogs/table.js');
        if (m.addMenuItems)m.addMenuItems({table: {label: o.menu, command: 'tableProperties', group: 'table', order: 5}, tabledelete: {label: o.deleteTable, command: 'tableDelete', group: 'table', order: 1}});
        m.on('doubleclick', function (p) {
            var q = p.data.element;
            if (q.is('table'))p.data.dialog = 'tableProperties';
        });
        if (m.contextMenu)m.contextMenu.addListener(function (p, q) {
            if (!p || p.isReadOnly())return null;
            var r = p.hasAscendant('table', 1);
            if (r)return{tabledelete: 2, table: 2};
            return null;
        });
    }});
    (function () {
        var m = /^(?:td|th)$/;

        function n(G) {
            var H = G.getRanges(), I = [], J = {};

            function K(S) {
                if (I.length > 0)return;
                if (S.type == 1 && m.test(S.getName()) && !S.getCustomData('selected_cell')) {
                    h.setMarker(J, S, 'selected_cell', true);
                    I.push(S);
                }
            };
            for (var L = 0; L < H.length; L++) {
                var M = H[L];
                if (M.collapsed) {
                    var N = M.getCommonAncestor(), O = N.getAscendant('td', true) || N.getAscendant('th', true);
                    if (O)I.push(O);
                } else {
                    var P = new d.walker(M), Q;
                    P.guard = K;
                    while (Q = P.next()) {
                        var R = Q.getAscendant('td') || Q.getAscendant('th');
                        if (R && !R.getCustomData('selected_cell')) {
                            h.setMarker(J, R, 'selected_cell', true);
                            I.push(R);
                        }
                    }
                }
            }
            h.clearAllMarkers(J);
            return I;
        };
        function o(G) {
            var H = 0, I = G.length - 1, J = {}, K, L, M;
            while (K = G[H++])h.setMarker(J, K, 'delete_cell', true);
            H = 0;
            while (K = G[H++]) {
                if ((L = K.getPrevious()) && !L.getCustomData('delete_cell') || (L = K.getNext()) && !L.getCustomData('delete_cell')) {
                    h.clearAllMarkers(J);
                    return L;
                }
            }
            h.clearAllMarkers(J);
            M = G[0].getParent();
            if (M = M.getPrevious())return M.getLast();
            M = G[I].getParent();
            if (M = M.getNext())return M.getChild(0);
            return null;
        };
        function p(G, H) {
            var I = n(G), J = I[0], K = J.getAscendant('table'), L = J.getDocument(), M = I[0].getParent(), N = M.$.rowIndex, O = I[I.length - 1], P = O.getParent().$.rowIndex + O.$.rowSpan - 1, Q = new h(K.$.rows[P]), R = H ? N : P, S = H ? M : Q, T = e.buildTableMap(K), U = T[R], V = H ? T[R - 1] : T[R + 1], W = T[0].length, X = L.createElement('tr');
            for (var Y = 0; U[Y] && Y < W; Y++) {
                var Z;
                if (U[Y].rowSpan > 1 && V && U[Y] == V[Y]) {
                    Z = U[Y];
                    Z.rowSpan += 1;
                } else {
                    Z = new h(U[Y]).clone();
                    Z.removeAttribute('rowSpan');
                    !c && Z.appendBogus();
                    X.append(Z);
                    Z = Z.$;
                }
                Y += Z.colSpan - 1;
            }
            H ? X.insertBefore(S) : X.insertAfter(S);
        };
        function q(G) {
            if (G instanceof d.selection) {
                var H = n(G), I = H[0], J = I.getAscendant('table'), K = e.buildTableMap(J), L = H[0].getParent(), M = L.$.rowIndex, N = H[H.length - 1], O = N.getParent().$.rowIndex + N.$.rowSpan - 1, P = [];
                for (var Q = M; Q <= O; Q++) {
                    var R = K[Q], S = new h(J.$.rows[Q]);
                    for (var T = 0; T < R.length; T++) {
                        var U = new h(R[T]), V = U.getParent().$.rowIndex;
                        if (U.$.rowSpan == 1)U.remove(); else {
                            U.$.rowSpan -= 1;
                            if (V == Q) {
                                var W = K[Q + 1];
                                W[T - 1] ? U.insertAfter(new h(W[T - 1])) : new h(J.$.rows[Q + 1]).append(U, 1);
                            }
                        }
                        T += U.$.colSpan - 1;
                    }
                    P.push(S);
                }
                var X = J.$.rows, Y = new h(X[O + 1] || (M > 0 ? X[M - 1] : null) || J.$.parentNode);
                for (Q = P.length; Q >= 0; Q--)q(P[Q]);
                return Y;
            } else if (G instanceof h) {
                J = G.getAscendant('table');
                if (J.$.rows.length == 1)J.remove(); else G.remove();
            }
            return null;
        };
        function r(G, H) {
            var I = G.getParent(), J = I.$.cells, K = 0;
            for (var L = 0; L < J.length; L++) {
                var M = J[L];
                K += H ? 1 : M.colSpan;
                if (M == G.$)break;
            }
            return K - 1;
        };
        function s(G, H) {
            var I = H ? Infinity : 0;
            for (var J = 0; J < G.length; J++) {
                var K = r(G[J], H);
                if (H ? K < I : K > I)I = K;
            }
            return I;
        };
        function t(G, H) {
            var I = n(G), J = I[0], K = J.getAscendant('table'), L = s(I, 1), M = s(I), N = H ? L : M, O = e.buildTableMap(K), P = [], Q = [], R = O.length;
            for (var S = 0; S < R; S++) {
                P.push(O[S][N]);
                var T = H ? O[S][N - 1] : O[S][N + 1];
                Q.push(T);
            }
            for (S = 0; S < R; S++) {
                var U;
                if (!P[S])continue;
                if (P[S].colSpan > 1 && Q[S] == P[S]) {
                    U = P[S];
                    U.colSpan += 1;
                } else {
                    U = new h(P[S]).clone();
                    U.removeAttribute('colSpan');
                    !c && U.appendBogus();
                    U[H ? 'insertBefore' : 'insertAfter'].call(U, new h(P[S]));
                    U = U.$;
                }
                S += U.rowSpan - 1;
            }
        };
        function u(G) {
            var H = n(G), I = H[0], J = H[H.length - 1], K = I.getAscendant('table'), L = e.buildTableMap(K), M, N, O = [];
            for (var P = 0, Q = L.length; P < Q; P++)for (var R = 0, S = L[P].length; R < S; R++) {
                if (L[P][R] == I.$)M = R;
                if (L[P][R] == J.$)N = R;
            }
            for (P = M; P <= N; P++)for (R = 0; R < L.length; R++) {
                var T = L[R], U = new h(K.$.rows[R]), V = new h(T[P]);
                if (V.$) {
                    if (V.$.colSpan == 1)V.remove(); else V.$.colSpan -= 1;
                    R += V.$.rowSpan - 1;
                    if (!U.$.cells.length)O.push(U);
                }
            }
            var W = K.$.rows[0] && K.$.rows[0].cells, X = new h(W[M] || (M ? W[M - 1] : K.$.parentNode));
            if (O.length == Q)K.remove();
            return X;
        };
        function v(G) {
            var H = [], I = G[0] && G[0].getAscendant('table'), J, K, L, M;
            for (J = 0, K = G.length; J < K; J++)H.push(G[J].$.cellIndex);
            H.sort();
            for (J = 1, K = H.length; J < K; J++) {
                if (H[J] - H[J - 1] > 1) {
                    L = H[J - 1] + 1;
                    break;
                }
            }
            if (!L)L = H[0] > 0 ? H[0] - 1 : H[H.length - 1] + 1;
            var N = I.$.rows;
            for (J = 0, K = N.length; J < K; J++) {
                M = N[J].cells[L];
                if (M)break;
            }
            return M ? new h(M) : I.getPrevious();
        };
        function w(G, H) {
            var I = G.getStartElement(), J = I.getAscendant('td', 1) || I.getAscendant('th', 1);
            if (!J)return;
            var K = J.clone();
            if (!c)K.appendBogus();
            if (H)K.insertBefore(J); else K.insertAfter(J);
        };
        function x(G) {
            if (G instanceof d.selection) {
                var H = n(G), I = H[0] && H[0].getAscendant('table'), J = o(H);
                for (var K = H.length - 1; K >= 0; K--)x(H[K]);
                if (J)z(J, true); else if (I)I.remove();
            } else if (G instanceof h) {
                var L = G.getParent();
                if (L.getChildCount() == 1)L.remove();
                else G.remove();
            }
        };
        function y(G) {
            var H = G.getBogus();
            H && H.remove();
            G.trim();
        };
        function z(G, H) {
            var I = new d.range(G.getDocument());
            if (!I['moveToElementEdit' + (H ? 'End' : 'Start')](G)) {
                I.selectNodeContents(G);
                I.collapse(H ? false : true);
            }
            I.select(true);
        };
        function A(G, H, I) {
            var J = G[H];
            if (typeof I == 'undefined')return J;
            for (var K = 0; J && K < J.length; K++) {
                if (I.is && J[K] == I.$)return K; else if (K == I)return new h(J[K]);
            }
            return I.is ? -1 : null;
        };
        function B(G, H) {
            var I = [];
            for (var J = 0; J < G.length; J++) {
                var K = G[J];
                I.push(K[H]);
                if (K[H].rowSpan > 1)J += K[H].rowSpan - 1;
            }
            return I;
        };
        function C(G, H, I) {
            var J = n(G), K;
            if ((H ? J.length != 1 : J.length < 2) || (K = G.getCommonAncestor()) && K.type == 1 && K.is('table'))return false;
            var L, M = J[0], N = M.getAscendant('table'), O = e.buildTableMap(N), P = O.length, Q = O[0].length, R = M.getParent().$.rowIndex, S = A(O, R, M);
            if (H) {
                var T;
                try {
                    var U = parseInt(M.getAttribute('rowspan'), 10) || 1, V = parseInt(M.getAttribute('colspan'), 10) || 1;
                    T = O[H == 'up' ? R - U : H == 'down' ? R + U : R][H == 'left' ? S - V : H == 'right' ? S + V : S];
                } catch (an) {
                    return false;
                }
                if (!T || M.$ == T)return false;
                J[H == 'up' || H == 'left' ? 'unshift' : 'push'](new h(T));
            }
            var W = M.getDocument(), X = R, Y = 0, Z = 0, aa = !I && new d.documentFragment(W), ab = 0;
            for (var ac = 0; ac < J.length; ac++) {
                L = J[ac];
                var ad = L.getParent(), ae = L.getFirst(), af = L.$.colSpan, ag = L.$.rowSpan, ah = ad.$.rowIndex, ai = A(O, ah, L);
                ab += af * ag;
                Z = Math.max(Z, ai - S + af);
                Y = Math.max(Y, ah - R + ag);
                if (!I) {
                    if (y(L), L.getChildren().count()) {
                        if (ah != X && ae && !(ae.isBlockBoundary && ae.isBlockBoundary({br: 1}))) {
                            var aj = aa.getLast(d.walker.whitespaces(true));
                            if (aj && !(aj.is && aj.is('br')))aa.append('br');
                        }
                        L.moveChildren(aa);
                    }
                    ac ? L.remove() : L.setHtml('');
                }
                X = ah;
            }
            if (!I) {
                aa.moveChildren(M);
                if (!c)M.appendBogus();
                if (Z >= Q)M.removeAttribute('rowSpan'); else M.$.rowSpan = Y;
                if (Y >= P)M.removeAttribute('colSpan'); else M.$.colSpan = Z;
                var ak = new d.nodeList(N.$.rows), al = ak.count();
                for (ac = al - 1; ac >= 0; ac--) {
                    var am = ak.getItem(ac);
                    if (!am.$.cells.length) {
                        am.remove();
                        al++;
                        continue;
                    }
                }
                return M;
            } else return Y * Z == ab;
        };
        function D(G, H) {
            var I = n(G);
            if (I.length > 1)return false; else if (H)return true;
            var J = I[0], K = J.getParent(), L = K.getAscendant('table'), M = e.buildTableMap(L), N = K.$.rowIndex, O = A(M, N, J), P = J.$.rowSpan, Q, R, S, T;
            if (P > 1) {
                R = Math.ceil(P / 2);
                S = Math.floor(P / 2);
                T = N + R;
                var U = new h(L.$.rows[T]), V = A(M, T), W;
                Q = J.clone();
                for (var X = 0; X < V.length; X++) {
                    W = V[X];
                    if (W.parentNode == U.$ && X > O) {
                        Q.insertBefore(new h(W));
                        break;
                    } else W = null;
                }
                if (!W)U.append(Q, true);
            } else {
                S = R = 1;
                U = K.clone();
                U.insertAfter(K);
                U.append(Q = J.clone());
                var Y = A(M, N);
                for (var Z = 0; Z < Y.length; Z++)Y[Z].rowSpan++;
            }
            if (!c)Q.appendBogus();
            J.$.rowSpan = R;
            Q.$.rowSpan = S;
            if (R == 1)J.removeAttribute('rowSpan');
            if (S == 1)Q.removeAttribute('rowSpan');
            return Q;
        };
        function E(G, H) {
            var I = n(G);
            if (I.length > 1)return false; else if (H)return true;
            var J = I[0], K = J.getParent(), L = K.getAscendant('table'), M = e.buildTableMap(L), N = K.$.rowIndex, O = A(M, N, J), P = J.$.colSpan, Q, R, S;
            if (P > 1) {
                R = Math.ceil(P / 2);
                S = Math.floor(P / 2);
            } else {
                S = R = 1;
                var T = B(M, O);
                for (var U = 0; U < T.length; U++)T[U].colSpan++;
            }
            Q = J.clone();
            Q.insertAfter(J);
            if (!c)Q.appendBogus();
            J.$.colSpan = R;
            Q.$.colSpan = S;
            if (R == 1)J.removeAttribute('colSpan');
            if (S == 1)Q.removeAttribute('colSpan');
            return Q;
        };
        var F = {thead: 1, tbody: 1, tfoot: 1, td: 1, tr: 1, th: 1};
        j.tabletools = {requires: ['table', 'dialog'], init: function (G) {
            var H = G.lang.table;
            G.addCommand('cellProperties', new a.dialogCommand('cellProperties'));
            a.dialog.add('cellProperties', this.path + 'dialogs/tableCell.js');
            G.addCommand('tableDelete', {exec: function (I) {
                var J = I.getSelection(), K = J && J.getStartElement(), L = K && K.getAscendant('table', 1);
                if (!L)return;
                var M = L.getParent();
                if (M.getChildCount() == 1 && !M.is('body', 'td', 'th'))L = M;
                var N = new d.range(I.document);
                N.moveToPosition(L, 3);
                L.remove();
                N.select();
            }});
            G.addCommand('rowDelete', {exec: function (I) {
                var J = I.getSelection();
                z(q(J));
            }});
            G.addCommand('rowInsertBefore', {exec: function (I) {
                var J = I.getSelection();
                p(J, true);
            }});
            G.addCommand('rowInsertAfter', {exec: function (I) {
                var J = I.getSelection();
                p(J);
            }});
            G.addCommand('columnDelete', {exec: function (I) {
                var J = I.getSelection(), K = u(J);
                K && z(K, true);
            }});
            G.addCommand('columnInsertBefore', {exec: function (I) {
                var J = I.getSelection();
                t(J, true);
            }});
            G.addCommand('columnInsertAfter', {exec: function (I) {
                var J = I.getSelection();
                t(J);
            }});
            G.addCommand('cellDelete', {exec: function (I) {
                var J = I.getSelection();
                x(J);
            }});
            G.addCommand('cellMerge', {exec: function (I) {
                z(C(I.getSelection()), true);
            }});
            G.addCommand('cellMergeRight', {exec: function (I) {
                z(C(I.getSelection(), 'right'), true);
            }});
            G.addCommand('cellMergeDown', {exec: function (I) {
                z(C(I.getSelection(), 'down'), true);
            }});
            G.addCommand('cellVerticalSplit', {exec: function (I) {
                z(D(I.getSelection()));
            }});
            G.addCommand('cellHorizontalSplit', {exec: function (I) {
                z(E(I.getSelection()));
            }});
            G.addCommand('cellInsertBefore', {exec: function (I) {
                var J = I.getSelection();
                w(J, true);
            }});
            G.addCommand('cellInsertAfter', {exec: function (I) {
                var J = I.getSelection();
                w(J);
            }});
            if (G.addMenuItems)G.addMenuItems({tablecell: {label: H.cell.menu, group: 'tablecell', order: 1, getItems: function () {
                var I = G.getSelection(), J = n(I);
                return{tablecell_insertBefore: 2, tablecell_insertAfter: 2, tablecell_delete: 2, tablecell_merge: C(I, null, true) ? 2 : 0, tablecell_merge_right: C(I, 'right', true) ? 2 : 0, tablecell_merge_down: C(I, 'down', true) ? 2 : 0, tablecell_split_vertical: D(I, true) ? 2 : 0, tablecell_split_horizontal: E(I, true) ? 2 : 0, tablecell_properties: J.length > 0 ? 2 : 0};
            }}, tablecell_insertBefore: {label: H.cell.insertBefore, group: 'tablecell', command: 'cellInsertBefore', order: 5}, tablecell_insertAfter: {label: H.cell.insertAfter, group: 'tablecell', command: 'cellInsertAfter', order: 10}, tablecell_delete: {label: H.cell.deleteCell, group: 'tablecell', command: 'cellDelete', order: 15}, tablecell_merge: {label: H.cell.merge, group: 'tablecell', command: 'cellMerge', order: 16}, tablecell_merge_right: {label: H.cell.mergeRight, group: 'tablecell', command: 'cellMergeRight', order: 17}, tablecell_merge_down: {label: H.cell.mergeDown, group: 'tablecell', command: 'cellMergeDown', order: 18}, tablecell_split_horizontal: {label: H.cell.splitHorizontal, group: 'tablecell', command: 'cellHorizontalSplit', order: 19}, tablecell_split_vertical: {label: H.cell.splitVertical, group: 'tablecell', command: 'cellVerticalSplit', order: 20}, tablecell_properties: {label: H.cell.title, group: 'tablecellproperties', command: 'cellProperties', order: 21}, tablerow: {label: H.row.menu, group: 'tablerow', order: 1, getItems: function () {
                return{tablerow_insertBefore: 2, tablerow_insertAfter: 2, tablerow_delete: 2};
            }}, tablerow_insertBefore: {label: H.row.insertBefore, group: 'tablerow', command: 'rowInsertBefore', order: 5}, tablerow_insertAfter: {label: H.row.insertAfter, group: 'tablerow', command: 'rowInsertAfter', order: 10}, tablerow_delete: {label: H.row.deleteRow, group: 'tablerow', command: 'rowDelete', order: 15}, tablecolumn: {label: H.column.menu, group: 'tablecolumn', order: 1, getItems: function () {
                return{tablecolumn_insertBefore: 2, tablecolumn_insertAfter: 2, tablecolumn_delete: 2};
            }}, tablecolumn_insertBefore: {label: H.column.insertBefore, group: 'tablecolumn', command: 'columnInsertBefore', order: 5}, tablecolumn_insertAfter: {label: H.column.insertAfter, group: 'tablecolumn', command: 'columnInsertAfter', order: 10}, tablecolumn_delete: {label: H.column.deleteColumn, group: 'tablecolumn', command: 'columnDelete', order: 15}});
            if (G.contextMenu)G.contextMenu.addListener(function (I, J) {
                if (!I || I.isReadOnly())return null;
                while (I) {
                    if (I.getName() in F)return{tablecell: 2, tablerow: 2, tablecolumn: 2};
                    I = I.getParent();
                }
                return null;
            });
        }, getSelectedCells: n};
        j.add('tabletools', j.tabletools);
    })();
    e.buildTableMap = function (m) {
        var n = m.$.rows, o = -1, p = [];
        for (var q = 0; q < n.length; q++) {
            o++;
            !p[o] && (p[o] = []);
            var r = -1;
            for (var s = 0; s < n[q].cells.length; s++) {
                var t = n[q].cells[s];
                r++;
                while (p[o][r])r++;
                var u = isNaN(t.colSpan) ? 1 : t.colSpan, v = isNaN(t.rowSpan) ? 1 : t.rowSpan;
                for (var w = 0; w < v; w++) {
                    if (!p[o + w])p[o + w] = [];
                    for (var x = 0; x < u; x++)p[o + w][r + x] = n[q].cells[s];
                }
                r += u - 1;
            }
        }
        return p;
    };
    j.add('specialchar', {requires: ['dialog'], availableLangs: {cs: 1, cy: 1, de: 1, el: 1, en: 1, eo: 1, et: 1, fa: 1, fi: 1, fr: 1, he: 1, hr: 1, it: 1, nb: 1, nl: 1, no: 1, 'pt-br': 1, tr: 1, ug: 1, 'zh-cn': 1}, init: function (m) {
        var n = 'specialchar', o = this;
        a.dialog.add(n, this.path + 'dialogs/specialchar.js');
        m.addCommand(n, {exec: function () {
            var p = m.langCode;
            p = o.availableLangs[p] ? p : 'en';
            a.scriptLoader.load(a.getUrl(o.path + 'lang/' + p + '.js'), function () {
                e.extend(m.lang.specialChar, o.langEntries[p]);
                m.openDialog(n);
            });
        }, modes: {wysiwyg: 1}, canUndo: false});
        m.ui.addButton('SpecialChar', {label: m.lang.specialChar.toolbar, command: n});
    }});
    i.specialChars = ['!', '&quot;', '#', '$', '%', '&amp;', "'", '(', ')', '*', '+', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '&lt;', '=', '&gt;', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '[', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '|', '}', '~', '&euro;', '&lsquo;', '&rsquo;', '&ldquo;', '&rdquo;', '&ndash;', '&mdash;', '&iexcl;', '&cent;', '&pound;', '&curren;', '&yen;', '&brvbar;', '&sect;', '&uml;', '&copy;', '&ordf;', '&laquo;', '&not;', '&reg;', '&macr;', '&deg;', '&sup2;', '&sup3;', '&acute;', '&micro;', '&para;', '&middot;', '&cedil;', '&sup1;', '&ordm;', '&raquo;', '&frac14;', '&frac12;', '&frac34;', '&iquest;', '&Agrave;', '&Aacute;', '&Acirc;', '&Atilde;', '&Auml;', '&Aring;', '&AElig;', '&Ccedil;', '&Egrave;', '&Eacute;', '&Ecirc;', '&Euml;', '&Igrave;', '&Iacute;', '&Icirc;', '&Iuml;', '&ETH;', '&Ntilde;', '&Ograve;', '&Oacute;', '&Ocirc;', '&Otilde;', '&Ouml;', '&times;', '&Oslash;', '&Ugrave;', '&Uacute;', '&Ucirc;', '&Uuml;', '&Yacute;', '&THORN;', '&szlig;', '&agrave;', '&aacute;', '&acirc;', '&atilde;', '&auml;', '&aring;', '&aelig;', '&ccedil;', '&egrave;', '&eacute;', '&ecirc;', '&euml;', '&igrave;', '&iacute;', '&icirc;', '&iuml;', '&eth;', '&ntilde;', '&ograve;', '&oacute;', '&ocirc;', '&otilde;', '&ouml;', '&divide;', '&oslash;', '&ugrave;', '&uacute;', '&ucirc;', '&uuml;', '&yacute;', '&thorn;', '&yuml;', '&OElig;', '&oelig;', '&#372;', '&#374', '&#373', '&#375;', '&sbquo;', '&#8219;', '&bdquo;', '&hellip;', '&trade;', '&#9658;', '&bull;', '&rarr;', '&rArr;', '&hArr;', '&diams;', '&asymp;'];
    (function () {
        var m = {editorFocus: false, modes: {wysiwyg: 1, source: 1}}, n = {exec: function (q) {
            q.container.focusNext(true, q.tabIndex);
        }}, o = {exec: function (q) {
            q.container.focusPrevious(true, q.tabIndex);
        }};

        function p(q) {
            return{editorFocus: false, canUndo: false, modes: {wysiwyg: 1}, exec: function (r) {
                if (r.focusManager.hasFocus) {
                    var s = r.getSelection(), t = s.getCommonAncestor(), u;
                    if (u = t.getAscendant('td', true) || t.getAscendant('th', true)) {
                        var v = new d.range(r.document), w = e.tryThese(function () {
                            var D = u.getParent(), E = D.$.cells[u.$.cellIndex + (q ? -1 : 1)];
                            E.parentNode.parentNode;
                            return E;
                        }, function () {
                            var D = u.getParent(), E = D.getAscendant('table'), F = E.$.rows[D.$.rowIndex + (q ? -1 : 1)];
                            return F.cells[q ? F.cells.length - 1 : 0];
                        });
                        if (!(w || q)) {
                            var x = u.getAscendant('table').$, y = u.getParent().$.cells, z = new h(x.insertRow(-1), r.document);
                            for (var A = 0, B = y.length; A < B; A++) {
                                var C = z.append(new h(y[A], r.document).clone(false, false));
                                !c && C.appendBogus();
                            }
                            v.moveToElementEditStart(z);
                        } else if (w) {
                            w = new h(w);
                            v.moveToElementEditStart(w);
                            if (!(v.checkStartOfBlock() && v.checkEndOfBlock()))v.selectNodeContents(w);
                        } else return true;
                        v.select(true);
                        return true;
                    }
                }
                return false;
            }};
        };
        j.add('tab', {requires: ['keystrokes'], init: function (q) {
            var r = q.config.enableTabKeyTools !== false, s = q.config.tabSpaces || 0, t = '';
            while (s--)t += '\xa0';
            if (t)q.on('key', function (u) {
                if (u.data.keyCode == 9) {
                    q.insertHtml(t);
                    u.cancel();
                }
            });
            if (r)q.on('key', function (u) {
                if (u.data.keyCode == 9 && q.execCommand('selectNextCell') || u.data.keyCode == 2228224 + 9 && q.execCommand('selectPreviousCell'))u.cancel();
            });
            if (b.webkit || b.gecko)q.on('key', function (u) {
                var v = u.data.keyCode;
                if (v == 9 && !t) {
                    u.cancel();
                    q.execCommand('blur');
                }
                if (v == 2228224 + 9) {
                    q.execCommand('blurBack');
                    u.cancel();
                }
            });
            q.addCommand('blur', e.extend(n, m));
            q.addCommand('blurBack', e.extend(o, m));
            q.addCommand('selectNextCell', p());
            q.addCommand('selectPreviousCell', p(true));
        }});
    })();
    h.prototype.focusNext = function (m, n) {
        var w = this;
        var o = w.$, p = n === undefined ? w.getTabIndex() : n, q, r, s, t, u, v;
        if (p <= 0) {
            u = w.getNextSourceNode(m, 1);
            while (u) {
                if (u.isVisible() && u.getTabIndex() === 0) {
                    s = u;
                    break;
                }
                u = u.getNextSourceNode(false, 1);
            }
        } else {
            u = w.getDocument().getBody().getFirst();
            while (u = u.getNextSourceNode(false, 1)) {
                if (!q)if (!r && u.equals(w)) {
                    r = true;
                    if (m) {
                        if (!(u = u.getNextSourceNode(true, 1)))break;
                        q = 1;
                    }
                } else if (r && !w.contains(u))q = 1;
                if (!u.isVisible() || (v = u.getTabIndex()) < 0)continue;
                if (q && v == p) {
                    s = u;
                    break;
                }
                if (v > p && (!s || !t || v < t)) {
                    s = u;
                    t = v;
                } else if (!s && v === 0) {
                    s = u;
                    t = v;
                }
            }
        }
        if (s)s.focus();
    };
    h.prototype.focusPrevious = function (m, n) {
        var w = this;
        var o = w.$, p = n === undefined ? w.getTabIndex() : n, q, r, s, t = 0, u, v = w.getDocument().getBody().getLast();
        while (v = v.getPreviousSourceNode(false, 1)) {
            if (!q)if (!r && v.equals(w)) {
                r = true;
                if (m) {
                    if (!(v = v.getPreviousSourceNode(true, 1)))break;
                    q = 1;
                }
            } else if (r && !w.contains(v))q = 1;
            if (!v.isVisible() || (u = v.getTabIndex()) < 0)continue;
            if (p <= 0) {
                if (q && u === 0) {
                    s = v;
                    break;
                }
                if (u > t) {
                    s = v;
                    t = u;
                }
            } else {
                if (q && u == p) {
                    s = v;
                    break;
                }
                if (u < p && (!s || u > t)) {
                    s = v;
                    t = u;
                }
            }
        }
        if (s)s.focus();
    };
    (function () {
        j.add('templates', {requires: ['dialog'], init: function (o) {
            a.dialog.add('templates', a.getUrl(this.path + 'dialogs/templates.js'));
            o.addCommand('templates', new a.dialogCommand('templates'));
            o.ui.addButton('Templates', {label: o.lang.templates.button, command: 'templates'});
        }});
        var m = {}, n = {};
        a.addTemplates = function (o, p) {
            m[o] = p;
        };
        a.getTemplates = function (o) {
            return m[o];
        };
        a.loadTemplates = function (o, p) {
            var q = [];
            for (var r = 0, s = o.length; r < s; r++) {
                if (!n[o[r]]) {
                    q.push(o[r]);
                    n[o[r]] = 1;
                }
            }
            if (q.length)a.scriptLoader.load(q, p); else setTimeout(p, 0);
        };
    })();
    i.templates_files = [a.getUrl('plugins/templates/templates/default.js')];
    i.templates_replaceContent = true;
    (function () {
        var m = function () {
            this.toolbars = [];
            this.focusCommandExecuted = false;
        };
        m.prototype.focus = function () {
            for (var o = 0, p; p = this.toolbars[o++];)for (var q = 0, r; r = p.items[q++];) {
                if (r.focus) {
                    r.focus();
                    return;
                }
            }
        };
        var n = {toolbarFocus: {modes: {wysiwyg: 1, source: 1}, readOnly: 1, exec: function (o) {
            if (o.toolbox) {
                o.toolbox.focusCommandExecuted = true;
                if (c || b.air)setTimeout(function () {
                    o.toolbox.focus();
                }, 100); else o.toolbox.focus();
            }
        }}};
        j.add('toolbar', {requires: ['button'], init: function (o) {
            var p, q = function (r, s) {
                var t, u, v = o.lang.dir == 'rtl', w = o.config.toolbarGroupCycling;
                w = w === undefined || w;
                switch (s) {
                    case 9:
                    case 2228224 + 9:
                        while (!u || !u.items.length) {
                            u = s == 9 ? (u ? u.next : r.toolbar.next) || o.toolbox.toolbars[0] : (u ? u.previous : r.toolbar.previous) || o.toolbox.toolbars[o.toolbox.toolbars.length - 1];
                            if (u.items.length) {
                                r = u.items[p ? u.items.length - 1 : 0];
                                while (r && !r.focus) {
                                    r = p ? r.previous : r.next;
                                    if (!r)u = 0;
                                }
                            }
                        }
                        if (r)r.focus();
                        return false;
                    case v ? 37 : 39:
                    case 40:
                        t = r;
                        do {
                            t = t.next;
                            if (!t && w)t = r.toolbar.items[0];
                        } while (t && !t.focus);
                        if (t)t.focus(); else q(r, 9);
                        return false;
                    case v ? 39 : 37:
                    case 38:
                        t = r;
                        do {
                            t = t.previous;
                            if (!t && w)t = r.toolbar.items[r.toolbar.items.length - 1];
                        } while (t && !t.focus);
                        if (t)t.focus(); else {
                            p = 1;
                            q(r, 2228224 + 9);
                            p = 0;
                        }
                        return false;
                    case 27:
                        o.focus();
                        return false;
                    case 13:
                    case 32:
                        r.execute();
                        return false;
                }
                return true;
            };
            o.on('themeSpace', function (r) {
                if (r.data.space == o.config.toolbarLocation) {
                    o.toolbox = new m();
                    var s = e.getNextId(), t = ['<div class="cke_toolbox" role="group" aria-labelledby="', s, '" onmousedown="return false;"'], u = o.config.toolbarStartupExpanded !== false, v;
                    t.push(u ? '>' : ' style="display:none">');
                    t.push('<span id="', s, '" class="cke_voice_label">', o.lang.toolbars, '</span>');
                    var w = o.toolbox.toolbars, x = o.config.toolbar instanceof Array ? o.config.toolbar : o.config['toolbar_' + o.config.toolbar];
                    for (var y = 0; y < x.length; y++) {
                        var z, A = 0, B, C = x[y], D;
                        if (!C)continue;
                        if (v) {
                            t.push('</div>');
                            v = 0;
                        }
                        if (C === '/') {
                            t.push('<div class="cke_break"></div>');
                            continue;
                        }
                        D = C.items || C;
                        for (var E = 0; E < D.length; E++) {
                            var F, G = D[E], H;
                            F = o.ui.create(G);
                            if (F) {
                                H = F.canGroup !== false;
                                if (!A) {
                                    z = e.getNextId();
                                    A = {id: z, items: []};
                                    B = C.name && (o.lang.toolbarGroups[C.name] || C.name);
                                    t.push('<span id="', z, '" class="cke_toolbar"', B ? ' aria-labelledby="' + z + '_label"' : '', ' role="toolbar">');
                                    B && t.push('<span id="', z, '_label" class="cke_voice_label">', B, '</span>');
                                    t.push('<span class="cke_toolbar_start"></span>');
                                    var I = w.push(A) - 1;
                                    if (I > 0) {
                                        A.previous = w[I - 1];
                                        A.previous.next = A;
                                    }
                                }
                                if (H) {
                                    if (!v) {
                                        t.push('<span class="cke_toolgroup" role="presentation">');
                                        v = 1;
                                    }
                                } else if (v) {
                                    t.push('</span>');
                                    v = 0;
                                }
                                var J = F.render(o, t);
                                I = A.items.push(J) - 1;
                                if (I > 0) {
                                    J.previous = A.items[I - 1];
                                    J.previous.next = J;
                                }
                                J.toolbar = A;
                                J.onkey = q;
                                J.onfocus = function () {
                                    if (!o.toolbox.focusCommandExecuted)o.focus();
                                };
                            }
                        }
                        if (v) {
                            t.push('</span>');
                            v = 0;
                        }
                        if (A)t.push('<span class="cke_toolbar_end"></span></span>');
                    }
                    t.push('</div>');
                    if (o.config.toolbarCanCollapse) {
                        var K = e.addFunction(function () {
                            o.execCommand('toolbarCollapse');
                        });
                        o.on('destroy', function () {
                            e.removeFunction(K);
                        });
                        var L = e.getNextId();
                        o.addCommand('toolbarCollapse', {readOnly: 1, exec: function (M) {
                            var N = a.document.getById(L), O = N.getPrevious(), P = M.getThemeSpace('contents'), Q = O.getParent(), R = parseInt(P.$.style.height, 10), S = Q.$.offsetHeight, T = !O.isVisible();
                            if (!T) {
                                O.hide();
                                N.addClass('cke_toolbox_collapser_min');
                                N.setAttribute('title', M.lang.toolbarExpand);
                            } else {
                                O.show();
                                N.removeClass('cke_toolbox_collapser_min');
                                N.setAttribute('title', M.lang.toolbarCollapse);
                            }
                            N.getFirst().setText(T ? '▲' : '◀');
                            var U = Q.$.offsetHeight - S;
                            P.setStyle('height', R - U + 'px');
                            M.fire('resize');
                        }, modes: {wysiwyg: 1, source: 1}});
                        t.push('<a title="' + (u ? o.lang.toolbarCollapse : o.lang.toolbarExpand) + '" id="' + L + '" tabIndex="-1" class="cke_toolbox_collapser');
                        if (!u)t.push(' cke_toolbox_collapser_min');
                        t.push('" onclick="CKEDITOR.tools.callFunction(' + K + ')">', '<span>&#9650;</span>', '</a>');
                    }
                    r.data.html += t.join('');
                }
            });
            o.on('destroy', function () {
                var r, s = 0, t, u, v;
                r = this.toolbox.toolbars;
                for (; s < r.length; s++) {
                    u = r[s].items;
                    for (t = 0; t < u.length; t++) {
                        v = u[t];
                        if (v.clickFn)e.removeFunction(v.clickFn);
                        if (v.keyDownFn)e.removeFunction(v.keyDownFn);
                    }
                }
            });
            o.addCommand('toolbarFocus', n.toolbarFocus);
            o.ui.add('-', a.UI_SEPARATOR, {});
            o.ui.addHandler(a.UI_SEPARATOR, {create: function () {
                return{render: function (r, s) {
                    s.push('<span class="cke_separator" role="separator"></span>');
                    return{};
                }};
            }});
        }});
    })();
    a.UI_SEPARATOR = 'separator';
    i.toolbarLocation = 'top';
    i.toolbar_Basic = [
        ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink', '-', 'About']
    ];
    i.toolbar_Full = [
        {name: 'document', items: ['Source', '-', 'Save', 'NewPage', 'DocProps', 'Preview', 'Print', '-', 'Templates']},
        {name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo']},
        {name: 'editing', items: ['Find', 'Replace', '-', 'SelectAll', '-', 'SpellChecker', 'Scayt']},
        {name: 'forms', items: ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField']},
        '/',
        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat']},
        {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl']},
        {name: 'links', items: ['Link', 'Unlink', 'Anchor']},
        {name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe']},
        '/',
        {name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize']},
        {name: 'colors', items: ['TextColor', 'BGColor']},
        {name: 'tools', items: ['Maximize', 'ShowBlocks', '-', 'About']}
    ];
    i.toolbar = 'Full';
    i.toolbarCanCollapse = true;
    (function () {
        j.add('undo', {requires: ['selection', 'wysiwygarea'], init: function (s) {
            var t = new o(s), u = s.addCommand('undo', {exec: function () {
                if (t.undo()) {
                    s.selectionChange();
                    this.fire('afterUndo');
                }
            }, state: 0, canUndo: false}), v = s.addCommand('redo', {exec: function () {
                if (t.redo()) {
                    s.selectionChange();
                    this.fire('afterRedo');
                }
            }, state: 0, canUndo: false});
            t.onChange = function () {
                u.setState(t.undoable() ? 2 : 0);
                v.setState(t.redoable() ? 2 : 0);
            };
            function w(x) {
                if (t.enabled && x.data.command.canUndo !== false)t.save();
            };
            s.on('beforeCommandExec', w);
            s.on('afterCommandExec', w);
            s.on('saveSnapshot', function (x) {
                t.save(x.data && x.data.contentOnly);
            });
            s.on('contentDom', function () {
                s.document.on('keydown', function (x) {
                    if (!x.data.$.ctrlKey && !x.data.$.metaKey)t.type(x);
                });
            });
            s.on('beforeModeUnload', function () {
                s.mode == 'wysiwyg' && t.save(true);
            });
            s.on('mode', function () {
                t.enabled = s.readOnly ? false : s.mode == 'wysiwyg';
                t.onChange();
            });
            s.ui.addButton('Undo', {label: s.lang.undo, command: 'undo'});
            s.ui.addButton('Redo', {label: s.lang.redo, command: 'redo'});
            s.resetUndo = function () {
                t.reset();
                s.fire('saveSnapshot');
            };
            s.on('updateSnapshot', function () {
                if (t.currentImage)t.update();
            });
        }});
        j.undo = {};
        var m = j.undo.Image = function (s) {
            this.editor = s;
            s.fire('beforeUndoImage');
            var t = s.getSnapshot(), u = t && s.getSelection();
            c && t && (t = t.replace(/\s+data-cke-expando=".*?"/g, ''));
            this.contents = t;
            this.bookmarks = u && u.createBookmarks2(true);
            s.fire('afterUndoImage');
        }, n = /\b(?:href|src|name)="[^"]*?"/gi;
        m.prototype = {equals: function (s, t) {
            var u = this.contents, v = s.contents;
            if (c && (b.ie7Compat || b.ie6Compat)) {
                u = u.replace(n, '');
                v = v.replace(n, '');
            }
            if (u != v)return false;
            if (t)return true;
            var w = this.bookmarks, x = s.bookmarks;
            if (w || x) {
                if (!w || !x || w.length != x.length)return false;
                for (var y = 0; y < w.length; y++) {
                    var z = w[y], A = x[y];
                    if (z.startOffset != A.startOffset || z.endOffset != A.endOffset || !e.arrayCompare(z.start, A.start) || !e.arrayCompare(z.end, A.end))return false;
                }
            }
            return true;
        }};
        function o(s) {
            this.editor = s;
            this.reset();
        };
        var p = {8: 1, 46: 1}, q = {16: 1, 17: 1, 18: 1}, r = {37: 1, 38: 1, 39: 1, 40: 1};
        o.prototype = {type: function (s) {
            var t = s && s.data.getKey(), u = t in q, v = t in p, w = this.lastKeystroke in p, x = v && t == this.lastKeystroke, y = t in r, z = this.lastKeystroke in r, A = !v && !y, B = v && !x, C = !(u || this.typing) || A && (w || z);
            if (C || B) {
                var D = new m(this.editor), E = this.snapshots.length;
                e.setTimeout(function () {
                    var G = this;
                    var F = G.editor.getSnapshot();
                    if (c)F = F.replace(/\s+data-cke-expando=".*?"/g, '');
                    if (D.contents != F && E == G.snapshots.length) {
                        G.typing = true;
                        if (!G.save(false, D, false))G.snapshots.splice(G.index + 1, G.snapshots.length - G.index - 1);
                        G.hasUndo = true;
                        G.hasRedo = false;
                        G.typesCount = 1;
                        G.modifiersCount = 1;
                        G.onChange();
                    }
                }, 0, this);
            }
            this.lastKeystroke = t;
            if (v) {
                this.typesCount = 0;
                this.modifiersCount++;
                if (this.modifiersCount > 25) {
                    this.save(false, null, false);
                    this.modifiersCount = 1;
                }
            } else if (!y) {
                this.modifiersCount = 0;
                this.typesCount++;
                if (this.typesCount > 25) {
                    this.save(false, null, false);
                    this.typesCount = 1;
                }
            }
        }, reset: function () {
            var s = this;
            s.lastKeystroke = 0;
            s.snapshots = [];
            s.index = -1;
            s.limit = s.editor.config.undoStackSize || 20;
            s.currentImage = null;
            s.hasUndo = false;
            s.hasRedo = false;
            s.resetType();
        }, resetType: function () {
            var s = this;
            s.typing = false;
            delete s.lastKeystroke;
            s.typesCount = 0;
            s.modifiersCount = 0;
        }, fireChange: function () {
            var s = this;
            s.hasUndo = !!s.getNextImage(true);
            s.hasRedo = !!s.getNextImage(false);
            s.resetType();
            s.onChange();
        }, save: function (s, t, u) {
            var w = this;
            var v = w.snapshots;
            if (!t)t = new m(w.editor);
            if (t.contents === false)return false;
            if (w.currentImage && t.equals(w.currentImage, s))return false;
            v.splice(w.index + 1, v.length - w.index - 1);
            if (v.length == w.limit)v.shift();
            w.index = v.push(t) - 1;
            w.currentImage = t;
            if (u !== false)w.fireChange();
            return true;
        }, restoreImage: function (s) {
            var w = this;
            var t = w.editor, u;
            if (s.bookmarks) {
                t.focus();
                u = t.getSelection();
            }
            w.editor.loadSnapshot(s.contents);
            if (s.bookmarks)u.selectBookmarks(s.bookmarks); else if (c) {
                var v = w.editor.document.getBody().$.createTextRange();
                v.collapse(true);
                v.select();
            }
            w.index = s.index;
            w.update();
            w.fireChange();
        }, getNextImage: function (s) {
            var x = this;
            var t = x.snapshots, u = x.currentImage, v, w;
            if (u)if (s)for (w = x.index - 1; w >= 0; w--) {
                v = t[w];
                if (!u.equals(v, true)) {
                    v.index = w;
                    return v;
                }
            } else for (w = x.index + 1; w < t.length; w++) {
                v = t[w];
                if (!u.equals(v, true)) {
                    v.index = w;
                    return v;
                }
            }
            return null;
        }, redoable: function () {
            return this.enabled && this.hasRedo;
        }, undoable: function () {
            return this.enabled && this.hasUndo;
        }, undo: function () {
            var t = this;
            if (t.undoable()) {
                t.save(true);
                var s = t.getNextImage(true);
                if (s)return t.restoreImage(s), true;
            }
            return false;
        }, redo: function () {
            var t = this;
            if (t.redoable()) {
                t.save(true);
                if (t.redoable()) {
                    var s = t.getNextImage(false);
                    if (s)return t.restoreImage(s), true;
                }
            }
            return false;
        }, update: function () {
            var s = this;
            s.snapshots.splice(s.index, 1, s.currentImage = new m(s.editor));
        }};
    })();
    (function () {
        var m = /(^|<body\b[^>]*>)\s*<(p|div|address|h\d|center|pre)[^>]*>\s*(?:<br[^>]*>|&nbsp;|\u00A0|&#160;)?\s*(:?<\/\2>)?\s*(?=$|<\/body>)/gi, n = d.walker.whitespaces(true), o = d.walker.bogus(true), p = function (E) {
            return n(E) && o(E);
        };

        function q(E) {
            return E.isBlockBoundary() && f.$empty[E.getName()];
        };
        function r(E) {
            return function (F) {
                if (this.mode == 'wysiwyg') {
                    this.focus();
                    var G = this.getSelection(), H = G.isLocked;
                    H && G.unlock();
                    this.fire('saveSnapshot');
                    E.call(this, F.data);
                    H && this.getSelection().lock();
                    var I = this;
                    setTimeout(function () {
                        try {
                            I.fire('saveSnapshot');
                        } catch (J) {
                            setTimeout(function () {
                                I.fire('saveSnapshot');
                            }, 200);
                        }
                    }, 0);
                }
            };
        };
        function s(E) {
            var N = this;
            if (N.dataProcessor)E = N.dataProcessor.toHtml(E);
            if (!E)return;
            var F = N.getSelection(), G = F.getRanges()[0];
            if (G.checkReadOnly())return;
            if (b.opera) {
                var H = new d.elementPath(G.startContainer);
                if (H.block) {
                    var I = a.htmlParser.fragment.fromHtml(E, false).children;
                    for (var J = 0, K = I.length; J < K; J++) {
                        if (I[J]._.isBlockLike) {
                            G.splitBlock(N.enterMode == 3 ? 'div' : 'p');
                            G.insertNode(G.document.createText(''));
                            G.select();
                            break;
                        }
                    }
                }
            }
            if (c) {
                var L = F.getNative();
                if (L.type == 'Control')L.clear(); else if (F.getType() == 2) {
                    G = F.getRanges()[0];
                    var M = G && G.endContainer;
                    if (M && M.type == 1 && M.getAttribute('contenteditable') == 'false' && G.checkBoundaryOfElement(M, 2)) {
                        G.setEndAfter(G.endContainer);
                        G.deleteContents();
                    }
                }
                L.createRange().pasteHTML(E);
            } else N.document.$.execCommand('inserthtml', false, E);
            if (b.webkit) {
                F = N.getSelection();
                F.scrollIntoView();
            }
        };
        function t(E) {
            var F = this.getSelection(), G = F.getStartElement().hasAscendant('pre', true) ? 2 : this.config.enterMode, H = G == 2, I = e.htmlEncode(E.replace(/\r\n|\r/g, '\n'));
            I = I.replace(/^[ \t]+|[ \t]+$/g, function (O, P, Q) {
                if (O.length == 1)return '&nbsp;'; else if (!P)return e.repeat('&nbsp;', O.length - 1) + ' '; else return ' ' + e.repeat('&nbsp;', O.length - 1);
            });
            I = I.replace(/[ \t]{2,}/g, function (O) {
                return e.repeat('&nbsp;', O.length - 1) + ' ';
            });
            var J = G == 1 ? 'p' : 'div';
            if (!H)I = I.replace(/(\n{2})([\s\S]*?)(?:$|\1)/g, function (O, P, Q) {
                return '<' + J + '>' + Q + '</' + J + '>';
            });
            I = I.replace(/\n/g, '<br>');
            if (!(H || c))I = I.replace(new RegExp('<br>(?=</' + J + '>)'), function (O) {
                return e.repeat(O, 2);
            });
            if (b.gecko || b.webkit) {
                var K = new d.elementPath(F.getStartElement()), L = [];
                for (var M = 0; M < K.elements.length; M++) {
                    var N = K.elements[M].getName();
                    if (N in f.$inline)L.unshift(K.elements[M].getOuterHtml().match(/^<.*?>/)); else if (N in f.$block)break;
                }
                I = L.join('') + I;
            }
            s.call(this, I);
        };
        function u(E) {
            var F = this.getSelection(), G = F.getRanges(), H = E.getName(), I = f.$block[H], J = F.isLocked;
            if (J)F.unlock();
            var K, L, M, N;
            for (var O = G.length - 1; O >= 0; O--) {
                K = G[O];
                if (!K.checkReadOnly()) {
                    K.deleteContents(1);
                    L = !O && E || E.clone(1);
                    var P, Q;
                    if (I)while ((P = K.getCommonAncestor(0, 1)) && (Q = f[P.getName()]) && !(Q && Q[H])) {
                        if (P.getName() in f.span)K.splitElement(P); else if (K.checkStartOfBlock() && K.checkEndOfBlock()) {
                            K.setStartBefore(P);
                            K.collapse(true);
                            P.remove();
                        } else K.splitBlock();
                    }
                    K.insertNode(L);
                    if (!M)M = L;
                }
            }
            if (M) {
                K.moveToPosition(M, 4);
                if (I) {
                    var R = M.getNext(p), S = R && R.type == 1 && R.getName();
                    if (S && f.$block[S]) {
                        if (f[S]['#'])K.moveToElementEditStart(R); else K.moveToElementEditEnd(M);
                    } else if (!R) {
                        R = K.fixBlock(true, this.config.enterMode == 3 ? 'div' : 'p');
                        K.moveToElementEditStart(R);
                    }
                }
            }
            F.selectRanges([K]);
            if (J)this.getSelection().lock();
        };
        function v(E) {
            if (!E.checkDirty())setTimeout(function () {
                E.resetDirty();
            }, 0);
        };
        var w = d.walker.whitespaces(true), x = d.walker.bookmark(false, true);

        function y(E) {
            return w(E) && x(E);
        };
        function z(E) {
            return E.type == 3 && e.trim(E.getText()).match(/^(?:&nbsp;|\xa0)$/);
        };
        function A(E) {
            if (E.isLocked) {
                E.unlock();
                setTimeout(function () {
                    E.lock();
                }, 0);
            }
        };
        function B(E) {
            return E.getOuterHtml().match(m);
        };
        w = d.walker.whitespaces(true);
        function C(E) {
            var F = E.window, G = E.document, H = E.document.getBody(), I = H.getFirst(), J = H.getChildren().count();
            if (!J || J == 1 && I.type == 1 && I.hasAttribute('_moz_editor_bogus_node')) {
                v(E);
                var K = E.element.getDocument(), L = K.getDocumentElement(), M = L.$.scrollTop, N = L.$.scrollLeft, O = G.$.createEvent('KeyEvents');
                O.initKeyEvent('keypress', true, true, F.$, false, false, false, false, 0, 32);
                G.$.dispatchEvent(O);
                if (M != L.$.scrollTop || N != L.$.scrollLeft)K.getWindow().$.scrollTo(N, M);
                J && H.getFirst().remove();
                G.getBody().appendBogus();
                var P = new d.range(G);
                P.setStartAt(H, 1);
                P.select();
            }
        };
        function D(E) {
            var F = E.editor, G = E.data.path, H = G.blockLimit, I = E.data.selection, J = I.getRanges()[0], K = F.document.getBody(), L = F.config.enterMode;
            if (b.gecko) {
                var M = G.block || G.blockLimit, N = M && M.getLast(y);
                if (M && M.isBlockBoundary() && !(N && N.type == 1 && N.isBlockBoundary()) && !M.is('pre') && !M.getBogus())M.appendBogus();
            }
            if (F.config.autoParagraph !== false && L != 2 && J.collapsed && H.getName() == 'body' && !G.block) {
                var O = J.fixBlock(true, F.config.enterMode == 3 ? 'div' : 'p');
                if (c) {
                    var P = O.getFirst(y);
                    P && z(P) && P.remove();
                }
                if (B(O)) {
                    var Q = O.getNext(w);
                    if (Q && Q.type == 1 && !q(Q)) {
                        J.moveToElementEditStart(Q);
                        O.remove();
                    } else {
                        Q = O.getPrevious(w);
                        if (Q && Q.type == 1 && !q(Q)) {
                            J.moveToElementEditEnd(Q);
                            O.remove();
                        }
                    }
                }
                J.select();
                E.cancel();
            }
            var R = new d.range(F.document);
            R.moveToElementEditEnd(F.document.getBody());
            var S = new d.elementPath(R.startContainer);
            if (!S.blockLimit.is('body')) {
                var T;
                if (L != 2)T = K.append(F.document.createElement(L == 1 ? 'p' : 'div')); else T = K;
                if (!c)T.appendBogus();
            }
        };
        j.add('wysiwygarea', {requires: ['editingblock'], init: function (E) {
            var F = E.config.enterMode != 2 && E.config.autoParagraph !== false ? E.config.enterMode == 3 ? 'div' : 'p' : false, G = E.lang.editorTitle.replace('%1', E.name), H = E.lang.editorHelp;
            if (c)G += ', ' + H;
            var I = a.document.getWindow(), J;
            E.on('editingBlockReady', function () {
                var M, N, O, P, Q, R, S, T = b.isCustomDomain(), U = function (X) {
                    if (N)N.remove();
                    var Y = 'document.open();' + (T ? 'document.domain="' + document.domain + '";' : '') + 'document.close();';
                    Y = b.air ? 'javascript:void(0)' : c ? 'javascript:void(function(){' + encodeURIComponent(Y) + '}())' : '';
                    var Z = e.getNextId();
                    N = h.createFromHtml('<iframe style="width:100%;height:100%" frameBorder="0" aria-describedby="' + Z + '"' + ' title="' + G + '"' + ' src="' + Y + '"' + ' tabIndex="' + (b.webkit ? -1 : E.tabIndex) + '"' + ' allowTransparency="true"' + '></iframe>');
                    if (document.location.protocol == 'chrome:')a.event.useCapture = true;
                    N.on('load', function (aa) {
                        Q = 1;
                        aa.removeListener();
                        var ab = N.getFrameDocument();
                        ab.write(X);
                        b.air && W(ab.getWindow().$);
                    });
                    if (document.location.protocol == 'chrome:')a.event.useCapture = false;
                    M.append(h.createFromHtml('<span id="' + Z + '" class="cke_voice_label">' + H + '</span>'));
                    M.append(N);
                    if (b.webkit) {
                        S = function () {
                            M.setStyle('width', '100%');
                            N.hide();
                            N.setSize('width', M.getSize('width'));
                            M.removeStyle('width');
                            N.show();
                        };
                        I.on('resize', S);
                    }
                };
                J = e.addFunction(W);
                var V = '<script id="cke_actscrpt" type="text/javascript" data-cke-temp="1">' + (T ? 'document.domain="' + document.domain + '";' : '') + 'window.parent.CKEDITOR.tools.callFunction( ' + J + ', window );' + '</script>';

                function W(X) {
                    if (!Q)return;
                    Q = 0;
                    E.fire('ariaWidget', N);
                    var Y = X.document, Z = Y.body, aa = Y.getElementById('cke_actscrpt');
                    aa && aa.parentNode.removeChild(aa);
                    Z.spellcheck = !E.config.disableNativeSpellChecker;
                    var ab = !E.readOnly;
                    if (c) {
                        Z.hideFocus = true;
                        Z.disabled = true;
                        Z.contentEditable = ab;
                        Z.removeAttribute('disabled');
                    } else setTimeout(function () {
                        if (b.gecko && b.version >= 10900 || b.opera)Y.$.body.contentEditable = ab; else if (b.webkit)Y.$.body.parentNode.contentEditable = ab; else Y.$.designMode = ab ? 'off' : 'on';
                    }, 0);
                    ab && b.gecko && e.setTimeout(C, 0, null, E);
                    X = E.window = new d.window(X);
                    Y = E.document = new g(Y);
                    ab && Y.on('dblclick', function (ag) {
                        var ah = ag.data.getTarget(), ai = {element: ah, dialog: ''};
                        E.fire('doubleclick', ai);
                        ai.dialog && E.openDialog(ai.dialog);
                    });
                    c && Y.on('click', function (ag) {
                        var ah = ag.data.getTarget();
                        if (ah.is('input')) {
                            var ai = ah.getAttribute('type');
                            if (ai == 'submit' || ai == 'reset')ag.data.preventDefault();
                        }
                    });
                    if (!(c || b.opera))Y.on('mousedown', function (ag) {
                        var ah = ag.data.getTarget();
                        if (ah.is('img', 'hr', 'input', 'textarea', 'select'))E.getSelection().selectElement(ah);
                    });
                    if (b.gecko)Y.on('mouseup', function (ag) {
                        if (ag.data.$.button == 2) {
                            var ah = ag.data.getTarget();
                            if (!ah.getOuterHtml().replace(m, '')) {
                                var ai = new d.range(Y);
                                ai.moveToElementEditStart(ah);
                                ai.select(true);
                            }
                        }
                    });
                    Y.on('click', function (ag) {
                        ag = ag.data;
                        if (ag.getTarget().is('a') && ag.$.button != 2)ag.preventDefault();
                    });
                    if (b.webkit) {
                        Y.on('mousedown', function () {
                            ad = 1;
                        });
                        Y.on('click', function (ag) {
                            if (ag.data.getTarget().is('input', 'select'))ag.data.preventDefault();
                        });
                        Y.on('mouseup', function (ag) {
                            if (ag.data.getTarget().is('input', 'textarea'))ag.data.preventDefault();
                        });
                    }
                    var ac = c ? N : X;
                    ac.on('blur', function () {
                        E.focusManager.blur();
                    });
                    var ad;
                    ac.on('focus', function () {
                        var ag = E.document;
                        if (b.gecko || b.opera)ag.getBody().focus(); else if (b.webkit)if (!ad) {
                            E.document.getDocumentElement().focus();
                            ad = 1;
                        }
                        E.focusManager.focus();
                    });
                    var ae = E.keystrokeHandler;
                    ae.blockedKeystrokes[8] = !ab;
                    ae.attach(Y);
                    Y.getDocumentElement().addClass(Y.$.compatMode);
                    E.on('key', function (ag) {
                        if (E.mode != 'wysiwyg')return;
                        var ah = ag.data.keyCode;
                        if (ah in {8: 1, 46: 1}) {
                            var ai = E.getSelection(), aj = ai.getSelectedElement(), ak = ai.getRanges()[0], al = new d.elementPath(ak.startContainer), am, an, ao, ap = ah == 8;
                            if (aj) {
                                E.fire('saveSnapshot');
                                ak.moveToPosition(aj, 3);
                                aj.remove();
                                ak.select();
                                E.fire('saveSnapshot');
                                ag.cancel();
                            } else if (ak.collapsed)if ((am = al.block) && ak[ap ? 'checkStartOfBlock' : 'checkEndOfBlock']() && (ao = am[ap ? 'getPrevious' : 'getNext'](n)) && ao.is('table')) {
                                E.fire('saveSnapshot');
                                if (ak[ap ? 'checkEndOfBlock' : 'checkStartOfBlock']())am.remove();
                                ak['moveToElementEdit' + (ap ? 'End' : 'Start')](ao);
                                ak.select();
                                E.fire('saveSnapshot');
                                ag.cancel();
                            } else if (al.blockLimit.is('td') && (an = al.blockLimit.getAscendant('table')) && ak.checkBoundaryOfElement(an, ap ? 1 : 2) && (ao = an[ap ? 'getPrevious' : 'getNext'](n))) {
                                E.fire('saveSnapshot');
                                ak['moveToElementEdit' + (ap ? 'End' : 'Start')](ao);
                                if (ak.checkStartOfBlock() && ak.checkEndOfBlock())ao.remove(); else ak.select();
                                E.fire('saveSnapshot');
                                ag.cancel();
                            }
                        }
                        if (ah == 33 || ah == 34)if (b.gecko) {
                            var aq = Y.getBody();
                            if (X.$.innerHeight > aq.$.offsetHeight) {
                                ak = new d.range(Y);
                                ak[ah == 33 ? 'moveToElementEditStart' : 'moveToElementEditEnd'](aq);
                                ak.select();
                                ag.cancel();
                            }
                        }
                    });
                    if (c && Y.$.compatMode == 'CSS1Compat') {
                        var af = {33: 1, 34: 1};
                        Y.on('keydown', function (ag) {
                            if (ag.data.getKeystroke() in af)setTimeout(function () {
                                E.getSelection().scrollIntoView();
                            }, 0);
                        });
                    }
                    if (c && E.config.enterMode != 1)Y.on('selectionchange', function () {
                        var ag = Y.getBody(), ah = E.getSelection(), ai = ah && ah.getRanges()[0];
                        if (ai && ag.getHtml().match(/^<p>&nbsp;<\/p>$/i) && ai.startContainer.equals(ag))setTimeout(function () {
                            ai = E.getSelection().getRanges()[0];
                            if (!ai.startContainer.equals('body')) {
                                ag.getFirst().remove(1);
                                ai.moveToElementEditEnd(ag);
                                ai.select(1);
                            }
                        }, 0);
                    });
                    if (E.contextMenu)E.contextMenu.addTarget(Y, E.config.browserContextMenuOnCtrl !== false);
                    setTimeout(function () {
                        E.fire('contentDom');
                        if (R) {
                            E.mode = 'wysiwyg';
                            E.fire('mode', {previousMode: E._.previousMode});
                            R = false;
                        }
                        O = false;
                        if (P) {
                            E.focus();
                            P = false;
                        }
                        setTimeout(function () {
                            E.fire('dataReady');
                        }, 0);
                        try {
                            E.document.$.execCommand('2D-position', false, true);
                        } catch (ag) {
                        }
                        try {
                            E.document.$.execCommand('enableInlineTableEditing', false, !E.config.disableNativeTableHandles);
                        } catch (ah) {
                        }
                        if (E.config.disableObjectResizing)try {
                            E.document.$.execCommand('enableObjectResizing', false, false);
                        } catch (ai) {
                            E.document.getBody().on(c ? 'resizestart' : 'resize', function (aj) {
                                aj.data.preventDefault();
                            });
                        }
                        if (c)setTimeout(function () {
                            if (E.document) {
                                var aj = E.document.$.body;
                                aj.runtimeStyle.marginBottom = '0px';
                                aj.runtimeStyle.marginBottom = '';
                            }
                        }, 1000);
                    }, 0);
                };
                E.addMode('wysiwyg', {load: function (X, Y, Z) {
                    M = X;
                    if (c && b.quirks)X.setStyle('position', 'relative');
                    E.mayBeDirty = true;
                    R = true;
                    if (Z)this.loadSnapshotData(Y); else this.loadData(Y);
                }, loadData: function (X) {
                    O = true;
                    E._.dataStore = {id: 1};
                    var Y = E.config, Z = Y.fullPage, aa = Y.docType, ab = '<style type="text/css" data-cke-temp="1">' + E._.styles.join('\n') + '</style>';
                    !Z && (ab = e.buildStyleHtml(E.config.contentsCss) + ab);
                    var ac = Y.baseHref ? '<base href="' + Y.baseHref + '" data-cke-temp="1" />' : '';
                    if (Z)X = X.replace(/<!DOCTYPE[^>]*>/i, function (ad) {
                        E.docType = aa = ad;
                        return '';
                    }).replace(/<\?xml\s[^\?]*\?>/i, function (ad) {
                        E.xmlDeclaration = ad;
                        return '';
                    });
                    if (E.dataProcessor)X = E.dataProcessor.toHtml(X, F);
                    if (Z) {
                        if (!/<body[\s|>]/.test(X))X = '<body>' + X;
                        if (!/<html[\s|>]/.test(X))X = '<html>' + X + '</html>';
                        if (!/<head[\s|>]/.test(X))X = X.replace(/<html[^>]*>/, '$&<head><title></title></head>'); else if (!/<title[\s|>]/.test(X))X = X.replace(/<head[^>]*>/, '$&<title></title>');
                        ac && (X = X.replace(/<head>/, '$&' + ac));
                        X = X.replace(/<\/head\s*>/, ab + '$&');
                        X = aa + X;
                    } else X = Y.docType + '<html dir="' + Y.contentsLangDirection + '"' + ' lang="' + (Y.contentsLanguage || E.langCode) + '">' + '<head>' + '<title>' + G + '</title>' + ac + ab + '</head>' + '<body' + (Y.bodyId ? ' id="' + Y.bodyId + '"' : '') + (Y.bodyClass ? ' class="' + Y.bodyClass + '"' : '') + '>' + X + '</html>';
                    if (b.gecko)X = X.replace(/<br \/>(?=\s*<\/(:?html|body)>)/, '$&<br type="_moz" />');
                    X += V;
                    this.onDispose();
                    U(X);
                }, getData: function () {
                    var X = E.config, Y = X.fullPage, Z = Y && E.docType, aa = Y && E.xmlDeclaration, ab = N.getFrameDocument(), ac = Y ? ab.getDocumentElement().getOuterHtml() : ab.getBody().getHtml();
                    if (b.gecko)ac = ac.replace(/<br>(?=\s*(:?$|<\/body>))/, '');
                    if (E.dataProcessor)ac = E.dataProcessor.toDataFormat(ac, F);
                    if (X.ignoreEmptyParagraph)ac = ac.replace(m, function (ad, ae) {
                        return ae;
                    });
                    if (aa)ac = aa + '\n' + ac;
                    if (Z)ac = Z + '\n' + ac;
                    return ac;
                }, getSnapshotData: function () {
                    return N.getFrameDocument().getBody().getHtml();
                }, loadSnapshotData: function (X) {
                    N.getFrameDocument().getBody().setHtml(X);
                }, onDispose: function () {
                    if (!E.document)return;
                    E.document.getDocumentElement().clearCustomData();
                    E.document.getBody().clearCustomData();
                    E.window.clearCustomData();
                    E.document.clearCustomData();
                    N.clearCustomData();
                    N.remove();
                }, unload: function (X) {
                    this.onDispose();
                    if (S)I.removeListener('resize', S);
                    E.window = E.document = N = M = P = null;
                    E.fire('contentDomUnload');
                }, focus: function () {
                    var X = E.window;
                    if (O)P = true; else if (X) {
                        var Y = E.getSelection(), Z = Y && Y.getNative();
                        if (Z && Z.type == 'Control')return;
                        b.air ? setTimeout(function () {
                            X.focus();
                        }, 0) : X.focus();
                        E.selectionChange();
                    }
                }});
                E.on('insertHtml', r(s), null, null, 20);
                E.on('insertElement', r(u), null, null, 20);
                E.on('insertText', r(t), null, null, 20);
                E.on('selectionChange', function (X) {
                    if (E.readOnly)return;
                    var Y = E.getSelection();
                    if (Y && !Y.isLocked) {
                        var Z = E.checkDirty();
                        E.fire('saveSnapshot', {contentOnly: 1});
                        D.call(this, X);
                        E.fire('updateSnapshot');
                        !Z && E.resetDirty();
                    }
                }, null, null, 1);
            });
            E.on('contentDom', function () {
                var M = E.document.getElementsByTag('title').getItem(0);
                M.data('cke-title', E.document.$.title);
                c && (E.document.$.title = G);
            });
            E.on('readOnly', function () {
                if (E.mode == 'wysiwyg') {
                    var M = E.getMode();
                    M.loadData(M.getData());
                }
            });
            if (a.document.$.documentMode >= 8) {
                E.addCss('html.CSS1Compat [contenteditable=false]{ min-height:0 !important;}');
                var K = [];
                for (var L in f.$removeEmpty)K.push('html.CSS1Compat ' + L + '[contenteditable=false]');
                E.addCss(K.join(',') + '{ display:inline-block;}');
            } else if (b.gecko) {
                E.addCss('html { height: 100% !important; }');
                E.addCss('img:-moz-broken { -moz-force-broken-image-icon : 1;\tmin-width : 24px; min-height : 24px; }');
            }
            E.addCss('html {\t_overflow-y: scroll; cursor: text;\t*cursor:auto;}');
            E.addCss('img, input, textarea { cursor: default;}');
            E.on('insertElement', function (M) {
                var N = M.data;
                if (N.type == 1 && (N.is('input') || N.is('textarea'))) {
                    var O = N.getAttribute('contenteditable') == 'false';
                    if (!O) {
                        N.data('cke-editable', N.hasAttribute('contenteditable') ? 'true' : '1');
                        N.setAttribute('contenteditable', false);
                    }
                }
            });
        }});
        if (b.gecko)(function () {
            var E = document.body;
            if (!E)window.addEventListener('load', arguments.callee, false); else {
                var F = E.getAttribute('onpageshow');
                E.setAttribute('onpageshow', (F ? F + ';' : '') + 'event.persisted && (function(){' + 'var allInstances = CKEDITOR.instances, editor, doc;' + 'for ( var i in allInstances )' + '{' + '\teditor = allInstances[ i ];' + '\tdoc = editor.document;' + '\tif ( doc )' + '\t{' + '\t\tdoc.$.designMode = "off";' + '\t\tdoc.$.designMode = "on";' + '\t}' + '}' + '})();');
            }
        })();
    })();
    i.disableObjectResizing = false;
    i.disableNativeTableHandles = true;
    i.disableNativeSpellChecker = true;
    i.ignoreEmptyParagraph = true;
    j.add('wsc', {requires: ['dialog'], init: function (m) {
        var n = 'checkspell', o = m.addCommand(n, new a.dialogCommand(n));
        o.modes = {wysiwyg: !b.opera && !b.air && document.domain == window.location.hostname};
        m.ui.addButton('SpellChecker', {label: m.lang.spellCheck.toolbar, command: n});
        a.dialog.add(n, this.path + 'dialogs/wsc.js');
    }});
    i.wsc_customerId = i.wsc_customerId || '1:ua3xw1-2XyGJ3-GWruD3-6OFNT1-oXcuB1-nR6Bp4-hgQHc-EcYng3-sdRXG3-NOfFk';
    i.wsc_customLoaderScript = i.wsc_customLoaderScript || null;
    a.DIALOG_RESIZE_NONE = 0;
    a.DIALOG_RESIZE_WIDTH = 1;
    a.DIALOG_RESIZE_HEIGHT = 2;
    a.DIALOG_RESIZE_BOTH = 3;
    (function () {
        var m = e.cssLength;

        function n(R) {
            return!!this._.tabs[R][0].$.offsetHeight;
        };
        function o() {
            var V = this;
            var R = V._.currentTabId, S = V._.tabIdList.length, T = e.indexOf(V._.tabIdList, R) + S;
            for (var U = T - 1; U > T - S; U--) {
                if (n.call(V, V._.tabIdList[U % S]))return V._.tabIdList[U % S];
            }
            return null;
        };
        function p() {
            var V = this;
            var R = V._.currentTabId, S = V._.tabIdList.length, T = e.indexOf(V._.tabIdList, R);
            for (var U = T + 1; U < T + S; U++) {
                if (n.call(V, V._.tabIdList[U % S]))return V._.tabIdList[U % S];
            }
            return null;
        };
        function q(R, S) {
            var T = R.$.getElementsByTagName('input');
            for (var U = 0, V = T.length; U < V; U++) {
                var W = new h(T[U]);
                if (W.getAttribute('type').toLowerCase() == 'text')if (S) {
                    W.setAttribute('value', W.getCustomData('fake_value') || '');
                    W.removeCustomData('fake_value');
                } else {
                    W.setCustomData('fake_value', W.getAttribute('value'));
                    W.setAttribute('value', '');
                }
            }
        };
        function r(R, S) {
            var U = this;
            var T = U.getInputElement();
            if (T)R ? T.removeAttribute('aria-invalid') : T.setAttribute('aria-invalid', true);
            if (!R)if (U.select)U.select(); else U.focus();
            S && alert(S);
            U.fire('validated', {valid: R, msg: S});
        };
        function s() {
            var R = this.getInputElement();
            R && R.removeAttribute('aria-invalid');
        };
        a.dialog = function (R, S) {
            var T = a.dialog._.dialogDefinitions[S], U = e.clone(v), V = R.config.dialog_buttonsOrder || 'OS', W = R.lang.dir, X = {}, Y, Z, aa;
            if (V == 'OS' && b.mac || V == 'rtl' && W == 'ltr' || V == 'ltr' && W == 'rtl')U.buttons.reverse();
            T = e.extend(T(R), U);
            T = e.clone(T);
            T = new z(this, T);
            var ab = a.document, ac = R.theme.buildDialog(R);
            this._ = {editor: R, element: ac.element, name: S, contentSize: {width: 0, height: 0}, size: {width: 0, height: 0}, contents: {}, buttons: {}, accessKeyMap: {}, tabs: {}, tabIdList: [], currentTabId: null, currentTabIndex: null, pageCount: 0, lastTab: null, tabBarMode: false, focusList: [], currentFocusIndex: 0, hasFocus: false};
            this.parts = ac.parts;
            e.setTimeout(function () {
                R.fire('ariaWidget', this.parts.contents);
            }, 0, this);
            var ad = {position: b.ie6Compat ? 'absolute' : 'fixed', top: 0, visibility: 'hidden'};
            ad[W == 'rtl' ? 'right' : 'left'] = 0;
            this.parts.dialog.setStyles(ad);
            a.event.call(this);
            this.definition = T = a.fire('dialogDefinition', {name: S, definition: T}, R).definition;
            if (!('removeDialogTabs' in R._) && R.config.removeDialogTabs) {
                var ae = R.config.removeDialogTabs.split(';');
                for (Y = 0; Y < ae.length; Y++) {
                    var af = ae[Y].split(':');
                    if (af.length == 2) {
                        var ag = af[0];
                        if (!X[ag])X[ag] = [];
                        X[ag].push(af[1]);
                    }
                }
                R._.removeDialogTabs = X;
            }
            if (R._.removeDialogTabs && (X = R._.removeDialogTabs[S]))for (Y = 0; Y < X.length; Y++)T.removeContents(X[Y]);
            if (T.onLoad)this.on('load', T.onLoad);
            if (T.onShow)this.on('show', T.onShow);
            if (T.onHide)this.on('hide', T.onHide);
            if (T.onOk)this.on('ok', function (ar) {
                R.fire('saveSnapshot');
                setTimeout(function () {
                    R.fire('saveSnapshot');
                }, 0);
                if (T.onOk.call(this, ar) === false)ar.data.hide = false;
            });
            if (T.onCancel)this.on('cancel', function (ar) {
                if (T.onCancel.call(this, ar) === false)ar.data.hide = false;
            });
            var ah = this, ai = function (ar) {
                var as = ah._.contents, at = false;
                for (var au in as)for (var av in as[au]) {
                    at = ar.call(this, as[au][av]);
                    if (at)return;
                }
            };
            this.on('ok', function (ar) {
                ai(function (as) {
                    if (as.validate) {
                        var at = as.validate(this), au = typeof at == 'string' || at === false;
                        if (au) {
                            ar.data.hide = false;
                            ar.stop();
                        }
                        r.call(as, !au, typeof at == 'string' ? at : undefined);
                        return au;
                    }
                });
            }, this, null, 0);
            this.on('cancel', function (ar) {
                ai(function (as) {
                    if (as.isChanged()) {
                        if (!confirm(R.lang.common.confirmCancel))ar.data.hide = false;
                        return true;
                    }
                });
            }, this, null, 0);
            this.parts.close.on('click', function (ar) {
                if (this.fire('cancel', {hide: true}).hide !== false)this.hide();
                ar.data.preventDefault();
            }, this);
            function aj() {
                var ar = ah._.focusList;
                ar.sort(function (au, av) {
                    if (au.tabIndex != av.tabIndex)return av.tabIndex - au.tabIndex; else return au.focusIndex - av.focusIndex;
                });
                var as = ar.length;
                for (var at = 0; at < as; at++)ar[at].focusIndex = at;
            };
            function ak(ar) {
                var as = ah._.focusList;
                ar = ar || 0;
                if (as.length < 1)return;
                var at = ah._.currentFocusIndex;
                try {
                    as[at].getInputElement().$.blur();
                } catch (aw) {
                }
                var au = (at + ar + as.length) % as.length, av = au;
                while (ar && !as[av].isFocusable()) {
                    av = (av + ar + as.length) % as.length;
                    if (av == au)break;
                }
                as[av].focus();
                if (as[av].type == 'text')as[av].select();
            };
            this.changeFocus = ak;
            function al(ar) {
                var ay = this;
                if (ah != a.dialog._.currentTop)return;
                var as = ar.data.getKeystroke(), at = R.lang.dir == 'rtl', au;
                Z = aa = 0;
                if (as == 9 || as == 2228224 + 9) {
                    var av = as == 2228224 + 9;
                    if (ah._.tabBarMode) {
                        var aw = av ? o.call(ah) : p.call(ah);
                        ah.selectPage(aw);
                        ah._.tabs[aw][0].focus();
                    } else ak(av ? -1 : 1);
                    Z = 1;
                } else if (as == 4456448 + 121 && !ah._.tabBarMode && ah.getPageCount() > 1) {
                    ah._.tabBarMode = true;
                    ah._.tabs[ah._.currentTabId][0].focus();
                    Z = 1;
                } else if ((as == 37 || as == 39) && ah._.tabBarMode) {
                    aw = as == (at ? 39 : 37) ? o.call(ah) : p.call(ah);
                    ah.selectPage(aw);
                    ah._.tabs[aw][0].focus();
                    Z = 1;
                } else if ((as == 13 || as == 32) && ah._.tabBarMode) {
                    ay.selectPage(ay._.currentTabId);
                    ay._.tabBarMode = false;
                    ay._.currentFocusIndex = -1;
                    ak(1);
                    Z = 1;
                } else if (as == 13) {
                    var ax = ar.data.getTarget();
                    if (!ax.is('a', 'button', 'select', 'textarea') && (!ax.is('input') || ax.$.type != 'button')) {
                        au = ay.getButton('ok');
                        au && e.setTimeout(au.click, 0, au);
                        Z = 1;
                    }
                    aa = 1;
                } else if (as == 27) {
                    au = ay.getButton('cancel');
                    if (au)e.setTimeout(au.click, 0, au); else if (ay.fire('cancel', {hide: true}).hide !== false)ay.hide();
                    aa = 1;
                } else return;
                am(ar);
            };
            function am(ar) {
                if (Z)ar.data.preventDefault(1); else if (aa)ar.data.stopPropagation();
            };
            var an = this._.element;
            this.on('show', function () {
                an.on('keydown', al, this);
                if (b.opera || b.gecko)an.on('keypress', am, this);
            });
            this.on('hide', function () {
                an.removeListener('keydown', al);
                if (b.opera || b.gecko)an.removeListener('keypress', am);
                ai(function (ar) {
                    s.apply(ar);
                });
            });
            this.on('iframeAdded', function (ar) {
                var as = new g(ar.data.iframe.$.contentWindow.document);
                as.on('keydown', al, this, null, 0);
            });
            this.on('show', function () {
                var av = this;
                aj();
                if (R.config.dialog_startupFocusTab && ah._.pageCount > 1) {
                    ah._.tabBarMode = true;
                    ah._.tabs[ah._.currentTabId][0].focus();
                } else if (!av._.hasFocus) {
                    av._.currentFocusIndex = -1;
                    if (T.onFocus) {
                        var ar = T.onFocus.call(av);
                        ar && ar.focus();
                    } else ak(1);
                    if (av._.editor.mode == 'wysiwyg' && c) {
                        var as = R.document.$.selection, at = as.createRange();
                        if (at)if (at.parentElement && at.parentElement().ownerDocument == R.document.$ || at.item && at.item(0).ownerDocument == R.document.$) {
                            var au = document.body.createTextRange();
                            au.moveToElementText(av.getElement().getFirst().$);
                            au.collapse(true);
                            au.select();
                        }
                    }
                }
            }, this, null, 4294967295);
            if (b.ie6Compat)this.on('load', function (ar) {
                var as = this.getElement(), at = as.getFirst();
                at.remove();
                at.appendTo(as);
            }, this);
            B(this);
            C(this);
            new d.text(T.title, a.document).appendTo(this.parts.title);
            for (Y = 0; Y < T.contents.length; Y++) {
                var ao = T.contents[Y];
                ao && this.addPage(ao);
            }
            this.parts.tabs.on('click', function (ar) {
                var au = this;
                var as = ar.data.getTarget();
                if (as.hasClass('cke_dialog_tab')) {
                    var at = as.$.id;
                    au.selectPage(at.substring(4, at.lastIndexOf('_')));
                    if (au._.tabBarMode) {
                        au._.tabBarMode = false;
                        au._.currentFocusIndex = -1;
                        ak(1);
                    }
                    ar.data.preventDefault();
                }
            }, this);
            var ap = [], aq = a.dialog._.uiElementBuilders.hbox.build(this, {type: 'hbox', className: 'cke_dialog_footer_buttons', widths: [], children: T.buttons}, ap).getChild();
            this.parts.footer.setHtml(ap.join(''));
            for (Y = 0; Y < aq.length; Y++)this._.buttons[aq[Y].id] = aq[Y];
        };
        function t(R, S, T) {
            this.element = S;
            this.focusIndex = T;
            this.tabIndex = 0;
            this.isFocusable = function () {
                return!S.getAttribute('disabled') && S.isVisible();
            };
            this.focus = function () {
                R._.currentFocusIndex = this.focusIndex;
                this.element.focus();
            };
            S.on('keydown', function (U) {
                if (U.data.getKeystroke() in {32: 1, 13: 1})this.fire('click');
            });
            S.on('focus', function () {
                this.fire('mouseover');
            });
            S.on('blur', function () {
                this.fire('mouseout');
            });
        };
        function u(R) {
            var S = a.document.getWindow();

            function T() {
                R.layout();
            };
            S.on('resize', T);
            R.on('hide', function () {
                S.removeListener('resize', T);
            });
        };
        a.dialog.prototype = {destroy: function () {
            this.hide();
            this._.element.remove();
        }, resize: (function () {
            return function (R, S) {
                var T = this;
                if (T._.contentSize && T._.contentSize.width == R && T._.contentSize.height == S)return;
                a.dialog.fire('resize', {dialog: T, skin: T._.editor.skinName, width: R, height: S}, T._.editor);
                T.fire('resize', {skin: T._.editor.skinName, width: R, height: S}, T._.editor);
                if (T._.editor.lang.dir == 'rtl' && T._.position)T._.position.x = a.document.getWindow().getViewPaneSize().width - T._.contentSize.width - parseInt(T._.element.getFirst().getStyle('right'), 10);
                T._.contentSize = {width: R, height: S};
            };
        })(), getSize: function () {
            var R = this._.element.getFirst();
            return{width: R.$.offsetWidth || 0, height: R.$.offsetHeight || 0};
        }, move: function (R, S, T) {
            var ab = this;
            var U = ab._.element.getFirst(), V = ab._.editor.lang.dir == 'rtl', W = U.getComputedStyle('position') == 'fixed';
            if (W && ab._.position && ab._.position.x == R && ab._.position.y == S)return;
            ab._.position = {x: R, y: S};
            if (!W) {
                var X = a.document.getWindow().getScrollPosition();
                R += X.x;
                S += X.y;
            }
            if (V) {
                var Y = ab.getSize(), Z = a.document.getWindow().getViewPaneSize();
                R = Z.width - Y.width - R;
            }
            var aa = {top: (S > 0 ? S : 0) + 'px'};
            aa[V ? 'right' : 'left'] = (R > 0 ? R : 0) + 'px';
            U.setStyles(aa);
            T && (ab._.moved = 1);
        }, getPosition: function () {
            return e.extend({}, this._.position);
        }, show: function () {
            var R = this._.element, S = this.definition;
            if (!(R.getParent() && R.getParent().equals(a.document.getBody())))R.appendTo(a.document.getBody()); else R.setStyle('display', 'block');
            if (b.gecko && b.version < 10900) {
                var T = this.parts.dialog;
                T.setStyle('position', 'absolute');
                setTimeout(function () {
                    T.setStyle('position', 'fixed');
                }, 0);
            }
            this.resize(this._.contentSize && this._.contentSize.width || S.width || S.minWidth, this._.contentSize && this._.contentSize.height || S.height || S.minHeight);
            this.reset();
            this.selectPage(this.definition.contents[0].id);
            if (a.dialog._.currentZIndex === null)a.dialog._.currentZIndex = this._.editor.config.baseFloatZIndex;
            this._.element.getFirst().setStyle('z-index', a.dialog._.currentZIndex += 10);
            if (a.dialog._.currentTop === null) {
                a.dialog._.currentTop = this;
                this._.parentDialog = null;
                H(this._.editor);
            } else {
                this._.parentDialog = a.dialog._.currentTop;
                var U = this._.parentDialog.getElement().getFirst();
                U.$.style.zIndex -= Math.floor(this._.editor.config.baseFloatZIndex / 2);
                a.dialog._.currentTop = this;
            }
            R.on('keydown', L);
            R.on(b.opera ? 'keypress' : 'keyup', M);
            this._.hasFocus = false;
            e.setTimeout(function () {
                this.layout();
                u(this);
                this.parts.dialog.setStyle('visibility', '');
                this.fireOnce('load', {});
                k.fire('ready', this);
                this.fire('show', {});
                this._.editor.fire('dialogShow', this);
                this.foreach(function (V) {
                    V.setInitValue && V.setInitValue();
                });
            }, 100, this);
        }, layout: function () {
            var X = this;
            var R = X.parts.dialog, S = X.getSize(), T = a.document.getWindow(), U = T.getViewPaneSize(), V = (U.width - S.width) / 2, W = (U.height - S.height) / 2;
            if (!b.ie6Compat)if (S.height + (W > 0 ? W : 0) > U.height || S.width + (V > 0 ? V : 0) > U.width)R.setStyle('position', 'absolute'); else R.setStyle('position', 'fixed');
            X.move(X._.moved ? X._.position.x : V, X._.moved ? X._.position.y : W);
        }, foreach: function (R) {
            var U = this;
            for (var S in U._.contents)for (var T in U._.contents[S])R.call(U, U._.contents[S][T]);
            return U;
        }, reset: (function () {
            var R = function (S) {
                if (S.reset)S.reset(1);
            };
            return function () {
                this.foreach(R);
                return this;
            };
        })(), setupContent: function () {
            var R = arguments;
            this.foreach(function (S) {
                if (S.setup)S.setup.apply(S, R);
            });
        }, commitContent: function () {
            var R = arguments;
            this.foreach(function (S) {
                if (c && this._.currentFocusIndex == S.focusIndex)S.getInputElement().$.blur();
                if (S.commit)S.commit.apply(S, R);
            });
        }, hide: function () {
            if (!this.parts.dialog.isVisible())return;
            this.fire('hide', {});
            this._.editor.fire('dialogHide', this);
            this.selectPage(this._.tabIdList[0]);
            var R = this._.element;
            R.setStyle('display', 'none');
            this.parts.dialog.setStyle('visibility', 'hidden');
            O(this);
            while (a.dialog._.currentTop != this)a.dialog._.currentTop.hide();
            if (!this._.parentDialog)I(); else {
                var S = this._.parentDialog.getElement().getFirst();
                S.setStyle('z-index', parseInt(S.$.style.zIndex, 10) + Math.floor(this._.editor.config.baseFloatZIndex / 2));
            }
            a.dialog._.currentTop = this._.parentDialog;
            if (!this._.parentDialog) {
                a.dialog._.currentZIndex = null;
                R.removeListener('keydown', L);
                R.removeListener(b.opera ? 'keypress' : 'keyup', M);
                var T = this._.editor;
                T.focus();
                if (T.mode == 'wysiwyg' && c) {
                    var U = T.getSelection();
                    U && U.unlock(true);
                }
            } else a.dialog._.currentZIndex -= 10;
            delete this._.parentDialog;
            this.foreach(function (V) {
                V.resetInitValue && V.resetInitValue();
            });
        }, addPage: function (R) {
            var ad = this;
            var S = [], T = R.label ? ' title="' + e.htmlEncode(R.label) + '"' : '', U = R.elements, V = a.dialog._.uiElementBuilders.vbox.build(ad, {type: 'vbox', className: 'cke_dialog_page_contents', children: R.elements, expand: !!R.expand, padding: R.padding, style: R.style || 'width: 100%;height:100%'}, S), W = h.createFromHtml(S.join(''));
            W.setAttribute('role', 'tabpanel');
            var X = b, Y = 'cke_' + R.id + '_' + e.getNextNumber(), Z = h.createFromHtml(['<a class="cke_dialog_tab"', ad._.pageCount > 0 ? ' cke_last' : 'cke_first', T, !!R.hidden ? ' style="display:none"' : '', ' id="', Y, '"', X.gecko && X.version >= 10900 && !X.hc ? '' : ' href="javascript:void(0)"', ' tabIndex="-1"', ' hidefocus="true"', ' role="tab">', R.label, '</a>'].join(''));
            W.setAttribute('aria-labelledby', Y);
            ad._.tabs[R.id] = [Z, W];
            ad._.tabIdList.push(R.id);
            !R.hidden && ad._.pageCount++;
            ad._.lastTab = Z;
            ad.updateStyle();
            var aa = ad._.contents[R.id] = {}, ab, ac = V.getChild();
            while (ab = ac.shift()) {
                aa[ab.id] = ab;
                if (typeof ab.getChild == 'function')ac.push.apply(ac, ab.getChild());
            }
            W.setAttribute('name', R.id);
            W.appendTo(ad.parts.contents);
            Z.unselectable();
            ad.parts.tabs.append(Z);
            if (R.accessKey) {
                N(ad, ad, 'CTRL+' + R.accessKey, Q, P);
                ad._.accessKeyMap['CTRL+' + R.accessKey] = R.id;
            }
        }, selectPage: function (R) {
            if (this._.currentTabId == R)return;
            if (this.fire('selectPage', {page: R, currentPage: this._.currentTabId}) === true)return;
            for (var S in this._.tabs) {
                var T = this._.tabs[S][0], U = this._.tabs[S][1];
                if (S != R) {
                    T.removeClass('cke_dialog_tab_selected');
                    U.hide();
                }
                U.setAttribute('aria-hidden', S != R);
            }
            var V = this._.tabs[R];
            V[0].addClass('cke_dialog_tab_selected');
            if (b.ie6Compat || b.ie7Compat) {
                q(V[1]);
                V[1].show();
                setTimeout(function () {
                    q(V[1], 1);
                }, 0);
            } else V[1].show();
            this._.currentTabId = R;
            this._.currentTabIndex = e.indexOf(this._.tabIdList, R);
        }, updateStyle: function () {
            this.parts.dialog[(this._.pageCount === 1 ? 'add' : 'remove') + 'Class']('cke_single_page');
        }, hidePage: function (R) {
            var T = this;
            var S = T._.tabs[R] && T._.tabs[R][0];
            if (!S || T._.pageCount == 1 || !S.isVisible())return; else if (R == T._.currentTabId)T.selectPage(o.call(T));
            S.hide();
            T._.pageCount--;
            T.updateStyle();
        }, showPage: function (R) {
            var T = this;
            var S = T._.tabs[R] && T._.tabs[R][0];
            if (!S)return;
            S.show();
            T._.pageCount++;
            T.updateStyle();
        }, getElement: function () {
            return this._.element;
        }, getName: function () {
            return this._.name;
        }, getContentElement: function (R, S) {
            var T = this._.contents[R];
            return T && T[S];
        }, getValueOf: function (R, S) {
            return this.getContentElement(R, S).getValue();
        }, setValueOf: function (R, S, T) {
            return this.getContentElement(R, S).setValue(T);
        }, getButton: function (R) {
            return this._.buttons[R];
        }, click: function (R) {
            return this._.buttons[R].click();
        }, disableButton: function (R) {
            return this._.buttons[R].disable();
        }, enableButton: function (R) {
            return this._.buttons[R].enable();
        }, getPageCount: function () {
            return this._.pageCount;
        }, getParentEditor: function () {
            return this._.editor;
        }, getSelectedElement: function () {
            return this.getParentEditor().getSelection().getSelectedElement();
        }, addFocusable: function (R, S) {
            var U = this;
            if (typeof S == 'undefined') {
                S = U._.focusList.length;
                U._.focusList.push(new t(U, R, S));
            } else {
                U._.focusList.splice(S, 0, new t(U, R, S));
                for (var T = S + 1; T < U._.focusList.length; T++)U._.focusList[T].focusIndex++;
            }
        }};
        e.extend(a.dialog, {add: function (R, S) {
            if (!this._.dialogDefinitions[R] || typeof S == 'function')this._.dialogDefinitions[R] = S;
        }, exists: function (R) {
            return!!this._.dialogDefinitions[R];
        }, getCurrent: function () {
            return a.dialog._.currentTop;
        }, okButton: (function () {
            var R = function (S, T) {
                T = T || {};
                return e.extend({id: 'ok', type: 'button', label: S.lang.common.ok, 'class': 'cke_dialog_ui_button_ok', onClick: function (U) {
                    var V = U.data.dialog;
                    if (V.fire('ok', {hide: true}).hide !== false)V.hide();
                }}, T, true);
            };
            R.type = 'button';
            R.override = function (S) {
                return e.extend(function (T) {
                    return R(T, S);
                }, {type: 'button'}, true);
            };
            return R;
        })(), cancelButton: (function () {
            var R = function (S, T) {
                T = T || {};
                return e.extend({id: 'cancel', type: 'button', label: S.lang.common.cancel, 'class': 'cke_dialog_ui_button_cancel', onClick: function (U) {
                    var V = U.data.dialog;
                    if (V.fire('cancel', {hide: true}).hide !== false)V.hide();
                }}, T, true);
            };
            R.type = 'button';
            R.override = function (S) {
                return e.extend(function (T) {
                    return R(T, S);
                }, {type: 'button'}, true);
            };
            return R;
        })(), addUIElement: function (R, S) {
            this._.uiElementBuilders[R] = S;
        }});
        a.dialog._ = {uiElementBuilders: {}, dialogDefinitions: {}, currentTop: null, currentZIndex: null};
        a.event.implementOn(a.dialog);
        a.event.implementOn(a.dialog.prototype, true);
        var v = {resizable: 3, minWidth: 600, minHeight: 400, buttons: [a.dialog.okButton, a.dialog.cancelButton]}, w = function (R, S, T) {
            for (var U = 0, V; V = R[U]; U++) {
                if (V.id == S)return V;
                if (T && V[T]) {
                    var W = w(V[T], S, T);
                    if (W)return W;
                }
            }
            return null;
        }, x = function (R, S, T, U, V) {
            if (T) {
                for (var W = 0, X; X = R[W]; W++) {
                    if (X.id == T) {
                        R.splice(W, 0, S);
                        return S;
                    }
                    if (U && X[U]) {
                        var Y = x(X[U], S, T, U, true);
                        if (Y)return Y;
                    }
                }
                if (V)return null;
            }
            R.push(S);
            return S;
        }, y = function (R, S, T) {
            for (var U = 0, V; V = R[U]; U++) {
                if (V.id == S)return R.splice(U, 1);
                if (T && V[T]) {
                    var W = y(V[T], S, T);
                    if (W)return W;
                }
            }
            return null;
        }, z = function (R, S) {
            this.dialog = R;
            var T = S.contents;
            for (var U = 0, V; V = T[U]; U++)T[U] = V && new A(R, V);
            e.extend(this, S);
        };
        z.prototype = {getContents: function (R) {
            return w(this.contents, R);
        }, getButton: function (R) {
            return w(this.buttons, R);
        }, addContents: function (R, S) {
            return x(this.contents, R, S);
        }, addButton: function (R, S) {
            return x(this.buttons, R, S);
        }, removeContents: function (R) {
            y(this.contents, R);
        }, removeButton: function (R) {
            y(this.buttons, R);
        }};
        function A(R, S) {
            this._ = {dialog: R};
            e.extend(this, S);
        };
        A.prototype = {get: function (R) {
            return w(this.elements, R, 'children');
        }, add: function (R, S) {
            return x(this.elements, R, S, 'children');
        }, remove: function (R) {
            y(this.elements, R, 'children');
        }};
        function B(R) {
            var S = null, T = null, U = R.getElement().getFirst(), V = R.getParentEditor(), W = V.config.dialog_magnetDistance, X = V.skin.margins || [0, 0, 0, 0];
            if (typeof W == 'undefined')W = 20;
            function Y(aa) {
                var ab = R.getSize(), ac = a.document.getWindow().getViewPaneSize(), ad = aa.data.$.screenX, ae = aa.data.$.screenY, af = ad - S.x, ag = ae - S.y, ah, ai;
                S = {x: ad, y: ae};
                T.x += af;
                T.y += ag;
                if (T.x + X[3] < W)ah = -X[3]; else if (T.x - X[1] > ac.width - ab.width - W)ah = ac.width - ab.width + (V.lang.dir == 'rtl' ? 0 : X[1]); else ah = T.x;
                if (T.y + X[0] < W)ai = -X[0]; else if (T.y - X[2] > ac.height - ab.height - W)ai = ac.height - ab.height + X[2]; else ai = T.y;
                R.move(ah, ai, 1);
                aa.data.preventDefault();
            };
            function Z(aa) {
                a.document.removeListener('mousemove', Y);
                a.document.removeListener('mouseup', Z);
                if (b.ie6Compat) {
                    var ab = F.getChild(0).getFrameDocument();
                    ab.removeListener('mousemove', Y);
                    ab.removeListener('mouseup', Z);
                }
            };
            R.parts.title.on('mousedown', function (aa) {
                S = {x: aa.data.$.screenX, y: aa.data.$.screenY};
                a.document.on('mousemove', Y);
                a.document.on('mouseup', Z);
                T = R.getPosition();
                if (b.ie6Compat) {
                    var ab = F.getChild(0).getFrameDocument();
                    ab.on('mousemove', Y);
                    ab.on('mouseup', Z);
                }
                aa.data.preventDefault();
            }, R);
        };
        function C(R) {
            var S = R.definition, T = S.resizable;
            if (T == 0)return;
            var U = R.getParentEditor(), V, W, X, Y, Z, aa, ab = e.addFunction(function (ae) {
                Z = R.getSize();
                var af = R.parts.contents, ag = af.$.getElementsByTagName('iframe').length;
                if (ag) {
                    aa = h.createFromHtml('<div class="cke_dialog_resize_cover" style="height: 100%; position: absolute; width: 100%;"></div>');
                    af.append(aa);
                }
                W = Z.height - R.parts.contents.getSize('height', !(b.gecko || b.opera || c && b.quirks));
                V = Z.width - R.parts.contents.getSize('width', 1);
                Y = {x: ae.screenX, y: ae.screenY};
                X = a.document.getWindow().getViewPaneSize();
                a.document.on('mousemove', ac);
                a.document.on('mouseup', ad);
                if (b.ie6Compat) {
                    var ah = F.getChild(0).getFrameDocument();
                    ah.on('mousemove', ac);
                    ah.on('mouseup', ad);
                }
                ae.preventDefault && ae.preventDefault();
            });
            R.on('load', function () {
                var ae = '';
                if (T == 1)ae = ' cke_resizer_horizontal'; else if (T == 2)ae = ' cke_resizer_vertical';
                var af = h.createFromHtml('<div class="cke_resizer' + ae + ' cke_resizer_' + U.lang.dir + '"' + ' title="' + e.htmlEncode(U.lang.resize) + '"' + ' onmousedown="CKEDITOR.tools.callFunction(' + ab + ', event )"></div>');
                R.parts.footer.append(af, 1);
            });
            U.on('destroy', function () {
                e.removeFunction(ab);
            });
            function ac(ae) {
                var af = U.lang.dir == 'rtl', ag = (ae.data.$.screenX - Y.x) * (af ? -1 : 1), ah = ae.data.$.screenY - Y.y, ai = Z.width, aj = Z.height, ak = ai + ag * (R._.moved ? 1 : 2), al = aj + ah * (R._.moved ? 1 : 2), am = R._.element.getFirst(), an = af && am.getComputedStyle('right'), ao = R.getPosition();
                if (ao.y + al > X.height)al = X.height - ao.y;
                if ((af ? an : ao.x) + ak > X.width)ak = X.width - (af ? an : ao.x);
                if (T == 1 || T == 3)ai = Math.max(S.minWidth || 0, ak - V);
                if (T == 2 || T == 3)aj = Math.max(S.minHeight || 0, al - W);
                R.resize(ai, aj);
                if (!R._.moved)R.layout();
                ae.data.preventDefault();
            };
            function ad() {
                a.document.removeListener('mouseup', ad);
                a.document.removeListener('mousemove', ac);
                if (aa) {
                    aa.remove();
                    aa = null;
                }
                if (b.ie6Compat) {
                    var ae = F.getChild(0).getFrameDocument();
                    ae.removeListener('mouseup', ad);
                    ae.removeListener('mousemove', ac);
                }
            };
        };
        var D, E = {}, F;

        function G(R) {
            R.data.preventDefault(1);
        };
        function H(R) {
            var S = a.document.getWindow(), T = R.config, U = T.dialog_backgroundCoverColor || 'white', V = T.dialog_backgroundCoverOpacity, W = T.baseFloatZIndex, X = e.genKey(U, V, W), Y = E[X];
            if (!Y) {
                var Z = ['<div tabIndex="-1" style="position: ', b.ie6Compat ? 'absolute' : 'fixed', '; z-index: ', W, '; top: 0px; left: 0px; ', !b.ie6Compat ? 'background-color: ' + U : '', '" class="cke_dialog_background_cover">'];
                if (b.ie6Compat) {
                    var aa = b.isCustomDomain(), ab = "<html><body style=\\'background-color:" + U + ";\\'></body></html>";
                    Z.push('<iframe hidefocus="true" frameborder="0" id="cke_dialog_background_iframe" src="javascript:');
                    Z.push('void((function(){document.open();' + (aa ? "document.domain='" + document.domain + "';" : '') + "document.write( '" + ab + "' );" + 'document.close();' + '})())');
                    Z.push('" style="position:absolute;left:0;top:0;width:100%;height: 100%;progid:DXImageTransform.Microsoft.Alpha(opacity=0)"></iframe>');
                }
                Z.push('</div>');
                Y = h.createFromHtml(Z.join(''));
                Y.setOpacity(V != undefined ? V : 0.5);
                Y.on('keydown', G);
                Y.on('keypress', G);
                Y.on('keyup', G);
                Y.appendTo(a.document.getBody());
                E[X] = Y;
            } else Y.show();
            F = Y;
            var ac = function () {
                var af = S.getViewPaneSize();
                Y.setStyles({width: af.width + 'px', height: af.height + 'px'});
            }, ad = function () {
                var af = S.getScrollPosition(), ag = a.dialog._.currentTop;
                Y.setStyles({left: af.x + 'px', top: af.y + 'px'});
                if (ag)do {
                    var ah = ag.getPosition();
                    ag.move(ah.x, ah.y);
                } while (ag = ag._.parentDialog)
            };
            D = ac;
            S.on('resize', ac);
            ac();
            if (!(b.mac && b.webkit))Y.focus();
            if (b.ie6Compat) {
                var ae = function () {
                    ad();
                    arguments.callee.prevScrollHandler.apply(this, arguments);
                };
                S.$.setTimeout(function () {
                    ae.prevScrollHandler = window.onscroll || (function () {
                    });
                    window.onscroll = ae;
                }, 0);
                ad();
            }
        };
        function I() {
            if (!F)return;
            var R = a.document.getWindow();
            F.hide();
            R.removeListener('resize', D);
            if (b.ie6Compat)R.$.setTimeout(function () {
                var S = window.onscroll && window.onscroll.prevScrollHandler;
                window.onscroll = S || null;
            }, 0);
            D = null;
        };
        function J() {
            for (var R in E)E[R].remove();
            E = {};
        };
        var K = {}, L = function (R) {
            var S = R.data.$.ctrlKey || R.data.$.metaKey, T = R.data.$.altKey, U = R.data.$.shiftKey, V = String.fromCharCode(R.data.$.keyCode), W = K[(S ? 'CTRL+' : '') + (T ? 'ALT+' : '') + (U ? 'SHIFT+' : '') + V];
            if (!W || !W.length)return;
            W = W[W.length - 1];
            W.keydown && W.keydown.call(W.uiElement, W.dialog, W.key);
            R.data.preventDefault();
        }, M = function (R) {
            var S = R.data.$.ctrlKey || R.data.$.metaKey, T = R.data.$.altKey, U = R.data.$.shiftKey, V = String.fromCharCode(R.data.$.keyCode), W = K[(S ? 'CTRL+' : '') + (T ? 'ALT+' : '') + (U ? 'SHIFT+' : '') + V];
            if (!W || !W.length)return;
            W = W[W.length - 1];
            if (W.keyup) {
                W.keyup.call(W.uiElement, W.dialog, W.key);
                R.data.preventDefault();
            }
        }, N = function (R, S, T, U, V) {
            var W = K[T] || (K[T] = []);
            W.push({uiElement: R, dialog: S, key: T, keyup: V || R.accessKeyUp, keydown: U || R.accessKeyDown});
        }, O = function (R) {
            for (var S in K) {
                var T = K[S];
                for (var U = T.length - 1; U >= 0; U--) {
                    if (T[U].dialog == R || T[U].uiElement == R)T.splice(U, 1);
                }
                if (T.length === 0)delete K[S];
            }
        }, P = function (R, S) {
            if (R._.accessKeyMap[S])R.selectPage(R._.accessKeyMap[S]);
        }, Q = function (R, S) {
        };
        (function () {
            k.dialog = {uiElement: function (R, S, T, U, V, W, X) {
                if (arguments.length < 4)return;
                var Y = (U.call ? U(S) : U) || 'div', Z = ['<', Y, ' '], aa = (V && V.call ? V(S) : V) || {}, ab = (W && W.call ? W(S) : W) || {}, ac = (X && X.call ? X.call(this, R, S) : X) || '', ad = this.domId = ab.id || e.getNextId() + '_uiElement', ae = this.id = S.id, af;
                ab.id = ad;
                var ag = {};
                if (S.type)ag['cke_dialog_ui_' + S.type] = 1;
                if (S.className)ag[S.className] = 1;
                if (S.disabled)ag.cke_disabled = 1;
                var ah = ab['class'] && ab['class'].split ? ab['class'].split(' ') : [];
                for (af = 0; af < ah.length; af++) {
                    if (ah[af])ag[ah[af]] = 1;
                }
                var ai = [];
                for (af in ag)ai.push(af);
                ab['class'] = ai.join(' ');
                if (S.title)ab.title = S.title;
                var aj = (S.style || '').split(';');
                if (S.align) {
                    var ak = S.align;
                    aa['margin-left'] = ak == 'left' ? 0 : 'auto';
                    aa['margin-right'] = ak == 'right' ? 0 : 'auto';
                }
                for (af in aa)aj.push(af + ':' + aa[af]);
                if (S.hidden)aj.push('display:none');
                for (af = aj.length - 1; af >= 0; af--) {
                    if (aj[af] === '')aj.splice(af, 1);
                }
                if (aj.length > 0)ab.style = (ab.style ? ab.style + '; ' : '') + aj.join('; ');
                for (af in ab)Z.push(af + '="' + e.htmlEncode(ab[af]) + '" ');
                Z.push('>', ac, '</', Y, '>');
                T.push(Z.join(''));
                (this._ || (this._ = {})).dialog = R;
                if (typeof S.isChanged == 'boolean')this.isChanged = function () {
                    return S.isChanged;
                };
                if (typeof S.isChanged == 'function')this.isChanged = S.isChanged;
                if (typeof S.setValue == 'function')this.setValue = e.override(this.setValue, function (am) {
                    return function (an) {
                        am.call(this, S.setValue.call(this, an));
                    };
                });
                if (typeof S.getValue == 'function')this.getValue = e.override(this.getValue, function (am) {
                    return function () {
                        return S.getValue.call(this, am.call(this));
                    };
                });
                a.event.implementOn(this);
                this.registerEvents(S);
                if (this.accessKeyUp && this.accessKeyDown && S.accessKey)N(this, R, 'CTRL+' + S.accessKey);
                var al = this;
                R.on('load', function () {
                    var am = al.getInputElement();
                    if (am) {
                        var an = al.type in {checkbox: 1, ratio: 1} && c && b.version < 8 ? 'cke_dialog_ui_focused' : '';
                        am.on('focus', function () {
                            R._.tabBarMode = false;
                            R._.hasFocus = true;
                            al.fire('focus');
                            an && this.addClass(an);
                        });
                        am.on('blur', function () {
                            al.fire('blur');
                            an && this.removeClass(an);
                        });
                    }
                });
                if (this.keyboardFocusable) {
                    this.tabIndex = S.tabIndex || 0;
                    this.focusIndex = R._.focusList.push(this) - 1;
                    this.on('focus', function () {
                        R._.currentFocusIndex = al.focusIndex;
                    });
                }
                e.extend(this, S);
            }, hbox: function (R, S, T, U, V) {
                if (arguments.length < 4)return;
                this._ || (this._ = {});
                var W = this._.children = S, X = V && V.widths || null, Y = V && V.height || null, Z = {}, aa, ab = function () {
                    var ad = ['<tbody><tr class="cke_dialog_ui_hbox">'];
                    for (aa = 0; aa < T.length; aa++) {
                        var ae = 'cke_dialog_ui_hbox_child', af = [];
                        if (aa === 0)ae = 'cke_dialog_ui_hbox_first';
                        if (aa == T.length - 1)ae = 'cke_dialog_ui_hbox_last';
                        ad.push('<td class="', ae, '" role="presentation" ');
                        if (X) {
                            if (X[aa])af.push('width:' + m(X[aa]));
                        } else af.push('width:' + Math.floor(100 / T.length) + '%');
                        if (Y)af.push('height:' + m(Y));
                        if (V && V.padding != undefined)af.push('padding:' + m(V.padding));
                        if (c && b.quirks && W[aa].align)af.push('text-align:' + W[aa].align);
                        if (af.length > 0)ad.push('style="' + af.join('; ') + '" ');
                        ad.push('>', T[aa], '</td>');
                    }
                    ad.push('</tr></tbody>');
                    return ad.join('');
                }, ac = {role: 'presentation'};
                V && V.align && (ac.align = V.align);
                k.dialog.uiElement.call(this, R, V || {type: 'hbox'}, U, 'table', Z, ac, ab);
            }, vbox: function (R, S, T, U, V) {
                if (arguments.length < 3)return;
                this._ || (this._ = {});
                var W = this._.children = S, X = V && V.width || null, Y = V && V.heights || null, Z = function () {
                    var aa = ['<table role="presentation" cellspacing="0" border="0" '];
                    aa.push('style="');
                    if (V && V.expand)aa.push('height:100%;');
                    aa.push('width:' + m(X || '100%'), ';');
                    aa.push('"');
                    aa.push('align="', e.htmlEncode(V && V.align || (R.getParentEditor().lang.dir == 'ltr' ? 'left' : 'right')), '" ');
                    aa.push('><tbody>');
                    for (var ab = 0; ab < T.length; ab++) {
                        var ac = [];
                        aa.push('<tr><td role="presentation" ');
                        if (X)ac.push('width:' + m(X || '100%'));
                        if (Y)ac.push('height:' + m(Y[ab])); else if (V && V.expand)ac.push('height:' + Math.floor(100 / T.length) + '%');
                        if (V && V.padding != undefined)ac.push('padding:' + m(V.padding));
                        if (c && b.quirks && W[ab].align)ac.push('text-align:' + W[ab].align);
                        if (ac.length > 0)aa.push('style="', ac.join('; '), '" ');
                        aa.push(' class="cke_dialog_ui_vbox_child">', T[ab], '</td></tr>');
                    }
                    aa.push('</tbody></table>');
                    return aa.join('');
                };
                k.dialog.uiElement.call(this, R, V || {type: 'vbox'}, U, 'div', null, {role: 'presentation'}, Z);
            }};
        })();
        k.dialog.uiElement.prototype = {getElement: function () {
            return a.document.getById(this.domId);
        }, getInputElement: function () {
            return this.getElement();
        }, getDialog: function () {
            return this._.dialog;
        }, setValue: function (R, S) {
            this.getInputElement().setValue(R);
            !S && this.fire('change', {value: R});
            return this;
        }, getValue: function () {
            return this.getInputElement().getValue();
        }, isChanged: function () {
            return false;
        }, selectParentTab: function () {
            var U = this;
            var R = U.getInputElement(), S = R, T;
            while ((S = S.getParent()) && S.$.className.search('cke_dialog_page_contents') == -1) {
            }
            if (!S)return U;
            T = S.getAttribute('name');
            if (U._.dialog._.currentTabId != T)U._.dialog.selectPage(T);
            return U;
        }, focus: function () {
            this.selectParentTab().getInputElement().focus();
            return this;
        }, registerEvents: function (R) {
            var S = /^on([A-Z]\w+)/, T, U = function (W, X, Y, Z) {
                X.on('load', function () {
                    W.getInputElement().on(Y, Z, W);
                });
            };
            for (var V in R) {
                if (!(T = V.match(S)))continue;
                if (this.eventProcessors[V])this.eventProcessors[V].call(this, this._.dialog, R[V]); else U(this, this._.dialog, T[1].toLowerCase(), R[V]);
            }
            return this;
        }, eventProcessors: {onLoad: function (R, S) {
            R.on('load', S, this);
        }, onShow: function (R, S) {
            R.on('show', S, this);
        }, onHide: function (R, S) {
            R.on('hide', S, this);
        }}, accessKeyDown: function (R, S) {
            this.focus();
        }, accessKeyUp: function (R, S) {
        }, disable: function () {
            var R = this.getElement(), S = this.getInputElement();
            S.setAttribute('disabled', 'true');
            R.addClass('cke_disabled');
        }, enable: function () {
            var R = this.getElement(), S = this.getInputElement();
            S.removeAttribute('disabled');
            R.removeClass('cke_disabled');
        }, isEnabled: function () {
            return!this.getElement().hasClass('cke_disabled');
        }, isVisible: function () {
            return this.getInputElement().isVisible();
        }, isFocusable: function () {
            if (!this.isEnabled() || !this.isVisible())return false;
            return true;
        }};
        k.dialog.hbox.prototype = e.extend(new k.dialog.uiElement(), {getChild: function (R) {
            var S = this;
            if (arguments.length < 1)return S._.children.concat();
            if (!R.splice)R = [R];
            if (R.length < 2)return S._.children[R[0]]; else return S._.children[R[0]] && S._.children[R[0]].getChild ? S._.children[R[0]].getChild(R.slice(1, R.length)) : null;
        }}, true);
        k.dialog.vbox.prototype = new k.dialog.hbox();
        (function () {
            var R = {build: function (S, T, U) {
                var V = T.children, W, X = [], Y = [];
                for (var Z = 0; Z < V.length && (W = V[Z]); Z++) {
                    var aa = [];
                    X.push(aa);
                    Y.push(a.dialog._.uiElementBuilders[W.type].build(S, W, aa));
                }
                return new k.dialog[T.type](S, Y, X, U, T);
            }};
            a.dialog.addUIElement('hbox', R);
            a.dialog.addUIElement('vbox', R);
        })();
        a.dialogCommand = function (R) {
            this.dialogName = R;
        };
        a.dialogCommand.prototype = {exec: function (R) {
            b.opera ? e.setTimeout(function () {
                R.openDialog(this.dialogName);
            }, 0, this) : R.openDialog(this.dialogName);
        }, canUndo: false, editorFocus: c || b.webkit};
        (function () {
            var R = /^([a]|[^a])+$/, S = /^\d*$/, T = /^\d*(?:\.\d+)?$/, U = /^(((\d*(\.\d+))|(\d*))(px|\%)?)?$/, V = /^(((\d*(\.\d+))|(\d*))(px|em|ex|in|cm|mm|pt|pc|\%)?)?$/i, W = /^(\s*[\w-]+\s*:\s*[^:;]+(?:;|$))*$/;
            a.VALIDATE_OR = 1;
            a.VALIDATE_AND = 2;
            a.dialog.validate = {functions: function () {
                var X = arguments;
                return function () {
                    var Y = this && this.getValue ? this.getValue() : X[0], Z = undefined, aa = 2, ab = [], ac;
                    for (ac = 0; ac < X.length; ac++) {
                        if (typeof X[ac] == 'function')ab.push(X[ac]); else break;
                    }
                    if (ac < X.length && typeof X[ac] == 'string') {
                        Z = X[ac];
                        ac++;
                    }
                    if (ac < X.length && typeof X[ac] == 'number')aa = X[ac];
                    var ad = aa == 2 ? true : false;
                    for (ac = 0; ac < ab.length; ac++) {
                        if (aa == 2)ad = ad && ab[ac](Y); else ad = ad || ab[ac](Y);
                    }
                    return!ad ? Z : true;
                };
            }, regex: function (X, Y) {
                return function () {
                    var Z = this && this.getValue ? this.getValue() : arguments[0];
                    return!X.test(Z) ? Y : true;
                };
            }, notEmpty: function (X) {
                return this.regex(R, X);
            }, integer: function (X) {
                return this.regex(S, X);
            }, number: function (X) {
                return this.regex(T, X);
            }, cssLength: function (X) {
                return this.functions(function (Y) {
                    return V.test(e.trim(Y));
                }, X);
            }, htmlLength: function (X) {
                return this.functions(function (Y) {
                    return U.test(e.trim(Y));
                }, X);
            }, inlineStyle: function (X) {
                return this.functions(function (Y) {
                    return W.test(e.trim(Y));
                }, X);
            }, equals: function (X, Y) {
                return this.functions(function (Z) {
                    return Z == X;
                }, Y);
            }, notEqual: function (X, Y) {
                return this.functions(function (Z) {
                    return Z != X;
                }, Y);
            }};
            a.on('instanceDestroyed', function (X) {
                if (e.isEmpty(a.instances)) {
                    var Y;
                    while (Y = a.dialog._.currentTop)Y.hide();
                    J();
                }
                var Z = X.editor._.storedDialogs;
                for (var aa in Z)Z[aa].destroy();
            });
        })();
        e.extend(a.editor.prototype, {openDialog: function (R, S) {
            if (this.mode == 'wysiwyg' && c) {
                var T = this.getSelection();
                T && T.lock();
            }
            var U = a.dialog._.dialogDefinitions[R], V = this.skin.dialog;
            if (a.dialog._.currentTop === null)H(this);
            if (typeof U == 'function' && V._isLoaded) {
                var W = this._.storedDialogs || (this._.storedDialogs = {}), X = W[R] || (W[R] = new a.dialog(this, R));
                S && S.call(X, X);
                X.show();
                return X;
            } else if (U == 'failed') {
                I();
                throw new Error('[CKEDITOR.dialog.openDialog] Dialog "' + R + '" failed when loading definition.');
            }
            var Y = this;

            function Z(ab) {
                var ac = a.dialog._.dialogDefinitions[R], ad = Y.skin.dialog;
                if (!ad._isLoaded || aa && typeof ab == 'undefined')return;
                if (typeof ac != 'function')a.dialog._.dialogDefinitions[R] = 'failed';
                Y.openDialog(R, S);
            };
            if (typeof U == 'string') {
                var aa = 1;
                a.scriptLoader.load(a.getUrl(U), Z, null, 0, 1);
            }
            a.skins.load(this, 'dialog', Z);
            return null;
        }});
    })();
    j.add('dialog', {requires: ['dialogui']});
    j.add('styles', {requires: ['selection'], init: function (m) {
        m.on('contentDom', function () {
            m.document.setCustomData('cke_includeReadonly', !m.config.disableReadonlyStyling);
        });
    }});
    a.editor.prototype.attachStyleStateChange = function (m, n) {
        var o = this._.styleStateChangeCallbacks;
        if (!o) {
            o = this._.styleStateChangeCallbacks = [];
            this.on('selectionChange', function (p) {
                for (var q = 0; q < o.length; q++) {
                    var r = o[q], s = r.style.checkActive(p.data.path) ? 1 : 2;
                    r.fn.call(this, s);
                }
            });
        }
        o.push({style: m, fn: n});
    };
    a.STYLE_BLOCK = 1;
    a.STYLE_INLINE = 2;
    a.STYLE_OBJECT = 3;
    (function () {
        var m = {address: 1, div: 1, h1: 1, h2: 1, h3: 1, h4: 1, h5: 1, h6: 1, p: 1, pre: 1, section: 1, header: 1, footer: 1, nav: 1, article: 1, aside: 1, figure: 1, dialog: 1, hgroup: 1, time: 1, meter: 1, menu: 1, command: 1, keygen: 1, output: 1, progress: 1, details: 1, datagrid: 1, datalist: 1}, n = {a: 1, embed: 1, hr: 1, img: 1, li: 1, object: 1, ol: 1, table: 1, td: 1, tr: 1, th: 1, ul: 1, dl: 1, dt: 1, dd: 1, form: 1, audio: 1, video: 1}, o = /\s*(?:;\s*|$)/, p = /#\((.+?)\)/g, q = d.walker.bookmark(0, 1), r = d.walker.whitespaces(1);
        a.style = function (T, U) {
            var X = this;
            var V = T.attributes;
            if (V && V.style) {
                T.styles = e.extend({}, T.styles, Q(V.style));
                delete V.style;
            }
            if (U) {
                T = e.clone(T);
                L(T.attributes, U);
                L(T.styles, U);
            }
            var W = X.element = T.element ? typeof T.element == 'string' ? T.element.toLowerCase() : T.element : '*';
            X.type = m[W] ? 1 : n[W] ? 3 : 2;
            if (typeof X.element == 'object')X.type = 3;
            X._ = {definition: T};
        };
        a.style.prototype = {apply: function (T) {
            S.call(this, T, false);
        }, remove: function (T) {
            S.call(this, T, true);
        }, applyToRange: function (T) {
            var U = this;
            return(U.applyToRange = U.type == 2 ? t : U.type == 1 ? x : U.type == 3 ? v : null).call(U, T);
        }, removeFromRange: function (T) {
            var U = this;
            return(U.removeFromRange = U.type == 2 ? u : U.type == 1 ? y : U.type == 3 ? w : null).call(U, T);
        }, applyToObject: function (T) {
            K(T, this);
        }, checkActive: function (T) {
            var Y = this;
            switch (Y.type) {
                case 1:
                    return Y.checkElementRemovable(T.block || T.blockLimit, true);
                case 3:
                case 2:
                    var U = T.elements;
                    for (var V = 0, W; V < U.length; V++) {
                        W = U[V];
                        if (Y.type == 2 && (W == T.block || W == T.blockLimit))continue;
                        if (Y.type == 3) {
                            var X = W.getName();
                            if (!(typeof Y.element == 'string' ? X == Y.element : X in Y.element))continue;
                        }
                        if (Y.checkElementRemovable(W, true))return true;
                    }
            }
            return false;
        }, checkApplicable: function (T) {
            switch (this.type) {
                case 2:
                case 1:
                    break;
                case 3:
                    return T.lastElement.getAscendant(this.element, true);
            }
            return true;
        }, checkElementMatch: function (T, U) {
            var aa = this;
            var V = aa._.definition;
            if (!T || !V.ignoreReadonly && T.isReadOnly())return false;
            var W, X = T.getName();
            if (typeof aa.element == 'string' ? X == aa.element : X in aa.element) {
                if (!U && !T.hasAttributes())return true;
                W = M(V);
                if (W._length) {
                    for (var Y in W) {
                        if (Y == '_length')continue;
                        var Z = T.getAttribute(Y) || '';
                        if (Y == 'style' ? R(W[Y], P(Z, false)) : W[Y] == Z) {
                            if (!U)return true;
                        } else if (U)return false;
                    }
                    if (U)return true;
                } else return true;
            }
            return false;
        }, checkElementRemovable: function (T, U) {
            if (this.checkElementMatch(T, U))return true;
            var V = N(this)[T.getName()];
            if (V) {
                var W, X;
                if (!(W = V.attributes))return true;
                for (var Y = 0; Y < W.length; Y++) {
                    X = W[Y][0];
                    var Z = T.getAttribute(X);
                    if (Z) {
                        var aa = W[Y][1];
                        if (aa === null || typeof aa == 'string' && Z == aa || aa.test(Z))return true;
                    }
                }
            }
            return false;
        }, buildPreview: function (T) {
            var U = this._.definition, V = [], W = U.element;
            if (W == 'bdo')W = 'span';
            V = ['<', W];
            var X = U.attributes;
            if (X)for (var Y in X)V.push(' ', Y, '="', X[Y], '"');
            var Z = a.style.getStyleText(U);
            if (Z)V.push(' style="', Z, '"');
            V.push('>', T || U.name, '</', W, '>');
            return V.join('');
        }};
        a.style.getStyleText = function (T) {
            var U = T._ST;
            if (U)return U;
            U = T.styles;
            var V = T.attributes && T.attributes.style || '', W = '';
            if (V.length)V = V.replace(o, ';');
            for (var X in U) {
                var Y = U[X], Z = (X + ':' + Y).replace(o, ';');
                if (Y == 'inherit')W += Z; else V += Z;
            }
            if (V.length)V = P(V);
            V += W;
            return T._ST = V;
        };
        function s(T) {
            var U, V;
            while (T = T.getParent()) {
                if (T.getName() == 'body')break;
                if (T.getAttribute('data-nostyle'))U = T; else if (!V) {
                    var W = T.getAttribute('contentEditable');
                    if (W == 'false')U = T; else if (W == 'true')V = 1;
                }
            }
            return U;
        };
        function t(T) {
            var ay = this;
            var U = T.document;
            if (T.collapsed) {
                var V = J(ay, U);
                T.insertNode(V);
                T.moveToPosition(V, 2);
                return;
            }
            var W = ay.element, X = ay._.definition, Y, Z = X.ignoreReadonly, aa = Z || X.includeReadonly;
            if (aa == undefined)aa = U.getCustomData('cke_includeReadonly');
            var ab = f[W] || (Y = true, f.span);
            T.enlarge(1, 1);
            T.trim();
            var ac = T.createBookmark(), ad = ac.startNode, ae = ac.endNode, af = ad, ag;
            if (!Z) {
                var ah = s(ad), ai = s(ae);
                if (ah)af = ah.getNextSourceNode(true);
                if (ai)ae = ai;
            }
            if (af.getPosition(ae) == 2)af = 0;
            while (af) {
                var aj = false;
                if (af.equals(ae)) {
                    af = null;
                    aj = true;
                } else {
                    var ak = af.type, al = ak == 1 ? af.getName() : null, am = al && af.getAttribute('contentEditable') == 'false', an = al && af.getAttribute('data-nostyle');
                    if (al && af.data('cke-bookmark')) {
                        af = af.getNextSourceNode(true);
                        continue;
                    }
                    if (!al || ab[al] && !an && (!am || aa) && (af.getPosition(ae) | 4 | 0 | 8) == 4 + 0 + 8 && (!X.childRule || X.childRule(af))) {
                        var ao = af.getParent();
                        if (ao && ((ao.getDtd() || f.span)[W] || Y) && (!X.parentRule || X.parentRule(ao))) {
                            if (!ag && (!al || !f.$removeEmpty[al] || (af.getPosition(ae) | 4 | 0 | 8) == 4 + 0 + 8)) {
                                ag = new d.range(U);
                                ag.setStartBefore(af);
                            }
                            if (ak == 3 || am || ak == 1 && !af.getChildCount()) {
                                var ap = af, aq;
                                while ((aj = !ap.getNext(q)) && (aq = ap.getParent(), ab[aq.getName()]) && (aq.getPosition(ad) | 2 | 0 | 8) == 2 + 0 + 8 && (!X.childRule || X.childRule(aq)))ap = aq;
                                ag.setEndAfter(ap);
                            }
                        } else aj = true;
                    } else aj = true;
                    af = af.getNextSourceNode(an || am);
                }
                if (aj && ag && !ag.collapsed) {
                    var ar = J(ay, U), as = ar.hasAttributes(), at = ag.getCommonAncestor(), au = {styles: {}, attrs: {}, blockedStyles: {}, blockedAttrs: {}}, av, aw, ax;
                    while (ar && at) {
                        if (at.getName() == W) {
                            for (av in X.attributes) {
                                if (au.blockedAttrs[av] || !(ax = at.getAttribute(aw)))continue;
                                if (ar.getAttribute(av) == ax)au.attrs[av] = 1; else au.blockedAttrs[av] = 1;
                            }
                            for (aw in X.styles) {
                                if (au.blockedStyles[aw] || !(ax = at.getStyle(aw)))continue;
                                if (ar.getStyle(aw) == ax)au.styles[aw] = 1; else au.blockedStyles[aw] = 1;
                            }
                        }
                        at = at.getParent();
                    }
                    for (av in au.attrs)ar.removeAttribute(av);
                    for (aw in au.styles)ar.removeStyle(aw);
                    if (as && !ar.hasAttributes())ar = null;
                    if (ar) {
                        ag.extractContents().appendTo(ar);
                        G(ay, ar);
                        ag.insertNode(ar);
                        ar.mergeSiblings();
                        if (!c)ar.$.normalize();
                    } else {
                        ar = new h('span');
                        ag.extractContents().appendTo(ar);
                        ag.insertNode(ar);
                        G(ay, ar);
                        ar.remove(true);
                    }
                    ag = null;
                }
            }
            T.moveToBookmark(ac);
            T.shrink(2);
        };
        function u(T) {
            T.enlarge(1, 1);
            var U = T.createBookmark(), V = U.startNode;
            if (T.collapsed) {
                var W = new d.elementPath(V.getParent()), X;
                for (var Y = 0, Z; Y < W.elements.length && (Z = W.elements[Y]); Y++) {
                    if (Z == W.block || Z == W.blockLimit)break;
                    if (this.checkElementRemovable(Z)) {
                        var aa;
                        if (T.collapsed && (T.checkBoundaryOfElement(Z, 2) || (aa = T.checkBoundaryOfElement(Z, 1)))) {
                            X = Z;
                            X.match = aa ? 'start' : 'end';
                        } else {
                            Z.mergeSiblings();
                            if (Z.getName() == this.element)F(this, Z); else H(Z, N(this)[Z.getName()]);
                        }
                    }
                }
                if (X) {
                    var ab = V;
                    for (Y = 0; true; Y++) {
                        var ac = W.elements[Y];
                        if (ac.equals(X))break; else if (ac.match)continue; else ac = ac.clone();
                        ac.append(ab);
                        ab = ac;
                    }
                    ab[X.match == 'start' ? 'insertBefore' : 'insertAfter'](X);
                }
            } else {
                var ad = U.endNode, ae = this;

                function af() {
                    var ai = new d.elementPath(V.getParent()), aj = new d.elementPath(ad.getParent()), ak = null, al = null;
                    for (var am = 0; am < ai.elements.length; am++) {
                        var an = ai.elements[am];
                        if (an == ai.block || an == ai.blockLimit)break;
                        if (ae.checkElementRemovable(an))ak = an;
                    }
                    for (am = 0; am < aj.elements.length; am++) {
                        an = aj.elements[am];
                        if (an == aj.block || an == aj.blockLimit)break;
                        if (ae.checkElementRemovable(an))al = an;
                    }
                    if (al)ad.breakParent(al);
                    if (ak)V.breakParent(ak);
                };
                af();
                var ag = V;
                while (!ag.equals(ad)) {
                    var ah = ag.getNextSourceNode();
                    if (ag.type == 1 && this.checkElementRemovable(ag)) {
                        if (ag.getName() == this.element)F(this, ag); else H(ag, N(this)[ag.getName()]);
                        if (ah.type == 1 && ah.contains(V)) {
                            af();
                            ah = V.getNext();
                        }
                    }
                    ag = ah;
                }
            }
            T.moveToBookmark(U);
        };
        function v(T) {
            var U = T.getCommonAncestor(true, true), V = U.getAscendant(this.element, true);
            V && !V.isReadOnly() && K(V, this);
        };
        function w(T) {
            var U = T.getCommonAncestor(true, true), V = U.getAscendant(this.element, true);
            if (!V)return;
            var W = this, X = W._.definition, Y = X.attributes;
            if (Y)for (var Z in Y)V.removeAttribute(Z, Y[Z]);
            if (X.styles)for (var aa in X.styles) {
                if (!X.styles.hasOwnProperty(aa))continue;
                V.removeStyle(aa);
            }
        };
        function x(T) {
            var U = T.createBookmark(true), V = T.createIterator();
            V.enforceRealBlocks = true;
            if (this._.enterMode)V.enlargeBr = this._.enterMode != 2;
            var W, X = T.document, Y;
            while (W = V.getNextParagraph()) {
                if (!W.isReadOnly()) {
                    var Z = J(this, X, W);
                    z(W, Z);
                }
            }
            T.moveToBookmark(U);
        };
        function y(T) {
            var Y = this;
            var U = T.createBookmark(1), V = T.createIterator();
            V.enforceRealBlocks = true;
            V.enlargeBr = Y._.enterMode != 2;
            var W;
            while (W = V.getNextParagraph()) {
                if (Y.checkElementRemovable(W))if (W.is('pre')) {
                    var X = Y._.enterMode == 2 ? null : T.document.createElement(Y._.enterMode == 1 ? 'p' : 'div');
                    X && W.copyAttributes(X);
                    z(W, X);
                } else F(Y, W, 1);
            }
            T.moveToBookmark(U);
        };
        function z(T, U) {
            var V = !U;
            if (V) {
                U = T.getDocument().createElement('div');
                T.copyAttributes(U);
            }
            var W = U && U.is('pre'), X = T.is('pre'), Y = W && !X, Z = !W && X;
            if (Y)U = E(T, U); else if (Z)U = D(V ? [T.getHtml()] : B(T), U); else T.moveChildren(U);
            U.replace(T);
            if (W)A(U); else if (V)I(U);
        };
        function A(T) {
            var U;
            if (!((U = T.getPrevious(r)) && U.is && U.is('pre')))return;
            var V = C(U.getHtml(), /\n$/, '') + '\n\n' + C(T.getHtml(), /^\n/, '');
            if (c)T.$.outerHTML = '<pre>' + V + '</pre>'; else T.setHtml(V);
            U.remove();
        };
        function B(T) {
            var U = /(\S\s*)\n(?:\s|(<span[^>]+data-cke-bookmark.*?\/span>))*\n(?!$)/gi, V = T.getName(), W = C(T.getOuterHtml(), U, function (Y, Z, aa) {
                return Z + '</pre>' + aa + '<pre>';
            }), X = [];
            W.replace(/<pre\b.*?>([\s\S]*?)<\/pre>/gi, function (Y, Z) {
                X.push(Z);
            });
            return X;
        };
        function C(T, U, V) {
            var W = '', X = '';
            T = T.replace(/(^<span[^>]+data-cke-bookmark.*?\/span>)|(<span[^>]+data-cke-bookmark.*?\/span>$)/gi, function (Y, Z, aa) {
                Z && (W = Z);
                aa && (X = aa);
                return '';
            });
            return W + T.replace(U, V) + X;
        };
        function D(T, U) {
            var V;
            if (T.length > 1)V = new d.documentFragment(U.getDocument());
            for (var W = 0; W < T.length; W++) {
                var X = T[W];
                X = X.replace(/(\r\n|\r)/g, '\n');
                X = C(X, /^[ \t]*\n/, '');
                X = C(X, /\n$/, '');
                X = C(X, /^[ \t]+|[ \t]+$/g, function (Z, aa, ab) {
                    if (Z.length == 1)return '&nbsp;'; else if (!aa)return e.repeat('&nbsp;', Z.length - 1) + ' '; else return ' ' + e.repeat('&nbsp;', Z.length - 1);
                });
                X = X.replace(/\n/g, '<br>');
                X = X.replace(/[ \t]{2,}/g, function (Z) {
                    return e.repeat('&nbsp;', Z.length - 1) + ' ';
                });
                if (V) {
                    var Y = U.clone();
                    Y.setHtml(X);
                    V.append(Y);
                } else U.setHtml(X);
            }
            return V || U;
        };
        function E(T, U) {
            var V = T.getBogus();
            V && V.remove();
            var W = T.getHtml();
            W = C(W, /(?:^[ \t\n\r]+)|(?:[ \t\n\r]+$)/g, '');
            W = W.replace(/[ \t\r\n]*(<br[^>]*>)[ \t\r\n]*/gi, '$1');
            W = W.replace(/([ \t\n\r]+|&nbsp;)/g, ' ');
            W = W.replace(/<br\b[^>]*>/gi, '\n');
            if (c) {
                var X = T.getDocument().createElement('div');
                X.append(U);
                U.$.outerHTML = '<pre>' + W + '</pre>';
                U.copyAttributes(X.getFirst());
                U = X.getFirst().remove();
            } else U.setHtml(W);
            return U;
        };
        function F(T, U) {
            var V = T._.definition, W = V.attributes, X = V.styles, Y = N(T)[U.getName()], Z = e.isEmpty(W) && e.isEmpty(X);
            for (var aa in W) {
                if ((aa == 'class' || T._.definition.fullMatch) && U.getAttribute(aa) != O(aa, W[aa]))continue;
                Z = U.hasAttribute(aa);
                U.removeAttribute(aa);
            }
            for (var ab in X) {
                if (T._.definition.fullMatch && U.getStyle(ab) != O(ab, X[ab], true))continue;
                Z = Z || !!U.getStyle(ab);
                U.removeStyle(ab);
            }
            H(U, Y, m[U.getName()]);
            if (Z)!f.$block[U.getName()] || T._.enterMode == 2 && !U.hasAttributes() ? I(U) : U.renameNode(T._.enterMode == 1 ? 'p' : 'div');
        };
        function G(T, U) {
            var V = T._.definition, W = V.attributes, X = V.styles, Y = N(T), Z = U.getElementsByTag(T.element);
            for (var aa = Z.count(); --aa >= 0;)F(T, Z.getItem(aa));
            for (var ab in Y) {
                if (ab != T.element) {
                    Z = U.getElementsByTag(ab);
                    for (aa = Z.count() - 1; aa >= 0; aa--) {
                        var ac = Z.getItem(aa);
                        H(ac, Y[ab]);
                    }
                }
            }
        };
        function H(T, U, V) {
            var W = U && U.attributes;
            if (W)for (var X = 0; X < W.length; X++) {
                var Y = W[X][0], Z;
                if (Z = T.getAttribute(Y)) {
                    var aa = W[X][1];
                    if (aa === null || aa.test && aa.test(Z) || typeof aa == 'string' && Z == aa)T.removeAttribute(Y);
                }
            }
            if (!V)I(T);
        };
        function I(T) {
            if (!T.hasAttributes())if (f.$block[T.getName()]) {
                var U = T.getPrevious(r), V = T.getNext(r);
                if (U && (U.type == 3 || !U.isBlockBoundary({br: 1})))T.append('br', 1);
                if (V && (V.type == 3 || !V.isBlockBoundary({br: 1})))T.append('br');
                T.remove(true);
            } else {
                var W = T.getFirst(), X = T.getLast();
                T.remove(true);
                if (W) {
                    W.type == 1 && W.mergeSiblings();
                    if (X && !W.equals(X) && X.type == 1)X.mergeSiblings();
                }
            }
        };
        function J(T, U, V) {
            var W, X = T._.definition, Y = T.element;
            if (Y == '*')Y = 'span';
            W = new h(Y, U);
            if (V)V.copyAttributes(W);
            W = K(W, T);
            if (U.getCustomData('doc_processing_style') && W.hasAttribute('id'))W.removeAttribute('id'); else U.setCustomData('doc_processing_style', 1);
            return W;
        };
        function K(T, U) {
            var V = U._.definition, W = V.attributes, X = a.style.getStyleText(V);
            if (W)for (var Y in W)T.setAttribute(Y, W[Y]);
            if (X)T.setAttribute('style', X);
            return T;
        };
        function L(T, U) {
            for (var V in T)T[V] = T[V].replace(p, function (W, X) {
                return U[X];
            });
        };
        function M(T) {
            var U = T._AC;
            if (U)return U;
            U = {};
            var V = 0, W = T.attributes;
            if (W)for (var X in W) {
                V++;
                U[X] = W[X];
            }
            var Y = a.style.getStyleText(T);
            if (Y) {
                if (!U.style)V++;
                U.style = Y;
            }
            U._length = V;
            return T._AC = U;
        };
        function N(T) {
            if (T._.overrides)return T._.overrides;
            var U = T._.overrides = {}, V = T._.definition.overrides;
            if (V) {
                if (!e.isArray(V))V = [V];
                for (var W = 0; W < V.length; W++) {
                    var X = V[W], Y, Z, aa;
                    if (typeof X == 'string')Y = X.toLowerCase(); else {
                        Y = X.element ? X.element.toLowerCase() : T.element;
                        aa = X.attributes;
                    }
                    Z = U[Y] || (U[Y] = {});
                    if (aa) {
                        var ab = Z.attributes = Z.attributes || [];
                        for (var ac in aa)ab.push([ac.toLowerCase(), aa[ac]]);
                    }
                }
            }
            return U;
        };
        function O(T, U, V) {
            var W = new h('span');
            W[V ? 'setStyle' : 'setAttribute'](T, U);
            return W[V ? 'getStyle' : 'getAttribute'](T);
        };
        function P(T, U) {
            var V;
            if (U !== false) {
                var W = new h('span');
                W.setAttribute('style', T);
                V = W.getAttribute('style') || '';
            } else V = T;
            V = V.replace(/(font-family:)(.*?)(?=;|$)/, function (X, Y, Z) {
                var aa = Z.split(',');
                for (var ab = 0; ab < aa.length; ab++)aa[ab] = e.trim(aa[ab].replace(/["']/g, ''));
                return Y + aa.join(',');
            });
            return V.replace(/\s*([;:])\s*/, '$1').replace(/([^\s;])$/, '$1;').replace(/,\s+/g, ',').replace(/\"/g, '').toLowerCase();
        };
        function Q(T) {
            var U = {};
            T.replace(/&quot;/g, '"').replace(/\s*([^ :;]+)\s*:\s*([^;]+)\s*(?=;|$)/g, function (V, W, X) {
                U[W] = X;
            });
            return U;
        };
        function R(T, U) {
            typeof T == 'string' && (T = Q(T));
            typeof U == 'string' && (U = Q(U));
            for (var V in T) {
                if (!(V in U && (U[V] == T[V] || T[V] == 'inherit' || U[V] == 'inherit')))return false;
            }
            return true;
        };
        function S(T, U) {
            var V = T.getSelection(), W = V.createBookmarks(1), X = V.getRanges(), Y = U ? this.removeFromRange : this.applyToRange, Z, aa = X.createIterator();
            while (Z = aa.getNextRange())Y.call(this, Z);
            if (W.length == 1 && W[0].collapsed) {
                V.selectRanges(X);
                T.getById(W[0].startNode).remove();
            } else V.selectBookmarks(W);
            T.removeCustomData('doc_processing_style');
        };
    })();
    a.styleCommand = function (m) {
        this.style = m;
    };
    a.styleCommand.prototype.exec = function (m) {
        var o = this;
        m.focus();
        var n = m.document;
        if (n)if (o.state == 2)o.style.apply(n); else if (o.state == 1)o.style.remove(n);
        return!!n;
    };
    a.stylesSet = new a.resourceManager('', 'stylesSet');
    a.addStylesSet = e.bind(a.stylesSet.add, a.stylesSet);
    a.loadStylesSet = function (m, n, o) {
        a.stylesSet.addExternal(m, n, '');
        a.stylesSet.load(m, o);
    };
    a.editor.prototype.getStylesSet = function (m) {
        if (!this._.stylesDefinitions) {
            var n = this, o = n.config.stylesCombo_stylesSet || n.config.stylesSet || 'default';
            if (o instanceof Array) {
                n._.stylesDefinitions = o;
                m(o);
                return;
            }
            var p = o.split(':'), q = p[0], r = p[1], s = j.registered.styles.path;
            a.stylesSet.addExternal(q, r ? p.slice(1).join(':') : s + 'styles/' + q + '.js', '');
            a.stylesSet.load(q, function (t) {
                n._.stylesDefinitions = t[q];
                m(n._.stylesDefinitions);
            });
        } else m(this._.stylesDefinitions);
    };
    j.add('domiterator');
    (function () {
        function m(s) {
            var t = this;
            if (arguments.length < 1)return;
            t.range = s;
            t.forceBrBreak = 0;
            t.enlargeBr = 1;
            t.enforceRealBlocks = 0;
            t._ || (t._ = {});
        };
        var n = /^[\r\n\t ]+$/, o = d.walker.bookmark(false, true), p = d.walker.whitespaces(true), q = function (s) {
            return o(s) && p(s);
        };

        function r(s, t, u) {
            var v = s.getNextSourceNode(t, null, u);
            while (!o(v))v = v.getNextSourceNode(t, null, u);
            return v;
        };
        m.prototype = {getNextParagraph: function (s) {
            var S = this;
            var t, u, v, w, x, y;
            if (!S._.started) {
                u = S.range.clone();
                u.shrink(1, true);
                w = u.endContainer.hasAscendant('pre', true) || u.startContainer.hasAscendant('pre', true);
                u.enlarge(S.forceBrBreak && !w || !S.enlargeBr ? 3 : 2);
                if (!u.collapsed) {
                    var z = new d.walker(u.clone()), A = d.walker.bookmark(true, true);
                    z.evaluator = A;
                    S._.nextNode = z.next();
                    z = new d.walker(u.clone());
                    z.evaluator = A;
                    var B = z.previous();
                    S._.lastNode = B.getNextSourceNode(true);
                    if (S._.lastNode && S._.lastNode.type == 3 && !e.trim(S._.lastNode.getText()) && S._.lastNode.getParent().isBlockBoundary()) {
                        var C = new d.range(u.document);
                        C.moveToPosition(S._.lastNode, 4);
                        if (C.checkEndOfBlock()) {
                            var D = new d.elementPath(C.endContainer), E = D.block || D.blockLimit;
                            S._.lastNode = E.getNextSourceNode(true);
                        }
                    }
                    if (!S._.lastNode) {
                        S._.lastNode = S._.docEndMarker = u.document.createText('');
                        S._.lastNode.insertAfter(B);
                    }
                    u = null;
                }
                S._.started = 1;
            }
            var F = S._.nextNode;
            B = S._.lastNode;
            S._.nextNode = null;
            while (F) {
                var G = 0, H = F.hasAscendant('pre'), I = F.type != 1, J = 0;
                if (!I) {
                    var K = F.getName();
                    if (F.isBlockBoundary(S.forceBrBreak && !H && {br: 1})) {
                        if (K == 'br')I = 1; else if (!u && !F.getChildCount() && K != 'hr') {
                            t = F;
                            v = F.equals(B);
                            break;
                        }
                        if (u) {
                            u.setEndAt(F, 3);
                            if (K != 'br')S._.nextNode = F;
                        }
                        G = 1;
                    } else {
                        if (F.getFirst()) {
                            if (!u) {
                                u = new d.range(S.range.document);
                                u.setStartAt(F, 3);
                            }
                            F = F.getFirst();
                            continue;
                        }
                        I = 1;
                    }
                } else if (F.type == 3)if (n.test(F.getText()))I = 0;
                if (I && !u) {
                    u = new d.range(S.range.document);
                    u.setStartAt(F, 3);
                }
                v = (!G || I) && F.equals(B);
                if (u && !G)while (!F.getNext(q) && !v) {
                    var L = F.getParent();
                    if (L.isBlockBoundary(S.forceBrBreak && !H && {br: 1})) {
                        G = 1;
                        I = 0;
                        v = v || L.equals(B);
                        u.setEndAt(L, 2);
                        break;
                    }
                    F = L;
                    I = 1;
                    v = F.equals(B);
                    J = 1;
                }
                if (I)u.setEndAt(F, 4);
                F = r(F, J, B);
                v = !F;
                if (v || G && u)break;
            }
            if (!t) {
                if (!u) {
                    S._.docEndMarker && S._.docEndMarker.remove();
                    S._.nextNode = null;
                    return null;
                }
                var M = new d.elementPath(u.startContainer), N = M.blockLimit, O = {div: 1, th: 1, td: 1};
                t = M.block;
                if (!t && !S.enforceRealBlocks && O[N.getName()] && u.checkStartOfBlock() && u.checkEndOfBlock())t = N; else if (!t || S.enforceRealBlocks && t.getName() == 'li') {
                    t = S.range.document.createElement(s || 'p');
                    u.extractContents().appendTo(t);
                    t.trim();
                    u.insertNode(t);
                    x = y = true;
                } else if (t.getName() != 'li') {
                    if (!u.checkStartOfBlock() || !u.checkEndOfBlock()) {
                        t = t.clone(false);
                        u.extractContents().appendTo(t);
                        t.trim();
                        var P = u.splitBlock();
                        x = !P.wasStartOfBlock;
                        y = !P.wasEndOfBlock;
                        u.insertNode(t);
                    }
                } else if (!v)S._.nextNode = t.equals(B) ? null : r(u.getBoundaryNodes().endNode, 1, B);
            }
            if (x) {
                var Q = t.getPrevious();
                if (Q && Q.type == 1)if (Q.getName() == 'br')Q.remove(); else if (Q.getLast() && Q.getLast().$.nodeName.toLowerCase() == 'br')Q.getLast().remove();
            }
            if (y) {
                var R = t.getLast();
                if (R && R.type == 1 && R.getName() == 'br')if (c || R.getPrevious(o) || R.getNext(o))R.remove();
            }
            if (!S._.nextNode)S._.nextNode = v || t.equals(B) || !B ? null : r(t, 1, B);
            return t;
        }};
        d.range.prototype.createIterator = function () {
            return new m(this);
        };
    })();
    j.add('panelbutton', {requires: ['button'], onLoad: function () {
        function m(n) {
            var p = this;
            var o = p._;
            if (o.state == 0)return;
            p.createPanel(n);
            if (o.on) {
                o.panel.hide();
                return;
            }
            o.panel.showBlock(p._.id, p.document.getById(p._.id), 4);
        };
        k.panelButton = e.createClass({base: k.button, $: function (n) {
            var p = this;
            var o = n.panel;
            delete n.panel;
            p.base(n);
            p.document = o && o.parent && o.parent.getDocument() || a.document;
            o.block = {attributes: o.attributes};
            p.hasArrow = true;
            p.click = m;
            p._ = {panelDefinition: o};
        }, statics: {handler: {create: function (n) {
            return new k.panelButton(n);
        }}}, proto: {createPanel: function (n) {
            var o = this._;
            if (o.panel)return;
            var p = this._.panelDefinition || {}, q = this._.panelDefinition.block, r = p.parent || a.document.getBody(), s = this._.panel = new k.floatPanel(n, r, p), t = s.addBlock(o.id, q), u = this;
            s.onShow = function () {
                if (u.className)this.element.getFirst().addClass(u.className + '_panel');
                u.setState(1);
                o.on = 1;
                if (u.onOpen)u.onOpen();
            };
            s.onHide = function (v) {
                if (u.className)this.element.getFirst().removeClass(u.className + '_panel');
                u.setState(u.modes && u.modes[n.mode] ? 2 : 0);
                o.on = 0;
                if (!v && u.onClose)u.onClose();
            };
            s.onEscape = function () {
                s.hide();
                u.document.getById(o.id).focus();
            };
            if (this.onBlock)this.onBlock(s, t);
            t.onHide = function () {
                o.on = 0;
                u.setState(2);
            };
        }}});
    }, beforeInit: function (m) {
        m.ui.addHandler('panelbutton', k.panelButton.handler);
    }});
    a.UI_PANELBUTTON = 'panelbutton';
    j.add('floatpanel', {requires: ['panel']});
    (function () {
        var m = {}, n = false;

        function o(p, q, r, s, t) {
            var u = e.genKey(q.getUniqueId(), r.getUniqueId(), p.skinName, p.lang.dir, p.uiColor || '', s.css || '', t || ''), v = m[u];
            if (!v) {
                v = m[u] = new k.panel(q, s);
                v.element = r.append(h.createFromHtml(v.renderHtml(p), q));
                v.element.setStyles({display: 'none', position: 'absolute'});
            }
            return v;
        };
        k.floatPanel = e.createClass({$: function (p, q, r, s) {
            r.forceIFrame = 1;
            var t = q.getDocument(), u = o(p, t, q, r, s || 0), v = u.element, w = v.getFirst().getFirst();
            v.disableContextMenu();
            this.element = v;
            this._ = {editor: p, panel: u, parentElement: q, definition: r, document: t, iframe: w, children: [], dir: p.lang.dir};
            p.on('mode', function () {
                this.hide();
            }, this);
        }, proto: {addBlock: function (p, q) {
            return this._.panel.addBlock(p, q);
        }, addListBlock: function (p, q) {
            return this._.panel.addListBlock(p, q);
        }, getBlock: function (p) {
            return this._.panel.getBlock(p);
        }, showBlock: function (p, q, r, s, t) {
            var u = this._.panel, v = u.showBlock(p);
            this.allowBlur(false);
            n = 1;
            this._.returnFocus = this._.editor.focusManager.hasFocus ? this._.editor : new h(a.document.$.activeElement);
            var w = this.element, x = this._.iframe, y = this._.definition, z = q.getDocumentPosition(w.getDocument()), A = this._.dir == 'rtl', B = z.x + (s || 0), C = z.y + (t || 0);
            if (A && (r == 1 || r == 4))B += q.$.offsetWidth; else if (!A && (r == 2 || r == 3))B += q.$.offsetWidth - 1;
            if (r == 3 || r == 4)C += q.$.offsetHeight - 1;
            this._.panel._.offsetParentId = q.getId();
            w.setStyles({top: C + 'px', left: 0, display: ''});
            w.setOpacity(0);
            w.getFirst().removeStyle('width');
            if (!this._.blurSet) {
                var D = c ? x : new d.window(x.$.contentWindow);
                a.event.useCapture = true;
                D.on('blur', function (E) {
                    var G = this;
                    if (!G.allowBlur())return;
                    var F = E.data.getTarget();
                    if (F.getName && F.getName() != 'iframe')return;
                    if (G.visible && !G._.activeChild && !n) {
                        delete G._.returnFocus;
                        G.hide();
                    }
                }, this);
                D.on('focus', function () {
                    this._.focused = true;
                    this.hideChild();
                    this.allowBlur(true);
                }, this);
                a.event.useCapture = false;
                this._.blurSet = 1;
            }
            u.onEscape = e.bind(function (E) {
                if (this.onEscape && this.onEscape(E) === false)return false;
            }, this);
            e.setTimeout(function () {
                var E = e.bind(function () {
                    var F = w.getFirst();
                    if (v.autoSize) {
                        var G = v.element.$;
                        if (b.gecko || b.opera)G = G.parentNode;
                        if (c)G = G.document.body;
                        var H = G.scrollWidth;
                        if (c && b.quirks && H > 0)H += (F.$.offsetWidth || 0) - (F.$.clientWidth || 0) + 3;
                        H += 4;
                        F.setStyle('width', H + 'px');
                        v.element.addClass('cke_frameLoaded');
                        var I = v.element.$.scrollHeight;
                        if (c && b.quirks && I > 0)I += (F.$.offsetHeight || 0) - (F.$.clientHeight || 0) + 3;
                        F.setStyle('height', I + 'px');
                        u._.currentBlock.element.setStyle('display', 'none').removeStyle('display');
                    } else F.removeStyle('height');
                    if (A)B -= w.$.offsetWidth;
                    w.setStyle('left', B + 'px');
                    var J = u.element, K = J.getWindow(), L = w.$.getBoundingClientRect(), M = K.getViewPaneSize(), N = L.width || L.right - L.left, O = L.height || L.bottom - L.top, P = A ? L.right : M.width - L.left, Q = A ? M.width - L.right : L.left;
                    if (A) {
                        if (P < N)if (Q > N)B += N; else if (M.width > N)B -= L.left; else B = B - L.right + M.width;
                    } else if (P < N)if (Q > N)B -= N; else if (M.width > N)B = B - L.right + M.width; else B -= L.left;
                    var R = M.height - L.top, S = L.top;
                    if (R < O)if (S > O)C -= O; else if (M.height > O)C = C - L.bottom + M.height; else C -= L.top;
                    if (c) {
                        var T = new h(w.$.offsetParent), U = T;
                        if (U.getName() == 'html')U = U.getDocument().getBody();
                        if (U.getComputedStyle('direction') == 'rtl')if (b.ie8Compat)B -= w.getDocument().getDocumentElement().$.scrollLeft * 2; else B -= T.$.scrollWidth - T.$.clientWidth;
                    }
                    var V = w.getFirst(), W;
                    if (W = V.getCustomData('activePanel'))W.onHide && W.onHide.call(this, 1);
                    V.setCustomData('activePanel', this);
                    w.setStyles({top: C + 'px', left: B + 'px'});
                    w.setOpacity(1);
                }, this);
                u.isLoaded ? E() : u.onLoad = E;
                e.setTimeout(function () {
                    x.$.contentWindow.focus();
                    this.allowBlur(true);
                }, 0, this);
            }, b.air ? 200 : 0, this);
            this.visible = 1;
            if (this.onShow)this.onShow.call(this);
            n = 0;
        }, hide: function (p) {
            var r = this;
            if (r.visible && (!r.onHide || r.onHide.call(r) !== true)) {
                r.hideChild();
                b.gecko && r._.iframe.getFrameDocument().$.activeElement.blur();
                r.element.setStyle('display', 'none');
                r.visible = 0;
                r.element.getFirst().removeCustomData('activePanel');
                var q = p !== false && r._.returnFocus;
                if (q) {
                    if (b.webkit && q.type)q.getWindow().$.focus();
                    q.focus();
                }
            }
        }, allowBlur: function (p) {
            var q = this._.panel;
            if (p != undefined)q.allowBlur = p;
            return q.allowBlur;
        }, showAsChild: function (p, q, r, s, t, u) {
            if (this._.activeChild == p && p._.panel._.offsetParentId == r.getId())return;
            this.hideChild();
            p.onHide = e.bind(function () {
                e.setTimeout(function () {
                    if (!this._.focused)this.hide();
                }, 0, this);
            }, this);
            this._.activeChild = p;
            this._.focused = false;
            p.showBlock(q, r, s, t, u);
            if (b.ie7Compat || b.ie8 && b.ie6Compat)setTimeout(function () {
                p.element.getChild(0).$.style.cssText += '';
            }, 100);
        }, hideChild: function () {
            var p = this._.activeChild;
            if (p) {
                delete p.onHide;
                delete p._.returnFocus;
                delete this._.activeChild;
                p.hide();
            }
        }}});
        a.on('instanceDestroyed', function () {
            var p = e.isEmpty(a.instances);
            for (var q in m) {
                var r = m[q];
                if (p)r.destroy(); else r.element.hide();
            }
            p && (m = {});
        });
    })();
    j.add('menu', {beforeInit: function (m) {
        var n = m.config.menu_groups.split(','), o = m._.menuGroups = {}, p = m._.menuItems = {};
        for (var q = 0; q < n.length; q++)o[n[q]] = q + 1;
        m.addMenuGroup = function (r, s) {
            o[r] = s || 100;
        };
        m.addMenuItem = function (r, s) {
            if (o[s.group])p[r] = new a.menuItem(this, r, s);
        };
        m.addMenuItems = function (r) {
            for (var s in r)this.addMenuItem(s, r[s]);
        };
        m.getMenuItem = function (r) {
            return p[r];
        };
        m.removeMenuItem = function (r) {
            delete p[r];
        };
    }, requires: ['floatpanel']});
    (function () {
        a.menu = e.createClass({$: function (n, o) {
            var r = this;
            o = r._.definition = o || {};
            r.id = e.getNextId();
            r.editor = n;
            r.items = [];
            r._.listeners = [];
            r._.level = o.level || 1;
            var p = e.extend({}, o.panel, {css: n.skin.editor.css, level: r._.level - 1, block: {}}), q = p.block.attributes = p.attributes || {};
            !q.role && (q.role = 'menu');
            r._.panelDefinition = p;
        }, _: {onShow: function () {
            var v = this;
            var n = v.editor.getSelection();
            if (c)n && n.lock();
            var o = n && n.getStartElement(), p = v._.listeners, q = [];
            v.removeAll();
            for (var r = 0; r < p.length; r++) {
                var s = p[r](o, n);
                if (s)for (var t in s) {
                    var u = v.editor.getMenuItem(t);
                    if (u && (!u.command || v.editor.getCommand(u.command).state)) {
                        u.state = s[t];
                        v.add(u);
                    }
                }
            }
        }, onClick: function (n) {
            this.hide(false);
            if (n.onClick)n.onClick(); else if (n.command)this.editor.execCommand(n.command);
        }, onEscape: function (n) {
            var o = this.parent;
            if (o) {
                o._.panel.hideChild();
                var p = o._.panel._.panel._.currentBlock, q = p._.focusIndex;
                p._.markItem(q);
            } else if (n == 27)this.hide();
            return false;
        }, onHide: function () {
            var o = this;
            if (c && !o.parent) {
                var n = o.editor.getSelection();
                n && n.unlock(true);
            }
            o.onHide && o.onHide();
        }, showSubMenu: function (n) {
            var v = this;
            var o = v._.subMenu, p = v.items[n], q = p.getItems && p.getItems();
            if (!q) {
                v._.panel.hideChild();
                return;
            }
            var r = v._.panel.getBlock(v.id);
            r._.focusIndex = n;
            if (o)o.removeAll(); else {
                o = v._.subMenu = new a.menu(v.editor, e.extend({}, v._.definition, {level: v._.level + 1}, true));
                o.parent = v;
                o._.onClick = e.bind(v._.onClick, v);
            }
            for (var s in q) {
                var t = v.editor.getMenuItem(s);
                if (t) {
                    t.state = q[s];
                    o.add(t);
                }
            }
            var u = v._.panel.getBlock(v.id).element.getDocument().getById(v.id + String(n));
            o.show(u, 2);
        }}, proto: {add: function (n) {
            if (!n.order)n.order = this.items.length;
            this.items.push(n);
        }, removeAll: function () {
            this.items = [];
        }, show: function (n, o, p, q) {
            if (!this.parent) {
                this._.onShow();
                if (!this.items.length)return;
            }
            o = o || (this.editor.lang.dir == 'rtl' ? 2 : 1);
            var r = this.items, s = this.editor, t = this._.panel, u = this._.element;
            if (!t) {
                t = this._.panel = new k.floatPanel(this.editor, a.document.getBody(), this._.panelDefinition, this._.level);
                t.onEscape = e.bind(function (F) {
                    if (this._.onEscape(F) === false)return false;
                }, this);
                t.onHide = e.bind(function () {
                    this._.onHide && this._.onHide();
                }, this);
                var v = t.addBlock(this.id, this._.panelDefinition.block);
                v.autoSize = true;
                var w = v.keys;
                w[40] = 'next';
                w[9] = 'next';
                w[38] = 'prev';
                w[2228224 + 9] = 'prev';
                w[s.lang.dir == 'rtl' ? 37 : 39] = c ? 'mouseup' : 'click';
                w[32] = c ? 'mouseup' : 'click';
                c && (w[13] = 'mouseup');
                u = this._.element = v.element;
                u.addClass(s.skinClass);
                var x = u.getDocument();
                x.getBody().setStyle('overflow', 'hidden');
                x.getElementsByTag('html').getItem(0).setStyle('overflow', 'hidden');
                this._.itemOverFn = e.addFunction(function (F) {
                    var G = this;
                    clearTimeout(G._.showSubTimeout);
                    G._.showSubTimeout = e.setTimeout(G._.showSubMenu, s.config.menu_subMenuDelay || 400, G, [F]);
                }, this);
                this._.itemOutFn = e.addFunction(function (F) {
                    clearTimeout(this._.showSubTimeout);
                }, this);
                this._.itemClickFn = e.addFunction(function (F) {
                    var H = this;
                    var G = H.items[F];
                    if (G.state == 0) {
                        H.hide();
                        return;
                    }
                    if (G.getItems)H._.showSubMenu(F); else H._.onClick(G);
                }, this);
            }
            m(r);
            var y = s.container.getChild(1), z = y.hasClass('cke_mixed_dir_content') ? ' cke_mixed_dir_content' : '', A = ['<div class="cke_menu' + z + '" role="presentation">'], B = r.length, C = B && r[0].group;
            for (var D = 0; D < B; D++) {
                var E = r[D];
                if (C != E.group) {
                    A.push('<div class="cke_menuseparator" role="separator"></div>');
                    C = E.group;
                }
                E.render(this, D, A);
            }
            A.push('</div>');
            u.setHtml(A.join(''));
            k.fire('ready', this);
            if (this.parent)this.parent._.panel.showAsChild(t, this.id, n, o, p, q); else t.showBlock(this.id, n, o, p, q);
            s.fire('menuShow', [t]);
        }, addListener: function (n) {
            this._.listeners.push(n);
        }, hide: function (n) {
            var o = this;
            o._.onHide && o._.onHide();
            o._.panel && o._.panel.hide(n);
        }}});
        function m(n) {
            n.sort(function (o, p) {
                if (o.group < p.group)return-1; else if (o.group > p.group)return 1;
                return o.order < p.order ? -1 : o.order > p.order ? 1 : 0;
            });
        };
        a.menuItem = e.createClass({$: function (n, o, p) {
            var q = this;
            e.extend(q, p, {order: 0, className: 'cke_button_' + o});
            q.group = n._.menuGroups[q.group];
            q.editor = n;
            q.name = o;
        }, proto: {render: function (n, o, p) {
            var w = this;
            var q = n.id + String(o), r = typeof w.state == 'undefined' ? 2 : w.state, s = ' cke_' + (r == 1 ? 'on' : r == 0 ? 'disabled' : 'off'), t = w.label;
            if (w.className)s += ' ' + w.className;
            var u = w.getItems;
            p.push('<span class="cke_menuitem' + (w.icon && w.icon.indexOf('.png') == -1 ? ' cke_noalphafix' : '') + '">' + '<a id="', q, '" class="', s, '" href="javascript:void(\'', (w.label || '').replace("'", ''), '\')" title="', w.label, '" tabindex="-1"_cke_focus=1 hidefocus="true" role="menuitem"' + (u ? 'aria-haspopup="true"' : '') + (r == 0 ? 'aria-disabled="true"' : '') + (r == 1 ? 'aria-pressed="true"' : ''));
            if (b.opera || b.gecko && b.mac)p.push(' onkeypress="return false;"');
            if (b.gecko)p.push(' onblur="this.style.cssText = this.style.cssText;"');
            var v = (w.iconOffset || 0) * -16;
            p.push(' onmouseover="CKEDITOR.tools.callFunction(', n._.itemOverFn, ',', o, ');" onmouseout="CKEDITOR.tools.callFunction(', n._.itemOutFn, ',', o, ');" ' + (c ? 'onclick="return false;" onmouseup' : 'onclick') + '="CKEDITOR.tools.callFunction(', n._.itemClickFn, ',', o, '); return false;"><span class="cke_icon_wrapper"><span class="cke_icon"' + (w.icon ? ' style="background-image:url(' + a.getUrl(w.icon) + ');background-position:0 ' + v + 'px;"' : '') + '></span></span>' + '<span class="cke_label">');
            if (u)p.push('<span class="cke_menuarrow">', '<span>&#', w.editor.lang.dir == 'rtl' ? '9668' : '9658', ';</span>', '</span>');
            p.push(t, '</span></a></span>');
        }}});
    })();
    i.menu_groups = 'clipboard,form,tablecell,tablecellproperties,tablerow,tablecolumn,table,anchor,link,image,flash,checkbox,radio,textfield,hiddenfield,imagebutton,button,select,textarea,div';
    (function () {
        var m;
        j.add('editingblock', {init: function (n) {
            if (!n.config.editingBlock)return;
            n.on('themeSpace', function (o) {
                if (o.data.space == 'contents')o.data.html += '<br>';
            });
            n.on('themeLoaded', function () {
                n.fireOnce('editingBlockReady');
            });
            n.on('uiReady', function () {
                n.setMode(n.config.startupMode);
            });
            n.on('afterSetData', function () {
                if (!m) {
                    function o() {
                        m = true;
                        n.getMode().loadData(n.getData());
                        m = false;
                    };
                    if (n.mode)o(); else n.on('mode', function () {
                        if (n.mode) {
                            o();
                            n.removeListener('mode', arguments.callee);
                        }
                    });
                }
            });
            n.on('beforeGetData', function () {
                if (!m && n.mode) {
                    m = true;
                    n.setData(n.getMode().getData(), null, 1);
                    m = false;
                }
            });
            n.on('getSnapshot', function (o) {
                if (n.mode)o.data = n.getMode().getSnapshotData();
            });
            n.on('loadSnapshot', function (o) {
                if (n.mode)n.getMode().loadSnapshotData(o.data);
            });
            n.on('mode', function (o) {
                o.removeListener();
                b.webkit && n.container.on('focus', function () {
                    n.focus();
                });
                if (n.config.startupFocus)n.focus();
                setTimeout(function () {
                    n.fireOnce('instanceReady');
                    a.fire('instanceReady', null, n);
                }, 0);
            });
            n.on('destroy', function () {
                var o = this;
                if (o.mode)o._.modes[o.mode].unload(o.getThemeSpace('contents'));
            });
        }});
        a.editor.prototype.mode = '';
        a.editor.prototype.addMode = function (n, o) {
            o.name = n;
            (this._.modes || (this._.modes = {}))[n] = o;
        };
        a.editor.prototype.setMode = function (n) {
            this.fire('beforeSetMode', {newMode: n});
            var o, p = this.getThemeSpace('contents'), q = this.checkDirty();
            if (this.mode) {
                if (n == this.mode)return;
                this._.previousMode = this.mode;
                this.fire('beforeModeUnload');
                var r = this.getMode();
                o = r.getData();
                r.unload(p);
                this.mode = '';
            }
            p.setHtml('');
            var s = this.getMode(n);
            if (!s)throw '[CKEDITOR.editor.setMode] Unknown mode "' + n + '".';
            if (!q)this.on('mode', function () {
                this.resetDirty();
                this.removeListener('mode', arguments.callee);
            });
            s.load(p, typeof o != 'string' ? this.getData() : o);
        };
        a.editor.prototype.getMode = function (n) {
            return this._.modes && this._.modes[n || this.mode];
        };
        a.editor.prototype.focus = function () {
            this.forceNextSelectionCheck();
            var n = this.getMode();
            if (n)n.focus();
        };
    })();
    i.startupMode = 'wysiwyg';
    i.editingBlock = true;
    (function () {
        function m() {
            var G = this;
            try {
                var D = G.getSelection();
                if (!D || !D.document.getWindow().$)return;
                var E = D.getStartElement(), F = new d.elementPath(E);
                if (!F.compare(G._.selectionPreviousPath)) {
                    G._.selectionPreviousPath = F;
                    G.fire('selectionChange', {selection: D, path: F, element: E});
                }
            } catch (H) {
            }
        };
        var n, o;

        function p() {
            o = true;
            if (n)return;
            q.call(this);
            n = e.setTimeout(q, 200, this);
        };
        function q() {
            n = null;
            if (o) {
                e.setTimeout(m, 0, this);
                o = false;
            }
        };
        function r(D) {
            function E(I, J) {
                if (!I || I.type == 3)return false;
                var K = D.clone();
                return K['moveToElementEdit' + (J ? 'End' : 'Start')](I);
            };
            var F = D.startContainer, G = D.getPreviousNode(A, null, F), H = D.getNextNode(A, null, F);
            if (E(G) || E(H, 1))return true;
            if (!(G || H) && !(F.type == 1 && F.isBlockBoundary() && F.getBogus()))return true;
            return false;
        };
        var s = {modes: {wysiwyg: 1, source: 1}, readOnly: c || b.webkit, exec: function (D) {
            switch (D.mode) {
                case 'wysiwyg':
                    D.document.$.execCommand('SelectAll', false, null);
                    D.forceNextSelectionCheck();
                    D.selectionChange();
                    break;
                case 'source':
                    var E = D.textarea.$;
                    if (c)E.createTextRange().execCommand('SelectAll'); else {
                        E.selectionStart = 0;
                        E.selectionEnd = E.value.length;
                    }
                    E.focus();
            }
        }, canUndo: false};

        function t(D) {
            w(D);
            var E = D.createText('​');
            D.setCustomData('cke-fillingChar', E);
            return E;
        };
        function u(D) {
            return D && D.getCustomData('cke-fillingChar');
        };
        function v(D) {
            var E = D && u(D);
            if (E)if (E.getCustomData('ready'))w(D); else E.setCustomData('ready', 1);
        };
        function w(D) {
            var E = D && D.removeCustomData('cke-fillingChar');
            if (E) {
                var F, G = D.getSelection().getNative(), H = G && G.type != 'None' && G.getRangeAt(0);
                if (E.getLength() > 1 && H && H.intersectsNode(E.$)) {
                    F = [G.anchorOffset, G.focusOffset];
                    var I = G.anchorNode == E.$ && G.anchorOffset > 0, J = G.focusNode == E.$ && G.focusOffset > 0;
                    I && F[0]--;
                    J && F[1]--;
                    x(G) && F.unshift(F.pop());
                }
                E.setText(E.getText().replace(/\u200B/g, ''));
                if (F) {
                    var K = G.getRangeAt(0);
                    K.setStart(K.startContainer, F[0]);
                    K.setEnd(K.startContainer, F[1]);
                    G.removeAllRanges();
                    G.addRange(K);
                }
            }
        };
        function x(D) {
            if (!D.isCollapsed) {
                var E = D.getRangeAt(0);
                E.setStart(D.anchorNode, D.anchorOffset);
                E.setEnd(D.focusNode, D.focusOffset);
                return E.collapsed;
            }
        };
        j.add('selection', {init: function (D) {
            if (b.webkit) {
                D.on('selectionChange', function () {
                    v(D.document);
                });
                D.on('beforeSetMode', function () {
                    w(D.document);
                });
                var E, F;

                function G() {
                    var I = D.document, J = u(I);
                    if (J) {
                        var K = I.$.defaultView.getSelection();
                        if (K.type == 'Caret' && K.anchorNode == J.$)F = 1;
                        E = J.getText();
                        J.setText(E.replace(/\u200B/g, ''));
                    }
                };
                function H() {
                    var I = D.document, J = u(I);
                    if (J) {
                        J.setText(E);
                        if (F) {
                            I.$.defaultView.getSelection().setPosition(J.$, J.getLength());
                            F = 0;
                        }
                    }
                };
                D.on('beforeUndoImage', G);
                D.on('afterUndoImage', H);
                D.on('beforeGetData', G, null, null, 0);
                D.on('getData', H);
            }
            D.on('contentDom', function () {
                var I = D.document, J = a.document, K = I.getBody(), L = I.getDocumentElement();
                if (c) {
                    var M, N, O = 1;
                    K.on('focusin', function (V) {
                        if (V.data.$.srcElement.nodeName != 'BODY')return;
                        var W = I.getCustomData('cke_locked_selection');
                        if (W) {
                            W.unlock(1);
                            W.lock();
                        } else if (M && O) {
                            try {
                                M.select();
                            } catch (X) {
                            }
                            M = null;
                        }
                    });
                    K.on('focus', function () {
                        N = 1;
                        U();
                    });
                    K.on('beforedeactivate', function (V) {
                        if (V.data.$.toElement)return;
                        N = 0;
                        O = 1;
                    });
                    c && D.on('blur', function () {
                        try {
                            I.$.selection.empty();
                        } catch (V) {
                        }
                    });
                    L.on('mousedown', function () {
                        O = 0;
                    });
                    L.on('mouseup', function () {
                        O = 1;
                    });
                    var P;
                    K.on('mousedown', function (V) {
                        if (V.data.$.button == 2) {
                            var W = D.document.$.selection;
                            if (W.type == 'None')P = D.window.getScrollPosition();
                        }
                        T();
                    });
                    K.on('mouseup', function (V) {
                        if (V.data.$.button == 2 && P) {
                            D.document.$.documentElement.scrollLeft = P.x;
                            D.document.$.documentElement.scrollTop = P.y;
                        }
                        P = null;
                        N = 1;
                        setTimeout(function () {
                            U(true);
                        }, 0);
                    });
                    K.on('keydown', T);
                    K.on('keyup', function () {
                        N = 1;
                        U();
                    });
                    if (I.$.compatMode != 'BackCompat') {
                        if (b.ie7Compat || b.ie6Compat) {
                            function Q(V, W, X) {
                                try {
                                    V.moveToPoint(W, X);
                                } catch (Y) {
                                }
                            };
                            L.on('mousedown', function (V) {
                                function W(ab) {
                                    ab = ab.data.$;
                                    if (Z) {
                                        var ac = K.$.createTextRange();
                                        Q(ac, ab.x, ab.y);
                                        Z.setEndPoint(aa.compareEndPoints('StartToStart', ac) < 0 ? 'EndToEnd' : 'StartToStart', ac);
                                        Z.select();
                                    }
                                };
                                function X() {
                                    J.removeListener('mouseup', Y);
                                    L.removeListener('mouseup', Y);
                                };
                                function Y() {
                                    L.removeListener('mousemove', W);
                                    X();
                                    Z.select();
                                };
                                V = V.data;
                                if (V.getTarget().is('html') && V.$.x < L.$.clientWidth && V.$.y < L.$.clientHeight) {
                                    var Z = K.$.createTextRange();
                                    Q(Z, V.$.x, V.$.y);
                                    var aa = Z.duplicate();
                                    L.on('mousemove', W);
                                    J.on('mouseup', Y);
                                    L.on('mouseup', Y);
                                }
                            });
                        }
                        if (b.ie8) {
                            L.on('mousedown', function (V) {
                                if (V.data.getTarget().is('html')) {
                                    J.on('mouseup', S);
                                    L.on('mouseup', S);
                                }
                            });
                            function R() {
                                J.removeListener('mouseup', S);
                                L.removeListener('mouseup', S);
                            };
                            function S() {
                                R();
                                var V = a.document.$.selection, W = V.createRange();
                                if (V.type != 'None' && W.parentElement().ownerDocument == I.$)W.select();
                            };
                        }
                    }
                    I.on('selectionchange', U);
                    function T() {
                        N = 0;
                    };
                    function U(V) {
                        if (N) {
                            var W = D.document, X = D.getSelection(), Y = X && X.getNative();
                            if (V && Y && Y.type == 'None')if (!W.$.queryCommandEnabled('InsertImage')) {
                                e.setTimeout(U, 50, this, true);
                                return;
                            }
                            var Z;
                            if (Y && Y.type && Y.type != 'Control' && (Z = Y.createRange()) && (Z = Z.parentElement()) && (Z = Z.nodeName) && Z.toLowerCase() in {input: 1, textarea: 1})return;
                            try {
                                M = Y && X.getRanges()[0];
                            } catch (aa) {
                            }
                            p.call(D);
                        }
                    };
                } else {
                    I.on('mouseup', p, D);
                    I.on('keyup', p, D);
                    I.on('selectionchange', p, D);
                }
                if (b.webkit)I.on('keydown', function (V) {
                    var W = V.data.getKey();
                    switch (W) {
                        case 13:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 39:
                        case 8:
                        case 45:
                        case 46:
                            w(D.document);
                    }
                }, null, null, -1);
            });
            D.on('contentDomUnload', D.forceNextSelectionCheck, D);
            D.addCommand('selectAll', s);
            D.ui.addButton('SelectAll', {label: D.lang.selectAll, command: 'selectAll'});
            D.selectionChange = function (I) {
                (I ? m : p).call(this);
            };
            b.ie9Compat && D.on('destroy', function () {
                var I = D.getSelection();
                I && I.getNative().clear();
            }, null, null, 9);
        }});
        a.editor.prototype.getSelection = function () {
            return this.document && this.document.getSelection();
        };
        a.editor.prototype.forceNextSelectionCheck = function () {
            delete this._.selectionPreviousPath;
        };
        g.prototype.getSelection = function () {
            var D = new d.selection(this);
            return!D || D.isInvalid ? null : D;
        };
        a.SELECTION_NONE = 1;
        a.SELECTION_TEXT = 2;
        a.SELECTION_ELEMENT = 3;
        d.selection = function (D) {
            var G = this;
            var E = D.getCustomData('cke_locked_selection');
            if (E)return E;
            G.document = D;
            G.isLocked = 0;
            G._ = {cache: {}};
            if (c)try {
                var F = G.getNative().createRange();
                if (!F || F.item && F.item(0).ownerDocument != G.document.$ || F.parentElement && F.parentElement().ownerDocument != G.document.$)throw 0;
            } catch (H) {
                G.isInvalid = true;
            }
            return G;
        };
        var y = {img: 1, hr: 1, li: 1, table: 1, tr: 1, td: 1, th: 1, embed: 1, object: 1, ol: 1, ul: 1, a: 1, input: 1, form: 1, select: 1, textarea: 1, button: 1, fieldset: 1, thead: 1, tfoot: 1};
        d.selection.prototype = {getNative: c ? function () {
            return this._.cache.nativeSel || (this._.cache.nativeSel = this.document.$.selection);
        } : function () {
            return this._.cache.nativeSel || (this._.cache.nativeSel = this.document.getWindow().$.getSelection());
        }, getType: c ? function () {
            var D = this._.cache;
            if (D.type)return D.type;
            var E = 1;
            try {
                var F = this.getNative(), G = F.type;
                if (G == 'Text')E = 2;
                if (G == 'Control')E = 3;
                if (F.createRange().parentElement)E = 2;
            } catch (H) {
            }
            return D.type = E;
        } : function () {
            var D = this._.cache;
            if (D.type)return D.type;
            var E = 2, F = this.getNative();
            if (!F)E = 1; else if (F.rangeCount == 1) {
                var G = F.getRangeAt(0), H = G.startContainer;
                if (H == G.endContainer && H.nodeType == 1 && G.endOffset - G.startOffset == 1 && y[H.childNodes[G.startOffset].nodeName.toLowerCase()])E = 3;
            }
            return D.type = E;
        }, getRanges: (function () {
            var D = c ? (function () {
                function E(G) {
                    return new d.node(G).getIndex();
                };
                var F = function (G, H) {
                    G = G.duplicate();
                    G.collapse(H);
                    var I = G.parentElement(), J = I.ownerDocument;
                    if (!I.hasChildNodes())return{container: I, offset: 0};
                    var K = I.children, L, M, N = G.duplicate(), O = 0, P = K.length - 1, Q = -1, R, S, T;
                    while (O <= P) {
                        Q = Math.floor((O + P) / 2);
                        L = K[Q];
                        N.moveToElementText(L);
                        R = N.compareEndPoints('StartToStart', G);
                        if (R > 0)P = Q - 1; else if (R < 0)O = Q + 1; else if (b.ie9Compat && L.tagName == 'BR') {
                            var U = J.defaultView.getSelection();
                            return{container: U[H ? 'anchorNode' : 'focusNode'], offset: U[H ? 'anchorOffset' : 'focusOffset']};
                        } else return{container: I, offset: E(L)};
                    }
                    if (Q == -1 || Q == K.length - 1 && R < 0) {
                        N.moveToElementText(I);
                        N.setEndPoint('StartToStart', G);
                        S = N.text.replace(/(\r\n|\r)/g, '\n').length;
                        K = I.childNodes;
                        if (!S) {
                            L = K[K.length - 1];
                            if (L.nodeType != 3)return{container: I, offset: K.length}; else return{container: L, offset: L.nodeValue.length};
                        }
                        var V = K.length;
                        while (S > 0 && V > 0) {
                            M = K[--V];
                            if (M.nodeType == 3) {
                                T = M;
                                S -= M.nodeValue.length;
                            }
                        }
                        return{container: T, offset: -S};
                    } else {
                        N.collapse(R > 0 ? true : false);
                        N.setEndPoint(R > 0 ? 'StartToStart' : 'EndToStart', G);
                        S = N.text.replace(/(\r\n|\r)/g, '\n').length;
                        if (!S)return{container: I, offset: E(L) + (R > 0 ? 0 : 1)};
                        while (S > 0)try {
                            M = L[R > 0 ? 'previousSibling' : 'nextSibling'];
                            if (M.nodeType == 3) {
                                S -= M.nodeValue.length;
                                T = M;
                            }
                            L = M;
                        } catch (W) {
                            return{container: I, offset: E(L)};
                        }
                        return{container: T, offset: R > 0 ? -S : T.nodeValue.length + S};
                    }
                };
                return function () {
                    var Q = this;
                    var G = Q.getNative(), H = G && G.createRange(), I = Q.getType(), J;
                    if (!G)return[];
                    if (I == 2) {
                        J = new d.range(Q.document);
                        var K = F(H, true);
                        J.setStart(new d.node(K.container), K.offset);
                        K = F(H);
                        J.setEnd(new d.node(K.container), K.offset);
                        if (J.endContainer.getPosition(J.startContainer) & 4 && J.endOffset <= J.startContainer.getIndex())J.collapse();
                        return[J];
                    } else if (I == 3) {
                        var L = [];
                        for (var M = 0; M < H.length; M++) {
                            var N = H.item(M), O = N.parentNode, P = 0;
                            J = new d.range(Q.document);
                            for (; P < O.childNodes.length && O.childNodes[P] != N; P++) {
                            }
                            J.setStart(new d.node(O), P);
                            J.setEnd(new d.node(O), P + 1);
                            L.push(J);
                        }
                        return L;
                    }
                    return[];
                };
            })() : function () {
                var E = [], F, G = this.document, H = this.getNative();
                if (!H)return E;
                if (!H.rangeCount) {
                    F = new d.range(G);
                    F.moveToElementEditStart(G.getBody());
                    E.push(F);
                }
                for (var I = 0; I < H.rangeCount; I++) {
                    var J = H.getRangeAt(I);
                    F = new d.range(G);
                    F.setStart(new d.node(J.startContainer), J.startOffset);
                    F.setEnd(new d.node(J.endContainer), J.endOffset);
                    E.push(F);
                }
                return E;
            };
            return function (E) {
                var F = this._.cache;
                if (F.ranges && !E)return F.ranges; else if (!F.ranges)F.ranges = new d.rangeList(D.call(this));
                if (E) {
                    var G = F.ranges;
                    for (var H = 0; H < G.length; H++) {
                        var I = G[H], J = I.getCommonAncestor();
                        if (J.isReadOnly())G.splice(H, 1);
                        if (I.collapsed)continue;
                        if (I.startContainer.isReadOnly()) {
                            var K = I.startContainer;
                            while (K) {
                                if (K.is('body') || !K.isReadOnly())break;
                                if (K.type == 1 && K.getAttribute('contentEditable') == 'false')I.setStartAfter(K);
                                K = K.getParent();
                            }
                        }
                        var L = I.startContainer, M = I.endContainer, N = I.startOffset, O = I.endOffset, P = I.clone();
                        if (L && L.type == 3)if (N >= L.getLength())P.setStartAfter(L); else P.setStartBefore(L);
                        if (M && M.type == 3)if (!O)P.setEndBefore(M); else P.setEndAfter(M);
                        var Q = new d.walker(P);
                        Q.evaluator = function (R) {
                            if (R.type == 1 && R.isReadOnly()) {
                                var S = I.clone();
                                I.setEndBefore(R);
                                if (I.collapsed)G.splice(H--, 1);
                                if (!(R.getPosition(P.endContainer) & 16)) {
                                    S.setStartAfter(R);
                                    if (!S.collapsed)G.splice(H + 1, 0, S);
                                }
                                return true;
                            }
                            return false;
                        };
                        Q.next();
                    }
                }
                return F.ranges;
            };
        })(), getStartElement: function () {
            var K = this;
            var D = K._.cache;
            if (D.startElement !== undefined)return D.startElement;
            var E, F = K.getNative();
            switch (K.getType()) {
                case 3:
                    return K.getSelectedElement();
                case 2:
                    var G = K.getRanges()[0];
                    if (G) {
                        if (!G.collapsed) {
                            G.optimize();
                            while (1) {
                                var H = G.startContainer, I = G.startOffset;
                                if (I == (H.getChildCount ? H.getChildCount() : H.getLength()) && !H.isBlockBoundary())G.setStartAfter(H); else break;
                            }
                            E = G.startContainer;
                            if (E.type != 1)return E.getParent();
                            E = E.getChild(G.startOffset);
                            if (!E || E.type != 1)E = G.startContainer; else {
                                var J = E.getFirst();
                                while (J && J.type == 1) {
                                    E = J;
                                    J = J.getFirst();
                                }
                            }
                        } else {
                            E = G.startContainer;
                            if (E.type != 1)E = E.getParent();
                        }
                        E = E.$;
                    }
            }
            return D.startElement = E ? new h(E) : null;
        }, getSelectedElement: function () {
            var D = this._.cache;
            if (D.selectedElement !== undefined)return D.selectedElement;
            var E = this, F = e.tryThese(function () {
                return E.getNative().createRange().item(0);
            }, function () {
                var G, H, I = E.getRanges()[0], J = I.getCommonAncestor(1, 1), K = {table: 1, ul: 1, ol: 1, dl: 1};
                for (var L in K) {
                    if (G = J.getAscendant(L, 1))break;
                }
                if (G) {
                    var M = new d.range(this.document);
                    M.setStartAt(G, 1);
                    M.setEnd(I.startContainer, I.startOffset);
                    var N = e.extend(K, f.$listItem, f.$tableContent), O = new d.walker(M), P = function (Q, R) {
                        return function (S, T) {
                            if (S.type == 3 && (!e.trim(S.getText()) || S.getParent().data('cke-bookmark')))return true;
                            var U;
                            if (S.type == 1) {
                                U = S.getName();
                                if (U == 'br' && R && S.equals(S.getParent().getBogus()))return true;
                                if (T && U in N || U in f.$removeEmpty)return true;
                            }
                            Q.halted = 1;
                            return false;
                        };
                    };
                    O.guard = P(O);
                    if (O.checkBackward() && !O.halted) {
                        O = new d.walker(M);
                        M.setStart(I.endContainer, I.endOffset);
                        M.setEndAt(G, 2);
                        O.guard = P(O, 1);
                        if (O.checkForward() && !O.halted)H = G.$;
                    }
                }
                if (!H)throw 0;
                return H;
            }, function () {
                var G = E.getRanges()[0], H, I;
                for (var J = 2; J && !((H = G.getEnclosedNode()) && H.type == 1 && y[H.getName()] && (I = H)); J--)G.shrink(1);
                return I.$;
            });
            return D.selectedElement = F ? new h(F) : null;
        }, getSelectedText: function () {
            var D = this._.cache;
            if (D.selectedText !== undefined)return D.selectedText;
            var E = '', F = this.getNative();
            if (this.getType() == 2)E = c ? F.createRange().text : F.toString();
            return D.selectedText = E;
        }, lock: function () {
            var D = this;
            D.getRanges();
            D.getStartElement();
            D.getSelectedElement();
            D.getSelectedText();
            D._.cache.nativeSel = {};
            D.isLocked = 1;
            D.document.setCustomData('cke_locked_selection', D);
        }, unlock: function (D) {
            var I = this;
            var E = I.document, F = E.getCustomData('cke_locked_selection');
            if (F) {
                E.setCustomData('cke_locked_selection', null);
                if (D) {
                    var G = F.getSelectedElement(), H = !G && F.getRanges();
                    I.isLocked = 0;
                    I.reset();
                    if (G)I.selectElement(G); else I.selectRanges(H);
                }
            }
            if (!F || !D) {
                I.isLocked = 0;
                I.reset();
            }
        }, reset: function () {
            this._.cache = {};
        }, selectElement: function (D) {
            var F = this;
            if (F.isLocked) {
                var E = new d.range(F.document);
                E.setStartBefore(D);
                E.setEndAfter(D);
                F._.cache.selectedElement = D;
                F._.cache.startElement = D;
                F._.cache.ranges = new d.rangeList(E);
                F._.cache.type = 3;
                return;
            }
            E = new d.range(D.getDocument());
            E.setStartBefore(D);
            E.setEndAfter(D);
            E.select();
            F.document.fire('selectionchange');
            F.reset();
        }, selectRanges: function (D) {
            var R = this;
            if (R.isLocked) {
                R._.cache.selectedElement = null;
                R._.cache.startElement = D[0] && D[0].getTouchedStartNode();
                R._.cache.ranges = new d.rangeList(D);
                R._.cache.type = 2;
                return;
            }
            if (c) {
                if (D.length > 1) {
                    var E = D[D.length - 1];
                    D[0].setEnd(E.endContainer, E.endOffset);
                    D.length = 1;
                }
                if (D[0])D[0].select();
                R.reset();
            } else {
                var F = R.getNative();
                if (!F)return;
                if (D.length) {
                    F.removeAllRanges();
                    b.webkit && w(R.document);
                }
                for (var G = 0; G < D.length; G++) {
                    if (G < D.length - 1) {
                        var H = D[G], I = D[G + 1], J = H.clone();
                        J.setStart(H.endContainer, H.endOffset);
                        J.setEnd(I.startContainer, I.startOffset);
                        if (!J.collapsed) {
                            J.shrink(1, true);
                            var K = J.getCommonAncestor(), L = J.getEnclosedNode();
                            if (K.isReadOnly() || L && L.isReadOnly()) {
                                I.setStart(H.startContainer, H.startOffset);
                                D.splice(G--, 1);
                                continue;
                            }
                        }
                    }
                    var M = D[G], N = R.document.$.createRange(), O = M.startContainer;
                    if (M.collapsed && (b.opera || b.gecko && b.version < 10900) && O.type == 1 && !O.getChildCount())O.appendText('');
                    if (M.collapsed && b.webkit && r(M)) {
                        var P = t(R.document);
                        M.insertNode(P);
                        var Q = P.getNext();
                        if (Q && !P.getPrevious() && Q.type == 1 && Q.getName() == 'br') {
                            w(R.document);
                            M.moveToPosition(Q, 3);
                        } else M.moveToPosition(P, 4);
                    }
                    N.setStart(M.startContainer.$, M.startOffset);
                    try {
                        N.setEnd(M.endContainer.$, M.endOffset);
                    } catch (S) {
                        if (S.toString().indexOf('NS_ERROR_ILLEGAL_VALUE') >= 0) {
                            M.collapse(1);
                            N.setEnd(M.endContainer.$, M.endOffset);
                        } else throw S;
                    }
                    F.addRange(N);
                }
                R.document.fire('selectionchange');
                R.reset();
            }
        }, createBookmarks: function (D) {
            return this.getRanges().createBookmarks(D);
        }, createBookmarks2: function (D) {
            return this.getRanges().createBookmarks2(D);
        }, selectBookmarks: function (D) {
            var E = [];
            for (var F = 0; F < D.length; F++) {
                var G = new d.range(this.document);
                G.moveToBookmark(D[F]);
                E.push(G);
            }
            this.selectRanges(E);
            return this;
        }, getCommonAncestor: function () {
            var D = this.getRanges(), E = D[0].startContainer, F = D[D.length - 1].endContainer;
            return E.getCommonAncestor(F);
        }, scrollIntoView: function () {
            var D = this.getStartElement();
            D.scrollIntoView();
        }};
        var z = d.walker.whitespaces(true), A = d.walker.invisible(1), B = /\ufeff|\u00a0/, C = {table: 1, tbody: 1, tr: 1};
        d.range.prototype.select = c ? function (D) {
            var O = this;
            var E = O.collapsed, F, G, H, I = O.getEnclosedNode();
            if (I)try {
                H = O.document.$.body.createControlRange();
                H.addElement(I.$);
                H.select();
                return;
            } catch (P) {
            }
            if (O.startContainer.type == 1 && O.startContainer.getName() in C || O.endContainer.type == 1 && O.endContainer.getName() in C)O.shrink(1, true);
            var J = O.createBookmark(), K = J.startNode, L;
            if (!E)L = J.endNode;
            H = O.document.$.body.createTextRange();
            H.moveToElementText(K.$);
            H.moveStart('character', 1);
            if (L) {
                var M = O.document.$.body.createTextRange();
                M.moveToElementText(L.$);
                H.setEndPoint('EndToEnd', M);
                H.moveEnd('character', -1);
            } else {
                var N = K.getNext(z);
                F = !(N && N.getText && N.getText().match(B)) && (D || !K.hasPrevious() || K.getPrevious().is && K.getPrevious().is('br'));
                G = O.document.createElement('span');
                G.setHtml('&#65279;');
                G.insertBefore(K);
                if (F)O.document.createText('\ufeff').insertBefore(K);
            }
            O.setStartBefore(K);
            K.remove();
            if (E) {
                if (F) {
                    H.moveStart('character', -1);
                    H.select();
                    O.document.$.selection.clear();
                } else H.select();
                O.moveToPosition(G, 3);
                G.remove();
            } else {
                O.setEndBefore(L);
                L.remove();
                H.select();
            }
            O.document.fire('selectionchange');
        } : function () {
            this.document.getSelection().selectRanges([this]);
        };
    })();
    (function () {
        var m = a.htmlParser.cssStyle, n = e.cssLength, o = /^((?:\d*(?:\.\d+))|(?:\d+))(.*)?$/i;

        function p(r, s) {
            var t = o.exec(r), u = o.exec(s);
            if (t) {
                if (!t[2] && u[2] == 'px')return u[1];
                if (t[2] == 'px' && !u[2])return u[1] + 'px';
            }
            return s;
        };
        var q = {elements: {$: function (r) {
            var s = r.attributes, t = s && s['data-cke-realelement'], u = t && new a.htmlParser.fragment.fromHtml(decodeURIComponent(t)), v = u && u.children[0];
            if (v && r.attributes['data-cke-resizable']) {
                var w = new m(r).rules, x = v.attributes, y = w.width, z = w.height;
                y && (x.width = p(x.width, y));
                z && (x.height = p(x.height, z));
            }
            return v;
        }}};
        j.add('fakeobjects', {requires: ['htmlwriter'], afterInit: function (r) {
            var s = r.dataProcessor, t = s && s.htmlFilter;
            if (t)t.addRules(q);
        }});
        a.editor.prototype.createFakeElement = function (r, s, t, u) {
            var v = this.lang.fakeobjects, w = v[t] || v.unknown, x = {'class': s, 'data-cke-realelement': encodeURIComponent(r.getOuterHtml()), 'data-cke-real-node-type': r.type, alt: w, title: w, align: r.getAttribute('align') || ''};
            if (!b.hc)x.src = a.getUrl('images/spacer.gif');
            if (t)x['data-cke-real-element-type'] = t;
            if (u) {
                x['data-cke-resizable'] = u;
                var y = new m(), z = r.getAttribute('width'), A = r.getAttribute('height');
                z && (y.rules.width = n(z));
                A && (y.rules.height = n(A));
                y.populate(x);
            }
            return this.document.createElement('img', {attributes: x});
        };
        a.editor.prototype.createFakeParserElement = function (r, s, t, u) {
            var v = this.lang.fakeobjects, w = v[t] || v.unknown, x, y = new a.htmlParser.basicWriter();
            r.writeHtml(y);
            x = y.getHtml();
            var z = {'class': s, 'data-cke-realelement': encodeURIComponent(x), 'data-cke-real-node-type': r.type, alt: w, title: w, align: r.attributes.align || ''};
            if (!b.hc)z.src = a.getUrl('images/spacer.gif');
            if (t)z['data-cke-real-element-type'] = t;
            if (u) {
                z['data-cke-resizable'] = u;
                var A = r.attributes, B = new m(), C = A.width, D = A.height;
                C != undefined && (B.rules.width = n(C));
                D != undefined && (B.rules.height = n(D));
                B.populate(z);
            }
            return new a.htmlParser.element('img', z);
        };
        a.editor.prototype.restoreRealElement = function (r) {
            if (r.data('cke-real-node-type') != 1)return null;
            var s = h.createFromHtml(decodeURIComponent(r.data('cke-realelement')), this.document);
            if (r.data('cke-resizable')) {
                var t = r.getStyle('width'), u = r.getStyle('height');
                t && s.setAttribute('width', p(s.getAttribute('width'), t));
                u && s.setAttribute('height', p(s.getAttribute('height'), u));
            }
            return s;
        };
    })();
    j.add('richcombo', {requires: ['floatpanel', 'listblock', 'button'], beforeInit: function (m) {
        m.ui.addHandler('richcombo', k.richCombo.handler);
    }});
    a.UI_RICHCOMBO = 'richcombo';
    k.richCombo = e.createClass({$: function (m) {
        var o = this;
        e.extend(o, m, {title: m.label, modes: {wysiwyg: 1}});
        var n = o.panel || {};
        delete o.panel;
        o.id = e.getNextNumber();
        o.document = n && n.parent && n.parent.getDocument() || a.document;
        n.className = (n.className || '') + ' cke_rcombopanel';
        n.block = {multiSelect: n.multiSelect, attributes: n.attributes};
        o._ = {panelDefinition: n, items: {}, state: 2};
    }, statics: {handler: {create: function (m) {
        return new k.richCombo(m);
    }}}, proto: {renderHtml: function (m) {
        var n = [];
        this.render(m, n);
        return n.join('');
    }, render: function (m, n) {
        var o = b, p = 'cke_' + this.id, q = e.addFunction(function (v) {
            var y = this;
            var w = y._;
            if (w.state == 0)return;
            y.createPanel(m);
            if (w.on) {
                w.panel.hide();
                return;
            }
            y.commit();
            var x = y.getValue();
            if (x)w.list.mark(x); else w.list.unmarkAll();
            w.panel.showBlock(y.id, new h(v), 4);
        }, this), r = {id: p, combo: this, focus: function () {
            var v = a.document.getById(p).getChild(1);
            v.focus();
        }, clickFn: q};

        function s() {
            var w = this;
            var v = w.modes[m.mode] ? 2 : 0;
            w.setState(m.readOnly && !w.readOnly ? 0 : v);
            w.setValue('');
        };
        m.on('mode', s, this);
        !this.readOnly && m.on('readOnly', s, this);
        var t = e.addFunction(function (v, w) {
            v = new d.event(v);
            var x = v.getKeystroke();
            switch (x) {
                case 13:
                case 32:
                case 40:
                    e.callFunction(q, w);
                    break;
                default:
                    r.onkey(r, x);
            }
            v.preventDefault();
        }), u = e.addFunction(function () {
            r.onfocus && r.onfocus();
        });
        r.keyDownFn = t;
        n.push('<span class="cke_rcombo" role="presentation">', '<span id=', p);
        if (this.className)n.push(' class="', this.className, ' cke_off"');
        n.push(' role="presentation">', '<span id="' + p + '_label" class=cke_label>', this.label, '</span>', '<a hidefocus=true title="', this.title, '" tabindex="-1"', o.gecko && o.version >= 10900 && !o.hc ? '' : " href=\"javascript:void('" + this.label + "')\"", ' role="button" aria-labelledby="', p, '_label" aria-describedby="', p, '_text" aria-haspopup="true"');
        if (b.opera || b.gecko && b.mac)n.push(' onkeypress="return false;"');
        if (b.gecko)n.push(' onblur="this.style.cssText = this.style.cssText;"');
        n.push(' onkeydown="CKEDITOR.tools.callFunction( ', t, ', event, this );" onfocus="return CKEDITOR.tools.callFunction(', u, ', event);" ' + (c ? 'onclick="return false;" onmouseup' : 'onclick') + '="CKEDITOR.tools.callFunction(', q, ', this); return false;"><span><span id="' + p + '_text" class="cke_text cke_inline_label">' + this.label + '</span>' + '</span>' + '<span class=cke_openbutton><span class=cke_icon>' + (b.hc ? '&#9660;' : b.air ? '&nbsp;' : '') + '</span></span>' + '</a>' + '</span>' + '</span>');
        if (this.onRender)this.onRender();
        return r;
    }, createPanel: function (m) {
        if (this._.panel)return;
        var n = this._.panelDefinition, o = this._.panelDefinition.block, p = n.parent || a.document.getBody(), q = new k.floatPanel(m, p, n), r = q.addListBlock(this.id, o), s = this;
        q.onShow = function () {
            if (s.className)this.element.getFirst().addClass(s.className + '_panel');
            s.setState(1);
            r.focus(!s.multiSelect && s.getValue());
            s._.on = 1;
            if (s.onOpen)s.onOpen();
        };
        q.onHide = function (t) {
            if (s.className)this.element.getFirst().removeClass(s.className + '_panel');
            s.setState(s.modes && s.modes[m.mode] ? 2 : 0);
            s._.on = 0;
            if (!t && s.onClose)s.onClose();
        };
        q.onEscape = function () {
            q.hide();
        };
        r.onClick = function (t, u) {
            s.document.getWindow().focus();
            if (s.onClick)s.onClick.call(s, t, u);
            if (u)s.setValue(t, s._.items[t]); else s.setValue('');
            q.hide(false);
        };
        this._.panel = q;
        this._.list = r;
        q.getBlock(this.id).onHide = function () {
            s._.on = 0;
            s.setState(2);
        };
        if (this.init)this.init();
    }, setValue: function (m, n) {
        var p = this;
        p._.value = m;
        var o = p.document.getById('cke_' + p.id + '_text');
        if (o) {
            if (!(m || n)) {
                n = p.label;
                o.addClass('cke_inline_label');
            } else o.removeClass('cke_inline_label');
            o.setHtml(typeof n != 'undefined' ? n : m);
        }
    }, getValue: function () {
        return this._.value || '';
    }, unmarkAll: function () {
        this._.list.unmarkAll();
    }, mark: function (m) {
        this._.list.mark(m);
    }, hideItem: function (m) {
        this._.list.hideItem(m);
    }, hideGroup: function (m) {
        this._.list.hideGroup(m);
    }, showAll: function () {
        this._.list.showAll();
    }, add: function (m, n, o) {
        this._.items[m] = o || m;
        this._.list.add(m, n, o);
    }, startGroup: function (m) {
        this._.list.startGroup(m);
    }, commit: function () {
        var m = this;
        if (!m._.committed) {
            m._.list.commit();
            m._.committed = 1;
            k.fire('ready', m);
        }
        m._.committed = 1;
    }, setState: function (m) {
        var n = this;
        if (n._.state == m)return;
        n.document.getById('cke_' + n.id).setState(m);
        n._.state = m;
    }}});
    k.prototype.addRichCombo = function (m, n) {
        this.add(m, 'richcombo', n);
    };
    j.add('htmlwriter');
    a.htmlWriter = e.createClass({base: a.htmlParser.basicWriter, $: function () {
        var o = this;
        o.base();
        o.indentationChars = '\t';
        o.selfClosingEnd = ' />';
        o.lineBreakChars = '\n';
        o.forceSimpleAmpersand = 0;
        o.sortAttributes = 1;
        o._.indent = 0;
        o._.indentation = '';
        o._.inPre = 0;
        o._.rules = {};
        var m = f;
        for (var n in e.extend({}, m.$nonBodyContent, m.$block, m.$listItem, m.$tableContent))o.setRules(n, {indent: 1, breakBeforeOpen: 1, breakAfterOpen: 1, breakBeforeClose: !m[n]['#'], breakAfterClose: 1});
        o.setRules('br', {breakAfterOpen: 1});
        o.setRules('title', {indent: 0, breakAfterOpen: 0});
        o.setRules('style', {indent: 0, breakBeforeClose: 1});
        o.setRules('pre', {indent: 0});
    }, proto: {openTag: function (m, n) {
        var p = this;
        var o = p._.rules[m];
        if (p._.indent)p.indentation(); else if (o && o.breakBeforeOpen) {
            p.lineBreak();
            p.indentation();
        }
        p._.output.push('<', m);
    }, openTagClose: function (m, n) {
        var p = this;
        var o = p._.rules[m];
        if (n)p._.output.push(p.selfClosingEnd); else {
            p._.output.push('>');
            if (o && o.indent)p._.indentation += p.indentationChars;
        }
        if (o && o.breakAfterOpen)p.lineBreak();
        m == 'pre' && (p._.inPre = 1);
    }, attribute: function (m, n) {
        if (typeof n == 'string') {
            this.forceSimpleAmpersand && (n = n.replace(/&amp;/g, '&'));
            n = e.htmlEncodeAttr(n);
        }
        this._.output.push(' ', m, '="', n, '"');
    }, closeTag: function (m) {
        var o = this;
        var n = o._.rules[m];
        if (n && n.indent)o._.indentation = o._.indentation.substr(o.indentationChars.length);
        if (o._.indent)o.indentation(); else if (n && n.breakBeforeClose) {
            o.lineBreak();
            o.indentation();
        }
        o._.output.push('</', m, '>');
        m == 'pre' && (o._.inPre = 0);
        if (n && n.breakAfterClose)o.lineBreak();
    }, text: function (m) {
        var n = this;
        if (n._.indent) {
            n.indentation();
            !n._.inPre && (m = e.ltrim(m));
        }
        n._.output.push(m);
    }, comment: function (m) {
        if (this._.indent)this.indentation();
        this._.output.push('<!--', m, '-->');
    }, lineBreak: function () {
        var m = this;
        if (!m._.inPre && m._.output.length > 0)m._.output.push(m.lineBreakChars);
        m._.indent = 1;
    }, indentation: function () {
        var m = this;
        if (!m._.inPre)m._.output.push(m._.indentation);
        m._.indent = 0;
    }, setRules: function (m, n) {
        var o = this._.rules[m];
        if (o)e.extend(o, n, true); else this._.rules[m] = n;
    }}});
    j.add('menubutton', {requires: ['button', 'menu'], beforeInit: function (m) {
        m.ui.addHandler('menubutton', k.menuButton.handler);
    }});
    a.UI_MENUBUTTON = 'menubutton';
    (function () {
        var m = function (n) {
            var o = this._;
            if (o.state === 0)return;
            o.previousState = o.state;
            var p = o.menu;
            if (!p) {
                p = o.menu = new a.menu(n, {panel: {className: n.skinClass + ' cke_contextmenu', attributes: {'aria-label': n.lang.common.options}}});
                p.onHide = e.bind(function () {
                    this.setState(this.modes && this.modes[n.mode] ? o.previousState : 0);
                }, this);
                if (this.onMenu)p.addListener(this.onMenu);
            }
            if (o.on) {
                p.hide();
                return;
            }
            this.setState(1);
            p.show(a.document.getById(this._.id), 4);
        };
        k.menuButton = e.createClass({base: k.button, $: function (n) {
            var o = n.panel;
            delete n.panel;
            this.base(n);
            this.hasArrow = true;
            this.click = m;
        }, statics: {handler: {create: function (n) {
            return new k.menuButton(n);
        }}}});
    })();
    j.add('dialogui');
    (function () {
        var m = function (u) {
            var x = this;
            x._ || (x._ = {});
            x._['default'] = x._.initValue = u['default'] || '';
            x._.required = u.required || false;
            var v = [x._];
            for (var w = 1; w < arguments.length; w++)v.push(arguments[w]);
            v.push(true);
            e.extend.apply(e, v);
            return x._;
        }, n = {build: function (u, v, w) {
            return new k.dialog.textInput(u, v, w);
        }}, o = {build: function (u, v, w) {
            return new k.dialog[v.type](u, v, w);
        }}, p = {build: function (u, v, w) {
            var x = v.children, y, z = [], A = [];
            for (var B = 0; B < x.length && (y = x[B]); B++) {
                var C = [];
                z.push(C);
                A.push(a.dialog._.uiElementBuilders[y.type].build(u, y, C));
            }
            return new k.dialog[v.type](u, A, z, w, v);
        }}, q = {isChanged: function () {
            return this.getValue() != this.getInitValue();
        }, reset: function (u) {
            this.setValue(this.getInitValue(), u);
        }, setInitValue: function () {
            this._.initValue = this.getValue();
        }, resetInitValue: function () {
            this._.initValue = this._['default'];
        }, getInitValue: function () {
            return this._.initValue;
        }}, r = e.extend({}, k.dialog.uiElement.prototype.eventProcessors, {onChange: function (u, v) {
            if (!this._.domOnChangeRegistered) {
                u.on('load', function () {
                    this.getInputElement().on('change', function () {
                        if (!u.parts.dialog.isVisible())return;
                        this.fire('change', {value: this.getValue()});
                    }, this);
                }, this);
                this._.domOnChangeRegistered = true;
            }
            this.on('change', v);
        }}, true), s = /^on([A-Z]\w+)/, t = function (u) {
            for (var v in u) {
                if (s.test(v) || v == 'title' || v == 'type')delete u[v];
            }
            return u;
        };
        e.extend(k.dialog, {labeledElement: function (u, v, w, x) {
            if (arguments.length < 4)return;
            var y = m.call(this, v);
            y.labelId = e.getNextId() + '_label';
            var z = this._.children = [], A = function () {
                var B = [], C = v.required ? ' cke_required' : '';
                if (v.labelLayout != 'horizontal')B.push('<label class="cke_dialog_ui_labeled_label' + C + '" ', ' id="' + y.labelId + '"', y.inputId ? ' for="' + y.inputId + '"' : '', (v.labelStyle ? ' style="' + v.labelStyle + '"' : '') + '>', v.label, '</label>', '<div class="cke_dialog_ui_labeled_content"' + (v.controlStyle ? ' style="' + v.controlStyle + '"' : '') + ' role="presentation">', x.call(this, u, v), '</div>'); else {
                    var D = {type: 'hbox', widths: v.widths, padding: 0, children: [
                        {type: 'html', html: '<label class="cke_dialog_ui_labeled_label' + C + '"' + ' id="' + y.labelId + '"' + ' for="' + y.inputId + '"' + (v.labelStyle ? ' style="' + v.labelStyle + '"' : '') + '>' + e.htmlEncode(v.label) + '</span>'},
                        {type: 'html', html: '<span class="cke_dialog_ui_labeled_content"' + (v.controlStyle ? ' style="' + v.controlStyle + '"' : '') + '>' + x.call(this, u, v) + '</span>'}
                    ]};
                    a.dialog._.uiElementBuilders.hbox.build(u, D, B);
                }
                return B.join('');
            };
            k.dialog.uiElement.call(this, u, v, w, 'div', null, {role: 'presentation'}, A);
        }, textInput: function (u, v, w) {
            if (arguments.length < 3)return;
            m.call(this, v);
            var x = this._.inputId = e.getNextId() + '_textInput', y = {'class': 'cke_dialog_ui_input_' + v.type, id: x, type: v.type}, z;
            if (v.validate)this.validate = v.validate;
            if (v.maxLength)y.maxlength = v.maxLength;
            if (v.size)y.size = v.size;
            if (v.inputStyle)y.style = v.inputStyle;
            var A = function () {
                var B = ['<div class="cke_dialog_ui_input_', v.type, '" role="presentation"'];
                if (v.width)B.push('style="width:' + v.width + '" ');
                B.push('><input ');
                y['aria-labelledby'] = this._.labelId;
                this._.required && (y['aria-required'] = this._.required);
                for (var C in y)B.push(C + '="' + y[C] + '" ');
                B.push(' /></div>');
                return B.join('');
            };
            k.dialog.labeledElement.call(this, u, v, w, A);
        }, textarea: function (u, v, w) {
            if (arguments.length < 3)return;
            m.call(this, v);
            var x = this, y = this._.inputId = e.getNextId() + '_textarea', z = {};
            if (v.validate)this.validate = v.validate;
            z.rows = v.rows || 5;
            z.cols = v.cols || 20;
            if (typeof v.inputStyle != 'undefined')z.style = v.inputStyle;
            var A = function () {
                z['aria-labelledby'] = this._.labelId;
                this._.required && (z['aria-required'] = this._.required);
                var B = ['<div class="cke_dialog_ui_input_textarea" role="presentation"><textarea class="cke_dialog_ui_input_textarea" id="', y, '" '];
                for (var C in z)B.push(C + '="' + e.htmlEncode(z[C]) + '" ');
                B.push('>', e.htmlEncode(x._['default']), '</textarea></div>');
                return B.join('');
            };
            k.dialog.labeledElement.call(this, u, v, w, A);
        }, checkbox: function (u, v, w) {
            if (arguments.length < 3)return;
            var x = m.call(this, v, {'default': !!v['default']});
            if (v.validate)this.validate = v.validate;
            var y = function () {
                var z = e.extend({}, v, {id: v.id ? v.id + '_checkbox' : e.getNextId() + '_checkbox'}, true), A = [], B = e.getNextId() + '_label', C = {'class': 'cke_dialog_ui_checkbox_input', type: 'checkbox', 'aria-labelledby': B};
                t(z);
                if (v['default'])C.checked = 'checked';
                if (typeof z.inputStyle != 'undefined')z.style = z.inputStyle;
                x.checkbox = new k.dialog.uiElement(u, z, A, 'input', null, C);
                A.push(' <label id="', B, '" for="', C.id, '"' + (v.labelStyle ? ' style="' + v.labelStyle + '"' : '') + '>', e.htmlEncode(v.label), '</label>');
                return A.join('');
            };
            k.dialog.uiElement.call(this, u, v, w, 'span', null, null, y);
        }, radio: function (u, v, w) {
            if (arguments.length < 3)return;
            m.call(this, v);
            if (!this._['default'])this._['default'] = this._.initValue = v.items[0][1];
            if (v.validate)this.validate = v.valdiate;
            var x = [], y = this, z = function () {
                var A = [], B = [], C = {'class': 'cke_dialog_ui_radio_item', 'aria-labelledby': this._.labelId}, D = v.id ? v.id + '_radio' : e.getNextId() + '_radio';
                for (var E = 0; E < v.items.length; E++) {
                    var F = v.items[E], G = F[2] !== undefined ? F[2] : F[0], H = F[1] !== undefined ? F[1] : F[0], I = e.getNextId() + '_radio_input', J = I + '_label', K = e.extend({}, v, {id: I, title: null, type: null}, true), L = e.extend({}, K, {title: G}, true), M = {type: 'radio', 'class': 'cke_dialog_ui_radio_input', name: D, value: H, 'aria-labelledby': J}, N = [];
                    if (y._['default'] == H)M.checked = 'checked';
                    t(K);
                    t(L);
                    if (typeof K.inputStyle != 'undefined')K.style = K.inputStyle;
                    x.push(new k.dialog.uiElement(u, K, N, 'input', null, M));
                    N.push(' ');
                    new k.dialog.uiElement(u, L, N, 'label', null, {id: J, 'for': M.id}, F[0]);
                    A.push(N.join(''));
                }
                new k.dialog.hbox(u, x, A, B);
                return B.join('');
            };
            k.dialog.labeledElement.call(this, u, v, w, z);
            this._.children = x;
        }, button: function (u, v, w) {
            if (!arguments.length)return;
            if (typeof v == 'function')v = v(u.getParentEditor());
            m.call(this, v, {disabled: v.disabled || false});
            a.event.implementOn(this);
            var x = this;
            u.on('load', function (A) {
                var B = this.getElement();
                (function () {
                    B.on('click', function (C) {
                        x.fire('click', {dialog: x.getDialog()});
                        C.data.preventDefault();
                    });
                    B.on('keydown', function (C) {
                        if (C.data.getKeystroke() in {32: 1}) {
                            x.click();
                            C.data.preventDefault();
                        }
                    });
                })();
                B.unselectable();
            }, this);
            var y = e.extend({}, v);
            delete y.style;
            var z = e.getNextId() + '_label';
            k.dialog.uiElement.call(this, u, y, w, 'a', null, {style: v.style, href: 'javascript:void(0)', title: v.label, hidefocus: 'true', 'class': v['class'], role: 'button', 'aria-labelledby': z}, '<span id="' + z + '" class="cke_dialog_ui_button">' + e.htmlEncode(v.label) + '</span>');
        }, select: function (u, v, w) {
            if (arguments.length < 3)return;
            var x = m.call(this, v);
            if (v.validate)this.validate = v.validate;
            x.inputId = e.getNextId() + '_select';
            var y = function () {
                var z = e.extend({}, v, {id: v.id ? v.id + '_select' : e.getNextId() + '_select'}, true), A = [], B = [], C = {id: x.inputId, 'class': 'cke_dialog_ui_input_select', 'aria-labelledby': this._.labelId};
                if (v.size != undefined)C.size = v.size;
                if (v.multiple != undefined)C.multiple = v.multiple;
                t(z);
                for (var D = 0, E; D < v.items.length && (E = v.items[D]); D++)B.push('<option value="', e.htmlEncode(E[1] !== undefined ? E[1] : E[0]).replace(/"/g, '&quot;'), '" /> ', e.htmlEncode(E[0]));
                if (typeof z.inputStyle != 'undefined')z.style = z.inputStyle;
                x.select = new k.dialog.uiElement(u, z, A, 'select', null, C, B.join(''));
                return A.join('');
            };
            k.dialog.labeledElement.call(this, u, v, w, y);
        }, file: function (u, v, w) {
            if (arguments.length < 3)return;
            if (v['default'] === undefined)v['default'] = '';
            var x = e.extend(m.call(this, v), {definition: v, buttons: []});
            if (v.validate)this.validate = v.validate;
            var y = function () {
                x.frameId = e.getNextId() + '_fileInput';
                var z = b.isCustomDomain(), A = ['<iframe frameborder="0" allowtransparency="0" class="cke_dialog_ui_input_file" role="presentation" id="', x.frameId, '" title="', v.label, '" src="javascript:void('];
                A.push(z ? "(function(){document.open();document.domain='" + document.domain + "';" + 'document.close();' + '})()' : '0');
                A.push(')"></iframe>');
                return A.join('');
            };
            u.on('load', function () {
                var z = a.document.getById(x.frameId), A = z.getParent();
                A.addClass('cke_dialog_ui_input_file');
            });
            k.dialog.labeledElement.call(this, u, v, w, y);
        }, fileButton: function (u, v, w) {
            if (arguments.length < 3)return;
            var x = m.call(this, v), y = this;
            if (v.validate)this.validate = v.validate;
            var z = e.extend({}, v), A = z.onClick;
            z.className = (z.className ? z.className + ' ' : '') + 'cke_dialog_ui_button';
            z.onClick = function (B) {
                var C = v['for'];
                if (!A || A.call(this, B) !== false) {
                    u.getContentElement(C[0], C[1]).submit();
                    this.disable();
                }
            };
            u.on('load', function () {
                u.getContentElement(v['for'][0], v['for'][1])._.buttons.push(y);
            });
            k.dialog.button.call(this, u, z, w);
        }, html: (function () {
            var u = /^\s*<[\w:]+\s+([^>]*)?>/, v = /^(\s*<[\w:]+(?:\s+[^>]*)?)((?:.|\r|\n)+)$/, w = /\/$/;
            return function (x, y, z) {
                if (arguments.length < 3)return;
                var A = [], B, C = y.html, D, E;
                if (C.charAt(0) != '<')C = '<span>' + C + '</span>';
                var F = y.focus;
                if (F) {
                    var G = this.focus;
                    this.focus = function () {
                        G.call(this);
                        typeof F == 'function' && F.call(this);
                        this.fire('focus');
                    };
                    if (y.isFocusable) {
                        var H = this.isFocusable;
                        this.isFocusable = H;
                    }
                    this.keyboardFocusable = true;
                }
                k.dialog.uiElement.call(this, x, y, A, 'span', null, null, '');
                B = A.join('');
                D = B.match(u);
                E = C.match(v) || ['', '', ''];
                if (w.test(E[1])) {
                    E[1] = E[1].slice(0, -1);
                    E[2] = '/' + E[2];
                }
                z.push([E[1], ' ', D[1] || '', E[2]].join(''));
            };
        })(), fieldset: function (u, v, w, x, y) {
            var z = y.label, A = function () {
                var B = [];
                z && B.push('<legend' + (y.labelStyle ? ' style="' + y.labelStyle + '"' : '') + '>' + z + '</legend>');
                for (var C = 0; C < w.length; C++)B.push(w[C]);
                return B.join('');
            };
            this._ = {children: v};
            k.dialog.uiElement.call(this, u, y, x, 'fieldset', null, null, A);
        }}, true);
        k.dialog.html.prototype = new k.dialog.uiElement();
        k.dialog.labeledElement.prototype = e.extend(new k.dialog.uiElement(), {setLabel: function (u) {
            var v = a.document.getById(this._.labelId);
            if (v.getChildCount() < 1)new d.text(u, a.document).appendTo(v); else v.getChild(0).$.nodeValue = u;
            return this;
        }, getLabel: function () {
            var u = a.document.getById(this._.labelId);
            if (!u || u.getChildCount() < 1)return ''; else return u.getChild(0).getText();
        }, eventProcessors: r}, true);
        k.dialog.button.prototype = e.extend(new k.dialog.uiElement(), {click: function () {
            var u = this;
            if (!u._.disabled)return u.fire('click', {dialog: u._.dialog});
            u.getElement().$.blur();
            return false;
        }, enable: function () {
            this._.disabled = false;
            var u = this.getElement();
            u && u.removeClass('cke_disabled');
        }, disable: function () {
            this._.disabled = true;
            this.getElement().addClass('cke_disabled');
        }, isVisible: function () {
            return this.getElement().getFirst().isVisible();
        }, isEnabled: function () {
            return!this._.disabled;
        }, eventProcessors: e.extend({}, k.dialog.uiElement.prototype.eventProcessors, {onClick: function (u, v) {
            this.on('click', function () {
                this.getElement().focus();
                v.apply(this, arguments);
            });
        }}, true), accessKeyUp: function () {
            this.click();
        }, accessKeyDown: function () {
            this.focus();
        }, keyboardFocusable: true}, true);
        k.dialog.textInput.prototype = e.extend(new k.dialog.labeledElement(), {getInputElement: function () {
            return a.document.getById(this._.inputId);
        }, focus: function () {
            var u = this.selectParentTab();
            setTimeout(function () {
                var v = u.getInputElement();
                v && v.$.focus();
            }, 0);
        }, select: function () {
            var u = this.selectParentTab();
            setTimeout(function () {
                var v = u.getInputElement();
                if (v) {
                    v.$.focus();
                    v.$.select();
                }
            }, 0);
        }, accessKeyUp: function () {
            this.select();
        }, setValue: function (u) {
            !u && (u = '');
            return k.dialog.uiElement.prototype.setValue.apply(this, arguments);
        }, keyboardFocusable: true}, q, true);
        k.dialog.textarea.prototype = new k.dialog.textInput();
        k.dialog.select.prototype = e.extend(new k.dialog.labeledElement(), {getInputElement: function () {
            return this._.select.getElement();
        }, add: function (u, v, w) {
            var x = new h('option', this.getDialog().getParentEditor().document), y = this.getInputElement().$;
            x.$.text = u;
            x.$.value = v === undefined || v === null ? u : v;
            if (w === undefined || w === null) {
                if (c)y.add(x.$); else y.add(x.$, null);
            } else y.add(x.$, w);
            return this;
        }, remove: function (u) {
            var v = this.getInputElement().$;
            v.remove(u);
            return this;
        }, clear: function () {
            var u = this.getInputElement().$;
            while (u.length > 0)u.remove(0);
            return this;
        }, keyboardFocusable: true}, q, true);
        k.dialog.checkbox.prototype = e.extend(new k.dialog.uiElement(), {getInputElement: function () {
            return this._.checkbox.getElement();
        }, setValue: function (u, v) {
            this.getInputElement().$.checked = u;
            !v && this.fire('change', {value: u});
        }, getValue: function () {
            return this.getInputElement().$.checked;
        }, accessKeyUp: function () {
            this.setValue(!this.getValue());
        }, eventProcessors: {onChange: function (u, v) {
            if (!c)return r.onChange.apply(this, arguments); else {
                u.on('load', function () {
                    var w = this._.checkbox.getElement();
                    w.on('propertychange', function (x) {
                        x = x.data.$;
                        if (x.propertyName == 'checked')this.fire('change', {value: w.$.checked});
                    }, this);
                }, this);
                this.on('change', v);
            }
            return null;
        }}, keyboardFocusable: true}, q, true);
        k.dialog.radio.prototype = e.extend(new k.dialog.uiElement(), {setValue: function (u, v) {
            var w = this._.children, x;
            for (var y = 0; y < w.length && (x = w[y]); y++)x.getElement().$.checked = x.getValue() == u;
            !v && this.fire('change', {value: u});
        }, getValue: function () {
            var u = this._.children;
            for (var v = 0; v < u.length; v++) {
                if (u[v].getElement().$.checked)return u[v].getValue();
            }
            return null;
        }, accessKeyUp: function () {
            var u = this._.children, v;
            for (v = 0; v < u.length; v++) {
                if (u[v].getElement().$.checked) {
                    u[v].getElement().focus();
                    return;
                }
            }
            u[0].getElement().focus();
        }, eventProcessors: {onChange: function (u, v) {
            if (!c)return r.onChange.apply(this, arguments); else {
                u.on('load', function () {
                    var w = this._.children, x = this;
                    for (var y = 0; y < w.length; y++) {
                        var z = w[y].getElement();
                        z.on('propertychange', function (A) {
                            A = A.data.$;
                            if (A.propertyName == 'checked' && this.$.checked)x.fire('change', {value: this.getAttribute('value')});
                        });
                    }
                }, this);
                this.on('change', v);
            }
            return null;
        }}, keyboardFocusable: true}, q, true);
        k.dialog.file.prototype = e.extend(new k.dialog.labeledElement(), q, {getInputElement: function () {
            var u = a.document.getById(this._.frameId).getFrameDocument();
            return u.$.forms.length > 0 ? new h(u.$.forms[0].elements[0]) : this.getElement();
        }, submit: function () {
            this.getInputElement().getParent().$.submit();
            return this;
        }, getAction: function () {
            return this.getInputElement().getParent().$.action;
        }, registerEvents: function (u) {
            var v = /^on([A-Z]\w+)/, w, x = function (z, A, B, C) {
                z.on('formLoaded', function () {
                    z.getInputElement().on(B, C, z);
                });
            };
            for (var y in u) {
                if (!(w = y.match(v)))continue;
                if (this.eventProcessors[y])this.eventProcessors[y].call(this, this._.dialog, u[y]); else x(this, this._.dialog, w[1].toLowerCase(), u[y]);
            }
            return this;
        }, reset: function () {
            var u = this._, v = a.document.getById(u.frameId), w = v.getFrameDocument(), x = u.definition, y = u.buttons, z = this.formLoadedNumber, A = this.formUnloadNumber, B = u.dialog._.editor.lang.dir, C = u.dialog._.editor.langCode;
            if (!z) {
                z = this.formLoadedNumber = e.addFunction(function () {
                    this.fire('formLoaded');
                }, this);
                A = this.formUnloadNumber = e.addFunction(function () {
                    this.getInputElement().clearCustomData();
                }, this);
                this.getDialog()._.editor.on('destroy', function () {
                    e.removeFunction(z);
                    e.removeFunction(A);
                });
            }
            function D() {
                w.$.open();
                if (b.isCustomDomain())w.$.domain = document.domain;
                var E = '';
                if (x.size)E = x.size - (c ? 7 : 0);
                var F = u.frameId + '_input';
                w.$.write(['<html dir="' + B + '" lang="' + C + '"><head><title></title></head><body style="margin: 0; overflow: hidden; background: transparent;">', '<form enctype="multipart/form-data" method="POST" dir="' + B + '" lang="' + C + '" action="', e.htmlEncode(x.action), '">', '<label id="', u.labelId, '" for="', F, '" style="display:none">', e.htmlEncode(x.label), '</label>', '<input id="', F, '" aria-labelledby="', u.labelId, '" type="file" name="', e.htmlEncode(x.id || 'cke_upload'), '" size="', e.htmlEncode(E > 0 ? E : ''), '" />', '</form>', '</body></html>', '<script>window.parent.CKEDITOR.tools.callFunction(' + z + ');', 'window.onbeforeunload = function() {window.parent.CKEDITOR.tools.callFunction(' + A + ')}</script>'].join(''));
                w.$.close();
                for (var G = 0; G < y.length; G++)y[G].enable();
            };
            if (b.gecko)setTimeout(D, 500); else D();
        }, getValue: function () {
            return this.getInputElement().$.value || '';
        }, setInitValue: function () {
            this._.initValue = '';
        }, eventProcessors: {onChange: function (u, v) {
            if (!this._.domOnChangeRegistered) {
                this.on('formLoaded', function () {
                    this.getInputElement().on('change', function () {
                        this.fire('change', {value: this.getValue()});
                    }, this);
                }, this);
                this._.domOnChangeRegistered = true;
            }
            this.on('change', v);
        }}, keyboardFocusable: true}, true);
        k.dialog.fileButton.prototype = new k.dialog.button();
        k.dialog.fieldset.prototype = e.clone(k.dialog.hbox.prototype);
        a.dialog.addUIElement('text', n);
        a.dialog.addUIElement('password', n);
        a.dialog.addUIElement('textarea', o);
        a.dialog.addUIElement('checkbox', o);
        a.dialog.addUIElement('radio', o);
        a.dialog.addUIElement('button', o);
        a.dialog.addUIElement('select', o);
        a.dialog.addUIElement('file', o);
        a.dialog.addUIElement('fileButton', o);
        a.dialog.addUIElement('html', o);
        a.dialog.addUIElement('fieldset', p);
    })();
    j.add('panel', {beforeInit: function (m) {
        m.ui.addHandler('panel', k.panel.handler);
    }});
    a.UI_PANEL = 'panel';
    k.panel = function (m, n) {
        var o = this;
        if (n)e.extend(o, n);
        e.extend(o, {className: '', css: []});
        o.id = e.getNextId();
        o.document = m;
        o._ = {blocks: {}};
    };
    k.panel.handler = {create: function (m) {
        return new k.panel(m);
    }};
    k.panel.prototype = {renderHtml: function (m) {
        var n = [];
        this.render(m, n);
        return n.join('');
    }, render: function (m, n) {
        var p = this;
        var o = p.id;
        n.push('<div class="', m.skinClass, '" lang="', m.langCode, '" role="presentation" style="display:none;z-index:' + (m.config.baseFloatZIndex + 1) + '">' + '<div' + ' id=', o, ' dir=', m.lang.dir, ' role="presentation" class="cke_panel cke_', m.lang.dir);
        if (p.className)n.push(' ', p.className);
        n.push('">');
        if (p.forceIFrame || p.css.length) {
            n.push('<iframe id="', o, '_frame" frameborder="0" role="application" src="javascript:void(');
            n.push(b.isCustomDomain() ? "(function(){document.open();document.domain='" + document.domain + "';" + 'document.close();' + '})()' : '0');
            n.push(')"></iframe>');
        }
        n.push('</div></div>');
        return o;
    }, getHolderElement: function () {
        var m = this._.holder;
        if (!m) {
            if (this.forceIFrame || this.css.length) {
                var n = this.document.getById(this.id + '_frame'), o = n.getParent(), p = o.getAttribute('dir'), q = o.getParent().getAttribute('class'), r = o.getParent().getAttribute('lang'), s = n.getFrameDocument();
                b.iOS && o.setStyles({overflow: 'scroll', '-webkit-overflow-scrolling': 'touch'});
                var t = e.addFunction(e.bind(function (w) {
                    this.isLoaded = true;
                    if (this.onLoad)this.onLoad();
                }, this)), u = '<!DOCTYPE html><html dir="' + p + '" class="' + q + '_container" lang="' + r + '">' + '<head>' + '<style>.' + q + '_container{visibility:hidden}</style>' + e.buildStyleHtml(this.css) + '</head>' + '<body class="cke_' + p + ' cke_panel_frame ' + b.cssClass + '" style="margin:0;padding:0"' + ' onload="( window.CKEDITOR || window.parent.CKEDITOR ).tools.callFunction(' + t + ');"></body>' + '</html>';
                s.write(u);
                var v = s.getWindow();
                v.$.CKEDITOR = a;
                s.on('key' + (b.opera ? 'press' : 'down'), function (w) {
                    var z = this;
                    var x = w.data.getKeystroke(), y = z.document.getById(z.id).getAttribute('dir');
                    if (z._.onKeyDown && z._.onKeyDown(x) === false) {
                        w.data.preventDefault();
                        return;
                    }
                    if (x == 27 || x == (y == 'rtl' ? 39 : 37))if (z.onEscape && z.onEscape(x) === false)w.data.preventDefault();
                }, this);
                m = s.getBody();
                m.unselectable();
                b.air && e.callFunction(t);
            } else m = this.document.getById(this.id);
            this._.holder = m;
        }
        return m;
    }, addBlock: function (m, n) {
        var o = this;
        n = o._.blocks[m] = n instanceof k.panel.block ? n : new k.panel.block(o.getHolderElement(), n);
        if (!o._.currentBlock)o.showBlock(m);
        return n;
    }, getBlock: function (m) {
        return this._.blocks[m];
    }, showBlock: function (m) {
        var r = this;
        var n = r._.blocks, o = n[m], p = r._.currentBlock, q = !r.forceIFrame || c ? r._.holder : r.document.getById(r.id + '_frame');
        if (p) {
            q.removeAttributes(p.attributes);
            p.hide();
        }
        r._.currentBlock = o;
        q.setAttributes(o.attributes);
        a.fire('ariaWidget', q);
        o._.focusIndex = -1;
        r._.onKeyDown = o.onKeyDown && e.bind(o.onKeyDown, o);
        o.show();
        return o;
    }, destroy: function () {
        this.element && this.element.remove();
    }};
    k.panel.block = e.createClass({$: function (m, n) {
        var o = this;
        o.element = m.append(m.getDocument().createElement('div', {attributes: {tabIndex: -1, 'class': 'cke_panel_block', role: 'presentation'}, styles: {display: 'none'}}));
        if (n)e.extend(o, n);
        if (!o.attributes.title)o.attributes.title = o.attributes['aria-label'];
        o.keys = {};
        o._.focusIndex = -1;
        o.element.disableContextMenu();
    }, _: {markItem: function (m) {
        var p = this;
        if (m == -1)return;
        var n = p.element.getElementsByTag('a'), o = n.getItem(p._.focusIndex = m);
        if (b.webkit || b.opera)o.getDocument().getWindow().focus();
        o.focus();
        p.onMark && p.onMark(o);
    }}, proto: {show: function () {
        this.element.setStyle('display', '');
    }, hide: function () {
        var m = this;
        if (!m.onHide || m.onHide.call(m) !== true)m.element.setStyle('display', 'none');
    }, onKeyDown: function (m) {
        var r = this;
        var n = r.keys[m];
        switch (n) {
            case 'next':
                var o = r._.focusIndex, p = r.element.getElementsByTag('a'), q;
                while (q = p.getItem(++o)) {
                    if (q.getAttribute('_cke_focus') && q.$.offsetWidth) {
                        r._.focusIndex = o;
                        q.focus();
                        break;
                    }
                }
                return false;
            case 'prev':
                o = r._.focusIndex;
                p = r.element.getElementsByTag('a');
                while (o > 0 && (q = p.getItem(--o))) {
                    if (q.getAttribute('_cke_focus') && q.$.offsetWidth) {
                        r._.focusIndex = o;
                        q.focus();
                        break;
                    }
                }
                return false;
            case 'click':
            case 'mouseup':
                o = r._.focusIndex;
                q = o >= 0 && r.element.getElementsByTag('a').getItem(o);
                if (q)q.$[n] ? q.$[n]() : q.$['on' + n]();
                return false;
        }
        return true;
    }}});
    j.add('listblock', {requires: ['panel'], onLoad: function () {
        k.panel.prototype.addListBlock = function (m, n) {
            return this.addBlock(m, new k.listBlock(this.getHolderElement(), n));
        };
        k.listBlock = e.createClass({base: k.panel.block, $: function (m, n) {
            var q = this;
            n = n || {};
            var o = n.attributes || (n.attributes = {});
            (q.multiSelect = !!n.multiSelect) && (o['aria-multiselectable'] = true);
            !o.role && (o.role = 'listbox');
            q.base.apply(q, arguments);
            var p = q.keys;
            p[40] = 'next';
            p[9] = 'next';
            p[38] = 'prev';
            p[2228224 + 9] = 'prev';
            p[32] = c ? 'mouseup' : 'click';
            c && (p[13] = 'mouseup');
            q._.pendingHtml = [];
            q._.items = {};
            q._.groups = {};
        }, _: {close: function () {
            if (this._.started) {
                this._.pendingHtml.push('</ul>');
                delete this._.started;
            }
        }, getClick: function () {
            if (!this._.click)this._.click = e.addFunction(function (m) {
                var o = this;
                var n = true;
                if (o.multiSelect)n = o.toggle(m); else o.mark(m);
                if (o.onClick)o.onClick(m, n);
            }, this);
            return this._.click;
        }}, proto: {add: function (m, n, o) {
            var r = this;
            var p = r._.pendingHtml, q = e.getNextId();
            if (!r._.started) {
                p.push('<ul role="presentation" class=cke_panel_list>');
                r._.started = 1;
                r._.size = r._.size || 0;
            }
            r._.items[m] = q;
            p.push('<li id=', q, ' class=cke_panel_listItem role=presentation><a id="', q, '_option" _cke_focus=1 hidefocus=true title="', o || m, '" href="javascript:void(\'', m, "')\" " + (c ? 'onclick="return false;" onmouseup' : 'onclick') + '="CKEDITOR.tools.callFunction(', r._.getClick(), ",'", m, "'); return false;\"", ' role="option">', n || m, '</a></li>');
        }, startGroup: function (m) {
            this._.close();
            var n = e.getNextId();
            this._.groups[m] = n;
            this._.pendingHtml.push('<h1 role="presentation" id=', n, ' class=cke_panel_grouptitle>', m, '</h1>');
        }, commit: function () {
            var m = this;
            m._.close();
            m.element.appendHtml(m._.pendingHtml.join(''));
            delete m._.size;
            m._.pendingHtml = [];
        }, toggle: function (m) {
            var n = this.isMarked(m);
            if (n)this.unmark(m); else this.mark(m);
            return!n;
        }, hideGroup: function (m) {
            var n = this.element.getDocument().getById(this._.groups[m]), o = n && n.getNext();
            if (n) {
                n.setStyle('display', 'none');
                if (o && o.getName() == 'ul')o.setStyle('display', 'none');
            }
        }, hideItem: function (m) {
            this.element.getDocument().getById(this._.items[m]).setStyle('display', 'none');
        }, showAll: function () {
            var m = this._.items, n = this._.groups, o = this.element.getDocument();
            for (var p in m)o.getById(m[p]).setStyle('display', '');
            for (var q in n) {
                var r = o.getById(n[q]), s = r.getNext();
                r.setStyle('display', '');
                if (s && s.getName() == 'ul')s.setStyle('display', '');
            }
        }, mark: function (m) {
            var p = this;
            if (!p.multiSelect)p.unmarkAll();
            var n = p._.items[m], o = p.element.getDocument().getById(n);
            o.addClass('cke_selected');
            p.element.getDocument().getById(n + '_option').setAttribute('aria-selected', true);
            p.onMark && p.onMark(o);
        }, unmark: function (m) {
            var q = this;
            var n = q.element.getDocument(), o = q._.items[m], p = n.getById(o);
            p.removeClass('cke_selected');
            n.getById(o + '_option').removeAttribute('aria-selected');
            q.onUnmark && q.onUnmark(p);
        }, unmarkAll: function () {
            var q = this;
            var m = q._.items, n = q.element.getDocument();
            for (var o in m) {
                var p = m[o];
                n.getById(p).removeClass('cke_selected');
                n.getById(p + '_option').removeAttribute('aria-selected');
            }
            q.onUnmark && q.onUnmark();
        }, isMarked: function (m) {
            return this.element.getDocument().getById(this._.items[m]).hasClass('cke_selected');
        }, focus: function (m) {
            this._.focusIndex = -1;
            if (m) {
                var n = this.element.getDocument().getById(this._.items[m]).getFirst(), o = this.element.getElementsByTag('a'), p, q = -1;
                while (p = o.getItem(++q)) {
                    if (p.equals(n)) {
                        this._.focusIndex = q;
                        break;
                    }
                }
                setTimeout(function () {
                    n.focus();
                }, 0);
            }
        }}});
    }});
    a.themes.add('default', (function () {
        var m = {};

        function n(o, p) {
            var q, r;
            r = o.config.sharedSpaces;
            r = r && r[p];
            r = r && a.document.getById(r);
            if (r) {
                var s = '<span class="cke_shared " dir="' + o.lang.dir + '"' + '>' + '<span class="' + o.skinClass + ' ' + o.id + ' cke_editor_' + o.name + '">' + '<span class="' + b.cssClass + '">' + '<span class="cke_wrapper cke_' + o.lang.dir + '">' + '<span class="cke_editor">' + '<div class="cke_' + p + '">' + '</div></span></span></span></span></span>', t = r.append(h.createFromHtml(s, r.getDocument()));
                if (r.getCustomData('cke_hasshared'))t.hide(); else r.setCustomData('cke_hasshared', 1);
                q = t.getChild([0, 0, 0, 0]);
                !o.sharedSpaces && (o.sharedSpaces = {});
                o.sharedSpaces[p] = q;
                o.on('focus', function () {
                    for (var u = 0, v, w = r.getChildren(); v = w.getItem(u); u++) {
                        if (v.type == 1 && !v.equals(t) && v.hasClass('cke_shared'))v.hide();
                    }
                    t.show();
                });
                o.on('destroy', function () {
                    t.remove();
                });
            }
            return q;
        };
        return{build: function (o, p) {
            var q = o.name, r = o.element, s = o.elementMode;
            if (!r || s == 0)return;
            if (s == 1)r.hide();
            var t = o.fire('themeSpace', {space: 'top', html: ''}).html, u = o.fire('themeSpace', {space: 'contents', html: ''}).html, v = o.fireOnce('themeSpace', {space: 'bottom', html: ''}).html, w = u && o.config.height, x = o.config.tabIndex || o.element.getAttribute('tabindex') || 0;
            if (!u)w = 'auto'; else if (!isNaN(w))w += 'px';
            var y = '', z = o.config.width;
            if (z) {
                if (!isNaN(z))z += 'px';
                y += 'width: ' + z + ';';
            }
            var A = t && n(o, 'top'), B = n(o, 'bottom');
            A && (A.setHtml(t), t = '');
            B && (B.setHtml(v), v = '');
            var C = '<style>.' + o.skinClass + '{visibility:hidden;}</style>';
            if (m[o.skinClass])C = ''; else m[o.skinClass] = 1;
            var D = h.createFromHtml(['<span id="cke_', q, '" class="', o.skinClass, ' ', o.id, ' cke_editor_', q, '" dir="', o.lang.dir, '" title="', b.gecko ? ' ' : '', '" lang="', o.langCode, '"' + (b.webkit ? ' tabindex="' + x + '"' : '') + ' role="application"' + ' aria-labelledby="cke_', q, '_arialbl"' + (y ? ' style="' + y + '"' : '') + '>' + '<span id="cke_', q, '_arialbl" class="cke_voice_label">' + o.lang.editor + '</span>' + '<span class="', b.cssClass, '" role="presentation"><span class="cke_wrapper cke_', o.lang.dir, '" role="presentation"><table class="cke_editor" border="0" cellspacing="0" cellpadding="0" role="presentation"><tbody><tr', t ? '' : ' style="display:none"', ' role="presentation"><td id="cke_top_', q, '" class="cke_top" role="presentation">', t, '</td></tr><tr', u ? '' : ' style="display:none"', ' role="presentation"><td id="cke_contents_', q, '" class="cke_contents" style="height:', w, '" role="presentation">', u, '</td></tr><tr', v ? '' : ' style="display:none"', ' role="presentation"><td id="cke_bottom_', q, '" class="cke_bottom" role="presentation">', v, '</td></tr></tbody></table>' + C + '</span>' + '</span>' + '</span>'].join(''));
            D.getChild([1, 0, 0, 0, 0]).unselectable();
            D.getChild([1, 0, 0, 0, 2]).unselectable();
            if (s == 1)D.insertAfter(r); else r.append(D);
            o.container = D;
            D.disableContextMenu();
            o.on('contentDirChanged', function (E) {
                var F = (o.lang.dir != E.data ? 'add' : 'remove') + 'Class';
                D.getChild(1)[F]('cke_mixed_dir_content');
                var G = this.sharedSpaces && this.sharedSpaces[this.config.toolbarLocation];
                G && G.getParent().getParent()[F]('cke_mixed_dir_content');
            });
            o.fireOnce('themeLoaded');
            o.fireOnce('uiReady');
        }, buildDialog: function (o) {
            var p = e.getNextNumber(), q = h.createFromHtml(['<div class="', o.id, '_dialog cke_editor_', o.name.replace('.', '\\.'), '_dialog cke_skin_', o.skinName, '" dir="', o.lang.dir, '" lang="', o.langCode, '" role="dialog" aria-labelledby="%title#"><table class="cke_dialog', ' ' + b.cssClass, ' cke_', o.lang.dir, '" style="position:absolute" role="presentation"><tr><td role="presentation"><div class="%body" role="presentation"><div id="%title#" class="%title" role="presentation"></div><a id="%close_button#" class="%close_button" href="javascript:void(0)" title="' + o.lang.common.close + '" role="button"><span class="cke_label">X</span></a>' + '<div id="%tabs#" class="%tabs" role="tablist"></div>' + '<table class="%contents" role="presentation">' + '<tr>' + '<td id="%contents#" class="%contents" role="presentation"></td>' + '</tr>' + '<tr>' + '<td id="%footer#" class="%footer" role="presentation"></td>' + '</tr>' + '</table>' + '</div>' + '<div id="%tl#" class="%tl"></div>' + '<div id="%tc#" class="%tc"></div>' + '<div id="%tr#" class="%tr"></div>' + '<div id="%ml#" class="%ml"></div>' + '<div id="%mr#" class="%mr"></div>' + '<div id="%bl#" class="%bl"></div>' + '<div id="%bc#" class="%bc"></div>' + '<div id="%br#" class="%br"></div>' + '</td></tr>' + '</table>', c ? '' : '<style>.cke_dialog{visibility:hidden;}</style>', '</div>'].join('').replace(/#/g, '_' + p).replace(/%/g, 'cke_dialog_')), r = q.getChild([0, 0, 0, 0, 0]), s = r.getChild(0), t = r.getChild(1);
            if (c && !b.ie6Compat) {
                var u = b.isCustomDomain(), v = 'javascript:void(function(){' + encodeURIComponent('document.open();' + (u ? 'document.domain="' + document.domain + '";' : '') + 'document.close();') + '}())', w = h.createFromHtml('<iframe frameBorder="0" class="cke_iframe_shim" src="' + v + '"' + ' tabIndex="-1"' + '></iframe>');
                w.appendTo(r.getParent());
            }
            s.unselectable();
            t.unselectable();
            return{element: q, parts: {dialog: q.getChild(0), title: s, close: t, tabs: r.getChild(2), contents: r.getChild([3, 0, 0, 0]), footer: r.getChild([3, 0, 1, 0])}};
        }, destroy: function (o) {
            var p = o.container, q = o.element;
            if (p) {
                p.clearCustomData();
                p.remove();
            }
            if (q) {
                q.clearCustomData();
                o.elementMode == 1 && q.show();
                delete o.element;
            }
        }};
    })());
    a.editor.prototype.getThemeSpace = function (m) {
        var n = 'cke_' + m, o = this._[n] || (this._[n] = a.document.getById(n + '_' + this.name));
        return o;
    };
    a.editor.prototype.resize = function (m, n, o, p) {
        var v = this;
        var q = v.container, r = a.document.getById('cke_contents_' + v.name), s = b.webkit && v.document && v.document.getWindow().$.frameElement, t = p ? q.getChild(1) : q;
        t.setSize('width', m, true);
        s && (s.style.width = '1%');
        var u = o ? 0 : (t.$.offsetHeight || 0) - (r.$.clientHeight || 0);
        r.setStyle('height', Math.max(n - u, 0) + 'px');
        s && (s.style.width = '100%');
        v.fire('resize');
    };
    a.editor.prototype.getResizable = function (m) {
        return m ? a.document.getById('cke_contents_' + this.name) : this.container;
    };
})();
