package com.yahoo.elide.async.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.yahoo.elide.Elide;
import com.yahoo.elide.ElideSettings;
import com.yahoo.elide.async.models.AsyncQuery;
import com.yahoo.elide.async.models.AsyncQueryResult;
import com.yahoo.elide.core.DataStore;
import com.yahoo.elide.core.DataStoreTransaction;

public class AsyncDbUtilTest {

	private Elide elide;
	private DataStore dataStore;
	private DataStoreTransaction tx;
	private ElideSettings settings;

	@BeforeEach
	public void setupMocks() {
		elide = Mockito.mock(Elide.class);
		dataStore = Mockito.mock(DataStore.class);
		tx = Mockito.mock(DataStoreTransaction.class);
		settings = Mockito.mock(ElideSettings.class);
		Mockito.when(elide.getDataStore()).thenReturn(dataStore);
		Mockito.when(dataStore.beginTransaction()).thenReturn(tx);
		Mockito.when(elide.getElideSettings()).thenReturn(settings);
	}

	@Test
	public void testGetInstance() {
		assertNotNull(AsyncDbUtil.getInstance(elide));
	}

//	@Test
//	public void testUpdateAsyncQueryStatus() {
//		AsyncQuery asyncQuery = new AsyncQuery();
//		UUID id = UUID.fromString("ba31ca4e-ed8f-4be0-a0f3-12088fa9263d");
//		Mockito.when(tx.loadObject(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(asyncQuery);
//		AsyncDbUtil dbUtil = AsyncDbUtil.getInstance(elide);
//		AsyncQuery result = dbUtil.updateAsyncQuery(id, (asyncQueryObj) -> {
//			asyncQueryObj.setStatus(QueryStatus.QUEUED);
//		});
//
//		assertEquals(result.getStatus(), QueryStatus.QUEUED);
//		Mockito.verify(tx, Mockito.times(1)).save(Mockito.isA(AsyncQuery.class), Mockito.isA(RequestScope.class));
//	}

	@Test
	public void testUpdateAsyncQueryResult() {
		AsyncQuery asyncQuery = new AsyncQuery();
		AsyncQueryResult asyncQueryResult = new AsyncQueryResult();
		UUID id = UUID.fromString("ba31ca4e-ed8f-4be0-a0f3-12088fa9263d");
		asyncQueryResult.setId(id);
		asyncQueryResult.setStatus(200);
		Mockito.when(tx.loadObject(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(asyncQuery);
		AsyncDbUtil dbUtil = AsyncDbUtil.getInstance(elide);
		AsyncQuery result = dbUtil.updateAsyncQuery(id, (asyncQueryObj) -> {
			asyncQueryObj.setResult(asyncQueryResult);
		});

		assertEquals(result.getResult().getId(), id);
		assertEquals(result.getResult().getStatus(), 200);
	}

	@Test
	public void testDeleteAsyncQueryAndResult() {
		AsyncQuery asyncQuery = new AsyncQuery();
		UUID id = UUID.fromString("ba31ca4e-ed8f-4be0-a0f3-12088fa9263d");
		Mockito.when(tx.loadObject(Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(asyncQuery);
		AsyncDbUtil dbUtil = AsyncDbUtil.getInstance(elide);
		dbUtil.deleteAsyncQueryAndResult(id);
		//Mockito.verify(tx, Mockito.times(1)).delete(Mockito.any(), Mockito.any());
	}

}
