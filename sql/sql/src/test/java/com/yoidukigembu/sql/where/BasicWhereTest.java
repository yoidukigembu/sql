package com.yoidukigembu.sql.where;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.yoidukigembu.sql.exception.WhereException;

import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class BasicWhereTest extends TestCase {
	
	private static final String NULL_FORMAT = "value must not be NULL. query:[%s]";
	
	private static final String EMPTY_FORMAT = "value must not be EMPTY. query:[%s]";

	@Test
	public void andIsNotNull() {
		Where where = new BasicWhere();
		where.isNotNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNotNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL AND name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void andIsNotNullAlias() {
		Where where = new BasicWhere();
		where.isNotNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNotNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL AND ALIAS.name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void orIsNotNull() {
		Where where = new BasicWhere();
		where.orIsNotNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNotNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NOT NULL OR name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void orIsNotNullAlias() {
		Where where = new BasicWhere();
		where.orIsNotNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNotNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NOT NULL OR ALIAS.name IS NOT NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	
	@Test
	public void andIsNull() {
		Where where = new BasicWhere();
		where.isNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NULL AND name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void andIsNullAlias() {
		Where where = new BasicWhere();
		where.isNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.isNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL AND ALIAS.name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	@Test
	public void orIsNull() {
		Where where = new BasicWhere();
		where.orIsNull("id");
		where.build((query, params) -> {
			assertEquals("id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNull("name");
		where.build((query, params) -> {
			assertEquals("id IS NULL OR name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	
	@Test
	public void orIsNullAlias() {
		Where where = new BasicWhere();
		where.orIsNull("ALIAS", "id");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
		
		
		where.orIsNull("ALIAS", "name");
		where.build((query, params) -> {
			assertEquals("ALIAS.id IS NULL OR ALIAS.name IS NULL", query.trim());
			assertTrue(params.isEmpty());
		});
	}
	
	@Test
	public void andEq() {
		Where where = new BasicWhere();
		where.eq("id", 1);
		where.build((query, params) -> {
			assertEquals("id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.eq("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id = ? AND name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.eq("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex = ?"), e.getMessage());
		}
	}
	
	@Test
	public void andEqAlias() {
		Where where = new BasicWhere();
		where.eq("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.eq("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ? AND ALIAS.name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.eq("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex = ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orEq() {
		Where where = new BasicWhere();
		where.orEq("id", 1);
		where.build((query, params) -> {
			assertEquals("id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orEq("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id = ? OR name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orEq("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex = ?"), e.getMessage());
		}
	}
	
	@Test
	public void orEqAlias() {
		Where where = new BasicWhere();
		where.orEq("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orEq("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id = ? OR ALIAS.name = ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orEq("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex = ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andNe() {
		Where where = new BasicWhere();
		where.ne("id", 1);
		where.build((query, params) -> {
			assertEquals("id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ne("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id != ? AND name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.ne("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andNeAlias() {
		Where where = new BasicWhere();
		where.ne("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ne("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ? AND ALIAS.name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.ne("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orNe() {
		Where where = new BasicWhere();
		where.orNe("id", 1);
		where.build((query, params) -> {
			assertEquals("id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orNe("name", "hilo");
		where.build((query, params) -> {
			assertEquals("id != ? OR name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orNe("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orNeAlias() {
		Where where = new BasicWhere();
		where.orNe("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orNe("ALIAS", "name", "hilo");
		where.build((query, params) -> {
			assertEquals("ALIAS.id != ? OR ALIAS.name != ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals("hilo", params.get(1));
		});
		
		where.orNe("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex != ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andGt() {
		Where where = new BasicWhere();
		where.gt("id", 1);
		where.build((query, params) -> {
			assertEquals("id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.gt("age", 10);
		where.build((query, params) -> {
			assertEquals("id > ? AND age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.gt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex > ?"), e.getMessage());
		}
	}
	
	@Test
	public void andGtAlias() {
		Where where = new BasicWhere();
		where.gt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.gt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ? AND ALIAS.age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.gt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex > ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orGt() {
		Where where = new BasicWhere();
		where.orGt("id", 1);
		where.build((query, params) -> {
			assertEquals("id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGt("age", 10);
		where.build((query, params) -> {
			assertEquals("id > ? OR age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex > ?"), e.getMessage());
		}
	}
	
	@Test
	public void orGtAlias() {
		Where where = new BasicWhere();
		where.orGt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id > ? OR ALIAS.age > ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex > ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andGe() {
		Where where = new BasicWhere();
		where.ge("id", 1);
		where.build((query, params) -> {
			assertEquals("id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ge("age", 10);
		where.build((query, params) -> {
			assertEquals("id >= ? AND age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.ge("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex >= ?"), e.getMessage());
		}
	}
	
	@Test
	public void andGeAlias() {
		Where where = new BasicWhere();
		where.ge("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.ge("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ? AND ALIAS.age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.ge("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex >= ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orGe() {
		Where where = new BasicWhere();
		where.orGe("id", 1);
		where.build((query, params) -> {
			assertEquals("id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGe("age", 10);
		where.build((query, params) -> {
			assertEquals("id >= ? OR age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGe("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex >= ?"), e.getMessage());
		}
	}
	
	@Test
	public void orGeAlias() {
		Where where = new BasicWhere();
		where.orGe("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orGe("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id >= ? OR ALIAS.age >= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orGe("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex >= ?"), e.getMessage());
		}
	}

	

	@Test
	public void andLt() {
		Where where = new BasicWhere();
		where.lt("id", 1);
		where.build((query, params) -> {
			assertEquals("id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.lt("age", 10);
		where.build((query, params) -> {
			assertEquals("id < ? AND age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.lt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex < ?"), e.getMessage());
		}
	}
	
	@Test
	public void andLtAlias() {
		Where where = new BasicWhere();
		where.lt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.lt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ? AND ALIAS.age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.lt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex < ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orLt() {
		Where where = new BasicWhere();
		where.orLt("id", 1);
		where.build((query, params) -> {
			assertEquals("id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orLt("age", 10);
		where.build((query, params) -> {
			assertEquals("id < ? OR age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orLt("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex < ?"), e.getMessage());
		}
	}
	
	@Test
	public void orLtAlias() {
		Where where = new BasicWhere();
		where.orLt("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orLt("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id < ? OR ALIAS.age < ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orLt("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex < ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andLe() {
		Where where = new BasicWhere();
		where.le("id", 1);
		where.build((query, params) -> {
			assertEquals("id <= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.le("age", 10);
		where.build((query, params) -> {
			assertEquals("id <= ? AND age <= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.le("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex <= ?"), e.getMessage());
		}
	}
	
	@Test
	public void andLeAlias() {
		Where where = new BasicWhere();
		where.le("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id <= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.le("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id <= ? AND ALIAS.age <= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.le("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex <= ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orLe() {
		Where where = new BasicWhere();
		where.orLe("id", 1);
		where.build((query, params) -> {
			assertEquals("id <= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orLe("age", 10);
		where.build((query, params) -> {
			assertEquals("id <= ? OR age <= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orLe("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex <= ?"), e.getMessage());
		}
	}
	
	@Test
	public void orLeAlias() {
		Where where = new BasicWhere();
		where.orLe("ALIAS", "id", 1);
		where.build((query, params) -> {
			assertEquals("ALIAS.id <= ?", query.trim());
			assertEquals(1, params.size());
			assertEquals(1, params.get(0));
		});
		
		
		where.orLe("ALIAS", "age", 10);
		where.build((query, params) -> {
			assertEquals("ALIAS.id <= ? OR ALIAS.age <= ?", query.trim());
			assertEquals(2, params.size());
			assertEquals(1, params.get(0));
			assertEquals(10, params.get(1));
		});
		
		where.orLe("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex <= ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andIn() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.in("id", idList);
		where.build((query, params) -> {
			assertEquals("id IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.in("age", ageList);
		where.build((query, params) -> {
			assertEquals("id IN (?, ?, ?) AND age IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.in("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void andInEmpty() {
		try {
			Where where = new BasicWhere();
			where.in("ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex IN ()"), e.getMessage());
		}
	}
	
	
	@Test
	public void andInAlias() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.in("ALIAS", "id", idList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.in("ALIAS", "age", ageList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id IN (?, ?, ?) AND ALIAS.age IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.in("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void andInAliasEmpty() {
		try {
			Where where = new BasicWhere();
			where.in("ALIAS", "ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void orIn() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.orIn("id", idList);
		where.build((query, params) -> {
			assertEquals("id IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.orIn("age", ageList);
		where.build((query, params) -> {
			assertEquals("id IN (?, ?, ?) OR age IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.orIn("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void orInEmpty() {
		try {
			Where where = new BasicWhere();
			where.orIn("ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex IN ()"), e.getMessage());
		}
	}
	
	
	@Test
	public void orInAlias() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.orIn("ALIAS", "id", idList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.orIn("ALIAS", "age", ageList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id IN (?, ?, ?) OR ALIAS.age IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.orIn("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void orInAliasEmpty() {
		try {
			Where where = new BasicWhere();
			where.orIn("ALIAS", "ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex IN ()"), e.getMessage());
		}
	}
	
	
	
	@Test
	public void andNotIn() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.notIn("id", idList);
		where.build((query, params) -> {
			assertEquals("id NOT IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.notIn("age", ageList);
		where.build((query, params) -> {
			assertEquals("id NOT IN (?, ?, ?) AND age NOT IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.notIn("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex NOT IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void andNotInEmpty() {
		try {
			Where where = new BasicWhere();
			where.notIn("ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex NOT IN ()"), e.getMessage());
		}
	}
	
	
	@Test
	public void andNotInAlias() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.notIn("ALIAS", "id", idList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id NOT IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.notIn("ALIAS", "age", ageList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id NOT IN (?, ?, ?) AND ALIAS.age NOT IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.notIn("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex NOT IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void andNotInAliasEmpty() {
		try {
			Where where = new BasicWhere();
			where.notIn("ALIAS", "ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex NOT IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void orNotIn() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.orNotIn("id", idList);
		where.build((query, params) -> {
			assertEquals("id NOT IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.orNotIn("age", ageList);
		where.build((query, params) -> {
			assertEquals("id NOT IN (?, ?, ?) OR age NOT IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.orNotIn("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex NOT IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void orNotInEmpty() {
		try {
			Where where = new BasicWhere();
			where.orNotIn("ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ex NOT IN ()"), e.getMessage());
		}
	}
	
	
	@Test
	public void orNotInAlias() {
		List<Integer> idList = Arrays.asList(5, 10, 2);
		Where where = new BasicWhere();
		where.orNotIn("ALIAS", "id", idList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id NOT IN (?, ?, ?)", query.trim());
			assertEquals(3, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
		});
		
		
		List<Long> ageList = Arrays.asList(30l, 40l, 20l, 50l);
		where.orNotIn("ALIAS", "age", ageList);
		where.build((query, params) -> {
			assertEquals("ALIAS.id NOT IN (?, ?, ?) OR ALIAS.age NOT IN (?, ?, ?, ?)", query.trim());
			assertEquals(7, params.size());
			assertEquals(5, params.get(0));
			assertEquals(10, params.get(1));
			assertEquals(2, params.get(2));
			assertEquals(30l, params.get(3));
			assertEquals(40l, params.get(4));
			assertEquals(20l, params.get(5));
			assertEquals(50l, params.get(6));
		});
		
		where.orNotIn("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex NOT IN ()"), e.getMessage());
		}
	}
	
	@Test
	public void orNotInAliasEmpty() {
		try {
			Where where = new BasicWhere();
			where.orNotIn("ALIAS", "ex", new ArrayList<>());
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(EMPTY_FORMAT, "ALIAS.ex NOT IN ()"), e.getMessage());
		}
	}
	
	
	@Test
	public void andBeginWith() {
		Where where = new BasicWhere();
		where.beginWith("name", "h");
		where.build((query, params) -> {
			assertEquals("name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("h%", params.get(0));
		});
		
		
		where.beginWith("name", "i");
		where.build((query, params) -> {
			assertEquals("name LIKE ? AND name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("h%", params.get(0));
			assertEquals("i%", params.get(1));
		});
		
		where.beginWith("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex LIKE ?"), e.getMessage());
		}
	}
	
	@Test
	public void andBeginWithAlias() {
		Where where = new BasicWhere();
		where.beginWith("ALIAS", "name", "h");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("h%", params.get(0));
		});
		
		
		where.beginWith("ALIAS", "name", "i");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ? AND ALIAS.name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("h%", params.get(0));
			assertEquals("i%", params.get(1));
		});
		
		where.beginWith("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex LIKE ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orBeginWith() {
		Where where = new BasicWhere();
		where.orBeginWith("name", "h");
		where.build((query, params) -> {
			assertEquals("name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("h%", params.get(0));
		});
		
		
		where.orBeginWith("name", "i");
		where.build((query, params) -> {
			assertEquals("name LIKE ? OR name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("h%", params.get(0));
			assertEquals("i%", params.get(1));
		});
		
		where.orBeginWith("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex LIKE ?"), e.getMessage());
		}
	}
	
	@Test
	public void orBeginWithAlias() {
		Where where = new BasicWhere();
		where.orBeginWith("ALIAS", "name", "h");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("h%", params.get(0));
		});
		
		
		where.orBeginWith("ALIAS", "name", "i");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ? OR ALIAS.name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("h%", params.get(0));
			assertEquals("i%", params.get(1));
		});
		
		where.orBeginWith("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex LIKE ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andEndsWith() {
		Where where = new BasicWhere();
		where.endsWith("name", "h");
		where.build((query, params) -> {
			assertEquals("name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h", params.get(0));
		});
		
		
		where.endsWith("name", "i");
		where.build((query, params) -> {
			assertEquals("name LIKE ? AND name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h", params.get(0));
			assertEquals("%i", params.get(1));
		});
		
		where.endsWith("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex LIKE ?"), e.getMessage());
		}
	}
	
	@Test
	public void andEndsWithAlias() {
		Where where = new BasicWhere();
		where.endsWith("ALIAS", "name", "h");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h", params.get(0));
		});
		
		
		where.endsWith("ALIAS", "name", "i");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ? AND ALIAS.name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h", params.get(0));
			assertEquals("%i", params.get(1));
		});
		
		where.endsWith("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex LIKE ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orEndsWith() {
		Where where = new BasicWhere();
		where.orEndsWith("name", "h");
		where.build((query, params) -> {
			assertEquals("name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h", params.get(0));
		});
		
		
		where.orEndsWith("name", "i");
		where.build((query, params) -> {
			assertEquals("name LIKE ? OR name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h", params.get(0));
			assertEquals("%i", params.get(1));
		});
		
		where.orEndsWith("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex LIKE ?"), e.getMessage());
		}
	}
	
	@Test
	public void orEndsWithAlias() {
		Where where = new BasicWhere();
		where.orEndsWith("ALIAS", "name", "h");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h", params.get(0));
		});
		
		
		where.orEndsWith("ALIAS", "name", "i");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ? OR ALIAS.name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h", params.get(0));
			assertEquals("%i", params.get(1));
		});
		
		where.orEndsWith("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex LIKE ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void andContains() {
		Where where = new BasicWhere();
		where.contains("name", "h");
		where.build((query, params) -> {
			assertEquals("name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h%", params.get(0));
		});
		
		
		where.contains("name", "i");
		where.build((query, params) -> {
			assertEquals("name LIKE ? AND name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h%", params.get(0));
			assertEquals("%i%", params.get(1));
		});
		
		where.contains("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex LIKE ?"), e.getMessage());
		}
	}
	
	@Test
	public void andContainsAlias() {
		Where where = new BasicWhere();
		where.contains("ALIAS", "name", "h");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h%", params.get(0));
		});
		
		
		where.contains("ALIAS", "name", "i");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ? AND ALIAS.name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h%", params.get(0));
			assertEquals("%i%", params.get(1));
		});
		
		where.contains("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex LIKE ?"), e.getMessage());
		}
	}
	
	
	@Test
	public void orContains() {
		Where where = new BasicWhere();
		where.orContains("name", "h");
		where.build((query, params) -> {
			assertEquals("name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h%", params.get(0));
		});
		
		
		where.orContains("name", "i");
		where.build((query, params) -> {
			assertEquals("name LIKE ? OR name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h%", params.get(0));
			assertEquals("%i%", params.get(1));
		});
		
		where.orContains("ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ex LIKE ?"), e.getMessage());
		}
	}
	
	@Test
	public void orContainsAlias() {
		Where where = new BasicWhere();
		where.orContains("ALIAS", "name", "h");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ?", query.trim());
			assertEquals(1, params.size());
			assertEquals("%h%", params.get(0));
		});
		
		
		where.orContains("ALIAS", "name", "i");
		where.build((query, params) -> {
			assertEquals("ALIAS.name LIKE ? OR ALIAS.name LIKE ?", query.trim());
			assertEquals(2, params.size());
			assertEquals("%h%", params.get(0));
			assertEquals("%i%", params.get(1));
		});
		
		where.orContains("ALIAS", "ex", null);
		try {
			where.build((query, params) -> {});
			fail("WhereException must be occured");
		} catch (WhereException e) {
			assertEquals(String.format(NULL_FORMAT, "ALIAS.ex LIKE ?"), e.getMessage());
		}
	}
}
