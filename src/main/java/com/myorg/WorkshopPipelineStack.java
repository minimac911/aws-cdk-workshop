package com.myorg;

import java.util.List;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.pipelines.CodeBuildStep;
import software.amazon.awscdk.pipelines.CodePipeline;
import software.amazon.awscdk.pipelines.CodePipelineSource;
import software.amazon.awscdk.pipelines.ConnectionSourceOptions;
import software.constructs.Construct;

public class WorkshopPipelineStack extends Stack {
    public WorkshopPipelineStack(Construct scope, String id) {
        this(scope, id, null);
    }

    public WorkshopPipelineStack(Construct scope, String id, StackProps props) {
        super(scope, id, props);

        final CodePipelineSource githubCodeSource = CodePipelineSource.connection("minimac911/aws-cdk-workshop", "main", new ConnectionSourceOptions() {
            @Override
            public String getConnectionArn() {
                return "arn:aws:codestar-connections:us-east-1:714185102750:connection/84ca8d3b-cc50-44ef-b691-98ebe151e809";
            }
        });

        final CodePipeline pipeline = CodePipeline.Builder.create(this, "Pipeline")
                .pipelineName("WorkshopPipeline")
                .synth(CodeBuildStep.Builder.create("SynthStep")
                        .input(githubCodeSource)
                        .installCommands(List.of(
                                "npm install -g aws-cdk"
                        ))
                        .commands(List.of(
                                "mvn package",
                                "npx cdk synth" // need to test if npx is actually needed
                        )).build())
                .build();

        final WorkshopPipelineStage stage = new WorkshopPipelineStage(this, "Deploy");
        pipeline.addStage(stage);
    }


}
