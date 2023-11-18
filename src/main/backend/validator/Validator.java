package main.backend.validator;

public abstract class Validator {
    protected Object obj;

    public Validator(Object obj) {
        this.obj = obj;
    }
    public abstract void validate();
}
