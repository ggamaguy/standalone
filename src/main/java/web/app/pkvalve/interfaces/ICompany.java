package web.app.pkvalve.interfaces;

import java.util.List;
import org.springframework.dao.DataAccessException;
import web.app.pkvalve.domains.Company;

public interface ICompany {
	public List<Company> getAllCompany() throws DataAccessException;
	public Company getCompanyByCompanyCode(String companyCode) throws DataAccessException;
	public int insertCompany(String comapanyCode, String companyName) throws DataAccessException;
	public int updateCompany(String comapanyCode, String companyName) throws DataAccessException;
	public int deleteCompany(String comapanyCode, String companyName) throws DataAccessException;
}
