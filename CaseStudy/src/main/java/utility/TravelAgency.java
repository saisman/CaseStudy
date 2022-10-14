package utility;

import java.sql.*;
import java.util.*;
import java.io.*;

import dao.DBHandler;
import model.HolidayPackage;

public class TravelAgency {
	public boolean validate(String id) {
		String regex = "[0-9]{3}[/]{1}[A-Z]{3}";
		return(id.matches(regex));
	}
	
	public List<HolidayPackage> generatePackageCost() throws InvalidIdException, SQLException{
		List<HolidayPackage> list = new ArrayList<HolidayPackage>();
		Scanner sc = null;
		int i = 1;
		try {
			Connection c = DBHandler.estConnection();
			PreparedStatement ps1 = c.prepareStatement("insert into HolidayPackage values(?,?,?,?,?)");
			PreparedStatement ps2 = c.prepareStatement("select * from HolidayPackage where package_id=?");
			
			File file = new File("C:\\Users\\saismand\\OneDrive - Capgemini\\Documents\\Abridge\\Java\\CaseStudy//Package.txt");
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String str = sc.nextLine();
				String strarr[] = str.trim().split(",");
				String id = strarr[0].trim();
				if(validate(id)) {
					String source = strarr[1].trim();
					String destination = strarr[2].trim();
					int days = Integer.parseInt(strarr[3].trim());
					double fare = Double.parseDouble(strarr[4].trim());
					
					HolidayPackage hp = new HolidayPackage(id, source, destination, days, fare);
					
					list.add(hp);
					
					ps1.setString(1, id);
					ps1.setString(2, source);
					ps1.setString(3, destination);
					ps1.setInt(4, days);
					ps1.setDouble(5, hp.getPackageCost());
					ps2.setString(1, id);
					
					ResultSet rs = ps2.executeQuery();
					if(!rs.next()) {
						ps1.executeUpdate();
					}
					i++;
				}
				else {
					throw new InvalidIdException();
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Problem in line number - "+i);
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public List<HolidayPackage> findPackageWithMinumumNumberOFDays(){
		List<HolidayPackage> list = new ArrayList<HolidayPackage>();
		try {
			Connection c = DBHandler.estConnection();
			PreparedStatement ps = c.prepareStatement("select * from HolidayPackage where no_of_days=(select MIN(no_of_days) from HolidayPackage)");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String id = rs.getString("package_id");
				String source = rs.getString("source_place");
				String destination = rs.getString("destination_place");
				int days = rs.getInt("no_of_days");
				double cost = rs.getDouble("package_cost");
				
				HolidayPackage hp = new HolidayPackage(id, source, destination, days);
				hp.setPackageCost(cost);
				
				list.add(hp);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return list;
	}
}

