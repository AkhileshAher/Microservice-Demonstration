package in.akhilesh.ecomorderservice.service;

import in.akhilesh.ecomorderservice.client.InventoryClient;
import in.akhilesh.ecomorderservice.dto.Inventory;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class InventoryService {

    private final InventoryClient inventoryClient;

    public InventoryService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

//    @Retryable(
//            value = Exception.class,
//            maxRetries = 3,
//            delay = 2000
//    )
//    @RateLimiter(
//            name = "inventoryService",
//            fallbackMethod = "fallbackMethod"
//    )

//    @CircuitBreaker(
//            name = "inventoryServiceCircuitBreaker",
//            fallbackMethod = "circuitBreakerFallbackMethod"
//    )
//    @TimeLimiter(
//            name = "inventoryServiceTimeLimiter",
//            fallbackMethod = "timeLimiterFallbackMethod"
//    )
    @Bulkhead(
            name = "inventoryThreadPool",
            type = Bulkhead.Type.THREADPOOL,
            fallbackMethod = "bulkHeadFallbackMethod"
    )
    public CompletableFuture<Inventory> getInventory(Long productId) {
//        System.out.println("Calling Inventory Service for product id " + productId);
        System.out.println("Calling Inventory Service for product id " + productId
                + "| THREAD: " + Thread.currentThread().getName());
        return CompletableFuture.supplyAsync(() -> inventoryClient.getInventory(productId));
//        return inventoryClient.getInventory(productId);
    }

    public CompletableFuture<Inventory> bulkHeadFallbackMethod(Long productId,Throwable throwable) {
//        System.out.println("Fallback Inventory Service for product id " + productId);
        System.out.println(
                "Bulkhead rejected request for product id " + productId
                        + "| Reason: " + throwable.getClass().getSimpleName());
        Inventory inventory = new Inventory(productId,0);
        return CompletableFuture.completedFuture(inventory);
//        return new Inventory(productId,0);
    }


//    @Bulkhead(
//            name = "inventoryServiceBulkhead",
//            fallbackMethod = "bulkHeadFallbackMethod"
//    )
//    public Inventory getInventory(Long productId) {
//        System.out.println("Calling Inventory Service for product id " + productId
//        + "| THREAD: " + Thread.currentThread().getName());
//        return inventoryClient.getInventory(productId);
//    }
//
//
//    public Inventory bulkHeadFallbackMethod(Long productId,Throwable throwable) {
//        System.out.println(
//                "Bulkhead rejected request for product id " + productId
//                        + "| Reason: " + throwable.getClass().getSimpleName());
//        return new Inventory(productId,0);
//    }


}
