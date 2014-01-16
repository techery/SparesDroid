package com.techery.spares.statemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.ds.ef.StateEnum;
import au.com.ds.ef.call.ContextHandler;
import au.com.ds.ef.call.StateHandler;

public class StateObservingAdapter implements StateObserving {
    private final StateMachine stateMachine;

    private final Map<StateEnum, ContextHandler<StateMachineContext>> contextHandlers = new HashMap<StateEnum, ContextHandler<StateMachineContext>>();
    private final List<StateHandler<StateMachineContext>> stateHandlers = new ArrayList<StateHandler<StateMachineContext>>();

    public StateObservingAdapter(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public StateObserving whenEnter(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        contextHandlers.put(state, contextHandler);
        stateMachine.whenEnter(state, contextHandler);
        return this;
    }

    @Override
    public StateObserving whenLeave(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        contextHandlers.put(state, contextHandler);
        stateMachine.whenLeave(state, contextHandler);
        return this;
    }

    @Override
    public StateObserving whenEnter(StateHandler<StateMachineContext> stateHandler) {
        stateHandlers.add(stateHandler);
        stateMachine.whenEnter(stateHandler);
        return this;
    }

    public void release() {
        for(Map.Entry<StateEnum, ContextHandler<StateMachineContext>> c : contextHandlers.entrySet()) {
            stateMachine.releaseContextHandler(c.getKey(), c.getValue());
        }

        for(StateHandler<StateMachineContext> s : stateHandlers) {
            stateMachine.releaseStateHandler(s);
        }
    }
}
