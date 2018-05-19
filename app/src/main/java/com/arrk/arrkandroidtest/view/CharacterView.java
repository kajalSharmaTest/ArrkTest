package com.arrk.arrkandroidtest.view;



import com.arrk.arrkandroidtest.model.Character;

import java.util.List;

/**
 * This class represents the character view interface.
 *
 *
 * @author Kajal Sharma
 * @date 19/05/2018.
 */
public interface CharacterView {

    void peoplelistReady(List<Character> characters);
    void showSpinner(boolean state);
    }
