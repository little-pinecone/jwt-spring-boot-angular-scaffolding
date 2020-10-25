package in.keepgrowing.jwtspringbootangularscaffolding.dummydata;

public class FlavourDummy {

    protected static final String FLAVOUR_MIXED_KEY = "#{flavour.mixed}";

    private final CustomDummy customDummy;

    public FlavourDummy(CustomDummy customDummy) {
        this.customDummy = customDummy;
    }

    public String mixed() {
        return customDummy.expressionResolver().resolve(FLAVOUR_MIXED_KEY);
    }
}
