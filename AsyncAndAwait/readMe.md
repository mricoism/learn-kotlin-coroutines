# ASYNC & AWAIT

if we have several suspend functions and execute them both in coroutine then they are sequential by default.
witch means the first function will executed first and when it finished the second one will be executed.
However if want to do two network calls for example then actually we don't want make them one after another. We rather want them to be executed at the same time.


