function filterByMenuCommon(currentMenuHandler){
 
var filterByMenu = Ext.create('Ext.menu.Menu', {items: [
                                                  {   
													   text: 'List Data',
													   menu:{
													      items:[
														{
														  text:'CreatedBy Me',
														  group:'createdby',
														  handler: Ext.JSON.decode(currentMenuHandler)
														},
														{
														  text:'AssignedTo Me',
														  group:'user',
														  handler: Ext.JSON.decode(currentMenuHandler)
														},
														{
															  text:'All Published',
															  group:'status',
															  handler: Ext.JSON.decode(currentMenuHandler)
															}
													     ] 
													  }
												  }
                                                  ],scope :this });
      return filterByMenu;
  }

//includeJavascript(apacheNcmsData + '/menu.js');