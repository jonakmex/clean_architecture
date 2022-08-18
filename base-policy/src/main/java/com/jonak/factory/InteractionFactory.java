package com.jonak.factory;

import com.jonak.boundary.Interaction;

public interface InteractionFactory {
    Interaction make(String name);
}
