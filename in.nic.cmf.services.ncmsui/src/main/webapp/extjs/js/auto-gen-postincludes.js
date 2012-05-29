Ext.require(['NcmsUIModel', 'Ext.ux.tree.TreeEditing']);

/**
 * Global variable declaration starts here 
 */

var pageIaPath = '';
var cmsSearchText;
var pmsSearchText;
var userSearchText;
var feedSearchText;
var domainSearchText;
var workflowSearchText;
var adminSearchText;
var queryField = '';
var selectedEntity = '';
var selectedIaPath = '';
var pageAssoObject = '';

/**
 * Global variable declaration Ends here 
 */

var wfStageStore = createStore('Stage', '');
wfStageStore.load();
var stageColorHash = new Ext.util.HashMap(); // hashmap for workflowstage colorcode  

/**
 * This event fires whenever records have been prefetched and it adds colorcode with stage name to hashmap predefined  
 */

wfStageStore.on('load', function (store, records, successful, operation, eOpts) {
    if(!Ext.isEmpty(records)){
	for (var i = 0; i < records.length; i++) {
	    stageColorHash.add(records[i].data.StageName, records[i].data.ColorCode);
	}
    }
}, this);

/**
 * Page form declaration
 */

var PageForm = [{
    title: 'Page',
    xtype: 'form',
    id: 'PageForm1',
    autoScroll: true,
    fieldDefaults: {
        labelWidth: 155
    },
    items: [{
        name: "Id",
        id: "PageId",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        minLength: 13,
        maxLength: 13,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Page Id",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        vtype: "alphanumMid",
        hidden: true,
        allowBlank: true

    },

    {
        name: "EntityType",
        id: "PageEntityType",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Entity Type",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        value: "Page",
        allowBlank: true
    },

    {
        name: "PageName",
        id: "PagePageName",
        maxLength: 50,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Page Name <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        vtype: "alphanumMid",
        allowBlank: false

    }, {
        name: "PageExtension",
        id: "PagePageExtension",
        fieldLabel: "Page Extension <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        xtype: "combobox",
        store: createStore('Format', ''),
        queryMode: "remote",
	typeAhead : true,
	minChars:1,
	// forceSelection: true,
	triggerAction: 'all',
        displayField: "FileExtension",
        valueField: "FileExtension",
        emptyText: "",
        allowBlank: false,
        listeners: {
            render: function (combo, options) {
                this.store.sort('FileExtension', 'ASC');
            },
	    expand : function(field, eOpts){
		field.queryMode = "local";
	    },
	    keypress : function(combo, e, eOpts){
	      if((e.getKey() >= 65 && e.getKey() <= 90) 
		|| (e.getKey() >= 97 && e.getKey() <= 122)
		|| (e.getKey() == Ext.EventObject.ENTER)
		|| (e.getKey() == Ext.EventObject.BACKSPACE)
		) {
		combo.collapse();
		if(Ext.isEmpty(combo.getValue())){
		  combo.forceSelection=true;
		  combo.store.clearFilter(true);
		  combo.store.load();
		  combo.expand();
		}
	      }
	    },
	    focus : function(combo){
		combo.forceSelection=true;
		combo.store.clearFilter(true);
		combo.store.load();
	    }
        }
    }, {
        xtype: 'combobox',
        allowBlank: false,
	typeAhead : true,
	minChars:1,
	// forceSelection: true,
	triggerAction: 'all',
        fieldLabel: "Template Name <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        name: 'TemplateName',
        displayField: 'TemplateName',
        valueField: 'TemplateName',
        id: 'PageTemplateName',
        queryMode: 'remote',
        scope: this,
        store: createPreloadedStore('Template'),
        listeners: {
            select: function (combo, selection) {
                var post = selection[0];
                layoutTemplatePath = post.get('FilePath');
                var pmstemplatecontent = Ext.getCmp("pmstemplatecontent");
                Ext.MessageBox.wait('Getting data...');
                Ext.Ajax.request({
                    method: 'GET',
                    // url: 'http://localhost/fileupload.html',
                    url: mediaPath + layoutTemplatePath,
                    async: false,
                    defaultHeaders: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    },
                    success: function (response, opts) { // function
                        // called
                        // on
                        // success
                        Ext.MessageBox.hide();
                        pmstemplatecontent.setValue(response.responseText);
                        layoutTemplatePath = "";
                    },
                    failure: function (response, opts) {
                        pmstemplatecontent.setValue("");
                        Ext.MessageBox.hide();
                        Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>Record not found.</div>");
                    }
                    // params : newRec.data
                });
            },
            render: function (combo, options) {
                this.store.sort('TemplateName', 'ASC');
            },
	    expand : function(field, eOpts){
		  field.queryMode = "local";
	    },
	    keypress : function(combo, e, eOpts){
	      if((e.getKey() >= 65 && e.getKey() <= 90) 
		|| (e.getKey() >= 97 && e.getKey() <= 122)
		|| (e.getKey() == Ext.EventObject.ENTER)
		|| (e.getKey() == Ext.EventObject.BACKSPACE)
		) {
		combo.collapse();
		if(Ext.isEmpty(combo.getValue())){
		  combo.forceSelection=true;
		  combo.store.clearFilter(true);
		  combo.store.load();
		  combo.expand();
		}
	      }
	    },
	    focus : function(combo){
		combo.forceSelection=true;
		combo.store.clearFilter(true);
		combo.store.load();
	    }
        }
    },

    {
        name: "WorkflowId",
        id: "PageWorkflowInfoWorkflowId",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Workflow Id",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "WorkflowName",
        id: "PageWorkflowInfoWorkflowName",
        fieldLabel: "Choose Workflow <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        xtype: "combobox",
        store: createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
        queryMode: "remote",
        displayField: "WorkflowName",
        valueField: "WorkflowName",
        emptyText: "",
	typeAhead : true,
	minChars:1,
     //	forceSelection: true,
	triggerAction: 'all',
        listeners: {
            select: function () {
                var v = this.getValue();
                var i = this.store.findExact(this.valueField || this.displayField, v);
                if (typeof (Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != "") {
                    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
                    isTopLevel.setValue("false");
                }
                if (v != "") {
                    var pId = Ext.getCmp('PageWorkflowInfoWorkflowId');
                    pId.setValue(this.store.getAt(i).get('Id'));
                }
            },
            render: function (combo, options) {
                this.store.sort('WorkflowName', 'ASC');
            },
	    expand : function(field, eOpts){
		  field.queryMode = "local";
	    },
	    keypress : function(combo, e, eOpts){
	      if((e.getKey() >= 65 && e.getKey() <= 90) 
		|| (e.getKey() >= 97 && e.getKey() <= 122)
		|| (e.getKey() == Ext.EventObject.ENTER)
		|| (e.getKey() == Ext.EventObject.BACKSPACE)
		) {
		combo.collapse();
		if(Ext.isEmpty(combo.getValue())){
		  combo.forceSelection=true;
		  combo.store.clearFilter(true);
		  combo.store.load();
		  combo.expand();
		}
	      }
	    },
	    focus : function(combo){
		combo.forceSelection=true;
		combo.store.clearFilter(true);
		combo.store.load();
	    }
        },
        allowBlank: false

    },

    {
        xtype: 'textarea',
        fieldLabel: "Content <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        name: 'Content',
        anchor: '100%',
        id: 'pmstemplatecontent',
        height: '88%',
        hidden: false,
        allowBlank: false
    }, {
        name: "Status",
        id: "PageStatus",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Status",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "CreatedDate",
        id: "PageCreatedDate",
        editable: false,
        fieldLabel: "Created Date",
        xtype: "datetimefield",
        listeners: {
            render: function () {
                var date = new Date();
                if (this.getValue() == null || this.getValue() == "") {
                    this.setValue(date);
                }
            }
        },
        hidden: true,
        allowBlank: false

    },

    {
        name: "LastModifiedDate",
        id: "PageLastModifiedDate",
        editable: false,
        fieldLabel: "Last Modified Date",
        xtype: "datetimefield",
        listeners: {
            render: function () {
                var date = new Date();
                this.setValue(date);
            }
        },
        hidden: true,
        allowBlank: false

    },

    {
        name: "CreatedBy",
        id: "PageCreatedBy",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Created By",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        listeners: {
            render: function () {
                if (this.getValue() == "") {
                    this.setValue(cmsUserName);
                }
            }
        },
        allowBlank: true

    },

    {
        name: "LastModifiedBy",
        id: "PageLastModifiedBy",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Last Modified By",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        listeners: {
            render: function () {
                this.setValue(cmsUserName);
            }
        },
        allowBlank: true

    },

    {
        name: "CurrentUser",
        id: "PageWorkflowInfoCurrentUser",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Current User",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "CurrentAction",
        id: "PageWorkflowInfoCurrentAction",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Current Event",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    }, {
        name: "Stage",
        id: "PageWorkflowInfoStage",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Stage",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    }, {
        name: "WorkflowComments",
        id: "PageWorkflowInfoWorkflowComments",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Workflow Comments",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "Site",
        id: "PageSite",
        readOnly: true,
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Site",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "Version",
        id: "PageVersion",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        enforceMaxLength: true,
        xtype: "textfield",
        fieldLabel: "Version",
        listeners: {
            blur: function () {
                var str = trim(this.getValue());
                str = str.replace(/ +(?= )/g, '');
                this.setValue(str);
            }
        },
        hidden: true,
        allowBlank: true

    }, {
        xtype: 'toolbar',
        items: [
        // Ext.create("Ext.button.Button", {
        {
            xtype: 'button',
            text: 'Get Layout',
            handler: function () {
                getHtml();
            }
        },
        // }),
        // Ext.create("Ext.button.Button", {
        {
            xtype: 'button',
            text: 'Page Preview',
            handler: function () {
                getPage();
            }
        }
        // })
	  
	]
    }]
}];

/*
 * 
 * Tree page Associator
 */

Ext.define('PageAssoModel', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'treeitem',
        type: 'string'
    }, ]
});

/**
 * Store reference for page associator IA with predefined configuration 
 * to load generated Information Architecture json data.
 */

var pageAssoIaStore = Ext.create('Ext.data.TreeStore', {
    model: 'PageAssoModel',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        noCache: false,
        // the store will get the content from the .json file
        //url: apacheNcmsData + '/InformationArchitectureTree.json'
        url: domainSpecificMasterDataUrl + '/InformationArchitectureTree.json'
    },
    folderSort: true
});

/**
 * * Store reference for page associator Entity with predefined configuration 
 * to load generated Entity tree grid json data.
 */

var pageAssoEntityStore = Ext.create('Ext.data.TreeStore', {
    model: 'PageAssoModel',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        noCache: false,
        url: dataPath + '/entitytreegrid.json'
    },
    folderSort: true
});

/**
 *  Page associator form window left side IA tree grid declartion
 */

