package dom.bizlogic;
import java.util.HashMap;
import java.util.Map;

import dom.entity.SocialPerson;

public class TownerRepository {
	
	private static TownerRepository instance;
	
	public Map<String, SocialPerson> townersMap;

	//--------------------------------------------------------------------------
	
	private TownerRepository(){
		//
		this.townersMap = new HashMap<String, SocialPerson>();
	}
	
	public static TownerRepository getInstance() {
		// 
		return instance;
	}
	
	//--------------------------------------------------------------------------
	
	/**
	 * 
	 * @param email
	 */
	public SocialPerson findPerson(String email){
		//
		return townersMap.get(email);
	}

	/**
	 * 
	 * @param towner
	 */
	public void addTowner(SocialPerson towner){
		//
		townersMap.put(towner.getEmail(), towner);
	}

}