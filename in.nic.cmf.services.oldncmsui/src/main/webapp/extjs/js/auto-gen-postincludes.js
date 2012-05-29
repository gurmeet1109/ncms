//http://124.7.228.161/ncmsui/extjs/pms/js/pmsform.js?_dc=3002

Ext.require([
             'NcmsUIModel',
             'Ext.ux.tree.TreeEditing'
             ]);

var pageIaPath = '';
var cmsSearchText;
var pmsSearchText;
var userSearchText;
var feedSearchText;
var domainSearchText;
var workflowSearchText;
var adminSearchText;
var PageForm = [
{
 title:'Page',
 xtype:'form',
 id:'PageForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"PageId",
               readOnly:true,
                    minLength :13,
               maxLength :13,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Id",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"alphanumMid",
                                          allowBlank :true
                           
},

                     {
     name :"EntityType",
           id:"PageEntityType",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Entity Type",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                    value:"Page",
                                                                   allowBlank :true
                           
},

                     {
     name :"PageName",
           id:"PagePageName",
                              maxLength :50,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Page Name *",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"alphanumMid",
                                          allowBlank : false
                           
},
			      {
				      name :"PageExtension",
				      id:"PagePageExtension",
				      editable:false,
				      fieldLabel : "Page Extension *",
				      xtype:"combobox",
				      editable:false,
				      store:createStore('Format',''),
				      queryMode:"remote",
				      displayField:"FileExtension",
				      valueField:"FileExtension",
				      emptyText:"",
				      allowBlank : false,
				      listeners : {
					    focus: function(combo){
					      this.store.load();
					    }
				      }
			       },
                	        {
                	    	   xtype: 'combo',
                	    	   triggerAction:  'all',
                	    	   forceSelection: true,
                	    	   editable:       false, 
                	    	   allowBlank :    false,
                	    	   fieldLabel:     'Template Name *',
                	    	   name:           'TemplateName',
                	    	   displayField:   'TemplateName',
                	    	   valueField:     'TemplateName',
                	    	   id: 'TemplateName',
                	    	   queryMode: 'remote',
                	    	   scope: this,
                	    	   store:  createPreloadedStore('Template'),
                	    	   listeners: {
                	    		   select: function(combo, selection) {
                	    			   var post = selection[0];
                	    			   layoutTemplatePath =  post.get('FilePath');
                	    			   var pmstemplatecontent = Ext.getCmp("pmstemplatecontent");
                	    			   Ext.MessageBox.wait('Getting data...');
                	    			   Ext.Ajax.request({
                	    				   method : 'GET',
                	    				   //	url: 'http://localhost/fileupload.html',
                	    				   url : mediaPath + layoutTemplatePath,
                	    				   async : false,  
                	    				   defaultHeaders : { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}, 
                	    				   success : function(response, opts) { // function called on success
                	    					   Ext.MessageBox.hide();
                	    					   pmstemplatecontent.setValue(response.responseText);
                	    					   layoutTemplatePath="";
                	    				   },
                	    				   failure : function(response, opts) {
							           pmstemplatecontent.setValue("");
                	    					   Ext.MessageBox.hide();
                	    					   Ext.example.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>Record not found.</div>");
                	    				   }
                	    				   //params : newRec.data
                	    			   });
                	    		   },
                	    		   render:function(combo,options){
                	    			   this.store.sort('TemplateName','ASC');
                	    		   },
					    focus: function(combo){
					      this.store.load();
					    }
                	    	   }
                	       },
                	       {
                	    	   xtype: 'textarea',
                	    	   fieldLabel: 'Content *',
                	    	   name: 'Content',
                	    	   anchor :'100%',
                	    	   id:'pmstemplatecontent',
                	    	   height: '88%',
                	    	   hidden: false,
                	    	   allowBlank:false
                	       },
                     {
     name :"Status",
           id:"PageStatus",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Status",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

                     {
     name :"CreatedDate",
           id:"PageCreatedDate",
               editable:false,
               fieldLabel : "Created Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                         if(this.getValue() == null || this.getValue() == ""){
                this.setValue(date);
             } 
            	   }
	 },
	                hidden:true,
                                                     allowBlank:false
                   
},

                     {
     name :"LastModifiedDate",
           id:"PageLastModifiedDate",
               editable:false,
               fieldLabel : "Last Modified Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                            this.setValue(date);
            	   }
	 },
	                hidden:true,
                                                     allowBlank:false
                   
},

                     {
     name :"CreatedBy",
           id:"PageCreatedBy",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Created By",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                                                         allowBlank :true
                           
},

                     {
     name :"LastModifiedBy",
           id:"PageLastModifiedBy",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Last Modified By",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                                                         allowBlank :true
                           
},

               	                    {
     name :"CurrentUser",
           id:"WorkflowInfoCurrentUser",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Current User",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                    allowBlank : true
         
},

                     {
     name :"CurrentEvent",
           id:"WorkflowInfoCurrentEvent",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Current Event",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                    allowBlank : true
         
},

               	                    {
     name :"User",
           id:"AssignedToUser",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "User",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

     	                               {
     name :"Stage",
           id:"AssignedToStage",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Stage",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                    allowBlank : true
         
},

               	                    {
     name :"NextPossibleEvent",
           id:"NextPossibleEventsNextPossibleEvent",
                 fieldLabel : "Next Possible Event *",
               hidden:true,
               xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('Event', '', 'EventName'),
                                queryMode:"remote",
     displayField:"EventName",
     valueField:"EventName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('EventName','ASC');
           }
        },
                                                     allowBlank:true
                   
},

     	                               {
     name :"WorkflowComments",
           id:"NextPossibleEventsWorkflowComments",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Workflow Comments",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                    allowBlank : true
         
},

     	                               {
     name :"Site",
           id:"PageSite",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Site",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

                     {
     name :"Version",
           id:"PageVersion",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Version",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},{
                	    		 xtype:'toolbar',
                	    		 items:[
//Ext.create("Ext.button.Button", {
	   {
	   xtype:'button',
	   text: 'Get Layout',
	   handler: function() {
		   getHtml();
	   	}
	   },
	   //}),
	   
	   //Ext.create("Ext.button.Button", {

		{
			xtype:'button',
		   text: 'Page Preview',
		   handler: function() {
			 getPage();
		   }
		}
		   //})
	   
                	    		 ]
                	      } ]}];
/*
 * 
 * Tree page Associator
 */

Ext.define('PageAssoModel', {
	extend: 'Ext.data.Model',
	fields: [
	         {name: 'treeitem',     type: 'string'},
	         ]
});

var pageAssoIaStore = Ext.create('Ext.data.TreeStore', {
	model: 'PageAssoModel',
	autoLoad:false,
	proxy: {
		type: 'ajax',
		noCache:false,
		//the store will get the content from the .json file
		url:  apacheNcmsData + '/InformationArchitectureTree.json'
	},
	folderSort: true
});

var pageAssoEntityStore = Ext.create('Ext.data.TreeStore', {
	model: 'PageAssoModel',
	autoLoad:false,
	proxy: {
		type: 'ajax',
		noCache:false,
		url: dataPath + '/entitytreegrid.json'
	},
	folderSort: true
});

//Ext.ux.tree.TreeGrid is no longer a Ux. You can simply use a tree.TreePanel
var pageAssoIaTree = Ext.create('Ext.tree.Panel', {
	region:'west',
	expanded: true,
	padding:1,
	width:340,
	height: 300,
	useArrows: true,
	rootVisible: false,
	store: pageAssoIaStore,
	autoScroll:true,
	hideHeaders:true,
	singleExpand: false,
	frame: false,
	stripeRows: false,
	forceFit: true,
	selType: 'cellmodel',
	columns: [{
		xtype: 'treecolumn', //this is so we know which column will show the tree
		text: 'Item',
		sortable: false,
		dataIndex: 'treeitem'
	}]
});

var pageAssoEntityTree = Ext.create('Ext.tree.Panel', {
	region:'east',
	expanded: true,
	padding:1,
	width:340,
	height: 300,
	useArrows: true,
	rootVisible: false,
	store: pageAssoEntityStore,
	autoScroll:true,
	hideHeaders:true,
	singleExpand: false,
	frame: false,
	stripeRows: false,
	forceFit: true,
	selType: 'cellmodel',
	columns: [{
		xtype: 'treecolumn', //this is so we know which column will show the tree
		text: 'Item',
		sortable: false,
		dataIndex: 'treeitem'
	}]
});

var pageNameCombo = Ext.create('Ext.form.field.ComboBox',{ 
	fieldLabel:     'Assigned To',
	name:           'PageName',
	displayField:   'PageName',
	valueField:     'PageName',
	editable:false,
	allowBlank:false,
overflow:'auto',
	triggerAction : 'all',
	queryMode: 'remote', 
	store:  createPreloadedStore('Page',''),
	listeners: {
		render:function(combo,options){
			this.store.sort('PageName','ASC');
		},
		focus : function(){
			this.store.load();
		}
	}
});

var queryField = '';
var selectedEntity = '';
var selectedIaPath = '';
var pageAssoObject = '';

pageAssoIaTree.on('itemclick',function(view, record, htmlelement, index, e){
	pageNameCombo.setValue('');
	if(record.data.treeitem != 'InformationArchitecture'){
		queryField = 'ianame';
		selectedEntity = '';
		selectedIaName = record.data.id;
		pageFormPanel.setTitle('Assign Page For InformationArchitecture');
		var o = {};
		o[queryField] = record.data.id;
		pageFormPanel.getEl().mask();
		Ext.Ajax.request({
			method : 'GET',
			url: dataservicesPath+'/pageassociator/search?q=&format=extjson', 
			success : function(response, opts) { 
				pageFormPanel.getEl().unmask(); 
				pageAssoObject = {};
				var resp = Ext.JSON.decode(response.responseText);
				if(resp.Collections.PageAssociator && resp.Collections.PageAssociator.PageName){
					pageNameCombo.setValue(resp.Collections.PageAssociator.PageName);
					pageAssoObject = resp.Collections.PageAssociator;
				}
				else{
					pageNameCombo.setValue('');
				}
				pageNameCombo.store.load();
			},
			failure : function(response, opts) {
				pageFormPanel.getEl().unmask(); 
				Ext.MessageBox.hide();
				if(response.responseText) {
					Ext.example.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + getCustomErrorInfo(response.responseText)+ "</div>");
				}
				else{
					Ext.example.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + response.statusText+ "</div>");
				}
			},
			params : o
		});
	}
},pageAssoIaTree);

pageAssoEntityTree.on('itemclick',function(view, record, htmlelement, index, e){
	pageNameCombo.setValue('');
	if(record.data.treeitem != 'Entities'){
		pageFormPanel.setTitle('Assign Page For Entity');
		selectedEntity = record.data.treeitem;
		selectedIaName = '';
		queryField = 'pageentityname';
		var o = {};
		o[queryField] = record.data.id;
		pageFormPanel.getEl().mask();
		Ext.Ajax.request({
			method : 'GET',
			url: dataservicesPath+'/pageassociator/search?q=&format=extjson', 
			success : function(response, opts) { 
				pageFormPanel.getEl().unmask();
				pageAssoObject = {};
				var resp = Ext.JSON.decode(response.responseText);
				if(resp.Collections.PageAssociator && resp.Collections.PageAssociator.PageName){
					pageNameCombo.setValue(resp.Collections.PageAssociator.PageName);
					pageAssoObject = resp.Collections.PageAssociator;
				}
				else{
					pageAssoObject = {
							Site:pageAssoObject.Site,
							Id:pageAssoObject.Id,
							EntityType:'PageAssociator',
							PageEntityName:selectedEntity,
							PageName:pageNameCombo.getValue(),
							IaName:selectedIaName,
							Status:pageAssoObject.Status != null ? pageAssoObject.Status : "Draft",
							CreatedDate:pageAssoObject.CreatedDate != null ? pageAssoObject.CreatedDate : formatDate(new Date(),"Y-m-d H:i:s"),
							CreatedBy:pageAssoObject.CreatedBy != null ? pageAssoObject.CreatedBy : cmsUserName,
							LastModifiedDate:formatDate(new Date(),"Y-m-d H:i:s"),
							LastModifiedBy: cmsUserName,
							Version:'',
							User:pageAssoObject.User,
							CurrentUser:pageAssoObject.CurrentUser,
							CurrentEvent:pageAssoObject.CurrentEvent,
							Stage:pageAssoObject.Stage,
							NextPossibleEvent:pageAssoObject.NextPossibleEvent
					};
					pageNameCombo.setValue('');
				}

			},
			failure : function(response, opts) {
				pageFormPanel.getEl().unmask();
				Ext.MessageBox.hide();
				if(response.responseText) {
					Ext.example.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + getCustomErrorInfo(response.responseText)+ "</div>");
				}
				else{
					Ext.example.msg("<div class = 'errorHeaderMsg'>Status</div>", "<div class = 'errorMsg'>" + response.statusText+ "</div>");
				}
			},
			params : o
		});
	}
},pageAssoEntityTree);


