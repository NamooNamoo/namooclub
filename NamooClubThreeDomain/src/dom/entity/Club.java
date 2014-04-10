package dom.entity;

import java.util.ArrayList;
import java.util.List;

import com.namoo.ns1.common.Identifiable;

public class Club implements Identifiable{

	private static final long serialVersionUID = -9203553406578338655L;


	private String id;
	private String name;
	private String description;

	private ClubManager manager;
	private List<ClubMember> members;

	private String category;
	private String date;

	//--------------------------------------------------------------------------
	// constructors

	/**
	 *
	 * @param clubName
	 * @param admin
	 */
	public Club(String id, String clubName, String description, SocialPerson admin, String category, String date){
		//
		this.id = id;
		this.name = clubName;
		this.description = description;
		this.members = new ArrayList<ClubMember>();
		this.category = category;
		this.date = date;

		setManager(admin);
		addMember(admin);

	}

	//--------------------------------------------------------------------------
	// getter/setter



	public String getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
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

	public ClubManager getManager() {
		return manager;
	}

	public List<ClubMember> getMembers() {
		return members;
	}

	//--------------------------------------------------------------------------
	// public methods

	public ClubMember findMember(String email) {
		//
		for (ClubMember member : members) {
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
		ClubManager manager = new ClubManager(name, rolePerson);
		this.manager = manager;
	}

	/**
	 *
	 * @param rolePerson
	 */
	public void addMember(SocialPerson rolePerson){
		//
		ClubMember member = new ClubMember(name, rolePerson);
		this.members.add(member);
	}

	@Override
	public String getOId() {
		//
		return id;
	}

	public void removeMember(String email) {
		//
		ClubMember found = null;
		for (ClubMember member : members) {
			if (member.getEmail().equals(email)) {
				found = member;
			}
		}
		if (found != null) {
			members.remove(found);
		}
	}
}
