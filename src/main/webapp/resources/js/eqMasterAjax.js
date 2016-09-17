$(document).ready(setDefaultModalData);

function clickEqTableRow(value) {
	var eqId = value.replace(/[(a-z)]/gi,"");
	var url = "getEqByEqId";
	$.ajax({
		url : url, // Pass you Servlet Mapping / JSP Url
		data : {
			eqId : eqId
		}, // This will be passed as parameter to server (JSP/Servlet)
		dataType : 'json',

		success : function(data) {
			modalDrawing(data);
		},
		error : function(request, textStatus, errorThrown) {
			alert(request.status + ', Error: ' + request.statusText);
		}
	});
}

function addNewEq() {
	$("#newEqModal").modal('show');
}

function resetSiteSelection() {
	$("#default-site")
			.replaceWith(
					"<p id=\"default-site\" class=\"selected-msg defalt-msg\">사업장 선택</p>");
}

function resetUpperGroupSelection() {
	$("#upperGroupSelection ul li").remove();
	$("#default-upperGroup")
			.replaceWith(
					"<p id=\"default-upperGroup\" class=\"defalt-msg selected-msg\">상위 그룹 선택</p>");
	$("#upperGroupSelection ul").append(
			"<li class=\"disabled\"><a>상위 그룹 선택</a></li>");
}

function resetSubGroupSelection() {
	$("#subGroupSelection ul li").remove();
	$("#default-subGroup")
			.replaceWith(
					"<p id=\"default-subGroup\" class=\"defalt-msg selected-msg\">하위 그룹 선택</p>");
	$("#subGroupSelection ul").append(
			"<li class=\"disabled\"><a>하위 그룹 선택</a></li>");
}

function resetEqTypeSelection() {
	$("#default-eqType")
			.replaceWith(
					"<p id=\"default-eqType\" class=\"selected-msg defalt-msg\">설비 유형 선택</p>");
}

function resetEqDetailSelection() {
	$("#eqDetailSelection ul li").remove();
	$("#default-eqDetail")
			.replaceWith(
					"<p id=\"default-eqDetail\" class=\"defalt-msg selected-msg\">설비 내역 선택</p>");
	$("#eqDetailSelection ul").append(
			"<li class=\"disabled\"><a>설비 내역 선택</a></li>");
}

function resetEqUsingGroupSelection() {
	$("#usingGroupSelection ul li").remove();
	$("#default-usingGroup")
			.replaceWith(
					"<p id=\"default-usingGroup\" class=\"defalt-msg selected-msg\">사용 부서 선택</p>");
	$("#usingGroupSelection ul").append(
			"<li class=\"disabled\"><a>사용 부서 선택</a></li>");
}

function drawTable(data) {
	for (var i = 0; i < data.rowNum; i++) {
		var id = data.table[i].id;
		var companyCode = data.table[i].companyCode;
		var siteName = data.table[i].siteName;
		var groupName = data.table[i].groupName;
		var eqCode = data.table[i].eqCode;
		var eqDetail = data.table[i].eqDetail;
		var subGroupName = data.table[i].subGroupName;
		var location = data.table[i].location;
		var funcLoc = data.table[i].funcLocation;
		var processName = data.table[i].processName;
		var energy1 = data.table[i].energy1;
		var energy2 = data.table[i].energy2;
		var energy3 = data.table[i].energy3;
		$("#eqTable tbody").append(
				"<tr onclick = \"clickEqTableRow(this.id)\""
						+ " class=\"eqRow\" id=\"eqRow"
						+ id
						+ "\"><td>"
						+ companyCode
						+ "</td><td>"
						+ siteName
						+ "</td>"
						+ "<td>"
						+ groupName
						+ "</td><td>"
						+ eqCode
						+ "</td><td>"
						+ eqDetail
						+ "</td><td>"
						+ subGroupName
						+ "</td><td>"
						+ location
						+ "</td><td>"
						+ funcLoc
						+ "</td><td>"
						+ processName
						+ "</td><td>"
						+ energy1
						+ "</td><td>"
						+ energy2
						+ "</td><td>"
						+ energy3
						+ "</td></tr>");

	}
	$("#eqTable tbody")
			.append(
					"<tr><td id=\"addNewEq\" onclick=\"addNewEq();\" colspan=\"12\">"
							+ "<span class=\"glyphicon glyphicon-plus-sign\"></span></td></tr>");
}

function getUpperGroup(value) {
	var siteCode = value.split('/')[0];
	var siteName = value.split('/')[1];
	$("#site").attr("value", siteCode);
	$("#default-site").replaceWith(
			"<p id=\"default-site\" class=\"selected-msg\">" + siteName
					+ "</p>");
	resetUpperGroupSelection();
	resetSubGroupSelection();
	resetEqTypeSelection();
	resetEqDetailSelection();
	resetEqUsingGroupSelection();
	$("#eqTable tbody tr").remove();

	if (value != null) {
		var url = "getUpperGroup";
		$.ajax({
			url : url, // Pass you Servlet Mapping / JSP Url
			data : {
				siteCode : siteCode
			}, // This will be passed as parameter to server (JSP/Servlet)
			dataType : 'json',

			success : function(data) {
				// alert(data.content[0].groupCode+"aaaaaaaa"+data.content[0].groupName);
				for (var i = 0; i < data.num; i++) {
					var name = data.content[i].groupName;
					var code = data.content[i].groupCode;
					$("#upperGroupSelection ul").append(
							"<li><a onclick=\"getSubGroup(this.id);\" id="
									+ code + "/" + name + ">" + name
									+ "</a></li>");
				}
				drawTable(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ', Error: ' + request.statusText);
			}
		});

	} else {
	}
}

