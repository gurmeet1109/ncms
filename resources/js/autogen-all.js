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
          fieldLabel : "Xls Name *",
                                    listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>XlsName should end with valid file extension</div>");
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
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
     name :"Site",
           id:"AccessControlListSite",
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
           id:"AccessControlListVersion",
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
                           
} ]}];


var AddressForm = [
{
 title:'Address',
 xtype:'form',
 id:'AddressForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"AddressId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Address Id",
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
           id:"AddressEntityType",
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
                    value:"Address",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"AddressAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"AddressName",
           id:"AddressAddressName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Address Name *",
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
     name :"Address",
           id:"AddressAddress",
                    xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Address *",
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
     name :"Village",
           id:"AddressVillage",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Village",
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
     name :"Taluk",
           id:"AddressTaluk",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Taluk",
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
     name :"CityId",
           id:"AddressCityId",
               readOnly:true,
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
           id:"AddressCity",
               editable:false,
                 fieldLabel : "City *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('City', '', 'CityName'),
                                queryMode:"remote",
     displayField:"CityName",
     valueField:"CityName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('AddressIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('AddressIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('AddressCityId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('CityName','ASC');
              }
	   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"StateId",
           id:"AddressStateId",
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
           id:"AddressState",
               editable:false,
                 fieldLabel : "State  *",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('AddressIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('AddressIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('AddressStateId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   					,   
			focus: function(){
			  this.setValue('');
			  			  var sname = Ext.getCmp('AddressState').getValue();
				this.store.currentPage = 1; //reset page
				if(!Ext.isEmpty(sname) && sname != 'All India'){
					this.store.load({params:{"ParentGoiDirItemName":'"'+ sname + '"'}});
				}		
				else{
					this.store.load();
				}
			}
			   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"DistrictId",
           id:"AddressDistrictId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "District Id",
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
     name :"District",
           id:"AddressDistrict",
               editable:false,
                 fieldLabel : "District",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('AddressIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('AddressIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('AddressDistrictId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   					,   
			focus: function(){
			  this.setValue('');
			  			  var sname = Ext.getCmp('AddressState').getValue();
				this.store.currentPage = 1; //reset page
				if(!Ext.isEmpty(sname) && sname != 'All India'){
					this.store.load({params:{"ParentGoiDirItemName":'"'+ sname + '"'}});
				}		
				else{
					this.store.load();
				}
			}
			   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"Pincode",
           id:"AddressPincode",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Pincode",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"pincode",
                                          allowBlank :true
                           
},

               	                    {
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"AddressStatus",
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
           id:"AddressCreatedDate",
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
           id:"AddressLastModifiedDate",
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
           id:"AddressCreatedBy",
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
           id:"AddressLastModifiedBy",
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
     name :"Site",
           id:"AddressSite",
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
           id:"AddressVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"AddressWeightage",
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


var AppAdminForm = [
{
 title:'AppAdmin',
 xtype:'form',
 id:'AppAdminForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"AppAdminId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "AppAdmin Id",
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
           id:"AppAdminEntityType",
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
                    value:"AppAdmin",
                                                                   allowBlank :true
                           
},

                     {
     name :"Name",
           id:"AppAdminName",
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
                                                                                                   vtype:"alphanumMid",
                                          allowBlank : false
                           
},

                     {
     name :"Address",
           id:"AppAdminAddress",
                    xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Address",
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
     name :"Email",
           id:"AppAdminEmail",
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
     name :"Requirement",
           id:"AppAdminRequirement",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Requirement",
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
     name :"AppDate",
           id:"AppAdminAppDate",
               editable:false,
               fieldLabel : "Request Created Date",
     xtype : "datetimefield",
	                hidden:true,
                                                     allowBlank:true
                   
},

                     {
     name :"MobileNo",
           id:"AppAdminMobileNo",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Mobile No",
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
     name :"CreatedBy",
           id:"AppAdminCreatedBy",
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
           id:"AppAdminLastModifiedBy",
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
     name :"Version",
           id:"AppAdminVersion",
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
                           
},

                     {
     name :"Site",
           id:"AppAdminSite",
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
     name :"Weightage",
           id:"AppAdminWeightage",
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
     name :"AliasId",
           id:"ArticleAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"Title",
           id:"ArticleTitle",
                              maxLength :200,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title *",
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
          fieldLabel : "Description *",  
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
     name :"ArticleType",
           id:"ArticleArticleType",
               editable:false,
                 fieldLabel : "ArticleType *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('ArticleType', '', 'ArticleTypeName'),
                                queryMode:"remote",
     displayField:"ArticleTypeName",
     valueField:"ArticleTypeName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"AssociatedIAPath",
           id:"ArticleAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"Md5",
           id:"ArticleMd5",
               readOnly:true,
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
     name :"SeoUrl",
           id:"ArticleSeoUrl",
               readOnly:true,
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"ArticleStatus",
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
     name :"Site",
           id:"ArticleSite",
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
           id:"ArticleVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"ArticleWeightage",
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
	title:'NPIMetadata',
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
        items:[RelatedItemPanelForm ]}];


var ArticleTypeForm = [
{
 title:'ArticleType',
 xtype:'form',
 id:'ArticleTypeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ArticleTypeId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ArticleType Id",
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
           id:"ArticleTypeEntityType",
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
                    value:"ArticleType",
                                                                   allowBlank :true
                           
},

                     {
     name :"ArticleTypeName",
           id:"ArticleTypeArticleTypeName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ArticleType Name *",
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
           id:"ArticleTypeCreatedDate",
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
           id:"ArticleTypeLastModifiedDate",
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
           id:"ArticleTypeCreatedBy",
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
           id:"ArticleTypeLastModifiedBy",
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
     name :"Site",
           id:"ArticleTypeSite",
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
           id:"ArticleTypeVersion",
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
                           
} ]}];


var AudienceCategoryForm = [
{
 title:'AudienceCategory',
 xtype:'form',
 id:'AudienceCategoryForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"AudienceCategoryId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "AudienceCategory Id",
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
           id:"AudienceCategoryEntityType",
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
                    value:"AudienceCategory",
                                                                   allowBlank :true
                           
},

                     {
     name :"AudienceCategoryName",
           id:"AudienceCategoryAudienceCategoryName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Audience Category Name *",
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
     name :"ParentAudienceCategoryId",
           id:"AudienceCategoryParentAudienceCategoryId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Parent Audience Id",
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
     name :"ParentAudienceCategoryName",
           id:"AudienceCategoryParentAudienceCategoryName",
               readOnly:true,
               xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Parent Audience Name",
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
                                                     showTreePanelWindow(this, 'AudienceCategory', 'ParentAudienceCategoryId' ,'parentchild');
                                               }
                 	},
                                                                                       allowBlank :true
                           
},

                     {
     name :"CreatedDate",
           id:"AudienceCategoryCreatedDate",
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
           id:"AudienceCategoryLastModifiedDate",
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
           id:"AudienceCategoryCreatedBy",
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
           id:"AudienceCategoryLastModifiedBy",
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
     name :"Site",
           id:"AudienceCategorySite",
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
           id:"AudienceCategoryVersion",
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
                           
} ]}];


