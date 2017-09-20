# Что здесь есть  
  
Здесь есть два пакета [Dwelling](src/buildings/dwelling) и [OfficeBuilding](src/buildings/office), которые содержат по три взаимосвязанных класса для создания помещения, этажа и здания определенного типа.  
Есть пакет [Hotel](src/buildings/dwelling/hotel), где лежат два класса - для отеля и для его этажа. Эти классы расширяют соответствующие классы [жилого здания](src/buildings/dwelling).  
Все эти классы имеют разнообразные get и set методы, в случае возникновения ошибок выбрасывающие соответствующие [исключения](src/exceptions).  
Кроме того, для этих классов реализованы методы toString(), equals() и hashcode().  
  
Классы [офисного этажа](src/buildings/office/OfficeFloor.java) и [офисного здания](src/buildings/office/OfficeBuilding.java) примечательны тем, что реализованы на основе односвязного и двусвязного списка соответственно. В обоих случаях списки циклические и с выделенной головой.  
  
Также здесь есть взаимосвязанные интерфейсы [Space](src/buildings/Space.java), [Floor](src/buildings/Floor.java) и [Building](src/buildings/Building.java). Они соответствуют общей функциональности классов помещений, этажей и зданий, а те, в свою очередь, реализуют эти интерфейсы.  
  
Класс [PlacementExchanger](src/buildings/PlacementExchanger.java) осуществляет проверку возможности обмена и непосредственно сам обмен помещениями/этажами зданий.  
  
Класс [Buildings](src/buildings/Buildings.java) содержит методы, которые записывают данные о здании в байтовый или символьный поток, и методы, которые читают их оттуда и на основе полученных данных создают объект здания.  
  
В [этом](src/buildings/threads) пакете лежат классы потоков. Два из них ([SequentalRepairer](src/buildings/threads/SequentalRepairer.java) и [SequentalCleaner](src/buildings/threads/SequentalCleaner.java)) синхронизированы (с помощью очень-очень простого вспомогательного [класса-семафора](src/buildings/threads/Test.java)).  
