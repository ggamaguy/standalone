package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.EquipmentDetail;
import web.app.pkvalve.interfaces.IEqDetail;

@Service
public class EqDetailFacade implements IEqDetail{

	@Autowired
	SqlSession session;
	
	@Override
	public List<EquipmentDetail> retrieveEqDetail(String eqCode) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
