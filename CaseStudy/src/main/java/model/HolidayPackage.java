package model;

public class HolidayPackage {
	private String packageId;
	private String sourcePlace;
	private String destinationPlace;
	private double basicFare;
	private int noOfDays;
	private double packageCost;
	
	//Constructors
	public HolidayPackage() {}

	public HolidayPackage(String packageId, String sourcePlace, String destinationPlace, int noOfDays, double basicFare, 
			double packageCost) {
		super();
		this.packageId = packageId;
		this.sourcePlace = sourcePlace;
		this.destinationPlace = destinationPlace;
		this.basicFare = basicFare;
		this.noOfDays = noOfDays;
		this.packageCost = packageCost;
	}
	
	public HolidayPackage(String packageId, String sourcePlace, String destinationPlace, int noOfDays, double basicFare) {
		super();
		this.packageId = packageId;
		this.sourcePlace = sourcePlace;
		this.destinationPlace = destinationPlace;
		this.basicFare = basicFare;
		this.noOfDays = noOfDays;
		this.calculatePackageCost();
	}
	
	public HolidayPackage(String packageId, String sourcePlace, String destinationPlace, int noOfDays) {
		super();
		this.packageId = packageId;
		this.sourcePlace = sourcePlace;
		this.destinationPlace = destinationPlace;
		this.noOfDays = noOfDays;
	}

	//Getters and Setters
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getSourcePlace() {
		return sourcePlace;
	}
	public void setSourcePlace(String sourcePlace) {
		this.sourcePlace = sourcePlace;
	}

	public String getDestinationPlace() {
		return destinationPlace;
	}
	public void setDestinationPlace(String destinationPlace) {
		this.destinationPlace = destinationPlace;
	}

	public double getBasicFare() {
		return basicFare;
	}
	public void setBasicFare(double basicFare) {
		this.basicFare = basicFare;
	}

	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public double getPackageCost() {
		return packageCost;
	}
	public void setPackageCost(double packageCost) {
		this.packageCost = packageCost;
	}
	
	//Methods
	public void calculatePackageCost() {
		int disc =0;
		
		if(noOfDays<=5) {
			disc = 0;
		}
		else if(noOfDays>5 && noOfDays<=8) {
			disc = 3;
		}
		else if(noOfDays>8 && noOfDays<=10) {
			disc = 5;
		}
		else if(noOfDays>10) {
			disc = 7;
		}
		double finalBasicFare = this.getBasicFare()-(this.getBasicFare()*(0.01)*disc);
		double gst = finalBasicFare*0.12;
		this.setPackageCost(finalBasicFare + gst);
	}

}
