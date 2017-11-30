package com.example;
import java.util.Random;

public class JokeTeller {
    //create an arry of jokes

    private static final  String jokes[]={
            "Autocorrect has become my worst enema.",
            "The guy who invented auto-correct for smart phones passed away today. \n  Restaurant in peace",
            "You know you’re texting too much when…\n" +
                    "\n" +
                    "…you try to text, but you’re on a landline."
    };
    public static String getJoke(){
        int size=jokes.length;
        Random r=new Random();
        int index=r.nextInt(size);
        return jokes[index];
    }

}