var pageAssignButton = Ext.create('Ext.Button', {
	padding:3,
	formBind:true,
	text: 'Assign',
	handler: function() {
		var dataObject = {
				Site:pageAssoObject.Site,
				Id:pageAssoObject.Id,
				EntityType:'PageAssociator',
				PageEntityName:selectedEntity,
				PageName:pageNameCombo.getValue(),
				IaName:selectedIaName,
				Status:pageAssoObject.Status != null ? pageAssoObject.Status : "Draft",
				CreatedDate:pageAssoObject.CreatedDate != null ? pageAssoObject.CreatedDate : formatDate(new Date(),"Y-m-d H:i:s"),
				CreatedBy:pageAssoObject.CreatedBy != null ? pageAssoObject.CreatedBy : cmsUserName,
				LastModifiedDate:formatDate(new Date(),"Y-m-d H:i:s"),
				LastModifiedBy: cmsUserName,
				Version:'',
				User:pageAssoObject.User,
				CurrentUser:pageAssoObject.CurrentUser,
				CurrentEvent:pageAssoObject.CurrentEvent,
				Stage:pageAssoObject.Stage,
				NextPossibleEvent:pageAssoObject.NextPossibleEvent
		};
		pageFormPanel.getEl().mask();
		Ext.Ajax.request({
			method : 'POST',
			url: dataservicesPath+'/pageassociator',
			defaultHeaders : { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}, 
			success : function(response, opts) { 
				pageFormPanel.getEl().unmask();
				var resp = Ext.JSON.decode(response.responseText);
				if(resp.success){
					Ext.example.msg("<div class = 'successHeaderMsg'>Success</div>", "<div class = 'successMsg'> Saved Successfully.</div>");
				}
			},
			failure : function(response, opts) {
				pageFormPanel.getEl().unmask();
				var actionString = response.responseText;
        	                var actionObj = Ext.JSON.decode(actionString);
                        if(!Ext.isEmpty(actionObj.msg)){
                                //pmsErrorPanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
				Ext.example.msg("<div class = 'errorHeaderMsg'>Can't Assign</div>", "<div class = 'errorMsg'>" +getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
                        }
                        else{
                                //pmsErrorPanel.update("<div class = 'errorHeaderMsg'>" + action.response.responseText + "</div>");
Ext.example.msg("<div class = 'errorHeaderMsg'>Can't Assign</div>", "<div class = 'errorMsg'>" + response.statusText+ "</div>");
                        }


			},
			params : dataObject
		});
	}
});


var pageFormPanel = Ext.create('Ext.form.Panel',{
	frame: true,
	title: 'Assign Page For',
	width: 340,
	layoutConfig : {
		type : 'vbox',
		align : 'stretch',
		pack : 'start'
	},
	fieldDefaults: {
		labelAlign: 'right',
		labelWidth: 90
	},
	region:'south',
	height:120,
	items:[pageNameCombo,pageAssignButton]
});
 
var PageAssociatorForm = [
                          {
                        	  title:"PageAssociator",
                        	  layout:'border',
                        	  autoScroll:true,
			   		border:false,
                        	  items:[pageAssoIaTree,pageAssoEntityTree,
                        	         {region:'center',width:2},
                        	         pageFormPanel
                        	 ]
                          }
                          ];

//http://124.7.228.161/ncmsui/extjs/pms/js/pmsleftpanel.js?_dc=3002
			  
			  Ext.require([
    'Ext.tree.*',
    'Ext.data.*',
    'Ext.grid.*'
]);
Ext.define('NIC.PMSLeftPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.pmsleftpanel',   

    pmsitementities : Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
	layout: 'fit',
	width:250,
	useArrows: true,
	height:getWindowHeight(),
	rootVisible: true,
	store: Ext.create('Ext.data.TreeStore', {
	autoLoad:false,
	proxy: {
	    type: 'memory',
	    //url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=pms&rolename=' + userRole,
	    //url: dataPath + '/dummy.json',
            reader : {
				type : 'json',
				root : 'AllowedEntities'
			}
	},
	root: {
            text: 'Allowed Entities',
            expanded: false
        } 
     }),
      listeners: {
	 render: function(panel, options){
	   this.store.sort('text','ASC');
	 } 
       } 
   }),
                 
   initComponent: function(){
     	Ext.apply(this, {
	  region:"west",
	  autoHeight:true,
	  autoScroll:true,
	  width:250,
	  scroll: 'both',
  	 items : [this.pmsitementities] 
     });
       this.callParent();
     this.pmsitementities.on('load',function( store, records, successful){
       this.pmsitementities.expand(true);
       this.doLayout();
     },this);      
       
    }
});

//http://124.7.228.161/ncmsui/extjs/pms/js/pmsrightpanel.js?_dc=3002


  Ext.require([
   'Writer.Grid',
   'NcmsUIModel'  
  ]);
  Ext.define('NIC.PMSRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.pmsrightpanel',
     menu:showFilterByMenu('pms'),
     grid:  Ext.create('Writer.Grid', {
                    title:'Listing',
                    id: 'pms-main-grid',
		    flex: 1,
		    height:'100%',
		    store: Ext.create('Ext.data.Store', {
				model: 'NcmsUIModel',
				pageSize: itemsPerPage,
				remoteSort: true,
				proxy: {
					type: 'ajax',
					url: dataservicesPath + '/search?q=&format=extjson&entitytype=Template',
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
                     columns:[{text:'',dataIndex:'Name',width:80}],
		        viewConfig: {
		            stripeRows: true,
		            listeners: {
		                itemcontextmenu: function(View,record,item,index,e,options ) {
		              //     if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined") && (record.data.CurrentEvent=="Expire")) {
		                   if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined")) {
    		                      e.stopEvent();
		                      var cMenu = contextMenu(record,"pms");
		                      if(cMenu)
		                        cMenu.showAt(e.getXY());
		                      return false;
		                   }
		                }
		            }
		        }
     }),
     searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	  maxLength :100,enforceMaxLength:true,vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    pmsSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str = pmsSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('pms-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
     }),
     addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus'}),
     menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
     advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
     initComponent: function(){
     this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	  
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		  {xtype: 'tbspacer', width: 20},
		  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	],
        autoScroll : true,
	region:"center",
	items: [this.grid]
     });
     this.callParent();
     }
  });    
 

//http://124.7.228.161/ncmsui/extjs/pms/js/pmsmainpanel.js?_dc=3002
  
  var pmsCurrentEntity='';
  var isPmsFile = "";
  var currentColumn = [];
  Ext.define('NIC.PMSMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.pmsmainpanel',
       pmsLeftPanel: Ext.create("NIC.PMSLeftPanel"),
       pmsRightPanel : Ext.create("NIC.PMSRightPanel"),
       initComponent: function(){
	     	  Ext.apply(this, {
	     	  region: "center",
	     	  layout:"border",
	     	  closeAction:'hide',
	     	  items:[this.pmsLeftPanel, this.pmsRightPanel] 
       }); 
         this.pmsRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            pmsSearchText='';
	 });         
	 this.pmsLeftPanel.pmsitementities.on('itemclick',function(view, record, item, index, e){
		pmsCurrentEntity = record.data.text;
		clearSearchBox(this.pmsRightPanel.searchText); 
		this.pmsRightPanel.grid.doLayout();
	        if(index != 0){
                        if(pmsCurrentEntity=="Media"){
                           isPmsFile = ""; 
                        }   
                        else{
                           isPmsFile = ""; 
                        }   
                //        pmsCreateWindow("", "", "new", this, true)
			this.pmsRightPanel.searchText.enable(true);
			this.pmsRightPanel.menuBar.enable(true);
                        this.pmsRightPanel.addButton.enable(true);
			this.pmsRightPanel.searchButton.enable(true);
			this.pmsRightPanel.advSearchButton.enable(true);
			this.pmsRightPanel.grid.headerCt.removeAll();
			this.pmsRightPanel.grid.getStore().removeAll();
			this.pmsRightPanel.grid.setTitle(pmsCurrentEntity+" Listing");
		     currentColumn = Ext.JSON.decode(pmsCurrentEntity+"Column");
		     var cloneCurrentColumn = Ext.clone(currentColumn);
                     if(!allowDeleteColumn(cmsCurrentEntity)){
                    	 cloneCurrentColumn.splice(cloneCurrentColumn.length-1,1);
                     }
			this.pmsRightPanel.grid.headerCt.add(currentColumn);
			this.pmsRightPanel.doLayout();
			this.pmsRightPanel.grid.store.currentPage=1;
			this.pmsRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+isPmsFile+getSubQuery(pmsCurrentEntity);
			this.pmsRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+pmsCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
			);
			this.pmsRightPanel.grid.store.load();
                }
                else{
                  this.pmsRightPanel.addButton.disable(true);
                  this.pmsRightPanel.menuBar.disable(true);
                //  this.pmsRightPanel.uploadMedia.disable(true);
                  this.pmsRightPanel.searchText.disable(true);
                  this.pmsRightPanel.searchButton.disable(true);
                  this.pmsRightPanel.advSearchButton.disable(true);
                }
	  },this);
	 
	  this.pmsRightPanel.menuBar.on('click',function(button, e){
	     clearSearchBox(this.pmsRightPanel.searchText); 
	  },this);
	 
         this.pmsRightPanel.addButton.on('click',function(button, e){
	   clearSearchBox(this.pmsRightPanel.searchText); 
	   pmsCreateWindow("", "", "new",this.pmsRightPanel.grid,false);
	 },this);
	 
 	 this.pmsRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.pmsRightPanel, isPmsFile, pmsCurrentEntity,'specialKey');
	  },this);
	 this.pmsRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.pmsRightPanel, isPmsFile, pmsCurrentEntity,'searchbutton'); 
	 },this);
	 
	  // Starting Advanced Search
	  
	 this.pmsRightPanel.advSearchButton.on('click',function(button, e){
	   globalAdvancedSeacrh(this, this.pmsRightPanel, pmsCurrentEntity, isPmsFile);		 
	 },this);
	 
	 // Ending Advanced Search
	 
         this.callParent();
	loadDomainEntities(this.pmsLeftPanel.pmsitementities.store,'pms');
         this.getResponse = function(options){
           this.pmsRightPanel.formpanel.add(options);
           this.pmsRightPanel.formpanel.doLayout();
         }
       }
    //}
});

//http://124.7.228.161/ncmsui/extjs/cms/js/cmsleftpanel.js?_dc=3002
  
  Ext.require([
             'Ext.tree.*',
             'Ext.data.*',
             'Ext.grid.*'
             ]);
  
  Ext.define('NIC.CMSLeftPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.cmsleftpanel',   

	cmsitementities : Ext.create('Ext.tree.Panel', {
		hideHeaders: true,
		scope: this,
		width:250,
		useArrows: true,
		height:getWindowHeight(),
		preventHeader : true,
		autoScroll:true,
		layout: {
		    type: 'fit',
		    align: 'stretch'
		},
		rootVisible: true,
		store: Ext.create('Ext.data.TreeStore', {
			//autoLoad:false,
			proxy: { 
				type: 'memory', 
				//url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=cms&rolename='+userRole,
				//url: dataPath + '/dummy.json',
				reader : {
					type : 'json',
					root : 'AllowedEntities'
				}
			},
			root: {
				text: 'Allowed Entities',
				expanded: false
			}
		}),
		listeners: {
			render: function(panel, options){
				this.store.sort('text','ASC');
			} 
		}
	}),

	initComponent: function(){
		Ext.apply(this, {
			region:"west",
			//autoScroll:true,
			width:250,
			items : [this.cmsitementities] //, this.workflowentities, this.iaentities]
		});
		this.callParent();
		
		this.cmsitementities.on('load',function( store, records, successful){
		  this.cmsitementities.expand(true);
		  this.cmsitementities.determineScrollbars();
		  this.doLayout();
		},this);
		
	}
});

//http://124.7.228.161/ncmsui/extjs/cms/js/cmsrightpanel.js?_dc=3002

  Ext.require([
    'Writer.Grid',
    'NcmsUIModel'
  ]);
  Ext.define('NIC.CMSRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.cmsrightpanel',
     menu:showFilterByMenu('cms'),
     grid:Ext.create('Writer.Grid', {
	    title:'Listing',
	    id: 'cms-main-grid',
	    flex: 1,
	    height:'100%',
	    store: Ext.create('Ext.data.Store', {
			model: 'NcmsUIModel',
			pageSize: itemsPerPage,
			remoteSort: true,
			proxy: {
				type: 'ajax',
				url: dataservicesPath + '/search?q=&format=extjson&entitytype=Article', 
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
	      columns:[{text:'',dataIndex:'Name',width:80}],
	      viewConfig: {
		    stripeRows: true,
		    listeners: {
		        itemcontextmenu: function(view, record, node, index, e) {
	                   if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined")) {
  	                      e.stopEvent();
	                      var cMenu = contextMenu(record,"cms");
	                      if(cMenu)
	                      cMenu.showAt(e.getXY());
	                      return false;
	                   }   
		        }
		    }
		}
      }),
      searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	    maxLength :100,enforceMaxLength:true,vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    cmsSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str = cmsSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('cms-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
      }),
      addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus',"id":'cms-add-new'}),
      menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     // loadButton:Ext.create('Ext.button.Button', {text: 'Load Data' ,disabled:true, iconCls:'icon-load'}),
      searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
      advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
      initComponent: function(){
        this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	 
             //     this.loadButton,
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		  {xtype: 'tbspacer', width: 20},
		  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	      ],
        autoScroll : true,      
	region:"center",
	//forceFit:true,
	//layout: 'fit',
     	items: [this.grid]	
      });
      this.callParent();
   //   this.tabpanel.setActiveTab(0);          
     }
  });     	

  
  //http://124.7.228.161/ncmsui/extjs/cms/js/cmsmainpanel.js?_dc=3002
  
