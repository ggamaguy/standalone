package web.app.pkvalve.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import web.app.pkvalve.domains.Company;
import web.app.pkvalve.domains.EqCategory;
import web.app.pkvalve.domains.Equipment;
import web.app.pkvalve.domains.Site;
import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.domains.UpperGroup;
import web.app.pkvalve.facades.CompanyFacade;
import web.app.pkvalve.facades.EnergyFacade;
import web.app.pkvalve.facades.EqFacade;
import web.app.pkvalve.facades.SiteFacade;
import web.app.pkvalve.facades.SubGroupFacade;
import web.app.pkvalve.facades.UpperGroupFacade;

@RestController
public class EquipmentController {

	@Autowired
	SiteFacade siteFacade;
	@Autowired
	UpperGroupFacade uppergroupFacade;
	@Autowired
	SubGroupFacade subGroupFacade;
	@Autowired
	EqFacade eqFacade;
	@Autowired
	EnergyFacade energyFacade;
	@Autowired
	CompanyFacade companyFacade;

	@RequestMapping(value = "/equipment", method = RequestMethod.GET)

	public ModelAndView site() {
		ModelAndView mv = new ModelAndView("equipmentMaster");
		try {
			List<Site> siteArray = siteFacade.getAllSiteList();
			List<EqCategory> eqCatalogArray = eqFacade.getAllEqCategory();
			
			JSONObject json = new JSONObject();
			JSONObject json2 = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONArray eqCatalogJSONArray = new JSONArray();
			for (int index = 0; index < siteArray.size(); index++) {
				json.put("companyCode", siteArray.get(index).getCompanyCode());
				json.put("siteName", siteArray.get(index).getSiteName());
				json.put("siteCode", siteArray.get(index).getSiteCode());
				JSONObject tempJson = new JSONObject(json);
				jsonArray.add(tempJson);
			}
			json.clear();
			json.put("num", jsonArray.size());
			json.put("content", jsonArray);
			mv.addObject("sites", json);
			for(int index = 0; index < eqCatalogArray.size(); index++){
				json2.put("eqCode", eqCatalogArray.get(index).getEqCode());
				json2.put("eqDetail", eqCatalogArray.get(index).getEqDetail());
				json2.put("eqCapacity", eqCatalogArray.get(index).getEqCapacity());
				json2.put("eqType", eqCatalogArray.get(index).getEqType());
				JSONObject tempJson = new JSONObject(json2);
				eqCatalogJSONArray.add(tempJson);
			}
			json2.clear();
			JSONArray nonDuplicatedJSONArray = new JSONArray();
			for(int i = 0;i < eqCatalogJSONArray.size();i++){
				for(int j = 0; j < i;j++ ){
					if(((JSONObject)eqCatalogJSONArray.get(i)).get("eqType")==((JSONObject)eqCatalogJSONArray.get(j)).get("eqType")){
						break;
					}
					if(i == j+1){
						nonDuplicatedJSONArray.add(eqCatalogJSONArray.get(i));
					}
				}
			}
			json2.put("num", nonDuplicatedJSONArray.size());
			json2.put("content", nonDuplicatedJSONArray);
			mv.addObject("eqCategories",json2);
						
		} catch (Exception e) {
			mv.addObject("sites", null);
			mv.addObject("eqCategories",null);
		}
		return mv;
	}

