package in.keepgrowing.jwtspringbootangularscaffolding.dataprovider.dummyprovider;

import dev.codesoapbox.dummy4j.Dummy4j;

import java.util.ArrayList;
import java.util.List;

public class FlavourDefinitions {

    private final Dummy4j customDummy;

    public FlavourDefinitions(Dummy4j customDummy) {
        this.customDummy = customDummy;
    }

    public List<String> mixed(int amount) {
        List<String> flavours = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            flavours.add(uniqueMixedFlavour("group"));
        }
        return flavours;
    }

    public String uniqueMixedFlavour(String group) {
        return customDummy.unique().value(group,
                () -> customDummy.expressionResolver().resolve("#{flavour.double}"));
    }
}
