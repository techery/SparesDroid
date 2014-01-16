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

public abstract class StateMachine implements StateObserving {

    private EasyFlow<StateMachineContext> flow;
    private StateMachineContext context;

    public StateMachine() {
        this.flow = buildFlow().executor(new MainThreadExecutor());
        this.context = new StateMachineContext();
        this.flow.whenError(new ExecutionErrorHandler<StatefulContext>() {
            @Override
            public void call(ExecutionError error, StatefulContext context) {
                throw new InvalidTransitionException(error.getLocalizedMessage());
            }
        });
    }

    public StateEnum getState() {
        return getContext().getState();
    }

    protected abstract EasyFlow<StateMachineContext> buildFlow();

    public EasyFlow<StateMachineContext> getFlow() {
        return flow;
    }

    public StateMachineContext getContext() {
        return context;
    }

    public void start() {
        flow.start(true, this.context);
    }

    public void start(StateMachineContext context) {
        this.context = context;
        start();
    }

    public void trigger(EventEnum event) throws InvalidTransitionException {
        try {
            getFlow().trigger(event, this.context);
        } catch (LogicViolationError logicViolationError) {
            throw new InvalidTransitionException(logicViolationError.getLocalizedMessage());
        }
    }

    BroadcastHandler enterHolder = new BroadcastHandler();

    @Override
    public StateObserving whenEnter(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        getFlow().whenEnter(state, enterHolder.resolve(state, contextHandler));
        return this;
    }

    BroadcastHandler leaveHolder = new BroadcastHandler();

    @Override
    public StateObserving whenLeave(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        getFlow().whenLeave(state, leaveHolder.resolve(state, contextHandler));
        return this;
    }

    BroadcastStateHandler broadcastStateHandler = new BroadcastStateHandler();

    @Override
    public StateObserving whenEnter(StateHandler<StateMachineContext> stateHandler) {
        broadcastStateHandler.add(stateHandler);
        getFlow().whenEnter(broadcastStateHandler);
        return this;
    }

    public void releaseContextHandler(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        enterHolder.release(state, contextHandler);
        leaveHolder.release(state, contextHandler);
    }

    public void releaseStateHandler(StateHandler<StateMachineContext> stateHandler) {
        broadcastStateHandler.release(stateHandler);
    }
}
