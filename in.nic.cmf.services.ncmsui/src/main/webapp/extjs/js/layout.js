var wdata = "";
var apikey = parent.window.opener.getCookie("api_key");
var authuser = parent.window.opener.getCookie("authusername");
var aclrole = parent.window.opener.getCookie("aclrole");
var domainname = parent.window.opener.getDomainName();
var hostdet = parent.window.opener.getHost();

function setCookie(name, value, expires, path, domain, secure) {
    // set time, it's in milliseconds
    var today = new Date();
    today.setTime(today.getTime());

    /*
        if the expires variable is set, make the correct
        expires time, the current script below will set
        it for x number of days, to make it for hours,
        delete * 24, for minutes, delete * 60 * 24
        */
    if (expires) {
        expires = expires * 1000 * 60 * 60 * 24;
    }
    var expires_date = new Date(today.getTime() + (expires));

    document.cookie = name + "=" + escape(value) + ((expires) ? ";expires=" + expires_date.toGMTString() : "") + ((path) ? ";path=" + path : "") + ((domain) ? ";domain=" + domain : "") + ((secure) ? ";secure" : "");
}
setCookie("authusername", authuser, '', '/', '', '');
setCookie("aclrole", aclrole, '', '/', '', '');
setCookie("api_key", apikey, '', '/', '', '');
start();

function isJSLoaded(jspath) {
    var script = document.getElementsByTagName("script");
    var scrlen = script.length;
    for (var j = 0; j < scrlen; ++j) {
        if (script[j].getAttribute("src") === jspath) {
            return true;
        }
    }
    return false;
}

function start() {
    $.ajaxSetup({
        async: false
    });

    var frametext = window.opener.getContent();
    // alert(frametext);
    if (typeof (frametext.split("<head")[1]) != "undefined") {
        var headcont = frametext.split("<head")[1].split(">").slice(1).join(">").split("</head>")[0];
        //headcont = headcont.replace(/\<script/g , "&ltscript").replace(/<\/script>/g,"&lt;/script&gt;");
    }
    if (typeof (frametext.split("<body")[1]) != "undefined") {
        var bodycont = frametext.split("<body")[1].split(">").slice(1).join(">").split("</body>")[0];
        /* var re = /<script\b[^>]*>([\s\S]*?)<\/script>/gm;
           if(bodycont.match(re)){
             alert("Script Tag Found in Body Content");
             bodycont = bodycont.replace(/\<script/g , "_START SCRIPT_").replace(/<\/script>/g,"_END SCRIPT_");
           }*/
        //  bodycont = bodycont.replace(/\<script/g , "&ltscript").replace(/<\/script>/g,"&lt;/script&gt;");
    }
    var frame = $('<iframe id="layoutiframe" width="100%" height="800" marginwidth="0" marginheight="0" frameborder="1" scrolling="auto" resizable="yes" onLoad="getFramefunc();">');
    $('div#contentouter').html(frame);
    setTimeout(function () {
        var doc = frame[0].contentWindow.document;
        if (typeof (headcont) != "undefined") {
            var $head = $('head', doc);
            $head.html(headcont);
        }
        if (typeof (bodycont) != "undefined") {
            var $body = $('body', doc);
            $body.html(bodycont);
        }
        if (typeof (headcont) == "undefined" && typeof (bodycont) == "undefined") {
            var $cont = $('body', doc);
            $cont.html(frametext);
        }
    }, 1000);


    getWidgetCode();
    /*$('#layoutiframe').load(function(){
         var frameBody = $('#layoutiframe').contents().find('body');
         frameBody.delegate('div', 'mouseover', function() {
              $(this).css("background-color", "#66ccff");
          });
         frameBody.delegate('div', 'mouseout', function() {
              $(this).css("background-color", "");
          });
         frameBody.delegate('div', 'click', function() {
              $(this).addClass("bincss");
              if(typeof($('input:radio[name=widgetname]:checked').val()) != "undefined"){
                   $(this).html('');
              }
              $(this).text($('input:radio[name=widgetname]:checked').val());
         });
    });*/
}

function getFramefunc() {
    var frameBody = $('#layoutiframe').contents().find('body');
    frameBody.delegate('div', 'mouseover', function () {
        $(this).css("background-color", "#66ccff");
    });
    frameBody.delegate('div', 'mouseout', function () {
        $(this).css("background-color", "");
    });
    frameBody.delegate('div', 'click', function () {
        $(this).addClass("bincss");
        if (typeof ($('input:radio[name=widgetname]:checked').val()) != "undefined") {
            $(this).html('');
        }
        $(this).text($('input:radio[name=widgetname]:checked').val());
    });
}

function getwidget(data) {
    wdata = data;
}

