function getAllEnergyUse(){
	$("#dbIO input").remove();
	$("#dbIO")
			.append(
					"<input type=\"button\" value=\"신규\" class=\"btn btn-primary pull-right\" "
							+ "onclick=\"insertEnergyUse()\" /><input type=\"button\" value=\"저장\"class=\"btn "
							+ "btn-primary pull-right\" onclick=\"updateEnergyUse()\" /><input type=\"button\" "
							+ "value=\"삭제\"class=\"btn btn-primary pull-right\" onclick=\"deleteEnergyUse()\" />");
	$("#inner-content table").remove();
	$("#inner-content")
			.append(
					"<table class=\"table\"><thead><tr><th>No</th><th>에너지 이용 코드</th><th>에너지 이용 명</th></tr></thead><table>");
	$("#inputform div").remove();
	$("#inputform").append(	"<div class=\"form-group\">"+
	"<label for=\"energyUseCode\" class=\"sr-only\">에너지 이용 코드</label> <input "+
	"id=\"energyUseCodeInput\" type=\"text\" class=\"form-control\" placeholder=\"에너지 이용 코드\"></div>"+
	"<div class=\"form-group\"><label for=\"energyUseName\" class=\"sr-only\">에너지 이용 명</label> <input "+
	"id=\"energyUseNameInput\" type=\"text\" class=\"form-control\" placeholder=\"에너지 이용 명\"></div>");
	
	var url = "getEnergyUse";
	$
			.ajax({
				type : 'get',
				url : url, // Pass you Servlet Mapping / JSP Url
				dataType : 'json',

				success : function(data) {
					$("#inner-content .table tbody").remove();
					$("#inner-content .table").append("<tbody></tbody>");
					for (var i = 0; i < data.rowNum; i++) {
						var code = data.table[i].code;
						var name = data.table[i].name;
						$("#inner-content .table tbody")
								.append(
										"<tr id=\"row"
												+ (i + 1)
												+ "\" onclick=\"energyUseTableClick(this.id);\"><td>"
												+ (i + 1)
												+ "</td>"
												+ "<td><label class=\"code\" value=\""
												+ code
												+ "\">"
												+ code
												+ "</label></td>"
												+ "<td><label class=\"name\" value=\""
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
function insertEnergyUse(){
	if ($("#energyUseCodeInput").val() == null || $("#energyUseNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "insertEnergyUse";
		var code = $("#energyUseCodeInput").val();
		var name = $("#energyUseNameInput").val();
		alert(code+name);
		//alert(siteCode+siteName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						code : code,
						name : name
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllEnergyUse();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllEnergyUse();
						// perform tasks for error
					}
				});
	}
}
function updateEnergyUse(){
	if ($("#energyUseCodeInput").val() == null || $("#energyUseNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "updateEnergyUse";
		var code = $("#energyUseCodeInput").val();
		var name = $("#energyUseNameInput").val();
		//alert(siteCode+siteName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						code : code,
						name : name
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllEnergyUse();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllEnergyUse();
						// perform tasks for error
					}
				});
	}
}
function deleteEnergyUse(){
	if ($("#energyUseCodeInput").val() == null || $("#energyUseNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "deleteEnergyUse";
		var code = $("#energyUseCodeInput").val();
		var name = $("#energyUseNameInput").val();
		//alert(siteCode+siteName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						code : code,
						name : name
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllEnergyUse();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllEnergyUse();
						// perform tasks for error
					}
				});
	}
}
function energyUseTableClick(value){
	var code = $("#" + value + " .code").attr("value");
	var name = $("#" + value + " .name").attr("value");

	$("#energyUseCodeInput").val(code);
	$("#energyUseNameInput").val(name);
}