var cmsCurrentEntity = "";
var isCmsFile = "";
var currentColumn = [];
var mainStoregrid = "";
Ext.define('NIC.CMSMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.cmsmainpanel',
    
       cmsLeftPanel: Ext.create("NIC.CMSLeftPanel"),
       cmsRightPanel : Ext.create("NIC.CMSRightPanel"),
       passFn : function(){
          Ext.example.msg('Status', 'Saved successfully.');
       },
       failFn : function(){
          Ext.example.msg('Status', 'Not Saved.');
       },    
       initComponent: function(){
     	  Ext.apply(this, {
     	  region: "center",
     	  layout:"border",
     	  autoScroll:true,
     	  items:[this.cmsLeftPanel, this.cmsRightPanel] 
       });
	 this.cmsRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            cmsSearchText='';
	 });  
	 this.cmsLeftPanel.cmsitementities.on('itemclick',function(view, record, item, index, e){
	        this.cmsRightPanel.grid.doLayout();
		clearSearchBox(this.cmsRightPanel.searchText);
		if(index != 0){
		  cmsCurrentEntity = record.data.text;
		      if(cmsCurrentEntity=="Media"){
                        isCmsFile = "";  
                      }   
                      else{
                        isCmsFile = "";
                      }  
		//     cmsCreateWindow("", "", "new", this, true);
                     this.cmsRightPanel.addButton.enable(true);
                    // this.cmsRightPanel.loadButton.enable(true);
                     this.cmsRightPanel.menuBar.enable(true);
                     this.cmsRightPanel.addButton.enable(true);
                     this.cmsRightPanel.searchText.enable(true);
                     this.cmsRightPanel.searchButton.enable(true);
                     this.cmsRightPanel.advSearchButton.enable(true);
                     this.cmsRightPanel.grid.headerCt.removeAll();
                     this.cmsRightPanel.grid.getStore().removeAll();
                     this.cmsRightPanel.grid.setTitle(cmsCurrentEntity+" Listing");
		     currentColumn = Ext.JSON.decode(cmsCurrentEntity+"Column");
		     var cloneCurrentColumn = Ext.clone(currentColumn);
                     if(!allowDeleteColumn(cmsCurrentEntity)){
                    	 cloneCurrentColumn.splice(cloneCurrentColumn.length-1,1);
                     }
                     this.cmsRightPanel.grid.headerCt.add(cloneCurrentColumn);
                     this.cmsRightPanel.doLayout();
                     this.cmsRightPanel.grid.getView().refresh();
		     this.cmsRightPanel.grid.store.currentPage=1;
		     this.cmsRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+isCmsFile+ getSubQuery(cmsCurrentEntity);
		     this.cmsRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+cmsCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
		     );
                      this.cmsRightPanel.grid.getStore().load();
                }else{
                     this.cmsRightPanel.addButton.disable(true);
                     this.cmsRightPanel.searchText.disable(true);
                     this.cmsRightPanel.menuBar.disable(true);
                     this.cmsRightPanel.searchButton.disable(true);
                     this.cmsRightPanel.advSearchButton.disable(true);
                }

	 },this);

	 this.cmsRightPanel.menuBar.on('click',function(button, e){
	     clearSearchBox(this.cmsRightPanel.searchText); 
	 },this);

 	 this.cmsRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.cmsRightPanel, isCmsFile, cmsCurrentEntity,'specialKey');
	  },this);
	 this.cmsRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.cmsRightPanel, isCmsFile, cmsCurrentEntity,'searchbutton'); 
	 },this);
            
          this.cmsRightPanel.addButton.on('click',function(button, e){
	     clearSearchBox(this.cmsRightPanel.searchText);
             cmsCreateWindow("", "", "new",this.cmsRightPanel.grid,false); 
          },this);
	  
 	  // Starting Advanced Search
	  
	 this.cmsRightPanel.advSearchButton.on('click',function(button, e){
            globalAdvancedSeacrh(this, this.cmsRightPanel, cmsCurrentEntity, isCmsFile);
	 },this);

	 // Ending Advanced Search
	 
	 loadDomainEntities(this.cmsLeftPanel.cmsitementities.store,'cms');
         this.callParent();
}
});

//http://124.7.228.161/ncmsui/extjs/user/js/userleftpanel.js?_dc=3002

Ext.require([
    'Ext.tree.*',
    'Ext.data.*',
    'Ext.grid.*'
]);
Ext.define('NIC.USERLeftPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.userleftpanel',   

   useritementities : Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
	forceFit: true,
	width:250,
	useArrows: true,
	height:getWindowHeight(),
	layout: 'fit',
	rootVisible: true,
	store: Ext.create('Ext.data.TreeStore', {
               autoLoad:false,     
	proxy: {
	    type: 'memory',
  	    //url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=user&rolename='+userRole,
   	    //url: dataPath + '/dummy.json',
            reader : {
				type : 'json',
				root : 'AllowedEntities'
		     }
	},
	root: {
            text: 'Allowed Entities',
            id: 'AllowedEntities',
            expanded: false
        } 
     }), 
     listeners: {
	 render: function(panel, options){
	   this.store.sort('text','ASC');
	 } 
       } 
   }),
   
  initComponent: function(){
     	Ext.apply(this, {
	  region:"west",
	  autoHeight:true,
	  autoScroll:true,
	  width:250,
	  scroll: 'both',
  	 items : [this.useritementities] 
     });
       this.callParent();
       this.useritementities.on('load',function( store, records, successful){
       this.useritementities.expand(true);
	  this.doLayout();
       },this);
    }
});


//http://124.7.228.161/ncmsui/extjs/user/js/userrightpanel.js?_dc=3002
 
  Ext.require([
   'Writer.Grid',
   'NcmsUIModel'
  ]);
  Ext.define('NIC.USERRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.userrightpanel',
     menu:  showFilterByMenu('user'),
     grid:  Ext.create('Writer.Grid', {
                    title:'Listing', 
		    id: 'user-main-grid',
		    flex: 1,
		    height:'100%',
		    store: Ext.create('Ext.data.Store', {
				model: 'NcmsUIModel',
				pageSize: itemsPerPage,
				remoteSort: true,
				proxy: {
					type: 'ajax',
					//url: dataservicesPath + '/search?q=&format=extjson&entitytype=UserProfile',
					//url: dataPath + '/dummy.json',
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
                     columns:[{text:'',dataIndex:'Name',width:80}],
        		        viewConfig: {
        		            stripeRows: true,
        		            listeners: {
        		                itemcontextmenu: function(view, record, node, index, e) {
        		                   if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined")) {
            		                      e.stopEvent();
        		                      var cMenu = contextMenu(record,"user");
        		                      if(cMenu)
          		                       cMenu.showAt(e.getXY());
        		                      return false;
        		                   }  
        		               }
        		            }
        		        }
     }), 
     searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	  maxLength :100,enforceMaxLength:true,vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    userSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str = userSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('user-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
     }),
     addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus'}),
     menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
     advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
     initComponent: function(){
        this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	  
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		  {xtype: 'tbspacer', width: 20},
		  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	],
        autoScroll : true, 
	region:"center",
	items: [this.grid]
     });
     this.callParent();
     }
  });     	
  
 // http://124.7.228.161/ncmsui/extjs/user/js/usermainpanel.js?_dc=3002
  
  var userCurrentEntity='';
  var api_key = Ext.util.Cookies.get("api_key");
  var authUsername = Ext.util.Cookies.get("authUsername");
  var currentColumn = [];
  Ext.define('NIC.USERMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.usermainpanel',
       userLeftPanel: Ext.create("NIC.USERLeftPanel"),
       userRightPanel : Ext.create("NIC.USERRightPanel"),
       initComponent: function(){
	     	  Ext.apply(this, {
	     	  region: "center",
	     	  layout:"border",
	     	  closeAction:'hide',
	     	  items:[this.userLeftPanel, this.userRightPanel] 
       });
         
         this.userRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            userSearchText='';
	 });  
	 this.userLeftPanel.useritementities.on('itemclick',function(view, record, item, index, e){
		userCurrentEntity = record.data.text;
		clearSearchBox(this.userRightPanel.searchText); 
		this.userRightPanel.grid.doLayout();
	        if(index != 0){
		     //   userCreateWindow("", "", "new",this,true);
   		        this.userRightPanel.searchText.enable(true);
                        this.userRightPanel.addButton.enable(true);
                        this.userRightPanel.searchButton.enable(true);
                        this.userRightPanel.menuBar.enable(true);
                        this.userRightPanel.advSearchButton.enable(true);
		        this.userRightPanel.grid.headerCt.removeAll();
		        this.userRightPanel.grid.getStore().removeAll();
		        this.userRightPanel.grid.setTitle(userCurrentEntity+" Listing");
			currentColumn = Ext.JSON.decode(userCurrentEntity+"Column");
			var cloneCurrentColumn = Ext.clone(currentColumn);
			if(!allowDeleteColumn(cmsCurrentEntity)){
			    cloneCurrentColumn.splice(cloneCurrentColumn.length-1,1);
			}
		        this.userRightPanel.grid.headerCt.add(currentColumn);
		        this.userRightPanel.doLayout();
			this.userRightPanel.grid.store.currentPage=1;
			this.userRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+getSubQuery(userCurrentEntity);
			this.userRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+userCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
			); 
		        this.userRightPanel.grid.store.load();
                }
                else{
                  this.userRightPanel.addButton.disable(true);
                  this.userRightPanel.menuBar.disable(true);
                  this.userRightPanel.searchText.disable(true);
                  this.userRightPanel.searchButton.disable(true);
                  this.userRightPanel.advSearchButton.disable(true);
                }
	  },this);
	 
	  this.userRightPanel.menuBar.on('click',function(button, e){
	     clearSearchBox(this.userRightPanel.searchText); 
	  },this);
	  
          this.userRightPanel.addButton.on('click',function(button, e){
	     clearSearchBox(this.userRightPanel.searchText);
            userCreateWindow("", "", "new",this.userRightPanel.grid,false);
	  },this);
      
 	 this.userRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.userRightPanel, '', userCurrentEntity,'specialKey');
	  },this);
	 this.userRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.userRightPanel, '', userCurrentEntity,'searchbutton'); 
	 },this);
	 
	   // Starting Advanced Search
	  
	 this.userRightPanel.advSearchButton.on('click',function(button, e){
	   globalAdvancedSeacrh(this, this.userRightPanel, userCurrentEntity, "");	 
	 },this);
	 
	 // Ending Advanced Search
	  loadDomainEntities(this.userLeftPanel.useritementities.store,'user');
         this.callParent();
         this.getResponse = function(options){
           this.userRightPanel.formpanel.add(options);
           this.userRightPanel.formpanel.doLayout();
         }
       }
    //}
});


 // http://124.7.228.161/ncmsui/extjs/feed/js/feedleftpanel.js?_dc=3002
  
  Ext.require([
    'Ext.tree.*',
    'Ext.data.*',
    'Ext.grid.*'
]);
Ext.define('NIC.FEEDLeftPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.feedleftpanel',   

   feeditementities : Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
	forceFit: true,
	width:250,
	useArrows: true,
	height:getWindowHeight(),
	layout: 'fit',
	rootVisible: true,
	store: Ext.create('Ext.data.TreeStore', {
               autoLoad:false,
	proxy: {
	    type: 'memory',
	    //url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=feed&rolename='+userRole,
	   // url: dataPath + '/dummy.json',
            reader : {
				type : 'json',
				root : 'AllowedEntities'
			}
	},
	root: {
            text: 'Allowed Entities',
            id: 'AllowedEntities',
            expanded: false
        } 
     }), 
   listeners: {
	 render: function(panel, options){
	   this.store.sort('text','ASC');
	 } 
       } 
   }),
  
  initComponent: function(){
     	Ext.apply(this, {
	  region:"west",
	  autoHeight:true,
	  autoScroll:true,
	  width:250,
	  scroll: 'both',
  	 items : [this.feeditementities] 
     });
       this.callParent();
      this.feeditementities.on('load',function( store, records, successful){
      this.feeditementities.expand(true);
	  this.doLayout();
      },this);
    }
});


