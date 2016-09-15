package web.app.pkvalve.facades;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.UpperGroup;
import web.app.pkvalve.interfaces.IUpperGroup;

@Service
public class UpperGroupFacade implements IUpperGroup{
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<UpperGroup> getUpperGroup(String siteCode) throws DataAccessException {
		try{
			return session.selectList("UpperGroupFacade.getUpperGroupBySiteId",siteCode);
		}catch(DataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UpperGroup> getAllUpperGroup() throws DataAccessException {
		try{
			return session.selectList("UpperGroupFacade.getAllUpperGroup");
		}catch(DataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertUpperGroup(String siteCode, String groupCode, String groupName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("siteCode", siteCode);
		hashmap.put("groupCode", groupCode);
		hashmap.put("groupName", groupName);
		return session.insert("UpperGroupFacade.insertUpperGroup", hashmap);
	}

	@Override
	public int updateUpperGroup(String siteCode, String groupCode, String groupName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("siteCode", siteCode);
		hashmap.put("groupCode", groupCode);
		hashmap.put("groupName", groupName);
		return session.insert("UpperGroupFacade.updateUpperGroup", hashmap);
	}

	@Override
	public int deleteUpperGroup(String siteCode, String groupCode, String groupName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("siteCode", siteCode);
		hashmap.put("groupCode", groupCode);
		hashmap.put("groupName", groupName);
		return session.insert("UpperGroupFacade.deleteUpperGroup", hashmap);
	}
}
