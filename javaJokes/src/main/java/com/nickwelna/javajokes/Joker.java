package com.nickwelna.javajokes;

import java.util.Random;

public class Joker {

    private Random random;

    public Joker() {

        random = new Random();

    }

    public String getJoke() {

        String[] jokes = new String[]{"An Android app walks into a bar. Bartender asks, \"Can I " +
                                              "get you a drink?\" The app looks disappointed and " +
                                              "says, \"That wasn't my intent.\"",
                                      "A fragment walks into a bar and says, \"I'm lost. Can I " +
                                              "please speak to the FragmentManager?\"",
                                      "99 little bugs in the code\n" + "99 bugs in the code\n" +
                                              "patch one down, compile it around\n" +
                                              "117 bugs in the code",
                                      "A programmer puts two glasses on his bedside table before " +
                                              "going to sleep. A full one, in case he gets " +
                                              "thirsty, and an empty one, in case he doesn't.",
                                      "There are 10 kinds of people in this world: Those who " +
                                              "understand binary, those who don't, and those who " +
                                              "weren't expecting a base 3 joke."};

        return jokes[random.nextInt(5)];

    }

}
