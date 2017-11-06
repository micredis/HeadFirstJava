import java.util.ArrayList;

public class DotCom {
	ArrayList<String> locationCells;
	String name;
	//int numOfHits = 0;

	public void setLocationCells(ArrayList<String> loc) {
		locationCells = loc;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String checkYourself(String userInput) {
		String result = "miss";
		int index = locationCells.indexOf(userInput);
		if (index >= 0) {
			locationCells.remove(index);
			if (locationCells.isEmpty()) {
				result = "kill";
			} else {
				result = "hit";
			}
		}

		return result;
	}
}