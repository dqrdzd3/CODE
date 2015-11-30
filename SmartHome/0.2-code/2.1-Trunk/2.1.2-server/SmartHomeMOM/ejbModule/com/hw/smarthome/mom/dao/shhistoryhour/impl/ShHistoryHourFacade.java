package com.hw.smarthome.mom.dao.shhistoryhour.impl;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHour;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHourFacadeLocal;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHourFacadeRemote;
import com.hw.util.LogUtil;

/**
 * Facade for entity ShHistoryHour.
 * 
 * @see com.hw.smarthome.mom.dao.ShHistoryHour
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class ShHistoryHourFacade implements ShHistoryHourFacadeLocal,
		ShHistoryHourFacadeRemote {
	// property constants
	public static final String MA002 = "ma002";
	public static final String MA003 = "ma003";
	public static final String MA004 = "ma004";
	public static final String MA005 = "ma005";
	public static final String MA007 = "ma007";
	public static final String MA009 = "ma009";
	public static final String MA011 = "ma011";
	public static final String MA013 = "ma013";
	public static final String MA015 = "ma015";
	public static final String MA017 = "ma017";
	public static final String MA019 = "ma019";
	public static final String MA021 = "ma021";
	public static final String MA023 = "ma023";
	public static final String MA025 = "ma025";
	public static final String MA027 = "ma027";
	public static final String MA029 = "ma029";
	public static final String MA031 = "ma031";
	public static final String MA033 = "ma033";
	public static final String MA035 = "ma035";
	public static final String MA037 = "ma037";
	public static final String MA039 = "ma039";
	public static final String MA041 = "ma041";
	public static final String MA043 = "ma043";
	public static final String MA045 = "ma045";
	public static final String MA047 = "ma047";
	public static final String MA049 = "ma049";
	public static final String MA051 = "ma051";
	public static final String MA053 = "ma053";
	public static final String MA054 = "ma054";
	public static final String MA055 = "ma055";
	public static final String MA056 = "ma056";
	public static final String MA057 = "ma057";
	public static final String MA058 = "ma058";
	public static final String MA059 = "ma059";
	public static final String MA060 = "ma060";

	@PersistenceContext
	private EntityManager entityManager;

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
	public void save(ShHistoryHour entity) {
		LogUtil.log("saving ShHistoryHour instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ShHistoryHour entity.
	 * 
	 * @param entity
	 *            ShHistoryHour entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ShHistoryHour entity) {
		LogUtil.log("deleting ShHistoryHour instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(ShHistoryHour.class,
					entity.getMa001());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	public ShHistoryHour update(ShHistoryHour entity) {
		LogUtil.log("updating ShHistoryHour instance", Level.INFO, null);
		try {
			ShHistoryHour result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ShHistoryHour findById(String id) {
		LogUtil.log("finding ShHistoryHour instance with id: " + id,
				Level.INFO, null);
		try {
			ShHistoryHour instance = entityManager
					.find(ShHistoryHour.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            maximum number of results to return.
	 * @return List<ShHistoryHour> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ShHistoryHour> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding ShHistoryHour instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ShHistoryHour model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ShHistoryHour> findByProperty(String propertyName1,
			Object value1,String propertyName2,
			Object value2, int... rowStartIdxAndCount){
				LogUtil.log("finding ShHistoryHour instance with property: "
						+ propertyName1 + ", value: " + value1, Level.INFO, null);
				try {
					final String queryString = "select model from ShHistoryHour model where model."
							+ propertyName1 + "= :propertyValue1 and model."+propertyName2+"= :propertyValue2";
					Query query = entityManager.createQuery(queryString);
					query.setParameter("propertyValue1", value1);
					query.setParameter("propertyValue2", value2);
					if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
						int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
						if (rowStartIdx > 0) {
							query.setFirstResult(rowStartIdx);
						}

						if (rowStartIdxAndCount.length > 1) {
							int rowCount = Math.max(0, rowStartIdxAndCount[1]);
							if (rowCount > 0) {
								query.setMaxResults(rowCount);
							}
						}
					}
					return query.getResultList();
				} catch (RuntimeException re) {
					LogUtil.log("find by property name failed", Level.SEVERE, re);
					throw re;
				}
			}
	
	public List<ShHistoryHour> findByMa002(Object ma002,
			int... rowStartIdxAndCount) {
		return findByProperty(MA002, ma002, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa003(Object ma003,
			int... rowStartIdxAndCount) {
		return findByProperty(MA003, ma003, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa004(Object ma004,
			int... rowStartIdxAndCount) {
		return findByProperty(MA004, ma004, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa005(Object ma005,
			int... rowStartIdxAndCount) {
		return findByProperty(MA005, ma005, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa007(Object ma007,
			int... rowStartIdxAndCount) {
		return findByProperty(MA007, ma007, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa009(Object ma009,
			int... rowStartIdxAndCount) {
		return findByProperty(MA009, ma009, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa011(Object ma011,
			int... rowStartIdxAndCount) {
		return findByProperty(MA011, ma011, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa013(Object ma013,
			int... rowStartIdxAndCount) {
		return findByProperty(MA013, ma013, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa015(Object ma015,
			int... rowStartIdxAndCount) {
		return findByProperty(MA015, ma015, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa017(Object ma017,
			int... rowStartIdxAndCount) {
		return findByProperty(MA017, ma017, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa019(Object ma019,
			int... rowStartIdxAndCount) {
		return findByProperty(MA019, ma019, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa021(Object ma021,
			int... rowStartIdxAndCount) {
		return findByProperty(MA021, ma021, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa023(Object ma023,
			int... rowStartIdxAndCount) {
		return findByProperty(MA023, ma023, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa025(Object ma025,
			int... rowStartIdxAndCount) {
		return findByProperty(MA025, ma025, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa027(Object ma027,
			int... rowStartIdxAndCount) {
		return findByProperty(MA027, ma027, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa029(Object ma029,
			int... rowStartIdxAndCount) {
		return findByProperty(MA029, ma029, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa031(Object ma031,
			int... rowStartIdxAndCount) {
		return findByProperty(MA031, ma031, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa033(Object ma033,
			int... rowStartIdxAndCount) {
		return findByProperty(MA033, ma033, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa035(Object ma035,
			int... rowStartIdxAndCount) {
		return findByProperty(MA035, ma035, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa037(Object ma037,
			int... rowStartIdxAndCount) {
		return findByProperty(MA037, ma037, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa039(Object ma039,
			int... rowStartIdxAndCount) {
		return findByProperty(MA039, ma039, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa041(Object ma041,
			int... rowStartIdxAndCount) {
		return findByProperty(MA041, ma041, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa043(Object ma043,
			int... rowStartIdxAndCount) {
		return findByProperty(MA043, ma043, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa045(Object ma045,
			int... rowStartIdxAndCount) {
		return findByProperty(MA045, ma045, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa047(Object ma047,
			int... rowStartIdxAndCount) {
		return findByProperty(MA047, ma047, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa049(Object ma049,
			int... rowStartIdxAndCount) {
		return findByProperty(MA049, ma049, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa051(Object ma051,
			int... rowStartIdxAndCount) {
		return findByProperty(MA051, ma051, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa053(Object ma053,
			int... rowStartIdxAndCount) {
		return findByProperty(MA053, ma053, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa054(Object ma054,
			int... rowStartIdxAndCount) {
		return findByProperty(MA054, ma054, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa055(Object ma055,
			int... rowStartIdxAndCount) {
		return findByProperty(MA055, ma055, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa056(Object ma056,
			int... rowStartIdxAndCount) {
		return findByProperty(MA056, ma056, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa057(Object ma057,
			int... rowStartIdxAndCount) {
		return findByProperty(MA057, ma057, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa058(Object ma058,
			int... rowStartIdxAndCount) {
		return findByProperty(MA058, ma058, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa059(Object ma059,
			int... rowStartIdxAndCount) {
		return findByProperty(MA059, ma059, rowStartIdxAndCount);
	}

	public List<ShHistoryHour> findByMa060(Object ma060,
			int... rowStartIdxAndCount) {
		return findByProperty(MA060, ma060, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<ShHistoryHour> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all ShHistoryHour instances", Level.INFO, null);
		try {
			final String queryString = "select model from ShHistoryHour model";
			Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}