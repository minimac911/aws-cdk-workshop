package com.myorg;


import software.amazon.awscdk.services.lambda.IFunction;

public interface HitCounterProps {
    static Builder builder(){
        return new Builder();
    }

    IFunction getDownstream();

    class Builder{
        private IFunction downstream;

        public Builder downstream(final IFunction function){
            this.downstream = function;
            return this;
        }

        public HitCounterProps build(){
            if(downstream == null){
                throw new NullPointerException("The downstream property is required!");
            }
            return () -> downstream;
        }
    }
}
