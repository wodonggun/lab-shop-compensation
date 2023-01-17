package labshopcompensation.domain;

import labshopcompensation.InventoryApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name="Inventory_table")
@Data

public class Inventory  {

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long stock;

    @PostPersist
    public void onPostPersist(){
    }

    public static InventoryRepository repository(){
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(InventoryRepository.class);
        return inventoryRepository;
    }




    public static void decreaseStock(OrderPlaced orderPlaced){

        repository().findById(orderPlaced.getId()).ifPresent(inventory->{
            
            
            inventory.setStock(inventory.getStock()-orderPlaced.getQty());

            repository().save(inventory);


         });
 

        
    }
    public static void increaseStock(OrderCancelled orderCancelled){


        repository().findById(orderCancelled.getId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + orderCancelled.getQty());
            repository().save(inventory);


         });
  

        
    }


}
