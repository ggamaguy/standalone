function tableClick(value) {
	var id = value;
	for (var i = 1; i <= 13; i++) {
		var str = i + "-row";
		if ($("#" + str).attr("class") == "active") {
			$("#" + str).attr("class", "");
		}
	}
	$("#" + id).attr("class", "active");
	if (id == "1-row") {
		$("#dbIO input").remove();
		$("#dbIO").append(
						"<input type=\"button\" value=\"신규\" class=\"btn btn-primary pull-right\" " +
						"onclick=\"newSite()\" /><input type=\"button\" value=\"저장\"class=\"btn " +
						"btn-primary pull-right\" onclick=\"saveSite()\" /><input type=\"button\" " +
						"value=\"삭제\"class=\"btn btn-primary pull-right\" onclcik=\"deleteSite()\" />");
		$("#inner-content table").remove();
		$("#inner-content").append("<table class=\"table\"><thead><tr><th>No</th><th>사업장 코드</th><th>사업장 명</th></tr></thead><table>");
		
		var url = "getAllSites";
		$.ajax({
					type : 'get',
					url : url, // Pass you Servlet Mapping / JSP Url
					dataType : 'json',

					success : function(data) {
						$("#inner-content table tbody").remove();
						$("#inner-content table").append("<tbody></tbody>");
						for (var i = 0; i < data.num; i++) {
							var code = data.content[i].siteCode;
							var name = data.content[i].siteName;
							$("#inner-content table tbody")
									.append(
											"<tr id=\"row"
													+ (i + 1)
													+ "\" onclick=\"dataTableClick(this.id);\"><td>"
													+ (i + 1)
													+ "</td>"
													+ "<td><label class=\"siteCode\" value=\""
													+ code
													+ "\">"
													+ code
													+ "</label></td>"
													+ "<td><label class=\"siteName\" value=\""
													+ name + "\">" + name
													+ "</label></td></tr>");
						}
					},
					error : function(request, textStatus, errorThrown) {
						alert(request.status + ', Error: ' + request.statusText);
						// perform tasks for error
					}
				});
		

	}
	if (id == "2-row") {

	}
	if (id == "3-row") {

	}
	if (id == "4-row") {

	}
	if (id == "5-row") {
	}
	if (id == "6-row") {
	}
	if (id == "7-row") {
	}
	if (id == "8-row") {
	}
	if (id == "9-row") {
	}
	if (id == "10-row") {
	}
	if (id == "11-row") {
	}
	if (id == "12-row") {
	}
	if (id == "13-row") {
	}

}
function dataTableClick(value) {

	var code = $("#" + value + " .siteCode").attr("value");
	var name = $("#" + value + " .siteName").attr("value");

	$("#siteCodeInput").val(code);
	$("#siteNameInput").val(name);
}
function newSite() {
	if ($("#siteCodeInput").val() == null || $("#siteNameInput").val() == null) {
		alert("모두 입력해 주세요");
	} else {
		var url = "insertSite";
		var siteCode = $("#siteCodeInput").val();
		var siteName = $("#siteNameInput").val();

		$.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						siteCode : siteCode,
						siteName : siteName
					}, // This will be passed as parameter to server
						// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						$("#inner-content table tbody").remove();
						$("#inner-content table").append("<tbody></tbody>");
						for (var i = 0; i < data.num; i++) {
							var code = data.content[i].siteCode;
							var name = data.content[i].siteName;
							$("#inner-content table tbody")
									.append(
											"<tr id=\"row"
													+ (i + 1)
													+ "\" onclick=\"dataTableClick(this.id);\"><td>"
													+ (i + 1)
													+ "</td>"
													+ "<td><label class=\"siteCode\" value=\""
													+ code
													+ "\">"
													+ code
													+ "</label></td>"
													+ "<td><label class=\"siteName\" value=\""
													+ name + "\">" + name
													+ "</label></td></tr>");
						}
					},
					error : function(request, textStatus, errorThrown) {
						alert(request.status + ', Error: ' + request.statusText);
						// perform tasks for error
					}
				});
	}
}
function saveSite() {
	if ($("#siteCodeInput").val() == null || $("#siteNameInput").val() == null) {
		alert("모두 입력해 주세요");
	} else {
		var url = "updateSite";
		var siteCode = $("#siteCodeInput").val();
		var siteName = $("#siteNameInput").val();

		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						siteCode : siteCode,
						siteName : siteName
					}, // This will be passed as parameter to server
						// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						$("#inner-content table tbody").remove();
						$("#inner-content table").append("<tbody></tbody>");
						for (var i = 0; i < data.num; i++) {
							var code = data.content[i].siteCode;
							var name = data.content[i].siteName;
							$("#inner-content table tbody")
									.append(
											"<tr id=\"row"
													+ (i + 1)
													+ "\" onclick=\"dataTableClick(this.id);\"><td>"
													+ (i + 1)
													+ "</td>"
													+ "<td><label class=\"siteCode\" value=\""
													+ code
													+ "\">"
													+ code
													+ "</label></td>"
													+ "<td><label class=\"siteName\" value=\""
													+ name + "\">" + name
													+ "</label></td></tr>");

						}
					},
					error : function(request, textStatus, errorThrown) {
						alert(request.status + ', Error: ' + request.statusText);
						// perform tasks for error
					}
				});
	}
}
function deleteSite() {
	if ($("#siteCodeInput").val() == null || $("#siteNameInput").val() == null) {
		alert("모두 입력해 주세요");
	} else {
		var url = "deleteSite";
		var siteCode = $("#siteCodeInput").val();
		var siteName = $("#siteNameInput").val();

		$
				.ajax({
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						type : 'post',
						siteCode : siteCode,
						siteName : siteName
					}, // This will be passed as parameter to server
						// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						$("#inner-content table tbody").remove();
						$("#inner-content table").append("<tbody></tbody>");
						for (var i = 0; i < data.num; i++) {
							var code = data.content[i].siteCode;
							var name = data.content[i].siteName;
							$("#inner-content table tbody")
									.append(
											"<tr id=\"row"
													+ (i + 1)
													+ "\" onclick=\"dataTableClick(this.id);\"><td>"
													+ (i + 1)
													+ "</td>"
													+ "<td><label class=\"siteCode\" value=\""
													+ code
													+ "\">"
													+ code
													+ "</label></td>"
													+ "<td><label class=\"siteName\" value=\""
													+ name + "\">" + name
													+ "</label></td></tr>");
						}
					},
					error : function(request, textStatus, errorThrown) {
						alert(request.status + ', Error: ' + request.statusText);
						// perform tasks for error
					}
				});
	}
}

