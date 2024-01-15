package org.zhuo.zexample.es.base.service.impl;

import org.springframework.stereotype.Service;
import org.zhuo.zexample.es.base.entity.Salaries;
import org.zhuo.zexample.es.base.service.SalariesService;
import org.zhuo.zexample.es.base.mapper.SalariesDao;

import javax.annotation.Resource;

/**
 * (Salaries)表服务实现类
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
@Service("salariesService")
public class SalariesServiceImpl implements SalariesService {
    @Resource
    private SalariesDao salariesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    @Override
    public Salaries queryById(Integer empNo) {
        return this.salariesDao.queryById(empNo);
    }



    /**
     * 新增数据
     *
     * @param salaries 实例对象
     * @return 实例对象
     */
    @Override
    public Salaries insert(Salaries salaries) {
        this.salariesDao.insert(salaries);
        return salaries;
    }

    /**
     * 修改数据
     *
     * @param salaries 实例对象
     * @return 实例对象
     */
    @Override
    public Salaries update(Salaries salaries) {
        this.salariesDao.update(salaries);
        return this.queryById(salaries.getEmpNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer empNo) {
        return this.salariesDao.deleteById(empNo) > 0;
    }
}
