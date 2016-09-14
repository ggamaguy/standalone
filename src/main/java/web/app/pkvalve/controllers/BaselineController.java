package web.app.pkvalve.controllers;

import java.util.ArrayList;
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

import web.app.pkvalve.domains.Site;
import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.domains.UpperGroup;
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
		System.out.println(json);
		return json;
	}
	@RequestMapping(value="/updateSite", method = RequestMethod.POST, produces="application/json")
	public JSONObject updateSite(@RequestParam("siteCode")String siteCode, @RequestParam("siteName")String siteName){
		int result = siteFacade.updateSite(siteCode, siteName);
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
		System.out.println(json);
		return json;
	}
	@RequestMapping(value="/deleteSite", method = RequestMethod.POST,produces="application/json")
	public JSONObject deleteSite(@RequestParam("siteCode")String siteCode, @RequestParam("siteName")String siteName){
		int result = siteFacade.deleteSite(siteCode, siteName);
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
		System.out.println(json);
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
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}
	@RequestMapping(value="/subgroup",method = RequestMethod.GET)
	public ModelAndView subGroup(){
		ModelAndView mv = new ModelAndView("subgroup");
		List<SubGroup> list = subGroupFacade.retrieveAllSubGroup();
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i < list.size();i++){
			json.put("upperGroupCode", list.get(i).getUpperGroupCode());
			json.put("subGroupCode", list.get(i).getSubGroupCode());
			json.put("subGroupName", list.get(i).getSubGroupName());
			json.put("upperGroupName", upperGroupFacade.retrieveUpperGroupByGroupId(list.get(i).getUpperGroupCode()).getGroupName());
			JSONObject json2 = new JSONObject(json);
			jsonArray.add(json2);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		mv.addObject("subgroup",json);
		return mv;
	}
	@RequestMapping(value="/insertsubgroup", method = RequestMethod.GET,produces="application/json")
	public JSONObject insertSubGroup(@RequestParam("upperGroupName")String upperGroupName,
										@RequestParam("upperGroupCode")String upperGroupCode,
										@RequestParam("subGroupCode")String subGroupCode,
										@RequestParam("subGroupName")String subGroupName){
		subGroupFacade.
	}
}