var pageAssoIaTree = Ext.create('Ext.tree.Panel', {
    region: 'west',
    expanded: true,
    padding: 1,
    width: 340,
    //height : 300,
    useArrows: true,
    rootVisible: false,
    store: pageAssoIaStore,
    autoScroll: true,
    hideHeaders: true,
    singleExpand: false,
    frame: false,
    stripeRows: false,
    forceFit: true,
    selType: 'cellmodel',
    columns: [{
        xtype: 'treecolumn',
        // this is so we know which column will show the
        // tree
        text: 'Item',
        sortable: false,
        dataIndex: 'treeitem'
    }]
});
/**
 *  Page associator form window right side Entity tree grid declartion
 */
var pageAssoEntityTree = Ext.create('Ext.tree.Panel', {
    region: 'east',
    expanded: true,
    padding: 1,
    width: 340,
    //height : 300,
    useArrows: true,
    rootVisible: false,
    store: pageAssoEntityStore,
    autoScroll: true,
    hideHeaders: true,
    singleExpand: false,
    frame: false,
    stripeRows: false,
    forceFit: true,
    selType: 'cellmodel',
    columns: [{
        xtype: 'treecolumn',
        // this is so we know which column will show the
        // tree
        text: 'Item',
        sortable: false,
        dataIndex: 'treeitem'
    }]
});
/**
 *  Page associator page name combobox declartion
 */
var pageNameCombo = Ext.create('Ext.form.field.ComboBox', {
    fieldLabel: "Assigned To <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
    name: 'PageName',
    displayField: 'PageName',
    valueField: 'PageName',
    allowBlank: false,
    overflow: 'auto',
    typeAhead : true,
    minChars:1,
    forceSelection: false,
    triggerAction: 'all',
    queryMode: 'remote',
    store: createPreloadedStore('Page', ''),
    listeners: {
        render: function (combo, options) {
            this.store.sort('PageName', 'ASC');
        },
	expand : function(field, eOpts){
	    field.queryMode = "local";
	},
	keypress : function(combo, e, eOpts){
	  if((e.getKey() >= 65 && e.getKey() <= 90) 
	    || (e.getKey() >= 97 && e.getKey() <= 122)
	    || (e.getKey() == Ext.EventObject.ENTER)
	    || (e.getKey() == Ext.EventObject.BACKSPACE)
	    ) {
	    combo.collapse();
	    if(Ext.isEmpty(combo.getValue())){
	      combo.forceSelection=true;
	      combo.store.clearFilter(true);
	      combo.store.load();
	      combo.expand();
	    }
	  }
	},
	focus : function(combo){
	    combo.forceSelection=true;
	    combo.store.clearFilter(true);
	    combo.store.load();
	}
    }
});
/**
 *  Page associator workflow combobox declartion
 */
var pageAssoWorkflowName = Ext.create('Ext.form.field.ComboBox', {
    name: "WorkflowName",
    id: "PageAssociatorWorkflowInfoWorkflowName",
    fieldLabel: "Choose Workflow <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
    store: createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
    queryMode: "remote",
    typeAhead : true,
    minChars:1,
    enableKeyEvents:true,
  //  forceSelection: true,
    triggerAction: 'all',
    displayField: "WorkflowName",
    valueField: "WorkflowName",
    scope: pageFormPanel,
    emptyText: "",
    listeners: {
        select: function () { 
            var v = this.getValue();
            var i = this.store.findExact(this.valueField || this.displayField, v);
            if (v != "") {
                var wId = pageFormPanel.getForm().findField("PageAssociatorWorkflowInfoWorkflowId");
                wId.setRawValue(this.store.getAt(i).get('Id'));
            }
        },
        render: function (combo, options) { 
            this.store.sort('WorkflowName', 'ASC');
        },
	expand : function(field, eOpts){ 
	    field.queryMode = "local";
	}, 
	keypress : function(combo, e, eOpts){
	  if((e.getKey() >= 65 && e.getKey() <= 90) 
	    || (e.getKey() >= 97 && e.getKey() <= 122)
	    || (e.getKey() == Ext.EventObject.ENTER)
	    || (e.getKey() == Ext.EventObject.BACKSPACE)
	    ) {
	    combo.collapse();
	    if(Ext.isEmpty(combo.getValue())){
	      combo.forceSelection=true;
	      combo.store.clearFilter(true);
	      combo.store.load();
	      combo.expand();
	    }
	  }
	},
	focus : function(combo){
	    combo.forceSelection=true;
	    combo.store.clearFilter(true);
	    combo.store.load();
	}
    },
    allowBlank: false
});

/**
 * Page associator IA tree itme click event handler
 */

pageAssoIaTree.on('itemclick', function (view, record, htmlelement, index, e) {
    pageNameCombo.setValue('');
    pageAssoWorkflowId.setValue('');
    pageAssoWorkflowName.setValue('');
    pageAssoEntityTree.getView().refresh();
    if (record.data.treeitem != 'InformationArchitecture') {
        queryField = 'ianame';
        selectedEntity = '';
        selectedIaName = record.data.id;
        pageFormPanel.setTitle('Choose Workflow & Assign Page For InformationArchitecture');
        var o = {};
        o[queryField] =  record.data.id;
        pageFormPanel.getEl().mask();
        Ext.Ajax.request({
            method: 'GET',
            url: dataservicesPath + '/pageassociator/search?q=&format=extjson',
            success: function (response, opts) {
                pageFormPanel.getEl().unmask();
                pageAssoObject = {};
                var resp = Ext.JSON.decode(response.responseText);
                if (resp.Collections.PageAssociator) {
                    pageAssoWorkflowName.setRawValue(resp.Collections.PageAssociator.WorkflowName);
                    pageAssoWorkflowId.setRawValue(resp.Collections.PageAssociator.WorkflowId);
                    pageNameCombo.setRawValue(resp.Collections.PageAssociator.PageName);
                    pageAssoObject = resp.Collections.PageAssociator;
                }
                /*else{
										    pageNameCombo.setValue('');
										    pageAssoWorkflowId.setValue('');
										    pageAssoWorkflowName.setValue('');
										}
										pageNameCombo.store.load();*/
            },
            failure: function (response, opts) {
                pageFormPanel.getEl().unmask();
                Ext.MessageBox.hide();
                if (response.responseText) {
                    Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + getCustomErrorInfo(response.responseText) + "</div>");
                } else {
                    Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + response.statusText + "</div>");
                }
            },
            params: o
        });
    }
}, pageAssoIaTree);

/**
 * Page associator entity tree itme click event handler
 */

pageAssoEntityTree.on('itemclick', function (view, record, htmlelement, index, e) {
    pageNameCombo.setValue('');
    pageAssoWorkflowId.setValue('');
    pageAssoWorkflowName.setValue('');
    pageAssoIaTree.getView().refresh();
    if (record.data.treeitem != 'Entities') {
        pageFormPanel.setTitle('Choose Workflow & Assign Page For Entity');
        selectedEntity = record.data.treeitem;
        selectedIaName = '';
        queryField = 'pageentityname';
        var o = {};
        o[queryField] = record.data.id;
        pageFormPanel.getEl().mask();
        Ext.Ajax.request({
            method: 'GET',
            url: dataservicesPath + '/pageassociator/search?q=&format=extjson',
            success: function (response, opts) {
                pageFormPanel.getEl().unmask();
                pageAssoObject = {};
                var resp = Ext.JSON.decode(response.responseText);
                if (resp.Collections.PageAssociator) {
                    pageAssoWorkflowName.setRawValue(resp.Collections.PageAssociator.WorkflowName);
                    pageAssoWorkflowId.setRawValue(resp.Collections.PageAssociator.WorkflowId);
                    pageNameCombo.setRawValue(resp.Collections.PageAssociator.PageName);
                    pageAssoObject = resp.Collections.PageAssociator;
                }
                /*else{
										    pageNameCombo.setValue('');
										    pageAssoWorkflowId.setValue('');
										    pageAssoWorkflowName.setValue('');
										}
										pageNameCombo.store.load();*/
            },
            failure: function (response, opts) {
                pageFormPanel.getEl().unmask();
                Ext.MessageBox.hide();
                if (response.responseText) {
                    Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + getCustomErrorInfo(response.responseText) + "</div>");
                } else {
                    Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + response.statusText + "</div>");
                }
            },
            params: o
        });
    }
}, pageAssoEntityTree);

/**
 * Page associator workflow id field declaration
 */
var pageAssoWorkflowId = Ext.create('Ext.form.field.Text', {
    name: "WorkflowId",
    id: "PageAssociatorWorkflowInfoWorkflowId",
    readOnly: true,
    fieldCls: 'readonly-background-class',
    enforceMaxLength: true,
    fieldLabel: "Workflow Id",
    listeners: {
        blur: function () {
            var str = trim(this.getValue());
            str = str.replace(/ +(?= )/g, '');
            this.setValue(str);
        }
    },
    hidden: true,
    allowBlank: true

});
/**
 * Page associator assign button field declaration
 */
var pageAssignButton = Ext.create('Ext.Button', {
    padding: 3,
    formBind: true,
    text: 'Assign',
    handler: function () {
        var dataObject = {
            Site: pageAssoObject.Site,
            Id: pageAssoObject.Id,
            EntityType: 'PageAssociator',
            PageEntityName: selectedEntity,
            PageName: pageNameCombo.getValue(),
            IaName: selectedIaName,
            Status: pageAssoObject.Status != null ? pageAssoObject.Status : "Draft",
            CreatedDate: pageAssoObject.CreatedDate != null ? pageAssoObject.CreatedDate : formatDate(new Date(), "Y-m-d H:i:s"),
            CreatedBy: pageAssoObject.CreatedBy != null ? pageAssoObject.CreatedBy : cmsUserName,
            LastModifiedDate: formatDate(new Date(), "Y-m-d H:i:s"),
            LastModifiedBy: cmsUserName,
            Version: '',
            AllowedActions: pageAssoObject.AllowedActions,
            CurrentUser: pageAssoObject.CurrentUser,
            CurrentAction: pageAssoObject.CurrentAction,
            Stage: pageAssoObject.Stage,
            WorkflowId: pageAssoWorkflowId.getValue(),
            WorkflowName: pageAssoWorkflowName.getValue()
        };
        pageFormPanel.getEl().mask();
        Ext.Ajax.request({
            method: 'POST',
            url: dataservicesPath + '/pageassociator',
            defaultHeaders: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            success: function (response, opts) {
                pageFormPanel.getEl().unmask();
                var resp = Ext.JSON.decode(response.responseText);
                if (resp.success) {
                    Ext.DropDownAlert.msg("<div class = 'successHeaderMsg'>Success</div>", "<div class = 'successMsg'> Page assigned successfully.</div>");
                }
                pageAssignButton.setDisabled(true);
            },
            failure: function (response, opts) {
                pageFormPanel.getEl().unmask();
                var actionString = response.responseText;
                var actionObj = Ext.JSON.decode(actionString);
                if (!Ext.isEmpty(actionObj.msg)) {
                    // pmsErrorPanel.update("<div class
                    // = 'errorHeaderMsg'>" +
                    // getCustomErrorInfo(Ext.JSON.encode(actionObj.msg))
                    // + "</div>");
                    Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Can't Assign</div>", "<div class = 'errorMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
                } else {
                    // pmsErrorPanel.update("<div class
                    // = 'errorHeaderMsg'>" +
                    // action.response.responseText +
                    // "</div>");
                    Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Can't Assign</div>", "<div class = 'errorMsg'>" + response.statusText + "</div>");
                }

            },
            params: dataObject
        });
    }
});

