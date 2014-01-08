package com.techery.spares.statemachine;

import au.com.ds.ef.EasyFlow;
import au.com.ds.ef.EventEnum;
import au.com.ds.ef.StateEnum;
import au.com.ds.ef.call.ContextHandler;
import au.com.ds.ef.call.StateHandler;
import au.com.ds.ef.err.LogicViolationError;

public abstract class StateMachine {

    private EasyFlow<StateMachineContext> flow;
    private StateMachineContext context;

    public StateMachine() {
        this.flow = buildFlow().executor(new MainThreadExecutor());
        this.context = new StateMachineContext();
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

    public void trigger(EventEnum event) {
        try {
            getFlow().trigger(event, this.context);
        } catch (LogicViolationError logicViolationError) {
            logicViolationError.printStackTrace();
        }
    }

    BroadcastHandlerHolder enterHolder = new BroadcastHandlerHolder();

    public StateMachine whenEnter(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        getFlow().whenEnter(state, enterHolder.resolve(state, contextHandler));
        return this;
    }

    BroadcastHandlerHolder leaveHolder = new BroadcastHandlerHolder();
    public StateMachine whenLeave(StateEnum state, ContextHandler<StateMachineContext> contextHandler) {
        getFlow().whenLeave(state, leaveHolder.resolve(state, contextHandler));
        return this;
    }

    BroadcastStateHandler broadcastStateHandler = new BroadcastStateHandler();
    public StateMachine whenEnter(StateHandler<StateMachineContext> stateHandler) {
        broadcastStateHandler.add(stateHandler);
        getFlow().whenEnter(broadcastStateHandler);
        return this;
    }
}
