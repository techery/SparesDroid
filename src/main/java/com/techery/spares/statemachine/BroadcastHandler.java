package com.techery.spares.statemachine;

import java.util.HashMap;
import java.util.Map;

import au.com.ds.ef.StateEnum;
import au.com.ds.ef.call.ContextHandler;

public class BroadcastHandler {

    Map<String, BroadcastContextHandler> handlerMap = new HashMap<String, BroadcastContextHandler>();

    public ContextHandler<StateMachineContext> resolve(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        BroadcastContextHandler handler = handlerMap.get(state.name());

        if (handler == null) {
            handler = new BroadcastContextHandler();
            handlerMap.put(state.name(), handler);
        }

        handler.add(contextHandler);

        return handler;
    }

    public void release(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        BroadcastContextHandler handler = handlerMap.get(state.name());
        handler.remove(contextHandler);
    }
}
