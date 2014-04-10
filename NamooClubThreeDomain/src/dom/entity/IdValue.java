package dom.entity;

import com.namoo.ns1.common.Identifiable;


public class IdValue implements Identifiable {

	private static final long serialVersionUID = 4120185083354293408L;

	private String name;
	private int value;


	public IdValue(String name) {
		//
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public void increaseValue(){
		value++;
	}

	@Override
	public String getOId() {
		//
		return name;
	}
}