//http://124.7.228.161/ncmsui/extjs/feed/js/feedrightpanel.js?_dc=3002

  Ext.require([
   'Writer.Grid',
   'NcmsUIModel'
  ]);
  Ext.define('NIC.FEEDRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.feedrightpanel',
     menu:showFilterByMenu('feed'),
     grid:  Ext.create('Writer.Grid', {
                    title:'Listing', 
		    id: 'feed-main-grid',
		    flex: 1,
		    height:'100%',
		    store: Ext.create('Ext.data.Store', {
				model: 'NcmsUIModel',
				pageSize: itemsPerPage,
				remoteSort: true,
				proxy: {
					type: 'ajax',
					url: dataservicesPath + '/search?q=&format=extjson&entitytype=ContentPartner',
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
                     columns:[{text:'',dataIndex:'Name',width:80}],
        		        viewConfig: {
        		            stripeRows: true,
        		            listeners: {
        		                itemcontextmenu: function(view, record, node, index, e) {
        		                  if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined")) {
            		                     e.stopEvent();
        		                     var cMenu = contextMenu(record,"feed");
        		                     if(cMenu)
          		                      cMenu.showAt(e.getXY());
        		                     return false;
        		                   }  
        		               }
        		            }
        		        }
     }), 
     searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	  maxLength :100,enforceMaxLength:true,vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    feedSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str = feedSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('feed-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
     }),
     addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus'}),
     menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
     advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
     initComponent: function(){
        this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	  
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		  {xtype: 'tbspacer', width: 20},
		  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	],
        autoScroll : true, 
	region:"center",
	items: [this.grid]
     });
     this.callParent();
     }
  });     	

  
 // http://124.7.228.161/ncmsui/extjs/feed/js/feedmainpanel.js?_dc=3002
  
  
  var feedCurrentEntity='';
  var currentColumn = [];
  Ext.define('NIC.FEEDMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.feedmainpanel',
       feedLeftPanel: Ext.create("NIC.FEEDLeftPanel"),
       feedRightPanel : Ext.create("NIC.FEEDRightPanel"),
       initComponent: function(){
	     	  Ext.apply(this, {
	     	  region: "center",
	     	  layout:"border",
	     	  closeAction:'hide',
	     	  items:[this.feedLeftPanel, this.feedRightPanel] 
       });
         this.feedRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            feedSearchText='';
	 });         
	 this.feedLeftPanel.feeditementities.on('itemclick',function(view, record, item, index, e){
		feedCurrentEntity = record.data.text;
		clearSearchBox(this.feedRightPanel.searchText); 
		this.feedRightPanel.grid.doLayout();
	        if(index != 0){
		    //    feedCreateWindow("", "", "new",this,true);
   		        this.feedRightPanel.searchText.enable(true);
                        this.feedRightPanel.addButton.enable(true);
                        this.feedRightPanel.menuBar.enable(true);
                        this.feedRightPanel.searchButton.enable(true);
                        this.feedRightPanel.advSearchButton.enable(true);
		        this.feedRightPanel.grid.headerCt.removeAll();
		        this.feedRightPanel.grid.getStore().removeAll();
		        this.feedRightPanel.grid.setTitle(feedCurrentEntity+" Listing");
		     currentColumn = Ext.JSON.decode(feedCurrentEntity+"Column");
		     var cloneCurrentColumn = Ext.clone(currentColumn);
                     if(!allowDeleteColumn(cmsCurrentEntity)){
                    	 cloneCurrentColumn.splice(cloneCurrentColumn.length-1,1);
                     }
		        this.feedRightPanel.grid.headerCt.add(currentColumn);
		        this.feedRightPanel.doLayout();
			this.feedRightPanel.grid.store.currentPage=1;
			this.feedRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+getSubQuery(feedCurrentEntity);
			this.feedRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+feedCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
			); 
		        this.feedRightPanel.grid.store.load();
                }
                else{
                  this.feedRightPanel.addButton.disable(true);
                  this.feedRightPanel.menuBar.disable(true);
                  this.feedRightPanel.searchText.disable(true);
                  this.feedRightPanel.searchButton.disable(true);
                  this.feedRightPanel.advSearchButton.disable(true);
                }
	  },this);
	 
	  this.feedRightPanel.menuBar.on('click',function(button, e){
	     clearSearchBox(this.feedRightPanel.searchText); 
	  },this);
	  
          this.feedRightPanel.addButton.on('click',function(button, e){
	    clearSearchBox(this.feedRightPanel.searchText); 
            feedCreateWindow("", "", "new",this.feedRightPanel.grid,false);
	  },this);
      
 	 this.feedRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.feedRightPanel, '', feedCurrentEntity,'specialKey');
	  },this);
	 this.feedRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.feedRightPanel, '', feedCurrentEntity,'searchbutton'); 
	 },this);
	  
	  // Starting Advanced Search
	  
	 this.feedRightPanel.advSearchButton.on('click',function(button, e){
	    globalAdvancedSeacrh(this, this.feedRightPanel, feedCurrentEntity, "");	 
	 },this);
	 
	 // Ending Advanced Search
	 
         this.callParent();
	loadDomainEntities(this.feedLeftPanel.feeditementities.store,'feed');
         this.getResponse = function(options){
           this.feedRightPanel.formpanel.add(options);
           this.feedRightPanel.formpanel.doLayout();
         }
       }
    //}
});

//http://124.7.228.161/ncmsui/extjs/admin/js/adminleftpanel.js?_dc=3002
  
  
  Ext.require([
             'Ext.tree.*',
             'Ext.data.*',
             'Ext.grid.*'
             ]);
Ext.define('NIC.ADMINLeftPanel', {
	extend: 'Ext.panel.Panel',
	alias: 'widget.adminleftpanel',   

	adminitementities : Ext.create('Ext.tree.Panel', {
		hideHeaders: true,
		scope: this,
		layout: 'fit',
		forceFit: true,
		width:250,
		useArrows: true,
		height:getWindowHeight(),
		rootVisible: true,
		store: Ext.create('Ext.data.TreeStore', {
			autoLoad:false,
			proxy: {
				type: 'memory',
				//url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=admin&rolename=' + userRole,
				//url: dataPath + '/dummy.json',
				reader : {
					type : 'json',
					root : 'AllowedEntities'
				}
			},
			root: {
				text: 'Allowed Entities',
				expanded: false
			} 
		}), 
		listeners: {
			render: function(panel, options){
				this.store.sort('text','ASC');
			} 
		} 
	}),

	initComponent: function(){
		Ext.apply(this, {
			region:"west",
			autoHeight:true,
			autoScroll:true,
			width:250,
	                scroll: 'both',
			items : [this.adminitementities] 
		});
		this.callParent();
		this.adminitementities.on('load',function( store, records, successful){
		this.adminitementities.expand(true);
		   this.doLayout();
	        },this);
	}
	
});


//http://124.7.228.161/ncmsui/extjs/admin/js/adminrightpanel.js?_dc=3002

  Ext.require([
   'Writer.Grid',
   'NcmsUIModel'
  ]);
  Ext.define('NIC.ADMINRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.adminrightpanel',
     menu:showFilterByMenu('admin'),
     grid:  Ext.create('Writer.Grid', {
                    title:'Listing', 
		    id: 'admin-main-grid',
		    flex: 1,
		    height:'100%',
		    store: Ext.create('Ext.data.Store', {
				model: 'NcmsUIModel',
				pageSize: itemsPerPage,
				remoteSort: true,
				proxy: {
					type: 'ajax',
					url: dataservicesPath + '/search?q=&format=extjson&entitytype=admin',
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
                     columns:[{text:'',dataIndex:'Name',width:80}],
        		        viewConfig: {
        		            stripeRows: true,
        		            listeners: { /*
        		                itemcontextmenu: function(view, record, node, index, e) {
            		                    e.stopEvent();
        		                   var cMenu = contextMenu(record,"admin");
        		                   if(cMenu)
          		                     cMenu.showAt(e.getXY());
        		                   return false;
        		                } */
        		            }
        		        }
     }), 
     searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	  maxLength :100, enforceMaxLength:true, vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    adminSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str = adminSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('admin-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
     }),
     addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus'}),
     menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
     advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
     initComponent: function(){
        this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	  
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		  {xtype: 'tbspacer', width: 20},
		  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	],
        autoScroll : true, 
	region:"center",
	items: [this.grid]
     });
     this.callParent();
     }
  });     	

  
  //http://124.7.228.161/ncmsui/extjs/admin/js/adminmainpanel.js?_dc=3002
  
  var adminCurrentEntity='';
  var currentColumn = [];
  Ext.define('NIC.ADMINMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminmainpanel',
       adminLeftPanel: Ext.create("NIC.ADMINLeftPanel"),
       adminRightPanel : Ext.create("NIC.ADMINRightPanel"),
       initComponent: function(){
	     	  Ext.apply(this, {
	     	  region: "center",
	     	  layout:"border",
	     	  closeAction:'hide',
	     	  items:[this.adminLeftPanel, this.adminRightPanel] 
       });
         this.adminRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            adminSearchText='';
	 });          
	 this.adminLeftPanel.adminitementities.on('itemclick',function(view, record, item, index, e){
		adminCurrentEntity = record.data.text;
		this.adminRightPanel.grid.doLayout();
		clearSearchBox(this.adminRightPanel.searchText); 
	        if(index != 0){
	        
	                if(adminCurrentEntity=="AppAdmin" || adminCurrentEntity=="Feedback"){
                           this.adminRightPanel.addButton.disable(true);
                        }   
                        else{
                           this.adminRightPanel.addButton.enable(true);
                        }    
	              //  adminCreateWindow("", "", "new",this,true); 
   		        this.adminRightPanel.searchText.enable(true);
                        this.adminRightPanel.searchButton.enable(true);
                        this.adminRightPanel.menuBar.enable(true);
                        this.adminRightPanel.advSearchButton.enable(true);
		        this.adminRightPanel.grid.headerCt.removeAll();
		        this.adminRightPanel.grid.getStore().removeAll();
		        this.adminRightPanel.grid.setTitle(adminCurrentEntity+" Listing");
  		        currentColumn = Ext.JSON.decode(adminCurrentEntity+"Column");
                        if(!allowDeleteColumn(cmsCurrentEntity)){
                    	  currentColumn.splice(currentColumn.length-1,1);
                        }
		        this.adminRightPanel.grid.headerCt.add(currentColumn);
		        this.adminRightPanel.doLayout();
			this.adminRightPanel.grid.store.currentPage=1;
			this.adminRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+getSubQuery(adminCurrentEntity);
			this.adminRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+adminCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
			);
		        this.adminRightPanel.grid.store.load();
                }
                else{
                  this.adminRightPanel.addButton.disable(true);
                  this.adminRightPanel.menuBar.disable(true);
                  this.adminRightPanel.searchText.disable(true);
                  this.adminRightPanel.searchButton.disable(true);
                  this.adminRightPanel.advSearchButton.disable(true);
                }
	  },this);
	 
 	 this.adminRightPanel.menuBar.on('click',function(button, e){
	     clearSearchBox(this.adminRightPanel.searchText); 
	 },this);

	  
          this.adminRightPanel.addButton.on('click',function(button, e){
	    clearSearchBox(this.adminRightPanel.searchText); 
            adminCreateWindow("", "", "new",this.adminRightPanel.grid,false);
	  },this);
      
 	 this.adminRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.adminRightPanel, '', adminCurrentEntity,'specialKey');
	  },this);
	 this.adminRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.adminRightPanel, '', adminCurrentEntity,'searchbutton'); 
	 },this);
	 
	  // Starting Advanced Search
	  
	 this.adminRightPanel.advSearchButton.on('click',function(button, e){
	    globalAdvancedSeacrh(this, this.adminRightPanel, adminCurrentEntity, "");
	 },this);
	 
	 // Ending Advanced Search
	
         this.callParent();
	loadDomainEntities(this.adminLeftPanel.adminitementities.store,'admin');
         this.getResponse = function(options){
           this.adminRightPanel.formpanel.add(options);
           this.adminRightPanel.formpanel.doLayout();
         }
       }
    //}
});


//  http://124.7.228.161/ncmsui/extjs/domain/js/domainleftpanel.js?_dc=3002
  
  Ext.require([
    'Ext.tree.*',
    'Ext.data.*',
    'Ext.grid.*'
]);
Ext.define('NIC.DOMAINLeftPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.domainleftpanel',   

   domainitementities : Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,     
	layout: 'fit',
	width:250,
	useArrows: true,
	height:getWindowHeight(),
	rootVisible: true,
	store: Ext.create('Ext.data.TreeStore', {
               autoLoad:false,
	proxy: {
	    type: 'memory',
	//url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=domain&rolename='+userRole,
	//url: dataPath + '/dummy.json',
            reader : {
				type : 'json',
				root : 'AllowedEntities'
		}
	},
	root: {
            text: 'Allowed Entities',
            id: 'AllowedEntities',
            expanded: false
        } 
     }), 
     listeners: {
	 render: function(panel, options){
	   this.store.sort('text','ASC');
	 } 
       } 
   }),
   
  initComponent: function(){
     	Ext.apply(this, {
	  region:"west",
	  autoHeight:true,
	  autoScroll:true,
	  width:250,
	  scroll: 'both',
  	 items : [this.domainitementities] 
     });
       this.callParent();
	this.domainitementities.on('load',function( store, records, successful){
	this.domainitementities.expand(true);
	    this.doLayout();
	},this);
    }
});


//http://124.7.228.161/ncmsui/extjs/domain/js/domainrightpanel.js?_dc=3002

  Ext.require([
   'Writer.Grid',
   'NcmsUIModel'
  ]);
  Ext.define('NIC.DOMAINRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.domainrightpanel',
     menu:showFilterByMenu('domain'),
     grid:  Ext.create('Writer.Grid', {
                    title:'Listing', 
		    id: 'domain-main-grid',
		    flex: 1,
		    height:'100%',
		    store: Ext.create('Ext.data.Store', {
				model: 'NcmsUIModel',
				pageSize: itemsPerPage,
				remoteSort: true,
				proxy: {
					type: 'ajax',
					url: dataservicesPath + '/search?q=&format=extjson&entitytype=domain',
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
                     columns:[{text:'',dataIndex:'Name',width:80}],
        		        viewConfig: {
        		            stripeRows: true,
        		            listeners: {
        		                itemcontextmenu: function(view, record, node, index, e) {
        		                  if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined")) {
            		                      e.stopEvent();
        		                      var cMenu = contextMenu(record,"domain");
        		                      if(cMenu)
          		                        cMenu.showAt(e.getXY());
        		                      return false;
        		                   }  
        		                }
        		            }
        		        }
     }), 
     searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	    maxLength :100,enforceMaxLength:true,vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    domainSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str = domainSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('domain-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
     }),
     addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus'}),
     menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
     advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
     initComponent: function(){
        this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	  
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		  {xtype: 'tbspacer', width: 20},
		  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	],
        autoScroll : true, 
	region:"center",
	items: [this.grid]
     });
     this.callParent();
     }
  });     	

 // http://124.7.228.161/ncmsui/extjs/domain/js/domainmainpanel.js?_dc=3002
  
  var domainCurrentEntity='';
  var currentColumn = [];
  Ext.define('NIC.DOMAINMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.domainmainpanel',
       domainLeftPanel: Ext.create("NIC.DOMAINLeftPanel"),
       domainRightPanel : Ext.create("NIC.DOMAINRightPanel"),
       initComponent: function(){
	     	  Ext.apply(this, {
	     	  region: "center",
	     	  layout:"border",
	     	  closeAction:'hide',
	     	  items:[this.domainLeftPanel, this.domainRightPanel] 
       });
         this.domainRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            domainSearchText='';
	 });         
	 this.domainLeftPanel.domainitementities.on('itemclick',function(view, record, item, index, e){
		domainCurrentEntity = record.data.text;
		this.domainRightPanel.grid.doLayout();
		clearSearchBox(this.domainRightPanel.searchText); 
	        if(index != 0){
		   //     domainCreateWindow("", "", "new",this,true); 
   		        this.domainRightPanel.searchText.enable(true);
                        this.domainRightPanel.addButton.enable(true);
                        this.domainRightPanel.searchButton.enable(true);
                        this.domainRightPanel.menuBar.enable(true);
                        this.domainRightPanel.advSearchButton.enable(true);
		        this.domainRightPanel.grid.headerCt.removeAll();
		        this.domainRightPanel.grid.getStore().removeAll();
		        this.domainRightPanel.grid.setTitle(domainCurrentEntity+" Listing");
		     currentColumn = Ext.JSON.decode(domainCurrentEntity+"Column");
		     var cloneCurrentColumn = Ext.clone(currentColumn);
                     if(!allowDeleteColumn(cmsCurrentEntity)){
                    	 cloneCurrentColumn.splice(cloneCurrentColumn.length-1,1);
                     }
		        this.domainRightPanel.grid.headerCt.add(currentColumn);
		        this.domainRightPanel.doLayout();
			this.domainRightPanel.grid.store.currentPage=1;
			this.domainRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+getSubQuery(domainCurrentEntity);
			this.domainRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+domainCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
			); 
		        this.domainRightPanel.grid.store.load();
                }
                else{
                  this.domainRightPanel.addButton.disable(true);
                  this.domainRightPanel.menuBar.disable(true);
                  this.domainRightPanel.searchText.disable(true);
                  this.domainRightPanel.searchButton.disable(true);
                  this.domainRightPanel.advSearchButton.disable(true);
                }
	  },this);
	 
	  this.domainRightPanel.menuBar.on('click',function(button, e){
	     clearSearchBox(this.domainRightPanel.searchText); 
	  },this);
	 
          this.domainRightPanel.addButton.on('click',function(button, e){
	    clearSearchBox(this.domainRightPanel.searchText); 
            domainCreateWindow("", "", "new",this.domainRightPanel.grid,false); 
	  },this);
      
 	 this.domainRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.domainRightPanel, '', domainCurrentEntity,'specialKey');
	  },this);
	 this.domainRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.domainRightPanel, '', domainCurrentEntity,'searchbutton'); 
	 },this);
	 
	  // Starting Advanced Search
	  
	 this.domainRightPanel.advSearchButton.on('click',function(button, e){
	    globalAdvancedSeacrh(this, this.domainRightPanel, domainCurrentEntity, "");	 
	 },this);
	 
	 // Ending Advanced Search
	 
         this.callParent();
	loadDomainEntities(this.domainLeftPanel.domainitementities.store,'domain');
         this.getResponse = function(options){
           this.domainRightPanel.formpanel.add(options);
           this.domainRightPanel.formpanel.doLayout();
         }
       }
    //}
});


  //http://124.7.228.161/ncmsui/extjs/workflow/js/workflowform.js?_dc=3002
  
