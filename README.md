# CS 570 — Homework 3: Indexed Doubly Linked List (IDLList)

Implements an Indexed Doubly Linked List (IDLList) in Java: a doubly linked list with O(1) random access via an internal ArrayList index. Includes add/append, get/getHead/getLast, remove/removeLast/removeAt/remove(E), size, and toString, with full index maintenance, bounds checks, and exception-safe operations.

---

## Overview
This project extends a classic doubly linked list (DLL) with a separate array-based index (`ArrayList<Node<E>> indices`) that stores direct references to each node. This design preserves the dynamic sizing of linked lists while enabling fast `get(int)` lookups in O(1). All mutating operations maintain both the DLL links and the index so they remain consistent.

---

## File Layout
```
DataStructures_IndexedDoublyLinkedList/
  IDLList.java
  IDLListTest.java
  README.md
```

---

## Compile and Run
From the project root (adjust paths as needed):
```bash
# Compile
javac IDLList.java IDLListTest.java

# Run tests
java IDLListTest
```

If you use a different layout, ensure the package declaration at the top of each file matches the folder structure.

---

## Public API (IDLList<E>)
```java
// Constructors
public IDLList(); // empty list

// Insertions
public boolean add(int index, E elem); // insert at position, 0..size
public boolean add(E elem);            // insert at head
public boolean append(E elem);         // insert at tail

// Queries
public E get(int index);  // O(1) via indices
public E getHead();
public E getLast();
public int size();

// Removals
public E remove();              // remove head
public E removeLast();          // remove tail
public E removeAt(int index);   // remove at position
public boolean remove(E elem);  // remove first occurrence

// Representation
public String toString();
```

### Representation Invariants
- `head` is the first node (or `null` if empty); `tail` is the last node (or `null` if empty).
- `size` equals `indices.size()` at all times.
- `indices.get(i)` references the node at logical position `i` in the list.
- For a node `n`: `n.prev.next == n` and `n.next.prev == n` whenever those neighbors exist.

### Inner Class
```java
private class Node<E> {
  E data;
  Node<E> prev;
  Node<E> next;

  Node(E elem) { ... }
  Node(E elem, Node<E> prev, Node<E> next) { ... }
}
```

---

## Complexity
- `get(int i)`: O(1) via `indices`.
- `add(E)`, `append(E)`: O(1) pointer work + O(n) index shift in worst case for `add(0)` (due to `indices.add(0, ...)`), O(1) amortized for append to `indices`.
- `add(int i, E)`: O(1) pointer work + O(n) for `indices.add(i, ...)`.
- `remove()`, `removeLast()`: O(1) pointer work + O(n) index shift if removing at head.
- `removeAt(int i)`: O(1) pointer work + O(n) for `indices.remove(i)`.
- `remove(E)`: O(n) search + removal costs as above.

---

## Index Maintenance Rules
Every mutating operation must update both the pointer links and the `indices` list:
- **add(E elem)**: insert node before current `head`; `indices.add(0, node)`.
- **append(E elem)**: link after current `tail`; `indices.add(size, node)`.
- **add(int index, E elem)**: splice between `indices.get(index-1)` and `indices.get(index)`; `indices.add(index, node)`.
- **remove()**: unlink `head`; `indices.remove(0)`.
- **removeLast()**: unlink `tail`; `indices.remove(size-1)`.
- **removeAt(int index)**: unlink `indices.get(index)`; `indices.remove(index)`.
- **remove(E elem)**: find first match by scanning `indices`; delegate to `removeAt(i)`.

Maintain invariants on empty-to-nonempty transitions (and vice versa): when the list becomes empty, set `head = tail = null` and ensure `indices.size() == 0` and `size == 0`.

---

## Error Handling Expectations
The following conditions should be validated by the implementation and result in clear exceptions:
- Index bounds for `get`, `add(int, E)`, and `removeAt`.
- Operations on an empty list for `remove` and `removeLast`.
- `null` element insertions should be rejected (if disallowed by your design).
- Inconsistent internal state should never occur—use invariants defensively.

---

## Testing Checklist (IDLListTest.java)
Recommended minimal coverage:
1. Construct empty list; verify `size() == 0`, `getHead()`/`getLast()` throw.
2. `add(E)` twice and `append(E)` once; check order and `size()`.
3. `add(int, E)` at positions 0, middle, and `size()`; verify structure and index.
4. `get(int)` at multiple positions; check O(1) usage of `indices` (conceptually).
5. `remove()`, `removeLast()`, `removeAt(int)`; verify pointer/link updates and `indices` alignment.
6. `remove(E)` where present and not present; confirm boolean result and stability.
7. Bounds checks: negative index, `index == size` for `get/removeAt`, and empty removals.
8. `toString()` format sanity (comma-separated list of `data.toString()`).
