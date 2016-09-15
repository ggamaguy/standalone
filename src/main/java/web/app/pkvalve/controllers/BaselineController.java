package web.app.pkvalve.controllers;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import web.app.pkvalve.domains.EnergyUse;
import web.app.pkvalve.domains.Site;
import web.app.pkvalve.domains.SubGroupForBaseline;
import web.app.pkvalve.domains.UpperGroup;
import web.app.pkvalve.facades.EnergyUseFacade;
import web.app.pkvalve.facades.SiteFacade;
import web.app.pkvalve.facades.SubGroupFacade;
import web.app.pkvalve.facades.UpperGroupFacade;

@RestController
public class BaselineController {
	private static final Logger logger = LoggerFactory.getLogger(BaselineController.class);
	
	@Autowired
	SiteFacade siteFacade;
	@Autowired
	SubGroupFacade subGroupFacade;
	@Autowired
	UpperGroupFacade upperGroupFacade;
	@Autowired
	EnergyUseFacade energyUseFacade;
	
	@RequestMapping(value = "/baseline", method = RequestMethod.GET)
	public ModelAndView monitor() {
		ModelAndView mv = new ModelAndView("baseline");
		List<Site> siteList = siteFacade.getAllSiteList();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int index = 0;index < siteList.size();index++){
			json.put("siteName", siteList.get(index).getSiteName());
			json.put("siteCode", siteList.get(index).getSiteCode());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		mv.addObject("siteList",json);
		return mv;
	}
	
