package org.zhuo.zexample.es.base.service.impl;

import org.springframework.stereotype.Service;
import org.zhuo.zexample.es.base.entity.Titles;
import org.zhuo.zexample.es.base.mapper.TitlesDao;
import org.zhuo.zexample.es.base.service.TitlesService;

import javax.annotation.Resource;

/**
 * (Titles)表服务实现类
 *
 * @author makejava
 * @since 2024-01-14 15:55:49
 */
@Service("titlesService")
public class TitlesServiceImpl implements TitlesService {
    @Resource
    private TitlesDao titlesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    @Override
    public Titles queryById(Integer empNo) {
        return this.titlesDao.queryById(empNo);
    }



    /**
     * 新增数据
     *
     * @param titles 实例对象
     * @return 实例对象
     */
    @Override
    public Titles insert(Titles titles) {
        this.titlesDao.insert(titles);
        return titles;
    }

    /**
     * 修改数据
     *
     * @param titles 实例对象
     * @return 实例对象
     */
    @Override
    public Titles update(Titles titles) {
        this.titlesDao.update(titles);
        return this.queryById(titles.getEmpNo());
    }

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer empNo) {
        return this.titlesDao.deleteById(empNo) > 0;
    }
}
