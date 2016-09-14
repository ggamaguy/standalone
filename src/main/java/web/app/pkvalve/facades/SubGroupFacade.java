package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import web.app.pkvalve.domains.SubGroup;
import web.app.pkvalve.interfaces.ISubGroup;

@Service
public class SubGroupFacade implements ISubGroup{

	@Autowired
	SqlSession session;
	
	@Override
	public List<SubGroup> retrieveSubGroup(String upperGroupCode) throws DataAccessException {
		try{
			return session.selectList("SubGroupFacade.getSubGroupByUpperGroupId",upperGroupCode);
		}catch(DataAccessException e){
			return null;
		}
	}

	@Override
	public List<SubGroup> retrieveAllSubGroup() throws DataAccessException {
		try{
			return session.selectList("SubGroupFacade.getAllSubGroup");
		}catch(DataAccessException e){
			e.printStackTrace();
			return null;
		}
	}
}
