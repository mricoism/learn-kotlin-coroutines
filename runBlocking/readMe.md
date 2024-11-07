# runBlock

You know that if we call delay in coroutines, it wont actually block the thread it is running in.
However there is a function that will start a coroutine in the main thread and also block it, witch is called run blocking.


**What the diffrent between runBlocking and GlobalScope.launch(Dispatcher.Main) is ?**
runBlocking will actually block the main threads.
But GlobalScope.launch(Dispatcher.Main) wont block the main threads.


**Question is, Why we need to block our main thread, if i have option that doesn't block it ?**
This can be useful if you don't necessarily need that coroutine behaviour but still want to call suspend functions in the main thread.

**IMPORTAT**
Main thread is run sync not async!