	@RequestMapping(value="/insertSite", method = RequestMethod.POST,produces="application/json")
	public JSONObject insertSite(@RequestParam("siteCode")String siteCode, @RequestParam("siteName")String siteName){
		int result = siteFacade.insertSite(siteCode, siteName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
	
	@RequestMapping(value="/updateSite", method = RequestMethod.POST, produces="application/json")
	public JSONObject updateSite(@RequestParam("siteCode")String siteCode, @RequestParam("siteName")String siteName){
		int result = siteFacade.updateSite(siteCode, siteName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
	
	@RequestMapping(value="/deleteSite", method = RequestMethod.POST,produces="application/json")
	public JSONObject deleteSite(@RequestParam("siteCode")String siteCode, @RequestParam("siteName")String siteName){
		int result = siteFacade.deleteSite(siteCode, siteName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println("updateSite: "+result);
		return json;
	}
	
	@RequestMapping(value="getAllSites",method = RequestMethod.GET,produces="application/json")
	public JSONObject getAllsite(){
		List<Site> siteList = siteFacade.getAllSiteList();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int index = 0;index < siteList.size();index++){
			json.put("siteName", siteList.get(index).getSiteName());
			json.put("siteCode", siteList.get(index).getSiteCode());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("rowNum", jsonArray.size());
		json.put("table", jsonArray);
		return json;
	}

	@RequestMapping(value="/insertSubGroup", method=RequestMethod.POST, produces="application/json")
	public JSONObject insertSubGroup(@RequestParam(value = "upperGroupCode")String upperGroupCode,
									@RequestParam(value="subGroupCode")String subGroupCode,
									@RequestParam(value="subGroupName")String subGroupName){	
		int result = subGroupFacade.insertSubGroup(upperGroupCode, subGroupCode, subGroupName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println("insertUpperGroup: "+result);
		return json;
	}
	
	@RequestMapping(value="/updateSubGroup", method=RequestMethod.POST,produces="application/json")
	public JSONObject updateSubGroup(@RequestParam(value="upperGroupCode")String upperGroupCode,
									@RequestParam(value="subGroupCode")String subGroupCode,
									@RequestParam(value="subGroupName")String subGroupName){	
		int result = subGroupFacade.updateSubGroup(upperGroupCode, subGroupCode, subGroupName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println("updateSubGroup: "+result+upperGroupCode+subGroupCode+upperGroupCode);
		return json;
	}
	
	@RequestMapping(value="/deleteSubGroup", method=RequestMethod.POST,produces="application/json")
	public JSONObject deleteSubGroup(@RequestParam(value="upperGroupCode")String upperGroupCode,
									@RequestParam(value="subGroupCode")String subGroupCode,
									@RequestParam(value="subGroupName")String subGroupName){	
		int result = subGroupFacade.deleteSubGroup(upperGroupCode, subGroupCode, subGroupName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println("deleteSubGroup: "+result);
		return json;
	}	
	
	@RequestMapping(value="/getAllSubGroup", method = RequestMethod.GET, produces="application/json")
	public JSONObject getAllsubGroup(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<SubGroupForBaseline> list = subGroupFacade.getAllSubGroup();
		for(int index = 0; index < list.size();index++){
			json.put("upperGroupCode", list.get(index).getGroupCode());
			json.put("upperGroupName", list.get(index).getGroupName());
			json.put("subGroupCode",list.get(index).getSubGroupCode());
			json.put("subGroupName", list.get(index).getSubGroupName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("rowNum",jsonArray.size());
		json.put("table", jsonArray);
		System.out.println(json);
		return json;
	}

	@RequestMapping(value="/insertUpperGroup", method=RequestMethod.POST, produces="application/json")
	public JSONObject insertUpperGroup(@RequestParam(value="siteCode")String siteCode, @RequestParam(value="groupCode")String groupCode, @RequestParam(value="groupName")String groupName){
		int result=upperGroupFacade.insertUpperGroup(siteCode, groupCode, groupName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println("insertUpperGroup: "+result);
		return json;
	}
	
	@RequestMapping(value="/updateUpperGroup", method=RequestMethod.POST, produces="application/json")
	public JSONObject updateUpperGroup(@RequestParam(value="siteCode")String siteCode, @RequestParam(value="groupCode")String groupCode, @RequestParam(value="groupName")String groupName){
		int result=upperGroupFacade.updateUpperGroup(siteCode, groupCode, groupName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println(result);
		return json;
	}
	
	@RequestMapping(value="/deleteUpperGroup", method=RequestMethod.POST, produces="application/json")
	public JSONObject deleteUpperGroup(@RequestParam(value="siteCode")String siteCode, @RequestParam(value="groupCode")String groupCode, @RequestParam(value="groupName")String groupName){
		int result=upperGroupFacade.deleteUpperGroup(siteCode, groupCode, groupName);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println(result);
		return json;
	}
	
	@RequestMapping(value="/getAllUpperGroup", method = RequestMethod.GET, produces="application/json")
	public JSONObject getAllUpperGroup(){
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<UpperGroup> list = upperGroupFacade.getAllUpperGroup();
		for(int index=0; index < list.size();index++){
			json.put("siteCode", list.get(index).getSiteCode());
			json.put("groupCode", list.get(index).getGroupCode());
			json.put("groupName", list.get(index).getGroupName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("rowNum", jsonArray.size());
		json.put("table", jsonArray);
		
		return json;
	}
	@RequestMapping(value="/getEnergyUse", method = RequestMethod.GET, produces="application/json")
	public JSONObject getAllEnergyUse(){
		List<EnergyUse> list = energyUseFacade.getAllEnergyUse();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int index = 0;index < list.size();index++){
			json.put("code", list.get(index).getCode());
			json.put("name", list.get(index).getName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("rowNum", jsonArray.size());
		json.put("table", jsonArray);
		return json;
	}
	
	@RequestMapping(value="/insertEnergyUse", method = RequestMethod.POST,produces="application/json")
	public JSONObject insertEnergyUse(@RequestParam(value="code")String code, @RequestParam(value="name")String name){
		int result = energyUseFacade.insertEnergyUse(code, name);
		JSONObject json = new JSONObject();
		json.put("result", result);
		System.out.println(result);
		return json;
	}
	
	@RequestMapping(value="/updateEnergyUse", method = RequestMethod.POST,produces="application/json")
	public JSONObject updateEnergyUse(@RequestParam(value="code")String code, @RequestParam(value="name")String name){
		int result = energyUseFacade.updateEnergyUse(code, name);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
	
	@RequestMapping(value="/deleteEnergyUse", method = RequestMethod.POST,produces="application/json")
	public JSONObject deleteEnergyUse(@RequestParam(value="code")String code, @RequestParam(value="name")String name){
		int result = energyUseFacade.deleteEnergyUse(code, name);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json;
	}
}
