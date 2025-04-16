package Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> historyMap = new HashMap<>();
    private Node head;
    private Node tail;

    private class Node {
        Task task;
        Node prev;
        Node next;

        public Node(Task task, Node prev, Node next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkLast(Task task) {
        Node oldtail = tail;
        Node newNode = new Node(task, tail, null);
        tail = newNode;
        if (oldtail == null) {
            head = newNode;
        } else {
            oldtail.next = newNode;
        }
    }

    @Override
    public void add(Task task) {
        remove(task.id);
        linkLast(task);
        historyMap.put(task.id, tail);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        Node actual = head;
        while (actual != null) {
            history.add(actual.task);
            actual = actual.next;
        }
        return history;
    }

    @Override
    public void remove(int id) {
        Node node = historyMap.get(id);
        if (node != null) {
            removeNode(node);
            historyMap.remove(id);
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }
}