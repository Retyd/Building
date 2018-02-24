# Что здесь есть  
  
Есть два пакета [Dwelling](src/buildings/dwelling) и [Office](src/buildings/office), которые содержат по три взаимосвязанных класса для создания помещения, этажа и здания определенного типа.  
Есть пакет [Hotel](src/buildings/dwelling/hotel), где лежат два класса - для отеля и для его этажа. Эти классы расширяют соответствующие классы [жилого здания](src/buildings/dwelling).  
Все эти классы имеют разнообразные get и set методы, в случае возникновения ошибок выбрасывающие соответствующие [исключения](src/exceptions).  
Кроме того, для этих классов реализованы методы toString(), equals(), hashcode() и clone().  
  
Классы [офисного этажа](src/buildings/office/OfficeFloor.java) и [офисного здания](src/buildings/office/OfficeBuilding.java) примечательны тем, что реализованы на основе односвязного и двусвязного списка соответственно. В обоих случаях списки циклические и с выделенной головой.  
  
Также здесь есть взаимосвязанные интерфейсы [Space](src/buildings/interfaces/Space.java), [Floor](src/buildings/interfaces/Floor.java) и [Building](src/buildings/interfaces/Building.java). Они соответствуют общей функциональности классов помещений, этажей и зданий, а те, в свою очередь, реализуют эти интерфейсы.  
  
Класс [PlacementExchanger](src/buildings/PlacementExchanger.java) осуществляет проверку возможности обмена и непосредственно сам обмен помещениями/этажами зданий.  
  
Класс [Buildings](src/buildings/Buildings.java) содержит методы, которые записывают данные о здании в байтовый или символьный поток, и методы, которые читают их оттуда и на основе полученных данных создают объект здания.  
  
В [этом](src/buildings/threads) пакете лежат классы потоков. Два из них ([SequentalRepairer](src/buildings/threads/SequentalRepairer.java) и [SequentalCleaner](src/buildings/threads/SequentalCleaner.java)) синхронизированы (с помощью очень-очень простого вспомогательного [класса-семафора](src/buildings/threads/MySemaphore.java)).  
[SynchronizedFloor](src/buildings/SynchronizedFloor.java) - класс-декоратор, реализующий с обеспечением синхронизации методы интерфейса Floor, а также переопределяющий ряд методов класса Object.   
  
Есть [однопоточный](src/buildings/net/server/sequental/BinaryServer.java) и [многопоточный](src/buildings/net/server/parallel/BinaryServer.java) серверы, производящие оценку здания на основе информации, передаваемой ему [клиентом](src/buildings/net/client/BinaryClient.java).  