package org.zhuo.zexample.es.base.service;

import org.zhuo.zexample.es.base.entity.DeptManager;

/**
 * (DeptManager)表服务接口
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
public interface DeptManagerService {

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    DeptManager queryById(Integer empNo);



    /**
     * 新增数据
     *
     * @param deptManager 实例对象
     * @return 实例对象
     */
    DeptManager insert(DeptManager deptManager);

    /**
     * 修改数据
     *
     * @param deptManager 实例对象
     * @return 实例对象
     */
    DeptManager update(DeptManager deptManager);

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer empNo);

}
