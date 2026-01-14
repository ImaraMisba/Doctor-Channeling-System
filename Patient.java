public class Patient {
	private String name;
	private String mobile;
	private String email;
	private String city;
	private int age;
	private String medicalHistory;
	
	public Patient(String name, String mobile, String email, String city, int age, String medicalHistory)
	{
		this.name =name;
		this.mobile=mobile;
		this.email= email;
		this.city= city;
		this.age = age;
		this.medicalHistory=medicalHistory;
		
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public void displayPatient() {
        System.out.println("Name: " + name);
        System.out.println("Mobile: " + mobile);
        System.out.println("Email: " + email);
        System.out.println("City: " + city);
        System.out.println("Age: " + age);
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("-----------------------------");
    }
}
