package web.app.pkvalve.interfaces;

import java.util.List;
import org.springframework.dao.DataAccessException;
import web.app.pkvalve.domains.Energy;

public interface IEnergy {
	public List<Energy> getAllEnergyType() throws DataAccessException;
	public String getEnergyType(String energyCode) throws DataAccessException;
}
