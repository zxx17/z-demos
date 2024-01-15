package org.zhuo.zexample.es.base.service;

import org.zhuo.zexample.es.base.entity.Titles;

/**
 * (Titles)表服务接口
 *
 * @author makejava
 * @since 2024-01-14 15:55:49
 */
public interface TitlesService {

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    Titles queryById(Integer empNo);


    /**
     * 新增数据
     *
     * @param titles 实例对象
     * @return 实例对象
     */
    Titles insert(Titles titles);

    /**
     * 修改数据
     *
     * @param titles 实例对象
     * @return 实例对象
     */
    Titles update(Titles titles);

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer empNo);

}
