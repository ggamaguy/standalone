package web.app.pkvalve.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import web.app.pkvalve.domains.EquipmentDetail;

public interface IEqDetail {
	public List<EquipmentDetail> retrieveEqDetail(String eqCode) throws DataAccessException;
}
