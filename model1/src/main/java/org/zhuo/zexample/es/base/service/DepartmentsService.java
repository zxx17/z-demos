package org.zhuo.zexample.es.base.service;


import org.zhuo.zexample.es.base.entity.Departments;

/**
 * (Departments)表服务接口
 *
 * @author makejava
 * @since 2024-01-14 15:55:21
 */
public interface DepartmentsService {

    /**
     * 通过ID查询单条数据
     *
     * @param deptNo 主键
     * @return 实例对象
     */
    Departments queryById(String deptNo);


    /**
     * 新增数据
     *
     * @param departments 实例对象
     * @return 实例对象
     */
    Departments insert(Departments departments);

    /**
     * 修改数据
     *
     * @param departments 实例对象
     * @return 实例对象
     */
    Departments update(Departments departments);

    /**
     * 通过主键删除数据
     *
     * @param deptNo 主键
     * @return 是否成功
     */
    boolean deleteById(String deptNo);

}
