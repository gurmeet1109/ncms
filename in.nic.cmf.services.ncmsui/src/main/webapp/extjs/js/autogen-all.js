var AccessControlListForm = [
{
 title:'AccessControlList',
 xtype:'form',
 id:'AccessControlListForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"AccessControlListId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "AccessControlList Id",
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
	name :"EntityType",
		  id:"AccessControlListEntityType",
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
				  value:"AccessControlList",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"XlsName",
		  id:"AccessControlListXlsName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Xls Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				       listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>XlsName should end with valid file extension</div>");
                   this.setValue("");
           }
           }
	       var str = trim(this.getValue());
           str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
       }
        },
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"AccessControlListSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FilePath",
		  id:"AccessControlListFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"Md5",
		  id:"AccessControlListMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
		  id:"AccessControlListCreatedDate",
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
		  id:"AccessControlListLastModifiedDate",
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
		  id:"AccessControlListCreatedBy",
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
		  id:"AccessControlListLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"AccessControlListSite",
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
		  id:"AccessControlListVersion",
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




	
var ArticleForm = [
{
 title:'Article',
 xtype:'form',
 id:'ArticleForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ArticleId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Article Id",
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
	name :"EntityType",
		  id:"ArticleEntityType",
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
				  value:"Article",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Status",
		  id:"ArticleStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"SeoUrl",
		  id:"ArticleSeoUrl",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :150,
	      		fieldLabel : "SeoUrl",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		},
	          										vtype:"url",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Title",
		  id:"ArticleTitle",
				    	    	    maxLength :200,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Title  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"Description",
		  id:"ArticleDescription",
				      xtype : "htmleditor",
	      	      		maxLength :4000,
	      	      fieldLabel : "Description  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
												    allowBlank:false
				
    },
                   	                   	{
	name :"Channel",
		  id:"ChannelsChannel",
			  //editable:false,
			  fieldLabel : "Channels  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      	      	      queryMode:"remote",
	      	    displayField:"$fields.annotations.UI.queryField",
	    valueField:"$fields.annotations.UI.queryField",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('$cname','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                         	                   	{
	name :"Category",
		  id:"CategoriesCategory",
			  //editable:false,
			  fieldLabel : "Category  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('Category', '', 'CategoryName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"CategoryName",
	    valueField:"CategoryName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('CategoryName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"AssociatedIAPath",
		  id:"ArticleAssociatedIAPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :400,
	      		fieldLabel : "Associated IA Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this,'InformationArchitecture', '', 'associatedia');
								      }
		  	      		},
	          											  	  	    	      allowBlank : false 
	    				
    },
                        	{
	name :"ShortDescription",
		  id:"ArticleShortDescription",
				      xtype : "htmleditor",
	      	      	      fieldLabel : "Short Description",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
			  hidden:true,
											    allowBlank:true
				
    },
                        	{
	name :"SourceAuthor",
		  id:"ArticleSourceAuthor",
				    	    	    maxLength :200,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Source Author",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Md5",
		  id:"ArticleMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
	name :"City",
		  id:"LocationCity",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "City",
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
	name :"Priority",
		  id:"ArticlePriority",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Priority",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'Metadata',
	xtype:'form',
	id:'ArticleForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm,
	                         	              	                                                                                                                                                                    	               	       {
      name:"Dummy",
      id:"dummy",
      hidden:true
    }
	]},{
	title:'DependentItems',
	xtype:'form',
	id:'ArticleForm3',
	autoScroll:true,
        items:[DependentItemPanelForm,
                           	              	                                                                                                                                                                    	               	                  {
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
        ]},{
	title:'RelatedItems',
	xtype:'form',
	id:'ArticleForm4',
	autoScroll:true,
        items:[RelatedItemPanelForm,
                          	{
	name :"CreatedDate",
		  id:"ArticleCreatedDate",
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
		  id:"ArticleLastModifiedDate",
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
		  id:"ArticleCreatedBy",
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
		  id:"ArticleLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ArticleSite",
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
		  id:"ArticleVersion",
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




	
var CategoryForm = [
{
 title:'Category',
 xtype:'form',
 id:'CategoryForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"CategoryId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Category Id",
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
	name :"EntityType",
		  id:"CategoryEntityType",
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
				  value:"Category",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CategoryName",
		  id:"CategoryCategoryName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Category Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"ParentCategoryId",
		  id:"CategoryParentCategoryId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Parent Category Id",
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
	name :"ParentCategoryName",
		  id:"CategoryParentCategoryName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :1000,
	      		fieldLabel : "Parent Category Name",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this, 'Category', 'ParentCategoryId' ,'parentchild');
								      }
		  	      		},
	          											  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"CategoryCreatedDate",
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
		  id:"CategoryLastModifiedDate",
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
		  id:"CategoryCreatedBy",
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
		  id:"CategoryLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"CategorySite",
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
		  id:"CategoryVersion",
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




	
var ChannelForm = [
{
 title:'Channel',
 xtype:'form',
 id:'ChannelForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ChannelId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Channel Id",
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
	name :"EntityType",
		  id:"ChannelEntityType",
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
				  value:"Channel",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ChannelName",
		  id:"ChannelChannelName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Channel Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"ChannelCreatedDate",
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
	name :"Site",
		  id:"ChannelSite",
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
		  id:"ChannelVersion",
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




	
var CityForm = [
{
 title:'City',
 xtype:'form',
 id:'CityForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"CityId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "City Id",
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
	name :"EntityType",
		  id:"CityEntityType",
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
				  value:"City",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CityName",
		  id:"CityCityName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "City Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alpha",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"CityCreatedDate",
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
		  id:"CityLastModifiedDate",
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
		  id:"CityCreatedBy",
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
		  id:"CityLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"CitySite",
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
		  id:"CityVersion",
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




	
var ClassAttributesForm = [
{
 title:'ClassAttributes',
 xtype:'form',
 id:'ClassAttributesForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ClassAttributesId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "ClassAttributes Id",
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
	name :"EntityType",
		  id:"ClassAttributesEntityType",
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
				  value:"ClassAttributes",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FieldName",
		  id:"ClassAttributesFieldName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Field Name",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    									  allowBlank : true
		
    },
                        	{
	name :"FieldType",
		  id:"ClassAttributesFieldType",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Field Type  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"XmlName",
		  id:"ClassAttributesXmlName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "XML Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"Type",
		  id:"ClassAttributesType",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Type",
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
	name :"Cdata",
		  id:"ClassAttributesCdata",
				  fieldLabel : "CData  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["cdata"],
				  data : Ext.cdata // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"cdata",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"DefaultSearch",
		  id:"ClassAttributesDefaultSearch",
				  fieldLabel : "DefaultSearch  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["defaultSearch"],
				  data : Ext.defaultSearch // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"defaultSearch",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"UIConfig",
		  id:"ClassAttributesUIConfig",
				    	    	    maxLength :4000,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "UI Config",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"RuleConfig",
		  id:"ClassAttributesRuleConfig",
				    	    	    maxLength :4000,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Rule Config",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"ClassAttributesCreatedDate",
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
		  id:"ClassAttributesLastModifiedDate",
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
		  id:"ClassAttributesCreatedBy",
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
		  id:"ClassAttributesLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ClassAttributesSite",
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
		  id:"ClassAttributesVersion",
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




	
var ClassImportForm = [
{
 title:'ClassImport',
 xtype:'form',
 id:'ClassImportForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ClassImportId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "ClassImport Id",
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
	name :"EntityType",
		  id:"ClassImportEntityType",
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
				  value:"ClassImport",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ImportName",
		  id:"ClassImportImportName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Import Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"ClassImportCreatedDate",
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
		  id:"ClassImportLastModifiedDate",
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
		  id:"ClassImportCreatedBy",
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
		  id:"ClassImportLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ClassImportSite",
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
		  id:"ClassImportVersion",
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




	
var ClassInfoForm = [
{
 title:'ClassInfo',
 xtype:'form',
 id:'ClassInfoForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ClassInfoId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "ClassInfo Id",
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
	name :"EntityType",
		  id:"ClassInfoEntityType",
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
				  value:"ClassInfo",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ClassName",
		  id:"ClassInfoClassName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Class Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"MasterData",
		  id:"ClassInfoMasterData",
				  fieldLabel : "MasterData  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["masterData"],
				  data : Ext.masterData // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"masterData",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"TreeEntity",
		  id:"ClassInfoTreeEntity",
				  fieldLabel : "TreeEntity  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["treeEntity"],
				  data : Ext.treeEntity // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"treeEntity",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"UI",
		  id:"ClassInfoUI",
				  fieldLabel : "UI  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["uI"],
				  data : Ext.uI // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"uI",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"UIForm",
		  id:"ClassInfoUIForm",
				  fieldLabel : "UI Form <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["uIForm"],
				  data : Ext.uIForm // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"uIForm",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                   	                   	{
	name :"ClassField",
		  id:"ClassFieldsClassField",
				  fieldLabel : "FieldName  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('ClassAttributes', '', 'FieldName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"FieldName",
	    valueField:"FieldName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('FieldName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                         	                   	{
	name :"EntityGroup",
		  id:"EntityGroupsEntityGroup",
			  //editable:false,
			  fieldLabel : "Entity Group",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["entityGroup"],
				  data : Ext.entityGroup // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"entityGroup",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('entityGroup','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"CreatedDate",
		  id:"ClassInfoCreatedDate",
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
		  id:"ClassInfoLastModifiedDate",
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
		  id:"ClassInfoCreatedBy",
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
		  id:"ClassInfoLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"EditOption",
		  id:"ClassInfoEditOption",
				  fieldLabel : "Edit Option  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["editOption"],
				  data : Ext.editOption // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"editOption",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"DownloadOption",
		  id:"ClassInfoDownloadOption",
				  fieldLabel : "Download Option  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["downloadOption"],
				  data : Ext.downloadOption // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"downloadOption",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                   	                   	{
	name :"ClassImplement",
		  id:"ClassImplementsClassImplement",
				  fieldLabel : "Implements",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('InterfaceInfo', '', 'InterfaceName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"InterfaceName",
	    valueField:"InterfaceName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('InterfaceName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:true
				
    },
         	                              	{
	name :"XmlName",
		  id:"ClassInfoXmlName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "XML Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"PackageName",
		  id:"ClassInfoPackageName",
				  fieldLabel : "Package Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('ClassPackage', '', 'ClassPackageName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"ClassPackageName",
	    valueField:"ClassPackageName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('ClassPackageName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
                   	                   	{
	name :"ClassImport",
		  id:"ClassImportsClassImport",
				  fieldLabel : "Import  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('ClassImport', '', 'ImportName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"ImportName",
	    valueField:"ImportName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('ImportName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"Site",
		  id:"ClassInfoSite",
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
		  id:"ClassInfoVersion",
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




	
var ClassPackageForm = [
{
 title:'ClassPackage',
 xtype:'form',
 id:'ClassPackageForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ClassPackageId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "ClassPackage Id",
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
	name :"EntityType",
		  id:"ClassPackageEntityType",
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
				  value:"ClassPackage",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ClassPackageName",
		  id:"ClassPackageClassPackageName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Package Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"ClassPackageCreatedDate",
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
		  id:"ClassPackageLastModifiedDate",
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
		  id:"ClassPackageCreatedBy",
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
		  id:"ClassPackageLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ClassPackageSite",
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
		  id:"ClassPackageVersion",
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




	
var ContentPartnerForm = [
{
 title:'ContentPartner',
 xtype:'form',
 id:'ContentPartnerForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ContentPartnerId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "ContentPartner Id",
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
	name :"EntityType",
		  id:"ContentPartnerEntityType",
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
				  value:"ContentPartner",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Description",
		  id:"ContentPartnerDescription",
				      xtype : "htmleditor",
	      	      		maxLength :4000,
	      	      fieldLabel : "Description  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
												    allowBlank:false
				
    },
                        	{
	name :"RssAggregated",
		  id:"ContentPartnerRssAggregated",
				  fieldLabel : "RssAggregated  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["rssAggregated"],
				  data : Ext.rssAggregated // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"rssAggregated",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"Source",
		  id:"ContentPartnerSource",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Source  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"SourceType",
		  id:"ContentPartnerSourceType",
			  //editable:false,
			  fieldLabel : "SourceType  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('SourceType', '', 'SourceTypeName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"SourceTypeName",
	    valueField:"SourceTypeName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"SupplementName",
		  id:"ContentPartnerSupplementName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Supplement Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"Url",
		  id:"ContentPartnerUrl",
				    	    	    maxLength :150,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Url  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    						  value:"http://",
							vtype:"url",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"NoOfDays",
		  id:"ContentPartnerNoOfDays",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "No Of Days to Maintain Feed  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"ContentPartnerCreatedDate",
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
		  id:"ContentPartnerLastModifiedDate",
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
		  id:"ContentPartnerCreatedBy",
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
		  id:"ContentPartnerLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ContentPartnerSite",
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
		  id:"ContentPartnerVersion",
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




	
var CountryForm = [
{
 title:'Country',
 xtype:'form',
 id:'CountryForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"CountryId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Country Id",
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
	name :"EntityType",
		  id:"CountryEntityType",
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
				  value:"Country",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CountryName",
		  id:"CountryCountryName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Country Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alpha",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CountryCode",
		  id:"CountryCountryCode",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Country Code  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumeric",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"CountryCreatedDate",
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
		  id:"CountryLastModifiedDate",
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
		  id:"CountryCreatedBy",
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
		  id:"CountryLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"CountrySite",
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
		  id:"CountryVersion",
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




	
var CssForm = [
{
 title:'Css',
 xtype:'form',
 id:'CssForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"CssId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Css Id",
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
	name :"EntityType",
		  id:"CssEntityType",
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
				  value:"Css",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CssName",
		  id:"CssCssName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Css Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				       listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.css)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>CssName should end with valid file extension</div>");
                   this.setValue("");
           }
           }
	       var str = trim(this.getValue());
           str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
       }
        },
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"CssSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FolderPath",
		  id:"CssFolderPath",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Folder Path",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    , 
		    render: function(c) {
		Ext.QuickTips.register({
			target: c.getEl(),
			text: 'Default location will be Css'
		});
		}
		    		    		},
			      	    	      		  	      	    											vtype:"folderpath",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FilePath",
		  id:"CssFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.css)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"Md5",
		  id:"CssMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"Status",
		  id:"CssStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"CssCreatedDate",
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
		  id:"CssLastModifiedDate",
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
		  id:"CssCreatedBy",
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
		  id:"CssLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"CssSite",
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
		  id:"CssVersion",
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
	    				
    },
                        	{
	name :"Weightage",
		  id:"CssWeightage",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Weightage",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    } ]}];




	
var DesignationForm = [
{
 title:'Designation',
 xtype:'form',
 id:'DesignationForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"DesignationId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Designation Id",
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
	name :"EntityType",
		  id:"DesignationEntityType",
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
				  value:"Designation",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"DesignationName",
		  id:"DesignationDesignationName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Designation Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"DesignationCreatedDate",
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
		  id:"DesignationLastModifiedDate",
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
		  id:"DesignationCreatedBy",
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
		  id:"DesignationLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"DesignationSite",
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
		  id:"DesignationVersion",
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




	
var DomainForm = [
{
 title:'Domain',
 xtype:'form',
 id:'DomainForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"DomainId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Domain Id",
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
	name :"EntityType",
		  id:"DomainEntityType",
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
				  value:"Domain",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"DomainName",
		  id:"DomainDomainName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Domain Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"DomainCreatedDate",
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
		  id:"DomainLastModifiedDate",
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
		  id:"DomainCreatedBy",
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
		  id:"DomainLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"DomainSite",
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
		  id:"DomainVersion",
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




	
var DomainEntityForm = [
{
 title:'DomainEntity',
 xtype:'form',
 id:'DomainEntityForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"DomainEntityId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "DomainEntity Id",
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
	name :"EntityType",
		  id:"DomainEntityEntityType",
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
				  value:"DomainEntity",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"EntityName",
		  id:"DomainEntityEntityName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Entity Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"EntityGroup",
		  id:"EntityGroupsEntityGroup",
			  //editable:false,
			  fieldLabel : "Entity Group",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["entityGroup"],
				  data : Ext.entityGroup // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"entityGroup",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('entityGroup','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"CreatedDate",
		  id:"DomainEntityCreatedDate",
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
		  id:"DomainEntityLastModifiedDate",
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
		  id:"DomainEntityCreatedBy",
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
		  id:"DomainEntityLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"DomainEntitySite",
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
		  id:"DomainEntityVersion",
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




	
var UserProfileForm = [
{
 title:'UserProfile',
 xtype:'form',
 id:'UserProfileForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"UserProfileId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "UserProfile Id",
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
	name :"EntityType",
		  id:"UserProfileEntityType",
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
				  value:"UserProfile",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"UserName",
		  id:"UserProfileUserName",
				    	    	    maxLength :15,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "User Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"Password",
		  id:"UserProfilePassword",
				    xtype: "textfield",
	    	    	    maxLength :32,
	    	    enforceMaxLength:true,
	    inputType: "password",
	    fieldLabel : "Password  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    		listeners:{
		render:function(){
		  this.setValue(''); 
		}
		},
	    											vtype:"passwordVType",
			    allowBlank:false
				
    },
                        	{
	name :"UserRole",
		  id:"UserProfileUserRole",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "User Role  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    						  value:"portaluser",
								  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Title",
		  id:"UserProfileTitle",
				    	    	    maxLength :200,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Title  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FirstName",
		  id:"UserProfileFirstName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "First Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alpha",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"MiddleName",
		  id:"UserProfileMiddleName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Middle Name",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alpha",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"LastName",
		  id:"UserProfileLastName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Last Name",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alpha",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Email",
		  id:"UserProfileEmail",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Email  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"email",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CityId",
		  id:"UserProfileCityId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "CityId",
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
	name :"City",
		  id:"UserProfileCity",
			  //editable:false,
			  fieldLabel : "City",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('City', '', 'CityName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"CityName",
	    valueField:"CityName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('UserProfileIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('UserProfileIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('UserProfileCityId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('CityName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:true
				
    },
                        	{
	name :"StateId",
		  id:"UserProfileStateId",
				    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "State Id",
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
	name :"State",
		  id:"UserProfileState",
			  //editable:false,
			  fieldLabel : "State",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"GoiDirItemName",
	    valueField:"GoiDirItemName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('UserProfileIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('UserProfileIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('UserProfileStateId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('GoiDirItemName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:true
				
    },
                        	{
	name :"Profession",
		  id:"UserProfileProfession",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Profession",
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
	name :"Gender",
		  id:"UserProfileGender",
			  //editable:false,
			  fieldLabel : "Gender  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["gender"],
				  data : Ext.gender // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"gender",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"BirthDate",
		  id:"UserProfileBirthDate",
			  editable:false,
			      fieldLabel : "Birth Date",
	      xtype : "datetimefield",
	      												    allowBlank:true
				
    },
                        	{
	name :"AgeCategory",
		  id:"UserProfileAgeCategory",
			  //editable:false,
			  fieldLabel : "Age Category  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["ageCategory"],
				  data : Ext.ageCategory // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"ageCategory",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"IsActive",
		  id:"UserProfileIsActive",
			  //editable:false,
			  fieldLabel : "IsActive  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["isActive"],
				  data : Ext.isActive // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"isActive",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"CreatedDate",
		  id:"UserProfileCreatedDate",
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
		  id:"UserProfileLastModifiedDate",
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
		  id:"UserProfileCreatedBy",
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
		  id:"UserProfileLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"UserProfileSite",
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
		  id:"UserProfileVersion",
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




	
var ActionForm = [
{
 title:'Action',
 xtype:'form',
 id:'ActionForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ActionId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Action Id",
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
	name :"EntityType",
		  id:"ActionEntityType",
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
				  value:"Action",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ActionName",
		  id:"ActionActionName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Action Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"ActionCreatedDate",
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
		  id:"ActionLastModifiedDate",
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
		  id:"ActionCreatedBy",
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
		  id:"ActionLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ActionSite",
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
		  id:"ActionVersion",
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




	
var FeedbackForm = [
{
 title:'Feedback',
 xtype:'form',
 id:'FeedbackForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"FeedbackId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Feedback Id",
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
	name :"EntityType",
		  id:"FeedbackEntityType",
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
				  value:"Feedback",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Name",
		  id:"FeedbackName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Name",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alpha",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"Email",
		  id:"FeedbackEmail",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Email",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"email",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"ContentId",
		  id:"FeedbackContentId",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Content Id",
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
	name :"Comments",
		  id:"FeedbackComments",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Comments",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"RateonAspect",
		  id:"FeedbackRateonAspect",
			  //editable:false,
			  fieldLabel : "Rateon Aspect",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["rateonAspect"],
				  data : Ext.rateonAspect // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"rateonAspect",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    									vtype:"numeric",
			    allowBlank:false
				
    },
                        	{
	name :"RateUsefullness",
		  id:"FeedbackRateUsefullness",
			  //editable:false,
			  fieldLabel : "Rate Usefullness",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["rateUsefullness"],
				  data : Ext.rateUsefullness // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"rateUsefullness",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    									vtype:"numeric",
			    allowBlank:false
				
    },
                        	{
	name :"CreatedDate",
		  id:"FeedbackCreatedDate",
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
		  id:"FeedbackLastModifiedDate",
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
		  id:"FeedbackCreatedBy",
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
		  id:"FeedbackLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"FeedbackSite",
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
		  id:"FeedbackVersion",
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




	
var FormatForm = [
{
 title:'Format',
 xtype:'form',
 id:'FormatForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"FormatId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Format Id",
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
	name :"EntityType",
		  id:"FormatEntityType",
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
				  value:"Format",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FormatName",
		  id:"FormatFormatName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Format Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"FileExtension",
		  id:"FormatFileExtension",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "File Extension  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"MimeType",
		  id:"FormatMimeType",
			  //editable:false,
			  fieldLabel : "Mime Type   <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["mimeType"],
				  data : Ext.mimeType // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"mimeType",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"CreatedDate",
		  id:"FormatCreatedDate",
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
		  id:"FormatLastModifiedDate",
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
		  id:"FormatCreatedBy",
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
		  id:"FormatLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"IsGeneratable",
		  id:"FormatIsGeneratable",
			  //editable:false,
			  fieldLabel : "IsGeneratable  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["isGeneratable"],
				  data : Ext.isGeneratable // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"isGeneratable",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"MediaGroup",
		  id:"FormatMediaGroup",
			  //editable:false,
			  fieldLabel : "Media Group  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('MediaGroup', '', 'MediaGroupName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"MediaGroupName",
	    valueField:"MediaGroupName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"Site",
		  id:"FormatSite",
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
		  id:"FormatVersion",
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




	
var GalleryForm = [
{
 title:'Gallery',
 xtype:'form',
 id:'GalleryForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"GalleryId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Gallery Id",
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
	name :"EntityType",
		  id:"GalleryEntityType",
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
				  value:"Gallery",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Status",
		  id:"GalleryStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"SeoUrl",
		  id:"GallerySeoUrl",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :150,
	      		fieldLabel : "SeoUrl",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		},
	          										vtype:"url",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Title",
		  id:"GalleryTitle",
				    	    	    maxLength :200,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Title  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"Description",
		  id:"GalleryDescription",
				      xtype : "htmleditor",
	      	      		maxLength :4000,
	      	      fieldLabel : "Description  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
												    allowBlank:false
				
    },
                   	                   	{
	name :"Channel",
		  id:"ChannelsChannel",
			  //editable:false,
			  fieldLabel : "Channels  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      	      	      queryMode:"remote",
	      	    displayField:"$fields.annotations.UI.queryField",
	    valueField:"$fields.annotations.UI.queryField",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('MediaGroupName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                         	                   	{
	name :"Category",
		  id:"CategoriesCategory",
			  //editable:false,
			  fieldLabel : "Category  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('Category', '', 'CategoryName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"CategoryName",
	    valueField:"CategoryName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('CategoryName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"AssociatedIAPath",
		  id:"GalleryAssociatedIAPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :400,
	      		fieldLabel : "Associated IA Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this,'InformationArchitecture', '', 'associatedia');
								      }
		  	      		},
	          											  	  	    	      allowBlank : false 
	    				
    },
                        	{
	name :"ContentFormat",
		  id:"GalleryContentFormat",
			  //editable:false,
			  fieldLabel : "Content Format   <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('Format', '', 'FormatName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"FormatName",
	    valueField:"FormatName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"Viewer",
		  id:"GalleryViewer",
			  //editable:false,
			  fieldLabel : "Viewer   <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('Viewer', '', 'ViewerName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"ViewerName",
	    valueField:"ViewerName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"Rating",
		  id:"GalleryRating",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Rating  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"floatStrict",
			  	  	    	      allowBlank : false
	    				
    },
                   	              	                   	{
	name :"City",
		  id:"LocationCity",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "City",
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
	name :"TotalItems",
		  id:"GalleryTotalItems",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Total Items  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Priority",
		  id:"GalleryPriority",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Priority",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'Metadata',
	xtype:'form',
	id:'GalleryForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm,
	                         	              	                                                                                                                                                                    	               	       {
      name:"Dummy",
      id:"dummy",
      hidden:true
    }
	]},{
	title:'DependentItems',
	xtype:'form',
	id:'GalleryForm3',
	autoScroll:true,
        items:[DependentItemPanelForm,
                           	              	                                                                                                                                                                    	               	                  {
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
        ]},{
	title:'RelatedItems',
	xtype:'form',
	id:'GalleryForm4',
	autoScroll:true,
        items:[RelatedItemPanelForm,
                          	{
	name :"CreatedDate",
		  id:"GalleryCreatedDate",
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
		  id:"GalleryLastModifiedDate",
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
		  id:"GalleryCreatedBy",
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
		  id:"GalleryLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"GallerySite",
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
		  id:"GalleryVersion",
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




	
var InformationArchitectureForm = [
{
 title:'InformationArchitecture',
 xtype:'form',
 id:'InformationArchitectureForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"InformationArchitectureId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "InformationArchitecture Id",
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
	name :"EntityType",
		  id:"InformationArchitectureEntityType",
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
				  value:"InformationArchitecture",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"IaName",
		  id:"InformationArchitectureIaName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "IA Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		  		      ,
		      change:function(){
						setTreePath();
		      }
		  	      		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"InformationArchitectureSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FilePath",
		  id:"InformationArchitectureFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:true
				
    },
                        	{
	name :"ShortDescription",
		  id:"InformationArchitectureShortDescription",
				      xtype : "htmleditor",
	      	      	      fieldLabel : "Short Description",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
												    allowBlank:true
				
    },
                        	{
	name :"ParentIAID",
		  id:"InformationArchitectureParentIAID",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Parent IA ID",
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
	name :"ParentIAName",
		  id:"InformationArchitectureParentIAName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :1000,
	      		fieldLabel : "Parent IA Name",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this, 'InformationArchitecture', 'ParentIAID' ,'parentchild');
								      }
		  	      		},
	          											  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"IaPath",
		  id:"InformationArchitectureIaPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :400,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "IA Path",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Md5",
		  id:"InformationArchitectureMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
	name :"IsTopLevel",
		  id:"InformationArchitectureIsTopLevel",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "IsTopLevel",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    			  hidden:true,
					  value:"true",
								  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"PageLevel",
		  id:"InformationArchitecturePageLevel",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Page Level  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"Visible",
		  id:"InformationArchitectureVisible",
			  //editable:false,
			  fieldLabel : "Visible  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["visible"],
				  data : Ext.visible // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"visible",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"Status",
		  id:"InformationArchitectureStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"InformationArchitectureCreatedDate",
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
		  id:"InformationArchitectureLastModifiedDate",
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
		  id:"InformationArchitectureCreatedBy",
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
		  id:"InformationArchitectureLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"InformationArchitectureSite",
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
		  id:"InformationArchitectureVersion",
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
	    				
    },
                        	{
	name :"Weightage",
		  id:"InformationArchitectureWeightage",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Weightage",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                          {
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'Metadata',
	xtype:'form',
	id:'InformationArchitectureForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];




	
var InterfaceInfoForm = [
{
 title:'InterfaceInfo',
 xtype:'form',
 id:'InterfaceInfoForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"InterfaceInfoId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "InterfaceInfo Id",
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
	name :"EntityType",
		  id:"InterfaceInfoEntityType",
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
				  value:"InterfaceInfo",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"InterfaceName",
		  id:"InterfaceInfoInterfaceName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Interface Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                   	                   	{
	name :"InterfaceField",
		  id:"InterfaceFieldsInterfaceField",
				  fieldLabel : "FieldName",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('ClassAttributes', '', 'FieldName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"FieldName",
	    valueField:"FieldName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('FieldName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:true
				
    },
         	                              	{
	name :"CreatedDate",
		  id:"InterfaceInfoCreatedDate",
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
		  id:"InterfaceInfoLastModifiedDate",
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
		  id:"InterfaceInfoCreatedBy",
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
		  id:"InterfaceInfoLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"InterfaceInfoSite",
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
		  id:"InterfaceInfoVersion",
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




	
var JsForm = [
{
 title:'Js',
 xtype:'form',
 id:'JsForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"JsId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Js Id",
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
	name :"EntityType",
		  id:"JsEntityType",
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
				  value:"Js",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"JsName",
		  id:"JsJsName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Js Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				       listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.js)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>JsName should end with valid file extension</div>");
                   this.setValue("");
           }
           }
	       var str = trim(this.getValue());
           str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
       }
        },
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"JsSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FolderPath",
		  id:"JsFolderPath",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Folder Path",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    , 
		    render: function(c) {
		Ext.QuickTips.register({
			target: c.getEl(),
			text: 'Default location will be Js'
		});
		}
		    		    		},
			      	    	      		  	      	    											vtype:"folderpath",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FilePath",
		  id:"JsFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.js)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"Md5",
		  id:"JsMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"Status",
		  id:"JsStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"JsCreatedDate",
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
		  id:"JsLastModifiedDate",
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
		  id:"JsCreatedBy",
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
		  id:"JsLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"JsSite",
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
		  id:"JsVersion",
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
	    				
    },
                        	{
	name :"Weightage",
		  id:"JsWeightage",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Weightage",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    } ]}];




	
var LanguageForm = [
{
 title:'Language',
 xtype:'form',
 id:'LanguageForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"LanguageId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Language Id",
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
	name :"EntityType",
		  id:"LanguageEntityType",
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
				  value:"Language",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"LanguageName",
		  id:"LanguageLanguageName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Language Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"LanguageCode",
		  id:"LanguageLanguageCode",
				    	    minLength :3,
	    	    	    maxLength :3,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Language Code  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"LanguageCreatedDate",
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
		  id:"LanguageLastModifiedDate",
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
		  id:"LanguageCreatedBy",
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
		  id:"LanguageLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"LanguageSite",
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
		  id:"LanguageVersion",
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




	
var MediaGroupForm = [
{
 title:'MediaGroup',
 xtype:'form',
 id:'MediaGroupForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"MediaGroupId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MediaGroup Id",
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
	name :"EntityType",
		  id:"MediaGroupEntityType",
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
				  value:"MediaGroup",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"MediaGroupName",
		  id:"MediaGroupMediaGroupName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Media Group Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"MediaGroupCreatedDate",
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
		  id:"MediaGroupCreatedBy",
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
		  id:"MediaGroupLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"LastModifiedDate",
		  id:"MediaGroupLastModifiedDate",
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
	name :"IsGeneratable",
		  id:"MediaGroupIsGeneratable",
			  //editable:false,
			  fieldLabel : "IsGeneratable  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["isGeneratable"],
				  data : Ext.isGeneratable // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"isGeneratable",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                        	{
	name :"Site",
		  id:"MediaGroupSite",
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
		  id:"MediaGroupVersion",
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




	
var MetadataInfoForm = [
{
 title:'MetadataInfo',
 xtype:'form',
 id:'MetadataInfoForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
            	                   	{
	name :"AdTag",
		  id:"AdTagsAdTag",
				  fieldLabel : "Ad Tags",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createPreloadedStore('Article', '', 'AdTag'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"AdTag",
	    valueField:"AdTag",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('AdTag','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                         	                   	{
	name :"Tag",
		  id:"TagsTag",
				  fieldLabel : "Tags",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createPreloadedStore('Article', '', 'Tag'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"Tag",
	    valueField:"Tag",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('Tag','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"Caption",
		  id:"MetadataInfoCaption",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Caption",
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
	name :"Personality",
		  id:"PersonalitiesPersonality",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Personality",
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
	name :"Event",
		  id:"EventsEvent",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Events",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    												  	  	    	      allowBlank : false
	    				
    },
         	                              	{
	name :"Format",
		  id:"MetadataInfoFormat",
			  //editable:false,
			  fieldLabel : "Format   <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('Format', '', 'FormatName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"FormatName",
	    valueField:"FormatName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    									vtype:"alphanumMid",
			    allowBlank:true
				
    },
                        	{
	name :"LanguageId",
		  id:"MetadataInfoLanguageId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Language Id",
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
	name :"Language",
		  id:"MetadataInfoLanguage",
			  //editable:false,
			  fieldLabel : "Language  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('Language', '', 'LanguageCode'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"LanguageCode",
	    valueField:"LanguageCode",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('MetadataInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('MetadataInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('MetadataInfoLanguageId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('LanguageCode','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    									vtype:"alphanumMid",
			    allowBlank:true
				
    },
                        	{
	name :"EventDate",
		  id:"MetadataInfoEventDate",
			  editable:false,
			      fieldLabel : "Event Date",
	      xtype : "datetimefield",
	      												    allowBlank:true
				
    },
                        	{
	name :"GoLiveDate",
		  id:"MetadataInfoGoLiveDate",
			  editable:false,
			      fieldLabel : "Go Live Date",
	      xtype : "datetimefield",
	      												    allowBlank:true
				
    },
                        	{
	name :"ExpiryDate",
		  id:"MetadataInfoExpiryDate",
			  editable:false,
			      fieldLabel : "Expiry Date",
	      xtype : "datetimefield",
	      												    allowBlank:true
				
    },
                        	{
	name :"Source",
		  id:"MetadataInfoSource",
				  fieldLabel : "Source",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('Source', '', 'SourceName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"SourceName",
	    valueField:"SourceName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('SourceName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    									vtype:"alphanumMid",
			    allowBlank:true
				
    },
                        	{
	name :"SourceProperties",
		  id:"MetadataInfoSourceProperties",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Source Properties",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumMid",
			  	  	    	      allowBlank :true
	    				
    } ]}];




	
var PmsMediaForm = [
{
 title:'PmsMedia',
 xtype:'form',
 id:'PmsMediaForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"PmsMediaId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "PmsMedia Id",
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
	name :"EntityType",
		  id:"PmsMediaEntityType",
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
				  value:"PmsMedia",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"PmsMediaName",
		  id:"PmsMediaPmsMediaName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Pms Media Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				       listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.zip)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>PmsMediaName should end with valid file extension</div>");
                   this.setValue("");
           }
           }
	       var str = trim(this.getValue());
           str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
       }
        },
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"PmsMediaSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FolderPath",
		  id:"PmsMediaFolderPath",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Folder Path",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    , 
		    render: function(c) {
		Ext.QuickTips.register({
			target: c.getEl(),
			text: 'Default location will be PmsMedia'
		});
		}
		    		    		},
			      	    	      		  	      	    											vtype:"folderpath",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FilePath",
		  id:"PmsMediaFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.png|.gif|.zip)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"Status",
		  id:"PmsMediaStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Md5",
		  id:"PmsMediaMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
		  id:"PmsMediaCreatedDate",
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
		  id:"PmsMediaLastModifiedDate",
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
		  id:"PmsMediaCreatedBy",
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
		  id:"PmsMediaLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"PmsMediaSite",
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
		  id:"PmsMediaVersion",
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
	    				
    },
                        	{
	name :"Weightage",
		  id:"PmsMediaWeightage",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Weightage",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    } ]}];




	
var RoleForm = [
{
 title:'Role',
 xtype:'form',
 id:'RoleForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"RoleId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Role Id",
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
	name :"EntityType",
		  id:"RoleEntityType",
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
				  value:"Role",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"RoleName",
		  id:"RoleRoleName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Role Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"ParentRoleId",
		  id:"RoleParentRoleId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Parent Role Id",
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
	name :"ParentRoleName",
		  id:"RoleParentRoleName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :1000,
	      		fieldLabel : "Parent Role Name",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this, 'Role', 'ParentRoleId' ,'parentchild');
								      }
		  	      		},
	          											  	  	    	      allowBlank :true
	    				
    },
                   	                   	{
	name :"DomainEntity",
		  id:"DomainEntitiesDomainEntity",
			  //editable:false,
			  fieldLabel : "Domain Entity  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      				  		    store:createStore('DomainEntity', '', 'EntityName'),
		  			      	      	      queryMode:"local",
	      	    displayField:"EntityName",
	    valueField:"EntityName",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('EntityName','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                         	                   	{
	name :"UiTab",
		  id:"UiTabsUiTab",
			  //editable:false,
			  fieldLabel : "UITab  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	      multiSelect:true,
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["uiTab"],
				  data : Ext.uiTab // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"uiTab",
	    	    emptyText:"",

	    	      listeners:{
		  beforerender:function(combo){
		      this.store.load();
		  },
		  render:function(combo){
		      this.store.sort('uiTab','ASC');
		  }
		  ,expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
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
		},
	    										    allowBlank:false
				
    },
         	                              	{
	name :"CreatedDate",
		  id:"RoleCreatedDate",
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
		  id:"RoleLastModifiedDate",
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
		  id:"RoleCreatedBy",
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
		  id:"RoleLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"IsTopLevel",
		  id:"RoleIsTopLevel",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "IsTopLevel",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    			  hidden:true,
					  value:"true",
								  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"IsSuperAdmin",
		  id:"RoleIsSuperAdmin",
			  //editable:false,
			  fieldLabel : "IsSuperAdmin",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      store:Ext.create('Ext.data.ArrayStore', {
				  fields: ["isSuperAdmin"],
				  data : Ext.isSuperAdmin // pick boolean values from a store
			      }),
	      queryMode:"local",
	      displayField:"isSuperAdmin",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    				  value:"false",
								    allowBlank:false
				
    },
                        	{
	name :"Site",
		  id:"RoleSite",
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
		  id:"RoleVersion",
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




	
var SourceForm = [
{
 title:'Source',
 xtype:'form',
 id:'SourceForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"SourceId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Source Id",
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
	name :"EntityType",
		  id:"SourceEntityType",
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
				  value:"Source",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"SourceName",
		  id:"SourceSourceName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"SourceCreatedDate",
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
		  id:"SourceLastModifiedDate",
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
		  id:"SourceCreatedBy",
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
		  id:"SourceLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"SourceSite",
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
		  id:"SourceVersion",
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




	
var StageForm = [
{
 title:'Stage',
 xtype:'form',
 id:'StageForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"StageId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Stage Id",
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
	name :"EntityType",
		  id:"StageEntityType",
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
				  value:"Stage",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"StageName",
		  id:"StageStageName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Stage Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"StageCreatedDate",
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
		  id:"StageLastModifiedDate",
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
		  id:"StageCreatedBy",
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
		  id:"StageLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ColorCode",
		  id:"StageColorCode",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"colorfield",
	    	    fieldLabel : "ColorCode",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumMid",
			    allowBlank:true
				
    },
                        	{
	name :"Site",
		  id:"StageSite",
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
		  id:"StageVersion",
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




	
var StatusForm = [
{
 title:'Status',
 xtype:'form',
 id:'StatusForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"StatusId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Status Id",
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
	name :"EntityType",
		  id:"StatusEntityType",
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
				  value:"Status",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"StatusName",
		  id:"StatusStatusName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Status Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"StatusCreatedDate",
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
		  id:"StatusLastModifiedDate",
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
		  id:"StatusCreatedBy",
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
		  id:"StatusLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"StatusSite",
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
		  id:"StatusVersion",
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




	
var TemplateForm = [
{
 title:'Template',
 xtype:'form',
 id:'TemplateForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"TemplateId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Template Id",
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
	name :"EntityType",
		  id:"TemplateEntityType",
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
				  value:"Template",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"TemplateName",
		  id:"TemplateTemplateName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Template Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				       listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.html|.tpl)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>TemplateName should end with valid file extension</div>");
                   this.setValue("");
           }
           }
	       var str = trim(this.getValue());
           str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
       }
        },
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"TemplateSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FilePath",
		  id:"TemplateFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.html|.tpl)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"Status",
		  id:"TemplateStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"TemplateCreatedDate",
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
		  id:"TemplateLastModifiedDate",
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
		  id:"TemplateCreatedBy",
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
		  id:"TemplateLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"TemplateSite",
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
	name :"Md5",
		  id:"TemplateMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
		  id:"TemplateVersion",
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




	
var ValidationsForm = [
{
 title:'Validations',
 xtype:'form',
 id:'ValidationsForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ValidationsId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Validations Id",
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
	name :"EntityType",
		  id:"ValidationsEntityType",
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
				  value:"Validations",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"XlsName",
		  id:"ValidationsXlsName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Xls Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				       listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>XlsName should end with valid file extension</div>");
                   this.setValue("");
           }
           }
	       var str = trim(this.getValue());
           str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
       }
        },
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"ValidationsSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"FilePath",
		  id:"ValidationsFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"Md5",
		  id:"ValidationsMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
		  id:"ValidationsCreatedDate",
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
		  id:"ValidationsLastModifiedDate",
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
		  id:"ValidationsCreatedBy",
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
		  id:"ValidationsLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ValidationsSite",
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
		  id:"ValidationsVersion",
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




	
var ViewerForm = [
{
 title:'Viewer',
 xtype:'form',
 id:'ViewerForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ViewerId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Viewer Id",
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
	name :"EntityType",
		  id:"ViewerEntityType",
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
				  value:"Viewer",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ViewerName",
		  id:"ViewerViewerName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Viewer Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"CreatedDate",
		  id:"ViewerCreatedDate",
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
		  id:"ViewerLastModifiedDate",
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
		  id:"ViewerCreatedBy",
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
		  id:"ViewerLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ViewerSite",
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
		  id:"ViewerVersion",
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




	
var WidgetForm = [
{
 title:'Widget',
 xtype:'form',
 id:'WidgetForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"WidgetId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Widget Id",
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
	name :"EntityType",
		  id:"WidgetEntityType",
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
				  value:"Widget",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"WidgetName",
		  id:"WidgetWidgetName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Widget Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
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
	name :"WidgetType",
		  id:"WidgetWidgetType",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Widget Type",
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
	name :"WidgetCode",
		  id:"WidgetWidgetCode",
				      xtype : "textarea",
	      	      		maxLength :1000,
	      		fieldLabel : "Widget Code  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      	      height:200,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		},
	          											  	  	    	      allowBlank : false
	    				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"Status",
		  id:"WidgetStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"WidgetCreatedDate",
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
		  id:"WidgetLastModifiedDate",
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
		  id:"WidgetCreatedBy",
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
		  id:"WidgetLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"WidgetSite",
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
		  id:"WidgetVersion",
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
	    				
    },
                        	{
	name :"Weightage",
		  id:"WidgetWeightage",
				    	    minLength :1,
	    	    	    maxLength :10,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Weightage",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    } ]}];




	
var ImageForm = [
{
 title:'Image',
 xtype:'form',
 id:'ImageForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ImageId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Media Id",
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
	name :"EntityType",
		  id:"ImageEntityType",
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
				  value:"Image",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Status",
		  id:"ImageStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"SeoUrl",
		  id:"ImageSeoUrl",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :150,
	      		fieldLabel : "SeoUrl",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		},
	          										vtype:"url",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Title",
		  id:"ImageTitle",
				    	    	    maxLength :200,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Title",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ImageName",
		  id:"ImageImageName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Media Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"ImageSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"ShortDescription",
		  id:"ImageShortDescription",
				      xtype : "htmleditor",
	      	      		maxLength :500,
	      	      fieldLabel : "Short Description  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
												    allowBlank:false
				
    },
                        	{
	name :"AssociatedIAPath",
		  id:"ImageAssociatedIAPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :400,
	      		fieldLabel : "Associated IA Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this,'InformationArchitecture', '', 'associatedia');
								      }
		  	      		},
	          											  	  	    	      allowBlank : false 
	    				
    },
                        	{
	name :"FilePath",
		  id:"ImageFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"ThumbnailPath",
		  id:"ImageThumbnailPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Thumbnail Path",
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
	name :"FileSize",
		  id:"ImageFileSize",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Size",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Md5",
		  id:"ImageMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
	name :"Height",
		  id:"ImageHeight",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Height",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"floatStrict",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Width",
		  id:"ImageWidth",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Width",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"floatStrict",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"MimeSimpleName",
		  id:"ImageMimeSimpleName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Mime Simple Name",
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
	name :"MimeType",
		  id:"ImageMimeType",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Mime Type",
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
	name :"ContentFormat",
		  id:"ImageContentFormat",
			  //editable:false,
			  fieldLabel : "Content Format   <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('Format', '', 'FormatName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"FormatName",
	    valueField:"FormatName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'Metadata',
	xtype:'form',
	id:'ImageForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm,
	                              	{
	name :"CreatedDate",
		  id:"ImageCreatedDate",
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
		  id:"ImageLastModifiedDate",
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
		  id:"ImageCreatedBy",
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
		  id:"ImageLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ImageSite",
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
		  id:"ImageVersion",
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




	
var VideoForm = [
{
 title:'Video',
 xtype:'form',
 id:'VideoForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"VideoId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Media Id",
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
	name :"EntityType",
		  id:"VideoEntityType",
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
				  value:"Video",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Status",
		  id:"VideoStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"SeoUrl",
		  id:"VideoSeoUrl",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :150,
	      		fieldLabel : "SeoUrl",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		},
	          										vtype:"url",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Title",
		  id:"VideoTitle",
				    	    	    maxLength :200,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Title",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"alphanumLoose",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"VideoName",
		  id:"VideoVideoName",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Video Name  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"filename",
			  	  	    	      allowBlank : false
	    				
    },
                        	{
	name :"SourceName",
		  id:"VideoSourceName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "SourceName",
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
	name :"ShortDescription",
		  id:"VideoShortDescription",
				      xtype : "htmleditor",
	      	      		maxLength :500,
	      	      fieldLabel : "Short Description  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",  
	      height:200,
	      value:"",
	      defaultValue:"",
	      anchor :'100%',
	      listeners:{
		blur:function(){
		    var str = trim(this.getValue());
		    str = str.replace(/ +(?= )/g,'');
		    this.setValue(str);
		}
	      },
												    allowBlank:false
				
    },
                        	{
	name :"AssociatedIAPath",
		  id:"VideoAssociatedIAPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			      xtype : "textarea",
	      	      		maxLength :400,
	      		fieldLabel : "Associated IA Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      		height:40,
	      	      value:"",
	      defaultValue:"",
	      anchor :'100%', 
	      		listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		  		      ,
		      focus:function(){
									    showTreePanelWindow(this,'InformationArchitecture', '', 'associatedia');
								      }
		  	      		},
	          											  	  	    	      allowBlank : false 
	    				
    },
                        	{
	name :"FilePath",
		  id:"VideoFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"ThumbnailPath",
		  id:"VideoThumbnailPath",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Thumbnail Path",
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
	name :"FileSize",
		  id:"VideoFileSize",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "File Size",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Md5",
		  id:"VideoMd5",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "MD5",
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
	name :"Bitrate",
		  id:"VideoBitrate",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "BitRate",
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
	name :"PlayDuration",
		  id:"VideoPlayDuration",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "PlayDuration",
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
	name :"MimeSimpleName",
		  id:"VideoMimeSimpleName",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Mime Simple Name",
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
	name :"MimeType",
		  id:"VideoMimeType",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Mime Type",
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
	name :"ContentFormat",
		  id:"VideoContentFormat",
			  //editable:false,
			  fieldLabel : "Content Format   <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createStore('Format', '', 'FormatName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"FormatName",
	    valueField:"FormatName",
	    	    emptyText:"",

	    	      listeners:{
		  expand : function(field, eOpts){
		    field.queryMode = "local";
		  },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
              combo.store.load();
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
		},
	    										    allowBlank:false
				
    },
                   	                   	{
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'Metadata',
	xtype:'form',
	id:'VideoForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm,
	                              	{
	name :"CreatedDate",
		  id:"VideoCreatedDate",
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
		  id:"VideoLastModifiedDate",
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
		  id:"VideoCreatedBy",
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
		  id:"VideoLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"VideoSite",
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
		  id:"VideoVersion",
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




	
var ThumbnailForm = [
{
 title:'Thumbnail',
 xtype:'form',
 id:'ThumbnailForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"ThumbnailId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Media Id",
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
	name :"EntityType",
		  id:"ThumbnailEntityType",
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
				  value:"Thumbnail",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"ParentItemId",
		  id:"ThumbnailParentItemId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Parent IA ID",
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
	name :"FilePath",
		  id:"ThumbnailFilePath",
				      xtype:"filefield",
	      fieldLabel : "File Path  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
	      				  		         listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setRawValue("");
           }
           }
       }
        },
		  			      		  		    		  	      												    allowBlank:false
				
    },
                        	{
	name :"Size",
		  id:"ThumbnailSize",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Size",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"FileSize",
		  id:"ThumbnailFileSize",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Size",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"numeric",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Height",
		  id:"ThumbnailHeight",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Height",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"floatStrict",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Width",
		  id:"ThumbnailWidth",
				    	    	    maxLength :50,
	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Width",
	    	      				  listeners:{
		    blur:function(){
		      var str = trim(this.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      this.setValue(str);
		    }
		    		    		},
			      	    	      		  	      	    											vtype:"floatStrict",
			  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"CreatedDate",
		  id:"ThumbnailCreatedDate",
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
		  id:"ThumbnailLastModifiedDate",
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
		  id:"ThumbnailCreatedBy",
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
		  id:"ThumbnailLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"ThumbnailSite",
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
		  id:"ThumbnailVersion",
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




	
var EmbedCodeForm = [
{
 title:'EmbedCode',
 xtype:'form',
 id:'EmbedCodeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                 	{
	name :"Id",
		  id:"EmbedCodeId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Media Id",
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
	name :"EntityType",
		  id:"EmbedCodeEntityType",
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
				  value:"EmbedCode",
									  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Status",
		  id:"EmbedCodeStatus",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    maxLength :50,
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
			      	    	      		  	      	    												  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Title",
		  id:"EmbedCodeTitle",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Title",
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
	name :"Description",
		  id:"EmbedCodeDescription",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Description",
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
	name :"WorkflowId",
		  id:"WorkflowInfoWorkflowId",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Workflow Id",
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
	name :"WorkflowName",
		  id:"WorkflowInfoWorkflowName",
			  //editable:false,
			  fieldLabel : "Choose Workflow  <span style='color: rgb(255, 0, 0); padding-left: 2px;'>*</span>",
				    xtype:"combobox",
	    typeAhead : true,
	    minChars:1,
	    enableKeyEvents:true,
	    forceSelection: false,
	    triggerAction: 'all',
	    	    	      				  		    store:createPreloadedStore('Workflow', 'contentcreator', 'WorkflowName'),
		  			      	      	      queryMode:"remote",
	      	    displayField:"WorkflowName",
	    valueField:"WorkflowName",
	    	    emptyText:"",

	    	      listeners:{
	      select:function(){
		var v = this.getValue();
		var i = this.store.findExact(this.valueField || this.displayField, v);
				if(typeof(Ext.getCmp('WorkflowInfoIsTopLevel')) != "undefined" && v != ""){
		    var isTopLevel = Ext.getCmp('WorkflowInfoIsTopLevel');
		    isTopLevel.setValue("false");
		}
		  if(v != ""){   
		    var pId = Ext.getCmp('WorkflowInfoWorkflowId');
		    pId.setValue(this.store.getAt(i).get('Id'));
		  }
		  
	      }
	      	      , 
	      		      render: function(combo,options){
			this.store.sort('WorkflowName','ASC');
		      }
	      
	      		    ,expand : function(field, eOpts){
		      field.queryMode = "local";
		    },
          focus : function(combo){
              combo.forceSelection=true;
              combo.store.clearFilter(true);
                              combo.store.load();
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
	      },    
	    										    allowBlank:false
				
    },
                        	{
	name :"CurrentUser",
		  id:"WorkflowInfoCurrentUser",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CurrentAction",
		  id:"WorkflowInfoCurrentAction",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Current Action",
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
	name :"Stage",
		  id:"WorkflowInfoStage",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"WorkflowComments",
		  id:"AssignedToRolesWorkflowComments",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
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
	name :"CreatedDate",
		  id:"EmbedCodeCreatedDate",
			  readOnly:true,
	  fieldCls:'readonly-background-class',
			    	    	    enforceMaxLength:true,
	    	      xtype:"textfield",
	    	    fieldLabel : "Parent IA ID",
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
	name :"LastModifiedDate",
		  id:"EmbedCodeLastModifiedDate",
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
		  id:"EmbedCodeCreatedBy",
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
		  id:"EmbedCodeLastModifiedBy",
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
		  this.setValue(cmsUserName);
	    }
	  },
						  	  	    	      allowBlank :true
	    				
    },
                        	{
	name :"Site",
		  id:"EmbedCodeSite",
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
		  id:"EmbedCodeVersion",
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
var AccessControlListColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Xls Name",
        width:250,
        editor:"textfield",
        dataIndex:"XlsName"
     },
                                                                   {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                    {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ArticleColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                         {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var AuthenticationColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var CategoryColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Category Name",
        width:250,
        editor:"textfield",
        dataIndex:"CategoryName"
     },
                                         {
                header:"Parent Category Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentCategoryName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ChannelColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Channel Name",
        width:250,
        editor:"textfield",
        dataIndex:"ChannelName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                                         {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var CityColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"City Name",
        width:250,
        editor:"textfield",
        dataIndex:"CityName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ClassAttributesColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Field Name",
        width:250,
        editor:"textfield",
        dataIndex:"FieldName"
     },
                            {
                header:"Field Type",
        width:250,
        editor:"textfield",
        dataIndex:"FieldType"
     },
                                         {
                header:"Type",
        width:250,
        editor:"textfield",
        dataIndex:"Type"
     },
                            {
                header:"CData",
        width:250,
        editor:"textfield",
        dataIndex:"Cdata"
     },
                            {
                header:"DefaultSearch",
        width:250,
        editor:"textfield",
        dataIndex:"DefaultSearch"
     },
                                                      {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ClassImportColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Import Name",
        width:250,
        editor:"textfield",
        dataIndex:"ImportName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ClassInfoColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Class Name",
        width:250,
        editor:"textfield",
        dataIndex:"ClassName"
     },
                            {
                header:"MasterData",
        width:250,
        editor:"textfield",
        dataIndex:"MasterData"
     },
                            {
                header:"TreeEntity",
        width:250,
        editor:"textfield",
        dataIndex:"TreeEntity"
     },
                            {
                header:"UI",
        width:250,
        editor:"textfield",
        dataIndex:"UI"
     },
                            {
                header:"UI Form",
        width:250,
        editor:"textfield",
        dataIndex:"UIForm"
     },
                                                                    {
                header:"Entity Group",
        width:250,
        editor:"textfield",
        dataIndex:"EntityGroup"
     },
                                 {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                         {
                header:"Edit Option",
        width:250,
        editor:"textfield",
        dataIndex:"EditOption"
     },
                            {
                header:"Download Option",
        width:250,
        editor:"textfield",
        dataIndex:"DownloadOption"
     },
                                                                                                                             {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ClassPackageColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Package Name",
        width:250,
        editor:"textfield",
        dataIndex:"ClassPackageName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ContentPartnerColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                                         {
                header:"Source",
        width:250,
        editor:"textfield",
        dataIndex:"Source"
     },
                                         {
                header:"Supplement Name",
        width:250,
        editor:"textfield",
        dataIndex:"SupplementName"
     },
                                         {
                header:"No Of Days to Maintain Feed",
        width:250,
        editor:"textfield",
        dataIndex:"NoOfDays"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var CountryColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Country Name",
        width:250,
        editor:"textfield",
        dataIndex:"CountryName"
     },
                                         {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var CssColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Css Name",
        width:250,
        editor:"textfield",
        dataIndex:"CssName"
     },
                                         {
                header:"Folder Path",
        width:250,
        editor:"textfield",
        dataIndex:"FolderPath"
     },
                                                                                                                                                                                                                                                           {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                 {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var DesignationColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Designation Name",
        width:250,
        editor:"textfield",
        dataIndex:"DesignationName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var DomainColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Domain Name",
        width:250,
        editor:"textfield",
        dataIndex:"DomainName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var DomainEntityColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Entity Name",
        width:250,
        editor:"textfield",
        dataIndex:"EntityName"
     },
                                       {
                header:"Entity Group",
        width:250,
        editor:"textfield",
        dataIndex:"EntityGroup"
     },
                                 {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var UserProfileColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"User Name",
        width:250,
        editor:"textfield",
        dataIndex:"UserName"
     },
                                         {
                header:"User Role",
        width:250,
        editor:"textfield",
        dataIndex:"UserRole"
     },
                                                                                {
                header:"Email",
        width:250,
        editor:"textfield",
        dataIndex:"Email"
     },
                                         {
                header:"City",
        width:250,
        editor:"textfield",
        dataIndex:"City"
     },
                                                                                                          {
                header:"IsActive",
        width:250,
        editor:"textfield",
        dataIndex:"IsActive"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ActionColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Action Name",
        width:250,
        editor:"textfield",
        dataIndex:"ActionName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var FeedbackColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Name",
        width:250,
        editor:"textfield",
        dataIndex:"Name"
     },
                            {
                header:"Email",
        width:250,
        editor:"textfield",
        dataIndex:"Email"
     },
                            {
                header:"Content Id",
        width:250,
        editor:"textfield",
        dataIndex:"ContentId"
     },
                                                                   {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                        {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var FormatColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Format Name",
        width:250,
        editor:"textfield",
        dataIndex:"FormatName"
     },
                                                      {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                                {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var GalleryColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                         {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var InformationArchitectureColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"IA Name",
        width:250,
        editor:"textfield",
        dataIndex:"IaName"
     },
                                                                                {
                header:"Parent IA Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentIAName"
     },
                                                                                                                                                                                                                                                                                                  {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                                                                                                                                                                                                                                                                          {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var InterfaceInfoColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Interface Name",
        width:250,
        editor:"textfield",
        dataIndex:"InterfaceName"
     },
                                                         {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var JsColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Js Name",
        width:250,
        editor:"textfield",
        dataIndex:"JsName"
     },
                                         {
                header:"Folder Path",
        width:250,
        editor:"textfield",
        dataIndex:"FolderPath"
     },
                                                                                                                                                                                                                                                           {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                 {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var LanguageColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Language Name",
        width:250,
        editor:"textfield",
        dataIndex:"LanguageName"
     },
                            {
                header:"Language Code",
        width:250,
        editor:"textfield",
        dataIndex:"LanguageCode"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var MediaGroupColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Media Group Name",
        width:250,
        editor:"textfield",
        dataIndex:"MediaGroupName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                         {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var MetadataInfoColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                                                                                                                                                                                                                 {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var PageColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Page Name",
        width:250,
        editor:"textfield",
        dataIndex:"PageName"
     },
                            {
                header:"Page Extension",
        width:250,
        editor:"textfield",
        dataIndex:"PageExtension"
     },
                            {
                header:"Template Name",
        width:250,
        editor:"textfield",
        dataIndex:"TemplateName"
     },
                                                                                                                                                                                                                                              {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var PageAssociatorColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Page Name",
        width:250,
        editor:"textfield",
        dataIndex:"PageName"
     },
                            {
                header:"Page Entity Name",
        width:250,
        editor:"textfield",
        dataIndex:"PageEntityName"
     },
                            {
                header:"IA Name",
        width:250,
        editor:"textfield",
        dataIndex:"IaName"
     },
                                                                                                                                                                                                                                 {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var PmsMediaColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Pms Media Name",
        width:250,
        editor:"textfield",
        dataIndex:"PmsMediaName"
     },
                                         {
                header:"Folder Path",
        width:250,
        editor:"textfield",
        dataIndex:"FolderPath"
     },
                                                                                                                                                                                                                                              {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                         {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                 {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var PropertiesColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                  {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var PropertyColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                  {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var RoleColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Role Name",
        width:250,
        editor:"textfield",
        dataIndex:"RoleName"
     },
                                         {
                header:"Parent Role Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentRoleName"
     },
                                                                                      {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                                {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var SourceColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"SourceName",
        width:250,
        editor:"textfield",
        dataIndex:"SourceName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var StageColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Stage Name",
        width:250,
        editor:"textfield",
        dataIndex:"StageName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                   {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var StatusColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Status Name",
        width:250,
        editor:"textfield",
        dataIndex:"StatusName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var TemplateColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Template Name",
        width:250,
        editor:"textfield",
        dataIndex:"TemplateName"
     },
                                                                                                                                                                                                                                                           {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                 {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var CmsUserProfileColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"User Name",
        width:250,
        editor:"textfield",
        dataIndex:"UserName"
     },
                                                      {
                header:"User Role",
        width:250,
        editor:"textfield",
        dataIndex:"UserRole"
     },
                                                                                {
                header:"Email",
        width:250,
        editor:"textfield",
        dataIndex:"Email"
     },
                                         {
                header:"City",
        width:250,
        editor:"textfield",
        dataIndex:"City"
     },
                                                                                             {
                header:"IsActive",
        width:250,
        editor:"textfield",
        dataIndex:"IsActive"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ValidationsColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Xls Name",
        width:250,
        editor:"textfield",
        dataIndex:"XlsName"
     },
                                                                   {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                    {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ViewerColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Viewer Name",
        width:250,
        editor:"textfield",
        dataIndex:"ViewerName"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var WidgetColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Widget Name",
        width:250,
        editor:"textfield",
        dataIndex:"WidgetName"
     },
                            {
                header:"Widget Type",
        width:250,
        editor:"textfield",
        dataIndex:"WidgetType"
     },
                                                                                                                                                                                                                                              {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                            {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                                   {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var WorkflowColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Workflow Name",
        width:250,
        editor:"textfield",
        dataIndex:"WorkflowName"
     },
                                                                                                                                                                                                                                                                                                                           {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                         {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                                         {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var WorkflowStageColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                                                                                                                                   {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var WorkflowStagesColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                                                                                                                                                   {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ImageColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                         {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                            {
                header:"Media Name",
        width:250,
        editor:"textfield",
        dataIndex:"ImageName"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                    {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var VideoColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                         {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                            {
                header:"Video Name",
        width:250,
        editor:"textfield",
        dataIndex:"VideoName"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                    {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var ThumbnailColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                                                                                                             {
                header:"Created Date",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedDate"
     },
                            {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                    {	
	       header: 'Download',
	       xtype:'actioncolumn', 
	       width: 100,
	       align : 'center',
               scope:this,
	       items: [{
		 icon: imagePath+'/icons/application_put.png',  // Use a URL in the icon config
		 tooltip: 'Download',
                 handler:function(grid,rowIndex,colIndex){
                    var rec = grid.getStore().getAt(rowIndex);
                    if(!rec.data.FilePath)
                       Ext.DropDownAlert.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
                    else
                      window.open(location.protocol+'//'+location.host+mediaPath+rec.data.FilePath+'?format=download');
		  }
	       }]
    },
      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];var EmbedCodeColumn = [
    {	
	      header:'&nbsp;',
	       xtype:'actioncolumn', 
	       width:5,
	       sortable :false,
	       menuDisabled:true, 
	       align : 'center',
	       dataIndex:"Stage",
	       scope:this,
	       tdCls: 'x-stage-cell'
    },

                                               {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                                                                                                                                                                                                                                                        {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
     },
                                                      {	
	       header: 'Edit',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
           scope:this,
	       items: [{
		   icon: imagePath+'/icons/application_edit.png',  // Use a URL in the icon config
		   tooltip: 'Edit',
                 handler:function(grid,rowIndex,colIndex){
                                    createWindow(grid, rowIndex, "edit","",false);
                 }
	       }]

            
    },
       {	
	       header: 'Delete',
	       xtype:'actioncolumn', 
	       width:50,
	       align : 'center',
	       items: [{
	       icon: imagePath+'/icons/delete.png',  // Use a URL in the icon config
	       tooltip: 'Delete',
	       handler: function(grid, rowIndex, colIndex) {
                        globalDelete(grid, rowIndex, colIndex);
		}
	       }]               
    } ];
Ext.AccessControlListFields = [
        ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["XlsName"]
    ]

Ext.ArticleFields = [
        ["SeoUrl"],
         ["Description"],
         ["LastModifiedDate"],
         ["LastModifiedBy"],
         ["WorkflowName"],
         ["Title"],
         ["City"],
         ["EventDate"],
         ["Priority"],
         ["ShortDescription"],
         ["Status"],
         ["SourceAuthor"],
         ["CreatedDate"],
         ["Id"]
    ]

Ext.CategoryFields = [
        ["CategoryName"],
         ["ParentCategoryName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.ChannelFields = [
        ["CreatedDate"],
         ["Id"]
    ]

Ext.CityFields = [
        ["CityName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.ClassAttributesFields = [
        ["Type"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.ClassImportFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["ImportName"]
    ]

Ext.ClassInfoFields = [
        ["TreeEntity"],
         ["UI"],
         ["LastModifiedDate"],
         ["EntityGroup"],
         ["LastModifiedBy"],
         ["UIForm"],
         ["PackageName"],
         ["MasterData"],
         ["CreatedDate"],
         ["Id"],
         ["DownloadOption"],
         ["EditOption"],
         ["XmlName"],
         ["ClassName"]
    ]

Ext.ClassPackageFields = [
        ["ClassPackageName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.ContentPartnerFields = [
        ["Source"],
         ["Description"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.CountryFields = [
        ["CountryName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.CssFields = [
        ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["CssName"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["WorkflowName"],
         ["Weightage"]
    ]

Ext.DesignationFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["DesignationName"]
    ]

Ext.DomainFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.DomainEntityFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["EntityName"],
         ["Id"],
         ["LastModifiedBy"],
         ["EntityGroup"]
    ]

Ext.UserProfileFields = [
        ["State"],
         ["Email"],
         ["StateId"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["UserName"],
         ["Id"],
         ["LastModifiedBy"],
         ["City"],
         ["Title"]
    ]

Ext.ActionFields = [
        ["ActionName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.FeedbackFields = [
        ["Name"],
         ["Email"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.FormatFields = [
        ["FormatName"],
         ["MimeType"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.GalleryFields = [
        ["SeoUrl"],
         ["Status"],
         ["Description"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"],
         ["EventDate"],
         ["City"],
         ["Title"],
         ["Priority"]
    ]

Ext.InformationArchitectureFields = [
        ["ParentIAName"],
         ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["WorkflowName"],
         ["IaName"],
         ["EventDate"],
         ["Weightage"]
    ]

Ext.InterfaceInfoFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["InterfaceName"]
    ]

Ext.JsFields = [
        ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["JsName"],
         ["WorkflowName"],
         ["Weightage"]
    ]

Ext.LanguageFields = [
        ["LanguageCode"],
         ["LanguageName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.MediaGroupFields = [
        ["MediaGroupName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.MetadataInfoFields = [
        ["EventDate"]
    ]

Ext.PageFields = [
        ["Status"],
         ["PageName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"]
    ]

Ext.PageAssociatorFields = [
        ["Status"],
         ["PageName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"]
    ]

Ext.PmsMediaFields = [
        ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["PmsMediaName"],
         ["WorkflowName"],
         ["Weightage"]
    ]

Ext.RoleFields = [
        ["RoleName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["ParentRoleName"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.SourceFields = [
        ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.StageFields = [
        ["StageName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.StatusFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["StatusName"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.TemplateFields = [
        ["Status"],
         ["TemplateName"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["WorkflowName"]
    ]

Ext.CmsUserProfileFields = [
        ["ContactNumber"],
         ["LastModifiedDate"],
         ["UserName"],
         ["LastModifiedBy"],
         ["Title"],
         ["City"],
         ["Email"],
         ["State"],
         ["StateId"],
         ["CreatedDate"],
         ["Id"],
         ["AssociatedMin"],
         ["Designation"]
    ]

Ext.ValidationsFields = [
        ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["XlsName"]
    ]

Ext.ViewerFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["ViewerName"]
    ]

Ext.WidgetFields = [
        ["WidgetName"],
         ["Status"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"],
         ["Weightage"],
         ["WidgetType"]
    ]

Ext.WorkflowFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"]
    ]

Ext.ImageFields = [
        ["SeoUrl"],
         ["Status"],
         ["ImageName"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["WorkflowName"],
         ["EventDate"],
         ["Title"]
    ]

Ext.VideoFields = [
        ["SeoUrl"],
         ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["WorkflowName"],
         ["VideoName"],
         ["EventDate"],
         ["Title"]
    ]

Ext.ThumbnailFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["FilePath"]
    ]

Ext.EmbedCodeFields = [
        ["Status"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"]
    ]


var masterEntities = ['Category','Channel','City','ClassAttributes','ClassImport','ClassPackage','Country','Designation','DomainEntity','Action','Format','InformationArchitecture','InterfaceInfo','Language','MediaGroup','Page','PageAssociator','Role','Source','Stage','Status','Template','Viewer']
Ext.WorkflowEntities = [['any'],['Article'],['Css'],['Gallery'],['InformationArchitecture'],['Js'],['Page'],['PageAssociator'],['PmsMedia'],['Template'],['Widget'],['Image'],['Video'],['EmbedCode']]
var metaDataInfoList = ['Article','Gallery','InformationArchitecture','Image','Video']