/**
 * @author Ulaganathan Ramesh, Suresh KB
 * Copyright Sify Software Ltd.
 */
/**
 * Returns the default filterby menu this will be overwritten if users creates 
 * a Stage or InformationArchitecture
 * @param currentMenuHandler {String}
 * @returns Ext.menu.Menu
 */
function filterByMenuCommon(currentMenuHandler) {

    var filterByMenu = Ext.create('Ext.menu.Menu', {
        items: [{
            text: "List Data",
            menu: {
                items: [{
                    text: "CreatedBy Me",
                    group: "createdby",
                    handler: Ext.JSON.decode(currentMenuHandler)
                }, {
                    text: "AssignedTo Me",
                    group: "assignedtouser",
                    handler: Ext.JSON.decode(currentMenuHandler)
                }, {
                    text: "All Published",
                    group: "status",
                    handler: Ext.JSON.decode(currentMenuHandler)
                }, {
                    text: 'Created Today',
                    group: 'today',
                    handler: Ext.JSON.decode(currentMenuHandler)
                }, {
                    text: 'Last 7 Days',
                    group: 'lastweek',
                    handler: Ext.JSON.decode(currentMenuHandler)
                }, {
                    text: 'Last 30 Days',
                    group: 'lastmonth',
                    handler: Ext.JSON.decode(currentMenuHandler)
                }]
            }
        }],
        scope: this
    });
    return filterByMenu;
}