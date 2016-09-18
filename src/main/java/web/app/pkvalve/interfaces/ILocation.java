package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.Location;

public interface ILocation {
	public List<Location> getAllLocList() throws DataAccessException;
}
