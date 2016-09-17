package web.app.pkvalve.facades;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.EqCategory;
import web.app.pkvalve.domains.Equipment;
import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.interfaces.IEq;

@Service
public class EqFacade implements IEq {

	@Autowired
	SqlSession session;

	@Override
	public List<Equipment> getEqBySite(String siteCode) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("siteCode", siteCode);
		try {
			return session.selectList("SiteFacade.getEqBySite", hashmap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Equipment> getEqBySiteUppergroup(String siteCode, String groupCode) throws DataAccessException {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("siteCode", siteCode);
		hashmap.put("groupCode", groupCode);
		try {
			return session.selectList("UpperGroupFacade.getEqBySiteUppergroup", hashmap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Equipment> getEqBySiteUppergroupSubgroup(String siteCode, String groupCode, String subGroupCode)
			throws DataAccessException {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		hashmap.put("siteCode", siteCode);
		hashmap.put("groupCode", groupCode);
		hashmap.put("subGroupCode", subGroupCode);
		try {
			return session.selectList("SubGroupFacade.getEqBySiteUppergroupSubgroup", hashmap);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<EqCategory> getAllEqCategory() throws DataAccessException {
		try{
			return session.selectList("EqFacade.getAllEqCategory");
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EqCategory> getEqCategoryByEqType(String eqType) throws DataAccessException {
		try{
			return session.selectList("EqFacade.getEqCategoryByEqType", eqType);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<SubGroup> getSubGroupNameByEqTypeEqDetail(String eqType, String eqDetail)
			throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("eqType", eqType);
		hashmap.put("eqDetail", eqDetail);
		try{
			return session.selectList("EqFacade.getSubGroupNameByEqTypeEqDetail",hashmap);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<Equipment> getEqTableByEqType(String eqType) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("eqType", eqType);
		try{
			return session.selectList("EqFacade.getEqTableByEqType", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Equipment> getEqTableByEqTypeEqDetail(String eqType, String eqDetail) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("eqType", eqType);
		hashmap.put("eqDetail", eqDetail);
		try{
			return session.selectList("EqFacade.getEqTableByEqTypeEqDetail", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Equipment> getEqTableByEqTypeEqDetailSubGroupName(String eqType, String eqDetail, String subGroupName)
			throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("eqType", eqType);
		hashmap.put("eqDetail", eqDetail);
		hashmap.put("subGroupName", subGroupName);
		try{
			return session.selectList("EqFacade.getEqTableByEqTypeEqDetailSubGroupName", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Equipment getEqByEqId(String eqId) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("eqId", eqId);
		try{
			return session.selectOne("EqFacade.getEqByEqId", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
