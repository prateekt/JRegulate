package org.intellij.jregulator.impl;

import com.intellij.codeInsight.editorActions.enter.EnterHandlerDelegate;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnterTracker implements EnterHandlerDelegate {

    @Override
    public Result preprocessEnter(@NotNull PsiFile file, @NotNull Editor editor, @NotNull Ref<Integer> caretOffset, @NotNull Ref<Integer> caretAdvance, @NotNull DataContext dataContext, @Nullable EditorActionHandler originalHandler) {
        return null;
    }

    @Override
    public Result postProcessEnter(@NotNull PsiFile file, @NotNull Editor editor, @NotNull DataContext dataContext) {
        int caret = editor.getCaretModel().getOffset();
        System.out.println("Enter at: "+ caret);
        TextDecomposition.getInstance().insertChar('\n', CharacterLabel.HUMAN_TEXT, caret-1);
        return Result.Stop;
    }
}