var AwardsForm = [
{
 title:'Awards',
 xtype:'form',
 id:'AwardsForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"AwardsId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Awards Id",
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
           id:"AwardsEntityType",
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
                    value:"Awards",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"AwardsAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"Prefix",
           id:"AwardsPrefix",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Prefix",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"prefix",
                                          allowBlank :true
                           
},

                     {
     name :"Age",
           id:"AwardsAge",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Age",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"age",
                                          allowBlank :true
                           
},

                     {
     name :"AwardName",
           id:"AwardsAwardName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Award Name *",
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
     name :"AwardYear",
           id:"AwardsAwardYear",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Award Year *",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"year",
                                          allowBlank : false
                           
},

                     {
     name :"AwardField",
           id:"AwardsAwardField",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Award Field *",
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
     name :"FirstName",
           id:"AwardsFirstName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "First Name *",
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
     name :"LastName",
           id:"AwardsLastName",
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
     name :"StateId",
           id:"AwardsStateId",
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
           id:"AwardsState",
               editable:false,
                 fieldLabel : "State",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('AwardsIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('AwardsIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('AwardsStateId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   			   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"CountryId",
           id:"AwardsCountryId",
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
     name :"Country",
           id:"AwardsCountry",
               editable:false,
                 fieldLabel : "Country",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Country', '', 'CountryName'),
                                queryMode:"remote",
     displayField:"CountryName",
     valueField:"CountryName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('AwardsIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('AwardsIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('AwardsCountryId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('CountryName','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

               	                    {
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"AwardsStatus",
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
           id:"AwardsCreatedDate",
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
           id:"AwardsLastModifiedDate",
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
           id:"AwardsCreatedBy",
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
           id:"AwardsLastModifiedBy",
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
     name :"Site",
           id:"AwardsSite",
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
           id:"AwardsVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"AwardsWeightage",
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


var BannerForm = [
{
 title:'Banner',
 xtype:'form',
 id:'BannerForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"BannerId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Banner Id",
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
           id:"BannerEntityType",
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
                    value:"Banner",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"BannerAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"BannerTitle",
           id:"BannerBannerTitle",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Banner Title *",
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
     name :"ShortDescription",
           id:"BannerShortDescription",
                    xtype : "textarea",
               maxLength :500,
          fieldLabel : "Short Description *",
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
     name :"AssociatedIAPath",
           id:"BannerAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"SourceName",
           id:"BannerSourceName",
               readOnly:true,
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
           id:"BannerFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
           }
           }
       }
        },
                                                                                                             allowBlank:false
                   
},

                     {
     name :"Width",
           id:"BannerWidth",
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
                                                                                                                                   allowBlank :true
                           
},

                     {
     name :"Height",
           id:"BannerHeight",
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
                                                                                                                                   allowBlank :true
                           
},

               	                    {
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"BannerStatus",
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
           id:"BannerCreatedDate",
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
           id:"BannerLastModifiedDate",
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
           id:"BannerCreatedBy",
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
           id:"BannerLastModifiedBy",
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
     name :"Md5",
           id:"BannerMd5",
               readOnly:true,
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
     name :"Site",
           id:"BannerSite",
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
           id:"BannerVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"BannerWeightage",
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
	title:'NPIMetadata',
	xtype:'form',
	id:'BannerForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];


var BookForm = [
{
 title:'Book',
 xtype:'form',
 id:'BookForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"BookId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Book Id",
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
           id:"BookEntityType",
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
                    value:"Book",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"BookAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"Title",
           id:"BookTitle",
                              maxLength :200,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title *",
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
     name :"Author",
           id:"BookAuthor",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Author *",
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
     name :"SourceName",
           id:"BookSourceName",
               readOnly:true,
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
           id:"BookFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
           }
           }
       }
        },
                                                                                                             allowBlank:true
                   
},

                     {
     name :"Description",
           id:"BookDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description *",  
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
     name :"TableofContents",
           id:"BookTableofContents",
                    xtype : "textarea",
               maxLength :4000,
          fieldLabel : "Table Of contents *",
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
     name :"ContentLanguageId",
           id:"BookContentLanguageId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Content Language Id",
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
     name :"ContentLanguage",
           id:"BookContentLanguage",
               editable:false,
                 fieldLabel : "Content Language *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Language', '', 'LanguageCode'),
                                queryMode:"remote",
     displayField:"LanguageCode",
     valueField:"LanguageCode",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('BookIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('BookIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('BookContentLanguageId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('LanguageCode','ASC');
              }
	   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"ISBN",
           id:"BookISBN",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ISBN *",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"isbn",
                                          allowBlank : false
                           
},

                     {
     name :"Publication",
           id:"BookPublication",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Publication *",
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
     name :"CurrencyId",
           id:"BookCurrencyId",
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
     name :"CurrencyName",
           id:"BookCurrencyName",
               editable:false,
                 fieldLabel : "Currency Name",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Currency', '', 'CurrencyName'),
                                queryMode:"remote",
     displayField:"CurrencyName",
     valueField:"CurrencyName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('BookIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('BookIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('BookCurrencyId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('CurrencyName','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"Price",
           id:"BookPrice",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Price *",
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"BookStatus",
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
           id:"BookCreatedDate",
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
           id:"BookLastModifiedDate",
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
           id:"BookCreatedBy",
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
           id:"BookLastModifiedBy",
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
     name :"Md5",
           id:"BookMd5",
               readOnly:true,
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
     name :"Site",
           id:"BookSite",
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
           id:"BookVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"BookWeightage",
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
	title:'NPIMetadata',
	xtype:'form',
	id:'BookForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];


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
          fieldLabel : "Category Name *",
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
     name :"Site",
           id:"CategorySite",
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
           id:"CategoryVersion",
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
          fieldLabel : "City Name *",
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
     name :"Site",
           id:"CitySite",
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
           id:"CityVersion",
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
          fieldLabel : "Field Type *",
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
          fieldLabel : "XML Name *",
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
                      fieldLabel : "CData *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["cdata"],
                        data : Ext.cdata // from data.js
                    }),
     queryMode:"local",
     displayField:"cdata",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"DefaultSearch",
           id:"ClassAttributesDefaultSearch",
                      fieldLabel : "DefaultSearch *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["defaultSearch"],
                        data : Ext.defaultSearch // from data.js
                    }),
     queryMode:"local",
     displayField:"defaultSearch",
          emptyText:"",

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
     name :"Site",
           id:"ClassAttributesSite",
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
           id:"ClassAttributesVersion",
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
          fieldLabel : "Import Name *",
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
     name :"Site",
           id:"ClassImportSite",
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
           id:"ClassImportVersion",
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
          fieldLabel : "Class Name *",
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
                      fieldLabel : "MasterData *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["masterData"],
                        data : Ext.masterData // from data.js
                    }),
     queryMode:"local",
     displayField:"masterData",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"TreeEntity",
           id:"ClassInfoTreeEntity",
                      fieldLabel : "TreeEntity *",
                    xtype:"combobox",
                           queryMode:"remote",
     displayField:"$fields.annotations.UI.queryField",
     valueField:"$fields.annotations.UI.queryField",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"UI",
           id:"ClassInfoUI",
                      fieldLabel : "UI *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["uI"],
                        data : Ext.uI // from data.js
                    }),
     queryMode:"local",
     displayField:"uI",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"UIForm",
           id:"ClassInfoUIForm",
                      fieldLabel : "UI Form*",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["uIForm"],
                        data : Ext.uIForm // from data.js
                    }),
     queryMode:"local",
     displayField:"uIForm",
          emptyText:"",

                                                          allowBlank:false
                   
},

               	                    {
     name :"ClassField",
           id:"ClassFieldsClassField",
                      fieldLabel : "FieldName *",
                    xtype:"combobox",
               multiSelect:true,
                                                 store:createStore('ClassAttributes', '', 'FieldName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

     	                         	                    {
     name :"EntityGroup",
           id:"EntityGroupsEntityGroup",
               editable:false,
                 fieldLabel : "Entity Group",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
               store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["entityGroup"],
                        data : Ext.entityGroup // from data.js
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
     name :"EditOption",
           id:"ClassInfoEditOption",
                      fieldLabel : "Edit Option *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["editOption"],
                        data : Ext.editOption // from data.js
                    }),
     queryMode:"local",
     displayField:"editOption",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"DownloadOption",
           id:"ClassInfoDownloadOption",
                      fieldLabel : "Download Option *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["downloadOption"],
                        data : Ext.downloadOption // from data.js
                    }),
     queryMode:"local",
     displayField:"downloadOption",
          emptyText:"",

                                                          allowBlank:false
                   
},

               	                    {
     name :"ClassImplement",
           id:"ClassImplementsClassImplement",
                      fieldLabel : "Implements",
                    xtype:"combobox",
               multiSelect:true,
                                                 store:createStore('InterfaceInfo', '', 'InterfaceName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:true
                   
},

     	                               {
     name :"XmlName",
           id:"ClassInfoXmlName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "XML Name *",
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
                      fieldLabel : "Package Name *",
                    xtype:"combobox",
               multiSelect:true,
                                                 store:createStore('ClassPackage', '', 'ClassPackageName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

               	                    {
     name :"ClassImport",
           id:"ClassImportsClassImport",
                      fieldLabel : "Import *",
                    xtype:"combobox",
               multiSelect:true,
                                                 store:createStore('ClassImport', '', 'ImportName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

     	                               {
     name :"Site",
           id:"ClassInfoSite",
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
           id:"ClassInfoVersion",
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
          fieldLabel : "Package Name *",
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
     name :"Site",
           id:"ClassPackageSite",
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
           id:"ClassPackageVersion",
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
                           
} ]}];


var ContactNumberForm = [
{
 title:'ContactNumber',
 xtype:'form',
 id:'ContactNumberForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ContactNumberId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ContactNumber Id",
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
           id:"ContactNumberEntityType",
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
                    value:"ContactNumber",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"ContactNumberAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"ContactType",
           id:"ContactNumberContactType",
               editable:false,
                 fieldLabel : "Contact Type *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('ContactType', '', 'ContactTypeName'),
                                queryMode:"remote",
     displayField:"ContactTypeName",
     valueField:"ContactTypeName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"CountryCode",
           id:"ContactNumberCountryCode",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Country Code *",
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
     name :"CityCode",
           id:"ContactNumberCityCode",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "City Code *",
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
     name :"OfficePhone",
           id:"ContactNumberOfficePhone",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Office Phone *",
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
     name :"Extension",
           id:"ContactNumberExtension",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Extension",
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
     name :"BoardNo",
           id:"ContactNumberBoardNo",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Board No",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"alphanumeric",
                                          allowBlank :true
                           
},

                     {
     name :"TollfreeNo",
           id:"ContactNumberTollfreeNo",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Tollfree No",
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
     name :"MobileNo",
           id:"ContactNumberMobileNo",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Mobile No *",
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
     name :"Fax",
           id:"ContactNumberFax",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Fax",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"alphanumeric",
                                          allowBlank :true
                           
},

                     {
     name :"Email",
           id:"ContactNumberEmail",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Email *",
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"ContactNumberStatus",
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
           id:"ContactNumberCreatedDate",
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
           id:"ContactNumberLastModifiedDate",
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
           id:"ContactNumberCreatedBy",
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
           id:"ContactNumberLastModifiedBy",
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
     name :"Site",
           id:"ContactNumberSite",
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
     name :"Weightage",
           id:"ContactNumberWeightage",
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
     name :"Version",
           id:"ContactNumberVersion",
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
                           
} ]}];


var ContactTypeForm = [
{
 title:'ContactType',
 xtype:'form',
 id:'ContactTypeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ContactTypeId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ContactType Id",
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
           id:"ContactTypeEntityType",
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
                    value:"ContactType",
                                                                   allowBlank :true
                           
},

                     {
     name :"ContactTypeName",
           id:"ContactTypeContactTypeName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Contact Type Name *",
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
           id:"ContactTypeCreatedDate",
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
           id:"ContactTypeLastModifiedDate",
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
           id:"ContactTypeCreatedBy",
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
           id:"ContactTypeLastModifiedBy",
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
     name :"Site",
           id:"ContactTypeSite",
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
           id:"ContactTypeVersion",
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
     name :"AliasId",
           id:"ContentPartnerAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
           id:"ContentPartnerDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description *",  
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
                      fieldLabel : "RssAggregated *",
                    xtype:"combobox",
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["rssAggregated"],
                        data : Ext.rssAggregated // from data.js
                    }),
     queryMode:"local",
     displayField:"rssAggregated",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"Source",
           id:"ContentPartnerSource",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Source *",
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
               editable:false,
                 fieldLabel : "SourceType *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('SourceType', '', 'SourceTypeName'),
                                queryMode:"remote",
     displayField:"SourceTypeName",
     valueField:"SourceTypeName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"SupplementName",
           id:"ContentPartnerSupplementName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Supplement Name *",
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
          fieldLabel : "Url *",
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
          fieldLabel : "No Of Days to Maintain Feed *",
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
     name :"Site",
           id:"ContentPartnerSite",
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
           id:"ContentPartnerVersion",
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
          fieldLabel : "Country Name *",
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
          fieldLabel : "Country Code *",
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
     name :"Site",
           id:"CountrySite",
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
           id:"CountryVersion",
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
          fieldLabel : "Css Name *",
                                    listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.css)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>CssName should end with valid file extension</div>");
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
	     	},
                                                                                                   vtype:"folderpath",
                                          allowBlank :true
                           
},

                     {
     name :"FilePath",
           id:"CssFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.css)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"Site",
           id:"CssSite",
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
           id:"CssVersion",
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
     name :"AliasId",
           id:"DesignationAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"DesignationName",
           id:"DesignationDesignationName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Designation Name *",
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
     name :"Site",
           id:"DesignationSite",
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
           id:"DesignationVersion",
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
          fieldLabel : "Domain Name *",
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
     name :"Site",
           id:"DomainSite",
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
     name :"IsActive",
           id:"DomainIsActive",
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
     name :"DomainEntity",
           id:"DomainEntitiesDomainEntity",
               editable:false,
                 fieldLabel : "Domain Entity *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('DomainEntity', '', 'EntityName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

     	                         	                    {
     name :"UiTab",
           id:"UiTabsUiTab",
               editable:false,
                 fieldLabel : "UITab *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
               store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["uiTab"],
                        data : Ext.uiTab // from data.js
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
        },
                                                     allowBlank:false
                   
},

     	                         	                    {
     name :"roleName",
           id:"RolesroleName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Role Name *",
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
     name :"Version",
           id:"DomainVersion",
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
                           
},

                     {
     name :"SuperAdminRole",
           id:"DomainSuperAdminRole",
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
                           
},

                     {
     name :"SuperAdminUserName",
           id:"DomainSuperAdminUserName",
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
                           
},

                     {
     name :"SuperAdminPassword",
           id:"DomainSuperAdminPassword",
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
                           
},

               	                    {
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
          fieldLabel : "Entity Name *",
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
               editable:false,
                 fieldLabel : "Entity Group",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
               store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["entityGroup"],
                        data : Ext.entityGroup // from data.js
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
     name :"Site",
           id:"DomainEntitySite",
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
           id:"DomainEntityVersion",
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
                           
},

                     {
     name :"IsMasterData",
           id:"DomainEntityIsMasterData",
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
                           
},

                     {
     name :"IsTreeEntity",
           id:"DomainEntityIsTreeEntity",
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
                           
},

                     {
     name :"IsUIEntity",
           id:"DomainEntityIsUIEntity",
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
                           
},

                     {
     name :"IsAutoGenUIForm",
           id:"DomainEntityIsAutoGenUIForm",
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
                           
},

               	                    {
     name :"EntityGroup",
           id:"EntityGroupsEntityGroup",
               editable:false,
                 fieldLabel : "Entity Group",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
               store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["entityGroup"],
                        data : Ext.entityGroup // from data.js
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
        },
                                                     allowBlank:false
                   
},

     	                         	              	                    {
     name :"ValidationKey",
           id:"ValidationConstraintValidationKey",
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
                           
},

                     {
     name :"ValidationValue",
           id:"ValidationConstraintValidationValue",
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
                           
},

     	               	                         	              	                    {
     name :"FieldName",
           id:"FieldFieldName",
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
                           
},

                     {
     name :"FieldType",
           id:"FieldFieldType",
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
                           
},

                     {
     name :"GenericType",
           id:"FieldGenericType",
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
                           
},

                     {
     name :"IsCDATAField",
           id:"FieldIsCDATAField",
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
                           
},

                     {
     name :"IsAdvanceSearchField",
           id:"FieldIsAdvanceSearchField",
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
                           
},

               	              	                    {
     name :"ValidationKey",
           id:"ValidationConstraintValidationKey",
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
                           
},

                     {
     name :"ValidationValue",
           id:"ValidationConstraintValidationValue",
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
                           
},

     	               	                         	              	                    {
     name :"Key",
           id:"FormPropertyKey",
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
                           
},

                     {
     name :"Value",
           id:"FormPropertyValue",
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
     name :"AliasId",
           id:"UserProfileAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
           id:"UserProfileUserName",
                              maxLength :15,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "User Name *",
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
     fieldLabel : "Password *",
     	listeners:{
        render:function(){
           this.setValue(''); 
        }
	},
                                                               allowBlank:false
                   
},

                     {
     name :"UserRole",
           id:"UserProfileUserRole",
               readOnly:true,
                         maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "User Role *",
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
          fieldLabel : "Title *",
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
          fieldLabel : "First Name *",
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
          fieldLabel : "Email *",
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
               editable:false,
                 fieldLabel : "City",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('CityName','ASC');
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
               editable:false,
                 fieldLabel : "State",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
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
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
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
               editable:false,
                 fieldLabel : "Gender *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["gender"],
                        data : Ext.gender // from data.js
                    }),
     queryMode:"local",
     displayField:"gender",
          emptyText:"",

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
               editable:false,
                 fieldLabel : "Age Category *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["ageCategory"],
                        data : Ext.ageCategory // from data.js
                    }),
     queryMode:"local",
     displayField:"ageCategory",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"IsActive",
           id:"UserProfileIsActive",
               editable:false,
                 fieldLabel : "IsActive *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["isActive"],
                        data : Ext.isActive // from data.js
                    }),
     queryMode:"local",
     displayField:"isActive",
          emptyText:"",

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
     name :"Site",
           id:"UserProfileSite",
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
           id:"UserProfileVersion",
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
          fieldLabel : "Action Name *",
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
     name :"Site",
           id:"ActionSite",
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
           id:"ActionVersion",
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
     name :"AliasId",
           id:"FeedbackAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
               editable:false,
                 fieldLabel : "Rateon Aspect",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["rateonAspect"],
                        data : Ext.rateonAspect // from data.js
                    }),
     queryMode:"local",
     displayField:"rateonAspect",
          emptyText:"",

                                                  vtype:"numeric",
                  allowBlank:false
                   
},

                     {
     name :"RateUsefullness",
           id:"FeedbackRateUsefullness",
               editable:false,
                 fieldLabel : "Rate Usefullness",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["rateUsefullness"],
                        data : Ext.rateUsefullness // from data.js
                    }),
     queryMode:"local",
     displayField:"rateUsefullness",
          emptyText:"",

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
     name :"Site",
           id:"FeedbackSite",
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
           id:"FeedbackVersion",
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
                           
} ]}];


