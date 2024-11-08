# LivecycleScope & viewModelScope


So far you learn that GlobalScope is used to start coroutine that lives as long as application does.
However most of the times it will be a bad practice to use GlobalScope. 
Because we rarely need our coroutine to lives as long as the application.
And for android there are two very useful predefined scopes witch is Livecycle Scope and viewModelScope. 
To add this we need to include some of lifeCycle dependencies :
``
def arch_version = '2.2.0-alpha01'
implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"
``

or in my latest version
``
implementation(libs.androidx.lifecycle.viewmodel.ktx)
implementation(libs.androidx.lifecycle.runtime.ktx)
``


if we using 'GlobalScope.launch' it will keep live. Even tho we has move to 'SecondActivity'.
It won't be destroy if our activity destroy. Instead it will be destroyed when our whole application is destroyed.
And its very big mistake, that can easily create memory leaks.
Because if the coroutine started in main activity, uses resources of that main activity, witch is now destroyed.
The resources won't garbage collected, even though the activity is destroyed. Because the coroutine still uses those resources.


And to solve this problem, we should swap out Global scope with lifecycleScope
What this will do ?, it will stick this coroutine to the lifecycle of our activity.
So if our activity is destroyed, that means that all coroutines launch in this lifecycleScope will also destroyed.

