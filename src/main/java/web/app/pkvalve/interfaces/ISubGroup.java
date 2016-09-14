package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.SubGroup;

public interface ISubGroup {
	public List<SubGroup> retrieveSubGroup(String upperGroupCode) throws DataAccessException;
	public List<SubGroup> retrieveAllSubGroup() throws DataAccessException;
	public int insertSubGroup(String subGroupCode, String subGroupName, String upperGroupCode) throws DataAccessException;
	public int deleteSubGroup(String subGroupCode)  throws DataAccessException;
	public int updateSubGroup(String subGroupCode,String subGroupName, String upperGroupCode)  throws DataAccessException;
}
