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
package com.clicktravel.cheddar.domain.feature.toggle;

/**
 * Default FeaturePropertyAdapter which will use the format:
 * 
 * feature.toggle.services.{context}.{featureName}
 * 
 * Property will be lower-cased with underscores changed into dots.
 * 
 */
public class DefaultFeaturePropertyAdapter implements FeaturePropertyAdapter {

    @Override
    public String toPropertyKey(final Feature feature) {
        return ("feature.toggle.services." + feature.context() + "." + feature.name()).replace("_", ".").toLowerCase();
    }

}