/**
 * Page form panel 
 */

var pageFormPanel = Ext.create('Ext.form.Panel', {
    frame: true,
    title: 'Assign Page For',
    width: 340,
    layoutConfig: {
        type: 'vbox',
        align: 'stretch',
        pack: 'start'
    },
    fieldDefaults: {
        labelAlign: 'right',
        labelWidth: 90
    },
    region: 'south',
    height: 120,
    items: [pageAssoWorkflowId, pageAssoWorkflowName, pageNameCombo, pageAssignButton]
});

/**
 * Page Associator form panel 
 */

var PageAssociatorForm = [{
    title: "PageAssociator",
    xtype: 'panel',
    layout: 'border',
    autoScroll: true,
    border: false,
    items: [pageAssoIaTree, pageAssoEntityTree,
    {
        region: 'center',
        width: 2
    },
    pageFormPanel]
}];

/**
 * Pms left panel definition 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);
Ext.define('NIC.PMSLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.pmsleftpanel',

    pmsitementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        layout: 'fit',
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            autoLoad: false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=pms&rolename='
                // + userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            autoHeight: true,
            autoScroll: true,
            width: 250,
            scroll: 'both',
            items: [this.pmsitementities]
        });
        this.callParent();
        this.pmsitementities.on('load', function (store, records, successful) {
            this.pmsitementities.expand(true);
            this.doLayout();
        }, this);

    }
});

/**
 * Pms right panel definition 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.PMSRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.pmsrightpanel',
    menu: showFilterByMenu('pms'),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'pms-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                reader: {
                    type: 'json',
                    root: 'Collections.Template',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]

        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (view, record, node, index, e) {
                    contextMenu("pms", view, record, node, index, e);
                    /*
                     * e.stopEvent(); view.getSelectionModel().select(record);
                     * var cMenu = contextMenu(record,"domain"); if(cMenu)
                     * cMenu.showAt(e.getXY()); return false;
                     */
                }
            },
            getRowClass: function (record, index, rowParams, ds) {
                if (record.data.Stage && applyStageColor) {
                    return record.data.Stage + '-row-background-class';
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) pmsSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = pmsSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('pms-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus'
    }),
    menuBar: Ext.create('Ext.button.Button', {
        text: 'Filter By',
        disabled: true,
        iconCls: 'icon-domains-entities'
    }),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [{
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            items: [this.grid]
        });
        this.callParent();
    }
});

/**
 * Pms main panel definition 
 */

var pmsCurrentEntity = '';
var currentColumn = [];
Ext.define('NIC.PMSMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.pmsmainpanel',
    pmsLeftPanel: Ext.create("NIC.PMSLeftPanel"),
    pmsRightPanel: Ext.create("NIC.PMSRightPanel"),
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            closeAction: 'hide',
            items: [this.pmsLeftPanel, this.pmsRightPanel]
        });
        this.pmsRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            pmsSearchText = '';
        });
        this.pmsLeftPanel.pmsitementities.on('itemclick', function (view, record, item, index, e) {
            pmsCurrentEntity = record.data.text;
            clearSearchBox(this.pmsRightPanel.searchText);
            this.pmsRightPanel.grid.doLayout();
            if (index != 0) {
                this.pmsRightPanel.searchText.enable(true);
                this.pmsRightPanel.menuBar.enable(true);
                this.pmsRightPanel.addButton.enable(true);
                this.pmsRightPanel.searchButton.enable(true);
                this.pmsRightPanel.advSearchButton.enable(true);
                this.pmsRightPanel.grid.headerCt.removeAll();
                this.pmsRightPanel.grid.getStore().removeAll();
                this.pmsRightPanel.grid.setTitle(pmsCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(pmsCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(pmsCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(pmsCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.pmsRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.pmsRightPanel.doLayout();
                this.pmsRightPanel.grid.store.currentPage = 1;
                this.pmsRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(pmsCurrentEntity);
                this.pmsRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + pmsCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                // this.pmsRightPanel.grid.store.load();
                this.pmsRightPanel.grid.store.sort('CreatedDate', 'DESC');
            } else {
                this.pmsRightPanel.addButton.disable(true);
                this.pmsRightPanel.menuBar.disable(true);
                // this.pmsRightPanel.uploadMedia.disable(true);
                this.pmsRightPanel.searchText.disable(true);
                this.pmsRightPanel.searchButton.disable(true);
                this.pmsRightPanel.advSearchButton.disable(true);
            }
        }, this);

        this.pmsRightPanel.menuBar.on('click', function (button, e) {
            clearSearchBox(this.pmsRightPanel.searchText);
        }, this);

        this.pmsRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.pmsRightPanel.searchText);
            pmsCreateWindow("", "", "new", this.pmsRightPanel.grid, false);
        }, this);

        this.pmsRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.pmsRightPanel, pmsCurrentEntity, 'specialKey');
        }, this);
        this.pmsRightPanel.searchButton.on('click', function (button, e) {
            globalSearch(button, e, this.pmsRightPanel, pmsCurrentEntity, 'searchbutton');
        }, this);

        // Starting Advanced Search
        this.pmsRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.pmsRightPanel, pmsCurrentEntity);
        }, this);

        // Ending Advanced Search
        this.callParent();
        loadDomainEntities(
        this.pmsLeftPanel.pmsitementities.store, 'pms');
        this.getResponse = function (options) {
            this.pmsRightPanel.formpanel.add(options);
            this.pmsRightPanel.formpanel.doLayout();
        }
    }
    // }
});

/**
 * Cms left panel definition 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);

Ext.define('NIC.CMSLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.cmsleftpanel',

    cmsitementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        preventHeader: true,
        autoScroll: true,
        layout: {
            type: 'fit',
            align: 'stretch'
        },
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            // autoLoad:false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=cms&rolename='+userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            // autoScroll:true,
            width: 250,
            items: [this.cmsitementities]
            // , this.workflowentities, this.iaentities]
        });
        this.callParent();

        this.cmsitementities.on('load', function (store, records, successful) {
            this.cmsitementities.expand(true);
            this.cmsitementities.determineScrollbars();
            this.doLayout();
        }, this);

    }
});

/**
 * Cms right panel definition 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.CMSRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.cmsrightpanel',
    menu: showFilterByMenu('cms'),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'cms-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                reader: {
                    type: 'json',
                    root: 'Collections.Article',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]

        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (view, record, node, index, e) {
                    contextMenu("cms", view, record, node, index, e);
                    /*
                     * e.stopEvent(); view.getSelectionModel().select(record);
                     * var cMenu = contextMenu(record,"domain"); if(cMenu)
                     * cMenu.showAt(e.getXY()); return false;
                     */
                }
            },
            getRowClass: function (record, index, rowParams, ds) {
                if (record.data.Stage && applyStageColor) {
                    return record.data.Stage + '-row-background-class';
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) cmsSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = cmsSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('cms-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus',
        "id": 'cms-add-new'
    }),
    sourceList: Ext.create('Ext.form.field.ComboBox', {
     fiedLable :"Source List",
disabled: true,
           id:"ToolbarSource",
	   emptyText:"Select Source",
       store:createStore('Source', '', 'SourceName'),
                                queryMode:"remote",
     displayField:"SourceName",
     valueField:"SourceName",       
	}),
    menuBar: Ext.create('Ext.button.Button', {
        text: 'Filter By',
        disabled: true,
        iconCls: 'icon-domains-entities'
    }),
    // loadButton:Ext.create('Ext.button.Button', {text: 'Load Data'
    // ,disabled:true, iconCls:'icon-load'}),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [
            // this.loadButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            {
                xtype: 'tbspacer',
                width: 20
            }
	    ,
            this.sourceList,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            // forceFit:true,
            // layout: 'fit',
            items: [this.grid]
        });
        this.callParent();
        // this.tabpanel.setActiveTab(0);
    }
});

/**
 * Cms main panel definition 
 */

var cmsCurrentEntity = "";
var currentColumn = [];
var mainStoregrid = "";
Ext.define('NIC.CMSMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.cmsmainpanel',

    cmsLeftPanel: Ext.create("NIC.CMSLeftPanel"),
    cmsRightPanel: Ext.create("NIC.CMSRightPanel"),
    passFn: function () {
        Ext.DropDownAlert.msg('Status', 'Saved successfully.');
    },
    failFn: function () {
        Ext.DropDownAlert.msg('Status', 'Not Saved.');
    },
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            autoScroll: true,
            items: [this.cmsLeftPanel, this.cmsRightPanel]
        });
        this.cmsRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            cmsSearchText = '';
        });
        this.cmsLeftPanel.cmsitementities.on('itemclick', function (view, record, item, index, e) {
            this.cmsRightPanel.grid.doLayout();
            clearSearchBox(this.cmsRightPanel.searchText);
            if (index != 0) {
                cmsCurrentEntity = record.data.text;
                this.cmsRightPanel.addButton.enable(true);
                this.cmsRightPanel.menuBar.enable(true);
                this.cmsRightPanel.addButton.enable(true);
		this.cmsRightPanel.sourceList.enable(true);
                this.cmsRightPanel.searchText.enable(true);
                this.cmsRightPanel.searchButton.enable(true);
                this.cmsRightPanel.advSearchButton.enable(true);
                this.cmsRightPanel.grid.headerCt.removeAll();
                this.cmsRightPanel.grid.getStore().removeAll();
                this.cmsRightPanel.grid.setTitle(cmsCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(cmsCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(cmsCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(cmsCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.cmsRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.cmsRightPanel.doLayout();
                this.cmsRightPanel.grid.getView().refresh();
                this.cmsRightPanel.grid.store.currentPage = 1;
                this.cmsRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(cmsCurrentEntity);
                this.cmsRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + cmsCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                //this.cmsRightPanel.grid
                //.getStore().load();
                this.cmsRightPanel.grid.store.sort('CreatedDate', 'DESC');
            } else {
                this.cmsRightPanel.addButton.disable(true);
                this.cmsRightPanel.searchText.disable(true);
                this.cmsRightPanel.menuBar.disable(true);
                this.cmsRightPanel.searchButton.disable(true);
                this.cmsRightPanel.advSearchButton.disable(true);
            }

        }, this);

        this.cmsRightPanel.menuBar.on('click', function (button, e) {
            clearSearchBox(this.cmsRightPanel.searchText);
        }, this);

        this.cmsRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.cmsRightPanel, cmsCurrentEntity, 'specialKey');
        }, this);
        this.cmsRightPanel.searchButton.on('click', function (button, e) {
            globalSearch(button, e, this.cmsRightPanel, cmsCurrentEntity, 'searchbutton');
        }, this);

        this.cmsRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.cmsRightPanel.searchText);
            cmsCreateWindow("", "", "new", this.cmsRightPanel.grid, false);
        }, this);

        // Starting Advanced Search
        this.cmsRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.cmsRightPanel, cmsCurrentEntity);
        }, this);

        // Ending Advanced Search
        loadDomainEntities(
        this.cmsLeftPanel.cmsitementities.store, 'cms');
        this.callParent();
    }
});

