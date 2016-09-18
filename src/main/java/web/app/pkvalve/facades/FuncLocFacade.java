package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.FuncLoc;
import web.app.pkvalve.interfaces.IFuncLoc;

@Service
public class FuncLocFacade implements IFuncLoc {

	@Autowired
	SqlSession session;
	
	@Override
	public List<FuncLoc> getAllFuncLocList() throws DataAccessException {
		try{
			return session.selectList("FuncLocFacade.getAllFuncLocList");
		}catch(Exception e){
			return null;
		}
	}

}