function subGroupDataTableClick(value){
	var upperGorupName = $("#" + value + " .upperGroupName").attr("value");
	var uppergroupCode = $("#" + value + " .groupCode").attr("value").split('/')[0];
	var subgroupCode = $("#" + value + " .groupCode").attr("value").split('/')[1];
	var groupName = $("#" + value + " .groupName").attr("value");
	
	$("#subGroupCodeHiden").val(subgroupCode);
	$("#upperGroupCodeHiden").val(uppergroupCode);
	
	$("#upperGroupNameInput").replaceWith(
			"<button type=\"button\" id=\"upperGroupNameInput\""+
				"class=\"btn btn-default dropdown-toggle\""+
				"data-toggle=\"dropdown\"><p>"+upperGorupName+"</p><span class=\"caret\"></span></button>"
			
	);

	$("#subGroupCodeInput").val(uppergroupCode+subgroupCode);
	$("#subGroupNameInput").val(groupName);
}
function newSubGroup(){
	alert($("#upperGroupNameInput p").html());
	var upperGroupName = $("#upperGroupNameInput p").html();
	var upperGroupCode = $("#upperGroupCodeHiden").val();
	var subGroupCode = $("#subGroupCodeHiden").val();
	var subGroupName = $("#subGroupNameInput").val();

	var url = "insertsubgroup";
	response.sendRedirect("/insertsubgroup?upperGroupName="+upperGroupName+"&upperGroupCode="+upperGroupCode+
	"&subGroupCode="+subGroupCode+"&subGroupName="+subGroupName);


	
}