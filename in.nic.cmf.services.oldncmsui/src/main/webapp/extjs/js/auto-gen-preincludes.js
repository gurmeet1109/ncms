//http://124.7.228.161/ncmsui/extjs/js/data.js

Ext.uiTab = [
              ["CMS"],
              ["PMS"],
              ["Users And Roles"],
              ["Feed Automation"],
              ["Admin"],
              ["Domain"],
	      ["Workflow"]
            ];
           
Ext.isActive = [
                 ["true"],
                 ["false"]
               ];
Ext.online = [
              ["true"],
              ["false"]
            ];
Ext.cdata = [
              ["true"],
              ["false"]
            ];

Ext.defaultSearch = [
              ["true"],
              ["false"]
            ];

Ext.masterData = [
              ["true"],
              ["false"]
            ];

Ext.uI = [
              ["true"],
              ["false"]
            ];

Ext.uIForm = [
              ["true"],
              ["false"]
            ];

Ext.editOption = [
              ["true"],
              ["false"]
            ];

Ext.deleteOption= [
              ["true"],
              ["false"]
            ];

Ext.downloadOption = [
              ["true"],
              ["false"]
            ];
Ext.isSuperAdmin = [
              ["true"],
              ["false"]
            ];

Ext.isGeneratable = [
              ["true"],
              ["false"]
            ];    

Ext.visible = [
              ["true"],
              ["false"]
            ];

Ext.entityGroup = [
  ["cms"],
  ["pms"],
  ["feed"],
  ["user"],
  ["workflow"],
  ["admin"],
  ["domain"]
];

Ext.itemDependentEntityType = [
  ["Media"]
];

Ext.itemRelatedEntityType = [
  ["Article"],
  ["Form"],
  ["Gallery"],
  ["Media"],
  ["Service"]
];
            
Ext.newsCategory = [
                      ["DDNEWS"],
                      ["AIR"],
                      ["PIB"]
                   ];
                   
Ext.categoryType = [
                      ["TempCategory"]
                   ];
                   
Ext.whosWhoCategory = [
                      ["President"],
                      ["V. President"],
                      ["PM"],
                      ["LMP"],
                      ["Governor"]
                   ];

Ext.metadataCategory = [
                      ["TempCategory"]
                   ];
                   
Ext.galleryCategory = [
                      ["TempCategory"]
                   ];
                   
Ext.ageCategory = [
                ["1-14"],
                ["14-21"],
                ["21-30"],
                ["30-40"],
                ["40-60"],
                [">60"]
               ];
Ext.languageType = [
                ["en"],
                ["ta"],
                ["te"]
               ];
Ext.serviceType = [
                    ["Obtain"],
                    ["Register"],
                    ["ApplyFor"]
                  ];
Ext.status = [
                ["Live"],
                ["Draft"]
               ];

Ext.language = [
                ["eng"],
                ["tam"],
                ["tel"]
               ];


Ext.priority = [
                 ["1"],
                 ["2"],
                 ["3"],
                 ["4"],
                 ["5"]
               ];

Ext.online = [
               ["true"],
               ["false"]
             ];

Ext.rssAggregated = [
               ["true"],
               ["false"]
             ];

Ext.temp = [
               ["true"],
               ["false"]
             ];

Ext.councilOfMin = [
               ["true"],
               ["false"]
             ];

Ext.gender = [
               ["Male"],
               ["Female"]  
             ];

Ext.whosWhoCat = [
                   ["President"],
                   ["V. President"],
                   ["PM"],
                   ["LMP"],
                   ["RMP"],
                   ["Governor"],
                   ["CM"],
                   ["Judges"],
                   ["Defence Chief"]
                 ];

Ext.mpCategory = [
                   ["LS"],
                   ["RS"],
                   ["OT"]
                 ];



Ext.userRole = [
                    ["CMS"],
                    ["PortalUser"],
                    ["PortalAdmin"]
                   ];

Ext.stateType = [
                  ["State"],
                  ["Union Territory"]
                ];


Ext.deptType = [
                  ["Department"], 
                  ["Organisation"], 
                  ["Body"]
               ];

Ext.maritalStatus  = [
                  ["Married"], 
                  ["Single"], 
                  ["Divorcee"],
                  ["Widow"]
               ];

Ext.serviceType = [
                  ["Obtain"], 
                  ["Register"], 
                  ["ApplyFor"]
               ];

Ext.rateUsefullness = [
                  ["0"], 
                  ["1"], 
                  ["2"],
		  ["3"],
		  ["4"],
		  ["5"]
               ];
               
Ext.rateonAspect = [
                  ["0"], 
                  ["1"], 
                  ["2"],
		  ["3"],
		  ["4"],
		  ["5"]
               ];
      
Ext.isPmsFile =     [
               ["true"],
               ["false"]
             ];                 

Ext.type = [
                  ["Residence"], 
                  ["Office"], 
                  ["Business"]
               ];

Ext.viewer = [
                  ["Windows Media Player"], 
                  ["Flash Player"]
               ];

Ext.category = [
                  ["Bollywood"],
                  ["Hollywood"],
                  ["Offbeat"],
                  ["Others"]
               ];

Ext.channel = [
                  ["Movies"],
                  ["Sports"],
                  ["Finance"],
                  ["News"]
              ];
	      
Ext.workflow = [
		  ["District"],
                  ["EntityType"],
		  ["Ministry"],
		  ["PublisherDepartment"],
		  ["PublisherOrgName"],
		  ["State"]
              ];
	     /*
	       Ext.workflow = [
                  ["EntityType"],
                  ["ArticleType"],
                  ["Source"],
                  ["AssociatedIAName"],
		  ["State"],
		  ["Language"],
		  ["AssociatedIAID"]
              ];
	      */

Ext.leafOrNonLeaf = [
              ["Terminal"],
              ["Non Terminal"]
            ];
Ext.workflowType = [
              ["Manual"],
              ["Automatic"]
            ];	    

Ext.mappedFor = [
              ["CmsUserProfile"],
              ["Role"]
            ];
	    
//http://124.7.228.161/ncmsui/extjs/js/itempanels.js?_dc=3002

  var treePanelParentPath = "";
  var dynamicEntityType="media";
  var dynamicRelatedType="";
  var WorkflowEventsGrid;

   Ext.define("ItemSearch", {
	  extend: 'Ext.data.Model',
	  proxy: {
	      type: 'jsonp',
	      url: dataservicesPath + '/search?q=&format=extjson&entitytype='+dynamicEntityType,
	      reader: {
			type: 'json',
			root: 'Collections.'+dynamicEntityType,
			totalProperty: 'Collections.Count' 
	      }
	  },

	  fields: [
	      {name: 'ItemId',mapping:'Id'},
	      {name: 'ItemEntityType',mapping:'EntityType'},
	      {name: 'ItemTitle',mapping:'Title'},
	      {name: 'ItemDescription',mapping:'Description'},
              {name: 'ItemFilePath',mapping:'FilePath'},
	      {name: 'ItemThumbnailPath',mapping:'ThumbnailPath'}
	  ]
      });
   
    var imageTpl = new Ext.XTemplate(
	  '<tpl for=".">',
	      '<div class="thumb-wrap" id="{name}">',
	      '<tpl if="this.isTrue(ItemEntityType) && ItemThumbnailPath!==&quot;&quot;">',
		  '<div class="thumb"><img src='+mediaPath+'{ItemThumbnailPath} width=100 height=80 title="{ItemTitle}"></div>',
	      '</tpl>',
	      '<tpl if="this.isTrue(ItemEntityType) == false || ItemThumbnailPath==&quot;&quot;">',
		  '<div class="thumb"><img src='+imagePath+'/status-icons/{ItemEntityType}.jpg width=100 height=80 title="{ItemTitle}"></div>',
	      '</tpl>', 
	      '<span class="x-editable"></span></div>',
	  '</tpl>',
	  '<div class="x-clear"></div>',{
	      isTrue: function(name){
		  return name == 'Media';
	      }
	  }
      );
      
   
    function itemContextMenu(record,view){
	   
	    var ctextMenu  = Ext.create('Ext.menu.Menu', {
		items:[
			  {
			    text:'Set Title',
			    checked:false,
			    group:'title',
			    handler: function(){ 
			      itemContextMenuHandler(record,"title");
			    } 
			  },
			  {
 			    text:'Set Description',
			    checked:false,
			    group:'description',
			    handler: function(){ 
			      itemContextMenuHandler(record,"description");
			    } 
			  },
			  {
			    text:'Get Id',
			    checked:false,
			    group:'id',
			    handler: function(){ 
			      itemContextMenuHandler(record,"id");
			    }
			  },
			  {
			    text:'Clear',
			    checked:false,
			    group:'clear',
			    handler: function(){
			      view.getStore().remove(record);
			    }
			  },
			  {
			    text:'Clear All',
			    checked:false,
			    group:'clearall',
			    handler: function(){
			      view.getStore().removeAll();
			    }
			  }
		      ],
		      scope :this 
	    });
	    return ctextMenu;
	}		
      
      function thumbIcon(val) {
        return '<img src="' +mediaPath + val + '" width=80 height=60>'; 
      }
      
      function itemContextMenuHandler(record,currentItem){
	  var winTitle    = "";
	  var xtypeField  = "";
	  var itemContent = "";
	  
	  if(currentItem=="title"){
	    winTitle    = "Set Title";
	    xtypeField  = "textarea";
	    itemContent =  record.data.ItemTitle;
	  }  
	  else if(currentItem=="description"){
	    winTitle = "Set Description";
	    xtypeField = "htmleditor";
	    itemContent =  record.data.ItemDescription;
	  }  
	  else if(currentItem=="id"){
	    winTitle = "Item Id";
	    xtypeField = "textarea";
	    itemContent =  record.data.ItemId;
	  }

          function setItemPickContent(currentItem,val){
	      if(currentItem=="title"){
		record.set("ItemTitle",val);
	      }  
	      else if(currentItem=="description"){
		record.set("ItemDescription",val);
	      }
	      record.commit();
	  }

 	  var formPanel   = Ext.create('Ext.form.Panel', {
		frame:true,
		region:'center',
		//bodyStyle:'padding: 5px 5px 5px 5px',
		layout: 'fit',
		fieldDefaults: {
			msgTarget: 'side'
		},
		resizable:false,
		scope:this,
		items:[
			  {
			      xtype: xtypeField,
			      name:'itemPickContent',
			      id:'itemPickContent',
			      anchor : '100%',
			      allowBlank:false,
			      listeners:{
				 render:function(){
				   this.setValue(itemContent);
				 }
			      }
			  }
		      ]
	  });
      
	  if(!contextwin){ 
	      var contextwin = Ext.create('Ext.window.Window', {
		  width: 550,
		  height: 240,
		  layout: 'fit',
		  plain:true,
		  autoScroll:true,
		  resizable:false,
		  //closeAction: 'hide',
		  items: formPanel,
		  buttons: [{
				text : 'Ok',
				scope : formPanel,
				formBind:true,
				handler : function() {
        			   var itemPickContent = Ext.getCmp('itemPickContent');
				   setItemPickContent(currentItem,itemPickContent.getValue());
				   contextwin.close();
				}
			    },{
			         text: 'Cancel',
			         handler:function(){
			         formPanel.getForm().reset();
			         contextwin.close();
			    }
		  }]
	      });
	  }
	  
	  contextwin.setTitle(winTitle);
	  contextwin.show(); 
      }
     
   var DependentItemPanelForm = 
                                    [{
          name :"ItemDependentEntityType",
	  id:"ItemDependentEntityType",
          editable:false,
	  xtype:'combo',
          fieldLabel : "EntityType",
          store:Ext.create('Ext.data.ArrayStore', {
             fields: ["itemDependentEntityType"],
	      autoLoad:true,
             data : Ext.itemDependentEntityType // from data.js
          }),
          queryMode:"local",
          displayField:"itemDependentEntityType",
	  valueField:"itemDependentEntityType"
      },{
	    //region:'center',
            store: Ext.create('Ext.data.Store', {
               pageSize: 10,
               model: 'ItemSearch'
            }),
	    xtype:'combo',
	    scope:this,
            displayField: 'ItemTitle',
	    fieldLabel : 'Enter keywords',
	    labelWidth:100,	
	    minChars:2,
	    width: 600,
	//    scope:dependentSearchField,
            typeAhead: false,
	    multiSelect:true,
            hideLabel: false,
            hideTrigger:false,
	    selectOnFocus:true,
            listConfig: {
                loadingText: 'Searching...',
                emptyText: 'No matching records found.',
                // Custom rendering template for each item
                getInnerTpl: function() {
		      return '<div class="search-item">'+
			    '<table border=0>'+
			    '<tr><td rowspan=2>'+
			    '<tpl if="ItemEntityType==&quot;Media&quot; && ItemThumbnailPath!==&quot;&quot;">'+
			      '<img src='+mediaPath+'{ItemThumbnailPath} width=100 height=80 />'+
			    '</tpl>'+
			    '<tpl if="ItemThumbnailPath==&quot;&quot;">'+
			      '<img src='+imagePath+'/status-icons/{ItemEntityType}.jpg width=100 height=80 />'+
			    '</tpl>'+
			    '</td><td>{ItemMediaName}</td></tr>'+
			    '<tr><td>{ItemTitle}</td></tr></table>'+
			    '</div>';
                }
            },
            pageSize: 10,
            listeners: {
                select: function(combo, selection) {
		    var dependentView = Ext.getCmp('dependent-images-view');
		    for(var i = 0; i < selection.length; i++){
		        var id = selection[i].data.ItemId;
		        var exists = dependentView.store.find('ItemId',id);
			if(exists == -1 ){
			  dependentView.store.add(selection[i]);
			}
		    }
                },
              /*  focus : function(){
		  this.setValue('');
		}, */
	        beforequery:function(eventObj){
		 var dependentEntityCombo = Ext.getCmp('ItemDependentEntityType');
	         this.store.proxy.url= dataservicesPath + '/search?q=&format=extjson&entitytype='+dependentEntityCombo.getValue().toLowerCase();
		 this.store.proxy.setReader(
		 {
		      type: 'json',
		      root: 'Collections.'+dependentEntityCombo.getValue(),
		      totalProperty: 'Collections.Count' 
		 });
	        }
             }
       },{
	  xtype:'dataview',
	  id:'dependent-images-view',
	  fieldLabel : 'Available Dependent Items',
	  border:'10 5 3 10',
	  store: Ext.create('Ext.data.Store', {
		  model: 'ItemSearch',
		  autoScroll:true,
		  storeId: 'itemDependentViewStore',
	    	  pageSize: itemsPerPage,
		  autoLoad : false,
		  proxy: {
			  type: 'jsonp',
			  url: dataservicesPath + '/search?q=&format=extjson&entitytype=article',
			  reader: {
			      type: 'json',
			      root: 'Collections.DependentItems'
			  }
		  }
	    }),
	  tpl: imageTpl,
 	  height:400,
	  border:true,
	  trackOver: true,
	  overItemCls: 'x-item-over',
	  itemSelector: 'div.thumb-wrap',
	  emptyText: 'No Items Available',
	  plugins: [
                Ext.create('Ext.ux.DataView.DragSelector', {})
          ],
	  listeners: {
		itemcontextmenu: function(View,record,item,index,e,options ) {
		    e.stopEvent();
		    var dependentView = Ext.getCmp('dependent-images-view');
		    var cMenu = itemContextMenu(record,dependentView);
		    cMenu.showAt(e.getXY());
		    return false;
		}
	  }
      }];

      
    var RelatedItemPanelForm = [{
          name :"ItemRelatedEntityType",
	  id:"ItemRelatedEntityType",
          editable:false,
	  xtype:'combo',
          fieldLabel : "EntityType",
          store:Ext.create('Ext.data.ArrayStore', {
             fields: ["itemRelatedEntityType"],
	     autoLoad:true,
             data : Ext.itemRelatedEntityType // from data.js
          }),
          queryMode:"local",
	  valueField:"itemRelatedEntityType",
          displayField:"itemRelatedEntityType"
      },{
            store: Ext.create('Ext.data.Store', {
               pageSize: 10,
               model: 'ItemSearch'
            }),
	 //   scope: relatedPanel,
            displayField: 'ItemTitle',
	    labelWidth:100,
	    xtype:'combo',
	    minChars:2,
            fieldLabel : 'Enter keywords',
            typeAhead: false,
	    multiSelect:true,
            hideLabel: false,
            hideTrigger:false,
	    scope:this,
            width:600,
            listConfig: {
                loadingText: 'Searching...',
                emptyText: 'No matching records found.',
                // Custom rendering template for each item
                getInnerTpl: function() {
		      return '<div class="search-item">'+
			    '<table border=0>'+
			    '<tr><td rowspan=2>'+
			    '<tpl if="ItemEntityType==&quot;Media&quot;  && ItemThumbnailPath!==&quot;&quot;">'+
			      '<img src='+mediaPath+'{ItemThumbnailPath} width=100 height=80 />'+
			    '</tpl>'+
			    '<tpl if="ItemEntityType!==&quot;Media&quot; || ItemThumbnailPath==&quot;&quot;">'+
			      '<img src='+imagePath+'/status-icons/{ItemEntityType}.jpg width=100 height=80 />'+
			    '</tpl>'+
			    '</td><td>{ItemMediaName}</td></tr>'+
			    '<tr><td>{ItemTitle}</td></tr></table>'+
			    '</div>';
                }
            },
            pageSize: 10,
            listeners: {
                select: function(combo, selection) {
		  var relatedView = Ext.getCmp('related-images-view');
		    for(var i = 0; i < selection.length; i++){
		        var id = selection[i].data.ItemId;
		        var exists = relatedView.store.find('ItemId',id);
			if(exists == -1 ){
			  relatedView.store.add(selection[i]);
			}
		    }
                },
		/*focus: function(){
		  this.setValue('');
		}, */
	        beforequery:function(eventObj){
		 var relatedEntityCombo = Ext.getCmp('ItemRelatedEntityType');
	         this.store.proxy.url= dataservicesPath + '/search?q=&format=extjson&entitytype='+relatedEntityCombo.getValue().toLowerCase();
		 this.store.proxy.setReader(
		 {
		      type: 'json',
		      root: 'Collections.'+relatedEntityCombo.getValue(),
		      totalProperty: 'Collections.Count' 
		 });
	        },
		onTriggerClick: function(e) {
		  this.updateBox(e);
		  this.expand();
		}
             }
      },{
	  xtype:'dataview',
	  id:'related-images-view',
	  border:'10 5 3 10',
	  store: Ext.create('Ext.data.Store', {
		model: 'ItemSearch',
		storeId: 'itemRelatedViewStore',
		autoScroll:true,
		pageSize: itemsPerPage,
		autoLoad : false,
		proxy: {
			type: 'jsonp',
			url: dataservicesPath + '/search?q=&format=extjson&entitytype=article',
			reader: {
			    type: 'json',
			    root: 'Collections.RelatedItems'
			}
		}
	  }),
	  tpl: imageTpl,
	 // multiSelect: true,
	  border:true,
 	  height:400,
	  trackOver: true,
	  overItemCls: 'x-item-over',
	  itemSelector: 'div.thumb-wrap',
	  emptyText: 'No Items Available',
	  plugins: [
                Ext.create('Ext.ux.DataView.DragSelector', {})
          ],
	  listeners: {
	      itemcontextmenu: function(View,record,item,index,e,options ) {
		  var relatedView = Ext.getCmp('related-images-view');
		  e.stopEvent();
		  var cMenu = itemContextMenu(record,relatedView);
		  cMenu.showAt(e.getXY());
		  return false;
	      }
	  }
      }];   
       
