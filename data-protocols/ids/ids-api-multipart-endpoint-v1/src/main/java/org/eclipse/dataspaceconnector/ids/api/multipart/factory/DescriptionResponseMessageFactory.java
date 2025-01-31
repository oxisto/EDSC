/*
 *  Copyright (c) 2021 Daimler TSS GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Daimler TSS GmbH - Initial API and Implementation
 *
 */

package org.eclipse.dataspaceconnector.ids.api.multipart.factory;

import de.fraunhofer.iais.eis.DescriptionResponseMessage;
import de.fraunhofer.iais.eis.DescriptionResponseMessageBuilder;
import de.fraunhofer.iais.eis.Message;
import org.eclipse.dataspaceconnector.ids.core.util.CalendarUtil;
import org.eclipse.dataspaceconnector.ids.spi.IdsId;
import org.eclipse.dataspaceconnector.ids.spi.version.IdsProtocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;


// TODO Add security token to the messages
// TODO Add authentication token to the messages
@Deprecated // This functionality will be moved to a transformer class
public class DescriptionResponseMessageFactory {

    private final DescriptionResponseMessageFactorySettings descriptionResponseMessageFactorySettings;

    public DescriptionResponseMessageFactory(@NotNull DescriptionResponseMessageFactorySettings descriptionResponseMessageFactorySettings) {
        this.descriptionResponseMessageFactorySettings = Objects.requireNonNull(descriptionResponseMessageFactorySettings);
    }

    public DescriptionResponseMessage createDescriptionResponseMessage(
            @Nullable Message correlationMessage) {

        IdsId messageId = IdsId.message(UUID.randomUUID().toString());

        DescriptionResponseMessageBuilder builder = new DescriptionResponseMessageBuilder(messageId.toUri());

        builder._contentVersion_(IdsProtocol.INFORMATION_MODEL_VERSION);
        builder._modelVersion_(IdsProtocol.INFORMATION_MODEL_VERSION);

        URI connectorId = descriptionResponseMessageFactorySettings.getId();
        if (connectorId != null) {
            builder._issuerConnector_(connectorId);
            builder._senderAgent_(connectorId);
        }

        builder._issued_(CalendarUtil.gregorianNow());

        if (correlationMessage != null) {
            URI id = correlationMessage.getId();
            if (id != null) {
                builder._correlationMessage_(id);
            }

            URI senderAgent = correlationMessage.getSenderAgent();
            if (senderAgent != null) {
                builder._recipientAgent_(new ArrayList<>(Collections.singletonList(senderAgent)));
            }

            URI issuerConnector = correlationMessage.getIssuerConnector();
            if (issuerConnector != null) {
                builder._recipientConnector_(new ArrayList<>(Collections.singletonList(issuerConnector)));
            }
        }

        return builder.build();
    }
}
