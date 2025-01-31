/*
 *  Copyright (c) 2020, 2021 Microsoft Corporation
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

package org.eclipse.dataspaceconnector.ids.api.catalog;

import org.eclipse.dataspaceconnector.spi.iam.ClaimToken;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;

import java.util.Collection;

/**
 * Executes consumer queries.
 */
public interface QueryEngine {

    /**
     * Executes a query. Implementations must treat the query as originating from an untrusted source.
     */
    Collection<Asset> execute(String correlationId, ClaimToken consumerToken, String connectorId, String type, String query);

}