//http://124.7.228.161/ncmsui/extjs/js/nic-vtypes.js?_dc=3002

Ext.apply(Ext.form.field.VTypes, {
	 alpha: function(val, field) {
    	 return /^([a-zA-Z ]+)$/.test(val);
     },
     alphaMask:/^([a-zA-Z ]+)$/,
     alphaText: 'This field should only contain letters',

     alphanumStrict: function(val, field) {
    	 return /^([a-zA-Z0-9 _-]+)$/.test(val);
     },
     alphanumStrictMask:/^([a-zA-Z0-9 _-]+)$/,
     alphanumStrictText: 'This field should only contain letters, numbers and _ -',  
      
     alphanumMid: function(val, field) {
    	 return /^([a-z.:;@#,()\/"'A-Z0-9 _-]+)$/.test(val);
     },
     alphanumMidMask:/^([a-z.:;@#,()\/"'A-Z0-9 _-]+)$/,
     aalphanumMidText: 'This field should only contain letters, numbers and allowed special characters [.:;@#,()"\'/ _-]',

     alphanumLoose: function(val, field) {
    	 return /^([a-z.:;@#!$%^&*{},()\\/"'A-Z0-9 _-]+)$/.test(val);
     },
     alphanumLooseMask:/^([a-z.:;@#!$%^&*{},()\\/"'A-Z0-9 _-]+)$/,
     alphanumLooseText: 'This field should only contain letters, numbers and special characters',
     
     alphanumeric : function(val,field){
         return /^[0-9a-zA-Z ]+$/.test(val);
     },
     alphanumericMask:/^[0-9a-zA-Z ]+$/,
     alphanumericText: 'This field should only contain letters and numbers',

     searchText: function(val, field) {
         var ft =  /^([a-z.:;@#,\/"A-Z0-9 _-]+)$/.test(val);
	 if(!ft)
	   return false;
         if(val.length>=1){
	    var pattern=/[\.\,\#]/gi;
	    if(val.charAt(0).match(pattern))
		return false;
	      else
		return true;
	 }
     },
     searchTextMask:/^([a-z.:;@#,\/"A-Z0-9 _-]+)$/,
     searchTextText: 'This field should contain only characters',
     
     numeric: function(val, field) {
     	return /^([0-9]+)$/.test(val);
     },
     numericMask: /^([0-9]+)$/,
     numericText: 'This field should contain only numbers',
     pincode: function(val, field) {
     	 return /^[0-9]{0,6}$/.test(val); 
     },
     pincodeMask: /^[0-9]{0,6}$/,
     pincodeText: 'This field should contain only numbers',
     
     float: function(val, field) {
    	 return /^([0-9-.]+)$/.test(val);
     },
     floatMask: /^([0-9-.]+)$/,
     floatText: 'This field should contain only numbers and .',
     
     filename: function(val, field) {
	  var regArray = val.split(".");
	  if(regArray.length==2){
	      if(!regArray[1])
  	        return false;
	      if(regArray[0].length>=1 && regArray[1].length>=2 && regArray[1].length<=4)
		return true
	  }
	  else if(regArray.length==3){
	      if(!regArray[2])
  	        return false;
	      if(regArray[0].length>=1 && regArray[1].length>=1 && regArray[2].length>=2 && regArray[2].length<=4) 
	        return true;
	  }  
	  else
	    return false; 
	  /* return /^([a-z.A-Z0-9_-]+)$/.test(val);
	  var pattern=/^[.]{2}$/;
	  if(!val.match(pattern))
	    return false;
	  if(regArray.length>=2){
	   lastElement = regArray.length-1
	    if(regArray[0].length>=1 && regArray[lastElement].length>=2 &&  regArray[lastElement].length<=4)
	      return true 
	  }
	  else
	    return false;*/
     },
     filenameMask:/^([a-z.A-Z0-9_-]+)$/,
     filenameText: 'This field should only contain valid filename',
     
     folderpath: function(val, field) {
    	 return /^([a-z.\\\/A-Z0-9 _-]+)$/.test(val);
     },
     folderpathMask:/^([a-z.\\\/A-Z0-9 _-]+)$/,
     folderpathText: 'This field should only contain letters, numbers and _ -',
     
     year: function(val, field) {
    	 return /^[0-9]{0,4}$/.test(val);
     },
     yearMask:/^[0-9]{0,4}$/,
     yearText: 'This field should contain valid year',
     
     prefix: function(val, field) {
    	 return /^[a-zA-Z ]{0,6}$/.test(val);
     },
     prefixMask:/^[a-zA-Z ]{0,6}$/,
     prefixText: 'This field should contain only characters and length should not be more than 6',
     
     age: function(val, field) {
    	 return /^[0-9]{0,2}$/.test(val);
     },
     ageMask:/^[0-9]{0,2}$/,
     ageText: 'This field should contain only numbers',

     isbn: function(val, field) {
	 /*if(!/^[0-9]{13,13}$/i.test(v))
		 return false;
		 else{
		 var summe = 0;
		 var j = 1;
		 var teil = 0;
		 for(var i = 0; i < 9; i++){
			 teil = v.substr(i,1);
			 summe += teil * j;

			 j++;
		 }
		 var mod = summe % 11;
		 if(mod == v.substr(i,9)){
		 	return true;
		 }
		 else
		 	return false;
		 }
	 },*/
         // return /^ISBN\s(?=[-0-9xX ]{13}$)(?:[0-9]+[- ]){3}[0-9]*[xX0-9]$/.test(val)
            return validateISBN(val);
         },
	 isbnText: 'This Field Should have Valid ISBN Format',
	 isbnMask: /^[0-9 -]+$/
 });

function validateISBN(isbn) {
  if(isbn.match(/[^0-9xX\.\-\s]/)) {
    return false;
  }
 
  isbn = isbn.replace(/[^0-9xX]/g,'');
 
  if(isbn.length != 10 && isbn.length != 13) {
    return false;
  }
 
    checkDigit = 0;
  if(isbn.length == 10) {
    checkDigit = 11 - ( (
                 10 * isbn.charAt(0) +
                 9  * isbn.charAt(1) +
                 8  * isbn.charAt(2) +
                 7  * isbn.charAt(3) +
                 6  * isbn.charAt(4) +
                 5  * isbn.charAt(5) +
                 4  * isbn.charAt(6) +
                 3  * isbn.charAt(7) +
                 2  * isbn.charAt(8)
                ) % 11);
 
    if(checkDigit == 10) {
      return (isbn.charAt(9) == 'x' || isbn.charAt(9) == 'X') ? true : false;
    } else {
      return (isbn.charAt(9) == checkDigit ? true : false);
    }
  } else {
    checkDigit = 10 -  ((
                 1 * isbn.charAt(0) +
                 3 * isbn.charAt(1) +
                 1 * isbn.charAt(2) +
                 3 * isbn.charAt(3) +
                 1 * isbn.charAt(4) +
                 3 * isbn.charAt(5) +
                 1 * isbn.charAt(6) +
                 3 * isbn.charAt(7) +
                 1 * isbn.charAt(8) +
                 3 * isbn.charAt(9) +
                 1 * isbn.charAt(10) +
                 3 * isbn.charAt(11)
                ) % 10);
 
    if(checkDigit == 10) {
      return (isbn.charAt(12) == 0 ? true : false) ;
    } else {
      return (isbn.charAt(12) == checkDigit ? true : false);
    }
  }
}

//http://124.7.228.161/ncmsui/extjs/js/global-extjs-windows-fns.js?_dc=3002

 Ext.Ajax.on('requestexception', function(conn, response, options){
        if(response.status == 401){
		var actionString = response.responseText;
		var errMsg = '';
		if(!Ext.isEmpty(actionString)){
		  if(typeof(actionString) != "undefined"){
			 errMsg = getCustomErrorInfo(actionString);
		  }
		} 
		Ext.example.msg("<div class = 'errorHeaderMsg'>Access Denied</div>", 
			"<div class = 'errorMsg'>" + errMsg + "</div>");
        }
	if(response.status == 503){
           Ext.example.msg("<div class = 'errorHeaderMsg'>Server Unreachable</div>", "<div class = 'errorMsg'>Service temporarily unavailable</div>");
        }
    });

Ext.Ajax.on('beforerequest', function(conn, response, options){
    if(!userRole){
	    Ext.example.msg("<div class = 'errorHeaderMsg'>Session Expired</div>", "<div class = 'errorMsg'>Please login again</div>");
    }
});

Ext.Ajax.on('requestcomplete', function(conn, response, options){
  		//alert(response.responseText);
	if(response.status != 200 && response.responseText != ''){
		Ext.MessageBox.hide();
		//alert(response.responseText);
		try{
			var pmsResponse = JSON.parse(response.responseText);
			if(pmsResponse.success != 'true'){
				//alert(response.responseText);
			}
		}
		catch(error){
		    	Ext.MessageBox.hide();
			if(aForms[0] && aForms[0].findField('FilePath')){
				aForms[0].findField('FilePath').setValue('');
			}
			if(globalEntityName == "Css" || globalEntityName == "Js" 
				|| globalEntityName == "Template"){
				pmsErrorpanel.expand(false); 
				pmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + response.responseText + "</div>");
			}
			else if(globalEntityName == "Media" ){
				pmsErrorpanel.expand(false); 
				pmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + response.responseText + "</div>");
				cmsErrorpanel.expand(false); 
				cmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + response.responseText + "</div>");
			}
			else if(globalEntityName == "Validations" || globalEntityName == "AccessControlList"){
				adminErrorpanel.expand(false); 
				adminErrorpanel.update("<div class = 'errorHeaderMsg'>" + response.responseText + "</div>");
			}
		}
	}
	else if(response.responseText == ''){
		Ext.MessageBox.hide();
		var alertMsg = 'Invalid Content OR Server Error';
		if(aForms[0] && aForms[0].findField('FilePath')){
				aForms[0].findField('FilePath').setValue('');
		}
		if(globalEntityName == "Css" || globalEntityName == "Js" 
			|| globalEntityName == "Template"){
			pmsErrorpanel.expand(false); 
			pmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + alertMsg + "</div>");
		}
		else if(globalEntityName == "Media" ){
			pmsErrorpanel.expand(false); 
			pmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + alertMsg + "</div>");
			cmsErrorpanel.expand(false); 
			cmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + alertMsg + "</div>");
		}
		else if(globalEntityName == "Validations" || globalEntityName == "AccessControlList"){
			adminErrorpanel.expand(false); 
			adminErrorpanel.update("<div class = 'errorHeaderMsg'>" + alertMsg + "</div>");
		}
	}
},this);


function trim(s)
{
	return rtrim(ltrim(s));
}

function ltrim(s)
{
	var l=0;
	while(l < s.length && s[l] == ' ')
	{	l++; }
	return s.substring(l, s.length);
}

function rtrim(s)
{
	var r=s.length -1;
	while(r > 0 && s[r] == ' ')
	{	r-=1;	}
	return s.substring(0, r+1);
}
function getDomainName(){
   return domainName;
}
function getHost(){
   return host;
}

/*function createStoreByFilter(root, filterName){
   var form = aForms[0];
   var fName = form.findField(root+filterName).getValue();
   alert(fName);
   return createStore(root, filterName+"="+fName);
}*/
function createStore(root, queryParam, sortField) {
	var cs = Ext.create('Ext.data.Store', {
		autoLoad : false,
		pageSize : 100,
		sortOnFilter:true,
		model : 'NcmsUIModel',
		remoteFilter:false,
		proxy : {
			type : 'ajax',
			noCache : true,
			url : apacheNcmsData + "/" + root + ".json",
			reader : {
				type : 'json',
				root : 'Collections.' + root,
				totalProperty : 'Collections.Count'
			}
		        
               },
               sorters:[{property:sortField, direction:'ASC'}]
	});
	if(!Ext.isEmpty(queryParam)){
		var queryArray = queryParam.split('&');
		for(var i = 0; i < queryArray.length; i++){
			var keyValuePairs = queryArray[i].split('=');
			cs.filter(keyValuePairs[0],keyValuePairs[1]);
		}
	}
	return cs;
}


function createQueryStore(root, queryParam, sortField) {
	if(!Ext.isEmpty(queryParam)){
		queryParam = '&' + queryParam;
	}
	else{
		queryParam = '';
	}
	var cs = Ext.create('Ext.data.Store', {
		autoLoad : false,
		pageSize : 100,
		model : 'NcmsUIModel',
		proxy : {
			type : 'ajax',
			noCache : false,
			url : dataservicesPath+'/search?q=&entitytype=' + root
			+ '&format=extjson' + queryParam,
			//url : apacheNcmsData + "/" + root + ".json",
			reader : {
				type : 'json',
				root : 'Collections.' + root,
				totalProperty : 'Collections.Count'
			},
			simpleSortMode:true
		}
               // sorters:[{property:sortField, direction:'ASC'}]
	});
	/*if(!Ext.isEmpty(queryParam)){
		var queryArray = queryParam.split('&');
		for(var i = 0; i < queryArray.length; i++){
			var keyValuePairs = queryArray[i].split('=');
			cs.filter(keyValuePairs[0],keyValuePairs[1]);
		}
	}*/
	return cs;
}


function createPreloadedStore(root, sortField) {
	var cps = Ext.create('Ext.data.Store', {
		autoLoad : false,
		pageSize : 100,
		model : 'NcmsUIModel',
		proxy : {
			type : 'jsonp',
			noCache : true,
			url : dataservicesPath+'/search?q=&entitytype=' + root
			+ '&format=extjson',
			reader : {
				type : 'json',
				root : 'Collections.' + root,
				totalProperty : 'Collections.Count'
			},
			simpleSortMode:true
		},
		listener :{
			loadexception: function(qe){
	            delete qe.combo.lastQuery;
	            this.proxy.noCache = true;
	        }
		}
	});
	return cps;
}

function createLocalJsonStore(root) {
	var cps = Ext.create('Ext.data.Store', {
		pageSize : 100,
		model : 'NcmsUIModel',
		proxy : {
			type : 'ajax',
			noCache : true,
			url : apacheNcmsData + "/" + root + ".json",
			reader : {
				type : 'json',
				root : root
			},
			simpleSortMode: true
		}
	});
	return cps;
}

var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
    clicksToEdit: 1
});

Ext.define('EventListModel', {
    extend: 'Ext.data.Model',
    proxy: {
	type: 'jsonp',
	url: dataservicesPath + '/search?q=&format=extjson&entitytype=media',
	reader: {
		  type: 'json',
		  root: 'Collections.Media',
		  totalProperty: 'Collections.Count' 
	}
    },
    fields: [
	{name: 'EventName'},
	{name: 'DisplayName'},
	{name: 'AssociatedStage'},
	{name: 'AssociatedStatus'},
	{name: 'PossibleEvent'}
    ]
});

function cmsCreateWindow(grid, rowIndex, formAction, activeMainPanelGrid, preload) {
	globalEntityName = cmsCurrentEntity;
	var winHeight    = getWindowHeight();
	cmsErrorpanel = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',layout:'fit',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var formItem      = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		labelStyle: 'white-space: nowrap;',
		width : 750,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [ Ext.JSON.decode(cmsCurrentEntity + "Form") ],
		scope : Ext.JSON.decode(cmsCurrentEntity + "Form")
	});

	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;
	if(formAction != 'new'){
		var currentEntityItem  =  grid.getStore().getAt(rowIndex);
		currentEntityItemId    =  currentEntityItem.data.Id;
	}  
	if (!cmsWindow) { 
		var cmsWindow = new Ext.Window({
			//closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 755,
			items : [ formItem,cmsErrorpanel ], //[ formItem,cmsErrorpanel ],
			buttons : [ {
				text : 'Save',
				id : 'cms-save',
				scope : Ext.JSON.decode(cmsCurrentEntity + "Form"),
				formBind:true,
				handler : function() {
					//loadFormsArray(cmsCurrentEntity);
					saveEntity(activeMainPanelGrid,cmsWindow,cmsCurrentEntity,formAction,cmsErrorpanel);
				}
			},{
				text: 'Cancel',
				id : 'cms-cancel',
				handler:function(){
				   cmsWindow.close();
				}
			}
			]
		});
		
		cmsWindow.on('close',function(){
		          hideHtmlEditor();
		      	  aForms = new Array();
			  var dependentView = Ext.getCmp('dependent-images-view');
			  var relatedView = Ext.getCmp('related-images-view');
			  if(!Ext.isEmpty(dependentView)){
			    dependentView.store.removeAll();			    
			  }
			  if(!Ext.isEmpty(relatedView)){
			    relatedView.store.removeAll();			    
			  }
		});
		
	}
	cmsWindow.setTitle(wintitle);
	loadFormsArray(cmsCurrentEntity);
	if(preload){
		if(grid != 'getfields'){
			comboPreLoader(grid,rowIndex,formAction,preload);
		}
		return false;
	}
	if(formAction != 'new'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	cmsWindow.show();
}

function pmsCreateWindow(grid, rowIndex, formAction, activeMainPanelGrid, preload) {
	globalEntityName = pmsCurrentEntity;
	var winHeight    = getWindowHeight();
	pmsErrorpanel = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var formItem = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		width : 700,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [ Ext.JSON.decode(pmsCurrentEntity + "Form") ],
		scope : Ext.JSON.decode(pmsCurrentEntity + "Form")
	});
	
	 if(pmsCurrentEntity == "Media"){
		 formItem.setActiveTab(1);
	 	 var npiTab = formItem.getActiveTab();
	  	 npiTab.setDisabled(true);
	  	 formItem.setActiveTab(0);
	 }

	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;
		var pmsWindow = new Ext.Window({
			//closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 715,
			items : [ formItem,pmsErrorpanel ],
			buttons : [ {
				text : 'Save',
				id:'pms-save-btn',
				scope : Ext.JSON.decode(pmsCurrentEntity + "Form"),
				formBind:true,
				handler : function() {  
					//loadFormsArray(pmsCurrentEntity);
					try{
						saveEntity(activeMainPanelGrid,pmsWindow,pmsCurrentEntity,formAction,pmsErrorpanel);
					}
					catch(error){
						Ext.MessageBox.hide();
					}
				}
			},{
				text: 'Cancel',
				handler:function(){
   				  pmsWindow.close();
				}

			}
			]
		});
	
	if (pmsCurrentEntity == "PageAssociator") { 
	    pmsWindow.closeAction = 'hide';
	}
	
	pmsWindow.on('close',function(){
	  hideHtmlEditor();
	  aForms = new Array(); 
	});
	pmsWindow.setTitle(wintitle);
	if(pmsCurrentEntity == "PageAssociator"){
		Ext.getCmp('pms-save-btn').setDisabled(true);
	}
	else{
		Ext.getCmp('pms-save-btn').setDisabled(false);
	}
	loadFormsArray(pmsCurrentEntity);
	if(preload){
		if(preload){
			if(grid != 'getfields'){
				comboPreLoader(grid,rowIndex,formAction,preload);
			}
			return false;
		}
	}
	if(formAction != 'new' && pmsCurrentEntity != 'PageAssociator'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	pmsWindow.show();
}

function userCreateWindow(grid, rowIndex, formAction, activeMainPanelGrid, preload) {
        var userErrorpanel = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var winHeight    = getWindowHeight();
	var formItem = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		width : 700,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [ Ext.JSON.decode(userCurrentEntity + "Form") ],
		scope : Ext.JSON.decode(userCurrentEntity + "Form")
	});

	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;

	if (!userWindow) { 
		var userWindow = new Ext.Window({
			//closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 715,
			items : [ formItem,userErrorpanel ],
			buttons : [ {
				text : 'Save',
				scope : Ext.JSON.decode(userCurrentEntity + "Form"),
				formBind:true,
				handler : function() {  
					saveEntity(activeMainPanelGrid,userWindow,userCurrentEntity,formAction,userErrorpanel);
				}
			},{
				text: 'Cancel',
				handler:function(){
					userWindow.close();
				}

			}
			]
		});
	}
	userWindow.on('close',function(){
	  hideHtmlEditor();
	  aForms = new Array(); 
	});
	userWindow.setTitle(wintitle);
	loadFormsArray(userCurrentEntity);
	if(preload){
		if(grid != 'getfields'){
			comboPreLoader(grid,rowIndex,formAction,preload);
		}
		return false;
	}
	if(formAction != 'new'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	userWindow.show();
}

function feedCreateWindow(grid, rowIndex, formAction, activeMainPanelGrid, preload) {
	var feedErrorpanel = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var winHeight      = getWindowHeight();
	var formItem = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		width : 700,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [ Ext.JSON.decode(feedCurrentEntity + "Form") ],
		scope : Ext.JSON.decode(feedCurrentEntity + "Form")
	});

	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;

	if (!feedWindow) { 
		var feedWindow = new Ext.Window({
			//closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 715,
			items : [ formItem,feedErrorpanel ],
			buttons : [ {
				text : 'Save',
				scope : Ext.JSON.decode(feedCurrentEntity + "Form"),
				formBind:true,
				handler : function() {  
					saveEntity(activeMainPanelGrid,feedWindow,feedCurrentEntity,formAction,feedErrorpanel);
				}
			},{
				text: 'Cancel',
				handler:function(){
					feedWindow.close();
				}

			}
			]
		});
	}
	feedWindow.on('close',function(){
	  hideHtmlEditor();
	  aForms = new Array(); 
	}); 
	feedWindow.setTitle(wintitle);
	loadFormsArray(feedCurrentEntity);
	if(preload){
		if(grid != 'getfields'){
			comboPreLoader(grid,rowIndex,formAction,preload);
		}
		return false;
	}
	if(formAction != 'new'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	feedWindow.show();
} 

function adminCreateWindow(grid, rowIndex, formAction, activeMainPanelGrid, preload) {
	globalEntityName = adminCurrentEntity;
	adminErrorpanel  = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var winHeight    = getWindowHeight();
	var formItem = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		width : 700,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [ Ext.JSON.decode(adminCurrentEntity + "Form") ],
		scope : Ext.JSON.decode(adminCurrentEntity + "Form")
	});

	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;

	if (!adminWindow) { 
		var adminWindow = new Ext.Window({
			//closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 715,
			items : [ formItem,adminErrorpanel ],
			buttons : [ {
				text : 'Save',
				scope : Ext.JSON.decode(adminCurrentEntity + "Form"),
				formBind:true,
				handler : function() {  
					saveEntity(activeMainPanelGrid,adminWindow,adminCurrentEntity,formAction,adminErrorpanel);
				}
			},{
				text: 'Cancel',
				handler:function(){
					adminWindow.close();
				}

			}
			]
		});
	}
    adminWindow.on('close',function(){
          hideHtmlEditor(); 
	  aForms = new Array(); 
	}); 
	adminWindow.setTitle(wintitle);
	loadFormsArray(adminCurrentEntity);
	if(preload){
		if(grid != 'getfields'){
			comboPreLoader(grid,rowIndex,formAction,preload);
		}
		return false;
	}
	if(formAction != 'new'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	adminWindow.show();
} 

