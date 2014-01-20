package com.techery.spares.statemachine;

import au.com.ds.ef.EasyFlow;
import au.com.ds.ef.EventEnum;
import au.com.ds.ef.StateEnum;
import au.com.ds.ef.StatefulContext;
import au.com.ds.ef.call.ContextHandler;
import au.com.ds.ef.call.ExecutionErrorHandler;
import au.com.ds.ef.call.StateHandler;
import au.com.ds.ef.err.ExecutionError;
import au.com.ds.ef.err.LogicViolationError;

public abstract class StateMachine {

    private EasyFlow<StateMachineContext> flow;
    private StateMachineContext context;

    public StateMachine() {
        this.flow = buildFlow().executor(new MainThreadExecutor());
        this.context = new StateMachineContext();
        this.flow.whenError(new ExecutionErrorHandler<StatefulContext>() {
            @Override
            public void call(ExecutionError error, StatefulContext context) {
                throw new InvalidTransitionException(error.getCause().getLocalizedMessage());
            }
        });
    }

    public StateEnum getState() {
        return getContext().getState();
    }

    protected abstract EasyFlow<StateMachineContext> buildFlow();

    public StateMachineContext getContext() {
        return context;
    }

    public void start() {
        flow.start(true, this.context);
    }

    public void trigger(EventEnum event) throws InvalidTransitionException {
        try {
            this.flow.trigger(event, this.context);
        } catch (LogicViolationError logicViolationError) {
            throw new InvalidTransitionException(logicViolationError.getLocalizedMessage());
        }
    }

    public StateMachine whenEnter(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        this.flow.whenEnter(state, contextHandler);
        return this;
    }

    public StateMachine whenEnter(StateHandler<StateMachineContext> stateHandler) {
        this.flow.whenEnter(stateHandler);
        return this;
    }
}