/**
 * User left panel definition 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);
Ext.define('NIC.USERLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.userleftpanel',
    useritementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        forceFit: true,
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        layout: 'fit',
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            autoLoad: false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=user&rolename='+userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                id: 'AllowedEntities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            autoHeight: true,
            autoScroll: true,
            width: 250,
            scroll: 'both',
            items: [this.useritementities]
        });
        this.callParent();
        this.useritementities.on('load', function (store, records, successful) {
            this.useritementities.expand(true);
            this.doLayout();
        }, this);
    }
});

/**
 * User right panel definition 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.USERRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.userrightpanel',
    menu: showFilterByMenu('user'),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'user-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'Collections.UserProfile',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]
        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (view, record, node, index, e) {
                    e.stopEvent();
                    view.getSelectionModel().select(record);
                    var cMenu = contextMenu(record, "user");
                    if (cMenu) cMenu.showAt(e.getXY());
                    return false;
                }
            },
            getRowClass: function (record, index, rowParams, ds) {
                if (record.data.Stage && applyStageColor) {
                    return record.data.Stage + '-row-background-class';
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) userSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = userSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('user-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus'
    }),
    menuBar: Ext.create('Ext.button.Button', {
        text: 'Filter By',
        disabled: true,
        iconCls: 'icon-domains-entities'
    }),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [{
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            items: [this.grid]
        });
        this.callParent();
    }
});

/**
 * User main panel definition 
 */

var userCurrentEntity = '';
var api_key = Ext.util.Cookies.get("api_key");
var authUsername = Ext.util.Cookies.get("authUsername");
var currentColumn = [];
Ext.define('NIC.USERMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.usermainpanel',
    userLeftPanel: Ext.create("NIC.USERLeftPanel"),
    userRightPanel: Ext.create("NIC.USERRightPanel"),
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            closeAction: 'hide',
            items: [this.userLeftPanel, this.userRightPanel]
        });

        this.userRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            userSearchText = '';
        });
        this.userLeftPanel.useritementities.on('itemclick', function (view, record, item, index, e) {
            userCurrentEntity = record.data.text;
            clearSearchBox(this.userRightPanel.searchText);
            this.userRightPanel.grid.doLayout();
            if (index != 0) {
                // userCreateWindow("", "",
                // "new",this,true);
                this.userRightPanel.searchText.enable(true);
                this.userRightPanel.addButton.enable(true);
                this.userRightPanel.searchButton.enable(true);
                this.userRightPanel.menuBar.enable(true);
                this.userRightPanel.advSearchButton.enable(true);
                this.userRightPanel.grid.headerCt.removeAll();
                this.userRightPanel.grid.getStore().removeAll();
                this.userRightPanel.grid.setTitle(userCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(userCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(userCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(userCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.userRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.userRightPanel.doLayout();
                this.userRightPanel.grid.store.currentPage = 1;
                this.userRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(userCurrentEntity);
                this.userRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + userCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                //this.userRightPanel.grid.store.load();
                this.userRightPanel.grid.store.sort('CreatedDate', 'DESC');
            } else {
                this.userRightPanel.addButton.disable(true);
                this.userRightPanel.menuBar.disable(true);
                this.userRightPanel.searchText.disable(true);
                this.userRightPanel.searchButton.disable(true);
                this.userRightPanel.advSearchButton.disable(true);
            }
        }, this);

        this.userRightPanel.menuBar.on('click', function (
        button, e) {
            clearSearchBox(this.userRightPanel.searchText);
        }, this);

        this.userRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.userRightPanel.searchText);
            userCreateWindow("", "", "new", this.userRightPanel.grid, false);
        }, this);

        this.userRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.userRightPanel, userCurrentEntity, 'specialKey');
        }, this);
        this.userRightPanel.searchButton.on('click', function (
        button, e) {
            globalSearch(button, e, this.userRightPanel, userCurrentEntity, 'searchbutton');
        }, this);

        // Starting Advanced Search
        this.userRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.userRightPanel, userCurrentEntity);
        }, this);

        // Ending Advanced Search
        loadDomainEntities(
        this.userLeftPanel.useritementities.store, 'user');
        this.callParent();
        this.getResponse = function (options) {
            this.userRightPanel.formpanel.add(options);
            this.userRightPanel.formpanel.doLayout();
        }
    }
    // }
});

/**
 * Feed left panel definition 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);
Ext.define('NIC.FEEDLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.feedleftpanel',
    feeditementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        forceFit: true,
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        layout: 'fit',
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            autoLoad: false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=feed&rolename='+userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                id: 'AllowedEntities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            autoHeight: true,
            autoScroll: true,
            width: 250,
            scroll: 'both',
            items: [this.feeditementities]
        });
        this.callParent();
        this.feeditementities.on('load', function (store, records, successful) {
            this.feeditementities.expand(true);
            this.doLayout();
        }, this);
    }
});

/**
 * Feed right panel definition 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.FEEDRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.feedrightpanel',
    menu: showFilterByMenu('feed'),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'feed-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                reader: {
                    type: 'json',
                    root: 'Collections.ContentPartner',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]
        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (
                view, record, node, index, e) {
                    e.stopEvent();
                    view.getSelectionModel().select(record);
                    var cMenu = contextMenu(
                    record, "feed");
                    if (cMenu) cMenu.showAt(e.getXY());
                    return false;
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) feedSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = feedSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('feed-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus'
    }),
    menuBar: Ext.create('Ext.button.Button', {
        text: 'Filter By',
        disabled: true,
        iconCls: 'icon-domains-entities'
    }),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [{
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            items: [this.grid]
        });
        this.callParent();
    }
});

/**
 * Feed main panel definition 
 */

var feedCurrentEntity = '';
var currentColumn = [];
Ext.define('NIC.FEEDMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.feedmainpanel',
    feedLeftPanel: Ext.create("NIC.FEEDLeftPanel"),
    feedRightPanel: Ext.create("NIC.FEEDRightPanel"),
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            closeAction: 'hide',
            items: [this.feedLeftPanel, this.feedRightPanel]
        });
        this.feedRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            feedSearchText = '';
        });
        this.feedLeftPanel.feeditementities.on('itemclick', function (view, record, item, index, e) {
            feedCurrentEntity = record.data.text;
            clearSearchBox(this.feedRightPanel.searchText);
            this.feedRightPanel.grid.doLayout();
            if (index != 0) {
                // feedCreateWindow("", "",
                // "new",this,true);
                this.feedRightPanel.searchText.enable(true);
                this.feedRightPanel.addButton.enable(true);
                this.feedRightPanel.menuBar.enable(true);
                this.feedRightPanel.searchButton.enable(true);
                this.feedRightPanel.advSearchButton.enable(true);
                this.feedRightPanel.grid.headerCt.removeAll();
                this.feedRightPanel.grid.getStore().removeAll();
                this.feedRightPanel.grid.setTitle(feedCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(feedCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(feedCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(feedCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.feedRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.feedRightPanel.doLayout();
                this.feedRightPanel.grid.store.currentPage = 1;
                this.feedRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(feedCurrentEntity);
                this.feedRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + feedCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                // this.feedRightPanel.grid.store.load();
                this.feedRightPanel.grid.store.sort('CreatedDate', 'DESC');
            } else {
                this.feedRightPanel.addButton.disable(true);
                this.feedRightPanel.menuBar.disable(true);
                this.feedRightPanel.searchText.disable(true);
                this.feedRightPanel.searchButton.disable(true);
                this.feedRightPanel.advSearchButton.disable(true);
            }
        }, this);

        this.feedRightPanel.menuBar.on('click', function (
        button, e) {
            clearSearchBox(this.feedRightPanel.searchText);
        }, this);

        this.feedRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.feedRightPanel.searchText);
            feedCreateWindow("", "", "new", this.feedRightPanel.grid, false);
        }, this);

        this.feedRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.feedRightPanel, feedCurrentEntity, 'specialKey');
        }, this);
        this.feedRightPanel.searchButton.on('click', function (
        button, e) {
            globalSearch(button, e, this.feedRightPanel, feedCurrentEntity, 'searchbutton');
        }, this);

        // Starting Advanced Search
        this.feedRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.feedRightPanel, feedCurrentEntity);
        }, this);

        // Ending Advanced Search
        this.callParent();
        loadDomainEntities(
        this.feedLeftPanel.feeditementities.store, 'feed');
        this.getResponse = function (options) {
            this.feedRightPanel.formpanel.add(options);
            this.feedRightPanel.formpanel.doLayout();
        }
    }
    // }
});

/**
 * Admin left panel definition 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);
Ext.define('NIC.ADMINLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminleftpanel',
    adminitementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        layout: 'fit',
        forceFit: true,
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            autoLoad: false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=admin&rolename='
                // + userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            autoHeight: true,
            autoScroll: true,
            width: 250,
            scroll: 'both',
            items: [this.adminitementities]
        });
        this.callParent();
        this.adminitementities.on('load', function (store, records, successful) {
            this.adminitementities.expand(true);
            this.doLayout();
        }, this);
    }

});

/**
 * Admin right panel definition 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.ADMINRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminrightpanel',
    menu: showFilterByMenu('admin'),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'admin-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                reader: {
                    type: 'json',
                    root: 'Collections.Admin',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]
        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (view, record, node, index, e) {
                    e.stopEvent();
                    view.getSelectionModel().select(record);
                    var cMenu = contextMenu(record, "admin");
                    if (cMenu) cMenu.showAt(e.getXY());
                    return false;
                }
            },
            getRowClass: function (record, index, rowParams, ds) {
                if (record.data.Stage && applyStageColor) {
                    return record.data.Stage + '-row-background-class';
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) adminSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = adminSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('admin-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus'
    }),
    menuBar: Ext.create('Ext.button.Button', {
        text: 'Filter By',
        disabled: true,
        iconCls: 'icon-domains-entities'
    }),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [{
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            items: [this.grid]
        });
        this.callParent();
    }
});

/**
 * Admin main panel definition 
 */

