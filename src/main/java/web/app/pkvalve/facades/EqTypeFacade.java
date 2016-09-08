package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.EquipmentType;
import web.app.pkvalve.interfaces.IEqType;

@Service
public class EqTypeFacade implements IEqType {

	@Autowired
	SqlSession session;
	
	@Override
	public List<EquipmentType> retrieveEqType(String subGroupCode) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
