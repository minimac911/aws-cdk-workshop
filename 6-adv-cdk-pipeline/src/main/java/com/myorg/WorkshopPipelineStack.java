package com.myorg;

import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.constructs.Construct;

public class WorkshopPipelineStack extends Stack {
    public WorkshopPipelineStack(Construct scope, String id) {
        super(scope, id);
    }

    public WorkshopPipelineStack(Construct scope, String id, StackProps props) {
        super(scope, id, props);
    }


}
