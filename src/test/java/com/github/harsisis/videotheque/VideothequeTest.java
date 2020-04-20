package com.github.harsisis.videotheque;

import com.github.harsisis.objet.Videotheque;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class VideothequeTest {

    @Test
    public void given_validInputParams_when_ajoutClient_then_true() {
        Videotheque videotheque = new Videotheque();

        boolean result = videotheque.ajoutClient("John", "Smith", true);

        assertTrue(result);
    }


}