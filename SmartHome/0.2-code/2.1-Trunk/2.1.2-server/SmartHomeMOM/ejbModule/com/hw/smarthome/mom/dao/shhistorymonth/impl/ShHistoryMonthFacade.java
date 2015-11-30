package com.hw.smarthome.mom.dao.shhistorymonth.impl;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonth;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonthFacadeLocal;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonthFacadeRemote;
import com.hw.util.LogUtil;

/**
 * Facade for entity ShHistoryMonth.
 * 
 * @see com.hw.smarthome.mom.dao.ShHistoryMonth
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class ShHistoryMonthFacade implements ShHistoryMonthFacadeLocal,
		ShHistoryMonthFacadeRemote {
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
	public static final String MA055 = "ma055";
	public static final String MA057 = "ma057";
	public static final String MA059 = "ma059";
	public static final String MA061 = "ma061";
	public static final String MA063 = "ma063";
	public static final String MA065 = "ma065";
	public static final String MA067 = "ma067";
	public static final String MA068 = "ma068";
	public static final String MA069 = "ma069";
	public static final String MA070 = "ma070";

	@PersistenceContext
	private EntityManager entityManager;

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
	public void save(ShHistoryMonth entity) {
		LogUtil.log("saving ShHistoryMonth instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ShHistoryMonth entity.
	 * 
	 * @param entity
	 *            ShHistoryMonth entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ShHistoryMonth entity) {
		LogUtil.log("deleting ShHistoryMonth instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(ShHistoryMonth.class,
					entity.getMa001());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	public ShHistoryMonth update(ShHistoryMonth entity) {
		LogUtil.log("updating ShHistoryMonth instance", Level.INFO, null);
		try {
			ShHistoryMonth result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ShHistoryMonth findById(String id) {
		LogUtil.log("finding ShHistoryMonth instance with id: " + id,
				Level.INFO, null);
		try {
			ShHistoryMonth instance = entityManager.find(ShHistoryMonth.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            maximum number of results to return.
	 * @return List<ShHistoryMonth> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ShHistoryMonth> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding ShHistoryMonth instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ShHistoryMonth model where model."
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
	public List<ShHistoryMonth> findByProperty(String propertyName1,
			Object value1, String propertyName2,
			Object value2,int... rowStartIdxAndCount) {
		LogUtil.log("finding ShHistoryMonth instance with property: "
				+ propertyName1 + ", value: " + value1, Level.INFO, null);
		try {
			final String queryString = "select model from ShHistoryMonth model where model."
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

	public List<ShHistoryMonth> findByMa002(Object ma002,
			int... rowStartIdxAndCount) {
		return findByProperty(MA002, ma002, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa003(Object ma003,
			int... rowStartIdxAndCount) {
		return findByProperty(MA003, ma003, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa004(Object ma004,
			int... rowStartIdxAndCount) {
		return findByProperty(MA004, ma004, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa005(Object ma005,
			int... rowStartIdxAndCount) {
		return findByProperty(MA005, ma005, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa007(Object ma007,
			int... rowStartIdxAndCount) {
		return findByProperty(MA007, ma007, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa009(Object ma009,
			int... rowStartIdxAndCount) {
		return findByProperty(MA009, ma009, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa011(Object ma011,
			int... rowStartIdxAndCount) {
		return findByProperty(MA011, ma011, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa013(Object ma013,
			int... rowStartIdxAndCount) {
		return findByProperty(MA013, ma013, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa015(Object ma015,
			int... rowStartIdxAndCount) {
		return findByProperty(MA015, ma015, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa017(Object ma017,
			int... rowStartIdxAndCount) {
		return findByProperty(MA017, ma017, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa019(Object ma019,
			int... rowStartIdxAndCount) {
		return findByProperty(MA019, ma019, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa021(Object ma021,
			int... rowStartIdxAndCount) {
		return findByProperty(MA021, ma021, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa023(Object ma023,
			int... rowStartIdxAndCount) {
		return findByProperty(MA023, ma023, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa025(Object ma025,
			int... rowStartIdxAndCount) {
		return findByProperty(MA025, ma025, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa027(Object ma027,
			int... rowStartIdxAndCount) {
		return findByProperty(MA027, ma027, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa029(Object ma029,
			int... rowStartIdxAndCount) {
		return findByProperty(MA029, ma029, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa031(Object ma031,
			int... rowStartIdxAndCount) {
		return findByProperty(MA031, ma031, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa033(Object ma033,
			int... rowStartIdxAndCount) {
		return findByProperty(MA033, ma033, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa035(Object ma035,
			int... rowStartIdxAndCount) {
		return findByProperty(MA035, ma035, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa037(Object ma037,
			int... rowStartIdxAndCount) {
		return findByProperty(MA037, ma037, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa039(Object ma039,
			int... rowStartIdxAndCount) {
		return findByProperty(MA039, ma039, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa041(Object ma041,
			int... rowStartIdxAndCount) {
		return findByProperty(MA041, ma041, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa043(Object ma043,
			int... rowStartIdxAndCount) {
		return findByProperty(MA043, ma043, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa045(Object ma045,
			int... rowStartIdxAndCount) {
		return findByProperty(MA045, ma045, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa047(Object ma047,
			int... rowStartIdxAndCount) {
		return findByProperty(MA047, ma047, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa049(Object ma049,
			int... rowStartIdxAndCount) {
		return findByProperty(MA049, ma049, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa051(Object ma051,
			int... rowStartIdxAndCount) {
		return findByProperty(MA051, ma051, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa053(Object ma053,
			int... rowStartIdxAndCount) {
		return findByProperty(MA053, ma053, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa055(Object ma055,
			int... rowStartIdxAndCount) {
		return findByProperty(MA055, ma055, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa057(Object ma057,
			int... rowStartIdxAndCount) {
		return findByProperty(MA057, ma057, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa059(Object ma059,
			int... rowStartIdxAndCount) {
		return findByProperty(MA059, ma059, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa061(Object ma061,
			int... rowStartIdxAndCount) {
		return findByProperty(MA061, ma061, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa063(Object ma063,
			int... rowStartIdxAndCount) {
		return findByProperty(MA063, ma063, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa065(Object ma065,
			int... rowStartIdxAndCount) {
		return findByProperty(MA065, ma065, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa067(Object ma067,
			int... rowStartIdxAndCount) {
		return findByProperty(MA067, ma067, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa068(Object ma068,
			int... rowStartIdxAndCount) {
		return findByProperty(MA068, ma068, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa069(Object ma069,
			int... rowStartIdxAndCount) {
		return findByProperty(MA069, ma069, rowStartIdxAndCount);
	}

	public List<ShHistoryMonth> findByMa070(Object ma070,
			int... rowStartIdxAndCount) {
		return findByProperty(MA070, ma070, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<ShHistoryMonth> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all ShHistoryMonth instances", Level.INFO, null);
		try {
			final String queryString = "select model from ShHistoryMonth model";
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