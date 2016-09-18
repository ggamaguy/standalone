package web.app.pkvalve.interfaces;

import java.util.List;
import web.app.pkvalve.domains.Proc;
import org.springframework.dao.DataAccessException;


public interface IProc {
	public List<Proc> getAllProcList() throws DataAccessException;
}
