package org.example;

public class ListaOrdonata<E extends Comparable<E>> {
    private E[] atomi;
    private int size;

    public ListaOrdonata(int capacitate) {
        atomi = (E[]) new Comparable[capacitate];
        size = 0;
    }

    public void add(E atom) {
        int start = 0;
        int end = size;
        while (start < end) {
            int mid = (start + end) / 2;
            if (atom.compareTo(atomi[mid]) < 0) {
                end = mid;
            } else if (atom.compareTo(atomi[mid]) > 0) {
                start = mid + 1;
            } else {
                return;
            }
        }
        for (int i = size; i > start; i--) {
            atomi[i] = atomi[i - 1];
        }
        atomi[start] = atom;
        size++;
    }

    public E get(int i) {
        if (i >= 0 && i < size) {
            return atomi[i];
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }
    }

    public int indexOf(E s) {
        int start = 0 ;
        int end = size;
        while(start < end){
            int mid = (start + end ) / 2;
            if (s.compareTo(atomi[mid]) < 0) {
                end = mid;
            } else if (s.compareTo(atomi[mid]) > 0) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int size(){
        return size;
    }
}
