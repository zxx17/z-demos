package org.zhuo.zexample.es.base.service.impl;

import org.springframework.stereotype.Service;
import org.zhuo.zexample.es.base.entity.Employees;
import org.zhuo.zexample.es.base.mapper.EmployeesDao;
import org.zhuo.zexample.es.base.service.EmployeesService;

import javax.annotation.Resource;

/**
 * (Employees)表服务实现类
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
@Service("employeesService")
public class EmployeesServiceImpl implements EmployeesService {
    @Resource
    private EmployeesDao employeesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    @Override
    public Employees queryById(Integer empNo) {
        return this.employeesDao.queryById(empNo);
    }


    /**
     * 新增数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    @Override
    public Employees insert(Employees employees) {
        this.employeesDao.insert(employees);
        return employees;
    }

    /**
     * 修改数据
     *
     * @param employees 实例对象
     * @return 实例对象
     */
    @Override
    public Employees update(Employees employees) {
        this.employeesDao.update(employees);
        return this.queryById(employees.getEmpNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer empNo) {
        return this.employeesDao.deleteById(empNo) > 0;
    }
}
