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

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class SequentalRepairer implements Runnable {
    protected Floor floor;
    MySemaphore sem;

    public SequentalRepairer(Floor floor, MySemaphore data) {
        this.floor = floor;
        this.sem = data;
    }

    @Override
    public void run() {
        int spacesAmount = floor.getSpacesAmount();
        Space[] spaces = floor.getSpaceArray();
        synchronized (sem) {
            for (int i = 0; i < spacesAmount; i++) {
                if (sem.isRepaired == false) {
                    try {
                        sem.notify();
                        System.out.println("Repairing space number " + i + " with total area " + spaces[i].getArea() + " square meters");
                        sem.isRepaired = true;
                        sem.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("Что-то не так в потоке SequentalRepairer");
                    }
                }
            }
        }
    }
}
