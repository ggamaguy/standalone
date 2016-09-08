package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.SubGroup;

public interface ISubGroup {
	public List<SubGroup> retrieveSubGroup(String upperGroupCode) throws DataAccessException;
}
