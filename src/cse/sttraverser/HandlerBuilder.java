package cse.sttraverser;

import cse.node.DummyNode;
import cse.sttraverser.handler.*;

public class HandlerBuilder {
    public Handler handler = null;

    /***
     * build the handler chain which handles Standardizing of STNodes.
     * @return
     */
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
        DummyHandler dummyHandler = new DummyHandler();

        conditionalHandler.setNextHandler(gammaHandler);
        gammaHandler.setNextHandler(lambdaHandler);
        lambdaHandler.setNextHandler(identifierHandler);
        identifierHandler.setNextHandler(stringHandler);
        stringHandler.setNextHandler(integerHandler);
        integerHandler.setNextHandler(tauHandler);
        tauHandler.setNextHandler(yStarHandler);
        yStarHandler.setNextHandler(booleanHandler);
        booleanHandler.setNextHandler(dummyHandler);



        handler = conditionalHandler;

        return conditionalHandler;
    }
}
