package in.keepgrowing.jwtspringbootangularscaffolding.dummydata;

import dev.codesoapbox.dummy4j.Dummy4j;

public class CustomDummy extends Dummy4j {

    private final FlavourDummy flavourDummy;

    public CustomDummy() {
        flavourDummy = new FlavourDummy(this);
    }

    public FlavourDummy flavour() {
        return flavourDummy;
    }
}