	@RequestMapping(value="/getSite", method = RequestMethod.GET,produces="application/json")
	public JSONObject getSite(){
			List<Site> siteArray = siteFacade.getAllSiteList();
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONArray eqCatalogJSONArray = new JSONArray();
			for (int index = 0; index < siteArray.size(); index++) {
				json.put("companyCode", siteArray.get(index).getCompanyCode());
				json.put("siteName", siteArray.get(index).getSiteName());
				json.put("siteCode", siteArray.get(index).getSiteCode());
				JSONObject tempJson = new JSONObject(json);
				jsonArray.add(tempJson);
			}
			json.clear();
			json.put("num", jsonArray.size());
			json.put("content", jsonArray);
			return json;
	}
	@RequestMapping(value="/getEqType", method = RequestMethod.GET,produces="application/json")
	public JSONObject getEqType(){
		List<EqCategory> eqCatalogArray = eqFacade.getAllEqCategory();
		JSONObject json = new JSONObject();
		JSONArray eqCatalogJSONArray = new JSONArray();
		
		for(int index = 0; index < eqCatalogArray.size(); index++){
			json.put("eqCode", eqCatalogArray.get(index).getEqCode());
			json.put("eqDetail", eqCatalogArray.get(index).getEqDetail());
			json.put("eqCapacity", eqCatalogArray.get(index).getEqCapacity());
			json.put("eqType", eqCatalogArray.get(index).getEqType());
			JSONObject tempJson = new JSONObject(json);
			eqCatalogJSONArray.add(tempJson);
		}
		json.clear();
		JSONArray nonDuplicatedJSONArray = new JSONArray();
		for(int i = 0;i < eqCatalogJSONArray.size();i++){
			for(int j = 0; j < i;j++ ){
				if(((JSONObject)eqCatalogJSONArray.get(i)).get("eqType")==((JSONObject)eqCatalogJSONArray.get(j)).get("eqType")){
					break;
				}
				if(i == j+1){
					nonDuplicatedJSONArray.add(eqCatalogJSONArray.get(i));
				}
			}
		}
		json.put("num", nonDuplicatedJSONArray.size());
		json.put("content", nonDuplicatedJSONArray);
		return json;
	}
	
