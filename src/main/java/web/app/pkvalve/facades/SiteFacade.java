package web.app.pkvalve.facades;

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
	
	
}
