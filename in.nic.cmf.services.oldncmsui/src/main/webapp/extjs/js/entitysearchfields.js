    Ext.PageFields = [ 
        ['TemplateName'],
        ['PageName'],
        ["Site"],
        ["CurrentUser"],
        ["LastModifiedDate"],
        ["FilePath"],
        ["LastModifiedBy"],
        ["Status"],
        ["CurrentEvent"],
        ["NextPossibleEvent"],
        ["CreatedDate"],
        ["CreatedBy"],
        ["Id"],
        ["Version"],
        ["Stage"]
    ]; 
    Ext.PageAssociatorFields = [ 
        ['IaName'],
        ['PageName'],
        ['PageEntityName'],
        ["Site"],
        ["CurrentUser"],
        ["LastModifiedDate"],
        ["FilePath"],
        ["LastModifiedBy"],
        ["Status"],
        ["CurrentEvent"],
        ["NextPossibleEvent"],
        ["CreatedDate"],
        ["CreatedBy"],
        ["Id"],
        ["Version"],
        ["Stage"]
    ];
    
    Ext.EventFields = [
        ['Id'],
        ['EventName'],
		['DisplayName'],
		['CreatedDate'],
        ['LastModifiedDate'],
        ['CreatedBy'],
        ['LastModifiedBy'],
        ["Site"],
        ["Version"]
    ]; 
   Ext.WorkflowFields = [
        ['Id'], 
        ['WorkflowName'],
	['CreatedDate'],
        ['LastModifiedDate'],
        ['CreatedBy'],
        ['LastModifiedBy'],
        ["Site"],
        ["Version"]
    ];
   Ext.WorkflowModelFields = [
        ['Id'], 
        ['WorkflowModelName'],
        ['CreatedDate'],
        ['LastModifiedDate'],
        ['CreatedBy'],
        ['LastModifiedBy'],
        ["Site"],
        ["Version"]
    ];
   Ext.WorkflowModelMapFields = [
        ['Id'], 
        ['ModelMapName'],
        ['WorkflowName'],
        ['CreatedDate'],
        ['LastModifiedDate'],
        ['CreatedBy'],
        ['LastModifiedBy'],
        ["Site"],
        ["Version"]
    ];
    Ext.WorkflowUserMapFields = [
        ['Id'], 
        ['WorkflowName'],
        ['CreatedDate'],
        ['LastModifiedDate'],
        ['CreatedBy'],
        ['LastModifiedBy'],
        ["Site"],
        ["Version"]
    ];
    
    var arrayCombineFields = [
      {name:'AND',value:'AND'},
      {name:'OR',value:'OR'}
    ];
    var arrayEqualFields = [
      {name:'=',value:'='},
      {name:'!=',value:'!='}
    ];  