Ext.define('WorkflowModel', {
    extend: 'Ext.data.Model',
    proxy: {
	type: 'jsonp',
	url: dataservicesPath + '/search?q=&format=extjson',
	reader: {
		  type: 'json',
		  root: 'Collections.Media',
		  totalProperty: 'Collections.Count' 
	}
    },
    fields: [
	{name: 'EventName'},
	{name: 'UserName'},
	// {name: 'RoleName'},
	{name: 'DisplayName'},
	{name: 'AssociatedStage'},
	{name: 'AssociatedStatus'},
	{name: 'PossibleEvent'}
    ]
});


var WorkflowModelForm = [
{
 title:'WorkflowModel',
 xtype:'form',
 id:'WorkflowModelForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:150
 },
 items:[
		    {
	name :"Id",
		  readOnly:true,
		  xtype:"textfield",
		  id:"WorkflowModelId",
	          fieldLabel : "Id",
						  allowBlank : true
		  
    },

                    {
     name :"EntityType",
           id:"WorkflowModelEntityType",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Entity Type",
               hidden:true,
                    value:"WorkflowModel",
                                    allowBlank : true
              
},

 {
	name :"WorkflowModelName",
	id:"WorkflowModelWorkflowModelName",
	xtype:"textfield",
	enforceMaxLength:true,
	maxLength :100,
	vtype:"alphanumMid",
	fieldLabel : "WorkflowModelName *",
	allowBlank : false,
	listeners:{
	  blur:function(){
	    var str = trim(this.getValue());
            str = str.replace(/ +(?= )/g,''); 
	    this.setValue(str);
	  }
	}
    },
  {
     name :"Key",
     fieldLabel : "Key *",
     xtype:"combobox",
     id:"WorkflowModelKey",
     isFormField:false,
      editable:false,
     multiSelect:true,
     store:Ext.create('Ext.data.ArrayStore', {
            fields: ["Key"],
            data : Ext.workflowModel
     }),
     queryMode:"local",
     displayField:"Key",
     emptyText:"",
     allowBlank : false
  },
                                               {
     name :"CreatedDate",
           id:"WorkflowModelCreatedDate",
               readOnly:true,
               fieldLabel : "Created Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                         if(this.getValue() == null || this.getValue() == ""){
                this.setValue(date);
             } 
            	   }
	 },
	                hidden:true,
                                              allowBlank : true
              
},

                                             {
     name :"CreatedBy",
           id:"WorkflowModelCreatedBy",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Created By",
               hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                          allowBlank : true
              
},

                                             {
     name :"LastModifiedBy",
           id:"WorkflowModelLastModifiedBy",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Last Modified By",
               hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                          allowBlank : true
              
},

                                             {
     name :"LastModifiedDate",
           id:"WorkflowModelLastModifiedDate",
               readOnly:true,
               fieldLabel : "Last Modified Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                            this.setValue(date);
            	   }
	 },
	                hidden:true,
                                              allowBlank : true
              
},

{
     name :"Site",
           id:"WorkflowModelSite",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Site",
               hidden:true,
                                              allowBlank : true
              
},

                                             {
     name :"Version",
           id:"WorkflowModelVersion",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Version",
               hidden:true,
                                              allowBlank : true
              
}  
   
]}];

     var workflowList =  Ext.create('Ext.form.ComboBox', {
	name :"WorkflowName",
	id:'WorkflowModelMapWorkflowName',
	fieldLabel : "Choose Workflow *",
	anchor:'50%',
	labelWidth:150,
        editable:false,
	store:createStore('Workflow',''),
	queryMode:"remote",
	displayField:"WorkflowName",
	valueField:"WorkflowName",
	emptyText:"",
	allowBlank : false,
	listeners:{
		focus: function(){
			this.store.load();
		}
	}
     });
     var modelMapPanel2 = Ext.create('Ext.form.Panel',{id: 'modelmap-mainform', region:'north',height:82,bodyPadding:5,items:[ 
     {
	          name :"Id",
		  readOnly:true,
		  xtype:"textfield",
		  id:'WorkflowModelMapId',
	          fieldLabel : "Id",
	          allowBlank : true
     },
      {
	  name :"ModelMapName",
	  id:"ModelMapName",
	  xtype:"textfield",
	  enforceMaxLength:true,
	  labelWidth:150,
          maxLength :100,
	  vtype:"alphanumMid",
	  fieldLabel : "ModelMapName *",
	  allowBlank : false,
	  listeners:{
	  blur:function(){
	    var str = trim(this.getValue());
            str = str.replace(/ +(?= )/g,''); 
	    this.setValue(str);
	  }
         }
       },
       workflowList,
      {
        name :"CreatedDate",
           id:"WorkflowModelMapCreatedDate",
               readOnly:true,
               fieldLabel : "Created Date",
        xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                         if(this.getValue() == null || this.getValue() == ""){
                this.setValue(date);
             } 
            	   }
	 },
	                hidden:true,
                                              allowBlank : true
              
   },
  {
     name :"CreatedBy",
           id:"WorkflowModelMapCreatedBy",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Created By",
               hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
      allowBlank : true
   },

                                             {
     name :"LastModifiedBy",
           id:"WorkflowModelMapLastModifiedBy",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Last Modified By",
               hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                          allowBlank : true
              
},
                                             {
     name :"LastModifiedDate",
           id:"WorkflowModelMapLastModifiedDate",
               readOnly:true,
               fieldLabel : "Last Modified Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                            this.setValue(date);
            	   }
	 },
	                hidden:true,
                                              allowBlank : true
              
},

{
     name :"Site",
           id:"WorkflowModelMapSite",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Site",
               hidden:true,
                                              allowBlank : true
              
},

                                             {
     name :"Version",
           id:"WorkflowModelMapVersion",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Version",
               hidden:true,
                                              allowBlank : true
              
}
 ],width:500,border:false});  
     
     var modelMapPanel3    =  Ext.create('Ext.form.Panel',{title:'Mapping Data',region:'south',items:[],height:612,bodyPadding:5}); 
     var workflowModelList =  Ext.create('Ext.form.ComboBox', {
        name :"WorkflowModelName",
	isFormField:false,
	labelWidth:150,
	editable:false,
        fieldLabel : "Choose WorkflowModel *",
        anchor:'50%', 
	store: createStore('WorkflowModel',''),
	queryMode:"remote",
        displayField:"WorkflowModelName",
	valueField:"WorkflowModelName",
        emptyText:"",
        allowBlank : false,
	listeners: {
	      select: function(combo, selection) {
		    var post = selection[0];
		    var workflowModelProperties =  Ext.JSON.decode(post.get('Properties'));
		    if(Ext.isArray(workflowModelProperties.Property)){
		        loadPropertyForm(workflowModelProperties.Property);
		    }else{
			loadPropertyForm(new Array(workflowModelProperties.Property));
		    }
	      },
	      focus: function(combo){
		this.store.load();
	      }
	}        
     });
    
     var modelMapPanel1 = Ext.create('Ext.form.Panel',{region:'center',items:[workflowModelList],height:40,bodyPadding:5,border:false});
     
     var workflowAutoGenerateGrid = Ext.create('Ext.grid.Panel', {
	  height:600,
	  border:false,
	  autoScroll:true,
	  id:'workflow-event-grid',
	  forceFit:true,
	columnLines:true, 
	  store: Ext.create('Ext.data.Store', {
	      model: 'EventListModel',
	      remoteSort: true
	  }),
	  columns: [ 
	      {header: 'Event Name',width:130,  dataIndex:'EventName',field:{
		    readOnly:true ,allowBlank : false},sortable:false
	      },
	      {header: 'Display Name',width:130,  id: 'displayName', dataIndex:'DisplayName',field:{
		    allowBlank : false,enforceMaxLength:true, maxLength :50,vtype:"alphanumMid",
		    listeners:{
			    blur:function(){
			      var str = trim(this.getValue());
			      str = str.replace(/ +(?= )/g,'');
			      this.setValue(str);
			    }
			} },sortable:false
	      },
	      {header: 'Stage Name',dataIndex: 'AssociatedStage',width: 140, field: {
		xtype:'combobox',editable:false,store:createStore('Stage',''),queryMode:"remote",displayField:"StageName",valueField:"StageName",
		emptyText:"",allowBlank : false},sortable:false
	      },
	      {header: 'Status',dataIndex: 'AssociatedStatus',width: 140, field: {
		xtype:'combobox',editable:false,store:createStore('Status',''),queryMode:"remote",displayField:"StatusName",valueField:"StatusName",
		emptyText:"",allowBlank : false},sortable:false
	      },
	      {header: 'Next Possible Event',dataIndex: 'PossibleEvent',width: 140, field: {
		id:'WorkflowUsermapGridPossibleEvent',
		xtype:'combobox',editable:false, multiSelect:true,
		store:Ext.create('Ext.data.ArrayStore', { fields: ["EventName"],data : []}),
			    queryMode:"local",
			    displayField:"EventName",valueField:"EventName",emptyText:"",
			    listeners:{
				    focus:function(combo){
					    this.store.loadData(workflowGridPossibleEventData);
				    },
				    render:function(combo){
					    //this.store.sort('EventName','ASC');
				    }
			    } 
		  },sortable:false
	      }		
	  ],
	  selModel: {
		selType: 'cellmodel'
	  },      
	  plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit: 1
		    })]
     });
     
     
     var WorkflowModelMapForm = [
	{
	title:'WorkflowModelMap',
	xtype:'form',
	layout:'border',
	id:'WorkflowModelMapForm1',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:150
	},
	  items:[
	    modelMapPanel1,
	    modelMapPanel2,
	    modelMapPanel3
	  ]
       }];
      
     var workflowPanel2 = Ext.create('Ext.form.Panel',{title:'WorkFlow Details',region:'south',scroll:true,autoScroll:true,items:[workflowAutoGenerateGrid],height:640});
     var workflowPanel1 = Ext.create('Ext.form.Panel',{region:'center',bodyPadding:5,
    	    items:[                                                                                       
    	      {
    		  name :"EventName",
    		  id:"WorkflowEventEventName",
    		  xtype:"combobox",
    		  editable:false,
    		  fieldLabel : "Select Events",
    		  multiSelect:true,
		  labelWidth:150,
    		  store:createStore('Event',''),
    		  queryMode:"remote",
    		  displayField:"EventName",
    		  valueField:"EventName",
    		  emptyText:"", 
    		  listeners:{
		    focus:function(combo){
    			this.store.load();
    		    },
    		    beforerender:function(combo){
    			this.store.load();
    		    },
    		    render:function(combo){
    			//this.store.sort('EventName','ASC');
    		    },
    		    blur: function(combo, selection) {
    		    	if(combo.getValue() != ''){
			        //workflowAutoGenerateGrid.store.removeAll();
    		    		var eventArray = combo.getValue().toString().split(",");
    		    		loadWorkflowEventsGrid(eventArray);	    		
    		    	}
    		    }   
    		  }
    	    }
    	  ],border:false,height:50});
     
  var workflowPanel3 = Ext.create('Ext.form.Panel',{id:'WorkflowForm1',bodyPadding:5,region:'north',border:false,items:[ 
      {
	  name :"Id",
	  id:"WorkflowId",
	  readOnly:true,
	  xtype:"textfield",
	  labelWidth:150,
	  fieldLabel : "Id",
	  allowBlank : true
      },
          {
       name :"WorkflowName",
       id:"WorkflowWorkflowName",
       xtype:"textfield",
       labelWidth:150,
       fieldLabel : "Workflow Name *",
       listeners:{
	  blur:function(){
	    var str = trim(this.getValue());
            str = str.replace(/ +(?= )/g,''); 
	    this.setValue(str);
	  }
	},
        enforceMaxLength:true,
        maxLength :100,
	vtype:"alphanumMid",
        allowBlank : false 
    }, 
                                                   {
     name :"CreatedDate",
           id:"WorkflowCreatedDate",
               readOnly:true,
               fieldLabel : "Created Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                         if(this.getValue() == null || this.getValue() == ""){
                this.setValue(date);
             } 
            	   }
	 },
	                hidden:true,
                                              allowBlank : true
              
},

                                             {
     name :"CreatedBy",
           id:"WorkflowCreatedBy",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Created By",
               hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                          allowBlank : true
              
},

                                             {
     name :"LastModifiedBy",
           id:"WorkflowLastModifiedBy",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Last Modified By",
               hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                          allowBlank : true
              
},

                                             {
     name :"LastModifiedDate",
           id:"WorkflowLastModifiedDate",
               readOnly:true,
               fieldLabel : "Last Modified Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                            this.setValue(date);
            	   }
	 },
	                hidden:true,
                                              allowBlank : true
              
},

