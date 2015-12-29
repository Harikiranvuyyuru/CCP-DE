/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.example.crunch;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class WebEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"WebEvent\",\"namespace\":\"com.example.crunch\",\"fields\":[{\"name\":\"clientIp\",\"type\":\"string\"},{\"name\":\"clientId\",\"type\":\"string\"},{\"name\":\"userId\",\"type\":\"string\"},{\"name\":\"timeStamp\",\"type\":\"string\"},{\"name\":\"requestType\",\"type\":{\"type\":\"enum\",\"name\":\"Request\",\"symbols\":[\"GET\",\"PUT\"]}},{\"name\":\"requestPage\",\"type\":\"string\"},{\"name\":\"httpProtocol\",\"type\":\"string\"},{\"name\":\"responseCode\",\"type\":\"long\"},{\"name\":\"responseSize\",\"type\":\"long\"},{\"name\":\"referrer\",\"type\":\"string\"},{\"name\":\"agentId\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence clientIp;
  @Deprecated public java.lang.CharSequence clientId;
  @Deprecated public java.lang.CharSequence userId;
  @Deprecated public java.lang.CharSequence timeStamp;
  @Deprecated public com.example.crunch.Request requestType;
  @Deprecated public java.lang.CharSequence requestPage;
  @Deprecated public java.lang.CharSequence httpProtocol;
  @Deprecated public long responseCode;
  @Deprecated public long responseSize;
  @Deprecated public java.lang.CharSequence referrer;
  @Deprecated public java.lang.CharSequence agentId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public WebEvent() {}

  /**
   * All-args constructor.
   */
  public WebEvent(java.lang.CharSequence clientIp, java.lang.CharSequence clientId, java.lang.CharSequence userId, java.lang.CharSequence timeStamp, com.example.crunch.Request requestType, java.lang.CharSequence requestPage, java.lang.CharSequence httpProtocol, java.lang.Long responseCode, java.lang.Long responseSize, java.lang.CharSequence referrer, java.lang.CharSequence agentId) {
    this.clientIp = clientIp;
    this.clientId = clientId;
    this.userId = userId;
    this.timeStamp = timeStamp;
    this.requestType = requestType;
    this.requestPage = requestPage;
    this.httpProtocol = httpProtocol;
    this.responseCode = responseCode;
    this.responseSize = responseSize;
    this.referrer = referrer;
    this.agentId = agentId;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return clientIp;
    case 1: return clientId;
    case 2: return userId;
    case 3: return timeStamp;
    case 4: return requestType;
    case 5: return requestPage;
    case 6: return httpProtocol;
    case 7: return responseCode;
    case 8: return responseSize;
    case 9: return referrer;
    case 10: return agentId;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: clientIp = (java.lang.CharSequence)value$; break;
    case 1: clientId = (java.lang.CharSequence)value$; break;
    case 2: userId = (java.lang.CharSequence)value$; break;
    case 3: timeStamp = (java.lang.CharSequence)value$; break;
    case 4: requestType = (com.example.crunch.Request)value$; break;
    case 5: requestPage = (java.lang.CharSequence)value$; break;
    case 6: httpProtocol = (java.lang.CharSequence)value$; break;
    case 7: responseCode = (java.lang.Long)value$; break;
    case 8: responseSize = (java.lang.Long)value$; break;
    case 9: referrer = (java.lang.CharSequence)value$; break;
    case 10: agentId = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'clientIp' field.
   */
  public java.lang.CharSequence getClientIp() {
    return clientIp;
  }

  /**
   * Sets the value of the 'clientIp' field.
   * @param value the value to set.
   */
  public void setClientIp(java.lang.CharSequence value) {
    this.clientIp = value;
  }

  /**
   * Gets the value of the 'clientId' field.
   */
  public java.lang.CharSequence getClientId() {
    return clientId;
  }

  /**
   * Sets the value of the 'clientId' field.
   * @param value the value to set.
   */
  public void setClientId(java.lang.CharSequence value) {
    this.clientId = value;
  }

  /**
   * Gets the value of the 'userId' field.
   */
  public java.lang.CharSequence getUserId() {
    return userId;
  }

  /**
   * Sets the value of the 'userId' field.
   * @param value the value to set.
   */
  public void setUserId(java.lang.CharSequence value) {
    this.userId = value;
  }

  /**
   * Gets the value of the 'timeStamp' field.
   */
  public java.lang.CharSequence getTimeStamp() {
    return timeStamp;
  }

  /**
   * Sets the value of the 'timeStamp' field.
   * @param value the value to set.
   */
  public void setTimeStamp(java.lang.CharSequence value) {
    this.timeStamp = value;
  }

  /**
   * Gets the value of the 'requestType' field.
   */
  public com.example.crunch.Request getRequestType() {
    return requestType;
  }

  /**
   * Sets the value of the 'requestType' field.
   * @param value the value to set.
   */
  public void setRequestType(com.example.crunch.Request value) {
    this.requestType = value;
  }

  /**
   * Gets the value of the 'requestPage' field.
   */
  public java.lang.CharSequence getRequestPage() {
    return requestPage;
  }

  /**
   * Sets the value of the 'requestPage' field.
   * @param value the value to set.
   */
  public void setRequestPage(java.lang.CharSequence value) {
    this.requestPage = value;
  }

  /**
   * Gets the value of the 'httpProtocol' field.
   */
  public java.lang.CharSequence getHttpProtocol() {
    return httpProtocol;
  }

  /**
   * Sets the value of the 'httpProtocol' field.
   * @param value the value to set.
   */
  public void setHttpProtocol(java.lang.CharSequence value) {
    this.httpProtocol = value;
  }

  /**
   * Gets the value of the 'responseCode' field.
   */
  public java.lang.Long getResponseCode() {
    return responseCode;
  }

  /**
   * Sets the value of the 'responseCode' field.
   * @param value the value to set.
   */
  public void setResponseCode(java.lang.Long value) {
    this.responseCode = value;
  }

  /**
   * Gets the value of the 'responseSize' field.
   */
  public java.lang.Long getResponseSize() {
    return responseSize;
  }

  /**
   * Sets the value of the 'responseSize' field.
   * @param value the value to set.
   */
  public void setResponseSize(java.lang.Long value) {
    this.responseSize = value;
  }

  /**
   * Gets the value of the 'referrer' field.
   */
  public java.lang.CharSequence getReferrer() {
    return referrer;
  }

  /**
   * Sets the value of the 'referrer' field.
   * @param value the value to set.
   */
  public void setReferrer(java.lang.CharSequence value) {
    this.referrer = value;
  }

  /**
   * Gets the value of the 'agentId' field.
   */
  public java.lang.CharSequence getAgentId() {
    return agentId;
  }

  /**
   * Sets the value of the 'agentId' field.
   * @param value the value to set.
   */
  public void setAgentId(java.lang.CharSequence value) {
    this.agentId = value;
  }

  /** Creates a new WebEvent RecordBuilder */
  public static com.example.crunch.WebEvent.Builder newBuilder() {
    return new com.example.crunch.WebEvent.Builder();
  }
  
  /** Creates a new WebEvent RecordBuilder by copying an existing Builder */
  public static com.example.crunch.WebEvent.Builder newBuilder(com.example.crunch.WebEvent.Builder other) {
    return new com.example.crunch.WebEvent.Builder(other);
  }
  
  /** Creates a new WebEvent RecordBuilder by copying an existing WebEvent instance */
  public static com.example.crunch.WebEvent.Builder newBuilder(com.example.crunch.WebEvent other) {
    return new com.example.crunch.WebEvent.Builder(other);
  }
  
  /**
   * RecordBuilder for WebEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<WebEvent>
    implements org.apache.avro.data.RecordBuilder<WebEvent> {

    private java.lang.CharSequence clientIp;
    private java.lang.CharSequence clientId;
    private java.lang.CharSequence userId;
    private java.lang.CharSequence timeStamp;
    private com.example.crunch.Request requestType;
    private java.lang.CharSequence requestPage;
    private java.lang.CharSequence httpProtocol;
    private long responseCode;
    private long responseSize;
    private java.lang.CharSequence referrer;
    private java.lang.CharSequence agentId;

    /** Creates a new Builder */
    private Builder() {
      super(com.example.crunch.WebEvent.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.example.crunch.WebEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.clientIp)) {
        this.clientIp = data().deepCopy(fields()[0].schema(), other.clientIp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.clientId)) {
        this.clientId = data().deepCopy(fields()[1].schema(), other.clientId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.timeStamp)) {
        this.timeStamp = data().deepCopy(fields()[3].schema(), other.timeStamp);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.requestType)) {
        this.requestType = data().deepCopy(fields()[4].schema(), other.requestType);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.requestPage)) {
        this.requestPage = data().deepCopy(fields()[5].schema(), other.requestPage);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.httpProtocol)) {
        this.httpProtocol = data().deepCopy(fields()[6].schema(), other.httpProtocol);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.responseCode)) {
        this.responseCode = data().deepCopy(fields()[7].schema(), other.responseCode);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.responseSize)) {
        this.responseSize = data().deepCopy(fields()[8].schema(), other.responseSize);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.referrer)) {
        this.referrer = data().deepCopy(fields()[9].schema(), other.referrer);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.agentId)) {
        this.agentId = data().deepCopy(fields()[10].schema(), other.agentId);
        fieldSetFlags()[10] = true;
      }
    }
    
    /** Creates a Builder by copying an existing WebEvent instance */
    private Builder(com.example.crunch.WebEvent other) {
            super(com.example.crunch.WebEvent.SCHEMA$);
      if (isValidValue(fields()[0], other.clientIp)) {
        this.clientIp = data().deepCopy(fields()[0].schema(), other.clientIp);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.clientId)) {
        this.clientId = data().deepCopy(fields()[1].schema(), other.clientId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.userId)) {
        this.userId = data().deepCopy(fields()[2].schema(), other.userId);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.timeStamp)) {
        this.timeStamp = data().deepCopy(fields()[3].schema(), other.timeStamp);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.requestType)) {
        this.requestType = data().deepCopy(fields()[4].schema(), other.requestType);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.requestPage)) {
        this.requestPage = data().deepCopy(fields()[5].schema(), other.requestPage);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.httpProtocol)) {
        this.httpProtocol = data().deepCopy(fields()[6].schema(), other.httpProtocol);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.responseCode)) {
        this.responseCode = data().deepCopy(fields()[7].schema(), other.responseCode);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.responseSize)) {
        this.responseSize = data().deepCopy(fields()[8].schema(), other.responseSize);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.referrer)) {
        this.referrer = data().deepCopy(fields()[9].schema(), other.referrer);
        fieldSetFlags()[9] = true;
      }
      if (isValidValue(fields()[10], other.agentId)) {
        this.agentId = data().deepCopy(fields()[10].schema(), other.agentId);
        fieldSetFlags()[10] = true;
      }
    }

    /** Gets the value of the 'clientIp' field */
    public java.lang.CharSequence getClientIp() {
      return clientIp;
    }
    
    /** Sets the value of the 'clientIp' field */
    public com.example.crunch.WebEvent.Builder setClientIp(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.clientIp = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'clientIp' field has been set */
    public boolean hasClientIp() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'clientIp' field */
    public com.example.crunch.WebEvent.Builder clearClientIp() {
      clientIp = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'clientId' field */
    public java.lang.CharSequence getClientId() {
      return clientId;
    }
    
    /** Sets the value of the 'clientId' field */
    public com.example.crunch.WebEvent.Builder setClientId(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.clientId = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'clientId' field has been set */
    public boolean hasClientId() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'clientId' field */
    public com.example.crunch.WebEvent.Builder clearClientId() {
      clientId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'userId' field */
    public java.lang.CharSequence getUserId() {
      return userId;
    }
    
    /** Sets the value of the 'userId' field */
    public com.example.crunch.WebEvent.Builder setUserId(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.userId = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'userId' field has been set */
    public boolean hasUserId() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'userId' field */
    public com.example.crunch.WebEvent.Builder clearUserId() {
      userId = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'timeStamp' field */
    public java.lang.CharSequence getTimeStamp() {
      return timeStamp;
    }
    
    /** Sets the value of the 'timeStamp' field */
    public com.example.crunch.WebEvent.Builder setTimeStamp(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.timeStamp = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'timeStamp' field has been set */
    public boolean hasTimeStamp() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'timeStamp' field */
    public com.example.crunch.WebEvent.Builder clearTimeStamp() {
      timeStamp = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'requestType' field */
    public com.example.crunch.Request getRequestType() {
      return requestType;
    }
    
    /** Sets the value of the 'requestType' field */
    public com.example.crunch.WebEvent.Builder setRequestType(com.example.crunch.Request value) {
      validate(fields()[4], value);
      this.requestType = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'requestType' field has been set */
    public boolean hasRequestType() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'requestType' field */
    public com.example.crunch.WebEvent.Builder clearRequestType() {
      requestType = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'requestPage' field */
    public java.lang.CharSequence getRequestPage() {
      return requestPage;
    }
    
    /** Sets the value of the 'requestPage' field */
    public com.example.crunch.WebEvent.Builder setRequestPage(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.requestPage = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'requestPage' field has been set */
    public boolean hasRequestPage() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'requestPage' field */
    public com.example.crunch.WebEvent.Builder clearRequestPage() {
      requestPage = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'httpProtocol' field */
    public java.lang.CharSequence getHttpProtocol() {
      return httpProtocol;
    }
    
    /** Sets the value of the 'httpProtocol' field */
    public com.example.crunch.WebEvent.Builder setHttpProtocol(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.httpProtocol = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'httpProtocol' field has been set */
    public boolean hasHttpProtocol() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'httpProtocol' field */
    public com.example.crunch.WebEvent.Builder clearHttpProtocol() {
      httpProtocol = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'responseCode' field */
    public java.lang.Long getResponseCode() {
      return responseCode;
    }
    
    /** Sets the value of the 'responseCode' field */
    public com.example.crunch.WebEvent.Builder setResponseCode(long value) {
      validate(fields()[7], value);
      this.responseCode = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'responseCode' field has been set */
    public boolean hasResponseCode() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'responseCode' field */
    public com.example.crunch.WebEvent.Builder clearResponseCode() {
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'responseSize' field */
    public java.lang.Long getResponseSize() {
      return responseSize;
    }
    
    /** Sets the value of the 'responseSize' field */
    public com.example.crunch.WebEvent.Builder setResponseSize(long value) {
      validate(fields()[8], value);
      this.responseSize = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'responseSize' field has been set */
    public boolean hasResponseSize() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'responseSize' field */
    public com.example.crunch.WebEvent.Builder clearResponseSize() {
      fieldSetFlags()[8] = false;
      return this;
    }

    /** Gets the value of the 'referrer' field */
    public java.lang.CharSequence getReferrer() {
      return referrer;
    }
    
    /** Sets the value of the 'referrer' field */
    public com.example.crunch.WebEvent.Builder setReferrer(java.lang.CharSequence value) {
      validate(fields()[9], value);
      this.referrer = value;
      fieldSetFlags()[9] = true;
      return this; 
    }
    
    /** Checks whether the 'referrer' field has been set */
    public boolean hasReferrer() {
      return fieldSetFlags()[9];
    }
    
    /** Clears the value of the 'referrer' field */
    public com.example.crunch.WebEvent.Builder clearReferrer() {
      referrer = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    /** Gets the value of the 'agentId' field */
    public java.lang.CharSequence getAgentId() {
      return agentId;
    }
    
    /** Sets the value of the 'agentId' field */
    public com.example.crunch.WebEvent.Builder setAgentId(java.lang.CharSequence value) {
      validate(fields()[10], value);
      this.agentId = value;
      fieldSetFlags()[10] = true;
      return this; 
    }
    
    /** Checks whether the 'agentId' field has been set */
    public boolean hasAgentId() {
      return fieldSetFlags()[10];
    }
    
    /** Clears the value of the 'agentId' field */
    public com.example.crunch.WebEvent.Builder clearAgentId() {
      agentId = null;
      fieldSetFlags()[10] = false;
      return this;
    }

    //@Override
    public WebEvent build() {
      try {
        WebEvent record = new WebEvent();
        record.clientIp = fieldSetFlags()[0] ? this.clientIp : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.clientId = fieldSetFlags()[1] ? this.clientId : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.userId = fieldSetFlags()[2] ? this.userId : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.timeStamp = fieldSetFlags()[3] ? this.timeStamp : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.requestType = fieldSetFlags()[4] ? this.requestType : (com.example.crunch.Request) defaultValue(fields()[4]);
        record.requestPage = fieldSetFlags()[5] ? this.requestPage : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.httpProtocol = fieldSetFlags()[6] ? this.httpProtocol : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.responseCode = fieldSetFlags()[7] ? this.responseCode : (java.lang.Long) defaultValue(fields()[7]);
        record.responseSize = fieldSetFlags()[8] ? this.responseSize : (java.lang.Long) defaultValue(fields()[8]);
        record.referrer = fieldSetFlags()[9] ? this.referrer : (java.lang.CharSequence) defaultValue(fields()[9]);
        record.agentId = fieldSetFlags()[10] ? this.agentId : (java.lang.CharSequence) defaultValue(fields()[10]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
