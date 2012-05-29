/* Check user authenticated or not */
if(getCookie("uidomainname") != domainName){
   logout();
}
else{
  myMask = new Ext.LoadMask(Ext.getBody(), {msg:"Loading please wait..."});
  myMask.show(); 
}

if(!userRole && !cmsApiKey && getCookie("cmsmodules") == null)  {
  window.location = loginUrl;
}
