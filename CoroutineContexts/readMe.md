# Coroutine Context

**Dispatchers.Main**
This will run in main thread and very useful for update UI or UI related. Because you can change UI from the main thread.

**Dispatchers.IO**
Just used for all kinds of data operation. Such as Networking to databases or reading and writing to files

**Dispatchers.Default**
You should choose this if you are planning on doing complex and long-running calculation that will block the main thread. Ex, you need to sort a list of a 10.000 elements. Then you should do that in the default dispatcher to not block your main thread and your UI

**Dispatchers.unconfined**
As the name says its not confined to specific thread. So if you start coroutines that causes a suspend function, it will stay in the thread that the suspend function resumed 

**newSingleThreadContext("MyThread")**
By this we can start our own new thread. Its start new thread and run coroutine in that new created thread