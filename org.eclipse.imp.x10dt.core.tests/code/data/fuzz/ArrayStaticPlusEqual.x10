/*
 *  This file is part of the X10 project (http://x10-lang.org).
 *
 *  This file is licensed to You under the Eclipse Public License (EPL);
 *  You may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *      http://www.opensource.org/licenses/eclipse-1.0.php
 *
 *  (C) Copyright IBM Corporation 2006-2010.
 */

import harness.x10Test;

public class ArrayStaticPlusEqual extends x10Test {

    static val v: Rail[int] = Rail.make[int](2, (x:int)=>0);

    public def run() {
	at (v) {
            for ((i):Point(1) in 0..1) v(i) += 5;
            for ((i):Point(1) in 0..1) chk(v(i) == 5);
        }
        return true;
    }

    public static def main(var args: Rail[String]): void = {
        new ArrayStaticPlusEqual().execute();
    }
}
