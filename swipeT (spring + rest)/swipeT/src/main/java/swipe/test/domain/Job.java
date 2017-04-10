package swipe.test.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
{
"jobId":39,
"billRate":"$13.81",
"requiredCertificates":["Healthy Living Promoter","Outside the Box Thinker","Excellence in Humor and Entertainment"],
"jobTitle":"Genius",
"about":"Tempor dolor laboris nisi cupidatat non cupidatat dolore minim. Consequat magna minim aliquip est incididunt quis commodo id aute aliquip magna amet dolore consequat. Non voluptate aliqua enim exercitation do quis ex tempor enim reprehenderit deserunt est.",
"guid":"562f66aa74fad5909b0f04e5",
"location":{"latitude":"49.997162","longitude":"13.9942"},
"company":"Anacho",
"driverLicenseRequired":true,
"workersRequired":5,
"startDate":"2015-11-11T14:55:59.435Z"
}
*/

public class Job {

	private Integer jobId;
	private String billRate;
	private List<String> requiredCertificates;
	private String jobTitle;
	private String about;
	private String guid;
	private Location location;
	private String company;
	private Boolean driverLicenseRequired;
	private Integer workersRequired;
	private Date startDate;

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getBillRate() {
		return billRate;
	}

	public void setBillRate(String billRate) {
		this.billRate = billRate;
	}


	public List<String> getRequiredCertificates() {
		return requiredCertificates;
	}

	public void setRequiredCertificates(List<String> requiredCertificates) {
		this.requiredCertificates = requiredCertificates;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getDriverLicenseRequired() {
		return driverLicenseRequired;
	}

	public void setDriverLicenseRequired(Boolean driverLicenseRequired) {
		this.driverLicenseRequired = driverLicenseRequired;
	}

	public Integer getWorkersRequired() {
		return workersRequired;
	}

	public void setWorkersRequired(Integer workersRequired) {
		this.workersRequired = workersRequired;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}