function domainCreateWindow(grid, rowIndex, formAction, activeMainPanelGrid, preload) {
	var domainErrorpanel = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var winHeight    = getWindowHeight();
	var formItem = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		width : 700,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [ Ext.JSON.decode(domainCurrentEntity + "Form") ],
		scope : Ext.JSON.decode(domainCurrentEntity + "Form")
	});

	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;

	if (!domainWindow) { 
		var domainWindow = new Ext.Window({
			//closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 715,
			items : [ formItem,domainErrorpanel ],
			buttons : [ {
				text : 'Save',
				scope : Ext.JSON.decode(domainCurrentEntity + "Form"),
				formBind:true,
				handler : function() {  
					saveEntity(activeMainPanelGrid,domainWindow,domainCurrentEntity,formAction,domainErrorpanel);
				}
			},{
				text: 'Cancel',
				handler:function(){
				  domainWindow.close();
				}

			}
			]
		});
	}
	domainWindow.on('close',function(){
	  hideHtmlEditor();
	  aForms = new Array(); 
	});  
	domainWindow.setTitle(wintitle);
	loadFormsArray(domainCurrentEntity);
	if(preload){
		if(grid != 'getfields'){
			comboPreLoader(grid,rowIndex,formAction,preload);
		}
		return false;
	}
	if(formAction != 'new'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	domainWindow.show()
}

function workflowCreateWindow(grid, rowIndex, formAction, activeMainPanel, preload) {
	var workflowErrorpanel = Ext.create("Ext.panel.Panel", {title:'Error Info',region:'south',height:100,collapsed :true,collapsible:true, autoScroll:true}); 
	var winHeight    = getWindowHeight();
	var formItem = Ext.create("Ext.tab.Panel", {
		activeTab : 0,
		region:'center',
		autoScroll : true,
		width : 770,
		height : 450,
		defaults : {
			bodyStyle : 'padding:10px'
		},
		items : [Ext.JSON.decode(workflowCurrentEntity + "Form")],
		scope : Ext.JSON.decode(workflowCurrentEntity + "Form")
	});
	var wintitle = formAction == "edit" ? 'Edit': 'Add New' ;
	var validatingForms  =  new Array();
	workflowForms        =  new Array();
	if(workflowCurrentEntity=="Workflow"){
		WorkflowEventsGrid.store.removeAll();
		if(formAction != 'new'){
			var record  = grid.getStore().getAt(rowIndex);
			//WorkflowProperties = record.data.Properties;
			if(record.data.WorkflowEventUserMaps != ''){
			    var workflowEventUserMaps = Ext.JSON.decode(record.data.WorkflowEventUserMaps);
			    if(Ext.isArray(workflowEventUserMaps.WorkflowEventUserMap)){
			            var eventUserMapRecord = WorkflowFlattenData(workflowEventUserMaps.WorkflowEventUserMap);
				    WorkflowEventsGrid.store.loadData(eventUserMapRecord);
			    }else{  
			            var eventUserMapRecord = WorkflowFlattenData(new Array(workflowEventUserMaps.WorkflowEventUserMap));
				    WorkflowEventsGrid.store.loadData(eventUserMapRecord);
			    }
			}
			
			var workflowModelMapEditProperties = Ext.JSON.decode(record.data.Properties);
			loadPropertyForm(workflowModelMapEditProperties.Property,'edit');
		}
		else{
			WorkflowPanel1.getForm().reset();
			//Ext.getCmp('WorkflowWorkflowModelMapName').setValue('');
			Ext.getCmp('WorkflowCreatedDate').setValue(new Date());
			Ext.getCmp('WorkflowCreatedBy').setValue(cmsUserName);
			Ext.getCmp('WorkflowLastModifiedDate').setValue(new Date());
			Ext.getCmp('WorkflowLastModifiedBy').setValue(cmsUserName);
                   //     WorkflowPanel2.removeAll();  // optional
		}
	}
	if(!workflowWindow) { 
		var workflowWindow = new Ext.Window({
			closeAction : 'hide',
			autoScroll : true,
			title:wintitle,
			modal:true,
			layout: 'border',
			height : winHeight,
			width : 785,
			items : [ formItem,workflowErrorpanel ],
			buttons : [ {
				text : 'Save',
				scope : Ext.JSON.decode(workflowCurrentEntity + "Form"),
				formBind:true,
				handler : function() {  
					loadFormsArray(workflowCurrentEntity);
				// WorkflowEventName
					if(workflowCurrentEntity=="Workflow"){  
						if(!validateStore(WorkflowEventsGrid.store,workflowCurrentEntity) || Ext.getCmp('WorkflowKey').getValue() == '' || !WorkflowPanel2.getForm().isValid()){
						       Ext.getCmp('WorkflowKey').markInvalid();	
						       Ext.example.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Required field missing OR Invalid data</div>");
						       return false;
						} 
					}
					
					if(!validateForm()){
						Ext.example.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Required field missing OR Invalid data</div>");
						return false;
					}
					
					Ext.MessageBox.wait('Saving Data In Progress. Please Wait ...');
					Ext.Ajax.request({
						method:"POST",
		       				url: dataservicesPath+'/'+workflowCurrentEntity.toLowerCase(),  
						defaultHeaders : { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}, 
						success: function(response, opts){
							var actionObj = Ext.JSON.decode(response.responseText);
							if(actionObj.success == "false"){
								Ext.MessageBox.hide();
								workflowErrorpanel.expand(false);
								if(actionObj.msg.ValidationError){
									workflowErrorpanel.update("<div class = 'errorHeaderMsg'>" + actionObj.msg.ValidationError + "</div>");
								}
								else{
									if(aForms[0] && aForms[0].findField('FilePath')){
										aForms[0].findField('FilePath').setValue('');
									}
									if(actionObj.msg){
										workflowErrorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
									}else{
										workflowErrorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(response.responseText)+ "</div>");
									}
								}
								return false;
							}
							else{
							        Ext.MessageBox.hide(); 
								Ext.example.msg("<div class = 'successHeaderMsg'>Success!</div>", "<div class = 'successMsg'>Saved Successfully.</div>");
								if(formAction=="new"){
									activeMainPanel.getStore().load();
								}
								else{
									grid.getStore().load(); 
								}
								aForms = new Array();
								workflowWindow.close();
							} 
						},
						failure: function(form, action) {
							Ext.MessageBox.hide(); 
							var actionString = action.response.responseText;
							var actionObj = Ext.JSON.decode(actionString);
							if(!Ext.isEmpty(actionString)){
							  workflowErrorpanel.expand(false);
							  if(typeof(actionObj.msg) != "undefined"){
								  workflowErrorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
							  }
							  else{
								  workflowErrorpanel.update("<div class = 'errorHeaderMsg'>" + action.response.responseText + "</div>");
							  }
							}
						},
		                                params: getFinalFormParams(workflowCurrentEntity,formAction)
						//waitMsg: 'Saving Data In Progress. Please Wait ...' 
					});  
				}
			},{
				text: 'Cancel',
				handler:function(){
				  workflowWindow.close();
				}

			}
			]
		});
	}
	workflowWindow.setTitle(wintitle);
	workflowWindow.on('close',function(){
	    hideHtmlEditor();
	    aForms = new Array();
	  // workflowPanel2.removeAll();
	    WorkflowPanel2.removeAll();
	    workflowForms = new Array(); 
	    WorkflowPanel1.getForm().reset();
            Ext.getCmp('WorkflowKey').setValue('');
	    Ext.getCmp('WorkflowEventName').setValue('');
	});
	loadFormsArray(workflowCurrentEntity);
	if(preload){
		if(grid != 'getfields'){
			comboPreLoader(grid,rowIndex,formAction,preload);
		}
		return false;
	}
	if(formAction != 'new'){
		comboPreLoader(grid,rowIndex,formAction,preload);
	}
	workflowWindow.show();
}

