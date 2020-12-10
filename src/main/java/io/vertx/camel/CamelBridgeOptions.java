/*
 *  Copyright (c) 2011-2015 The original author or authors
 *  ------------------------------------------------------
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *       The Eclipse Public License is available at
 *       http://www.eclipse.org/legal/epl-v10.html
 *
 *       The Apache License v2.0 is available at
 *       http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */
package io.vertx.camel;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.core.json.JsonObject;
import org.apache.camel.CamelContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Camel bridge configuration.
 *
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
@DataObject
public class CamelBridgeOptions {

  // This class is not a DataObject as it's not polyglot and requires object not serializable from / to Json (Camel
  // context).

  private List<InboundMapping> inbound = new ArrayList<>();
  private List<OutboundMapping> outbound = new ArrayList<>();

  private final CamelContext context;

  /**
   * Creates a new instance of {@link CamelBridgeOptions}.
   *
   * @param context the camel context used by the bridge. Must not be {@code null}, may not be {@code started},
   *                however it should be started when the bridge is started.
   */
  public CamelBridgeOptions(CamelContext context) {
    Objects.requireNonNull(context);
    this.context = context;
  }

  /**
   * Comply with the {@link DataObject} contract, however calling this constructor will always throw.
   *
   * @param json a json object
   * @throws IllegalArgumentException always.
   */
  @GenIgnore
  public CamelBridgeOptions(JsonObject json) {
    throw new IllegalArgumentException("This class is not a DataObject as it's not polyglot and requires object not serializable from / to Json (CamelContext)");
  }

  /**
   * Adds an inbound mapping (Camel to Vert.x).
   *
   * @param mapping the mapping, must not be {@code null}
   * @return the current {@link CamelBridgeOptions}
   */
  public CamelBridgeOptions addInboundMapping(InboundMapping mapping) {
    Objects.requireNonNull(mapping);
    this.inbound.add(mapping);
    return this;
  }

  /**
   * Adds an outbound mapping (Vert.x to Camel).
   *
   * @param mapping the mapping, must not be {@code null}
   * @return the current {@link CamelBridgeOptions}
   */
  public CamelBridgeOptions addOutboundMapping(OutboundMapping mapping) {
    this.outbound.add(mapping);
    return this;
  }

  public CamelContext getCamelContext() {
    return context;
  }

  public List<InboundMapping> getInboundMappings() {
    return inbound;
  }

  public List<OutboundMapping> getOutboundMappings() {
    return outbound;
  }
}
