package de.stga.weldtesting.alternative;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit.MockBean;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(WeldJunit5Extension.class)
public class ManualWeldcontainer2Test {

    // Here the fish mock won`t overwrite the Fish class.

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(WeldInitiator.createWeld().enableDiscovery()).addBeans(createFishBean())
            .activate(SessionScoped.class).build();

    @Inject
    private FishTank classUnderTest;

    static Bean<?> createFishBean() {
        return MockBean.builder().types(Fish.class).scope(SessionScoped.class).selectedAlternative(Fish.class)
                .creating(Mockito.when(Mockito.mock(Fish.class).numberOfLegs()).thenReturn(400).getMock()).build();
    }

    @Test
    public void testNumberOfLegs1() {
        assertThat(this.classUnderTest.numberOfFishLegs(), CoreMatchers.is(400));
    }

}
