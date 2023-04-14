// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.jregulator.impl;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * This is a custom {@link TypedHandlerDelegate} that handles actions activated keystrokes in the editor.
 * The execute method inserts a fixed string at Offset 0 of the document.
 * Document changes are made in the context of a write action.
 */
class TypingTracker extends TypedHandlerDelegate {

  @NotNull
  @Override
  public Result charTyped(char c, @NotNull Project project, @NotNull Editor editor,
                          @NotNull PsiFile file) {
    int caret = editor.getCaretModel().getOffset();
    System.out.println("Char typed: " + c + ", " + caret);
    TextDecomposition.getInstance().insertChar(c, CharacterLabel.HUMAN_TEXT, caret-1);
    return Result.STOP;
  }

}
