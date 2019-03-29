package com.example.myapplicationst.NetCommunication.BodyClassForRequest;

/**
 * Created by Ыщвф on 10.11.2018.
 */

public class BodyCreate {
    public String title;
    public String type;
    public Body body;

    public BodyCreate(String title, String type, String value) {
        this.title = title;
        this.type = type;
        body = new Body(value);
    }

    private class Body {

        Und[] und;

        public Body(String value) {
            und = new Und[1];
            und[0] = new Und(value);
        }

        public class Und {
            String value;

            public Und(String value) {
                this.value = value;
            }
        }
    }
}
