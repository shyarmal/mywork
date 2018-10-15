package vote.counting.aconex.domain;

import java.util.List;

public class Candidate {

	private String name;
	private int preference;
	private boolean greyed;
	
	public Candidate(String name, int preference) {
		this.name = name;
		this.preference = preference;
		this.greyed = (preference == 0);
	}

	public boolean isGreyed() {
		return greyed;
	}

	public String getName() {
		return name;
	}

	public int getPreference() {
		return preference;
	}
	
	public void greyOutIfInList(List<String> eliminations) {
		if (!this.greyed)
			this.greyed =  eliminations.contains(this.name);
	}
}
