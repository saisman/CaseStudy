package CaseStudy.CaseStudy;

import java.sql.SQLException;
import java.util.List;

import model.HolidayPackage;
import utility.InvalidIdException;
import utility.TravelAgency;

public class App 
{
    public static void main( String[] args )
    {
    	TravelAgency ta = new TravelAgency();
    	List<HolidayPackage> costlist;
    	List<HolidayPackage> mincostlist;
    	try {
			costlist =  ta.generatePackageCost();
			for(HolidayPackage hp : costlist) {
	    		System.out.println("id: "+hp.getPackageId()+"; source place: "+hp.getSourcePlace()+"; destination place: "+hp.getDestinationPlace()+"; no of days: "+ hp.getNoOfDays()+"; total cost: "+hp.getPackageCost());
	    	}
			mincostlist = ta.findPackageWithMinumumNumberOFDays();
			for(HolidayPackage hp : mincostlist) {
				System.out.println("The package with least cost is: ");
				System.out.println("id: "+hp.getPackageId()+"; source place: "+hp.getSourcePlace()+"; destination place: "+hp.getDestinationPlace()+"; no of days: "+ hp.getNoOfDays()+"; total cost: "+hp.getPackageCost());
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
}
