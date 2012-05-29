function isAuthenticated() {
    if (	!getCookie("api_key") || 
    		getCookie("uidomainname") != domainName || 
    		!getCookie("authusername") || 
    		getCookie("cmsmodules") == null) {
        return false;
    }
    return true;
}
if (!isAuthenticated()) {
    Ext.onReady(function () {
        var sessionWindow = new Ext.Window({
            title: "Session Expired",
            modal: true,
            closable: false,
            width: 300,
            height: 150,
            html: "<H2 align=center>Please click OK to re-login.</H2>",
            //items : [{html:"Please login to continue."}], 
            buttons: [{
                text: 'OK',
                id: 'session-ok',
                handler: function () {
		        loginRedirection();
		        sessionWindow.close();
                }
            }],
            keys: [{
                key: [Ext.EventObject.ENTER],
                handler: function () {
		        loginRedirection();
		        sessionWindow.close();
                }
            }, {
                key: [Ext.EventObject.ESC],
                handler: function () {
		        loginRedirection();
		        sessionWindow.close();
                }
            }]
        });
        sessionWindow.show();
    });
} else {
    myMask = new Ext.LoadMask(Ext.getBody(), {
        msg: "Loading please wait..."
    });
    myMask.show();
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/jquery.min.js><\/script>");
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/commonmenu.js?" + buildVersion + "><\/script>");
    //document.write("<script type=text/javascript src=/ncmsdata/" + domainName + "/menu.js?" + rNum + "><\/script>");
    document.write("<link rel='stylesheet' type='text/css' href=/dataservices/app/" + domainName + "/static/stages.css?" + rNum + "/>");
    document.write("<script type=text/javascript src=/dataservices/app/" + domainName + "/static/menu.js?" + rNum + "><\/script>");
    //document.write("<script type=text/javascript src=/dataservices/app/" + domainName + "/static/stages.css?" + rNum + "><\/script>");    
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/library-all.js></script?" + buildVersion + "><\/script>");
    /*document.write("<script type=text/javascript src=/ncmsui/extjs/js/modelnew.js?" + buildVersion + "><\/script>");*/
    document.write("<script type=text/javascript src=/context/" + domainName + "/resources/js/modelnew.js?" + buildVersion + "><\/script>");
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/dnd-sortable.js?" + buildVersion + "><\/script>");
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/image-viewer.js?" + buildVersion + "><\/script>");
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/swfobject.js?" + buildVersion + "><\/script>");
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/auto-gen-preincludes.js?" + buildVersion + "><\/script>");
    //document.write("<script type=text/javascript src=/ncmsui/extjs/js/autogen-all.js?" + buildVersion + "><\/script>");
    document.write("<script type=text/javascript src=/context/" + domainName + "/resources/js/autogen-all.js?" + buildVersion + "><\/script>");
    document.write("<script type=text/javascript src=/ncmsui/extjs/js/auto-gen-postincludes.js?" + buildVersion + "><\/script>");
}
