package web.app.pkvalve.facades;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.EnergyUse;
import web.app.pkvalve.interfaces.IEnergyUse;

@Service
public class EnergyUseFacade implements IEnergyUse{

	@Autowired
	SqlSession session;
	
	@Override
	public List<EnergyUse> getAllEnergyUse() throws DataAccessException {
		try{
			return session.selectList("EnergyUseFacade.getAllEnergyUse"); 
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateEnergyUse(String code, String name) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("code", code);
		hashmap.put("name", name);
		try{
			return session.update("EnergyUseFacade.updateEnergyUse",hashmap);
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public int insertEnergyUse(String code, String name) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("code", code);
		hashmap.put("name", name);
		try{
			return session.insert("EnergyUseFacade.insertEnergyUse", hashmap);
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public int deleteEnergyUse(String code, String name) throws DataAccessException {
		HashMap<String,String> hashmap = new HashMap<String,String>();
		hashmap.put("code", code);
		hashmap.put("name", name);
		try{
			return session.delete("EnergyUseFacade.deleteEnergyUse",hashmap);
		}catch(Exception e){
			return 0;
		}
	}

}
