package de.stga.weldtesting.alternative;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FishTank {

    @Inject
    private Fish fish;

    public int numberOfFishLegs() {
        return this.fish.numberOfLegs();
    }
}