var FormForm = [
{
 title:'Form',
 xtype:'form',
 id:'FormForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"FormId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Form Id",
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
           id:"FormEntityType",
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
                    value:"Form",
                                                                   allowBlank :true
                           
},

                     {
     name :"Title",
           id:"FormTitle",
                              maxLength :200,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title *",
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
     name :"AliasId",
           id:"FormAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"SourceName",
           id:"FormSourceName",
               readOnly:true,
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
           id:"FormFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.png|.gif|.pdf)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
           }
           }
       }
        },
                                                                                                             allowBlank:true
                   
},

                     {
     name :"UrlOfResource",
           id:"FormUrlOfResource",
                              maxLength :250,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Url of Resource",
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
     name :"ShortDescription",
           id:"FormShortDescription",
                    xtype : "htmleditor",
               maxLength :500,
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
     name :"AssociatedIAPath",
           id:"FormAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"SeoUrl",
           id:"FormSeoUrl",
               readOnly:true,
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"FormStatus",
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
           id:"FormCreatedDate",
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
           id:"FormLastModifiedDate",
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
           id:"FormCreatedBy",
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
           id:"FormLastModifiedBy",
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
     name :"Md5",
           id:"FormMd5",
               readOnly:true,
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
     name :"Site",
           id:"FormSite",
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
           id:"FormVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"FormWeightage",
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
	title:'NPIMetadata',
	xtype:'form',
	id:'FormForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];


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
          fieldLabel : "Format Name *",
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
          fieldLabel : "File Extension *",
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
               editable:false,
                 fieldLabel : "Mime Type  *",
                    xtype:"combobox",
          editable:false,
                           queryMode:"remote",
     displayField:"$fields.annotations.UI.queryField",
     valueField:"$fields.annotations.UI.queryField",
          emptyText:"",

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
     name :"IsGeneratable",
           id:"FormatIsGeneratable",
               editable:false,
                 fieldLabel : "IsGeneratable *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["isGeneratable"],
                        data : Ext.isGeneratable // from data.js
                    }),
     queryMode:"local",
     displayField:"isGeneratable",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"MediaGroup",
           id:"FormatMediaGroup",
               editable:false,
                 fieldLabel : "Media Group *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('MediaGroup', '', 'MediaGroupName'),
                                queryMode:"remote",
     displayField:"MediaGroupName",
     valueField:"MediaGroupName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"Site",
           id:"FormatSite",
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
           id:"FormatVersion",
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
     name :"AliasId",
           id:"GalleryAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"Title",
           id:"GalleryTitle",
                              maxLength :200,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title *",
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
     name :"Url",
           id:"GalleryUrl",
                              maxLength :150,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Url *",
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
     name :"Description",
           id:"GalleryDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description *",  
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
           id:"GalleryAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
               editable:false,
                 fieldLabel : "Content Format  *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Format', '', 'FormatName'),
                                queryMode:"remote",
     displayField:"FormatName",
     valueField:"FormatName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"Viewer",
           id:"GalleryViewer",
               editable:false,
                 fieldLabel : "Viewer  *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Viewer', '', 'ViewerName'),
                                queryMode:"remote",
     displayField:"ViewerName",
     valueField:"ViewerName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"FileSize",
           id:"GalleryFileSize",
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
                                                                                                   vtype:"alphanumMid",
                                          allowBlank :true
                           
},

                     {
     name :"Rating",
           id:"GalleryRating",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Rating *",
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
     name :"Category",
           id:"CategoriesCategory",
               editable:false,
                 fieldLabel : "Category *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('Category', '', 'CategoryName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
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
     name :"SeoUrl",
           id:"GallerySeoUrl",
               readOnly:true,
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"GalleryStatus",
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
     name :"Site",
           id:"GallerySite",
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
           id:"GalleryVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"GalleryWeightage",
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
	title:'NPIMetadata',
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
        items:[RelatedItemPanelForm ]}];


var GoiDirCatForm = [
{
 title:'GoiDirCat',
 xtype:'form',
 id:'GoiDirCatForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"GoiDirCatId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "GoiDirCat Id",
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
           id:"GoiDirCatEntityType",
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
                    value:"GoiDirCat",
                                                                   allowBlank :true
                           
},

                     {
     name :"GoiDirCatName",
           id:"GoiDirCatGoiDirCatName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "GoiDirCat Name *",
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
                                                                                                   vtype:"alphanumMid",
                                          allowBlank : false
                           
},

                     {
     name :"GoiDirCatPath",
           id:"GoiDirCatGoiDirCatPath",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "GoiDirCat Path",
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
     name :"ParentGoiDirCatId",
           id:"GoiDirCatParentGoiDirCatId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Parent GoiDirCatId",
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
     name :"ParentGoiDirCatName",
           id:"GoiDirCatParentGoiDirCatName",
               readOnly:true,
               xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Parent GoiDirCat Name",
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
                                                     showTreePanelWindow(this, 'GoiDirCat', 'ParentGoiDirCatId' ,'parentchild');
                                               }
                 	},
                                                                                       allowBlank :true
                           
},

                     {
     name :"LeafOrNonLeaf",
           id:"GoiDirCatLeafOrNonLeaf",
               editable:false,
                 fieldLabel : "Leaf or Non Leaf *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["leafOrNonLeaf"],
                        data : Ext.leafOrNonLeaf // from data.js
                    }),
     queryMode:"local",
     displayField:"leafOrNonLeaf",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"IsTopLevel",
           id:"GoiDirCatIsTopLevel",
               readOnly:true,
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
     name :"CreatedDate",
           id:"GoiDirCatCreatedDate",
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
           id:"GoiDirCatLastModifiedDate",
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
           id:"GoiDirCatCreatedBy",
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
           id:"GoiDirCatLastModifiedBy",
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
     name :"Site",
           id:"GoiDirCatSite",
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
           id:"GoiDirCatVersion",
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
                           
} ]}];


var GoiDirItemForm = [
{
 title:'GoiDirItem',
 xtype:'form',
 id:'GoiDirItemForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"GoiDirItemId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "GoiDirItem Id",
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
           id:"GoiDirItemEntityType",
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
                    value:"GoiDirItem",
                                                                   allowBlank :true
                           
},

                     {
     name :"GoiDirId",
           id:"GoiDirItemGoiDirId",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Goi Directory Id *",
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
     name :"GoiDirItemName",
           id:"GoiDirItemGoiDirItemName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "GoiDirItem Name *",
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
     name :"ParentGoiDirItemId",
           id:"GoiDirItemParentGoiDirItemId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Parent GoiDirItem Id",
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
     name :"ParentGoiDirItemName",
           id:"GoiDirItemParentGoiDirItemName",
               readOnly:true,
               xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Parent GoiDirItem Name",
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
                                                     showTreePanelWindow(this, 'GoiDirItem', 'ParentGoiDirItemId' ,'parentchild');
                                               }
                 	},
                                                                                       allowBlank :true
                           
},

               	                    {
     name :"Category",
           id:"GoiDirCatInfoCategory",
               editable:false,
                 fieldLabel : "Goi Dir Category Name",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('GoiDirCat', '', 'GoiDirCatName'),
                                queryMode:"remote",
     displayField:"GoiDirCatName",
     valueField:"GoiDirCatName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('GoiDirCatInfoIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('GoiDirCatInfoIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('GoiDirCatInfoHPath');
             pId.setValue(this.store.getAt(i).get('GoiDirCatPath'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirCatName','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"HPath",
           id:"GoiDirCatInfoHPath",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "HPath",
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
     name :"BelongsTo",
           id:"GoiDirItemBelongsTo",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Belongs To *",
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
           id:"GoiDirItemUrl",
                              maxLength :150,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Url",
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
     name :"LeafOrNonLeaf",
           id:"GoiDirItemLeafOrNonLeaf",
               editable:false,
                 fieldLabel : "Leaf or Non Leaf  *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["leafOrNonLeaf"],
                        data : Ext.leafOrNonLeaf // from data.js
                    }),
     queryMode:"local",
     displayField:"leafOrNonLeaf",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"CreatedDate",
           id:"GoiDirItemCreatedDate",
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
           id:"GoiDirItemLastModifiedDate",
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
           id:"GoiDirItemCreatedBy",
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
           id:"GoiDirItemLastModifiedBy",
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
     name :"Site",
           id:"GoiDirItemSite",
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
           id:"GoiDirItemVersion",
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
     name :"AliasId",
           id:"InformationArchitectureAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"IaName",
           id:"InformationArchitectureIaName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "IA Name *",
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
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
     name :"KeywordToVocabulary",
           id:"KeywordToVocabulariesKeywordToVocabulary",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Keyword To Vocabulary *",
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
     name :"Md5",
           id:"InformationArchitectureMd5",
               readOnly:true,
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
          fieldLabel : "Page Level *",
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
               editable:false,
                 fieldLabel : "Visible *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["visible"],
                        data : Ext.visible // from data.js
                    }),
     queryMode:"local",
     displayField:"visible",
          emptyText:"",

                                                          allowBlank:false
                   
},

               	                    {
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"Site",
           id:"InformationArchitectureSite",
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
           id:"InformationArchitectureVersion",
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
	title:'NPIMetadata',
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
          fieldLabel : "Interface Name *",
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
               multiSelect:true,
                                                 store:createStore('ClassAttributes', '', 'FieldName'),
                                queryMode:"remote",
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
     name :"Site",
           id:"InterfaceInfoSite",
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
           id:"InterfaceInfoVersion",
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
          fieldLabel : "Js Name *",
                                    listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.js)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>JsName should end with valid file extension</div>");
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
	     	},
                                                                                                   vtype:"folderpath",
                                          allowBlank :true
                           
},

                     {
     name :"FilePath",
           id:"JsFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.js)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"Site",
           id:"JsSite",
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
           id:"JsVersion",
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


var JurisdictionTypeForm = [
{
 title:'JurisdictionType',
 xtype:'form',
 id:'JurisdictionTypeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"JurisdictionTypeId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "JurisdictionType Id",
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
           id:"JurisdictionTypeEntityType",
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
                    value:"JurisdictionType",
                                                                   allowBlank :true
                           
},

                     {
     name :"JurisdictionTypeName",
           id:"JurisdictionTypeJurisdictionTypeName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Jurisdiction Type Name *",
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
           id:"JurisdictionTypeCreatedDate",
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
           id:"JurisdictionTypeLastModifiedDate",
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
           id:"JurisdictionTypeCreatedBy",
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
           id:"JurisdictionTypeLastModifiedBy",
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
     name :"Site",
           id:"JurisdictionTypeSite",
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
           id:"JurisdictionTypeVersion",
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
          fieldLabel : "Language Name *",
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
          fieldLabel : "Language Code *",
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
     name :"Site",
           id:"LanguageSite",
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
           id:"LanguageVersion",
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
                           
} ]}];


