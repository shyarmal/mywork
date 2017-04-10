package swipe.test.domain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 {
"rating":5,
"availability":[{"dayIndex":7,"title":"Sunday"},{"dayIndex":5,"title":"Friday"},{"dayIndex":2,"title":"Tuesday"},{"dayIndex":3,"title":"Wednesday"},{"dayIndex":6,"title":"Saturday"},{"dayIndex":1,"title":"Monday"},null,null],
"isActive":true,
"userId":49,
"transportation":"CAR",
"skills":["President and TeaEO","Genius","Head of global trends and futuring"],
"jobSearchAddress":{"unit":"km","maxJobDistance":50,"latitude":"50.305751","longitude":"14.9517"},
"certificates":["The Risk Taker","Outstanding Memory Award","Outstanding Innovator","Marvelous Multitasker","Healthy Living Promoter","Office Lunch Expert","Excellence in Humor and Entertainment"],
"hasDriversLicense":true,
"phone":"+1 (980) 474-2391",
"name":{"last":"Maldonado","first":"Kim"},
"guid":"562f6647149fab43222ec756",
"email":"kim.maldonado@gonkle.ca",
"age":35
}
 * */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker {

	@JsonProperty("rating")
	private Integer rating;
	@JsonProperty("availability")
	private List<Days> availability;
	@JsonProperty("isActive")
	private Boolean isActive;
	@JsonProperty("userId")
	private Integer userId;
	@JsonProperty("transportation")
	private String transportation;
	@JsonProperty("skills")
	private List<String> skills;
	@JsonProperty("jobSearchAddress")
	private JobSearchAddress jobSearchAddress;
	@JsonProperty("certificates")
	private List<String> certificates;
	@JsonProperty("hasDriversLicense")
	private Boolean hasDriversLicense;
	@JsonProperty("phone")
	private String phone;
	@JsonProperty("name")
	private Name name;
	@JsonProperty("guid")
	private String guid;
	@JsonProperty("email")
	private String email;
	@JsonProperty("age")
	private Integer age;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<Days> getAvailability() {
		return availability;
	}

	public void setAvailability(List<Days> availability) {
		this.availability = availability;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTransportation() {
		return transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public JobSearchAddress getJobSearchAddress() {
		return jobSearchAddress;
	}

	public void setJobSearchAddress(JobSearchAddress jobSearchAddress) {
		this.jobSearchAddress = jobSearchAddress;
	}

	public List<String> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<String> certificates) {
		this.certificates = certificates;
	}

	public Boolean getHasDriversLicense() {
		return hasDriversLicense;
	}

	public void setHasDriversLicense(Boolean hasDriversLicense) {
		this.hasDriversLicense = hasDriversLicense;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
