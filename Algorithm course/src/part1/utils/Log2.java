/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package part1.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jota
 */
public class Log2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Double> number = new ArrayList<>();
        number.add(0.001);
        number.add(0.004);
        number.add(0.019);
        number.add(0.099);
        number.add(0.531);
        number.add(2.865);
        number.add(15.321);
        number.add(81.342);
        number.add(436.284);
        number.add(2327.456);
        calculate(number);
    }

    public static void calculate(List<Double> number) {
        double average = 0.0;
        for (int i = number.size() - 1; i > 0; i--) {
            //System.out.println(number.get(i) +"/"+number.get(i-1) );
            Double value = Math.round(Math.log(number.get(i) / number.get(i - 1)) / Math.log(2) * 100.0) / 100.0;
            average += value;
            System.out.println("value = " + value);
        }
        average /= number.size() - 1;
        System.out.println("average = " + average);
    }
}
