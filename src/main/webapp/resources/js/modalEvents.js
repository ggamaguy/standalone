function addNewEq(){
	modalReset();
	setDefaultModalData();
	setEqDetailModalData();
	setEqFuncLocModalData();
	setEqLocModalData();
	setEqProcModalData();
	setEqEnergyModalData();
	$(".modal-footer .deleteEq").remove();
	$(".modal-footer").append("<button type=\"button\" onclick=\"insertFromModal();\" class=\"btn btn-primary\">장비 저장</button>");
	$("#updateModal").modal('show');	
}

function modalDrawing(data){

	modalReset();
	setDefaultModalData();
	setEqDetailModalData();
	setEqFuncLocModalData();
	setEqLocModalData();
	setEqProcModalData();
	setEqEnergyModalData();
	
	$(".modal-footer").prepend("<button type=\"button\" id =\"deleteEq\" onclick=\"deleteFromModal()\" class=\"btn btn-default\" data-dismiss=\"modal\">장비 삭제</button>")
	$(".modal-footer").append("<button type=\"button\" onclick=\"updateFromModal();\" class=\"btn btn-primary\">장비 변경 사항 저장</button>");
	$("#updateModalEqId").val(data.id);
	$("#updateModalCompanyCode").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.companyCode+"</option>");
	$("#updateModalSiteCode").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.siteName+"</option>");
	$("#updateModalUpperGroup").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.groupName+"</option>");
	$("#updateModalEqCode").val(data.eqCode);
	$("#updateModalEqDetail").prepend("<option class = \"default-selection\" selected=\"true\" value=\"\"disabled=\"disabled\">"+data.eqDetail+"</option>");
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
	$("#updateModalCompanyCode option").remove();
	$("#updateModalSiteCode option").remove();
	$("#updateModalUpperGroup option").remove();
	$("#updateModalEqCode").val("");
	$("#updateModalEqDetail option").remove();
	$("#updateModalSubGroup option").remove();
	$("#updateModalLoc option").remove();
	$("#updateModalFuncLoc option").remove();
	$("#updateModalProcess option").remove();
	$("#updateModalEnergy1 option").remove();
	$("#updateModalEnergy2 option").remove();
	$("#updateModalEnergy3 option").remove();
}

function setDefaultModalData(){
	var url = "getCompany";
		$.ajax({
			url : url,
			dataType : 'json',
			success : function(data) {
				for(var i = 0; i < data.num; i++){
					$("#updateModalCompanyCode").append("<option value=\""+data.content[0].companyCode+"\">"+data.content[0].companyCode+"</option>");
				} 
			},
			error : function(request, textStatus, errorThrown) {
				alert(request.status + ',Error: ' + request.statusText);
			}
		});
}

