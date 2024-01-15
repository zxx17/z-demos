package org.zhuo.zexample.es.base.mapper;

import org.apache.ibatis.annotations.Param;
import org.zhuo.zexample.es.base.entity.Titles;

import java.util.List;

/**
 * (Titles)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-14 15:55:49
 */
public interface TitlesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param empNo 主键
     * @return 实例对象
     */
    Titles queryById(Integer empNo);



    /**
     * 统计总行数
     *
     * @param titles 查询条件
     * @return 总行数
     */
    long count(Titles titles);

    /**
     * 新增数据
     *
     * @param titles 实例对象
     * @return 影响行数
     */
    int insert(Titles titles);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Titles> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Titles> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Titles> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Titles> entities);

    /**
     * 修改数据
     *
     * @param titles 实例对象
     * @return 影响行数
     */
    int update(Titles titles);

    /**
     * 通过主键删除数据
     *
     * @param empNo 主键
     * @return 影响行数
     */
    int deleteById(Integer empNo);

}

