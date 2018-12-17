package de.stga.weldtesting.alternative;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.hamcrest.CoreMatchers;
import org.jboss.weld.junit.MockBean;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(WeldJunit5Extension.class)
public class ManualWeldcontainer {

    // Here the fish mock won`t overwrite the Fish class.
    @Mock
    private Fish fishMock;

    @WeldSetup
    public WeldInitiator weldInitiator = WeldInitiator.from(WeldInitiator.createWeld().enableDiscovery())
            .addBeans(MockBean.builder().types(Fish.class).selectedAlternative().beanClass(Fish.class).create(c -> this.fishMock).build())
            .activate(SessionScoped.class).build();

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
