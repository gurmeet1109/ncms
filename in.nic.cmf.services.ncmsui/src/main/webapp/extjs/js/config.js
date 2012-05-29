/**
 * @author Ulaganathan Ramesh, Suresh KB
 * JS configuration file
 * Copyright Sify Software Ltd.
 */
var pathName = location.pathname; // Path name of NCMSUI url
var host = location.protocol + "//" + location.host; 
var pathNameArray = pathName.split("/");
var domainName = pathNameArray[3]; // domain of NCMSUI loaded
var extPath = "/" + pathNameArray[1] + "/extjs"; // exjs app location
var cmsPath = "/" + pathNameArray[1] + "/" + pathNameArray[2] 
				+ "/" + pathNameArray[3]; 
var mediaPath = "/" + "appflow";
var dataservicesPath = "/" + "appflow" + "/" + pathNameArray[3] 
var loginUrl = cmsPath + "/login"; // login page URL
var stagingSiteUrl = "https://staging." + domainName + "/"; // Preview site URL
var dataPath = "/" + pathNameArray[1] + "/extjs/data"; 
var imagePath = "/" + pathNameArray[1] + "/extjs/images";
var itemsPerPage = 20; // No of records loaded in the grid 
var authPostUrl = "/auth/" + domainName + "/Authentication?format=extjson";
var authUrl = "/auth/" + domainName + "/login"; 
var cmsUserName = getCookie("authusername"); 
var userRole = getCookie("aclrole");
var cmsApiKey = getCookie("api_key");
var masterDataDomainEntities = ["GoiDirItem"];
var apacheNcmsData = "/context/" + masterDataDomainName + "/data";
//var apacheNcmsData = dataservicesPath + "/static";
var aclStatus = ""; //= "&status=published"
var aclSubQuery = "&createdby=" + cmsUserName + aclStatus;
Ext.Ajax.timeout = 30000; 
var publisherRoleName = 'Publisher';
var applyStageColor = true;
var accessMasterDataDomain = true;
var domainSpecifMasterData = [
                              "DomainEntity",
                              "InformationArchitecture",
                              "Stage",
                              "Action",
                              "Workflow",
                              "Role",
                              "Template",
                              "Status",
                              "Page",
                              "PageAssociator"
                             ];
var domainSpecificMasterDataUrl = "/" + "appflow" + "/" + pathNameArray[2] 
				+ "/" + domainName + "/static";
var masterDataDSUrl = "/" + "appflow" + "/" + pathNameArray[2] 
+ "/" + masterDataDomainName;
// Alert messages definition
var NICAlerts = {};
NICAlerts.stageTransitionSuccessHeading = "Success";
NICAlerts.stageTransitionSuccessMessage = "Stage moved successfully";
NICAlerts.stageTransitionFailureHeading = "Request Failed";
NICAlerts.validationErrorHeading = "Validation Error";

"use strict"; // Variable declartaion check
