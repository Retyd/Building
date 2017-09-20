/**
 * В пакете buildings.threads создайте два новых класса SequentalRepairer и 
 * SequentalCleaner, реализующих интерфейс Runnable, с инструкциями для нитей.
 * Нити должны обеспечивать поочередность операций ремонта-уборки помещений 
 * (т.е. в консоль сообщения выводятся в порядке Repairing-Cleaning-Repairing-
 * Cleaning-…) независимо от приоритетов потоков. Уборка помещения не может 
 * следовать до ремонта помещения.
 * Для этого потребуется описать вспомогательный класс семафора, объект которого 
 * будет использоваться при взаимодействии нитей.
 * В остальном новые реализации нити должны повторять функциональность нитей 
 * из задания 1.
 */
package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class SequentalRepairer implements Runnable {
	protected Floor floor;
	public Test data;
	
	public SequentalRepairer(Floor floor, Test data) {
		this.floor = floor;
		this.data = data;
	}
	
	@Override
	public void run() {
		 int spacesAmount = floor.getSpacesAmount();
		 Space[] spaces = floor.getSpaceArray();
		 synchronized(floor) {
			 for(int i = 0; i < spacesAmount; i++) {	
				if(data.value == 0) {
					try {
						floor.notify();
						System.out.println("Repairing space number " + i + " with total area " + spaces[i].getArea() + " square meters");
						data.value = 1;
						floor.wait();
					} catch (InterruptedException ex) {
						System.out.println("Что-то не так в потоке SequentalRepairer");
					}
				}
			}
		 }
	}
}
