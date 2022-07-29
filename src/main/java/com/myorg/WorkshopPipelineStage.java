package com.myorg;

import software.amazon.awscdk.Stage;
import software.amazon.awscdk.StageProps;
import software.constructs.Construct;

public class WorkshopPipelineStage extends Stage {
    public WorkshopPipelineStage(Construct scope, String id) {
        this(scope, id, null);
    }

    public WorkshopPipelineStage(Construct scope, String id, StageProps props) {
        super(scope, id, props);

        new CdkWorkshopStack(this, "WorkshopWebService");
    }
}
