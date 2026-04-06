# Reflection
At a high level, this project is an implementation of a custom `CString` class that is meant to emulate how a string works under the hood.

## Challenges, Assumtions, Design Decisions
I found this projet the most difficult out of all of them, probably because of all the methods I had to write and the errors I had to fix. I have more specific comments in the code, but here is my process and explanation of a few difficult methods.

### Some methods I found particularly challenging:
___
I spent a long time thinking of how I could write the `maxMirror` method efficiently. My first idea was to iterate through the array based on the size of mirror section, starting from the length of the array and going down to one. Nested inside that loop would be annother loop that loops through the indices, getting the sequences of characters starting at different indices of the current size. Then, there would be another nested loop inside that one to search the array for a mirror of that sequence. 
If I'm not mistaken, this is `O(n^3)` time complexity, which would be inefficient.
___
I then came up with my final solution. There would be two index variables, `right` and `left`, which started at opposite ends of the passed-in array. They would cycle through the array in the following manner: 

* `left` would increment until it reached the index before `right`.
* When `left` reached the index before `right`, `right` would decrement once, and `left` would be set to index `0` again. 
* This would be done until `right` ends up at index `0`. 

However, each iteration, there would be a check if the element at `left` and `right` were the same. If so, a mirror section was found, and `left` was incremented and `right` was decremented. A variable to keep track of the current mirror section length, `current` was also incremented. As long as the elements at `left` and `right` were equal, `left`, `right`, and `current` were incremented/decremented in the same way. 

When the elements at `left` and `right` stopped being equal, that means the mirror section stopped, so `current` was checked against a `max` variable, which kept track of the largest mirror secion size. The method is still `O(n^3)` time complexity, because it has 3 nested loops, but I still think it is more efficient than my previous attemps.
___
Another method I had dificulty on was the `memeifyArray` method. For this one I also spent some time thinking through how I would write this method, before settling on a log-type system. 

An `ArrayList` was created in the method, which would be converted back into an `int[]` to be returned at the end of the method. Elements from the passed in `int[]` were added to the `ArrayList` one by one, but sevens were not added, and whenever a six was added, a seven was added directly after it. These adding/removing of sevens were added to a log. Every time there was a pair of opposite log entries, they were both removed from the log because they canceled out. At the end of the method, if there was still entries in the log left over, sevens would be removed/re-added so no sevens would be created or destroyed by the method. 

This method did meet all the requirements specified in `README.md`, but in the testing, the array my method returned had two elements swapped compared to the expected array, so it didn't pass the test. So I decided to write a less efficient method with nested loops that searched through the array for a seven every time it encountered a six. I did have a little problem with this method, which was that when searching for 7s, it would take the 7s from previously-made 67 pairs. I managed to get around it by skipping an index every time a 6 was encountered, to skip the 7 directly after it.
___
The `decrypt` method was a little tricky to figure out, but I eventually found the right formula for the offset.
___
This was a fun project!