var adminCurrentEntity = '';
var currentColumn = [];
Ext.define('NIC.ADMINMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminmainpanel',
    adminLeftPanel: Ext.create("NIC.ADMINLeftPanel"),
    adminRightPanel: Ext.create("NIC.ADMINRightPanel"),
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            closeAction: 'hide',
            items: [this.adminLeftPanel, this.adminRightPanel]
        });
        this.adminRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            adminSearchText = '';
        });
        this.adminLeftPanel.adminitementities.on('itemclick', function (view, record, item, index, e) {
            adminCurrentEntity = record.data.text;
            this.adminRightPanel.grid.doLayout();
            clearSearchBox(this.adminRightPanel.searchText);
            if (index != 0) {

                if (adminCurrentEntity == "AppAdmin" || adminCurrentEntity == "Feedback") {
                    this.adminRightPanel.addButton.disable(true);
                } else {
                    this.adminRightPanel.addButton.enable(true);
                }
                // adminCreateWindow("", "",
                // "new",this,true);
                this.adminRightPanel.searchText.enable(true);
                this.adminRightPanel.searchButton.enable(true);
                this.adminRightPanel.menuBar.enable(true);
                this.adminRightPanel.advSearchButton.enable(true);
                this.adminRightPanel.grid.headerCt.removeAll();
                this.adminRightPanel.grid.getStore().removeAll();
                this.adminRightPanel.grid.setTitle(adminCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(adminCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(adminCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(adminCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.adminRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.adminRightPanel.doLayout();
                this.adminRightPanel.grid.store.currentPage = 1;
                this.adminRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(adminCurrentEntity);
                this.adminRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + adminCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                //this.adminRightPanel.grid.store.load();
                this.adminRightPanel.grid.store.sort('CreatedDate', 'DESC');
            } else {
                this.adminRightPanel.addButton.disable(true);
                this.adminRightPanel.menuBar.disable(true);
                this.adminRightPanel.searchText.disable(true);
                this.adminRightPanel.searchButton.disable(true);
                this.adminRightPanel.advSearchButton.disable(true);
            }
        }, this);

        this.adminRightPanel.menuBar.on('click', function (
        button, e) {
            clearSearchBox(this.adminRightPanel.searchText);
        }, this);

        this.adminRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.adminRightPanel.searchText);
            adminCreateWindow("", "", "new", this.adminRightPanel.grid, false);
        }, this);

        this.adminRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.adminRightPanel, adminCurrentEntity, 'specialKey');
        }, this);
        this.adminRightPanel.searchButton.on('click', function (
        button, e) {
            globalSearch(button, e, this.adminRightPanel, adminCurrentEntity, 'searchbutton');
        }, this);

        // Starting Advanced Search
        this.adminRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.adminRightPanel, adminCurrentEntity);
        }, this);

        // Ending Advanced Search
        this.callParent();
        loadDomainEntities(
        this.adminLeftPanel.adminitementities.store, 'admin');
        this.getResponse = function (options) {
            this.adminRightPanel.formpanel.add(options);
            this.adminRightPanel.formpanel.doLayout();
        }
    }
    // }
});

/**
 * Domain left panel definition 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);
Ext.define('NIC.DOMAINLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.domainleftpanel',
    domainitementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        layout: 'fit',
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            autoLoad: false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=domain&rolename='+userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                id: 'AllowedEntities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            autoHeight: true,
            autoScroll: true,
            width: 250,
            scroll: 'both',
            items: [this.domainitementities]
        });
        this.callParent();
        this.domainitementities.on('load', function (store, records, successful) {
            this.domainitementities.expand(true);
            this.doLayout();
        }, this);
    }
});

/**
 * Admin right panel definition 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.DOMAINRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.domainrightpanel',
    menu: showFilterByMenu('domain'),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'domain-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                reader: {
                    type: 'json',
                    root: 'Collections.Domain',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]
        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (view, record, node, index, e) {
                    contextMenu("domain", view, record, node, index, e);
                    /*
                     * e.stopEvent(); view.getSelectionModel().select(record);
                     * var cMenu = contextMenu(record,"domain"); if(cMenu)
                     * cMenu.showAt(e.getXY()); return false;
                     */
                }
            },
            getRowClass: function (record, index, rowParams, ds) {
                if (record.data.Stage && applyStageColor) {
                    return record.data.Stage + '-row-background-class';
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) domainSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = domainSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('domain-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus'
    }),
    menuBar: Ext.create('Ext.button.Button', {
        text: 'Filter By',
        disabled: true,
        iconCls: 'icon-domains-entities'
    }),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [{
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            items: [this.grid]
        });
        this.callParent();
    }
});

/**
 * Admin main panel definition 
 */

var domainCurrentEntity = '';
var currentColumn = [];
Ext.define('NIC.DOMAINMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.domainmainpanel',
    domainLeftPanel: Ext.create("NIC.DOMAINLeftPanel"),
    domainRightPanel: Ext.create("NIC.DOMAINRightPanel"),
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            closeAction: 'hide',
            items: [this.domainLeftPanel, this.domainRightPanel]
        });
        this.domainRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            domainSearchText = '';
        });
        this.domainLeftPanel.domainitementities.on('itemclick', function (view, record, item, index, e) {
            domainCurrentEntity = record.data.text;
            this.domainRightPanel.grid.doLayout();
            clearSearchBox(this.domainRightPanel.searchText);
            if (index != 0) {
                // domainCreateWindow("", "",
                // "new",this,true);
                this.domainRightPanel.searchText.enable(true);
                this.domainRightPanel.addButton.enable(true);
                this.domainRightPanel.searchButton.enable(true);
                this.domainRightPanel.menuBar.enable(true);
                this.domainRightPanel.advSearchButton.enable(true);
                this.domainRightPanel.grid.headerCt.removeAll();
                this.domainRightPanel.grid.getStore().removeAll();
                this.domainRightPanel.grid.setTitle(domainCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(domainCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(domainCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(domainCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.domainRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.domainRightPanel.doLayout();
                this.domainRightPanel.grid.store.currentPage = 1;
                this.domainRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(domainCurrentEntity);
                this.domainRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + domainCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                //this.domainRightPanel.grid.store.load();
                this.domainRightPanel.grid.store.sort('CreatedDate', 'DESC');

            } else {
                this.domainRightPanel.addButton.disable(true);
                this.domainRightPanel.menuBar.disable(true);
                this.domainRightPanel.searchText.disable(true);
                this.domainRightPanel.searchButton.disable(true);
                this.domainRightPanel.advSearchButton.disable(true);
            }
        }, this);

        this.domainRightPanel.menuBar.on('click', function (
        button, e) {
            clearSearchBox(this.domainRightPanel.searchText);
        }, this);

        this.domainRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.domainRightPanel.searchText);
            domainCreateWindow("", "", "new", this.domainRightPanel.grid, false);
        }, this);

        this.domainRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.domainRightPanel, domainCurrentEntity, 'specialKey');
        }, this);
        this.domainRightPanel.searchButton.on('click', function (button, e) {
            globalSearch(button, e, this.domainRightPanel, domainCurrentEntity, 'searchbutton');
        }, this);

        // Starting Advanced Search
        this.domainRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.domainRightPanel, domainCurrentEntity);
        }, this);

        // Ending Advanced Search
        this.callParent();
        loadDomainEntities(
        this.domainLeftPanel.domainitementities.store, 'domain');
        this.getResponse = function (options) {
            this.domainRightPanel.formpanel.add(options);
            this.domainRightPanel.formpanel.doLayout();
        }
    }
    // }
});

/**
 * Model Definition for Workflow module
 */

Ext.define('WorkflowDefinition', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'StageName'
    }, {
        name: 'StageType'
    }, {
        name: 'PossibleActionName'
    }, {
        name: 'NextStage'
    }, {
        name: 'NextContentStatus'
    }, {
        name: 'PossibleActionUser'
    }]
});

/**
 * Workflow Editable grid definition
 */

Ext.define('Writer.EditableGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.writereditgrid',
    requires: ['Ext.grid.plugin.CellEditing', 'Ext.form.field.Text', 'Ext.toolbar.TextItem'],

    initComponent: function () {

        this.editing = Ext.create('Ext.grid.plugin.CellEditing');
        Ext.apply(this, {
            iconCls: 'icon-grid',
            autoScroll: true,
            plugins: [this.editing],
            /*
             * dockedItems: [{ xtype: 'toolbar', items: [{ iconCls:'icon-plus',
             * text: 'Add', scope: this, handler: this.onAddClick }, { iconCls:
             * 'icon-minus', text: 'Delete', //disabled: true, itemId: 'delete',
             * scope: this, handler: this.onDeleteClick }] }],
             */
            selModel: {
                selType: 'cellmodel'
            },
            plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 1
            })]
        });
        this.callParent();
        this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
    },

    onSelectChange: function (selModel, selections) {
        this.down('#delete').setDisabled(selections.length === 0);
    },

    onDeleteClick: function () {
        var selection = this.getView().getSelectionModel().getSelection()[0];
        if (selection) {
            this.store.remove(selection);
        }
    },

    onAddClick: function () {
        var rec = new WorkflowDefinition({
            StageName: '',
            StageType: '',
            PossibleActionName: '',
            NextStage: '',
            NextContentStatus: '',
            PossibleActionUser: ''
        }),
            edit = this.editing;

        var insertAt = this.store.getCount();
        this.store.insert(insertAt, rec);
        edit.startEditByPosition({
            row: 0,
            column: 1
        });
    }
});

/**
 * Workflow stage grid configuration
 */

var WorkflowStageGrid = Ext.create('Writer.EditableGrid', {
    columnLines: true,
    store: Ext.create('Ext.data.Store', {
        autoLoad: false,
        model: 'WorkflowDefinition'
    }),
    autoScroll: true,
    forceFit: true,
    columns: [{
        header: 'Workflow Stages',
        dataIndex: 'StageName',
        width: 70,
        field: {
            xtype: 'combobox',
            editable: false,
            store: Ext.create('Ext.data.ArrayStore', {
                fields: ["StageName"],
                data: WorkflowStageArray
            }),
            queryMode: 'local',
            displayField: "StageName",
            valueField: "StageName",
            emptyText: "",
            allowBlank: false,
            listeners: {
                beforerender: function (combo) {
                    this.store.sort('StageName');
                },
                focus: function () {
                    if (Ext.getCmp('WorkflowComboStageName').getValue() == '') this.store.removeAll();
                    else {
                        if (!Ext.isEmpty(WorkflowStageArray)) this.store.loadData(WorkflowStageArray);
                        else {
                            var WorkflowStageNewArray = WorkflowStageValue.split(",")
                            for (var i = 0; i < WorkflowStageNewArray.length; i++) {
                                WorkflowStageArray[i] = [WorkflowStageNewArray[i]];
                            }
                            this.store.loadData(WorkflowStageArray);
                        }
                    }
                }
            }
        },
        sortable: false
    }, {
        header: 'Stage Types',
        dataIndex: 'StageType',
        width: 70,
        field: {
            xtype: 'combobox',
            editable: false,
            store: Ext.create('Ext.data.ArrayStore', {
                fields: ["StageType"],
                data: Ext.workflowStageType
            }),
            displayField: "StageType",
            valueField: "StageType",
            emptyText: "",
            allowBlank: false
        },
        sortable: false
    }, {
        header: 'Workflow Actions',
        width: 80,
        dataIndex: 'PossibleActionName',
        field: {
            xtype: 'combobox',
            multiSelect: true,
            store: createStore('Action', ''),
	    typeAhead : true,
	    minChars:1,
	 //  forceSelection: true,
	    triggerAction: 'all',
            queryMode: "local",
            displayField: "ActionName",
            valueField: "ActionName",
            emptyText: "",
            allowBlank: false,
            listeners: {
                beforerender: function (combo) {
                    this.store.load();
                },
                render: function (combo, options) {
                    this.store.sort('ActionName', 'ASC');
                },
		expand : function(field, eOpts){
		    field.queryMode = "local";
		},
		keypress : function(combo, e, eOpts){
		  if((e.getKey() >= 65 && e.getKey() <= 90) 
		    || (e.getKey() >= 97 && e.getKey() <= 122)
		    || (e.getKey() == Ext.EventObject.ENTER)
		    || (e.getKey() == Ext.EventObject.BACKSPACE)
		    ) {
		    combo.collapse();
		    if(Ext.isEmpty(combo.getValue())){
		      combo.forceSelection=true;
		      combo.store.clearFilter(true);
		      combo.store.load();
		      combo.expand();
		    }
		  }
		},
		focus : function(combo){
		    combo.forceSelection=true;
		    combo.store.clearFilter(true);
		    combo.store.load();
		}
            }
        },
        sortable: false
    }]
});