{
     name :"Site",
           id:"WorkflowSite",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Site",
               hidden:true,
                                              allowBlank : true
              
},

                                             {
     name :"Version",
           id:"WorkflowVersion",
               readOnly:true,
               xtype:"textfield",
     fieldLabel : "Version",
               hidden:true,
                                              allowBlank : true
              
} 
  ],height:55,width:500}); 

  
// WorkflowMappingForm
  
  var WorkflowMappingForm = [
{
 title:'WorkflowMapping',
 xtype:'form',
 id:'WorkflowMappingForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"WorkflowMappingId",
               readOnly:true,
     fieldCls:'readonly-background-class',
                    minLength :13,
               maxLength :13,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Id",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"alphanumMid",
                                          allowBlank :true
                           
},

                     {
     name :"EntityType",
           id:"WorkflowMappingEntityType",
               readOnly:true,
     fieldCls:'readonly-background-class',
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Entity Type",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                    value:"WorkflowMapping",
                                                                   allowBlank :true
                           
},
 {
				      name :"MappingFor",
				      id:"WorkflowMappingFor",
				      editable:false,
				      fieldLabel : "Mapping For *",
				      xtype:"combobox",
				      editable:false,
				      store: Ext.create('Ext.data.ArrayStore',{
					    fields: ["mapped"],
					    data:Ext.mappedFor
				      }),
				      queryMode:"local",
				      displayField:"mapped",
				      valueField:"mapped",
				      value:'CmsUserProfile',
				      emptyText:"",
				      hidden:true,
				      allowBlank : false,
				      listeners : {
					    select: function(combo){
					      var userId = Ext.getCmp('WorkflowMappingUserId');
					      var userName = Ext.getCmp('WorkflowMappingUserName');
					      var roleId = Ext.getCmp('WorkflowMappingRoleId');
					      var roleName = Ext.getCmp('WorkflowMappingRoleName');
					      
					      if(this.getValue() == "Role"){
						userName.setValue('');
						userId.setDisabled(true);
						userName.setDisabled(true);
						roleId.setDisabled(false);
						roleName.setDisabled(false);
					      }
					      else{
						roleName.setValue('');
						userId.setDisabled(false);
						userName.setDisabled(false);
						roleId.setDisabled(true);
						roleName.setDisabled(true);
					      }
					    }
				      }
			       },
                     {
     name :"UserId",
           id:"WorkflowMappingUserId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "UserId",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

                     {
     name :"UserName",
           id:"WorkflowMappingUserName",
               editable:false,
                 fieldLabel : "User Name",
                    xtype:"combobox",
          editable:false,
                                                       store:createPreloadedStore('CmsUserProfile', '', 'UserName'),
                                queryMode:"remote",
     displayField:"UserName",
     valueField:"UserName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('WorkflowMappingIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('WorkflowMappingIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('WorkflowMappingUserId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                //this.store.sort('UserName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"RoleId",
           id:"WorkflowMappingRoleId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "RoleId",
	   disabled:true,
	  hidden:true,
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

                     {
     name :"RoleName",
           id:"WorkflowMappingRoleName",
               editable:false,
                 fieldLabel : "Role Name",
		 disabled:true,
                    xtype:"combobox",
          editable:false,
	  hidden:true,
                          store:createPreloadedStore('Role', '', 'RoleName'),
                                queryMode:"remote",
     displayField:"RoleName",
     valueField:"RoleName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('WorkflowMappingIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('WorkflowMappingIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('WorkflowMappingRoleId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                //this.store.sort('RoleName','ASC');
              }
	   			   
              },    
                                                          allowBlank:true
                   
},

               	              	                    {
     name :"WorkflowId",
           id:"MappingWorkflowWorkflowId",
               readOnly:true,
	       isFormField:false,
     fieldCls:'readonly-background-class',
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "WorkflowId",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
}
,
		{
		    name :"WorkflowName",
		    id:"MappingWorkflowWorkflowName",
		    xtype:"combobox",
		    editable:false,
		    isFormField:false,
		    multiSelect:true,
		    store:createStore('Workflow',''),
		    queryMode:"remote",
		    displayField:"WorkflowName",
		    valueField:"WorkflowName",
		    emptyText:"",
		    fieldLabel : "Assign Workflows *",
		    listeners:{
			select: function(combo, selections, opts) {
			  var wfIds   = new Array();
			  var wfNames = new Array();
			   for(var i = 0; i < selections.length; i++){
			     wfIds[i] = selections[i].data.Id;
			     wfNames[i] = selections[i].data.WorkflowName;
			   }
			   Ext.getCmp('MappingWorkflowWorkflowId').setValue(wfIds);
			},
			focus:function(combo){
			  this.store.load();
			}
		    },
		    allowBlank : false 
                },

     	               	                               {
     name :"CreatedDate",
           id:"WorkflowMappingCreatedDate",
               editable:false,
               fieldLabel : "Created Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                         if(this.getValue() == null || this.getValue() == ""){
                this.setValue(date);
             } 
            	   }
	 },
	                hidden:true,
                                                     allowBlank:false
                   
},

                     {
     name :"CreatedBy",
           id:"WorkflowMappingCreatedBy",
               readOnly:true,
     fieldCls:'readonly-background-class',
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Created By",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                                                         allowBlank :true
                           
},

                     {
     name :"LastModifiedBy",
           id:"WorkflowMappingLastModifiedBy",
               readOnly:true,
     fieldCls:'readonly-background-class',
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Last Modified By",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                              listeners:{
       render:function(){
         if(this.getValue() == ""){
            this.setValue(cmsUserName);
         }
       }
     },
                                                         allowBlank :true
                           
},

                     {
     name :"LastModifiedDate",
           id:"WorkflowMappingLastModifiedDate",
               editable:false,
               fieldLabel : "Last Modified Date",
     xtype : "datetimefield",
	 	 listeners:{
	   render:function(){
	     var date = new Date();
                            this.setValue(date);
            	   }
	 },
	                hidden:true,
                                                     allowBlank:false
                   
},

                     {
     name :"Site",
           id:"WorkflowMappingSite",
               readOnly:true,
     fieldCls:'readonly-background-class',
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Site",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

                     {
     name :"Version",
           id:"WorkflowMappingVersion",
               readOnly:true,
     fieldCls:'readonly-background-class',
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Version",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                hidden:true,
                                                                             allowBlank :true
                           
} ]}];

var workflowUserMapGrid = Ext.create('Ext.grid.Panel', {
      height:300,
      border:false,
      autoScroll:true,
      columnLines:true,
      id:'workflow-usermap-grid',
      store: Ext.create('Ext.data.Store', {
	  autoLoad:false,
	  model: 'WorkflowModel',
	  remoteSort: false
      }),
      forceFit:true,
      columns: [ 
	  {header: 'WorkFlow Actions',width:80,  dataIndex:'EventName',field:{
		readOnly:true ,allowBlank : false},sortable:false
          },
	  {header: 'Display Name',width:70,  id: 'displayName', dataIndex:'DisplayName',field:{
		    allowBlank : false,enforceMaxLength:true, maxLength :50,vtype:"alphanumMid",
		    listeners:{
			    blur:function(){
			      var str = trim(this.getValue());
			      str = str.replace(/ +(?= )/g,'');
			      this.setValue(str);
			    }
			} },sortable:false
	  },
	  {header: 'Content Stage',dataIndex: 'AssociatedStage',width: 70, field: {
	    xtype:'combobox',editable:false,store:createStore('Stage',''),queryMode:"remote",displayField:"StageName",valueField:"StageName",
	    emptyText:"",allowBlank : false},sortable:false
	  },
	  {header: 'Content Status',dataIndex: 'AssociatedStatus',width: 70, field: {
	    xtype:'combobox',editable:false,store:createStore('Status',''),queryMode:"remote",displayField:"StatusName",valueField:"StatusName",
	    emptyText:"",allowBlank : false},sortable:false
	  },
	  {header: 'Next Possible Actions',dataIndex: 'PossibleEvent',width: 90, field: {
	    id:'WorkflowUsermapGridPossibleEvent',
	    xtype:'combobox',editable:false, multiSelect:true,
	    store:Ext.create('Ext.data.ArrayStore', { fields: ["EventName"],data : []}),
			queryMode:"local",
			displayField:"EventName",valueField:"EventName",emptyText:"",
			listeners:{
				focus:function(combo){
					this.store.loadData(workflowGridPossibleEventData);
				},
				render:function(combo){
					//this.store.sort('EventName','ASC');
				}
			} 
	      },sortable:false
	  },
	  {header: 'Assigned User',dataIndex: 'UserName',width: 80, field: {
	    xtype:'combobox',editable:false,store:createPreloadedStore('CmsUserProfile','UserName'),queryMode:"remote",displayField:"UserName",valueField:"UserName",
            emptyText:"",
            pageSize:100,
            typeAhead:true,
	    multiSelect:true,
            triggerAction: 'all',
	    allowBlank : false, listeners:{
		   focus : function(){
			this.store.load();
		   },
		   render:function(combo){
		     // this.store.sort('UserName','ASC');
		   }
	    },sortable:false
	  }
	}/*,
	{header: 'Assigned Role',dataIndex: 'RoleName',width: 80, field: {
	    xtype:'combobox',editable:false,store:createPreloadedStore('Role','RoleName'),queryMode:"remote",displayField:"RoleName",valueField:"RoleName",
            emptyText:"",
            pageSize:100,
            typeAhead:true,
	    multiSelect:true,
            triggerAction: 'all',
	    allowBlank : false, listeners:{
		   focus : function(){
			this.store.load();
		   },
		   render:function(combo){
		     // this.store.sort('UserName','ASC');
		   }
	    },sortable:false
	  }
	} */
      ],
      selModel: {
            selType: 'cellmodel'
      },      
      plugins: [Ext.create('Ext.grid.plugin.CellEditing', {
	    clicksToEdit: 1
	})]
  });

var WorkflowPanel3   = Ext.create('Ext.form.Panel',{title:'Map Users To Events', region:'south',items:[WorkflowEventsGrid],height:310,width:500,autoScroll:true});
var WorkflowPanel2   = Ext.create('Ext.form.Panel',{items:[],region:'center',autoScroll:true,border:0,height:120,bodyPadding:5}); 
var WorkflowPanel1   = Ext.create('Ext.form.Panel',{id:'WorkflowForm1',border:0,region:'north',bodyPadding:5,items:[ 
		{
		  name :"Id",
		  id:"WorkflowId",
		  readOnly:true,
		  labelWidth:190,
		  xtype:"textfield",
		  fieldLabel : "Id",
		  allowBlank : true
	        },
                {
		    name :"WorkflowName",
		    id:"WorkflowName",
		    xtype:"textfield",
		    labelWidth:190,
		    enforceMaxLength:true,
		    maxLength :100,
		    vtype:"alphanumMid",
		    fieldLabel : "WorkflowName *",
		    allowBlank : false
   		},
		{
		    name :"WorkflowType",
		    fieldLabel : "WorkflowType *",
		    xtype:"combobox",
		    id:"WorkflowType",
		    labelWidth:190,
		    editable:false,
		    multiSelect:false,
		    store:Ext.create('Ext.data.ArrayStore', {
			    fields: ["WorkflowType"],
			    data : Ext.workflowType
		    }),
		    queryMode:"local",
		    displayField:"WorkflowType",
		    emptyText:"",
		    allowBlank : false
	        },
		{
		    name :"EventName",
		    id:"WorkflowEventName",
		    xtype:"combobox",
		    editable:false,
		    labelWidth:190,
		    isFormField:false,
		    multiSelect:true,
		    store:createStore('Event',''),
		    queryMode:"remote",
    	  	    displayField:"EventName",
    		    valueField:"EventName",
		    emptyText:"",
		    fieldLabel : "WorkflowEvents*",
		    listeners:{
			beforerender:function(combo){
    			  this.store.load();
    		        },
			blur: function(combo, selection) {
			  if(combo.getValue() != ''){
				  var eventArray = combo.getValue().toString().split(",");
				  loadWorkflowEventsGrid(eventArray);	    		
			  }
    		        },   
			focus:function(combo){
			  this.store.load();
			}
		    },
		    allowBlank : false 
                },
		{
		    name :"Key",
		    fieldLabel : "Key *",
		    xtype:"combobox",
		    id:"WorkflowKey",
		    labelWidth:190,
		    isFormField:false,
		    editable:false,
		    multiSelect:true,
		    store:Ext.create('Ext.data.ArrayStore', {
			    fields: ["Key"],
			    data : Ext.workflow
		    }),
		    queryMode:"local",
		    displayField:"Key",
		    emptyText:"",
		    allowBlank : false, 
		    listeners: {
		            blur: function(combo, selection) {
			      if(Ext.isArray(combo.getValue())){
				  loadPropertyForm(combo.getValue(),'add');
			      }else{
				  loadPropertyForm(new Array(combo.getValue()),'add');
			      }
			    }
	            }    
	       },
               {
                name :"CreatedDate",
                      id:"WorkflowCreatedDate",
                          readOnly:true,
                          fieldLabel : "Created Date",
                xtype : "datetimefield",
           	 	 listeners:{
           	   render:function(){
           	     var date = new Date();
                                    if(this.getValue() == null || this.getValue() == ""){
                           this.setValue(date);
                        } 
                       	   }
           	 },
           	                hidden:true,
                                                         allowBlank : true
                         
           },

                                                        {
                name :"CreatedBy",
                      id:"WorkflowCreatedBy",
                          readOnly:true,
                          xtype:"textfield",
                fieldLabel : "Created By",
                          hidden:true,
                                         listeners:{
                  render:function(){
                    if(this.getValue() == ""){
                       this.setValue(cmsUserName);
                    }
                  }
                },
                                     allowBlank : true
                         
           },

                                                        {
                name :"LastModifiedBy",
                      id:"WorkflowLastModifiedBy",
                          readOnly:true,
                          xtype:"textfield",
                fieldLabel : "Last Modified By",
                          hidden:true,
                                         listeners:{
                  render:function(){
                    if(this.getValue() == ""){
                       this.setValue(cmsUserName);
                    }
                  }
                },
                                     allowBlank : true
                         
           },

                                                        {
                name :"LastModifiedDate",
                      id:"WorkflowLastModifiedDate",
                          readOnly:true,
                          fieldLabel : "Last Modified Date",
                xtype : "datetimefield",
           	 	 listeners:{
           	   render:function(){
           	     var date = new Date();
                                       this.setValue(date);
                       	   }
           	 },
           	                hidden:true,
                                                         allowBlank : true
                         
           },

           {
                name :"Site",
                      id:"WorkflowSite",
                          readOnly:true,
                          xtype:"textfield",
                fieldLabel : "Site",
                          hidden:true,
                                                         allowBlank : true
                         
           },

                                                        {
                name :"Version",
                      id:"WorkflowVersion",
                          readOnly:true,
                          xtype:"textfield",
                fieldLabel : "Version",
                          hidden:true,
                                                         allowBlank : true
                         
           } 
    ],height:160,width:500}); 

