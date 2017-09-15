/**
 * В пакете buildings.threads создайте два класса нитей 
 * (наследуют от класса Thread) Repairer и Cleaner, 
 * взаимодействующих с помощью промежуточного объекта типа Floor.
 * Вторая нить последовательно считывает значения 
 * площадей помещений этажа. 
 * Каждый раз, когда она читает значение помещения, 
 * она выводит в консоль сообщение вида 
 * «Cleaning room number <index> with total area <area> square meters». 
 * По достижении конца этажа, а также в случае прерывания 
 * нить заканчивает свое выполнение.
 */

package buildings.threads;

public class Cleaner extends Thread {

}