/**
 * Workflow usermap grid configuration
 */

var WorkflowUserMapGrid = Ext.create('Writer.EditableGrid', {
    columnLines: true,
    store: Ext.create('Ext.data.Store', {
        model: 'WorkflowDefinition',
        storeId: 'workflow-usermap-grid'
    }),
    autoScroll: true,
    forceFit: true,
    columns: [{
        header: 'Workflow Stages',
        dataIndex: 'StageName',
        width: 70,
        sortable: false
    },
    {
        header: 'Workflow Actions',
        width: 80,
        dataIndex: 'PossibleActionName',
        sortable: false
    }, {
        header: 'Next Stage',
        width: 70,
        dataIndex: 'NextStage',
        field: {
            xtype: 'combobox',
            store: Ext.create('Ext.data.ArrayStore', {
                fields: ["StageName"],
                sortOnLoad: true,
                data: WorkflowStageArray
            }),
	    typeAhead : true,
	    minChars:1,
	  //  forceSelection: true,
	    triggerAction: 'all',
            queryMode: 'local',
            displayField: "StageName",
            valueField: "StageName",
            emptyText: "",
            allowBlank: false,
            listeners: {
                focus: function (combo) {
                    if (Ext.getCmp('WorkflowComboStageName').getValue() == '') this.store.removeAll();
                    else {
                        if (!Ext.isEmpty(WorkflowStageArray)) this.store.loadData(WorkflowStageArray);
                        else {
                            var WorkflowStageNewArray = WorkflowStageValue.split(",")
                            for (var i = 0; i < WorkflowStageNewArray.length; i++) {
                                WorkflowStageArray[i] = [WorkflowStageNewArray[i]];
                            }
                            this.store.loadData(WorkflowStageArray);
                        }
                    }
                    combo.forceSelection=true;
		    combo.store.clearFilter(true);
		    //combo.store.load();
                },
		expand : function(field, eOpts){
		    field.queryMode = "local";
		},
		keypress : function(combo, e, eOpts){
		    if((e.getKey() >= 65 && e.getKey() <= 90) 
		      || (e.getKey() >= 97 && e.getKey() <= 122)
		      || (e.getKey() == Ext.EventObject.ENTER)
		      || (e.getKey() == Ext.EventObject.BACKSPACE)
		      ) {
		      combo.collapse();
		      if(Ext.isEmpty(combo.getValue())){
			combo.forceSelection=true;
			combo.store.clearFilter(true);
			combo.store.load();
			combo.expand();
		      }
		    }
		}
            }
        },
        sortable: false
    }, {
        header: 'Next Content Status',
        dataIndex: 'NextContentStatus',
        width: 80,
        field: {
            xtype: 'combobox',
            store: createStore('Status', ''),
            queryMode: "remote",
            displayField: "StatusName",
            valueField: "StatusName",
            emptyText: "",
	    typeAhead : true,
	    minChars:1,
	 //   forceSelection: true,
	    triggerAction: 'all',
            allowBlank: false,
            listeners: {
                render: function (combo, options) {
                    this.store.sort('StatusName', 'ASC');
                },
		expand : function(field, eOpts){
		    field.queryMode = "local";
		},
		keypress : function(combo, e, eOpts){
		  if((e.getKey() >= 65 && e.getKey() <= 90) 
		    || (e.getKey() >= 97 && e.getKey() <= 122)
		    || (e.getKey() == Ext.EventObject.ENTER)
		    || (e.getKey() == Ext.EventObject.BACKSPACE)
		    ) {
		    combo.collapse();
		    if(Ext.isEmpty(combo.getValue())){
		      combo.forceSelection=true;
		      combo.store.clearFilter(true);
		      combo.store.load();
		      combo.expand();
		    }
		  }
		},
		focus : function(combo){
		    combo.forceSelection=true;
		    combo.store.clearFilter(true);
		    combo.store.load();
		}
            }
        },
        sortable: false
    }, {
        header: 'Assigned Users',
        width: 150,
        dataIndex: 'PossibleActionUser',
        field: {
            xtype: 'combobox',
            pageSize: 40,
            typeAhead: true,
	    minChars:1,
	//    forceSelection: true,
	    triggerAction: 'all',
            multiSelect: true,
            store: createPreloadedStore('CmsUserProfile', ''),
            queryMode: "remote",
            displayField: "UserName",
            valueField: "UserName",
            emptyText: "",
            allowBlank: false,
            listeners: {
                render: function (combo, options) {
                    this.store.sort('UserName', 'ASC');
                },
		expand : function(field, eOpts){
		    field.queryMode = "local";
		},
		keypress : function(combo, e, eOpts){
		  if((e.getKey() >= 65 && e.getKey() <= 90) 
		    || (e.getKey() >= 97 && e.getKey() <= 122)
		    || (e.getKey() == Ext.EventObject.ENTER)
		    || (e.getKey() == Ext.EventObject.BACKSPACE)
		    ) {
		    combo.collapse();
		    if(Ext.isEmpty(combo.getValue())){
		      combo.forceSelection=true;
		      combo.store.clearFilter(true);
		      combo.store.load();
		      combo.expand();
		    }
		  }
		},
		focus : function(combo){
		    combo.forceSelection=true;
		    combo.store.clearFilter(true);
		    combo.store.load();
		}
            }
        },
        sortable: false
    }],
    viewConfig: {
        stripeRows: true,
        listeners: {
            itemcontextmenu: function (View, record, item, index, e, options) {
                e.stopEvent();
                var cMenu = contextWorkflowStageDelete(record, View);
                cMenu.showAt(e.getXY());
                return false;
            }
        }
    }
});

/**
 * Method to insert workflowstage json data to workflowstage grid 
 * @param stageName {String}
 * @param insertAt {Integer}
 */

function workflowStageInsert(stageName, insertAt) {
    var rec = new WorkflowDefinition({
        StageName: stageName,
        StageType: '',
        PossibleActionName: '',
        NextStage: '',
        NextContentStatus: '',
        PossibleActionUser: ''
    }),
        edit = Ext.create('Ext.grid.plugin.CellEditing');
    WorkflowStageGrid.store.insert(insertAt, rec);
    /*
     * edit.startEditByPosition({ row: 0, column: 1 });
     */
}

/**
 * Workflowtab panel declartion for stage and usermap grid 
 */

var WorkflowPanel3 = Ext.create('Ext.tab.Panel', {
    height: 520,
    activeTab: 0,
    region: 'center',
    items: [{
        title: 'Define Workflow',
        xtype: 'form',
        id: 'DefineWorkflow',
        autoScroll: true,
        fieldDefaults: {
            labelWidth: 155
        },
        items: [WorkflowStageGrid]
    }, {
        title: 'Map Users',
        id: 'MapUsers',
        xtype: 'form',
        autoScroll: true,
        fieldDefaults: {
            labelWidth: 155
        },
        items: [WorkflowUserMapGrid]
    }]
});

WorkflowPanel3.setActiveTab(0); //By Default first workflow panel is activated  

/**
 * This event fires when a new tab has been activated and it calls worklflow column field validation method.  
 */

WorkflowPanel3.on('tabchange', function (tabPanel, newCard, oldCard, eOpts) {
    // var currentTabId = WorkflowPanel3.getActiveTab().getId();
    if (newCard.getId() == 'MapUsers') {
        workflowGridColumnValidation();
    }
});

/**
 * This event fires when an item is clicked it validates possible actions
 * If it is empty then it makes the rest of the empty fields to non editable.
 */

WorkflowUserMapGrid.on('itemclick', function (view, record, item, index, e) {
    if (Ext.isEmpty(record.get('PossibleActionName'))) {
        view.deselect(record);
        view.refresh();
    }
});

/**
 * Method to validate every column in a workflow stage and usermap grid
 * if the workflowstage definition is not proper it throws validation error.
 * @returns {Boolean}
 */

function workflowGridColumnValidation() {

    if (WorkflowUserMapGrid.store.getCount() >= 1) {
        var storeJsonData = Ext.encode(Ext.pluck(
        WorkflowStageGrid.store.data.items, 'data'));
        var JsonData = Ext.JSON.decode(storeJsonData);
        var workflowActionsFlag = 0;
        var workflowStageTypeFlag = 0;
        var WorkflowStageNamearr = [];
        if (!Ext.isArray(JsonData)) {
            JsonData = new Array(JsonData);
        }
        for (var k = 0; k < JsonData.length; k++) {
            WorkflowStageNamearr[k] = JsonData[k].StageType;
            if (Ext.isEmpty(JsonData[k].PossibleActionName) && JsonData[k].StageType != 'End') workflowActionsFlag = 1;
            if (Ext.isEmpty(JsonData[k].StageType)) workflowStageTypeFlag = 1;
        }
        if (!Ext.Array.contains(WorkflowStageNamearr, 'Start') || !Ext.Array.contains(WorkflowStageNamearr, 'End')) {
            Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Start or End Stage is missing</div>");
            WorkflowPanel3.setActiveTab(0);
            return false;
        }
        if (!Ext.Array.contains(WorkflowStageNamearr, 'Intermediate')) {
            Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Atleast 1 intermediate stage required</div>");
            WorkflowPanel3.setActiveTab(0);
            return false;
        }
        if (arrayHasDuplicates(WorkflowStageNamearr)) {
            Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Start or End Stage is repeated</div>");
            WorkflowPanel3.setActiveTab(0);
            return false;
        }
        if (workflowActionsFlag == 1) {
            Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Workflow Actions is not null</div>");
            WorkflowPanel3.setActiveTab(0);
            return false;
        }
        if (workflowStageTypeFlag == 1) {
            Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>StageType is not null</div>");
            WorkflowPanel3.setActiveTab(0);
            return false;
        }
        WorkflowStageNamearr = []
    }
    return true;
}

/**
* On editing Workflowstagegrid data updated on usermap grid 
* @param {None} None
* @return {Json}   Returns json 
*/

WorkflowStageGrid.on('edit', function (editor, e) {
    var storeJsonData;
    // WorkflowUserMapGrid.store.removeAll();
    if (WorkflowStageGrid.store.getCount() >= 1) {
        storeJsonData = Ext.encode(Ext.pluck(
        WorkflowStageGrid.store.data.items, 'data'));
        var storeDump = [];
        var k = 0;
        var storeModel;
        var JsonData = Ext.JSON.decode(storeJsonData);
        if (!Ext.isArray(JsonData)) {
            JsonData = new Array(JsonData);
        }
        for (var i = 0; i < JsonData.length; i++) {
            if (!Ext.isEmpty(JsonData[i].PossibleActionName)) {
                var action = JsonData[i].PossibleActionName.toString().split(",");
                if (!Ext.isArray(action)) action = new Array(action);
                for (var j = 0; j < action.length; j++) {
                    storeModel = {
                        StageName: JsonData[i].StageName,
                        StageType: JsonData[i].StageType,
                        PossibleActionName: action[j],
                        NextStage: '',
                        NextContentStatus: '',
                        PossibleActionUser: ''
                    }
                    storeDump[k] = storeModel;
                    k++;
                }
            } else {
                storeModel = {
                    StageName: JsonData[i].StageName,
                    StageType: JsonData[i].StageType,
                    PossibleActionName: '',
                    NextStage: '',
                    NextContentStatus: '',
                    PossibleActionUser: ''
                }
                storeDump[k] = storeModel;
                k++;
            }
        }
        // WorkflowUserMapGrid.store.loadData(storeDump);
        WorkflowUserMapLoadData(storeDump);
    }
});

