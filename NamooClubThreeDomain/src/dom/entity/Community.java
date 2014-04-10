package dom.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.namoo.ns1.common.Identifiable;

public class Community implements Identifiable {

	private static final long serialVersionUID = -1649818789572216203L;

	private String id;
	private String name;
	private String description;

	private CommunityManager manager;
	private List<CommunityMember> members;

	private Map<String, Club> clubs;

	private String[] categories;

	private String date;
	//--------------------------------------------------------------------------
	// constructors

	/**
	 *
	 * @param communityName
	 * @param admin
	 */
	public Community(String id, String communityName, String description, SocialPerson admin, String date, String[] categories){
		//
		this.id = id;
		this.name = communityName;
		this.description = description;
		this.members = new ArrayList<CommunityMember>();
		this.clubs = new HashMap<String, Club>();
		this.categories = categories;
		this.date = date;

		setManager(admin);
		addMember(admin);
	}

	//--------------------------------------------------------------------------
	// getter/setter

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CommunityManager getManager() {
		return manager;
	}

	public List<CommunityMember> getMembers() {
		return members;
	}



	//--------------------------------------------------------------------------
	// public methods

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public String getDate() {
		return date;
	}

	public CommunityMember findMember(String email) {
		//
		for (CommunityMember member : members) {
			if(member.getEmail().equals(email)) {
				return member;
			};
		}
		return null;
	}

	/**
	 *
	 * @param rolePerson
	 */
	public void setManager(SocialPerson rolePerson){
		//
		CommunityManager manager = new CommunityManager(name, rolePerson);
		this.manager = manager;
	}

	/**
	 *
	 * @param rolePerson
	 */
	public void addMember(SocialPerson rolePerson){
		//
		CommunityMember member = new CommunityMember(name, rolePerson);
		this.members.add(member);
	}

	@Override
	public String getOId() {
		//
		return id;
	}

	public void removeMember(String email) {
		//
		CommunityMember found = null;
		for (CommunityMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}

	//--------------------------------------------------------------------------------------------
	//Club

	public void addClub(Club club) {
		//
		this.clubs.put(club.getId(), club);
	}

	public Club findClub(String clubId){
		//
		return clubs.get(clubId);
	}

	public Map<String, Club> getAllClub(){
		//
		return clubs;
	}

	public void removeClub(String clubId) {
		//
		clubs.remove(clubId);
	}
}