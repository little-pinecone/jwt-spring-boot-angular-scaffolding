package in.keepgrowing.jwtspringbootangularscaffolding.dummydata;

import dev.codesoapbox.dummy4j.ExpressionResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlavourDummyTest {

    @Mock
    private CustomDummy customDummy;

    @Mock
    private ExpressionResolver expressionResolver;

    private FlavourDummy flavourDummy;

    @BeforeEach
    void setUp() {
        flavourDummy = new FlavourDummy(customDummy);
    }

    @Test
    void shouldGenerateMixedFlavours() {
        when(customDummy.expressionResolver())
                .thenReturn(expressionResolver);
        when(expressionResolver.resolve(FlavourDummy.FLAVOUR_MIXED_KEY))
                .thenReturn("test flavour");

        String actual = flavourDummy.mixed();

        assertEquals("test flavour", actual);
    }
}