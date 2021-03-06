/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.clicktravel.infrastructure.persistence.aws.cloudsearch.client;

import static com.clicktravel.common.random.Randoms.randomString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class BatchDocumentUpdateRequestTest {

    @Test
    public void shouldCreateBatchDocumentUpdateRequest_withSearchDomain() throws Exception {
        // Given
        final String searchDomain = randomString(10);

        // When
        final BatchDocumentUpdateRequest request = new BatchDocumentUpdateRequest(searchDomain);

        // Then
        assertNotNull(request);
        assertEquals(searchDomain, request.getSearchDomain());
    }

    @Test
    public void shouldBuildBatchDocumentUpdateRequest_withDocument() throws Exception {
        // Given
        final DocumentUpdate document = mock(DocumentUpdate.class);
        final String searchDomain = randomString(10);
        final BatchDocumentUpdateRequest request = new BatchDocumentUpdateRequest(searchDomain);

        // When
        final BatchDocumentUpdateRequest updatedRequest = request.withDocument(document);

        // Then
        assertNotNull(updatedRequest);
        assertEquals(1, updatedRequest.getDocumentUpdates().size());
        assertEquals(document, updatedRequest.getDocumentUpdates().iterator().next());
    }

}
