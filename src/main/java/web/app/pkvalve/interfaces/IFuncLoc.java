package web.app.pkvalve.interfaces;

import java.util.List;
import org.springframework.dao.DataAccessException;
import web.app.pkvalve.domains.FuncLoc;

public interface IFuncLoc {
	public List<FuncLoc> getAllFuncLocList() throws DataAccessException;
}
