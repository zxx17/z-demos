package org.zhuo.zexample.es.base.service.impl;

import org.springframework.stereotype.Service;
import org.zhuo.zexample.es.base.entity.DeptManager;
import org.zhuo.zexample.es.base.service.DeptManagerService;
import org.zhuo.zexample.es.base.mapper.DeptManagerDao;

import javax.annotation.Resource;

/**
 * (DeptManager)表服务实现类
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
@Service("deptManagerService")
public class DeptManagerServiceImpl implements DeptManagerService {
    @Resource
    private DeptManagerDao deptManagerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    @Override
    public DeptManager queryById(Integer empNo) {
        return this.deptManagerDao.queryById(empNo);
    }


    /**
     * 新增数据
     *
     * @param deptManager 实例对象
     * @return 实例对象
     */
    @Override
    public DeptManager insert(DeptManager deptManager) {
        this.deptManagerDao.insert(deptManager);
        return deptManager;
    }

    /**
     * 修改数据
     *
     * @param deptManager 实例对象
     * @return 实例对象
     */
    @Override
    public DeptManager update(DeptManager deptManager) {
        this.deptManagerDao.update(deptManager);
        return this.queryById(deptManager.getEmpNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer empNo) {
        return this.deptManagerDao.deleteById(empNo) > 0;
    }
}
