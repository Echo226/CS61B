/* Main.java */

/**
 *  Code to experimentally resolve the following questions about Java.
 */

public class Main {
    public static void main(String[] args) {
        
    	// Part I
        X x = new X();
        Y y = new Y();

        X[] xa = new X[1];
        Y[] ya = new Y[1];


        // The following code can compile, but will invoke run-time error "java.lang.ClassCastException", Lab5.X cannot be cast to Lab5.Y
        y = (Y)x;

        // (a) At compile-time, can we assign xa to ya, and vice versa?
        //     When is a cast required?
        xa = ya;         // can compile;
        ya = (Y[])xa;    // can not compile without casting;

        // (b) At run-time, if ya references an array of Y's, can we assign it to xa?
        //     Can we then assign it back from xa to ya?
        xa[0] = new X();
        ya[0] = new Y();

        xa = ya;         // can compile;
        ya = (Y[])xa;    // can not run even with casting, even xa now is referencing an array of Y. Why?

        // (c) If xa references an array of X's (that are not Y's), can we assign it to ya?
        //     Can we then assign it back from ya to xa?
        //     Does it make a difference if the array of type X[] references objects that are all of class Y?  Why? - No.
        //     Do you think this is the case?
        ya = xa;        // can not compile;
        xa = ya;        // can compile and run;




    }
}
