package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class SocialPerson implements Identifiable {

	private static final long serialVersionUID = -1648010196772136086L;
	
	private String name;
	private String email;
	private String password;

	public SocialPerson(){
		//
	}

	/**
	 * 
	 * @param name
	 * @param email
	 * @param password
	 */
	public SocialPerson(String name, String email, String password){
		//
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getOId() {
		//
		return email;
	}
}