package com.myorg;

import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class CdkWorkshopStack extends Stack {
    public CdkWorkshopStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkWorkshopStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

        final Function helloLambda = Function.Builder.create(this, "HelloHandler")
                .runtime(Runtime.NODEJS_14_X)
                .code(Code.fromAsset("lambda"))
                .handler("hello.handler")
                .build();

        final HitCounterProps hitCounterProps = HitCounterProps.builder()
                .downstream(helloLambda)
                .build();
        final HitCounter hitCounter = new HitCounter(this, "HelloHitCounter", hitCounterProps);

        LambdaRestApi.Builder.create(this, "Endpoint")
                .handler(hitCounter.getHandler())
                .build();
    }
}
