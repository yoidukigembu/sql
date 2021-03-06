package com.yoidukigembu.sql.select.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.yoidukigembu.sql.orderBy.OrderBy;
import com.yoidukigembu.sql.select.Select;
import com.yoidukigembu.sql.where.Where;

public class SelectImpl<T> implements Select<T> {

	/** スキーマ */
	private final Optional<String> schema;
	
	/** エイリアス */
	private final Optional<String> alias;
	
	/** テーブル名 */
	private final String tableName;
	
	/** セレクトするカラムリスト */
	private List<String> columnList;
	
	/** 検索条件 */
	private Optional<Where> where = Optional.empty();
	
	/** limit */
	private Optional<Integer> limit = Optional.empty();
	
	/** offset */
	private Optional<Integer> offset = Optional.empty();
	
	/** GROUP BY のリスト */
	private Optional<List<String>> groupByList = Optional.empty();
	
	/** ORDER BY */
	private Optional<CharSequence> orderBy = Optional.empty();

	private List<Object> params;
	
	public SelectImpl(String schema, String alias, String tableName) {
		this.schema = Optional.ofNullable(schema);
		this.alias = Optional.ofNullable(alias);
		this.tableName = tableName;
	}

	public Optional<String> getSchema() {
		return schema;
	}

	public String getAlias() {
		return alias.orElse("");
	}
	
	public String getTableName() {
		return tableName;
	}

	@Override
	public Select<T> columns(String... columns) {
		return columns(Arrays.asList(columns));
	}

	@Override
	public Select<T> columns(List<String> columns) {
		this.columnList = columns;
		return this;
	}

	@Override
	public Select<T> where(Where where) {
		this.where = Optional.of(where);
		return this;
	}

	@Override
	public Select<T> limit(int limit) {
		this.limit = Optional.of(limit);
		return this;
	}

	@Override
	public Select<T> offset(int offset) {
		this.offset = Optional.of(offset);
		return this;
	}

	@Override
	public Select<T> groupBy(String... columns) {
		return groupBy(Arrays.asList(columns));
	}

	@Override
	public Select<T> groupBy(List<String> columns) {
		this.groupByList = Optional.of(columns);
		return this;
	}

	@Override
	public Select<T> orderBy(OrderBy orderBy) {
		return orderBy(orderBy.getOrder());
	}

	@Override
	public Select<T> orderBy(CharSequence orderBy) {
		this.orderBy = Optional.ofNullable(orderBy);
		return this;
	}
	
	
	@Override
	public <RESULT> RESULT generate(QueryGenerator<RESULT> generator) {
		this.params = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder("SELECT ");
		sql.append(createColumn())
			.append(" FROM ");
		schema.filter(s -> StringUtils.isNotBlank(s))
			.map(s -> s.concat("."))
				.ifPresent(s -> sql.append(s));
		sql.append(tableName);
		
		alias.ifPresent(a -> sql.append(" ").append(a));
		
		addWhere(sql);
		
		addGroupBy(sql);
		
		addOrderBy(sql);
		
		addLimit(sql);
		
		addOffset(sql);
		
		return generator.generate(sql.toString(), params);
	}
	
	@Override
	public Long generateCount(QueryGenerator<Long> generator) {
		return this.generate((sql, params) -> {
			StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM (");
			sb.append(sql)
				.append(") _C");
			
			return generator.generate(sb.toString(), params);
		});
		
	}
	
	/**
	 * カラムの作成
	 * @return
	 */
	private CharSequence createColumn() {
		if (columnList == null || columnList.isEmpty()) {
			return alias(alias, "*");
		}
		
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (String column : columnList) {
			if (index++ > 0) {
				sb.append(", ");
			}
			sb.append(alias(alias, column));
		}
		
		return sb;
	}
	
	private void addWhere(StringBuilder sql) {
		where.ifPresent(w -> {
			w.build((query, params) -> {
				sql.append(" WHERE ").append(query);
				SelectImpl.this.params = params;
			});
		});
	}
	
	private void addGroupBy(StringBuilder sql) {
		groupByList.ifPresent(list -> {
			sql.append(" GROUP BY ");
			int index = 0;
			for (String group : list) {
				if (index++ > 0) {
					sql.append(", ");
				}
				sql.append(group);
			}
		});
	}
	
	private void addOrderBy(StringBuilder sql) {
		orderBy.ifPresent(order -> sql.append(" ORDER BY ").append(order));
	}
	
	private void addLimit(StringBuilder sql) {
		limit.ifPresent(limit -> sql.append(" LIMIT ").append(limit));
	}
	
	private void addOffset(StringBuilder sql) {
		offset.ifPresent(offset -> sql.append(" OFFSET ").append(offset));
	}
	
}
