package web.app.pkvalve.facades;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.interfaces.IEnergy;
@Service
public class EnergyFacade implements IEnergy{

	@Autowired
	SqlSession session;
	
	@Override
	public String getEnergyType(String energyCode) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("energyCode", energyCode);
		try{
		return session.selectOne("EnergyFacade.getEnergyType", hashmap);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
