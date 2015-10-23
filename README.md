# array-of-integers-flattener-java
Java implementation of a flattener of nested lists.

The structure of lists is traversed Depth-first search (DFS).

> E.g.: <i>[1, [2], [3, 4], 5] --> [1, 2, 3, 4, 5]</i>

The initial requirement was to develop an implementation based on "array of integers" but in Java a variable declared like `int[]` cannot contain elements that are arrays. Choosing `Object[]` would have been akward, especially because the arrays dimensions have to be declared upfront at that would have made the implementation overly complex. Also working with implementation of `List` to convert to and from arrays would have made the code pretty ugly and more difficoult to maintain.

The implementation chosen in the end doesn't take into account performance issues but focuses on readibility. The previous implementation (see history) was slightly more efficient but more verbose.

I'm aware that with Java 8 we could develop a more elegant implementation based on streams and lambdas but because those are topics I'm still not familiar with I preferred to avoid it.