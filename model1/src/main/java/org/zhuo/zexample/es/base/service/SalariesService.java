package org.zhuo.zexample.es.base.service;

import org.zhuo.zexample.es.base.entity.Salaries;

/**
 * (Salaries)表服务接口
 *
 * @author makejava
 * @since 2024-01-14 15:55:23
 */
public interface SalariesService {

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    Salaries queryById(Integer empNo);



    /**
     * 新增数据
     *
     * @param salaries 实例对象
     * @return 实例对象
     */
    Salaries insert(Salaries salaries);

    /**
     * 修改数据
     *
     * @param salaries 实例对象
     * @return 实例对象
     */
    Salaries update(Salaries salaries);

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 是否成功
     */
    boolean deleteById(Integer empNo);

}
