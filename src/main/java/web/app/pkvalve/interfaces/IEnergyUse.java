package web.app.pkvalve.interfaces;

import java.util.List;
import org.springframework.dao.DataAccessException;
import web.app.pkvalve.domains.EnergyUse;

public interface IEnergyUse {
	public List<EnergyUse> getAllEnergyUse() throws DataAccessException;
	public int updateEnergyUse(String code, String name) throws DataAccessException;
	public int insertEnergyUse(String code, String name) throws DataAccessException;
	public int deleteEnergyUse(String code, String name)throws DataAccessException;
}
