package com.myorg;

import io.github.cdklabs.dynamotableviewer.TableViewer;
import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class CdkWorkshopStack extends Stack {
    public final CfnOutput outputViewerUrl;
    public final CfnOutput outputEndpoint;
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

        final LambdaRestApi gateway = LambdaRestApi.Builder.create(this, "Endpoint")
                .handler(hitCounter.getHandler())
                .build();

        final TableViewer tv = TableViewer.Builder.create(this, "ViewerHitCount")
                .title("Hello Hits")
                .table(hitCounter.getTable())
                .sortBy("-hits")
                .build();

        outputViewerUrl = CfnOutput.Builder.create(this, "TableViewerUrl")
                .value(tv.getEndpoint())
                .build();

        outputEndpoint = CfnOutput.Builder.create(this, "GatewayUrl")
                .value(gateway.getUrl())
                .build();
    }
}
