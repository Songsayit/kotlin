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

package org.jetbrains.jet.lang.types;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.lang.descriptors.annotations.Annotated;
import org.jetbrains.jet.lang.resolve.scopes.JetScope;
import org.jetbrains.jet.lang.types.checker.JetTypeChecker;

import java.util.List;

/**
 * @author abreslav
 * @see JetTypeChecker#isSubtypeOf(JetType, JetType)
 */
public interface JetType extends Annotated {
    @NotNull TypeConstructor getConstructor();
    @NotNull List<TypeProjection> getArguments();
    boolean isNullable();

    @NotNull
    JetScope getMemberScope();

    @Override
    boolean equals(Object other);
}
