package cse.sttraverser;

import cse.sttraverser.handler.*;

public class HandlerBuilder {
    public Handler handler = null;

    public Handler buildTraverserHandler(){
        ConditionalHandler conditionalHandler = new ConditionalHandler();
        GammaHandler gammaHandler = new GammaHandler();
        LambdaHandler lambdaHandler = new LambdaHandler();
        IdentifierHandler identifierHandler = new IdentifierHandler();
        StringHandler stringHandler = new StringHandler();
        IntegerHandler integerHandler = new IntegerHandler();
        TauHandler tauHandler = new TauHandler();
        YStarHandler yStarHandler = new YStarHandler();
        BooleanHandler booleanHandler = new BooleanHandler();

        conditionalHandler.setNextHandler(gammaHandler);
        gammaHandler.setNextHandler(lambdaHandler);
        lambdaHandler.setNextHandler(identifierHandler);
        identifierHandler.setNextHandler(stringHandler);
        stringHandler.setNextHandler(integerHandler);
        integerHandler.setNextHandler(tauHandler);
        tauHandler.setNextHandler(yStarHandler);
        yStarHandler.setNextHandler(booleanHandler);



        handler = conditionalHandler;

        return conditionalHandler;
    }
}
