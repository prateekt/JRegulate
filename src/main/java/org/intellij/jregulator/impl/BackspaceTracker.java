package org.intellij.jregulator.impl;

import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
public class BackspaceTracker extends BackspaceHandlerDelegate {


    @Override
    public void beforeCharDeleted(char c, @NotNull PsiFile file, @NotNull Editor editor) {}

    @Override
    public boolean charDeleted(char c, @NotNull PsiFile file, @NotNull Editor editor) {
        int caret = editor.getCaretModel().getOffset();
        System.out.println("Char deleted: " + c + ", " + caret);
        TextDecomposition.getInstance().deleteChar(caret);
        return true;
    }
}
