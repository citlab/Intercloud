package de.tu_berlin.cit.intercloud.client.model.occi;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AttributeModel implements Serializable {
    private static final long serialVersionUID = -4070567697021876585L;
    private static final String WRONG_TYPE_MSG = "Cannot set %s Attribute for type %s.";

    public enum Type {
        STRING,
        ENUM,       // String
        INTEGER,
        DOUBLE,
        FLOAT,
        BOOLEAN,
        URI,        // String
        SIGNATURE,  // byte[] Base64Binary --> String
        KEY,        // byte[] Base64Binary --> String
        DATETIME,   // Date (ISO8601)
        DURATION,   // GDuration --> Duration (ISO8601)
        LIST,       // ListType --> List<String>
        MAP         // MapType --> Map<String, String>
    }

    private final String name;
    private final Type type;
    private final boolean required;
    private final boolean mutable;
    private final String description;
    private Object value;

    public AttributeModel(String name, Type type, boolean required, boolean mutable, String description) {
        this.name = name;
        this.type = type;
        this.required = required;
        this.mutable = mutable;
        this.description = description;
    }

    public AttributeModel(String name, String type, boolean required, boolean mutable, String description) {
        this(name, Type.valueOf(type.toString()), required, mutable, description);
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isMutable() {
        return mutable;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasValue() {
        return null != value;
    }

    public Object getValue() {
        return this.value;
    }

    public void clearValue() {
        this.value = null;
    }

    @Override
    public String toString() {
        return "AttributeModel{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", required=" + required +
                ", mutable=" + mutable +
                '}';
    }

    /*
        STRING
     */

    public boolean isString() {
        return Type.STRING.equals(this.type);
    }

    public void setString(String string) {
        if (isString()) {
            this.value = string;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "String", this.type));
        }
    }

    public String getString() {
        return isString() ? (String) this.value : null;
    }

    /*
        ENUM
     */

    public boolean isEnum() {
        return Type.ENUM.equals(this.type);
    }

    public void setEnum(String enumeration) {
        if (isEnum()) {
            this.value = enumeration;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Enum", this.type));
        }
    }

    public String getEnum() {
        return isEnum() ? (String) this.value : null;
    }

    /*
        INTEGER
     */

    public boolean isInteger() {
        return Type.INTEGER.equals(this.type);
    }

    public void setInteger(Integer integer) {
        if (isInteger()) {
            this.value = integer;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Integer", this.type));
        }
    }

    public Integer getInteger() {
        return isInteger() ? (Integer) this.value : null;
    }

    /*
        DOUBLE
     */

    public boolean isDouble() {
        return Type.DOUBLE.equals(this.type);
    }

    public void setDouble(Double d) {
        if (isDouble()) {
            this.value = d;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Double", this.type));
        }
    }

    public Double getDouble() {
        return isDouble() ? (Double) this.value : null;
    }

    /*
        FLOAT
     */

    public boolean isFloat() {
        return Type.FLOAT.equals(this.type);
    }

    public void setFloat(Float f) {
        if (isFloat()) {
            this.value = f;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Float", this.type));
        }
    }

    public Float getFloat() {
        return isFloat() ? (Float) value : null;
    }

    /*
        BOOLEAN
     */

    public boolean isBoolean() {
        return Type.BOOLEAN.equals(this.type);
    }

    public void setBoolean(Boolean b) {
        if (isBoolean()) {
            this.value = b;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Boolean", this.type));
        }
    }

    public Boolean getBoolean() {
        return isBoolean() ? (Boolean) value : null;
    }

    /*
        DATETIME
     */

    public boolean isDatetime() {
        return Type.DATETIME.equals(this.type);
    }

    public void setDatetime(Date datetime) {
        if (isDatetime()) {
            this.value = datetime;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Datetime", this.type));
        }
    }

    public Date getDatetime() {
        return isDatetime() ? (Date) this.value : null;
    }

    /*
        URI
     */

    public boolean isUri() {
        return Type.URI.equals(this.type);
    }

    public void setUri(String uri) {
        if (isUri()) {
            this.value = uri;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Uri", this.type));
        }
    }

    public String getUri() {
        return isUri() ? (String) value : null;
    }

    /*
        LIST
     */

    public boolean isList() {
        return Type.LIST.equals(this.type);
    }

    public void setList(List<String> list) {
        if (isList()) {
            this.value = list;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "List", this.type));
        }
    }

    public List<String> getList() {
        return isList() ? (List<String>) this.value : null;
    }


    /*
        MAP
     */

    public boolean isMap() {
        return Type.MAP.equals(this.type);
    }

    public void setMap(Map<String, String> map) {
        if (isMap()) {
            this.value = map;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Map", this.type));
        }
    }

    public Map<String, String> getMap() {
        return isMap() ? (Map<String, String>) this.value : null;
    }

    /*
        DURATION
     */

    public boolean isDuration() {
        return Type.DURATION.equals(this.type);
    }

    public void setDuration(Duration duration) {
        if (isDuration()) {
            this.value = duration;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Duration", this.type));
        }
    }

    public Duration getDuration() {
        return isDuration() ? (Duration) value : null;
    }

    /*
        KEY
     */

    public boolean isKey() {
        return Type.KEY.equals(this.type);
    }

    public void setKey(String key) {
        if (isKey()) {
            this.value = key;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Key", this.type));
        }
    }

    public String getKey() {
        return isKey() ? (String) this.value : null;
    }

    /*
        SIGNATURE
     */

    public boolean isSignature() {
        return Type.SIGNATURE.equals(this.type);
    }

    public void setSignature(String signature) {
        if (isSignature()) {
            this.value = signature;
        } else {
            throw new IllegalArgumentException(String.format(WRONG_TYPE_MSG, "Signature", this.type));
        }
    }

    public String getSignature() {
        return isSignature() ? (String) value : null;
    }
}