/**
 * Workflow form panel1 declaration 
 */

var WorkflowPanel1 = Ext.create('Ext.form.Panel', {
    id: 'WorkflowForm1',
    border: 0,
    region: 'north',
    height:100,
    width: 770,
    forceFit: true,
    bodyPadding: 5,
    items: [{
        name: "Id",
        id: "WorkflowWorkflowId",
        readOnly: true,
        labelWidth: 190,
        fieldCls: 'readonly-background-class',
        xtype: "textfield",
        fieldLabel: "Workflow Id",
        allowBlank: true,
        hidden: true
    }, {
        name: "WorkflowName",
        id: "WorkflowWorkflowName",
        xtype: "textfield",
        labelWidth: 190,
        enforceMaxLength: true,
        maxLength: 100,
        vtype: "alphanumStrict",
        fieldLabel: "Workflow Name <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        allowBlank: false
    }, {
        name: "WorkflowType",
        fieldLabel: "Workflow Type <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
        xtype: "combobox",
        id: "WorkflowWorkflowType",
        labelWidth: 190,
        hidden: true,
        editable: false,
        multiSelect: false,
        store: Ext.create('Ext.data.ArrayStore', {
            fields: ["WorkflowType"],
            data: Ext.workflowType
        }),
        queryMode: "local",
        displayField: "WorkflowType",
        value: "Manual",
        emptyText: "",
        allowBlank: false
        /*
         * , listeners:{ select: function(combo){ var workflowKey =
         * Ext.getCmp('WorkflowKey'); if(this.getValue() == "Manual"){
         * workflowKey.setValue(''); workflowKey.setDisabled(true); } else
         * workflowKey.setDisabled(false); } }
         */
    }, {
        name: "StageName",
        id: "WorkflowComboStageName",
        xtype: "combobox",
        labelWidth: 190,
        isFormField: false,
        multiSelect: true,
	typeAhead : true,
	minChars:1,
	// forceSelection: true,
	triggerAction: 'all',
        allowBlank: true,
        store: createStore('Stage', '', 'StageName'),
        queryMode: "local",
        displayField: "StageName",
        valueField: "StageName",
        emptyText: "",
        fieldLabel: "Choose Workflow Stages",
        listeners: {
            blur: function (combo, selection) {
                var storeModel = '';
                var storeDump = [];
                var z = 0;
                if (worklfowFormAction == 'new') {
                    stageMapHash.clear();
                }
                if (stageMapHash.getCount() >= 1) {
                    stageMapHash.each(function (key, value, length) {
                        if (finalStageHash.containsKey(key)) {
                            // workflowStageInsert(eventArray[i], i);
                            storeDump[z] = finalStageHash.get(key);
                        } else {
                            storeModel = {
                                StageName: key,
                                StageType: '',
                                PossibleActionName: '',
                                NextStage: '',
                                NextContentStatus: '',
                                PossibleActionUser: ''
                            }
                            storeDump[z] = storeModel;
                        }
                        z++
                    });
                    WorkflowStageGrid.store.removeAll();
                    WorkflowStageGrid.store.loadData(storeDump);
                    WorkflowStageGrid.fireEvent('edit');
                }
            },
            beforedeselect: function (combo, record, index, eOpts) {
                stageMapHash.removeAtKey(combo.getValue());
            },
            select: function (combo, records, eOpts) {
                stageMapHash.clear();
                WorkflowStageArray = [];
                var tempArray = combo.getValue();
                for (var i = 0; i < tempArray.length; i++) {
                    WorkflowStageArray[i] = [tempArray[i]];
                    stageMapHash.add(tempArray[i], tempArray[i]);
                }
            },
            render: function (combo, options) {
                this.store.sort('StageName', 'ASC');
            },
	    expand : function(field, eOpts){
	        field.queryMode = "local";
	    },
	    keypress : function(combo, e, eOpts){
	      if((e.getKey() >= 65 && e.getKey() <= 90) 
		|| (e.getKey() >= 97 && e.getKey() <= 122)
		|| (e.getKey() == Ext.EventObject.ENTER)
		|| (e.getKey() == Ext.EventObject.BACKSPACE)
		) {
		combo.collapse();
		if(Ext.isEmpty(combo.getValue())){
		  combo.forceSelection=true;
		  combo.store.clearFilter(true);
		  combo.store.load();
		  combo.expand();
		}
	      }
	    },
	    focus : function(combo){
		combo.forceSelection=true;
		combo.store.clearFilter(true);
		combo.store.load();
	    }
        }
    },
    /*
     * { name :"Key", fieldLabel : "Key *", xtype:"combobox", id:"WorkflowKey",
     * labelWidth:190, isFormField:false, editable:false, multiSelect:true,
     * store:Ext.create('Ext.data.ArrayStore', { fields: ["Key"], data :
     * Ext.workflow }), queryMode:"local", displayField:"Key", emptyText:"",
     * allowBlank : true, listeners: { blur: function(combo, selection) {
     * if(Ext.isArray(combo.getValue())){
     * loadPropertyForm(combo.getValue(),'add'); }else{ loadPropertyForm(new
     * Array(combo.getValue()),'add'); } } } },
     */ {
        name: "CreatedDate",
        id: "WorkflowCreatedDate",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        fieldLabel: "Created Date",
        xtype: "datetimefield",
        listeners: {
            render: function () {
                var date = new Date();
                if (this.getValue() == null || this.getValue() == "") {
                    this.setValue(date);
                }
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "CreatedBy",
        id: "WorkflowCreatedBy",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        xtype: "textfield",
        fieldLabel: "Created By",
        hidden: true,
        listeners: {
            render: function () {
                if (this.getValue() == "") {
                    this.setValue(cmsUserName);
                }
            }
        },
        allowBlank: true

    },

    {
        name: "LastModifiedBy",
        id: "WorkflowLastModifiedBy",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        xtype: "textfield",
        fieldLabel: "Last Modified By",
        hidden: true,
        listeners: {
            render: function () {
                this.setValue(cmsUserName);
            }
        },
        allowBlank: true

    },

    {
        name: "LastModifiedDate",
        id: "WorkflowLastModifiedDate",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        fieldLabel: "Last Modified Date",
        xtype: "datetimefield",
        listeners: {
            render: function () {
                var date = new Date();
                this.setValue(date);
            }
        },
        hidden: true,
        allowBlank: true

    },

    {
        name: "Site",
        id: "WorkflowSite",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        xtype: "textfield",
        fieldLabel: "Site",
        hidden: true,
        allowBlank: true

    },

    {
        name: "Version",
        id: "WorkflowVersion",
        readOnly: true,
        fieldCls: 'readonly-background-class',
        xtype: "textfield",
        fieldLabel: "Version",
        hidden: true,
        allowBlank: true

    }]
});

/**
 * Workflow main form declaration  
 */

var WorkflowForm = [{
    title: 'Workflow',
    xtype: 'form',
    layout: 'border',
    forceFit: true,
    autoScroll: true,
    fieldDefaults: {
        labelWidth: 150
    },
    items: [WorkflowPanel1,
    // WorkflowPanel2,
    WorkflowPanel3]
}];

/**
 * Method to convert store records to json data 
 * @param root {String}
 * @param objectName {String}
 * @param listStore {Array}
 * @returns {String}
 */

function getJsonOfStore(root, objectName, listStore) {
    var jsonData = '';
    if (listStore.count() > 0) {
        var storeJsonData = Ext.encode(Ext.pluck(listStore.data.items, 'data'));
        jsonData = '{"' + root + '\":' + '{"' + objectName + '\":' + storeJsonData + '}}';
    }
    return jsonData;
}

/**
 * Workflow left panel declaration 
 */

Ext.require(['Ext.tree.*', 'Ext.data.*', 'Ext.grid.*']);
Ext.define('NIC.WORKFLOWLeftPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.workflowleftpanel',

    workflowitementities: Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
        layout: 'fit',
        width: 250,
        useArrows: true,
        height: getWindowHeight(),
        rootVisible: true,
        store: Ext.create('Ext.data.TreeStore', {
            autoLoad: false,
            proxy: {
                type: 'memory',
                // url: dataservicesPath +
                // '/search?q=&entitytype=role&format=treejson&egroup=workflow&rolename='
                // + userRole,
                // url: dataPath + '/dummy.json',
                reader: {
                    type: 'json',
                    root: 'AllowedEntities'
                }
            },
            root: {
                text: 'Allowed Entities',
                expanded: false
            }
        }),
        listeners: {
            render: function (panel, options) {
                this.store.sort('text', 'ASC');
            }
        }
    }),

    initComponent: function () {
        Ext.apply(this, {
            region: "west",
            autoHeight: true,
            autoScroll: true,
            width: 250,
            scroll: 'both',
            items: [this.workflowitementities]
        });
        this.callParent();

        this.workflowitementities.on('load', function (store, records, successful) {
            this.workflowitementities.expand(true);
            this.doLayout();
        }, this);
    }
});

/**
 * Workflow right panel declaration 
 */

