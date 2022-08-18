package com.jonak.boundary;

public interface Interaction {
    void execute(Request request, Presenter callback);
}
