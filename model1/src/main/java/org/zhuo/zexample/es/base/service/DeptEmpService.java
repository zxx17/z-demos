package org.zhuo.zexample.es.base.service;


import org.zhuo.zexample.es.base.entity.DeptEmp;

/**
 * (DeptEmp)表服务接口
 *
 * @author makejava
 * @since 2024-01-14 15:55:22
 */
public interface DeptEmpService {

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    DeptEmp queryById(Integer empNo);


    /**
     * 新增数据
     *
     * @param deptEmp 实例对象
     * @return 实例对象
     */
    DeptEmp insert(DeptEmp deptEmp);

    /**
     * 修改数据
     *
     * @param deptEmp 实例对象
     * @return 实例对象
     */
    DeptEmp update(DeptEmp deptEmp);

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer empNo);

}
