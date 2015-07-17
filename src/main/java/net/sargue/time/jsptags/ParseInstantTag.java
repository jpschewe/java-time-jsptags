/*
 * Copyright 1999-2004 The Apache Software Foundation.
 * Modifications, Copyright 2005 Stephen Colebourne, 2014 Sergi Baila
 * 
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
 */
package net.sargue.time.jsptags;

import javax.servlet.jsp.JspTagException;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Locale;

/**
 * <p>
 * A handler for &lt;parseDate&gt; that supports rtexprvalue-based attributes.
 * </p>
 * 
 * @author Jan Luehe
 * @author Jim Newsham
 * @author Sergi Baila
 */

@SuppressWarnings("UnusedDeclaration")
public class ParseInstantTag extends ParseInstantSupport {

    /**
     * Sets the value attribute.
     * 
     * @param value  the value
     */
    public void setValue(String value) throws JspTagException {
        this.value = value;
        this.valueSpecified = true;
    }

    /**
     * Sets the style attribute.
     * 
     * @param style  the style
     */
    public void setStyle(String style) throws JspTagException {
        this.style = style;
    }

    /**
     * Sets the pattern attribute.
     * 
     * @param pattern  the pattern
     */
    public void setPattern(String pattern) throws JspTagException {
        this.pattern = pattern;
    }

    /**
     * Sets the zone attribute.
     * 
     * @param dtz  the zone
     */
    public void setZoneId(Object dtz) throws JspTagException {
        if (dtz == null || dtz instanceof String
                && ((String) dtz).length() == 0) {
            this.zoneId = null;
        } else if (dtz instanceof ZoneId) {
            this.zoneId = (ZoneId) dtz;
        } else if (dtz instanceof String) {
            try {
                this.zoneId = ZoneId.of((String) dtz);
            } catch (IllegalArgumentException iae) {
                this.zoneId = ZoneOffset.UTC;
            }
        } else
            throw new JspTagException("Can only accept ZoneId or String objects.");
    }

    /**
     * Sets the style attribute.
     * 
     * @param loc  the locale
     */
    public void setLocale(Object loc) throws JspTagException {
        if (loc == null
                || (loc instanceof String && ((String) loc).length() == 0)) {
            this.locale = null;
        } else if (loc instanceof Locale) {
            this.locale = (Locale) loc;
        } else if (loc instanceof String) {
            locale = Util.parseLocale((String) loc);
        } else
            throw new JspTagException("Can only accept Locale or String objects.");
    }

}