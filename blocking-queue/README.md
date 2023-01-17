# Blocking Queue

## This subproject depicts ways to implement `Producer-Consumer` problem using three methods:

1. [com.lld.app.ProducerConsumerWithObjectMonitor](./src/main/java/com/lld/app/ProducerConsumerWithObjectMonitor.java) <br>
   This is the first method where the [BlockingQueue](./src/main/java/com/lld/app/util/BlockingQueue.java) is implemented using normal object monitor leveraging synchronized block. <br>
   Run using below command: 
```shell
mvn clean compile exec:java -Dexec.mainClass=com.lld.app.ProducerConsumerWithObjectMonitor
```
<br>

2. [com.lld.app2.ProducerConsumerWithMutexLock](./src/main/java/com/lld/app2/ProducerConsumerWithMutexLock.java) <br>
   This is second implementation which uses the `java.concurrent` package to make use of lock object and constructs the [BlockingQueueWithMutex](./src/main/java/com/lld/app2/util/BlockingQueueWithMutex.java) <br>
   Run using below command:
```shell
mvn clean compile exec:java -Dexec.mainClass=com.lld.app2.ProducerConsumerWithMutexLock
```   
<br>

3. [com.lld.app3.ProducerConsumerWithConditionVariable.java](./src/main/java/com/lld/app3/ProducerConsumerWithConditionVariable.java) <br>
   This is the third implementation where we make use of ReentrantLock as well as Condition variable to perform inter thread communication. <br>
   [BlockingQueueWithConditionVariable](./src/main/java/com/lld/app3/util/BlockingQueueWithConditionVariable.java) <br>
   Run using below command:
```shell
mvn clean compile exec:java -Dexec.mainClass=com.lld.app3.ProducerConsumerWithConditionVariable
```

4. [com.lld.app3.ProducerConsumerWithSemaphore](./src/main/java/com/lld/app4/ProducerConsumerWithSemaphore.java) <br>
   This is the third implementation where we make use of Manually constructed [CountingSemaphore](./src/main/java/com/lld/app4/util/CountingSemaphore.java) to keep track of permits. <br> 
   Run using below command:
```shell
mvn clean compile exec:java -Dexec.mainClass=com.lld.app3.ProducerConsumerWithSemaphore
```

<br>

## To generate new template use the following command:

```shell
mvn archetype:generate -DgroupId=com.lld.app -DartifactId=lld-template -DarchetypeArtifactId=maven-archetype-quickstart  -DinteractiveMode=false
```
