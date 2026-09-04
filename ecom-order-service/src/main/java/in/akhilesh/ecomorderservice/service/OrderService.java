package in.akhilesh.ecomorderservice.service;

import in.akhilesh.ecomorderservice.client.InventoryClient;
import in.akhilesh.ecomorderservice.dto.Inventory;
import in.akhilesh.ecomorderservice.exceptions.MyCustomRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    private final RestTemplate restTemplate;
    private final RestClient restClient;
    private final InventoryClient  inventoryClient;
    private final InventoryService inventoryService;
    
    public OrderService(RestTemplate restTemplate, RestClient restClient, InventoryClient inventoryClient, InventoryService inventoryService) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.inventoryClient = inventoryClient;
        this.inventoryService = inventoryService;
    }
    
    public String placeOrder(Long productId) {
        // RestTemplate
//        String response = restTemplate.getForObject(
//                "http://localhost:8081/inventory/" + productId,
//                String.class
//        );


        // RestClient
//        ResponseEntity<Inventory> entity= restClient.get()
//                .uri("http://localhost:8081/inventory/{productId}", productId)
//                .retrieve()
//                .onStatus(HttpStatusCode::is4xxClientError,(req, res) -> {
//                    throw new MyCustomRuntimeException(res.getStatusCode(),res.getHeaders());
//                })
//                .toEntity(Inventory.class);
////                .body(String.class);

//        Inventory inventory = inventoryClient.getInventory(productId);
        Inventory inventory = inventoryService.getInventory(productId).join();
        updateInventory(inventory);
        return inventory.getQuantity() > 0 ?
                "Order Placed Successfully" :
                "Order Not Placed, Product Out of Stock";

//        System.out.println(entity.getStatusCode());
//        updateInventory(entity.getBody());

//        return entity != null && entity.getBody().getQuantity() > 0 ?
//                "Order Placed Successfully" :
//                "Order Not Placed, Product Out of Stock";

//        return "IN STOCK".equals(response.getBody()) ?
//                "Order Placed Successfully" :
//                "Order Not Placed, Product Out of Stock";
    }

    private void updateInventory(Inventory inventory) {
        inventory.setQuantity(inventory.getQuantity() - 1);
        inventoryClient.updateInventory(inventory);

//        restClient.post()
//                .uri("http://localhost:8081/inventory")
//                .body(inventory)
//                .retrieve()
//                .toBodilessEntity();

    }
}