function getWidgetCode() {
    $.ajaxSetup({
        async: false
    });
    //   $.ajaxSetup({ cache: true });
    $.getScript(hostdet + "/dataservices/app/" + domainname + "/search?q=&entitytype=widget&format=extjson&limit=100&status=published&callback=getwidget", function (data) {
        var retdata = "";
        var widgetObj = wdata.Collections.Widget;
        var widgetCount = wdata.Collections.Count;
        if (widgetCount > 1) {
            for (var i = 0; i < wdata.Collections.Widget.length; i++) {
                retdata += "<input type='radio' name='widgetname' id='id" + i + "' value='" + widgetObj[i].WidgetCode + "'>" + widgetObj[i].WidgetName + "</input><br/><br/>";
            }
        } else {
            retdata += "<input type='radio' name='widgetname' id='id1' value='" + widgetObj.WidgetCode + "'>" + widgetObj.WidgetName + "</input><br/><br/>";
        }
        $('div#gethtmlbut').append("<div style='width:100%;float:left;'>" + retdata + "</div>");
        // parent.window.opener.setCookie("authusername",authuser, '', '/', '', '' );
        // parent.window.opener.setCookie("aclrole",aclrole, '', '/', '', '' );
        // parent.window.opener.setCookie("api_key",apikey, '', '/', '', '' );
    });
}

function gethtml() {
    
    var txt = $('#layoutiframe').contents().find('html').html();
    //txt = HTMLtoXML(txt);
    var siteUrl = window.opener.getStagingSiteUrl();
    txt = txt.replace(/<[^>]*>/g, function (match) {
        return match
    });
    txt = txt.replace(/<span class="apple-style-span">(.*)<\/span>/g, '$1');
    txt = txt.replace(/ class="apple-style-span"/g, '');
    txt = txt.replace(/<span style="">/g, '');
    txt = txt.replace(/<br>/g, '<br />');
    txt = txt.replace(/<br ?\/?>$/g, '');
    txt = txt.replace(/^<br ?\/?>/g, '');
    txt = txt.replace(/(<img [^>]+[^\/])>/gi, '$1 />');
    txt = txt.replace(/(<meta [^>]+[^\/])>/gi, '$1 />');
    txt = txt.replace(/(<link [^>]+[^\/])>/gi, '$1 />');
    txt = txt.replace(/<b\b[^>]*>(.*?)<\/b[^>]*>/g, '<strong>$1</strong>');
    txt = txt.replace(/<i\b[^>]*>(.*?)<\/i[^>]*>/g, '<em>$1</em>');
    txt = txt.replace(/<u\b[^>]*>(.*?)<\/u[^>]*>/g, '<span style="text-decoration:underline">$1</span>');
    txt = txt.replace(/<(b|strong|em|i|u) style="font-weight: normal;?">(.*)<\/(b|strong|em|i|u)>/g, '$2');
    txt = txt.replace(/<(b|strong|em|i|u) style="(.*)">(.*)<\/(b|strong|em|i|u)>/g, '<span style="$2"><$4>$3</$4></span>');
    txt = txt.replace(/<span style="font-weight: normal;?">(.*)<\/span>/g, '$1');
    txt = txt.replace(/<span style="font-weight: bold;?">(.*)<\/span>/g, '<strong>$1</strong>');
    txt = txt.replace(/<span style="font-style: italic;?">(.*)<\/span>/g, '<em>$1</em>');
    txt = txt.replace(/<span style="font-weight: bold;?">(.*)<\/span>|<b\b[^>]*>(.*?)<\/b[^>]*>/g, '<strong>$1</strong>')
    dom = $(txt);

    dom.filter('script').each(function (i, item) {
        var scriptsrc = $(item).attr("src");
        if (typeof (scriptsrc) != "undefined" && scriptsrc != "") {
            if (scriptsrc.indexOf('http') > -1) {
                txt = txt.replace(siteUrl, "");
            }
        }
    });

    dom.filter('link').each(function (i, item) {
        var linkhref = $(item).attr("href");
        if (typeof (linkhref) != "undefined" && linkhref != "") {
            if (linkhref.indexOf('http') > -1) {
                txt = txt.replace(siteUrl, "");
            }
        }
    });

    dom.find('img').each(function (i, item) {
        var imgsrc = $(item).attr("src");
        $(item).attr("src", imgsrc + i);
        if (typeof (imgsrc) != "undefined" && imgsrc != "") {
            if (imgsrc.indexOf('http') > -1) {
                txt = txt.replace(siteUrl, "");
            }
        }
    });

    dom.find('a').each(function (i, item) {
        var ahref = $(item).attr("href");
        if (typeof (ahref) != "undefined" && ahref != "") {
            if (ahref.indexOf('http') > -1) {
                txt = txt.replace(siteUrl, "");
            }
        }
    });
    $('textarea[name="Content"]', opener.document).val('');
    $('textarea[name="Content"]', opener.document).val("<html>" + txt + "</html>");
    window.close();
}
