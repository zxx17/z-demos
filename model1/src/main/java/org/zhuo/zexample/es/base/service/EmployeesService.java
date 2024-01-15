package org.zhuo.zexample.es.base.service;

import org.zhuo.zexample.es.base.entity.Employees;

/**
 * (Employees)表服务接口
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
public interface EmployeesService {

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    Employees queryById(Integer empNo);


    /**
     * 新增数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    Employees insert(Employees employees);

    /**
     * 修改数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    Employees update(Employees employees);

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer empNo);

}
