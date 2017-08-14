import buildings.Dwelling;

public class Main {

    public static void main(String[] args) {
    	int[] flatsAmount = {1, 2, 3, 4, 5, 6, 7};
        Dwelling newBuilding = new Dwelling(7, flatsAmount);
        
        System.out.println(newBuilding);        
    }
}
