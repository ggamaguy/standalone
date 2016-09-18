package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.EqCategory;
import web.app.pkvalve.domains.Equipment;
import web.app.pkvalve.domains.EquipmentList;
import web.app.pkvalve.domains.SubGroup;

public interface IEq {
	public List<Equipment> getEqBySite(String siteCode)throws DataAccessException;
	public List<Equipment> getEqBySiteUppergroup(String siteCode, String groupCode) throws DataAccessException;
	public List<Equipment> getEqBySiteUppergroupSubgroup(String siteCOde, String groupCode, String subGroupCode) throws DataAccessException;
	public List<EqCategory> getAllEqCategory() throws DataAccessException;
	public List<EqCategory> getEqCategoryByEqType(String eqType) throws DataAccessException;
	public List<SubGroup> getSubGroupNameByEqTypeEqDetail(String eqType, String eqDetail)throws DataAccessException;
	public List<Equipment> getEqTableByEqType(String eqType) throws DataAccessException;
	public List<Equipment> getEqTableByEqTypeEqDetail(String eqType,String eqDetail) throws DataAccessException;
	public List<Equipment> getEqTableByEqTypeEqDetailSubGroupName(String eqType, String eqDetail, String subGroupName) throws DataAccessException;
	public Equipment getEqByEqId(String eqId) throws DataAccessException;
	public int updateEquipment(EquipmentList eq) throws DataAccessException;
	public int insertEquipment(EquipmentList eq) throws DataAccessException;
	public int deleteEquipment(String eqId) throws DataAccessException;
}	
