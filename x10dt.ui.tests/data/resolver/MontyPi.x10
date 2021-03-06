/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2015.
 */

import x10.util.Random;
import x10.io.Console;

/**
 * Calculation of an approximation to pi by using a Monte Carlo simulation
 * (throwing darts into the unit square and determining the fraction that land
 * in the unit circle).
 * Converted to 2.1 9/1/2010.
 */
public class MontyPi {
    public static def main(args:Rail[String]) {
        if (args.size != 1) {
            Console.OUT.println("Usage: MontyPi <number of points>");
            return;
        }
        val N = int.parse(args(0));
        val initializer = (i:Point) => {
            val r = new Random();
            var result:double=0.0D;
            for(1..N) {
                val x = r.nextDouble(), y=r.nextDouble();
                if (x*x +y*y <= 1.0) result++;
            }
            result
        };
        val result = DistArray.make[Double](Dist.makeUnique(), initializer);
        val pi = 4*result.reduce(Double.+,0)/(N*Place.MAX_PLACES);
        Console.OUT.println("The value of pi is " + pi);
    }
}
