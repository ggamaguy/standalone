function modalDrawing(data){
	modalReset();
	$("#updateModalEqId").val(data.id);
	$("#updateModalCompanyCode").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.companyCode+"</option>");
	$("#updateModalSiteCode").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.siteName+"</option>");
	$("#updateModalUpperGroup").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.groupName+"</option>");
	$("#updateModalEqCode").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.eqCode+"</option>");
	$("#updateModalEqDetail").val(data.eqDetail);
	$("#updateModalSubGroup").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.subGroupName+"</option>");
	$("#updateModalLoc").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.location+"</option>");
	$("#updateModalFuncLoc").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.funcLocation+"</option>");
	$("#updateModalProcess").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.processName+"</option>");
	$("#updateModalEnergy1").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.energy1+"</option>");
	$("#updateModalEnergy2").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.energy2+"</option>");
	$("#updateModalEnergy3").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.energy3+"</option>");
	$("#updateModal").modal('show');
}

function modalReset(){
	$("#updateModalEqId").val("");
	$("#updateModalCompanyCode .default-selection").remove();
	$("#updateModalSiteCode .default-selection").remove();
	$("#updateModalUpperGroup .default-selection").remove();
	$("#updateModalEqCode .default-selection").remove()
	$("#updateModalEqDetail").val("");
	$("#updateModalSubGroup .default-selection").remove();
	$("#updateModalLoc .default-selection").remove();
	$("#updateModalFuncLoc .default-selection").remove();
	$("#updateModalProcess .default-selection").remove();
	$("#updateModalEnergy1 .default-selection").remove();
	$("#updateModalEnergy2 .default-selection").remove();
	$("#updateModalEnergy3 .default-selection").remove();
	$("#updateModal").modal('show');
}

function setDefaultModalData(){
	var url1 = "getCompany"
		$.ajax({
			url : url1,

			dataType : 'json',
			success : function(data) {
				createDefaultSelect(data);
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}

		});
}
function createDefaultSelect(data){
	for(var i = 0; i < data.num; i++){
		$("#updateModalCompanyCode").appned("<option value=\""+data.companyCode+"\">"+data.companyName+"</option>");
	}
	
}
