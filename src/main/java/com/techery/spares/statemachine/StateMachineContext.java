package com.techery.spares.statemachine;


import au.com.ds.ef.StatefulContext;

public class StateMachineContext extends StatefulContext{
    Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