var MediaForm = [
{
 title:'Media',
 xtype:'form',
 id:'MediaForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"MediaId",
               readOnly:true,
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
           id:"MediaEntityType",
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
                    value:"Media",
                                                                   allowBlank :true
                           
},

                     {
     name :"Title",
           id:"MediaTitle",
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
     name :"MediaName",
           id:"MediaMediaName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Media Name *",
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
           id:"MediaSourceName",
               readOnly:true,
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
           id:"MediaShortDescription",
                    xtype : "htmleditor",
               maxLength :500,
          fieldLabel : "Short Description *",  
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
           id:"MediaAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"FolderPath",
           id:"MediaFolderPath",
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
	     	},
                                                                                                   vtype:"folderpath",
                                          allowBlank :true
                           
},

                     {
     name :"FilePath",
           id:"MediaFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
           }
           }
       }
        },
                                                                                                             allowBlank:false
                   
},

                     {
     name :"ThumbnailPath",
           id:"MediaThumbnailPath",
               readOnly:true,
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
     name :"Size",
           id:"MediaSize",
               readOnly:true,
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
           id:"MediaMd5",
               readOnly:true,
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
           id:"MediaHeight",
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
                                                                                                                                   allowBlank :true
                           
},

                     {
     name :"Width",
           id:"MediaWidth",
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
                                                                                                                                   allowBlank :true
                           
},

                     {
     name :"Bitrate",
           id:"MediaBitrate",
               readOnly:true,
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
           id:"MediaPlayDuration",
               readOnly:true,
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
           id:"MediaMimeSimpleName",
               readOnly:true,
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
           id:"MediaMimeType",
               readOnly:true,
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
           id:"MediaContentFormat",
               editable:false,
                 fieldLabel : "Content Format  *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Format', '', 'FormatName'),
                                queryMode:"remote",
     displayField:"FormatName",
     valueField:"FormatName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"ContentLanguageId",
           id:"MediaContentLanguageId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Content Language Id",
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
     name :"ContentLanguage",
           id:"MediaContentLanguage",
               editable:false,
                 fieldLabel : "Content Language *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Language', '', 'LanguageCode'),
                                queryMode:"remote",
     displayField:"LanguageCode",
     valueField:"LanguageCode",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('MediaIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('MediaIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('MediaContentLanguageId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('LanguageCode','ASC');
              }
	   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"SeoUrl",
           id:"MediaSeoUrl",
               readOnly:true,
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"MediaStatus",
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
           id:"MediaCreatedDate",
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
           id:"MediaLastModifiedDate",
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
           id:"MediaCreatedBy",
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
           id:"MediaLastModifiedBy",
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
     name :"Site",
           id:"MediaSite",
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
           id:"MediaVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"MediaWeightage",
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
	title:'NPIMetadata',
	xtype:'form',
	id:'MediaForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];


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
          fieldLabel : "Media Group Name *",
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
               editable:false,
                 fieldLabel : "IsGeneratable *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["isGeneratable"],
                        data : Ext.isGeneratable // from data.js
                    }),
     queryMode:"local",
     displayField:"isGeneratable",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"Site",
           id:"MediaGroupSite",
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
           id:"MediaGroupVersion",
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
                           
} ]}];


var NewsForm = [
{
 title:'News',
 xtype:'form',
 id:'NewsForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"NewsId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "News Id",
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
           id:"NewsEntityType",
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
                    value:"News",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"NewsAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"Category",
           id:"CategoriesCategory",
               editable:false,
                 fieldLabel : "Category *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('Category', '', 'CategoryName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

     	                               {
     name :"Title",
           id:"NewsTitle",
                              maxLength :200,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title *",
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
           id:"NewsDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description *",  
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
     name :"AtomId",
           id:"NewsAtomId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Atom Id",
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
     name :"Summary",
           id:"NewsSummary",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Summary",
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
     name :"Url",
           id:"NewsUrl",
                              maxLength :250,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "News Url *",
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
     name :"ImageUrl",
           id:"ImageImageUrl",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Image Url",
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
     name :"Width",
           id:"DimensionWidth",
               readOnly:true,
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
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

                     {
     name :"Height",
           id:"DimensionHeight",
               readOnly:true,
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
                                                                hidden:true,
                                                                             allowBlank :true
                           
},

     	               	                               {
     name :"PubDate",
           id:"NewsPubDate",
               editable:false,
               fieldLabel : "News Publish Date *",
     xtype : "datetimefield",
	                                                           allowBlank:false
                   
},

                     {
     name :"AssociatedIAPath",
           id:"NewsAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"CreatedDate",
           id:"NewsCreatedDate",
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
           id:"NewsLastModifiedDate",
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
           id:"NewsCreatedBy",
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
           id:"NewsLastModifiedBy",
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
     name :"Site",
           id:"NewsSite",
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
     name :"SeoUrl",
           id:"NewsSeoUrl",
               readOnly:true,
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
     name :"Version",
           id:"NewsVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"NewsWeightage",
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
	title:'DependentItems',
	xtype:'form',
	id:'NewsForm3',
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
	id:'NewsForm4',
	autoScroll:true,
        items:[RelatedItemPanelForm ]}];


var OfficeLocationForm = [
{
 title:'OfficeLocation',
 xtype:'form',
 id:'OfficeLocationForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"OfficeLocationId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "OfficeLocation Id",
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
           id:"OfficeLocationEntityType",
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
                    value:"OfficeLocation",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"OfficeLocationAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"ContactPersonName",
           id:"OfficeLocationContactPersonName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Contact Person Name *",
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
     name :"HindiName",
           id:"OfficeLocationHindiName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Hindi Name",
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
     name :"Designation",
           id:"OfficeLocationDesignation",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Designation *",
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
     name :"HindiDesignation",
           id:"OfficeLocationHindiDesignation",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Hindi Designation",
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
     name :"RtiUrl",
           id:"OfficeLocationRtiUrl",
                              maxLength :250,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Rti Url *",
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
     name :"Url",
           id:"OfficeLocationUrl",
                              maxLength :250,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Url Website *",
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
     name :"ContactUrl",
           id:"OfficeLocationContactUrl",
                              maxLength :250,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Contact us Web Url *",
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
     name :"Latitude",
           id:"OfficeLocationLatitude",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Latitude",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"float",
                                          allowBlank :true
                           
},

                     {
     name :"Longitude",
           id:"OfficeLocationLongitude",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Longitude",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"float",
                                          allowBlank :true
                           
},

                     {
     name :"Status",
           id:"OfficeLocationStatus",
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
           id:"OfficeLocationCreatedDate",
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
           id:"OfficeLocationLastModifiedDate",
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
           id:"OfficeLocationCreatedBy",
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
           id:"OfficeLocationLastModifiedBy",
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"OfficeLocationSite",
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
           id:"OfficeLocationVersion",
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
                           
},

                      {
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'Address',
	xtype:'form',
	id:'OfficeLocationForm5',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentAddressForm,
	                                {
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'HindiAddress',
	xtype:'form',
	id:'OfficeLocationForm6',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentHindiAddressForm,
	                                {
	  name:"Dummy",
	  id:"dummy",
	  hidden:true
	}
	]},{
	title:'ContactNumber',
	xtype:'form',
	id:'OfficeLocationForm7',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentContactNumberForm,
	                               {
     name :"Weightage",
           id:"OfficeLocationWeightage",
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


var ParliamentForm = [
{
 title:'Parliament',
 xtype:'form',
 id:'ParliamentForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ParliamentId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Parliament Id",
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
           id:"ParliamentEntityType",
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
                    value:"Parliament",
                                                                   allowBlank :true
                           
},

                     {
     name :"ParliamentName",
           id:"ParliamentParliamentName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Parliament Name *",
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
           id:"ParliamentCreatedDate",
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
           id:"ParliamentLastModifiedDate",
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
           id:"ParliamentCreatedBy",
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
           id:"ParliamentLastModifiedBy",
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
     name :"Site",
           id:"ParliamentSite",
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
           id:"ParliamentVersion",
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
          fieldLabel : "Pms Media Name *",
                                    listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.zip)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>PmsMediaName should end with valid file extension</div>");
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
	     	},
                                                                                                   vtype:"folderpath",
                                          allowBlank :true
                           
},

                     {
     name :"FilePath",
           id:"PmsMediaFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.png|.gif|.zip)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"Md5",
           id:"PmsMediaMd5",
               readOnly:true,
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
     name :"Site",
           id:"PmsMediaSite",
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
           id:"PmsMediaVersion",
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


var PortFolioForm = [
{
 title:'PortFolio',
 xtype:'form',
 id:'PortFolioForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"PortFolioId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "PortFolio Id",
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
           id:"PortFolioEntityType",
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
                    value:"PortFolio",
                                                                   allowBlank :true
                           
},

                     {
     name :"PortFolioName",
           id:"PortFolioPortFolioName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "PortFolio Name *",
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
           id:"PortFolioCreatedDate",
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
           id:"PortFolioLastModifiedDate",
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
           id:"PortFolioCreatedBy",
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
           id:"PortFolioLastModifiedBy",
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
     name :"Site",
           id:"PortFolioSite",
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
           id:"PortFolioVersion",
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
                           
} ]}];


var PrefixForm = [
{
 title:'Prefix',
 xtype:'form',
 id:'PrefixForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"PrefixId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Prefix Id",
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
           id:"PrefixEntityType",
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
                    value:"Prefix",
                                                                   allowBlank :true
                           
},

                     {
     name :"PrefixName",
           id:"PrefixPrefixName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Prefix Name *",
                               listeners:{
	    blur:function(){
	       var str = trim(this.getValue());
               str = str.replace(/ +(?= )/g,'');
	       this.setValue(str);
	    }
	     	},
                                                                                                   vtype:"prefix",
                                          allowBlank : false
                           
},

                     {
     name :"CreatedDate",
           id:"PrefixCreatedDate",
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
           id:"PrefixLastModifiedDate",
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
           id:"PrefixCreatedBy",
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
           id:"PrefixLastModifiedBy",
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
     name :"Site",
           id:"PrefixSite",
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
           id:"PrefixVersion",
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
          fieldLabel : "Role Name *",
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
               editable:false,
                 fieldLabel : "Domain Entity *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('DomainEntity', '', 'EntityName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

     	                         	                    {
     name :"UiTab",
           id:"UiTabsUiTab",
               editable:false,
                 fieldLabel : "UITab *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
               store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["uiTab"],
                        data : Ext.uiTab // from data.js
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
     name :"IsTopLevel",
           id:"RoleIsTopLevel",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "IsSuperAdmin",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["isSuperAdmin"],
                        data : Ext.isSuperAdmin // from data.js
                    }),
     queryMode:"local",
     displayField:"isSuperAdmin",
          emptyText:"",

                              value:"false",
                                      allowBlank:false
                   
},

                     {
     name :"Site",
           id:"RoleSite",
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
           id:"RoleVersion",
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
                           
} ]}];


