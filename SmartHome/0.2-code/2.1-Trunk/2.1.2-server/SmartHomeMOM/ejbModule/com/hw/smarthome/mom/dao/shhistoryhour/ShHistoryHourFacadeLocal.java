package com.hw.smarthome.mom.dao.shhistoryhour;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for ShHistoryHourFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface ShHistoryHourFacadeLocal {
	/**
	 * Perform an initial save of a previously unsaved
	 * ShHistoryHour entity. All subsequent persist actions of
	 * this entity should use the #update() method.
	 * 
	 * @param entity
	 *            ShHistoryHour entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ShHistoryHour entity);

	/**
	 * Delete a persistent ShHistoryHour entity.
	 * 
	 * @param entity
	 *            ShHistoryHour entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ShHistoryHour entity);

	/**
	 * Persist a previously saved ShHistoryHour entity and return
	 * it or a copy of it to the sender. A copy of the
	 * ShHistoryHour entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            ShHistoryHour entity to update
	 * @return ShHistoryHour the persisted ShHistoryHour entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ShHistoryHour update(ShHistoryHour entity);

	public ShHistoryHour findById(String id);

	/**
	 * Find all ShHistoryHour entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ShHistoryHour property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0]
	 *            specifies the the row index in the query
	 *            result-set to begin collecting the results.
	 *            rowStartIdxAndCount[1] specifies the the
	 *            maximum count of results to return.
	 * @return List<ShHistoryHour> found by query
	 */
	public List<ShHistoryHour> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);
	public List<ShHistoryHour> findByProperty(String propertyName1,
			Object value1,String propertyName2,
			Object value2, int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa002(Object ma002,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa003(Object ma003,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa004(Object ma004,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa005(Object ma005,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa007(Object ma007,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa009(Object ma009,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa011(Object ma011,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa013(Object ma013,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa015(Object ma015,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa017(Object ma017,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa019(Object ma019,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa021(Object ma021,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa023(Object ma023,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa025(Object ma025,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa027(Object ma027,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa029(Object ma029,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa031(Object ma031,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa033(Object ma033,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa035(Object ma035,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa037(Object ma037,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa039(Object ma039,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa041(Object ma041,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa043(Object ma043,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa045(Object ma045,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa047(Object ma047,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa049(Object ma049,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa051(Object ma051,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa053(Object ma053,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa054(Object ma054,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa055(Object ma055,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa056(Object ma056,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa057(Object ma057,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa058(Object ma058,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa059(Object ma059,
			int... rowStartIdxAndCount);

	public List<ShHistoryHour> findByMa060(Object ma060,
			int... rowStartIdxAndCount);

	/**
	 * Find all ShHistoryHour entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0]
	 *            specifies the the row index in the query
	 *            result-set to begin collecting the results.
	 *            rowStartIdxAndCount[1] specifies the the
	 *            maximum count of results to return.
	 * @return List<ShHistoryHour> all ShHistoryHour entities
	 */
	public List<ShHistoryHour> findAll(int... rowStartIdxAndCount);
}