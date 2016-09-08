function getUpperGroup(value){
    var siteCode = value;
    if(value != null){
    	var url = "retrieveUpperGroup";
        $.ajax({
                url : url, // Pass you Servlet Mapping / JSP Url
                data : {
                	siteCode : siteCode
                }, // This will be passed as parameter to server (JSP/Servlet)
                dataType : 'json',
                success : function(data) {
                	//alert(data.content[0].groupCode+"aaaaaaaa"+data.content[0].groupName);
                	for(var i = 0; i < data.num; i++){
                		var name = data.content[i].groupName;
                		var code = data.content[i].groupCode;
                		$("#upperGroup").append("<option value=\""+code+"\">"+name+"</option>");
                	}
                },
                error : function(request, textStatus, errorThrown) {
                    alert(request.status + ', Error: ' + request.statusText);
                     // perform tasks for error 
                }
            });
    }else{
    	
    }
    
}