function createWindow(grid, rowIndex, edit, action , preload){
	var activemainPanelGrid = Ext.getCmp(globalActiveTab + '-main-grid');
	if(globalActiveTab == 'cms'){
		cmsCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
	else if(globalActiveTab == 'pms'){
		pmsCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
	else if(globalActiveTab == 'admin'){
		adminCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
	else if(globalActiveTab == 'domain'){
		domainCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
	else if(globalActiveTab == 'workflow'){
		workflowCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
	else if(globalActiveTab == 'feed'){
		feedCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
	else if(globalActiveTab == 'user'){
		userCreateWindow(grid, rowIndex, edit,activemainPanelGrid,preload);
	}
}

 function hideHtmlEditor(){
    for(var i = 0; i < aForms.length; i++){
      if(!Ext.isEmpty(aForms[i])){
	var collections = aForms[i].getFields();
	var count = collections.getCount();
	for(var j = 0; j < count; j++){
	    var field = collections.getAt(j);
	    if(field.getXType() == 'htmleditor'){
		field.setVisible(false);
	    }
	}
       }
    }
 } 

//http://124.7.228.161/ncmsui/extjs/js/global-extjs-filterclick-fns.js?_dc=3002

function showFilterByMenu(tab){
	var menuHandler = tab + "MenuClickHandler";
	if(typeof filterByMenu == 'function') {
		return filterByMenu(menuHandler);
	}
	else{
		return filterByMenuCommon(menuHandler);
	}
}

function feedMenuClickHandler(item){
	aclQuery = getAclQuery(item,feedCurrentEntity);
	var grid = Ext.getCmp("feed-main-grid");
	grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+aclQuery+'&entitytype='+feedCurrentEntity;
	grid.store.proxy.setReader(
			{
				type: 'json',
				root: 'Collections.'+feedCurrentEntity,
				totalProperty: 'Collections.Count' 
			}
	);
	grid.store.load(); 
}

function cmsMenuClickHandler(item){
	aclQuery = getAclQuery(item,cmsCurrentEntity);
	var grid = Ext.getCmp("cms-main-grid");
	grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+isCmsFile+aclQuery+'&entitytype='+cmsCurrentEntity;
	grid.store.proxy.setReader(
			{
				type: 'json',
				root: 'Collections.'+cmsCurrentEntity,
				totalProperty: 'Collections.Count' 
			}
	);
	grid.store.load(); 
}

function userMenuClickHandler(item){
	aclQuery = getAclQuery(item,userCurrentEntity);
	var grid = Ext.getCmp("user-main-grid");
	grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+aclQuery+'&entitytype='+userCurrentEntity;
	grid.store.proxy.setReader(
			{
				type: 'json',
				root: 'Collections.'+userCurrentEntity,
				totalProperty: 'Collections.Count' 
			}
	);
	grid.store.load(); 
}
function adminMenuClickHandler(item){
	aclQuery = getAclQuery(item,adminCurrentEntity);
	var grid = Ext.getCmp("admin-main-grid");
	grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+aclQuery+'&entitytype='+adminCurrentEntity;
	grid.store.proxy.setReader(
			{
				type: 'json',
				root: 'Collections.'+adminCurrentEntity,
				totalProperty: 'Collections.Count' 
			}
	);
	grid.store.load(); 
}

function domainMenuClickHandler(item){
	aclQuery = getAclQuery(item,domainCurrentEntity);
	var grid = Ext.getCmp("domain-main-grid");
	grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+aclQuery+'&entitytype='+domainCurrentEntity;
	grid.store.proxy.setReader(
			{
				type: 'json',
				root: 'Collections.'+domainCurrentEntity,
				totalProperty: 'Collections.Count' 
			}
	); 
	grid.store.load(); 
}

function pmsMenuClickHandler(item){
	aclQuery = getAclQuery(item,pmsCurrentEntity);
	var grid = Ext.getCmp("pms-main-grid");
	grid.store.proxy.url= dataservicesPath + '/search?q=&format=extjson'+isPmsFile+aclQuery+'&entitytype='+pmsCurrentEntity;
	grid.store.proxy.setReader(
			{
				type: 'json',
				root: 'Collections.'+pmsCurrentEntity,
				totalProperty: 'Collections.Count' 
			}
	);
	grid.store.load(); 
}

//http://124.7.228.161/ncmsui/extjs/js/global-extjs-wfmenuclick-fns.js?_dc=3002

function checkNpiMetaDataFilled(record){
  if(Ext.Array.contains(metaDataInfoList,record.data.EntityType)){
	  if(Ext.isEmpty(record.get('TitleMain'))){
	     return false;
	  }
  }
  return true;
}

var lastItemClicked = new Array();

function sendWorkflowActionRequest(item,postData,wfEntityName,grid){
	if(postData['Id'] == lastItemClicked[0] && postData["CurrentEvent"] != lastItemClicked[2]){
		Ext.example.msg("<div class = 'errorHeaderMsg'>Request in progress</div>","<div class = 'errorMsg'> Please wait ... Processing previous request.</div>");
		return false;
	}
	postData["CurrentEvent"] = item.text;
	var wfCommentsText = "";
	wfCommentsText += postData["WorkflowComments"];
	
	var commentsWindow = Ext.create('Ext.window.Window', {
	    title: 'Workflow Comments',
	    height: 275,
	    modal:true,
	    width: 500,
	    items: [{  
			        xtype: 'panel',
			        border: false,
			        html:wfCommentsText,
			        autoScroll:true,
			        bodyStyle:{"background-color":"#F5F5F5"},
			        height:100
			    },
			    {
			    	xtype : 'label',
			    	fieldLabel:'&nbsp;',
			    },
			    {
			        xtype: 'textarea',
			        id:'wf-comment-text',
			        fieldLabel:'Enter your comments',
			        height:110,
			        width:482
			    }
	    ],
	    buttons : [ {
			text : 'OK',
			handler : function() {
				var text = Ext.getCmp('wf-comment-text').getValue();
				if(!Ext.isEmpty(text)){
				    postData["WorkflowComments"] += 
				      cmsUserName +
				      ' [' + postData["CurrentEvent"] + '] :' + 
				      text + "</br>";
				}
		    	Ext.MessageBox.wait('Saving Data In Progress. Please Wait ...');
		    	Ext.Ajax.request({
		    		method : 'POST',
		    		url: dataservicesPath+'/'+wfEntityName.toLowerCase(),    
				scope:commentsWindow,
		    		defaultHeaders : { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}, 
		    		success : function(response, opts) { 
		    			Ext.MessageBox.hide();
		    			var resp = Ext.JSON.decode(response.responseText);
		    			if(resp.success){
		    				lastItemClicked[0] = postData['Id'];
		    				lastItemClicked[1] = postData['EntityType'];
		    				lastItemClicked[2] = postData['CurrentEvent'];
		    				Ext.example.msg("<div class = 'successHeaderMsg'>Success</div>", "<div class = 'successMsg'>Saved Successfully.</div>");
		    				grid.store.load();
		    			}
		    			commentsWindow.close();
		    		},
		    		failure: function( result, request ) {
		    			Ext.MessageBox.hide();
		    			var actionString = result.responseText;
		    			if(actionString != ''){
		    				var actionObj = Ext.JSON.decode(actionString);
		    				if(!Ext.isEmpty(actionObj.msg)){
		    					Ext.example.msg("<div class = 'errorHeaderMsg'>Request Failed</div>","<div class = 'errorMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
		    				}
		    			}
		    			else{
		    				Ext.example.msg("<div class = 'errorHeaderMsg'>Request Failed</div>",
		    						"<div class = 'errorHeaderMsg'>Request Timedout Or No Response From Server </div>");
		    			}
		    			commentsWindow.close();
		    		},
		    		params : postData
		    	});
			}
		},
		{
			text: 'Cancel',
			handler:function(){
				commentsWindow.close();
			}
		}
		
		]
	}).show();
	
	return false;
	
	/*Ext.Msg.prompt('Workflow Comments', wfCommentsText, 
			function(btn, text){
	    if (btn == 'ok'){
	    	postData["WorkflowComments"] += 
	    				cmsUserName +
	    				' [' + postData["CurrentEvent"] + '] :' + 
	    				text + "</br>";
	        // process text value and close...
	    	alert(postData["WorkflowComments"]);
	    	Ext.MessageBox.wait('Saving Data In Progress. Please Wait ...');
	    	Ext.Ajax.request({
	    		method : 'POST',
	    		url: dataservicesPath+'/'+wfEntityName.toLowerCase(),    
	    		defaultHeaders : { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}, 
	    		success : function(response, opts) { 
	    			Ext.MessageBox.hide();
	    			var resp = Ext.JSON.decode(response.responseText);
	    			if(resp.success){
	    				lastItemClicked[0] = postData['Id'];
	    				lastItemClicked[1] = postData['EntityType'];
	    				lastItemClicked[2] = postData['CurrentEvent'];
	    				Ext.example.msg("<div class = 'successHeaderMsg'>Success</div>", "<div class = 'successMsg'>Saved Successfully.</div>");
	    				grid.store.load();
	    			}
	    		},
	    		failure: function( result, request ) {
	    			Ext.MessageBox.hide();
	    			var actionString = result.responseText;
	    			if(actionString != ''){
	    				var actionObj = Ext.JSON.decode(actionString);
	    				if(!Ext.isEmpty(actionObj.msg)){
	    					Ext.example.msg("<div class = 'errorHeaderMsg'>Request Failed</div>","<div class = 'errorMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
	    				}
	    			}
	    			else{
	    				Ext.example.msg("<div class = 'errorHeaderMsg'>Request Failed</div>",
	    						"<div class = 'errorHeaderMsg'>Request Timedout Or No Response From Server </div>");
	    			}
	    		},
	    		params : postData
	    	});
	    }
	},this,true);
	*/
}

function cmsWorkflowmenuhandler(item){ 
	var grid   = Ext.getCmp("cms-main-grid");
	var rec    = grid.getSelectionModel().getSelection()[0];
	if(!checkNpiMetaDataFilled(rec)){
	  Ext.example.msg("<div class = 'errorMsg'>Validation Error</div>", "<div class = 'errorMsg'>NPI Metadata not filled</div>");
	  return false;
	}
	newRec     = objClone(rec);
	var postData = {};
	cmsCreateWindow("getfields", "", "new",this,true);
	var entityFields = getFinalFormParams(cmsCurrentEntity,'edit',true);
	for (var property in entityFields){
		postData[property] = newRec.data[property];
		if(property == 'RelatedItems'){
			var items = {};
			items.RelatedItems = "";
			if(!Ext.isEmpty(newRec.data[property])){
				items.RelatedItems = Ext.JSON.decode(newRec.data[property]);
			}
			postData[property] = Ext.JSON.encode(items);
		}
		if(property == 'DependentItems'){
			var items = {};
			items.DependentItems = "";
			if(!Ext.isEmpty(newRec.data[property])){
				items.DependentItems = Ext.JSON.decode(newRec.data[property]);
			}
			postData[property] = Ext.JSON.encode(items);
		}
	}
	sendWorkflowActionRequest(item,postData,cmsCurrentEntity,grid);
	return false;
}

function feedWorkflowmenuhandler(item){ 
	var grid   = Ext.getCmp("feed-main-grid");
	var rec    = grid.getSelectionModel().getSelection()[0];
	newRec     = objClone(rec);
	var postData = {};
	feedCreateWindow("getfields", "", "new",this,true);
	var entityFields = getFinalFormParams(feedCurrentEntity,'edit',true);
	for (var property in entityFields){
		postData[property] = newRec.data[property];
	}
	sendWorkflowActionRequest(item,postData,feedCurrentEntity,grid);
}

function userWorkflowmenuhandler(item){ 
	var grid   = Ext.getCmp("user-main-grid");
	var rec    = grid.getSelectionModel().getSelection()[0];
	newRec     = objClone(rec);
	var postData = {};
	userCreateWindow("getfields", "", "new",this,true);
	var entityFields = getFinalFormParams(userCurrentEntity,'edit',true);
	for (var property in entityFields){
		postData[property] = newRec.data[property];
	}
	sendWorkflowActionRequest(item,postData,userCurrentEntity,grid);
}

function domainWorkflowmenuhandler(item){ 
	var grid   = Ext.getCmp("domain-main-grid");
	var rec    = grid.getSelectionModel().getSelection()[0];
	newRec     = objClone(rec);
	var postData = {};
	domainCreateWindow("getfields", "", "new",this,true);
	var entityFields = getFinalFormParams(domainCurrentEntity,'edit',true);
	for (var property in entityFields){
		postData[property] = newRec.data[property];
	}
	sendWorkflowActionRequest(item,postData,domainCurrentEntity,grid);
}

function pmsWorkflowmenuhandler(item) {
	var grid   =  Ext.getCmp("pms-main-grid");
	var rec    = grid.getSelectionModel().getSelection()[0];
	newRec     = objClone(rec);
	var postData = {};
	pmsCreateWindow("getfields", "", "new",this,true);
	var entityFields = getFinalFormParams(pmsCurrentEntity,'edit',true);
	for (var property in entityFields){
		postData[property] = newRec.data[property];
	}
	sendWorkflowActionRequest(item,postData,pmsCurrentEntity,grid);
}


//http://124.7.228.161/ncmsui/extjs/js/global-extjs-fns.js?_dc=3002

function getAclQuery(item,cEntity){
    var activeItem = item.group;
    var aclQuery   = "";
    if(activeItem == "createdby")
      aclQuery = aclSubQuery;
    else if(activeItem == "user")
      aclQuery = "&"+activeItem+"=" + cmsUserName + aclStatus;
    else if(activeItem == "status")
      aclQuery = "&"+activeItem+"=" + "published";
    else if(cEntity== "InformationArchitecture" && activeItem=="associatedianame")
      aclQuery = "&parentianame="+item.text+getFilterBySubQuery(cEntity);
    else
      aclQuery = "&"+activeItem+"="+item.text+getFilterBySubQuery(cEntity);
     
    return aclQuery;
}  

function objClone(obj){
	if(obj == null || typeof(obj) != 'object')
	   return obj;
	var temp = {}; // changed
	for (key in obj) {
		temp[key] = obj[key];
	}
	return temp;
} 

function contextMenu(record,currentTab){
	var possibleStates = [];
	var disabledFlag = true;
	var states = new Array();
	if((record.data.User.toLowerCase() == cmsUserName.toLowerCase() || isSuperAdmin()) && record.data.NextPossibleEvent){  
		var states = record.data.NextPossibleEvent.split(",");
		if(typeof states == 'string'){
			states = new Array(states);
		}
		for(var i = 0; i < states.length; i++){
			possibleStates[i] = Ext.decode('{text:"'+states[i]+'",checked: false,group: "theme",handler: '+currentTab+'Workflowmenuhandler}');
		}
		disabledFlag = false;
	}
	
	var ctextMenu  = Ext.create('Ext.menu.Menu', {
	    items:[
		    {
			text:"Workflow Actions",
			menu :{items:[possibleStates]},
			disabled:disabledFlag
			
		    },
		    {
				text:"Workflow Details",
				handler :function(){
					getWorkflowDetails(record);
				}
		    }
		  ],
		  scope :this 
	});
	return ctextMenu;
}

function getWorkflowDetails(record){
  var wfId 	= 'llkpE6ajbeidc';  //record.data.WorkflowId;
  var wfName 	= 'Test Workflow'; //record.data.WorkflowName;

  if(!Ext.isEmpty(wfId)){
      Ext.Ajax.request({
		      method : 'GET',
		      url: dataservicesPath+'/workflow/search?q=&format=extjson&id=' + wfId,  
		      method:"GET",
		      success: function(response){
			      var collectionsObj = Ext.JSON.decode(response.responseText);
			      if(collectionsObj.Collections.Count == 1){
				  var message = '<b>Workflow Name </b>   - ' + wfName + '</br>';
				  message += '<b>Workflow Stages </b> ';
				  var wfEvents = Ext.JSON.decode(collectionsObj.Collections.Workflow.WorkflowEvents);
				  for(var i = 0; i < wfEvents.WorkflowEvent.length; i++ ){
				    if(record.data.Stage == wfEvents.WorkflowEvent[i].AssociatedStage){
				      message += ' - <font color=green><b>' + wfEvents.WorkflowEvent[i].AssociatedStage + '</b></font>';
				    }
				    else{
				      message += ' - ' + wfEvents.WorkflowEvent[i].AssociatedStage;
				    }
				  }
				      Ext.Msg.show({
					  title:'Workflow Details',
					  msg: message,
					  height: 500,
					  width: 400,
					  buttons: Ext.Msg.OK,
					  icon: Ext.Msg.INFO
				      });
			      }
			      else{
				      return false;
			      }
		      },
		      failure: function(form, action) {
			      Ext.MessageBox.hide(); 
		      },
		      waitMsg: 'Loading Metadata. Please Wait ...'
	      });
  }
  
}


function itemsGridPreLoader(record){

	var entityName = record.data.EntityType;
	var form3 = Ext.getCmp(entityName+ 'Form3');
	var form4 = Ext.getCmp(entityName+ 'Form4');
	var dependentView = Ext.getCmp('dependent-images-view');
	var relatedView = Ext.getCmp('related-images-view'); 
	if (typeof (form3) != "undefined") { 
		dependentView.getStore().removeAll();
		if(record.data.DependentItems){
			var depItemRecords = Ext.JSON.decode(record.data.DependentItems); 
			if(depItemRecords.DependentItem){
				if(Ext.isArray(depItemRecords.DependentItem)){
					dependentView.store.loadData(depItemRecords.DependentItem);
				}else{
					dependentView.store.loadData(new Array(depItemRecords.DependentItem));
				}
			}
		}
	}

	if (typeof (form4) != "undefined") {
		relatedView.getStore().removeAll();
		if(record.data.RelatedItems){
			var relatedItemRecords = Ext.JSON.decode(record.data.RelatedItems);
			if(relatedItemRecords.RelatedItem){
				if(Ext.isArray(relatedItemRecords.RelatedItem)){
					relatedView.store.loadData(relatedItemRecords.RelatedItem);	
				}else{
					relatedView.store.loadData(new Array(relatedItemRecords.RelatedItem));	
				}	    
			}
		}
	}
}	

function comboPreLoader(grid,rowIndex,action,preload){
  	var dependentView = Ext.getCmp('dependent-images-view');
	var relatedView = Ext.getCmp('related-images-view'); 
	var j = 1; var record;
	if(preload){
		aForms = new Array();
		dependentView.store.removeAll();
		relatedView.store.removeAll();
		return false;
	}
	record = grid.getStore().getAt(rowIndex);
	itemsGridPreLoader(record);

	//while(j < aForms.length){
	while(j < 8){
        if(j != 3 && j != 4){
	var formPanel  = Ext.getCmp(record.data.EntityType+ 'Form'+j); 
        if(!Ext.isEmpty(formPanel)){
          var form = formPanel.getForm();
	  if(!Ext.isEmpty(form)){
		//console.log("form length :" + aForms.length);
		var formCombo = form;
		if((rowIndex) || (rowIndex=="0") && !preload){
			var record = grid.getStore().getAt(rowIndex);
		}
		var collections = formCombo.getFields();
		var count = collections.getCount();
		for(var i = 0; i < count; i++){
			var field = collections.getAt(i);
			if(field.getXType() == 'htmleditor' && !preload){
				field.setValue(record.data[field.getName()]);
			}
			if(field.getXType() == 'filefield'){
				field.setRawValue(record.data[field.getName()]);
			}
			else if(field.getXType() == 'combobox'){
				if(field.multiSelect){
					/*
					 * field.store.on('load',function(){
					 * field.setValue(record.data[field.getName()].split(','));
					 * },this);
					 */
					if(preload){
						//field.store.load();  
					}
					else{
						//console.log(field.getName() + ":" + field.getXType() + ":" + record.data[field.getName()] + ": SET VALUE");
						var fieldValue = record.data[field.getName()].split(',');
						if(!Ext.isEmpty(fieldValue)){
							field.setValue(fieldValue);
						}
					}
				}
				else{
					if(!preload){
						field.setValue(record.data[field.getName()]);
					}
				}
			}
			else{  
				if(action != "new" && !preload){
					if(field.getName() == 'UserName'){
					  field.readOnly=true;
					}
					field.setValue(record.data[field.getName()]);
				}
			}
		}
	  }
          }
	}
	  j++;
    }
	/*if(aForms[1]){
		var npiEditData = npiFormHandler(action,record.data.Id);
	}*/
}

function loadFormsArray(entityName){
	var form1 = Ext.getCmp(entityName+ 'Form1');
	var form2 = Ext.getCmp(entityName+ 'Form2');
        var form5 = Ext.getCmp(entityName+ 'Form5');
        var form6 = Ext.getCmp(entityName+ 'Form6');
        var form7 = Ext.getCmp(entityName+ 'Form7');
	
	if (!Ext.isEmpty(form1)) { 
		aForms[0] = form1.getForm();
	}
	
	if (!Ext.isEmpty(form2)) { 
		 aForms[1] = form2.getForm(); 
	}

	if (!Ext.isEmpty(form5)) { 
		 aForms[2] = form5.getForm(); 
	}
	if (!Ext.isEmpty(form6)) { 
		 aForms[3] = form6.getForm(); 
	}
	if (!Ext.isEmpty(form7)) { 
		 aForms[4] = form7.getForm(); 
	}
}

function validateForm(){
	for(var i = 0; i < aForms.length; i++){
if(!Ext.isEmpty(aForms[i])){
		if(!aForms[i].isValid()){
			if(i == 1){ 
                               if(aForms[i].findField('TitleMain') != null){
				if(aForms[i].findField('TitleMain').getValue() != ''){
					return false; 
				} 
                               }
				return true; 
			}
			return false;
		}
	}
}
	return true;
}


function getFinalFormParams(currentEntity,action,fieldsOnly){
	var form1data = {};
	var form1 = Ext.getCmp(currentEntity+ 'Form1');
	var form2 = Ext.getCmp(currentEntity+ 'Form2');
	var form3 = Ext.getCmp(currentEntity+ 'Form3');
	var form4 = Ext.getCmp(currentEntity+ 'Form4');
	var form5 = Ext.getCmp(currentEntity+ 'Form5');
	var form6 = Ext.getCmp(currentEntity+ 'Form6');
	var form7 = Ext.getCmp(currentEntity+ 'Form7');
        if(fieldsOnly){
	        if(currentEntity=="PageAssociator"){
	        	form1data = {
			      Site:'',
			      Id:'',
			      EntityType:'PageAssociator',
			      PageEntityName:'',
			      PageName:'',
			      IaName:'',
			      Status:'',
			      CreatedDate:'',
			      CreatedBy:'',
			      LastModifiedDate:'',
			      LastModifiedBy: '',
			      Version:'',
			      User:'',
			      CurrentUser:'',
			      CurrentEvent:'',
			      Stage:''
			 }
			 
	        }
	        else {
		    if(!Ext.isEmpty(form1)){
		      	var fields = form1.getForm().getFields();
			for(var i = 0; i < fields.length; i++){
			  form1data[fields.getAt(i).name] = ''; 
			}		      
		    }
		   
		    if(!Ext.isEmpty(form2)){
		        var fields = form2.getForm().getFields();
			for(var i = 0; i < fields.length; i++){
			  form1data[fields.getAt(i).name] = ''; 
			}		      
		    }
		    if(!Ext.isEmpty(form3)) { 
		      form1data["DependentItems"] = '';
		    }
		    if(!Ext.isEmpty(form4)) { 
		      form1data["RelatedItems"]   = '';
		    }
                    if(!Ext.isEmpty(form5)) { 
                       var form = form5.getForm();
		       var fields = form.getFields();
			for(var i = 0; i < fields.length; i++){
			  form1data[fields.getAt(i).name] = ''; 
			}
		    }

                    if(!Ext.isEmpty(form6)) { 
                       var form = form6.getForm();
		       var fields = form.getFields();
			for(var i = 0; i < fields.length; i++){
			  form1data[fields.getAt(i).name] = ''; 
			}
		    }
                    if(!Ext.isEmpty(form7)) { 
                       var form = form7.getForm();
		       var fields = form.getFields();
			for(var i = 0; i < fields.length; i++){
			  form1data[fields.getAt(i).name] = ''; 
			}
		    }
		}
		form1data.EntityType = currentEntity;
		form1data.CreatedBy = cmsUserName;
            return form1data;
	}
        form1data = aForms[0].getValues();		  
	if(typeof (form3) != "undefined") { 
		var depStore = Ext.data.StoreManager.lookup('itemDependentViewStore'); 
		form1data["DependentItems"] = getJsonOfStore('DependentItems','DependentItem',depStore);
	}
	if(typeof (form4) != "undefined") { 
		var relatedStore = Ext.data.StoreManager.lookup('itemRelatedViewStore'); 
		form1data["RelatedItems"]   = getJsonOfStore('RelatedItems','RelatedItem',relatedStore);
	}
	if(currentEntity=="Workflow"){
		var wfEvent1   = new Array();
		var grid       =  WorkflowEventsGrid;
		for(var i = 0; i < grid.getStore().getCount(); i++) 
		{
		    var record = grid.getStore().getAt(i);
		    record.commit();
		    wfEvent1[i] = stringToUsers(record.data);
		}
		winEvents = {WorkflowEventUserMaps:{ WorkflowEventUserMap:wfEvent1}};
		form1data["WorkflowEventUserMaps"] = Ext.JSON.encode(winEvents);
		var wfmodelArr = "";
		var workFlowData             =  WorkflowPanel2.getValues();
		var workFlowKeys             =  Ext.isArray(workFlowData.Key)?workFlowData.Key: new Array(workFlowData.Key);
		var workFlowDepartmentValues =  Ext.isArray(workFlowData.PublisherDepartment)?workFlowData.PublisherDepartment: 
		                                        new  Array(workFlowData.PublisherDepartment);
		var workFlowPublisherOrgNameValues =  Ext.isArray(workFlowData.PublisherOrgName)?workFlowData.PublisherOrgName: new Array(workFlowData.PublisherOrgName);
		
		var workFlowStateValues =  Ext.isArray(workFlowData.State)?workFlowData.State: new Array(workFlowData.State);
		var workFlowDistrictValues =  Ext.isArray(workFlowData.District)?workFlowData.District: new Array(workFlowData.District);
		var workFlowMinistryValues =  Ext.isArray(workFlowData.Ministry)?workFlowData.Ministry: new Array(workFlowData.Ministry);
		var workFlowEntityTypeValues =  Ext.isArray(workFlowData.EntityType)?workFlowData.EntityType: new Array(workFlowData.EntityType);

		for(var i = 0; i < workFlowPublisherOrgNameValues.length; i++){
		    if(i>0){
		       if(workFlowPublisherOrgNameValues[i])
		          wfmodelArr = wfmodelArr+',{"Key":'+'"PublisherOrgName","Value":"'+ workFlowPublisherOrgNameValues[i] +'"}';
		    }
		    else{
		       if(workFlowPublisherOrgNameValues[i])
			  wfmodelArr = wfmodelArr+'{"Key":'+'"PublisherOrgName","Value":"'+ workFlowPublisherOrgNameValues[i] +'"}';
		    }
		}
		
		if(!Ext.isEmpty(wfmodelArr) && workFlowStateValues[0]){
		  wfmodelArr = wfmodelArr + ',';
		}
		
		for(var i = 0; i < workFlowStateValues.length; i++){
		    if(i>0){
		       if(workFlowStateValues[i])
		          wfmodelArr = wfmodelArr+',{"Key":'+'"State","Value":"'+ workFlowStateValues[i] +'"}';
		    }
		    else{
		       if(workFlowStateValues[i])
			  wfmodelArr = wfmodelArr+'{"Key":'+'"State","Value":"'+ workFlowStateValues[i] +'"}';
		    }
		}
		
		if(!Ext.isEmpty(wfmodelArr) && workFlowDistrictValues[0]){
		  wfmodelArr = wfmodelArr + ',';
		}
		
		for(var i = 0; i < workFlowDistrictValues.length; i++){
		    if(i>0){
		       if(workFlowDistrictValues[i])
		          wfmodelArr = wfmodelArr+',{"Key":'+'"District","Value":"'+ workFlowDistrictValues[i] +'"}';
		    }
		    else{
		       if(workFlowDistrictValues[i])
			  wfmodelArr = wfmodelArr+'{"Key":'+'"District","Value":"'+ workFlowDistrictValues[i] +'"}';
		    }
		}
		
		if(!Ext.isEmpty(wfmodelArr) && workFlowMinistryValues[0]){
		  wfmodelArr = wfmodelArr + ',';
		}
		
		for(var i = 0; i < workFlowMinistryValues.length; i++){
		    if(i>0){
		       if(workFlowMinistryValues[i])
		          wfmodelArr = wfmodelArr+',{"Key":'+'"Ministry","Value":"'+ workFlowMinistryValues[i] +'"}';
		    }
		    else{
		       if(workFlowMinistryValues[i])
			  wfmodelArr = wfmodelArr+'{"Key":'+'"Ministry","Value":"'+ workFlowMinistryValues[i] +'"}';
		    }
		}
		
		
		if(!Ext.isEmpty(wfmodelArr) && workFlowEntityTypeValues[0]){
		  wfmodelArr = wfmodelArr + ',';
		}
		
		for(var i = 0; i < workFlowEntityTypeValues.length; i++){
		    if(i>0){
		       if(workFlowEntityTypeValues[i])
		          wfmodelArr = wfmodelArr+',{"Key":'+'"EntityType","Value":"'+ workFlowEntityTypeValues[i] +'"}';
		    }
		    else{
		       if(workFlowEntityTypeValues[i])
			  wfmodelArr = wfmodelArr+'{"Key":'+'"EntityType","Value":"'+ workFlowEntityTypeValues[i] +'"}';
		    }
		}
		
		if(!Ext.isEmpty(wfmodelArr) && workFlowDepartmentValues[0]){
		  wfmodelArr = wfmodelArr + ',';
		}
		
		for(var j = 0; j < workFlowDepartmentValues.length; j++){
		    if(j>0){
		       if(workFlowDepartmentValues[j])
		          wfmodelArr = wfmodelArr+',{"Key":'+'"PublisherDepartment","Value":"'+ workFlowDepartmentValues[j] +'"}';
		    }
		    else{
		       if(workFlowDepartmentValues[j]){
			   wfmodelArr = wfmodelArr+'{"Key":'+'"PublisherDepartment","Value":"'+ workFlowDepartmentValues[j] +'"}';
		       }	  
		    }
		}
		wfmodelArr = '['+wfmodelArr+']';
		form1data["Properties"] = getPropertiesJson(wfmodelArr);		
	}
        if(!Ext.isEmpty(form2)){
             var form = form2.getForm();
             var fields = form.getFields();
		    for(var i = 0; i < fields.length; i++){
		    	if(fields.getAt(i).xtype == 'datetimefield'){
		    		var datevalue = fields.getAt(i).getValue();
		    		if(!Ext.isEmpty(datevalue)){
		    			form1data[fields.getAt(i).name] = 
		    				Ext.util.Format.date(new Date(fields.getAt(i).getValue()),'Y-m-d h:m:s');
		    		}
		    	}
		    	else{
                                    if(Ext.isEmpty(fields.getAt(i).getValue())){
                                       form1data[fields.getAt(i).name] = null;
                                    }else{
				       form1data[fields.getAt(i).name] = fields.getAt(i).getValue();
                                    } 
		    	}
		    }    
        }
  if(!Ext.isEmpty(form5)){
             var form = form5.getForm();
             var fields = form.getFields();
		    for(var i = 0; i < fields.length; i++){
		    	if(fields.getAt(i).xtype == 'datetimefield'){
		    		var datevalue = fields.getAt(i).getValue();
		    		if(!Ext.isEmpty(datevalue)){
		    			form1data[fields.getAt(i).name] = 
		    				Ext.util.Format.date(new Date(fields.getAt(i).getValue()),'Y-m-d h:m:s');
		    		}
		    	}
		    	else{
                                    if(Ext.isEmpty(fields.getAt(i).getValue())){
                                       form1data[fields.getAt(i).name] = null;
                                    }else{
				       form1data[fields.getAt(i).name] = fields.getAt(i).getValue();
                                    } 
		    	}
		    }    
        }
 if(!Ext.isEmpty(form6)){
             var form = form6.getForm();
             var fields = form.getFields();
		    for(var i = 0; i < fields.length; i++){
		    	if(fields.getAt(i).xtype == 'datetimefield'){
		    		var datevalue = fields.getAt(i).getValue();
		    		if(!Ext.isEmpty(datevalue)){
		    			form1data[fields.getAt(i).name] = 
		    				Ext.util.Format.date(new Date(fields.getAt(i).getValue()),'Y-m-d h:m:s');
		    		}
		    	}
		    	else{
                                    if(Ext.isEmpty(fields.getAt(i).getValue())){
                                       form1data[fields.getAt(i).name] = null;
                                    }else{
				       form1data[fields.getAt(i).name] = fields.getAt(i).getValue();
                                    } 
		    	}
		    }    
        }
 if(!Ext.isEmpty(form7)){
             var form = form7.getForm();
             var fields = form.getFields();
		    for(var i = 0; i < fields.length; i++){
		    	if(fields.getAt(i).xtype == 'datetimefield'){
		    		var datevalue = fields.getAt(i).getValue();
		    		if(!Ext.isEmpty(datevalue)){
		    			form1data[fields.getAt(i).name] = 
		    				Ext.util.Format.date(new Date(fields.getAt(i).getValue()),'Y-m-d h:m:s');
		    		}
		    	}
		    	else{
                                    if(Ext.isEmpty(fields.getAt(i).getValue())){
                                       form1data[fields.getAt(i).name] = null;
                                    }else{
				       form1data[fields.getAt(i).name] = fields.getAt(i).getValue();
                                    } 
		    	}
		    }    
        }
	form1data.EntityType = currentEntity;
	form1data.CreatedBy = cmsUserName;
	return form1data;
}

function getContent(){
	return jtext;
}

function getLayoutPath(){
	return layoutTemplatePath;
}

function getStagingSiteUrl(){
 return stagingSiteUrl;
}
function getHtml(){	
	jtext = Ext.getCmp("pmstemplatecontent").getValue();
        jtext = getPagePreview();
	var re = /<script\b[^>]*>([\s\S]*?)<\/script>/gm;
	if(jtext.match(re)){
	    Ext.Msg.confirm('Script', 'Script Tag Found in Body Content. Do you want to proceed? ',
	    function(btn) {
		if (btn == 'Yes' || btn == 'yes') {
		    jtext = jtext.replace(/\<script/g , "_START_SCRIPT_").replace(/<\/script>/g,"_END_SCRIPT_");
		    getWindow();
		}
	    }, this);
	}
	else{
		getWindow();
	}
}

function getWindow(){
	var NewWin=window.open("","NewWin","toolbar=no,status=no,scrollbars=yes,resizable=yes");
	// NewWin.document.write('<link href="/ncmsui/extjs/css/layout.css"
	// rel="stylesheet" type="text/css"></link>');
	// NewWin.document.write('<script type="text/javascript"
	// src="/ncmsui/extjs/js/layoutmaster.js"><\/script>');
	NewWin.document.write('<div style="width:100%;float:left;" id="outerdiv">');
	NewWin.document.write('<div style="width:20%;float:left;">');
	NewWin.document.write('<div style="width:100%;float:left;margin-top:10px;margin-bottom:20px;"><div style="width:100%;float:left;font-weight:bold;font-size:14px;">Widget Listing</div></div>');
	NewWin.document.write('<div id ="gethtmlbut" style="width:100%;float:left">');
	NewWin.document.write('</div>');
	NewWin.document.write("<div style='width:100%;float:left;margin-top:10px;margin-bottom:10px;' id='ghtmlbut'><input type='button' name='ghtml' id='ghtml' value='GetHtml' onClick='javascript: return gethtml();' align='center'></input></div>");
	NewWin.document.write('</div>');
	NewWin.document.write('<div style="width:78%;float:left;margin-left:10px;">');
	NewWin.document.write('<div style="width:100%;float:left;" id="contentouter">'); 
	// NewWin.document.write('<iframe id="layoutiframe" width="100%"
	// height="100%" scrolling="yes"></iframe>');
	NewWin.document.write('</div>');
	NewWin.document.write('</div>');
	NewWin.document.write('</div>');
	NewWin.document.write('<script type="text/javascript">function getScript(jsPath){var headID=document.getElementsByTagName("head")[0]; var jsNode = document.createElement("script"); jsNode.type="text/javascript"; jsNode.src=jsPath; headID.appendChild(jsNode);}getScript("/ncmsui/extjs/js/jquery.min.js");function loadScript(){if(typeof jQuery == "undefined"){window.setTimeout(function(){loadScript();}, 10); return;}else{getScript("/ncmsui/extjs/js/layout.js");}}loadScript();<\/script>');
	return NewWin;	

}
function getPage() {
  var content = getPagePreview();
  var NewWin=window.open("","NewWin","toolbar=no,status=no,scrollbars=yes"); 
  NewWin.document.write(content);
}

function getPagePreview(){
	var ptext = Ext.getCmp("pmstemplatecontent").getValue();
	var newtxt = ptext.replace(/^\s+|\s+$/g, '').replace(/\&lt;/g, "<").replace(/\&gt;/g, ">");
	newtxt = newtxt.replace(/_START_SCRIPT_/g,"<script").replace(/_END_SCRIPT_/g, "<\/script>");
        dom = $(newtxt);	

	dom.filter('script').each(function(i,item){
	  var scriptsrc = $(item).attr("src");
	  if(typeof(scriptsrc) != "undefined" && scriptsrc != ""){
	   if(scriptsrc.indexOf('http') == -1){
	     newtxt = newtxt.replace('src="'+scriptsrc+'"', 'src="'+stagingSiteUrl+scriptsrc+'"');
	     newtxt = newtxt.replace("src='"+scriptsrc+"'", "src='"+stagingSiteUrl+scriptsrc+"'");
	   }
	  }
	});

	dom.filter('link').each(function(i, item){
	  var linkhref = $(item).attr("href");
	  if(typeof(linkhref) != "undefined" && linkhref != ""){
	    if(linkhref.indexOf('http') == -1){
	     newtxt = newtxt.replace('href="'+linkhref+'"', 'href="'+stagingSiteUrl+linkhref+'"');
	     newtxt = newtxt.replace("href='"+linkhref+"'", "href='"+stagingSiteUrl+linkhref+"'");
	    }
	  }
	});

	dom.find('img').each(function(i, item){
	  var imgsrc = $(item).attr("src");
	  $(item).attr("src", imgsrc+i);
	  if(typeof(imgsrc) != "undefined" && imgsrc != ""){
	    if(imgsrc.indexOf('http') == -1){
	     newtxt = newtxt.replace('src="'+imgsrc+'"', 'src="'+stagingSiteUrl+imgsrc+'"');
	     newtxt = newtxt.replace("src='"+imgsrc+"'", "src='"+stagingSiteUrl+imgsrc+"'");
	    }
	  }
	});

	dom.find('a').each(function(i, item){
	  var ahref = $(item).attr("href");
	  if(typeof(ahref) != "undefined" && ahref != ""){
	  if(ahref.indexOf('http') == -1 && ahref.indexOf("#") == -1 && ahref.indexOf("javascript") == -1 && ahref.indexOf("Javascript")){ 
	      newtxt = newtxt.replace('href="'+ahref+'"', 'href="'+stagingSiteUrl+ahref+'"');
	      newtxt = newtxt.replace("href='"+ahref+"'", "href='"+stagingSiteUrl+ahref+"'");
	   }
	  }
	});
return newtxt;
}

function trim(s)
{
	return rtrim(ltrim(s));
}

function ltrim(s)
{
	var l=0;
	while(l < s.length && s[l] == ' ')
	{        l++; }
	return s.substring(l, s.length);
}

function rtrim(s)
{
	var r=s.length -1;
	while(r > 0 && s[r] == ' ')
	{        r-=1;        }
	return s.substring(0, r+1);
}


function formatDate(date,format){
	return Ext.Date.format(date,format);
}

function npiFormHandler(action,contentId){
	if(contentId != ''){
		Ext.Ajax.request({
				method : 'GET',
				url: dataservicesPath+'/npimetadata/search?q=&format=extjson&contentid=' + contentId,  
				method:"GET",
				//timeout: 30,
				success: function(response){
					var collectionsObj = Ext.JSON.decode(response.responseText);
					if(collectionsObj.Collections.Count == 1){
						formDataLoader(aForms[1],collectionsObj.Collections.NpiMetadata);
					}
					else{
						return false;
					}
				},
				failure: function(form, action) {
					Ext.MessageBox.hide(); 
				},
				waitMsg: 'Loading Metadata. Please Wait ...'
			});
	}
}

function formDataLoader(form,valuesObj){
		var collections = form.getFields();
		var count = collections.getCount();
		for(var i = 0; i < count; i++){
		    var field = collections.getAt(i);
		    if(field.getXType() == 'htmleditor'){
			field.setValue(valuesObj[field.getName()]);
		    }
		    else if(field.getXType() == 'combobox'){
			if(field.multiSelect){
				if(field.store.getCount > 0){
					field.setValue(valuesObj[field.getName()].split(',')); 
				}
				else{
					field.setValue(valuesObj[field.getName()]); 
				}
			}
			else{
				field.setValue(valuesObj[field.getName()]);
			}
		    }
		    else{  
			field.setValue(valuesObj[field.getName()]);
		    }
		}
}

function isFilledForm(form){
	if(!Ext.isEmpty(form)){
		var collections = form.getFields();
		var count = collections.getCount();
		for(var i = 0; i < count; i++){
		    var field = collections.getAt(i);
		    if(!field.hidden){
				if(!Ext.isEmpty(field.getValue()) && field.getValue() != ""){
				    return false;
				}
		    }
		}
		return true;
	}
	return false;
}

function isMandatoryFilledForm(form){
	if(!Ext.isEmpty(form)){
		var collections = form.getFields();
		var count = collections.getCount();
		for(var i = 0; i < count; i++){
		    var field = collections.getAt(i);
		    if(!field.hidden && !field.allowBlank){
				if(!Ext.isEmpty(field.getValue()) && field.getValue() != ""){
				    return false;
				}
		    }
		}
		return true;
	}
	return false;
}

function hasEntry(form){
	if(!Ext.isEmpty(form)){
		var collections = form.getFields();
		var count = collections.getCount();
		for(var i = 0; i < count; i++){
		    var field = collections.getAt(i);
		    if(!field.hidden){
			if(!Ext.isEmpty(field.getValue()) && field.getValue() != ""){
			    return true;
			}
		    }
		}
		return false;
	}
}

function validateNpiForm(){
	var form = aForms[1];
	if(!Ext.isEmpty(form)){
		var isFilled = hasEntry(form);
		if(isFilled){
			if(!form.isValid()){
			    return false;
			}	
		}		
	}
	return true;
}

function setTreePath(formPanel){
	var form = aForms[0]; // Global variable
	var entityType = form.findField('EntityType').getValue();
	var parentPath = '';
	var itemName   = '';
	var itemPath   = '';
	var pathField  = '';
	if(entityType === 'InformationArchitecture'){
		var pIaNameField 	= form.findField('InformationArchitectureParentIAName');
		var iANameField 	= form.findField('InformationArchitectureIaName');
		itemName = iANameField.getValue();
		pathField 	= form.findField('InformationArchitectureIaPath');
		/*var record = pIaNameField.store.findRecord("IaName",pIaNameField.getValue());
		if(!Ext.isEmpty(record)){
			parentPath = record.data.IaPath;
		}*/
                if(treePanelParentPath != ""){
                  parentPath = treePanelParentPath;
                }

	}
        if(entityType === 'GoiDirCat'){
		var pIaNameField 	= form.findField('GoiDirCatParentGoiDirCatName');
                var iANameField  = form.findField('GoiDirCatGoiDirCatName');
		itemName = iANameField.getValue();
		pathField 	= form.findField('GoiDirCatGoiDirCatPath');
		/*var record = pIaNameField.store.findRecord("GoiDirCatName",pIaNameField.getValue());
		if(!Ext.isEmpty(record)){
			parentPath = record.data.GoiDirCatPath;
		}*/
                if(treePanelParentPath != ""){
                  parentPath = treePanelParentPath;
                }
	}
	if(!Ext.isEmpty(parentPath)){
		itemPath += parentPath;
	}
	if(!Ext.isEmpty(itemName)){
		itemPath += "/" + itemName;
	}
	pathField.setValue(itemPath);
}

var domainEntitiesObject;

function loadDomainEntities(treestore,egroup){
	if(!Ext.isEmpty(egroup)){
		  if(Ext.isEmpty(domainEntitiesObject)){
			  Ext.Ajax.request({
					  async: false,
					  //timeout:20,
					  //url : dataservicesPath + '/search?q=&entitytype=workflow&format=json&workflowname=' + wfName,
					  url: apacheNcmsData + '/DomainEntity.json',
					  success : function(resp,req) {
						  	  collectionsObj = Ext.JSON.decode(resp.responseText);
							  domainEntitiesObject = collectionsObj.Collections.DomainEntity;
							  getAllowedEntitesTreeStore(treestore,egroup);
					  },
					  failure : function() {
						Ext.example.msg("<div class = 'errorHeaderMsg'>Loading domain entities failed</div>", "<div class = 'errorMsg'>Unable to load allowed entities list</div>");
					  }
				      });

		}
		else{
		 	getAllowedEntitesTreeStore(treestore,egroup);
		}
	}
}

function getAllowedEntitesTreeStore(treestore,egroup){
	var domainEntities = getCookie("domainentities").split(",");
	var node = treestore.getRootNode();
	if(!Ext.isEmpty(domainEntitiesObject)){
	  for(var i = 0; i < domainEntitiesObject.length; i++){
		  if(Ext.Array.contains(domainEntities,domainEntitiesObject[i].EntityName)
			  && egroup == domainEntitiesObject[i].EntityGroup){
			  node.appendChild({"text":domainEntitiesObject[i].EntityName,
				  "idName":domainEntitiesObject[i].EntityName,"leaf":true});
		  }
	  }
	}else{
	  Ext.example.msg("<div class = 'errorHeaderMsg'>Loading domain entities failed</div>", 
			  "<div class = 'errorMsg'>Data not available</div>");
	}
	treestore.setRootNode(node);
}


function globalDelete(grid, rowIndex, colIndex){
      var rec = grid.getStore().getAt(rowIndex);
      Ext.Msg.confirm('Delete', 'Are you sure you want to delete?',
      function(btn) {
     if(btn == 'Yes' || btn == 'yes') {
          var statusSubQry = '?';
          if(!Ext.isEmpty(rec.data.Status)){
        statusSubQry += "&Status=" + rec.data.Status;
          }
          if(!Ext.isEmpty(rec.data.CreatedBy)){
        statusSubQry += "&CreatedBy=" + rec.data.CreatedBy;
          }
          if(!Ext.isEmpty(rec.data.User)){
        statusSubQry += "&AssignedTo=" + rec.data.User;
          }
          if(allowDeleteAction(rec)){
          grid.getEl().mask();
          Ext.Ajax.request({
            url:dataservicesPath + '/' + rec.data.EntityType + '/' + rec.data.Id + statusSubQry,
            success: function(){
              grid.getEl().unmask();
              Ext.example.msg("<div class = 'successHeaderMsg'>Deletion Success</div>", "<div class = 'successMsg'>Deleted Successfully</div>");
              grid.store.load();
            },
            failure: function(){
              grid.getEl().unmask();
              Ext.example.msg("<div class = 'errorHeaderMsg'>Request Failed</div>", "<div class = 'errorMsg'>Deletion Failed</div>");   
            },
            method:'Delete'
          });
         }
         else{
           Ext.example.msg("<div class = 'errorHeaderMsg'>Access Denied</div>", "<div class = 'errorMsg'>You are not autherized to delete this resource</div>");   
         }
         grid.getEl().unmask();
     }
      }, this);

}

function allowDeleteAction(record){
  var dataCreatedBy = record.data.CreatedBy.toLowerCase();
  var dataStatus    = record.data.Status.toLowerCase();
  var dataAssigned  = record.data.User.toLowerCase();
  if(isSuperAdmin()){
    return true;
  }
  if((publisherRoleName.toLowerCase() == userRole.toLowerCase()
	&& dataStatus == "published") || (userRole.toLowerCase() == publisherRoleName.toLowerCase() && dataAssigned == cmsUserName.toLowerCase())){
    return true;
  }
  if(dataCreatedBy == cmsUserName.toLowerCase()){
    return true;
  }
  return false;
} 

function clearSearchBox(searchBox){
   searchBox.setValue('');
} 

function getSubQuery(entity){
    var aclSubQuery1;
    if(Ext.Array.contains(masterEntities,entity) || isSuperAdmin()){
          aclSubQuery1  = "&entitytype="+ entity;
	}
    else{
	  aclSubQuery1  = "&entitytype="+ entity +"&createdby=" + cmsUserName;
	}
    return aclSubQuery1;
}

function getFilterBySubQuery(entity){
    var aclSubQuery1;
    if(Ext.Array.contains(masterEntities,entity) || isSuperAdmin()){
          aclSubQuery1  = "";
    }
    else{
	  aclSubQuery1  = "&createdby=" + cmsUserName;
    }
    return aclSubQuery1;
}

function npiMetaDataSave(saveEntityName,form,action,errorpanel,rightPanelGrid,formWindow){
	var actionObj = Ext.JSON.decode(action.response.responseText);
	aForms[0].findField('Id').setValue(action.result.msg.Id);
	aForms[1].findField('ContentId').setValue(action.result.msg.Id);
	aForms[1].findField('ContentEntityType').setValue(saveEntityName);
	aForms[1].findField('EntityType').setValue("NpiMetadata");
	if(!Ext.isEmpty(action.result.msg.SeoUrl)){
	    aForms[1].findField('Identifier').setValue(action.result.msg.SeoUrl);									
	}
	var npiTitleMain = aForms[1].findField('TitleMain').getValue();
	var npiContentId = aForms[1].findField('ContentId').getValue();
	var npiIdentifier = aForms[1].findField('Identifier').getValue();
		aForms[1].submit({
			method : 'POST',
			url: dataservicesPath+'/npimetadata',  
			method:"POST",
			success: function(form, action){
				var actionObj = Ext.JSON.decode(action.response.responseText);
				if(actionObj.success == "false"){
					Ext.MessageBox.hide();
					errorpanel.expand(false);
					if(!Ext.isEmpty(actionObj.msg.ValidationError)){
						var erroMsg = "<font color=green>Tab1 Form Saved.</font><br>";
						erroMsg += "<font color=blue>Tab2 Form Has Errors.</font><br>";
						erroMsg += actionObj.msg.ValidationError;
						errorpanel.update("<div class = 'errorHeaderMsg'>" + erroMsg + "</div>");
					}
					else{
						if(aForms[0] && aForms[0].findField('FilePath')){
							aForms[0].findField('FilePath').setValue('');
						}
						if(actionObj.msg){
							var erroMsg = "<font color=green>Tab1 Form Saved.</font><br>";
							erroMsg += "<font color=blue>Tab2 Form Has Errors.</font><br>";
							erroMsg += getCustomErrorInfo(Ext.JSON.encode(actionObj.msg));
							errorpanel.update("<div class = 'errorHeaderMsg'>" + erroMsg + "</div>");
						}else{
							errorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(action.response.responseText)+ "</div>");
						}
					}
					return false;
				}
				else if(actionObj.success == "true"){
					Ext.example.msg("<div class = 'successHeaderMsg'>Success!</div>", "<div class = 'successMsg'>Saved Successfully.</div>");
					formWindow.close();
					rightPanelGrid.store.load();
				}
			},
			failure: function(form, action) {
				Ext.MessageBox.hide(); 
				cmsErrorpanel.expand(false);
				if(action.response.responseText != ''){
					var actionString = action.response.responseText;
					var actionObj = Ext.JSON.decode(actionString);
					if(typeof(actionObj.msg) != "undefined"){
						cmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + actionObj.msg + "</div>");
					}else{
						cmsErrorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(action.response.responseText)+ "</div>");
					}
				}
				else{
					cmsErrorpanel.update("<div class = 'errorHeaderMsg'>Request Timedout Or No Response From Server </div>");
				}
	
			},
			waitMsg: 'Saving Npi MetaData In Progress. Please Wait ...'
		});
}

function autoModifyFormValues(){
      var form = aForms[0]; // Global variable
      if(form.findField('Password') && !Ext.isEmpty(form.findField('Password').getValue())){
	  form.findField('Password').setValue(md5(form.findField('Password').getValue()));
      }
}
function resetPassword(){
    var form = aForms[0];
    if(form.findField('Password') && !Ext.isEmpty(form.findField('Password').getValue())){
	form.findField('Password').setValue('');
    }
}			

function saveEntity(rightPanelGrid, formWindow, saveEntityName, formAction, errorpanel){
	npiMetaData = '';
	if(!validateForm() || !validateNpiForm()){ 
	    Ext.example.msg("<div class = 'errorHeaderMsg'>Validation Error!</div>", "<div class = 'errorMsg'>Required field missing OR Invalid data</div>");
	    return false;
	}
	autoModifyFormValues();
	aForms[0].submit({ 
	    url: dataservicesPath+'/'+saveEntityName.toLowerCase(),  
	    method:"POST",
	    //timeout: 30,
	    params: getFinalFormParams(saveEntityName,formAction),
	    success: function(form, action){
		    var actionObj = Ext.JSON.decode(action.response.responseText);
		    allFormsSaved = false;
		    if(actionObj.success == "false"){
			    resetPassword();
			    Ext.MessageBox.hide();
			    errorpanel.expand(false);
			    if(!Ext.isEmpty(actionObj.msg.ValidationError)){
				    errorpanel.update("<div class = 'errorHeaderMsg'>" + actionObj.msg.ValidationError + "</div>");
			    }
			    else{
				    if(aForms[0] && aForms[0].findField('FilePath')){
					    aForms[0].findField('FilePath').setValue('');
				    }
				    if(actionObj.msg){
					    errorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
				    }else{
					    errorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(action.response.responseText)+ "</div>");
				    }
			    }
			    return false;
		    }
		   /* if(!Ext.isEmpty(aForms[1]) && isFilledForm(aForms[1])){
			    npiMetaDataSave(saveEntityName,form, action,errorpanel,rightPanelGrid,formWindow);
		    }
		    else */
                    if(actionObj.success == "true"){
			    Ext.example.msg("<div class = 'successHeaderMsg'>Success!</div>", "<div class = 'successMsg'>Saved Successfully.</div>");
			    formWindow.close();
			    rightPanelGrid.store.load();
		    }
	    },
	    failure: function(form, action) {
		    resetPassword();
		    var actionString = action.response.responseText;
		    Ext.MessageBox.hide(); 
		    if(!Ext.isEmpty(actionString)){
		      errorpanel.expand(false); 
		      var actionObj = Ext.JSON.decode(actionString);
		      if(!Ext.isEmpty(actionObj.msg)){
			      errorpanel.update("<div class = 'errorHeaderMsg'>" + getCustomErrorInfo(Ext.JSON.encode(actionObj.msg)) + "</div>");
		      }
		      else{
			      errorpanel.update("<div class = 'errorHeaderMsg'>" + action.response.responseText + "</div>");
		      }
		    }
	    },
	    scope:Ext.JSON.decode(saveEntityName + "Form"),
	    waitMsg: 'Saving Data In Progress. Please Wait ...'
	});
}

function showTreePanelWindow(element, entity, IdFieldName, type){
		var selectedItemName = '';
                var selectedItemPath = '';
		Ext.define('TreeModel', {
			extend: 'Ext.data.Model',
			fields: [
			         {name: 'treeitem',     type: 'string'},
                                 {name: 'text', type:'string'}
			         ]
		});
		
		var treeStore = Ext.create('Ext.data.TreeStore', {
			model: 'TreeModel',
			autoLoad:false,
			proxy: {
				type: 'ajax',
				url: apacheNcmsData + '/' + entity + 'Tree.json'
				//url:apacheNcmsData + '/iatreegrid.json'
			},
			folderSort: true
		});
		
		
		var treePanel = Ext.create('Ext.tree.Panel', {
			expanded: true,
			padding:1,
			width:498,
			height: 320,
			useArrows: true,
			rootVisible: false,
			store: treeStore,
			autoScroll : true,
			hideHeaders:true,
			//singleExpand: false,
			containerScroll: true,
			border: false,
			animate:true,
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
		
		treePanel.on('itemclick',function(view, record, htmlelement, index, e){
		  if(entity != record.data.treeitem){
		    selectedItemName = (entity == "InformationArchitecture" && type == "parentchild") ? record.data.treeitem : record.data.id;
                    selectedItemPath = (entity == "InformationArchitecture" || entity == "GoiDirCat") ? record.data.id :'';
                    if(type == "parentchild"){
                      selectedParentId = record.data.text;
                    }
		  }
		},this);
		
		
		var mainPanel = Ext.create('Ext.panel.Panel', {
			//region:'east',
			//autoScroll:true,
			//scroll:true,
			width : 500,
			height : '325',
			border:false,
			items:[treePanel]
		 });
		
		
		var treeWindow = new Ext.Window({
			//closeAction : 'hide',
			//autoScroll : true,
			modal:true,
			//layout: 'border',
			width : 510,
			height : '410',
			items : [mainPanel], //[ formItem,cmsErrorpanel ],
			buttons : [ 
			{
				text: 'Clear',
				id : 'tree-clear',
				handler:function(){
				   element.setValue('');
                                   if((entity == "InformationArchitecture" && type != "associatedia") || entity == "GoiDirCat"){
                                     treePanelParentPath = '';
                                     setTreePath();
                                   }
 				   if(type == "parentchild"){
                                     var pId = Ext.getCmp(entity+IdFieldName);  
                                     pId.setValue("");
                                   }
					treeWindow.close();
				}
			},{
				text : 'Ok',
				id : 'tree-ok',
				handler : function() {
				   element.setValue(selectedItemName);
                                   if((entity == "InformationArchitecture" && type != "associatedia") || entity == "GoiDirCat"){
                                     treePanelParentPath = selectedItemPath;
                                     setTreePath();
                                   }
 				   if(type == "parentchild"){
                                     var pId = Ext.getCmp(entity+IdFieldName);  
                                     pId.setValue(selectedParentId);
                                   }
				   treeWindow.close();
				}
			},{
				text: 'Cancel',
				id : 'tree-cancel',
				handler:function(){
                                  if(type == "parentchild"){
                                     var pId = Ext.getCmp(entity+IdFieldName);  
                                     pId.setValue("");
                                  }
					treeWindow.close();
				}
			}
			]
		});
		
		treeStore.on('load',function(){
		  treePanel.selectPath(element.getValue());
		},this);
		
		treeWindow.show();
}


//http://124.7.228.161/ncmsui/extjs/js/advancedsearch.js?_dc=3002

  function globalAdvancedSeacrh(currentPanel,rightPanel,cEntity,isMediaFile){
  
       clearSearchBox(rightPanel.searchText);
            currentPanel.xmlFields = Ext.create('Ext.data.ArrayStore', {
                   fields: [cEntity+"Fields"],
		   data :  Ext.JSON.decode("Ext."+cEntity+"Fields")
                });

	    currentPanel.comfields = Ext.create('Ext.data.Store',{
                  fields:['value','name'],
                  data:arrayCombineFields
            });
	    currentPanel.combineFields = Ext.create('Ext.form.field.ComboBox',{store : currentPanel.comfields,
	        displayField: 'name',
		editable:false,
                valueField:'value',
                width:60,
                queryMode:'local',
                triggerAction: 'all',
                forceSelection: true
            });
	    currentPanel.combineFields.on('render',function(combo1) {
                currentPanel.combineFields.setValue('AND');
                currentPanel.combineFields.queryMode = 'local';
            },currentPanel, {
                single: true
            });
        currentPanel.combineFields.hide();
 	currentPanel.equalfields = Ext.create('Ext.data.Store',{
                fields:['value','name'],
                data: arrayEqualFields
        });
        currentPanel.equalFields = Ext.create('Ext.form.field.ComboBox',{
                store:currentPanel.equalfields,
                displayField: 'name',
		editable:false,
                valueField: 'value',
                width:50,
                queryMode: 'local',
                triggerAction: 'all',
                forceSelection: true
        });
         currentPanel.equalFields.on('render',function(combo1) {
                currentPanel.equalFields.setValue('=');
                currentPanel.equalFields.queryMode = 'local';
            },currentPanel, {
                single: true
            });
	
	    currentPanel.searchFields = Ext.create('Ext.form.field.ComboBox',{
                 editable:false,
                 store: currentPanel.xmlFields,
                 queryMode:"local",
	         displayField: cEntity+"Fields",
                 emptyText:"Select field",
		 multiple:false,
                 /*listeners:{
                   beforerender:function(combo){
	             currentPanel.store.load();
                   },
                   render:function(combo){
	             currentPanel.store.sort(cEntity+"Fields",'ASC');
                   }
                 }, */
		 allowBlank:false
	    });
           
        currentPanel.fromSearchDateTxt = Ext.create('Ext.form.field.Date',{
    	            fieldLabel: '',
    	            name: 'fromDate',
    	            xtype: 'datefield',
		    editable:false,
		    allowBlank:false,
    		    anchor:'18',
		    width: 80,
  		    format: 'm/d/y',
		    dateConfig: {
	            altFormats:'Y-m-d|Y-n-d',
		      readOnly : false  
		   }
	    });
	
	currentPanel.fromSearchTimeTxt = Ext.create('Ext.form.field.Time',{
	  xtype: 'timefield',
	  name: 'time_in',
	  editable:false,
	  allowBlank:false,
	  fieldLabel: '',
	  width: 60,
	  format:'H:i',
	  timeConfig:{
	    altFormats:'H:i',
	    readOnly : false    
	  }
	});    

	currentPanel.fromSearchDateTxt.hide();
	currentPanel.fromSearchTimeTxt.hide();
	
	currentPanel.toSearchDateTxt = Ext.create('Ext.form.field.Date',{
	    name: 'toDate',
	    fieldLabel: '',
	    editable:false,
	    allowBlank:false,
            xtype: 'datefield',
	    anchor:'18',
	    width: 80,
	    format: 'm/d/y',
	    dateConfig: {
            altFormats:'Y-m-d|Y-n-d',
	        readOnly : false  
	   }
       });
	       
       currentPanel.toSearchTimeTxt = Ext.create('Ext.form.field.Time',{
	  xtype: 'timefield',
	  name: 'time_out',
	  editable:false,
	  allowBlank:false,
	  fieldLabel: '',
	  width: 60,
	  format:'H:i',
	  timeConfig:{
	    altFormats:'H:i',
	    readOnly : false    
	  }
	});    

	currentPanel.toSearchDateTxt.hide();
	currentPanel.toSearchTimeTxt.hide();
	currentPanel.queryTxt = Ext.create('Ext.form.field.Text',{
	      width: 200,
	      emptyText: 'Enter Search term',
	      maxLength :100,
	      scope: this,
	      enforceMaxLength: true,
	      vtype: 'alpha',
	      listeners: {
		  focus : function(tbx){
		      tbx.setValue(""); 
		  },
		  blur:function(tbx){
		      var str = trim(tbx.getValue());
		      str = str.replace(/ +(?= )/g,'');
		      tbx.setValue(str);
		  }
	      }
	});
	currentPanel.queryContent = Ext.create('Ext.form.field.TextArea',{
				width: 698,
				height: 250,
				readOnly:true,
				anchor:'100%'
			});
	currentPanel.andBtn = Ext.create('Ext.Button',{
			text:"ADD",
			iconCls:'icon-plus'
		      });

	currentPanel.queryBuilder = Ext.create('Ext.window.Window',{
		width:710,
		title:"Advanced Search - Query Builder",
		tbar : ["<b>Search</b>","-",currentPanel.combineFields,"-",currentPanel.searchFields,"-",currentPanel.equalFields,"-",currentPanel.queryTxt,currentPanel.fromSearchDateTxt,currentPanel.fromSearchTimeTxt,"--",currentPanel.toSearchDateTxt,currentPanel.toSearchTimeTxt,currentPanel.andBtn],
		height: 340,
		modal:true,
		scope: currentPanel,
		closeAction:'hide',
		items: currentPanel.queryContent,
		bbar: [
			{
			    text: 'Search',
			    scope: currentPanel,
			    id:'cms-advsearch-button',
			    disabled:true,
			    iconCls:'icon-search',
			    handler: function() {
				  if(currentPanel.queryContent.getValue()){
				      rightPanel.grid.store.currentPage=1;
				      rightPanel.grid.store.proxy.url= dataservicesPath + '/'+cEntity.toLowerCase()+'/search?q=&format=extjson'+isMediaFile+'&'+currentPanel.queryContent.getValue()+getSubQuery(cEntity);
				      rightPanel.grid.store.proxy.setReader(
					  {
						type: 'json',
						root: 'Collections.'+cEntity,
						totalProperty: 'Collections.Count' 
					  }
				      );  
				      rightPanel.grid.store.load();
				}
				else{
				    Ext.example.msg("<div class = 'errorHeaderMsg'>Search Term Missing</div>", "<div class = 'errorMsg'>Enter Keywords to Search</div>"); 
				}
			  }
			},
			{
			    text: 'Reset',
			    scope: currentPanel,
			    iconCls:'icon-reset',
			    handler: function() {
				Ext.getCmp('cms-advsearch-button').disable(true); 
				currentPanel.queryContent.reset();
				currentPanel.queryTxt.reset();
				currentPanel.fromSearchDateTxt.reset();
				currentPanel.fromSearchTimeTxt.reset();
				currentPanel.toSearchDateTxt.reset();
				currentPanel.toSearchTimeTxt.reset();
				currentPanel.queryValue = "";
				currentPanel.combineFields.hide();
			    }
			},
			{
			    text: 'Close',
			    scope: currentPanel,
			    iconCls:'icon-close',
			    handler: function() {
				currentPanel.queryBuilder.hide();
				currentPanel.queryValue = "";
				currentPanel.combineFields.hide();
				currentPanel.advancedSearchFlag = false;
				currentPanel.queryContent.reset();
				currentPanel.queryTxt.reset();
				currentPanel.fromSearchDateTxt.reset();
				currentPanel.fromSearchTimeTxt.reset();
				currentPanel.toSearchDateTxt.reset();
				currentPanel.toSearchTimeTxt.reset();
			    }
			}]
	    });
	    currentPanel.queryBuilder.on('beforeclose', function() {
	    Ext.getCmp('cms-advsearch-button').disable(true); 
	    currentPanel.queryBuilder.hide();
	    currentPanel.queryValue = "";
	    currentPanel.combineFields.hide();
            currentPanel.advancedSearchFlag = false;
	    currentPanel.queryContent.reset();
	    currentPanel.queryTxt.reset();
	    currentPanel.fromSearchDateTxt.reset();
	    currentPanel.fromSearchTimeTxt.reset();
	    currentPanel.toSearchDateTxt.reset();
	    currentPanel.toSearchTimeTxt.reset();
            return false;
        },currentPanel);
        currentPanel.advancedSearchFlag = true;
		currentPanel.queryBuilder.show();
		var validData = false;
		var isoFromDate = "";
		var isoFromTime = "";
		var isoToDate = "";
		var isoToTime = "";
		currentPanel.queryValue = "";
		
		currentPanel.andBtn.on('click',function(){
		  
		    if(currentPanel.queryTxt.isValid()){

		        if(currentPanel.searchFields.getValue() == 'CreatedDate' || currentPanel.searchFields.getValue() == 'LastModifiedDate'){
		            if(currentPanel.fromSearchDateTxt.getValue())
			      isoFromDate = new Date(currentPanel.fromSearchDateTxt.getValue()).format('Y-m-d');
		            if(currentPanel.fromSearchTimeTxt.getValue())
			      isoFromTime = new Date(currentPanel.fromSearchTimeTxt.getValue()).format('TH:i:sZ');
		            if(currentPanel.toSearchDateTxt.getValue())
			      isoToDate   = new Date(currentPanel.toSearchDateTxt.getValue()).format('Y-m-d');
		            if(currentPanel.toSearchTimeTxt.getValue())
			      isoToTime   = new Date(currentPanel.toSearchTimeTxt.getValue()).format('TH:i:sZ');  
			    
			    if((!isoFromDate) || (!isoFromTime) || (!isoToDate) || (!isoToTime)){
			      validData = false;
			      Ext.example.msg("<div class = 'errorHeaderMsg'>Search Term Missing</div>", "<div class = 'errorMsg'>Enter Date to Search</div>");
			    }  
			    else {
			      Ext.getCmp('cms-advsearch-button').enable(true); 
		              validData = true;
			      if(currentPanel.queryValue){
				currentPanel.queryValue = currentPanel.queryValue + "&";
			      }
			      currentPanel.queryValue = currentPanel.queryValue + currentPanel.searchFields.getValue() + "=[" + isoFromDate + isoFromTime + " TO " + isoToDate + isoToTime +"]";
			    }  
		        }
		        else{
	                    if((!currentPanel.searchFields.getValue()) || (!currentPanel.queryTxt.getValue())) {
			      validData = false;
			      Ext.example.msg("<div class = 'errorHeaderMsg'>Search Term Missing</div>", "<div class = 'errorMsg'>Enter Keywords to Search</div>");
			    }  
			    else{
			      Ext.getCmp('cms-advsearch-button').enable(true); 
			      validData = true;
			      assignval = currentPanel.equalFields.getValue();
			      if(currentPanel.queryTxt.getValue()){
				  if(assignval == '!=')
				      qval = "!"+currentPanel.searchFields.getValue() + "=" + (currentPanel.queryTxt.getValue()).replace(/[' ']/gim,"+");
				  else
				      qval = currentPanel.searchFields.getValue() + "=" + (currentPanel.queryTxt.getValue()).replace(/[' ']/gim,"+");
				  
				  if(currentPanel.queryValue == ''){
				    currentPanel.queryValue = qval;
				  }else{
				    currentPanel.queryValue = currentPanel.queryValue  + "&"+ qval;
				  }
			      }  
			   }
		        }
		        if(validData){
			   currentPanel.combineFields.show();
			   currentPanel.queryParam = currentPanel.queryValue+"&cond="+currentPanel.combineFields.value;
			   currentPanel.queryContent.setValue(currentPanel.queryParam);
			}
		    }
		    else{
		      Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid Keyword</div>", "<div class = 'errorMsg'>Enter valid keyword</div>");
		    }
	 		       
		},currentPanel);
		currentPanel.searchFields.on('change',function(field,newVal,oldVal){
		       if(newVal == 'CreatedDate' || newVal == 'LastModifiedDate'){
		            currentPanel.queryTxt.hide();
		            currentPanel.fromSearchDateTxt.show();
		            currentPanel.fromSearchTimeTxt.show();
		            currentPanel.toSearchDateTxt.show();
		            currentPanel.toSearchTimeTxt.show();
		       }
		       else{ 
		            currentPanel.queryTxt.show();
		            currentPanel.fromSearchDateTxt.hide();
  	                    currentPanel.fromSearchTimeTxt.hide();
		            currentPanel.toSearchDateTxt.hide();
		            currentPanel.toSearchTimeTxt.hide();
		       }
	        },currentPanel);
  		    
  }
  
  function globalSearch(cElement, e, rightPanel, isMediaFile, cEntity, Key){
      var searchValue = rightPanel.searchText.getValue();
      if(e.getKey() == e.ENTER || Key == 'searchbutton') {
	  if(searchValue){
	    if(!rightPanel.searchText.isValid()){
		Ext.example.msg("<div class = 'errorHeaderMsg'>Invalid Keyword</div>", "<div class = 'errorMsg'>Enter valid keyword</div>");
	    }
	    else{
		rightPanel.grid.store.currentPage=1;
		rightPanel.grid.store.proxy.url= dataservicesPath + '/search?format=extjson'+isMediaFile + getSubQuery(cEntity) + '&q='+ searchValue;
		rightPanel.grid.store.proxy.setReader(
		    {
			  type: 'json',
			  root: 'Collections.'+cEntity,
			  totalProperty: 'Collections.Count' 
		    }
		); 
		rightPanel.grid.store.load();
	    }
	  }
	  else{
	    Ext.example.msg("<div class = 'errorHeaderMsg'>Search Term Missing</div>", "<div class = 'errorMsg'>Enter Keywords to Search</div>");
	  }
      }
  }   

//http://124.7.228.161/ncmsui/extjs/js/npimetadataform.js?_dc=3002

     var DependentNpiMetadataForm = 
                                    [{
     name :"Id",
           id:"NpiMetadataId",
               readOnly:true,
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
                                                                hidden:true,
                                                   allowBlank : true
                   
},

                                             {
     name :"EntityType",
           id:"NpiMetadataEntityType",
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
                    value:"NpiMetadata",
                                         allowBlank : true
                   
},

                                             {
     name :"Identifier",
           id:"NpiMetadataIdentifier",
                              maxLength :250,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Identifier *",
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
     name :"ContentId",
           id:"NpiMetadataContentId",
                         minLength :13,
               maxLength :13,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Content Id *",
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
     name :"ContentEntityType",
           id:"NpiMetadataContentEntityType",
                              maxLength :50,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Content Entity Type *",
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
     name :"IdentifierAlternate",
           id:"NpiMetadataIdentifierAlternate",
                              maxLength :50,
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
                allowBlank : true
                   
},

                                             {
     name :"TitleMain",
           id:"NpiMetadataTitleMain",
                              maxLength :50,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Title Main *",
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
     name :"TitleAlternate",
           id:"NpiMetadataTitleAlternate",
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
                allowBlank : true
                   
},

                                             {
     name :"Description",
           id:"NpiMetadataDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description  *",  
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
                                    store:createStore('Sector', ''),
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
                                                   allowBlank : false 
                   
},

                                                                       {
     name :"PublisherOrgName",
           id:"PublisherOrgNamesPublisherOrgName",
               editable:false,
                 fieldLabel : "PublisherOrgName",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,store:createQueryStore('GoiDirItem', 'Category="Organisations"'),
               width:500,
               pageSize:100,
               typeAhead:true,
               triggerAction: 'all',
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
                                                   allowBlank : true
                   
},

                                                                       {
     name :"PublisherDepartment",
           id:"PublisherDeptNamePublisherDepartment",
               editable:false,
                 fieldLabel : "PublisherDeptName *",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,
                store:createQueryStore('GoiDirItem', 'Category="Departments"'),
                width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
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
                                                   allowBlank : false 
                   
},

                                                  {
     name :"PublisherAddress",
           id:"NpiMetadataPublisherAddress",
           height:200,
                              maxLength :1000,
          enforceMaxLength:true,
          height:200,
          anchor :'100%', 
     xtype:"textarea",
     fieldLabel : "Publisher Address",
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
	name :"SameAsPublisher",
	id:"NpiMetadataSameAsPublisher",
	xtype:"checkboxfield",
	isFormField:false,
	fieldLabel : "Creator details same as Publisher?",
	listeners:{
		change: function(checkbox, newValue, oldValue){
			var creatorOrgName = Ext.getCmp('CreatorOrgNamesCreatorOrgName');
			var creatorDepartment = Ext.getCmp('CreatorDeptNameCreatorDepartment');
			var creatorAddress = Ext.getCmp('NpiMetadataCreatorAddress');
			var publisherOrgName = Ext.getCmp('PublisherOrgNamesPublisherOrgName');
			var publisherDepartment = Ext.getCmp('PublisherDeptNamePublisherDepartment');
			var publisherAddress = Ext.getCmp('NpiMetadataPublisherAddress');
			if(this.getValue()){
				creatorOrgName.setValue(publisherOrgName.getValue());
				creatorDepartment.setValue(publisherDepartment.getValue());
				creatorAddress.setValue(publisherAddress.getValue());
			}
			else{
				creatorOrgName.setValue('');
				creatorDepartment.setValue('');
				creatorAddress.setValue('');
			}
		}
	}
},


{
     name :"CreatorOrgName",
           id:"CreatorOrgNamesCreatorOrgName",
               editable:false,
                 fieldLabel : "CreatorOrgName",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,
                                    store:createQueryStore('GoiDirItem', 'Category="Organisations"'),
                    width:500,
                    pageSize:100,
                    typeAhead:true,
                    triggerAction: 'all',
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
                                                   allowBlank : true
                   
},

                                                                       {
     name :"CreatorDepartment",
           id:"CreatorDeptNameCreatorDepartment",
               editable:false,
                 fieldLabel : "CreatorDeptName *",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,
                     store:createQueryStore('GoiDirItem', 'Category="Departments"'),
                     width:500,
                     pageSize:100,
                     typeAhead:true,
                     triggerAction: 'all',
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
                                                   allowBlank : false 
                   
},

                                                  {
     name :"CreatorAddress",
           id:"NpiMetadataCreatorAddress",
                              maxLength :1000,
                              anchor :'100%', 
          enforceMaxLength:true,
          height:200,
     xtype:"textarea",
     fieldLabel : "Creator address",
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
     name :"Format",
           id:"NpiMetadataFormat",
               editable:false,
                 fieldLabel : "Format  *",
                    xtype:"combobox",
          editable:false,
                                          store:createStore('Format', ''),
                     queryMode:"remote",
     displayField:"FormatName",
     valueField:"FormatName",
          emptyText:"",
          listeners:{
	      beforerender:function(combo){
		this.store.load();
	      },
               render: function(combo,options){
                        this.store.sort('FormatName','ASC');
                      } 
               }, 
                                                        allowBlank : false 
                   
},{
     name :"LanguageId",
           id:"NpiMetadataLanguageId",
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
     name :"Language",
           id:"NpiMetadataLanguage",
               editable:false,
                 fieldLabel : "Language *",
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
                  if(typeof(Ext.getCmp('NpiMetadataIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('NpiMetadataIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('NpiMetadataLanguageId');
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
     name :"CreatedDate",
           id:"NpiMetadataCreatedDate",
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
     name :"MetaDataCreatedDate",
           id:"NpiMetadataMetaDataCreatedDate",
                    fieldLabel : "MetaData Created Date",
     xtype : "datetimefield",
	                                                         allowBlank : true,
	                                                         editable:false
                   
},

                                             {
     name :"PublishedDate",
           id:"NpiMetadataPublishedDate",
                    fieldLabel : "Published Date",
     xtype : "datetimefield",
	                                                         allowBlank : true,
	                                                         editable:false
                   
},

                                             {
     name :"ValidDate",
           id:"NpiMetadataValidDate",
                    fieldLabel : "Valid Date",
     xtype : "datetimefield",
	                                                         allowBlank : true,
	                                                         editable:false
                   
},

                                             {
     name :"LastModifiedDate",
           id:"NpiMetadataLastModifiedDate",
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
     name :"JurisdictionName",
           id:"JurisdictionJurisdictionName",
               editable:false,
                 fieldLabel : "Jurisdiction Name *",
                    xtype:"combobox",
          editable:false,
                                          store:createStore('JurisdictionType', ''),
                     queryMode:"remote",
     displayField:"JurisdictionTypeName",
     valueField:"JurisdictionTypeName",
          emptyText:"",
                                                        allowBlank : false 
                   
},

                                             {
     name :"JurisdictionStateId",
           id:"JurisdictionJurisdictionStateId",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "JurisdictionStateId",
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
     name :"JurisdictionStateName",
           id:"JurisdictionJurisdictionStateName",
               editable:false,
                 fieldLabel : "Jurisdiction State Name *",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
                                                        allowBlank : false 
                   
},

                                             {
     name :"JurisdictionDistrictId",
           id:"JurisdictionJurisdictionDistrictId",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "JurisidictionDistrictId",
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
     name :"JurisdictionDistrictName",
           id:"JurisdictionJurisdictionDistrictName",
               editable:false,
                 fieldLabel : "Jurisdiction District Name *",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
	focus: function(){
	  	this.setValue('');
		this.store.currentPage = 1;
	  	var sname = Ext.getCmp("JurisdictionJurisdictionStateName").getValue();
		if(!Ext.isEmpty(sname) && sname !== 'All India'){
			this.store.load({params:{"ParentGoiDirItemName":'"'+ sname + '"'}});
		}
		else{
			this.store.load();
		}
	},
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              } 
       },    
                                                        allowBlank : false 
                   
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
                                                   allowBlank : true
                   
},

                                             {
     name :"SpatialStateName",
           id:"SpatialSpatialStateName",
               editable:false,
                 fieldLabel : "Spatial State Name",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
                                                        allowBlank : true
                   
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
                                                   allowBlank : true
                   
},

                                             {
     name :"SpatialDistrictName",
           id:"SpatialSpatialDistrictName",
               editable:false,
                 fieldLabel : "Spatial District Name",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
	focus: function(){
		this.setValue('');
		var sname = Ext.getCmp("SpatialSpatialStateName").getValue();
		this.store.currentPage = 1; //reset page
		if(!Ext.isEmpty(sname) && sname != 'All India'){
			this.store.load({params:{"ParentGoiDirItemName":'"'+ sname + '"'}});
		}		
		else{
			this.store.load();
		}
	},
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              } 
       },    
                                                        allowBlank : true
                   
},

                                                       {
     name :"CoverageTemporal",
           id:"NpiMetadataCoverageTemporal",
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
                allowBlank : true
                   
},

                                                                  {
     name :"AudienceCategory",
           id:"NpiMetadataAudienceAudienceCategory",
               editable:false,
                 fieldLabel : "Audience *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                    store:createStore('AudienceCategory', ''),
                     queryMode:"remote",
     displayField:"AudienceCategoryName",
     valueField:"AudienceCategoryName",
          emptyText:"",
                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('AudienceCategoryName','ASC');
           }
        },
                                                   allowBlank : false 
                   
},

                                                                       {
     name :"CategoryType",
           id:"NpiMetadataCategoryTypesCategoryType",
               editable:false,
                 fieldLabel : "Type Category *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                    store:createStore('Category', ''),
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
                                                   allowBlank : false 
                   
},

                                                  {
     name :"ConformsTo",
           id:"NpiMetadataConformsTo",
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
                allowBlank : true
                   
},

                                                                  {
     name :"HasPart",
           id:"NpiMetadataHasPartsHasPart",
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
                allowBlank : true
                   
},

                                                  {
     name :"IsPartOf",
           id:"NpiMetadataIsPartOf",
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
                allowBlank : true
                   
},

                                             {
     name :"HasVersion",
           id:"NpiMetadataHasVersion",
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
                allowBlank : true
                   
},

                                             {
     name :"IsVersionOf",
           id:"NpiMetadataIsVersionOf",
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
                allowBlank : true
                   
},

                                             {
     name :"Status",
           id:"NpiMetadataStatus",
               editable:false,
                 fieldLabel : "Status",
               hidden:true,
               xtype:"combobox",
          editable:false,
                                          store:createStore('Stage', ''),
                     queryMode:"remote",
     displayField:"StageName",
     valueField:"StageName",
          emptyText:"",
                                                        allowBlank : true
                   
},

                                             {
     name :"CreatedBy",
           id:"NpiMetadataCreatedBy",
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
                               allowBlank : true
                   
},

                                             {
     name :"LastModifiedBy",
           id:"NpiMetadataLastModifiedBy",
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
                               allowBlank : true
                   
},

                                             {
     name :"Source",
           id:"NpiMetadataSource",
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
     name :"CurrentUser",
           id:"WorkflowInfoCurrentUser",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "CurrentRole",
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
     fieldLabel : "CurrentEvent",
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
     fieldLabel : "Id",
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
               editable:false,
                 fieldLabel : "Possible Event *",
               hidden:true,
               xtype:"combobox",
          editable:false,
               multiSelect:true,
                                    store:createStore('Event', ''),
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
                                                   allowBlank : true
                   
},

                                                  {
     name :"WorkflowComments",
           id:"NextPossibleEventsWorkflowComments",
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
     name :"Site",
           id:"NpiMetadataSite",
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
                                                   allowBlank : true
                   
},

                                             {
     name :"Version",
           id:"NpiMetadataVersion",
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
                                                   allowBlank : true
                   
}];
var DependentAddressForm = [

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
                 fieldLabel : "District *",
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
                                                          allowBlank:false
                   
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
                           
}];

var DependentHindiAddressForm = [
                     {
     name :"HindiAddressName",
           id:"HindiAddressAddressName",
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
     name :"HindiAddress",
           id:"HindiAddressAddress",
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
     name :"HindiVillage",
           id:"HindiAddressVillage",
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
     name :"HindiTaluk",
           id:"HindiAddressTaluk",
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
     name :"HindiDistrictId",
           id:"HindiAddressDistrictId",
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
     name :"HindiDistrict",
           id:"HindiAddressDistrict",
               editable:false,
                 fieldLabel : "District *",
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
                  if(typeof(Ext.getCmp('HindiAddressIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('HindiAddressIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('HindiAddressDistrictId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   					,   
			focus: function(){
			  this.setValue('');
			  			  var sname = Ext.getCmp('HindiAddressState').getValue();
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
     name :"HindiCityId",
           id:"HindiAddressCityId",
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
     name :"HindiCity",
           id:"HindiAddressCity",
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
                  if(typeof(Ext.getCmp('HindiAddressIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('HindiAddressIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('HindiAddressCityId');
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
     name :"HindiStateId",
           id:"HindiAddressStateId",
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
     name :"HindiState",
           id:"HindiAddressState",
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
                  if(typeof(Ext.getCmp('HindiAddressIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('HindiAddressIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('HindiAddressStateId');
             pId.setValue(this.store.getAt(i).get('Id'));
          }
      } ,   
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              }
	   					,   
			focus: function(){
			  this.setValue('');
			  			  var sname = Ext.getCmp('HindiAddressState').getValue();
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
     name :"HindiAddressPincode",
           id:"HindiAddressPincode",
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
                           
}];

 var DependentMetadataInfoForm = [

                                             {
     name :"Identifier",
           id:"MetadataInfoIdentifier",
                              maxLength :250,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Identifier *",
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
     name :"IdentifierAlternate",
           id:"MetadataInfoIdentifierAlternate",
                              maxLength :50,
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
                allowBlank : true
                   
},

                                             {
     name :"TitleMain",
           id:"MetadataInfoTitleMain",
                              maxLength :50,
          enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "Title Main *",
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
     name :"TitleAlternate",
           id:"MetadataInfoTitleAlternate",
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
                allowBlank : true
                   
},

                                             {
     name :"MetadataDescription",
           id:"MetadataInfoMetadataDescription",
                    xtype : "htmleditor",
               maxLength :4000,
          fieldLabel : "Description  *",  
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
                                    store:createStore('Sector', ''),
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
                                                   allowBlank : false 
                   
},

                                                                       {
     name :"PublisherOrgName",
           id:"PublisherOrgNamesPublisherOrgName",
               editable:false,
                 fieldLabel : "PublisherOrgName",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,store:createQueryStore('GoiDirItem', 'Category="Organisations"'),
               width:500,
               pageSize:100,
               typeAhead:true,
               triggerAction: 'all',
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
                                                   allowBlank : true
                   
},

                                                                       {
     name :"PublisherDepartment",
           id:"PublisherDeptNamePublisherDepartment",
               editable:false,
                 fieldLabel : "PublisherDeptName *",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,
                store:createQueryStore('GoiDirItem', 'Category="Departments"'),
                width:500,
                pageSize:100,
                typeAhead:true,
                triggerAction: 'all',
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
                                                   allowBlank : false 
                   
},

                                                  {
     name :"PublisherAddress",
           id:"MetadataInfoPublisherAddress",
           height:200,
                              maxLength :1000,
          enforceMaxLength:true,
          height:200,
          anchor :'100%', 
     xtype:"textarea",
     fieldLabel : "Publisher Address",
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
	name :"SameAsPublisher",
	id:"MetadataInfoSameAsPublisher",
	xtype:"checkboxfield",
	isFormField:false,
	fieldLabel : "Creator details same as Publisher?",
	listeners:{
		change: function(checkbox, newValue, oldValue){
			var creatorOrgName = Ext.getCmp('CreatorOrgNamesCreatorOrgName');
			var creatorDepartment = Ext.getCmp('CreatorDeptNameCreatorDepartment');
			var creatorAddress = Ext.getCmp('MetadataInfoCreatorAddress');
			var publisherOrgName = Ext.getCmp('PublisherOrgNamesPublisherOrgName');
			var publisherDepartment = Ext.getCmp('PublisherDeptNamePublisherDepartment');
			var publisherAddress = Ext.getCmp('MetadataInfoPublisherAddress');
			if(this.getValue()){
				creatorOrgName.setValue(publisherOrgName.getValue());
				creatorDepartment.setValue(publisherDepartment.getValue());
				creatorAddress.setValue(publisherAddress.getValue());
			}
			else{
				creatorOrgName.setValue('');
				creatorDepartment.setValue('');
				creatorAddress.setValue('');
			}
		}
	}
},


{
     name :"CreatorOrgName",
           id:"CreatorOrgNamesCreatorOrgName",
               editable:false,
                 fieldLabel : "CreatorOrgName",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,
                                    store:createQueryStore('GoiDirItem', 'Category="Organisations"'),
                    width:500,
                    pageSize:100,
                    typeAhead:true,
                    triggerAction: 'all',
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
                                                   allowBlank : true
                   
},

                                                                       {
     name :"CreatorDepartment",
           id:"CreatorDeptNameCreatorDepartment",
               editable:false,
                 fieldLabel : "CreatorDeptName *",
                    xtype:"combobox",
          editable:false,
               multiSelect:false,
                     store:createQueryStore('GoiDirItem', 'Category="Departments"'),
                     width:500,
                     pageSize:100,
                     typeAhead:true,
                     triggerAction: 'all',
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
                                                   allowBlank : false 
                   
},

                                                  {
     name :"CreatorAddress",
           id:"MetadataInfoCreatorAddress",
                              maxLength :1000,
                              anchor :'100%', 
          enforceMaxLength:true,
          height:200,
     xtype:"textarea",
     fieldLabel : "Creator address",
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
     name :"Format",
           id:"MetadataInfoFormat",
               editable:false,
                 fieldLabel : "Format  *",
                    xtype:"combobox",
          editable:false,
                                          store:createStore('Format', ''),
                     queryMode:"remote",
     displayField:"FormatName",
     valueField:"FormatName",
          emptyText:"",
          listeners:{
               render: function(combo,options){
                        this.store.sort('FormatName','ASC');
                      } 
               }, 
                                                        allowBlank : false 
                   
},
{
     name :"LanguageId",
           id:"MetadataInfoLanguageId",
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
           id:"MetadataInfoLanguage",
               editable:false,
                 fieldLabel : "Language *",
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
                  if(typeof(Ext.getCmp('MetadataInfoIsTopLevel')) != "undefined" && v != ""){
            var isTopLevel = Ext.getCmp('MetadataInfoIsTopLevel');
            isTopLevel.setValue("false");
         }
          if(v != ""){   
             var pId = Ext.getCmp('MetadataInfoLanguageId');
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
     name :"MetaDataCreatedDate",
           id:"MetadataInfoMetaDataCreatedDate",
                    fieldLabel : "MetaData Created Date",
     xtype : "datetimefield",
	                                                         allowBlank : true,
	                                                         editable:false
                   
},

                                             {
     name :"PublishedDate",
           id:"MetadataInfoPublishedDate",
                    fieldLabel : "Published Date",
     xtype : "datetimefield",
	                                                         allowBlank : true,
	                                                         editable:false
                   
},

                                             {
     name :"ValidDate",
           id:"MetadataInfoValidDate",
                    fieldLabel : "Valid Date",
     xtype : "datetimefield",
	                                                         allowBlank : true,
	                                                         editable:false
                   
},

                                             {
     name :"LastModifiedDate",
           id:"MetadataInfoLastModifiedDate",
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
     name :"JurisdictionName",
           id:"JurisdictionJurisdictionName",
               editable:false,
                 fieldLabel : "Jurisdiction Name *",
                    xtype:"combobox",
          editable:false,
                                          store:createStore('JurisdictionType', ''),
                     queryMode:"remote",
     displayField:"JurisdictionTypeName",
     valueField:"JurisdictionTypeName",
          emptyText:"",
                                                        allowBlank : false 
                   
},

                                             {
     name :"JurisdictionStateId",
           id:"JurisdictionJurisdictionStateId",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "JurisdictionStateId",
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
     name :"JurisdictionStateName",
           id:"JurisdictionJurisdictionStateName",
               editable:false,
                 fieldLabel : "Jurisdiction State Name *",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
                                                        allowBlank : false 
                   
},

                                             {
     name :"JurisdictionDistrictId",
           id:"JurisdictionJurisdictionDistrictId",
               readOnly:true,
                         enforceMaxLength:true,
     xtype:"textfield",
     fieldLabel : "JurisidictionDistrictId",
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
     name :"JurisdictionDistrictName",
           id:"JurisdictionJurisdictionDistrictName",
               editable:false,
                 fieldLabel : "Jurisdiction District Name *",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
	focus: function(){
	  	this.setValue('');
		this.store.currentPage = 1;
	  	var sname = Ext.getCmp("JurisdictionJurisdictionStateName").getValue();
		if(!Ext.isEmpty(sname) && sname !== 'All India'){
			this.store.load({params:{"ParentGoiDirItemName":'"'+ sname + '"'}});
		}
		else{
			this.store.load();
		}
	},
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              } 
       },    
                                                        allowBlank : false 
                   
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
                                                   allowBlank : true
                   
},

                                             {
     name :"SpatialStateName",
           id:"SpatialSpatialStateName",
               editable:false,
                 fieldLabel : "Spatial State Name",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
                                                        allowBlank : true
                   
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
                                                   allowBlank : true
                   
},

                                             {
     name :"SpatialDistrictName",
           id:"SpatialSpatialDistrictName",
               editable:false,
                 fieldLabel : "Spatial District Name",
                    xtype:"combobox",
          editable:false,
                                                                      store:createQueryStore('GoiDirItem', 'category="State District"', 'GoiDirItemName'),
               width:500,
                pageSize:40,
                typeAhead:true,
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
	focus: function(){
		this.setValue('');
		var sname = Ext.getCmp("SpatialSpatialStateName").getValue();
		this.store.currentPage = 1; //reset page
		if(!Ext.isEmpty(sname) && sname != 'All India'){
			this.store.load({params:{"ParentGoiDirItemName":'"'+ sname + '"'}});
		}		
		else{
			this.store.load();
		}
	},
       render: function(combo,options){
                this.store.sort('GoiDirItemName','ASC');
              } 
       },    
                                                        allowBlank : true
                   
},

                                                       {
     name :"CoverageTemporal",
           id:"MetadataInfoCoverageTemporal",
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
                allowBlank : true
                   
},

                                                                  {
     name :"AudienceCategory",
           id:"MetadataInfoAudienceAudienceCategory",
               editable:false,
                 fieldLabel : "Audience *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                    store:createStore('AudienceCategory', ''),
                     queryMode:"remote",
     displayField:"AudienceCategoryName",
     valueField:"AudienceCategoryName",
          emptyText:"",
                listeners:{
           beforerender:function(combo){
	      this.store.load();
           },
           render:function(combo){
	      this.store.sort('AudienceCategoryName','ASC');
           }
        },
                                                   allowBlank : false 
                   
},

                                                                       {
     name :"CategoryType",
           id:"MetadataInfoCategoryTypesCategoryType",
               editable:false,
                 fieldLabel : "Type Category *",
                    xtype:"combobox",
          editable:false,
               multiSelect:true,
                                    store:createStore('Category', ''),
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
                                                   allowBlank : false 
                   
},

                                                  {
     name :"ConformsTo",
           id:"MetadataInfoConformsTo",
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
                allowBlank : true
                   
},

                                                                  {
     name :"HasPart",
           id:"MetadataInfoHasPartsHasPart",
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
                allowBlank : true
                   
},

                                                  {
     name :"IsPartOf",
           id:"MetadataInfoIsPartOf",
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
                allowBlank : true
                   
},

                                             {
     name :"HasVersion",
           id:"MetadataInfoHasVersion",
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
                allowBlank : true
                   
},

                                             {
     name :"IsVersionOf",
           id:"MetadataInfoIsVersionOf",
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
                allowBlank : true
                   
},
                      {
     name :"Source",
           id:"MetadataInfoSource",
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
                   
}                   
];

var DependentContactNumberForm = [
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
                                                                                                   vtype:"alphanumeric",
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
                                                                                                   vtype:"alphanumeric",
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
                                                                                                   vtype:"alphanumeric",
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
                           
}];

 var NpiMetadataForm = [
	{
		 title:'NpiMetadata',
		 xtype:'form',
		 id:'NpiMetadataForm1',
		 autoScroll:true,
		 fieldDefaults:{
		   labelWidth:155
		 },
	 items:[DependentNpiMetadataForm]
	}
];

function getWindowHeight(){
  var windowHeight;
  if(typeof( window.innerHeight ) == 'number')  
      windowHeight = window.innerHeight;                     // the more standards compliant browsers (mozilla/netscape/opera/IE7)
  else if(document.documentElement &&  document.documentElement.clientHeight) 
      windowHeight = document.documentElement.clientHeight;  //IE 6+ in 'standards compliant mode' 
  else if(document.body && document.body.clientHeight) 
      windowHeight = document.body.clientHeight;             //IE 4 compatible 
  
  windowHeight = (windowHeight-(windowHeight/100)*10);    
  return windowHeight;     
}
