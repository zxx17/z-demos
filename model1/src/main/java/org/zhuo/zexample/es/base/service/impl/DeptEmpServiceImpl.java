package org.zhuo.zexample.es.base.service.impl;

import org.springframework.stereotype.Service;
import org.zhuo.zexample.es.base.entity.DeptEmp;
import org.zhuo.zexample.es.base.mapper.DeptEmpDao;
import org.zhuo.zexample.es.base.service.DeptEmpService;

import javax.annotation.Resource;

/**
 * (DeptEmp)表服务实现类
 *
 * @author makejava
 * @since 2024-01-14 15:55:22
 */
@Service("deptEmpService")
public class DeptEmpServiceImpl implements DeptEmpService {
    @Resource
    private DeptEmpDao deptEmpDao;

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    @Override
    public DeptEmp queryById(Integer empNo) {
        return this.deptEmpDao.queryById(empNo);
    }


    /**
     * 新增数据
     *
     * @param deptEmp 实例对象
     * @return 实例对象
     */
    @Override
    public DeptEmp insert(DeptEmp deptEmp) {
        this.deptEmpDao.insert(deptEmp);
        return deptEmp;
    }

    /**
     * 修改数据
     *
     * @param deptEmp 实例对象
     * @return 实例对象
     */
    @Override
    public DeptEmp update(DeptEmp deptEmp) {
        this.deptEmpDao.update(deptEmp);
        return this.queryById(deptEmp.getEmpNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer empNo) {
        return this.deptEmpDao.deleteById(empNo) > 0;
    }
}
