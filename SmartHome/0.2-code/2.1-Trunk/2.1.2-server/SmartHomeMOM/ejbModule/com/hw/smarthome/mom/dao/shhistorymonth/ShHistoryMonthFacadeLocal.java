package com.hw.smarthome.mom.dao.shhistorymonth;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for ShHistoryMonthFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface ShHistoryMonthFacadeLocal {
	/**
	 * Perform an initial save of a previously unsaved
	 * ShHistoryMonth entity. All subsequent persist actions of
	 * this entity should use the #update() method.
	 * 
	 * @param entity
	 *            ShHistoryMonth entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ShHistoryMonth entity);

	/**
	 * Delete a persistent ShHistoryMonth entity.
	 * 
	 * @param entity
	 *            ShHistoryMonth entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ShHistoryMonth entity);

	/**
	 * Persist a previously saved ShHistoryMonth entity and
	 * return it or a copy of it to the sender. A copy of the
	 * ShHistoryMonth entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            ShHistoryMonth entity to update
	 * @return ShHistoryMonth the persisted ShHistoryMonth entity
	 *         instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ShHistoryMonth update(ShHistoryMonth entity);

	public ShHistoryMonth findById(String id);

	/**
	 * Find all ShHistoryMonth entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ShHistoryMonth property to
	 *            query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0]
	 *            specifies the the row index in the query
	 *            result-set to begin collecting the results.
	 *            rowStartIdxAndCount[1] specifies the the
	 *            maximum count of results to return.
	 * @return List<ShHistoryMonth> found by query
	 */
	public List<ShHistoryMonth> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);
	public List<ShHistoryMonth> findByProperty(String propertyName1,
			Object value1, String propertyName2,
			Object value2,int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa002(Object ma002,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa003(Object ma003,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa004(Object ma004,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa005(Object ma005,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa007(Object ma007,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa009(Object ma009,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa011(Object ma011,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa013(Object ma013,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa015(Object ma015,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa017(Object ma017,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa019(Object ma019,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa021(Object ma021,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa023(Object ma023,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa025(Object ma025,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa027(Object ma027,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa029(Object ma029,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa031(Object ma031,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa033(Object ma033,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa035(Object ma035,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa037(Object ma037,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa039(Object ma039,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa041(Object ma041,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa043(Object ma043,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa045(Object ma045,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa047(Object ma047,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa049(Object ma049,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa051(Object ma051,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa053(Object ma053,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa055(Object ma055,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa057(Object ma057,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa059(Object ma059,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa061(Object ma061,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa063(Object ma063,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa065(Object ma065,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa067(Object ma067,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa068(Object ma068,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa069(Object ma069,
			int... rowStartIdxAndCount);

	public List<ShHistoryMonth> findByMa070(Object ma070,
			int... rowStartIdxAndCount);

	/**
	 * Find all ShHistoryMonth entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0]
	 *            specifies the the row index in the query
	 *            result-set to begin collecting the results.
	 *            rowStartIdxAndCount[1] specifies the the
	 *            maximum count of results to return.
	 * @return List<ShHistoryMonth> all ShHistoryMonth entities
	 */
	public List<ShHistoryMonth> findAll(int... rowStartIdxAndCount);
}