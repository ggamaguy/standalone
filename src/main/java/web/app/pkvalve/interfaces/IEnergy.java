package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface IEnergy {
	public String getEnergyType(String energyCode) throws DataAccessException;
}
