function getAllUpperGroup(){
	$("#dbIO input").remove();
	$("#dbIO")
			.append(
					"<input type=\"button\" value=\"신규\" class=\"btn btn-primary pull-right\" "
							+ "onclick=\"insertUpperGroup()\" /><input type=\"button\" value=\"저장\"class=\"btn "
							+ "btn-primary pull-right\" onclick=\"updateUpperGroup()\" /><input type=\"button\" "
							+ "value=\"삭제\"class=\"btn btn-primary pull-right\" onclick=\"deleteUpperGroup()\" />");
	$("#inner-content table").remove();
	$("#inner-content").append("<table class=\"table\"><thead><tr><th>No</th><th></th><th>본부 코드</th><th>본부 명</th>" +
	"</tr></thead><table>");
	$("#inputform div").remove();
	$("#inputform").append("<div class=\"form-group\">"+
			"<label for=\"siteCode\" class=\"sr-only\">사업장 코드</label>" +
			"<select class = \"selectBox\" id=\"selectSiteCode\"></select></div>"+	
			"<div class=\"form-group\">"+
			"<label for=\"upperGroupCode\" class=\"sr-only\">본부 코드</label> <input "+
			"id=\"upperGroupCodeInput\" type=\"text\" class=\"form-control\" placeholder=\"본부 코드\"></div>"+
			"<div class=\"form-group\"><label for=\"upperGroupName\" class=\"sr-only\">본부 명</label> <input "+
			"id=\"upperGroupNameInput\" type=\"text\" class=\"form-control\" placeholder=\"본부 명\"></div>");
	var url = "getAllUpperGroup";
	var url2 = "getAllSites";	
	$
			.ajax({
				type : 'get',
				url : url, // Pass you Servlet Mapping / JSP Url
				dataType : 'json',

				success : function(data) {
					$("#inner-content .table tbody").remove();
					$("#inner-content .table").append("<tbody></tbody>");
					for (var i = 0; i < data.rowNum; i++) {
						var siteCode = data.table[i].siteCode;
						var groupCode = data.table[i].groupCode;
						var groupName = data.table[i].groupName;
						$("#inner-content .table tbody")
								.append(
										"<tr id=\"row"
												+ (i + 1)
												+ "\" onclick=\"upperGroupDataTableClick(this.id);\"><td>"
												+ (i + 1)
												+ "</td>"
												+"<td><label class=\"upperGroupSiteCode sr-only\" style=\"width:0px\">"
												+ siteCode+"<input type=\"hidden\" value=\""+siteCode+"\">"
												+ "</label></td>"
												+ "<td><label class=\"upperGroupCode\">"
												+ groupCode+"<input type=\"hidden\" value=\""+groupCode+"\">"
												+ "</label></td>"
												+ "<td><label class=\"upperGroupName\">" + groupName
												+"<input type=\"hidden\" value=\""+groupName+"\">"
												+ "</label></td></tr>");
					}
				},
				error : function(request, textStatus, errorThrown) {
					//alert(request.status + ', Error: ' + request.statusText);
					// perform tasks for error
				}
			});
	
	$
			.ajax({
				type : 'get',
				url : url2, // Pass you Servlet Mapping / JSP Url
				dataType : 'json',

				success : function(data) {
					for (var i = 0; i < data.rowNum; i++){
					var code = data.table[i].siteCode;
					var name = data.table[i].siteName;
					$("#selectSiteCode").append(
							"<option value=\""+code+"\"><a>"+name+"</a></option>");
					}
				},
				error : function(request, textStatus, errorThrown) {
					//alert(request.status + ', Error: ' + request.statusText);
					// perform tasks for error
				}
			});
}
function upperGroupDataTableClick(value){
	var siteCode = $("#"+value+" .upperGroupSiteCode input").val();
	var groupCode = $("#"+value+" .upperGroupCode input").val();
	var groupName = $("#"+value+" .upperGroupName input").val();
	$("#selectSiteCode").val(siteCode);
	$("#upperGroupCodeInput").val(groupCode);
	$("#upperGroupNameInput").val(groupName);	
}
function insertUpperGroup(){
	if ($("#upperGroupCodeInput").val() == null || $("#upperGroupNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "insertUpperGroup";
		var siteCode = $("#selectSiteCode").val();
		var groupCode = $("#upperGroupCodeInput").val();
		var groupName = $("#upperGroupNameInput").val();
		//alert(siteCode +groupCode+groupName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						siteCode :siteCode,
						groupCode : groupCode,
						groupName : groupName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllUpperGroup();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllUpperGroup();
						// perform tasks for error
					}
				});
	}
}
function deleteUpperGroup(){
	if ($("#upperGroupCodeInput").val() == null || $("#upperGroupNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "deleteUpperGroup";
		var siteCode = $("#selectSiteCode").val();
		var groupCode = $("#upperGroupCodeInput").val();
		var groupName = $("#upperGroupNameInput").val();
		//alert(siteCode+groupCode+groupName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						siteCode :siteCode,
						groupCode : groupCode,
						groupName : groupName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllUpperGroup();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllUpperGroup();
						// perform tasks for error
					}
				});
	}
}
function updateUpperGroup(){
	if ($("#upperGroupCodeInput").val() == null || $("#upperGroupNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "updateUpperGroup";
		var siteCode = $("#selectSiteCode").val();
		var groupCode = $("#upperGroupCodeInput").val();
		var groupName = $("#upperGroupNameInput").val();
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						siteCode :siteCode,
						groupCode : groupCode,
						groupName : groupName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllUpperGroup();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllUpperGroup();
						// perform tasks for error
					}
				});
	}
}
