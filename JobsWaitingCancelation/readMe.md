# JOBS, WAITING, CANCELATION


This is documentation creating jobs, wait for jobs done, and cancel jobs
-
`val job = GlobalScope.launch(Dispatchers.Default) {}`
This launch function actually will return a JOB. and you can save job inside variable.
by defult job will executed. and no need to call. you can wait job done by using .join() and cancel using .cancel().
-
`.join()`
Join function is suspend function, so you need call it inside coroutine scope.
.join() function is used for suspend coroutine until job is complete execute.
-
`.cancel()` 
is used for cancel job that running. its can accept CancelException.
How ever canceling coroutine is not always as easy as it is here.
Because cancellation is actually cooperative, witch means our coroutine need to be setup to be correctly canceled. 
So there needs to be enough time tell our coroutine that it has been canceled.
-
`withTimeout(2000L) {}`
Function and block code will do is..
Will do execute this code but if it need longer than three seconds it will cancel it automatically.
Without needing us to cancel it manually