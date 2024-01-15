package org.zhuo.zexample.es.base.service.impl;

import org.springframework.stereotype.Service;
import org.zhuo.zexample.es.base.entity.Departments;
import org.zhuo.zexample.es.base.mapper.DepartmentsDao;
import org.zhuo.zexample.es.base.service.DepartmentsService;

import javax.annotation.Resource;

/**
 * (Departments)表服务实现类
 *
 * @author makejava
 * @since 2024-01-14 15:55:22
 */
@Service("departmentsService")
public class DepartmentsServiceImpl implements DepartmentsService {
    @Resource
    private DepartmentsDao departmentsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deptNo 主键
     * @return 实例对象
     */
    @Override
    public Departments queryById(String deptNo) {
        return this.departmentsDao.queryById(deptNo);
    }



    /**
     * 新增数据
     *
     * @param departments 实例对象
     * @return 实例对象
     */
    @Override
    public Departments insert(Departments departments) {
        this.departmentsDao.insert(departments);
        return departments;
    }

    /**
     * 修改数据
     *
     * @param departments 实例对象
     * @return 实例对象
     */
    @Override
    public Departments update(Departments departments) {
        this.departmentsDao.update(departments);
        return this.queryById(departments.getDeptNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param deptNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String deptNo) {
        return this.departmentsDao.deleteById(deptNo) > 0;
    }
}
