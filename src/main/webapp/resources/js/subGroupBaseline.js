function insertSubGroup(){
	if ($("#subGroupCodeInput").val() == null || $("#subGroupNameInput").val() == null) {
		alert("모두 입력해 주세요");
	} else {
		alert(upperGroupCode +" "+subGroupCode +" "+subGroupName);
		var url = "insertSubGroup";
		var upperGroupCode = $("#selectUpperGroup").val();
		var subGroupCode = $("#subGroupCodePart").html();
		var subGroupName = $("#subGroupNameInput").val();
		alert(upperGroupCode +subGroupCode+subGroupName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						upperGroupCode :upperGroupCode,
						subGroupCode : subGroupCode,
						subGroupName : subGroupName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllSubGroup();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllSubGroup();
						// perform tasks for error
					}
				});
	}
}
function deleteSubGroup(){
	if ($("#selectUpperGroup").val() == null || $("#subGroupNameInput").val() == null) {
		alert("모두 입력해 주세요");
	} else {
		var url = "deleteSubGroup";
		var upperGroupCode = $("#selectUpperGroup").val();
		var subGroupCode = $("#subGroupCodePart").html();
		var subGroupName = $("#subGroupNameInput").val();
		alert(upperGroupCode +subGroupCode+subGroupName);
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						upperGroupCode :upperGroupCode,
						subGroupCode : subGroupCode,
						subGroupName : subGroupName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllSubGroup();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllSubGroup();
						// perform tasks for error
					}
				});
	}
}
function updateSubGroup(){
	if ($("#subGroupCodeInput").val() == null || $("#subGroupNameInput").val() == null) {
		//alert("모두 입력해 주세요");
	} else {
		var url = "updateSubGroup";
		var upperGroupCode = $("#selectUpperGroup").val();
		var subGroupCode = $("#subGroupCodePart").html();
		var subGroupName = $("#subGroupNameInput").val();
		
		$
				.ajax({
					type : 'post',
					url : url, // Pass you Servlet Mapping / JSP Url
					data : {
						upperGroupCode :upperGroupCode,
						subGroupCode : subGroupCode,
						subGroupName : subGroupName
					}, // This will be passed as parameter to server
					// (JSP/Servlet)
					dataType : 'json',

					success : function(data) {
						getAllSubGroup();
					},
					error : function(request, textStatus, errorThrown) {
						//alert(request.status + ', Error: ' + request.statusText);
						getAllSubGroup();
						// perform tasks for error
					}
				});
	}
}
function subGroupDataTableClick(value) {
	var upperGroupName = $("#"+value+" .upperGroupName input").val();
	var groupCode = $("#"+value+" .groupCode input").val();
	var upperGroupCode = $("#"+value+" .groupCode input").val().split('/')[0];
	var subGroupCode = $("#"+value+" .groupCode input").val().split('/')[1];
	var subGroupName = $("#"+value+" .subGroupName input").val();
	
	$("#selectUpperGroup").val(upperGroupCode);
	$("#subGroupCodePart").html(subGroupCode);
	$("#subGroupCodeInput").val(upperGroupCode+subGroupCode);
	$("#subGroupNameInput").val(subGroupName);
	
}
function getAllSubGroup(){
	$("#dbIO input").remove();
	$("#dbIO")
			.append(
					"<input type=\"button\" value=\"신규\" class=\"btn btn-primary pull-right\" "
							+ "onclick=\"insertSubGroup()\" /><input type=\"button\" value=\"저장\"class=\"btn "
							+ "btn-primary pull-right\" onclick=\"updateSubGroup()\" /><input type=\"button\" "
							+ "value=\"삭제\"class=\"btn btn-primary pull-right\" onclick=\"deleteSubGroup()\" />");
	$("#inner-content table").remove();
	$("#inner-content")
			.append(
					"<table class=\"table\"><thead><tr><th>No</th><th>본부 코드</th><th>조직 코드</th><th>조직 명</th></tr></thead><table>");
	$("#inputform div").remove();
	$("#inputform").append(	"<div class=\"form-group\">"+
			"<label for=\"upperGroupName\" class=\"sr-only\">본부 코드</label>" +
			"<select onchange=\"labelling(this.value);\" class = \"selectBox\" id=\"selectUpperGroup\"></select></div>"+
			"<div class=\"form-group\"><label class=\"sr-only\" id=\"subGroupCodePart\" for=\"subGroupCode\">조직 코드</label> " +
			"<input id=\"subGroupCodeInput\" type=\"text\" class=\"form-control\""+
		"placeholder=\"조직 코드\" readonly></div><div class=\"form-group\">"+
	"<label for=\"siteName\" class=\"sr-only\">사업장 명</label> <input "+ 
	"id=\"subGroupNameInput\" type=\"text\" class=\"form-control\" placeholder=\"조직 명\"></div>");
	
	var url = "getAllSubGroup";
	var url2 = "getAllUpperGroup"
	$
			.ajax({
				type : 'get',
				url : url, // Pass you Servlet Mapping / JSP Url
				dataType : 'json',

				success : function(data) {
					$("#inner-content .table tbody").remove();
					$("#inner-content .table").append("<tbody></tbody>");
					for (var i = 0; i < data.rowNum; i++) {
						var upperGroupCode = data.table[i].upperGroupCode;;
						var upperGroupName = data.table[i].upperGroupName;
						var subGroupCode = data.table[i].subGroupCode;
						var subGroupName = data.table[i].subGroupName;
						$("#inner-content .table tbody")
								.append(
										"<tr id=\"row"
												+ (i + 1)
												+ "\" onclick=\"subGroupDataTableClick(this.id);\"><td>"
												+ (i + 1)
												+ "</td>"
												+ "<td class=\"upperGroupName\"><label>"
												+ upperGroupName
												+ "</label><input type=\"hidden\" value=\""+upperGroupName+"\"></td>"
												+ "<td class=\"groupCode\"><label>" + upperGroupCode+
												("00"+subGroupCode).slice(-3) + "</label><input type=\"hidden\" value=\""+upperGroupCode+
												"/"+("00"+subGroupCode).slice(-3)+"\"></td>" +
												"<td class=\"subGroupName\"><label>" + subGroupName
												+ "</label><input type=\"hidden\" value=\""+subGroupName+"\"></td></tr>");
						/*$("#selectUpperGroup").append(
								"<option value=\""+upperGroupCode+"\"><a>"+upperGroupName+"</a></option>"
						);*/
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
			for (var i = 0; i < data.rowNum; i++) {
				var upperGroupCode = data.table[i].groupCode;
				var upperGroupName = data.table[i].groupName;
				$("#selectUpperGroup").append(
						"<option value=\""+upperGroupCode+"\"><a>"+upperGroupName+"</a></option>"
				);
			}
		},
		error : function(request, textStatus, errorThrown) {
			//alert(request.status + ', Error: ' + request.statusText);
			// perform tasks for error
		}
	});	
} 
function labelling(value){
	$("#subGroupCodeInput").val(value);
} 
