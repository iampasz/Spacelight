package com.appsforkids.pasz.spacelight.Interfaces;

import java.io.Serializable;

public interface DoThisAction extends Serializable {

    void doThis();

    void doThis(int hours, int minutes);

    void doThat();

}
