# Odd Even Printer

This project shows how we can implement odd even printer using ReentrantLock and ConditionVariable to leverage the inter thread communication.

`ReentrantLock` helps to guard a block of code so that only a single thread can execute that block at a given point of time. <br>
`ConditionVariable` helps us to do the inter thread communication between the threads.

<br>

We can compare the `ReentrantLock` with the `synchronized` block and `ConditionVariable` with the `wait()` and `notify()` method. 

