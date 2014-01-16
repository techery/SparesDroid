package com.techery.spares.statemachine;

import au.com.ds.ef.StateEnum;
import au.com.ds.ef.call.ContextHandler;
import au.com.ds.ef.call.StateHandler;

public interface StateObserving {
    public StateObserving whenEnter(StateEnum state, ContextHandler<StateMachineContext> contextHandler);
    public StateObserving whenLeave(StateEnum state, ContextHandler<StateMachineContext> contextHandler);
    public StateObserving whenEnter(StateHandler<StateMachineContext> stateHandler);
}
