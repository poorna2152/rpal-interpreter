package standardize;

import standardize.standardizer.*;

public class StandardizerBuilder {

    /**
     * Build the standardizer chain
     * @return
     */
    public Standardizer buildStandardizer(){
        AndStandardizer andStandardizer = new AndStandardizer();
        AtStandardizer atStandardizer = new AtStandardizer();
        FunctionFormStandardizer functionFormStandardizer = new FunctionFormStandardizer();
        LetStandardizer letStandardizer = new LetStandardizer();
        MultiParameterStandardizer multiParameterStandardizer = new MultiParameterStandardizer();
        RecStandardizer recStandardizer = new RecStandardizer();
        WhereStandardizer whereStandardizer = new WhereStandardizer();
        WithinStandardizer withinStandardizer = new WithinStandardizer();

        andStandardizer.setNextStandardizer(atStandardizer);
        atStandardizer.setNextStandardizer(functionFormStandardizer);
        functionFormStandardizer.setNextStandardizer(letStandardizer);
        letStandardizer.setNextStandardizer(multiParameterStandardizer);
        multiParameterStandardizer.setNextStandardizer(recStandardizer);
        recStandardizer.setNextStandardizer(whereStandardizer);
        whereStandardizer.setNextStandardizer(withinStandardizer);

        return  andStandardizer;
    }
}