var SchemeForm = [
{
 title:'Scheme',
 xtype:'form',
 id:'SchemeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"SchemeId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Scheme Id",
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
           id:"SchemeEntityType",
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
                    value:"Scheme",
                                                                   allowBlank :true
                           
},

                     {
     name :"Title",
           id:"SchemeTitle",
                              maxLength :1024,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "SchemeTitle *",
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
     name :"ShortDescription",
           id:"SchemeShortDescription",
                    xtype : "htmleditor",
               maxLength :100,
          fieldLabel : "Short Description *",  
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
           id:"SchemeAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"ReferenceUrl",
           id:"SchemeReferenceUrl",
                              maxLength :1024,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Reference Url",
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
     name :"SponsoredBy",
           id:"SchemeSponsoredBy",
                              maxLength :1024,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "SponsoredBy",
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
     name :"FundingPattern",
           id:"SchemeFundingPattern",
                    xtype : "textarea",
               maxLength :1024,
          fieldLabel : "Funding Pattern",
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
                                                                                       allowBlank :true
                           
},

                     {
     name :"Beneficiaries",
           id:"SchemeBeneficiaries",
                    xtype : "textarea",
               maxLength :100,
          fieldLabel : "Beneficiaries",
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
                                                                                       allowBlank :true
                           
},

                     {
     name :"Benefits",
           id:"SchemeBenefits",
                    xtype : "textarea",
               maxLength :1024,
          fieldLabel : "Benefits",
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
                                                                                       allowBlank :true
                           
},

                     {
     name :"EligibilityCriteria",
           id:"SchemeEligibilityCriteria",
                    xtype : "textarea",
               maxLength :1024,
          fieldLabel : "Eligibility Criteria",
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
                                                                                       allowBlank :true
                           
},

                     {
     name :"SeoUrl",
           id:"SchemeSeoUrl",
               readOnly:true,
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
     name :"HowToAvail",
           id:"SchemeHowToAvail",
                    xtype : "textarea",
               maxLength :1024,
          fieldLabel : "HowToAvail",
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
                                                                                       allowBlank :true
                           
},

               	                    {
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"SchemeStatus",
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
           id:"SchemeCreatedDate",
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
           id:"SchemeLastModifiedDate",
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
           id:"SchemeCreatedBy",
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
           id:"SchemeLastModifiedBy",
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
     name :"Site",
           id:"SchemeSite",
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
           id:"SchemeVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"SchemeWeightage",
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
	title:'NPIMetadata',
	xtype:'form',
	id:'SchemeForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];


var SectorForm = [
{
 title:'Sector',
 xtype:'form',
 id:'SectorForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"SectorId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Sector Id",
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
           id:"SectorEntityType",
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
                    value:"Sector",
                                                                   allowBlank :true
                           
},

                     {
     name :"SectorName",
           id:"SectorSectorName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Sector Name *",
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
     name :"ParentSectorId",
           id:"SectorParentSectorId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Parent Sector Id",
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
     name :"ParentSectorName",
           id:"SectorParentSectorName",
               readOnly:true,
               xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Parent Sector Name",
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
                                                     showTreePanelWindow(this, 'Sector', 'ParentSectorId' ,'parentchild');
                                               }
                 	},
                                                                                       allowBlank :true
                           
},

                     {
     name :"CreatedDate",
           id:"SectorCreatedDate",
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
           id:"SectorCreatedBy",
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
     name :"LastModifiedDate",
           id:"SectorLastModifiedDate",
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
     name :"LastModifiedBy",
           id:"SectorLastModifiedBy",
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
     name :"IsTopLevel",
           id:"SectorIsTopLevel",
               readOnly:true,
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
     name :"Site",
           id:"SectorSite",
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
           id:"SectorVersion",
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
                           
} ]}];


var ServiceForm = [
{
 title:'Service',
 xtype:'form',
 id:'ServiceForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ServiceId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Service Id",
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
           id:"ServiceEntityType",
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
                    value:"Service",
                                                                   allowBlank :true
                           
},

                     {
     name :"Title",
           id:"ServiceTitle",
                              maxLength :200,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title *",
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
     name :"AliasId",
           id:"ServiceAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"ServiceTypeId",
           id:"ServiceServiceTypeId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ServiceTypeId",
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
     name :"ServiceType",
           id:"ServiceServiceType",
               editable:false,
                 fieldLabel : "Service Type *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('ServiceType', '', 'ServiceTypeName'),
                                queryMode:"remote",
     displayField:"ServiceTypeName",
     valueField:"ServiceTypeName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('ServiceIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('ServiceIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('ServiceServiceTypeId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('ServiceTypeName','ASC');
              }
	   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"SourceName",
           id:"ServiceSourceName",
               readOnly:true,
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
           id:"ServiceFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
           }
           }
       }
        },
                                                                                                             allowBlank:true
                   
},

                     {
     name :"Description",
           id:"ServiceDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description *",  
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
     name :"ShortDescription",
           id:"ServiceShortDescription",
                    xtype : "htmleditor",
               maxLength :500,
          fieldLabel : "Short Description *",  
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
           id:"ServiceAssociatedIAPath",
               readOnly:true,
               xtype : "textarea",
               maxLength :400,
          fieldLabel : "Associated IA Path *",
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
     name :"Md5",
           id:"ServiceMd5",
               readOnly:true,
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
     name :"SeoUrl",
           id:"ServiceSeoUrl",
               readOnly:true,
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
           id:"ServiceStatus",
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
           id:"ServiceCreatedDate",
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
           id:"ServiceLastModifiedDate",
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
           id:"ServiceCreatedBy",
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
           id:"ServiceLastModifiedBy",
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
     name :"Site",
           id:"ServiceSite",
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
           id:"ServiceVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"ServiceWeightage",
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
	title:'NPIMetadata',
	xtype:'form',
	id:'ServiceForm2',
	autoScroll:true,
	fieldDefaults:{
	  labelWidth:155
	},
	items: [DependentMetadataInfoForm ]}];


var ServiceTypeForm = [
{
 title:'ServiceType',
 xtype:'form',
 id:'ServiceTypeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ServiceTypeId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "ServiceType Id",
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
           id:"ServiceTypeEntityType",
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
                    value:"ServiceType",
                                                                   allowBlank :true
                           
},

                     {
     name :"ServiceTypeName",
           id:"ServiceTypeServiceTypeName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Service Type Name *",
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
           id:"ServiceTypeCreatedDate",
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
           id:"ServiceTypeCreatedBy",
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
     name :"LastModifiedDate",
           id:"ServiceTypeLastModifiedDate",
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
     name :"LastModifiedBy",
           id:"ServiceTypeLastModifiedBy",
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
     name :"Site",
           id:"ServiceTypeSite",
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
           id:"ServiceTypeVersion",
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
          fieldLabel : "SourceName *",
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
     name :"Site",
           id:"SourceSite",
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
           id:"SourceVersion",
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
                           
} ]}];


var SourceTypeForm = [
{
 title:'SourceType',
 xtype:'form',
 id:'SourceTypeForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"SourceTypeId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "SourceType Id",
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
           id:"SourceTypeEntityType",
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
                    value:"SourceType",
                                                                   allowBlank :true
                           
},

                     {
     name :"SourceTypeName",
           id:"SourceTypeSourceTypeName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "SourceType Name *",
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
     name :"CreatedBy",
           id:"SourceTypeCreatedBy",
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
           id:"SourceTypeLastModifiedBy",
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
     name :"CreatedDate",
           id:"SourceTypeCreatedDate",
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
           id:"SourceTypeLastModifiedDate",
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
           id:"SourceTypeSite",
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
           id:"SourceTypeVersion",
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
          fieldLabel : "Stage Name *",
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
          fieldLabel : "Status Name *",
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
     name :"Site",
           id:"StatusSite",
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
           id:"StatusVersion",
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
          fieldLabel : "Template Name *",
                                    listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.html|.tpl)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>TemplateName should end with valid file extension</div>");
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
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.html|.tpl)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"Site",
           id:"TemplateSite",
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
     name :"Md5",
           id:"TemplateMd5",
               readOnly:true,
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
          fieldLabel : "Xls Name *",
                                    listeners:{
           blur:function(){
                   value = this.getValue();
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>XlsName should end with valid file extension</div>");
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
     fieldLabel : "File Path *",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.xls)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
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
     name :"Site",
           id:"ValidationsSite",
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
           id:"ValidationsVersion",
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
          fieldLabel : "Viewer Name *",
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
     name :"Site",
           id:"ViewerSite",
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
           id:"ViewerVersion",
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
                           
} ]}];


var WhosWhoCatForm = [
{
 title:'WhosWhoCat',
 xtype:'form',
 id:'WhosWhoCatForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"WhosWhoCatId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "WhosWhoCat Id",
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
           id:"WhosWhoCatEntityType",
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
                    value:"WhosWhoCat",
                                                                   allowBlank :true
                           
},

                     {
     name :"WhosWhoCatName",
           id:"WhosWhoCatWhosWhoCatName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "WhosWhoCat Name *",
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
           id:"WhosWhoCatCreatedDate",
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
           id:"WhosWhoCatLastModifiedDate",
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
           id:"WhosWhoCatCreatedBy",
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
           id:"WhosWhoCatLastModifiedBy",
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
     name :"Site",
           id:"WhosWhoCatSite",
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
           id:"WhosWhoCatVersion",
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
                           
} ]}];


