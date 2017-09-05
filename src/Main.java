import buildings.Dwelling;
import officebuildings.OfficeFloor;

public class Main {

    public static void main(String[] args) {
    	/*int[] flatsAmount = {1, 2, 3, 4, 5, 6, 7};
        Dwelling newBuilding = new Dwelling(7, flatsAmount);
        
        System.out.println(newBuilding); 
        
        
        OfficeFloor one = new OfficeFloor();
        OfficeFloor.Node nOne = one.new Node();*/
        
        OfficeFloor testOfficeFloor = new OfficeFloor(5);
        /*System.out.println(testOfficeFloor.getFloorArea());
        System.out.println(testOfficeFloor.getRoomsOnFloorAmount());*/
        OfficeFloor testOfficeFloor2 = new OfficeFloor(testOfficeFloor.getOfficesOnFloorArray());
    }
}
