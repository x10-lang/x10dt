import pac.*;
import pac.pak.*;
import pak.Foo;
import pak.Foo.*;

/**
 * The canonical "Hello, World" demo class expressed in X10
 */
public class Hello extends Hi implements Hii{

    /**
     * The main method for the Hello class
     */
    public static def main(Rail[String]) {
        Console.OUT.println("Hello, World!");
        val h = new Howdy(new D());
        val j = new Foo[Bar[int]]();
        type loo = Loo;
        val k <: Howdy3 = new Howdy2();
        var name: Array[Goo] = new Array[Goo](null , (p: Point) => null );
        var fname:(A,B) => C = (x1:A, x2:B) => new C();
        if (h instanceof Howdy2) { val name2 = h as Howdy2; }
        val l = new S(); 
    }
    
    public def boo(g: Coucou): Hola {
    	val j: Ciao = foo();
    	return null;
    }
    
    public def foo(): Ciao { return null;}
    
    public def m[T](x:T){T <: Booa} {};

}