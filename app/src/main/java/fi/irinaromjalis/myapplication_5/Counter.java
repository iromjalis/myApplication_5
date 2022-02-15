package fi.irinaromjalis.myapplication_5;

import android.view.View;

public class Counter {

    int min, max, start, step;
    int value = start;

    public Counter(int min, int max, int start, int step){
        this.min = min;
        this.max = max;
        this.start = start;
        this.step = step;
    }
    public int increment (int value){
        int newValue = value + step;
        if (newValue >= max) {

            return max;
        }
        value = newValue;
        return value;
    }

    public int decrement (int value){
        int newValue = value - step;
        if (newValue <= min) {
            return min;
        }

        value = newValue;
        return value;
    }

    public int reset (){
        value = start;
        return value;
    }
}