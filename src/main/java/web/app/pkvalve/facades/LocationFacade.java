package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.Location;
import web.app.pkvalve.interfaces.ILocation;

@Service
public class LocationFacade implements ILocation {

	@Autowired
	SqlSession session;
	
	@Override
	public List<Location> getAllLocList() throws DataAccessException {
		try{
			return session.selectList("LocationFacade.getAllLocList");
		}catch(Exception e){
			return null;
		}
	}

}
