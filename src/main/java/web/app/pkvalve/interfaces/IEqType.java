package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.EquipmentType;

public interface IEqType {
	public List<EquipmentType>retrieveEqType(String subGroupCode) throws DataAccessException;
}
