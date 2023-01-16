# Compare And Swap

By using compareAndSwap, the program doesn't have to lock the critical section, it just attempts to update the critical section.
If previous update fails, it retries to update until the resource has been successfully updated.

![optimistic-pessimistic-lock.png](src%2Fmain%2Fresources%2Fimages%2Foptimistic-pessimistic-lock.png)

This can be used to implement optimisticLocking in java, where the resource is not actually locked, it is tried to be utilised by multiple threads until they are able to successfully utilise it (that is compareAndSwap method is successful).

Ref: [Implementing custom compareAndSwap](https://accessun.github.io/2017/03/12/Optimistic-locking-and-CAS-algorithm/#:~:text=Optimistic%20locking%20in%20Java%20is,to%20update%20the%20shared%20resource)

## Implementation

There are two implementations covered in this example: 

1. [OptimisticLockCounter](./src/main/java/com/lld/app/util/OptimisticLockCounter.java)
This example makes use of AtomicLong variable's build in method compareAndSwap to implement optimistic lock.
2. [SimulatedCAS](./src/main/java/com/lld/app2/util/SimulatedCAS.java)
This example uses primitive long type and defines two synchronized method compareAndSwap and compareAndSet to implement the Optimistic lock. 