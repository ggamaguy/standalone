package web.app.pkvalve.domains;

import java.util.List;

public class Equipment {
	private String id;
	private String companyCode;//ȸ�� �ڵ�
	private String siteName; //�����
	private String groupName;//����׷�
	private String eqCode;//���� �ڵ�
	private String eqDetail;//���� ����
	private String subGroupName;//����
	private String location;//��ġ
	private String funcLoc;//�����ġ
	private String processName;//����
	private String energyCode1;//������ �ڵ�1
	private String energyCode2;//�������ڵ�2
	private String energyCode3;//������ �ڵ�3
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getEqCode() {
		return eqCode;
	}
	public void setEqCode(String eqCode) {
		this.eqCode = eqCode;
	}
	public String getEqDetail() {
		return eqDetail;
	}
	public void setEqDetail(String eqDetail) {
		this.eqDetail = eqDetail;
	}
	public String getSubGroupName() {
		return subGroupName;
	}
	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFuncLoc() {
		return funcLoc;
	}
	public void setFuncLoc(String funcLoc) {
		this.funcLoc = funcLoc;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getEnergyCode1() {
		return energyCode1;
	}
	public void setEnergyCode1(String energyCode1) {
		this.energyCode1 = energyCode1;
	}
	public String getEnergyCode2() {
		return energyCode2;
	}
	public void setEnergyCode2(String energyCode2) {
		this.energyCode2 = energyCode2;
	}
	public String getEnergyCode3() {
		return energyCode3;
	}
	public void setEnergyCode3(String energyCode3) {
		this.energyCode3 = energyCode3;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
