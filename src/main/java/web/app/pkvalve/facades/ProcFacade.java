package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.Proc;
import web.app.pkvalve.interfaces.IProc;
@Service
public class ProcFacade implements IProc{

	@Autowired
	SqlSession session;
	
	@Override
	public List<Proc> getAllProcList() throws DataAccessException {
		try{
			return session.selectList("ProcFacade.getAllProcList");
		}catch(Exception e){
			return null;
		}
	}

}
