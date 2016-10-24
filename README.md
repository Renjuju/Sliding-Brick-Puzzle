# Sliding-Brick-Puzzle

# Euclidean Distance Heuristic
The euclidean distance takes the shorted path between 
the two goal state values by using the pythagorean theorem 
to get the distance
It is admissible as it is an underestimation
```java
return (int) Math.sqrt(Math.pow(2, Math.abs(x2position-x1Position)) + Math.pow(2 ,Math.abs(y2position-y1Position)));
```

# Manhattan Distance Heuristic
Admissible heuristic that calculates the difference 
```java
return Math.abs(x2position-x1Position) + Math.abs(y2position-y1Position);
```

## Instructions
Compile with any environment that supports java. The main class holds main so we compile at that point e.g. 

```java
public class main {
  public static void main(String args[]) {
    ...
  }
}
```

```bash
javac Main.java && java Main
```

## Features
1. A* Search
2. Breadth First Search
3. Depth First Search
4. Random search
