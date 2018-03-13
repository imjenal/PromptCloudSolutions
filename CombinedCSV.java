public class CombinedCSV implements Comparable {
	String fName;
	String lName;
	String dob;
	Long salary;
	Integer height;

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public CombinedCSV(String fName, String lName, String dob, Long salary, Integer height) {
		this.fName = fName;
		this.lName = lName;
		this.dob = dob;
		this.salary = salary;
		this.height = height;
	}

	@Override
	public String toString() {
		return fName + "," + lName + "," + dob + "," + salary + "," + height;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CombinedCSV))
			return false;
		CombinedCSV that = (CombinedCSV) o;
		if (!fName.equals(that.getfName()))
			return false;
		if (!lName.equals(that.getlName()))
			return false;
		if (!dob.equals(that.getDob()))
			return false;
		if (!salary.equals(that.getSalary()))
			return false;
		return height.equals(that.height);
	}

	public int compareTo(Object ob) {
		CombinedCSV o = (CombinedCSV) ob;
		int diff = this.dob.compareTo(o.getDob());
		if (diff != 0)
			return diff;
		else {
			diff = this.salary.compareTo(o.getSalary());
			if (diff != 0)
				return diff;
			else
				return this.height.compareTo(o.getHeight());
		}
	}
}
