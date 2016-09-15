package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.Equipment;
import web.app.pkvalve.domains.UpperGroup;

public interface IUpperGroup {
	public List<UpperGroup> getUpperGroup(String siteCode) throws DataAccessException;
	public List<UpperGroup> getAllUpperGroup() throws DataAccessException;
	public int insertUpperGroup(String siteCode, String groupCode, String groupName) throws DataAccessException;
	public int updateUpperGroup(String siteCode, String groupCode, String groupName) throws DataAccessException;
	public int deleteUpperGroup(String siteCode, String groupCode, String groupName) throws DataAccessException;
}
