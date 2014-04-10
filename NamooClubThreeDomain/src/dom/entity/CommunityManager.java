package dom.entity;

import com.namoo.ns1.common.Identifiable;

public class CommunityManager implements Identifiable {

	private static final long serialVersionUID = -1543226812538269293L;
	
	private String communityName;
	private SocialPerson rolePerson;

	//--------------------------------------------------------------------------
	// constructor
	
	/**
	 * 
	 * @param rolePerson
	 */
	public CommunityManager(String communityName, SocialPerson rolePerson){
		//
		this.communityName = communityName;
		this.rolePerson = rolePerson;
	}
	
	//--------------------------------------------------------------------------
	// getter/setter
	
	public String getName() {
		//
		return rolePerson.getName();
	}

	public String getEmail() {
		// 
		return rolePerson.getEmail();
	}
	
	@Override
	public String getOId() {
		// 
		return communityName + "|" + rolePerson.getEmail();
	}

}