package fr.fingarde.fayga.bot.command;

import java.lang.reflect.Method;

public final class SimpleCommand {

    private final String name, descrition;
    private final Command.ExecutorType executorType;
    private final Object object;
    private final Method method;

    public SimpleCommand(String name, String descrition, Command.ExecutorType executorType, Object object, Method method) {
        this.name = name;
        this.descrition = descrition;
        this.executorType = executorType;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDescrition() {
        return descrition;
    }

    public Command.ExecutorType getExecutorType() {
        return executorType;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
