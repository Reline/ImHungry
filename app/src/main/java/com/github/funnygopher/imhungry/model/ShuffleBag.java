package com.github.funnygopher.imhungry.model;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class ShuffleBag<T extends Comparable<T>> {

    private Stack<T> itemStack;

    public ShuffleBag(List<T> items) {
        this.itemStack = shuffle(items);
    }

    private Stack<T> shuffle(List<T> items) {
        Stack<T> itemStack = new Stack<T>();
        for(int i = 0; i < items.size(); i++) {
            itemStack.push(items.get(i));
        }

        Random rnd = new Random(System.currentTimeMillis());
        for(int i = items.size() - 1; i >0; i--) {
            int index = rnd.nextInt(i + 1);
            T item = items.get(index);
            items.set(index, items.get(i));
            items.set(i, item);
        }

        return itemStack;
    }

    public T grabItem() {
        return itemStack.pop();
    }

    public T grabItemButNotThisItem(T itemNotToGrab) {
        T item = itemStack.pop();

        // If there's only 1 item in the stack...
        if(itemStack.size() == 0) {
            return item;
        }

        while(item.compareTo(itemNotToGrab) == 0) {
            if(itemStack.size() == 0) {
                return item;
            }
            item = itemStack.pop();
        }

        return item;
    }
}