	@RequestMapping(value = "/getCompany", method = RequestMethod.GET, produces = "application/json")
	public JSONObject getCompany(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Company> list 	= companyFacade.getAllCompany();
		for(int index = 0; index < list.size();index++){
			json.put("companyCode", list.get(index).getCompanyCode());
			json.put("companyName", list.get(index).getCompanyName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}
	
	@RequestMapping(value = "/getUpperGroup", method = RequestMethod.GET, produces = "application/json")
	public JSONObject getUpperGroup(@RequestParam(value = "siteCode") String siteCode) {
		List<UpperGroup> list = uppergroupFacade.getUpperGroup(siteCode);
		List<Equipment> eqList = eqFacade.getEqBySite(siteCode);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrayForTable = new JSONArray();
		for(int index = 0;index < list.size();index++){
			json.put("groupCode", list.get(index).getGroupCode());
			json.put("groupName", list.get(index).getGroupName());
			JSONObject tempJson = new JSONObject(json);
			jsonArray.add(tempJson);
		}
		json.clear();
		for(int index = 0; index < eqList.size(); index++ ){
			json.put("id", eqList.get(index).getId());
			json.put("companyCode", eqList.get(index).getCompanyCode());
			json.put("siteName", eqList.get(index).getSiteName());
			json.put("groupName", eqList.get(index).getGroupName());
			json.put("eqCode", eqList.get(index).getEqCode());
			json.put("eqDetail", eqList.get(index).getEqDetail());
			json.put("subGroupName", eqList.get(index).getSubGroupName());
			json.put("location", eqList.get(index).getLocation());
			json.put("funcLocation", eqList.get(index).getFuncLoc());
			json.put("processName", eqList.get(index).getProcessName());
			eqList.get(index).setEnergyCode1(energyFacade.getEnergyType(eqList.get(index).getEnergyCode1()));
			json.put("energy1", eqList.get(index).getEnergyCode1());
			eqList.get(index).setEnergyCode2(energyFacade.getEnergyType(eqList.get(index).getEnergyCode2()));
			json.put("energy2", eqList.get(index).getEnergyCode2());
			eqList.get(index).setEnergyCode3(energyFacade.getEnergyType(eqList.get(index).getEnergyCode3()));
			json.put("energy3", eqList.get(index).getEnergyCode3());
			JSONObject tempJson = new JSONObject(json);
			jsonArrayForTable.add(tempJson);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		json.put("rowNum", jsonArrayForTable.size());
		json.put("table", jsonArrayForTable);
		System.out.println(json);
		return json;
	}

	@RequestMapping(value = "/getSubGroup", method = RequestMethod.GET, produces = "application/json")
	public JSONObject getSubGroup(@RequestParam(value="siteCode") String siteCode, @RequestParam(value = "upperGroupCode") String upperGroupCode) {
		List<SubGroup> list = subGroupFacade.getSubGroup(upperGroupCode);
		List<Equipment> eqList = eqFacade.getEqBySiteUppergroup(siteCode, upperGroupCode);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONArray jsonArrayForTable = new JSONArray();
		for(int index = 0; index < list.size();index++){
			json.put("subGroupCode", list.get(index).getSubGroupCode());
			json.put("subGroupName", list.get(index).getSubGroupName());
			JSONObject tempJson = new JSONObject(json);
			jsonArray.add(tempJson);
		}
		for(int index = 0; index < eqList.size(); index++ ){
			json.put("id", eqList.get(index).getId());
			json.put("companyCode", eqList.get(index).getCompanyCode());
			json.put("siteName", eqList.get(index).getSiteName());
			json.put("groupName", eqList.get(index).getGroupName());
			json.put("eqCode", eqList.get(index).getEqCode());
			json.put("eqDetail", eqList.get(index).getEqDetail());
			json.put("subGroupName", eqList.get(index).getSubGroupName());
			json.put("location", eqList.get(index).getLocation());
			json.put("funcLocation", eqList.get(index).getFuncLoc());
			json.put("processName", eqList.get(index).getProcessName());
			eqList.get(index).setEnergyCode1(energyFacade.getEnergyType(eqList.get(index).getEnergyCode1()));
			json.put("energy1", eqList.get(index).getEnergyCode1());
			eqList.get(index).setEnergyCode2(energyFacade.getEnergyType(eqList.get(index).getEnergyCode2()));
			json.put("energy2", eqList.get(index).getEnergyCode2());
			eqList.get(index).setEnergyCode3(energyFacade.getEnergyType(eqList.get(index).getEnergyCode3()));
			json.put("energy3", eqList.get(index).getEnergyCode3());
			JSONObject tempJson = new JSONObject(json);
			jsonArrayForTable.add(tempJson);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		json.put("rowNum", jsonArrayForTable.size());
		json.put("table", jsonArrayForTable);
		System.out.println(json);
		return json;
	}
	
	@RequestMapping(value="/getEqTable", method=RequestMethod.GET,produces="application/json")
	public JSONObject getEqTableBySiteGroupSubGroup(@RequestParam(value="siteCode")String siteCode,
													@RequestParam(value="upperGroupCode")String groupCode,
													@RequestParam(value="subGroupCode")String subGroupCode){
		List<Equipment> eqList = eqFacade.getEqBySiteUppergroupSubgroup(siteCode, groupCode, subGroupCode);
		JSONObject json = new JSONObject();
		JSONArray jsonArrayForTable = new JSONArray();
		for(int index = 0; index < eqList.size(); index++ ){
			json.put("id", eqList.get(index).getId());
			json.put("companyCode", eqList.get(index).getCompanyCode());
			json.put("siteName", eqList.get(index).getSiteName());
			json.put("groupName", eqList.get(index).getGroupName());
			json.put("eqCode", eqList.get(index).getEqCode());
			json.put("eqDetail", eqList.get(index).getEqDetail());
			json.put("subGroupName", eqList.get(index).getSubGroupName());
			json.put("location", eqList.get(index).getLocation());
			json.put("funcLocation", eqList.get(index).getFuncLoc());
			json.put("processName", eqList.get(index).getProcessName());
			eqList.get(index).setEnergyCode1(energyFacade.getEnergyType(eqList.get(index).getEnergyCode1()));
			json.put("energy1", eqList.get(index).getEnergyCode1());
			eqList.get(index).setEnergyCode2(energyFacade.getEnergyType(eqList.get(index).getEnergyCode2()));
			json.put("energy2", eqList.get(index).getEnergyCode2());
			eqList.get(index).setEnergyCode3(energyFacade.getEnergyType(eqList.get(index).getEnergyCode3()));
			json.put("energy3", eqList.get(index).getEnergyCode3());
			JSONObject tempJson = new JSONObject(json);
			jsonArrayForTable.add(tempJson);
		}
		json.clear();
		json.put("rowNum", jsonArrayForTable.size());
		json.put("table", jsonArrayForTable);
		System.out.println(json);
		return json;
	}

	@RequestMapping(value="/getEqDetail", method=RequestMethod.GET,produces="application/json")
	public JSONObject getEqdetail(@RequestParam(value="eqType") String eqType) throws UnsupportedEncodingException{
		String type = java.net.URLDecoder.decode(eqType, "UTF-8");
		List<EqCategory> eqCatalogArray = eqFacade.getEqCategoryByEqType(type);
		List<Equipment> eqList = eqFacade.getEqTableByEqType(type);
		JSONObject json = new JSONObject();
		JSONObject json2 = new JSONObject();
		JSONArray eqCatalogJSONArray = new JSONArray();
		JSONArray eqTableJSONArray = new JSONArray();
		for(int index = 0;index < eqCatalogArray.size();index++){
			json.put("eqCode", eqCatalogArray.get(index).getEqCode());
			json.put("eqDetail", eqCatalogArray.get(index).getEqDetail());
			json.put("eqCapacity", eqCatalogArray.get(index).getEqCapacity());
			json.put("eqType", eqCatalogArray.get(index).getEqType());
			JSONObject tempJson = new JSONObject(json);
			eqCatalogJSONArray.add(tempJson);
		}
		json.clear();		
		for(int index = 0; index < eqList.size(); index++ ){
			json.put("id", eqList.get(index).getId());
			json.put("companyCode", eqList.get(index).getCompanyCode());
			json.put("siteName", eqList.get(index).getSiteName());
			json.put("groupName", eqList.get(index).getGroupName());
			json.put("eqCode", eqList.get(index).getEqCode());
			json.put("eqDetail", eqList.get(index).getEqDetail());
			json.put("subGroupName", eqList.get(index).getSubGroupName());
			json.put("location", eqList.get(index).getLocation());
			json.put("funcLocation", eqList.get(index).getFuncLoc());
			json.put("processName", eqList.get(index).getProcessName());
			eqList.get(index).setEnergyCode1(energyFacade.getEnergyType(eqList.get(index).getEnergyCode1()));
			json.put("energy1", eqList.get(index).getEnergyCode1());
			eqList.get(index).setEnergyCode2(energyFacade.getEnergyType(eqList.get(index).getEnergyCode2()));
			json.put("energy2", eqList.get(index).getEnergyCode2());
			eqList.get(index).setEnergyCode3(energyFacade.getEnergyType(eqList.get(index).getEnergyCode3()));
			json.put("energy3", eqList.get(index).getEnergyCode3());
			JSONObject tempJson = new JSONObject(json);
			eqTableJSONArray.add(tempJson);
		}
		json.clear();
		json.put("num", eqCatalogJSONArray.size());
		json.put("content", eqCatalogJSONArray);
		json.put("rowNum", eqTableJSONArray.size());
		json.put("table", eqTableJSONArray);
		
		return json;
	}
	@RequestMapping(value="/getUsingGroup",method= RequestMethod.POST, produces="application/json")
	public JSONObject getUsingGroup(@RequestParam("eqType")String eqType, @RequestParam("eqDetail")String eqDetail) throws UnsupportedEncodingException{
		List<SubGroup> list = eqFacade.getSubGroupNameByEqTypeEqDetail(eqType, eqDetail);
		List<Equipment> eqList = eqFacade.getEqTableByEqTypeEqDetail(eqType, eqDetail);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		JSONArray eqTableJSONArray = new JSONArray();
		for(int index = 0; index < list.size();index++){
			json.put("subGroupName",list.get(index).getSubGroupName());
			JSONObject tempJson = new JSONObject(json);
			jsonArray.add(tempJson);
		}
		json.clear();
		for(int index = 0; index < eqList.size(); index++ ){
			json.put("id", eqList.get(index).getId());
			json.put("companyCode", eqList.get(index).getCompanyCode());
			json.put("siteName", eqList.get(index).getSiteName());
			json.put("groupName", eqList.get(index).getGroupName());
			json.put("eqCode", eqList.get(index).getEqCode());
			json.put("eqDetail", eqList.get(index).getEqDetail());
			json.put("subGroupName", eqList.get(index).getSubGroupName());
			json.put("location", eqList.get(index).getLocation());
			json.put("funcLocation", eqList.get(index).getFuncLoc());
			json.put("processName", eqList.get(index).getProcessName());
			eqList.get(index).setEnergyCode1(energyFacade.getEnergyType(eqList.get(index).getEnergyCode1()));
			json.put("energy1", eqList.get(index).getEnergyCode1());
			eqList.get(index).setEnergyCode2(energyFacade.getEnergyType(eqList.get(index).getEnergyCode2()));
			json.put("energy2", eqList.get(index).getEnergyCode2());
			eqList.get(index).setEnergyCode3(energyFacade.getEnergyType(eqList.get(index).getEnergyCode3()));
			json.put("energy3", eqList.get(index).getEnergyCode3());
			JSONObject tempJson = new JSONObject(json);
			eqTableJSONArray.add(tempJson);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		json.put("rowNum", eqTableJSONArray.size());
		json.put("table", eqTableJSONArray);
		return json;
	}
	
	@RequestMapping(value="/getEqTableByEqTypeEqDetailGroupName", method=RequestMethod.POST, produces="application/json")
	public JSONObject getEqTableByEqTypeEqDetailGroupName(@RequestParam("eqType")String eqType,
																@RequestParam("eqDetail")String eqDetail,
																@RequestParam("groupName")String groupName){
		List<Equipment> eqList = eqFacade.getEqTableByEqTypeEqDetailSubGroupName(eqType, eqDetail, groupName);
		JSONObject json = new JSONObject();
		JSONArray eqTableJSONArray = new JSONArray();
		for(int index = 0; index < eqList.size(); index++ ){
			json.put("id", eqList.get(index).getId());
			json.put("companyCode", eqList.get(index).getCompanyCode());
			json.put("siteName", eqList.get(index).getSiteName());
			json.put("groupName", eqList.get(index).getGroupName());
			json.put("eqCode", eqList.get(index).getEqCode());
			json.put("eqDetail", eqList.get(index).getEqDetail());
			json.put("subGroupName", eqList.get(index).getSubGroupName());
			json.put("location", eqList.get(index).getLocation());
			json.put("funcLocation", eqList.get(index).getFuncLoc());
			json.put("processName", eqList.get(index).getProcessName());
			eqList.get(index).setEnergyCode1(energyFacade.getEnergyType(eqList.get(index).getEnergyCode1()));
			json.put("energy1", eqList.get(index).getEnergyCode1());
			eqList.get(index).setEnergyCode2(energyFacade.getEnergyType(eqList.get(index).getEnergyCode2()));
			json.put("energy2", eqList.get(index).getEnergyCode2());
			eqList.get(index).setEnergyCode3(energyFacade.getEnergyType(eqList.get(index).getEnergyCode3()));
			json.put("energy3", eqList.get(index).getEnergyCode3());
			JSONObject tempJson = new JSONObject(json);
			eqTableJSONArray.add(tempJson);
		}
		json.clear();
		json.put("rowNum", eqTableJSONArray.size());
		json.put("table",eqTableJSONArray);
		System.out.println(json);
		return json;
	}
	
	@RequestMapping(value="/getEqByEqId", method=RequestMethod.GET,produces="application/json")
	public JSONObject getEqByEqId(@RequestParam(value="eqId")String eqId){
		JSONObject json = new JSONObject();
		Equipment equipment = eqFacade.getEqByEqId(eqId);
		
		json.put("id", equipment.getId());
		json.put("companyCode", equipment.getCompanyCode());
		json.put("siteName", equipment.getSiteName());
		json.put("groupName", equipment.getGroupName());
		json.put("eqCode",equipment.getEqCode());
		json.put("eqDetail", equipment.getEqDetail());
		json.put("subGroupName", equipment.getSubGroupName());
		json.put("location", equipment.getLocation());
		json.put("funcLocation",equipment.getFuncLoc());
		json.put("processName", equipment.getProcessName());
		equipment.setEnergyCode1(energyFacade.getEnergyType(equipment.getEnergyCode1()));
		json.put("energy1", equipment.getEnergyCode1());
		equipment.setEnergyCode2(energyFacade.getEnergyType(equipment.getEnergyCode2()));
		json.put("energy2", equipment.getEnergyCode2());
		equipment.setEnergyCode3(energyFacade.getEnergyType(equipment.getEnergyCode3()));
		json.put("energy3", equipment.getEnergyCode3());
		System.out.println(json);
		return json;
	}
}