var WhoswhoprofileForm = [
{
 title:'Whoswhoprofile',
 xtype:'form',
 id:'WhoswhoprofileForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"WhoswhoprofileId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Whoswhoprofile Id",
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
           id:"WhoswhoprofileEntityType",
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
                    value:"Whoswhoprofile",
                                                                   allowBlank :true
                           
},

                     {
     name :"AliasId",
           id:"WhoswhoprofileAliasId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Alias ID",
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
     name :"Title",
           id:"WhoswhoprofileTitle",
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
     name :"FirstName",
           id:"WhoswhoprofileFirstName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "First Name *",
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
           id:"WhoswhoprofileMiddleName",
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
           id:"WhoswhoprofileLastName",
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
     name :"FilePath",
           id:"WhoswhoprofileFilePath",
                    xtype:"filefield",
     fieldLabel : "File Path",
                                   listeners:{
           change:function(field,value){
                   value = value.replace(/^\s|\s$/g, "");
                   var valid_extensions = /(.jpg|.jpeg|.png|.gif|.doc|.pdf|.xls|.avi|.flv)$/i
           if(value != ""){
           if(!valid_extensions.test(value)){
                   Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid File</div>", "<div class = 'errorMsg'>FilePath should end with valid file extension</div>");
                   field.setValue("");
           }
           }
       }
        },
                                                                                                             allowBlank:true
                   
},

                     {
     name :"Gender",
           id:"WhoswhoprofileGender",
               editable:false,
                 fieldLabel : "Gender *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["gender"],
                        data : Ext.gender // from data.js
                    }),
     queryMode:"local",
     displayField:"gender",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"FatherName",
           id:"WhoswhoprofileFatherName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Father Name",
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
     name :"MotherName",
           id:"WhoswhoprofileMotherName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Mother Name",
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
     name :"SpouseName",
           id:"WhoswhoprofileSpouseName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Spouse Name",
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
           id:"WhoswhoprofileEmail",
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
                                          allowBlank :true
                           
},

                     {
     name :"BirthDate",
           id:"WhoswhoprofileBirthDate",
               editable:false,
               fieldLabel : "Birth Date",
     xtype : "datetimefield",
	                                                           allowBlank:true
                   
},

                     {
     name :"MaritalStatus",
           id:"WhoswhoprofileMaritalStatus",
               editable:false,
                 fieldLabel : "Marital Status *",
                    xtype:"combobox",
          editable:false,
                    store:Ext.create('Ext.data.ArrayStore', {
                        fields: ["maritalStatus"],
                        data : Ext.maritalStatus // from data.js
                    }),
     queryMode:"local",
     displayField:"maritalStatus",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"MarriageDate",
           id:"WhoswhoprofileMarriageDate",
               editable:false,
               fieldLabel : "Marriage Date",
     xtype : "datetimefield",
	                                                           allowBlank:true
                   
},

                     {
     name :"Childrens",
           id:"WhoswhoprofileChildrens",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "No Of Childrens",
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
     name :"PresentAddress",
           id:"WhoswhoprofilePresentAddress",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Present Address",
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
     name :"PermanentAddress",
           id:"WhoswhoprofilePermanentAddress",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Permanent Address",
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
     name :"Qualification",
           id:"WhoswhoprofileQualification",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Qualification *",
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
     name :"Constituency",
           id:"WhoswhoprofileConstituency",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Constituency",
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
     name :"WhosWhoCatId",
           id:"WhoswhoprofileWhosWhoCatId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "WhosWhoCat Id",
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
     name :"WhosWhoCat",
           id:"WhoswhoprofileWhosWhoCat",
               editable:false,
                 fieldLabel : "WhosWho Category",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('WhosWhoCat', '', 'WhosWhoCatName'),
                                queryMode:"remote",
     displayField:"WhosWhoCatName",
     valueField:"WhosWhoCatName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('WhoswhoprofileIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('WhoswhoprofileIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('WhoswhoprofileWhosWhoCatId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('WhosWhoCatName','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"MpCategoryId",
           id:"WhoswhoprofileMpCategoryId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "MpCategory Id",
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
     name :"MpCategory",
           id:"WhoswhoprofileMpCategory",
               editable:false,
                 fieldLabel : "MP Category",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Parliament', '', 'ParliamentName'),
                                queryMode:"remote",
     displayField:"ParliamentName",
     valueField:"ParliamentName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('WhoswhoprofileIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('WhoswhoprofileIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('WhoswhoprofileMpCategoryId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('ParliamentName','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"MpCode",
           id:"WhoswhoprofileMpCode",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Mp Code",
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
     name :"PortfolioId",
           id:"WhoswhoprofilePortfolioId",
                              enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Portfolio Id",
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
     name :"Portfolio",
           id:"WhoswhoprofilePortfolio",
               editable:false,
                 fieldLabel : "PortFolio",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('PortFolio', '', 'PortFolioName'),
                                queryMode:"remote",
     displayField:"PortFolioName",
     valueField:"PortFolioName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('WhoswhoprofileIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('WhoswhoprofileIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('WhoswhoprofilePortfolioId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('PortFolioName','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"Ministry",
           id:"WhoswhoprofileMinistry",
               editable:false,
                 fieldLabel : "Ministry",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="Ministry"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('WhoswhoprofileIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('WhoswhoprofileIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('WhoswhoprofileStateId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   			   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"SourceName",
           id:"WhoswhoprofileSourceName",
               readOnly:true,
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
     name :"ProfileDescription",
           id:"WhoswhoprofileProfileDescription",
                    xtype : "htmleditor",
               fieldLabel : "Profile Description *",  
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
     name :"Hobbies",
           id:"WhoswhoprofileHobbies",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Hobbies",
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
     name :"Activities",
           id:"WhoswhoprofileActivities",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Activities",
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
     name :"CountriesVisited",
           id:"WhoswhoprofileCountriesVisited",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Countries Visited",
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
     name :"BooksPublished",
           id:"WhoswhoprofileBooksPublished",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Books Published",
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
     name :"Profession",
           id:"WhoswhoprofileProfession",
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
     name :"OfficialUrl",
           id:"WhoswhoprofileOfficialUrl",
                              maxLength :150,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Official Weburl",
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
     name :"Status",
           id:"WhoswhoprofileStatus",
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
           id:"WhoswhoprofileCreatedDate",
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
           id:"WhoswhoprofileLastModifiedDate",
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
     name :"WorkflowId",
           id:"WorkflowInfoWorkflowId",
               readOnly:true,
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"CreatedBy",
           id:"WhoswhoprofileCreatedBy",
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
           id:"WhoswhoprofileLastModifiedBy",
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
     name :"Md5",
           id:"WhoswhoprofileMd5",
               readOnly:true,
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
     name :"Site",
           id:"WhoswhoprofileSite",
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
           id:"WhoswhoprofileVersion",
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
                           
},

                     {
     name :"Weightage",
           id:"WhoswhoprofileWeightage",
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
          fieldLabel : "Widget Name *",
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
          fieldLabel : "Widget Code *",
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
               editable:false,
                 fieldLabel : "Choose Workflow *",
                    xtype:"combobox",
          editable:false,
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
      } ,   
       render: function(combo,options){
                this.store.sort('WorkflowName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
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
     name :"CurrentAction",
           id:"WorkflowInfoCurrentAction",
               readOnly:true,
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
     name :"Site",
           id:"WidgetSite",
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
           id:"WidgetVersion",
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


var CurrencyForm = [
{
 title:'Currency',
 xtype:'form',
 id:'CurrencyForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"CurrencyId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Currency Id",
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
           id:"CurrencyEntityType",
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
                    value:"Currency",
                                                                   allowBlank :true
                           
},

                     {
     name :"CurrencyName",
           id:"CurrencyCurrencyName",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Currency Name *",
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
     name :"CurrencyCode",
           id:"CurrencyCurrencyCode",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Currency Code *",
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
     name :"CurrencySymbol",
           id:"CurrencyCurrencySymbol",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Currency Symbol *",
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
     name :"Country",
           id:"CurrencyCountry",
               editable:false,
                 fieldLabel : "Country",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Country', '', 'CountryName'),
                                queryMode:"remote",
     displayField:"CountryName",
     valueField:"CountryName",
          emptyText:"",

                                                          allowBlank:true
                   
},

                     {
     name :"LastModifiedDate",
           id:"CurrencyLastModifiedDate",
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
     name :"CreatedDate",
           id:"CurrencyCreatedDate",
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
           id:"CurrencyCreatedBy",
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
           id:"CurrencyLastModifiedBy",
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
     name :"Site",
           id:"CurrencySite",
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
           id:"CurrencyVersion",
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
                           
} ]}];


var CrawleddataForm = [
{
 title:'Crawleddata',
 xtype:'form',
 id:'CrawleddataForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"CrawleddataId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Crawleddata Id",
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
           id:"CrawleddataEntityType",
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
                    value:"Crawleddata",
                                                                   allowBlank :true
                           
},

                     {
     name :"Identifier",
           id:"CrawleddataIdentifier",
                              maxLength :250,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Identifier",
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
     name :"ContentId",
           id:"CrawleddataContentId",
                         minLength :13,
               maxLength :13,
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
                                                                                               allowBlank : true
              
},

                     {
     name :"ContentEntityType",
           id:"CrawleddataContentEntityType",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Content Entity Type",
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
     name :"IdentifierAlternate",
           id:"CrawleddataIdentifierAlternate",
                              maxLength :150,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Identifier Alternate",
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
     name :"TitleMain",
           id:"CrawleddataTitleMain",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title Main",
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
     name :"TitleAlternate",
           id:"CrawleddataTitleAlternate",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Title Alternate",
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
     name :"Description",
           id:"CrawleddataDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description",  
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
     name :"SubjectKeyword",
           id:"SubjectKeywordsSubjectKeyword",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Subject Keyword *",
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
     name :"Sector",
           id:"SubjectClassificationSector",
               editable:false,
                 fieldLabel : "Subject Classification *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('Sector', '', 'SectorName'),
                                queryMode:"remote",
     displayField:"SectorName",
     valueField:"SectorName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('SectorName','ASC');
           }
        },
                                                     allowBlank:false
                   
},

     	                         	                    {
     name :"PublisherOrgName",
           id:"PublisherOrgNamesPublisherOrgName",
               editable:false,
                 fieldLabel : "Publisher OrgName",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                                store:createQueryStore('GoiDirItem', 'category="Organisations"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
                                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('GoiDirItemName','ASC');
           }
        },
                                                     allowBlank:true
                   
},

     	                         	                    {
     name :"PublisherDepartment",
           id:"PublisherDeptNamePublisherDepartment",
               editable:false,
                 fieldLabel : "Publisher DeptName *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                                store:createQueryStore('GoiDirItem', 'category="Departments"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
                                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('GoiDirItemName','ASC');
           }
        },
                                                     allowBlank:false
                   
},

     	                               {
     name :"PublisherAddress",
           id:"CrawleddataPublisherAddress",
                    xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Publisher Address",
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
                                                                                       allowBlank :true
                           
},

               	                    {
     name :"CreatorOrgName",
           id:"CreatorOrgNamesCreatorOrgName",
               editable:false,
                 fieldLabel : "Creator OrgName",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                                store:createQueryStore('GoiDirItem', 'category="Organisations"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
                                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('GoiDirItemName','ASC');
           }
        },
                                                     allowBlank:true
                   
},

     	                         	                    {
     name :"CreatorDepartment",
           id:"CreatorDeptNameCreatorDepartment",
               editable:false,
                 fieldLabel : "Creator DeptName *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                                store:createQueryStore('GoiDirItem', 'category="Departments"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
                                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('GoiDirItemName','ASC');
           }
        },
                                                     allowBlank:false
                   
},

     	                               {
     name :"CreatorAddress",
           id:"CrawleddataCreatorAddress",
                    xtype : "textarea",
               maxLength :1000,
          fieldLabel : "Creator Address",
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
                                                                                       allowBlank :true
                           
},

                     {
     name :"Format",
           id:"CrawleddataFormat",
               editable:false,
                 fieldLabel : "Format",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Format', '', 'FormatName'),
                                queryMode:"remote",
     displayField:"FormatName",
     valueField:"FormatName",
          emptyText:"",

                                                          allowBlank:true
                   
},

                     {
     name :"LanguageId",
           id:"CrawleddataLanguageId",
               readOnly:true,
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
           id:"CrawleddataLanguage",
               editable:false,
                 fieldLabel : "Language",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('Language', '', 'LanguageCode'),
                                queryMode:"remote",
     displayField:"LanguageCode",
     valueField:"LanguageCode",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('CrawleddataIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('CrawleddataIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('CrawleddataLanguageId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('LanguageCode','ASC');
              }
	   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"MetaDataCreatedDate",
           id:"CrawleddataMetaDataCreatedDate",
               editable:false,
               fieldLabel : "MetaData Created Date",
     xtype : "datetimefield",
	                                                           allowBlank:true
                   
},

                     {
     name :"PublishedDate",
           id:"CrawleddataPublishedDate",
               editable:false,
               fieldLabel : "Published Date",
     xtype : "datetimefield",
	                                                           allowBlank:true
                   
},

                     {
     name :"ValidDate",
           id:"CrawleddataValidDate",
               editable:false,
               fieldLabel : "Valid Date",
     xtype : "datetimefield",
	                                                           allowBlank:true
                   
},

               	              	                    {
     name :"JurisdictionName",
           id:"JurisdictionJurisdictionName",
               editable:false,
                 fieldLabel : "Jurisdiction *",
                    xtype:"combobox",
          editable:false,
                                                       store:createStore('JurisdictionType', '', 'JurisdictionTypeName'),
                                queryMode:"remote",
     displayField:"JurisdictionTypeName",
     valueField:"JurisdictionTypeName",
          emptyText:"",

                                                          allowBlank:false
                   
},

                     {
     name :"JurisdictionStateId",
           id:"JurisdictionJurisdictionStateId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Jurisdiction StateId",
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
     name :"JurisdictionStateName",
           id:"JurisdictionJurisdictionStateName",
               editable:false,
                 fieldLabel : "Jurisdiction State *",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('JurisdictionIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('JurisdictionIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('JurisdictionJurisdictionStateId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   			   
              },    
                                                          allowBlank:true
                   
},

                     {
     name :"JurisdictionDistrictId",
           id:"JurisdictionJurisdictionDistrictId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Jurisidiction DistrictId",
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
     name :"JurisdictionDistrictName",
           id:"JurisdictionJurisdictionDistrictName",
               editable:false,
                 fieldLabel : "Jurisdiction District",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('JurisdictionIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('JurisdictionIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('JurisdictionJurisdictionDistrictId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   			   
              },    
                                                          allowBlank:true
                   
},

     	               	                         	              	                    {
     name :"SpatialStateId",
           id:"SpatialSpatialStateId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Spatial State Id",
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
     name :"SpatialStateName",
           id:"SpatialSpatialStateName",
               editable:false,
                 fieldLabel : "Spatial State",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('SpatialIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('SpatialIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('SpatialSpatialStateId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   			   
              },    
                                                          allowBlank:false
                   
},

                     {
     name :"SpatialDistrictId",
           id:"SpatialSpatialDistrictId",
               readOnly:true,
                         enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Spatial District Id",
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
     name :"SpatialDistrictName",
           id:"SpatialSpatialDistrictName",
               editable:false,
                 fieldLabel : "Spatial District",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
                                             queryMode:"remote",
     displayField:"GoiDirItemName",
     valueField:"GoiDirItemName",
          emptyText:"",

           listeners:{
      select:function(){
         var v = this.getValue();
         var i = this.store.findExact(this.valueField || this.displayField, v);
                  if(typeof(Ext.getCmp('SpatialIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('SpatialIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('SpatialSpatialDistrictId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   			   
              },    
                                                          allowBlank:true
                   
},

     	               	                               {
     name :"CoverageTemporal",
           id:"CrawleddataCoverageTemporal",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Coverage Temporal",
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
     name :"AudienceCategory",
           id:"AudienceAudienceCategory",
               editable:false,
                 fieldLabel : "Audience *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('AudienceCategory', '', 'AudienceName'),
                                queryMode:"remote",
     displayField:"AudienceName",
     valueField:"AudienceName",
          emptyText:"",

                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('AudienceName','ASC');
           }
        },
                                                     allowBlank:false
                   
},

     	                         	                    {
     name :"CategoryType",
           id:"CategoryTypesCategoryType",
               editable:false,
                 fieldLabel : "Type Category *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                                 store:createStore('Category', '', 'CategoryName'),
                                queryMode:"remote",
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
        },
                                                     allowBlank:false
                   
},

     	                               {
     name :"ConformsTo",
           id:"CrawleddataConformsTo",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Relation ConformsTo",
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
     name :"HasPart",
           id:"HasPartsHasPart",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Relation HasPart",
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
     name :"IsPartOf",
           id:"CrawleddataIsPartOf",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Relation IsPartOf",
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
     name :"HasVersion",
           id:"CrawleddataHasVersion",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Relation HasVersion",
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
     name :"IsVersionOf",
           id:"CrawleddataIsVersionOf",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Relation IsVersionof",
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
     name :"Weightage",
           id:"CrawleddataWeightage",
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
     name :"CreatedBy",
           id:"CrawleddataCreatedBy",
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
     name :"CreatedDate",
           id:"CrawleddataCreatedDate",
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
                                                     allowBlank:true
                   
},

                     {
     name :"LastModifiedBy",
           id:"CrawleddataLastModifiedBy",
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
     name :"LastModifiedDate",
           id:"CrawleddataLastModifiedDate",
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
                                                     allowBlank:true
                   
},

                     {
     name :"Source",
           id:"CrawleddataSource",
                              maxLength :50,
          enforceMaxLength:true,
          xtype:"textfield",
          fieldLabel : "Source *",
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
     name :"Site",
           id:"CrawleddataSite",
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
           id:"CrawleddataVersion",
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
                           
} ]}];


var ConfigurationForm = [
{
 title:'Configuration',
 xtype:'form',
 id:'ConfigurationForm1',
 autoScroll:true,
 fieldDefaults:{
   labelWidth:155
 },
 items:[
                  {
     name :"Id",
           id:"ConfigurationId",
               readOnly:true,
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
           id:"ConfigurationEntityType",
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
                    value:"Configuration",
                                                                   allowBlank :true
                           
},

                     {
     name :"Site",
           id:"ConfigurationSite",
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
           id:"ConfigurationVersion",
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
                           
},

                     {
     name :"ServiceType",
           id:"ConfigurationServiceType",
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
                                                                             allowBlank :true
                           
},

                     {
     name :"CreatedBy",
           id:"ConfigurationCreatedBy",
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
     name :"CreatedDate",
           id:"ConfigurationCreatedDate",
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
                                                     allowBlank:true
                   
},

                     {
     name :"LastModifiedBy",
           id:"ConfigurationLastModifiedBy",
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
     name :"LastModifiedDate",
           id:"ConfigurationLastModifiedDate",
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
                                                     allowBlank:true
                   
},

                     {
     name :"EntityName",
           id:"ConfigurationEntityName",
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
                                                                             allowBlank :true
                           
},

               	              	                    {
     name :"Key",
           id:"ConfigurationKeyKey",
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
                           
},

                     {
     name :"Value",
           id:"ConfigurationKeyValue",
               readOnly:true,
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
                           
} ]}];
var AccessControlListColumn = [
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var AddressColumn = [
                                                            {
                header:"Address Name",
        width:250,
        editor:"textfield",
        dataIndex:"AddressName"
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
    } ];var AppAdminColumn = [
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
    } ];var ArticleColumn = [
                                                            {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                                         {
                header:"ArticleType",
        width:250,
        editor:"textfield",
        dataIndex:"ArticleType"
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
    } ];var ArticleTypeColumn = [
                                               {
                header:"ArticleType Name",
        width:250,
        editor:"textfield",
        dataIndex:"ArticleTypeName"
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
    } ];var AudienceCategoryColumn = [
                                               {
                header:"Audience Category Name",
        width:250,
        editor:"textfield",
        dataIndex:"AudienceCategoryName"
     },
                                         {
                header:"Parent Audience Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentAudienceCategoryName"
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
    } ];var AwardsColumn = [
                                                                                      {
                header:"Award Name",
        width:250,
        editor:"textfield",
        dataIndex:"AwardName"
     },
                            {
                header:"Award Year",
        width:250,
        editor:"textfield",
        dataIndex:"AwardYear"
     },
                            {
                header:"Award Field",
        width:250,
        editor:"textfield",
        dataIndex:"AwardField"
     },
                            {
                header:"First Name",
        width:250,
        editor:"textfield",
        dataIndex:"FirstName"
     },
                            {
                header:"Last Name",
        width:250,
        editor:"textfield",
        dataIndex:"LastName"
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
    } ];var BannerColumn = [
                                                            {
                header:"Banner Title",
        width:250,
        editor:"textfield",
        dataIndex:"BannerTitle"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var BookColumn = [
                                                            {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                            {
                header:"Author",
        width:250,
        editor:"textfield",
        dataIndex:"Author"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var CategoryColumn = [
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
    } ];var CityColumn = [
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
    } ];var ContactNumberColumn = [
                                                            {
                header:"Contact Type",
        width:250,
        editor:"textfield",
        dataIndex:"ContactType"
     },
                                                      {
                header:"Office Phone",
        width:250,
        editor:"textfield",
        dataIndex:"OfficePhone"
     },
                                                                   {
                header:"Mobile No",
        width:250,
        editor:"textfield",
        dataIndex:"MobileNo"
     },
                                         {
                header:"Email",
        width:250,
        editor:"textfield",
        dataIndex:"Email"
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
    } ];var ContactTypeColumn = [
                                               {
                header:"Contact Type Name",
        width:250,
        editor:"textfield",
        dataIndex:"ContactTypeName"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
                header:"Role Name",
        width:250,
        editor:"textfield",
        dataIndex:"roleName"
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
                header:"Entity Group",
        width:250,
        editor:"textfield",
        dataIndex:"EntityGroup"
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
    } ];var FormColumn = [
                                               {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var FormatColumn = [
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
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
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
    } ];var GoiDirCatColumn = [
                                               {
                header:"GoiDirCat Name",
        width:250,
        editor:"textfield",
        dataIndex:"GoiDirCatName"
     },
                                                      {
                header:"Parent GoiDirCat Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentGoiDirCatName"
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
    } ];var GoiDirItemColumn = [
                                                            {
                header:"GoiDirItem Name",
        width:250,
        editor:"textfield",
        dataIndex:"GoiDirItemName"
     },
                                         {
                header:"Parent GoiDirItem Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentGoiDirItemName"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var JurisdictionTypeColumn = [
                                               {
                header:"Jurisdiction Type Name",
        width:250,
        editor:"textfield",
        dataIndex:"JurisdictionTypeName"
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
    } ];var LanguageColumn = [
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
    } ];var MediaColumn = [
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
        dataIndex:"MediaName"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
                                      if(pmsCurrentEntity=="Media")
                       cmsCurrentEntity = pmsCurrentEntity;
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
    } ];var NewsColumn = [
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
    } ];var MetadataInfoColumn = [
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
    } ];var OfficeLocationColumn = [
                                                            {
                header:"Contact Person Name",
        width:250,
        editor:"textfield",
        dataIndex:"ContactPersonName"
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
    } ];var PageColumn = [
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
    } ];var ParliamentColumn = [
                                               {
                header:"Parliament Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParliamentName"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var PortFolioColumn = [
                                               {
                header:"PortFolio Name",
        width:250,
        editor:"textfield",
        dataIndex:"PortFolioName"
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
    } ];var PrefixColumn = [
                                               {
                header:"Prefix Name",
        width:250,
        editor:"textfield",
        dataIndex:"PrefixName"
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
    } ];var PropertiesColumn = [
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
    } ];var SchemeColumn = [
                                               {
                header:"SchemeTitle",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                                                                   {
                header:"SponsoredBy",
        width:250,
        editor:"textfield",
        dataIndex:"SponsoredBy"
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
    } ];var SectorColumn = [
                                               {
                header:"Sector Name",
        width:250,
        editor:"textfield",
        dataIndex:"SectorName"
     },
                                         {
                header:"Parent Sector Name",
        width:250,
        editor:"textfield",
        dataIndex:"ParentSectorName"
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
    } ];var ServiceColumn = [
                                               {
                header:"Title",
        width:250,
        editor:"textfield",
        dataIndex:"Title"
     },
                                                      {
                header:"Service Type",
        width:250,
        editor:"textfield",
        dataIndex:"ServiceType"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var ServiceTypeColumn = [
                                               {
                header:"Service Type Name",
        width:250,
        editor:"textfield",
        dataIndex:"ServiceTypeName"
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
    } ];var SourceColumn = [
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
    } ];var SourceTypeColumn = [
                                               {
                header:"SourceType Name",
        width:250,
        editor:"textfield",
        dataIndex:"SourceTypeName"
     },
                            {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
                       Ext.example.msg("<div class = 'errorHeaderMsg'>404</div>", "<div class = 'errorMsg'>File Not Found</div>"); 
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
    } ];var WhosWhoCatColumn = [
                                               {
                header:"WhosWhoCat Name",
        width:250,
        editor:"textfield",
        dataIndex:"WhosWhoCatName"
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
    } ];var WhoswhoprofileColumn = [
                                                                         {
                header:"First Name",
        width:250,
        editor:"textfield",
        dataIndex:"FirstName"
     },
                                         {
                header:"Last Name",
        width:250,
        editor:"textfield",
        dataIndex:"LastName"
     },
                                                                                             {
                header:"Email",
        width:250,
        editor:"textfield",
        dataIndex:"Email"
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
    } ];var WidgetColumn = [
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
    } ];var NpiMetadataColumn = [
                                                                         {
                header:"Title Main",
        width:250,
        editor:"textfield",
        dataIndex:"TitleMain"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                {
                header:"Status",
        width:250,
        editor:"textfield",
        dataIndex:"Status"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                       {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
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
    } ];var CurrencyColumn = [
                                               {
                header:"Currency Name",
        width:250,
        editor:"textfield",
        dataIndex:"CurrencyName"
     },
                                                                   {
                header:"Last Modified Date",
        width:250,
        editor:"textfield",
        dataIndex:"LastModifiedDate"
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
    } ];var CrawleddataColumn = [
                     {
                header:"Crawleddata Id",
        width:250,
        editor:"textfield",
        dataIndex:"Id"
     },
                                                                   {
                header:"Content Entity Type",
        width:250,
        editor:"textfield",
        dataIndex:"ContentEntityType"
     },
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
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
    } ];var ConfigurationColumn = [
                                                                                      {
                header:"Created By",
        width:250,
        editor:"textfield",
        dataIndex:"CreatedBy"
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

Ext.AddressFields = [
        ["AliasId"],
         ["LastModifiedDate"],
         ["Village"],
         ["LastModifiedBy"],
         ["Pincode"],
         ["WorkflowName"],
         ["City"],
         ["Status"],
         ["State"],
         ["District"],
         ["StateId"],
         ["Address"],
         ["CreatedDate"],
         ["AddressName"],
         ["Id"],
         ["Weightage"],
         ["Taluk"]
    ]

Ext.AppAdminFields = [
        ["Name"],
         ["Email"],
         ["Address"],
         ["Id"],
         ["LastModifiedBy"],
         ["AppDate"],
         ["Weightage"],
         ["MobileNo"]
    ]

Ext.ArticleFields = [
        ["SeoUrl"],
         ["Description"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["ArticleType"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["City"],
         ["Status"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.ArticleTypeFields = [
        ["ArticleTypeName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.AudienceCategoryFields = [
        ["AudienceCategoryName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["ParentAudienceCategoryName"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.AwardsFields = [
        ["AliasId"],
         ["LastModifiedDate"],
         ["LastModifiedBy"],
         ["LastName"],
         ["WorkflowName"],
         ["Status"],
         ["State"],
         ["StateId"],
         ["AwardYear"],
         ["CreatedDate"],
         ["FirstName"],
         ["Id"],
         ["AwardField"],
         ["Weightage"],
         ["AwardName"]
    ]

Ext.BannerFields = [
        ["BannerTitle"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["FilePath"],
         ["LastModifiedBy"],
         ["WorkflowName"],
         ["MetaDataCreatedDate"],
         ["JurisdictionDistrictName"],
         ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.BookFields = [
        ["Description"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["CurrencyId"],
         ["Author"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["LastModifiedBy"],
         ["FilePath"],
         ["MetaDataCreatedDate"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["Status"],
         ["CurrencyName"],
         ["SourceName"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.CategoryFields = [
        ["CategoryName"],
         ["ParentCategoryName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
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

Ext.ContactNumberFields = [
        ["Status"],
         ["OfficePhone"],
         ["AliasId"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["ContactType"],
         ["WorkflowName"],
         ["Weightage"],
         ["MobileNo"]
    ]

Ext.ContactTypeFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["ContactTypeName"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.ContentPartnerFields = [
        ["Source"],
         ["Description"],
         ["AliasId"],
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
        ["AliasId"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["DesignationName"]
    ]

Ext.DomainFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["roleName"],
         ["Id"],
         ["LastModifiedBy"],
         ["WorkflowName"]
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
         ["AliasId"],
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
         ["AliasId"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.FormFields = [
        ["SeoUrl"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["FilePath"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
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
         ["Description"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["City"],
         ["Status"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.GoiDirCatFields = [
        ["ParentGoiDirCatName"],
         ["GoiDirCatName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.GoiDirItemFields = [
        ["HPath"],
         ["Category"],
         ["GoiDirId"],
         ["LeafOrNonLeaf"],
         ["Url"],
         ["CreatedDate"],
         ["GoiDirItemName"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["BelongsTo"],
         ["ParentGoiDirItemName"]
    ]

Ext.InformationArchitectureFields = [
        ["ParentIAName"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["FilePath"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["IaName"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["Id"],
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

Ext.JurisdictionTypeFields = [
        ["JurisdictionTypeName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.LanguageFields = [
        ["LanguageCode"],
         ["LanguageName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.MediaFields = [
        ["SeoUrl"],
         ["SpatialStateName"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["FilePath"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["Status"],
         ["SourceName"],
         ["MediaName"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.MediaGroupFields = [
        ["MediaGroupName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.NewsFields = [
        ["SeoUrl"],
         ["Description"],
         ["AliasId"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["Title"],
         ["Weightage"],
         ["AtomId"]
    ]

Ext.MetadataInfoFields = [
        ["SpatialStateName"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["JurisdictionName"],
         ["MetaDataCreatedDate"],
         ["JurisdictionDistrictName"]
    ]

Ext.OfficeLocationFields = [
        ["AliasId"],
         ["HindiAddressName"],
         ["Village"],
         ["LastModifiedDate"],
         ["LastModifiedBy"],
         ["HindiTaluk"],
         ["Pincode"],
         ["MobileNo"],
         ["ContactPersonName"],
         ["StateId"],
         ["AddressName"],
         ["HindiDistrict"],
         ["ContactType"],
         ["Taluk"],
         ["HindiAddress"],
         ["HindiState"],
         ["HindiStateId"],
         ["WorkflowName"],
         ["City"],
         ["Status"],
         ["Email"],
         ["OfficePhone"],
         ["State"],
         ["HindiVillage"],
         ["District"],
         ["Address"],
         ["CreatedDate"],
         ["HindiCity"],
         ["HindiAddressPincode"],
         ["Id"],
         ["Weightage"]
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

Ext.ParliamentFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["ParliamentName"],
         ["Id"],
         ["LastModifiedBy"]
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

Ext.PortFolioFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["PortFolioName"]
    ]

Ext.PrefixFields = [
        ["PrefixName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.RoleFields = [
        ["RoleName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["ParentRoleName"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.SchemeFields = [
        ["SeoUrl"],
         ["SpatialStateName"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["LastModifiedBy"],
         ["WorkflowName"],
         ["MetaDataCreatedDate"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["Status"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.SectorFields = [
        ["SectorName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["ParentSectorName"]
    ]

Ext.ServiceFields = [
        ["SeoUrl"],
         ["Description"],
         ["SpatialStateName"],
         ["AliasId"],
         ["MetadataDescription"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["ServiceType"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["FilePath"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["WorkflowName"],
         ["JurisdictionDistrictName"],
         ["Title"],
         ["Status"],
         ["SourceName"],
         ["CreatedDate"],
         ["Id"],
         ["Weightage"]
    ]

Ext.ServiceTypeFields = [
        ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"],
         ["ServiceTypeName"]
    ]

Ext.SourceFields = [
        ["SourceName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.SourceTypeFields = [
        ["SourceTypeName"],
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
         ["AliasId"],
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
         ["AssociatedDeptOrg"],
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

Ext.WhosWhoCatFields = [
        ["WhosWhoCatName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.WhoswhoprofileFields = [
        ["Ministry"],
         ["AliasId"],
         ["LastModifiedDate"],
         ["FilePath"],
         ["LastModifiedBy"],
         ["LastName"],
         ["WorkflowName"],
         ["Status"],
         ["Email"],
         ["SourceName"],
         ["CreatedDate"],
         ["FirstName"],
         ["Id"],
         ["Weightage"]
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

Ext.NpiMetadataFields = [
        ["Description"],
         ["SpatialStateName"],
         ["JurisdictionStateName"],
         ["SpatialDistrictName"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["LastModifiedBy"],
         ["TitleMain"],
         ["WorkflowName"],
         ["MetaDataCreatedDate"],
         ["JurisdictionDistrictName"],
         ["Status"],
         ["CreatedDate"],
         ["Id"],
         ["TitleAlternate"],
         ["Weightage"]
    ]

Ext.CurrencyFields = [
        ["CurrencyName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]

Ext.CrawleddataFields = [
        ["Description"],
         ["SpatialStateName"],
         ["SpatialDistrictName"],
         ["JurisdictionStateName"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["JurisdictionName"],
         ["Id"],
         ["LastModifiedBy"],
         ["MetaDataCreatedDate"],
         ["JurisdictionDistrictName"],
         ["Weightage"]
    ]

Ext.ConfigurationFields = [
        ["Value"],
         ["CreatedDate"],
         ["LastModifiedDate"],
         ["Id"],
         ["LastModifiedBy"]
    ]


var masterEntities = ['ArticleType','AudienceCategory','Category','City','ClassAttributes','ClassImport','ClassPackage','ContactType','Country','Designation','DomainEntity','Action','Format','GoiDirCat','GoiDirItem','InformationArchitecture','InterfaceInfo','JurisdictionType','Language','MediaGroup','Page','PageAssociator','Parliament','PortFolio','Role','Sector','ServiceType','Source','SourceType','Stage','Status','Template','Viewer','WhosWhoCat','Currency']
Ext.WorkflowEntities = [['any'],['Address'],['Article'],['Awards'],['Banner'],['Book'],['ContactNumber'],['Css'],['Form'],['Gallery'],['InformationArchitecture'],['Js'],['Media'],['OfficeLocation'],['Page'],['PageAssociator'],['PmsMedia'],['Scheme'],['Service'],['Template'],['Whoswhoprofile'],['Widget'],['NpiMetadata']]
var metaDataInfoList = ['Article','Banner','Book','Form','Gallery','InformationArchitecture','Media','Scheme','Service']