Ext.require(['Writer.Grid', 'NcmsUIModel']);
Ext.define('NIC.WORKFLOWRightPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.workflowrightpanel',
    // menu:filterByMenu("workflowMenuClickHandler"),
    grid: Ext.create('Writer.Grid', {
        title: 'Listing',
        id: 'workflow-main-grid',
        flex: 1,
        height: '100%',
        store: Ext.create('Ext.data.Store', {
            model: 'NcmsUIModel',
            pageSize: itemsPerPage,
            remoteSort: true,
            proxy: {
                type: 'ajax',
                url: dataservicesPath + '/search?q=&format=extjson&entitytype=dummy',
                reader: {
                    type: 'json',
                    root: 'Collections.Template',
                    totalProperty: 'Collections.Count'
                },
                simpleSortMode: true
            },
            sorters: [{
                property: 'CreatedDate',
                direction: 'DESC'
            }]

        }),
        columns: [{
            text: '',
            dataIndex: 'Name',
            width: 80
        }],
        viewConfig: {
            stripeRows: true,
            listeners: {
                itemcontextmenu: function (view, record, item, index, e, options) {
                    // if((record.data.CurrentAction) &&
                    // (record.data.CurrentAction!="undefined") &&
                    // (record.data.CurrentAction=="Expire")) {
                    if ((record.data.CurrentAction) && (record.data.CurrentAction != "undefined")) {
                        e.stopEvent();
                        var cMenu = contextMenu(record, "workflow");
                        if (cMenu) cMenu.showAt(e.getXY());
                        return false;
                    }
                }
            },
            getRowClass: function (record, index, rowParams, ds) {
                if (record.data.Stage && applyStageColor) {
                    return record.data.Stage + '-row-background-class';
                }
            }
        }
    }),
    searchText: Ext.create('Ext.form.field.Text', {
        emptyText: 'Enter search term',
        disabled: true,
        width: 200,
        maxLength: 100,
        enforceMaxLength: true,
        vtype: 'alphanumSearch',
        scope: this,
        listeners: {
            focus: function (tbx) {
                var val = tbx.getValue();
                if (!Ext.isEmpty(val)) workflowSearchText = val;
                tbx.setValue("");
            },
            blur: function (tbx) {
                var str;
                if (Ext.isEmpty(tbx.getValue())) str = workflowSearchText;
                else str = trim(tbx.getValue());
                str = str.replace(/ +(?= )/g, '');
                if (Ext.getCmp('workflow-main-grid').store.getCount() >= 1) {
                    tbx.setValue(str);
                }
            }
        }
    }),
    addButton: Ext.create('Ext.button.Button', {
        text: 'Add New',
        disabled: true,
        iconCls: 'icon-plus'
    }),
    // menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true,
    // iconCls:'icon-domains-entities'}),
    searchButton: Ext.create('Ext.button.Button', {
        text: 'Search',
        disabled: true,
        iconCls: 'icon-search'
    }),
    advSearchButton: Ext.create('Ext.button.Button', {
        text: 'Advanced Search',
        disabled: true,
        iconCls: "x-Gsearch-icon"
    }),
    initComponent: function () {
        // this.menuBar.menu = this.menu;
        Ext.apply(this, {
            tbar: [{
                xtype: 'tbspacer',
                width: 20
            },
            this.addButton,
            // {xtype: 'tbspacer', width: 50},
            // this.menuBar,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.searchText, this.searchButton,
            {
                xtype: 'tbspacer',
                width: 20
            },
            this.advSearchButton],
            autoScroll: true,
            region: "center",
            items: [this.grid]
        });
        this.callParent();

    }
});

/**
 * Workflow main panel declaration 
 */

var workflowCurrentEntity = '';
var currentColumn = [];
Ext.define('NIC.WORKFLOWMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.workflowmainpanel',
    workflowLeftPanel: Ext.create("NIC.WORKFLOWLeftPanel"),
    workflowRightPanel: Ext.create("NIC.WORKFLOWRightPanel"),
    initComponent: function () {
        Ext.apply(this, {
            region: "center",
            layout: "border",
            closeAction: 'hide',
            items: [this.workflowLeftPanel, this.workflowRightPanel]
        });
        this.workflowRightPanel.grid.store.on('load', function (
        store, records, boolean, operation, eOpts) {
            workflowSearchText = '';
        });
        this.workflowLeftPanel.workflowitementities.on('itemclick', function (view, record, item, index, e) {
            workflowCurrentEntity = record.data.text;
            clearSearchBox(this.workflowRightPanel.searchText);
            this.workflowRightPanel.grid.doLayout();
            if (index != 0) {
                this.workflowRightPanel.searchText.enable(true);
                // this.workflowRightPanel.menuBar.enable(true);
                this.workflowRightPanel.addButton.enable(true);
                this.workflowRightPanel.searchButton.enable(true);
                this.workflowRightPanel.advSearchButton.enable(true);
                this.workflowRightPanel.grid.headerCt.removeAll();
                this.workflowRightPanel.grid.getStore().removeAll();
                this.workflowRightPanel.grid.setTitle(workflowCurrentEntity + " Listing");
                currentColumn = Ext.JSON.decode(workflowCurrentEntity + "Column");
                var cloneCurrentColumn = Ext.clone(currentColumn);
                if (!allowStageColorColumn(workflowCurrentEntity)) {
                    cloneCurrentColumn.splice(0, 1);
                }
                if (!allowDeleteColumn(workflowCurrentEntity)) {
                    cloneCurrentColumn.splice(
                    cloneCurrentColumn.length - 1, 1);
                }
                this.workflowRightPanel.grid.headerCt.add(cloneCurrentColumn);
                this.workflowRightPanel.doLayout();
                this.workflowRightPanel.grid.store.currentPage = 1;
                this.workflowRightPanel.grid.store.proxy.url = dataservicesPath + '/search?q=&format=extjson' + getSubQuery(workflowCurrentEntity);
                this.workflowRightPanel.grid.store.proxy.setReader({
                    type: 'json',
                    root: 'Collections.' + workflowCurrentEntity,
                    totalProperty: 'Collections.Count'
                });
                // this.workflowRightPanel.grid.store.load();
                this.workflowRightPanel.grid.store.sort('CreatedDate', 'DESC');

            } else {
                this.workflowRightPanel.addButton.disable(true);
                // this.workflowRightPanel.menuBar.disable(true);
                this.workflowRightPanel.searchText.disable(true);
                this.workflowRightPanel.searchButton.disable(true);
                this.workflowRightPanel.advSearchButton.disable(true);
            }
        }, this);

        this.workflowRightPanel.addButton.on('click', function (
        button, e) {
            clearSearchBox(this.workflowRightPanel.searchText);
            workflowCreateWindow("", "", "new", this.workflowRightPanel.grid, false);
        }, this);

        this.workflowRightPanel.searchText.on('specialkey', function (field, e) {
            globalSearch(field, e, this.workflowRightPanel, workflowCurrentEntity, 'specialKey');
        }, this);
        this.workflowRightPanel.searchButton.on('click', function (button, e) {
            globalSearch(button, e, this.workflowRightPanel, workflowCurrentEntity, 'searchbutton');
        }, this);

        // Starting Advanced Search
        this.workflowRightPanel.advSearchButton.on('click', function (button, e) {
            globalAdvancedSeacrh(this, this.workflowRightPanel, workflowCurrentEntity);
        }, this);

        // Ending Advanced Search
        this.callParent();
        loadDomainEntities(
        this.workflowLeftPanel.workflowitementities.store, 'workflow');
        this.getResponse = function (options) {
            this.workflowRightPanel.formpanel.add(options);
            this.workflowRightPanel.formpanel.doLayout();
        }
    }
    // }
});

// var username = "domainadmin";
Ext.override(Ext.LoadMask, {
    onHide: function () {
        this.callParent();
    }
});

/**
 * Loader configuration  
 */

var username = "";
Ext.Loader.setConfig({
    enabled: true
});

Ext.Loader.setPath('Ext.ux', extPath + '/ux');
Ext.require(['Ext.grid.*', 
             'Ext.data.*', 
             'Ext.util.*', 
             'Ext.toolbar.Paging', 
             'Ext.ModelManager', 
             'Ext.layout.*', 
             'Ext.tip.QuickTipManager', 
             'NIC.CMSMainPanel', 
             'NIC.PMSMainPanel', 
             'NIC.USERMainPanel', 
             'NIC.FEEDMainPanel', 
             'NIC.ADMINMainPanel', 
             'NIC.DOMAINMainPanel', 
             'NIC.WORKFLOWMainPanel', 
             'Ext.view.View', 
             'Ext.form.*', 
             'Ext.window.Window', 
             'Ext.selection.CellModel', 
             'Ext.state.*', 
             'Ext.ux.CheckColumn', 
             'Ext.ux.DateTimePicker', 
             'Ext.ux.TimePickerField', 
             'Ext.ux.DateTimeField', 
             'Ext.ux.DataView.DragSelector', 
             'Ext.ux.DataView.LabelEditor']);

Ext.onReady(function () {
    Ext.override(Ext.LoadMask, {
        onHide: function () {
            this.callParent();
        }
    });

    Ext.QuickTips.init();
    var maintabpanel = Ext.create('Ext.tab.Panel', {
        title: 'NIC Portal Admin 1.0 - Logged in as <font color=black>' + cmsUserName + '</font>',
        // height : '200',
        forceFit: true
        // ,
        // activeItem:0,
        // xtype: 'tabpanel',
        // items : [{title:"test"}]
    });
    var arrayModules = [];
    var objectModulesClasses = new Object();
    try {
        arrayModules = getCookie("cmsmodules").split(",");
    } catch (Error) {
    	alert(Error);
    }
    
    objectModulesClasses['CMS'] = 'cmsmainpanel';
    objectModulesClasses['Domain'] = 'domainmainpanel';
    objectModulesClasses['PMS'] = 'pmsmainpanel';
    objectModulesClasses['UsersAndRoles'] = 'usermainpanel';
    objectModulesClasses['FeedAutomation'] = 'feedmainpanel';
    objectModulesClasses['Admin'] = 'adminmainpanel';
    objectModulesClasses['Workflow'] = 'workflowmainpanel';

    arrayModules = arrayModules.sort();
    for (var i = 0; i < arrayModules.length; i++) {
        var tab = arrayModules[i].replace(/\s/g, "");
        maintabpanel.add({
            xtype: objectModulesClasses[tab],
            title: arrayModules[i],
            id: objectModulesClasses[tab] + "-tab",
            iconCls: 'icon-pms'
        });
    }
    if (!Ext.isEmpty(getCookie('activetab'))) {
        maintabpanel.setActiveTab(getCookie('activetab'));
    } else {
        maintabpanel.setActiveTab(0);
    }

    var shortTabNames = new Array();
    shortTabNames['adminmainpanel-tab'] = 'admin';
    shortTabNames['feedmainpanel-tab'] = 'feed';
    shortTabNames['cmsmainpanel-tab'] = 'cms';
    shortTabNames['pmsmainpanel-tab'] = 'pms';
    shortTabNames['usermainpanel-tab'] = 'user';
    shortTabNames['workflowmainpanel-tab'] = 'workflow';
    shortTabNames['domainmainpanel-tab'] = 'domain';

    var aTab = maintabpanel.getActiveTab();
    globalActiveTab = shortTabNames[aTab.getId()];

    maintabpanel.on('tabchange', function (tabPanel, newCard, oldCard) {
        setCookie("activetab", newCard.getId(), 1, '/', '', '');
        globalActiveTab = shortTabNames[newCard.getId()];
        if (!isAuthenticated()) {
            showSessionExpired();
            return false;
        }
    });

    var logoutPanel = Ext.create('Ext.panel.Panel', {
        title: 'Logout',
        iconCls: 'icon-pms',
        listeners: {
            activate: function () {
                Ext.Msg.confirm('Logout', 'Do you want to logout?', function (
                btn) {
                    if (btn == 'Yes' || btn == 'yes') {
                    	maintabpanel.getEl().mask();
                    	Ext.MessageBox.wait('Redirecting To Login Page. Please Wait ...');
			destroyServerSession();
                    } else {
                        maintabpanel.setActiveTab(0);
                    }
                }, this);
            }
        }
    });

    logoutPanel.setTitle("Logout");
    maintabpanel.add(logoutPanel);

    var viewport = Ext.create('Ext.Viewport', {
        layout: 'fit',
        id: 'nic-extjs-cms',
        lazyRender: true,
        height: '70%',
        items: [maintabpanel]
    });
    myMask.hide();
    Ext.EventManager.on(window, 'beforeunload', function() {
    	//destroyServerSession(); 
    });
});
