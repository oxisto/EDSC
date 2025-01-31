/*
 *  Copyright (c) 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */
package org.eclipse.dataspaceconnector.ids.spi.transform;

/**
 * Dispatches to {@link IdsTypeTransformer}s to bidirectionally convert between IDS and EDC types.
 */
public interface TransformerRegistry {

    /**
     * Registers a transformer.
     */
    void register(IdsTypeTransformer<?, ?> transformer);

    /**
     * Transforms the object and any contained types, returning its transformed representation or null if the operation cannot be completed.
     *
     * @param object the instance to transform
     * @param outputType the transformed output type
     * @param <INPUT> the instance type
     * @param <OUTPUT> the transformed object type
     * @return the transform result
     */
    <INPUT, OUTPUT> TransformResult<OUTPUT> transform(INPUT object, Class<OUTPUT> outputType);

}