var WorkflowForm = [
{
 title:'Workflow',
 xtype:'form',
 layout:'border',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:150
 },
  items:[
    WorkflowPanel1,
    WorkflowPanel2,
    WorkflowPanel3
  ]
}]; 


function WorkflowFlattenData(WorkflowEvent){
   workflowGridPossibleEventData = new Array();
   for(i=0;i<WorkflowEvent.length;i++){
     WorkflowEvent[i]["UserName"] = WorkflowEvent[i].Users.UserName;
     delete  WorkflowEvent[i]["Users"];
    /* WorkflowEvent[i]["RoleName"] = WorkflowEvent[i].Roles.RoleName;
     delete  WorkflowEvent[i]["Roles"];*/
     WorkflowEvent[i]["PossibleEvent"] = WorkflowEvent[i].PossibleEvents.PossibleEvent;
     workflowGridPossibleEventData[i] = {EventName : WorkflowEvent[i]["EventName"]};
     delete  WorkflowEvent[i]["PossibleEvents"];
   }
   return WorkflowEvent; 
}


function loadPropertyForm(jsonObject,formAction){
	if(!Ext.isArray(jsonObject)){
	   jsonObject = new Array(jsonObject);
	}
	WorkflowPanel2.removeAll();
	var stateValue = '';
	var districtValue = '';
	var ministryValue = '';
	var organisationValue = '';
	var departmentValue = '';
	var entityTypeValue = '';
	var stateFlag=0;
	var districtFlag=0;
	var ministryFlag=0;
	var organisationFlag=0;
	var deptFlag=0;
	var entityFlag=0;
	for(var i =0; i < jsonObject.length; i++){
	    try{
	        if(formAction=='edit'){
		    if(jsonObject[i].Key=='State'){
			if(!Ext.isEmpty(jsonObject[i].Value))	     		     	
			  stateValue = stateValue + jsonObject[i].Value+','; 
			stateFlag=1;
		    }if(jsonObject[i].Key=='Ministry'){
			if(!Ext.isEmpty(jsonObject[i].Value))	     		     	
			  ministryValue = ministryValue + jsonObject[i].Value+','; 
			ministryFlag=1;
		    }if(jsonObject[i].Key=='PublisherOrgName'){
			if(!Ext.isEmpty(jsonObject[i].Value))	     		     	
			  organisationValue = organisationValue + jsonObject[i].Value+','; 
			organisationFlag=1;
		    }if(jsonObject[i].Key=='District'){
			if(!Ext.isEmpty(jsonObject[i].Value))	     		     	
			  districtValue = districtValue + jsonObject[i].Value+','; 
			districtFlag=1;
		    }
		    if(jsonObject[i].Key=='PublisherDepartment'){
			if(!Ext.isEmpty(jsonObject[i].Value))	     		     	
			  departmentValue = departmentValue + jsonObject[i].Value+','; 
			deptFlag=1;
		    }
		    if(jsonObject[i].Key=='EntityType'){
			  if(!Ext.isEmpty(jsonObject[i].Value))
			    entityTypeValue = entityTypeValue + jsonObject[i].Value+','; 
			  entityFlag=1;
		    }
	        }
		else{
		    if(!Ext.isEmpty(jsonObject[i]) && jsonObject[i]=='State'){	     		     	
			stateValue = ''; 
			stateFlag=1;
		    }if(!Ext.isEmpty(jsonObject[i]) && jsonObject[i]=='Ministry'){	     		     	
			ministryValue = ''; 
			ministryFlag=1;
		    }if(!Ext.isEmpty(jsonObject[i]) && jsonObject[i]=='PublisherOrgName'){	     		     	
			organisationValue = ''; 
			organisationFlag=1;
		    }if(!Ext.isEmpty(jsonObject[i]) && jsonObject[i]=='District'){	     		     	
			districtValue = ''; 
			districtFlag=1;
		    }if(!Ext.isEmpty(jsonObject[i]) && jsonObject[i]=='PublisherDepartment'){	     		     	
			departmentValue = ''; 
			deptFlag=1;
		    }if(!Ext.isEmpty(jsonObject[i]) && jsonObject[i]=='EntityType'){	     		     	
			entityTypeValue = ''; 
			entityFlag=1;
		    }
		}
		var key = Ext.create("Ext.form.field.Text", {name: 'Key',xtype:"textfield",value:jsonObject[i].Key,hidden:true, labelWidth:150,
		      listeners:{
			  blur:function(){
			    var str = trim(this.getValue());
			    str = str.replace(/ +(?= )/g,'');
			    this.setValue(str);
			  }
		      }
	        });
		key.setValue(jsonObject[i].Key);
		WorkflowPanel2.add(key);
	    }
	    catch(error){
	      
	    }
	}
	if(deptFlag==1){
	    var departmentCombo = Ext.create("Ext.form.field.ComboBox",{ name :"PublisherDepartment", editable:false, fieldLabel : 'PublisherDepartment *', multiSelect:true,labelWidth:190,
				    store:createQueryStore('GoiDirItem', 'Category="Departments"'),queryMode:"remote",displayField:"GoiDirItemName", valueField:"GoiDirItemName",emptyText:"", 
				    listeners:{
				      beforerender:function(combo){
					  this.store.load();
				      },
				      render:function(combo){
					  this.store.sort('GoiDirItemName','ASC');
				      }
				    }, allowBlank : false
	    });
	    departmentCombo.setValue(departmentValue.substr(0,departmentValue.length-1));
	    WorkflowPanel2.add(departmentCombo);
	}
	if(entityFlag==1){
	    var entityTypeCombo = Ext.create("Ext.form.field.ComboBox",{ name :"EntityType",fieldLabel : 'EntityType *',editable:false, multiSelect:true,labelWidth:190,
				      store:Ext.create('Ext.data.ArrayStore', { fields: ["WorkflowEntities"],data : Ext.WorkflowEntities }),queryMode:"local", displayField:"WorkflowEntities",emptyText:"",
				      listeners:{
				      beforerender:function(combo){
					this.store.load();
				      }
				    }, allowBlank : false
	    });
	    entityTypeCombo.setValue(entityTypeValue.substr(0,entityTypeValue.length-1));
	    WorkflowPanel2.add(entityTypeCombo);
	}
	if(stateFlag==1){
	    var stateCombo = Ext.create("Ext.form.field.ComboBox",{ name :"State", editable:false, fieldLabel : 'State *', multiSelect:true,labelWidth:190,
				    store:createQueryStore('GoiDirItem', 'Category="State"'),queryMode:"remote",displayField:"GoiDirItemName", valueField:"GoiDirItemName",emptyText:"", 
				    listeners:{
				      beforerender:function(combo){
					  this.store.load();
				      },
				      render:function(combo){
					  this.store.sort('GoiDirItemName','ASC');
				      }
				    }, allowBlank : false
	    });
	    stateCombo.setValue(stateValue.substr(0,stateValue.length-1));
	    WorkflowPanel2.add(stateCombo);
	}
	if(districtFlag==1){
	    var districtCombo = Ext.create("Ext.form.field.ComboBox",{ name :"District", editable:false, fieldLabel : 'District *', multiSelect:true,labelWidth:190,
				    store:createQueryStore('GoiDirItem', 'Category="State District"'),queryMode:"remote",displayField:"GoiDirItemName", valueField:"GoiDirItemName",emptyText:"", 
				    listeners:{
				      beforerender:function(combo){
					  this.store.load();
				      },
				      render:function(combo){
					  this.store.sort('GoiDirItemName','ASC');
				      }
				    }, allowBlank : false
	    });
	    districtCombo.setValue(districtValue.substr(0,districtValue.length-1));
	    WorkflowPanel2.add(districtCombo);
	}
	if(ministryFlag==1){
	    var ministryCombo = Ext.create("Ext.form.field.ComboBox",{ name :"Ministry", editable:false, fieldLabel : 'Ministry *', multiSelect:true,labelWidth:190,
				    store:createQueryStore('GoiDirItem', 'Category="Ministry"'),queryMode:"remote",displayField:"GoiDirItemName", valueField:"GoiDirItemName",emptyText:"", 
				    listeners:{
				      beforerender:function(combo){
					  this.store.load();
				      },
				      render:function(combo){
					  this.store.sort('GoiDirItemName','ASC');
				      }
				    }, allowBlank : false
	    });
	    ministryCombo.setValue(ministryValue.substr(0,ministryValue.length-1));
	    WorkflowPanel2.add(ministryCombo);
	}
	if(organisationFlag==1){
	    var organisationCombo = Ext.create("Ext.form.field.ComboBox",{ name :"PublisherOrgName", editable:false, fieldLabel : 'PublisherOrgName *', multiSelect:true,labelWidth:190,
				    store:createQueryStore('GoiDirItem', 'Category="Organisations"'),queryMode:"remote",displayField:"GoiDirItemName", valueField:"GoiDirItemName",emptyText:"", 
				    listeners:{
				      beforerender:function(combo){
					  this.store.load();
				      },
				      render:function(combo){
					  this.store.sort('GoiDirItemName','ASC');
				      }
				    }, allowBlank : false
	    });
	    organisationCombo.setValue(organisationValue.substr(0,organisationValue.length-1));
	    WorkflowPanel2.add(organisationCombo);
	}  
}

var workflowGridPossibleEventData = new Array();

function loadWorkflowEventsGrid(events){
	WorkflowEventsGrid.store.removeAll(false);
	workflowGridPossibleEventData = new Array();
	// var RoleNamesValue = '';
	var userNamesValue = '';
	var PossibleEventsValue = '';
	for(var i=0; i < events.length; i++){
	    if(events[i].Users){
	      if(events[i].Users.UserName){
		if(!Ext.isEmpty(userNamesValue)){
		  userNamesValue += ',';
		}
		userNamesValue += events[i].Users.UserName;
	      }
	    }
	   /* if(events[i].Roles){
	      if(events[i].Roles.RoleName){
		if(!Ext.isEmpty(RoleNamesValue)){
		  RoleNamesValue += ',';
		}
		RoleNamesValue += events[i].Roles.RoleName;
	      }
	    } */
	    if(events[i].PossibleEvents){
	      if(events[i].PossibleEvents.PossibleEvent){
		if(!Ext.isEmpty(PossibleEvent)){
		  PossibleEvent += ',';
		}
		PossibleEventsValue += events[i].PossibleEvents.PossibleEvent;
	      }
	    }
	    var record = {
		EventName: events[i],
		DisplayName: events[i],
		AssociatedStage: "",
		AssociatedStatus: "",
		PossibleEvent: PossibleEventsValue,
		UserName: userNamesValue,
		// RoleName: RoleNamesValue
	    };
	   WorkflowEventsGrid.store.add(record);
	   workflowGridPossibleEventData[i] = {EventName : events[i]};
	} 
}

function propertyJsonToKeyValue(record){
	var workflowPreloadData = new Array(); 
	var jsonData = Ext.JSON.decode(record);
	var props = new Array();
	if(Ext.isArray(jsonData.Property)){
		props = jsonData.Property
	}
	else{
		props = new Array(jsonData.Property);
	}
	for(var i = 0; i < props.length; i++){
		workflowPreloadData[i] = props[i].Key;
	}
	return workflowPreloadData;
}

function stringToUsers(data){
	data["Users"] = {UserName : data["UserName"]};
	// data["Roles"] = {RoleName : data["RoleName"]};
	data["PossibleEvents"] = {PossibleEvent : data["PossibleEvent"]};
	delete data["UserName"];
	//delete data["RoleName"];
        delete data["PossibleEvent"];
        return data;
}

