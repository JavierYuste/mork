# Common mistakes to avoid

In this page a list of common mistakes to avoid and best practices are enumerated. While some of this are forced by the framework, note that they are meta heuristics development best practices nonetheless.

## Customized random generator

The random generators follow the seed specified in the application.yml configuration file. **As long as the seed is constant the algorithms should always generate the same results.**

Every time a `Random` object is required request it with the `RandomManager.getRandom()` method. Do NOT create your own Random instances manually, and do not use Java API methods that do not allow you to provide a random. Examples:

* **Do not use `Math.random()`**. Use RandomManager.getRandom() to get a generator and then call `nextDouble()` instead. `Math.random()` uses a custom random internally that cannot be controlled. By default, this method is blocked (config property `advanced.block.math-random`, and trying to invoke it throws an Exception.

* **Do not use `Collections.shuffle(Collection<E>)`.** Use `CollectionUtil.shuffle()` from mork package instead (which is actually faster!).


## Instances

Instances must be inmmutable after exiting the constructor method. **Under no circumstances should an instance change while solving**. Remember than you may always extend an `InstanceImporter` and customize every aspect of the `Instance` creation while or just after the Instance has been loaded.

## Solution

Solutions are always owned by a thread/executor, and only one. This means that you should not implement concurrency controls inside the solution class, specially in the data structures. They will negatively affect computation times without providing any benefits.

## Algorithms & Components

Algorithms and their components **must be stateless**. This applies to algorithms, constructive methods, local searches, neighborhoods, etc.
You should not store any mutable information inside this classes, always use the solution and only the solution class to store mutable data.
