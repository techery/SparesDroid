package com.techery.spares.statemachine;

import java.util.ArrayList;
import java.util.List;

import au.com.ds.ef.call.ContextHandler;

public class BroadcastContextHandler implements ContextHandler<StateMachineContext> {
    private final List<ContextHandler<StateMachineContext>> handlers = new ArrayList<ContextHandler<StateMachineContext>>();

    public void add(ContextHandler<StateMachineContext> handler) {
        handlers.add(handler);
    }

    @Override
    public void call(StateMachineContext stateMachineContext) throws Exception {
        for(ContextHandler<StateMachineContext> handler : handlers) {
            handler.call(stateMachineContext);
        }
    }
}
