package com.myorg;

import org.jetbrains.annotations.NotNull;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.pipelines.ConnectionSourceOptions;
import software.constructs.Construct;

public class WorkshopPipelineStack extends Stack {
    public WorkshopPipelineStack(Construct scope, String id) {
        super(scope, id);
    }

    public WorkshopPipelineStack(Construct scope, String id, StackProps props) {
        super(scope, id, props);

        CodePipelineSource.connection("minimac911/aws-cdk-workshop", "feature/cdk-pipelines", new ConnectionSourceOptions() {
            @Override
            public @NotNull String getConnectionArn() {
                return null;
            }
        });
    }


}
