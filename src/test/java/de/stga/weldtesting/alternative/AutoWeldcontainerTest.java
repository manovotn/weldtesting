package de.stga.weldtesting.alternative;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.OverrideBean;
import org.jboss.weld.junit5.auto.WeldJunit5AutoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(WeldJunit5AutoExtension.class)
@AddBeanClasses({Fish.class, FishTank.class}) // Here I would need discovery on,
                                              // because of deltaspike libraries
public class AutoWeldcontainerTest {

    @Produces
    @OverrideBean
    @Mock
    private Fish fishMock;

    @Inject
    private FishTank classUnderTest;

    @Test
    public void testNumberOfLegs1() {
        Mockito.when(this.fishMock.numberOfLegs()).thenReturn(400);
        assertThat(this.classUnderTest.numberOfFishLegs(), CoreMatchers.is(400));
    }

    @Test
    public void testNumberOfLegs2() {
        Mockito.when(this.fishMock.numberOfLegs()).thenReturn(500);
        assertThat(this.classUnderTest.numberOfFishLegs(), CoreMatchers.is(500));
    }
}
