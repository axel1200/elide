/*
 * Copyright 2020, Yahoo Inc.
 * Licensed under the Apache License, Version 2.0
 * See LICENSE file in project root for terms.
 */
package com.yahoo.elide.async.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.mockito.Mockito;

import com.yahoo.elide.Elide;


public class AsyncCleanerServiceTest {

	@Test
	public void testCleanerSet() {
		Elide elide = Mockito.mock(Elide.class);
		AsyncCleanerService service = Mockito.spy(new AsyncCleanerService(elide, 5, 5));
		assertNotNull(service.getCleaner());
	}
}