function getSubGroup(value) {
	var siteCode = $("#site").val();
	var upperGroupCode = value.split('/')[0];
	var upperGroupName = value.split('/')[1];
	$("#group").attr("value", upperGroupCode);
	$("#default-upperGroup").replaceWith(
			"<p id=\"default-upperGroup\" class=\"selected-msg\">"
					+ upperGroupName + "</p>");
	resetSubGroupSelection();
	$("#eqTable tbody tr").remove();
	if (value != null) {
		var url = "getSubGroup";
		$.ajax({
			url : url,
			data : {
				siteCode : siteCode,
				upperGroupCode : upperGroupCode
			},
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.num; i++) {
					var name = data.content[i].subGroupName;
					var code = data.content[i].subGroupCode;
					$("#subGroupSelection ul").append(
							"<li><a onclick=\"getTable(this.id);\" id=" + code
									+ "/" + name + ">" + name + "</a></li>");
				}
				drawTable(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}

		});

	}
}

function getTable(value) {
	var siteCode = $("#site").val();
	var upperGroupCode = $("#group").val();
	var subGroupCode = value.split('/')[0];
	var subGroupName = value.split('/')[1];
	$("#subgroup").attr("value", subGroupCode);
	$("#default-subGroup").replaceWith(
			"<p id=\"default-subGroup\" class=\"selected-msg\">" + subGroupName
					+ "</p>");
	$("#eqTable tbody tr").remove();
	if (value != null) {
		var url = "getEqTable";
		$.ajax({
			url : url,
			data : {
				siteCode : siteCode,
				upperGroupCode : upperGroupCode,
				subGroupCode : subGroupCode
			},
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.num; i++) {
					var name = data.content[i].subGroupName;
					var code = data.content[i].subGroupCode;
					$("#subGroupSelection ul").append(
							"<li><a onclick=\"getTable(this.id);\" id=" + code
									+ "/" + name + ">" + name + "</a></li>");
				}

				drawTable(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}

		});
	}
}

function getEqDetail(value) {
	var eqType = value;
	$("#eqType").attr("value", eqType);
	$("#default-eqType").replaceWith(
			"<p id=\"default-eqType\" class=\"selected-msg\">" + eqType
					+ "</p>");
	resetSiteSelection();
	resetUpperGroupSelection();
	resetSubGroupSelection();
	resetEqDetailSelection();
	resetEqUsingGroupSelection();
	$("#eqTable tbody tr").remove();

	if (value != null) {
		var url = "getEqDetail?eqType=" + escape(encodeURIComponent(eqType));
		$.ajax({
			url : url,
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.num; i++) {
					var detail = data.content[i].eqDetail;
					$("#eqDetailSelection ul").append(
							"<li><a onclick=\"getEqUsingGroup(this.id);\" id="
									+ detail + ">" + detail + "</a></li>");
				}

				drawTable(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}

		});

	}

}

function getEqUsingGroup(value) {
	var eqDetail = value;
	$("#eqDetail").attr("value", eqDetail);
	$("#default-eqDetail").replaceWith(
			"<p id=\"default-eqDetail\" class=\"selected-msg\">" + eqDetail
					+ "</p>");

	resetEqUsingGroupSelection();
	$("#eqTable tbody tr").remove();

	if (value != null) {
		var url = "getUsingGroup";
		$.ajax({
			type : 'POST',
			url : url,
			data : {
				eqType : $("#eqType").val(),
				eqDetail : $("#eqDetail").val()
			},
			dataType : 'json',
			success : function(data) {
				for (var i = 0; i < data.num; i++) {
					var groupName = data.content[i].subGroupName;
					$("#usingGroupSelection ul")
							.append(
									"<li><a onclick=\"getTableByEqData(this.id);\" id="
											+ groupName + ">" + groupName
											+ "</a></li>");
				}
				drawTable(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}

		});
	}
}

function getTableByEqData(value) {
	var groupName = value;
	$("#eqUsingGroup").attr("value", groupName);
	$("#default-usingGroup").replaceWith(
			"<p id=\"default-usingGroup\" class=\"selected-msg\">" + groupName
					+ "</p>");
	$("#eqTable tbody tr").remove();
	if (value != null) {
		var url = "getEqTableByEqTypeEqDetailGroupName";
		$.ajax({
			type : 'POST',
			url : url,
			data : {
				eqType : $("#eqType").val(),
				eqDetail : $("#eqDetail").val(),
				groupName : $("#eqUsingGroup").val()
			},
			dataType : 'json',
			success : function(data) {
				drawTable(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}

		});
	}
}