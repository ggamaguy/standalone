package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.domains.SubGroupForBaseline;

public interface ISubGroup {
	public List<SubGroup> getSubGroup(String upperGroupCode) throws DataAccessException;
	public List<SubGroupForBaseline> getAllSubGroup() throws DataAccessException;
	public int insertSubGroup(String upperGroupCode, String subGroupCode, String subGroupName) throws DataAccessException;
	public int deleteSubGroup(String upperGroupCode, String subGroupCode, String subGroupName)  throws DataAccessException;
	public int updateSubGroup(String upperGroupCode, String subGroupCode, String subGroupName)  throws DataAccessException;
}
