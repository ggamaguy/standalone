package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.Site;

public interface ISite {
	public List<Site> getAllSiteList() throws DataAccessException;
	public int insertSite(String siteCode, String siteName) throws DataAccessException;
	public int updateSite(String siteCode, String siteName) throws DataAccessException;
	public int deleteSite(String siteCode, String siteName) throws DataAccessException;
}
