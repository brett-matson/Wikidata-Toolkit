package org.wikidata.wdtk.dumpfiles.constraint.template;

/*
 * #%L
 * Wikidata Toolkit Dump File Handling
 * %%
 * Copyright (C) 2014 Wikidata Toolkit Developers
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.Validate;

/**
 * 
 * This class represents a particular transclusion of a Wikibase template.
 * 
 * @author Julian Mendez
 * 
 */
public class Template {

	final String id;
	final Map<String, String> parameters = new TreeMap<String, String>();

	public Template(String id, Map<String, String> parameters) {
		Validate.notNull(id, "ID cannot be null.");
		Validate.notNull(parameters, "Parameters cannot be null.");
		this.id = id.trim();
		for (String key : parameters.keySet()) {
			String value = parameters.get(key);
			value = ((value == null) ? "" : value.trim());
			this.parameters.put(key.trim(), value);
		}
	}

	public String get(String key) {
		return this.parameters.get(key);
	}

	public String getId() {
		return this.id;
	}

	public Map<String, String> getParameters() {
		return Collections.unmodifiableMap(this.parameters);
	}

	public Set<String> keySet() {
		return this.parameters.keySet();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(TemplateConstant.OPENING_BRACES);
		sb.append(this.id);
		for (String key : this.parameters.keySet()) {
			sb.append(TemplateConstant.NEWLINE);
			sb.append(TemplateConstant.VERTICAL_BAR);
			sb.append(TemplateConstant.SPACE);
			sb.append(key);
			String value = this.parameters.get(key);
			if ((value != null) && !value.isEmpty()) {
				sb.append(TemplateConstant.EQUALS_SIGN);
				sb.append(value);
			}
		}
		sb.append(TemplateConstant.NEWLINE);
		sb.append(TemplateConstant.CLOSING_BRACES);
		return sb.toString();
	}

}