function setSiteModalData(){
	$("#updateModalSiteCode option").remove();
	$("#updateModalUpperGroup option").remove();
	$("#updateModalSubGroup option").remove();
	var url = "getSiteByCompanyCode";
	var companyCode = $("#updateModalCompanyCode").val();
	$.ajax({
		url:url,
		data : {
			companyCode : companyCode
		},
		dataType : 'json',
		success : function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalSiteCode").append("<option value=\""+
					data.content[i].siteCode+"\">"+data.content[i].siteName+"</option>");
			}
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function setUpperGroupModalData(){
	$("#updateModalUpperGroup option").remove();
	$("#updateModalSubGroup option").remove();
	var url = "getUpperGroupList";
	var siteCode = $("#updateModalSiteCode").val();
	$.ajax({
		url:url,
		data:{
			siteCode : siteCode
		},
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalUpperGroup").append("<option value=\""+
						data.content[i].groupCode+"\">"+data.content[i].groupName+"</option>");
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function setSubGroupModalData(){
	$("#updateModalSubGroup option").remove();
	var url = "getSubGroupList";
	var groupCode = $("#updateModalUpperGroup").val();
	$.ajax({
		url:url,
		data:{
			groupCode : groupCode
		},
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalSubGroup").append("<option value=\""+
						data.content[i].subGroupCode+"\">"+data.content[i].subGroupName+"</option>");
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
	
}

function setEqCode(){
	$("#updateModalEqCode").val($("#updateModalEqDetail").val());
}

function setEqDetailModalData(){
	var url = "getAllEqDetail";
	$.ajax({
		url:url,
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalEqDetail").append("<option value=\""+
						data.content[i].eqCode+"\">"+data.content[i].eqDetail+"</option>");
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function setEqLocModalData(){
	var url = "getAllLoc";
	$.ajax({
		url:url,
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalLoc").append("<option value=\""+
						data.content[i].locationCode+"\">"+data.content[i].location+"</option>");
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function setEqFuncLocModalData(){
	var url = "getAllFuncLoc";
	$.ajax({
		url:url,
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalFuncLoc").append("<option value=\""+
						data.content[i].functionCode+"\">"+data.content[i].funcLoc+"</option>");
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function setEqProcModalData(){
	var url = "getAllProcList";
	$.ajax({
		url:url,
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				$("#updateModalProcess").append("<option value=\""+
						data.content[i].processCode+"\">"+data.content[i].processName+"</option>");
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});	
}

function setEqEnergyModalData(){
	var url = "getAllEnergyType";
	$.ajax({
		url:url,
		dataType : 'json',
		success :function(data){
			for(var i = 0;i < data.num;i++){
				if(data.content[i].energyCode == "1"){
					$("#updateModalEnergy1").append("<option value=\""+
							data.content[i].energyCode+"\">N/A</option>");
					$("#updateModalEnergy2").append("<option value=\""+
							data.content[i].energyCode+"\">N/A</option>");
					$("#updateModalEnergy3").append("<option value=\""+
							data.content[i].energyCode+"\">N/A</option>");
				}
				else{
					$("#updateModalEnergy1").append("<option value=\""+
							data.content[i].energyCode+"\">"+data.content[i].energyType+"</option>");
					$("#updateModalEnergy2").append("<option value=\""+
							data.content[i].energyCode+"\">"+data.content[i].energyType+"</option>");
					$("#updateModalEnergy3").append("<option value=\""+
							data.content[i].energyCode+"\">"+data.content[i].energyType+"</option>");
				}
			}
		
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function updateFromModal(){
	var url = "updateEquipment";
	
	var id = $("#updateModalEqId").val();
	var companyCode = $("#updateModalCompanyCode").val();
	var siteCode = $("#updateModalSiteCode").val();
	var groupCode = $("#updateModalUpperGroup").val();
	var eqCode = $("#updateModalEqCode").val();
	var subGroupCode = $("#updateModalSubGroup").val();
	var upperLocationCode = $("#updateModalLoc").val();
	var upperFunctionCode = $("#updateModalFuncLoc").val();
	var upperProcess = $("#updateModalProcess").val();
	var energy1 = $("#updateModalEnergy1").val();
	var energy2 = $("#updateModalEnergy2").val();
	var energy3 = $("#updateModalEnergy3").val();
	
	var json = JSON.stringfy({
			id: id,
			companyCode: companyCode,
			siteCode: siteCode,
			groupCode: groupCode,
			eqCode: eqCode,
			subGroupCode: subGroupCode,
			location: upperLocationCode,
			funcLocation: upperFunctionCode,
			process: upperProcess,
			energy1: energy1,
			energy2: energy2,
			energy3: energy3
	});
	alert(json);
	$.ajax({
		url:url,
		type : 'post',
		data : {jsonData :  json},
		dataType : 'json',
		success : function(data){
			location.reload();
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function insertFromModal(){
	var url = "updateEquipment";
	
	var companyCode = $("#updateModalCompanyCode").val();
	var siteCode = $("#updateModalSiteCode").val();
	var groupCode = $("#updateModalUpperGroup").val();
	var eqCode = $("#updateModalEqCode").val();
	var subGroupCode = $("#updateModalSubGroup").val();
	var upperLocationCode = $("#updateModalLoc").val();
	var upperFunctionCode = $("#updateModalFuncLoc").val();
	var upperProcess = $("#updateModalProcess").val();
	var energy1 = $("#updateModalEnergy1").val();
	var energy2 = $("#updateModalEnergy2").val();
	var energy3 = $("#updateModalEnergy3").val();
	
	var json = JSON.stringfy({

			companyCode: companyCode,
			siteCode: siteCode,
			groupCode: groupCode,
			eqCode: eqCode,
			subGroupCode: subGroupCode,
			location: upperLocationCode,
			funcLocation: upperFunctionCode,
			process: upperProcess,
			energy1: energy1,
			energy2: energy2,
			energy3: energy3
	});
	alert(json);
	$.ajax({
		url:url,
		type : 'post',
		data : {jsonData :  json},
		dataType : 'json',
		success : function(data){
			location.reload();
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}

function deleteFromModal(){
	var url = "deleteEquipment";
	var eqId = $("#updateModalEqId").val();
	$.ajax({
		url:url,
		data:{
			eqId : eqId
		},
		dataType : 'json',
		success :function(data){
			location.reload();
		},
		error : function(request, textStatus, errThrown){
			alert(request.status + ',Error: ' + request.statusText);
		}
	});
}