function getPropertiesJson(wfmodelArr){
	var jsonData = '{"Properties":{"Property":' + wfmodelArr + '}}';
	return jsonData;
}

function getJsonOfStore(root,objectName,listStore){
	var jsonData = ''; 
	if(listStore.count() > 0){  
		var storeJsonData = Ext.encode(Ext.pluck(listStore.data.items, 'data'));
		jsonData = '{"' + root + '\":' +  '{"'+ objectName + '\":' + storeJsonData + '}}';
	}
	return jsonData;
}

function validateStore(store,entityName){
	var status = true;
	for(var i = 0; i < store.getCount(); i++){
	    var record = store.getAt(i);
	    if(entityName == 'Workflow'){
		    if(Ext.isEmpty(record.get('EventName')) ||
		       Ext.isEmpty(record.get('DisplayName')) ||
		       Ext.isEmpty(record.get('AssociatedStage')) || 
		       Ext.isEmpty(record.get('AssociatedStatus')) ||
		       Ext.isEmpty(record.get('PossibleEvent')) ||
		 //      Ext.isEmpty(record.get('RoleName')) ||
		       Ext.isEmpty(record.get('UserName'))){
			 status = false; 
		      }
	    }
	}
	return status;
}

//http://124.7.228.161/ncmsui/extjs/workflow/js/workflowleftpanel.js?_dc=3002

Ext.require([
    'Ext.tree.*',
    'Ext.data.*',
    'Ext.grid.*'
]);
Ext.define('NIC.WORKFLOWLeftPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.workflowleftpanel',   

   workflowitementities : Ext.create('Ext.tree.Panel', {
        hideHeaders: true,
        scope: this,
	layout: 'fit',
        width:250,
    useArrows: true,
	height:getWindowHeight(),
	rootVisible: true,
	store: Ext.create('Ext.data.TreeStore', {
	autoLoad:false,
	proxy: {
	    type: 'memory',
	    //url: dataservicesPath + '/search?q=&entitytype=role&format=treejson&egroup=workflow&rolename=' + userRole,
	    //url: dataPath + '/dummy.json',
            reader : {
				type : 'json',
				root : 'AllowedEntities'
			}
	},
	root: {
            text: 'Allowed Entities',
            expanded: false
        } 
     }),
      listeners: {
	 render: function(panel, options){
	   this.store.sort('text','ASC');
	 } 
       } 
   }),
                 
   initComponent: function(){
     	Ext.apply(this, {
	  region:"west",
	  autoHeight:true,
	  autoScroll:true,
	  width:250,
	  scroll: 'both',
  	 items : [this.workflowitementities] 
     });
       this.callParent();
       
    this.workflowitementities.on('load',function( store, records, successful){
      this.workflowitementities.expand(true);
      this.doLayout();
    },this);
    }
});

//http://124.7.228.161/ncmsui/extjs/workflow/js/workflowrightpanel.js?_dc=3002

  Ext.require([
   'Writer.Grid',
   'NcmsUIModel'  
  ]);
  Ext.define('NIC.WORKFLOWRightPanel', {
     extend: 'Ext.panel.Panel',
     alias: 'widget.workflowrightpanel',
  //   menu:filterByMenu("workflowMenuClickHandler"),
     grid:  Ext.create('Writer.Grid', {
                    title:'Listing',
                    id: 'workflow-main-grid',
		    flex: 1,
		    height:'100%',
		    store: Ext.create('Ext.data.Store', {
				model: 'NcmsUIModel',
				pageSize: itemsPerPage,
				remoteSort: true,
				proxy: {
					type: 'ajax',
					url: dataservicesPath + '/search?q=&format=extjson&entitytype=Template',
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
                     columns:[{text:'',dataIndex:'Name',width:80}],
		        viewConfig: {
		            stripeRows: true,
		            listeners: {
		                itemcontextmenu: function(View,record,item,index,e,options ) {
		              //     if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined") && (record.data.CurrentEvent=="Expire")) {
		                   if((record.data.CurrentEvent) && (record.data.CurrentEvent!="undefined")) {
    		                      e.stopEvent();
		                      var cMenu = contextMenu(record,"workflow");
		                      if(cMenu)
		                        cMenu.showAt(e.getXY());
		                      return false;
		                   }
		                }
		            }
		        }
     }),
     searchText:Ext.create('Ext.form.field.Text', {emptyText: 'Enter search term',disabled:true, width:200,
	  maxLength :100, enforceMaxLength:true, vtype: 'alpha',scope:this,
	      listeners: {
		focus : function(tbx){
		  var val = tbx.getValue();
		  if(!Ext.isEmpty(val))
		    workflowSearchText = val;
		  tbx.setValue(""); 
		},
                blur:function(tbx){
		      var str;
		      if(Ext.isEmpty(tbx.getValue()))
			str =  workflowSearchText;
		      else
		        str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      if(Ext.getCmp('workflow-main-grid').store.getCount()>=1){
		        tbx.setValue(str);
		      }
	        }
	      }
     }),
     addButton:Ext.create('Ext.button.Button', {text: 'Add New' ,disabled:true, iconCls:'icon-plus'}),
    // menuBar:Ext.create('Ext.button.Button',{text:'Filter By',disabled:true, iconCls:'icon-domains-entities'}),
     searchButton:Ext.create('Ext.button.Button', {text: 'Search' ,disabled:true, iconCls:'icon-search'}),
     advSearchButton:Ext.create('Ext.button.Button', {text:'Advanced Search',disabled:true, iconCls:"x-Gsearch-icon"}),
     initComponent: function(){
   //  this.menuBar.menu = this.menu;
     	Ext.apply(this, {
	tbar: [	  
		  {xtype: 'tbspacer', width: 20},
		  this.addButton,
		//  {xtype: 'tbspacer', width: 50},
		//  this.menuBar,
		  {xtype: 'tbspacer', width: 20},
		  this.searchText,
		  this.searchButton,
		  {xtype: 'tbspacer', width: 20},
		  this.advSearchButton
	],
        autoScroll : true,
	region:"center",
	items: [this.grid]
     });
     this.callParent();
     
     }
  });    
 

//http://124.7.228.161/ncmsui/extjs/workflow/js/workflowmainpanel.js?_dc=3002
  
  var workflowCurrentEntity='';
  var currentColumn = [];
  Ext.define('NIC.WORKFLOWMainPanel', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.workflowmainpanel',
       workflowLeftPanel: Ext.create("NIC.WORKFLOWLeftPanel"),
       workflowRightPanel : Ext.create("NIC.WORKFLOWRightPanel"),
       initComponent: function(){
	  Ext.apply(this, {
	      region: "center",
	      layout:"border",
	      closeAction:'hide',
	      items:[this.workflowLeftPanel, this.workflowRightPanel] 
          }); 
         this.workflowRightPanel.grid.store.on('load',function(store, records, boolean, operation, eOpts){
            workflowSearchText='';
	 });         
	 this.workflowLeftPanel.workflowitementities.on('itemclick',function(view, record, item, index, e){
		workflowCurrentEntity = record.data.text;
		clearSearchBox(this.workflowRightPanel.searchText);
		this.workflowRightPanel.grid.doLayout();
	        if(index != 0){
			this.workflowRightPanel.searchText.enable(true);
		//	this.workflowRightPanel.menuBar.enable(true);
                        this.workflowRightPanel.addButton.enable(true);
			this.workflowRightPanel.searchButton.enable(true);
			this.workflowRightPanel.advSearchButton.enable(true);
			this.workflowRightPanel.grid.headerCt.removeAll();
			this.workflowRightPanel.grid.getStore().removeAll();
			this.workflowRightPanel.grid.setTitle(workflowCurrentEntity+" Listing");
		     currentColumn = Ext.JSON.decode(workflowCurrentEntity+"Column");
		     var cloneCurrentColumn = Ext.clone(currentColumn);
                     if(!allowDeleteColumn(cmsCurrentEntity)){
                    	 cloneCurrentColumn.splice(cloneCurrentColumn.length-1,1);
                     }
			this.workflowRightPanel.grid.headerCt.add(currentColumn);
			this.workflowRightPanel.doLayout();
			this.workflowRightPanel.grid.store.currentPage=1;
			this.workflowRightPanel.grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+getSubQuery(workflowCurrentEntity);
			this.workflowRightPanel.grid.store.proxy.setReader(
			   {
				type: 'json',
				root: 'Collections.'+workflowCurrentEntity,
				totalProperty: 'Collections.Count' 
			    }
			);
			this.workflowRightPanel.grid.store.load();
                }
                else{
                  this.workflowRightPanel.addButton.disable(true);
              //    this.workflowRightPanel.menuBar.disable(true);
                  this.workflowRightPanel.searchText.disable(true);
                  this.workflowRightPanel.searchButton.disable(true);
                  this.workflowRightPanel.advSearchButton.disable(true);
                }
	  },this);

         this.workflowRightPanel.addButton.on('click',function(button, e){
	   clearSearchBox(this.workflowRightPanel.searchText);
	   workflowCreateWindow("", "", "new",this.workflowRightPanel.grid,false);
	 },this);
	 
 	 this.workflowRightPanel.searchText.on('specialkey',function(field, e){
             globalSearch(field, e, this.workflowRightPanel, '', workflowCurrentEntity,'specialKey');
	  },this);
	 this.workflowRightPanel.searchButton.on('click',function(button, e){ 
             globalSearch(button, e, this.workflowRightPanel, '', workflowCurrentEntity,'searchbutton'); 
	 },this);
	
	  // Starting Advanced Search
	  
	 this.workflowRightPanel.advSearchButton.on('click',function(button, e){
	   globalAdvancedSeacrh(this, this.workflowRightPanel, workflowCurrentEntity, "");
	 },this);
	 
	 // Ending Advanced Search
	 
         this.callParent();
	loadDomainEntities(this.workflowLeftPanel.workflowitementities.store,'workflow');
         this.getResponse = function(options){
           this.workflowRightPanel.formpanel.add(options);
           this.workflowRightPanel.formpanel.doLayout();
         }
       }
    //}
});

//http://124.7.228.161/ncmsui/extjs/js/appindex.js?_dc=3002
  
  //var username = "domainadmin";

Ext.override(Ext.LoadMask, {
	onHide: function() { this.callParent(); }
});


var username="";
Ext.Loader.setConfig({
   enabled : true
});
Ext.Loader.setPath('Ext.ux', extPath+'/ux');
Ext.require([ 'Ext.grid.*', 'Ext.data.*', 'Ext.util.*', 'Ext.toolbar.Paging',
              'Ext.ModelManager', 'Ext.layout.*', 'Ext.tip.QuickTipManager',
              'NIC.CMSMainPanel', 'NIC.PMSMainPanel', 'NIC.USERMainPanel',
              'NIC.FEEDMainPanel', 'NIC.ADMINMainPanel', 'NIC.DOMAINMainPanel', 'NIC.WORKFLOWMainPanel','Ext.view.View', 'Ext.form.*',
              'Ext.window.Window', 'Ext.selection.CellModel', 'Ext.state.*',
              'Ext.ux.CheckColumn','Ext.ux.DateTimePicker','Ext.ux.TimePickerField','Ext.ux.DateTimeField','Ext.ux.DataView.DragSelector','Ext.ux.DataView.LabelEditor' ]);
Ext.onReady(function() {

	Ext.override(Ext.LoadMask, {
      		onHide: function() { this.callParent(); }
	}); 
 
        Ext.QuickTips.init();
        //hideLayer();
	var maintabpanel = Ext.create('Ext.tab.Panel', {
		title : 'NIC Portal Admin 1.0 - Logged in as <font color=black>' + cmsUserName + '</font>',
		//height : '200',
		forceFit:true//,
		//activeItem:0,
		//xtype: 'tabpanel',
		//items : [{title:"test"}]
	});
	var arrayModules = [];
	var objectModulesClasses = new Object();         
	try{ 
	   arrayModules =  getCookie("cmsmodules").split(",");
	}		
	catch(Error){
	   window.location = loginUrl;
	}
	objectModulesClasses['CMS'] = 'cmsmainpanel';
	objectModulesClasses['Domain'] = 'domainmainpanel';
	objectModulesClasses['PMS'] = 'pmsmainpanel';
	objectModulesClasses['UsersAndRoles'] = 'usermainpanel';
	objectModulesClasses['FeedAutomation'] = 'feedmainpanel';
	objectModulesClasses['Admin'] = 'adminmainpanel';
	objectModulesClasses['Workflow'] = 'workflowmainpanel';
	
	arrayModules = arrayModules.sort();
	for( var i = 0; i < arrayModules.length; i++) {
		var tab = arrayModules[i].replace(/\s/g, "");
		maintabpanel.add({
			xtype : objectModulesClasses[tab],
			title : arrayModules[i],
			id : objectModulesClasses[tab] + "-tab",
			iconCls : 'icon-pms'
		});
	}
	maintabpanel.setActiveTab(0);
	
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
	
	
	maintabpanel.on('tabchange', function(tabPanel,newCard,oldCard){
	  globalActiveTab = shortTabNames[newCard.getId()];
		//alert(globalActiveTab);
	});
	
	var logoutPanel = Ext.create('Ext.panel.Panel', {
		title : 'Logout',
		iconCls : 'icon-pms',
		listeners : {
			activate : function() {
				Ext.Msg.confirm('Logout', 'Do you want to logout?',
						function(btn) {
					if (btn == 'Yes' || btn == 'yes') {
						logout();
						window.location = loginUrl;
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
		layout:'fit',
		id : 'nic-extjs-cms',
		lazyRender:true,
		height:'70%',
		items : [ maintabpanel ]
	});
	  myMask.hide();
	//Ext.EventManager.on(window, 'beforeunload', function() {
	   //logout();
	//});
});
