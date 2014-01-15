package com.techery.spares.statemachine;


import java.util.ArrayList;
import java.util.List;

import au.com.ds.ef.StateEnum;
import au.com.ds.ef.call.StateHandler;

public class BroadcastStateHandler implements StateHandler<StateMachineContext> {

    private final List<StateHandler<StateMachineContext>> handlers = new ArrayList<StateHandler<StateMachineContext>>();

    public void add(StateHandler<StateMachineContext> handler) {
        handlers.add(handler);
    }

    @Override
    public void call(StateEnum stateEnum, StateMachineContext stateMachineContext) throws Exception {
        for(StateHandler<StateMachineContext> handler : handlers) {
            handler.call(stateEnum, stateMachineContext);
        }
    }

    public void release(StateHandler<StateMachineContext> stateHandler) {
        handlers.remove(stateHandler);
    }
}
