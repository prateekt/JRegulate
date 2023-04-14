package org.intellij.jregulator.impl;
import java.util.ArrayList;

enum CharacterLabel {
    HUMAN_TEXT,
    ROBOT_TEXT,
    UNLABELED
}


class LabeledCharacter {
    public CharacterLabel label;
    public char character;
    public LabeledCharacter(char character, CharacterLabel label) {
        this.label = label;
        this.character = character;
    }

    public String toString() {
        return "(" + character + ", " + label.toString() + ")";
    }
}
public class TextDecomposition {
    protected ArrayList<LabeledCharacter> chars;

    // singleton
    private static TextDecomposition instance = null;
    private TextDecomposition() {
        chars = new ArrayList<>();
    }
    public static TextDecomposition getInstance() {
        if (instance == null) {
            instance = new TextDecomposition();
        }
        return instance;
    }

    public void addChar(char character, CharacterLabel label) {
        chars.add(new LabeledCharacter(character, label));
    }

    public void insertChar(char character, CharacterLabel label, int index) {
        chars.add(index, new LabeledCharacter(character, label));
    }

    public void insertString(String string, CharacterLabel label, int index) {
        for (int i = 0; i < string.length(); i++) {
            int insert_index = index + i + 1;
            if (insert_index >= chars.size()) {
                addChar(string.charAt(i), label);
            }
            else {
                LabeledCharacter lc = chars.get(insert_index);
                lc.character = string.charAt(i);
            }
        }
    }

    public void deleteChar(int index) {
        chars.remove(index);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (LabeledCharacter c : chars) {
            result.append(c.toString());
        }
        return result.toString();
    }

    public int findInsertion(String newText) {
        // find text in newText that is not in chars and return index of insertion
        int index = 0;
        for (int i = 0; i < newText.length(); i++) {
            if (i >= chars.size()) {
                return i;
            }
            if (newText.charAt(i) != chars.get(index).character) {
                return i;
            }
            index++;
        }
        return -1;
    }
}
