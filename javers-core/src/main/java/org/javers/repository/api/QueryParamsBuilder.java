package org.javers.repository.api;

import org.joda.time.LocalDateTime;

/**
 * @author michal wesolowski
 */
public class QueryParamsBuilder {
  private int limit;
  private LocalDateTime from;
  private LocalDateTime to;

  private QueryParamsBuilder(int limit) {
    this.limit = limit;
  };

  /**
   * Initializes builder with given limit
   *
   * @param limit choose reasonable limit (number of objects to fetch),
   *              production database could contain more records than you expect
   */
  public static QueryParamsBuilder withLimit(int limit) {
    checkLimit(limit);
    return new QueryParamsBuilder(limit);
  }

  /**
   * Initializes builder with param values from given queryParams instance
   *
   * @param queryParams instance to initialize builder param values from
   */
  public static QueryParamsBuilder initializeWith(QueryParams queryParams) {
    if (queryParams == null) {
      throw new IllegalArgumentException("Query params to copy properties from is null.");
    }
    QueryParamsBuilder builder = QueryParamsBuilder.withLimit(queryParams.getLimit());
    if (queryParams.isFromSet()) {
      builder = builder.from(queryParams.getFrom());
    }
    if (queryParams.isToSet()) {
      builder = builder.to(queryParams.getTo());
    }
    return builder;
  }

  /**
   * @param limit choose reasonable limit (number of objects to fetch),
   *              production database could contain more records than you expect
   */
  public QueryParamsBuilder limit(int limit) {
    checkLimit(limit);
    this.limit = limit;
    return this;
  }

  /**
   * @param from limits results to snapshots which was created from the given point in time
   */
  public QueryParamsBuilder from(LocalDateTime from) {
    this.from = from;
    return this;
  }

  /**
   * @param to limits results to snapshots which was created to the given point in time
   */
  public QueryParamsBuilder to(LocalDateTime to) {
    this.to = to;
    return this;
  }

  private static void checkLimit(int limit) {
    if (limit <= 0) {
      throw new IllegalArgumentException("Limit is not a positive number.");
    }
  }

  public QueryParams build() {
    return new QueryParams(limit, from, to);
  }
}