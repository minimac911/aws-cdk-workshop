package com.myorg;

import software.amazon.awscdk.CfnOutput;
import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

public class WorkshopPipelineStage extends Stage {

    public final CfnOutput outputViewerUrl;
    public final CfnOutput outputEndpoint;

    public WorkshopPipelineStage(Construct scope, String id) {
        this(scope, id, null);
    }

    public WorkshopPipelineStage(Construct scope, String id, StageProps props) {
        super(scope, id, props);

        final CdkWorkshopStack service = new CdkWorkshopStack(this, "WorkshopWebService");

        outputViewerUrl = service.outputViewerUrl;
        outputEndpoint = service.outputEndpoint;
    }
}
