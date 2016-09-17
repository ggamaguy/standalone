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
		getAllSites();
	}
	if (id == "2-row") {
		
	}
	if (id == "3-row") {
		getAllUpperGroup();
	}
	if (id == "4-row") {
		getAllSubGroup();
	}
	if (id == "5-row") {
	}
	if (id == "6-row") {
		getAllEnergyUse();
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
function SiteTableClick(value) {

	var companyCode = $("#" + value + " .companyCode").attr("value");
	var code = $("#" + value + " .siteCode").attr("value");
	var name = $("#" + value + " .siteName").attr("value");

	$("#companyCodeInput").val(companyCode);
	$("#siteCodeInput").val(code);
	$("#siteNameInput").val(name);
}
function insertSite() {
	if ($("#siteCodeInput").val() == null || $("#siteNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "insertSite";
		var companyCode = $("#companyCodeInput").val();
		var siteCode = $("#siteCodeInput").val();
		var siteName = $("#siteNameInput").val();
		//alert(siteCode+siteName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						companyCode : companyCode,
						siteCode : siteCode,
						siteName : siteName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllSites();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllSites();
						// perform tasks for error
					}
				});
	}
}
function updateSite() {
	if ($("#siteCodeInput").val() == null || $("#siteNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "updateSite";
		var companyCode = $("#companyCodeInput").val();
		var siteCode = $("#siteCodeInput").val();
		var siteName = $("#siteNameInput").val();

		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						companyCode:companyCode,
						siteCode : siteCode,
						siteName : siteName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						$("#inner-content .table tbody").remove();
						$("#inner-content .table").append("<tbody></tbody>");
						getAllSites();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllSites();
					}
				});
	}
}
function deleteSite() {
	if ($("#siteCodeInput").val() == null || $("#siteNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "deleteSite";
		var companyCode = $("#companyCodeInput").val();
		var siteCode = $("#siteCodeInput").val();
		var siteName = $("#siteNameInput").val();
		//alert(siteCode+siteName);	
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						companyCode : companyCode,
						siteCode : siteCode,
						siteName : siteName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllSites();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						// perform tasks for error
					}
				});
	}
}
function getAllSites() {
	$("#dbIO input").remove();
	$("#dbIO")
			.append(
					"<input type=\"button\" value=\"신규\" class=\"btn btn-primary pull-right\" "
							+ "onclick=\"insertSite()\" /><input type=\"button\" value=\"저장\"class=\"btn "
							+ "btn-primary pull-right\" onclick=\"updateSite()\" /><input type=\"button\" "
							+ "value=\"삭제\"class=\"btn btn-primary pull-right\" onclick=\"deleteSite()\" />");
	$("#inner-content table").remove();
	$("#inner-content")
			.append(
					"<table class=\"table table-bordered\"><thead><tr><th>No</th><th>회사 코드</th><th>사업장 코드</th><th>사업장 명</th></tr></thead><table>");
	$("#inputform div").remove();
	$("#inputform").append(	"<div class=\"form-group\">"+
	"<label for=\"companyCode\" class=\"sr-only\">회사 코드</label> <input "+
	"id=\"companyCodeInput\" type=\"text\" class=\"form-control\" placeholder=\"회사 코드\" readonly></div>"+
	"<div class=\"form-group\"><label for=\"siteCode\" class=\"sr-only\">사업장 코드</label> <input "+
	"id=\"siteCodeInput\" type=\"text\" class=\"form-control\" placeholder=\"사업장 코드\"></div>"+
	"<div class=\"form-group\"><label for=\"siteName\" class=\"sr-only\">사업장 명</label> <input "+
	"id=\"siteNameInput\" type=\"text\" class=\"form-control\" placeholder=\"사업장 명\"></div>");
	
	var url = "getAllSites";
	$
			.ajax({
				type : 'get',
				url : url, // Pass you Servlet Mapping / JSP Url
				dataType : 'json',

				success : function(data) {
					$("#inner-content .table tbody").remove();
					$("#inner-content .table").append("<tbody></tbody>");
					for (var i = 0; i < data.rowNum; i++) {
						var companyCode = data.table[i].companyCode;
						var code = data.table[i].siteCode;
						var name = data.table[i].siteName;
						$("#inner-content .table tbody")
								.append(
										"<tr id=\"row"
												+ (i + 1)
												+ "\" onclick=\"SiteTableClick(this.id);\"><td>"
												+ (i + 1)
												+ "</td>"
												+ "<td><label class=\"companyCode\" value=\""
												+ companyCode
												+ "\">" + companyCode + "</label></td>"
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
					//alert(request.status + ', Error: ' + request.statusText);
					// perform tasks for error
				}
			});

}
