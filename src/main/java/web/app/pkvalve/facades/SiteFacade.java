package web.app.pkvalve.facades;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.Site;
import web.app.pkvalve.interfaces.ISite;

@Service
public class SiteFacade implements ISite {
	@Autowired
	SqlSession session;

	@Override
	public List<Site> getAllSiteList() throws DataAccessException{
		try{
			return session.selectList("SiteFacade.getAllSites");
		}catch(DataAccessException e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Site> getSiteByCompanyCode(String companyCode) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("companyCode", companyCode);
		//System.out.println("aaaaaaa");
		try{
			return session.selectList("SiteFacade.getSiteByCompanyCode", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int insertSite(String companyCode, String siteCode, String siteName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("companyCode", companyCode);
		hashmap.put("siteCode", siteCode);
		hashmap.put("siteName", siteName);
		try{
		return session.insert("SiteFacade.insertSite", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateSite(String companyCode, String siteCode, String siteName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("companyCode", companyCode);
		hashmap.put("siteCode", siteCode);
		hashmap.put("siteName", siteName);
		try{
		return session.update("SiteFacade.updateSite", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteSite(String companyCode, String siteCode, String siteName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("companyCode", companyCode);
		hashmap.put("siteCode", siteCode);
		hashmap.put("siteName", siteName);
		try{
		return session.delete("SiteFacade.deleteSite", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
}
