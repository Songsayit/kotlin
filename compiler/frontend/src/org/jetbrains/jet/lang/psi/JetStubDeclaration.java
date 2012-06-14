/*
 * Copyright 2010-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.lang.psi;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.JetNodeTypes;
import org.jetbrains.jet.lexer.JetToken;

/**
 * @author Nikolay Krasko
 */
abstract class JetStubDeclaration<T extends StubElement> extends StubBasedPsiElementBase<T> implements JetDeclaration {
    public JetStubDeclaration(@NotNull T stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public JetStubDeclaration(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public JetModifierList getModifierList() {
        return (JetModifierList) findChildByType(JetNodeTypes.MODIFIER_LIST);
    }

    @Override
    public boolean hasModifier(JetToken modifier) {
        JetModifierList modifierList = getModifierList();
        return modifierList != null && modifierList.hasModifier(modifier);
    }

    @Override
    public <D> void acceptChildren(@NotNull JetTreeVisitor<D> visitor, D data) {
        PsiElement child = getFirstChild();
        while (child != null) {
            if (child instanceof JetElement) {
                ((JetElement) child).accept(visitor, data);
            }
            child = child.getNextSibling();
        }
    }

    @Override
    public void accept(@NotNull JetVisitorVoid visitor) {
        visitor.visitExpression(this);
    }

    @Override
    public <R, D> R accept(@NotNull JetVisitor<R, D> visitor, D data) {
        return visitor.visitExpression(this, data);
    }
}