package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.Site;

public interface ISite {
	public List<Site> getAllSiteList() throws DataAccessException;
	public int insertSite(String companyCode, String siteCode, String siteName) throws DataAccessException;
	public int updateSite(String companyCode, String siteCode, String siteName) throws DataAccessException;
	public int deleteSite(String companyCode, String siteCode, String siteName) throws DataAccessException;
}
