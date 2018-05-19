package com.arrk.arrkandroidtest.view;

import com.arrk.arrkandroidtest.model.Character;

import java.util.List;

/**
 * This class represents the character detail view interface.
 *
 *
 * @author Kajal Sharma
 * @date 19/05/2018.
 */
public interface CharacterDetailView {
    void detailReady(Character character);
    void showSpinner(boolean state);
}
