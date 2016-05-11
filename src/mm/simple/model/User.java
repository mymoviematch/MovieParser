package mm.simple.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

	public String name, surname;
	public String phone;
	public Long peopleId;
	
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}


	public Long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	public User(){};
	    
	    public User(Long peopleId, String name, String surname, String phone){
	    	
	    	this.peopleId = peopleId;

	    	this.name = name;
	    	this.surname = surname;
	    	this.phone = phone;
	    }

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		@Override
		public String toString(){
			return "User:" + name+" "+ surname+" "+ phone;
		}
}
