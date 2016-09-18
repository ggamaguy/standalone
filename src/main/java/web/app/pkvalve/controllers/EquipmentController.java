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
import web.app.pkvalve.domains.Energy;
import web.app.pkvalve.domains.EqCategory;
import web.app.pkvalve.domains.Equipment;
import web.app.pkvalve.domains.EquipmentList;
import web.app.pkvalve.domains.FuncLoc;
import web.app.pkvalve.domains.Location;
import web.app.pkvalve.domains.Proc;
import web.app.pkvalve.domains.Site;
import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.domains.UpperGroup;
import web.app.pkvalve.facades.CompanyFacade;
import web.app.pkvalve.facades.EnergyFacade;
import web.app.pkvalve.facades.EqFacade;
import web.app.pkvalve.facades.FuncLocFacade;
import web.app.pkvalve.facades.LocationFacade;
import web.app.pkvalve.facades.ProcFacade;
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
	@Autowired
	FuncLocFacade funcLocFacade;
	@Autowired
	LocationFacade locationFacade;
	@Autowired
	ProcFacade procFacade;
	
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

	@RequestMapping(value="/getSiteByCompanyCode", method = RequestMethod.GET,produces="application/json")
	public JSONObject getSite(@RequestParam("companyCode")String companyCode){
		//System.out.println("aaaaaaa1");
			List<Site> siteArray = siteFacade.getSiteByCompanyCode(companyCode);
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
			//System.out.println("sites:"+json);
			return json;
	}
	
	@RequestMapping(value="/getUpperGroupList", method=RequestMethod.GET, produces="application/json")
	public JSONObject getUpperGroupList(@RequestParam("siteCode")String siteCode){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<UpperGroup> list = uppergroupFacade.getUpperGroup(siteCode);
		for(int index = 0; index < list.size();index++){
			json.put("siteCode", list.get(index).getSiteCode());
			json.put("groupCode", list.get(index).getGroupCode());
			json.put("groupName", list.get(index).getGroupName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		//System.out.println(json);
		return json;
	}
	
	@RequestMapping(value="/getSubGroupList",method=RequestMethod.GET, produces="application/json")
	public JSONObject getSubGroupList(@RequestParam("groupCode")String groupCode){
		System.out.println("groupdcode: "+groupCode);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<SubGroup> list = subGroupFacade.getSubGroup(groupCode);
		for(int index = 0; index < list.size();index++){
			json.put("subGroupCode", list.get(index).getSubGroupCode());
			json.put("subGroupName", list.get(index).getSubGroupName());
			JSONObject tempJson = new JSONObject(json);
			jsonArray.add(tempJson);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		System.out.println(json);
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
		//System.out.println("company:"+json);
		return json;
	}
	
	@RequestMapping(value="/getAllEqDetail", method=RequestMethod.GET, produces="application/json")
	public JSONObject getAllEqDetail(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<EqCategory> list = eqFacade.getAllEqCategory();
		for(int index = 0;index < list.size();index++){
			json.put("eqCode", list.get(index).getEqCode());
			json.put("eqDetail", list.get(index).getEqDetail());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}
	
	@RequestMapping(value="/getAllFuncLoc", method=RequestMethod.GET, produces="application/json")
	public JSONObject getAllFuncLoc(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<FuncLoc> list = funcLocFacade.getAllFuncLocList();
		for(int index = 0; index < list.size();index++){
			json.put("functionCode", list.get(index).getFunctionCode());
			json.put("funcDesc", list.get(index).getFuncDesc());
			json.put("funcLoc", list.get(index).getFuncLoc());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}
	@RequestMapping(value="/getAllLoc", method=RequestMethod.GET, produces="application/json")
	public JSONObject getAllLoc(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Location> list = locationFacade.getAllLocList();
		for(int index = 0; index < list.size();index++){
			json.put("locationCode", list.get(index).getLocationCode());
			json.put("location", list.get(index).getLocation());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}
	
	@RequestMapping(value="/getAllProcList", method=RequestMethod.GET, produces="application/json")
	public JSONObject getAllProcList(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Proc> list = procFacade.getAllProcList();
		for(int index = 0; index < list.size();index++){
			json.put("processCode", list.get(index).getProcessCode());
			json.put("processName", list.get(index).getProcessName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}
	
	@RequestMapping(value="/getAllEnergyType", method=RequestMethod.GET, produces="application/json")
	public JSONObject getAllEnergyType(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<Energy> list = energyFacade.getAllEnergyType();
		for(int index = 0; index < list.size();index++){
			json.put("energyCode", list.get(index).getEnergyCode());
			json.put("energyType", list.get(index).getEnergyType());
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
		//System.out.println(json);
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
		//System.out.println(json);
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
		//System.out.println(json);
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
		//System.out.println(json);
		return json;
	}

	@RequestMapping(value="/updateEquipment", method=RequestMethod.PUT)
	public int updateEquipment(@RequestParam("jsonData")JSONObject data){
		int result = 0;
		Object id = data.get("id");
		Object companyCode = data.get("companyCode");
		Object siteCode = data.get("siteCode");
		Object groupCode = data.get("groupCode");
		Object eqCode = data.get("eqCode");
		Object subGroupCode = data.get("subGroupCode");
		Object location = data.get("location");
		Object funcLocation = data.get("funcLocation");
		Object process = data.get("process");
		Object energy1 = data.get("energy1");
		Object energy2 = data.get("energy2");
		Object energy3 = data.get("energy3");
		
		EquipmentList eq = new EquipmentList();
		eq.setId(id.toString());
		eq.setCompanyCode(companyCode.toString());
		eq.setSiteCode(siteCode.toString());
		eq.setGroupCode(groupCode.toString());
		eq.setEqCode(eqCode.toString());
		eq.setSubGroupCode(subGroupCode.toString());
		eq.setLocation(location.toString());
		eq.setFuncLocation(funcLocation.toString());
		eq.setProcess(process.toString());
		eq.setEnergy1(energy1.toString());
		eq.setEnergy2(energy2.toString());
		eq.setEnergy3(energy3.toString());
		
		result = eqFacade.updateEquipment(eq);
		return result;
	}

	@RequestMapping(value="/insertEquipment", method = RequestMethod.POST)
	public int insertEquipment(@RequestParam("jsonData")JSONObject data){

		Object companyCode = data.get("companyCode");
		Object siteCode = data.get("siteCode");
		Object groupCode = data.get("groupCode");
		Object eqCode = data.get("eqCode");
		Object subGroupCode = data.get("subGroupCode");
		Object location = data.get("location");
		Object funcLocation = data.get("funcLocation");
		Object process = data.get("process");
		Object energy1 = data.get("energy1");
		Object energy2 = data.get("energy2");
		Object energy3 = data.get("energy3");
		
		EquipmentList eq = new EquipmentList();
		eq.setId(null);
		eq.setCompanyCode(companyCode.toString());
		eq.setSiteCode(siteCode.toString());
		eq.setGroupCode(groupCode.toString());
		eq.setEqCode(eqCode.toString());
		eq.setSubGroupCode(subGroupCode.toString());
		eq.setLocation(location.toString());
		eq.setFuncLocation(funcLocation.toString());
		eq.setProcess(process.toString());
		eq.setEnergy1(energy1.toString());
		eq.setEnergy2(energy2.toString());
		eq.setEnergy3(energy3.toString());	
		
		return eqFacade.insertEquipment(eq);
	}
	@RequestMapping(value="/deleteEquipment", method = RequestMethod.DELETE)
	public int deleteEquipment(@RequestParam("eqId")String eqId){
		int result = eqFacade.deleteEquipment(eqId);
		return result;
	}
}
