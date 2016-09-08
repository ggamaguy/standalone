package web.app.pkvalve.controllers;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import web.app.pkvalve.domains.EquipmentDetail;
import web.app.pkvalve.domains.EquipmentType;
import web.app.pkvalve.domains.Site;
import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.domains.UpperGroup;
import web.app.pkvalve.facades.EqDetailFacade;
import web.app.pkvalve.facades.EqTypeFacade;
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
	EqTypeFacade eqTypeFacade;
	@Autowired
	EqDetailFacade eqDetailFacade;

	@RequestMapping(value = "/equipment", method = RequestMethod.GET)
	public ModelAndView site() {
		ModelAndView mv = new ModelAndView("equipmentMaster");
		try {
			List<Site> siteArray = retrieveSites();
			JSONObject json = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			for (int index = 0; index < siteArray.size(); index++) {
				json.put("siteName", siteArray.get(index).getSiteName());
				json.put("siteCode", siteArray.get(index).getSiteCode());
				json.put("siteAddress", siteArray.get(index).getSiteAddress());
				JSONObject tempJson = new JSONObject(json);
				jsonArray.add(tempJson);
			}
			json.clear();
			json.put("num", jsonArray.size());
			json.put("content", jsonArray);
			mv.addObject("sites", json);
			System.out.println(json);
		} catch (Exception e) {
			mv.addObject("sites", null);
		}
		return mv;
	}

	public List<Site> retrieveSites() {
		return siteFacade.getAllSiteList();
	}

	@RequestMapping(value = "/retrieveUpperGroup", method = RequestMethod.GET, produces = "application/json")
	public JSONObject retrieveUpperGroup(@RequestParam(value = "siteCode") String siteCode) {
		List<UpperGroup> list = uppergroupFacade.retrieveUpperGroup(siteCode);
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for(int index = 0;index < list.size();index++){
			json.put("groupCode", list.get(index).getGroupCode());
			json.put("groupName", list.get(index).getGroupName());
			JSONObject tempJson = new JSONObject(json);
			jsonArray.add(tempJson);
		}
		json.clear();
		json.put("num", jsonArray.size());
		json.put("content", jsonArray);
		return json;
	}

	@RequestMapping(value = "/retrieveSubGroup", method = RequestMethod.GET, produces = "application/json")
	public List<SubGroup> retrieveSubGroup(@RequestParam(value = "upperGroupCode") String upperGroupCode) {
		return subGroupFacade.retrieveSubGroup(upperGroupCode);
	}

	@RequestMapping(value = "/retrieveEqType", method = RequestMethod.GET, produces = "application/json")
	public List<EquipmentType> retrieveEqType(@RequestParam(value = "subGroupCode") String subGroupCode) {
		return eqTypeFacade.retrieveEqType(subGroupCode);
	}

	@RequestMapping(value = "/retrieveEqDetail", method = RequestMethod.GET, produces = "application/json")
	public List<EquipmentDetail> retrieveEqDetail(@RequestParam(value = "eqCode") String eqCode) {
		return eqDetailFacade.retrieveEqDetail(eqCode);
	}
}
