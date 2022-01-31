package Structs;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.NotFindException;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> first;
    private int count = 0;
    private Node<T> last;

    public LinkedList() {
        this.first = null;
        this.count = 0;
        this.last = null;
    }

    public LinkedList(LinkedList<T> linkedList) {
        
        this();
        
        for (T t : linkedList) {

            final Node<T> l = last;
            final Node<T> newNode = new Node<>(t, null, l);
            last = newNode;
            if (l == null) {
                first = newNode;
            } else {
                l.setNext(newNode);
            }

            this.count++;
        }
    }
    
    
    public void add(T object) {

        final Node<T> l = last;
        final Node<T> newNode = new Node<>(object, null, l);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.setNext(newNode);
        }

        this.count++;
    }

    public int size() {
        return count;
    }

    public T get(int index) throws ElementNotFoundException {

        return getNode(index).getObject();
    }

    private Node<T> getNode(int index) throws ElementNotFoundException {

        if (index >= count || index < 0) {
            throw new ElementNotFoundException();
        }

        Node<T> node = first;

        int i = 1;
        while (i <= index) {

            node = node.getNext();
            i++;
        }

        return node;
    }

    public boolean contains(T object) throws ElementNotFoundException {

        if (count == 0) {
            return false;
        }

        for (int i = 0; i < count; i++) {

            if (get(i).equals(object)) {
                return true;
            }
        }
        return false;
    }

    private int findNode(T object) throws NotFindException, ElementNotFoundException {

        for (int i = 0; i < count; i++) {

            if (get(i).equals(object)) {
                return i;
            }
        }

        throw new NotFindException();
    }

    public void remove(T object) throws NotFindException, EmptyCollectionException, ElementNotFoundException {

        remove(findNode(object));
    }

    public void remove(int index) throws EmptyCollectionException, ElementNotFoundException {

        if (isEmpty()) {
            throw new EmptyCollectionException("List is empty !");
        } else if (index >= count) {
            throw new ElementNotFoundException();
        }

        Node<T> node = getNode(index);

        if (node.equals(first)) {

            if (count == 1) {

                first = null;
                last = null;
                
            } else {

                node.getNext().setBefore(null);
                first = node.getNext();
            }

        } else if (node.getNext() != null) {
            node.getBefore().setNext(node.getNext());
            node.getNext().setBefore(node.getBefore());
        } else {
            // Se for o ultimo
            last = node.getBefore();
            last.setNext(null);
        }

        this.count--;

    }

    public boolean isEmpty() {
        if (this.count == 0) {
            return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new myItr();
    }

    private class myItr implements Iterator {

        private Node<T> current;

        public myItr() {
            current = first;
        }

        @Override
        public boolean hasNext() {

            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new ArrayIndexOutOfBoundsException("There isn't next element !");
            }

            T element = current.getObject();

            current = current.getNext();

            return element;
        }

        @Override
        public void remove() {

            try {

                LinkedList.this.remove(0);

            } catch (EmptyCollectionException ex) {

                System.out.println(ex.getMessage());
            } catch (ElementNotFoundException ex) {
                Logger.getLogger(LinkedList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String toString() {
        return "LinkedList{"
                + "front=" + first.toString()
                + ", count=" + count
                + '}';
    }
}
