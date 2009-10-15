import x10.util.Random;
import x10.io.Console;

public class MontyPi2 {
    public static  def main(s: Rail[String]) {
    	val N = (s.length==0)?500:int.parseInt(s(0));
    	val result = Array.make[Double](Dist.makeUnique(), (Point)=>0.0D);
    	
    	for(p:Point(1) in result.dist) at(result.dist(p)){
    		val r = new Random();
    		var a:double=0.0D;
    		for(j in 1..N){
    			val x = r.nextDouble(),y=r.nextDouble();
    			if(x*x +y*y <= 1.0) a++;
    		}
    		result(p)=a;
    	}
	val pi = 4*result.reduce(Double.+,0)/(N*Place.MAX_PLACES);
	Console.OUT.println("The value of pi is " + pi);
    }
}

