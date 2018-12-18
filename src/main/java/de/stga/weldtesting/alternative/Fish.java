package de.stga.weldtesting.alternative;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Fish implements Serializable {

    private static final long serialVersionUID = 1L;

    public int numberOfLegs() {
        return 1000;
    }

}
