package Structs;

// import Exceptions.NotComparableException;

import Exceptions.NotComparableException;


public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T>{

    public ArrayOrderedList() {
        super();
    }

    @Override
    public void add(T element) throws NotComparableException{
        
        if (!(element instanceof Comparable))
            throw new NotComparableException("Element not comparable !");
  
        if(num == list.length )
            expandCapacity();

        Comparable<T> comp = (Comparable<T>) element;
 
        int i = 0;
        
        for (; i < num; i++) {
            if(comp.compareTo(list[i]) > -1){
                break;
            }
        }

        for (int j = num; j > i; j--){
            list[j] = list[j-1];
        }

        num++;
        list[i] = element;
    }
}
