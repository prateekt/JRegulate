package org.intellij.jregulator.impl;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class TabTracker extends EditorActionHandler {

    public TabTracker() {
        super();
    }

    @Override
    protected void doExecute(@NotNull Editor editor, @Nullable Caret caret, DataContext dataContext) {

        // obtain text
        final Document document = editor.getDocument();
        String text = document.getText();

        // check if text has robotic insertion
        TextDecomposition textDecomposition = TextDecomposition.getInstance();
        int insertion = textDecomposition.findInsertion(text);
        if (insertion != -1) {
            // insert text
            String insertion_text = text.substring(insertion);
            textDecomposition.insertString(insertion_text, CharacterLabel.ROBOT_TEXT, insertion-1);
        }

        // execute
        super.doExecute(editor, caret, dataContext);

        // print TextDecomposition
        System.out.println("TextDecomposition: " + TextDecomposition.getInstance().toString());

    }
}
