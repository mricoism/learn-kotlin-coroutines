# Coroutines with Firebase Firestore

Firestore documentation:
https://firebase.google.com/docs/firestore/quickstart#swift

Create project in firebase first 
https://console.firebase.google.com/u/0/

If you use firestore in real project it can often happen that you get in the so called callback hell.
That happen if you have several network operation that all depend on each other and always use callbacks.
And that is basically how fast are worked in the past. 


Let's say you want to construct a chat between 2 users