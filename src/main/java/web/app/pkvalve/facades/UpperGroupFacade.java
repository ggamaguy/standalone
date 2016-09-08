package web.app.pkvalve.facades;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import web.app.pkvalve.domains.UpperGroup;
import web.app.pkvalve.interfaces.IUpperGroup;

@Service
public class UpperGroupFacade implements IUpperGroup{
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<UpperGroup> retrieveUpperGroup(String siteCode) throws DataAccessException {
		try{
			return session.selectList("UpperGroupFacade.getUpperGroupBySiteId",siteCode);
		}catch(DataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

}
