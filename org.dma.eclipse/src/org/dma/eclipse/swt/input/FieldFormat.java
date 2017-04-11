/*******************************************************************************
 * 2008-2017 Public Domain
 * Contributors
 * Marco Lopes (marcolopes@netc.pt)
 *******************************************************************************/
package org.dma.eclipse.swt.input;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.dma.java.util.StringUtils;
import org.dma.java.util.TimeDateUtils;

public class FieldFormat extends FieldProperties {

	/** Decimal Format CACHE */
	private static final Map<String, DecimalFormat> DF_CACHE=new HashMap();

	public static DecimalFormat getDecimalFormat(String pattern) {
		DecimalFormat df=DF_CACHE.get(pattern);
		if (df==null) DF_CACHE.put(pattern, df=new DecimalFormat(pattern));
		return df;
	}

	public enum TYPES {

		TIME ("Time", "java.sql.Time"),
		DATE ("Date", "java.util.Date"),
		DECIMAL ("BigDecimal", "java.math.BigDecimal"),
		INTEGER ("Integer", "java.lang.Integer"),
		BOOLEAN ("Boolean", "java.lang.Boolean"),
		STRING ("String", "java.lang.String"),
		LONGSTRING (STRING.java, STRING.klass);

		public final String java;
		public final String klass;

		TYPES(String java, String klass) {
			this.java=java;
			this.klass=klass;
		}

	}

	private final TYPES type;
	private final FieldSize size;
	private final String pattern;
	private final char[] exclude;
	private final String regex;

	public FieldFormat(TYPES type, int size, char...exclude) {
		this(type, size, NONE, exclude);
	}

	public FieldFormat(TYPES type, int size, int properties, char...exclude) {
		this(type, new FieldSize(size), NONE, exclude);
	}

	public FieldFormat(TYPES type, FieldSize size, int properties, char...exclude) {
		this(type, size, null, properties, exclude);
	}

	public FieldFormat(TYPES type, String pattern) {
		this(type, pattern, NONE);
	}

	public FieldFormat(TYPES type, String pattern, int properties) {
		this(type, new FieldSize(pattern.length()), pattern, properties);
	}

	public FieldFormat(TYPES type, FieldSize size, String pattern, int properties, char...exclude) {
		super(properties);
		this.type=type;
		this.size=size;
		this.pattern=pattern==null ? buildPattern() : pattern;
		this.exclude=exclude;
		this.regex=buildRegex(String.valueOf(exclude));
	}


	private String buildRegex(String exclude) {
		//expression start
		String regex="^";
		switch(type){
		case TIME:
		case DATE:
		case STRING:
		case LONGSTRING:
			String range="";
			//digits only
			if(isDigits()) range+="0-9 ";
			//letters only
			if(isLetters()) range+="a-zA-Z ";
			//digits and letters
			if (range.isEmpty()) range+=(char)0+"-"+(char)65535;
			//Character set
			regex+="[" + range + (exclude.isEmpty() ?
					"" : "&&[^"+exclude+"]") + "]";
			//alfanumeric limit
			regex+="{0," + size.size + "}";
			break;

		case DECIMAL:
		case INTEGER:
			//negative signal?
			if(!isPositive()) regex+="-{0,1}";
			//digits only
			regex+="\\d";
			//integer limit
			regex+="{0," + size.size + "}";
			//decimals
			if(type==TYPES.DECIMAL && size.scale>0){
				//decimal sparator
				String group="(\\.\\d";
				//fraction limit
				group+="{0," + size.scale + "}";
				//optional group
				group+=")?";
				regex+=group;
			}
			break;

		case BOOLEAN: break;
		}
		//expression end
		regex+="$";
		return regex;
	}


	/**
	 * <b>Examples</b><br>
	 * SIZE 9,0: ###,###,##0<br>
	 * SIZE 9,3: ###,##0.000<br>
	 * SIZE 4,3: #,##0.000
	 */
	private String buildPattern() {
		switch(type){
		case TIME: return TimeDateUtils.DEFAULT_TIME_PATTERN;
		case DATE: return TimeDateUtils.DEFAULT_DATE_PATTERN;
		case DECIMAL:
		case INTEGER:
			String pattern="";
			//#,###,##
			for(int i=size.size; i>1; i--){
				pattern+="#";
				if(i%3==1) pattern+=",";
			}
			//#,###,##0
			pattern+="0";
			//#,###,##0.000
			if(size.scale>0) pattern+="."+StringUtils.replicate("0", size.scale);
			return pattern;

		case BOOLEAN: break;
		case STRING: break;
		case LONGSTRING: break;
		}
		return pattern;
	}



	/*
	 * Format
	 */
	public String getDisplayPattern() {
		return pattern;
	}

	public String getEditPattern() {
		switch(type){
		case TIME: break;
		case DATE: break;
		case DECIMAL:
		case INTEGER: return isPositive() ? pattern : "-"+pattern;
		case BOOLEAN: break;
		case STRING: break;
		case LONGSTRING: break;
		}
		return pattern;
	}

	/** Time / Date / Number */
	public String format(Object value) {
		switch(type){
		case TIME: return format((Time)value);
		case DATE: return format((Date)value);
		case DECIMAL:
		case INTEGER: return getDecimalFormat(pattern).format(value);
		case BOOLEAN: break;
		case STRING: break;
		case LONGSTRING: break;
		}
		return value.toString();
	}

	public String format(Time time) {
		return TimeDateUtils.getTimeFormatted(time, pattern);
	}

	public String format(Date date) {
		return TimeDateUtils.getDateFormatted(date, pattern);
	}



	/*
	 * Validation
	 */
	public boolean isValid(Integer number) {
		return number!=null &&
				!(isPositive() && number<0) &&
				(number.toString().length()<=size.size+(number<0 ? 1 : 0));
	}

	public boolean isValid(BigInteger number) {
		return number!=null &&
				!(isPositive() && number.signum()<0) &&
				(number.toString().length()<=size.size+(number.signum()<0 ? 1 : 0));
	}

	public boolean isValid(BigDecimal number) {
		return isValid(number.toBigInteger());
	}

	public boolean isValid(String string) {
		return string!=null &&
				string.length()<=size.size &&
				!(isUppercase() && !StringUtils.isUppercase(string)) &&
				!(isLowercase() && !StringUtils.isLowercase(string)) &&
				!(isDigits() && !StringUtils.isNumeric(string)) &&
				!(isLetters() && !StringUtils.isLetters(string)) &&
				!StringUtils.contains(string, exclude);
	}


	/*
	 * Getters and setters
	 */
	public TYPES getType() {
		return type;
	}

	public FieldSize getSize() {
		return size;
	}

	public String getEditRegex() {
		return regex;
	}


}