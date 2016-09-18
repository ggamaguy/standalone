package web.app.pkvalve.facades;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import web.app.pkvalve.domains.Company;
import web.app.pkvalve.interfaces.ICompany;

@Service
public class CompanyFacade implements ICompany{

	@Autowired
	SqlSession session;
	@Override
	public List<Company> getAllCompany() throws DataAccessException {
		try{
			return session.selectList("CompanyFacade.getAllCompany");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Company getCompanyByCompanyCode(String companyCode) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCompany(String comapanyCode, String companyName) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCompany(String comapanyCode, String companyName) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCompany(String comapanyCode, String companyName) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
