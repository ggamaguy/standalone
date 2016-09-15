package web.app.pkvalve.facades;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.domains.SubGroupForBaseline;
import web.app.pkvalve.interfaces.ISubGroup;

@Service
public class SubGroupFacade implements ISubGroup{

	@Autowired
	SqlSession session;
	
	@Override
	public List<SubGroup> getSubGroup(String upperGroupCode) throws DataAccessException {
		try{
			return session.selectList("SubGroupFacade.getSubGroupByUpperGroupId",upperGroupCode);
		}catch(DataAccessException e){
			return null;
		}
	}

	@Override
	public List<SubGroupForBaseline> getAllSubGroup() throws DataAccessException {
		try{
			return session.selectList("SubGroupFacade.getAllSubGroupWith");
		}catch(DataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertSubGroup(String upperGroupCode, String subGroupCode, String subGroupName)
			throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("upperGroupCode", upperGroupCode);
		//hashmap.put("subGroupCode", subGroupCode);
		hashmap.put("subGroupName", subGroupName);
		return session.insert("SubGroupFacade.insertSubGroup", hashmap);
	}

	@Override
	public int deleteSubGroup(String upperGroupCode, String subGroupCode, String subGroupName) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("upperGroupCode", upperGroupCode);
		hashmap.put("subGroupCode", subGroupCode);
		hashmap.put("subGroupName", subGroupName);
		return session.insert("SubGroupFacade.deleteSubGroup", hashmap);
	}

	@Override
	public int updateSubGroup(String upperGroupCode, String subGroupCode, String subGroupName)
			throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("upperGroupCode", upperGroupCode);
		hashmap.put("subGroupCode", subGroupCode);
		hashmap.put("subGroupName", subGroupName);
		return session.insert("SubGroupFacade.updateSubGroup", hashmap);
	}
}
