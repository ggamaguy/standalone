package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.Equipment;
import web.app.pkvalve.domains.UpperGroup;

public interface IUpperGroup {
	public List<UpperGroup> retrieveUpperGroup(String siteCode) throws DataAccessException;
	public UpperGroup retrieveUpperGroupByGroupId(String groupCode) throws DataAccessException;
}
